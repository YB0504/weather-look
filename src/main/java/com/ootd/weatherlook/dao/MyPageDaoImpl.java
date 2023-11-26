package com.ootd.weatherlook.dao;

import com.ootd.weatherlook.model.*;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyPageDaoImpl implements MyPageDao {
	private final SqlSession session;

	@Override
	public MemberVO getMember(String nick) throws Exception {
		System.out.println("MyPageDaoImpl.getMember");
		return session.selectOne("myPage.get_member", nick);
	}

	@Override
	public int getPostCount(String nick) throws Exception {
		System.out.println("MyPageRepositoryImpl.getPostCount");
		return ((Integer) session.selectOne("myPage.postCount", nick)).intValue();
	}

	@Override
	public int getReplyCount(String nick) throws Exception {
		System.out.println("MyPageDaoImpl.getReplyCount");
		return ((Integer) session.selectOne("myPage.replyCount", nick)).intValue();
	}

	@Override
	public int getLikeCount(String nick) throws Exception {
		System.out.println("MyPageDaoImpl.getLikeCount");
		return ((Integer) session.selectOne("myPage.likeCount", nick)).intValue();
	}

	@Override
	public int getScrapCount(String nick) throws Exception {
		return ((Integer) session.selectOne("myPage.scrapCount", nick)).intValue();
	}

	@Override
	public List<ListVO> getAllPostList(ListQueryVO listQueryVO) throws Exception {
		System.out.println("MyPageRepositoryImpl.getAllPostList");
		return session.selectList("myPage.post_list", listQueryVO);
	}

	@Override
	public List<ListVO> getAllReplyList(ListQueryVO listQueryVO) throws Exception {
		System.out.println("MyPageDaoImpl.getAllReplyList");
		return session.selectList("myPage.reply_list", listQueryVO);
	}

	@Override
	public List<ListVO> getAllLikeList(ListQueryVO listQueryVO) throws Exception {
		System.out.println("MyPageDaoImpl.getAllLikeList");
		return session.selectList("myPage.like_list", listQueryVO);
	}

	@Override
	public List<ListVO> getAllScrapList(ListQueryVO listQueryVO) throws Exception {
		System.out.println("MyPageDaoImpl.getAllScrapList");
		return session.selectList("myPage.scrap_list", listQueryVO);
	}

	@Override
	public int updateMember(MemberVO memberVO) throws Exception {
		System.out.println("MyPageDaoImpl.updateMember");
		return session.update("myPage.updateMember", memberVO);
	}

	@Override
	public int deleteMember(String nick) throws Exception {
		System.out.println("MyPageDaoImpl.deleteMember");
		return session.delete("myPage.deleteMember", nick);
	}
}
