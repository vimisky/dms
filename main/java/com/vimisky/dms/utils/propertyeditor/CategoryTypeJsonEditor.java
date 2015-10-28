package com.vimisky.dms.utils.propertyeditor;

import java.beans.PropertyEditorSupport;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vimisky.dms.entity.CategoryType;

public class CategoryTypeJsonEditor extends PropertyEditorSupport {

	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub
		super.setValue(value);
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return super.getValue();
	}

	@Override
	public String getAsText() {
		// TODO Auto-generated method stub
//		return super.getAsText();
		ObjectMapper om = new ObjectMapper();
		try {
			String text = om.writeValueAsString(getValue());
			return text;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		// TODO Auto-generated method stub
//		super.setAsText(text);
		ObjectMapper om = new ObjectMapper();
		try {
			CategoryType ct = (CategoryType)om.readValue(text, CategoryType.class);
			setValue(ct);
		} catch (JsonParseException e) {
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


}
