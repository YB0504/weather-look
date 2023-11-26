package com.ootd.weatherlook.service;

import com.ootd.weatherlook.dao.MyPageDao;
import com.ootd.weatherlook.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService{
	private final MyPageDao myPageDao;

	@Override
	public MemberVO getMember(String nick) throws Exception {
		System.out.println("MyPageServiceImpl.getMember");
		return myPageDao.getMember(nick);
	}

	@Override
	public int getPostCount(String nick) throws Exception{
		System.out.println("MyPageServiceImpl.getPostCount");
		return myPageDao.getPostCount(nick);
	}

	@Override
	public int getReplyCount(String nick) throws Exception {
		System.out.println("MyPageServiceImpl.getReplyCount");
		return myPageDao.getReplyCount(nick);
	}

	@Override
	public int getLikeCount(String nick) throws Exception {
		System.out.println("MyPageServiceImpl.getLikeCount");
		return myPageDao.getLikeCount(nick);
	}

	@Override
	public int getScrapCount(String nick) throws Exception {
		System.out.println("MyPageServiceImpl.getScrapCount");
		return myPageDao.getScrapCount(nick);
	}

	@Override
	public List<ListVO> getAllPostList(ListQueryVO listQueryVO) throws Exception{
		System.out.println("MyPageServiceImpl.getAllPostList");
		return myPageDao.getAllPostList(listQueryVO);
	}

	@Override
	public List<ListVO> getAllReplyList(ListQueryVO listQueryVO) throws Exception {
		System.out.println("MyPageServiceImpl.getAllReplyList");
		return myPageDao.getAllReplyList(listQueryVO);
	}

	@Override
	public List<ListVO> getAllLikeList(ListQueryVO listQueryVO) throws Exception {
		System.out.println("MyPageServiceImpl.getAllLikeList");
		return myPageDao.getAllLikeList(listQueryVO);
	}

	@Override
	public List<ListVO> getAllScrapList(ListQueryVO listQueryVO) throws Exception {
		System.out.println("MyPageServiceImpl.getAllScrapList");
		return myPageDao.getAllScrapList(listQueryVO);
	}

	@Override
	public int updateMember(MemberVO memberVO) throws Exception{
		System.out.println("MyPageServiceImpl.updateMember");
		return myPageDao.updateMember(memberVO);
	}

	@Override
	public int deleteMember(String nick) throws Exception {
		System.out.println("MyPageServiceImpl.deleteMember");
		return myPageDao.deleteMember(nick);
	}
}
