package com.ootd.weatherlook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.CommReDao;

@Service
public class CommReService {

	@Autowired
	private CommReDao rdao;
}
