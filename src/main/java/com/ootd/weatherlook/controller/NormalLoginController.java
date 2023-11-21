package com.ootd.weatherlook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ootd.weatherlook.service.NormalLogService;

@Controller
public class NormalLoginController {

	@Autowired
	private NormalLogService service;
	
//	@RequestMapping("/")
//	public String test() {
//		System.out.println("되니ㅏ?");
//		return "member/loginform";
//	}

	@RequestMapping("loginform")
	public String loginform() {
		System.out.println("로그인 폼");
		return "member/loginform";
	}
	
	@RequestMapping("memberjoin")
	public String memberjoin() {
		System.out.println("회원 가입 폼");
		return "member/memberjoin";
	}
}
