package kr.co.koscom.oppf.apt.usr.mbrReg.service;

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
public class NewMbrRegTermsVO extends ComDefaultListVO{
    
    
    /* [약관]관련 일반회원약관동의(spt_customer_terms_profile)테이블 */
    private String customerRegNo="";               //회원 OP 등록 번호( 일반사용자 : C+YYYYMMDD+seq(3), 기업 : O+YYYYMMDD+seq(3), admin : A+YYYYMMDD+seq(3) )
    private String customerTermsType = "G008001";  //회원동의종류[G008(001:서비스이용약관, 002:개인정보취급방침, 003:개인정보수집이용동의, 010:공인인증서등록약관동의, 020:금융제공동의서)]
    private String customerTermsContentRegSeq =""; //동의서약관내용등록번호
    private String customerTermsAuthYn = "N";      //회원동의여부
    private String customerTermsAuthDate = "";     //회원동의일시
    
    public String getCustomerRegNo() {
        return customerRegNo;
    }
    public void setCustomerRegNo(String customerRegNo) {
        this.customerRegNo = customerRegNo;
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
    public String getCustomerTermsAuthYn() {
        return customerTermsAuthYn;
    }
    public void setCustomerTermsAuthYn(String customerTermsAuthYn) {
        this.customerTermsAuthYn = customerTermsAuthYn;
    }
    public String getCustomerTermsAuthDate() {
        return customerTermsAuthDate;
    }
    public void setCustomerTermsAuthDate(String customerTermsAuthDate) {
        this.customerTermsAuthDate = customerTermsAuthDate;
    }
    
}