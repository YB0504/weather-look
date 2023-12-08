package com.ootd.weatherlook.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.Report;
import com.ootd.weatherlook.model.Search;

@Repository
public class ReportDaoImpl implements ReportDao{

	@Autowired
	private SqlSession session;
	
	@Override
	public void removeDaily(int post_id) {
		session.delete("report.removedaily", post_id);
	}
	@Override
	public void removeReview(int post_id) {
		session.delete("report.removereview", post_id);
	}
	@Override
	public void removeCommunity(int post_id) {
		session.delete("report.removecommunity", post_id);
	}
	
	@Override
	public List<Report> getReportList(Search search) {
		// TODO Auto-generated method stub
		// 트래킹 ->
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[1];
		System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
		// <- 트래킹
		return session.selectList("report.reportlist", search);
	}

	@Override
	public int getReportCount(Search search) {
		// TODO Auto-generated method stub
		return session.selectOne("report.reportcount", search);
	}

	
}
