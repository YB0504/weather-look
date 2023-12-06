package com.ootd.weatherlook.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("dailylike")
public class Dailylike {

	private int post_id;	// 글번호값
	private String nick;	// 닉네임
	private int like_id;	// 좋아요번호
	private String like_check;	// 좋아요 체크여부 (새로 추가한 컬럼)
}
