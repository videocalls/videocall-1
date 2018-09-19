package kr.co.koscom.oppfm.intAcnt.service.impl;

import com.google.gson.Gson;
import kr.co.koscom.oppfm.cmm.util.CommonFunctionUtil;
import kr.co.koscom.oppfm.cmm.util.IntAccountWorkerUtil;
import kr.co.koscom.oppfm.intAcnt.model.*;
import kr.co.koscom.oppfm.intAcnt.service.PortfolioService;
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
public class PortfolioServiceImpl implements PortfolioService {

    private static final String ERROR_CATEGORY = "op-spt";
    private static final String ERROR_READ_TIME_OUT = "SocketTimeoutException: Read timed out";

    @Override
    public void setPortfolioResultData(List<Future<IntAccountWorkerUtil>> futures, List<Object> resultList, List<CommonFailRes> failList) throws Exception {

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
                PortfolioResultRes portfolioResultRes = new Gson().fromJson(jsonObject.toString(),PortfolioResultRes.class);
                resultList.add(setPortfolioData(portfolioResultRes, worker.getComId(), worker.getVtAccNo()));
            }else{
                CommonFailRes commonFailRes = new CommonFailRes(worker.getComId(), worker.getVtAccNo(), worker.getVtAccAlias(), worker.getResponseEntity().getBody());
                commonFailRes.setComName(CommonFunctionUtil.companyName(worker.getComId()));
                failList.add(commonFailRes);
            }
        }
    }

    private PortfolioResultRes setPortfolioData(PortfolioResultRes portfolioResultRes, String comId, String vtAccNo) throws Exception {

        if(null != portfolioResultRes && null != portfolioResultRes.getPortfolioList().getPortfolio()){
            PortfolioRes portfolioViewVO = portfolioResultRes.getPortfolioList().getPortfolio();
            //주식 리스트
            List<PortfolioEquityListRes> equityList = portfolioViewVO.getEquityList();
            for(PortfolioEquityListRes vo : equityList){
                vo.setVtAccNo(vtAccNo);
                vo.setComName(CommonFunctionUtil.companyName(comId));
                vo.setIsinName(CommonFunctionUtil.isinName(vo.getIsinCode()));
                vo.setAssetType(CommonFunctionUtil.equityAssetTypeName(vo.getAssetType()));
            }
            //펀드 리스트
            List<PortfolioFundListRes> fundList = portfolioViewVO.getFundList();
            for(PortfolioFundListRes vo : fundList){
                vo.setVtAccNo(vtAccNo);
                vo.setComName(CommonFunctionUtil.companyName(comId));
            }
            //기타 리스트
            List<PortfolioEtcListRes> etcList = portfolioViewVO.getEtcList();
            for(PortfolioEtcListRes vo : etcList){
                vo.setVtAccNo(vtAccNo);
                vo.setAssetType(CommonFunctionUtil.etcAssetTypeName(vo.getAssetType()));
                vo.setComName(CommonFunctionUtil.companyName(comId));
            }
        }

        return portfolioResultRes;
    }

}
