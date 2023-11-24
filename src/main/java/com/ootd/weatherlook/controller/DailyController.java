package com.ootd.weatherlook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ootd.weatherlook.model.Daily;
import com.ootd.weatherlook.service.DailyService;

@Controller
public class DailyController {

	@Autowired
	private DailyService service;
	
	@RequestMapping("dailyform")
	public String dailyform() {
		return "daily/dailyform";
	}
	
	@RequestMapping("dailywrite")
	public String dailywrite(Daily daily, Model model) {
		System.out.println("dailywrite controller");
		
		int result = service.insert(daily);
		if(result == 1) System.out.println("글 작성 성공");
		model.addAttribute("result", result);
		
		return "daily/insertresult";
	}
	
	 @RequestMapping(value="/writeAction", method=RequestMethod.POST)
	    public String writeAction(
	            HttpServletRequest req,@RequestParam("file") MultipartFile file,
	            @RequestParam("title")String title, 
	            @RequestParam("contents")String contents) throws IllegalStateException, IOException {
	        String PATH = req.getSession().getServletContext().getRealPath("/") + "resources/";
	        if (!file.getOriginalFilename().isEmpty()) {
	            file.transferTo(new File(PATH + file.getOriginalFilename()));
	        }
	        s.addBoard(new Board(0, title, contents, file.getOriginalFilename()));
	        return "board";
	    }
	
	
	
	
	
	
	@RequestMapping("dailylist")
	public String dailylist(@RequestParam(value = "page", defaultValue = "1") int page,
							Model model) {
		int limit = 10;
		
		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;
		
		int listCount = service.getCount();
		System.out.println("listCount:" + listCount);
	
		List<Daily> dailyList = service.getDailylist(page);
		System.out.println("dailyList:"+ dailyList);
	
		int pageCount = listCount/limit+((listCount%10 == 0)?0:1);
		
		int startPage = ((page-1)/10) * limit + 1;
		int endPage = startPage + 10 - 1;
		
		if(endPage > pageCount)
			endPage = pageCount;
		
		model.addAttribute("page", page);
		model.addAttribute("listCount", listCount);
		model.addAttribute("dailyList", dailyList);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "daily/dailylist";
	}
		
	@RequestMapping("dailycontent")
		public String dailycontent(@RequestParam("post_id") int post_id,
								   @RequestParam("page") String page,
								   Model model) {
		service.updatecount(post_id);
		Daily daily = service.getDaily(post_id);
		String content = daily.getContent().replace("\n", "<br>");
		
		model.addAttribute("daily",daily);
		model.addAttribute("content",content);
		model.addAttribute("page", page);
		
		return "daily/dailycontent";
		
	}		

	@RequestMapping("dailyupdateform")
	public String dailyupdateform(@RequestParam("post_id") int post_id,
								  @RequestParam("page") String page,
								  Model model) {
		
		Daily daily = service.getDaily(post_id);
		model.addAttribute("daily", daily);
		model.addAttribute("page", page);
		
		return "daily/dailyupdateform";
	}
	
	@RequestMapping("dailyupdate")
	public String dailyupdate(@ModelAttribute Daily daily,
							  @RequestParam("page") String page,
							  Model model) {
		
		int result = service.update(daily);
		
		model.addAttribute("result", result);
		model.addAttribute("daily", daily);
		model.addAttribute("page", page);
		
		return"redirect:dailylist";
		
	}

	@RequestMapping("dailydelete")
	public String dailydelete(@ModelAttribute Daily daily,
							  @RequestParam("page") String page,
							  Model model) {
		
		int result = service.delete(daily.getPost_id());
		
		model.addAttribute("result", result);
		model.addAttribute("page", page);
		
		return"redirect:dailylist";
	}
}
