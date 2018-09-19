package kr.co.koscom.oppfm.intAcnt.service.impl;

import com.google.gson.Gson;
import kr.co.koscom.oppfm.cmm.util.CommonFunctionUtil;
import kr.co.koscom.oppfm.cmm.util.CommonUtil;
import kr.co.koscom.oppfm.cmm.util.IntAccountWorkerUtil;
import kr.co.koscom.oppfm.intAcnt.model.*;
import kr.co.koscom.oppfm.intAcnt.service.BalanceService;
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
public class BalanceServiceImpl implements BalanceService{

    private static final String ERROR_CATEGORY = "op-spt";
    private static final String ERROR_READ_TIME_OUT = "SocketTimeoutException: Read timed out";

    @Override
    public void setBalanceSummary(BalanceSummaryRes balanceSummary_org, BalanceSummaryRes balanceSummaryRes) {
        //현금잔고
        balanceSummary_org.setCashBalance(CommonUtil.isNullToLong(balanceSummary_org.getCashBalance()) + CommonUtil.isNullToLong(balanceSummaryRes.getCashBalance()));
        //대용금
        balanceSummary_org.setSubstitute(CommonUtil.isNullToLong(balanceSummary_org.getSubstitute()) + CommonUtil.isNullToLong(balanceSummaryRes.getSubstitute()));
        //미수/미납금
        balanceSummary_org.setReceivable(CommonUtil.isNullToLong(balanceSummary_org.getReceivable()) + CommonUtil.isNullToLong(balanceSummaryRes.getReceivable()));
        //대용증거금
        balanceSummary_org.setSubsMargin(CommonUtil.isNullToLong(balanceSummary_org.getSubsMargin()) + CommonUtil.isNullToLong(balanceSummaryRes.getSubsMargin()));
        //대출/신용금
        balanceSummary_org.setLoanCredit(CommonUtil.isNullToLong(balanceSummary_org.getLoanCredit()) + CommonUtil.isNullToLong(balanceSummaryRes.getLoanCredit()));
        //유가증권매수금액
        balanceSummary_org.setValAtTrade(CommonUtil.isNullToLong(balanceSummary_org.getValAtTrade()) + CommonUtil.isNullToLong(balanceSummaryRes.getValAtTrade()));
        //유가증권평가금액
        balanceSummary_org.setValueAtCur(CommonUtil.isNullToLong(balanceSummary_org.getValueAtCur()) + CommonUtil.isNullToLong(balanceSummaryRes.getValueAtCur()));
        //유가증권평가손익
        balanceSummary_org.setProLoss(CommonUtil.isNullToLong(balanceSummary_org.getProLoss()) + CommonUtil.isNullToLong(balanceSummaryRes.getProLoss()));
        //총평가금액
        balanceSummary_org.setTotalAccVal(CommonUtil.isNullToLong(balanceSummary_org.getTotalAccVal()) + CommonUtil.isNullToLong(balanceSummaryRes.getTotalAccVal()));
        //출금가능액
        balanceSummary_org.setCashAvWithdraw(CommonUtil.isNullToLong(balanceSummary_org.getCashAvWithdraw()) + CommonUtil.isNullToLong(balanceSummaryRes.getCashAvWithdraw()));
    }

    @Override
    public void setBalanceResultData(List<Future<IntAccountWorkerUtil>> futures, List<Object> resultList, List<CommonFailRes> failList) throws Exception {

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
                BalanceResultRes balanceResultRes = new Gson().fromJson(jsonObject.toString(),BalanceResultRes.class);
                resultList.add(setBalanceData(balanceResultRes, worker.getComId(), worker.getVtAccNo()));
            }else{
                CommonFailRes commonFailRes = new CommonFailRes(worker.getComId(), worker.getVtAccNo(), worker.getVtAccAlias(), worker.getResponseEntity().getBody());
                commonFailRes.setComName(CommonFunctionUtil.companyName(worker.getComId()));
                failList.add(commonFailRes);
            }
        }

    }

    private BalanceResultRes setBalanceData(BalanceResultRes balanceResultRes, String comId, String vtAccNo) throws Exception {

        if(null != balanceResultRes && null != balanceResultRes.getBalanceList().getBalance()){
            BalanceRes balanceRes = balanceResultRes.getBalanceList().getBalance();
            //주식 리스트
            List<BalanceEquityListRes> equityList = balanceRes.getEquityList();
            for(BalanceEquityListRes vo : equityList){
                vo.setVtAccNo(vtAccNo);
                vo.setAssetType(CommonFunctionUtil.equityAssetTypeName(vo.getAssetType()));
                vo.setComName(CommonFunctionUtil.companyName(comId));
                vo.setIsinName(CommonFunctionUtil.isinName(vo.getIsinCode()));
            }
            //펀드 리스트
            List<BalanceFundListRes> fundList = balanceRes.getFundList();
            for(BalanceFundListRes vo : fundList){
                vo.setVtAccNo(vtAccNo);
                vo.setComName(CommonFunctionUtil.companyName(comId));
            }
            //기타 리스트
            List<BalanceEtcListRes> etcList = balanceRes.getEtcList();
            for(BalanceEtcListRes vo : etcList){
                vo.setVtAccNo(vtAccNo);
                vo.setAssetType(CommonFunctionUtil.etcAssetTypeName(vo.getAssetType()));
                vo.setComName(CommonFunctionUtil.companyName(comId));
            }
        }

        return balanceResultRes;
    }

}
