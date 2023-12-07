package com.ootd.weatherlook.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	public String hInsert(Dailylike dl, String page, String state, Model model) {

		dl.setLike_check(state);		
		
		// 좋아요 선택여부 체크
		int like_count = dService.likecount(dl); 	// 선택 : like_count = 1
													// 미선택 : like_count = 0
		System.out.println("like_count:" + like_count);
		
//		List<Dailylike> list = service.getList(dl);	
//		System.out.println("좋아요 목록"+ list);

		int result = 0;
//		if(!list.isEmpty()) {
//			Dailylike dailylike = list.get(0);
//			if(dailylike == null) {
//				result = service.insert(dl);
//			}else {
//				result = service.update(dl);
//			}
//		}

		if (like_count == 0 || dl.getLike_check() == null) { 	// 좋아요가 없을때만 insert SQL문 실행
			result = service.insert(dl);
		}else {
			result = service.update(dl);
		}

		if (result == 1) System.out.println("좋아요 클릭");

		return "redirect:dailycontent?post_id="+dl.getPost_id()+"&page="+page;
	}

	// nogood으로 수정
	@RequestMapping("heartUpdate")
	public String heartUpdate(Dailylike dl, String page,Model model) {
		System.out.println("heartUpdate in");
		dl.setLike_check("y");		
		
		// 좋아요 선태여부 체크
		int like_count = dService.likecount(dl); 	// 선택 : like_count = 1
													// 미선택 : like_count = 0
		System.out.println("like_count:" + like_count);

//		List<Dailylike> list = service.getList(dl);	
//		System.out.println("좋아요 목록"+ list);
//
//		if(!list.isEmpty()) {
//			Dailylike dailylike = list.get(0);
//			int like_id = dailylike.getLike_id();
//			
//			dl.setLike_id(like_id);
//		}
		
		int result = 0;
		if (like_count == 1) { 			// 좋아요가  있을때만 update SQL문 실행
			result = service.update(dl);
		}

		if (result == 1) System.out.println("좋아요 취소");

//		model.addAttribute("result", result);

		return "redirect:dailycontent?post_id="+dl.getPost_id()+"&page="+page;
	}

}
