package com.ootd.weatherlook.service;

import java.util.List;
import java.util.Map;

import com.ootd.weatherlook.model.Main;

public interface MainService {
	
	public int test();
	public String mainPage();
	public List<Main> getRecentList(Map<String, Integer> rangeSet)throws Exception;

}
