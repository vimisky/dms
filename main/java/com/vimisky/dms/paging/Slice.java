package com.vimisky.dms.paging;

import java.util.List;

import org.springframework.core.convert.converter.Converter;

public interface Slice<T> extends Iterable<T> {

	/**
	 * No.1 接口：获取分页页码
	 * @return
	 * */
	public int getNumber();
	/**
	 * No.2 接口：获取分页大小
	 * @return
	 * */
	public int getSize();
	/**
	 * No.3 接口：获取元素数量
	 * @return
	 * */
	public int getNumberOfElements();
	/**
	 * Page接口没有Offset
	 * */
//	public int getOffset();
	/**
	 * No.4 接口：判断分页中是否有元素内容
	 * @return
	 * */
	public boolean hasContent();
	/**
	 * No.5 接口：获取分页中的元素内容
	 * @return
	 * */
	public List<T> content();
	/**
	 * No.6 接口：获取分页中元素排序规则
	 * @return
	 * */
	public Sort getSort();
	/**
	 * No.7 接口：判断该分页是否为首页
	 * @return
	 * */
	public boolean isFirst();
	/**
	 * No.8 接口：判断该分页是否为尾页
	 * @return
	 * */
	public boolean isLast();
	/**
	 * No.9 接口：判断该分页是否有前一页
	 * @return
	 * */
	public boolean hasPrevious();
	/**
	 * No.10 接口：判断该分页是否有后一页
	 * @return
	 * */
	public boolean hasNext();
	/**
	 * No.11 接口：获取前一页分页请求对象
	 * @return
	 * */
	public Pageable previousPageable();
	/**
	 * No.12 接口：获取后一页分页请求对象
	 * @return
	 * */
	public Pageable nextPageable();
	/**
	 * No.13接口：转换T为S
	 * @return
	 * */
	public <S> Slice<S> map(Converter<? super T, ? extends S> converter);

}
