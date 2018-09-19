package kr.co.koscom.oppf.cmm.otp.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmOtpReqVO.java
* @Comment  : OTP 통신을 위한 VO
* @author   : 신동진
* @since    : 2016.06.22
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.22  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class CmmOtpReqVO extends ComDefaultListVO{
	private String customerRegNo;
	private String customerOtpStatus;
	
	private String loginCheckYn;
	
	/* req 정보 */
	private String customerSendOtpId;
	private String customerOtpId;
	
	/* respons 정보 */
	private String result;
	private String opt_id;
	private String message;
	
	private String customerOtpAction;
		
	public String getCustomerRegNo() {
		return customerRegNo;
	}
	public void setCustomerRegNo(String customerRegNo) {
		this.customerRegNo = customerRegNo;
	}
	public String getCustomerOtpStatus() {
		return customerOtpStatus;
	}
	public void setCustomerOtpStatus(String customerOtpStatus) {
		this.customerOtpStatus = customerOtpStatus;
	}
	public String getLoginCheckYn() {
		return loginCheckYn;
	}
	public void setLoginCheckYn(String loginCheckYn) {
		this.loginCheckYn = loginCheckYn;
	}
	public String getCustomerSendOtpId() {
		return customerSendOtpId;
	}
	public void setCustomerSendOtpId(String customerSendOtpId) {
		this.customerSendOtpId = customerSendOtpId;
	}
	public String getCustomerOtpId() {
		return customerOtpId;
	}
	public void setCustomerOtpId(String customerOtpId) {
		this.customerOtpId = customerOtpId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getOpt_id() {
		return opt_id;
	}
	public void setOpt_id(String opt_id) {
		this.opt_id = opt_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCustomerOtpAction() {
		return customerOtpAction;
	}
	public void setCustomerOtpAction(String customerOtpAction) {
		this.customerOtpAction = customerOtpAction;
	}
	
}
