package kr.co.koscom.oppf.apt.aptUsr.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptUserInfoVO.java
* @Comment  : admin 포털 내정보 관리 VO
* @author   : 신동진
* @since    : 2016.05.24
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.24  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class AptUserInfoVO extends ComDefaultListVO{
	/**
	 * com_admin_profile
	 */
	//admin 프로파일 등록번호
	private String adminProfileRegNo;
	//운영자명 한글
	private String adminNameKor;
	//운영자명 영문
	private String adminNameEng;
	//아이디
	private String adminId;
	//비밀번호
	private String adminPassword;
	//임시비밀번호 여부
	private String adminTempPasswordYn;
	//비밀번호 변경일시
	private String adminChgPasswordDate;
	//비밀번호 변경 예정일
	private String adminExpirePasswordDate;
	//비밀번호 변경 최종 예정일
	private String adminFinalPasswordDate;
	//비밀번호 실패횟수
	private int adminPasswordFailCnt ;
	//휴대폰번호(xxx-xxxx-xxxx)
	private String adminPhoneNo;
	//내선번호(xxx-xxxx-xxxx)
	private String adminTelNo;
	//email
	private String adminEmail;
	
	public String getAdminProfileRegNo() {
		return adminProfileRegNo;
	}
	public void setAdminProfileRegNo(String adminProfileRegNo) {
		this.adminProfileRegNo = adminProfileRegNo;
	}
	public String getAdminNameKor() {
		return adminNameKor;
	}
	public void setAdminNameKor(String adminNameKor) {
		this.adminNameKor = adminNameKor;
	}
	public String getAdminNameEng() {
		return adminNameEng;
	}
	public void setAdminNameEng(String adminNameEng) {
		this.adminNameEng = adminNameEng;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getAdminTempPasswordYn() {
		return adminTempPasswordYn;
	}
	public void setAdminTempPasswordYn(String adminTempPasswordYn) {
		this.adminTempPasswordYn = adminTempPasswordYn;
	}
	public String getAdminChgPasswordDate() {
		return adminChgPasswordDate;
	}
	public void setAdminChgPasswordDate(String adminChgPasswordDate) {
		this.adminChgPasswordDate = adminChgPasswordDate;
	}
	public String getAdminExpirePasswordDate() {
		return adminExpirePasswordDate;
	}
	public void setAdminExpirePasswordDate(String adminExpirePasswordDate) {
		this.adminExpirePasswordDate = adminExpirePasswordDate;
	}
	public String getAdminFinalPasswordDate() {
		return adminFinalPasswordDate;
	}
	public void setAdminFinalPasswordDate(String adminFinalPasswordDate) {
		this.adminFinalPasswordDate = adminFinalPasswordDate;
	}
	public int getAdminPasswordFailCnt() {
		return adminPasswordFailCnt;
	}
	public void setAdminPasswordFailCnt(int adminPasswordFailCnt) {
		this.adminPasswordFailCnt = adminPasswordFailCnt;
	}
	public String getAdminPhoneNo() {
		return adminPhoneNo;
	}
	public void setAdminPhoneNo(String adminPhoneNo) {
		this.adminPhoneNo = adminPhoneNo;
	}
	public String getAdminTelNo() {
		return adminTelNo;
	}
	public void setAdminTelNo(String adminTelNo) {
		this.adminTelNo = adminTelNo;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
}