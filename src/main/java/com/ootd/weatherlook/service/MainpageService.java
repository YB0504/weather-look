package com.ootd.weatherlook.service;

import java.util.List;

import com.ootd.weatherlook.model.Daily;

public interface MainpageService {
	
	public int test();
	public String mainPage();
	public List<Daily> getDailyList(Daily daily) throws Exception;

}
