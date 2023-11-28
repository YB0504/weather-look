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
	
	public int insert(Community comm) {
		System.out.println(comm.getNick());
		return session.insert("insert", comm);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return session.selectOne("count");
	}

	public List<Community> getCommList(int page) {
		// TODO Auto-generated method stub
		return session.selectList("list", page);
	}

	public void updatecount(int post_id) {
		// TODO Auto-generated method stub
		session.update("hit", post_id);
	}

	public Community getCommunity(int post_id) {
		// TODO Auto-generated method stub
		return session.selectOne("content", post_id);
	}

	public int update(Community comm) {
		// TODO Auto-generated method stub
		return session.update("update", comm);
	}

	public int delete(int post_id) {
		// TODO Auto-generated method stub
		return session.delete("delete", post_id);
	}

	

	
}
