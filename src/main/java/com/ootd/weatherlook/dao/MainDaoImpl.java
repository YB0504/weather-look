package com.ootd.weatherlook.dao;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ootd.weatherlook.model.MainBoard;
import com.ootd.weatherlook.model.Report;
import com.ootd.weatherlook.model.Search;
import com.ootd.weatherlook.model.SearchResult;

@Repository
@RequiredArgsConstructor
public class MainDaoImpl implements MainDao {

	private final SqlSession session;

	@Override
	public List<MainBoard> getMainList(Search search) {
		System.out.println("MainDaoImpl.getMainList");
		return session.selectList("main.mainlist", search);
	}

	@Override
	public int getMainCount(Search search) {
		System.out.println("MainDaoImpl.getMainCount");
		return session.selectOne("main.maincount", search);
	}

	@Override
	public List<SearchResult> getSearchList(Search search) {
		System.out.println("MainDaoImpl.getSearchList");
		return session.selectList("main.searchlist", search);
	}

	@Override
	public int getSearchCount(Search search) {
		System.out.println("MainDaoImpl.getSearchCount");
		return session.selectOne("main.searchcount", search);
	}
}
