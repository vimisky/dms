package com.vimisky.dms.dao;

import java.util.List;
import java.util.Map;

import com.vimisky.dms.entity.Category;

public interface CategoryDao {

	public Category getOldestCategory();
	public Category getCategoryById(int id);
	public Category getCategoryByCode(String code);
	public List<Category> getCategoriesByParentCategoryId(int pcid);
	public List<Category> getCategoriesByParentCategoryIdAndCategoryTypeId(int pcid, int ctid);
	public void insertCategory(Category category);
	public void updateCategory(Category category);
	public void patchCategory(Map<String, Object> categoryMap);
	public void deleteCategory(int id);
	public void deleteAllCategories();
	
}
