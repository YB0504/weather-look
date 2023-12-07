package com.ootd.weatherlook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.controller.ReReview;
import com.ootd.weatherlook.dao.ReReviewDao;
import com.ootd.weatherlook.model.ReReviewDTO;
import com.ootd.weatherlook.model.ReplyReportDTO;
import com.ootd.weatherlook.model.ReviewReportDTO;

@Service
public class ReReviewService {

	@Autowired
	private ReReviewDao reDao;

	public List<ReReview> reReviewList(int post_id) {
		return reDao.reReviewList(post_id);
	}

	public void reInsert(ReReviewDTO reReview) {
		reDao.reInsert(reReview);
	}

	public void reUpdate(ReReviewDTO reReview) {
		reDao.reUpdate(reReview);
	}

	public void reDelete(int re_id) {
		reDao.reDelete(re_id);
	}

	public void reReplyInsert(ReReviewDTO reReview) {
		reDao.reReplyInsert(reReview);
	}

	public void reReportInsert(ReplyReportDTO replyReport) {
		reDao.reReportInsert(replyReport);
	}

}
