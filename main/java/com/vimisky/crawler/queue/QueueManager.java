package com.vimisky.crawler.queue;

import java.util.LinkedList;
import java.util.Queue;

import com.sleepycat.bind.tuple.TupleBinding;
import com.sleepycat.je.Database;
import com.vimisky.crawler.common.BDBhelper;
import com.vimisky.crawler.datamodel.BDBDataModelInterface;
import com.vimisky.crawler.datamodel.CrawlArticle;
import com.vimisky.crawler.datamodel.CrawlArticleTupleBinding;
import com.vimisky.crawler.datamodel.CrawlURL;
import com.vimisky.crawler.datamodel.CrawlURLTupleBinding;

public class QueueManager {

	private enum QueueType {
		JAVA_LINKED_LIST, DATABASE_BDB;
	}
	
//	private static final QueueType queueType = QueueType.JAVA_LINKED_LIST;
	private static final QueueType queueType = QueueType.DATABASE_BDB;

	private static QueueManager _queueManager;
	
	private Queue<CrawlURL> pendingFilterUrlQueue = null;
	private Queue<CrawlURL> pendingCrawlUrlQueue = null;
	private Queue<CrawlArticle> pendingParseArticleQueue = null;
	private Queue<CrawlArticle> readyArticleQueue = null;
	
	private QueueManager(){
		super();
	}
	
	public static QueueManager getInstance(){
		if (_queueManager != null) {
			return _queueManager;
		}
		_queueManager = new QueueManager();
		if (queueType == QueueType.JAVA_LINKED_LIST) {
			_queueManager.pendingCrawlUrlQueue = new LinkedList<CrawlURL>();
			_queueManager.pendingFilterUrlQueue = new LinkedList<CrawlURL>();
			_queueManager.pendingParseArticleQueue = new LinkedList<CrawlArticle>();
			_queueManager.readyArticleQueue = new LinkedList<CrawlArticle>();
			return _queueManager;
		}else if (queueType == QueueType.DATABASE_BDB) {
			Database pendingCrawlUrl = BDBhelper.getInstance().openDatabase("pendingCrawlUrl");
			TupleBinding<CrawlURL> crawlURLTupleBinding = new CrawlURLTupleBinding();
			_queueManager.pendingCrawlUrlQueue = new BDBWorkQueue<CrawlURL>(pendingCrawlUrl, crawlURLTupleBinding);
			
			Database pendingFilterUrl = BDBhelper.getInstance().openDatabase("pendingFilterUrl");
			TupleBinding<CrawlURL> filterURLTupleBinding = new CrawlURLTupleBinding();
			_queueManager.pendingFilterUrlQueue = new BDBWorkQueue<CrawlURL>(pendingFilterUrl, filterURLTupleBinding);
			
			Database pendingParseArticle = BDBhelper.getInstance().openDatabase("pendingParseArticle");
			TupleBinding<CrawlArticle> parseArticleTupleBinding = new CrawlArticleTupleBinding();
			_queueManager.pendingParseArticleQueue = new BDBWorkQueue<CrawlArticle>(pendingParseArticle, parseArticleTupleBinding);
			
			Database readyArticle = BDBhelper.getInstance().openDatabase("readyArticle");
			TupleBinding<CrawlArticle> readyArticleTupleBinding = new CrawlArticleTupleBinding();
			_queueManager.readyArticleQueue = new BDBWorkQueue<CrawlArticle>(readyArticle, readyArticleTupleBinding);
			return _queueManager;
		}else {
			return null;
		}

	}
	public void makePersistence(BDBWorkQueue<BDBDataModelInterface> bQueue) {
		bQueue.getDatabase().sync();
	}
	/**
	 * 析构方法中加入关闭数据库的操作
	 * **/
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		if (queueType == QueueType.DATABASE_BDB) {
			BDBWorkQueue<CrawlURL> bdbPendingCrawlURLQueue =(BDBWorkQueue<CrawlURL>) this.pendingCrawlUrlQueue;
			BDBhelper.getInstance().closeDatabase(bdbPendingCrawlURLQueue.getDatabase());
			BDBWorkQueue<CrawlURL> bdbPendingFilterURLQueue =(BDBWorkQueue<CrawlURL>) this.pendingFilterUrlQueue;
			BDBhelper.getInstance().closeDatabase(bdbPendingFilterURLQueue.getDatabase());	
			BDBWorkQueue<CrawlArticle> bdbPendingParseArticleQueue =(BDBWorkQueue<CrawlArticle>) this.pendingParseArticleQueue;
			BDBhelper.getInstance().closeDatabase(bdbPendingParseArticleQueue.getDatabase());
			BDBWorkQueue<CrawlArticle> bdbReadyArticleQueue =(BDBWorkQueue<CrawlArticle>) this.readyArticleQueue;
			BDBhelper.getInstance().closeDatabase(bdbReadyArticleQueue.getDatabase());		
		}
		super.finalize();
	}
	
	public Queue<CrawlURL> getPendingFilterUrlQueue(){
		
		return pendingFilterUrlQueue;
	}
	
	public Queue<CrawlURL> getPendingCrawlUrlQueue(){
		
		return pendingCrawlUrlQueue;
	}
	
	public Queue<CrawlArticle> getPendingParseArticleQueue(){
		
		return pendingParseArticleQueue;
	}
	
	public Queue<CrawlArticle> getReadyArticleQueue(){
		
		return readyArticleQueue;
	}
	
	
}
