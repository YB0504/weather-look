package com.ootd.weatherlook.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;

import com.ootd.weatherlook.model.Community;
import com.ootd.weatherlook.model.CommunityLike;
import com.ootd.weatherlook.service.CommLikeService;
import com.ootd.weatherlook.service.CommService;

import lombok.extern.log4j.Log4j;

@Controller
public class CommController {

	@Autowired
	private CommService cs;
	
	@Autowired
	private CommLikeService cls;
	
	
	@RequestMapping("commform")
	public String commform() {
		return "comm/commform";
	}
	
	@RequestMapping("commwrite")
	public String commwrite(@ModelAttribute Community comm,
							HttpSession session,
							Model model) {
		
		
		
		int result = cs.insert(comm);
		
		session.setAttribute("nick",comm.getNick());
		if(result == 1) System.out.println("글 작성 성공");
			model.addAttribute("result", result);
			
		return "comm/insertresult";
	}
	
	@RequestMapping("commlist")
	public String commlist(@RequestParam(value = "page", defaultValue = "1") int page,
							Model model) {
	
		int limit = 10;
		
		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;
		
		int listCount = cs.getCount();
		System.out.println("listcount");
		
		List<Community> commlist = cs.getCommList(page);
		System.out.println("commlist");
		
		int pageCount = listCount/limit+((listCount%10 == 0)?0:1);
		
		int startPage = ((page-1)/10) * limit + 1;	// 1, 11, 21...
		int endPage = startPage + 10 - 1;			// 10, 20, 30...
		
		if(endPage > pageCount)
			endPage = pageCount;
		
		model.addAttribute("page", page);
		model.addAttribute("listcount", listCount);
		model.addAttribute("commlist", commlist);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "comm/commlist";
	}
	
	@RequestMapping("commcontent")
	public String commcontent(@RequestParam("post_id") int post_id,
							  @RequestParam("page") String page,
							  HttpSession session,
							  Model model) {
		cs.updatecount(post_id);
		System.out.println("updatecount");
		Community comm = cs.getCommunity(post_id);
		
		CommunityLike likeDTO = new CommunityLike();
		
		likeDTO.setNick((String)session.getAttribute("nick"));
		likeDTO.setPost_id(post_id);
		CommunityLike commlike = cls.getLike(likeDTO);
		model.addAttribute("commlike", commlike);
		System.out.println("컴라이크생성");
		
		String content = comm.getContent().replace("\n","<br>");
		
		model.addAttribute("comm", comm);
		model.addAttribute("content", content);
		model.addAttribute("page", page);
		
		return "comm/commcontent";
	}
	
	@RequestMapping("commupdateform")
	public String commupdateform(@RequestParam("post_id") int post_id,
								 @RequestParam("page") String page,
								 HttpSession session,
								 Model model) {
		System.out.println(session.getAttribute("nick"));
		Community comm = cs.getCommunity(post_id);
		model.addAttribute("comm", comm);
		model.addAttribute("page", page);
		
		return "comm/commupdateform";
	}
	
	@RequestMapping("commupdate")
	public String commupdate(@ModelAttribute Community comm,
							 @RequestParam("page") String page,
							 Model model) {
		
		int result = cs.update(comm);
		
		model.addAttribute("result", result);
		model.addAttribute("comm",comm);
		model.addAttribute("page",page);
		
		
		return "comm/updateresult";
	}
	
	
	@RequestMapping("commdelete")
	public String commdelete(@ModelAttribute Community comm,
							 @RequestParam("page") String page,
							 Model model) {
		
		int result = cs.delete(comm.getPost_id());
		
		model.addAttribute("result", result);
		model.addAttribute("page", page);
		
		return"redirect:commlist";
	}
	

}

	
	

