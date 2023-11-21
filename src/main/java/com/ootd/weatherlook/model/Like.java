package com.ootd.weatherlook.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("like")
public class Like {
	private int like_id;
	private int type_id;
	private int post_id;
	private String title;
	private String writer_nick;
}
