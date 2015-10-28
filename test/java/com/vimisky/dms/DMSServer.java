/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.vimisky.dms;

import org.eclipse.jetty.server.Server;

/**
 * ʹ��Jetty���е���WebӦ��, ��Console��������Ӧ��.
 * 
 * @author calvin
 */
public class DMSServer {

	public static final int PORT = 8080;
	public static final String CONTEXT = "/dms";
	public static final String[] TLD_JAR_NAMES = new String[] { "sitemesh", "spring-webmvc", "shiro-web" };

	public static void main(String[] args) throws Exception {
		// �趨Spring��profile
		Profiles.setProfileAsSystemProperty(Profiles.DEVELOPMENT);

		// ����Jetty
		Server server = JettyFactory.createServerInSource(PORT, CONTEXT);
		JettyFactory.setTldJarNames(server, TLD_JAR_NAMES);

		try {
			System.out.println("[HINT] Don't forget to set -XX:MaxPermSize=128m");

			server.start();
			System.out.println("Server running at http://localhost:" + PORT + CONTEXT);
			System.out.println("[HINT] Hit Enter to reload the application quickly");

			// �ȴ��û�����س�����Ӧ��.
			while (true) {
				char c = (char) System.in.read();
				if (c == '\n') {
					JettyFactory.reloadContext(server);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}