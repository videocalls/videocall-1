package kr.co.koscom.oppf.spt.usr.svcAppl.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptCustomerServiceTermsFileProfileVO.java
* @Comment  : [일반회원서비스약관프로파일]정보관리를 위한 VO 클래스
* @author   : 포털 이환덕
* @since    : 2016.06.09
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.09  이환덕        최초생성
*
*/
@SuppressWarnings("serial")
public class SptCustomerServiceTermsFileProfileVO extends ComDefaultListVO{
    
    //회원OP등록번호
    private String customerRegNo = "";
    
    //동의서파일종류
    private String termsFileType = "";
    
    //동의서파일등록번호
    private String termsFileRegNo = "";
    
    //동의서파일
    private byte[] termsFileData;
    
    //TSA 결과 해쉬값
    private String dataHash = "";
    
    
    /* 조회조건 */
    //조회조건:회원OP등록번호
    private String searchCustomerRegNo;
    
    //조회조건:동의서파일종류
    private String searchTermsFileType;
    
    //조회조건:동의서파일등록번호
    private String searchTermsFileRegNo;
    
    /* 삭제조건 */
    //삭제flag
    private String delYn = "N";

    
    public String getCustomerRegNo() {
        return customerRegNo;
    }

    public void setCustomerRegNo(String customerRegNo) {
        this.customerRegNo = customerRegNo;
    }

    public String getTermsFileType() {
        return termsFileType;
    }

    public void setTermsFileType(String termsFileType) {
        this.termsFileType = termsFileType;
    }

    public String getTermsFileRegNo() {
        return termsFileRegNo;
    }

    public void setTermsFileRegNo(String termsFileRegNo) {
        this.termsFileRegNo = termsFileRegNo;
    }

    public byte[] getTermsFileData() {
        return termsFileData;
    }

    public void setTermsFileData(byte[] termsFileData) {
        this.termsFileData = termsFileData;
    }

    public String getSearchCustomerRegNo() {
        return searchCustomerRegNo;
    }

    public void setSearchCustomerRegNo(String searchCustomerRegNo) {
        this.searchCustomerRegNo = searchCustomerRegNo;
    }

    public String getSearchTermsFileType() {
        return searchTermsFileType;
    }

    public void setSearchTermsFileType(String searchTermsFileType) {
        this.searchTermsFileType = searchTermsFileType;
    }

    public String getSearchTermsFileRegNo() {
        return searchTermsFileRegNo;
    }

    public void setSearchTermsFileRegNo(String searchTermsFileRegNo) {
        this.searchTermsFileRegNo = searchTermsFileRegNo;
    }

    public String getDelYn() {
        return delYn;
    }

    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }

    public String getDataHash() {
        return dataHash;
    }

    public void setDataHash(String dataHash) {
        this.dataHash = dataHash;
    }
    
}