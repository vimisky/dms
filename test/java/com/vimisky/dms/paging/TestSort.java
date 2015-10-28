package com.vimisky.dms.paging;

import com.vimisky.dms.paging.Sort.DIRECTION;
import com.vimisky.dms.paging.Sort.Order;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestSort extends TestCase{

	private Sort sort;
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		sort = new Sort(DIRECTION.ASC,"name","age","sexual");
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		sort = null;
	}

	public void testGetOrderFor(){
		Order order = sort.getOrderFor("name");
		Assert.assertEquals("获取单个元素的排序有问题", new Order("name"), order);
	}
	public void testEquals(){
		Sort thatSort = new Sort("name","age","sexual");
		boolean result = sort.equals(thatSort);
		Assert.assertEquals("equals方法有问题", true, result);
	}
	
}
