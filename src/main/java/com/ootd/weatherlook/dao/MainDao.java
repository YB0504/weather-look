package com.ootd.weatherlook.dao;

import java.util.List;
import java.util.Map;

import com.ootd.weatherlook.model.Main;

public interface MainDao {

	
	public List<Main> getRecentList(Map<String, Integer> rangeSet)throws Exception;
	
}
