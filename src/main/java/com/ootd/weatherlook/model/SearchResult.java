package com.ootd.weatherlook.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("searchresult")
public class SearchResult {

	private int type_id;
	private String type_name;
	
	private int post_id;
	private Date reg_date;
	
	private String title;
	private String nick;
	
	private int read_count;
	private int likes;
	private int scraps;
	
	private double temperature;
	private double latitude;
	private double longitude;
	
}
