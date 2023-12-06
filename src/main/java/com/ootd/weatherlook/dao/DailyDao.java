package com.ootd.weatherlook.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.Daily;
import com.ootd.weatherlook.model.Dailylike;

@Repository
public class DailyDao {

	@Autowired
	private SqlSession session;

	public int insert(Daily daily) {
		System.out.println(daily.getNick());
		return session.insert("insert", daily);
	}

	public int getCount() {
		return session.selectOne("count");
	}

	public List<Daily> getDailyList(int page) {
		return session.selectList("list", page);
	}

	public Daily getDaily(int post_id) {
		return session.selectOne("content", post_id);
	}

	public int update(Daily daily) {
		return session.update("update", daily);
	}

	public int delete(int post_id) {
		return session.delete("delete", post_id);
	}

	public int likecount(Dailylike dailylike) {
		return session.selectOne("likecheck", dailylike);
	}

	public void updatecount(int post_id) {
		session.update("hit", post_id);
	}

}
