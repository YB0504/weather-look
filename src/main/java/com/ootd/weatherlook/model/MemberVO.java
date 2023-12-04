package com.ootd.weatherlook.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("memberVO")
public class MemberVO {
	private String nick;
	private String id;
	private String passwd;
	private String profile_image;
	private String address;
	private String phone;
	private String admin_role;
}
