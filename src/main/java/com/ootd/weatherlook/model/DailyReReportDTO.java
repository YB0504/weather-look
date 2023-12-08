package com.ootd.weatherlook.model;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("dailyReReport")
public class DailyReReportDTO {
		private int report_id;
		private String reason;
		private Date report_date;
		private int re_id;
}
