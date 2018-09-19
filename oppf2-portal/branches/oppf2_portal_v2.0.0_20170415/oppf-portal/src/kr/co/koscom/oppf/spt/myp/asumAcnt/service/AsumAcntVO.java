package kr.co.koscom.oppf.spt.myp.asumAcnt.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.ComCompanyCodeVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.ComCompanyOperatorProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.ComCompanyProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.ComContractProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerAccountProfileVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SvcApplVO.java
* @Comment  : [마이페이지:가상계좌]정보관리를 위한 VO 클래스
* @author   : 포털 이환덕
* @since    : 2016.05.27
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.27  이환덕        최초생성
*
*/
@SuppressWarnings("serial")
public class AsumAcntVO extends ComDefaultListVO{
    
    /* 공통쓰임 관련 */
    private String companyCodeId;        /* 기업코드.기업코드 */
    private String companyCodeName;        /* 기업코드.기업이름 */

    /* 기업코드 테이블 관련 */
    private List<ComCompanyCodeVO> comCompanyCodeList;
    private String companyCodeRegNo;     /* 기업코드.기업코드등록번호 */
    private String companyCodeType;      /* 기업코드.기업코드표구분 */
    private String companyCodeKind;      /* 기업코드.기업종류 */
    private String companyCodeKindNm;    /* 기업코드.기업종류명 */
    private String companyNameKor;       /* 기업코드.기업이름한글 */
    private String companyNameEng;       /* 기업코드.기업이름영문 */
    private String companyNameKorAlias;  /* 기업코드.기업이름Alias한글 */
    private String companyNameEngAlias;  /* 기업코드.기업이름Alias영문 */
    
    /* 기업운영자프로파일 테이블 관련 */
    private List<ComCompanyOperatorProfileVO> comCompanyOperatorProfileList;
    
    /* 기업프로파일 테이블 관련 */
    private List<ComCompanyProfileVO> comCompanyProfileList;
    
    /* API사용계약관계 테이블 관련 */
    private List<ComContractProfileVO> comContractProfileList;
    
    /* 일반회원가상계좌프로파일 테이블 관련 */
    private List<SptCustomerAccountProfileVO> sptCustomerAccountProfileList;
    private String customerRegNo;                /* 일반회원가상계좌프로파일.회원OP가입번호 */
    private String companyCodeIdNm;              /* 일반회원가상계좌프로파일.기업코드명 */
    private String customerRealaccountNo;        /* 일반회원가상계좌프로파일.실계좌번호 */
    private String customerRealaccountNoMask;    /* 일반회원가상계좌프로파일.실계좌번호 마스크 */
    private String customerRealaccountNoEncryption; /* 일반회원가상계좌프로파일.실계좌번호암호화 */
    private String customerVtaccountNo;          /* 일반회원가상계좌프로파일.가상계좌번호 */
    private String customerVtaccountAlias;       /* 일반회원가상계좌프로파일.가상계좌별칭 */
    private String customerVtaccountStatus;      /* 일반회원가상계좌프로파일.가상계좌상태 */
    private String customerRealaccountType;      /* 일반회원가상계좌프로파일.실계좌유형 */
    private String customerRealaccountTypeNm;    /* 일반회원가상계좌프로파일.실계좌유형명 */
    private String customerRealaccountTypeNmEng; /* 일반회원가상계좌프로파일.실계좌유형영문명 */
    private String customerVtaccountRegDate;     /* 일반회원가상계좌프로파일.가상계좌발급일시 */
    private String customerVtaccountExpireDate;  /* 일반회원가상계좌프로파일.가상계좌폐기일시 */

    public List<ComCompanyCodeVO> getComCompanyCodeList() {
        return comCompanyCodeList;
    }

    public void setComCompanyCodeList(List<ComCompanyCodeVO> comCompanyCodeList) {
        this.comCompanyCodeList = comCompanyCodeList;
    }

    public List<ComCompanyOperatorProfileVO> getComCompanyOperatorProfileList() {
        return comCompanyOperatorProfileList;
    }

    public void setComCompanyOperatorProfileList(List<ComCompanyOperatorProfileVO> comCompanyOperatorProfileList) {
        this.comCompanyOperatorProfileList = comCompanyOperatorProfileList;
    }

    public List<ComCompanyProfileVO> getComCompanyProfileList() {
        return comCompanyProfileList;
    }

    public void setComCompanyProfileList(List<ComCompanyProfileVO> comCompanyProfileList) {
        this.comCompanyProfileList = comCompanyProfileList;
    }

    public List<ComContractProfileVO> getComContractProfileList() {
        return comContractProfileList;
    }

    public void setComContractProfileList(List<ComContractProfileVO> comContractProfileList) {
        this.comContractProfileList = comContractProfileList;
    }

    public List<SptCustomerAccountProfileVO> getSptCustomerAccountProfileList() {
        return sptCustomerAccountProfileList;
    }

    public void setSptCustomerAccountProfileList(List<SptCustomerAccountProfileVO> sptCustomerAccountProfileList) {
        this.sptCustomerAccountProfileList = sptCustomerAccountProfileList;
    }

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

    public String getCompanyCodeRegNo() {
        return companyCodeRegNo;
    }

    public void setCompanyCodeRegNo(String companyCodeRegNo) {
        this.companyCodeRegNo = companyCodeRegNo;
    }

    public String getCompanyCodeType() {
        return companyCodeType;
    }

    public void setCompanyCodeType(String companyCodeType) {
        this.companyCodeType = companyCodeType;
    }

    public String getCompanyCodeKind() {
        return companyCodeKind;
    }

    public void setCompanyCodeKind(String companyCodeKind) {
        this.companyCodeKind = companyCodeKind;
    }

    public String getCompanyCodeKindNm() {
        return companyCodeKindNm;
    }

    public void setCompanyCodeKindNm(String companyCodeKindNm) {
        this.companyCodeKindNm = companyCodeKindNm;
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

    public String getCustomerRegNo() {
        return customerRegNo;
    }

    public void setCustomerRegNo(String customerRegNo) {
        this.customerRegNo = customerRegNo;
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

    public String getCustomerRealaccountNoMask() {
        return customerRealaccountNoMask;
    }

    public void setCustomerRealaccountNoMask(String customerRealaccountNoMask) {
        this.customerRealaccountNoMask = customerRealaccountNoMask;
    }

    public String getCustomerRealaccountNoEncryption() {
        return customerRealaccountNoEncryption;
    }

    public void setCustomerRealaccountNoEncryption(String customerRealaccountNoEncryption) {
        this.customerRealaccountNoEncryption = customerRealaccountNoEncryption;
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