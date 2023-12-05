package com.ootd.weatherlook.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
public class LoginController {

	@Autowired
	private LoginService service;

	@RequestMapping("loginform")
	public String loginform() {
		System.out.println("로그인 폼");
		return "member/loginform";
	}

	@RequestMapping("memberjoin")
	public String memberjoin() {
		System.out.println("회원 가입 폼");
		return "member/memberjoin";
	}

	// ID 중복체크
	@RequestMapping(value = "idcheck", method = RequestMethod.POST)
	public String idcheck(@RequestParam("memid") String id, Model model) throws Exception {
		System.out.println("id : " + id);

		int result = service.idcheck(id);
		model.addAttribute("result", result);

		return "member/idcheckresult";
	}

	// NICKNAME 중복체크
	@RequestMapping(value = "nickcheck", method = RequestMethod.POST)
	public String nickcheck(@RequestParam("memnick") String nick, Model model) throws Exception {
		System.out.println("nick : " + nick);

		int result = service.nickcheck(nick);
		model.addAttribute("result", result);

		return "member/nickresult";
	}

	// 회원 가입
	@RequestMapping(value = "memberinsert")
	public String memberinsert(@RequestParam("profile_image_form") MultipartFile mf, MemberDTO member, Model model,
			HttpServletRequest request) throws Exception {

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
			
			if (size > 1000000) { // 10MB
				
				result = 1;
				
				model.addAttribute("result", result);
				
				return "member/uploadresult";

			}
			
			// 확장자 오류
			if (!extension.equals(".jpg") && !extension.equals(".jpeg") &&
				!extension.equals(".gif") && !extension.equals(".png")) {
				
				result = 2;
				
				model.addAttribute("result", result);
				
				return "member/uploadresult";
			}
		}
		
		if (size > 0) { // 첨부파일이 전송된 경우

			// path값과 새로 만들어지 파일명을 실제 업로드 하는 코드
			mf.transferTo(new File(path + "/" + newfilename));

		}
		
		if(newfilename == null) {
			newfilename = "default.jpeg";
		}
		
		member.setProfile_image(newfilename);
		
		
		service.insertMember(member);
		
		System.out.println("가입성공");
		
		return "member/loginform";
	}
	
	//카카오톡 로그인
	@RequestMapping("kakaologin")
	public String kakaologin(@RequestParam("nickname") String nick,
							 @RequestParam("email") String id,
							 HttpSession session,
							 Model model)throws Exception {
		
		System.out.println("카카오톡 로그인 컨트롤러");
		System.out.println("nick : "+nick);
		System.out.println("카카오 id : " +id);
		
		// 카카오 로그인 인증
		MemberDTO kakaoCheck = service.kakaoLoginCheck(nick);
		if(kakaoCheck == null) {	// 해당 nick으로 등록된 id가 없다면
			
			int result=0;
			MemberDTO kakao = new MemberDTO();
			
			kakao.setNick(nick);
			kakao.setProfile_image("dafault.jpeg");
			kakao.setId(id);
			result = service.kakaoLogin(kakao);		// 회원 DB에 저장
			
			MemberDTO logink2 = service.kakaoLoginCheck(nick);
			session.setAttribute("nick",logink2.getNick());	// 회원 등록 후 세션 설정
			session.setAttribute("id", logink2.getId());
			
			System.out.println("카톡카톡!!!!!");
			model.addAttribute("member", kakao);
			
			return "member/main";
			
		}else if(kakaoCheck.getNick().equals(nick))  // 등록되어 있는 회원
			
			System.out.println("카카오 로그인 성공");
			
			session.setAttribute("nick", nick);
			session.setAttribute("id", id);
			MemberDTO member = service.kakaoLoginCheck(nick);
			model.addAttribute("member", member);
			return "member/main";
			
	}

	// 로그인 성공
	@RequestMapping("main")
	public String login(String id, String passwd, HttpSession session, Model model)throws Exception {
		
		System.out.println("로그인 인증");
		
		int result = 0;
		
		MemberDTO member = service.login(id);
		
		if(member == null) {	// 가입되지 않은 회원
			
			result = 1;
			
			model.addAttribute("result", result);
			
			return "member/loginResult";
			
		} else {
			
			if("master".equals(id) && member.getPasswd().equals(passwd)) {
				
				session.setAttribute("id", id);
				session.setAttribute("nick", member.getNick());
				session.setAttribute("profile_image", member.getProfile_image());
				
				return "member/adminPage";
				
			}else if(member.getPasswd().equals(passwd)) {	// 비밀번호 일치 session 설정
				
				session.setAttribute("id", id);
				session.setAttribute("nick", member.getNick());
				session.setAttribute("profile_image", member.getProfile_image());
				
				System.out.println("로그인 성공");
				
				return "member/main";
				
			} else {	// 비밀번호 불일치 
				
				result = 2;
				
				model.addAttribute("result", result);
				
				System.out.println("로그인 실패");
				
				return "member/loginResult";
			}
		}
	}
	
	@RequestMapping("idSearchForm")
	public String idSearchForm()throws Exception {
		System.out.println("ID 찾기 폼");
		return "member/idSearchForm";
	}
	
	@RequestMapping("idSearch")
	public String idSearch(@ModelAttribute MemberDTO mem,@RequestParam("phone") String phone, Model model)throws Exception {
		
		System.out.println("ID 찾기");
		
		MemberDTO member = service.idSearch(mem);
		
		if(member.getPhone().equals(phone)) {
			
			System.out.println("ID 찾기 성공");
			
			model.addAttribute("searchId", member.getId());
			
		}
		
		return "member/idSearchForm";
		
	}
	
	@RequestMapping("pwSearchForm")
	public String pwSearchForm()throws Exception {
		System.out.println("비밀번호 찾기 폼");
		return "member/pwSearchForm";
	}
	
	@RequestMapping("pwSearch")
	public String pwSearch(@ModelAttribute MemberDTO mem, @RequestParam("id") String id,
						   @RequestParam("phone") String phone, Model model)throws Exception {
		
		System.out.println("비밀번호 찾기");
		
		MemberDTO member = service.pwSearch(mem);
		
		if(member.getId().equals(id) && member.getPhone().equals(phone)) {
		
			System.out.println("비밀번호 찾기 성공");
			
			model.addAttribute("searchPw", member.getPasswd());
			
		}
		
		return "member/pwSearchForm";
		
	}
}
