package com.ootd.weatherlook.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ootd.weatherlook.model.MemberDTO;
import com.ootd.weatherlook.service.NormalLogService;

@Controller
public class NormalLoginController {

	@Autowired
	private NormalLogService service;

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
		
		member.setProfile_image(newfilename);
		
		
		service.insertMember(member);
		
		System.out.println("가입성공");
		
		return "member/loginform";
	}
	
	// 로그인 성공
	@RequestMapping("main")
	public String login(String id, String passwd, HttpSession session, Model model)throws Exception {
		
		int result = 0;
		
		MemberDTO dto = service.login(id);
		
		if(dto == null) {	// 가입되지 않은 회원
			
			result = 1;
			
			model.addAttribute("result", result);
			
			return "member/loginResult";
			
		} else {
			
			if("master".equals(id) && dto.getPasswd().equals(passwd)) {
				
				session.setAttribute("id", id);
				
				String nick = dto.getNick();
				String profile_image = dto.getProfile_image();
				
				model.addAttribute("nick", nick);
				model.addAttribute("profile_image", profile_image);
				
				return "member/adminPage";
				
			}else if(dto.getPasswd().equals(passwd)) {	// 비밀번호 일치 session 설정
				
				session.setAttribute("id", id);
				
				String nick = dto.getNick();
				String profile_image = dto.getProfile_image();
				
				model.addAttribute("nick", nick);
				model.addAttribute("profile_image", profile_image);
				
				System.out.println("로그인 성공");
				
				return "member/main";
				
			} else {	// 비밀번호 불일치 
				
				result = 2;
				
				model.addAttribute("result", result);
				
				return "member/loginResult";
			}
		}
	}
	
}