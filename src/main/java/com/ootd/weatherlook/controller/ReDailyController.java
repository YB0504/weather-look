package com.ootd.weatherlook.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
public class ReDailyController {

	@Autowired
	private DailyService service;

	@Autowired
	private ReDailyService rds;

	// 댓글 목록을 구해오는 요청
	@RequestMapping("rdlist")
	public String slist(int post_id, Model model) {
		Daily daily = service.getDaily(post_id); // 부모글에 대한 상세정보
		List<ReDaily> rdlist = rds.list(post_id); // 댓글 목록(새로추가된 댓글도포함)
		System.out.println("rdlist:" + rdlist);

		model.addAttribute("rdlist", rdlist); // 모델에 저장 foreach태그루프돌림
		model.addAttribute("daily", daily);
		return "daily/rdlist";
	}

	// 댓글 저장
	@RequestMapping("rdInsert")
	public String sInsert(ReDaily rb, Model model) {
		System.out.println("댓글 작성");
		rds.insert(rb);
		return "redirect:rdlist?post_id=" + rb.getPost_id(); // 부모글 번호를 get방식으로 전달
	}

	// 댓글 삭제
	@RequestMapping("rdDelete")
	public String sDelete(ReDaily rb, Model model) {
		rds.delete(rb.getRe_id());
		return "redirect:rdlist?post_id=" + rb.getPost_id();
	}

	// 댓글 수정
	@RequestMapping("rdUpdate")
	public String sUpdate(ReDaily rb, Model model) {
		rds.update(rb);
		return "redirect:rdlist?post_id=" + rb.getPost_id();
	}

	// 대댓글 작성
	@RequestMapping("dailyReplyInsert")
	public String dailyReplyInsert(ReDaily rb, HttpSession session, HttpServletRequest request, Model model)
			throws Exception {
		System.out.println("대댓글 작성 완료");

		System.out.println("re_level" + rb.getRe_level());
		System.out.println("re_step" + rb.getRe_step());

		String nick = (String) session.getAttribute("nick");
		rb.setNick(nick);

		rds.dailyReplyInsert(rb);

		return "redirect:rdlist?post_id=" + rb.getPost_id();
	}

	@RequestMapping("dailyReReport")
	public String dailyReReport(int re_id, Model model) throws Exception {
		System.out.println("댓글 신고하기 폼");

		model.addAttribute("re_id", re_id);
		return "daily/dailyReReport";
	}

	@RequestMapping("dailyReReportIn")
	public String dailyReReportIn(@ModelAttribute DailyReReportDTO dailyReReport, @RequestParam("re_id") int re_id,
			Model model) throws Exception {

		System.out.println("reportInsert");

		dailyReReport.setRe_id(re_id);

		rds.reReportInsert(dailyReReport);

		System.out.println("신고 완료");

		model.addAttribute("report", "댓글에 대한 신고가 완료 되었습니다.");

		return "daily/dailyReReport";

	}

}
