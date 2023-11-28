package com.ootd.weatherlook.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("commre")
public class CommunityRe {

	private int re_id;
	private int post_id;
	private String re_content;
	private int re_ref;
	private int re_level;
	private int re_step;
	private Date re_regdate;
	private String nick;
}
