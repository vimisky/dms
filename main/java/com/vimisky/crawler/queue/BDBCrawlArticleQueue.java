package com.vimisky.crawler.queue;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.sleepycat.je.Cursor;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;
import com.vimisky.crawler.common.BDBhelper;
import com.vimisky.crawler.datamodel.CrawlArticle;
import com.vimisky.crawler.datamodel.CrawlArticleTupleBinding;
@Deprecated
/**
 * 统一由BDBWorkQueue托管
 * **/
public class BDBCrawlArticleQueue {
	private static Logger logger = Logger.getLogger(BDBCrawlArticleQueue.class);
	
	private Database database = null;
	private CrawlArticleTupleBinding valueBinding = null;
	
	public BDBCrawlArticleQueue(Database db) {
		super();
		this.database = db;
		this.valueBinding = new CrawlArticleTupleBinding();
	}

	public void put(CrawlArticle crawlArticle){
		String keyString = crawlArticle.getKeyString();
		byte[] keyBytes = null;
		try {
			keyBytes = keyString.getBytes("UTF-8");
			DatabaseEntry key = new DatabaseEntry(keyBytes);
			DatabaseEntry value = new DatabaseEntry();
			valueBinding.objectToEntry(crawlArticle, value);
			this.database.put(null, key, value);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void delete(CrawlArticle crawlArticle) {
		String keyString = crawlArticle.getKeyString();
		byte[] keyBytes = null;
		try {
			keyBytes = keyString.getBytes("UTF-8");
			DatabaseEntry key = new DatabaseEntry(keyBytes);
			this.database.delete(null, key);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public CrawlArticle get(DatabaseEntry key) {
		
		DatabaseEntry value = new DatabaseEntry();
		this.database.get(null, key, value, LockMode.DEFAULT);
		CrawlArticle crawlArticle = (CrawlArticle) valueBinding.entryToObject(value);

		return crawlArticle;
	}

	public DatabaseEntry getFirstKey() {
		DatabaseEntry key = new DatabaseEntry();
		DatabaseEntry value = new DatabaseEntry();
		Cursor cursor = this.database.openCursor(null, null);
		OperationStatus operationStatus = cursor.getFirst(key, value, LockMode.DEFAULT);
		
		cursor.close();
		if (operationStatus == OperationStatus.SUCCESS) {
			return key;
		}else {
			logger.error("no first key");
			return null;
		}
		
	}
	
	public List<CrawlArticle> getAll() {
		List<CrawlArticle> crawlArticles = new LinkedList<CrawlArticle>();
		DatabaseEntry key = new DatabaseEntry();
		DatabaseEntry value = new DatabaseEntry();
		Cursor cursor = this.database.openCursor(null, null);
		OperationStatus operationStatus = cursor.getFirst(key, value, LockMode.DEFAULT);
		if (operationStatus == OperationStatus.SUCCESS) {
			if (key.getData().length > 0 && value.getData().length>0) {
//				crawlArticles.add(valueBinding.entryToObject(value));
				try {
					logger.info("key:"+ new String(key.getData(),"UTF-8")+ "  value:"+new String(value.getData(),"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			while (OperationStatus.SUCCESS == cursor.getNext(key, value, LockMode.DEFAULT)) {
				if (key.getData().length>0 && value.getData().length>0) {
					crawlArticles.add(valueBinding.entryToObject(value));
					try {
						logger.info("key:"+ new String(key.getData(),"UTF-8")+ "  value:"+new String(value.getData(),"UTF-8"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		cursor.close();
		return crawlArticles;
	}
		
	
	public static void main(String[] args) {
		CrawlArticle crawlArticle = new CrawlArticle();
		crawlArticle.setTitle("这是一篇测试稿件12");
//		crawlArticle.setContent("这是一篇测试稿件正文");
		crawlArticle.setCrawltime(new Date()) ;
		crawlArticle.setAuthors("weihaitao");
		crawlArticle.setSourceArticleUrl("mobile.xinhua-news.com/article/2015/02/07/013.xml");
		crawlArticle.setSourceSiteName("home");
		crawlArticle.setSourceSiteUrl("mobile.xinhua-news.com");
		crawlArticle.setSubject("这是一篇稿件的摘要");
		crawlArticle.setTags("测试");
//
//		logger.info("key MD5:"+crawlArticle.getKeyString());
//		BDBCrawlArticleQueue bdbCrawlArticleQueue = new BDBCrawlArticleQueue();
//		bdbCrawlArticleQueue.initQueue("pendingArticle");
//		bdbCrawlArticleQueue.add(crawlArticle);
//		CrawlArticle getCrawlArticle = bdbCrawlArticleQueue.peekItem();
//		DatabaseEntry key = new DatabaseEntry(crawlArticle.getTitle().getBytes());
//		CrawlArticle getCrawlArticle = bdbCrawlArticleQueue.peekItem(key);

//		logger.info("crawl Article title :"+ getCrawlArticle.getTitle());
//		logger.info("crawl Aritcle content :"+ getCrawlArticle.getContent());
//		try {
//			bdbCrawlArticleQueue.peekAll();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		bdbCrawlArticleQueue.closeQueue();
		Database db = BDBhelper.getInstance().openDatabase("pendingArticle");
		BDBCrawlArticleQueue bdbCrawlArticleQueue = new BDBCrawlArticleQueue(db);
		bdbCrawlArticleQueue.put(crawlArticle);
		bdbCrawlArticleQueue.getAll();
		BDBhelper.getInstance().closeDatabase(db);
	}
}
