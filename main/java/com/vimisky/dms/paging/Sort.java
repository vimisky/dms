package com.vimisky.dms.paging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * �����࣬���ڷ�ҳ����ͷ�ҳ���ض��󣬱�ʾԪ���������
 * @author weihaitao
 * */
public class Sort implements Iterable<com.vimisky.dms.paging.Sort.Order>,Serializable{
	/**
	 * ���л�ID
	 */
	private static final long serialVersionUID = 2714997507807587553L;

	/**
	 * Ĭ������
	 * */
	public static final DIRECTION DEFAULT_DIRECTION = DIRECTION.ASC; 
	
	private List<Order> orders;
	//Sort������Ĭ�Ϲ��췽����orders����Ϊ�ա�ͨ������������Ĺ��췽�����Լ������岻�������Ĺ��췽������Ĭ�Ϲ��췽��������ȥ����
	/*
	public Sort(){
		super();
		System.out.println("Sort initiated");
	}*/
	//���췽�������ǲ���Ҫnew��return��
	/**
	 * ���Ĺ��췽��֮һ������{@link Sort}ʵ��
	 * @throws ������ordersΪ�ջ���Ԫ�ظ���Ϊ0ʱ���׳��쳣
	 * @param orders {@link Sort.Order} ʵ���б�
	 * */
	public Sort(List<Order> orders){
		if (null == orders || true == orders.isEmpty()) {
			throw new IllegalArgumentException("������Ҫ�ṩһ����������!");
		}
		this.orders = orders;
	}
	/**
	 * ���Ĺ��췽��֮һ������{@link Sort}ʵ��
	 * @throws ������propertiesΪ�ջ���Ԫ�ظ���Ϊ0ʱ���׳��쳣
	 * @param direction ������Ĭ��Ϊ{@link Sort#DEFAULT_DIRECTION}
	 * @param properties �����б�
	 * */
	public Sort(DIRECTION direction, List<String> properties){
		if (null == properties || true == properties.isEmpty()) {
			throw new IllegalArgumentException("������Ҫ�ṩһ����������!");
		}
		this.orders = new ArrayList<Sort.Order>(properties.size());
		for (String property : properties) {
			this.orders.add(new Order(direction, property));
		}
	}
	/**
	 * �������췽��֮һ������{@link Sort}ʵ��
	 * @param direction ������
	 * @param properties �ɲ���������{@link String}���͵������������
	 * */
	public Sort(DIRECTION direction, String... properties){
		
		this(direction, properties == null ? new ArrayList<String>() : Arrays.asList(properties));//Arrays.asList(properties);
		
	}
	/**
	 * �������췽��֮һ������{@link Sort}ʵ��
	 * ʹ��Ĭ�������� {@link Sort#DEFAULT_DIRECTION}
	 * @param properties �ɲ���������{@link String}���͵������������
	 * */	
	public Sort(String... properties){
		this(DEFAULT_DIRECTION,properties);
	}
	/**
	 * �������췽��֮һ������{@link Sort}ʵ��
	 * @param orders �ɲ���������{@link Order}���͵������������
	 * */	
	public Sort(Order... orders){
		this(Arrays.asList(orders));
	}
	/**
	 * �ϲ��µ�{@link Sort}���󣬷���{@link Sort}ʵ��
	 * @param sort ������{@link Sort}����
	 * @return �ϲ����{@link Sort}����
	 * */		
	public Sort and(Sort sort){
		ArrayList<Order> these = new ArrayList<Sort.Order>(this.orders);
		for (Order order : sort) {
			these.add(order);
		}
		return new Sort(these);
	}
	/**
	 * ��ȡĳһ�����Ե�����
	 * @param property ��������
	 * @return ����һ��{@link Sort.Order}����ʵ��
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
	 * �ع�����������������
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
	 * {@link Sort}���ڲ���(Sort Based/Nested Class) ����ʾ������������,��{@link DIRECTION}��property��ɡ�
	 * */
	public static class Order implements Serializable{
		/**
		 * ���л�ID
		 */
		private static final long serialVersionUID = 369662872707201337L;
		/**
		 * {@link ignore_case}Ĭ��ֵ��{@literal false}
		 * */
		private static final boolean DEFAULT_IGNORE_CASE = false;
		
		/**
		 * ��������֮һ��property������
		 * */
		private String property;
		/**
		 * ��������֮һ��direction��������
		 * */
		private DIRECTION direction;
		/**
		 * �Ƿ���Դ�Сд
		 * */
		private boolean ignoreCase;
		/**
		 * ���Ĺ��췽��֮һ������{@link Order}ʵ��
		 * @param direction, ������Ĭ��Ϊ{@link Sort#DEFAULT_DIRECTION}
		 * @param property, ��������
		 * */
		public Order(DIRECTION direction, String property, boolean ignoreCase){
			super();
			this.direction = direction == null? DEFAULT_DIRECTION:direction;
			this.property = property;
			this.ignoreCase = ignoreCase;
		}
		/**
		 * �򵥹��췽��֮һ������{@link Order}ʵ��
		 * */
		public Order(DIRECTION direction, String property){
			this(direction, property, DEFAULT_IGNORE_CASE);
		}
		/**
		 * �򵥹��췽��֮һ������{@link Order}ʵ��
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
		 * ��дhashCode�����JDKӰ�죬�����洢��ַ�޹أ�����������ֵ��ء�
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
		 * ��дequals�����JDKӰ�죬�����洢��ַ�޹أ�����������ֵ��ء�
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
