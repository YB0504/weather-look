package com.ootd.weatherlook.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.DailyReReportDTO;
import com.ootd.weatherlook.model.ReDaily;

@Repository
public class ReDailyDao {

	@Autowired
	private SqlSession ress;

	public List<ReDaily> list(int post_id) {
		return ress.selectList("rlist", post_id);
	}

	public void insert(ReDaily rb) {
		ress.insert("rinsert", rb);
	}

	public void delete(int re_id) {
		// TODO Auto-generated method stub
		ress.delete("rdelete", re_id);
	}

	public void update(ReDaily rb) {
		// TODO Auto-generated method stub
		ress.update("rupdate", rb);
	}

	public void dailyReplyInsert(ReDaily rb) {
		// TODO Auto-generated method stub
		ress.insert("dailyReplyInsert", rb);
	}

	public void reReportInsert(DailyReReportDTO dailyReReport) {
		// TODO Auto-generated method stub
		ress.insert("reReportInsert", dailyReReport);
	}
	
}
