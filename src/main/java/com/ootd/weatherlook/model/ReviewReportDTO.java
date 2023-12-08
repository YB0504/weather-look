package com.ootd.weatherlook.model;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("reviewReport")
public class ReviewReportDTO {
	private int report_id;
	private String reason;
	private int post_id;
	private Date report_date;
}
