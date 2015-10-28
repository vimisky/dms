package com.vimisky.functional;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.vimisky.dms.dao.CategoryTypeDao;
import com.vimisky.dms.dao.ibatisimpl.CategoryTypeDaoImpl;
import com.vimisky.dms.dao.ibatisimpl.support.SQLLimitParam;
import com.vimisky.dms.entity.CategoryType;
/**
 * 这里有插入操作，注意不要轻易执行。
 * 
 * */
public class IBatisTest {

	{
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}
	public Logger logger = Logger.getLogger("com.vimisky.functional.IBatisTest");
	public void springBatis(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
		CategoryTypeDao categoryTypeDao =(CategoryTypeDao) applicationContext.getBean(CategoryTypeDaoImpl.class);
		List<CategoryType> categoryTypes = categoryTypeDao.getAllCategoryTypes();
		if (categoryTypes != null) {
			System.out.println("category type size is "+ categoryTypes.size());
		}else {
			System.out.println("can't find category type ");
		}
	}
	
	public void ibatisCategoryTypeInsert() throws IOException{
		Reader reader = Resources.getResourceAsReader("myBatis.cfg.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		CategoryType categoryType = new CategoryType();
		categoryType.setName("一般分类");
		categoryType.setSecondaryName("一般分类");
		categoryType.setDescription("一般分类");
		categoryType.setThumbnailUrl("http://mobile.xinhua-news.com");
		categoryType.setThumbnailIcon("<icon class==\"test\"></icon>");
		categoryType.setLanguage("简体中文");
		categoryType.setCode("YBFLX");
		categoryType.setCreateTime(new Date());
		categoryType.setLastModifyTime(new Date());
		int i = 0;
		try {
			for(i = 0; i<1000 ; i++){
				String randomCode = getRandomCode();
				categoryType.setCode(randomCode);
//				System.out.println("random code "+ randomCode);
				sqlSession.insert("insertCategoryType", categoryType);
				
			}
			sqlSession.commit();
			System.out.println("插入成功，插入数据"+i+"条");
		} catch (Exception e) {
			// TODO: handle exception
			sqlSession.rollback();
			System.out.println("插入失败，执行回滚，数据量为"+i);
		}finally{
			sqlSession.close();
			System.out.println("插入完成，关闭sqlsession");
		}
	}
	public void ibatisCategoryTypeSelect() throws IOException{
		Reader reader = Resources.getResourceAsReader("myBatis.cfg.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<CategoryType> categoryTypes = sqlSession.selectList("selectCategoryType");
		System.out.println("categoryTypes has instances of " + (categoryTypes == null? 0 : categoryTypes.size()));
		sqlSession.close();
	}
	class IRowBounds {

	}
	public void ibatisCategoryTypePageSelect() throws IOException{
		Reader reader = Resources.getResourceAsReader("myBatis.cfg.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		RowBounds rowBounds = new RowBounds(0, 10);
		SQLLimitParam sqlLimitParam = new SQLLimitParam(10, 100);
		List<CategoryType> categoryTypes = sqlSession.selectList("selectCategoryType",sqlLimitParam,rowBounds);
		
		logger.info("查询到 " + (categoryTypes == null? 0 : categoryTypes.size()) + "条记录");
		
		if (categoryTypes!=null) {
			for (CategoryType categoryType : categoryTypes) {
//				System.out.println("code:"+categoryType.getCode());
				logger.info("code:"+categoryType.getCode());
			}
		}
		
		sqlSession.close();
	}
	private static String getRandomCode(){
		StringBuffer randomChar = new StringBuffer(5);
		for (int i = 0; i < randomChar.capacity(); i++) {
			randomChar.append(String.valueOf((char)(Math.round(Math.random()*(new Date().getTime()*10000)%25+65))));
		}
		return randomChar.toString();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
//			new IBatisTest().ibatisCategoryTypeInsert();
			new IBatisTest().ibatisCategoryTypeSelect();
//			new IBatisTest().ibatisCategoryTypePageSelect();
//			new IBatisTest().springBatis();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		System.out.println("random string "+getRandomCode());
	}

}
