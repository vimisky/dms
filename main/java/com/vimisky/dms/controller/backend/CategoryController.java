package com.vimisky.dms.controller.backend;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;
import javax.validation.Valid;
import javax.validation.Validation;

import org.apache.log4j.Logger;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vimisky.dms.controller.validation.NewEntity;
import com.vimisky.dms.controller.validation.PatchEntity;
import com.vimisky.dms.controller.validation.UpdateEntity;
import com.vimisky.dms.entity.Category;
import com.vimisky.dms.entity.backend.CategoryBrief;
import com.vimisky.dms.entity.backend.CategoryDetail;
import com.vimisky.dms.entity.backend.CategoryForm;
import com.vimisky.dms.entity.backend.CategoryTree;
import com.vimisky.dms.entity.backend.CategoryTypeDetail;
import com.vimisky.dms.entity.backend.OperationServiceResult;
import com.vimisky.dms.entity.backend.RestfulResult;
import com.vimisky.dms.service.backend.CategoryService;

@Controller(value="backendCategoryController")
@RequestMapping(value="/backend/restful")
public class CategoryController {

	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private CategoryService backendCategoryService;
	@Autowired
	private javax.validation.Validator validator;
	public CategoryService getBackendCategoryService() {
		return backendCategoryService;
	}
	public void setBackendCategoryService(CategoryService backendCategoryService) {
		this.backendCategoryService = backendCategoryService;
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
	public List<CategoryTypeDetail> getCategoryTypesJson(){
		List<CategoryTypeDetail> categoryTypes = this.backendCategoryService.getCategoryTypes();
		logger.info("请求分类类型列表");
		return categoryTypes;
	}
	
	/**
	 * json版本，获取某个分类类型的详细信息
	 * */
	@RequestMapping(value="/categorytype/{id}",method=RequestMethod.GET)
	@ResponseBody
	public CategoryTypeDetail getCategoryTypeJson(@PathVariable int id){
		return this.backendCategoryService.getCategoryTypeById(id);
	}
	/**
	 * json版本，插入某个分类类型
	 * */
	@RequestMapping(value="/categorytype", method=RequestMethod.POST)
	@ResponseBody
	public RestfulResult insertCategoryType(
			@Validated(value={NewEntity.class}) @ModelAttribute CategoryTypeDetail categoryTypeDetail,
			BindingResult bResult,
			HttpServletRequest webRequest){
		logger.debug("接收到的分类类型数据为："+categoryTypeDetail.toString());
		//在客户端的Request头中，增加Content-Type:x-www-form-urlencoded;charset=utf8;这里就能获取到，否则获取不到
		logger.debug(webRequest.getCharacterEncoding());
		try {
			webRequest.setCharacterEncoding("");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//补偿验证，避免用户上传ID
		categoryTypeDetail.setId(0);
		if(bResult.hasErrors()){
			StringBuffer errorString = new StringBuffer();
			logger.error("分类类型数据新增接口接收表单数据出现错误：");
//			对前端而言，不需要显示全部错误
			for (ObjectError objectError : bResult.getAllErrors()) {
				logger.error(objectError.getObjectName()+"赋值验证出错："+objectError.getDefaultMessage()+"，错误代码为:"+objectError.getCode());
			}
			for (FieldError fieldError : bResult.getFieldErrors()){
				logger.error(fieldError.getObjectName()+"的"+fieldError.getField()+"字段赋值验证出错："+fieldError.getDefaultMessage()+"，错误代码为:"+fieldError.getCode());				
//				errorString.append(fieldError.getField());
//				errorString.append(fieldError.getRejectedValue().toString());
				errorString.append(fieldError.getDefaultMessage()+"\r\n");
//				errorString.append(fieldError.toString());
			}
			return new RestfulResult(false, "表单提交数据错误："+errorString);
		}
		OperationServiceResult osr = this.backendCategoryService.insertCategoryType(categoryTypeDetail);
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
	public RestfulResult updateCategoryType(
			@PathVariable int ctid, 
			@Validated(value={UpdateEntity.class}) @ModelAttribute CategoryTypeDetail categoryTypeDetail, 
			BindingResult bResult){
		logger.info("通过HTTP PUT方法对分类"+categoryTypeDetail.getName()+"进行全部升级");
		if(bResult.hasErrors()){
			StringBuffer errorString = new StringBuffer();
			logger.error("分类类型数据更新接口接收表单数据出现错误：");
//			对前端而言，不需要显示全部错误
			for (ObjectError objectError : bResult.getAllErrors()) {
				logger.error(objectError.getObjectName()+"赋值验证出错："+objectError.getDefaultMessage()+"，错误代码为:"+objectError.getCode());
			}
			for (FieldError fieldError : bResult.getFieldErrors()){
				logger.error(fieldError.getObjectName()+"的"+fieldError.getField()+"字段赋值验证出错："+fieldError.getDefaultMessage()+"，错误代码为:"+fieldError.getCode());				
				errorString.append(fieldError.getDefaultMessage()+"\r\n");
			}
			return new RestfulResult(false, "表单提交数据错误："+errorString);
		}		
		//该判断补充验证框架中的值验证
		if(categoryTypeDetail.getId() != ctid){
			logger.warn("URL资源ID与表单ID数据不一致");
			return new RestfulResult(false, "URL资源ID与表单ID数据不一致");			
		}
		OperationServiceResult osr = this.backendCategoryService.updateCategoryType(categoryTypeDetail);
		return new RestfulResult(osr);
	}
	/**
	 * json版本，部分更新某个分类类型
	 * */
	@RequestMapping(value="/categorytype/{id}", method=RequestMethod.PATCH)
	@ResponseBody
	public RestfulResult updateCategoryTypePart(
			@PathVariable int id, 
			@Validated(value={PatchEntity.class}) @ModelAttribute CategoryTypeDetail categoryTypeDetail,
			BindingResult bResult){
		logger.info("通过HTTP PATCH方法对分类类型进行部分升级");
		logger.debug("原始CategoryType为："+categoryTypeDetail.toString());
		if(bResult.hasErrors()){
			StringBuffer errorString = new StringBuffer();
			logger.error("分类类型部分数据更新接口接收表单数据出现错误：");
//			对前端而言，不需要显示全部错误
			for (ObjectError objectError : bResult.getAllErrors()) {
				logger.error(objectError.getObjectName()+"赋值验证出错："+objectError.getDefaultMessage()+"，错误代码为:"+objectError.getCode());
			}
			for (FieldError fieldError : bResult.getFieldErrors()){
				logger.error(fieldError.getObjectName()+"的"+fieldError.getField()+"字段赋值验证出错："+fieldError.getDefaultMessage()+"，错误代码为:"+fieldError.getCode());				
				errorString.append(fieldError.getDefaultMessage()+"\r\n");
			}
			return new RestfulResult(false, "表单提交数据错误："+errorString);
		}
		//与完全更新不同，表单中无需提供ID，使用URL中的ID即可
		if(categoryTypeDetail.getId() == 0)
			categoryTypeDetail.setId(id);
		OperationServiceResult osr = backendCategoryService.updateCategoryTypePart(categoryTypeDetail);
		return new RestfulResult(osr);
	}
	/**
	 * json版本，更新分类类型单个属性字段
	 * */
	@RequestMapping(value="/categorytype/updatefield", method=RequestMethod.POST, params={"name","value","pk"})
	@ResponseBody
	public RestfulResult updateCategoryTypeField(
			@RequestParam(value="name", required = true) String name,
			@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "pk", required = true) int pk,
			WebRequest webRequest){
		//验证
		try {
			Class<CategoryTypeDetail> categoryTypeDetailClass =(Class<CategoryTypeDetail>) Class.forName("com.vimisky.dms.entity.backend.CategoryTypeDetail");
			CategoryTypeDetail categoryTypeDetail = categoryTypeDetailClass.newInstance();
			Field field = categoryTypeDetailClass.getDeclaredField(name);
			field.setAccessible(true);
			field.set(categoryTypeDetail, value);
			categoryTypeDetail.setId(pk);
			
/*
			javax.validation.ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
			javax.validation.Validator jValidator = validatorFactory.getValidator();
			*/
			Set<javax.validation.ConstraintViolation<CategoryTypeDetail>> constraintViolations = validator.validate(categoryTypeDetail, PatchEntity.class);
			if(constraintViolations.size()>0) {
				Iterator<javax.validation.ConstraintViolation<CategoryTypeDetail>> iterator = constraintViolations.iterator();
				StringBuffer errorString = new StringBuffer();
				while(iterator.hasNext()){
					javax.validation.ConstraintViolation<CategoryTypeDetail> constraintViolation = iterator.next();
					logger.warn(constraintViolation.getMessage());
					errorString.append(constraintViolation.getMessage());
				}
				return new RestfulResult(false, "表单提交数据错误："+errorString);
			}
			
			
/*
			DataBinder dBinder = new DataBinder(categoryTypeDetail);
			dBinder.addValidators(validator);
			dBinder.validate();
			BindingResult bResult = dBinder.getBindingResult();
			
			if(bResult.hasErrors()){
				StringBuffer errorString = new StringBuffer();
				logger.error("分类类型部分数据更新接口接收表单数据出现错误：");
				for (ObjectError objectError : bResult.getAllErrors()) {
					logger.error(objectError.getObjectName()+"赋值验证出错："+objectError.getDefaultMessage()+"，错误代码为:"+objectError.getCode());
				}
				for (FieldError fieldError : bResult.getFieldErrors()){
					logger.error(fieldError.getObjectName()+"的"+fieldError.getField()+"字段赋值验证出错："+fieldError.getDefaultMessage()+"，错误代码为:"+fieldError.getCode());				
					errorString.append(fieldError.getDefaultMessage()+"\r\n");
				}
				return new RestfulResult(false, "表单提交数据错误："+errorString);
			}
			//Validator.validate(obj,errors)可以做到,需要拿到validator对象
			//dBinder.validate()可以做到，但是弄不清楚pvs和obj的关系，已经解释通了。
			//JSR-303在Entity上注解的那些，怎么引进来？
//			validatorxxx.validate(arg0, arg1)
 */
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new RestfulResult(false,"服务器后台出错");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new RestfulResult(false,"服务器后台出错");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new RestfulResult(false,"服务器后台出错");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new RestfulResult(false,"服务器后台出错");
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new RestfulResult(false,"服务器后台出错");
		}
		
		OperationServiceResult osr = backendCategoryService.updateCategoryTypePart(pk, name, value);
		return new RestfulResult(osr);
		
	}
	/**
	 * json版本，删除某个分类类型
	 * */
	@RequestMapping(value="/categorytype/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public RestfulResult deleteCategoryType(@PathVariable int id){
		OperationServiceResult osr = this.backendCategoryService.deleteCategoryTypeById(id);
		return new RestfulResult(osr);
	}
	/**
	 * json 版本，获取分类Category列表信息，适用于datatable
	 * @param id 父分类ID，可选参数，默认值为0
	 * @return List<CategoryBrief> 分类列表，由Spring MVC自动转换为Json格式。
	 * */
	@RequestMapping(value="/category", method=RequestMethod.GET)
	@ResponseBody
	public List<CategoryBrief> getCategoriesJson(
			@RequestParam(required=false,value="id",defaultValue="0" ) int id){
	
		List<CategoryBrief> categories;// = backendCategoryService.getCategoryBriefList(1);
		if(id == 0)
			categories = backendCategoryService.getCategoryBriefList(1);
		else
			categories = backendCategoryService.getCategoryBriefList(id);
		return categories;
	}

	/**
	 * json 版本，获取分类Category列表信息，适用于jstree
	 * @param id 父分类ID，可选参数，默认值为0
	 * @return List<CategoryTree> 分类列表，由Spring MVC自动转换为Json格式。
	 * */
	@RequestMapping(value="/categorytree", method=RequestMethod.GET)
	@ResponseBody
	public List<CategoryTree> getCategoryTree(
			@RequestParam(required=false,value="id",defaultValue="0" ) int id){
		List<CategoryTree> categories;
		
		if(id == 0)
			categories = backendCategoryService.getCategoriesOfTree(1);
		else
			categories = backendCategoryService.getCategoriesOfTree(id);
		
		return categories;
	}
	/**
	 * jsonp 版本，获取分类Category列表信息。注：jsonp仅适用于GET Method
	 * @param callback jsonp回调方法，必填参数
	 * @param id 父分类ID，可选参数，默认值为0
	 * @return String 分类列表jsonp格式字符串，由自定义生成。
	 * */
	@RequestMapping(value="/category", method=RequestMethod.GET, params="callback", produces="application/javascript;charset=UTF-8")
	@ResponseBody
	public String getCategoriesJsonp(
			@RequestParam(value="callback", required=true) String callback,
			@RequestParam(required=false,value="id",defaultValue="0" ) int id){
		
		List<CategoryBrief> categories;// = backendCategoryService.getCategoryBriefList(1);
		if(id == 0)
			categories = backendCategoryService.getCategoryBriefList(1);
		else
			categories = backendCategoryService.getCategoryBriefList(id);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String res = objectMapper.writeValueAsString(categories);
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
	 * json 版本,获取分类Category详细信息
	 * @param cid 分类ID，在URL路径中获取的参数
	 * @return CategoryDetail 分类详情，由Spring MVC转换为Json格式。
	 * */
	@RequestMapping(value="/category/{cid}" , method=RequestMethod.GET)
	@ResponseBody
	public CategoryDetail getCategoryDetailJson(@PathVariable int cid,HttpServletResponse response){
		CategoryDetail categoryDetail = backendCategoryService.getCategoryDetail(cid);
		return categoryDetail ;
	}
	/**
	 * jsonp 版本，获取分类Category详细信息。注：jsonp仅适用于GET Method
	 * @param cid 分类ID，在URL路径中获取的参数
	 * @param callback jsonp回调方法，必填参数
	 * @return String 分类详情Json字符串，由自定义生成
	 * */
	@RequestMapping(value="/category/{cid}", method=RequestMethod.GET, params="callback", produces="application/javascript;charset=UTF-8")
	@ResponseBody
	public String getCategoryDetailJsonp(@PathVariable int cid, @RequestParam(value="callback", required=true) String callback){
		CategoryDetail categoryDetail = backendCategoryService.getCategoryDetail(cid);
		if(categoryDetail != null){
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				String res = objectMapper.writeValueAsString(categoryDetail);
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
		}
		return null;
	}	
	/**
	 * Json版本，新增分类Category
	 * @param category 分类，必填
	 * @return RestfulResult 接口响应结果，true or false
	 * */
	@RequestMapping(value="/category", method=RequestMethod.POST)
	@ResponseBody
	public RestfulResult insertCategoryJson(@ModelAttribute Category category){
		logger.info("通过HTTP POST方法新增分类"+category.getName());
		OperationServiceResult osr = backendCategoryService.insertCategory(category);
		return new RestfulResult(osr);
	}
	/**
	 * Json版本,更新分类Category；为支持CORS而新增的接口
	 * */
	@RequestMapping(value="/category/{cid}", method=RequestMethod.OPTIONS)
	@ResponseBody
	public RestfulResult updateCategoryOPTIONS(@PathVariable int cid, @ModelAttribute Category category, HttpServletResponse response){
		logger.info("响应HTTP OPTIONS Method");
		return new RestfulResult(true);
	}
	/**
	 * Json版本,完全更新分类Category
	 * @param cid 分类ID，从URL参数中获取
	 * @param category 分类，必填
	 * @return RestfulResult 接口响应结果，true or false
	 * */
	@RequestMapping(value="/category/{cid}", method=RequestMethod.PUT)
	@ResponseBody
	public RestfulResult updateCategoryJson(@PathVariable int cid, @ModelAttribute Category category){
		logger.info("通过HTTP PUT方法对分类"+category.getName()+"进行全部升级");

		if(category.getId() == 0)
			category.setId(cid);
		OperationServiceResult osr = backendCategoryService.updateCategory(category);
		return new RestfulResult(osr);
	}

	/**
	 * Json版本,更新分类Category
	 * */
	@RequestMapping(value="/category/{cid}", method=RequestMethod.PATCH)
	@ResponseBody
	public RestfulResult updateCategoryPart(
			@PathVariable int cid,
			WebRequest request){
		logger.info("通过HTTP PATCH方法对分类进行部分升级");
		OperationServiceResult osr = backendCategoryService.patchCategory(cid, request.getParameterMap());
		return new RestfulResult(osr);
	}
	/**
	 * Json版本,更新/删除分类Category
	 * */
	@RequestMapping(value="/category/{cid}", method=RequestMethod.POST)
	@ResponseBody
	public RestfulResult postUpdateCategory(
			WebRequest request,
			@PathVariable int cid,
			@RequestParam(value="method", required=true) String method){
		logger.info("通过HTTP POST方法对分类进行删除或者升级");
		OperationServiceResult osr = null;
		if(method.toLowerCase().equals("patch")){
			osr = backendCategoryService.patchCategory(cid,request.getParameterMap());
		}else if(method.toLowerCase().equals("delete")){
			osr = backendCategoryService.deleteCategory(cid);
		}else{
			return new RestfulResult(false,"参数错误");
		}
		 
		return new RestfulResult(osr);
	}

	/**
	 * Json版本
	 * */
	@RequestMapping(value="/category/{cid}", method=RequestMethod.DELETE)
	@ResponseBody
	public RestfulResult deleteCategoryJson(@PathVariable int cid){
		logger.info("通过HTTP DELETE方法删除分类");
		backendCategoryService.deleteCategory(cid);
		return new RestfulResult(true);
	}
}
