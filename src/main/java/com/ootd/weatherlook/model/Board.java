package com.ootd.weatherlook.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("board")
public class Board {
	//common
	private int post_id;
	private String title;
	private int read_count;
	private Date reg_date;
	private String content;
	private int type_id;
	private String nick;

	//daily
	private double temperature;
	private double latitude;
	private double longitude;

	//review
	private String item_type;
}
