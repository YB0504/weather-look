package com.ootd.weatherlook.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("weather")
public class Weather {

	private double latitude;
	private double longitude;
	private double temperature;

	
	
}
