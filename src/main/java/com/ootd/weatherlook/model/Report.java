package com.ootd.weatherlook.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("report")
public class Report {

	private int report_id;
	private String reason;
	private int type_id;
	private int post_id;
	private String title;
	private String type_name;
	
	
}
