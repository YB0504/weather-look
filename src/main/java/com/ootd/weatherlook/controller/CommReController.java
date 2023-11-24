package com.ootd.weatherlook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ootd.weatherlook.service.CommReService;
import com.ootd.weatherlook.service.CommService;

@Controller
public class CommReController {

	@Autowired
	private CommReService res;
	@Autowired
	private CommService cs;
}
