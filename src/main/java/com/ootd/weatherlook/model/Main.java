package com.ootd.weatherlook.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("main")
public class Main {

	private int type_id;
	private int post_id;
	private Date reg_date;
	private String title;
	private String nick;
	
	private String image_path;
	
	private String type_name;
	private int likes;

	
	
}
