package com.ootd.weatherlook.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.ReportDao;
import com.ootd.weatherlook.model.Report;
import com.ootd.weatherlook.model.Search;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

	private final ReportDao reportDao;

	@Override
	public void removeDaily(int post_id) {
		System.out.println("ReportServiceImpl.removeDaily");
		reportDao.removeDaily(post_id);
	}

	@Override
	public void removeReview(int post_id) {
		System.out.println("ReportServiceImpl.removeReview");
		reportDao.removeReview(post_id);
	}

	@Override
	public void removeCommunity(int post_id) {
		System.out.println("ReportServiceImpl.removeCommunity");
		reportDao.removeCommunity(post_id);
	}
	
	@Override
	public void removeDailyReply(int re_id) {
		System.out.println("ReportServiceImpl.removeDailyReply");
		reportDao.removeDailyReply(re_id);
	}

	@Override
	public void removeReviewReply(int re_id) {
		System.out.println("ReportServiceImpl.removeReviewReply");
		reportDao.removeReviewReply(re_id);
	}

	@Override
	public void removeCommunityReply(int re_id) {
		System.out.println("ReportServiceImpl.removeCommunityReply");
		reportDao.removeCommunityReply(re_id);
	}

	@Override
	public List<Report> getReportList(Search search) {
		System.out.println("ReportServiceImpl.getReportList");
		return reportDao.getReportList(search);
	}

	@Override
	public int getReportCount(Search search) {
		System.out.println("ReportServiceImpl.getReportCount");
		return reportDao.getReportCount(search);
	}

	@Override
	public int getReportedReplyCount(Search search) {
		System.out.println("ReportServiceImpl.getReportedReplyCount");
		return reportDao.getReportedReplyCount(search);
	}

	@Override
	public List<Report> getReportedReplyList(Search search) {
		System.out.println("ReportServiceImpl.getReportedReplyList");
		return reportDao.getReportedReplyList(search);
	}
}
