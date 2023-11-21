package com.ootd.weatherlook.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.MemberDTO;

@Repository
public class NormalLogDao {
	
	@Autowired
	private SqlSession session;

	public int idcheck(String id) {
		
		int re = -1;	// 가능 ID
		
		MemberDTO dto = session.selectOne("idcheck", id);
		if (dto != null)
			re = 1;		// 중복id
		
		return re;
	}

	public int nickcheck(String nick) {
		
		int re = -1;	// 가능 nick
		
		MemberDTO dto = session.selectOne("nickcheck", nick);
		if (dto != null)
			re = 1;		// 중복nick
		
		return re;
	}

}
