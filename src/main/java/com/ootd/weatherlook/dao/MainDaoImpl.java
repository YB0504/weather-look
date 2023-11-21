package com.ootd.weatherlook.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.Main;

@Repository
public class MainDaoImpl implements MainDao{
	
	@Autowired
	private SqlSession session;

	@Override
	public List<Main> getRecentList(Map<String, Integer> rangeSet) throws Exception {
		// 트래킹 ->
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[1];
		System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
		// <- 트래킹
		return session.selectList("main.main_recentlist", rangeSet);
	}
	
	

}
