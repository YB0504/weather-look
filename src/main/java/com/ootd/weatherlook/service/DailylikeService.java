package com.ootd.weatherlook.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.DailylikeDao;
import com.ootd.weatherlook.model.Dailylike;

@Service
public class DailylikeService {

	@Autowired
	private DailylikeDao dd;

	public int insert(Dailylike dl) {
		return dd.insert(dl);
	}

	public int update(Dailylike dl) {
		return dd.update(dl);
	}
	
	public List<Dailylike> getList(Map map) {
		return dd.getList(map);
	}
	
	
}
