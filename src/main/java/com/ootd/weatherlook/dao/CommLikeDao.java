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

	public int insert(CommunityLike commlike) {
		// TODO Auto-generated method stub
		return lsession.insert("linsert",commlike);
	}

	public CommunityLike getLike(CommunityLike commlike) {
		// TODO Auto-generated method stub
		return lsession.selectOne("getLike", commlike);
	}

	public void delete(int post_id) {
		// TODO Auto-generated method stub
		lsession.delete("likedelete",post_id);
	}





}
