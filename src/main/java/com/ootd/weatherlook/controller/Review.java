package com.ootd.weatherlook.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ootd.weatherlook.model.ReviewDTO;
import com.ootd.weatherlook.model.ReviewReportDTO;
import com.ootd.weatherlook.service.ReviewService;

@Controller
public class Review {

	@Autowired
	private ReviewService service;

	@RequestMapping("/")
	String main() {
		System.out.println("review Controller In");
		return "redirect:review";
	}

	@RequestMapping("review")
	String reviewMain(Model model, HttpSession session) {
		System.out.println("Session Setting In");
		String nick = "회원";

		// 닉네임으로 세션 설정
		session.setAttribute("nick", nick);
		return "redirect:reviewList";
	}

	@RequestMapping("reviewInsertForm")
	public String reviewInsertForm() {
		System.out.println("쇼핑 후기 폼");
		return "review/reviewInsertForm";
	}

	@RequestMapping("reviewInsert")
	public String reviewInsert(@ModelAttribute ReviewDTO review, @RequestParam("review_file_form") MultipartFile mf,
			Model model, HttpServletRequest request, HttpSession session) throws Exception {
		System.out.println("쇼핑 후기 작성");

		// 로그인한 nick세션 가져오기
		String nick = (String) session.getAttribute("nick");
		System.out.println("LoginSession : " + nick);

		String filename = mf.getOriginalFilename(); // 첨부파일명
		int size = (int) mf.getSize(); // 첨부파일의 크기

		String path = request.getRealPath("upload");
		System.out.println("mf : " + mf);
		System.out.println("filename : " + filename);
		System.out.println("size : " + size);
		System.out.println("path : " + path);

		int result = 0;

		String newfilename = null;

		if (size > 0) { // 첨부파일이 전송된 경우

			// 파일 중복문제 해결
			// 확장자 추출
			String extension = filename.substring(filename.lastIndexOf("."), filename.length());
			System.out.println("extension:" + extension);

			// 문자형태의 난수 생성하여 저장
			UUID uuid = UUID.randomUUID();

			// 랜덤으로 생성된 파일명과 확장자가 합쳐져서 새로 저장
			newfilename = uuid.toString() + extension;
			System.out.println("newfilename:" + newfilename);

			if (size > 1000000) {

				result = 1;

				model.addAttribute("result", result);

				return "review/uploadresult";

			}

			// 확장자 오류
			if (!extension.equals(".jpg") && !extension.equals(".jpeg") && !extension.equals(".gif")
					&& !extension.equals(".png")) {

				result = 2;

				model.addAttribute("result", result);

				return "review/uploadresult";
			}
		}

		if (size > 0) { // 첨부파일이 전송된 경우

			// path값과 새로 만들어지 파일명을 실제 업로드 하는 코드
			mf.transferTo(new File(path + "/" + newfilename));

		}

		review.setNick(nick);
		review.setReview_file(newfilename);

		service.reviewInsert(review);

		System.out.println("nick : " + nick);
		System.out.println("글 작성 성공");

		return "review/reviewDetail";
	}

	@RequestMapping("reviewList")
	public String reviewList(Model model, HttpSession session, HttpServletRequest request) throws Exception {
		System.out.println("쇼핑후기 리스트");
		
		List<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();

		int postCount, page, listSize, maxPage, startPage, endPage;

		String nick = (String) session.getAttribute("nick");
		System.out.println("ListSession : " + nick);

		page = 1;
		listSize = 10; // 한 페이지 출력할 수

		// 선택된 페이지 번호를 형변환
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		postCount = service.getPostCount();

		// 쇼핑후기 리스트 생성을 위한 page값 전달
//		postList = session.getReviewList(page);
		
		reviewList = service.getReviewList(page);

		// 데이터 갯수에 따른 총 페이지
		maxPage = postCount / listSize + ((postCount % listSize == 0) ? 0 : 1);

		// 1 : 10 , 11 : 20...(page)
		startPage = ((page - 1) / 10) * listSize + 1;
		endPage = startPage + 10 - 1;

		// 데이터 갯수를 초과하는 페이지수 출력 제한
		if (endPage > maxPage) {
			endPage = maxPage;
		}

		model.addAttribute("page", page);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("postCount", postCount);
		model.addAttribute("reviewList", reviewList);

		return "review/reviewList";
	}

	@RequestMapping("reviewDetail")
	public String reviewDetail(@RequestParam("post_id") int post_id, @RequestParam("page") String page,
			HttpSession session, HttpServletRequest request, Model model)throws Exception {
		System.out.println("review.reviewDetail");
		
		String nick = (String) session.getAttribute("nick");
		System.out.println("DetailSession : " +nick);
		
		service.updateCount(post_id);
		ReviewDTO review = service.getReview(post_id);
		String content = review.getContent().replace("\n", "<br>");

		model.addAttribute("review", review);
		model.addAttribute("content", content);
		model.addAttribute("page", page);

		return "review/reviewDetail";
	}
	
	@RequestMapping("sendReport")
	public String sendReport() throws Exception {
		System.out.println("신고하기 폼");
		return "review/sendReport";
	}

	@RequestMapping("reportSuccess")
	public String reportSuccess(ReviewReportDTO reviewReport) throws Exception {
		
		System.out.println("reportInsert");
		
		service.reportInsert(reviewReport);

		System.out.println("신고 완료");
		
		return "review/reportList";

	}

}
