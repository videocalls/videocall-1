package kr.co.koscom.oppfm.intAcnt.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName : IntAccountResultRes
 *
 * Description : IntAccountResult Response
 *
 * Created by LHT on 2017. 5. 23..
 */
@Data
public class IntAccountResultRes extends CommonVO {

    private static final long serialVersionUID = 2534024902494131250L;
    private int totalCount;
    private int successCount;
    private int failCount;
    private List<CommonFailRes> failList;
    private List<Object> resultList;

    public void setResultList(List<Object> resultList) {
        List<Object> mergeList = new ArrayList<>();
        String vtAccNo = "";
        if(null != resultList && resultList.size() > 0){
            for(Object o : resultList){
                if(o instanceof BalanceResultRes){
                    BalanceResultRes vo = (BalanceResultRes) o;
                    //가상계좌 번호 비교 후 일치하면 데이터 merge
                    if(vtAccNo.equals(vo.getAccInfo().getVtAccNo())){
                        BalanceResultRes mergeVo = (BalanceResultRes)mergeList.get(mergeList.size() - 1);
                        if("CASH".equals(vo.getBalanceResponseBody().getQueryType().getAssetType())){
                            //CASH 결과값 merge
                            mergeVo.getBalanceList().getBalance().setSummary(vo.getBalanceList().getBalance().getSummary());
                        }else if("EQTY".equals(vo.getBalanceResponseBody().getQueryType().getAssetType())){
                            //주식 리스트 결과값 merge
                            mergeVo.getBalanceList().getBalance().setEquityList(vo.getBalanceList().getBalance().getEquityList());
                        }else if("FUND".equals(vo.getBalanceResponseBody().getQueryType().getAssetType())){
                            //펀드 리스트 결과값 merge
                            mergeVo.getBalanceList().getBalance().setFundList(vo.getBalanceList().getBalance().getFundList());
                        }else if("ETC".equals(vo.getBalanceResponseBody().getQueryType().getAssetType())){
                            //기타 리스트 결과값 merge
                            mergeVo.getBalanceList().getBalance().setEtcList(vo.getBalanceList().getBalance().getEtcList());
                        }
                    }else{
                        mergeList.add(vo);
                    }
                    vtAccNo = vo.getAccInfo().getVtAccNo();
                }else if(o instanceof PortfolioResultRes){
                    PortfolioResultRes vo = (PortfolioResultRes) o;
                    //가상계좌 번호 비교 후 일치하면 데이터 merge
                    if(vtAccNo.equals(vo.getAccInfo().getVtAccNo())){
                        PortfolioResultRes mergeVo = (PortfolioResultRes)mergeList.get(mergeList.size() - 1);
                        if("EQTY".equals(vo.getPortfolioResponseBody().getQueryType().getAssetType())){
                            //주식 리스트 결과값 merge
                            mergeVo.getPortfolioList().getPortfolio().setEquityList(vo.getPortfolioList().getPortfolio().getEquityList());
                        }else if("FUND".equals(vo.getPortfolioResponseBody().getQueryType().getAssetType())){
                            //펀드 리스트 결과값 merge
                            mergeVo.getPortfolioList().getPortfolio().setFundList(vo.getPortfolioList().getPortfolio().getFundList());
                        }else if("ETC".equals(vo.getPortfolioResponseBody().getQueryType().getAssetType())){
                            //기타 리스트 결과값 merge
                            mergeVo.getPortfolioList().getPortfolio().setEtcList(vo.getPortfolioList().getPortfolio().getEtcList());
                        }
                    }else{
                        mergeList.add(vo);
                    }
                    vtAccNo = vo.getAccInfo().getVtAccNo();
                }
            }
        }
        this.resultList = mergeList.size() > 0 ? mergeList : resultList;
    }
}
