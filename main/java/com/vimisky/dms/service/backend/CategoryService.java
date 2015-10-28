package com.vimisky.dms.service.backend;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.vimisky.dms.dao.CategoryDao;
import com.vimisky.dms.dao.CategoryTypeDao;
import com.vimisky.dms.dao.comp.CategoryQueryDao;
import com.vimisky.dms.entity.Category;
import com.vimisky.dms.entity.CategoryType;
import com.vimisky.dms.entity.backend.CategoryBrief;
import com.vimisky.dms.entity.backend.CategoryDetail;
import com.vimisky.dms.entity.backend.CategoryTree;
import com.vimisky.dms.entity.backend.CategoryTypeDetail;
import com.vimisky.dms.entity.backend.OperationServiceResult;
import com.vimisky.dms.utils.VimiskyUtils;
/**
 * 分类业务逻辑类
 * 所有业务逻辑都应该在Service中体现
 * */
@Component(value="backendCategoryService")
public class CategoryService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private CategoryTypeDao categoryTypeDao;
	@Autowired
	private CategoryQueryDao categoryQueryDao;
	
	private static CategoryType defaultCategoryType = null;
	private static Category defaultRootCategory = null;
	{
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}
	private static Map<String, String> categoryTypeParamMap = new HashMap<String, String>();
	{
		categoryTypeParamMap.put("name", "name");
		categoryTypeParamMap.put("secondaryName", "secondary_name");
		categoryTypeParamMap.put("description", "description");
		categoryTypeParamMap.put("language", "language");
		categoryTypeParamMap.put("thumbnailUrl", "thumbnail_url");
		categoryTypeParamMap.put("thumbnailUri", "thumbnail_uri");
		categoryTypeParamMap.put("thumbnailIcon", "thumbnail_icon");
		categoryTypeParamMap.put("code", "code");
		categoryTypeParamMap.put("createTime", "create_time");
		categoryTypeParamMap.put("lastModifyTime", "last_modify_time");
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
	 * 获取默认分类类型
	 * @return CategoryType 分类类型
	 * */
	private CategoryType getDefaultCategoryType(){
		return defaultCategoryType == null ? categoryTypeDao.getOldestCategoryType() : defaultCategoryType;
	}
	/**
	 * 通过ID验证分类类型是否已存在
	 * @param categoryTypeId 分类类型ID
	 * @return boolean
	 * true 存在;
	 * false 不存在
	 * */
	private boolean categoryTypeExists(int categoryTypeId){
		CategoryType categoryType = categoryTypeDao.getCategoryTypeById(categoryTypeId);
		return categoryType == null ? false : true;
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
	 * 获取所有CategoryTypeDetail分类类型
	 * @return 
	 * */
	public List<CategoryTypeDetail> getCategoryTypes(){
		List<CategoryTypeDetail> categoryTypeDetails = new ArrayList<CategoryTypeDetail>();
		for (CategoryType categoryType : categoryTypeDao.getAllCategoryTypes()) {
			CategoryTypeDetail categoryTypeDetail = new CategoryTypeDetail(categoryType);
			categoryTypeDetails.add(categoryTypeDetail);
		}
		return categoryTypeDetails;
	}
	/**
	 * 根据CategoryTypeId获取分类类型CategoryTypeDetail记录
	 * @param ctid 数据库中存储的categoryTypeId
	 * @return CategoryTypeDetail 分类类型
	 * */
	public CategoryTypeDetail getCategoryTypeById(int ctid){
		CategoryType categoryType = categoryTypeDao.getCategoryTypeById(ctid);
		return categoryType == null ? null : new CategoryTypeDetail(categoryType);
	}
	/**
	 * 插入分类类型CategoryTypeDetail
	 * @param CategoryTypeDetail 分类类型对象（未持久化）
	 * @return 无
	 * */
	public OperationServiceResult insertCategoryType(CategoryTypeDetail categoryTypeDetail){
		if(categoryTypeDetail == null)
			return new OperationServiceResult(false, "分类类型对象不能为空");
		//检查code是否唯一
		if(categoryTypeDetail.getCode() == null || categoryExists(categoryTypeDetail.getCode())){
			String randomCode = null;
			do{
				randomCode = VimiskyUtils.getRandomCode();
			}while(categoryTypeExists(randomCode));
			categoryTypeDetail.setCode(randomCode);
		}
		categoryTypeDetail.setCreateTime(new Date());
		categoryTypeDetail.setLastModifyTime(new Date());
		try{
			categoryTypeDao.insertCategoryType(categoryTypeDetail.convert2CategoryType());
		}catch(Exception exception){
			return new OperationServiceResult(false, "向数据库中插入分类类型失败");
		}
		return new OperationServiceResult(true);
	}
	/**
	 * 完全更新分类类型CategoryTypeDetail
	 * @param CategoryTypeDetail 分类类型
	 * @return 无
	 * */
	public OperationServiceResult updateCategoryType(CategoryTypeDetail categoryTypeDetail){
		if(categoryTypeDetail == null||categoryTypeDetail.getId() == 0)
			return new OperationServiceResult(false, "分类类型对象不能为空或分类类型对象ID不能为0");
		CategoryType originCategoryType = categoryTypeDao.getCategoryTypeById(categoryTypeDetail.getId());
		//检查code是否唯一
		if(categoryTypeDetail.getCode()==null){
			categoryTypeDetail.setCode(originCategoryType.getCode());
		}else if(!originCategoryType.getCode().equals(categoryTypeDetail.getCode()) &&categoryExists(categoryTypeDetail.getCode())){
			String randomCode = null;
			do{
				randomCode = VimiskyUtils.getRandomCode();
			}while(categoryTypeExists(randomCode));
			categoryTypeDetail.setCode(randomCode);
		}

		categoryTypeDetail.setLastModifyTime(new Date());
		try{
			categoryTypeDao.updateCategoryType(categoryTypeDetail.convert2CategoryType());			
		}catch(Exception exception){
			return new OperationServiceResult(false, "向数据库中更新分类类型失败");			
		}
		return new OperationServiceResult(true);
	}
	/**
	 * 部分更新分类类型CategoryTypeDetail
	 * @param CategoryTypeDetail 分类类型
	 * @return OperationServiceResult
	 * */
	public OperationServiceResult updateCategoryTypePart(CategoryTypeDetail categoryTypeDetail){
		if(categoryTypeDetail == null||categoryTypeDetail.getId() == 0)
			return new OperationServiceResult(false, "分类类型对象不能为空或分类类型对象ID不能为0");
		CategoryType originCategoryType = categoryTypeDao.getCategoryTypeById(categoryTypeDetail.getId());
		logger.debug("数据库中原始数据为："+originCategoryType.toString());
		if(categoryTypeDetail.getName() == null)
			categoryTypeDetail.setName( originCategoryType.getName());
		if(categoryTypeDetail.getSecondaryName() == null)
			categoryTypeDetail.setSecondaryName(originCategoryType.getSecondaryName());
		if(categoryTypeDetail.getDescription() == null)
			categoryTypeDetail.setDescription(originCategoryType.getDescription());
		if(categoryTypeDetail.getLanguage() == null)
			categoryTypeDetail.setLanguage(originCategoryType.getLanguage());
		if(categoryTypeDetail.getThumbnailUrl() == null)
			categoryTypeDetail.setThumbnailUrl(originCategoryType.getThumbnailUrl());
		if(categoryTypeDetail.getThumbnailUri() == null)
			categoryTypeDetail.setThumbnailUri(originCategoryType.getThumbnailUri());
		if(categoryTypeDetail.getThumbnailIcon() == null)
			categoryTypeDetail.setThumbnailIcon(originCategoryType.getThumbnailIcon());
		if(categoryTypeDetail.getCode() == null){
			categoryTypeDetail.setCode(originCategoryType.getCode());
		}else if(!originCategoryType.getCode().equals(categoryTypeDetail.getCode()) &&categoryExists(categoryTypeDetail.getCode())){
			String randomCode = null;
			do{
				randomCode = VimiskyUtils.getRandomCode();
			}while(categoryTypeExists(randomCode));
			categoryTypeDetail.setCode(randomCode);
		}

		categoryTypeDetail.setLastModifyTime(new Date());
		logger.debug("重新包装后的CategoryType为："+categoryTypeDetail.toString());
		try{
			categoryTypeDao.updateCategoryType(categoryTypeDetail.convert2CategoryType());			
		}catch(Exception exception){
			return new OperationServiceResult(false, "向数据库中部分更新分类类型失败");			
		}
		return new OperationServiceResult(true);
	}	
	/**
	 * 部分更新分类类型CategoryTypeDetail
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
			categoryTypeDao.updateCategoryTypeByField(id, categoryTypeParamMap.get(name), value);
			categoryTypeDao.updateCategoryTypeByField(id, "last_modify_time", new Date());
		}catch(Exception exception){
			return new OperationServiceResult(false, "向数据库中更新分类类型字段失败");
		}

		return new OperationServiceResult(true);
	}
	/**
	 * 删除分类类型CategoryTypeDetail
	 * @param categoryTypeDetail 分类类型
	 * @return 无
	 * */
	public OperationServiceResult deleteCategoryType(CategoryTypeDetail categoryTypeDetail){
		if(categoryTypeDetail == null || categoryTypeDetail.getId() == 0)
			return new OperationServiceResult(false, "分类类型对象不能为空或分类类型对象ID不能为0");
		try{
			categoryTypeDao.deleteCategoryTypeById(categoryTypeDetail.getId());
		}catch(Exception exception){
			return new OperationServiceResult(false, "在数据库中删除分类类型失败");			
		}
		return new OperationServiceResult(true);
	}
	/**
	 * 通过Id删除分类类型CategoryTypeDetail
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
	 * 为前端分类配置页面提供分类简略信息列表，适用于dataTable
	 * 深度遍历
	 * */
	public List<CategoryBrief> getCategoryBriefList(int parentCategoryId){
		List<CategoryBrief> categories = categoryQueryDao.getCategoryBriefsByParentId(parentCategoryId);
		if(categories != null && categories.size() > 0){
			for (CategoryBrief categoryBrief : categories) {
				categoryBrief.setSonCategoryBriefs(getCategoryBriefList(categoryBrief.getId()));
			}
		}

		return categories;
	}
	/**
	 * 为前端分类配置页面提供分类树，适用于jstree
	 * */
	public List<CategoryTree> getCategoriesOfTree(int ancesterCategoryId){
		List<CategoryTree> categories = null;
		List<Category> toAddCategories = null;
		if( (toAddCategories = categoryDao.getCategoriesByParentCategoryId(ancesterCategoryId)) != null && toAddCategories.size()>0){
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
	 * 为前端分类配置界面提供分类详细信息
	 * */
	public CategoryDetail getCategoryDetail(int categoryId){
		return categoryQueryDao.getCategoryDetailById(categoryId);
	}	

	/**
	 * 插入1条新的分类记录
	 * @param category 分类
	 * @return 无
	 * */
	public OperationServiceResult insertCategory(Category category){
		//处理关系
		assert(category!=null);
		//检查分类父分类
		if(!categoryExists(category.getParentCategoryId()))
			return new OperationServiceResult(false, "父分类不存在");
		//检查分类类型
		if(!categoryTypeExists(category.getCategoryTypeId())){
			return new OperationServiceResult(false, "分类类型不存在");
		}
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
		try {
			categoryDao.insertCategory(category);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return new OperationServiceResult(false, "新增分类数据失败");
		}
		return new OperationServiceResult(true);		
		
		//留下记录日志的锚点，日后再写
		/**
		 * to do , write to log
		 * */
	}
	/**
	 * 更新分类信息
	 * @param category 分类
	 * @return 无
	 * */
	public OperationServiceResult updateCategory(Category category){

		assert(category!=null);
		
		//这里为业务检查，非参数验证
		//检查分类父分类
		if(!categoryExists(category.getParentCategoryId()))
			return new OperationServiceResult(false, "父分类不存在");
		//检查分类类型
		if(!categoryTypeExists(category.getCategoryTypeId())){
			return new OperationServiceResult(false, "分类类型不存在");
		}
		//检查code是否唯一
		if(category.getCode() == null || categoryExists(category.getCode())){
			String randomCode = null;
			do{
				randomCode = VimiskyUtils.getRandomCode();
			}while(categoryExists(randomCode));
			category.setCode(randomCode);
		}
		category.setLastModifyTime(new Date());
		try {
			categoryDao.updateCategory(category);			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return new OperationServiceResult(false, "更新分类数据失败");
		}
		return new OperationServiceResult(true);
	}

	public OperationServiceResult patchCategory(int id, Map<String, String[]> parameterMap){
		assert(parameterMap!=null);
		Map<String, Object> categoryMap = new HashMap<String, Object>();
		
		//检查父分类ID
		if(parameterMap.containsKey("parentCategoryId")){
			int parentCategoryId = new Integer(parameterMap.get("parentCategoryId")[0]);
			if ( parentCategoryId == 0 || !categoryExists(parentCategoryId)) {
				return new OperationServiceResult(false, "父分类不存在");
			}else {
				categoryMap.put("parentCategoryId", parentCategoryId);
			}
		}
		//检查分类类型ID
		if(parameterMap.containsKey("categoryTypeId")){
			int categoryTypeId = new Integer(parameterMap.get("categoryTypeId")[0]);
			if ( categoryTypeId == 0 || !categoryTypeExists(categoryTypeId)) {
				return new OperationServiceResult(false, "分类类型不存在");
			}else {
				categoryMap.put("categoryTypeId", categoryTypeId);
			}
		}
		//检查分类代码
		if(parameterMap.containsKey("code")){
			String code = (String)parameterMap.get("code")[0];
			if(code == null || categoryExists(code)){
				return new OperationServiceResult(false, "分类代码不存在");
			}else {
				categoryMap.put("code", code);
			}
		}		
		if (parameterMap.containsKey("name")) {
			categoryMap.put("name", parameterMap.get("name")[0]);
		}
		if (parameterMap.containsKey("secondaryName")) {
			categoryMap.put("secondaryName", parameterMap.get("secondaryName")[0]);
		}
		if (parameterMap.containsKey("description")) {
			categoryMap.put("description", parameterMap.get("description")[0]);
		}
		if (parameterMap.containsKey("language")) {
			categoryMap.put("language", parameterMap.get("language")[0]);
		}
		if (parameterMap.containsKey("thumbnailUrl")) {
			categoryMap.put("thumbnailUrl", parameterMap.get("thumbnailUrl")[0]);
		}
		if (parameterMap.containsKey("thumbnailUri")) {
			categoryMap.put("thumbnailUri", parameterMap.get("thumbnailUri")[0]);
		}
		if (parameterMap.containsKey("thumbnailIcon")) {
			categoryMap.put("thumbnailIcon", parameterMap.get("thumbnailIcon")[0]);
		}
		if (parameterMap.containsKey("useState")) {
			categoryMap.put("useState", new Integer(parameterMap.get("useState")[0]));
		}
		if (parameterMap.containsKey("priority")) {
			categoryMap.put("priority", new Integer(parameterMap.get("priority")[0]));
		}
		
		categoryMap.put("lastModifyTime", new Date());
		categoryMap.put("id", id);
		try {
			categoryDao.patchCategory(categoryMap);			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return new OperationServiceResult(false, "部分更新分类数据失败");
		}
		return new OperationServiceResult(true);
	}	
	
	/**
	 * 通过ID验证分类是否存在
	 * @param categoryId 分类代码
	 * @return boolean
	 * true 存在;
	 * false 不存在
	 * */
	private boolean categoryExists(int categoryId){
		Category category = categoryDao.getCategoryById(categoryId);
		return category == null ? false : true;
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
	 * 获取根分类
	 * @return Category根分类
	 * */
	private Category getRootCategory(){
		return defaultRootCategory == null ? categoryDao.getOldestCategory() : defaultRootCategory;
	}
	/**
	 * 删除分类
	 * @param category 分类（必须有Id）
	 * @return 无
	 * */
	public OperationServiceResult deleteCategory(CategoryDetail categoryDetail){
		assert(categoryDetail!=null);
		
		try {
			categoryDao.deleteCategory(categoryDetail.getId());			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return new OperationServiceResult(false, "删除分类失败");
		}
		return new OperationServiceResult(true);
	}
	/**
	 * 删除分类
	 * @param category 分类（必须有Id）
	 * @return 无
	 * */
	public OperationServiceResult deleteCategory(int cid){
		try {
			categoryDao.deleteCategory(cid);			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return new OperationServiceResult(false, "删除分类失败");
		}
		return new OperationServiceResult(true);
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
