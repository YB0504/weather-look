package com.ootd.weatherlook.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ootd.weatherlook.model.MemberDTO;
import com.ootd.weatherlook.service.LoginService;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService service;

	@RequestMapping("loginform")
	public String loginform() {
		System.out.println("LoginController.loginform");
		return "member/loginform";
	}

	@RequestMapping("memberjoin")
	public String memberjoin() {
		System.out.println("LoginController.memberjoin");
		return "member/memberjoin";
	}

	@RequestMapping(value = "idcheck", method = RequestMethod.POST)
	public String idcheck(@RequestParam("memid") String id, Model model) throws Exception {
		System.out.println("LoginController.idcheck");

		int result = service.idcheck(id);
		model.addAttribute("result", result);

		return "member/idcheckresult";
	}

	// NICKNAME 중복체크
	@RequestMapping(value = "nickcheck", method = RequestMethod.POST)
	public String nickcheck(@RequestParam("memnick") String nick, Model model) throws Exception {
		System.out.println("LoginController.nickcheck");

		int result = service.nickcheck(nick);
		model.addAttribute("result", result);

		return "member/nickresult";
	}

	// 회원 가입
	@RequestMapping(value = "memberinsert")
	public String memberinsert(@RequestParam("profile_image_form") MultipartFile mf, MemberDTO member, Model model, HttpServletRequest request) throws Exception {
		System.out.println("LoginController.memberinsert");

		String filename = mf.getOriginalFilename(); // 첨부파일명
		int size = (int) mf.getSize(); // 첨부파일의 크기
		String path = request.getRealPath("upload");

		int result = 0;
		String newfilename = null;

		if (size > 0) { // 첨부파일이 전송된 경우
			String extension = filename.substring(filename.lastIndexOf("."));
			System.out.println("extension:" + extension);

			UUID uuid = UUID.randomUUID();
			newfilename = uuid + extension;
			System.out.println("newfilename:" + newfilename);

			if (size > 1000000) {
				result = 1;
				model.addAttribute("result", result);

				return "member/uploadresult";
			}

			// 확장자 오류
			if (!extension.equals(".jpg") && !extension.equals(".jpeg") && !extension.equals(".gif") && !extension.equals(".png")) {
				result = 2;
				model.addAttribute("result", result);

				return "member/uploadresult";
			}
		}

		if (size > 0) { // 첨부파일이 전송된 경우
			mf.transferTo(new File(path + "/" + newfilename));
		}

		if (newfilename == null) {
			newfilename = "default.jpeg";
		}

		member.setProfile_image(newfilename);
		service.insertMember(member);

		return "member/loginform";
	}

	//카카오톡 로그인
	@RequestMapping("kakaologin")
	public String kakaologin(@RequestParam("nickname") String nick, @RequestParam("email") String id, HttpSession session, Model model) throws Exception {
		System.out.println("LoginController.kakaologin");

		MemberDTO kakaoCheck = service.kakaoLoginCheck(nick);
		if (kakaoCheck == null) {    // 해당 nick으로 등록된 id가 없다면
			MemberDTO kakao = new MemberDTO();

			kakao.setNick(nick);
			kakao.setProfile_image("dafault.jpeg");
			kakao.setId(id);
			service.kakaoLogin(kakao);        // 회원 DB에 저장

			MemberDTO logink2 = service.kakaoLoginCheck(nick);
			session.setAttribute("nick", logink2.getNick());    // 회원 등록 후 세션 설정
			model.addAttribute("member", kakao);

			return "redirect:main";
		} else if (kakaoCheck.getNick().equals(nick))  // 등록되어 있는 회원
			System.out.println("카카오 로그인 성공");

		session.setAttribute("nick", nick);
		MemberDTO member = service.kakaoLoginCheck(nick);
		model.addAttribute("member", member);
		return "redirect:login";
	}

	// 로그인 성공
	@RequestMapping("login")
	public String login(String id, String passwd, HttpSession session, Model model) throws Exception {
		System.out.println("LoginController.login");

		int result = 0;
		MemberDTO member = service.login(id);

		if (member == null) {    // 가입되지 않은 회원
			result = 1;
			model.addAttribute("result", result);

			return "member/loginResult";
		} else {
			if ("master".equals(id) && member.getPasswd().equals(passwd)) {
				session.setAttribute("nick", member.getNick());

				return "redirect:main";
			} else if (member.getPasswd().equals(passwd)) {    // 비밀번호 일치 session 설정
				session.setAttribute("nick", member.getNick());

				return "redirect:main";
			} else {    // 비밀번호 불일치
				result = 2;
				model.addAttribute("result", result);

				return "member/loginResult";
			}
		}
	}

	@RequestMapping("idSearchForm")
	public String idSearchForm() throws Exception {
		System.out.println("LoginController.idSearchForm");

		return "member/idSearchForm";
	}

	@RequestMapping("idSearch")
	public String idSearch(@ModelAttribute MemberDTO mem, @RequestParam("phone") String phone, Model model) throws Exception {
		System.out.println("LoginController.idSearch");

		MemberDTO member = service.idSearch(mem);

		if (member.getPhone().equals(phone))
			model.addAttribute("searchId", member.getId());

		return "member/idSearchForm";
	}

	@RequestMapping("pwSearchForm")
	public String pwSearchForm() {
		System.out.println("LoginController.pwSearchForm");

		return "member/pwSearchForm";
	}

	@RequestMapping("pwSearch")
	public String pwSearch(@ModelAttribute MemberDTO mem, @RequestParam("id") String id, @RequestParam("phone") String phone, Model model) {
		System.out.println("LoginController.pwSearch");

		MemberDTO member = service.pwSearch(mem);

		if (member.getId().equals(id) && member.getPhone().equals(phone))
			model.addAttribute("searchPw", member.getPasswd());

		return "member/pwSearchForm";
	}

	@RequestMapping("logOut")
	public String logOut(HttpSession session) {
		System.out.println("LoginController.logOut");

		session.invalidate();

		return "redirect:loginform";
	}
}
