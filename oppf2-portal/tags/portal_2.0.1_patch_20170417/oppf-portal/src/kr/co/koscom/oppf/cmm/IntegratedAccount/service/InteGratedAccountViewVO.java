package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

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
        this.resultList = resultList;
    }
    public List<CommonFailViewVO> getFailList() {
        return failList;
    }
    public void setFailList(List<CommonFailViewVO> failList) {
        this.failList = failList;
    }
}
