package com.ootd.weatherlook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.SocialLogDao;

@Service
public class SocialLogService {
	
	@Autowired
	private SocialLogDao socialDao;
}
