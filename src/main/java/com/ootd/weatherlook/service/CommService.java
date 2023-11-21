package com.ootd.weatherlook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.CommDao;
import com.ootd.weatherlook.model.Community;

@Service
public class CommService {

	@Autowired
	private CommDao dao;
	
	public int insert(Community comm) {
		System.out.println("service");
		return dao.insert(comm);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return dao.getCount();
	}

	public List<Community> getCommList(int page) {
		// TODO Auto-generated method stub
		return dao.getCommList(page);
	}

	
}
