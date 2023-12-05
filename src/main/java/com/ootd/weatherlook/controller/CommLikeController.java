package com.ootd.weatherlook.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ootd.weatherlook.model.Community;
import com.ootd.weatherlook.model.CommunityLike;
import com.ootd.weatherlook.model.CommunityRe;
import com.ootd.weatherlook.service.CommLikeService;
import com.ootd.weatherlook.service.CommService;

@Controller
public class CommLikeController {

	@Autowired
	private CommLikeService cls;
	
	@Autowired
	private CommService cs;
	
	@RequestMapping("commlikeinsert")
	public String likeinsert(CommunityLike commlike,
							@RequestParam("post_id") int post_id,
							@RequestParam("page") String page,
							HttpSession session
							,Model model) {
	
		 cls.insert(commlike);
		 int result = 1;
		 System.out.println("좋아요 클릭 성공");	 
	
		 	 model.addAttribute("commlike", commlike);
			 model.addAttribute("result", result);
			 model.addAttribute("page", page);
						
			return "comm/likeresult";
	}
	
	@RequestMapping("commlikedelete")
	public String likedelete(CommunityLike commlike,
							Model model) {

		cls.delete(commlike.getPost_id());
		
		return "redirect:content";
	}

}
