package com.ootd.weatherlook.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ootd.weatherlook.model.DateFormat;
import com.ootd.weatherlook.model.MainBoard;
import com.ootd.weatherlook.model.Search;
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
	public String mainpage(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {

		// 트래킹 ->
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[1];
		System.out.println("[경로 추적] : " + caller.getClassName() + "." + caller.getMethodName());
		// <- 트래킹

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

		int[] test = { 1, 5, 7, 14 };

		// 날짜 ============================>
		List<DateFormat> dates = new ArrayList<DateFormat>();
		// 현재 날짜를 얻기 위해 java.time.LocalDate 클래스 사용
		LocalDate today = LocalDate.now();

		// DateTimeFormatter을 사용하여 날짜를 원하는 형식으로 포맷팅
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM/dd");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("EEE");

		// 각각의 형식으로 현재 날짜 출력

		for (int i = 0; i < 4; i++) {

			today = today.plusDays(1);

			String df1 = today.format(formatter1);
			String df2 = today.format(formatter2);
			String df3 = today.format(formatter3);

			DateFormat dateformat = new DateFormat();
			dateformat.setDf1(df1);
			dateformat.setDf2(df2);
			dateformat.setDf3(df3);
			dates.add(dateformat);
		}
		model.addAttribute("dates", dates);
		// <============================ 날짜


		// =============================

		System.out.println("mainlist : " + mainlist);
		model.addAttribute("mainlist", mainlist);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("listcount", listcount);
		model.addAttribute("page", page);
		model.addAttribute("maxpage", maxpage);
		
		
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
