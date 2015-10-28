package com.vimisky.dms.utils;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import com.vimisky.dms.entity.ContentWeb;

public class HibernateInsert {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void initFactory() {
		return;
	}
	
	public static int insertContentWeb(ContentWeb contentWeb){
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.save(contentWeb);
		
		transaction.commit();
		session.close();
		return 0;
	}

	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistryBuilder.buildServiceRegistry());
		return sessionFactory;
	}
	
	private static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void closeSessionFactory() {
		sessionFactory.close();
	}
	
}
