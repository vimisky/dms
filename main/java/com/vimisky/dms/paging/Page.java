package com.vimisky.dms.paging;

import org.springframework.core.convert.converter.Converter;

public interface Page<T> extends Slice<T> {

	/**
	 * 获取页码总数
	 * @return
	 * */
	public int getTotalPages();
	/**
	 * 获取元素总数
	 * @return
	 * */
	public int getTotalElements();
	
	/**
	 * 转换
	 * */
	public <S> Page<S> map(Converter<? super T, ? extends S> converter);
	
}
