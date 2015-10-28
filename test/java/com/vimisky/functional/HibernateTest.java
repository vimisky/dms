package com.vimisky.functional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

import com.vimisky.dms.entity.ContentBase;
import com.vimisky.dms.entity.ContentWeb;



public class HibernateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		insertSampleData();
//		getSampleData();
	}
	private static int getSampleData(){
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
//		SQLQuery query = session.createSQLQuery("select contentid from dms_content_base").addEntity(ContentBase.class);
//		Query query = session.createQuery("from ContentBase");

		SQLQuery query = session.createSQLQuery("select * from  dms_content_base as cc ").addEntity("cc", ContentBase.class);
		
//		List<Object> res = query.list();
//		for (ContentBase contentBase : res) {
//			System.out.println("hehe:"+contentBase.getTitle());
//		}
		List testList = query.list();
		
		for (ContentBase object : (List<ContentBase>)testList) {
			System.out.println("Object:"+object.getAuthor());
		}
//		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		return 0;
	}
	private static int insertSampleData(){
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		/**
		Query query = session.getNamedQuery("");
		query.setParameter("", );
		query.setParameter("", );
		***/
		ContentBase contentBase = null;
		for (int j = 0; j < 100; j++) {
			contentBase = new ContentBase();
			contentBase.setTitle("title"+j);
			contentBase.setSubTitle("subtitle"+j);
			contentBase.setAuthor("author"+j);
			contentBase.setcLanguage("clanguage"+j);
			contentBase.setCreateTime(new Date());
			contentBase.setSourceName("sourcename"+j);
			contentBase.setSourceURL("sourceurl"+j);
			session.save(contentBase);
			if (0 == j%20) {
				session.flush();
				session.clear();
			}
		}
		
		transaction.commit();
		session.close();
		sessionFactory.close();
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
}
