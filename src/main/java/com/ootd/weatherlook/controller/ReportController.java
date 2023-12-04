package com.ootd.weatherlook.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

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
import com.ootd.weatherlook.service.ReportServiceImpl;

@Controller
public class ReportController {

	@Autowired
	private MainServiceImpl mainpageservice;
	
	@Autowired
	private ReportServiceImpl reportservice;
	
	@RequestMapping("report")
	public String reportPage(HttpServletRequest request, Model model) {
		// 트래킹 ->
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[1];
		System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
		// <- 트래킹


		int page = 1;
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int numberset = 10;
		int pageset = 10;
		int startrow = 1 + (page - 1) * numberset;
		int endrow = page*numberset;

		List<Report> reportlist = new ArrayList<Report>();

		Search search = new Search();
		search.setNumberset(numberset);
		search.setStartrow(startrow);
		search.setEndrow(endrow);
		search.setPage(page);
		
		int listcount = mainpageservice.getReportCount(search);
		
		int maxpage = listcount / numberset + ((listcount % numberset == 0) ? 0 : 1);
		int startpage = ((page - 1) / pageset) * numberset + 1; 
		int endpage = startpage + pageset - 1; 
		if (endpage > maxpage)
			endpage = maxpage;
		

		reportlist = mainpageservice.getReportList(search);
		System.out.println("reportlist : " + reportlist);
		model.addAttribute("reportlist", reportlist);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("listcount", listcount);
		model.addAttribute("page", page);
		
		
		return "main/reportpage";
	}

	@RequestMapping("remove")
	public String remove(HttpServletRequest request, Model model) {
		
		// 트래킹 ->
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[1];
		System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
		// <- 트래킹
		
		String type_name = "";
		int post_id = -1;
		int page = -1;
		
		if (request.getParameter("type_name") != null) {
			type_name = request.getParameter("type_name");
		}
		
		if (request.getParameter("post_id") != null) {
			post_id = Integer.parseInt(request.getParameter("post_id"));
		}
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		System.out.println("삭제대상 - type_name : " + type_name);
		System.out.println("삭제대상 - post_id : " + post_id);
		System.out.println("리턴대상 - page : " + page);
		
		if(type_name.equals("daily")) {
			reportservice.removeDaily(post_id);
			System.out.println("[delete] table : " + type_name + ", post_id : " + post_id);
		}
		if(type_name.equals("review")) {
			reportservice.removeReview(post_id);
			System.out.println("[delete] table : " + type_name + ", post_id : " + post_id);
		}
		if(type_name.equals("community")) {
			reportservice.removeCommunity(post_id);
			System.out.println("[delete] table : " + type_name + ", post_id : " + post_id);
		}
		model.addAttribute("page", page);
		
		return "redirect:report?page="+page;
	}
	


	
	
}
