package com.ootd.weatherlook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Report {
	
	@RequestMapping("sendReport")
	public String sendReport() {
		return "review/sendReport";
	}
	
}
