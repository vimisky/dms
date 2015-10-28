package com.vimisky.crawler.common;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;

public class BDBhelper {

	private static Logger logger = Logger.getLogger(BDBhelper.class);
	
	private static BDBhelper _bdbHelper = null;
	private Environment environment = null;
	
	private BDBhelper(){
		super();
	}
	
	public static BDBhelper getInstance() {
		if (_bdbHelper != null) {
			return _bdbHelper;
		}
		_bdbHelper = new BDBhelper();
		EnvironmentConfig eConfig = new EnvironmentConfig();
		//EnviromentConfig config
		eConfig.setAllowCreate(true);
		_bdbHelper.environment = new Environment(new File("F://cms/bdb_data"), eConfig);
		
		return _bdbHelper;
	}
	

	public Database openDatabase(String name) {
		
		DatabaseConfig databaseConfig = new DatabaseConfig();
		databaseConfig.setAllowCreate(true);
		databaseConfig.setDeferredWrite(true);
		
		Database database = environment.openDatabase(null, name, databaseConfig);
		
		return database;
	}
	public void closeDatabase(Database db) {
		db.sync();
		db.close();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Database testDatabase = BDBhelper.getInstance().openDatabase("test");
		DatabaseEntry key = new DatabaseEntry("weihaitao".getBytes());
//		DatabaseEntry value = new DatabaseEntry("haoren".getBytes());
//		testDatabase.put(null, key, value);
		DatabaseEntry value = new DatabaseEntry();
		OperationStatus operationStatus = testDatabase.get(null, key, value, LockMode.DEFAULT);
		if (operationStatus.equals(OperationStatus.SUCCESS)) {
			byte[] theDataByte = value.getData();
			String theDataString = new String(theDataByte);
			logger.info("got value:"+theDataString);
		}else {
			logger.warn("non value");
		}
		BDBhelper.getInstance().closeDatabase(testDatabase);
		
		
	}

	
}
