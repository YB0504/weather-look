package com.ootd.weatherlook.service;

import java.util.List;
import com.ootd.weatherlook.model.Report;
import com.ootd.weatherlook.model.Search;

public interface ReportService {
	void removeDaily(int post_id);
	void removeReview(int post_id);
	void removeCommunity(int post_id);
	void removeDailyReply(int re_id);
	void removeReviewReply(int re_id);
	void removeCommunityReply(int re_id);
	List<Report> getReportList(Search search);
	int getReportCount(Search search);
	int getReportedReplyCount(Search search);
	List<Report> getReportedReplyList(Search search);
}
