package com.vimisky.dms.dao.ibatisimpl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vimisky.dms.dao.CategoryDao;
import com.vimisky.dms.dao.ibatisimpl.support.CategoryParam;
import com.vimisky.dms.entity.Category;

@Repository("categoryDaoImpl")
public class CategoryDaoImpl extends SqlSessionDaoSupport implements
		CategoryDao {
		
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
	public Category getOldestCategory(){
		return getSqlSession().selectOne("selectOldestCategory");
	}
	
	@Override
	public Category getCategoryById(int id) {
		// TODO Auto-generated method stub
		
		return getSqlSession().selectOne("selectCategoryById",id);
	}

	@Override
	public Category getCategoryByCode(String code){
		
		return getSqlSession().selectOne("selectCategoryByCode", code);
	}

	@Override
	public List<Category> getCategoriesByParentCategoryId(int parentCategoryId) {
		// TODO Auto-generated method stub
		
		return getSqlSession().selectList("selectCategoryByParentId", parentCategoryId);
	}

	@Override
	public List<Category> getCategoriesByParentCategoryIdAndCategoryTypeId(
			int pcid, int ctid) {
		// TODO Auto-generated method stub
		CategoryParam cp = new CategoryParam();
		cp.setParentCategoryId(pcid);
		cp.setCategoryTypeId(ctid);
		return getSqlSession().selectList("selectCategoryByParentIdAndTypeId", cp);
	}

	@Override
	public void insertCategory(Category category) {
		// TODO Auto-generated method stub
		
		getSqlSession().insert("insertCategory", category);
	}

	@Override
	public void updateCategory(Category category) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateCategory",category);
	}	
	
	@Override
	public void patchCategory(Map<String, Object> categoryMap){
		getSqlSession().update("patchCategory",categoryMap);
	}
	
	@Override
	public void deleteCategory(int id) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteCategory",id);
		
	}

	@Override
	public void deleteAllCategories() {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteAllCategories");
	}
}
