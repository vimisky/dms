package com.vimisky.dms.dao.ibatisimpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.vimisky.dms.entity.GenreType;

public class GenreTypeDaoImpl extends SqlSessionDaoSupport{
	
	/* (non-Javadoc)
	 * @see org.mybatis.spring.support.SqlSessionDaoSupport#setSqlSessionFactory(org.apache.ibatis.session.SqlSessionFactory)
	 */
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String resoure = "myBatis.cfg.xml";
		SqlSession session = null;
		try {
			InputStream inputStream = Resources.getResourceAsStream(resoure);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
			List<GenreType> gTypes = session.selectList("com.vimisky.dms.entity.selectGenreType");
			System.out.println("count:"+gTypes.size());
			for (GenreType genreType : gTypes) {
				System.out.println("genretype name:"+genreType.getName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (session != null) {
				session.close();
			}
		}
		
	}

}
