package kr.co.koscom.oppf.spt.usr.svcAppl.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SvcApplVO.java
* @Comment  : [서비스신청:일반회원 기업 약관]정보관리를 위한 VO 클래스
* @author   : 신동진
* @since    : 2016.08.01
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.08.01  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class SptCustomerCompanyTermsProfileVO extends ComDefaultListVO{
    /* 일반회원기업약관동의(spt_customer_company_terms_profile) */
	private String customerRegNo;
	private String companyCodeId;
	private String companyTermsType;
	private String companyTermsContentRegSeq;
	private String companyTermsAuthYn;
	private String companyTermsAuthDate;
	
	/* 조회용 */
	private String companyName;
	private String companyTermsContent;

	public String getCustomerRegNo() {
		return customerRegNo;
	}

	public void setCustomerRegNo(String customerRegNo) {
		this.customerRegNo = customerRegNo;
	}

	public String getCompanyCodeId() {
		return companyCodeId;
	}

	public void setCompanyCodeId(String companyCodeId) {
		this.companyCodeId = companyCodeId;
	}

	public String getCompanyTermsType() {
		return companyTermsType;
	}

	public void setCompanyTermsType(String companyTermsType) {
		this.companyTermsType = companyTermsType;
	}

	public String getCompanyTermsContentRegSeq() {
		return companyTermsContentRegSeq;
	}

	public void setCompanyTermsContentRegSeq(String companyTermsContentRegSeq) {
		this.companyTermsContentRegSeq = companyTermsContentRegSeq;
	}

	public String getCompanyTermsAuthYn() {
		return companyTermsAuthYn;
	}

	public void setCompanyTermsAuthYn(String companyTermsAuthYn) {
		this.companyTermsAuthYn = companyTermsAuthYn;
	}

	public String getCompanyTermsAuthDate() {
		return companyTermsAuthDate;
	}

	public void setCompanyTermsAuthDate(String companyTermsAuthDate) {
		this.companyTermsAuthDate = companyTermsAuthDate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyTermsContent() {
		return companyTermsContent;
	}

	public void setCompanyTermsContent(String companyTermsContent) {
		this.companyTermsContent = companyTermsContent;
	}
}
