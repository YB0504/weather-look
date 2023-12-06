package com.ootd.weatherlook.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.Dailylike;

@Repository
public class DailylikeDao {

	@Autowired
	private SqlSession session;
	
	
	
	
	public int getDailyLike(Dailylike dl) {
		return session.selectOne("selectDailyLike", dl);
	}

	public int insert(Dailylike dl) {
		return session.insert("insertLike", dl);
	}

	public int update(Dailylike dl) {
		return session.update("updateLike", dl);
	}

	public List<Dailylike> getList(Map map) {
		return session.selectList("dailylikens.listLike", map);
	}

}
