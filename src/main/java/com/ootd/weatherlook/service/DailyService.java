package com.ootd.weatherlook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.DailyDao;
import com.ootd.weatherlook.model.Daily;
import com.ootd.weatherlook.model.DailyReportDTO;
import com.ootd.weatherlook.model.LikeDTO;
import com.ootd.weatherlook.model.ScrapDTO;
import com.ootd.weatherlook.model.Search;

@Service
public class DailyService {
	
	@Autowired
	private DailyDao dao;
	
	public int insert(Daily daily) {
		System.out.println("service");
		return dao.insert(daily);
	}

	// ========== 선홍 수정: dailylist ===========
	
	public int getCount(Search search) {
		return dao.getCount(search);
	}
	
	public List<Daily> getDailylist(Search search) {
		return dao.getDailyList(search);
	}


	// ========== 선홍 수정: dailylist ===========
	
	public Daily getDaily(int post_id) {
		return dao.getDaily(post_id);
	}

	public int update(Daily daily) {
		return dao.update(daily);
	}

	public int delete(int post_id) {
		return dao.delete(post_id);
	}

	public void updatecount(int post_id) {
		dao.updatecount(post_id);
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

	public void reportInsert(DailyReportDTO dailyReport) {
		// TODO Auto-generated method stub
		dao.reportInsert(dailyReport);
	}
}
