package com.vimisky.functional;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaSpectest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ClassLoader loader = JavaSpectest.class.getClassLoader(); 
//        while (loader != null) { 
//            System.out.println(loader.toString()); 
//            loader = loader.getParent(); 
//        } 
//        springTest();
		try {
			URL url = new URL("http://mobile.xinhua-news.com/cms/login?auth=no");
			System.out.println("URL Path is :"+url.getPath());
			System.out.println("URL Host is :"+url.getHost());
			System.out.println("URL File is :"+url.getFile());
			System.out.println("URL Query is :"+url.getQuery());
			System.out.println("URL Protocol is :"+url.getProtocol());
			System.out.println("URL String is :"+url.toString());
			System.out.println("URL is :"+url.getPath());
			System.out.println("URL is :"+url.getPath());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void springTest(){
		ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
		System.out.println("application context:"+appContext);
	}

}
