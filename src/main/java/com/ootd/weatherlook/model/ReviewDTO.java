package com.ootd.weatherlook.model;

import java.sql.Date;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("review")
public class ReviewDTO {
	private int post_id;
	private String title;
	private String read_count;
	private Date reg_date;
	private String content;
	private String nick;
	private String category;
	private String item_type;
	private String review_file;
}
