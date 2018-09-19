package kr.co.koscom.oppf.apt.fixMsg.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
 * Created by LSH on 2017. 3. 2..
 */

@SuppressWarnings("serial")
public class FixMessageVO extends ComDefaultListVO {

    /**
     * 공통
     * */
    private String companyId;
    private String companyName;
    private String companyNameKor;  //기업이름
    private String dt;              //일자
    private String senderCompId;    //송신자정보(CompanyID)
    private int seq;                //체결일련번호
    private String deliverCompId;   //수신자정보(CompanyID)
    private String msgType;         //메시지타입
    private String hubType;         //허브타입

    /**
     * 주문내역(send)
     * */
    private int orderCount;         //주문번호
    private String dataSendJson;    //전문내용(json)
    private String clientOrderId;   //cl_ord_id
    private String listOrderId;     //list_ord_id
    private String orderId;         //OrderId
    private String rejectYn;        //거부여부
    private String rejectYnNameKor; //거부여부 이름

    /**
     * 체결거부전문(receive)
     * */
    private String dataRecvJson;     //수신전문(json)
    private String processYn;        //재송신 처리여부

    /**
     * tabDivision(acceptor or Initiator)
     * */
    private String tabDivision;

    /**
     *hub type, msg type Name
     *  */
    private String hubTypeName;
    private String msgTypeName;

    /**
     * companyName
     * */
    private String senderCompName;
    private String deliverCompName;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNameKor() {
        return companyNameKor;
    }

    public void setCompanyNameKor(String companyNameKor) {
        this.companyNameKor = companyNameKor;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getSenderCompId() {
        return senderCompId;
    }

    public void setSenderCompId(String senderCompId) {
        this.senderCompId = senderCompId;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getDeliverCompId() {
        return deliverCompId;
    }

    public void setDeliverCompId(String deliverCompId) {
        this.deliverCompId = deliverCompId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getHubType() {
        return hubType;
    }

    public void setHubType(String hubType) {
        this.hubType = hubType;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public String getDataSendJson() {
        return dataSendJson;
    }

    public void setDataSendJson(String dataSendJson) {
        this.dataSendJson = dataSendJson;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public String getListOrderId() {
        return listOrderId;
    }

    public void setListOrderId(String listOrderId) {
        this.listOrderId = listOrderId;
    }

    public String getRejectYn() {
        return rejectYn;
    }

    public void setRejectYn(String rejectYn) {
        this.rejectYn = rejectYn;
    }

    public String getRejectYnNameKor() {
        return rejectYnNameKor;
    }

    public void setRejectYnNameKor(String rejectYnNameKor) {
        this.rejectYnNameKor = rejectYnNameKor;
    }

    public String getDataRecvJson() {
        return dataRecvJson;
    }

    public void setDataRecvJson(String dataRecvJson) {
        this.dataRecvJson = dataRecvJson;
    }

    public String getProcessYn() {
        return processYn;
    }

    public void setProcessYn(String processYn) {
        this.processYn = processYn;
    }

    public String getTabDivision() {
        return tabDivision;
    }

    public void setTabDivision(String tabDivision) {
        this.tabDivision = tabDivision;
    }

    public String getHubTypeName() {
        return hubTypeName;
    }

    public void setHubTypeName(String hubTypeName) {
        this.hubTypeName = hubTypeName;
    }

    public String getMsgTypeName() {
        return msgTypeName;
    }

    public void setMsgTypeName(String msgTypeName) {
        this.msgTypeName = msgTypeName;
    }

    public String getSenderCompName() {
        return senderCompName;
    }

    public void setSenderCompName(String senderCompName) {
        this.senderCompName = senderCompName;
    }

    public String getDeliverCompName() {
        return deliverCompName;
    }

    public void setDeliverCompName(String deliverCompName) {
        this.deliverCompName = deliverCompName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
