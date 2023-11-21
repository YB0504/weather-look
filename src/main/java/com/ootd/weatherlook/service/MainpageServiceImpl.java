package com.ootd.weatherlook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.MainpageDao;
import com.ootd.weatherlook.model.Daily;

@Service
public class MainpageServiceImpl implements MainpageService {
	
	@Autowired
	private MainpageDao mainpageDao;
	

	public int test() {
		return 1;
	}
	public String mainPage() {
		System.out.println("Service 통과");
		return "/";
	}
	@Override
	public List<Daily> getDailyList(Daily daily) throws Exception{
		
		return mainpageDao.getDailyList(daily);
	}
	
	
	
}
