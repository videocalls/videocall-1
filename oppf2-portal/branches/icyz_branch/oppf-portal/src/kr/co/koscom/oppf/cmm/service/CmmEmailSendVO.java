package kr.co.koscom.oppf.cmm.service;

@SuppressWarnings("serial")
public class CmmEmailSendVO extends ComDefaultListVO {
    /* 이메일발송(엔픽_썬더메일) 관련 */
    private String apiURL = "";                    //자동메일 연동 URL(필수)
    private String automailIDEncrypt = "";         //자동메일 템플릿ID 암호화값(필수)
    private String userCodeEncrypt = "";           //사용자 코드(필수)
    private String receiverName = "";              //받는 사람 이름(부분필수)
    private String receiverName2 = "";             //회원가입완료 단계에서 마스킹처리 받는 이름
    private String mailTitle = "";                 //메일 제목(부분필수)
    private String mailContent = "";               //메일 내용(부분필수)
    private String senderName = "";                //보내는 사람 이름(부분필수)
    private String senderEmail = "";               //보내는 이메일(부분필수)
    private String receiverEmail = "";             //받는 사람 이메일(필수)
    private String receiverEmail2 = "";            //회원가입완료 단계에서 마스킹처리 받는 이메일
    private String returnMail = "";                //반송 이메일(필수)
    private String onetooneInfo = "";              //일대일치환 정보
    private String reserveDate = "";               //예약발송 일시
    
    private String customerRegNo = "";             //회원 OP 등록 번호
    private String customerPassword = "";           //비밀번호찾기 단계에서 받는 회원비밀번호
    
    /* 이메일발송 결과 관련 */
    private String mailCode = "";                  //이메일발송 리턴코드
    private String mailMsg  = "";                  //이메일발송 리턴메시지
    
    /* com_email_send_info(이메일발송 정보) 로그적재 관련 */
    private String emailRegNo = "";                //이메일 발송 생성 번호
    private String emailSendType = "";             //이메일 유형
    private String emailVer = "";                  //이메일 버전
    private String senderKind = "";                //발신자 종류
    private String senderId = "";                  //발신자 ID
    private String receiverKind = "";              //수신자 종류
    private String receiverId = "";                //수신자 ID
    private String receiverId2 = "";               //회원가입완료 단계에서 마스킹처리 받는 수신자 ID
    private String emailTitle = "";                //이메일 제목
    private String emailContent = "";              //이메일 내용
    private String sendDate = "";                  //최초 발신일자
    private String sendId = "";                    //최초 발신자 ID
    private String reSendCount = "";               //재발신 건수
    private String reSendDate = "";                //재발신 일자
    private String reSendId = "";                  //재발신자 ID
    private String sendResult = "";                //발송결과
    private String sendResultDate = "";            //발송결과일자
    private String reSendResult = "";              //재발송결과
    private String reSendResultDate = "";          //재발송결과일자
    
    private String managerSetTitle = "";           //어드민 메일발송관리에서 관리자가 입력한 제목
    private String managerSetContent = "";         //어드민 메일발송관리에서 관리자가 입력한 내용
    private String managerAdjYn = "";              //어드민 메일발송관리 상세화면에서 수정했는지 여부
    
    private String systemKind;						//임의로 system kind값을 셋팅 시 이값으로 systemKind판별
    private String mbrComplt;                       //회원가입완료시 1회 메일발송은 insert가 되게함
    private String uriContextPath;                  //uriContextPath
    private String managerSetTbody;                 //금융거래 정보제공 동의 내용
    private String useApps;                         //이용중인 앱 정보

    /* 금융정보 제공동의 */
    private String customerName = "";
    private String customerEmail = "";
    
    private String termsMailContent = "";
    
    private String serviceRegNo = "";
    private String termsRegNo = "";
    private String termsAuthYn = "";
    private String subcompanyCodeId = "";
    private String subcompanyName = "";
    private String appId = "";
    private String appName = "";
    private String pubcompanyCodeId = "";
    private String pubcompanyName = "";
    private String apiId = "";
    private String apiName = "";
    private String customerVtaccountNo = "";
    private String customerVtaccountAlias = "";
    private String termsStartDate = "";
    private String termsExpireDate = "";
    
    
    public String getManagerSetTbody() {
        return managerSetTbody;
    }
    public void setManagerSetTbody(String managerSetTbody) {
        this.managerSetTbody = managerSetTbody;
    }
    public String getUriContextPath() {
        return uriContextPath;
    }
    public void setUriContextPath(String uriContextPath) {
        this.uriContextPath = uriContextPath;
    }
    public String getApiURL() {
        return apiURL;
    }
    public void setApiURL(String apiURL) {
        this.apiURL = apiURL;
    }
    public String getAutomailIDEncrypt() {
        return automailIDEncrypt;
    }
    public void setAutomailIDEncrypt(String automailIDEncrypt) {
        this.automailIDEncrypt = automailIDEncrypt;
    }
    public String getUserCodeEncrypt() {
        return userCodeEncrypt;
    }
    public void setUserCodeEncrypt(String userCodeEncrypt) {
        this.userCodeEncrypt = userCodeEncrypt;
    }
    public String getReceiverName() {
        return receiverName;
    }
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    public String getReceiverName2() {
        return receiverName2;
    }
    public void setReceiverName2(String receiverName2) {
        this.receiverName2 = receiverName2;
    }
    public String getMailTitle() {
        return mailTitle;
    }
    public void setMailTitle(String mailTitle) {
        this.mailTitle = mailTitle;
    }
    public String getMailContent() {
        return mailContent;
    }
    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }
    public String getSenderName() {
        return senderName;
    }
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    public String getSenderEmail() {
        return senderEmail;
    }
    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }
    public String getReceiverEmail() {
        return receiverEmail;
    }
    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }
    public String getReceiverEmail2() {
        return receiverEmail2;
    }
    public void setReceiverEmail2(String receiverEmail2) {
        this.receiverEmail2 = receiverEmail2;
    }
    public String getReturnMail() {
        return returnMail;
    }
    public void setReturnMail(String returnMail) {
        this.returnMail = returnMail;
    }
    public String getOnetooneInfo() {
        return onetooneInfo;
    }
    public void setOnetooneInfo(String onetooneInfo) {
        this.onetooneInfo = onetooneInfo;
    }
    public String getReserveDate() {
        return reserveDate;
    }
    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }
    public String getCustomerRegNo() {
        return customerRegNo;
    }
    public void setCustomerRegNo(String customerRegNo) {
        this.customerRegNo = customerRegNo;
    }
    public String getCustomerPassword() {
        return customerPassword;
    }
    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }
    public String getMailCode() {
        return mailCode;
    }
    public void setMailCode(String mailCode) {
        this.mailCode = mailCode;
    }
    public String getMailMsg() {
        return mailMsg;
    }
    public void setMailMsg(String mailMsg) {
        this.mailMsg = mailMsg;
    }
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
    public String getReceiverId2() {
        return receiverId2;
    }
    public void setReceiverId2(String receiverId2) {
        this.receiverId2 = receiverId2;
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
    public String getManagerSetTitle() {
        return managerSetTitle;
    }
    public void setManagerSetTitle(String managerSetTitle) {
        this.managerSetTitle = managerSetTitle;
    }
    public String getManagerSetContent() {
        return managerSetContent;
    }
    public void setManagerSetContent(String managerSetContent) {
        this.managerSetContent = managerSetContent;
    }
    public String getManagerAdjYn() {
        return managerAdjYn;
    }
    public void setManagerAdjYn(String managerAdjYn) {
        this.managerAdjYn = managerAdjYn;
    }
	public String getSystemKind() {
		return systemKind;
	}
	public void setSystemKind(String systemKind) {
		this.systemKind = systemKind;
	}
    public String getMbrComplt() {
        return mbrComplt;
    }
    public void setMbrComplt(String mbrComplt) {
        this.mbrComplt = mbrComplt;
    }
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getTermsMailContent() {
		return termsMailContent;
	}
	public void setTermsMailContent(String termsMailContent) {
		this.termsMailContent = termsMailContent;
	}
	public String getServiceRegNo() {
		return serviceRegNo;
	}
	public void setServiceRegNo(String serviceRegNo) {
		this.serviceRegNo = serviceRegNo;
	}
	public String getTermsRegNo() {
		return termsRegNo;
	}
	public void setTermsRegNo(String termsRegNo) {
		this.termsRegNo = termsRegNo;
	}
	public String getTermsAuthYn() {
		return termsAuthYn;
	}
	public void setTermsAuthYn(String termsAuthYn) {
		this.termsAuthYn = termsAuthYn;
	}
	public String getSubcompanyCodeId() {
		return subcompanyCodeId;
	}
	public void setSubcompanyCodeId(String subcompanyCodeId) {
		this.subcompanyCodeId = subcompanyCodeId;
	}
	public String getSubcompanyName() {
		return subcompanyName;
	}
	public void setSubcompanyName(String subcompanyName) {
		this.subcompanyName = subcompanyName;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getPubcompanyCodeId() {
		return pubcompanyCodeId;
	}
	public void setPubcompanyCodeId(String pubcompanyCodeId) {
		this.pubcompanyCodeId = pubcompanyCodeId;
	}
	public String getPubcompanyName() {
		return pubcompanyName;
	}
	public void setPubcompanyName(String pubcompanyName) {
		this.pubcompanyName = pubcompanyName;
	}
	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getCustomerVtaccountNo() {
		return customerVtaccountNo;
	}
	public void setCustomerVtaccountNo(String customerVtaccountNo) {
		this.customerVtaccountNo = customerVtaccountNo;
	}
	public String getCustomerVtaccountAlias() {
		return customerVtaccountAlias;
	}
	public void setCustomerVtaccountAlias(String customerVtaccountAlias) {
		this.customerVtaccountAlias = customerVtaccountAlias;
	}
	public String getTermsStartDate() {
		return termsStartDate;
	}
	public void setTermsStartDate(String termsStartDate) {
		this.termsStartDate = termsStartDate;
	}
	public String getTermsExpireDate() {
		return termsExpireDate;
	}
	public void setTermsExpireDate(String termsExpireDate) {
		this.termsExpireDate = termsExpireDate;
	}
    public String getUseApps() {
        return useApps;
    }
    public void setUseApps(String useApps) {
        this.useApps = useApps;
    }
}
