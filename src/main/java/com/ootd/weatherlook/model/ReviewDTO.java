package com.ootd.weatherlook.model;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("review")
public class ReviewDTO {

	private int post_id;
	private String title;
	private String read_count;
	private Date reg_date;
	private String content;
	private String item_type;
	private String nick;
	private String review_file;
}
