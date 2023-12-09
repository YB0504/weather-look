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

import com.ootd.weatherlook.model.CommReReportDTO;
import com.ootd.weatherlook.model.CommReportDTO;
import com.ootd.weatherlook.model.Community;
import com.ootd.weatherlook.model.CommunityRe;
import com.ootd.weatherlook.service.CommReService;
import com.ootd.weatherlook.service.CommService;

@Controller
@RequiredArgsConstructor
public class CommReController {

	private final CommService cs;

	private final CommReService crs;

	@RequestMapping("crlist")
	public String slist(int post_id, Model model) {
		System.out.println("CommReController.slist");

		Community comm = cs.getCommunity(post_id);
		List<CommunityRe> crlist = crs.list(post_id);
		model.addAttribute("crlist", crlist);
		model.addAttribute("comm", comm);

		return "comm/crlist";
	}

	@RequestMapping("crInsert")
	public String sInsert(CommunityRe cr) {
		System.out.println("CommReController.sInsert");

		crs.insert(cr);

		return "redirect:crlist?post_id=" + cr.getPost_id();
	}

	@RequestMapping("repDelete")
	public String delete(CommunityRe cr) {
		System.out.println("CommReController.delete");

		crs.delete(cr.getRe_id());

		return "redirect:crlist?post_id=" + cr.getPost_id();
	}

	@RequestMapping("repUpdate")
	public String repUpdate(CommunityRe cr) {
		System.out.println("CommReController.repUpdate");

		CommunityRe commre = crs.getReply(cr.getRe_id());
		commre.setRe_content(cr.getRe_content());
		crs.update(commre);

		return "redirect:crlist?post_id=" + cr.getPost_id();
	}

	@RequestMapping("commReplyInsert")
	public String commReplyInsert(CommunityRe cr, HttpSession session) {
		System.out.println("CommReController.commReplyInsert");

		cr.setNick((String) session.getAttribute("nick"));
		crs.commReplyInsert(cr);

		return "redirect:crlist?post_id=" + cr.getPost_id();
	}

	@RequestMapping("commReReport")
	public String commReReport(int re_id, Model model) {
		System.out.println("CommReController.commReReport");

		model.addAttribute("re_id", re_id);

		return "comm/commReReport";
	}

	@RequestMapping("commReReportIn")
	public String commReReportIn(@ModelAttribute CommReReportDTO commReReport, @RequestParam("re_id") int re_id, Model model){
		System.out.println("CommReController.commReReportIn");

		commReReport.setRe_id(re_id);
		crs.reReportInsert(commReReport);
		model.addAttribute("report", "댓글에 대한 신고가 완료 되었습니다.");

		return "comm/commReReport";
	}
}