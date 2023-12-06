package com.ootd.weatherlook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.CommScrapDao;
import com.ootd.weatherlook.model.CommunityScrap;

@Service
public class CommScrapService {

	@Autowired
	private CommScrapDao csd;

	public int insert(CommunityScrap commscrap) {
		// TODO Auto-generated method stub
		return csd.insert(commscrap);
	}

	public void delete(int scrap_id) {
		// TODO Auto-generated method stub
		csd.delete(scrap_id);
	}

	public CommunityScrap getScrap(CommunityScrap commscrap) {
		// TODO Auto-generated method stub
		return csd.getScrap(commscrap);
	}
	
}
