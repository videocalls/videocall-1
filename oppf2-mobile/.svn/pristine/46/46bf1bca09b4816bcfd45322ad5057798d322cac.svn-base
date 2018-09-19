package kr.co.koscom.oppfm.intAcnt.service.impl;

import com.google.gson.Gson;
import kr.co.koscom.oppfm.cmm.util.CommonFunctionUtil;
import kr.co.koscom.oppfm.cmm.util.IntAccountWorkerUtil;
import kr.co.koscom.oppfm.intAcnt.model.CommonFailRes;
import kr.co.koscom.oppfm.intAcnt.model.InterestGroupRes;
import kr.co.koscom.oppfm.intAcnt.model.InterestResultRes;
import kr.co.koscom.oppfm.intAcnt.service.InterestService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author lee
 * @date 2017-05-24
 */
@Service
public class IinterestServiceImpl implements InterestService {

    private static final String ERROR_CATEGORY = "op-spt";
    private static final String ERROR_READ_TIME_OUT = "SocketTimeoutException: Read timed out";

    @Override
    public void setInterestResultData(List<Future<IntAccountWorkerUtil>> futures, List<Object> resultList, List<CommonFailRes> failList) throws Exception {

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
                InterestResultRes interestResultRes = new Gson().fromJson(jsonObject.toString(),InterestResultRes.class);
                resultList.add(setInterestData(interestResultRes, worker.getComId(), worker.getVtAccNo()));
            }else{
                CommonFailRes commonFailRes = new CommonFailRes(worker.getComId(), worker.getVtAccNo(), worker.getVtAccAlias(), worker.getResponseEntity().getBody());
                commonFailRes.setComName(CommonFunctionUtil.companyName(worker.getComId()));
                failList.add(commonFailRes);
            }
        }
    }

    private InterestResultRes setInterestData(InterestResultRes interestResultRes, String comId, String vtAccNo) throws Exception {

        if(null != interestResultRes && null != interestResultRes.getInterestSymbolListResponseBody().getGroupList().getGroup()){
            for(InterestGroupRes vo : interestResultRes.getInterestSymbolListResponseBody().getGroupList().getGroup()){
                vo.setComName(CommonFunctionUtil.companyName(comId));
                List<String> isinNameList = new ArrayList<>();
                for(String isinCode : vo.getIsinCode()){
                    isinNameList.add(CommonFunctionUtil.isinName(isinCode));
                }
                vo.setIsinName(isinNameList);
            }
        }

        return interestResultRes;
    }

}
