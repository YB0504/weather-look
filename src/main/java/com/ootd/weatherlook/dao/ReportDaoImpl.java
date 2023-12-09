package com.ootd.weatherlook.dao;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.Report;
import com.ootd.weatherlook.model.Search;

@Repository
@RequiredArgsConstructor
public class ReportDaoImpl implements ReportDao{

	private final SqlSession session;
	
	@Override
	public void removeDaily(int post_id) {
		System.out.println("ReportDaoImpl.removeDaily");
		session.delete("report.removedaily", post_id);
	}
	@Override
	public void removeReview(int post_id) {
		System.out.println("ReportDaoImpl.removeReview");
		session.delete("report.removereview", post_id);
	}
	@Override
	public void removeCommunity(int post_id) {
		System.out.println("ReportDaoImpl.removeCommunity");
		session.delete("report.removecommunity", post_id);
	}
	@Override
	public void removeDailyReply(int re_id) {
		System.out.println("ReportDaoImpl.removeDailyReply");
		session.delete("report.removedailyreply", re_id);
	}
	@Override
	public void removeReviewReply(int re_id) {
		System.out.println("ReportDaoImpl.removeReviewReply");
		session.delete("report.removereviewreply", re_id);
	}
	@Override
	public void removeCommunityReply(int re_id) {
		System.out.println("ReportDaoImpl.removeCommunityReply");
		session.delete("report.removecommunityreply", re_id);
	}
	
	@Override
	public List<Report> getReportList(Search search) {
		System.out.println("ReportDaoImpl.getReportList");
		return session.selectList("report.reportlist", search);
	}

	@Override
	public int getReportCount(Search search) {
		System.out.println("ReportDaoImpl.getReportCount");
		return session.selectOne("report.reportcount", search);
	}

	@Override
	public List<Report> getReportedReplyList(Search search) {
		System.out.println("ReportDaoImpl.getReportedReplyList");
		return session.selectList("report.reportedreplylist", search);
	}

	@Override
	public int getReportedReplyCount(Search search) {
		System.out.println("ReportDaoImpl.getReportedReplyCount");
		return session.selectOne("report.reportedreplycount", search);
	}
}
