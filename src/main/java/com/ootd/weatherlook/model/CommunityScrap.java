package com.ootd.weatherlook.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("commscrap")
public class CommunityScrap {

	private int scrap_id;
	private int post_id;
	private String nick;
}
