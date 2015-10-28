package com.vimisky.functional;

import java.io.Reader;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.vimisky.dms.entity.CategoryType;
import com.vimisky.dms.entity.GenreType;

import junit.framework.Assert;
import junit.framework.TestCase;
/**
 * 这里也有数据库更新和插入操作，谨慎注意，日常测试请关闭
 * */
public class TestBatis extends TestCase {

	private SqlSessionFactory sqlSessionFactory;
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		Reader reader = Resources.getResourceAsReader("myBatis.cfg.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		reader.close();
		
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		sqlSessionFactory = null;
		
	}

	public void testSqlSessionFactory(){
		Assert.assertEquals("sqlSessionFactory对象不能为空", false, null == sqlSessionFactory);
	}
	public void testDBConnection() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Connection conn = sqlSession.getConnection();
			Assert.assertEquals("connection未建立", false, null == conn);
		}finally{
			sqlSession.close();
		}

	}/*
	public void testDBQuery(){
		//注意，这里的所有select/update/delete方法的参数均为batis配置文件中的key（而非sql ddl本身）,对应里面的statement。
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<GenreType> genreTypes = sqlSession.selectList("com.vimisky.dms.entity.GenreType");
			Assert.assertEquals("获取结果为0", true,genreTypes.size()>0);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			sqlSession.close();
		}
		
//		sqlSession.delete("");
//		sqlSession.select("", null);
//		sqlSession.update("");
//		sqlSession.selectOne("");
	}*/
	public void testDBQueryCType(){
		//注意，这里的所有select/update/delete方法的参数均为batis配置文件中的key（而非sql ddl本身）,对应里面的statement。
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<CategoryType> categoryTypes = sqlSession.selectList("selectCategoryType");
			Assert.assertEquals("获取结果为0", true,categoryTypes.size()>0);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			sqlSession.close();
		}
		
//		sqlSession.delete("");
//		sqlSession.select("", null);
//		sqlSession.update("");
//		sqlSession.selectOne("");
	}	
	/*
	public void testDBinsertCType(){
		//注意，这里的所有select/update/delete方法的参数均为batis配置文件中的key（而非sql ddl本身）,对应里面的statement。
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			CategoryType categoryType = new CategoryType();
			categoryType.setName("一般分类");
			categoryType.setSecondaryName("一般分类");
			categoryType.setDescription("一般分类");
			categoryType.setThumbnailUrl("http://mobile.xinhua-news.com");
			categoryType.setThumbnailIcon("<icon class==\"test\"></icon>");
			categoryType.setLanguage("简体中文");
			categoryType.setCode("YBFL");
			categoryType.setCreateTime(new Date());
			categoryType.setLastModifyTime(new Date());
			
			int nRes = sqlSession.insert("insertCategoryType", categoryType);
			sqlSession.commit();
			Assert.assertEquals("获取结果为0", true,nRes>0);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			sqlSession.close();
		}
		
//		sqlSession.delete("");
//		sqlSession.select("", null);
//		sqlSession.update("");
//		sqlSession.selectOne("");
	}	*/
	
}
