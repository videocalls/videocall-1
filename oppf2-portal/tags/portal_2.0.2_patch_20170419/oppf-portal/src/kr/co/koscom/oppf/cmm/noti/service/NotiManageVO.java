package kr.co.koscom.oppf.cmm.noti.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : NotiManageVO.java
* @Comment  : 관리자의 공지사항 관리를 위한 VO 클래스
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
public class NotiManageVO extends ComDefaultListVO{
	/**
	 * com_notice_info
	 */
    private int rownum;
	private String noticeSeq;
	private String noticeFixYn;
	private String noticeKind;
	private String noticePopYn;
	private String noticeTitle;
	private String noticeContent;
	private int readCount;
	private String noticeStartDate;
	private String noticeExpireDate;
	private String noticePopWidth;
	private String noticePopHeight;
	private String fileId;
	private String useYn;
	
	private String noticeKindName;
	private String noticePopYnName;
	
	/**
	 * 검색조건
	 */
	private List<String> searchNoticeKind;
	private String searchNoticeFixYn;
	private String searchNoticePopYn;
	private String searchUseYn;
	private String schDateFrom;
	private String schDateTo;
	
	/**
	 * 저장
	 */
	private List<String> fileName;
	private List<String> fileSize;
	private List<String> fileExt;
	
	private List<String> delFileSeq;
	
	private List<NotiFileManageVO> fileList; 
	
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public String getNoticeSeq() {
		return noticeSeq;
	}
	public void setNoticeSeq(String noticeSeq) {
		this.noticeSeq = noticeSeq;
	}
	public String getNoticeFixYn() {
		return noticeFixYn;
	}
	public void setNoticeFixYn(String noticeFixYn) {
		this.noticeFixYn = noticeFixYn;
	}
	public String getNoticeKind() {
		return noticeKind;
	}
	public void setNoticeKind(String noticeKind) {
		this.noticeKind = noticeKind;
	}
	public String getNoticePopYn() {
		return noticePopYn;
	}
	public void setNoticePopYn(String noticePopYn) {
		this.noticePopYn = noticePopYn;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getNoticeStartDate() {
		return noticeStartDate;
	}
	public void setNoticeStartDate(String noticeStartDate) {
		this.noticeStartDate = noticeStartDate;
	}
	public String getNoticeExpireDate() {
		return noticeExpireDate;
	}
	public void setNoticeExpireDate(String noticeExpireDate) {
		this.noticeExpireDate = noticeExpireDate;
	}
	public String getNoticePopWidth() {
		return noticePopWidth;
	}
	public void setNoticePopWidth(String noticePopWidth) {
		this.noticePopWidth = noticePopWidth;
	}
	public String getNoticePopHeight() {
		return noticePopHeight;
	}
	public void setNoticePopHeight(String noticePopHeight) {
		this.noticePopHeight = noticePopHeight;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getNoticeKindName() {
		return noticeKindName;
	}
	public void setNoticeKindName(String noticeKindName) {
		this.noticeKindName = noticeKindName;
	}
	public String getNoticePopYnName() {
		return noticePopYnName;
	}
	public void setNoticePopYnName(String noticePopYnName) {
		this.noticePopYnName = noticePopYnName;
	}
	public List<String> getSearchNoticeKind() {
		return searchNoticeKind;
	}
	public void setSearchNoticeKind(List<String> searchNoticeKind) {
		this.searchNoticeKind = searchNoticeKind;
	}
	public String getSearchNoticeFixYn() {
		return searchNoticeFixYn;
	}
	public void setSearchNoticeFixYn(String searchNoticeFixYn) {
		this.searchNoticeFixYn = searchNoticeFixYn;
	}
	public String getSearchNoticePopYn() {
		return searchNoticePopYn;
	}
	public void setSearchNoticePopYn(String searchNoticePopYn) {
		this.searchNoticePopYn = searchNoticePopYn;
	}
	public String getSearchUseYn() {
		return searchUseYn;
	}
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
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
	public List<String> getFileName() {
		return fileName;
	}
	public void setFileName(List<String> fileName) {
		this.fileName = fileName;
	}
	public List<String> getFileSize() {
		return fileSize;
	}
	public void setFileSize(List<String> fileSize) {
		this.fileSize = fileSize;
	}
	public List<String> getFileExt() {
		return fileExt;
	}
	public void setFileExt(List<String> fileExt) {
		this.fileExt = fileExt;
	}
	public List<String> getDelFileSeq() {
		return delFileSeq;
	}
	public void setDelFileSeq(List<String> delFileSeq) {
		this.delFileSeq = delFileSeq;
	}
	public List<NotiFileManageVO> getFileList() {
		return fileList;
	}
	public void setFileList(List<NotiFileManageVO> fileList) {
		this.fileList = fileList;
	}
}