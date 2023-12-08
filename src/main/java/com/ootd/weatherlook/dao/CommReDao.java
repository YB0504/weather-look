package com.ootd.weatherlook.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.CommReReportDTO;
import com.ootd.weatherlook.model.CommunityRe;

@Repository
public class CommReDao {

	@Autowired
	private SqlSession rsession;

	public List<CommunityRe> list(int post_id) {
		// TODO Auto-generated method stub
		return rsession.selectList("rlist", post_id);
	}

	public void insert(CommunityRe cr) {
		// TODO Auto-generated method stub
		rsession.insert("rinsert",cr);
	}

	public void update(CommunityRe cr) {
		// TODO Auto-generated method stub
		System.out.println("dao");
		rsession.update("rupdate", cr);
	}

	public void delete(int re_id) {
		// TODO Auto-generated method stub
		rsession.delete("rdelete", re_id);
	}

	public void commReplyInsert(CommunityRe cr) {
		rsession.insert("commReplyInsert", cr);
	}

	public void reReportInsert(CommReReportDTO commReReport) {
		rsession.insert("reReportInsert", commReReport);
	}


	
}
