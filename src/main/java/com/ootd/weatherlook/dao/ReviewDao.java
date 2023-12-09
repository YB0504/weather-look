package com.ootd.weatherlook.dao;

import java.util.List;
import com.ootd.weatherlook.model.LikeDTO;
import com.ootd.weatherlook.model.ScrapDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.ootd.weatherlook.model.ReviewDTO;
import com.ootd.weatherlook.model.ReviewReportDTO;

@Repository
@RequiredArgsConstructor
public class ReviewDao{

	private final SqlSession session;

	public int getPostCount() {
		System.out.println("ReviewDao.getPostCount");
		return ((Integer) session.selectOne("review.postCount")).intValue();
	}

	public void getReviewInsert(ReviewDTO review) {
		System.out.println("ReviewDao.getReviewInsert");
		session.insert("review.reviewInsert", review);
	}

	public List<ReviewDTO> getReviewList(int page) {
		System.out.println("ReviewDao.getReviewList");
		return session.selectList("review.list", page);
	}
	
	public void getUpdateCount(int post_id) {
		System.out.println("ReviewDao.getUpdateCount");
		session.update("review.updateCount", post_id);
	}

	public ReviewDTO getReview(int post_id) {
		System.out.println("ReviewDao.getReview");
		return session.selectOne("review.content", post_id);
	}

	public void reportInsert(ReviewReportDTO reviewReport) {
		System.out.println("ReviewDao.reportInsert");
		session.insert("review.reportInsert", reviewReport);
	}

	public int reviewUpdate(ReviewDTO review) {
		System.out.println("ReviewDao.reviewUpdate");
		return session.update("review.reviewUpdate", review);
	}

	public int reviewDelete(int post_id) {
		System.out.println("ReviewDao.reviewDelete");
		return session.delete("review.reviewDelete", post_id);
	}

	public void likeInsert(LikeDTO likeDTO) {
		System.out.println("ReviewDao.likeInsert");
		session.insert("review.likeInsert", likeDTO);
	}

	public void likeDelete(int like_id) {
		System.out.println("ReviewDao.likeDelete");
		session.delete("review.likeDelete", like_id);
	}

	public LikeDTO isLike(LikeDTO likeDTO) {
		System.out.println("ReviewDao.isLike");
		return session.selectOne("review.isLike", likeDTO);
	}

	public void scrapInsert(ScrapDTO scrapDTO) {
		System.out.println("ReviewDao.scrapInsert");
		session.insert("review.scrapInsert", scrapDTO);
	}

	public void scrapDelete(int scrap_id) {
		System.out.println("ReviewDao.scrapDelete");
		session.delete("review.scrapDelete", scrap_id);
	}

	public ScrapDTO isScrap(ScrapDTO scrapDTO) {
		System.out.println("ReviewDao.isScrap");
		return session.selectOne("review.isScrap", scrapDTO);
	}
}
