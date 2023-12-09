package com.ootd.weatherlook.controller;

import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ootd.weatherlook.model.CommunityScrap;
import com.ootd.weatherlook.model.CommunityLike;
import com.ootd.weatherlook.service.CommScrapService;

@Controller
@RequiredArgsConstructor
public class CommScrapController {

	private final CommScrapService css;

	@RequestMapping("commscrapinsert")
	public String likeinsert(CommunityScrap commscrap, @RequestParam("post_id") int post_id, @RequestParam("page") String page, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		System.out.println("CommScrapController.likeinsert");

		int result = css.commScrapInsert(commscrap);
		redirectAttributes.addAttribute("post_id", post_id);
		redirectAttributes.addAttribute("page", page);

		model.addAttribute("commscrap", commscrap);
		model.addAttribute("result", result);
		model.addAttribute("page", page);

		return "redirect:commcontent";
	}

	@RequestMapping("commscrapdelete")
	public String likedelete(@RequestParam("post_id") int post_id, @RequestParam("page") String page, @RequestParam("scrap_id") int scrap_id, RedirectAttributes redirectAttributes) {
		System.out.println("CommScrapController.likedelete");

		css.commScrapDelete(scrap_id);
		redirectAttributes.addAttribute("post_id", post_id);
		redirectAttributes.addAttribute("page", page);

		return "redirect:commcontent";
	}
}
