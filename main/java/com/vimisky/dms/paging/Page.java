package com.vimisky.dms.paging;

import org.springframework.core.convert.converter.Converter;

public interface Page<T> extends Slice<T> {

	/**
	 * ��ȡҳ������
	 * @return
	 * */
	public int getTotalPages();
	/**
	 * ��ȡԪ������
	 * @return
	 * */
	public int getTotalElements();
	
	/**
	 * ת��
	 * */
	public <S> Page<S> map(Converter<? super T, ? extends S> converter);
	
}
