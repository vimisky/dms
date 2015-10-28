package com.vimisky.dms.dao.comp;

import java.util.List;

import com.vimisky.dms.entity.backend.CategoryBrief;
import com.vimisky.dms.entity.backend.CategoryDetail;

public interface CategoryQueryDao {

	CategoryDetail getCategoryDetailById(int id);
	List<CategoryBrief> getCategoryBriefsByParentId(int parentId);
}
