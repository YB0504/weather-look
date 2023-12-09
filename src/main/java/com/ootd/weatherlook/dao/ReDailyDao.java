package com.ootd.weatherlook.dao;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.DailyReReportDTO;
import com.ootd.weatherlook.model.ReDaily;

@Repository
@RequiredArgsConstructor
public class ReDailyDao {
	private final SqlSession ress;

	public List<ReDaily> list(int post_id) {
		System.out.println("ReDailyDao.list");
		return ress.selectList("redailyns.rlist", post_id);
	}

	public void insert(ReDaily rb) {
		System.out.println("ReDailyDao.insert");
		ress.insert("redailyns.rinsert", rb);
	}

	public void delete(int re_id) {
		System.out.println("ReDailyDao.delete");
		ress.delete("redailyns.rdelete", re_id);
	}

	public void update(ReDaily rb) {
		System.out.println("ReDailyDao.update");
		ress.update("redailyns.rupdate", rb);
	}

	public void dailyReplyInsert(ReDaily rb) {
		System.out.println("ReDailyDao.dailyReplyInsert");
		ress.insert("redailyns.dailyReplyInsert", rb);
	}

	public void reReportInsert(DailyReReportDTO dailyReReport) {
		System.out.println("ReDailyDao.reReportInsert");
		ress.insert("redailyns.reReportInsert", dailyReReport);
	}
	
}
