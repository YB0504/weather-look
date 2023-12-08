package com.ootd.weatherlook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.CommReDao;
import com.ootd.weatherlook.model.CommReReportDTO;
import com.ootd.weatherlook.model.CommunityRe;

@Service
public class CommReService {

	@Autowired
	private CommReDao crd;

	public List<CommunityRe> list(int post_id) {
		// TODO Auto-generated method stub
		return crd.list(post_id);
	}

	public void insert(CommunityRe cr) {
		// TODO Auto-generated method stub
		crd.insert(cr);
	}

	public void update(CommunityRe cr) {
		// TODO Auto-generated method stub
		System.out.println("service");
		crd.update(cr);
		
	}

	public void delete(int re_id) {
		// TODO Auto-generated method stub
		crd.delete(re_id);
	}

	public void commReplyInsert(CommunityRe cr) {
		crd.commReplyInsert(cr);
	}

	public void reReportInsert(CommReReportDTO commReReport) {
		crd.reReportInsert(commReReport);
	}

	
	
}
