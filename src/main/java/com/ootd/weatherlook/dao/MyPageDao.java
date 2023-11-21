package com.ootd.weatherlook.dao;

import com.ootd.weatherlook.model.*;

import java.util.List;

public interface MyPageDao {
	int getListCount() throws Exception;

	List<Board> getAllPostList(String nick) throws Exception;

	Member getMember(String nick) throws Exception;

	List<Comment> getAllCommentList(String nick) throws Exception;

	List<Like> getAllLikeList(String nick) throws Exception;

	List<Scrap> getAllScrapList(String nick) throws Exception;
}
