package com.vimisky.dms.paging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

/**
 * 与Pageable相关的分页类，除了content外，其余信息均从pageable获取。（不能实现{@link Slice#hasNext()}）
 * 分页片段只是一种集合
 * 与List、Map、Set等类似
 * */
public abstract class Chunk<T> implements Slice<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7416451049769976834L;
	
	/**
	 * 实际分页中的元素本身
	 * */
	private final List<T> content = new ArrayList<T>();
	/**
	 * 作为构造参数输入的对象
	 * 一般而言，是从分页请求传递过来的对象
	 * */
	private final Pageable pageable;
	
	
	/**
	 * 核心构造方法，返回一个{@link Chunk}实例
	 * @param content，分页内容
	 * @param pageable，分页请求对象
	 * */
	public Chunk(List<T> content, Pageable pageable){
		if (content == null ) {
			throw new IllegalArgumentException("内容为空");
		}
		this.content.addAll(content);
		this.pageable = pageable;
	}
	
	/**
	 * 迭代器
	 * */
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return content.iterator();
	}

	/**
	 * 获取页码
	 * */
	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return pageable == null ? 0 : pageable.getPageNumber();
	}

	/**
	 * 获取分页单页大小
	 * */
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return pageable == null ? 0 : pageable.getPageSize();
	}

	/**
	 * 该分页中{@link Chunk#content}中的元素数量
	 * */
	@Override
	public int getNumberOfElements() {
		// TODO Auto-generated method stub
		return content.size();
	}

	/**
	 * 是否有内容
	 * */
	@Override
	public boolean hasContent() {
		// TODO Auto-generated method stub
		return !content.isEmpty();
	}

	/**
	 * 分页内容
	 * */
	@Override
	public List<T> content() {
		// TODO Auto-generated method stub
		//Collections静态方法，返回content的视图（read-only）
		return Collections.unmodifiableList(content);
	}

	/**
	 * 获取排序规则
	 * */
	@Override
	public Sort getSort() {
		// TODO Auto-generated method stub
		return pageable == null? null: pageable.getSort();
	}

	/**
	 * 是否是第一页
	 * */
	@Override
	public boolean isFirst() {
		// TODO Auto-generated method stub
		return !hasPrevious();
	}

	/**
	 * 是否有前一页
	 * */
	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
//		return pageable.hasPrevious();
		return getNumber()>0;
	}

	/**
	 * 是否为最后一页
	 * 依赖于{@link Chunk#hasNext()}
	 * */
	public boolean isLast(){
		return !hasNext();
	}
	public abstract boolean hasNext();
	//现在不能给出这个方法的实现，仅为片段
	/*
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}*/
	
	/**
	 * 前一页请求对象
	 * 这里的逻辑，需要明确，是null还是first，这里是null
	 * */
	@Override
	public Pageable previousPageable() {
		// TODO Auto-generated method stub
//		return hasPrevious()?pageable.previousOrFirst():pageable.first();
		return hasPrevious() ? pageable.previousOrFirst() : null;
	}
	/**
	 * 下一页请求对象
	 * 这里的逻辑，需要明确，是null还是last，这里是null
	 * */
	@Override
	public Pageable nextPageable() {
		// TODO Auto-generated method stub
		return hasNext() ? pageable.next() : null;
	}

	//这里给不出来map方法实现？
	/*
	@Override
	public <S> Slice<S> map(Converter<? super T, ? extends S> converter) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	/**
	 * 返回转换后的元素List集合
	 * @param converter是spring framework 核心包转换器接口
	 * */
	protected <S> List<S> getConvertedContent(Converter<? super T, ? extends S> converter){
		if (content == null) {
			throw new IllegalArgumentException("内容不能为空");
		}
		if (converter == null) {
			throw new IllegalArgumentException("转换器不能为空");
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
//		构造方法中，如果content为空，则构造失败，因此在类中，content不可能为空。
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
//		由于T是泛型，所以不能遍历集合中元素的toString()
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
