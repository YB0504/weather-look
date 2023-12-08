package com.ootd.weatherlook.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("like")
public class LikeDTO {
	private int like_id;
	private String nick;
	private int post_id;
}