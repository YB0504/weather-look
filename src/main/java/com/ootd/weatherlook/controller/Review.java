package com.ootd.weatherlook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ootd.weatherlook.service.ReviewService;

@Controller
public class Review {

	@Autowired
	private ReviewService service;
}
