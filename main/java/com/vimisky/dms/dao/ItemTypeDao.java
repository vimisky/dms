package com.vimisky.dms.dao;

import java.util.List;

import com.vimisky.dms.entity.ItemType;

public interface ItemTypeDao {

	public ItemType getItemTypeById(int id);
	public List<ItemType> getAllItemTypes();
	
}
