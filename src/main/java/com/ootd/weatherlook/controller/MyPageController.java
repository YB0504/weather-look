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

	//	작성글 목록 확인
	@RequestMapping("/myPostList")
	String myPostList(Model model, HttpSession session, HttpServletRequest request) {
		System.out.println("MyPageController.myPostList");
		List<ListVO> postList;
		ListQueryVO listQueryVO = new ListQueryVO();
		int postCount, startRow, endRow, page, listSize, totalPage, startPage, endPage;

		try {
			String nick = (String) session.getAttribute("nick");

			page = 1;
			listSize = 12;
			if (request.getParameter("page") != null) page = Integer.parseInt(request.getParameter("page"));
			//list 사이즈 조절 넣어도 좋을듯

			//row
			startRow = (page - 1) * listSize + 1;
			endRow = page * listSize;

			//page
			postCount = myPageService.getPostCount(nick);
			totalPage = postCount / listSize + ((postCount % listSize == 0) ? 0 : 1);
			startPage = ((page - 1) / 10) * listSize + 1;
			endPage = startPage + 10 - 1;

			if (endPage > totalPage) endPage = totalPage;

			//list query
			listQueryVO.setStartRow(startRow);
			listQueryVO.setEndRow(endRow);
			listQueryVO.setNick(nick);
			postList = myPageService.getAllPostList(listQueryVO);

			//transfer using model
			model.addAttribute("page", page);
			model.addAttribute("listSize", listSize);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("postCount", postCount);
			model.addAttribute("postList", postList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "myPage/myPostList";
	}

	//댓글 남긴 글 목록 확인
	@RequestMapping("myReplyList")
	String myReplyList(Model model, HttpSession session, HttpServletRequest request) {
		System.out.println("MyPageController.myReplyList");
		List<ListVO> replyList;
		ListQueryVO listQueryVO = new ListQueryVO();
		int replyCount, startRow, endRow, page, listSize, totalPage, startPage, endPage;

		try {
			String nick = (String) session.getAttribute("nick");

			page = 1;
			listSize = 12;
			if (request.getParameter("page") != null) page = Integer.parseInt(request.getParameter("page"));
			//list 사이즈 조절 넣어도 좋을듯

			//row
			startRow = (page - 1) * listSize + 1;
			endRow = page * listSize;

			//page
			replyCount = myPageService.getReplyCount(nick);
			totalPage = replyCount / listSize + ((replyCount % listSize == 0) ? 0 : 1);
			startPage = ((page - 1) / 10) * listSize + 1;
			endPage = startPage + 10 - 1;

			if (endPage > totalPage) endPage = totalPage;

			//list query
			listQueryVO.setStartRow(startRow);
			listQueryVO.setEndRow(endRow);
			listQueryVO.setNick(nick);
			replyList = myPageService.getAllReplyList(listQueryVO);

			//transfer using model
			model.addAttribute("page", page);
			model.addAttribute("listSize", listSize);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("replyCount", replyCount);
			model.addAttribute("replyList", replyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "myPage/myReplyList";
	}

	//좋아요 목록 확인
	@RequestMapping("myLikeList")
	String myLikeList(Model model, HttpSession session, HttpServletRequest request) {
		System.out.println("MyPageController.myLikeList");
		List<ListVO> likeList;
		ListQueryVO listQueryVO = new ListQueryVO();
		int likeCount, startRow, endRow, page, listSize, totalPage, startPage, endPage;

		try {
			String nick = (String) session.getAttribute("nick");

			page = 1;
			listSize = 12;
			if (request.getParameter("page") != null) page = Integer.parseInt(request.getParameter("page"));
			//list 사이즈 조절 넣어도 좋을듯

			//row
			startRow = (page - 1) * listSize + 1;
			endRow = page * listSize;

			//page
			likeCount = myPageService.getLikeCount(nick);
			totalPage = likeCount / listSize + ((likeCount % listSize == 0) ? 0 : 1);
			startPage = ((page - 1) / 10) * listSize + 1;
			endPage = startPage + 10 - 1;

			if (endPage > totalPage) endPage = totalPage;

			//list query
			listQueryVO.setStartRow(startRow);
			listQueryVO.setEndRow(endRow);
			listQueryVO.setNick(nick);
			likeList = myPageService.getAllLikeList(listQueryVO);

			//transfer using model
			model.addAttribute("page", page);
			model.addAttribute("listSize", listSize);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("likeCount", likeCount);
			model.addAttribute("likeList", likeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "myPage/myLikeList";
	}

	//스크랩 목록 확인
	@RequestMapping("myScrapList")
	String myScrapList(Model model, HttpSession session, HttpServletRequest request) {
		List<ListVO> scrapList;
		ListQueryVO listQueryVO = new ListQueryVO();
		int scrapCount, startRow, endRow, page, listSize, totalPage, startPage, endPage;

		try {
			String nick = (String) session.getAttribute("nick");

			page = 1;
			listSize = 12;
			if (request.getParameter("page") != null) page = Integer.parseInt(request.getParameter("page"));
			//list 사이즈 조절 넣어도 좋을듯

			//row
			startRow = (page - 1) * listSize + 1;
			endRow = page * listSize;

			//page
			scrapCount = myPageService.getScrapCount(nick);
			totalPage = scrapCount / listSize + ((scrapCount % listSize == 0) ? 0 : 1);
			startPage = ((page - 1) / 10) * listSize + 1;
			endPage = startPage + 10 - 1;

			if (endPage > totalPage) endPage = totalPage;

			//list query
			listQueryVO.setStartRow(startRow);
			listQueryVO.setEndRow(endRow);
			listQueryVO.setNick(nick);
			scrapList = myPageService.getAllScrapList(listQueryVO);

			//transfer using model
			model.addAttribute("page", page);
			model.addAttribute("listSize", listSize);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("scrapCount", scrapCount);
			model.addAttribute("scrapList", scrapList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "myPage/myScrapList";
	}

	//회원정보 수정 폼
	@RequestMapping("updateMemberForm")
	String updateMemberForm(Model model, HttpSession session) {
		System.out.println("MyPageController.updateMemberForm");
		try {
			String nick = (String) session.getAttribute("nick");
			MemberVO memberVO = myPageService.getMember(nick);
			model.addAttribute("member", memberVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "myPage/updateMemberForm";
	}

	//회원정보 수정
	@RequestMapping("updateMember")
	String updateMember(@RequestParam("profile_image_form") MultipartFile file, MemberVO updateMemberVO, HttpServletRequest request, Model model) {
		System.out.println("MyPageController.updateMember");
		try {
			int result;
			String path = request.getServletContext().getRealPath("upload");
			MemberVO storedMemberVO = myPageService.getMember(updateMemberVO.getNick());
			String updateProfile = updateMemberVO.getProfile_image();
			String storedProfile = storedMemberVO.getProfile_image();


			//			파일 X
			if (file.isEmpty()) {
				//				사진 유지 == 삭제 없음
				if (updateProfile.equals(storedProfile) || updateProfile.equals("")) {
					updateMemberVO.setProfile_image(storedProfile);
					myPageService.updateMember(updateMemberVO);
					return "redirect:/main";
				}

				//				기본이미지로 변경
				myPageService.updateMember(updateMemberVO);
				File deleteFile = new File(path + "/" + storedProfile);
				deleteFile.delete();
				return "redirect:/main";
			}

			//			10MB 이상인 파일은 저장하지 않음
			int fileSize = (int) file.getSize();
			if (fileSize > 10000000) {
				result = 1;
				model.addAttribute("result", result);
				return "updateMemberResult";
			}

			String originalFilename = file.getOriginalFilename();
			String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

			//			확장자가 jpg, jpeg, gif, png 가 아닌 파일은 저장하지 않음
			if (!extension.equals(".jpg") && !extension.equals(".jpeg") && !extension.equals(".gif") && !extension.equals(".png")) {
				result = 2;
				model.addAttribute("result", result);
				return "updateMemberResult";
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
			updateMemberVO.setProfile_image(newFilename);
			myPageService.updateMember(updateMemberVO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/main";
	}

	//회원삭제
	@RequestMapping("deleteMember")
	String deleteMember(HttpSession session) {
		System.out.println("MyPageController.deleteMember");
		try {
			myPageService.deleteMember((String) session.getAttribute("nick"));
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/loginform";
	}
}
