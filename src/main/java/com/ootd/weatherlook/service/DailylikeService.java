package com.ootd.weatherlook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.DailylikeDao;
import com.ootd.weatherlook.model.Dailylike;

@Service
public class DailylikeService {

	@Autowired
	private DailylikeDao dd;

	public int saveHeart(Dailylike to) {
		if (dd.getDailyLike(to) >= 1) {
			return 0;
		} else {
			dd.saveHeart(to);
			return 1;
		}
	}
	
}
