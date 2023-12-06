package com.ootd.weatherlook.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.CommunityLike;

@Repository
public class CommLikeDao {

	@Autowired
	private SqlSession lsession;

	public int commLikeInsert(CommunityLike commlike) {
		// TODO Auto-generated method stub
		return lsession.insert("commLikeInsert",commlike);
	}

	public CommunityLike getCommLike(CommunityLike commlike) {
		// TODO Auto-generated method stub
		return lsession.selectOne("getCommLike", commlike);
	}

	public void commLikeDelete(int post_id) {
		// TODO Auto-generated method stub
		lsession.delete("commLikeDelete",post_id);
	}





}
