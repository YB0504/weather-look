package com.ootd.weatherlook.dao;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.controller.ReReview;
import com.ootd.weatherlook.model.ReReviewDTO;
import com.ootd.weatherlook.model.ReplyReportDTO;
import com.ootd.weatherlook.model.ReviewReportDTO;

@Repository
@RequiredArgsConstructor
public class ReReviewDao {

	private final SqlSession session;

	public List<ReReview> reReviewList(int post_id) {
		System.out.println("ReReviewDao.reReviewList");
		return session.selectList("reReview.reReviewList", post_id);
	}

	public void reInsert(ReReviewDTO reReview) {
		System.out.println("ReReviewDao.reInsert");
		session.insert("reReview.reInsert", reReview);
	}

	public void reUpdate(ReReviewDTO reReview) {
		System.out.println("ReReviewDao.reUpdate");
		session.update("reReview.reUpdate", reReview);
	}

	public void reDelete(int re_id) {
		System.out.println("ReReviewDao.reDelete");
		session.delete("reReview.reDelete", re_id);
	}

	public void reReplyInsert(ReReviewDTO reReview) {
		System.out.println("ReReviewDao.reReplyInsert");
		session.insert("reReview.reReplyInsert", reReview);
	}

	public void reReportInsert(ReplyReportDTO replyReport) {
		System.out.println("ReReviewDao.reReportInsert");
		session.insert("reReview.reReportInsert", replyReport);
	}
	
	public ReReviewDTO getRevReply(int re_id) {
		System.out.println("ReReviewDao.getRevReply");
		return session.selectOne("reReview.getRevReply", re_id);
	}
}
