package com.ootd.weatherlook.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.Daily;
import com.ootd.weatherlook.model.Search;

@Repository
public class DailyDao {

	@Autowired
	private SqlSession session;

	public int insert(Daily daily) {
		System.out.println(daily.getNick());
		return session.insert("insert", daily);
	}

	public int getCount(Search search) {
		// TODO Auto-generated method stub
		return session.selectOne("dailyns.count", search);

	
	}

	public List<Daily> getDailyList(Search search) {
		// TODO Auto-generated method stub
		return session.selectList("dailyns.list", search);
	}

	public void updatecount(int post_id) {
		// TODO Auto-generated method stub
		session.update("hit", post_id);
	}

	public Daily getDaily(int post_id) {
		// TODO Auto-generated method stub
		return session.selectOne("content", post_id);
	}

	public int update(Daily daily) {
		// TODO Auto-generated method stub
		return session.update("update", daily);
	}

	public int delete(int post_id) {
		// TODO Auto-generated method stub
		return session.delete("delete", post_id);
	}
	
	
	
	
}