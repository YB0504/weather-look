package com.ootd.weatherlook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ootd.weatherlook.service.CommLikeService;

@Controller
public class CommLikeController {

	@Autowired
	private CommLikeService clikes;
	
}
