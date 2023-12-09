package com.ootd.weatherlook.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.CommunityScrap;

@Repository
@RequiredArgsConstructor
public class CommScrapDao {

	private final SqlSession cssession;

	public int commScrapInsert(CommunityScrap commscrap) {
		System.out.println("CommScrapDao.commScrapInsert");
		return cssession.insert("comm.commScrapInsert", commscrap);
	}

	public void commScrapDelete(int scrap_id) {
		System.out.println("CommScrapDao.commScrapDelete");
		cssession.delete("comm.commScrapDelete", scrap_id);
	}

	public CommunityScrap getCommScrap(CommunityScrap commscrap) {
		System.out.println("CommScrapDao.getCommScrap");
		return cssession.selectOne("comm.getCommScrap", commscrap);
	}
}
