package com.vimisky.dms.paging;

import java.io.Serializable;

public abstract class AbstractPageRequest implements Pageable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5583051520861339218L;
	/**
	 * 页码
	 * */
	private int page;
	/**
	 * 分页内元素数量
	 * */
	private int size;

	/**
	 * 核心构造方法，其实由于本类为Abstract类，因此不能直接使用初始化对象。
	 * @param page ,页码
	 * @param size ,分页内元素数量
	 * */
	public AbstractPageRequest(int page, int size){
		if (page < 0) {
			throw new IllegalArgumentException("页码必须大于等于0");
		}
		if (size < 1) {
			throw new IllegalArgumentException("分页元素数量必须大于0");
		}
		this.page = page;
		this.size = size;
	}
	
	@Override
	public int getPageNumber() {
		// TODO Auto-generated method stub
		return page;
	}

	@Override
	public int getPageSize() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public int getOffset() {
		// TODO Auto-generated method stub
		return page*size;
	}

	@Override
	public Pageable previousOrFirst() {
		// TODO Auto-generated method stub
		return hasPrevious()?previous():first();
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
//		return null;
		return page>0?true:false;
	}

	public abstract Pageable first();
	public abstract Pageable previous();
	public abstract Pageable next();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + page;
		result = prime * result + size;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		//abstract class里确实不好写
//		if (!(obj instanceof AbstractPageRequest))
//			return false;
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		AbstractPageRequest that = (AbstractPageRequest) obj;
		return page == that.page && size == that.size;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AbstractPageRequest [page=" + page + ", size=" + size + "]";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
