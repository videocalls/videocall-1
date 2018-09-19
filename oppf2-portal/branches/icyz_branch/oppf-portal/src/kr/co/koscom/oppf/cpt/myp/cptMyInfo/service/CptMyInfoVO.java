package kr.co.koscom.oppf.cpt.myp.cptMyInfo.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptMyInfoVO.java
* @Comment  : [기업회원정보]정보관리를 위한 VO 클래스
* @author   : 포털 유제량
* @since    : 2016.06.29
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.29  유제량        최초생성
*
*/
@SuppressWarnings("serial")
public class CptMyInfoVO extends ComDefaultListVO{
    
    /**
     * com_company_operator_profile
     */
    //기업 프로파일 등록번호
    private String companyProfileRegNo;
    //기업 운영자 프로파일 등록번호
    private String operatorProfileRegNo;
    //운영자명 한글
    private String operatorNameKor;
    //운영자명 영문
    private String operatorNameEng;
    //운영자 ID
    private String operatorId;
    //운영자 패스워드
    private String operatorPassword;
    //임시비밀번호 여부
    private String operatorTempPasswordYn;
    //비밀번호 변경일시
    private String operatorChgPasswordDate;
    //비밀번호 변경 예정일
    private String operatorExpirePasswordDate;
    //비밀번호 변경 최종 예정일
    private String operatorFinalPasswordDate;
    //비밀번호 실패횟수
    private int operatorPasswordFailCnt; 
    //휴대폰번호
    private String operatorPhoneNo;
    //email
    private String operatorEmail;
    //email 인증방식
    private String operatorEmailAuthType;
    //이메일 인증여부
    private String operatorEmailAuthYn;
    //email 인증일시
    private String operatorEmailAuthDate;
    //회원가입 일시
    private String operatorRegDate;
    //회원탈퇴 일시
    private String deleteDate;    
    //운영자 가입 상태
    private String operatorRegStatus;
    //내선번호
    private String operatorTelNo;
    
    //회원 비밀번호 임시여부
    private String operator_temp_password_yn;
    //회원 비밀번호 변경일자
    private String operator_chg_password_date;
    //비밀번호 변경 예정일
    private String operator_expire_password_date;
    //비밀번호 변경 최종 예정일
    private String operator_final_password_date;
    //비밀번호 실패횟수
    private int operator_password_fail_cnt;
    
    /**
     * com_company_profile
     */
    private String companyCodeId;          //기업 코드               
    private String companyServiceType;     //기업 핀테크 서비스 타입 
    private String issueVtaccount;         //가상계좌 발급 여부      
    private String issueAccountlist;       //실계좌목록조회 요청 여부
    private String companyNameKor;         //기업 이름 한글          
    private String companyNameEng;         //기업 이름 영문          
    private String companyNameKorAlias;    //기업 이름 Alias 한글    
    private String companyNameEngAlias;    //기업 이름 Alias 영문    
    private String companyBizregNo;        //기업 사업자 등록번호    
    private String companyPostNo;          //기업 우편번호           
    private String companyAddress;         //기업 주소               
    private String companyAddressDtl;      //기업 상세주소           
    private String companyCi;              //기업 이미지             
    private String ceoName;                //대표자명                
    private String exposureYn;             //노출여부                
    private String exposureOrder;          //노출순서                
    
    /**
     * com_company_code
     */
    private String companyCodeRegNo;       //기업 코드 등록 번호 
    private String companyCodeType;        //기업 코드 표 구분
    private String companyCodeKind;        //기업 종류           
    private String companyDivCode;         //기업 분류  
    private String companyCreateDate;      //기업 등록일
    private String companyUpdateDate;      //기업 수정일
    
    /* otp 정보 */
    private String operatorOtpYn;
    private String operatorOtpId;
    private String operatorOtpAction;
    
    public String getCompanyProfileRegNo() {
        return companyProfileRegNo;
    }
    public void setCompanyProfileRegNo(String companyProfileRegNo) {
        this.companyProfileRegNo = companyProfileRegNo;
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
    public String getOperatorPhoneNo() {
        return operatorPhoneNo;
    }
    public void setOperatorPhoneNo(String operatorPhoneNo) {
        this.operatorPhoneNo = operatorPhoneNo;
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
    public String getDeleteDate() {
        return deleteDate;
    }
    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }
    public String getOperatorRegStatus() {
        return operatorRegStatus;
    }
    public void setOperatorRegStatus(String operatorRegStatus) {
        this.operatorRegStatus = operatorRegStatus;
    }
    public String getOperatorTelNo() {
        return operatorTelNo;
    }
    public void setOperatorTelNo(String operatorTelNo) {
        this.operatorTelNo = operatorTelNo;
    }
    public String getOperator_temp_password_yn() {
        return operator_temp_password_yn;
    }
    public void setOperator_temp_password_yn(String operator_temp_password_yn) {
        this.operator_temp_password_yn = operator_temp_password_yn;
    }
    public String getOperator_chg_password_date() {
        return operator_chg_password_date;
    }
    public void setOperator_chg_password_date(String operator_chg_password_date) {
        this.operator_chg_password_date = operator_chg_password_date;
    }
    public String getOperator_expire_password_date() {
        return operator_expire_password_date;
    }
    public void setOperator_expire_password_date(String operator_expire_password_date) {
        this.operator_expire_password_date = operator_expire_password_date;
    }
    public String getOperator_final_password_date() {
        return operator_final_password_date;
    }
    public void setOperator_final_password_date(String operator_final_password_date) {
        this.operator_final_password_date = operator_final_password_date;
    }
    public int getOperator_password_fail_cnt() {
        return operator_password_fail_cnt;
    }
    public void setOperator_password_fail_cnt(int operator_password_fail_cnt) {
        this.operator_password_fail_cnt = operator_password_fail_cnt;
    }
    public String getCompanyCodeId() {
        return companyCodeId;
    }
    public void setCompanyCodeId(String companyCodeId) {
        this.companyCodeId = companyCodeId;
    }
    public String getCompanyServiceType() {
        return companyServiceType;
    }
    public void setCompanyServiceType(String companyServiceType) {
        this.companyServiceType = companyServiceType;
    }
    public String getIssueVtaccount() {
        return issueVtaccount;
    }
    public void setIssueVtaccount(String issueVtaccount) {
        this.issueVtaccount = issueVtaccount;
    }
    public String getIssueAccountlist() {
        return issueAccountlist;
    }
    public void setIssueAccountlist(String issueAccountlist) {
        this.issueAccountlist = issueAccountlist;
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
    public String getCompanyCi() {
        return companyCi;
    }
    public void setCompanyCi(String companyCi) {
        this.companyCi = companyCi;
    }
    public String getCeoName() {
        return ceoName;
    }
    public void setCeoName(String ceoName) {
        this.ceoName = ceoName;
    }
    public String getExposureYn() {
        return exposureYn;
    }
    public void setExposureYn(String exposureYn) {
        this.exposureYn = exposureYn;
    }
    public String getExposureOrder() {
        return exposureOrder;
    }
    public void setExposureOrder(String exposureOrder) {
        this.exposureOrder = exposureOrder;
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
    public String getCompanyDivCode() {
        return companyDivCode;
    }
    public void setCompanyDivCode(String companyDivCode) {
        this.companyDivCode = companyDivCode;
    }
    public String getCompanyCreateDate() {
        return companyCreateDate;
    }
    public void setCompanyCreateDate(String companyCreateDate) {
        this.companyCreateDate = companyCreateDate;
    }
    public String getCompanyUpdateDate() {
        return companyUpdateDate;
    }
    public void setCompanyUpdateDate(String companyUpdateDate) {
        this.companyUpdateDate = companyUpdateDate;
    }
    public String getOperatorOtpYn() {
        return operatorOtpYn;
    }
    public void setOperatorOtpYn(String operatorOtpYn) {
        this.operatorOtpYn = operatorOtpYn;
    }
    public String getOperatorOtpId() {
        return operatorOtpId;
    }
    public void setOperatorOtpId(String operatorOtpId) {
        this.operatorOtpId = operatorOtpId;
    }
    public String getOperatorOtpAction() {
        return operatorOtpAction;
    }
    public void setOperatorOtpAction(String operatorOtpAction) {
        this.operatorOtpAction = operatorOtpAction;
    }
    
}