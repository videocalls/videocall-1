package kr.co.koscom.oppf.cmm.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmNiceIpinCheckplusVO.java
* @Comment  : 공통 NICE평가정보에서 제공하는 i-pin인증 및 휴대폰인증 관리를 위한 VO 클래스
* @author   : 포털 유제량
* @since    : 2016.06.01
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.01  유제량        최초생성
*
*/
@SuppressWarnings("serial")
public class CmmNiceIpinCheckplusVO extends ComDefaultListVO{
    
    /* 아이핀 인증 관련 */
    private int iRtn;                             //아이핀 fnRequest 메소드 결과값
    private String sRtnMsg="";                    //아이핀 처리결과 메세지
    private String sEncData="";                   //아이핀 암호화 된 데이타
    private String domainSpt="";                  //ipin_process로 리턴할 URL Globals.domain.spt값
    
    private String customerVerify="";             //회원 검증값
    private String customerPhone="";              //회원명 한글
    private String customerNameKor="";            //회원 휴대폰 번호
    
    private String checkplusFailChk="";           //휴대폰인증(안심본인인증)
    private String sErrorCode="";                 //휴대폰인증 실패시 에러코드
    
    public int getiRtn() {
        return iRtn;
    }
    public void setiRtn(int iRtn) {
        this.iRtn = iRtn;
    }
    public String getsRtnMsg() {
        return sRtnMsg;
    }
    public void setsRtnMsg(String sRtnMsg) {
        this.sRtnMsg = sRtnMsg;
    }
    public String getsEncData() {
        return sEncData;
    }
    public void setsEncData(String sEncData) {
        this.sEncData = sEncData;
    }
    public String getDomainSpt() {
        return domainSpt;
    }
    public void setDomainSpt(String domainSpt) {
        this.domainSpt = domainSpt;
    }
    public String getCustomerVerify() {
        return customerVerify;
    }
    public void setCustomerVerify(String customerVerify) {
        this.customerVerify = customerVerify;
    }
    public String getCustomerPhone() {
        return customerPhone;
    }
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    public String getCustomerNameKor() {
        return customerNameKor;
    }
    public void setCustomerNameKor(String customerNameKor) {
        this.customerNameKor = customerNameKor;
    }
    public String getCheckplusFailChk() {
        return checkplusFailChk;
    }
    public void setCheckplusFailChk(String checkplusFailChk) {
        this.checkplusFailChk = checkplusFailChk;
    }
    public String getsErrorCode() {
        return sErrorCode;
    }
    public void setsErrorCode(String sErrorCode) {
        this.sErrorCode = sErrorCode;
    }
    
}