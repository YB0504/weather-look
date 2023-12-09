package com.ootd.weatherlook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ootd.weatherlook.dao.CommLikeDao;
import com.ootd.weatherlook.model.CommunityLike;

@Service
@RequiredArgsConstructor
public class CommLikeService {

	private final CommLikeDao cld;

	public int commLikeInsert(CommunityLike commlike) {
		System.out.println("CommLikeService.commLikeInsert");
		return cld.commLikeInsert(commlike);
	}

	public CommunityLike getCommLike(CommunityLike commlike) {
		System.out.println("CommLikeService.getCommLike");
		return cld.getCommLike(commlike);
	}

	public void commLikeDelete(int like_id) {
		System.out.println("CommLikeService.commLikeDelete");
		cld.commLikeDelete(like_id);
	}
}
	
