package com.ootd.weatherlook.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.DailyDao;
import com.ootd.weatherlook.model.Daily;
import com.ootd.weatherlook.model.DailyReportDTO;
import com.ootd.weatherlook.model.LikeDTO;
import com.ootd.weatherlook.model.ScrapDTO;
import com.ootd.weatherlook.model.Search;

@Service
@RequiredArgsConstructor
public class DailyService {

	private final DailyDao dao;
	
	public int insert(Daily daily) {
		System.out.println("DailyService.insert");
		return dao.insert(daily);
	}
	
	public int getCount(Search search) {
		System.out.println("DailyService.getCount");
		return dao.getCount(search);
	}
	
	public List<Daily> getDailylist(Search search) {
		System.out.println("DailyService.getDailylist");
		return dao.getDailyList(search);
	}
	
	public Daily getDaily(int post_id) {
		System.out.println("DailyService.getDaily");
		return dao.getDaily(post_id);
	}

	public int update(Daily daily) {
		System.out.println("DailyService.update");
		return dao.update(daily);
	}

	public int delete(int post_id) {
		System.out.println("DailyService.delete");
		return dao.delete(post_id);
	}

	public void updatecount(int post_id) {
		System.out.println("DailyService.updatecount");
		dao.updatecount(post_id);
	}

	public void likeInsert(LikeDTO likeDTO) {
		System.out.println("DailyService.likeInsert");
		dao.likeInsert(likeDTO);
	}

	public void likeDelete(int like_id) {
		System.out.println("DailyService.likeDelete");
		dao.likeDelete(like_id);
	}

	public LikeDTO isLike(LikeDTO likeDTO) {
		System.out.println("DailyService.isLike");
		return dao.isLike(likeDTO);
	}

	public void scrapInsert(ScrapDTO scrapDTO) {
		System.out.println("DailyService.scrapInsert");
		dao.scrapInsert(scrapDTO);
	}

	public void scrapDelete(int scrap_id) {
		System.out.println("DailyService.scrapDelete");
		dao.scrapDelete(scrap_id);
	}

	public ScrapDTO isScrap(ScrapDTO scrapDTO) {
		System.out.println("DailyService.isScrap");
		return dao.isScrap(scrapDTO);
	}

	public void reportInsert(DailyReportDTO dailyReport) {
		System.out.println("DailyService.reportInsert");
		dao.reportInsert(dailyReport);
	}
}
