package com.vimisky.dms.controller.db;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vimisky.dms.entity.Category;
import com.vimisky.dms.entity.CategoryType;
import com.vimisky.dms.entity.backend.CategoryDetail;
import com.vimisky.dms.entity.backend.CategoryTree;
import com.vimisky.dms.entity.backend.OperationServiceResult;
import com.vimisky.dms.entity.backend.RestfulResult;
import com.vimisky.dms.service.db.CategoryService;

@Controller(value="dbCategoryController")
@RequestMapping(value="/db/restful")
public class CategoryController {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private CategoryService dbDategoryService;
	
	public CategoryService getDbDategoryService() {
		return dbDategoryService;
	}
	public void setDbDategoryService(CategoryService dbDategoryService) {
		this.dbDategoryService = dbDategoryService;
	}
	/**
	 * POST Json的条件下，
	 * 验证
	 * 格式转换
	 * 
	 * 传递全部Object，DAO方便；但是明明不需要传递其它参数
	 * 传递部分，组装Object，DAO方便
	 * 传递部分，部分升级，DAO写的复杂
	 * 其实以上都是扯得，在服务器端每次更新前都select一下，就是效率低一些，但是方便了所有。
	 * update的情况并不多
	 * */
	@InitBinder
	public void CategoryJsonInitBinder(WebDataBinder webDataBinder){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
//		webDataBinder.registerCustomEditor(CategoryType.class, new CategoryTypeJsonEditor());
		logger.info("initBinder called");
	}
	/**
	 * 验证Restful Web服务是否可用
	 * */
	@RequestMapping(value="/available")
	@ResponseBody
	public RestfulResult getRestfulResult(){
		RestfulResult rr = new RestfulResult();
		rr.setSuccess(true);
		return rr;
	}

	/**
	 * json版本，获取分类类型列表
	 * */
	@RequestMapping(value="/categorytype", method=RequestMethod.GET)
	@ResponseBody
	public List<CategoryType> getCategoryTypesJson(){
		List<CategoryType> categoryTypes = this.dbDategoryService.getCategoryTypes();
		logger.info("请求分类类型列表");
		return categoryTypes;
	}
	
	/**
	 * json版本，获取某个分类类型的详细信息
	 * */
	@RequestMapping(value="/categorytype/{id}",method=RequestMethod.GET)
	@ResponseBody
	public CategoryType getCategoryTypeJson(@PathVariable int id){
		return this.dbDategoryService.getCategoryTypeById(id);
	}
	/**
	 * json版本，插入某个分类类型
	 * */
	@RequestMapping(value="/categorytype", method=RequestMethod.POST)
	@ResponseBody
	public RestfulResult insertCategoryType(@ModelAttribute CategoryType categoryType){
		logger.info("controller called");

		OperationServiceResult osr = this.dbDategoryService.insertCategoryType(categoryType);
		return new RestfulResult(osr);
	}
	/**
	 * json版本，响应OPTIONS
	 * */
	@RequestMapping(value="/categorytype/{id}", method=RequestMethod.OPTIONS)
	@ResponseBody
	public RestfulResult updateCategoryTypeOPTIONS(@PathVariable int id){
		logger.info("响应HTTP OPTIONS Method");		
		return new RestfulResult(true);
	}
	/**
	 * json版本，更新某个分类类型
	 * */
	@RequestMapping(value="/categorytype/{ctid}", method=RequestMethod.PUT)
	@ResponseBody
	public RestfulResult updateCategoryType(@PathVariable int ctid, @ModelAttribute CategoryType categoryType){
		logger.info("通过HTTP PUT方法对分类"+categoryType.getName()+"进行全部升级");
		if(categoryType.getId() == 0)
			categoryType.setId(ctid);
		OperationServiceResult osr = this.dbDategoryService.updateCategoryType(categoryType);
		return new RestfulResult(osr);
	}
	/**
	 * json版本，部分更新某个分类类型
	 * */
	@RequestMapping(value="/categorytype/{id}", method=RequestMethod.PATCH)
	@ResponseBody
	public RestfulResult updateCategoryTypePart(@PathVariable int id, @ModelAttribute CategoryType categoryType){
		logger.info("通过HTTP PATCH方法对分类类型进行部分升级");
		logger.debug("原始CategoryType为："+categoryType.toString());
		if(categoryType.getId() == 0)
			categoryType.setId(id);
		OperationServiceResult osr = dbDategoryService.updateCategoryTypePart(categoryType);
		return new RestfulResult(osr);
	}
	/**
	 * json版本，更新分类类型单个属性字段
	 * */
	@RequestMapping(value="/categorytype/updatefield", method=RequestMethod.POST, params={"name","value","pk"})
	@ResponseBody
	public RestfulResult updateCategoryTypeField(@RequestParam(value="name", required = true) String name,
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "pk", required = true) int pk){
		OperationServiceResult osr = dbDategoryService.updateCategoryTypePart(pk, name, value);
		return new RestfulResult(osr);
		
	}
	/**
	 * json版本，删除某个分类类型
	 * */
	@RequestMapping(value="/categorytype/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public RestfulResult deleteCategoryType(@PathVariable int id){
		OperationServiceResult osr = this.dbDategoryService.deleteCategoryTypeById(id);
		return new RestfulResult(osr);
	}
	/**
	 * json 版本，获取分类Category列表信息
	 * */
	@RequestMapping(value="/category", method=RequestMethod.GET)
	@ResponseBody
	public List<CategoryTree> getCategoriesJson(@RequestBody String body,HttpServletResponse response){
		List<CategoryTree> categories = dbDategoryService.getCategoriesOfTree(1);
//		logger.info("exe category controller");
//		response.addHeader("Access-Control-Allow-Origin", "*");
		return categories;
	}
	/**
	 * jsonp 版本，获取分类Category列表信息
	 * */
	@RequestMapping(value="/category", method=RequestMethod.GET, params="callback", produces="application/javascript;charset=UTF-8")
	@ResponseBody
	public String getCategoriesJsonp(@RequestParam(value="callback", required=true) String callback){
		List<CategoryTree> categories = dbDategoryService.getCategoriesOfTree(1);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String res = objectMapper.writeValueAsString(categories);
//			String.format("%s(%s)",callback,res); 
//			return callback+"("+res+");";
			return String.format("%s(%s)",callback,res);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Json版本,更新分类Category
	 * */
	@RequestMapping(value="/category/{cid}", method=RequestMethod.PUT)
	@ResponseBody
	public RestfulResult updateCategoryJson(@PathVariable int cid, @ModelAttribute Category category){
		logger.info("通过HTTP PUT方法对分类"+category.getName()+"进行全部升级");

		if(category.getId() == 0)
			category.setId(cid);
		dbDategoryService.updateCategory(category);
		return new RestfulResult(true);
	}
	/**
	 * Json版本,更新分类Category
	 * */
	@RequestMapping(value="/category/{cid}", method=RequestMethod.OPTIONS)
	@ResponseBody
	public RestfulResult updateCategoryOPTIONS(@PathVariable int cid, @ModelAttribute Category category, HttpServletResponse response){
		logger.info("响应HTTP OPTIONS Method");
//		response.addHeader("Access-Control-Allow-Origin", "*");
		return new RestfulResult(true);
	}
	/**
	 * Json版本,更新分类Category
	 * */
	@RequestMapping(value="/category/{cid}", method=RequestMethod.PATCH)
	@ResponseBody
	public RestfulResult updateCategoryPart(@ModelAttribute Category category,@PathVariable int cid){
		logger.info("通过HTTP PATCH方法对分类"+category.getName()+"进行部分升级");
		logger.info("category icon is "+ category.getThumbnailIcon());
		if(category.getId() == 0)
			category.setId(cid);
		Category originCategory = dbDategoryService.getCategory(cid);
		if(category.getName() == null)
			category.setName(originCategory.getName());
		if(category.getSecondaryName() == null)
			category.setSecondaryName(originCategory.getSecondaryName());
		if(category.getDescription() == null)
			category.setDescription(originCategory.getDescription());
		if(category.getCode() == null)
			category.setCode(originCategory.getCode());
		if(category.getThumbnailUrl() == null)
			category.setThumbnailUrl(originCategory.getThumbnailUrl());
		if(category.getThumbnailUri() == null)
			category.setThumbnailUri(originCategory.getThumbnailUri());
		if(category.getThumbnailIcon() == null)
			category.setThumbnailIcon(originCategory.getThumbnailIcon());
		if(category.getParentCategoryId() == 0)
			category.setParentCategoryId(originCategory.getParentCategoryId());
		if(category.getLanguage() == null)
			category.setLanguage(originCategory.getLanguage());
//		if(category.getCategoryType() == null)
//			category.setCategoryType(originCategory.getCategoryType());
		if(category.getCreateTime() == null)
			category.setCreateTime(originCategory.getCreateTime());
		dbDategoryService.updateCategory(category);
		return new RestfulResult(true);
	}
	/**
	 * Json版本,更新分类Category
	 * */
	@RequestMapping(value="/category/{cid}", method=RequestMethod.POST)
	@ResponseBody
	public RestfulResult postUpdateCategory(@PathVariable int cid, @ModelAttribute Category category, @RequestParam(value="partupdate") boolean partupdate){
		logger.info("通过HTTP POST方法对分类"+category.getName()+"进行部分升级");

		if(category.getId() == 0)
			category.setId(cid);
		if(partupdate == true){
			Category originCategory = dbDategoryService.getCategory(cid);
			if(category.getName() == null)
				category.setName(originCategory.getName());
			if(category.getSecondaryName() == null)
				category.setSecondaryName(originCategory.getSecondaryName());
			if(category.getDescription() == null)
				category.setDescription(originCategory.getDescription());
			if(category.getCode() == null)
				category.setCode(originCategory.getCode());
			if(category.getThumbnailUrl() == null)
				category.setThumbnailUrl(originCategory.getThumbnailUrl());
			if(category.getThumbnailUri() == null)
				category.setThumbnailUri(originCategory.getThumbnailUri());
			if(category.getThumbnailIcon() == null)
				category.setThumbnailIcon(originCategory.getThumbnailIcon());
			if(category.getParentCategoryId() == 0)
				category.setParentCategoryId(originCategory.getParentCategoryId());
			if(category.getLanguage() == null)
				category.setLanguage(originCategory.getLanguage());
//			if(category.getCategoryType() == null)
//				category.setCategoryType(originCategory.getCategoryType());
			if(category.getCreateTime() == null)
				category.setCreateTime(originCategory.getCreateTime());
		}
		dbDategoryService.updateCategory(category);
		return new RestfulResult(true);
	}
	/**
	 * Jsonp版本，没有这个方法，只要是Jsonp就是GET
	 * */
	/*
	@RequestMapping(value="/category/{cid}", method=RequestMethod.POST, params="callback", produces="application/javascript;charset=UTF-8")
	@ResponseBody
	public String updateCategoryJsonp(@PathVariable int cid, @ModelAttribute Category category, @RequestParam(value="partupdate") boolean partupdate, @RequestParam("callback") String callback){
		logger.info("category name ------"+category.getName());
		logger.info("category 是否部分升级？"+partupdate);
		if(category.getId() == 0)
			category.setId(cid);
		if(partupdate == true){
			Category originCategory = categoryService.getCategory(cid);
			if(category.getName() == null)
				category.setName(originCategory.getName());
			if(category.getSecondaryName() == null)
				category.setSecondaryName(originCategory.getSecondaryName());
			if(category.getDescription() == null)
				category.setDescription(originCategory.getDescription());
			if(category.getCode() == null)
				category.setCode(originCategory.getCode());
			if(category.getThumbnailUrl() == null)
				category.setThumbnailUrl(originCategory.getThumbnailUrl());
			if(category.getThumbnailUri() == null)
				category.setThumbnailUri(originCategory.getThumbnailUri());
			if(category.getThumbnailIcon() == null)
				category.setThumbnailIcon(originCategory.getThumbnailIcon());
			if(category.getParentCategoryId() == 0)
				category.setParentCategoryId(originCategory.getParentCategoryId());
			if(category.getLanguage() == null)
				category.setLanguage(originCategory.getLanguage());
			if(category.getCategoryType() == null)
				category.setCategoryType(originCategory.getCategoryType());
			if(category.getCreateTime() == null)
				category.setCreateTime(originCategory.getCreateTime());
		}
		categoryService.updateCategory(category);
		StatusDetail statusDetail = new StatusDetail(true);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String res = objectMapper.writeValueAsString(statusDetail);
			return String.format("%s(%s)",callback,res);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.format("%s({\"status\":\"false\"})",callback);
	}*/
	/**
	 * Json版本
	 * */
	@RequestMapping(value="/category", method=RequestMethod.POST)
	@ResponseBody
	public RestfulResult insertCategoryJson(@ModelAttribute Category category){
		logger.info("通过HTTP POST方法新增分类"+category.getName());
		dbDategoryService.insertCategory(category);
		return new RestfulResult(true);
	}
	/**
	 * Jsonp版本，没有这个方法，只要是Jsonp就是GET
	 * */
	/*
	@RequestMapping(value="/category", method=RequestMethod.POST, params="callback", produces="application/javascript;charset=UTF-8")
	@ResponseBody
	public String insertCategoryJsonp(@RequestBody String body, @ModelAttribute Category category, @RequestParam("callback") String callback){
		categoryService.insertCategory(category);
		StatusDetail statusDetail = new StatusDetail(true);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String res = objectMapper.writeValueAsString(statusDetail);
			return String.format("%s(%s)",callback,res);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.format("%s({\"status\":\"false\"})",callback);
	}*/
	/**
	 * Json版本
	 * */
	@RequestMapping(value="/category/{cid}", method=RequestMethod.DELETE)
	@ResponseBody
	public RestfulResult deleteCategoryJson(@PathVariable int cid){
		logger.info("通过HTTP DELETE方法删除分类");
		dbDategoryService.deleteCategory(cid);
		return new RestfulResult(true);
	}
	/**
	 * Jsonp版本没有这个方法，只要是Jsonp就是GET
	 * */
	/*
	@RequestMapping(value="/category/{cid}", method=RequestMethod.DELETE, params="callback", produces="application/javascript;charset=UTF-8")
	@ResponseBody
	public String deleteCategoryJsonp(@RequestBody String body, @PathVariable int cid, @RequestParam("callback") String callback){
		categoryService.deleteCategory(cid);
		StatusDetail statusDetail = new StatusDetail(true);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String res = objectMapper.writeValueAsString(statusDetail);
			return String.format("%s(%s)",callback,res);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.format("%s({\"status\":\"false\"})",callback);
	}	*/
}
