package com.ootd.weatherlook.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ootd.weatherlook.model.ReReviewDTO;
import com.ootd.weatherlook.model.ReviewDTO;
import com.ootd.weatherlook.service.ReReviewService;
import com.ootd.weatherlook.service.ReviewService;

@Controller
public class ReReview {
	
	@Autowired
	private ReviewService rs;
	
	@Autowired
	private ReReviewService rrs;
	
	
	// 댓글 목록을 구해오는 요청
	@RequestMapping("reReviewList")
	public String slist(int post_id, Model model)throws Exception {
		ReviewDTO review = rs.getReview(post_id); 					// 부모글에 대한 상세정보
		List<ReReview> reReviewList = rrs.reReviewList(post_id);	// 댓글 목록(새로추가된 댓글도포함)
		System.out.println("reReviewList:"+ reReviewList);
		
		model.addAttribute("reReviewList", reReviewList); 			// 모델에 저장 foreach태그
		model.addAttribute("review", review);
		return "review/reReviewList";
	}
	
	// 댓글 저장
	@RequestMapping("reInsert")
	public String reInsert(ReReviewDTO reReview, HttpSession session, 
			HttpServletRequest request, Model model)throws Exception {
		System.out.println("댓글 작성 완료");
		
		String nick = (String)session.getAttribute("nick");
		reReview.setNick(nick);
		
		rrs.reInsert(reReview);
		
		return "redirect:reReviewList?post_id="+reReview.getPost_id();	 // 부모글 번호를 get방식으로 전달
	}
	
	// 댓글 수정
	@RequestMapping("reUpdate")
	public String reUpdate(ReReviewDTO reReview, Model model)throws Exception {
		rrs.reUpdate(reReview);
		return "redirect:reReviewList?post_id=" + reReview.getPost_id();
	}
	
	// 댓글 삭제
	@RequestMapping("reDelete")
	public String reDelete(ReReviewDTO reReview, Model model)throws Exception {
		rrs.reDelete(reReview.getRe_id());
		return "redirect:reReviewList?post_id=" +reReview.getPost_id();
	}
	
}
