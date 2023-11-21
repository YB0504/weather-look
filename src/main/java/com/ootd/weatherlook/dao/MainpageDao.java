package com.ootd.weatherlook.dao;

import java.util.List;

import com.ootd.weatherlook.model.Daily;

public interface MainpageDao {

	public int test() throws Exception ;
	
	public List<Daily> getDailyList(Daily daily) throws Exception; 
	
}
