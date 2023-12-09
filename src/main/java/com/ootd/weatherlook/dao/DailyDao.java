package com.ootd.weatherlook.dao;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.ootd.weatherlook.model.Daily;
import com.ootd.weatherlook.model.DailyReportDTO;
import com.ootd.weatherlook.model.LikeDTO;
import com.ootd.weatherlook.model.ScrapDTO;
import com.ootd.weatherlook.model.Search;

@Repository
@RequiredArgsConstructor
public class DailyDao {

	private final SqlSession session;

	public int insert(Daily daily) {
		System.out.println("DailyDao.insert");
		return session.insert("dailyns.insert", daily);
	}
	
	public int getCount(Search search) {
		System.out.println("DailyDao.getCount");
		return session.selectOne("dailyns.count", search);
	}

	public List<Daily> getDailyList(Search search) {
		System.out.println("DailyDao.getDailyList");
		return session.selectList("dailyns.list", search);
	}
	
	public Daily getDaily(int post_id) {
		System.out.println("DailyDao.getDaily");
		return session.selectOne("dailyns.content", post_id);
	}

	public int update(Daily daily) {
		System.out.println("DailyDao.update");
		return session.update("dailyns.update", daily);
	}

	public int delete(int post_id) {
		System.out.println("DailyDao.delete");
		return session.delete("dailyns.delete", post_id);
	}

	public void updatecount(int post_id) {
		System.out.println("DailyDao.updatecount");
		session.update("dailyns.hit", post_id);
	}

	public void likeInsert(LikeDTO likeDTO) {
		System.out.println("DailyDao.likeInsert");
		session.insert("dailyns.likeInsert", likeDTO);
	}

	public void likeDelete(int like_id) {
		System.out.println("DailyDao.likeDelete");
		session.delete("dailyns.likeDelete", like_id);
	}

	public LikeDTO isLike(LikeDTO likeDTO) {
		System.out.println("DailyDao.isLike");
		return session.selectOne("dailyns.isLike", likeDTO);
	}

	public void scrapInsert(ScrapDTO scrapDTO) {
		System.out.println("DailyDao.scrapInsert");
		session.insert("dailyns.scrapInsert", scrapDTO);
	}

	public void scrapDelete(int scrap_id) {
		System.out.println("DailyDao.scrapDelete");
		session.delete("dailyns.scrapDelete", scrap_id);
	}

	public ScrapDTO isScrap(ScrapDTO scrapDTO) {
		System.out.println("DailyDao.isScrap");
		return session.selectOne("dailyns.isScrap", scrapDTO);
	}

	public void reportInsert(DailyReportDTO dailyReport) {
		System.out.println("DailyDao.reportInsert");
		session.insert("dailyns.reportInsert", dailyReport);
	}
}
