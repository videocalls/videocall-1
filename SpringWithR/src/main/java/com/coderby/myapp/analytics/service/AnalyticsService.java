package com.coderby.myapp.analytics.service;

import java.util.ArrayList;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderby.myapp.analytics.model.IrisVO;
import com.coderby.myapp.analytics.model.SampleVO;

@Service
public class AnalyticsService implements IAnalyticsService {

	private static final Logger logger = LoggerFactory.getLogger(AnalyticsService.class);
	
	@Autowired
	Rengine rEngine;
	
	@Override
	public ArrayList<IrisVO> getAvgPetalBySpecies() {
		ArrayList<IrisVO> irisList = new ArrayList<IrisVO>();
        try {
            String[] species = {"setosa", "versicolor", "virginica"};
            REXP result = rEngine.eval("tapply(iris$Petal.Length, iris$Species, mean)");
            REXP result2 = rEngine.eval("tapply(iris$Petal.Width, iris$Species, mean)");

            double resultList[] = result.asDoubleArray();
            double resultList2[] = result2.asDoubleArray();
            for(int i=0; i<resultList.length; i++) {
                IrisVO iris = new IrisVO();
                iris.setSpecies(species[i]);
                iris.setPetalLength(resultList[i]);
                iris.setPetalWidth(resultList2[i]);
                irisList.add(iris);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return irisList;

	}
	
	@Override
	public ArrayList<SampleVO> getAvgPetalBySpecies2() {
		ArrayList<SampleVO> irisList = new ArrayList<SampleVO>();
        try {
//            String[] species = {"setosa", "versicolor", "virginica"};
            REXP result = rEngine.eval("tapply(iris$Petal.Length, iris$Species, mean)");
            REXP result2 = rEngine.eval("tapply(iris$Petal.Width, iris$Species, mean)");

            SampleVO sample1 = new SampleVO();
            sample1.setName("P.L mean");
            sample1.setType("column");
            sample1.setData(result.asDoubleArray());
            irisList.add(sample1);
            SampleVO sample2 = new SampleVO();
            sample2.setName("P.W mean");
            sample2.setType("column");
            sample2.setData(result2.asDoubleArray());
            irisList.add(sample2);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return irisList;
	}

}
