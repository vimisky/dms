package com.vimisky.dms.dao;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vimisky.dms.dao.ibatisimpl.CategoryDaoImpl;
import com.vimisky.dms.dao.ibatisimpl.CategoryTypeDaoImpl;
import com.vimisky.dms.entity.Category;
import com.vimisky.dms.entity.CategoryType;
import com.vimisky.dms.utils.VimiskyUtils;

import junit.framework.Assert;
import junit.framework.TestCase;
/**
 * 这里也有数据库更新和插入操作，谨慎注意，日常测试请关闭
 * */
public class TestCategoryDaoImpl extends TestCase {

	private CategoryDao categoryDao;
	private CategoryTypeDao categoryTypeDao;
	private Logger logger = Logger.getLogger(getClass());
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-context.xml");
		categoryDao = (CategoryDaoImpl)ac.getBean(CategoryDaoImpl.class);
		categoryTypeDao = (CategoryTypeDao)ac.getBean(CategoryTypeDaoImpl.class);
		{
			org.apache.ibatis.logging.LogFactory.useLog4JLogging();
		}
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
    public void testGetRandomCode(){
    	String code = VimiskyUtils.getRandomCode();
    	Assert.assertEquals("随机获取Code失败", true, code!=null&&code.length()>0);
    }
	/**插入操作，普通测试不需要打开*/
	/*
	public void testInsertCategory(){
		CategoryType categoryType = categoryTypeDao.getOldestCategoryType();
		Category category = new Category();
		category.setName("国内");
		category.setSecondaryName("国内");
		category.setDescription("国内新闻抢先看");
		category.setLanguage("简体中文");
		category.setCode(getRandomCode());
		category.setCreateTime(new Date());
		category.setLastModifyTime(new Date());
		category.setCategoryType(categoryType);
		categoryDao.insertCategory(category);
	}*/
	/**
	 * 这条测试显然不充分
	 * */
	/*
	public void testSelectCategoryById(){
		Category category = categoryDao.getCategoryById(1);
		logger.info("category property:");
		logger.info(category.getCode());
		if (category.getCategoryType()!=null) {
			logger.info("category type is "+category.getCategoryType());
		}
		Assert.assertEquals(true, category.getId()==1);
	}*/
}
