package com.ootd.weatherlook.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("commlike")
public class CommunityLike {
	private int like_id;
	private int post_id;
	private String nick;
}
