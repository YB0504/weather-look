package com.ootd.weatherlook.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.ootd.weatherlook.model.CommunityLike;

@Repository
@RequiredArgsConstructor
public class CommLikeDao {

	private final SqlSession lsession;

	public int commLikeInsert(CommunityLike commlike) {
		System.out.println("CommLikeDao.commLikeInsert");
		return lsession.insert("comm.commLikeInsert",commlike);
	}

	public CommunityLike getCommLike(CommunityLike commlike) {
		System.out.println("CommLikeDao.getCommLike");
		return lsession.selectOne("comm.getCommLike", commlike);
	}

	public void commLikeDelete(int post_id) {
		System.out.println("CommLikeDao.commLikeDelete");
		lsession.delete("comm.commLikeDelete",post_id);
	}
}
