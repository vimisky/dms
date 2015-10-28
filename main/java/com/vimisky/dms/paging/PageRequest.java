package com.vimisky.dms.paging;

import com.vimisky.dms.paging.Sort.DIRECTION;

public class PageRequest extends AbstractPageRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5633658551995394989L;
	
	private Sort sort;

	/**
	 * 核心构造方法：返回{@link PageRequest}实例
	 * @param page 页码
	 * @param size 分页元素数量
	 * @param sort 排序
	 * */
	public PageRequest(int page, int size, Sort sort){
		super(page,size);
		this.sort = sort;
	}
	/**
	 * 衍生构造方法之一
	 * 
	 * */
	public PageRequest(int page, int size) {
		this(page, size, null);
	}
	/**
	 * 衍生构造方法之一
	 * */
	public PageRequest(int page, int size, DIRECTION direction,String... properties){
		this(page, size, new Sort(direction, properties));
	}
	
	@Override
	public Sort getSort() {
		// TODO Auto-generated method stub 
		return sort;
	}

	@Override
	public Pageable first() {
		// TODO Auto-generated method stub
		return new PageRequest(0, this.getPageSize(),this.getSort());
	}

	@Override
	public Pageable previous() {
		// TODO Auto-generated method stub
		return this.getPageNumber()==0?this: new PageRequest(this.getPageNumber()-1, this.getPageSize(), this.getSort());
	}

	@Override
	public Pageable next() {
		// TODO Auto-generated method stub
		return new PageRequest(this.getPageNumber()+1, this.getPageSize(), this.getSort());
	}

	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((sort == null) ? 0 : sort.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageRequest other = (PageRequest) obj;
		if (sort == null) {
			if (other.sort != null)
				return false;
		} else if (!sort.equals(other.sort))
			return false;
		return true;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("PageRequest [page : %s, size : %s, sort : %s ]",
				getPageNumber(),getPageSize(),sort==null?null:sort.toString());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PageRequest pr = new PageRequest(1, 20);
		System.out.println(pr.toString());
	}

}
