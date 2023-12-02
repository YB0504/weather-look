package com.ootd.weatherlook.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ootd.weatherlook.model.MainBoard;
import com.ootd.weatherlook.model.Report;
import com.ootd.weatherlook.model.Search;
import com.ootd.weatherlook.model.SearchResult;
import com.ootd.weatherlook.model.Weather;
import com.ootd.weatherlook.service.MainServiceImpl;

@Controller
public class SearchController {

	@Autowired
	private MainServiceImpl mainpageservice;


	@RequestMapping("search")
	public String searchpage(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {
		// 트래킹 ->
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[1];
		System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
		// <- 트래킹
		

		
		int numberset = 10;
		int pageset = 10;
		int startrow = 1 + (page - 1) * numberset;
		int endrow = page*numberset;

		List<SearchResult> searchresult = new ArrayList<SearchResult>();

		Search search = new Search();
		search.setNumberset(numberset);
		search.setStartrow(startrow);
		search.setEndrow(endrow);
		search.setPage(page);
		search.setKeyword(keyword);
		
		int listcount = mainpageservice.getSearchCount(search);
		
		int maxpage = listcount / numberset + ((listcount % numberset == 0) ? 0 : 1);
		int startpage = ((page - 1) / pageset) * numberset + 1; 
		int endpage = startpage + pageset - 1; 
		if (endpage > maxpage)
			endpage = maxpage;


		

		searchresult = mainpageservice.getSearchList(search);
		System.out.println("searchresult : " + searchresult);
		model.addAttribute("searchresult", searchresult);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("listcount", listcount);
		model.addAttribute("keyword", keyword);
		
		return "main/searchpage";
	}
	
	

	@RequestMapping("detail")
	public String detail(HttpServletRequest request, Model model) {
		
		// 트래킹 ->
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[1];
		System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
		// <- 트래킹
		
		String type_name = request.getParameter("type_name");
		String post_id = request.getParameter("post_id");
		System.out.println("type_name : " + type_name);
		System.out.println("post_id : " + post_id);
		
		if(type_name.equals("daily"))
			System.out.println("daily 게시판 실행");
		if(type_name.equals("review"))
			System.out.println("review 게시판 실행");
		if(type_name.equals("community"))
			System.out.println("community 게시판 실행");
		
		
		return "";
	}
	
}