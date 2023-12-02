package com.ootd.weatherlook.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("reviewReport")
public class reviewReportDTO {
	private int report_id;
	private String reason;
	private int post_id;
}
