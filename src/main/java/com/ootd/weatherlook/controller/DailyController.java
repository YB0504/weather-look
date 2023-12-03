package com.ootd.weatherlook.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import feature.RegionSTNResolver;
import feature.RegionTemperatureResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ootd.weatherlook.model.Daily;
import com.ootd.weatherlook.service.DailyService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DailyController {

	@Autowired
	private DailyService service;

	@RequestMapping("/")
	public String main() {
		System.out.println("DailyController.main");
		return "redirect:dailyform";
	}

	@RequestMapping("dailyform")
	public String dailyform(HttpSession session) {
		System.out.println("DailyController.dailyform");
		session.setAttribute("nick", "준혁");
		return "daily/dailyform";
	}
	
	@RequestMapping("dailywrite")
	public String dailywrite(@RequestParam("uploadFile") MultipartFile file,
	                         @RequestParam("imageDate") String imageDate,
	                         Daily daily,
							 HttpServletRequest request,
	                         Model model) {
		System.out.println("DailyController.dailywrite");
		int result;
		String path = request.getServletContext().getRealPath("upload");

		if (!file.isEmpty()) {
			//기온 추출 - imageDate가 없는 파일은 기온 추출도 불가능
			if (imageDate != null && daily.getLatitude() != null && daily.getLongitude() != null) {
				double latitude = daily.getLatitude();
				double longitude = daily.getLongitude();

				String region = RegionSTNResolver.getRegion(latitude, longitude);
				daily.setRegion(region);

				String stn = RegionSTNResolver.getSTN(region);
				System.out.println("stn = " + stn);

				double temperature = Double.parseDouble(RegionTemperatureResolver.getTemperature(imageDate, stn));
				System.out.println("temperature = " + temperature);
				daily.setTemperature(temperature);
			}

			//파일 전송
			try {
				String originalFilename = file.getOriginalFilename();
				String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

				UUID uuid = UUID.randomUUID();
				String newFilename = uuid + extension;
				file.transferTo(new File(path + "/" + newFilename));
				daily.setDaily_file(newFilename);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		result = service.insert(daily);
		model.addAttribute("result", result);
		return "daily/insertresult";
	}
	
	@RequestMapping("dailylist")
	public String dailylist(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		System.out.println("DailyController.dailylist");

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
		System.out.println("DailyController.dailycontent");
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
		System.out.println("DailyController.dailyupdateform");
		Daily daily = service.getDaily(post_id);
		model.addAttribute("daily", daily);
		model.addAttribute("page", page);
		
		return "daily/dailyupdateform";
	}
	
	@RequestMapping("dailyupdate")
	public String dailyupdate(@ModelAttribute Daily daily,
							  @RequestParam("page") String page,
							  @RequestParam("uploadFile") MultipartFile file,
							  @RequestParam("imageDate") String imageDate,
							  HttpServletRequest request,
							  Model model) {
		System.out.println("DailyController.dailyupdate");
		String path = request.getServletContext().getRealPath("upload");
		Daily storedDaily = service.getDaily(daily.getPost_id());
		String storedDailyFile = storedDaily.getDaily_file();

		if (!file.isEmpty()) {
			//기존 파일 삭제
			if (storedDailyFile != null){
				File deleteFile = new File(path + "/" + storedDailyFile);
				deleteFile.delete();
			}

			//새로운 파일에 대한 데이터 추출
			if (imageDate != null && daily.getLatitude() != null && daily.getLongitude() != null) {
				double latitude = daily.getLatitude();
				double longitude = daily.getLongitude();

				String region = RegionSTNResolver.getRegion(latitude, longitude);
				daily.setRegion(region);

				String stn = RegionSTNResolver.getSTN(region);
				System.out.println("stn = " + stn);

				double temperature = Double.parseDouble(RegionTemperatureResolver.getTemperature(imageDate, stn));
				System.out.println("temperature = " + temperature);
				daily.setTemperature(temperature);
			}

			try {
				String originalFilename = file.getOriginalFilename();
				String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

				UUID uuid = UUID.randomUUID();
				String newFilename = uuid + extension;
				file.transferTo(new File(path + "/" + newFilename));
				daily.setDaily_file(newFilename);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			//새로운 파일이 없을 경우 그대로 유지하기 위해 이전 글 정보를 넣음
			daily.setDaily_file(storedDailyFile);
			daily.setLatitude(storedDaily.getLatitude());
			daily.setLongitude(storedDaily.getLongitude());
			daily.setRegion(storedDaily.getRegion());
			daily.setTemperature(storedDaily.getTemperature());
		}

		int result = service.update(daily);
		model.addAttribute("result", result);
		model.addAttribute("daily", daily);
		model.addAttribute("page", page);
		
		return"redirect:dailylist";
	}

	@RequestMapping("dailydelete")
	public String dailydelete(@ModelAttribute Daily daily,
							  @RequestParam("page") String page,
							  HttpServletRequest request,
							  Model model) {
		System.out.println("DailyController.dailydelete");

		String path = request.getServletContext().getRealPath("upload");
		Daily storedDaily = service.getDaily(daily.getPost_id());
		String storedDailyFile = storedDaily.getDaily_file();

		if (storedDailyFile != null){
			File deleteFile = new File(path + "/" + storedDailyFile);
			deleteFile.delete();
		}
		int result = service.delete(daily.getPost_id());
		
		model.addAttribute("result", result);
		model.addAttribute("page", page);
		
		return"redirect:dailylist";
	}
}
