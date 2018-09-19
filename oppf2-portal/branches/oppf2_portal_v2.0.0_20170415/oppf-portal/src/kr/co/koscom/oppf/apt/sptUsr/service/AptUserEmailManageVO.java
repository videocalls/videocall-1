package kr.co.koscom.oppf.apt.sptUsr.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptUserEmailManageVO.java
* @Comment  : admin의 user VO
* @author   : 유제량
* @since    : 2016.06.10
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.10  유제량        최초생성
*
*/
@SuppressWarnings("serial")
public class AptUserEmailManageVO extends ComDefaultListVO{
	/**
	 * com_email_send_info(이메일 발송 정보)
	 */
    private String emailRegNo;        //이메일 발송 생성 번호
    private String emailSendType;     //이메일 유형
    private String emailSendType2;    //이메일 유형(업데이트용)
    private String emailVer;          //이메일 버전
    private String senderKind;        //발신자 종류
    private String senderKind2;       //발신자 종류(업데이트용)
    private String senderId;          //발신자 ID
    private String receiverKind;      //수신자 종류
    private String receiverId;        //수신자 ID
    private String receiverEmail;     //수신자 email
    private String emailTitle;        //이메일 제목
    private String emailContent;      //이메일 내용
    private String sendDate;          //최초 발신일자
    private String sendId;            //최초 발신자 ID
    private String reSendCount;       //재발신 건수
    private String reSendDate;        //재발신 일자
    private String reSendId;          //재발신자 ID
    private String createDate;        //생성 일시
    private String createId;          //생성자 ID
    private String updateDate;        //변경 일시
    private String updateId;          //변경자 ID
    private String sendResult;        //발송결과
    private String sendResultDate;    //발송결과일자
    private String reSendResult;      //재발송결과
    private String reSendResultDate;  //재발송결과일자
	
    private String receiverNmae;      //수신자 이름
    
    private String customerRegNo;     //회원 OP 등록 번호
    
	/**
	 * 검색조건
	 */
	private int rownum;
	private String schKind;
	private String schKey;
	private String schDeleteType;
	private String schDateType;
	private String schDateFrom;
	private String schDateTo;
	private String deleteType;
    
	public String getEmailRegNo() {
        return emailRegNo;
    }
    public void setEmailRegNo(String emailRegNo) {
        this.emailRegNo = emailRegNo;
    }
    public String getEmailSendType() {
        return emailSendType;
    }
    public void setEmailSendType(String emailSendType) {
        this.emailSendType = emailSendType;
    }
    public String getEmailSendType2() {
        return emailSendType2;
    }
    public void setEmailSendType2(String emailSendType2) {
        this.emailSendType2 = emailSendType2;
    }
    public String getEmailVer() {
        return emailVer;
    }
    public void setEmailVer(String emailVer) {
        this.emailVer = emailVer;
    }
    public String getSenderKind() {
        return senderKind;
    }
    public void setSenderKind(String senderKind) {
        this.senderKind = senderKind;
    }
    public String getSenderKind2() {
        return senderKind2;
    }
    public void setSenderKind2(String senderKind2) {
        this.senderKind2 = senderKind2;
    }
    public String getSenderId() {
        return senderId;
    }
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    public String getReceiverKind() {
        return receiverKind;
    }
    public void setReceiverKind(String receiverKind) {
        this.receiverKind = receiverKind;
    }
    public String getReceiverId() {
        return receiverId;
    }
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
    public String getReceiverEmail() {
        return receiverEmail;
    }
    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }
    public String getEmailTitle() {
        return emailTitle;
    }
    public void setEmailTitle(String emailTitle) {
        this.emailTitle = emailTitle;
    }
    public String getEmailContent() {
        return emailContent;
    }
    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }
    public String getSendDate() {
        return sendDate;
    }
    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }
    public String getSendId() {
        return sendId;
    }
    public void setSendId(String sendId) {
        this.sendId = sendId;
    }
    public String getReSendCount() {
        return reSendCount;
    }
    public void setReSendCount(String reSendCount) {
        this.reSendCount = reSendCount;
    }
    public String getReSendDate() {
        return reSendDate;
    }
    public void setReSendDate(String reSendDate) {
        this.reSendDate = reSendDate;
    }
    public String getReSendId() {
        return reSendId;
    }
    public void setReSendId(String reSendId) {
        this.reSendId = reSendId;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getCreateId() {
        return createId;
    }
    public void setCreateId(String createId) {
        this.createId = createId;
    }
    public String getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
    public String getUpdateId() {
        return updateId;
    }
    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }
    public String getSendResult() {
        return sendResult;
    }
    public void setSendResult(String sendResult) {
        this.sendResult = sendResult;
    }
    public String getSendResultDate() {
        return sendResultDate;
    }
    public void setSendResultDate(String sendResultDate) {
        this.sendResultDate = sendResultDate;
    }
    public String getReSendResult() {
        return reSendResult;
    }
    public void setReSendResult(String reSendResult) {
        this.reSendResult = reSendResult;
    }
    public String getReSendResultDate() {
        return reSendResultDate;
    }
    public void setReSendResultDate(String reSendResultDate) {
        this.reSendResultDate = reSendResultDate;
    }
    public String getReceiverNmae() {
        return receiverNmae;
    }
    public void setReceiverNmae(String receiverNmae) {
        this.receiverNmae = receiverNmae;
    }
    public String getCustomerRegNo() {
        return customerRegNo;
    }
    public void setCustomerRegNo(String customerRegNo) {
        this.customerRegNo = customerRegNo;
    }
    public int getRownum() {
        return rownum;
    }
    public void setRownum(int rownum) {
        this.rownum = rownum;
    }
    public String getSchKind() {
        return schKind;
    }
    public void setSchKind(String schKind) {
        this.schKind = schKind;
    }
    public String getSchKey() {
        return schKey;
    }
    public void setSchKey(String schKey) {
        this.schKey = schKey;
    }
    public String getSchDeleteType() {
        return schDeleteType;
    }
    public void setSchDeleteType(String schDeleteType) {
        this.schDeleteType = schDeleteType;
    }
    public String getSchDateType() {
        return schDateType;
    }
    public void setSchDateType(String schDateType) {
        this.schDateType = schDateType;
    }
    public String getSchDateFrom() {
        return schDateFrom;
    }
    public void setSchDateFrom(String schDateFrom) {
        this.schDateFrom = schDateFrom;
    }
    public String getSchDateTo() {
        return schDateTo;
    }
    public void setSchDateTo(String schDateTo) {
        this.schDateTo = schDateTo;
    }
    public String getDeleteType() {
        return deleteType;
    }
    public void setDeleteType(String deleteType) {
        this.deleteType = deleteType;
    }
    
}