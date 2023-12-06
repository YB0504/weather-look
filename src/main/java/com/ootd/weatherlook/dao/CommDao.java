package com.ootd.weatherlook.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.Community;

@Repository
public class CommDao {

	@Autowired
	private SqlSession session;
	
	public int commInsert(Community comm) {
		System.out.println(comm.getNick());
		return session.insert("commInsert", comm);
	}

	public int getCommCount() {
		// TODO Auto-generated method stub
		return session.selectOne("getCommCount");
	}

	public List<Community> getCommList(int page) {
		// TODO Auto-generated method stub
		return session.selectList("getCommList", page);
	}

	public void commUpdateCount(int post_id) {
		// TODO Auto-generated method stub
		session.update("commUpdateCount", post_id);
	}

	public Community getCommunity(int post_id) {
		// TODO Auto-generated method stub
		return session.selectOne("getCommunity", post_id);
	}

	public int commUpdate(Community comm) {
		// TODO Auto-generated method stub
		return session.update("commUpdate", comm);
	}

	public int commDelete(int post_id) {
		// TODO Auto-generated method stub
		return session.delete("commDelete", post_id);
	}

	

	
}
