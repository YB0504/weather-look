package com.ootd.weatherlook.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("comment")
public class Comment {
	private int re_id;
	private String re_content;
	private int re_ref;
	private int re_level;
	private int re_step;
	private Date re_regdate;
	private int post_id;
	private String nick;
}
