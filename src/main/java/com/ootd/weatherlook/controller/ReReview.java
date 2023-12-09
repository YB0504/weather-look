package com.ootd.weatherlook.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ootd.weatherlook.model.ReReviewDTO;
import com.ootd.weatherlook.model.ReplyReportDTO;
import com.ootd.weatherlook.model.ReviewDTO;
import com.ootd.weatherlook.service.ReReviewService;
import com.ootd.weatherlook.service.ReviewService;

@Controller
@RequiredArgsConstructor
public class ReReview {

	private final ReviewService rs;

	private final ReReviewService rrs;


	// 댓글 목록을 구해오는 요청
	@RequestMapping("reReviewList")
	public String slist(int post_id, Model model) throws Exception {
		System.out.println("ReReview.slist");

		ReviewDTO review = rs.getReview(post_id);
		List<ReReview> reReviewList = rrs.reReviewList(post_id);
		model.addAttribute("reReviewList", reReviewList);
		model.addAttribute("review", review);

		return "review/reReviewList";
	}

	// 댓글 저장
	@RequestMapping("reInsert")
	public String reInsert(ReReviewDTO reReview, HttpSession session) {
		System.out.println("ReReview.reInsert");

		String nick = (String) session.getAttribute("nick");
		reReview.setNick(nick);
		rrs.reInsert(reReview);

		return "redirect:reReviewList?post_id=" + reReview.getPost_id();     // 부모글 번호를 get방식으로 전달
	}

	// 댓글 수정
	@RequestMapping("reUpdate")
	public String reUpdate(ReReviewDTO reReview) {
		System.out.println("ReReview.reUpdate");

		ReReviewDTO reviewRep = rrs.getRevReply(reReview.getRe_id());
		reviewRep.setRe_content(reReview.getRe_content());
		rrs.reUpdate(reviewRep);

		return "redirect:reReviewList?post_id=" + reReview.getPost_id();
	}

	// 댓글 삭제
	@RequestMapping("reDelete")
	public String reDelete(ReReviewDTO reReview) {
		System.out.println("ReReview.reDelete");

		rrs.reDelete(reReview.getRe_id());

		return "redirect:reReviewList?post_id=" + reReview.getPost_id();
	}

	// 대댓글 작성
	@RequestMapping("reReplyInsert")
	public String reReplyInsert(ReReviewDTO reReview, HttpSession session) {
		System.out.println("ReReview.reReplyInsert");

		String nick = (String) session.getAttribute("nick");
		reReview.setNick(nick);
		rrs.reReplyInsert(reReview);

		return "redirect:reReviewList?post_id=" + reReview.getPost_id();
	}

	// 댓글 신고
	@RequestMapping("sendReReport")
	public String sendReport(int re_id, Model model) {
		System.out.println("ReReview.sendReport");

		model.addAttribute("re_id", re_id);

		return "review/sendReReport";
	}

	@RequestMapping("reReportSuccess")
	public String reportSuccess(@ModelAttribute ReplyReportDTO replyReport, @RequestParam("re_id") int re_id, Model model) {
		System.out.println("ReReview.reportSuccess");

		replyReport.setRe_id(re_id);
		rrs.reReportInsert(replyReport);
		model.addAttribute("report", "댓글에 대한 신고가 완료 되었습니다.");

		return "review/sendReReport";
	}
}
