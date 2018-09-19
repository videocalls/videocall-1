package kr.co.koscom.oppf.apt.dataSet.service;

import lombok.Data;

/**
 * Created by LSH on 2017. 2. 23..
 */
@Data
@SuppressWarnings("serial")
public class DataSetTransactionVO extends DataSetCommonVO{

    /**
     * Transaction
     * */
    private String datasetTransactionNumber;//데이터셋 거래내역 등록번호
    private String isinCode;//종목 코드
    private String transDate;//거래 일자
    private String transType;//거래 구분
    private long changeAmt;//잔고 수량
    private long changeQuantity;//금액 증감
    private double quantity;//수량 증감

}
