package kr.co.koscom.oppf.spt.usr.svcAppl.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptCustomerServiceTermsPubcompanyProfileVO.java
* @Comment  : [서비스신청:일반회원서비스약관금투사프로파일]정보관리를 위한 VO 클래스
* @author   : 신동진
* @since    : 2016.06.10
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.10  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class SptCustomerServiceTermsPubcompanyProfileVO extends ComDefaultListVO{
    
	/* spt_customer_service_terms_pubcompany_profile */
	private String customerRegNo;
	private String termsRegNo;
	private String termsPubcompanyRegNo;
	private String pubcompanyCodeId;
	private String pubcompanyName;
	private String termsChgDate;

	/* 조회용 */
	private String subcompanyCodeId;
	private String termsAuthYn;
	private String termsFileRegNo;
	private String actionType;
	
	public String getCustomerRegNo() {
		return customerRegNo;
	}
	public void setCustomerRegNo(String customerRegNo) {
		this.customerRegNo = customerRegNo;
	}
	public String getTermsRegNo() {
		return termsRegNo;
	}
	public void setTermsRegNo(String termsRegNo) {
		this.termsRegNo = termsRegNo;
	}
	public String getTermsPubcompanyRegNo() {
		return termsPubcompanyRegNo;
	}
	public void setTermsPubcompanyRegNo(String termsPubcompanyRegNo) {
		this.termsPubcompanyRegNo = termsPubcompanyRegNo;
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
	public String getTermsChgDate() {
		return termsChgDate;
	}
	public void setTermsChgDate(String termsChgDate) {
		this.termsChgDate = termsChgDate;
	}
	public String getSubcompanyCodeId() {
		return subcompanyCodeId;
	}
	public void setSubcompanyCodeId(String subcompanyCodeId) {
		this.subcompanyCodeId = subcompanyCodeId;
	}
	public String getTermsAuthYn() {
		return termsAuthYn;
	}
	public void setTermsAuthYn(String termsAuthYn) {
		this.termsAuthYn = termsAuthYn;
	}
	public String getTermsFileRegNo() {
		return termsFileRegNo;
	}
	public void setTermsFileRegNo(String termsFileRegNo) {
		this.termsFileRegNo = termsFileRegNo;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
}