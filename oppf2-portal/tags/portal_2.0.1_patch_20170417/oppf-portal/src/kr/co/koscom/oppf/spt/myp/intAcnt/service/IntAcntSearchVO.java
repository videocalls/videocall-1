package kr.co.koscom.oppf.spt.myp.intAcnt.service;

/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : IntAcntSearchVO.java
* @Comment  : 통합계좌 검색
* @author   : 이환덕
* @since    : 2017.03.08
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.21  이환덕        최초생성
*
*/
public class IntAcntSearchVO {
    
    private String customerRegNo; //사용자번호
    
    private String companyId;       //증권사ID
    private String accountNo;       //계좌NO
    private String searchIsinType;       //종목구분
    private String searchIsin;      //조회종목
    private String searchDateFrom;  //조회 시작일
    private String searchDateTo;    //조회 종료일

    public String getCustomerRegNo() {
        return customerRegNo;
    }
    public void setCustomerRegNo(String customerRegNo) {
        this.customerRegNo = customerRegNo;
    }
    public String getCompanyId() {
        return companyId;
    }
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    public String getAccountNo() {
        return accountNo;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    public String getSearchIsinType() {
        return searchIsinType;
    }
    public void setSearchIsinType(String searchIsinType) {
        this.searchIsinType = searchIsinType;
    }
    public String getSearchIsin() {
        return searchIsin;
    }
    public void setSearchIsin(String searchIsin) {
        this.searchIsin = searchIsin;
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
}
