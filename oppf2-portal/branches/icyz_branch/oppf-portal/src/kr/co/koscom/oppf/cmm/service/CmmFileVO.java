package kr.co.koscom.oppf.cmm.service;

import java.io.Serializable;


/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CmmFileVO.java
* @Comment  : 파일 VO
* @author   : 이환덕
* @since    : 2016.04.29
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.04.29  이환덕        최초생성
*
*/
@SuppressWarnings("serial")
public class CmmFileVO extends ComDefaultListVO implements Serializable {

    /**
     * 첨부파일 아이디
     */
    public String atchmnflId = "";
    
    /**
     * 파일순번
     */
    public String fileSn = "";
    
    /**
     * 저장파일경로명
     */
    public String streFlpthNm = "";
    
    /**
     * 저장파일명
     */
    public String streFileNm = "";
    
    /**
     * 원본파일명
     */
    public String orginlFileNm = "";
    
    /**
     * 파일확장자명
     */
    public String fileExtsnNm = "";
    
    /**
     * 파일크기
     */
    public String fileMg = "";
    
    /**************************************************************
     * app 아이콘 공통
     **************************************************************/
    private String appId;
    private String appName;
    private byte[] appIcon;
    
    /**************************************************************
     * company ci 공통
     **************************************************************/
    private String companyProfileRegNo;
    private String companyName;
    private byte[] companyCi;
    
    /**************************************************************
     * 공지사항 첨부파일 공통
     **************************************************************/
    private String fileId;
	private String fileSeq;
	private String fileName;
	private String fileExtention;
	private String fileSize;
	private byte[] fileData;
       

	public String getAtchmnflId() {
		return atchmnflId;
	}

	public void setAtchmnflId(String atchmnflId) {
		this.atchmnflId = atchmnflId;
	}

	public String getFileSn() {
		return fileSn;
	}

	public void setFileSn(String fileSn) {
		this.fileSn = fileSn;
	}

	public String getStreFlpthNm() {
		return streFlpthNm;
	}

	public void setStreFlpthNm(String streFlpthNm) {
		this.streFlpthNm = streFlpthNm;
	}

	public String getStreFileNm() {
		return streFileNm;
	}

	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}


	public String getFileMg() {
		return fileMg;
	}

	public void setFileMg(String fileMg) {
		this.fileMg = fileMg;
	}

	public String getFileExtsnNm() {
		return fileExtsnNm;
	}

	public void setFileExtsnNm(String fileExtsnNm) {
		this.fileExtsnNm = fileExtsnNm;
	}

	public String getOrginlFileNm() {
		return orginlFileNm;
	}

	public void setOrginlFileNm(String orginlFileNm) {
		this.orginlFileNm = orginlFileNm;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public byte[] getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(byte[] appIcon) {
		this.appIcon = appIcon;
	}

	public String getCompanyProfileRegNo() {
		return companyProfileRegNo;
	}

	public void setCompanyProfileRegNo(String companyProfileRegNo) {
		this.companyProfileRegNo = companyProfileRegNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public byte[] getCompanyCi() {
		return companyCi;
	}

	public void setCompanyCi(byte[] companyCi) {
		this.companyCi = companyCi;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileSeq() {
		return fileSeq;
	}

	public void setFileSeq(String fileSeq) {
		this.fileSeq = fileSeq;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtention() {
		return fileExtention;
	}

	public void setFileExtention(String fileExtention) {
		this.fileExtention = fileExtention;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	
	
}
