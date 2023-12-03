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

	@RequestMapping("main")
	public String main(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(name = "highest", required = false) Double highest,
			@RequestParam(name = "lowest", required = false) Double lowest, Model model) {

		if (highest != null && lowest != null) {

			model.addAttribute("highest", highest);
			model.addAttribute("lowest", lowest);

			return "redirect:weather?page=" + page;
		}

		return "redirect:recent?page=" + page;
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
		int endrow = page * numberset;
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

		// ============= 날씨 리스트 삽입 테스트

		List<Weather> weekly = new ArrayList<Weather>();

		double d = (Math.random() * 10);

		int[] test = {1, 5, 7, 14};
		
		for (int i = 0; i < 8; i++) {
			Weather weather = new Weather();
			weather.setHighest(Math.round(((Math.random() * 10) + 10) * 10.0) / 10.0);
			weather.setLowest(Math.round((Math.random() * 10) * 10.0) / 10.0);
			weather.setIco(test[(int)(Math.random()*4)]);
			weekly.add(i, weather);
		}
		System.out.println("weekly: " + weekly);
		model.addAttribute("weekly", weekly);

		// =============================

		System.out.println("mainlist : " + mainlist);
		model.addAttribute("mainlist", mainlist);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("listcount", listcount);

		return "main/mainpage";

	}

	@RequestMapping("weather")
	public String weather(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam("highest") Double highest, @RequestParam("lowest") Double lowest, Model model) {

		// 트래킹 ->
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[1];
		System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
		// <- 트래킹

		model.addAttribute("highest", highest);
		model.addAttribute("lowest", lowest);

		Weather weather = new Weather();
		Double average = (highest + lowest) / 2;

		weather.setHighest(highest);
		weather.setLowest(lowest);
		weather.setTemperature(average);
		weather.setDeviation(5);

		System.out.println("highest : " + highest);
		System.out.println("lowest : " + lowest);
		System.out.println("average : " + average);

		// 공통 설정 ->
		int numberset = 9;
		int pageset = 10;
		int startrow = 1 + (page - 1) * numberset;
		int endrow = page * numberset;
		List<MainBoard> mainlist = new ArrayList<MainBoard>();
		Search search = new Search();
		search.setNumberset(numberset);
		search.setStartrow(startrow);
		search.setEndrow(endrow);
		search.setPage(page);
		// <- 공통 설정

		search.setWeather(weather);
		int listcount = mainpageservice.getweatherCount(search);

		// 메인보드 출력물 갯수 연산 ->
		int maxpage = listcount / numberset + ((listcount % numberset == 0) ? 0 : 1);
		int startpage = ((page - 1) / pageset) * numberset + 1;
		int endpage = startpage + pageset - 1;
		if (endpage > maxpage)
			endpage = maxpage;
		// <- 메인보드 출력물 갯수 연산
		mainlist = mainpageservice.getWeatherList(search);
		System.out.println("mainlist: " + mainlist);

		model.addAttribute("mainlist", mainlist);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("listcount", listcount);

		return "main/mainpage";
	}

}
