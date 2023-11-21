package com.ootd.weatherlook.controller;

import com.ootd.weatherlook.model.*;
import com.ootd.weatherlook.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class MyPageController {
	private final MyPageService myPageService;

	@RequestMapping("/")
	String main(HttpSession session) {
		System.out.println("MyPageController.main");
		String nick = "준혁";
		session.setAttribute("nick", nick);
		return "redirect:/mypage";
	}

	@RequestMapping("/mypage")
	String myPage(Model model, HttpSession session){
		System.out.println("MyPageController.myPage");
		List<Board> boardList;
		List<Comment> commentList;
		List<Like> likeList;
		List<Scrap> scrapList;

		try {
			String nick = (String) session.getAttribute("nick");
			boardList = myPageService.getAllPostList(nick);
			commentList = myPageService.getAllCommentList(nick);
			likeList = myPageService.getAllLikeList(nick);
			scrapList = myPageService.getAllScrapList(nick);

			model.addAttribute("boardList", boardList);
			model.addAttribute("commentList", commentList);
			model.addAttribute("likeList", likeList);
			model.addAttribute("scrapList", scrapList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mypage";
	}

//	@RequestMapping("")
}
