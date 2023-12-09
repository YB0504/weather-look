package com.ootd.weatherlook.dao;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.CommReportDTO;
import com.ootd.weatherlook.model.Community;

@Repository
@RequiredArgsConstructor
public class CommDao {

	private final SqlSession session;
	
	public int commInsert(Community comm) {
		System.out.println("CommDao.commInsert");
		return session.insert("comm.commInsert", comm);
	}

	public int getCommCount() {
		System.out.println("CommDao.getCommCount");
		return session.selectOne("comm.getCommCount");
	}

	public List<Community> getCommList(int page) {
		System.out.println("CommDao.getCommList");
		return session.selectList("comm.getCommList", page);
	}

	public void commUpdateCount(int post_id) {
		System.out.println("CommDao.commUpdateCount");
		session.update("comm.commUpdateCount", post_id);
	}

	public Community getCommunity(int post_id) {
		System.out.println("CommDao.getCommunity");
		return session.selectOne("comm.getCommunity", post_id);
	}

	public int commUpdate(Community comm) {
		System.out.println("CommDao.commUpdate");
		return session.update("comm.commUpdate", comm);
	}

	public int commDelete(int post_id) {
		System.out.println("CommDao.commDelete");
		return session.delete("comm.commDelete", post_id);
	}

	public void reportInsert(CommReportDTO communityReport) {
		System.out.println("CommDao.reportInsert");
		session.insert("comm.reportInsert", communityReport);
	}
}
