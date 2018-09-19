package kr.co.koscom.oppfm.intAcnt.service;

import kr.co.koscom.oppfm.cmm.util.IntAccountWorkerUtil;
import kr.co.koscom.oppfm.intAcnt.model.CommonFailRes;

import java.util.List;
import java.util.concurrent.Future;

/**
 * FileName : TransactionService
 *
 * Description : TransactionService
 *
 * Created by LHT on 2017. 5. 25..
 */
public interface TransactionService {

    void setTransactionResultData(List<Future<IntAccountWorkerUtil>> futures, List<Object> resultList, List<CommonFailRes> failList) throws Exception;

}
