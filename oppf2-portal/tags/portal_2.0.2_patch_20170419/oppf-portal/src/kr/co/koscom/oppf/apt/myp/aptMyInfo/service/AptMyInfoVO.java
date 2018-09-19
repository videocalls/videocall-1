package kr.co.koscom.oppf.apt.myp.aptMyInfo.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptMyInfoVO.java
* @Comment  : [개인회원정보]정보관리를 위한 VO 클래스
* @author   : 포털 유제량
* @since    : 2016.06.28
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.28  유제량        최초생성
*
*/
@SuppressWarnings("serial")
public class AptMyInfoVO extends ComDefaultListVO{
    
    private String adminProfileRegNo="";        //admin 프로파일 등록번호 
    private String adminNameKor="";              //운영자명 한글           
    private String adminNameEng="";              //운영자명 영문           
    private String adminId="";                    //아이디                  
    private String adminPassword="";              //비밀번호                
    private String adminTempPasswordYn="";      //임시비밀번호 여부       
    private String adminChgPasswordDate="";     //비밀번호 변경일시       
    private String adminExpirePasswordDate="";  //비밀번호 변경 예정일    
    private String adminFinalPasswordDate="";   //비밀번호 변경 최종 예정일
    private String adminPasswordFailCnt="";     //비밀번호 실패횟수       
    private String adminPhoneNo="";              //휴대폰번호              
    private String adminTelNo="";                //내선번호                
    private String adminEmail="";                 //email                   
    private String deleteDate="";                 //회원탈퇴 일시           
    private String createDate="";                 //생성 일시               
    private String createId="";                   //생성자 ID               
    private String updateDate="";                 //변경 일시               
    private String updateId="";                   //변경자 ID               
    
    //회원 비밀번호 임시여부
    private String admin_temp_password_yn;
    //회원 비밀번호 변경일자
    private String admin_chg_password_date;
    //비밀번호 변경 예정일
    private String admin_expire_password_date;
    //비밀번호 변경 최종 예정일
    private String admin_final_password_date;
    //비밀번호 실패횟수
    private int admin_password_fail_cnt;
    
    /* otp 정보 */
    private String adminOtpYn;
    private String adminOtpId;
    private String adminOtpAction;
    
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
    public String getAdminPasswordFailCnt() {
        return adminPasswordFailCnt;
    }
    public void setAdminPasswordFailCnt(String adminPasswordFailCnt) {
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
    public String getAdmin_temp_password_yn() {
        return admin_temp_password_yn;
    }
    public void setAdmin_temp_password_yn(String admin_temp_password_yn) {
        this.admin_temp_password_yn = admin_temp_password_yn;
    }
    public String getAdmin_chg_password_date() {
        return admin_chg_password_date;
    }
    public void setAdmin_chg_password_date(String admin_chg_password_date) {
        this.admin_chg_password_date = admin_chg_password_date;
    }
    public String getAdmin_expire_password_date() {
        return admin_expire_password_date;
    }
    public void setAdmin_expire_password_date(String admin_expire_password_date) {
        this.admin_expire_password_date = admin_expire_password_date;
    }
    public String getAdmin_final_password_date() {
        return admin_final_password_date;
    }
    public void setAdmin_final_password_date(String admin_final_password_date) {
        this.admin_final_password_date = admin_final_password_date;
    }
    public int getAdmin_password_fail_cnt() {
        return admin_password_fail_cnt;
    }
    public void setAdmin_password_fail_cnt(int admin_password_fail_cnt) {
        this.admin_password_fail_cnt = admin_password_fail_cnt;
    }
    public String getAdminOtpYn() {
        return adminOtpYn;
    }
    public void setAdminOtpYn(String adminOtpYn) {
        this.adminOtpYn = adminOtpYn;
    }
    public String getAdminOtpId() {
        return adminOtpId;
    }
    public void setAdminOtpId(String adminOtpId) {
        this.adminOtpId = adminOtpId;
    }
    public String getAdminOtpAction() {
        return adminOtpAction;
    }
    public void setAdminOtpAction(String adminOtpAction) {
        this.adminOtpAction = adminOtpAction;
    }
    
}