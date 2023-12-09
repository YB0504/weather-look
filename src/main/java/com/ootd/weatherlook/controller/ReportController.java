package com.ootd.weatherlook.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ReportController {

	private final ReportServiceImpl reportservice;

	@RequestMapping("reportedpost")
	public String reportPostPage(HttpServletRequest request, Model model) {
		System.out.println("ReportController.reportPostPage");

		int page = 1;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int numberset = 10;
		int pageset = 10;
		int startrow = 1 + (page - 1) * numberset;
		int endrow = page * numberset;

		List<Report> reportlist = new ArrayList<Report>();

		Search search = new Search();
		search.setNumberset(numberset);
		search.setStartrow(startrow);
		search.setEndrow(endrow);
		search.setPage(page);

		int listcount = reportservice.getReportCount(search);

		int maxpage = listcount / numberset + ((listcount % numberset == 0) ? 0 : 1);
		int startpage = ((page - 1) / pageset) * numberset + 1;
		int endpage = startpage + pageset - 1;
		if (endpage > maxpage) endpage = maxpage;


		reportlist = reportservice.getReportList(search);
		System.out.println("reportlist : " + reportlist);
		model.addAttribute("reportlist", reportlist);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("listcount", listcount);
		model.addAttribute("page", page);

		return "main/reportpostpage";
	}

	@RequestMapping("reportedreply")
	public String reportReplyPage(HttpServletRequest request, Model model) {
		System.out.println("ReportController.reportReplyPage");

		int page = 1;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int numberset = 10;
		int pageset = 10;
		int startrow = 1 + (page - 1) * numberset;
		int endrow = page * numberset;

		List<Report> reportlist = new ArrayList<Report>();

		Search search = new Search();
		search.setNumberset(numberset);
		search.setStartrow(startrow);
		search.setEndrow(endrow);
		search.setPage(page);

		int listcount = reportservice.getReportedReplyCount(search);

		int maxpage = listcount / numberset + ((listcount % numberset == 0) ? 0 : 1);
		int startpage = ((page - 1) / pageset) * numberset + 1;
		int endpage = startpage + pageset - 1;
		if (endpage > maxpage) endpage = maxpage;


		reportlist = reportservice.getReportedReplyList(search);
		model.addAttribute("reportlist", reportlist);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("listcount", listcount);
		model.addAttribute("page", page);

		return "main/reportreplypage";
	}

	@RequestMapping("remove")
	public String remove(HttpServletRequest request, Model model) {
		System.out.println("ReportController.remove");

		String type_name = "";
		int post_id = -1;
		int re_id = -1;
		int page = -1;

		if (request.getParameter("type_name") != null) {
			type_name = request.getParameter("type_name");
		}

		if (request.getParameter("post_id") != null) {
			post_id = Integer.parseInt(request.getParameter("post_id"));
		}
		if (request.getParameter("re_id") != null) {
			re_id = Integer.parseInt(request.getParameter("re_id"));
		}

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		System.out.println("삭제대상 - type_name : " + type_name);
		System.out.println("삭제대상 - post_id : " + post_id);
		System.out.println("삭제대상 - re_id : " + re_id);
		System.out.println("리턴대상 - page : " + page);

		model.addAttribute("page", page);

		if (type_name.equals("daily")) {
			reportservice.removeDaily(post_id);
			System.out.println("[delete] table : " + type_name + ", post_id : " + post_id);
			return "redirect:reportedpost";
		}
		if (type_name.equals("review")) {
			reportservice.removeReview(post_id);
			System.out.println("[delete] table : " + type_name + ", post_id : " + post_id);
			return "redirect:reportedpost";
		}
		if (type_name.equals("community")) {
			reportservice.removeCommunity(post_id);
			System.out.println("[delete] table : " + type_name + ", post_id : " + post_id);
			return "redirect:reportedpost";
		}

		if (type_name.equals("daily_reply")) {
			reportservice.removeDailyReply(re_id);
			System.out.println("[delete] table : " + type_name + ", re_id : " + re_id);
			return "redirect:reportedreply";
		}
		if (type_name.equals("review_reply")) {
			reportservice.removeReviewReply(re_id);
			System.out.println("[delete] table : " + type_name + ", re_id : " + re_id);
			return "redirect:reportedreply";
		}
		if (type_name.equals("community_reply")) {
			reportservice.removeCommunityReply(re_id);
			System.out.println("[delete] table : " + type_name + ", re_id : " + re_id);
			return "redirect:reportedreply";
		}

		return "redirect:report";
	}
}
