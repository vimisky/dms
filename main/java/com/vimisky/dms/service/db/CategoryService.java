package com.vimisky.dms.service.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vimisky.dms.dao.CategoryDao;
import com.vimisky.dms.dao.CategoryTypeDao;
import com.vimisky.dms.entity.Category;
import com.vimisky.dms.entity.CategoryType;
import com.vimisky.dms.entity.backend.CategoryDetail;
import com.vimisky.dms.entity.backend.CategoryTree;
import com.vimisky.dms.entity.backend.OperationServiceResult;
import com.vimisky.dms.entity.backend.CategoryTree.State;
import com.vimisky.dms.utils.VimiskyUtils;

/**
 * 分类业务逻辑类
 * 所有业务逻辑都应该在Service中体现
 * */
@Component(value="dbCategoryService")
public class CategoryService {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private CategoryTypeDao categoryTypeDao;
	
	private static CategoryType defaultCategoryType = null;
	private static Category defaultRootCategory = null;
	{
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}
	/**
	 * @return the categoryDao
	 */
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}
	/**
	 * @param categoryDao the categoryDao to set
	 */
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	/**
	 * @return the categoryTypeDao
	 */
	public CategoryTypeDao getCategoryTypeDao() {
		return categoryTypeDao;
	}
	/**
	 * @param categoryTypeDao the categoryTypeDao to set
	 */
	public void setCategoryTypeDao(CategoryTypeDao categoryTypeDao) {
		this.categoryTypeDao = categoryTypeDao;
	}
	/**
	 * 验证分类类型代码是否已存在
	 * @param code 分类代码
	 * @return boolean
	 * true 存在;
	 * false 不存在
	 * */
	private boolean categoryTypeExists(String code){
		CategoryType categoryType = categoryTypeDao.getCategoryTypeByCode(code);
		return categoryType == null ? false : true;
	}
	/**
	 * 获取所有CategoryType分类类型
	 * @return 
	 * */
	public List<CategoryType> getCategoryTypes(){
		return categoryTypeDao.getAllCategoryTypes();
	}
	/**
	 * 根据CategoryTypeId获取分类类型CategoryType记录
	 * @param ctid 数据库中存储的categoryTypeId
	 * @return CategoryType 分类类型
	 * */
	public CategoryType getCategoryTypeById(int ctid){
		return categoryTypeDao.getCategoryTypeById(ctid);
	}
	/**
	 * 插入分类类型CategoryType
	 * @param categoryType 分类类型对象（未持久化）
	 * @return 无
	 * */
	public OperationServiceResult insertCategoryType(CategoryType categoryType){
		if(categoryType == null)
			return new OperationServiceResult(false, "分类类型对象不能为空");
		//检查code是否唯一
		if(categoryType.getCode() == null || categoryExists(categoryType.getCode())){
			String randomCode = null;
			do{
				randomCode = VimiskyUtils.getRandomCode();
			}while(categoryTypeExists(randomCode));
			categoryType.setCode(randomCode);
		}
		categoryType.setCreateTime(new Date());
		categoryType.setLastModifyTime(new Date());
		try{
			categoryTypeDao.insertCategoryType(categoryType);
		}catch(Exception exception){
			return new OperationServiceResult(false, "向数据库中插入分类类型失败");
		}
		return new OperationServiceResult(true);
	}
	/**
	 * 完全更新分类类型CategoryType
	 * @param categoryType 分类类型
	 * @return 无
	 * */
	public OperationServiceResult updateCategoryType(CategoryType categoryType){
		if(categoryType == null||categoryType.getId() == 0)
			return new OperationServiceResult(false, "分类类型对象不能为空或分类类型对象ID不能为0");
		CategoryType originCategoryType = categoryTypeDao.getCategoryTypeById(categoryType.getId());
		//检查code是否唯一
		if(categoryType.getCode()==null){
			categoryType.setCode(originCategoryType.getCode());
		}else if(!originCategoryType.getCode().equals(categoryType.getCode()) &&categoryExists(categoryType.getCode())){
			String randomCode = null;
			do{
				randomCode = VimiskyUtils.getRandomCode();
			}while(categoryTypeExists(randomCode));
			categoryType.setCode(randomCode);
		}

		categoryType.setLastModifyTime(new Date());
		try{
			categoryTypeDao.updateCategoryType(categoryType);			
		}catch(Exception exception){
			return new OperationServiceResult(false, "向数据库中更新分类类型失败");			
		}
		return new OperationServiceResult(true);
	}
	/**
	 * 部分更新分类类型CategoryType
	 * @param categoryType 分类类型
	 * @return OperationServiceResult
	 * */
	public OperationServiceResult updateCategoryTypePart(CategoryType categoryType){
		if(categoryType == null||categoryType.getId() == 0)
			return new OperationServiceResult(false, "分类类型对象不能为空或分类类型对象ID不能为0");
		CategoryType originCategoryType = categoryTypeDao.getCategoryTypeById(categoryType.getId());
		logger.debug("数据库中原始数据为："+originCategoryType.toString());
		if(categoryType.getName() == null)
			categoryType.setName( originCategoryType.getName());
		if(categoryType.getSecondaryName() == null)
			categoryType.setSecondaryName(originCategoryType.getSecondaryName());
		if(categoryType.getDescription() == null)
			categoryType.setDescription(originCategoryType.getDescription());
		if(categoryType.getLanguage() == null)
			categoryType.setLanguage(originCategoryType.getLanguage());
		if(categoryType.getThumbnailUrl() == null)
			categoryType.setThumbnailUrl(originCategoryType.getThumbnailUrl());
		if(categoryType.getThumbnailUri() == null)
			categoryType.setThumbnailUri(originCategoryType.getThumbnailUri());
		if(categoryType.getThumbnailIcon() == null)
			categoryType.setThumbnailIcon(originCategoryType.getThumbnailIcon());
		if(categoryType.getCode() == null){
			categoryType.setCode(originCategoryType.getCode());
		}else if(!originCategoryType.getCode().equals(categoryType.getCode()) &&categoryExists(categoryType.getCode())){
			String randomCode = null;
			do{
				randomCode = VimiskyUtils.getRandomCode();
			}while(categoryTypeExists(randomCode));
			categoryType.setCode(randomCode);
		}

		categoryType.setLastModifyTime(new Date());
		logger.debug("重新包装后的CategoryType为："+categoryType.toString());
		try{
			categoryTypeDao.updateCategoryType(categoryType);			
		}catch(Exception exception){
			return new OperationServiceResult(false, "向数据库中部分更新分类类型失败");			
		}
		return new OperationServiceResult(true);
	}	
	/**
	 * 部分更新分类类型CategoryType
	 * @param name 字段名称
	 * @param value 字段新的值
	 * @param pkid 行ID
	 * @return OperationServiceResult
	 * */
	public OperationServiceResult updateCategoryTypePart(int id, String name, Object value){
		if(id == 0 || name == null || name.trim().equals("") || value == null)
			return new OperationServiceResult(false, "分类类型字段不能为空或分类类型对象ID不能为0");
		CategoryType originCategoryType = categoryTypeDao.getCategoryTypeById(id);
		String randomCode = null;
		if(name.equals("code")){
			if(!originCategoryType.getCode().equals(value.toString()) &&categoryExists(value.toString()))
				do{
					randomCode = VimiskyUtils.getRandomCode();
				}while(categoryTypeExists(randomCode));
			value = randomCode;				
		}
		try{
			categoryTypeDao.updateCategoryTypeByField(id, name, value);
			categoryTypeDao.updateCategoryTypeByField(id, "last_modify_time", new Date());
		}catch(Exception exception){
			return new OperationServiceResult(false, "向数据库中更新分类类型字段失败");
		}

		return new OperationServiceResult(true);
	}
	/**
	 * 删除分类类型CategoryType
	 * @param categoryType 分类类型
	 * @return 无
	 * */
	public OperationServiceResult deleteCategoryType(CategoryType categoryType){
		if(categoryType == null || categoryType.getId() == 0)
			return new OperationServiceResult(false, "分类类型对象不能为空或分类类型对象ID不能为0");
		try{
			categoryTypeDao.deleteCategoryTypeById(categoryType.getId());
		}catch(Exception exception){
			return new OperationServiceResult(false, "在数据库中删除分类类型失败");			
		}
		return new OperationServiceResult(true);
	}
	/**
	 * 通过Id删除分类类型Categorytype
	 * @param id 分类类型ID
	 * @return 无
	 * */
	public OperationServiceResult deleteCategoryTypeById(int id){
		if(id == 0)
			return new OperationServiceResult(false, "分类类型对象ID不能为0");
		try{
			categoryTypeDao.deleteCategoryTypeById(id);		
		}catch(Exception exception){
			return new OperationServiceResult(false, "在数据库中删除分类类型失败");			
		}
		return new OperationServiceResult(true);
	}

	/**
	 * 删除所有分类类型
	 * @return 无
	 * */
	public OperationServiceResult deleteAllCategoryTypes(){
		try{
			categoryTypeDao.deleteAllCategoryTypes();
		}catch(Exception exception){
			return new OperationServiceResult(false, "在数据库中删除分类类型失败");			
		}
		return new OperationServiceResult(true);
	}	

	/**
	 * 批量插入分类
	 * 仅为测试使用
	 * */
	public void insertBatchCategory(List<Category> categories){
		for (Category category : categories) {
			insertCategory(category);
		}
	}

	/**
	 * 获取分类详细信息
	 * @param categoryId(int)，分类ID（数据库中的ID记录）
	 * @return Category, 分类对象
	 * */
	public Category getCategory(int categoryId){
		Category category = categoryDao.getCategoryById(categoryId);
		return category;
	}
	/**
	 * 获取某一分类下的所有直属子分类
	 * @param parentCategory 父类对象
	 * @return List<Category> 分类列表
	 * */
	public List<Category> getChildrenCategories(Category parentCategory){
		assert(parentCategory != null);
		return categoryDao.getCategoriesByParentCategoryId(parentCategory.getId());
	}
	/**
	 * 获取某一分类下的所有直属子分类
	 * @param parentCategoryId 数据库中的分类Id
	 * @return List<Category> 分类列表
	 * */
	public List<Category> getChildrenCategories(int parentCategoryId){
		return categoryDao.getCategoriesByParentCategoryId(parentCategoryId);
	}
	/**
	 * 获取某一分类下的所有直属子分类
	 * @param parentCategory 父类
	 * @param categoryType 分类类型
	 * @return List<Category> 分类列表
	 * */
	public List<Category> getChildrenCategories(Category parentCategory, CategoryType categoryType){
		assert(parentCategory != null);
		assert(categoryType != null);
		return categoryDao.getCategoriesByParentCategoryIdAndCategoryTypeId(parentCategory.getId(), categoryType.getId());		
	}
	/**
	 * 获取某一分类下的所有直属子分类
	 * @param parentCategoryId 父类Id
	 * @param categoryTypeId 分类类型Id
	 * @return List<Category> 分类列表
	 * */
	public List<Category> getChildrenCategories(int parentCategoryId, int categoryTypeId){		
		return categoryDao.getCategoriesByParentCategoryIdAndCategoryTypeId(parentCategoryId, categoryTypeId);
	}
	/**
	 * 获取某一分类下的所有子分类
	 * @param ancesterCategory 祖先分类
	 * @return List<Category> 分类列表
	 * */
	public List<Category> getCategories(Category ancesterCategory){
		assert(ancesterCategory != null);
		return getCategories(ancesterCategory.getId());
	}		
	/**
	 * 获取某一分类下的所有子分类
	 * 深度优先遍历DP
	 * 递归
	 * @param ancesterCategoryId 祖先分类Id，默认根祖先Id为0
	 * @return List<Category> 分类列表
	 * */
	public List<Category> getCategories(int ancesterCategoryId){
		List<Category> categories = null;
		List<Category> toAddCategories = null;
		if( (toAddCategories = getChildrenCategories(ancesterCategoryId)) != null && toAddCategories.size()>0){
			categories = new ArrayList<Category>(toAddCategories.size());
			for(Category category : toAddCategories){
				categories.add(category);
				categories.addAll(getCategories(category.getId()));
			}
		}
		return categories;
	}
	/**
	 * 获取某一分类下的所有分类，供前端页面使用
	 * */
	public List<CategoryTree> getCategoriesOfTree(int ancesterCategoryId){
		List<CategoryTree> categories = null;
		List<Category> toAddCategories = null;
		if( (toAddCategories = getChildrenCategories(ancesterCategoryId)) != null && toAddCategories.size()>0){
			categories =  new ArrayList<CategoryTree>(toAddCategories.size());
			for(Category category : toAddCategories){
				CategoryTree categoryTree = new CategoryTree();
				categoryTree.setText(category.getName());
				categoryTree.setId(String.valueOf(category.getId()));
				CategoryTree.State state = new CategoryTree.State();
				state.setOpened(true);
				categoryTree.setState(state);
				categoryTree.setType("default");
				categoryTree.setChildren(getCategoriesOfTree(category.getId()));
				categories.add(categoryTree);
			}
		}
		return categories;
	}
	/**
	 * 获取某一分类下的所有子分类
	 * */
	public List<Category> getCategories(Category ancesterCategory, CategoryType categoryType){
		List<Category> categories = new ArrayList<Category>();
		
		return categories;
	}		
	/**
	 * 获取某一分类下的所有子分类
	 * */
	public List<Category> getCategories(int ancesterCategoryId, int categoryTypeId){
		List<Category> categories = new ArrayList<Category>();
		
		return categories;
	}
	
	/**
	 * 插入1条新的分类记录
	 * @param category 分类
	 * @return 无
	 * */
	public void insertCategory(Category category){
		//处理关系
		//检查分类父分类
		if(category == null)
			return;
		if(category.getParentCategoryId() == 0 )
			category.setParentCategoryId(getRootCategory().getId());
		//检查分类类型
//		if(category.getCategoryType() == null)
//			category.setCategoryType(getDefaultCategoryType());
		//检查code是否唯一
		if(category.getCode() == null || categoryExists(category.getCode())){
			String randomCode = null;
			do{
				randomCode = VimiskyUtils.getRandomCode();
			}while(categoryExists(randomCode));
			category.setCode(randomCode);
		}
		category.setCreateTime(new Date());
		category.setLastModifyTime(new Date());
		categoryDao.insertCategory(category);
		//留下记录日志的锚点，日后再写
		/**
		 * to do , write to log
		 * */
	}
	/**
	 * 验证分类代码是否已存在
	 * @param code 分类代码
	 * @return boolean
	 * true 存在;
	 * false 不存在
	 * */
	private boolean categoryExists(String code){
		Category category = categoryDao.getCategoryByCode(code);
		return category == null ? false : true;
	}
	/**
	 * 获取默认分类类型
	 * @return CategoryType 分类类型
	 * */
	private CategoryType getDefaultCategoryType(){
		return defaultCategoryType == null ? categoryTypeDao.getOldestCategoryType() : defaultCategoryType;
	}
	/**
	 * 获取根分类
	 * @return Category根分类
	 * */
	private Category getRootCategory(){
		return defaultRootCategory == null ? categoryDao.getOldestCategory() : defaultRootCategory;
	}

	/**
	 * 更新分类信息
	 * @param category 分类
	 * @return 无
	 * */
	public void updateCategory(Category category){
		assert(category!=null);
		if(category.getParentCategoryId() == 0 )
			category.setParentCategoryId(getRootCategory().getId());
		//检查分类类型
//		if(category.getCategoryType() == null)
//			category.setCategoryType(getDefaultCategoryType());
		//检查code是否唯一
		if(category.getCode() == null || categoryExists(category.getCode())){
			String randomCode = null;
			do{
				randomCode = VimiskyUtils.getRandomCode();
			}while(categoryExists(randomCode));
			category.setCode(randomCode);
		}
		category.setLastModifyTime(new Date());
		categoryDao.updateCategory(category);
	}

	/**
	 * 删除分类
	 * @param category 分类（必须有Id）
	 * @return 无
	 * */
	public void deleteCategory(int cid){
		categoryDao.deleteCategory(cid);
	}
	
	/**
	 * 删除所有分类
	 * 仅用于测试
	 * */
	public void deleteAllCategories(){
		categoryDao.deleteAllCategories();
	}
	/**
	 * 插入根分类
	 * 仅用于测试
	 * */
	public void initRootCategory(){
		CategoryType categoryType = new CategoryType();
		categoryType.setId(1);
		categoryType.setName("DMS新闻分类");
		categoryType.setSecondaryName("新闻分类类型");
		categoryType.setDescription("新闻分类类型，用于新闻稿件");
		categoryType.setThumbnailUrl("http://www.baidu.com");
		categoryType.setCode("NEWSC");
		categoryType.setCreateTime(new Date());
		categoryType.setLastModifyTime(new Date());
		categoryType.setLanguage("简体中文");
		categoryTypeDao.insertCategoryType(categoryType);
		Category category = new Category();
		category.setId(1);
		category.setName("所有分类");
		category.setSecondaryName("根分类");
		category.setDescription("初始化分类");
		category.setThumbnailUrl("http://www.baidu.com");
		category.setCode("ROOTC");
		category.setCreateTime(new Date());
		category.setLastModifyTime(new Date());
		category.setLanguage("简体中文");
//		category.setCategoryType(getDefaultCategoryType());
		category.setParentCategoryId(0);
		categoryDao.insertCategory(category) ;
		category = new Category();
		category.setId(2);
		category.setName("待编稿库");
		category.setSecondaryName("素材库To_Edit");
		category.setDescription("素材入库，需要编辑");
		category.setThumbnailUrl("http://www.baidu.com");
		category.setCode("TOEDB");
		category.setCreateTime(new Date());
		category.setLastModifyTime(new Date());
		category.setLanguage("简体中文");
//		category.setCategoryType(getDefaultCategoryType());
		category.setParentCategoryId(1);
		categoryDao.insertCategory(category) ;
		category = new Category();
		category.setId(3);
		category.setName("待签稿库");
		category.setSecondaryName("半成品库TO_PUBLISH");
		category.setDescription("编辑好的稿件，等待签发人签发到成品");
		category.setThumbnailUrl("http://www.baidu.com");
		category.setCode("TOPDB");
		category.setCreateTime(new Date());
		category.setLastModifyTime(new Date());
		category.setLanguage("简体中文");
//		category.setCategoryType(getDefaultCategoryType());
		category.setParentCategoryId(1);
		categoryDao.insertCategory(category) ;
		category = new Category();
		category.setId(4);
		category.setName("已签稿库");
		category.setSecondaryName("成品库PUBLISH");
		category.setDescription("已经签发的稿件，成品稿件，可用于引用、编辑等");
		category.setThumbnailUrl("http://www.baidu.com");
		category.setCode("PUBDB");
		category.setCreateTime(new Date());
		category.setLastModifyTime(new Date());
		category.setLanguage("简体中文");
//		category.setCategoryType(getDefaultCategoryType());
		category.setParentCategoryId(1);
		categoryDao.insertCategory(category) ;
	}
}
