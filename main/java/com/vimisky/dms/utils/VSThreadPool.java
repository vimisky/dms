package com.vimisky.dms.utils;

import java.util.LinkedList;
import org.apache.log4j.Logger;

/**
 * CopyRight 2015-2020 : <Vimisky>
 * Project : DMS
 * ModuleID : 
 * Comments : 
 * JDK version used : 1.6
 * Namespace : 
 * Author : weihaitao
 * Create Date : 2015-02-01
 * Version : 0.1
 * 网页抓取线程池
 * 处理流程：
 * 1.初始化线程池，设置ThreadGroup名称，设置为守护线程模式
 * 2.初始化工作线程队列
 * 3.创建指定数量的Thread并开始执行
 * 
 * Thread执行过程：
 * 1.从workQueue中获取Runnable对象，如果获取不到，等待
 * 2.获取完成后，执行，结束该线程。
 * 
 * */

public class VSThreadPool extends ThreadGroup {

	private static Logger logger = Logger.getLogger(VSThreadPool.class);//Log4j日志
	
	private boolean isClosed = false;//线程池开启标志
	private int poolSize;//线程池大小
	private LinkedList<Runnable> workQueue;//工作线程队列，使用LinkedList类（实现了Queue接口）

	/*
	 * 构造方法：
	 * 方法描述：创建网页抓取线程池时使用
	 * 
	 * @param String name
	 * @param int poolSize
	 * 
	 * @return PageCrawlerThreadPool
	 * */
	public VSThreadPool(String name, int poolSize) {
		// TODO Auto-generated constructor stub
		super(name);
		setDaemon(true);//设置守护线程模式
		this.poolSize = poolSize;
		
		workQueue = new LinkedList<Runnable>();//工作线程队列初始化
		
		//启动线程池中指定数量的线程，进入等待状态
		for (int i = 0; i < poolSize; i++) {
			new VSThread(i).start();
		}
	}
	/* 互斥方法：一次只能有一个线程访问该对象的execute()方法
	 * 方法描述：执行任务
	 * 处理流程：
	 * 1.向workQueue中增加任务
	 * 2.通知this对象
	 * 同步锁方法，
	 * 是对ThreadGroup对象锁定？还是对此方法锁定？
	 * 
	 * @param Runnable task 任务
	 * @return void
	 * */
	public synchronized void execute(Runnable task) {
		if (isClosed) {
			throw new IllegalStateException();
		}
		if (null != task) {
			workQueue.add(task);
			notify();
		}
	}
	/* 互斥方法：一次只能有一个线程访问该对象的getTask()方法
	 * 方法描述：获取任务
	 * 处理流程：
	 * 1.等待，直到从workQueue中获取任务
	 * 
	 * @param int threadID 线程ID
	 * @return Runnable 任务
	 * */	
	private synchronized Runnable getTask(int threadID) throws InterruptedException {
		while (workQueue.size() == 0) {
			if (isClosed) {
				return null;
			}
			logger.info("Work Thread "+threadID+" is Waiting for Task" );
			wait();
		}
		logger.info("Work Thread "+threadID+" Start Run!");
		
		return workQueue.removeFirst();//workQueue的队列Queue接口
	}
	/* 互斥方法：一次只能有一个线程访问该对象的closePool()方法
	 * 关闭线程池
	 * 流程：
	 * 1.等待全部线程结束
	 * 2.设置标志变量isClosed
	 * 3.清空workQueue
	 * 4.发出ThreadGroup.interrupt()命令
	 * 
	 * @param null
	 * @return void
	 * */		
	public synchronized void closePool() {
		if (!isClosed) {
			waitFinish();
			isClosed = true;
			workQueue.clear();//清空工作线程Queue
			interrupt();//ThreadGroup.interrupt，中断标志 
		}
	}
	/* 等待所有线程关闭
	 * 流程：
	 * 1.检查并通知ThreadGroup对象
	 * 2.统计活动状态的Thread
	 * 3.关闭活动状态的Thread
	 * 
	 * 创建这些线程，并join，是什么意思？
	 * 
	 * @param null
	 * @return void
	 * */			
	public void waitFinish() {
		synchronized (this) {
			isClosed = true;
			notifyAll();//通知所有线程isClosed标志更改
		}
		Thread[] threads = new Thread[this.activeCount()];//计算活动线程
		int count = this.enumerate(threads);//ThreadGroup方法，将活动线程赋值到Thread中
		for (int i = 0; i < count; i++) {
			try {
				threads[i].join();//等待活动线程执行结束
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/* 私有类
	 * 继承自Thread
	 * 
	 * 线程run处理流程：
	 * 1. 从workQueue获取任务
	 * 2. 如果没有获取，一直等待，直到有任务或者被interrupt为止
	 * 
	 * */
	private class VSThread extends Thread {
		
		private int threadID;
		
		public VSThread(int threadID){
			super(threadID+"");
			this.threadID = threadID;
		}
		
		public void run() {
			while (!isInterrupted()) {
				Runnable task = null;
				try {
					task = getTask(threadID);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(task == null) return;
				task.run();
			}//while(!isInterrupted)
		}//run()
		
		
	}
}
