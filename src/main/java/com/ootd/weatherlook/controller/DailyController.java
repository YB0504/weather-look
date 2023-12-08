package com.ootd.weatherlook.controller;

import java.io.File;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ootd.weatherlook.model.Daily;
import com.ootd.weatherlook.model.DailyReportDTO;
import com.ootd.weatherlook.model.LikeDTO;
import com.ootd.weatherlook.model.ScrapDTO;
import com.ootd.weatherlook.model.Search;
import com.ootd.weatherlook.model.Weather;
import com.ootd.weatherlook.service.DailyService;

import feature.RegionSTNResolver;
import feature.RegionTemperatureResolver;

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
		session.setAttribute("nick", "혜림");
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
	
	// ========== 선홍 수정: dailylist ===========
	
	@RequestMapping("dailylist")
	public String dailylist(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "templow", required = false) Double templow,
			@RequestParam(value = "temphigh", required = false) Double temphigh,
			@RequestParam(value = "temp", required = false) Integer temp,
			@RequestParam(value = "region", required = false, defaultValue = "") String region,
			Model model) {

		System.out.println("DailyController.dailylist");

		int limit = 9;
		int startrow = (page - 1) * limit + 1;
		int endrow = page * limit;
		
		
		// 검색조건 설정 ==========>
		
		Search search = new Search();
		Weather weather = new Weather();
		
		double deviation = 5;
		weather.setDeviation(deviation); // 기온 편차 값 삽입
		if(templow == null && temphigh == null && temp != null) {
			weather.setHighest(temp+deviation);
			weather.setLowest(temp-deviation);
			weather.setTemperature(temp);
		}
		if(templow != null && temphigh != null && temp == null) {
			temp = (int)(templow + temphigh)/2;
			weather.setHighest(temphigh);
			weather.setLowest(templow);
			weather.setTemperature(temp);
		}
		
		if(region != null) {
			weather.setRegion(region);
		}

		search.setWeather(weather);
		search.setStartrow(startrow);
		search.setEndrow(endrow);
		search.setPage(page);
		
		
		// <========== 검색조건 설정 
		
		int listcount = service.getCount(search);
		System.out.println("listCount:" + listcount);
	
		List<Daily> dailyList = service.getDailylist(search);
		System.out.println("dailyList:"+ dailyList);
	
		int pageCount = listcount/limit+((listcount%limit == 0)?0:1);
		
		int startPage = ((page-1)/limit) * limit + 1;
		int endPage = startPage + limit - 1;
		
		if(endPage > pageCount)
			endPage = pageCount;
		
		model.addAttribute("page", page);
		model.addAttribute("listcount", listcount);
		model.addAttribute("dailyList", dailyList);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		model.addAttribute("temp",temp);		
		model.addAttribute("region",region);
		model.addAttribute("templow",templow);
		model.addAttribute("temphigh", temphigh);
		
		return "daily/dailylist";
	}
		
	// ========== 선홍 수정: dailylist ===========
	
		
	@RequestMapping("dailycontent")
		public String dailycontent(@RequestParam("post_id") int post_id,
								   @RequestParam("page") String page,
								   HttpSession session,
								   Model model) {
		System.out.println("DailyController.dailycontent");
		String nick = (String)session.getAttribute("nick");

		service.updatecount(post_id);
		Daily daily = service.getDaily(post_id);
		String content = daily.getContent().replace("\n", "<br>");

		model.addAttribute("daily",daily);
		model.addAttribute("content",content);
		model.addAttribute("page", page);

		//like 관련 코드
		LikeDTO likeDTO = new LikeDTO();
		likeDTO.setPost_id(post_id);
		likeDTO.setNick(nick);

		LikeDTO like = service.isLike(likeDTO);
		model.addAttribute("like", like);

		//scrap 관련 코드
		ScrapDTO scrapDTO = new ScrapDTO();
		scrapDTO.setPost_id(post_id);
		scrapDTO.setNick(nick);

		ScrapDTO scrap = service.isScrap(scrapDTO);
		model.addAttribute("scrap", scrap);
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

	@RequestMapping("dailyLikeInsert")
	public String dailyLikeInsert(@RequestParam("post_id") int post_id,
	                         @RequestParam("page") String page,
	                         HttpSession session,
	                         RedirectAttributes redirectAttributes) {
		System.out.println("DailyController.dailyLikeInsert");
		LikeDTO likeDTO = new LikeDTO();
		String nick = (String) session.getAttribute("nick");

		//log
		System.out.println("post_id = " + post_id);
		System.out.println("page = " + page);
		System.out.println("nick = " + nick);

		//insert
		likeDTO.setNick(nick);
		likeDTO.setPost_id(post_id);

		service.likeInsert(likeDTO);

		//redirect
		redirectAttributes.addAttribute("post_id", post_id);
		redirectAttributes.addAttribute("page", page);

		return "redirect:dailycontent";
	}

	@RequestMapping("dailyLikeDelete")
	public String dailyLikeDelete(@RequestParam("post_id") int post_id,
	                         @RequestParam("page") String page,
	                         @RequestParam("like_id") int like_id,
	                         RedirectAttributes redirectAttributes) {
		System.out.println("DailyController.dailyLikeDelete");

		//log
		System.out.println("post_id = " + post_id);
		System.out.println("page = " + page);
		System.out.println("like_id = " + like_id);

		//delete
		service.likeDelete(like_id);

		//redirect
		redirectAttributes.addAttribute("post_id", post_id);
		redirectAttributes.addAttribute("page", page);

		return "redirect:dailycontent";
	}

	@RequestMapping("dailyScrapInsert")
	public String dailyScrapInsert(@RequestParam("post_id") int post_id,
	                          @RequestParam("page") String page,
	                          HttpSession session,
	                          RedirectAttributes redirectAttributes) {
		System.out.println("DailyController.dailyScrapInsert");

		ScrapDTO scrapDTO = new ScrapDTO();
		String nick = (String) session.getAttribute("nick");

		//log
		System.out.println("post_id = " + post_id);
		System.out.println("page = " + page);
		System.out.println("nick = " + nick);

		//insert
		scrapDTO.setNick(nick);
		scrapDTO.setPost_id(post_id);

		service.scrapInsert(scrapDTO);

		//redirect
		redirectAttributes.addAttribute("post_id", post_id);
		redirectAttributes.addAttribute("page", page);

		return "redirect:dailycontent";
	}

	@RequestMapping("dailyScrapDelete")
	public String dailyScrapDelete(@RequestParam("post_id") int post_id,
	                          @RequestParam("page") String page,
	                          @RequestParam("scrap_id") int scrap_id,
	                          RedirectAttributes redirectAttributes) {
		System.out.println("DailyController.scrapDelete");

		//log
		System.out.println("post_id = " + post_id);
		System.out.println("page = " + page);
		System.out.println("scrap_id = " + scrap_id);

		//delete
		service.scrapDelete(scrap_id);

		//redirect
		redirectAttributes.addAttribute("post_id", post_id);
		redirectAttributes.addAttribute("page", page);

		return "redirect:dailycontent";
	}
	
		// 신고하기
		@RequestMapping("dailyReport")
		public String dailyReport(int post_id, Model model) throws Exception {
			System.out.println("신고하기 폼");

			model.addAttribute("post_id", post_id);
			return "daily/dailyReport";
		}

		@RequestMapping("dailyReportIn")
		public String dailyReportIn(@ModelAttribute DailyReportDTO dailyReport,
		                            @RequestParam("post_id") int post_id, Model model) throws Exception {

			System.out.println("reportInsert");

			dailyReport.setPost_id(post_id);

			service.reportInsert(dailyReport);

			System.out.println("신고 완료");

			model.addAttribute("report", "게시글에 대한 신고가 완료 되었습니다.");

			return "daily/dailyReport";

		}	
		
	
}
