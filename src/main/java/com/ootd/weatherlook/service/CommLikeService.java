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

	public int insert(CommunityLike commlike) {
		// TODO Auto-generated method stub
		return cld.insert(commlike);
	}

	public CommunityLike getLike(CommunityLike commlike) {
		// TODO Auto-generated method stub
		return cld.getLike(commlike);
	}

	public void delete(int post_id) {
		// TODO Auto-generated method stub
		cld.delete(post_id);
	}

	


}
	
