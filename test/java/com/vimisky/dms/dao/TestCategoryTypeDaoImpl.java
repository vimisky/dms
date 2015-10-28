package com.vimisky.dms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.vimisky.dms.dao.ibatisimpl.CategoryTypeDaoImpl;
import com.vimisky.dms.entity.CategoryType;
import com.vimisky.dms.paging.Page;
import com.vimisky.dms.paging.PageRequest;
import com.vimisky.dms.paging.Pageable;
import com.vimisky.dms.utils.VimiskyUtils;

import junit.framework.Assert;
import junit.framework.TestCase;
/**
 * 这里也有数据库更新和插入操作，谨慎注意，日常测试请关闭
 * */
public class TestCategoryTypeDaoImpl extends TestCase {

    private CategoryTypeDao categoryTypeDao;
    private ApplicationContext ac;

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        // TODO Auto-generated method stub
        super.setUp();
        ac = new ClassPathXmlApplicationContext("spring-context.xml");
        categoryTypeDao = (CategoryTypeDaoImpl)ac.getBean(CategoryTypeDaoImpl.class);
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
    /*
    public void testGetAllCategoryTypes(){
        List<CategoryType> categoryTypes = categoryTypeDao.getAllCategoryTypes();
        Assert.assertEquals(true, categoryTypes!=null&& categoryTypes.size() > 6000);
    }*/
/*
    private static String getRandomCode(){
        StringBuffer randomChar = new StringBuffer(5);
        for (int i = 0; i < randomChar.capacity(); i++) {
            randomChar.append(String.valueOf((char)(Math.round(Math.random()*(new Date().getTime()*10000)%25+65))));
        }
        return randomChar.toString();
    }
    */
    public void testGetRandomCode(){
    	String code = VimiskyUtils.getRandomCode();
    	Assert.assertEquals("随机获取Code失败", true, code!=null&&code.length()>0);
    }
    /**
     * 普通测试环境下请关闭
     * */
    /*
    public void testInsertCategoryType(){
        CategoryType categoryType = new CategoryType();
        categoryType.setName("一般分类");
        categoryType.setSecondaryName("一般分类");
        categoryType.setDescription("一般分类");
        categoryType.setThumbnailUrl("http://mobile.xinhua-news.com");
        categoryType.setThumbnailIcon("<icon class==\"test\"></icon>");
        categoryType.setLanguage("简体中文");
        categoryType.setCode(getRandomCode());
        categoryType.setCreateTime(new Date());
        categoryType.setLastModifyTime(new Date());
        categoryTypeDao.insertCategoryType(categoryType);
        
    }*/
    /*
    public void testUpdateCategoryType(){
        CategoryType categoryType = categoryTypeDao.getOldestCategoryType();
        categoryType.setLastModifyTime(new Date());
        categoryTypeDao.updateCategoryType(categoryType);
    }*/
    /*
    public void testGetCategoryTypeById(){
        CategoryType categoryType = categoryTypeDao.getCategoryTypeById(1);
        Assert.assertEquals("获取的code与实际不符",true, categoryType != null && categoryType.getCode().equals("cptfl"));
    }*/
    /*
    public void testGetCategoryTypeByPage(){
        Pageable pageable = new PageRequest(2, 30);
        Page<CategoryType> catePage = categoryTypeDao.getCategoryTypeByPagingAndSortId(pageable);
        for (CategoryType categoryType : catePage) {
            System.out.println("id:"+categoryType.getId()+" code:"+categoryType.getCode());
        }
    }*/
    /*
    public void testDeleteCategoryType(){
        CategoryType categoryType = categoryTypeDao.getOldestCategoryType();
        categoryTypeDao.deleteCategoryTypeById(categoryType.getId());
    }*/
}
