package com.ootd.weatherlook.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ootd.weatherlook.dao.CommDao;
import com.ootd.weatherlook.model.CommReportDTO;
import com.ootd.weatherlook.model.Community;

@Service
@RequiredArgsConstructor
public class CommService {

	private final CommDao cd;
	
	public int commInsert(Community comm) {
		System.out.println("CommService.commInsert");
		return cd.commInsert(comm);
	}

	public int getCommCount() {
		System.out.println("CommService.getCommCount");
		return cd.getCommCount();
	}

	public void commUpdateCount(int post_id) {
		System.out.println("CommService.commUpdateCount");
		cd.commUpdateCount(post_id);
	}

	public List<Community> getCommList(int page) {
		System.out.println("CommService.getCommList");
		return cd.getCommList(page);
	}


	public Community getCommunity(int post_id) {
		System.out.println("CommService.getCommunity");
		return cd.getCommunity(post_id);
	}

	public int commUpdate(Community comm) {
		System.out.println("CommService.commUpdate");
		return cd.commUpdate(comm);
	}

	public int commDelete(int post_id) {
		System.out.println("CommService.commDelete");
		return cd.commDelete(post_id);
	}

	public void reportInsert(CommReportDTO communityReport) {
		System.out.println("CommService.reportInsert");
		cd.reportInsert(communityReport);
	}

	

	
}
