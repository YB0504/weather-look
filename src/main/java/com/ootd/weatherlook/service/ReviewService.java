package com.ootd.weatherlook.service;

import java.util.List;

import com.ootd.weatherlook.model.LikeDTO;
import com.ootd.weatherlook.model.ScrapDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.ReviewDao;
import com.ootd.weatherlook.model.ReviewDTO;
import com.ootd.weatherlook.model.ReviewReportDTO;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewDao dao;

	public int getPostCount() {
		return dao.getPostCount();
	}

	public void reviewInsert(ReviewDTO review) {
		dao.getReviewInsert(review);
	}

	public List<ReviewDTO> getReviewList(int page) {
		return dao.getReviewList(page);
	}
	
	public void updateCount(int post_id) {
		dao.getUpdateCount(post_id);
	}

	public ReviewDTO getReview(int post_id) {
		return dao.getReview(post_id);
	}
	
	public void reportInsert(ReviewReportDTO reviewReport) {
		dao.reportInsert(reviewReport);
	}

	public int reviewUpdate(ReviewDTO review) {
		return dao.reviewUpdate(review);
	}

	public int reviewDelete(int post_id) {
		return dao.reviewDelete(post_id);
	}

	public void likeInsert(LikeDTO likeDTO) {
		dao.likeInsert(likeDTO);
	}

	public void likeDelete(int like_id) {
		dao.likeDelete(like_id);
	}

	public LikeDTO isLike(LikeDTO likeDTO) {
		return dao.isLike(likeDTO);
	}

	public void scrapInsert(ScrapDTO scrapDTO) {
		dao.scrapInsert(scrapDTO);
	}

	public void scrapDelete(int scrap_id) {
		dao.scrapDelete(scrap_id);
	}

	public ScrapDTO isScrap(ScrapDTO scrapDTO) {
		return dao.isScrap(scrapDTO);
	}
}
