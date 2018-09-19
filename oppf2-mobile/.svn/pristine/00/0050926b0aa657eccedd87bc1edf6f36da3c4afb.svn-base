package kr.co.koscom.oppfm.intAcnt.service;

import kr.co.koscom.oppfm.cmm.util.IntAccountWorkerUtil;
import kr.co.koscom.oppfm.intAcnt.model.*;

import java.util.List;
import java.util.concurrent.Future;

/**
 * FileName : BalanceService
 *
 * Description : BalanceService
 *
 * Created by LHT on 2017. 5. 24..
 */
public interface BalanceService {

    void setBalanceSummary(BalanceSummaryRes balanceSummary_org, BalanceSummaryRes balanceSummaryRes);

    void setBalanceResultData(List<Future<IntAccountWorkerUtil>> futures, List<Object> resultList, List<CommonFailRes> failList) throws Exception;

}
