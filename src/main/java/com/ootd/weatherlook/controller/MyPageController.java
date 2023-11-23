package com.ootd.weatherlook.controller;

import com.ootd.weatherlook.model.*;
import com.ootd.weatherlook.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MyPageController {
	private final MyPageService myPageService;

	@RequestMapping("/")
	String main(HttpSession session) {
		System.out.println("MyPageController.main");
		String nick = "선홍";
		session.setAttribute("nick", nick);
		return "redirect:/myPage";
	}

	//마이페이지
	@RequestMapping("/myPage")
	String myPage(Model model, HttpSession session) {
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
		return "myPage/mypage";
	}

	//회원정보 수정
	@RequestMapping("memberUpdateForm")
	String memberUpdateForm(Model model, HttpSession session) {
		System.out.println("MyPageController.memberUpdateForm");
		try {
			String nick = (String) session.getAttribute("nick");
			Member member = myPageService.getMember(nick);
			model.addAttribute("member", member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "myPage/memberUpdateForm";
	}

	@RequestMapping("memberUpdate")
	String memberUpdate(@RequestParam("profile_image_form") MultipartFile file, Member updateMember, HttpServletRequest request, Model model, HttpSession session) {
		System.out.println("MyPageController.memberUpdate");
		try {
			int result;
			String path = request.getServletContext().getRealPath("upload");
			Member storedMember = myPageService.getMember(updateMember.getNick());
			String updateProfile = updateMember.getProfile_image();
			String storedProfile = storedMember.getProfile_image();


			//			파일 X
			if (file.isEmpty()) {
				//				사진 유지 == 삭제 없음
				if (updateProfile.equals(storedProfile) || updateProfile.equals("")) {
					updateMember.setProfile_image(storedProfile);
					myPageService.updateMember(updateMember);
					return "redirect:/myPage";
				}

				//				기본이미지로 변경
				myPageService.updateMember(updateMember);
				File deleteFile = new File(path + "/" + storedProfile);
				deleteFile.delete();
				return "redirect:/myPage";
			}

			//			10MB 이상인 파일은 저장하지 않음
			int fileSize = (int) file.getSize();
			if (fileSize > 10000000) {
				result = 1;
				model.addAttribute("result", result);
				return "myPage/memberUpdateResult";
			}

			String originalFilename = file.getOriginalFilename();
			String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

			//			확장자가 jpg, jpeg, gif, png 가 아닌 파일은 저장하지 않음
			if (!extension.equals(".jpg") && !extension.equals(".jpeg") && !extension.equals(".gif") && !extension.equals(".png")) {
				result = 2;
				model.addAttribute("result", result);
				return "myPage/memberUpdateResult";
			}

			//			기존 이미지 삭제. 단, 기본이미지 -> 사용자 선택 이미지의 경우 삭제하지 않음
			if (!storedProfile.equals("default.jpeg")) {
				File deleteFile = new File(path + "/" + storedProfile);
				deleteFile.delete();
			}

			//		    실제 저장될 파일명 생성
			UUID uuid = UUID.randomUUID();
			String newFilename = uuid + extension;
			file.transferTo(new File(path + "/" + newFilename));
			updateMember.setProfile_image(newFilename);
			myPageService.updateMember(updateMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/myPage";
	}

	@RequestMapping("deleteMember")
	String deleteMember(HttpSession session) {
		System.out.println("MyPageController.deleteMember");
		try {
			myPageService.deleteMember((String) session.getAttribute("nick"));
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/myPage";
	}
}
