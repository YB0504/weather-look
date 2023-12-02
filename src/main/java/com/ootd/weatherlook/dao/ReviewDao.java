package com.ootd.weatherlook.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.ReviewDTO;

@Repository
public class ReviewDao{
	
	@Autowired
	private SqlSession session;

	// 게시글 개수
	public int getPostCount() {
		
		// 구해온 게시글 개수를 count에 저장하여 리턴
		int count = 0;	
		count = ((Integer) session.selectOne("postCount")).intValue();

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



}
