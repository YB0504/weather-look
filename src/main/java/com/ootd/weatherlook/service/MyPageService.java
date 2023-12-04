package com.ootd.weatherlook.service;

import com.ootd.weatherlook.model.*;

import java.util.List;

public interface MyPageService {
	MemberVO getMember(String nick) throws Exception;

	int getPostCount(String nick) throws Exception;

	int getReplyCount(String nick) throws Exception;

	int getLikeCount(String nick) throws Exception;

	int getScrapCount(String nick) throws Exception;

	List<ListVO> getAllPostList(ListQueryVO listQueryVO) throws Exception;

	List<ListVO> getAllReplyList(ListQueryVO listQueryVO) throws Exception;

	List<ListVO> getAllLikeList(ListQueryVO listQueryVO) throws Exception;

	List<ListVO> getAllScrapList(ListQueryVO listQueryVO) throws Exception;

	int updateMember(MemberVO memberVO) throws Exception;

	int deleteMember(String nick) throws Exception;
}
