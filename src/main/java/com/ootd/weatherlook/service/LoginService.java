package com.ootd.weatherlook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ootd.weatherlook.dao.LoginDao;
import com.ootd.weatherlook.model.MemberDTO;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final LoginDao memberDao;

	public int idcheck(String id) {
		System.out.println("LoginService.idcheck");
		return memberDao.idcheck(id);
	}

	public int nickcheck(String nick) {
		System.out.println("LoginService.nickcheck");
		return memberDao.nickcheck(nick);
	}

	public void insertMember(MemberDTO member) {
		System.out.println("LoginService.insertMember");
		memberDao.insertMember(member);
	}

	public MemberDTO login(String id) {
		System.out.println("LoginService.login");
		return memberDao.login(id);
	}
	
	public MemberDTO kakaoLoginCheck(String nick) {
		System.out.println("LoginService.kakaoLoginCheck");
		return memberDao.kakaoLoginCheck(nick);
	}

	public int kakaoLogin(MemberDTO kakao){
		System.out.println("LoginService.kakaoLogin");
		return memberDao.kakaologin(kakao);
	}

	public MemberDTO idSearch(MemberDTO mem) {
		System.out.println("LoginService.idSearch");
		return memberDao.idSearch(mem);
	}

	public MemberDTO pwSearch(MemberDTO mem) {
		System.out.println("LoginService.pwSearch");
		return memberDao.pwSearch(mem);
	}
}
