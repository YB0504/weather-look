package com.ootd.weatherlook.service;

import com.ootd.weatherlook.dao.MyPageDao;
import com.ootd.weatherlook.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService{
	private final MyPageDao myPageDao;

	@Override
	public int getListCount() throws Exception{
		System.out.println("MyPageServiceImpl.getListCount");
		return getListCount();
	}

	@Override
	public List<Board> getAllPostList(String nick) throws Exception{
		System.out.println("MyPageServiceImpl.getAllPostList");
		return myPageDao.getAllPostList(nick);
	}

	@Override
	public Member getMember(String nick) throws Exception {
		System.out.println("MyPageServiceImpl.getMember");
		return myPageDao.getMember(nick);
	}

	@Override
	public List<Comment> getAllCommentList(String nick) throws Exception {
		System.out.println("MyPageServiceImpl.getAllCommentList");
		return myPageDao.getAllCommentList(nick);
	}

	@Override
	public List<Like> getAllLikeList(String nick) throws Exception {
		System.out.println("MyPageServiceImpl.getAllLikeList");
		return myPageDao.getAllLikeList(nick);
	}

	@Override
	public List<Scrap> getAllScrapList(String nick) throws Exception {
		System.out.println("MyPageServiceImpl.getAllScrapList");
		return myPageDao.getAllScrapList(nick);
	}

}
