package com.ootd.weatherlook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ootd.weatherlook.model.Daily;
import com.ootd.weatherlook.model.ReDaily;
import com.ootd.weatherlook.service.DailyService;
import com.ootd.weatherlook.service.ReDailyService;

@Controller
public class ReDailyController {

	@Autowired
	private DailyService service;

	@Autowired
	private ReDailyService rds;

	// 댓글 목록을 구해오는 요청
	@RequestMapping("rdlist")
	public String slist(int post_id, Model model) {
		Daily daily = service.getDaily(post_id); 			// 부모글에 대한 상세정보
		List<ReDaily> rdlist = rds.list(post_id);			// 댓글 목록(새로추가된 댓글도포함)
		System.out.println("rdlist:"+ rdlist);
		
		model.addAttribute("rdlist", rdlist); 				// 모델에 저장 foreach태그루프돌림
		model.addAttribute("daily", daily);
		return "daily/rdlist";
	}

	// 댓글 저장
	@RequestMapping("rdInsert")
	public String sInsert(ReDaily rb, Model model) {
		rds.insert(rb);
		return "redirect:rdlist?post_id="+rb.getPost_id();	 // 부모글 번호를 get방식으로 전달
	}
	
	// 댓글 삭제
	@RequestMapping("rdDelete")
	public String sDelete(ReDaily rb, Model model) {
		rds.delete(rb.getRe_id());
		return "redirect:rdlist?post_id=" +rb.getPost_id();
	}
	
	// 댓글 수정
	@RequestMapping("rdUpdate")
	public String sUpdate(ReDaily rb, Model model) {
		rds.update(rb);
		return "redirect:rdlist?post_id=" + rb.getPost_id();
	}
	
}

