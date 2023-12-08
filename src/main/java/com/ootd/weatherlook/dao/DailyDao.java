package com.ootd.weatherlook.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.Daily;
import com.ootd.weatherlook.model.LikeDTO;
import com.ootd.weatherlook.model.ScrapDTO;
import com.ootd.weatherlook.model.Search;

@Repository
public class DailyDao {

	@Autowired
	private SqlSession session;

	public int insert(Daily daily) {
		System.out.println(daily.getNick());
		return session.insert("insert", daily);
	}

	// ========== 선홍 수정: dailylist ===========
	
	public int getCount(Search search) {
		// TODO Auto-generated method stub
		return session.selectOne("dailyns.count", search);
	}

	public List<Daily> getDailyList(Search search) {
		// TODO Auto-generated method stub
		return session.selectList("dailyns.list", search);
	}

	// ========== 선홍 수정: dailylist ===========
	
	public Daily getDaily(int post_id) {
		return session.selectOne("content", post_id);
	}

	public int update(Daily daily) {
		return session.update("update", daily);
	}

	public int delete(int post_id) {
		return session.delete("delete", post_id);
	}

	public void updatecount(int post_id) {
		session.update("hit", post_id);
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
