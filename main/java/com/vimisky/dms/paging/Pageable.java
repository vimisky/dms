package com.vimisky.dms.paging;

public interface Pageable {

	/**
	 * 获取分页请求对象锁请求的分页页码
	 * */
	public int getPageNumber();
	/**
	 * 获取分页请求对象锁请求的分页大小
	 * */
	public int getPageSize();
	/**
	 * 获取分页请求对象锁请求的分页位移
	 * */
	public int getOffset();
	/**
	 * 获取分页请求对象锁请求的分页元素排序规则
	 * */
	public Sort getSort();
	/**
	 * 获取首页分页请求对象锁请求
	 * */	
	Pageable first();
	/**
	 * 获取分页请求对象锁请求的分页页码
	 * */	
	Pageable previousOrFirst();
	/**
	 * 获取分页请求对象锁请求的分页页码
	 * */	
	public boolean hasPrevious();
	//感觉这个有意义，其它的上面的3个属性毛意义都没有
	/**
	 * 获取分页请求对象锁请求的分页页码
	 * */
	public Pageable next();
}
