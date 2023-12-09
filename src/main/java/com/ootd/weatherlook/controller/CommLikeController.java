package com.ootd.weatherlook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ootd.weatherlook.model.CommunityLike;
import com.ootd.weatherlook.service.CommLikeService;
import com.ootd.weatherlook.service.CommService;

@Controller
@RequiredArgsConstructor
public class CommLikeController {

	private final CommLikeService cls;

	private final CommService cs;

	@RequestMapping("commlikeinsert")
	public String likeinsert(CommunityLike commlike, @RequestParam("page") String page, Model model) {
		System.out.println("CommLikeController.likeinsert");

		int result = cls.commLikeInsert(commlike);
		model.addAttribute("commlike", commlike);
		model.addAttribute("result", result);
		model.addAttribute("page", page);

		return "comm/likeresult";
	}

	@RequestMapping("commlikedelete")
	public String likedelete(@RequestParam("post_id") int post_id, @RequestParam("page") String page, @RequestParam("like_id") int like_id, RedirectAttributes redirectAttributes) {
		System.out.println("CommLikeController.likedelete");

		cls.commLikeDelete(like_id);
		redirectAttributes.addAttribute("post_id", post_id);
		redirectAttributes.addAttribute("page", page);

		return "redirect:commcontent";
	}
}
