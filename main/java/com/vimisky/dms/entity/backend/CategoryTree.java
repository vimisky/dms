package com.vimisky.dms.entity.backend;

import java.util.List;

public class CategoryTree {
	
//	public Map<S,T> convert(Converter<S, T> converter){
//		
//		return null;
//	}
	public static class State {
		private boolean opened;
		private boolean selected;
		private boolean disabled;
		/**
		 * @return the opened
		 */
		public boolean isOpened() {
			return opened;
		}
		/**
		 * @param opened the opened to set
		 */
		public void setOpened(boolean opened) {
			this.opened = opened;
		}
		/**
		 * @return the selected
		 */
		public boolean isSelected() {
			return selected;
		}
		/**
		 * @param selected the selected to set
		 */
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
		/**
		 * @return the disabled
		 */
		public boolean isDisabled() {
			return disabled;
		}
		/**
		 * @param disabled the disabled to set
		 */
		public void setDisabled(boolean disabled) {
			this.disabled = disabled;
		}
		
	}
	private String id;
	private String text;
//	private String icon;
	private State state;
//	private String parent;
	private String type;
	private String li_attr;
	private String a_attr;
	private List<CategoryTree> children;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the li_attr
	 */
	public String getLi_attr() {
		return li_attr;
	}
	/**
	 * @param li_attr the li_attr to set
	 */
	public void setLi_attr(String li_attr) {
		this.li_attr = li_attr;
	}
	/**
	 * @return the a_attr
	 */
	public String getA_attr() {
		return a_attr;
	}
	/**
	 * @param a_attr the a_attr to set
	 */
	public void setA_attr(String a_attr) {
		this.a_attr = a_attr;
	}
	/**
	 * @return the children
	 */
	public List<CategoryTree> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<CategoryTree> children) {
		this.children = children;
	}
	
}
