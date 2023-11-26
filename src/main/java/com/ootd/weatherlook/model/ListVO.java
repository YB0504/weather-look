package com.ootd.weatherlook.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Alias("listVO")
public class ListVO {
	private String board_type;
	private int post_id;
	private String title;
	private String nick;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reg_date;
	private int read_count;
}