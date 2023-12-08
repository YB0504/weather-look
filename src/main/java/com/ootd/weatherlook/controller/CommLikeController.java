package com.ootd.weatherlook.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
		 cls.commLikeInsert(commlike);
		 int result = 1;
		 System.out.println("좋아요 클릭 성공");	 
	
		 	 model.addAttribute("commlike", commlike);
			 model.addAttribute("result", result);
			 model.addAttribute("page", page);
						
			return "comm/likeresult";
	}
	
	@RequestMapping("commlikedelete")
	public String likedelete(@RequestParam("post_id") int post_id,
            				@RequestParam("page") String page,
            				@RequestParam("like_id") int like_id,
							RedirectAttributes redirectAttributes) {		
		System.out.println("commlikedelete");
		System.out.println("post_id : " + post_id);
		System.out.println("page : " + page);
		System.out.println("like_id : " + like_id);
		
		cls.commLikeDelete(like_id);	
		System.out.println("삭제 완료");
		
		//redirect
		redirectAttributes.addAttribute("post_id", post_id);
		redirectAttributes.addAttribute("page", page);
				
		return "redirect:commcontent";

	}
}
