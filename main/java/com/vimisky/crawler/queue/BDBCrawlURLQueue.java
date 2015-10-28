package com.vimisky.crawler.queue;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import com.sleepycat.je.Cursor;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;
import com.vimisky.crawler.datamodel.CrawlURL;
import com.vimisky.crawler.datamodel.CrawlURLTupleBinding;
@Deprecated
/**
 * 统一由BDBWorkQueue托管
 * **/

public class BDBCrawlURLQueue {

	private static Logger logger = Logger.getLogger(BDBCrawlURLQueue.class);
	
	private Database database = null;
	private CrawlURLTupleBinding valueBinding = null;
	
	public BDBCrawlURLQueue(Database db) {
		super();
		this.database = db;
		this.valueBinding = new CrawlURLTupleBinding();
	}

	public void put(CrawlURL crawlURL){
		String keyString = crawlURL.getKeyString();
		byte[] keyBytes = null;
		try {
			keyBytes = keyString.getBytes("UTF-8");
			DatabaseEntry key = new DatabaseEntry(keyBytes);
			DatabaseEntry value = new DatabaseEntry();
			valueBinding.objectToEntry(crawlURL, value);
			this.database.put(null, key, value);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void delete(CrawlURL crawlURL) {
		String keyString = crawlURL.getKeyString();
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
	public CrawlURL get(DatabaseEntry key) {
		
		DatabaseEntry value = new DatabaseEntry();
		this.database.get(null, key, value, LockMode.DEFAULT);
		CrawlURL crawlURL = (CrawlURL) valueBinding.entryToObject(value);

		return crawlURL;
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
	
	public List<CrawlURL> getAll() {
		List<CrawlURL> crawlURLs = new LinkedList<CrawlURL>();
		DatabaseEntry key = new DatabaseEntry();
		DatabaseEntry value = new DatabaseEntry();
		Cursor cursor = this.database.openCursor(null, null);
		OperationStatus operationStatus = cursor.getFirst(key, value, LockMode.DEFAULT);
		if (operationStatus == OperationStatus.SUCCESS) {
			if (key.getData().length > 0 && value.getData().length>0) {
//				crawlURLs.add(valueBinding.entryToObject(value));
				try {
					logger.info("key:"+ new String(key.getData(),"UTF-8")+ "  value:"+new String(value.getData(),"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			while (OperationStatus.SUCCESS == cursor.getNext(key, value, LockMode.DEFAULT)) {
				if (key.getData().length>0 && value.getData().length>0) {
					crawlURLs.add(valueBinding.entryToObject(value));
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
		return crawlURLs;
	}
	
	public static void main(String[] args) {
		CrawlURL crawlURL = new CrawlURL();

	}
}
