package kr.co.koscom.oppf.apt.cptUsr.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptUserManageVO.java
* @Comment  : 기업회원 관리를 위한 위한 VO
* @author   : 신동진
* @since    : 2016.06.24
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.24  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class CptUserManageVO extends ComDefaultListVO{
	/* com_company_profile */
	private String companyProfileRegNo;
	private String companyNameKor;
	private String companyNameEng;
	private String companyNameKorAlias;
	private String companyNameEngAlias;
	private String companyServiceType;
	private String companyServiceTypeName;
	
	/* com_company_operator_profile */
	private String operatorProfileRegNo;        
	private String operatorNameKor;
	private String operatorNameEng;
	private String operatorId;
	
	private String operatorPassword;
	private String operatorTempPasswordYn;
	private String operatorChgPasswordDate;
	private String operatorExpirePasswordDate;
	private String operatorFinalPasswordDate;
	private int operatorPasswordFailCnt;
	private String operatorRegStatus;
	private String operatorPhoneNo;
	private String operatorTelNo;
	private String operatorEmail;
	private String operatorEmailAuthType;
	private String operatorEmailAuthYn;
	private String operatorEmailAuthDate;
	private String operatorRegDate;
	
	private String operatorRegStatusName;
	private String createIdName;
		
	/* 검색조건 */
	private String searchOperatorRegStatus;
	private String searchDateType;
	private String searchDateFrom;
	private String searchDateTo;
	
	/* 상세 */
	private String companyCodeId;
	private String companyCodeKind;
	private String companyCodeKindName;
	private String companyDivCode;
	private String companyDivCodeName;
	private String companyBizregNo;
	private String companyPostNo;
	private String companyAddress;
	private String companyAddressDtl;
	private String ceoName;
	private String issueVtaccount;
	
	public String getCompanyProfileRegNo() {
		return companyProfileRegNo;
	}
	public void setCompanyProfileRegNo(String companyProfileRegNo) {
		this.companyProfileRegNo = companyProfileRegNo;
	}
	public String getCompanyNameKor() {
		return companyNameKor;
	}
	public void setCompanyNameKor(String companyNameKor) {
		this.companyNameKor = companyNameKor;
	}
	public String getCompanyNameEng() {
		return companyNameEng;
	}
	public void setCompanyNameEng(String companyNameEng) {
		this.companyNameEng = companyNameEng;
	}
	public String getCompanyNameKorAlias() {
		return companyNameKorAlias;
	}
	public void setCompanyNameKorAlias(String companyNameKorAlias) {
		this.companyNameKorAlias = companyNameKorAlias;
	}
	public String getCompanyNameEngAlias() {
		return companyNameEngAlias;
	}
	public void setCompanyNameEngAlias(String companyNameEngAlias) {
		this.companyNameEngAlias = companyNameEngAlias;
	}
	public String getCompanyServiceType() {
		return companyServiceType;
	}
	public void setCompanyServiceType(String companyServiceType) {
		this.companyServiceType = companyServiceType;
	}
	public String getCompanyServiceTypeName() {
		return companyServiceTypeName;
	}
	public void setCompanyServiceTypeName(String companyServiceTypeName) {
		this.companyServiceTypeName = companyServiceTypeName;
	}
	public String getOperatorProfileRegNo() {
		return operatorProfileRegNo;
	}
	public void setOperatorProfileRegNo(String operatorProfileRegNo) {
		this.operatorProfileRegNo = operatorProfileRegNo;
	}
	public String getOperatorNameKor() {
		return operatorNameKor;
	}
	public void setOperatorNameKor(String operatorNameKor) {
		this.operatorNameKor = operatorNameKor;
	}
	public String getOperatorNameEng() {
		return operatorNameEng;
	}
	public void setOperatorNameEng(String operatorNameEng) {
		this.operatorNameEng = operatorNameEng;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorPassword() {
		return operatorPassword;
	}
	public void setOperatorPassword(String operatorPassword) {
		this.operatorPassword = operatorPassword;
	}
	public String getOperatorTempPasswordYn() {
		return operatorTempPasswordYn;
	}
	public void setOperatorTempPasswordYn(String operatorTempPasswordYn) {
		this.operatorTempPasswordYn = operatorTempPasswordYn;
	}
	public String getOperatorChgPasswordDate() {
		return operatorChgPasswordDate;
	}
	public void setOperatorChgPasswordDate(String operatorChgPasswordDate) {
		this.operatorChgPasswordDate = operatorChgPasswordDate;
	}
	public String getOperatorExpirePasswordDate() {
		return operatorExpirePasswordDate;
	}
	public void setOperatorExpirePasswordDate(String operatorExpirePasswordDate) {
		this.operatorExpirePasswordDate = operatorExpirePasswordDate;
	}
	public String getOperatorFinalPasswordDate() {
		return operatorFinalPasswordDate;
	}
	public void setOperatorFinalPasswordDate(String operatorFinalPasswordDate) {
		this.operatorFinalPasswordDate = operatorFinalPasswordDate;
	}
	public int getOperatorPasswordFailCnt() {
		return operatorPasswordFailCnt;
	}
	public void setOperatorPasswordFailCnt(int operatorPasswordFailCnt) {
		this.operatorPasswordFailCnt = operatorPasswordFailCnt;
	}
	public String getOperatorRegStatus() {
		return operatorRegStatus;
	}
	public void setOperatorRegStatus(String operatorRegStatus) {
		this.operatorRegStatus = operatorRegStatus;
	}
	public String getOperatorPhoneNo() {
		return operatorPhoneNo;
	}
	public void setOperatorPhoneNo(String operatorPhoneNo) {
		this.operatorPhoneNo = operatorPhoneNo;
	}
	public String getOperatorTelNo() {
		return operatorTelNo;
	}
	public void setOperatorTelNo(String operatorTelNo) {
		this.operatorTelNo = operatorTelNo;
	}
	public String getOperatorEmail() {
		return operatorEmail;
	}
	public void setOperatorEmail(String operatorEmail) {
		this.operatorEmail = operatorEmail;
	}
	public String getOperatorEmailAuthType() {
		return operatorEmailAuthType;
	}
	public void setOperatorEmailAuthType(String operatorEmailAuthType) {
		this.operatorEmailAuthType = operatorEmailAuthType;
	}
	public String getOperatorEmailAuthYn() {
		return operatorEmailAuthYn;
	}
	public void setOperatorEmailAuthYn(String operatorEmailAuthYn) {
		this.operatorEmailAuthYn = operatorEmailAuthYn;
	}
	public String getOperatorEmailAuthDate() {
		return operatorEmailAuthDate;
	}
	public void setOperatorEmailAuthDate(String operatorEmailAuthDate) {
		this.operatorEmailAuthDate = operatorEmailAuthDate;
	}
	public String getOperatorRegDate() {
		return operatorRegDate;
	}
	public void setOperatorRegDate(String operatorRegDate) {
		this.operatorRegDate = operatorRegDate;
	}
	public String getOperatorRegStatusName() {
		return operatorRegStatusName;
	}
	public void setOperatorRegStatusName(String operatorRegStatusName) {
		this.operatorRegStatusName = operatorRegStatusName;
	}
	public String getCreateIdName() {
		return createIdName;
	}
	public void setCreateIdName(String createIdName) {
		this.createIdName = createIdName;
	}
	public String getSearchOperatorRegStatus() {
		return searchOperatorRegStatus;
	}
	public void setSearchOperatorRegStatus(String searchOperatorRegStatus) {
		this.searchOperatorRegStatus = searchOperatorRegStatus;
	}
	public String getSearchDateType() {
		return searchDateType;
	}
	public void setSearchDateType(String searchDateType) {
		this.searchDateType = searchDateType;
	}
	public String getSearchDateFrom() {
		return searchDateFrom;
	}
	public void setSearchDateFrom(String searchDateFrom) {
		this.searchDateFrom = searchDateFrom;
	}
	public String getSearchDateTo() {
		return searchDateTo;
	}
	public void setSearchDateTo(String searchDateTo) {
		this.searchDateTo = searchDateTo;
	}
	public String getCompanyCodeId() {
		return companyCodeId;
	}
	public void setCompanyCodeId(String companyCodeId) {
		this.companyCodeId = companyCodeId;
	}
	public String getCompanyCodeKind() {
		return companyCodeKind;
	}
	public void setCompanyCodeKind(String companyCodeKind) {
		this.companyCodeKind = companyCodeKind;
	}
	public String getCompanyCodeKindName() {
		return companyCodeKindName;
	}
	public void setCompanyCodeKindName(String companyCodeKindName) {
		this.companyCodeKindName = companyCodeKindName;
	}
	public String getCompanyDivCode() {
		return companyDivCode;
	}
	public void setCompanyDivCode(String companyDivCode) {
		this.companyDivCode = companyDivCode;
	}
	public String getCompanyDivCodeName() {
		return companyDivCodeName;
	}
	public void setCompanyDivCodeName(String companyDivCodeName) {
		this.companyDivCodeName = companyDivCodeName;
	}
	public String getCompanyBizregNo() {
		return companyBizregNo;
	}
	public void setCompanyBizregNo(String companyBizregNo) {
		this.companyBizregNo = companyBizregNo;
	}
	public String getCompanyPostNo() {
		return companyPostNo;
	}
	public void setCompanyPostNo(String companyPostNo) {
		this.companyPostNo = companyPostNo;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyAddressDtl() {
		return companyAddressDtl;
	}
	public void setCompanyAddressDtl(String companyAddressDtl) {
		this.companyAddressDtl = companyAddressDtl;
	}
	public String getCeoName() {
		return ceoName;
	}
	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}
	public String getIssueVtaccount() {
		return issueVtaccount;
	}
	public void setIssueVtaccount(String issueVtaccount) {
		this.issueVtaccount = issueVtaccount;
	}
	
}