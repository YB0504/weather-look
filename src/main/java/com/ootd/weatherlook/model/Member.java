package com.ootd.weatherlook.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("member")
public class Member {
	private String nick;
	private String id;
	private String passwd;
	private String profile_image;
	private String address;
	private String phone;
	private String token;
	private String admin_role;
}
