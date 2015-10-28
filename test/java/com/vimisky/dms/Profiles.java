/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.vimisky.dms;

/**
 * Spring profile ���÷�����profile���ơ�
 * 
 * ������ʱ�趨spring��profile
 * 
 * @author calvin
 */
public class Profiles {

	public static final String ACTIVE_PROFILE = "spring.profiles.active";
	public static final String DEFAULT_PROFILE = "spring.profiles.default";

	public static final String PRODUCTION = "production";
	public static final String DEVELOPMENT = "development";
	public static final String UNIT_TEST = "test";
	public static final String FUNCTIONAL_TEST = "functional";

	/**
	 * ��Spring����ǰ������profile�Ļ���������
	 */
	public static void setProfileAsSystemProperty(String profile) {
		System.setProperty(ACTIVE_PROFILE, profile);
	}
}