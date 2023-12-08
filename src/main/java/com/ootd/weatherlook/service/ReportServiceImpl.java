package com.ootd.weatherlook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.ReportDao;
import com.ootd.weatherlook.model.Report;
import com.ootd.weatherlook.model.Search;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportDao reportDao;

	@Override
	public void removeDaily(int post_id) {
		reportDao.removeDaily(post_id);
	}

	@Override
	public void removeReview(int post_id) {
		reportDao.removeReview(post_id);
	}

	@Override
	public void removeCommunity(int post_id) {
		reportDao.removeCommunity(post_id);
	}
	
	@Override
	public void removeDailyReply(int re_id) {
		reportDao.removeDailyReply(re_id);

	}
	@Override
	public void removeReviewReply(int re_id) {
		reportDao.removeReviewReply(re_id);

	}
	@Override
	public void removeCommunityReply(int re_id) {
		reportDao.removeCommunityReply(re_id);

	}
	
	

	@Override
	public List<Report> getReportList(Search search) {
		// TODO Auto-generated method stub
		// 트래킹 ->
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[1];
		System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
		// <- 트래킹
		return reportDao.getReportList(search);
	}

	@Override
	public int getReportCount(Search search) {
		// TODO Auto-generated method stub
		return reportDao.getReportCount(search);

	}

	@Override
	public int getReportedReplyCount(Search search) {
		return reportDao.getReportedReplyCount(search);
	}

	@Override
	public List<Report> getReportedReplyList(Search search) {
		return reportDao.getReportedReplyList(search);
	}



}
