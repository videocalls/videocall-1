package kr.co.koscom.oppf.apt.usr.mbrReg.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : MbrRegVO.java
* @Comment  : [회원가입:약관동의]정보관리를 위한 VO 클래스
* @author   : 포털 이환덕
* @since    : 2016.05.02
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.02  이환덕        최초생성
*
*/
@SuppressWarnings("serial")
public class NewMbrRegTermsContentVO extends ComDefaultListVO{
    
    
    /* [약관]관련 일반회원약관내용(spt_customer_terms_content_profile)테이블 */
    private String customerTermsTypeName;      //회원동의종류명(제목)
    private String customerTermsType;          //회원동의종류
    private String customerTermsContentRegSeq; //동의서약관내용등록번호
    private String customerTermsContentVer;    //동의서약관내용버전
    private String customerTermsContent;       //동의서약관내용
    private String customerEmailYn;            //동의서email고지여부
    private String customerEmailDate;          //동의서email고지일자
    private List<String> searchCustomerTermsTypeList;
    
    public String getCustomerTermsTypeName() {
        return customerTermsTypeName;
    }
    public void setCustomerTermsTypeName(String customerTermsTypeName) {
        this.customerTermsTypeName = customerTermsTypeName;
    }
    public String getCustomerTermsType() {
        return customerTermsType;
    }
    public void setCustomerTermsType(String customerTermsType) {
        this.customerTermsType = customerTermsType;
    }
    public String getCustomerTermsContentRegSeq() {
        return customerTermsContentRegSeq;
    }
    public void setCustomerTermsContentRegSeq(String customerTermsContentRegSeq) {
        this.customerTermsContentRegSeq = customerTermsContentRegSeq;
    }
    public String getCustomerTermsContentVer() {
        return customerTermsContentVer;
    }
    public void setCustomerTermsContentVer(String customerTermsContentVer) {
        this.customerTermsContentVer = customerTermsContentVer;
    }
    public String getCustomerTermsContent() {
        return customerTermsContent;
    }
    public void setCustomerTermsContent(String customerTermsContent) {
        this.customerTermsContent = customerTermsContent;
    }
    public String getCustomerEmailYn() {
        return customerEmailYn;
    }
    public void setCustomerEmailYn(String customerEmailYn) {
        this.customerEmailYn = customerEmailYn;
    }
    public String getCustomerEmailDate() {
        return customerEmailDate;
    }
    public void setCustomerEmailDate(String customerEmailDate) {
        this.customerEmailDate = customerEmailDate;
    }
    public List<String> getSearchCustomerTermsTypeList() {
        return searchCustomerTermsTypeList;
    }
    public void setSearchCustomerTermsTypeList(List<String> searchCustomerTermsTypeList) {
        this.searchCustomerTermsTypeList = searchCustomerTermsTypeList;
    }
    
}