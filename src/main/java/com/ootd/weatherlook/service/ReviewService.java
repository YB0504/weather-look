package com.ootd.weatherlook.service;

import java.util.List;

import com.ootd.weatherlook.model.LikeDTO;
import com.ootd.weatherlook.model.ScrapDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.ReviewDao;
import com.ootd.weatherlook.model.ReviewDTO;
import com.ootd.weatherlook.model.ReviewReportDTO;

@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewDao dao;

	public int getPostCount() {
		System.out.println("ReviewService.getPostCount");
		return dao.getPostCount();
	}

	public void reviewInsert(ReviewDTO review) {
		System.out.println("ReviewService.reviewInsert");
		dao.getReviewInsert(review);
	}

	public List<ReviewDTO> getReviewList(int page) {
		System.out.println("ReviewService.getReviewList");
		return dao.getReviewList(page);
	}
	
	public void updateCount(int post_id) {
		System.out.println("ReviewService.updateCount");
		dao.getUpdateCount(post_id);
	}

	public ReviewDTO getReview(int post_id) {
		System.out.println("ReviewService.getReview");
		return dao.getReview(post_id);
	}
	
	public void reportInsert(ReviewReportDTO reviewReport) {
		System.out.println("ReviewService.reportInsert");
		dao.reportInsert(reviewReport);
	}

	public int reviewUpdate(ReviewDTO review) {
		System.out.println("ReviewService.reviewUpdate");
		return dao.reviewUpdate(review);
	}

	public int reviewDelete(int post_id) {
		System.out.println("ReviewService.reviewDelete");
		return dao.reviewDelete(post_id);
	}

	public void likeInsert(LikeDTO likeDTO) {
		System.out.println("ReviewService.likeInsert");
		dao.likeInsert(likeDTO);
	}

	public void likeDelete(int like_id) {
		System.out.println("ReviewService.likeDelete");
		dao.likeDelete(like_id);
	}

	public LikeDTO isLike(LikeDTO likeDTO) {
		System.out.println("ReviewService.isLike");
		return dao.isLike(likeDTO);
	}

	public void scrapInsert(ScrapDTO scrapDTO) {
		System.out.println("ReviewService.scrapInsert");
		dao.scrapInsert(scrapDTO);
	}

	public void scrapDelete(int scrap_id) {
		System.out.println("ReviewService.scrapDelete");
		dao.scrapDelete(scrap_id);
	}

	public ScrapDTO isScrap(ScrapDTO scrapDTO) {
		System.out.println("ReviewService.isScrap");
		return dao.isScrap(scrapDTO);
	}
}
