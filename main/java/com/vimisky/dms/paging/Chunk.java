package com.vimisky.dms.paging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

/**
 * ��Pageable��صķ�ҳ�࣬����content�⣬������Ϣ����pageable��ȡ��������ʵ��{@link Slice#hasNext()}��
 * ��ҳƬ��ֻ��һ�ּ���
 * ��List��Map��Set������
 * */
public abstract class Chunk<T> implements Slice<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7416451049769976834L;
	
	/**
	 * ʵ�ʷ�ҳ�е�Ԫ�ر���
	 * */
	private final List<T> content = new ArrayList<T>();
	/**
	 * ��Ϊ�����������Ķ���
	 * һ����ԣ��Ǵӷ�ҳ���󴫵ݹ����Ķ���
	 * */
	private final Pageable pageable;
	
	
	/**
	 * ���Ĺ��췽��������һ��{@link Chunk}ʵ��
	 * @param content����ҳ����
	 * @param pageable����ҳ�������
	 * */
	public Chunk(List<T> content, Pageable pageable){
		if (content == null ) {
			throw new IllegalArgumentException("����Ϊ��");
		}
		this.content.addAll(content);
		this.pageable = pageable;
	}
	
	/**
	 * ������
	 * */
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return content.iterator();
	}

	/**
	 * ��ȡҳ��
	 * */
	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return pageable == null ? 0 : pageable.getPageNumber();
	}

	/**
	 * ��ȡ��ҳ��ҳ��С
	 * */
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return pageable == null ? 0 : pageable.getPageSize();
	}

	/**
	 * �÷�ҳ��{@link Chunk#content}�е�Ԫ������
	 * */
	@Override
	public int getNumberOfElements() {
		// TODO Auto-generated method stub
		return content.size();
	}

	/**
	 * �Ƿ�������
	 * */
	@Override
	public boolean hasContent() {
		// TODO Auto-generated method stub
		return !content.isEmpty();
	}

	/**
	 * ��ҳ����
	 * */
	@Override
	public List<T> content() {
		// TODO Auto-generated method stub
		//Collections��̬����������content����ͼ��read-only��
		return Collections.unmodifiableList(content);
	}

	/**
	 * ��ȡ�������
	 * */
	@Override
	public Sort getSort() {
		// TODO Auto-generated method stub
		return pageable == null? null: pageable.getSort();
	}

	/**
	 * �Ƿ��ǵ�һҳ
	 * */
	@Override
	public boolean isFirst() {
		// TODO Auto-generated method stub
		return !hasPrevious();
	}

	/**
	 * �Ƿ���ǰһҳ
	 * */
	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
//		return pageable.hasPrevious();
		return getNumber()>0;
	}

	/**
	 * �Ƿ�Ϊ���һҳ
	 * ������{@link Chunk#hasNext()}
	 * */
	public boolean isLast(){
		return !hasNext();
	}
	public abstract boolean hasNext();
	//���ڲ��ܸ������������ʵ�֣���ΪƬ��
	/*
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}*/
	
	/**
	 * ǰһҳ�������
	 * ������߼�����Ҫ��ȷ����null����first��������null
	 * */
	@Override
	public Pageable previousPageable() {
		// TODO Auto-generated method stub
//		return hasPrevious()?pageable.previousOrFirst():pageable.first();
		return hasPrevious() ? pageable.previousOrFirst() : null;
	}
	/**
	 * ��һҳ�������
	 * ������߼�����Ҫ��ȷ����null����last��������null
	 * */
	@Override
	public Pageable nextPageable() {
		// TODO Auto-generated method stub
		return hasNext() ? pageable.next() : null;
	}

	//�����������map����ʵ�֣�
	/*
	@Override
	public <S> Slice<S> map(Converter<? super T, ? extends S> converter) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	/**
	 * ����ת�����Ԫ��List����
	 * @param converter��spring framework ���İ�ת�����ӿ�
	 * */
	protected <S> List<S> getConvertedContent(Converter<? super T, ? extends S> converter){
		if (content == null) {
			throw new IllegalArgumentException("���ݲ���Ϊ��");
		}
		if (converter == null) {
			throw new IllegalArgumentException("ת��������Ϊ��");
		}
		List<S> result = new ArrayList<S>(content.size());
		for (T t : content) {
			result.add(converter.convert(t));
		}
		return result;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
//		���췽���У����contentΪ�գ�����ʧ�ܣ���������У�content������Ϊ�ա�
//		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + content.hashCode();
		result = prime * result + (pageable == null ? 0 : pageable.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Chunk<?> that = (Chunk<?>) obj;
		return content.equals(that.content) && (pageable == null ? that.pageable == null : pageable.equals(that.pageable));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
//		String result = "Chunk";
//		����T�Ƿ��ͣ����Բ��ܱ���������Ԫ�ص�toString()
		/*
		StringBuffer sb = new StringBuffer("Chunk");
		
		for (T element : content) {
			sb.append(T.toString());
		}*/
		return "Chunk [content=" + content.toString() + ", pageable=" + pageable.toString() + "]";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
