package com.vimisky.dms.paging;

public interface Pageable {

	/**
	 * ��ȡ��ҳ�������������ķ�ҳҳ��
	 * */
	public int getPageNumber();
	/**
	 * ��ȡ��ҳ�������������ķ�ҳ��С
	 * */
	public int getPageSize();
	/**
	 * ��ȡ��ҳ�������������ķ�ҳλ��
	 * */
	public int getOffset();
	/**
	 * ��ȡ��ҳ�������������ķ�ҳԪ���������
	 * */
	public Sort getSort();
	/**
	 * ��ȡ��ҳ��ҳ�������������
	 * */	
	Pageable first();
	/**
	 * ��ȡ��ҳ�������������ķ�ҳҳ��
	 * */	
	Pageable previousOrFirst();
	/**
	 * ��ȡ��ҳ�������������ķ�ҳҳ��
	 * */	
	public boolean hasPrevious();
	//�о���������壬�����������3������ë���嶼û��
	/**
	 * ��ȡ��ҳ�������������ķ�ҳҳ��
	 * */
	public Pageable next();
}
