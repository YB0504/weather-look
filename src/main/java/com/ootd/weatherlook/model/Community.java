package com.ootd.weatherlook.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("comm")
public class Community {

	private int post_id;
	private String title;
	private int read_count;
	private Date reg_date;
	private String content;
	private int type_id;
	private String nick;
	private String category;
}
