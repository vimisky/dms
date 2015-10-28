package com.vimisky.dms.paging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 排序类，用于分页请求和分页返回对象，表示元素排序组合
 * @author weihaitao
 * */
public class Sort implements Iterable<com.vimisky.dms.paging.Sort.Order>,Serializable{
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 2714997507807587553L;

	/**
	 * 默认排序
	 * */
	public static final DIRECTION DEFAULT_DIRECTION = DIRECTION.ASC; 
	
	private List<Order> orders;
	//Sort不能有默认构造方法，orders不能为空。通过定义带参数的构造方法，以及不定义不带参数的构造方法，将默认构造方法从类中去掉。
	/*
	public Sort(){
		super();
		System.out.println("Sort initiated");
	}*/
	//构造方法里面是不需要new，return的
	/**
	 * 核心构造方法之一，构造{@link Sort}实例
	 * @throws 当参数orders为空或者元素个数为0时，抛出异常
	 * @param orders {@link Sort.Order} 实例列表
	 * */
	public Sort(List<Order> orders){
		if (null == orders || true == orders.isEmpty()) {
			throw new IllegalArgumentException("至少需要提供一个排序属性!");
		}
		this.orders = orders;
	}
	/**
	 * 核心构造方法之一，构造{@link Sort}实例
	 * @throws 当参数properties为空或者元素个数为0时，抛出异常
	 * @param direction 排序方向，默认为{@link Sort#DEFAULT_DIRECTION}
	 * @param properties 属性列表
	 * */
	public Sort(DIRECTION direction, List<String> properties){
		if (null == properties || true == properties.isEmpty()) {
			throw new IllegalArgumentException("至少需要提供一个排序属性!");
		}
		this.orders = new ArrayList<Sort.Order>(properties.size());
		for (String property : properties) {
			this.orders.add(new Order(direction, property));
		}
	}
	/**
	 * 衍生构造方法之一，返回{@link Sort}实例
	 * @param direction 排序方向
	 * @param properties 由不定数量的{@link String}类型的属性名称组成
	 * */
	public Sort(DIRECTION direction, String... properties){
		
		this(direction, properties == null ? new ArrayList<String>() : Arrays.asList(properties));//Arrays.asList(properties);
		
	}
	/**
	 * 衍生构造方法之一，返回{@link Sort}实例
	 * 使用默认排序方向 {@link Sort#DEFAULT_DIRECTION}
	 * @param properties 由不定数量的{@link String}类型的属性名称组成
	 * */	
	public Sort(String... properties){
		this(DEFAULT_DIRECTION,properties);
	}
	/**
	 * 衍生构造方法之一，返回{@link Sort}实例
	 * @param orders 由不定数量的{@link Order}类型的属性名称组成
	 * */	
	public Sort(Order... orders){
		this(Arrays.asList(orders));
	}
	/**
	 * 合并新的{@link Sort}对象，返回{@link Sort}实例
	 * @param sort 新增的{@link Sort}对象
	 * @return 合并后的{@link Sort}对象
	 * */		
	public Sort and(Sort sort){
		ArrayList<Order> these = new ArrayList<Sort.Order>(this.orders);
		for (Order order : sort) {
			these.add(order);
		}
		return new Sort(these);
	}
	/**
	 * 获取某一个属性的排序
	 * @param property 属性名称
	 * @return 返回一个{@link Sort.Order}对象实例
	 * */
	public Order getOrderFor(String property){
		for (Order order : this) {
			if (order.getProperty().equals(property)) {
				return order;
			}
		}
		return null;
	}

	@Override
	/**
	 * 重构方法，迭代器方法
	 * */
	public Iterator<com.vimisky.dms.paging.Sort.Order> iterator() {
		// TODO Auto-generated method stub
		return this.orders.iterator();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
//		return super.equals(obj);
		if (this == obj) {
			return true;
		}
		if (! (obj instanceof Sort)) {
			return false;
		}
		Sort that = (Sort)obj;
		return that.orders.equals(this.orders);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return StringUtils.collectionToCommaDelimitedString(this.orders);
	}

	/**
	 * Sort Based/Nested enum
	 * */
	public static enum DIRECTION{
		ASC,DESC;
	}
	
	/**
	 * {@link Sort}类内部类(Sort Based/Nested Class) ，表示单个排序属性,由{@link DIRECTION}和property组成。
	 * */
	public static class Order implements Serializable{
		/**
		 * 序列化ID
		 */
		private static final long serialVersionUID = 369662872707201337L;
		/**
		 * {@link ignore_case}默认值，{@literal false}
		 * */
		private static final boolean DEFAULT_IGNORE_CASE = false;
		
		/**
		 * 核心属性之一：property，属性
		 * */
		private String property;
		/**
		 * 核心属性之一：direction，排序方向
		 * */
		private DIRECTION direction;
		/**
		 * 是否忽略大小写
		 * */
		private boolean ignoreCase;
		/**
		 * 核心构造方法之一，构造{@link Order}实例
		 * @param direction, 排序方向，默认为{@link Sort#DEFAULT_DIRECTION}
		 * @param property, 排序属性
		 * */
		public Order(DIRECTION direction, String property, boolean ignoreCase){
			super();
			this.direction = direction == null? DEFAULT_DIRECTION:direction;
			this.property = property;
			this.ignoreCase = ignoreCase;
		}
		/**
		 * 简单构造方法之一，构造{@link Order}实例
		 * */
		public Order(DIRECTION direction, String property){
			this(direction, property, DEFAULT_IGNORE_CASE);
		}
		/**
		 * 简单构造方法之一，构造{@link Order}实例
		 * */
		public Order(String property){
			this(DEFAULT_DIRECTION, property);
		}
		
		
		public String getProperty(){
			return this.property;
		}
		public void setProperty(String property){
			this.property = property;
		}
		public DIRECTION getDirection(){
			return this.direction;
		}
		public void setDirection(DIRECTION direction){
			this.direction = direction;
		}
		/**
		 * @return the ignoreCase
		 */
		public boolean isIgnoreCase() {
			return ignoreCase;
		}
		/**
		 * @param ignoreCase the ignoreCase to set
		 */
		public void setIgnoreCase(boolean ignoreCase) {
			this.ignoreCase = ignoreCase;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 * 重写hashCode，解除JDK影响，与对象存储地址无关，与对象的属性值相关。
		 */
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			int result = 17;
			result = 31*result + property.hashCode();
			result = 31*result + direction.hashCode();
			result = 31*result + (ignoreCase?1:0);
			
			return result;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 * 重写equals，解除JDK影响，与对象存储地址无关，与对象的属性值相关。
		 */
		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof Order)) {
				return false;
			}
			Order that = (Order)obj;
			
			return that.direction == this.direction && that.property == this.property && that.ignoreCase == this.ignoreCase;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			String result = String.format("%s:%s", property,direction);
			if (ignoreCase) {
				result+="ignoring case";
			}
			return result;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Sort sort = new Sort("name","age","sexual");
		System.out.println(sort.toString());
		System.out.println(sort.hashCode());
		Order order1 = new Order("dog");
		Order order2 = new Order("dog");
		System.out.println("equals? "+order1.equals(order2));
		List<Order> orders = new ArrayList<Sort.Order>();
		orders.add(order1);
		orders.add(order2);
		System.out.println("orders size:"+orders.size());
		HashMap<Order, Integer> hashMap = new HashMap<Sort.Order, Integer>();
		hashMap.put(order1, 100);
		hashMap.put(order2, 100);
		System.out.println("hashmap size:"+hashMap.size());
	}
}
