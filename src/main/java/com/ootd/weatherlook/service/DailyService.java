package com.ootd.weatherlook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.DailyDao;
import com.ootd.weatherlook.model.Daily;

@Service
public class DailyService {
	
	@Autowired
	private DailyDao dao;
	
	public int insert(Daily daily) {
		System.out.println("service");
		return dao.insert(daily);
	}

	public int getCount() {
		return dao.getCount();
	}
	
	public List<Daily> getDailylist(int page) {
		return dao.getDailyList(page);
	}

	public void updatecount(int post_id) {
		// TODO Auto-generated method stub
		dao.updatecount(post_id);
	}

	public Daily getDaily(int post_id) {
		// TODO Auto-generated method stub
		return dao.getDaily(post_id);
	}

	public int update(Daily daily) {
		// TODO Auto-generated method stub
		return dao.update(daily);
	}

	public int delete(int post_id) {
		// TODO Auto-generated method stub
		return dao.delete(post_id);
	}



	
}
