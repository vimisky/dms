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
 * ��ҳץȡ�̳߳�
 * �������̣�
 * 1.��ʼ���̳߳أ�����ThreadGroup���ƣ�����Ϊ�ػ��߳�ģʽ
 * 2.��ʼ�������̶߳���
 * 3.����ָ��������Thread����ʼִ��
 * 
 * Threadִ�й��̣�
 * 1.��workQueue�л�ȡRunnable���������ȡ�������ȴ�
 * 2.��ȡ��ɺ�ִ�У��������̡߳�
 * 
 * */

public class VSThreadPool extends ThreadGroup {

	private static Logger logger = Logger.getLogger(VSThreadPool.class);//Log4j��־
	
	private boolean isClosed = false;//�̳߳ؿ�����־
	private int poolSize;//�̳߳ش�С
	private LinkedList<Runnable> workQueue;//�����̶߳��У�ʹ��LinkedList�ࣨʵ����Queue�ӿڣ�

	/*
	 * ���췽����
	 * ����������������ҳץȡ�̳߳�ʱʹ��
	 * 
	 * @param String name
	 * @param int poolSize
	 * 
	 * @return PageCrawlerThreadPool
	 * */
	public VSThreadPool(String name, int poolSize) {
		// TODO Auto-generated constructor stub
		super(name);
		setDaemon(true);//�����ػ��߳�ģʽ
		this.poolSize = poolSize;
		
		workQueue = new LinkedList<Runnable>();//�����̶߳��г�ʼ��
		
		//�����̳߳���ָ���������̣߳�����ȴ�״̬
		for (int i = 0; i < poolSize; i++) {
			new VSThread(i).start();
		}
	}
	/* ���ⷽ����һ��ֻ����һ���̷߳��ʸö����execute()����
	 * ����������ִ������
	 * �������̣�
	 * 1.��workQueue����������
	 * 2.֪ͨthis����
	 * ͬ����������
	 * �Ƕ�ThreadGroup�������������ǶԴ˷���������
	 * 
	 * @param Runnable task ����
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
	/* ���ⷽ����һ��ֻ����һ���̷߳��ʸö����getTask()����
	 * ������������ȡ����
	 * �������̣�
	 * 1.�ȴ���ֱ����workQueue�л�ȡ����
	 * 
	 * @param int threadID �߳�ID
	 * @return Runnable ����
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
		
		return workQueue.removeFirst();//workQueue�Ķ���Queue�ӿ�
	}
	/* ���ⷽ����һ��ֻ����һ���̷߳��ʸö����closePool()����
	 * �ر��̳߳�
	 * ���̣�
	 * 1.�ȴ�ȫ���߳̽���
	 * 2.���ñ�־����isClosed
	 * 3.���workQueue
	 * 4.����ThreadGroup.interrupt()����
	 * 
	 * @param null
	 * @return void
	 * */		
	public synchronized void closePool() {
		if (!isClosed) {
			waitFinish();
			isClosed = true;
			workQueue.clear();//��չ����߳�Queue
			interrupt();//ThreadGroup.interrupt���жϱ�־ 
		}
	}
	/* �ȴ������̹߳ر�
	 * ���̣�
	 * 1.��鲢֪ͨThreadGroup����
	 * 2.ͳ�ƻ״̬��Thread
	 * 3.�رջ״̬��Thread
	 * 
	 * ������Щ�̣߳���join����ʲô��˼��
	 * 
	 * @param null
	 * @return void
	 * */			
	public void waitFinish() {
		synchronized (this) {
			isClosed = true;
			notifyAll();//֪ͨ�����߳�isClosed��־����
		}
		Thread[] threads = new Thread[this.activeCount()];//�����߳�
		int count = this.enumerate(threads);//ThreadGroup����������̸߳�ֵ��Thread��
		for (int i = 0; i < count; i++) {
			try {
				threads[i].join();//�ȴ���߳�ִ�н���
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/* ˽����
	 * �̳���Thread
	 * 
	 * �߳�run�������̣�
	 * 1. ��workQueue��ȡ����
	 * 2. ���û�л�ȡ��һֱ�ȴ���ֱ����������߱�interruptΪֹ
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
