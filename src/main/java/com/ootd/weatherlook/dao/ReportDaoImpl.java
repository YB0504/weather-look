package com.ootd.weatherlook.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReportDaoImpl implements ReportDao{

	@Autowired
	private SqlSession session;
	
	@Override
	public void removeDaily(int post_id) {
		// TODO Auto-generated method stub
		
		session.delete("report.removedaily", post_id);
	}
	
}
