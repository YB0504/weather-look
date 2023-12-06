package com.ootd.weatherlook.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("scrap")
public class ScrapDTO {
	private int scrap_id;
	private String nick;
	private int post_id;
}
