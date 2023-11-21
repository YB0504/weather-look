package com.ootd.weatherlook.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ootd.weatherlook.model.Main;
import com.ootd.weatherlook.service.MainServiceImpl;

@Controller
public class MainController {

	@Autowired
	private MainServiceImpl mainpageservice;

	@RequestMapping("/")
	public String test() {

		// 트래킹 ->
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[1];
		System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
		// <- 트래킹

		return "redirect:main";
	}

	@RequestMapping("main")
	public String mainpage(Model model) {

		// 트래킹 ->
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[1];
		System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
		// <- 트래킹
		
		int page = 1;
		int numberSet = 9;
//		int startRow = 1 + (page - 1) * numberSet;
//		int endRow = page * numberSet;
		Map<String, Integer> rangeSet = new HashMap<String, Integer>();
		rangeSet.put("page", page);
		rangeSet.put("numberSet", numberSet);
		
		Main main = new Main();
		List<Main> recentlist = new ArrayList<Main>();
		
		try {
			recentlist = mainpageservice.getRecentList(rangeSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("recentlist : " +recentlist);
		model.addAttribute("recentlist", recentlist);

		return "main/mainpage";

	}

	@RequestMapping("search")
	public String searchpage() {
		// 트래킹 ->
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[1];
		System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
		// <- 트래킹
		
		
		return "main/searchpage";
	}

}
