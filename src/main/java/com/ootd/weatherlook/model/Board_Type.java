package com.ootd.weatherlook.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("board_type")
public class Board_Type {
	private int type_id;
	private String type_name;
}
