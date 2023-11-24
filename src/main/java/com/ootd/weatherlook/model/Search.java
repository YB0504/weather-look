package com.ootd.weatherlook.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("search")
public class Search {

	private String keyword;
	private Weather weather;
	private int page;
	private int numberset;
	private int startrow;
	private int endrow;
	private String type_name;
	private int post_id;
	
	
}
