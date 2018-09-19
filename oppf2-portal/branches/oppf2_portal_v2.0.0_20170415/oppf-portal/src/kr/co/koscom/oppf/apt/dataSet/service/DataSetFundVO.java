package kr.co.koscom.oppf.apt.dataSet.service;

import lombok.Data;

/**
 * Created by LSH on 2017. 2. 23..
 */
@Data
@SuppressWarnings("serial")
public class DataSetFundVO extends DataSetCommonVO{

    /**
     * Fund
     * */
    private String datasetType;//데이터셋 종류 -기본키
    private String datasetFundNumber;//데이터셋 펀드 등록 번호
    private String fundCode;//펀드표준코드
    private String fundName;//펀드이름
    private long valAtTrade;//매수금액
    private long valAtCur;//평가금액
    private long proLoss;//평가손익
    private String firstDateBuy;//최초매수일
    private String lastDateBuy;//최종매수일
    private String maturity;//만기일
    private double earningRate;//수익률
    private double quantity;//수량

}
