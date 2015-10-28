package com.vimisky.crawler.queue;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.apache.log4j.Logger;
import com.sleepycat.bind.tuple.TupleBinding;
import com.sleepycat.je.Cursor;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;
import com.vimisky.crawler.datamodel.BDBDataModelInterface;

public class BDBWorkQueue<B extends BDBDataModelInterface> implements Queue<B>{
	
	private static Logger logger = Logger.getLogger(BDBWorkQueue.class);
	
	private Database database = null;
	private TupleBinding<B> valueBinding = null;
	
	public BDBWorkQueue(Database db, TupleBinding<B> p) {
		super();
		this.database = db;
		this.valueBinding = p;
	}

	/**
	 * @return the database
	 */
	public Database getDatabase() {
		return database;
	}

	/**
	 * @param database the database to set
	 */
	public void setDatabase(Database database) {
		this.database = database;
	}

	public void put(B b){
		String keyString = this.getKeyStringOf(b);
		byte[] keyBytes = null;
		try {
			keyBytes = keyString.getBytes("UTF-8");
			DatabaseEntry key = new DatabaseEntry(keyBytes);
			DatabaseEntry value = new DatabaseEntry();
			valueBinding.objectToEntry(b, value);
			this.database.put(null, key, value);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void delete(B b) {
		String keyString = this.getKeyStringOf(b);
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
	public B get(DatabaseEntry key) {
		
		DatabaseEntry value = new DatabaseEntry();
		this.database.get(null, key, value, LockMode.DEFAULT);
		B b = (B) valueBinding.entryToObject(value);

		return b;
	}

	public DatabaseEntry getFirstKey() {
		DatabaseEntry key = new DatabaseEntry();
		DatabaseEntry value = new DatabaseEntry();
		Cursor cursor = this.database.openCursor(null, null);
		OperationStatus operationStatus = cursor.getFirst(key, value, LockMode.DEFAULT);
		
		if (operationStatus == OperationStatus.SUCCESS) {
			cursor.getNext(key, value, LockMode.DEFAULT);
		}else {
			logger.error("no first key");
			key = null;
		}
		cursor.close();

		return key;
	}
	
	public List<B> getAll() {
		List<B> bs = new LinkedList<B>();
		DatabaseEntry key = new DatabaseEntry();
		DatabaseEntry value = new DatabaseEntry();
		Cursor cursor = this.database.openCursor(null, null);
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
//					try {
//						logger.info("key:"+ new String(key.getData(),"UTF-8")+ "  value:"+new String(value.getData(),"UTF-8"));
//					} catch (UnsupportedEncodingException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}
			}
		}
		cursor.close();
		return bs;
	}

	private String getKeyStringOf(B b) {
		return b.getKeyString();
	}
	
	@Override
	public synchronized int size() {
		// TODO Auto-generated method stub
		return (int)this.database.count();
	}

	@Override
	public synchronized boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.database.count()>0?true:false;
	}

	/**
	 * not test
	 * **/
	@Override
	public synchronized boolean contains(Object o) {
		// TODO Auto-generated method stub
		boolean result = false;
		if (Object.class.isInstance(BDBDataModelInterface.class)) {
			logger.info("ÊôÓÚ BDBDataModelInterface ÀàÐÍ£¡£¡£¡£¡£¡£¡£¡");
			B b = (B)o;
			String keyString = b.getKeyString();
			byte[] keyBytes = null;
			try {
				keyBytes = keyString.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = this.get(new DatabaseEntry(keyBytes)) != null ? true:false;
		}
		
		return result;
	}

	@Override
	public Iterator<B> iterator() {
		// TODO Auto-generated method stub
		return this.getAll().iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return this.getAll().toArray();
	}
	/**
	 * not implement
	 * **/
	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * not implement completely
	 * **/
	@Override
	public synchronized boolean remove(Object o) {
		// TODO Auto-generated method stub
		B b = (B)o;
		String keyString = this.getKeyStringOf(b);
		byte[] keyBytes = null;
		try {
			keyBytes = keyString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.delete((B)o);
		
		return this.get(new DatabaseEntry(keyBytes)) == null ? true:false;
	}
	/**
	 * not implement
	 * **/
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * not implement
	 * **/
	@Override
	public boolean addAll(Collection<? extends B> c) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * not implement
	 * **/
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * not implement
	 * **/
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public synchronized void clear() {
		// TODO Auto-generated method stub
		this.database.getEnvironment().truncateDatabase(null, this.database.getDatabaseName(), true);
		
	}

	@Override
	public synchronized boolean add(B b) {
		// TODO Auto-generated method stub
		String keyString = this.getKeyStringOf(b);
		byte[] keyBytes = null;
		try {
			keyBytes = keyString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.put(b);
		return this.get(new DatabaseEntry(keyBytes)) != null ? true:false;
	}

	@Override
	public synchronized boolean offer(B b) {
		// TODO Auto-generated method stub
		String keyString = this.getKeyStringOf(b);
		byte[] keyBytes = null;
		try {
			keyBytes = keyString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.put(b);
		
		return this.get(new DatabaseEntry(keyBytes)) != null ? true:false;
	}

	@Override
	public synchronized B remove() {
		// TODO Auto-generated method stub
		DatabaseEntry key = this.getFirstKey();
		if (key == null) {
			return null;
		}
		B b = this.get(key);
		this.delete(b);
		return b;
	}

	@Override
	public synchronized B poll() {
		// TODO Auto-generated method stub
		DatabaseEntry key = this.getFirstKey();
		if (key == null) {
			return null;
		}
		B b = this.get(key);
		this.delete(b);
		return b;
	}

	@Override
	public synchronized B element() {
		// TODO Auto-generated method stub
		DatabaseEntry key = this.getFirstKey();
		if (key == null) {
			return null;
		}
		B b = this.get(key);
		return b;
	}

	@Override
	public synchronized B peek() {
		// TODO Auto-generated method stub
		DatabaseEntry key = this.getFirstKey();
		if (key == null) {
			return null;
		}
		B b = this.get(key);
		return b;
	}
}
