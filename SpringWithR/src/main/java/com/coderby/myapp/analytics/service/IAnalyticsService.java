package com.coderby.myapp.analytics.service;

import java.util.ArrayList;

import com.coderby.myapp.analytics.model.IrisVO;
import com.coderby.myapp.analytics.model.SampleVO;

public interface IAnalyticsService {
	ArrayList<IrisVO> getAvgPetalBySpecies();
	ArrayList<SampleVO> getAvgPetalBySpecies2();
}
