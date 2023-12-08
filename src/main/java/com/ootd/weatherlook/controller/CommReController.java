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

import com.ootd.weatherlook.model.CommReReportDTO;
import com.ootd.weatherlook.model.CommReportDTO;
import com.ootd.weatherlook.model.Community;
import com.ootd.weatherlook.model.CommunityRe;
import com.ootd.weatherlook.service.CommReService;
import com.ootd.weatherlook.service.CommService;

@Controller
public class CommReController {

	@Autowired
	private CommService cs;

	@Autowired
	private CommReService crs;
	
	@RequestMapping("crlist")
	public String slist(int post_id, Model model) {
		Community comm = cs.getCommunity(post_id);
		List<CommunityRe> crlist = crs.list(post_id);
		System.out.println("crlist : " + crlist);
		
		model.addAttribute("crlist", crlist);
		model.addAttribute("comm", comm);
		return "comm/crlist";
		
	}
	
	@RequestMapping("crInsert")
	public String sInsert(CommunityRe cr, Model model,
							HttpSession session) {
		crs.insert(cr);
		System.out.println("crInsert");
		return "redirect:crlist?post_id="+cr.getPost_id();
	}
	
	@RequestMapping("repDelete")
	public String delete(CommunityRe cr, Model model) {
		crs.delete(cr.getRe_id());
		return "redirect:crlist?post_id=" + cr.getPost_id();
	}

	@RequestMapping("repUpdate")
	public String repUpdate(CommunityRe cr, Model model) {
		crs.update(cr);
		System.out.println("repUpdate : ");
		return "redirect:crlist?post_id="+ cr.getPost_id();
	}
	
	// 대댓글 작성
	@RequestMapping("commReplyInsert")
    public String commReplyInsert(CommunityRe cr, HttpSession session, 
            HttpServletRequest request, Model model) throws Exception {
        System.out.println("대댓글 작성 완료");
        
        System.out.println("re_level" +cr.getRe_level());
        System.out.println("re_step" +cr.getRe_step());

        String nick = (String) session.getAttribute("nick");
        cr.setNick(nick);

        crs.commReplyInsert(cr);

        return "redirect:crlist?post_id=" + cr.getPost_id();
    }
	
	@RequestMapping("commReReport")
	public String commReReport(int re_id, Model model) throws Exception {
		System.out.println("댓글 신고하기 폼");

		model.addAttribute("re_id", re_id);
		return "comm/commReReport";
	}

	@RequestMapping("commReReportIn")
	public String commReReportIn(@ModelAttribute CommReReportDTO commReReport,
	                            @RequestParam("re_id") int re_id, Model model) throws Exception {

		System.out.println("reportInsert");

		commReReport.setRe_id(re_id);

		crs.reReportInsert(commReReport);

		System.out.println("신고 완료");

		model.addAttribute("report", "댓글에 대한 신고가 완료 되었습니다.");

		return "comm/commReReport";

	}
	
}