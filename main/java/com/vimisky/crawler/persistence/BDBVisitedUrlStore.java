package com.vimisky.crawler.persistence;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sleepycat.je.Cursor;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;
import com.vimisky.crawler.common.BDBhelper;
import com.vimisky.crawler.datamodel.CrawlURL;
import com.vimisky.crawler.datamodel.CrawlURLTupleBinding;
import com.vimisky.crawler.queue.BDBWorkQueue;

public class BDBVisitedUrlStore implements VisitedUrlStore {

	private static Logger logger = Logger.getLogger(BDBWorkQueue.class);
	
	private Database visitedUrlDB = null;
	private CrawlURLTupleBinding valueBinding = null;
	private static BDBVisitedUrlStore _bdbVisitedUrlStore = null;
	
	private BDBVisitedUrlStore(String visitedUrlDBName) {
		super();
		this.visitedUrlDB = BDBhelper.getInstance().openDatabase(visitedUrlDBName);
		this.valueBinding = new CrawlURLTupleBinding();
	}
	
	public static BDBVisitedUrlStore getInstance() {
		if (_bdbVisitedUrlStore != null) {
			return _bdbVisitedUrlStore;
		}
		_bdbVisitedUrlStore = new BDBVisitedUrlStore("visitedUrl");
		return _bdbVisitedUrlStore;
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		visitedUrlDB.sync();
		visitedUrlDB.close();
		super.finalize();
	}
	
	@Override
	public boolean exists(CrawlURL crawlURL) {
		// TODO Auto-generated method stub
		boolean result = false;
		String keyString =  crawlURL.getKeyString();
		byte[] keyBytes = null;
		try {
			keyBytes = keyString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DatabaseEntry key = new DatabaseEntry(keyBytes);
		DatabaseEntry value = new DatabaseEntry();
		OperationStatus operationStatus = this.visitedUrlDB.get(null, key, value, LockMode.DEFAULT);
		if (operationStatus == OperationStatus.SUCCESS || operationStatus == OperationStatus.KEYEXIST) {
			result = true;
		}
		return result;
	}
	
	@Override
	public void add(CrawlURL crawlURL) {
		// TODO Auto-generated method stub
		String keyString = crawlURL.getKeyString();
		byte[] keyBytes = null;
		try {
			keyBytes = keyString.getBytes("UTF-8");
			DatabaseEntry key = new DatabaseEntry(keyBytes);
			DatabaseEntry value = new DatabaseEntry();
			valueBinding.objectToEntry(crawlURL, value);
			this.visitedUrlDB.put(null, key, value);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(String keyString) {
		// TODO Auto-generated method stub
		byte[] keyBytes = null;
		try {
			keyBytes = keyString.getBytes("UTF-8");
			DatabaseEntry key = new DatabaseEntry(keyBytes);
			this.visitedUrlDB.delete(null, key);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(String keyString, CrawlURL crawlURL) {
		// TODO Auto-generated method stub
		byte[] keyBytes = null;
		try {
			keyBytes = keyString.getBytes("UTF-8");
			DatabaseEntry key = new DatabaseEntry(keyBytes);
			DatabaseEntry value = new DatabaseEntry();
			valueBinding.objectToEntry(crawlURL, value);
			this.visitedUrlDB.put(null, key, value);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public CrawlURL find(String keyString) {
		// TODO Auto-generated method stub
		byte[] keyBytes = null;
		try {
			keyBytes = keyString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DatabaseEntry key = new DatabaseEntry(keyBytes);
		DatabaseEntry value = new DatabaseEntry();
		this.visitedUrlDB.get(null, key, value, LockMode.DEFAULT);
		CrawlURL crawlURL = (CrawlURL) valueBinding.entryToObject(value);

		return crawlURL;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		this.visitedUrlDB.getEnvironment().truncateDatabase(null, this.visitedUrlDB.getDatabaseName(), true);
	}

	@Override
	public List<CrawlURL> findAll() {
		// TODO Auto-generated method stub

		List<CrawlURL> bs = new LinkedList<CrawlURL>();
		DatabaseEntry key = new DatabaseEntry();
		DatabaseEntry value = new DatabaseEntry();
		Cursor cursor = this.visitedUrlDB.openCursor(null, null);
		OperationStatus operationStatus = cursor.getFirst(key, value, LockMode.DEFAULT);
		if (operationStatus == OperationStatus.SUCCESS) {
			if (key.getData().length > 0 && value.getData().length>0) {
//				bs.add(valueBinding.entryToObject(value));
				try {
					logger.info("key:"+ new String(key.getData(),"UTF-8")+ "  value:"+new String(value.getData(),"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			while (OperationStatus.SUCCESS == cursor.getNext(key, value, LockMode.DEFAULT)) {
				if (key.getData().length>0 && value.getData().length>0) {
					bs.add(valueBinding.entryToObject(value));
				}
			}
		}
		cursor.close();
		return bs;				
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		if (this.visitedUrlDB!=null) {
			this.visitedUrlDB.sync();
		}
	}
	
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return this.visitedUrlDB.count();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}







}
