package kr.co.koscom.oppf.apt.stats.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : StatsUserVO.java
* @Comment  : 회원 통계 관리를 위한 위한 VO
* @author   : 신동진
* @since    : 2016.07.10
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.07.10  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class StatsUserVO extends ComDefaultListVO{
	/* 조회용 */
	private String sql1;
	private String sql2;
	private String sql3;
	private String searchTable;
	private String searchTableCondition;
	
	/* 조회 조건 */
	//회원구분
	private List<String> searchUserKind;
	private String searchUserKindAllYn;
	
	//가입상태
	private List<String> searchRegStatus;
	private String searchRegStatusAllYn;
		
	//구분 : 시간, 일, 개월 
	private String searchType;
	
	//조회기간 : 시간(일자 + 시간), 일(일자), 개월(일자)
	private String searchDateTime;
	
	//header용 조회결과
	private String searchStdDate;
	private String searchStdDateText;
	
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
	public List<String> getSearchUserKind() {
		return searchUserKind;
	}
	public void setSearchUserKind(List<String> searchUserKind) {
		this.searchUserKind = searchUserKind;
	}
	public String getSearchUserKindAllYn() {
		return searchUserKindAllYn;
	}
	public void setSearchUserKindAllYn(String searchUserKindAllYn) {
		this.searchUserKindAllYn = searchUserKindAllYn;
	}
	public List<String> getSearchRegStatus() {
		return searchRegStatus;
	}
	public void setSearchRegStatus(List<String> searchRegStatus) {
		this.searchRegStatus = searchRegStatus;
	}
	public String getSearchRegStatusAllYn() {
		return searchRegStatusAllYn;
	}
	public void setSearchRegStatusAllYn(String searchRegStatusAllYn) {
		this.searchRegStatusAllYn = searchRegStatusAllYn;
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
	public String getSearchStdDate() {
		return searchStdDate;
	}
	public void setSearchStdDate(String searchStdDate) {
		this.searchStdDate = searchStdDate;
	}
	public String getSearchStdDateText() {
		return searchStdDateText;
	}
	public void setSearchStdDateText(String searchStdDateText) {
		this.searchStdDateText = searchStdDateText;
	}	
}