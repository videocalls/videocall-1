package kr.co.koscom.oppf.cmm.qna.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : QnaVO.java
* @Comment  : [Q&A]정보관리를 위한 VO 클래스
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
public class QnaVO extends ComDefaultListVO{
    
    private Long id;
    private String name;
    private String state;
    private String country;
    
    private String detailId;
    
    private String searchId;
    private String searchName;
    private String searchState;
    private String searchCountry;
    
    private String insertId;
    private String insertName;
    private String insertState;
    private String insertCountry;
    
    private String updateId;
    private String updateName;
    private String updateState;
    private String updateCountry;
    
    private String deleteId;
    
    private String beforeId; //이전글ID
    private String beforeSj; //이전글명
    private String afterId;  //다음글ID
    private String afterSj;  //다음글명
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDetailId() {
        return detailId;
    }
    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getSearchId() {
        return searchId;
    }
    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }
    public String getSearchName() {
        return searchName;
    }
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
    public String getSearchState() {
        return searchState;
    }
    public void setSearchState(String searchState) {
        this.searchState = searchState;
    }
    public String getSearchCountry() {
        return searchCountry;
    }
    public void setSearchCountry(String searchCountry) {
        this.searchCountry = searchCountry;
    }
    public String getInsertId() {
        return insertId;
    }
    public void setInsertId(String insertId) {
        this.insertId = insertId;
    }
    public String getInsertName() {
        return insertName;
    }
    public void setInsertName(String insertName) {
        this.insertName = insertName;
    }
    public String getInsertState() {
        return insertState;
    }
    public void setInsertState(String insertState) {
        this.insertState = insertState;
    }
    public String getInsertCountry() {
        return insertCountry;
    }
    public void setInsertCountry(String insertCountry) {
        this.insertCountry = insertCountry;
    }
    public String getUpdateId() {
        return updateId;
    }
    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }
    public String getUpdateName() {
        return updateName;
    }
    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }
    public String getUpdateState() {
        return updateState;
    }
    public void setUpdateState(String updateState) {
        this.updateState = updateState;
    }
    public String getUpdateCountry() {
        return updateCountry;
    }
    public void setUpdateCountry(String updateCountry) {
        this.updateCountry = updateCountry;
    }
    public String getDeleteId() {
        return deleteId;
    }
    public void setDeleteId(String deleteId) {
        this.deleteId = deleteId;
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
    
    /*실제*/
//    private String qnaSeq="";        //순번
//    private String qnaType="";       //Q&A종류
//    private String qnaPublicYn="";   //공개여부
//    private String qnaPassword="";   //Q&A비밀번호
//    private String qnaRegId="";      //등록자ID
//    private String qnaRegDate="";    //등록일시
//    private String qnaTitle="";      //제목
//    private String qnaContent="";    //내용
//    private String fileId="";        //첨부파일ID
//    
//    public String getQnaSeq() {
//        return qnaSeq;
//    }
//    public void setQnaSeq(String qnaSeq) {
//        this.qnaSeq = qnaSeq;
//    }
//    public String getQnaType() {
//        return qnaType;
//    }
//    public void setQnaType(String qnaType) {
//        this.qnaType = qnaType;
//    }
//    public String getQnaPublicYn() {
//        return qnaPublicYn;
//    }
//    public void setQnaPublicYn(String qnaPublicYn) {
//        this.qnaPublicYn = qnaPublicYn;
//    }
//    public String getQnaPassword() {
//        return qnaPassword;
//    }
//    public void setQnaPassword(String qnaPassword) {
//        this.qnaPassword = qnaPassword;
//    }
//    public String getQnaRegId() {
//        return qnaRegId;
//    }
//    public void setQnaRegId(String qnaRegId) {
//        this.qnaRegId = qnaRegId;
//    }
//    public String getQnaRegDate() {
//        return qnaRegDate;
//    }
//    public void setQnaRegDate(String qnaRegDate) {
//        this.qnaRegDate = qnaRegDate;
//    }
//    public String getQnaTitle() {
//        return qnaTitle;
//    }
//    public void setQnaTitle(String qnaTitle) {
//        this.qnaTitle = qnaTitle;
//    }
//    public String getQnaContent() {
//        return qnaContent;
//    }
//    public void setQnaContent(String qnaContent) {
//        this.qnaContent = qnaContent;
//    }
//    public String getFileId() {
//        return fileId;
//    }
//    public void setFileId(String fileId) {
//        this.fileId = fileId;
//    }
    
}