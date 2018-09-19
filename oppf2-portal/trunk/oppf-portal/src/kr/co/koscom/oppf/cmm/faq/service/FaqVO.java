package kr.co.koscom.oppf.cmm.faq.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : FaqVO.java
* @Comment  : [FAQ]정보관리를 위한 VO 클래스
* @author   : 포털 유제량
* @since    : 2016.05.02
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.02  유제량        최초생성
*
*/
@SuppressWarnings("serial")
public class FaqVO extends ComDefaultListVO{
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
    //FAQ 게시 시작일
    private String faqStartDate;
    //FAQ 게시 종료일
    private String faqExpireDate;    
    //첨부파일 ID
    private String fileId;
    //삭제일시
    private String deleteDate;
    //생성 일시
    private String createDate;
    //생성 관리자 ID
    private String createId;
    //변경 일시
    private String updateDate;
    //변경 관리자 ID
    private String updateId;  
    
    /**
     * FAQ에서 사용할 VO
     */
    private String searchName;
    private String searchKind;
    private String beforeId; //이전글ID
    private String beforeSj; //이전글명
    private String afterId;  //다음글ID
    private String afterSj;  //다음글명
    
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
    public String getDeleteDate() {
        return deleteDate;
    }
    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getCreateId() {
        return createId;
    }
    public void setCreateId(String createId) {
        this.createId = createId;
    }
    public String getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
    public String getUpdateId() {
        return updateId;
    }
    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }
    public String getSearchName() {
        return searchName;
    }
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
    public String getSearchKind() {
        return searchKind;
    }
    public void setSearchKind(String searchKind) {
        this.searchKind = searchKind;
    }
    public String getBeforeId() {
        return beforeId;
    }
    public void setBeforeId(String beforeId) {
        this.beforeId = beforeId;
    }
    public String getBeforeSj() {
        return beforeSj;
    }
    public void setBeforeSj(String beforeSj) {
        this.beforeSj = beforeSj;
    }
    public String getAfterId() {
        return afterId;
    }
    public void setAfterId(String afterId) {
        this.afterId = afterId;
    }
    public String getAfterSj() {
        return afterSj;
    }
    public void setAfterSj(String afterSj) {
        this.afterSj = afterSj;
    }
     
}