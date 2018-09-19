package kr.co.koscom.oppf.apt.dataSet.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;
import lombok.Data;

/**
 * Created by LSH on 2017. 2. 23..
 */
@Data
@SuppressWarnings("serial")
public class DataSetSummaryVO extends DataSetCommonVO{

    /**
     *Summary -> accountpProfile
     * */
    private long cashBalance;//현금잔고
    private long substitute;//대용금
    private long receivable;//미수 미납금
    private long subsMargin;//대용 증거금
    private long loanCredit;//대출 신용금
    private long valAtTrade;//유가증권 매수 금액
    private long proLoss;//유가증권 평가 금액
    private long valueAtCur;//유가증권 평가 손익
    private long totalAccVal;//총 평가 금액
    private long cashAvWithdraw;//출금 가능액
    private long amt;//포트폴리오 현금 잔고

}
