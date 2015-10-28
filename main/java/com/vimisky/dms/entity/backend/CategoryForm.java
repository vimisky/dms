package com.vimisky.dms.entity.backend;

import java.util.HashMap;
import java.util.Map;

import com.vimisky.dms.entity.Category;

public class CategoryForm {

	Map<String, Object> categoryMap = new HashMap<String, Object>();
	Category category = new Category();
	public Map<String, Object> getCategoryMap() {
		return categoryMap;
	}
	public void setCategoryMap(Map<String, Object> categoryMap) {
		this.categoryMap = categoryMap;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	
	
}
