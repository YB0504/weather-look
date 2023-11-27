package com.ootd.weatherlook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ootd.weatherlook.dao.LoginDao;
import com.ootd.weatherlook.model.MemberDTO;

@Service
public class LoginService {

	@Autowired
	private LoginDao memberDao;

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
	
	public MemberDTO kakaoLoginCheck(String nick) {
		return memberDao.kakaoLoginCheck(nick);
	}
	//카카오로그인할때 db저장
	public int kakaoLogin(MemberDTO kakao){
		return memberDao.kakaologin(kakao);
	}
	
	
}
