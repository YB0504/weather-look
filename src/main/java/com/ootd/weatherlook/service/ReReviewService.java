package com.ootd.weatherlook.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.controller.ReReview;
import com.ootd.weatherlook.dao.ReReviewDao;
import com.ootd.weatherlook.model.ReReviewDTO;
import com.ootd.weatherlook.model.ReplyReportDTO;
import com.ootd.weatherlook.model.ReviewReportDTO;

@Service
@RequiredArgsConstructor
public class ReReviewService {

	private final ReReviewDao reDao;

	public List<ReReview> reReviewList(int post_id) {
		System.out.println("ReReviewService.reReviewList");
		return reDao.reReviewList(post_id);
	}

	public void reInsert(ReReviewDTO reReview) {
		System.out.println("ReReviewService.reInsert");
		reDao.reInsert(reReview);
	}

	public void reUpdate(ReReviewDTO reReview) {
		System.out.println("ReReviewService.reUpdate");
		reDao.reUpdate(reReview);
	}

	public void reDelete(int re_id) {
		System.out.println("ReReviewService.reDelete");
		reDao.reDelete(re_id);
	}

	public void reReplyInsert(ReReviewDTO reReview) {
		System.out.println("ReReviewService.reReplyInsert");
		reDao.reReplyInsert(reReview);
	}

	public void reReportInsert(ReplyReportDTO replyReport) {
		System.out.println("ReReviewService.reReportInsert");
		reDao.reReportInsert(replyReport);
	}
	
	public ReReviewDTO getRevReply(int re_id) {
		System.out.println("ReReviewService.getRevReply");
		return reDao.getRevReply(re_id);
	}
}
