package com.ootd.weatherlook.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("listQueryVO")
public class ListQueryVO {
	private int startRow;
	private int endRow;
	private String nick;
}
