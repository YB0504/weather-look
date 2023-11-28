package com.ootd.weatherlook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ootd.weatherlook.model.Community;
import com.ootd.weatherlook.model.CommunityRe;
import com.ootd.weatherlook.service.CommReService;
import com.ootd.weatherlook.service.CommService;

import ch.qos.logback.core.util.SystemInfo;

@Controller
public class CommReController {

	@Autowired
	private CommService cs;

	@Autowired
	private CommReService crs;
	
	@RequestMapping("crlist")
	public String slist(int post_id, Model model) {
		Community comm = cs.getCommunity(post_id);
		List<CommunityRe> crlist = crs.list(post_id);
		System.out.println("crlist : " + crlist);
		
		model.addAttribute("crlist", crlist);
		model.addAttribute("comm", comm);
		return "comm/crlist";
		
	}
	
	@RequestMapping("crInsert")
	public String sInsert(CommunityRe cr, Model model) {
		crs.insert(cr);
		System.out.println("crInsert");
		return "redirect:crlist?post_id="+cr.getPost_id();
	}
	
	@RequestMapping("repDelete")
	public String delete(CommunityRe cr, Model model) {
		crs.delete(cr.getRe_id());
		return "redirect:crlist?post_id=" + cr.getPost_id();
	}

	@RequestMapping("repUpdate")
	public String repUpdate(CommunityRe cr, Model model) {
		crs.update(cr);
		System.out.println("repUpdate : ");
		return "redirect:crlist?post_id="+ cr.getPost_id();
	}
	
	
	
}