package com.ootd.weatherlook.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("scrap")
public class Scrap {
	private int scrap_id;
	private int type_id;
	private int post_id;
	private String title;
	private String writer_nick;
}
