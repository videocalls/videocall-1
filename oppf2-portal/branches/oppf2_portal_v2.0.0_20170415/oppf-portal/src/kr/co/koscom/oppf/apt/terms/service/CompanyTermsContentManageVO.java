package kr.co.koscom.oppf.apt.terms.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CompanyTermsContentManageVO.java
* @Comment  : 관리자의 기업 약관동의 내용 관리를 위한 VO
* @author   : 신동진
* @since    : 2016.08.01
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.08.01  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class CompanyTermsContentManageVO extends ComDefaultListVO{
	/**
	 * spt_customer_company_terms_content_profile
	 */
	private String companyCodeId;
	private String companyCodeName;

	private String companyTermsType;
	private String companyTermsContentRegSeq;
	private String companyTermsContentVer;
	private String companyTermsContent;
	
	private String companyTermsTypeName;
	
	/**
	 * etc
	 */
	private String searchCompanyCodeId;
	private String searchCompanyTermsType;
	
	public String getCompanyCodeId() {
		return companyCodeId;
	}
	public void setCompanyCodeId(String companyCodeId) {
		this.companyCodeId = companyCodeId;
	}
	public String getCompanyCodeName() {
		return companyCodeName;
	}
	public void setCompanyCodeName(String companyCodeName) {
		this.companyCodeName = companyCodeName;
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
	public String getCompanyTermsContentVer() {
		return companyTermsContentVer;
	}
	public void setCompanyTermsContentVer(String companyTermsContentVer) {
		this.companyTermsContentVer = companyTermsContentVer;
	}
	public String getCompanyTermsContent() {
		return companyTermsContent;
	}
	public void setCompanyTermsContent(String companyTermsContent) {
		this.companyTermsContent = companyTermsContent;
	}
	public String getCompanyTermsTypeName() {
		return companyTermsTypeName;
	}
	public void setCompanyTermsTypeName(String companyTermsTypeName) {
		this.companyTermsTypeName = companyTermsTypeName;
	}
	public String getSearchCompanyCodeId() {
		return searchCompanyCodeId;
	}
	public void setSearchCompanyCodeId(String searchCompanyCodeId) {
		this.searchCompanyCodeId = searchCompanyCodeId;
	}
	public String getSearchCompanyTermsType() {
		return searchCompanyTermsType;
	}
	public void setSearchCompanyTermsType(String searchCompanyTermsType) {
		this.searchCompanyTermsType = searchCompanyTermsType;
	}
}