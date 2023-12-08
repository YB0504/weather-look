package com.ootd.weatherlook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ootd.weatherlook.dao.CommLikeDao;
import com.ootd.weatherlook.model.CommunityLike;
import com.ootd.weatherlook.model.CommunityRe;

@Service
public class CommLikeService {

	@Autowired
	private CommLikeDao cld;

	public int commLikeInsert(CommunityLike commlike) {
		// TODO Auto-generated method stub
		return cld.commLikeInsert(commlike);
	}

	public CommunityLike getCommLike(CommunityLike commlike) {
		// TODO Auto-generated method stub
		return cld.getCommLike(commlike);
	}

	public void commLikeDelete(int like_id) {
		// TODO Auto-generated method stub
		cld.commLikeDelete(like_id);

	}	


}
	
