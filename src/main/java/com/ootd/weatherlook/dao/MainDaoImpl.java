package com.ootd.weatherlook.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.MainBoard;
import com.ootd.weatherlook.model.Search;
import com.ootd.weatherlook.model.SearchResult;

@Repository
public class MainDaoImpl implements MainDao{
	
	@Autowired
	private SqlSession session;

	@Override
	public List<MainBoard> getMainList(Search search){
		// 트래킹 ->
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[1];
		System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
		// <- 트래킹
		return session.selectList("main.mainlist", search);
	}
	
	@Override
	public int getMainCount(Search search) {
		// TODO Auto-generated method stub
		// 트래킹 ->
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[1];
		System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
		// <- 트래킹
		return session.selectOne("main.maincount", search);
	}
	
	
	
	@Override
	public List<SearchResult> getSearchList(Search search) {

		// 트래킹 ->
				StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
				StackTraceElement caller = stackTrace[1];
				System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
				// <- 트래킹
		
		return session.selectList("main.searchlist",search);
	}
	
	@Override
	public int getSearchCount(Search search) {
		// 트래킹 ->
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[1];
		System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
		// <- 트래킹
		return session.selectOne("main.searchcount",search);
	}
	
@Override
public List<MainBoard> getWeatherList(Search search) {
	// 트래킹 ->
	StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
	StackTraceElement caller = stackTrace[1];
	System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
	// <- 트래킹
	return session.selectList("main.weather",search);
}
}
