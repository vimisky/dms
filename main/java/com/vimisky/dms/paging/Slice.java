package com.vimisky.dms.paging;

import java.util.List;

import org.springframework.core.convert.converter.Converter;

public interface Slice<T> extends Iterable<T> {

	/**
	 * No.1 �ӿڣ���ȡ��ҳҳ��
	 * @return
	 * */
	public int getNumber();
	/**
	 * No.2 �ӿڣ���ȡ��ҳ��С
	 * @return
	 * */
	public int getSize();
	/**
	 * No.3 �ӿڣ���ȡԪ������
	 * @return
	 * */
	public int getNumberOfElements();
	/**
	 * Page�ӿ�û��Offset
	 * */
//	public int getOffset();
	/**
	 * No.4 �ӿڣ��жϷ�ҳ���Ƿ���Ԫ������
	 * @return
	 * */
	public boolean hasContent();
	/**
	 * No.5 �ӿڣ���ȡ��ҳ�е�Ԫ������
	 * @return
	 * */
	public List<T> content();
	/**
	 * No.6 �ӿڣ���ȡ��ҳ��Ԫ���������
	 * @return
	 * */
	public Sort getSort();
	/**
	 * No.7 �ӿڣ��жϸ÷�ҳ�Ƿ�Ϊ��ҳ
	 * @return
	 * */
	public boolean isFirst();
	/**
	 * No.8 �ӿڣ��жϸ÷�ҳ�Ƿ�Ϊβҳ
	 * @return
	 * */
	public boolean isLast();
	/**
	 * No.9 �ӿڣ��жϸ÷�ҳ�Ƿ���ǰһҳ
	 * @return
	 * */
	public boolean hasPrevious();
	/**
	 * No.10 �ӿڣ��жϸ÷�ҳ�Ƿ��к�һҳ
	 * @return
	 * */
	public boolean hasNext();
	/**
	 * No.11 �ӿڣ���ȡǰһҳ��ҳ�������
	 * @return
	 * */
	public Pageable previousPageable();
	/**
	 * No.12 �ӿڣ���ȡ��һҳ��ҳ�������
	 * @return
	 * */
	public Pageable nextPageable();
	/**
	 * No.13�ӿڣ�ת��TΪS
	 * @return
	 * */
	public <S> Slice<S> map(Converter<? super T, ? extends S> converter);

}
