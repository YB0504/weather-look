package com.ootd.weatherlook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ootd.weatherlook.model.Daily;
import com.ootd.weatherlook.model.Dailylike;
import com.ootd.weatherlook.service.DailyService;
import com.ootd.weatherlook.service.DailylikeService;

@Controller
public class DailyLikeController {

	@Autowired
	private DailylikeService service;
	
	@Autowired
	private DailyService dService;

	// good 저장
	@RequestMapping("heartInsert")
	public String hInsert(Dailylike dl, Model model) {

		String ment;
		if (service.saveHeart(dl) == 1) {
			ment = "좋아요가 눌러졌습니다.";
		} else {
			ment = "이미 좋아요를 누르셨습니다.";
		}
		model.addAttribute("like", ment);

		dService.updatecount(dl.getPost_id());
		Daily daily = dService.getDaily(dl.getPost_id());
		String content = daily.getContent().replace("\n", "<br>");
		
		model.addAttribute("daily",daily);
		model.addAttribute("content", content);
		model.addAttribute("page", 1);
		
		return "daily/dailycontent";
	}

}
