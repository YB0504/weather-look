package com.ootd.weatherlook.dao;

import java.util.List;

import com.ootd.weatherlook.model.Report;
import com.ootd.weatherlook.model.Search;

public interface ReportDao {

	public void removeDaily(int post_id); 
	public void removeReview(int post_id); 
	public void removeCommunity(int post_id); 
	public List<Report> getReportList(Search search);
	public int getReportCount(Search search);
	public int getReportedReplyCount(Search search);
	public List<Report> getReportedReplyList(Search search);
	public void removeDailyReply(int re_id);
	public void removeReviewReply(int re_id);
	public void removeCommunityReply(int re_id);
}
