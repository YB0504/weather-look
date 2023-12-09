package com.ootd.weatherlook.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.ReDailyDao;
import com.ootd.weatherlook.model.DailyReReportDTO;
import com.ootd.weatherlook.model.ReDaily;

@Service
@RequiredArgsConstructor
public class ReDailyService {

	private final ReDailyDao rdd;

	public List<ReDaily> list(int post_id) {
		System.out.println("ReDailyService.list");
		return rdd.list(post_id);
	}

	public void insert(ReDaily rb) {
		System.out.println("ReDailyService.insert");
		rdd.insert(rb);
	}

	public void delete(int re_id) {
		System.out.println("ReDailyService.delete");
		rdd.delete(re_id);
	}

	public void update(ReDaily rb) {
		System.out.println("ReDailyService.update");
		rdd.update(rb);
	}

	public void dailyReplyInsert(ReDaily rb) {
		System.out.println("ReDailyService.dailyReplyInsert");
		rdd.dailyReplyInsert(rb);
	}

	public void reReportInsert(DailyReReportDTO dailyReReport) {
		System.out.println("ReDailyService.reReportInsert");
		rdd.reReportInsert(dailyReReport);
	}
}
