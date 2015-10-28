package com.vimisky.dms.dao.comp;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vimisky.dms.entity.backend.CategoryBrief;
import com.vimisky.dms.entity.backend.CategoryDetail;
@Repository("categoryQueryDaoImpl")
public class CategoryQueryDaoImpl extends SqlSessionDaoSupport implements CategoryQueryDao{

	private Logger logger = Logger.getLogger(getClass());
	/* (non-Javadoc)
	 * @see org.mybatis.spring.support.SqlSessionDaoSupport#setSqlSessionFactory(org.apache.ibatis.session.SqlSessionFactory)
	 */
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	/* (non-Javadoc)
	 * @see org.mybatis.spring.support.SqlSessionDaoSupport#setSqlSessionTemplate(org.mybatis.spring.SqlSessionTemplate)
	 */
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		// TODO Auto-generated method stub
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public CategoryDetail getCategoryDetailById(int id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectCategoryDetailById",id);
	}

	@Override
	public List<CategoryBrief> getCategoryBriefsByParentId(int parentCategoryId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("selectCategoryBriefsByParentId",parentCategoryId);
	}


}
