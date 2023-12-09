package com.ootd.weatherlook.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.MemberDTO;

@Repository
@RequiredArgsConstructor
public class LoginDao {

	private final SqlSession session;

	public int idcheck(String id) {
		System.out.println("LoginDao.idcheck");

		// 가능 ID: -1, 불가능 ID: 1
		int re = -1;
		MemberDTO dto = session.selectOne("member.idcheck", id);
		if (dto != null) re = 1;
		
		return re;
	}

	public int nickcheck(String nick) {
		System.out.println("LoginDao.nickcheck");

		// 가능 nick: -1, 불가능 nick: 1
		int re = -1;
		MemberDTO dto = session.selectOne("member.nickcheck", nick);
		if (dto != null) re = 1;
		
		return re;
	}

	public void insertMember(MemberDTO member) {
		System.out.println("LoginDao.insertMember");
		session.insert("member.insertmember", member);
	}

	public MemberDTO login(String id) {
		System.out.println("LoginDao.login");
		return session.selectOne("member.login", id);
	}

	public MemberDTO kakaoLoginCheck(String nick) {
		System.out.println("LoginDao.kakaoLoginCheck");
		return session.selectOne("member.kakaoLogin", nick);
	}

	public int kakaologin(MemberDTO kakao) {
		System.out.println("LoginDao.kakaologin");
		return session.insert("member.insertKakao", kakao);
	}

	public MemberDTO idSearch(MemberDTO mem) {
		System.out.println("LoginDao.idSearch");
		return session.selectOne("member.idSearch", mem);
	}

	public MemberDTO pwSearch(MemberDTO mem) {
		System.out.println("LoginDao.pwSearch");
		return session.selectOne("member.pwSearch", mem);
	}
}
