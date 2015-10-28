package com.vimisky.dms.dao;

import java.util.List;

import com.vimisky.dms.entity.GenreType;

public interface GenreTypeDao {

	public GenreType getGenreTypeById(int id);
	public List<GenreType> getAllGenreTypes();
	
}
