package kr.co.koscom.oppf.spt.usr.svcAppl.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SvcApplVO.java
* @Comment  : [일반회원가상계좌프로파일]정보관리를 위한 VO 클래스
* @author   : 포털 이환덕
* @since    : 2016.05.16
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.16  이환덕        최초생성
*
*/
@SuppressWarnings("serial")
public class SptCustomerAccountProfileVO extends ComDefaultListVO{
    
    /* 일반회원가상계좌프로파일 테이블 관련 */
    public String customerRegNo;                /* 일반회원가상계좌프로파일.회원OP가입번호 */
    public String companyCodeId;                /* 일반회원가상계좌프로파일.기업코드 */
    public String companyCodeIdNm;              /* 일반회원가상계좌프로파일.기업코드명 */
    public String customerRealaccountNo;        /* 일반회원가상계좌프로파일.실계좌번호 */
    public String customerVtaccountNo;          /* 일반회원가상계좌프로파일.가상계좌번호 */
    public String customerVtaccountAlias;       /* 일반회원가상계좌프로파일.가상계좌별칭 */
    public String customerVtaccountStatus;      /* 일반회원가상계좌프로파일.가상계좌상태 */
    public String customerRealaccountType;      /* 일반회원가상계좌프로파일.실계좌유형 */
    public String customerRealaccountTypeNm;    /* 일반회원가상계좌프로파일.실계좌유형명 */
    public String customerRealaccountTypeNmEng; /* 일반회원가상계좌프로파일.실계좌유형영문명 */
    public String customerVtaccountRegDate;     /* 일반회원가상계좌프로파일.가상계좌발급일시 */
    public String customerVtaccountExpireDate;  /* 일반회원가상계좌프로파일.가상계좌폐기일시 */
    
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
    public String getCompanyCodeIdNm() {
        return companyCodeIdNm;
    }
    public void setCompanyCodeIdNm(String companyCodeIdNm) {
        this.companyCodeIdNm = companyCodeIdNm;
    }
    public String getCustomerRealaccountNo() {
        return customerRealaccountNo;
    }
    public void setCustomerRealaccountNo(String customerRealaccountNo) {
        this.customerRealaccountNo = customerRealaccountNo;
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
    public String getCustomerVtaccountStatus() {
        return customerVtaccountStatus;
    }
    public void setCustomerVtaccountStatus(String customerVtaccountStatus) {
        this.customerVtaccountStatus = customerVtaccountStatus;
    }
    public String getCustomerRealaccountType() {
        return customerRealaccountType;
    }
    public void setCustomerRealaccountType(String customerRealaccountType) {
        this.customerRealaccountType = customerRealaccountType;
    }
    public String getCustomerRealaccountTypeNm() {
        return customerRealaccountTypeNm;
    }
    public void setCustomerRealaccountTypeNm(String customerRealaccountTypeNm) {
        this.customerRealaccountTypeNm = customerRealaccountTypeNm;
    }
    public String getCustomerRealaccountTypeNmEng() {
        return customerRealaccountTypeNmEng;
    }
    public void setCustomerRealaccountTypeNmEng(String customerRealaccountTypeNmEng) {
        this.customerRealaccountTypeNmEng = customerRealaccountTypeNmEng;
    }
    public String getCustomerVtaccountRegDate() {
        return customerVtaccountRegDate;
    }
    public void setCustomerVtaccountRegDate(String customerVtaccountRegDate) {
        this.customerVtaccountRegDate = customerVtaccountRegDate;
    }
    public String getCustomerVtaccountExpireDate() {
        return customerVtaccountExpireDate;
    }
    public void setCustomerVtaccountExpireDate(String customerVtaccountExpireDate) {
        this.customerVtaccountExpireDate = customerVtaccountExpireDate;
    }
    
}