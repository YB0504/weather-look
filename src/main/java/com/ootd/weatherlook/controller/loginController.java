package com.ootd.weatherlook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ootd.weatherlook.service.loginService;

@Controller
public class loginController {

	@Autowired
	private loginService lgs;
	
	@RequestMapping("loginform")
	public String loginform() {
		return "comm/loginform";
	}
}
