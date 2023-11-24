package com.ootd.weatherlook.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ootd.weatherlook.model.Daily;
import com.ootd.weatherlook.service.MainpageServiceImpl;

@Controller
public class MainpageController {

	@Autowired
	private MainpageServiceImpl mainpageservice;
	
	
	@RequestMapping("/test")
	public String test() {
		
		System.out.println("test컨트롤러 통과");
		
		return "redirect:main2";
	}
	
	@RequestMapping("main")
	public String mainpage() {
		System.out.println("mainpage 컨트롤러 통과");
		
		return "mainpage";
		
	}
	
	@RequestMapping("main2")
	public String main2(Model model) {
		System.out.println("데일리리스트 표시 테스트");
		
		Daily daily = new Daily();
		
		List<Daily> dailylist = new ArrayList<Daily>();
		
		try {
			dailylist = mainpageservice.getDailyList(daily);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("dailylist", dailylist);
		
		return "mainpage";
	}
	
	
}
