package com.ootd.weatherlook.service;

import java.util.List;
import java.util.Map;

import com.ootd.weatherlook.model.MainBoard;
import com.ootd.weatherlook.model.Report;
import com.ootd.weatherlook.model.Search;
import com.ootd.weatherlook.model.SearchResult;

public interface MainService {
	List<MainBoard> getMainList(Search search);
	int getMainCount(Search search);

	List<SearchResult> getSearchList(Search search);
	int getSearchCount(Search search);
}
