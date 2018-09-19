package kr.co.koscom.oppfm.intAcnt.model;

import lombok.Data;

/**
 * FileName : IntAccountBalanceRes
 *
 * Description : IntAccountBalance Response
 *
 * Created by LHT on 2017. 5. 23..
 */
@Data
public class IntAccountBalanceRes extends IntAccountResultRes {

    private static final long serialVersionUID = -4763717180156555792L;
    private BalanceResult totalResult;

    @Data
    public static class BalanceResult{
        private BalanceSummaryRes summary;
    }
}
