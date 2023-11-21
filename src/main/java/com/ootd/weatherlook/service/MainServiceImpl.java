package com.ootd.weatherlook.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.MainDao;
import com.ootd.weatherlook.model.Main;

@Service
public class MainServiceImpl implements MainService {
	
	@Autowired
	private MainDao mainDao;
	

	public int test() {
		return 1;
	}
	public String mainPage() {
		System.out.println("Service 통과");
		return "/";
	}
	@Override
	public List<Main> getRecentList(Map<String, Integer> rangeSet) throws Exception {
		// TODO Auto-generated method stub
		// 트래킹 ->
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[1];
		System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
		// <- 트래킹
		return mainDao.getRecentList(rangeSet);
	}
	
	
}
