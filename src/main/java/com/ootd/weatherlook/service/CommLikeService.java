package com.ootd.weatherlook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.CommLikeDao;

@Service
public class CommLikeService {

	@Autowired
	private CommLikeDao cliked;
}
