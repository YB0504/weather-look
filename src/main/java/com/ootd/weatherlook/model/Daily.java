package com.ootd.weatherlook.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("daily")
public class Daily {
	private int post_id;
	private String title;
	private int read_count;
	private Date reg_date;
	private String content;
	private Double temperature;
	private Double latitude;
	private Double longitude;
	private String region;
	private String daily_file;
	private String nick;
}