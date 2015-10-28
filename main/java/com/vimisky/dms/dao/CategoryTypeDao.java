package com.vimisky.dms.dao;

import java.util.List;
import com.vimisky.dms.entity.CategoryType;
import com.vimisky.dms.paging.Page;
import com.vimisky.dms.paging.Pageable;

public interface CategoryTypeDao {

	public CategoryType getCategoryTypeById(int id);
	public CategoryType getCategoryTypeByCode(String code);
	public List<CategoryType> getAllCategoryTypes();
	public CategoryType getOldestCategoryType();		
	public Page<CategoryType> getCategoryTypeByPagingAndSortId(Pageable pageable);
	public void insertCategoryType(CategoryType categoryType);
	public void updateCategoryType(CategoryType categoryType);
	public void updateCategoryTypeByField(int id, String name, Object value);
	public void deleteCategoryTypeById(int id);
	public void deleteAllCategoryTypes();
}

