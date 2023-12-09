package com.ootd.weatherlook.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ootd.weatherlook.model.Daily;
import com.ootd.weatherlook.model.DailyReReportDTO;
import com.ootd.weatherlook.model.ReDaily;
import com.ootd.weatherlook.service.DailyService;
import com.ootd.weatherlook.service.ReDailyService;

@Controller
@RequiredArgsConstructor
public class ReDailyController {

	private final DailyService service;

	private final ReDailyService rds;

	// 댓글 목록을 구해오는 요청
	@RequestMapping("rdlist")
	public String slist(int post_id, Model model) {
		System.out.println("ReDailyController.slist");

		Daily daily = service.getDaily(post_id); // 부모글에 대한 상세정보
		List<ReDaily> rdlist = rds.list(post_id); // 댓글 목록(새로추가된 댓글도포함)
		model.addAttribute("rdlist", rdlist);
		model.addAttribute("daily", daily);

		return "daily/rdlist";
	}

	@RequestMapping("rdInsert")
	public String sInsert(ReDaily rb) {
		System.out.println("ReDailyController.sInsert");

		rds.insert(rb);

		return "redirect:rdlist?post_id=" + rb.getPost_id();
	}

	// 댓글 삭제
	@RequestMapping("rdDelete")
	public String sDelete(ReDaily rb) {
		System.out.println("ReDailyController.sDelete");

		rds.delete(rb.getRe_id());

		return "redirect:rdlist?post_id=" + rb.getPost_id();
	}

	@RequestMapping("rdUpdate")
	public String sUpdate(ReDaily rb) {
		System.out.println("ReDailyController.sUpdate");

		rds.update(rb);

		return "redirect:rdlist?post_id=" + rb.getPost_id();
	}

	@RequestMapping("dailyReplyInsert")
	public String dailyReplyInsert(ReDaily rb, HttpSession session) {
		System.out.println("ReDailyController.dailyReplyInsert");

		String nick = (String) session.getAttribute("nick");
		rb.setNick(nick);
		rds.dailyReplyInsert(rb);

		return "redirect:rdlist?post_id=" + rb.getPost_id();
	}

	@RequestMapping("dailyReReport")
	public String dailyReReport(int re_id, Model model) {
		System.out.println("ReDailyController.dailyReReport");

		model.addAttribute("re_id", re_id);

		return "daily/dailyReReport";
	}

	@RequestMapping("dailyReReportIn")
	public String dailyReReportIn(@ModelAttribute DailyReReportDTO dailyReReport, @RequestParam("re_id") int re_id, Model model) {
		System.out.println("ReDailyController.dailyReReportIn");

		dailyReReport.setRe_id(re_id);
		rds.reReportInsert(dailyReReport);
		model.addAttribute("report", "댓글에 대한 신고가 완료 되었습니다.");

		return "daily/dailyReReport";
	}

}
