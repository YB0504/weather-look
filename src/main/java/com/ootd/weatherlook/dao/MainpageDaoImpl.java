package com.ootd.weatherlook.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.Daily;

@Repository
public class MainpageDaoImpl implements MainpageDao{
	
	@Autowired
	private SqlSession session;
	
	@Override
	public int test() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Daily> getDailyList(Daily daily) throws Exception {
		// TODO Auto-generated method stub
		
		List<Daily> dailylist = session.selectList("daily.daily_list",daily);
		
		return dailylist;
	}
	
	
	

}
