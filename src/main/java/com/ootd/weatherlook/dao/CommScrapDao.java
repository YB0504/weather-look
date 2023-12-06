package com.ootd.weatherlook.dao;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.CommunityScrap;

@Repository
public class CommScrapDao {

	@Autowired
	private SqlSession cssession;

	public int commScrapInsert(CommunityScrap commscrap) {
		// TODO Auto-generated method stub
		return cssession.insert("commScrapInsert", commscrap);
	}

	public void commScrapDelete(int scrap_id) {
		// TODO Auto-generated method stub
		cssession.delete("commScrapDelete", scrap_id);
	}

	public CommunityScrap getCommScrap(CommunityScrap commscrap) {
		// TODO Auto-generated method stub
		return cssession.selectOne("getCommScrap", commscrap);
	}
	
	
}
