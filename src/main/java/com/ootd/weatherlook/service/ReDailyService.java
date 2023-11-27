package com.ootd.weatherlook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.ReDailyDao;
import com.ootd.weatherlook.model.ReDaily;

@Service
public class ReDailyService {
	
	@Autowired
	private ReDailyDao rdd;
	

	public List<ReDaily> list(int post_id) {
		return rdd.list(post_id);
	}


	public void insert(ReDaily rb) {
		rdd.insert(rb);
	}


	
}
