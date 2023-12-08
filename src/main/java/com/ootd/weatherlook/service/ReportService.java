package com.ootd.weatherlook.service;

import java.util.List;

import com.ootd.weatherlook.model.Report;
import com.ootd.weatherlook.model.Search;

public interface ReportService {

	public void removeDaily(int post_id);
	public void removeReview(int post_id);
	public void removeCommunity(int post_id);

	public void removeDailyReply(int re_id);
	public void removeReviewReply(int re_id);
	public void removeCommunityReply(int re_id);
	
	public List<Report> getReportList(Search search);
	public int getReportCount(Search search);
	int getReportedReplyCount(Search search);
	List<Report> getReportedReplyList(Search search);
	
}
