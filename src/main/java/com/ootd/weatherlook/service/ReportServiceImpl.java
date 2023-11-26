package com.ootd.weatherlook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.ReportDao;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportDao reportDao;
	
	@Override
	public void removeDaily(int post_id) {
		reportDao.removeDaily(post_id);
	}
	@Override
	public void removeReview(int post_id) {
		reportDao.removeReview(post_id);
	}
	@Override
	public void removeCommunity(int post_id) {
		reportDao.removeCommunity(post_id);
	}
	
}
