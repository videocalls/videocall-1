package kr.co.koscom.oppf.apt.cmm.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptMainVO.java
* @Comment  : apt main 관리를 위한 위한 VO
* @author   : 신동진
* @since    : 2016.07.15
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.07.15  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class AptMainVO extends ComDefaultListVO{
	private String stdDate;
	private String Date;
	private String cntApiDurationY;
	private String cntApiDurationN;
	
	/* 조회 조건 */
	private String searchTable;
	private String searchTableCondition;
	private String searchTableCondition1;
	private String searchTableCondition2;
	
	private String sql1;
	private String sql2;
	private String sql3;
	private String sql4;
	private String sql5;
	private String sql6;
	
	//구분 : min(분), 
	private String searchType;
	
	//조회기간 : 분(일자 + 시간), 시간(일자 + 시간), 일(일자), 개월(일자)
	private String searchDateTime;
	private String searchDate;
	private String searchTime;

	//조회기간
	private String searchDateTimeFrom;
	private String searchDateTimeTo;
	private String searchDateFrom;
	private String searchTimeFrom;
	private String searchDateTo;
	private String searchTimeTo;
	private List<String> searchPubCompanyCodeId;
	private List<String> searchSubCompanyCodeId;
	private String searchPubCompanyCodeIdAllYn;
	private String searchSubCompanyCodeIdAllYn;

	//api 종류
	private List<String> searchApiCategory;

	public String getStdDate() {
		return stdDate;
	}

	public void setStdDate(String stdDate) {
		this.stdDate = stdDate;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getCntApiDurationY() {
		return cntApiDurationY;
	}

	public void setCntApiDurationY(String cntApiDurationY) {
		this.cntApiDurationY = cntApiDurationY;
	}

	public String getCntApiDurationN() {
		return cntApiDurationN;
	}

	public void setCntApiDurationN(String cntApiDurationN) {
		this.cntApiDurationN = cntApiDurationN;
	}

	public String getSearchTable() {
		return searchTable;
	}

	public void setSearchTable(String searchTable) {
		this.searchTable = searchTable;
	}

	public String getSearchTableCondition() {
		return searchTableCondition;
	}

	public void setSearchTableCondition(String searchTableCondition) {
		this.searchTableCondition = searchTableCondition;
	}

	public String getSearchTableCondition1() {
		return searchTableCondition1;
	}

	public void setSearchTableCondition1(String searchTableCondition1) {
		this.searchTableCondition1 = searchTableCondition1;
	}

	public String getSearchTableCondition2() {
		return searchTableCondition2;
	}

	public void setSearchTableCondition2(String searchTableCondition2) {
		this.searchTableCondition2 = searchTableCondition2;
	}

	public String getSql1() {
		return sql1;
	}

	public void setSql1(String sql1) {
		this.sql1 = sql1;
	}

	public String getSql2() {
		return sql2;
	}

	public void setSql2(String sql2) {
		this.sql2 = sql2;
	}

	public String getSql3() {
		return sql3;
	}

	public void setSql3(String sql3) {
		this.sql3 = sql3;
	}

	public String getSql4() {
		return sql4;
	}

	public void setSql4(String sql4) {
		this.sql4 = sql4;
	}

	public String getSql5() {
		return sql5;
	}

	public void setSql5(String sql5) {
		this.sql5 = sql5;
	}

	public String getSql6() {
		return sql6;
	}

	public void setSql6(String sql6) {
		this.sql6 = sql6;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchDateTime() {
		return searchDateTime;
	}

	public void setSearchDateTime(String searchDateTime) {
		this.searchDateTime = searchDateTime;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

	public String getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(String searchTime) {
		this.searchTime = searchTime;
	}

	public List<String> getSearchApiCategory() {
		return searchApiCategory;
	}

	public void setSearchApiCategory(List<String> searchApiCategory) {
		this.searchApiCategory = searchApiCategory;
	}

	public String getSearchDateFrom() {
		return searchDateFrom;
	}

	public void setSearchDateFrom(String searchDateFrom) {
		this.searchDateFrom = searchDateFrom;
	}

	public String getSearchTimeFrom() {
		return searchTimeFrom;
	}

	public void setSearchTimeFrom(String searchTimeFrom) {
		this.searchTimeFrom = searchTimeFrom;
	}

	public String getSearchDateTo() {
		return searchDateTo;
	}

	public void setSearchDateTo(String searchDateTo) {
		this.searchDateTo = searchDateTo;
	}

	public String getSearchTimeTo() {
		return searchTimeTo;
	}

	public void setSearchTimeTo(String searchTimeTo) {
		this.searchTimeTo = searchTimeTo;
	}

	public String getSearchDateTimeFrom() {
		return searchDateTimeFrom;
	}

	public void setSearchDateTimeFrom(String searchDateTimeFrom) {
		this.searchDateTimeFrom = searchDateTimeFrom;
	}

	public String getSearchDateTimeTo() {
		return searchDateTimeTo;
	}

	public void setSearchDateTimeTo(String searchDateTimeTo) {
		this.searchDateTimeTo = searchDateTimeTo;
	}

	public List<String> getSearchPubCompanyCodeId() {
		return searchPubCompanyCodeId;
	}

	public void setSearchPubCompanyCodeId(List<String> searchPubCompanyCodeId) {
		this.searchPubCompanyCodeId = searchPubCompanyCodeId;
	}

	public List<String> getSearchSubCompanyCodeId() {
		return searchSubCompanyCodeId;
	}

	public void setSearchSubCompanyCodeId(List<String> searchSubCompanyCodeId) {
		this.searchSubCompanyCodeId = searchSubCompanyCodeId;
	}

	public String getSearchPubCompanyCodeIdAllYn() {
		return searchPubCompanyCodeIdAllYn;
	}

	public void setSearchPubCompanyCodeIdAllYn(String searchPubCompanyCodeIdAllYn) {
		this.searchPubCompanyCodeIdAllYn = searchPubCompanyCodeIdAllYn;
	}

	public String getSearchSubCompanyCodeIdAllYn() {
		return searchSubCompanyCodeIdAllYn;
	}

	public void setSearchSubCompanyCodeIdAllYn(String searchSubCompanyCodeIdAllYn) {
		this.searchSubCompanyCodeIdAllYn = searchSubCompanyCodeIdAllYn;
	}
}