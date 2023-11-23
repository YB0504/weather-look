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
	
	@RequestMapping("apitest")
	public String apitest() {
		
		return "apitest";
	}

	@RequestMapping("main")
	public String main(@RequestParam(value = "page", defaultValue = "1") int page, HttpServletRequest request, Model model) {
		
		String temperature = request.getParameter("temperature");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		
		if(request.getParameter("temperature") != null
				&& request.getParameter("latitude") != null
				&& request.getParameter("longitude") != null) {
			
			return "redirect:weather?temperature="+temperature
					+"?latitude="+latitude
					+"?longitude="+longitude
					+"?page="+page;
		}
		
		return "redirect:recent?page="+page;
	}
	
	
	
	// 쇼핑후기 + 데일리 게시판 최신순 출력
	@RequestMapping("recent")
	public String mainpage(HttpServletRequest request, Model model) {

		// 트래킹 ->
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[1];
		System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
		// <- 트래킹

		// 공통 설정 ->
		int page = Integer.parseInt(request.getParameter("page"));
		int numberset = 9;
		int pageset = 10;
		int startrow = 1 + (page - 1) * numberset;
		int endrow = page*numberset;
		List<MainBoard> mainlist = new ArrayList<MainBoard>();
		Search search = new Search();
		search.setNumberset(numberset);
		search.setStartrow(startrow);
		search.setEndrow(endrow);
		search.setPage(page);
		// <- 공통 설정
		
		int listcount = mainpageservice.getMainCount(search);
		
		// 메인보드 출력물 갯수 연산 ->
		int maxpage = listcount / numberset + ((listcount % numberset == 0) ? 0 : 1);
		int startpage = ((page - 1) / pageset) * numberset + 1; 
		int endpage = startpage + pageset - 1; 
		if (endpage > maxpage)
			endpage = maxpage;
		// <- 메인보드 출력물 갯수 연산
		mainlist = mainpageservice.getMainList(search);

		System.out.println("mainlist : " + mainlist);
		model.addAttribute("mainlist", mainlist);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("listcount", listcount);

		return "main/mainpage";

	}
	// @requsetparam써서 조건별로
	@RequestMapping("weather")
	public String weathertest(HttpServletRequest request, Model model) {

		// 트래킹 ->
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[1];
		System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
		// <- 트래킹

		// 공통 설정 ->
		int page = Integer.parseInt(request.getParameter("page"));
		int numberset = 9;
		int pageset = 10;
		int startrow = 1 + (page - 1) * numberset;
		int endrow = page*numberset;
		List<MainBoard> mainlist = new ArrayList<MainBoard>();
		Search search = new Search();
		search.setNumberset(numberset);
		search.setStartrow(startrow);
		search.setEndrow(endrow);
		search.setPage(page);
		// <- 공통 설정

		double temperature = Double.parseDouble(request.getParameter("temperature"));
		double latitude = Double.parseDouble(request.getParameter("latitude"));
		double longitude = Double.parseDouble(request.getParameter("longitude"));
		
		Weather weather = new Weather();
		
		weather.setTemperature(temperature);
		weather.setLatitude(latitude);
		weather.setLongitude(longitude);

		search.setWeather(weather);
		
		
		
		
		int listcount = mainpageservice.getMainCount(search);
		
		// 메인보드 출력물 갯수 연산 ->
		int maxpage = listcount / numberset + ((listcount % numberset == 0) ? 0 : 1);
		int startpage = ((page - 1) / pageset) * numberset + 1; 
		int endpage = startpage + pageset - 1; 
		if (endpage > maxpage)
			endpage = maxpage;
		// <- 메인보드 출력물 갯수 연산

		mainlist = mainpageservice.getWeatherList(search);

		// view page 전송 정보 ->
		System.out.println("mainlist : " + mainlist);
		model.addAttribute("mainlist", mainlist);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("listcount", listcount);
		// <- view page 전송 정보
		
		return "main/mainpage";

	}
	
	// 검색페이지--------------------

	@RequestMapping("search")
	public String searchpage(HttpServletRequest request, Model model) {
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

		List<SearchResult> searchresult = new ArrayList<SearchResult>();

		Search search = new Search();
		search.setNumberset(numberset);
		search.setStartrow(startrow);
		search.setEndrow(endrow);
		search.setPage(page);
		
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
		
		
		return "main/searchpage";
	}
	
	// 관리자 페이지 -----------------------------------
	
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
		
		
		return "main/reportpage";
	}

	

}
