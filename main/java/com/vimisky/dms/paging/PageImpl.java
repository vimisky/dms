package com.vimisky.dms.paging;

import java.util.List;

import org.springframework.core.convert.converter.Converter;

/**
 * 页面类默认类.<br>
 * 之所以分成Chunk和PageImpl两个类，是因为Chunk中包装的是由调用者生成的Pageable接口能够返回的信息以及content，而PageImpl是由分页服务DAO返回的信息，主要是total和hasNext信息。
 * 不知道这么写注释是否能够说服自己
 * */
public class PageImpl<T> extends Chunk<T> implements Page<T> {
	/**
	 * 序列化ID，eclipse生成器自动生成
	 */
	private static final long serialVersionUID = 825069709849906256L;
	
	/**
	 * 所有元素数量
	 * */
	private final int total;
	/**
	 * 是否有下一页,没有这个变量
	 * */
//	private boolean hasNext;
	/**
	 * 分页请求对象，final修饰，chunk和PageImpl都有自己的pageable
	 * */
	private final Pageable pageable;
	/**
	 * 核心构造方法.返回{@link PageImpl}实例
	 * @param content
	 * @param pageable
	 * @param total
	 * */
	public PageImpl(List<T> content, Pageable pageable, int total){
		super(content, pageable);
		//断言
		assert total >= content.size();
		this.total = total;
		this.pageable = pageable;
	}
	
	/**
	 * 返回页面总数
	 * */
	@Override
	public int getTotalPages() {
		// TODO Auto-generated method stub
		//Math.ceil()，返回closest，大于等于
		return getSize() == 0 ? 1 : (int)Math.ceil((double)total/(double)getSize());
	}

	/**
	 * 返回元素总数
	 * */
	@Override
	public int getTotalElements() {
		// TODO Auto-generated method stub
		return total;
	}

	/**
	 * 判断是否有下一页.
	 * 使用普通数学运算内部解决此逻辑
	 * */
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		//totalpages 比最大的页码大1,页码是从0开始
		return getNumber()+1<getTotalPages();
	}

	/**
	 * 转换为Map
	 * */
	@Override
	public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
		// TODO Auto-generated method stub
		return new PageImpl<S>(getConvertedContent(converter), pageable, total);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (total^total>>>32);
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
		PageImpl<?> that = (PageImpl<?>) obj;
		return total == that.total && super.equals(that);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String contentType = "UNKNOWN";
		List<T> content = content();

		if (content.size() > 0) {
			contentType = content.get(0).getClass().getName();
		}

		return String.format("Page %s of %d containing %s instances", getNumber(), getTotalPages(), contentType);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int total = -10002;
		System.out.println("result:" + (total^total>>32));
		System.out.println("total >>> 1 :" + (total>>2));
		System.out.println("total >>> 1 :" + (total>>>1));
	}
}
