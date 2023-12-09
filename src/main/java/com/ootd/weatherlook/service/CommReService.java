package com.ootd.weatherlook.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.CommReDao;
import com.ootd.weatherlook.model.CommReReportDTO;
import com.ootd.weatherlook.model.CommunityRe;

@Service
@RequiredArgsConstructor
public class CommReService {

	private final CommReDao crd;

	public List<CommunityRe> list(int post_id) {
		System.out.println("CommReService.list");
		return crd.list(post_id);
	}

	public void insert(CommunityRe cr) {
		System.out.println("CommReService.insert");
		crd.insert(cr);
	}

	public void update(CommunityRe cr) {
		System.out.println("CommReService.update");
		crd.update(cr);
	}

	public void delete(int re_id) {
		System.out.println("CommReService.delete");
		crd.delete(re_id);
	}

	public void commReplyInsert(CommunityRe cr) {
		System.out.println("CommReService.commReplyInsert");
		crd.commReplyInsert(cr);
	}

	public void reReportInsert(CommReReportDTO commReReport) {
		System.out.println("CommReService.reReportInsert");
		crd.reReportInsert(commReReport);
	}

	public CommunityRe getReply(int re_id) {
		System.out.println("CommReService.getReply");
		return crd.getReply(re_id);
	}
}
