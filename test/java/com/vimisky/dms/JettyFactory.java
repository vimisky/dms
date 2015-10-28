/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.vimisky.dms;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;

import com.google.common.collect.Lists;

/**
 * ����Jetty Server�Ĺ�����.
 * 
 * @author calvin
 */
public class JettyFactory {

	private static final String DEFAULT_WEBAPP_PATH = "src/main/webapp";
	private static final String WINDOWS_WEBDEFAULT_PATH = "jetty/webdefault-windows.xml";

	/**
	 * �������ڿ������е��Ե�Jetty Server, ��src/main/webappΪWebӦ��Ŀ¼.
	 */
	public static Server createServerInSource(int port, String contextPath) {
		Server server = new Server();
		// ������JVM�˳�ʱ�ر�Jetty�Ĺ��ӡ�
		server.setStopAtShutdown(true);

		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(port);
		// ���Windows���ظ�����Jetty��Ȼ������˿ڳ�ͻ������.
		connector.setReuseAddress(false);
		server.setConnectors(new Connector[] { connector });
		// @weihaitao-����Jetty��webapp����
		WebAppContext webContext = new WebAppContext(DEFAULT_WEBAPP_PATH, contextPath);
		// �޸�webdefault.xml�����Windows��Jetty Lockס��̬�ļ�������.
		webContext.setDefaultsDescriptor(WINDOWS_WEBDEFAULT_PATH);
		server.setHandler(webContext);

		return server;
	}

	/**
	 * ���ó�jstl-*.jar��������tld�ļ���jar��������.
	 * jar���Ʋ���Ҫ�汾�ţ���sitemesh, shiro-web
	 */
	public static void setTldJarNames(Server server, String... jarNames) {
		WebAppContext context = (WebAppContext) server.getHandler();
		List<String> jarNameExprssions = Lists.newArrayList(".*/jstl-[^/]*\\.jar$", ".*/.*taglibs[^/]*\\.jar$");
		for (String jarName : jarNames) {
			jarNameExprssions.add(".*/" + jarName + "-[^/]*\\.jar$");
		}

		context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
				StringUtils.join(jarNameExprssions, '|'));

	}

	/**
	 * ������������application������target/classes��target/test-classes.
	 */
	public static void reloadContext(Server server) throws Exception {
		WebAppContext context = (WebAppContext) server.getHandler();

		System.out.println("[INFO] Application reloading");
		context.stop();

		WebAppClassLoader classLoader = new WebAppClassLoader(context);
		classLoader.addClassPath("target/classes");
		classLoader.addClassPath("target/test-classes");
		context.setClassLoader(classLoader);

		context.start();

		System.out.println("[INFO] Application reloaded");
	}
}