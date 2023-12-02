package com.ootd.weatherlook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.ReviewDao;
import com.ootd.weatherlook.model.ReviewDTO;

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


}
