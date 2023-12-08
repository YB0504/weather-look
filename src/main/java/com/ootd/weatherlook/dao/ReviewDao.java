package com.ootd.weatherlook.dao;

import java.util.List;

import com.ootd.weatherlook.model.LikeDTO;
import com.ootd.weatherlook.model.ScrapDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.ReviewDTO;
import com.ootd.weatherlook.model.ReviewReportDTO;

@Repository
public class ReviewDao{
	
	@Autowired
	private SqlSession session;

	// 게시글 개수
	public int getPostCount() {
		
		// 구해온 게시글 개수를 count에 저장하여 리턴
		int count = 0;	
		count = ((Integer) session.selectOne("review.postCount")).intValue();

		return count;
	}

	public void getReviewInsert(ReviewDTO review) {
		session.insert("reviewInsert", review);
	}

	public List<ReviewDTO> getReviewList(int page) {
		return session.selectList("list", page);
	}
	
	public void getUpdateCount(int post_id) {
		session.update("updateCount", post_id);
	}

	public ReviewDTO getReview(int post_id) {
		return session.selectOne("content", post_id);
	}

	public void reportInsert(ReviewReportDTO reviewReport) {
		session.insert("reportInsert", reviewReport);
	}

	public int reviewUpdate(ReviewDTO review) {
		return session.update("reviewUpdate", review);
	}

	public int reviewDelete(int post_id) {
		return session.delete("reviewDelete", post_id);
	}

	public void likeInsert(LikeDTO likeDTO) {
		session.insert("likeInsert", likeDTO);
	}

	public void likeDelete(int like_id) {
		session.delete("likeDelete", like_id);
	}

	public LikeDTO isLike(LikeDTO likeDTO) {
		return session.selectOne("isLike", likeDTO);
	}

	public void scrapInsert(ScrapDTO scrapDTO) {
		session.insert("scrapInsert", scrapDTO);
	}

	public void scrapDelete(int scrap_id) {
		session.delete("scrapDelete", scrap_id);
	}

	public ScrapDTO isScrap(ScrapDTO scrapDTO) {
		return session.selectOne("isScrap", scrapDTO);
	}
}
