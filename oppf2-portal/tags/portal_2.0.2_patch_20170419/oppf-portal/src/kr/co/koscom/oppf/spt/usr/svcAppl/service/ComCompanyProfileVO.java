package kr.co.koscom.oppf.spt.usr.svcAppl.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SvcApplVO.java
* @Comment  : [서비스신청:기업프로파일]정보관리를 위한 VO 클래스
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
public class ComCompanyProfileVO extends ComDefaultListVO{
    
    /* 기업프로파일 테이블 관련 */
    private String companyProfileRegNo; /* 기업프로파일.기업프로파일등록번호 */
    private String companyCodeId;       /* 기업프로파일.기업코드 */
    private String companyServiceType;  /* 기업프로파일.기업핀테크서비스타입 */
    private String issueVtaccount;      /* 기업프로파일.가상계좌발급여부 */
    private String issueAccountlist;    /* 기업프로파일.실계좌목록조회요청여부 */
    private String companyNameKor;      /* 기업프로파일.기업이름한글 */
    private String companyNameEng;      /* 기업프로파일.기업이름영문 */
    private String companyNameKorAlias; /* 기업프로파일.기업이름Alias한글 */
    private String companyNameEngAlias; /* 기업프로파일.기업이름영문Alias */
    private String companyBizregNo;     /* 기업프로파일.기업사업자등록번호 */
    private String companyPostNo;       /* 기업프로파일.기업우편번호 */
    private String companyAddress;      /* 기업프로파일.기업주소 */
    private String companyAddressDtl;   /* 기업프로파일.기업상세주소 */
    private String exposureYn;          /* 기업프로파일.노출여부 */
    private String exposureOrder;       /* 기업프로파일.노출순서 */
    
    /* 조회용 변수 */
    private String appId;
    private String apiId;
    private String apiTitle;
    
    public String getCompanyProfileRegNo() {
        return companyProfileRegNo;
    }
    public void setCompanyProfileRegNo(String companyProfileRegNo) {
        this.companyProfileRegNo = companyProfileRegNo;
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
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	public String getApiTitle() {
		return apiTitle;
	}
	public void setApiTitle(String apiTitle) {
		this.apiTitle = apiTitle;
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
	
}