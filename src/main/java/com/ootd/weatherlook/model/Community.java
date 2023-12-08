package com.ootd.weatherlook.model;

import org.apache.ibatis.type.Alias;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("comm")
public class Community {
	private int post_id;
	private String title;
	private int read_count;
	private Date reg_date;
	private String content;
	private String nick;
	private String category;
}
