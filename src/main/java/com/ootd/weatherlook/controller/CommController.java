package com.ootd.weatherlook.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ootd.weatherlook.model.CommReportDTO;
import com.ootd.weatherlook.model.Community;
import com.ootd.weatherlook.model.CommunityLike;
import com.ootd.weatherlook.model.CommunityScrap;
import com.ootd.weatherlook.service.CommLikeService;
import com.ootd.weatherlook.service.CommScrapService;
import com.ootd.weatherlook.service.CommService;

@Controller
@RequiredArgsConstructor
public class CommController {

	private final CommService cs;

	private final CommLikeService cls;

	private final CommScrapService css;

	@RequestMapping("commform")
	public String commform() {
		System.out.println("CommController.commform");
		return "comm/commform";
	}

	@RequestMapping("commwrite")
	public String commwrite(@ModelAttribute Community comm, Model model) {
		System.out.println("CommController.commwrite");
		int result = cs.commInsert(comm);
		if (result == 1)
			System.out.println("글 작성 성공");

		model.addAttribute("result", result);
		return "comm/insertresult";
	}

	@RequestMapping("commlist")
	public String commlist(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		System.out.println("CommController.commlist");

		int limit = 10;
		int listCount = cs.getCommCount();

		List<Community> commList = cs.getCommList(page);

		int pageCount = listCount / limit + ((listCount % 10 == 0) ? 0 : 1);
		int startPage = ((page - 1) / 10) * limit + 1;
		int endPage = startPage + 10 - 1;

		if (endPage > pageCount) endPage = pageCount;

		model.addAttribute("page", page);
		model.addAttribute("listCount", listCount);
		model.addAttribute("commList", commList);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		return "comm/commlist";
	}

	@RequestMapping("commcontent")
	public String commcontent(@RequestParam("post_id") int post_id, @RequestParam("page") String page, HttpSession session, Model model) {
		System.out.println("CommController.commcontent");

		cs.commUpdateCount(post_id);
		Community comm = cs.getCommunity(post_id);
		String nick = (String) session.getAttribute("nick");

		CommunityLike likeDTO = new CommunityLike();
		likeDTO.setNick(nick);
		likeDTO.setPost_id(post_id);
		CommunityLike commlike = cls.getCommLike(likeDTO);
		model.addAttribute("commlike", commlike);

		CommunityScrap scrapDTO = new CommunityScrap();
		scrapDTO.setNick(nick);
		scrapDTO.setPost_id(post_id);
		CommunityScrap commscrap = css.getCommScrap(scrapDTO);
		model.addAttribute("commscrap", commscrap);

		String content = comm.getContent().replace("\n", "<br>");
		model.addAttribute("comm", comm);
		model.addAttribute("content", content);
		model.addAttribute("page", page);

		return "comm/commcontent";
	}

	@RequestMapping("commupdateform")
	public String commupdateform(@RequestParam("post_id") int post_id, @RequestParam("page") String page, Model model) {
		System.out.println("CommController.commupdateform");

		Community comm = cs.getCommunity(post_id);
		model.addAttribute("comm", comm);
		model.addAttribute("page", page);

		return "comm/commupdateform";
	}

	@RequestMapping("commupdate")
	public String commupdate(@ModelAttribute Community comm, @RequestParam("page") String page, Model model) {
		System.out.println("CommController.commupdate");
		int result = cs.commUpdate(comm);

		model.addAttribute("result", result);
		model.addAttribute("comm", comm);
		model.addAttribute("page", page);

		return "comm/updateresult";
	}


	@RequestMapping("commdelete")
	public String commdelete(@ModelAttribute Community comm, @RequestParam("page") String page, Model model) {
		System.out.println("CommController.commdelete");

		int result = cs.commDelete(comm.getPost_id());
		model.addAttribute("result", result);
		model.addAttribute("page", page);

		return "redirect:commlist";
	}

	@RequestMapping("commReport")
	public String commReport(int post_id, Model model) throws Exception {
		System.out.println("CommController.commReport");

		model.addAttribute("post_id", post_id);

		return "comm/commReport";
	}

	@RequestMapping("commReportIn")
	public String commReportIn(@ModelAttribute CommReportDTO communityReport, @RequestParam("post_id") int post_id, Model model) throws Exception {
		System.out.println("CommController.commReportIn");

		communityReport.setPost_id(post_id);
		cs.reportInsert(communityReport);
		model.addAttribute("report", "게시글에 대한 신고가 완료 되었습니다.");
		return "comm/commReport";
	}
}

	
	

