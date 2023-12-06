package com.ootd.weatherlook.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.controller.ReReview;
import com.ootd.weatherlook.model.ReReviewDTO;

@Repository
public class ReReviewDao {
	
	@Autowired
	private SqlSession session;

	public List<ReReview> reReviewList(int post_id) {
		return session.selectList("reReviewList", post_id);
	}

	public void reInsert(ReReviewDTO reReview) {
		session.insert("reInsert", reReview);
	}

	public void reUpdate(ReReviewDTO reReview) {
		session.update("reUpdate", reReview);
	}

	public void reDelete(int re_id) {
		session.delete("reDelete", re_id);
	}

	public void reReplyInsert(ReReviewDTO reReview) {
		session.insert("reReplyInsert", reReview);
	}
}
