package com.ootd.weatherlook.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("weather")
public class Weather {

	private double latitude;
	private double longitude;
	private double temperature;
	private double highest;
	private double lowest;
	private double deviation;
	private int ico;

	
	
}
