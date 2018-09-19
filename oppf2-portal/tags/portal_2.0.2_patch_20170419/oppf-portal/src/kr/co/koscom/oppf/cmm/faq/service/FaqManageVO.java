package kr.co.koscom.oppf.cmm.faq.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : FaqManageVO.java
* @Comment  : 관리자의 FAQ 관리를 위한 VO 클래스
* @author   : 신동진
* @since    : 2016.05.19
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.19  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class FaqManageVO extends ComDefaultListVO{
	/**
     * com_faq_info
     */
    //순번
    private String faqSeq;
    //FAQ 노출
    private String faqKind;
    //제목
    private String faqTitle;
    //내용
    private String faqContent;
    //FAQ 표시순서
    private String faqOrder;
    //FAQ 게시 시작일
    private String faqStartDate;
    //FAQ 게시 종료일
    private String faqExpireDate;    
    //첨부파일 ID
    private String fileId;
    //사용여부
    private String useYn;
	
	/**
	 * 검색조건
	 */
	private List<String> searchFaqKind;
	private String schDateFrom;
	private String schDateTo;
	
	public String getFaqSeq() {
		return faqSeq;
	}
	public void setFaqSeq(String faqSeq) {
		this.faqSeq = faqSeq;
	}
	public String getFaqKind() {
		return faqKind;
	}
	public void setFaqKind(String faqKind) {
		this.faqKind = faqKind;
	}
	public String getFaqTitle() {
		return faqTitle;
	}
	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}
	public String getFaqContent() {
		return faqContent;
	}
	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}
	public String getFaqOrder() {
		return faqOrder;
	}
	public void setFaqOrder(String faqOrder) {
		this.faqOrder = faqOrder;
	}
	public String getFaqStartDate() {
		return faqStartDate;
	}
	public void setFaqStartDate(String faqStartDate) {
		this.faqStartDate = faqStartDate;
	}
	public String getFaqExpireDate() {
		return faqExpireDate;
	}
	public void setFaqExpireDate(String faqExpireDate) {
		this.faqExpireDate = faqExpireDate;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public List<String> getSearchFaqKind() {
		return searchFaqKind;
	}
	public void setSearchFaqKind(List<String> searchFaqKind) {
		this.searchFaqKind = searchFaqKind;
	}
	public String getSchDateFrom() {
		return schDateFrom;
	}
	public void setSchDateFrom(String schDateFrom) {
		this.schDateFrom = schDateFrom;
	}
	public String getSchDateTo() {
		return schDateTo;
	}
	public void setSchDateTo(String schDateTo) {
		this.schDateTo = schDateTo;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	
}