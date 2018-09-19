package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lee
 * @date 2017-02-14
 */
public class InteGratedAccountViewVO {

    private int totalCount;
    private int successCount;
    private int failCount;
    private List<CommonFailViewVO> failList;
    private List<Object> resultList;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public List<Object> getResultList() {
        return resultList;
    }
    public void setResultList(List<Object> resultList) {
        List<Object> mergeList = new ArrayList<>();
        String vtAccNo = "";
        if(null != resultList && resultList.size() > 0){
            for(Object o : resultList){
                if(o instanceof BalanceResponseViewVO){
                    BalanceResponseViewVO vo = (BalanceResponseViewVO) o;
                    //가상계좌 번호 비교 후 일치하면 데이터 merge
                    if(vtAccNo.equals(vo.getAccInfo().getVtAccNo())){
                        BalanceResponseViewVO mergeVo = (BalanceResponseViewVO)mergeList.get(mergeList.size() - 1);
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
                }else if(o instanceof PortfolioResponseViewVO){
                    PortfolioResponseViewVO vo = (PortfolioResponseViewVO) o;
                    //가상계좌 번호 비교 후 일치하면 데이터 merge
                    if(vtAccNo.equals(vo.getAccInfo().getVtAccNo())){
                        PortfolioResponseViewVO mergeVo = (PortfolioResponseViewVO)mergeList.get(mergeList.size() - 1);
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
    public List<CommonFailViewVO> getFailList() {
        return failList;
    }
    public void setFailList(List<CommonFailViewVO> failList) {
        this.failList = failList;
    }
}
