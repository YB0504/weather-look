package com.ootd.weatherlook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.CommDao;
import com.ootd.weatherlook.model.Community;

@Service
public class CommService {

	@Autowired
	private CommDao cd;
	
	public int commInsert(Community comm) {
		System.out.println("service");
		return cd.commInsert(comm);
	}

	public int getCommCount() {
		// TODO Auto-generated method stub
		return cd.getCommCount();
	}

	public void commUpdateCount(int post_id) {
		// TODO Auto-generated method stub
		cd.commUpdateCount(post_id);
	}

	public List<Community> getCommList(int page) {
		// TODO Auto-generated method stub
		return cd.getCommList(page);
	}


	public Community getCommunity(int post_id) {
		// TODO Auto-generated method stub
		return cd.getCommunity(post_id);
	}

	public int commUpdate(Community comm) {
		// TODO Auto-generated method stub
		return cd.commUpdate(comm);
	}

	public int commDelete(int post_id) {
		// TODO Auto-generated method stub
		return cd.commDelete(post_id);
	}

	

	
}
