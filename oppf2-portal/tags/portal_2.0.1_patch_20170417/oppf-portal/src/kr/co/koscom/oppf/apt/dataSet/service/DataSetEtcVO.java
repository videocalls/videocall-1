package kr.co.koscom.oppf.apt.dataSet.service;

import lombok.Data;

/**
 * Created by LSH on 2017. 2. 23..
 */
@Data
@SuppressWarnings("serial")
public class DataSetEtcVO extends DataSetCommonVO{
    /**
     * Etc
     * */
    private String datasetType;//데이터셋 종류 -기본키
    private String datasetEtcNumber;//데이터셋 기타 등록 번호 -기본키
    private String assetType;//상품 구분
    private String assetName;//상품명
    private double quantity;//수량
    private long valAtTrade;//매수 금액
    private long valueAtCur;//평가 금액
    private double earningRate;//수익률
    private String isinCode;//종목 코드

}
