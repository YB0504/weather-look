package com.ootd.weatherlook.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ootd.weatherlook.model.DateFormat;
import com.ootd.weatherlook.model.MainBoard;
import com.ootd.weatherlook.model.Search;
import com.ootd.weatherlook.model.SearchResult;
import com.ootd.weatherlook.model.Weather;
import com.ootd.weatherlook.service.MainServiceImpl;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final MainServiceImpl mainpageservice;

	@RequestMapping("/")
	public String root() {
		System.out.println("MainController.root");
		return "redirect:main";
	}

	@RequestMapping("main")
	public String mainpage(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		System.out.println("MainController.mainpage");

		//공통설정
		int numberset = 9;
		int pageset = 10;
		int startrow = 1 + (page - 1) * numberset;
		int endrow = page * numberset;

		List<MainBoard> mainlist;
		Search search = new Search();
		search.setNumberset(numberset);
		search.setStartrow(startrow);
		search.setEndrow(endrow);
		search.setPage(page);

		//메인보드 출력물 계산
		int listcount = mainpageservice.getMainCount(search);
		int maxpage = listcount / numberset + ((listcount % numberset == 0) ? 0 : 1);
		int startpage = ((page - 1) / pageset) * pageset + 1;
		int endpage = startpage + pageset - 1;
		if (endpage > maxpage) endpage = maxpage;
		mainlist = mainpageservice.getMainList(search);

		//날짜 포맷 설정
		List<DateFormat> dates = new ArrayList<DateFormat>();
		LocalDate today = LocalDate.now();

		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM/dd");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("EEE");

		int weatherforecastdays = 4;
		for (int i = 0; i < weatherforecastdays; i++) {

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
		model.addAttribute("mainlist", mainlist);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("listcount", listcount);
		model.addAttribute("page", page);
		model.addAttribute("maxpage", maxpage);

		return "main/mainpage";
	}

	@RequestMapping("search")
	public String searchpage(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {
		System.out.println("MainController.searchpage");

		int numberset = 10;
		int pageset = 10;
		int startrow = 1 + (page - 1) * numberset;
		int endrow = page * numberset;

		List<SearchResult> searchresult;

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
		if (endpage > maxpage) endpage = maxpage;

		searchresult = mainpageservice.getSearchList(search);
		model.addAttribute("searchresult", searchresult);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("listcount", listcount);
		model.addAttribute("keyword", keyword);

		return "main/searchpage";
	}


	@RequestMapping("detail")
	public String detail(HttpServletRequest request) {
		System.out.println("MainController.detail");

		int page = 1;
		String type_name = request.getParameter("type_name");
		String post_id = request.getParameter("post_id");

		if (type_name.equals("daily") || type_name.equals("daily_reply")) {
			return "redirect:dailycontent?post_id=" + post_id + "&page=" + page;
		}

		if (type_name.equals("review") || type_name.equals("review_reply")) {
			return "redirect:reviewDetail?post_id=" + post_id + "&page=" + page;
		}

		if (type_name.equals("community") || type_name.equals("community_reply")) {
			return "redirect:commcontent?post_id=" + post_id + "&page=" + page;
		}

		return "redirect:main";
	}
}
