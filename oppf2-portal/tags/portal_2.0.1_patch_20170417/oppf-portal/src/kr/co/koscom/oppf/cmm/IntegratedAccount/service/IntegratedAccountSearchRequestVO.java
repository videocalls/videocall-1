package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

/**
 * @author lee
 * @date 2017-02-15
 */
public class IntegratedAccountSearchRequestVO {

    private String customerId;
    //핀테크서비스 연계 확인용 핀테크 기업코드
    private String comId;
    //핀테크서비스 연계 확인용 핀테크 서비스코드
    private String srvId;
    //자산유형 CASH(현금), EQTY(주식), FUND(펀드), ETC(기타자산), ALL(전체)
    private String assetType;
    //종목코드
    private String isinCode;
    //매도, 매수 코드
    private String side;
    //조회시작날짜(YYYYMMDD)
    private String fromDate;
    //조회종료날짜(YYYYMMDD)
    private String toDate;
    //QTY(실잔고수량), RAT(잔고구성비율)
    private String rspType;

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getComId() {
        return comId;
    }
    public void setComId(String comId) {
        this.comId = comId;
    }
    public String getSrvId() {
        return srvId;
    }
    public void setSrvId(String srvId) {
        this.srvId = srvId;
    }
    public String getAssetType() {
        return assetType;
    }
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }
    public String getIsinCode() {
        return isinCode;
    }
    public void setIsinCode(String isinCode) {
        this.isinCode = isinCode;
    }
    public String getSide() {
        return side;
    }
    public void setSide(String side) {
        this.side = side;
    }
    public String getFromDate() {
        return fromDate;
    }
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
    public String getToDate() {
        return toDate;
    }
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    public String getRspType() {
        return rspType;
    }
    public void setRspType(String rspType) {
        this.rspType = rspType;
    }
}
