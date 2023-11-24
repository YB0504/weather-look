package com.ootd.weatherlook.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("commre")
public class Community_Re {

	private int re_id;
	private String re_content;
	private int re_ref;
	private int re_level;
	private int step;
	private Date rd_regdate;
	private String nick;
	private int post_id;
}
