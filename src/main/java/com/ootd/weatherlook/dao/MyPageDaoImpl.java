package com.ootd.weatherlook.dao;

import com.ootd.weatherlook.model.*;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyPageDaoImpl implements MyPageDao{
	private final SqlSession session;

	@Override
	public int getListCount() throws Exception {
		System.out.println("MyPageRepositoryImpl.getListCount");
		return ((Integer) session.selectOne("myPage.count")).intValue();
	}

	@Override
	public List<Board> getAllPostList(String nick) throws Exception {
		System.out.println("MyPageRepositoryImpl.getBoardList");
		return session.selectList("myPage.post_list", nick);
	}

	@Override
	public Member getMember(String nick) throws Exception {
		System.out.println("MyPageDaoImpl.getMember");
		return session.selectOne("myPage.get_member", nick);
	}

	@Override
	public List<Comment> getAllCommentList(String nick) throws Exception {
		System.out.println("MyPageDaoImpl.getAllCommentList");
		return session.selectList("myPage.comment_list", nick);
	}

	@Override
	public List<Like> getAllLikeList(String nick) throws Exception {
		System.out.println("MyPageDaoImpl.getAllLikeList");
		return session.selectList("myPage.like_list", nick);
	}

	@Override
	public List<Scrap> getAllScrapList(String nick) throws Exception {
		System.out.println("MyPageDaoImpl.getAllScrapList");
		return session.selectList("myPage.scrap_list", nick);
	}

	@Override
	public int updateMember(Member member) throws Exception {
		System.out.println("MyPageDaoImpl.updateMember");
		return session.update("myPage.updateMember", member);
	}

	@Override
	public int deleteMember(String nick) throws Exception {
		System.out.println("MyPageDaoImpl.deleteMember");
		return session.delete("myPage.deleteMember", nick);
	}
}
