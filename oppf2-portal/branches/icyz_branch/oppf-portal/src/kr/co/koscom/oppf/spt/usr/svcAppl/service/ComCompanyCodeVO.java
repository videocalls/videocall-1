package kr.co.koscom.oppf.spt.usr.svcAppl.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SvcApplVO.java
* @Comment  : [서비스신청:기업코드]정보관리를 위한 VO 클래스
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
public class ComCompanyCodeVO extends ComDefaultListVO{
    
    /* 기업코드 테이블 관련 */
    private String companyCodeRegNo;     /* 기업코드.기업코드등록번호 */
    private String companyCodeType;      /* 기업코드.기업코드표구분 */
    private String companyCodeId;        /* 기업코드.기업코드 */
    private String companyCodeKind;      /* 기업코드.기업종류 */
    private String companyCodeKindNm;    /* 기업코드.기업종류명 */
    private String companyNameKor;       /* 기업코드.기업이름한글 */
    private String companyNameEng;       /* 기업코드.기업이름영문 */
    private String companyNameKorAlias;  /* 기업코드.기업이름Alias한글 */
    private String companyNameEngAlias;  /* 기업코드.기업이름Alias영문 */
    
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
    
    
}