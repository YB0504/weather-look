package com.ootd.weatherlook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ootd.weatherlook.dao.CommScrapDao;
import com.ootd.weatherlook.model.CommunityScrap;

@Service
@RequiredArgsConstructor
public class CommScrapService {

	private final CommScrapDao csd;

	public int commScrapInsert(CommunityScrap commscrap) {
		System.out.println("CommScrapService.commScrapInsert");
		return csd.commScrapInsert(commscrap);
	}

	public void commScrapDelete(int scrap_id) {
		System.out.println("CommScrapService.commScrapDelete");
		csd.commScrapDelete(scrap_id);
	}

	public CommunityScrap getCommScrap(CommunityScrap commscrap) {
		System.out.println("CommScrapService.getCommScrap");
		return csd.getCommScrap(commscrap);
	}
}
