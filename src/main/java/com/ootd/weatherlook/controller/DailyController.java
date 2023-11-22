package com.ootd.weatherlook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ootd.weatherlook.service.DailyService;

@Controller
public class DailyController {

	@Autowired
	private DailyService service;
	
	@RequestMapping("dailyform")
	public String dailyform() {
		return "daily/dailyform";
	}
}
