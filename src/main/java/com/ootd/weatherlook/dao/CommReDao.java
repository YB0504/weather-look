package com.ootd.weatherlook.dao;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.CommReReportDTO;
import com.ootd.weatherlook.model.CommunityRe;

@Repository
@RequiredArgsConstructor
public class CommReDao {
	private final SqlSession rsession;

	public List<CommunityRe> list(int post_id) {
		System.out.println("CommReDao.list");
		return rsession.selectList("commre.rlist", post_id);
	}

	public void insert(CommunityRe cr) {
		System.out.println("CommReDao.insert");
		rsession.insert("commre.rinsert",cr);
	}

	public void update(CommunityRe cr) {
		System.out.println("CommReDao.update");
		rsession.update("commre.rupdate", cr);
	}

	public void delete(int re_id) {
		System.out.println("CommReDao.delete");
		rsession.delete("commre.rdelete", re_id);
	}

	public void commReplyInsert(CommunityRe cr) {
		System.out.println("CommReDao.commReplyInsert");
		rsession.insert("commre.commReplyInsert", cr);
	}

	public void reReportInsert(CommReReportDTO commReReport) {
		System.out.println("CommReDao.reReportInsert");
		rsession.insert("commre.reReportInsert", commReReport);
	}

	public CommunityRe getReply(int re_id) {
		System.out.println("CommReDao.getReply");
		return rsession.selectOne("commre.getReply", re_id);
	}
}
