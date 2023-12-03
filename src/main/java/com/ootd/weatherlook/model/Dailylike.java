package com.ootd.weatherlook.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("dailylike")
public class Dailylike {

	private int post_id;	//r_num
	private String nick;	//nickname
	private int like_id;	// timestamp like_dt;
	
}
