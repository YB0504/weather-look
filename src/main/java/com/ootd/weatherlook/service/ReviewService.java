package com.ootd.weatherlook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.ReviewDao;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewDao dao;
}
