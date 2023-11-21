package com.ootd.weatherlook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ootd.weatherlook.model.Community;
import com.ootd.weatherlook.service.CommService;

@Controller
public class CommController {

	@Autowired
	private CommService service;
	
	
	@RequestMapping("commform")
	public String commform() {
		return "comm/commform";
	}
	
	@RequestMapping("commwrite")
	public String commwrite(@ModelAttribute Community comm,
							Model model) {
		
		int result = service.insert(comm);
		if(result == 1) System.out.println("글 작성 성공");
			model.addAttribute("result", result);
	
		return "comm/commlist";
	}
	
	@RequestMapping("commlist")
	public String commlist(@RequestParam(value = "page", defaultValue = "1") int page,
							Model model) {
	
		int limit = 10;
		
		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;
		
		int listcount = service.getCount();
		System.out.println("listcount");
		
		List<Community> commlist = service.getCommList(page);
		System.out.println("commlist");
		
		int pageCount = listcount/limit+((listcount%10 == 0)?0:1);
		
		int startPage = ((page-1)/10) * limit + 1;	// 1, 11, 21...
		int endPage = startPage + 10 - 1;			// 10, 20, 30...
		
		if(endPage > pageCount)
			endPage = pageCount;
		
		model.addAttribute("page", page);
		model.addAttribute("listcount", listcount);
		model.addAttribute("commlist", commlist);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "comm/commlist";
	}
	
	
	
	
	
	
}
