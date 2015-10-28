
package com.vimisky.dms.dao.ibatisimpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.vimisky.dms.dao.CategoryTypeDao;
import com.vimisky.dms.dao.ibatisimpl.support.CategoryParam;
import com.vimisky.dms.dao.ibatisimpl.support.SQLLimitParam;
import com.vimisky.dms.entity.CategoryType;
import com.vimisky.dms.paging.Page;
import com.vimisky.dms.paging.PageImpl;
import com.vimisky.dms.paging.Pageable;

@Repository("categoryTypeDaoImpl")
public class CategoryTypeDaoImpl extends SqlSessionDaoSupport implements
		CategoryTypeDao {
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
	public CategoryType getCategoryTypeById(int id) {
		// TODO Auto-generated method stub
		CategoryType categoryType = getSqlSession().selectOne("com.vimisky.dms.entity.selectCategoryTypeById",id);
		return categoryType;
	}
	
	@Override
	public CategoryType getCategoryTypeByCode(String code) {
		// TODO Auto-generated method stub
		CategoryType categoryType = getSqlSession().selectOne("com.vimisky.dms.entity.selectCategoryTypeByCode",code);
		return categoryType;
	}

	@Override
	public List<CategoryType> getAllCategoryTypes() {
		// TODO Auto-generated method stub
		List<CategoryType> categoryTypes = getSqlSession().selectList("com.vimisky.dms.entity.selectAllCategoryType");
		return categoryTypes;
	}


	@Override
	public void insertCategoryType(CategoryType categoryType) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = getSqlSession();
		int nRes = sqlSession.insert("insertCategoryType", categoryType);
		logger.info("执行插入CategoryType SQL Insert成功，影响"+nRes+"条记录");		
	}

	@Override
	public void deleteCategoryTypeById(int id) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = getSqlSession();
		sqlSession.delete("deleteCategoryType", id);
	}

	@Override
	public void updateCategoryType(CategoryType categoryType) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = getSqlSession();
		sqlSession.update("updateCategoryType", categoryType);
	}
	
	@Override
	public void updateCategoryTypeByField(int id, String name, Object value) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = getSqlSession();
		CategoryParam cp = new CategoryParam();
		cp.setName(name);
		cp.setValue(value.toString());
		cp.setPkid(id);
		sqlSession.update("updateCategoryTypeByOneField", cp);
	}

	//iBatis的特点是灵活，所以不应该让其承担太多动态适应的框架类功能，应该遵循使用什么写什么实现。
	public Page<CategoryType> getCategoryTypeByPagingAndSortId(Pageable pageable){
		SqlSession sqlSession = getSqlSession();
		SQLLimitParam sqlLimitParam = new SQLLimitParam(pageable.getOffset(), pageable.getPageSize());
		List<CategoryType> categoryTypes = 
				sqlSession.selectList("selectCategoryTypeByPagingAndSortId", sqlLimitParam);
		int nCount = sqlSession.selectOne("selectCategoryTypeCount");
		Page<CategoryType> page = new PageImpl<CategoryType>(categoryTypes, pageable, nCount);
		return page;
	}

	@Override
	public CategoryType getOldestCategoryType() {
		// TODO Auto-generated method stub
		CategoryType categoryType = getSqlSession().selectOne("com.vimisky.dms.entity.selectOldestCategoryType");
		return categoryType;
	}

	@Override
	public void deleteAllCategoryTypes() {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteAllCategoryTypes");
	}
}