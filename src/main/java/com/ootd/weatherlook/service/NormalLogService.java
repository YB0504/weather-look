package com.ootd.weatherlook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.NormalLogDao;
import com.ootd.weatherlook.model.MemberDTO;

@Service
public class NormalLogService {

	@Autowired
	private NormalLogDao memberDao;

	public int idcheck(String id) {
		return memberDao.idcheck(id);
	}

	public int nickcheck(String nick) {
		return memberDao.nickcheck(nick);
	}

	public void insertMember(MemberDTO member) {
		memberDao.insertMember(member);
	}

	public MemberDTO login(String id) {
		return memberDao.login(id);
	}
	
	
}
