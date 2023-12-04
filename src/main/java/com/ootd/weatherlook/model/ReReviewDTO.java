package com.ootd.weatherlook.model;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("reviewReply")
public class ReReviewDTO {
	private int re_id;
	private String re_content;
	private int re_ref;
	private int re_level;
	private int re_step;
	private Date re_regdate;
	private String nick;
	private int post_id;
}
