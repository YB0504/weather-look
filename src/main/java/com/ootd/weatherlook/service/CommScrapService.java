package com.ootd.weatherlook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.CommScrapDao;
import com.ootd.weatherlook.model.CommunityScrap;

@Service
public class CommScrapService {

	@Autowired
	private CommScrapDao csd;

	public int commScrapInsert(CommunityScrap commscrap) {
		// TODO Auto-generated method stub
		return csd.commScrapInsert(commscrap);
	}

	public void commScrapDelete(int scrap_id) {
		// TODO Auto-generated method stub
		csd.commScrapDelete(scrap_id);
	}

	public CommunityScrap getCommScrap(CommunityScrap commscrap) {
		// TODO Auto-generated method stub
		return csd.getCommScrap(commscrap);
	}
	
}
