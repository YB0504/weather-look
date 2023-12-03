package com.ootd.weatherlook.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.Dailylike;

@Repository
public class DailylikeDao {

	@Autowired
	private SqlSession session;
	
	
	public int saveHeart(Dailylike to) {
		// 해당 게시물의 heart를 +1한다.
		return session.insert("insertLike", to);
	}
	
	public int getDailyLike(Dailylike dl) {
		// TODO Auto-generated method stub
		return session.selectOne("selectDailyLike", dl);
	}

	
	
	

//	public static Dailylike pictureSaveHeart(Dailylike to) {
		// TODO Auto-generated method stub
		// dailylike 테이블에 해당 게시물의 heart 수를 +1하기 위한 to세팅
//		Dailylike pto = new Dailylike();
//		pto.setNo(to.getPost_id());
		
		
		
		// dailylike 테이블에 추가
//		int result = SqlSession.insert("dailylike", to);
//		
//		if(result == 1) {	// p_heart 테이블에 새로운 좋아요 추가가 성공
//			
//		}


}
