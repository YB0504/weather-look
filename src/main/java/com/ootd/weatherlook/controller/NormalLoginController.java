package com.ootd.weatherlook.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ootd.weatherlook.model.MemberDTO;
import com.ootd.weatherlook.service.NormalLogService;

@Controller
public class NormalLoginController {

	@Autowired
	private NormalLogService service;
	
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
	
	// ID 중복체크
	@RequestMapping(value = "idcheck", method = RequestMethod.POST)
	public String idcheck(@RequestParam("memid") String id, Model model)throws Exception {
		System.out.println("id : " + id);
		
		int result = service.idcheck(id);
		model.addAttribute("result", result);
		
		return "member/idcheckresult";
	}
	
	// NICKNAME 중복체크
	@RequestMapping(value = "nickcheck", method = RequestMethod.POST)
	public String nickcheck(@RequestParam("memnick") String nick, Model model)throws Exception {
		System.out.println("nick : " + nick);
		
		int result = service.nickcheck(nick);
		model.addAttribute("result", result);
		
		return "member/nickresult";
	}
	
	// 회원 가입
	@RequestMapping(value = "memberinsert", method = RequestMethod.POST)
	public String memberinsert(@RequestParam("profile_image") MultipartFile mf,
															  MemberDTO member,
															  Model model,
															  HttpServletRequest request)throws Exception {
		
		return "redirect:member/loginform";
	}
	

}
