package com.ootd.weatherlook.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("member")
public class MemberDTO {

	private String nick;
	private String id;
	private String passwd;
	private String profile_image;
	private String address;
	private String phone;
	private String oauth_token;
	private String admin_role;
}
