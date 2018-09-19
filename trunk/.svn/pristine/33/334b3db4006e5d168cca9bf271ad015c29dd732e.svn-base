package kr.co.koscom.oppfm.intAcnt.service.impl;

import com.google.gson.Gson;
import kr.co.koscom.oppfm.cmm.util.CommonFunctionUtil;
import kr.co.koscom.oppfm.cmm.util.IntAccountWorkerUtil;
import kr.co.koscom.oppfm.intAcnt.model.CommonFailRes;
import kr.co.koscom.oppfm.intAcnt.model.TransactionRes;
import kr.co.koscom.oppfm.intAcnt.model.TransactionResultRes;
import kr.co.koscom.oppfm.intAcnt.service.TransactionService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author lee
 * @date 2017-05-24
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private static final String ERROR_CATEGORY = "op-spt";
    private static final String ERROR_READ_TIME_OUT = "SocketTimeoutException: Read timed out";

    @Override
    public void setTransactionResultData(List<Future<IntAccountWorkerUtil>> futures, List<Object> resultList, List<CommonFailRes> failList) throws Exception {

        for (Future<IntAccountWorkerUtil> future : futures) {
            IntAccountWorkerUtil worker = future.get();
            if(null == worker.getResponseEntity()){
                JSONObject failJson = new JSONObject();
                failJson.put("category", ERROR_CATEGORY);
                failJson.put("message", ERROR_READ_TIME_OUT);
                CommonFailRes commonFailRes = new CommonFailRes(worker.getComId(), worker.getVtAccNo(), worker.getVtAccAlias(), failJson.toString());
                commonFailRes.setComName(CommonFunctionUtil.companyName(worker.getComId()));
                failList.add(commonFailRes);
            }else if(HttpStatus.OK.equals(worker.getResponseEntity().getStatusCode())){
                JSONObject jsonObject =  new JSONObject(worker.getResponseEntity().getBody());
                TransactionResultRes transactionResultRes = new Gson().fromJson(jsonObject.toString(),TransactionResultRes.class);
                resultList.add(setTransactionData(transactionResultRes, worker.getComId(), worker.getVtAccNo()));
            }else{
                CommonFailRes commonFailRes = new CommonFailRes(worker.getComId(), worker.getVtAccNo(), worker.getVtAccAlias(), worker.getResponseEntity().getBody());
                commonFailRes.setComName(CommonFunctionUtil.companyName(worker.getComId()));
                failList.add(commonFailRes);
            }
        }
    }

    private TransactionResultRes setTransactionData(TransactionResultRes transactionResultRes, String comId, String vtAccNo) throws Exception {

        if(null != transactionResultRes && null != transactionResultRes.getTransList().getTransaction()){
            List<TransactionRes> transactionViewVOList = transactionResultRes.getTransList().getTransaction();
            for(TransactionRes vo : transactionViewVOList){
                vo.setComName(CommonFunctionUtil.companyName(comId));
                vo.setIsinName(CommonFunctionUtil.isinName(vo.getIsinCode()));
                vo.setTransType(CommonFunctionUtil.transTypeName(vo.getTransType()));
                vo.setVtAccNo(vtAccNo);
            }
        }
        return transactionResultRes;
    }
}
