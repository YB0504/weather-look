package com.ootd.weatherlook.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ootd.weatherlook.dao.MainDao;
import com.ootd.weatherlook.model.MainBoard;
import com.ootd.weatherlook.model.Search;
import com.ootd.weatherlook.model.SearchResult;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

	private final MainDao mainDao;

	@Override
	public List<MainBoard> getMainList(Search search) {
		System.out.println("MainServiceImpl.getMainList");
		return mainDao.getMainList(search);
	}

	@Override
	public List<SearchResult> getSearchList(Search search) {
		System.out.println("MainServiceImpl.getSearchList");
		return mainDao.getSearchList(search);
	}

	@Override
	public int getSearchCount(Search search) {
		System.out.println("MainServiceImpl.getSearchCount");
		return mainDao.getSearchCount(search);
	}

	@Override
	public int getMainCount(Search search) {
		System.out.println("MainServiceImpl.getMainCount");
		return mainDao.getMainCount(search);
	}
}
