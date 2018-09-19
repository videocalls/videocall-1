package kr.co.koscom.oppf.cpt.cmm.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CptLoginVO.java
* @Comment  : 기업사용자 login VO
* @author   : 신동진
* @since    : 2016.04.29
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.04.29  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class CptLoginVO extends ComDefaultListVO{
	/**
	 * com_company_operator_profile
	 */
	//기업 프로파일 등록번호
	private String company_profile_reg_no;
	//기업 운영자 프로파일 등록번호
	private String operator_profile_reg_no;
	//운영자명 한글
	private String operator_name_kor;
	//운영자명 영문
	private String operator_name_eng;
	//운영자 ID
	private String operator_id;
	//운영자 패스워드
	private String operator_password;
	//임시비밀번호 여부
	private String operator_temp_password_yn;
	//비밀번호 변경일시
	private String operator_chg_password_date;
	//비밀번호 변경 예정일
	private String operator_expire_password_date;
	//비밀번호 변경 최종 예정일
	private String operator_final_password_date;
	//비밀번호 실패횟수
	private int operator_password_fail_cnt; 
	//휴대폰번호
	private String operator_phone_no;
	//email
	private String operator_email;
	//email 인증방식
	private String operator_email_auth_type;
	//이메일 인증여부
	private String operator_email_auth_yn;
	//email 인증일시
	private String operator_email_auth_date;
	//회원가입 일시
	private String operator_reg_date;
	//회원탈퇴 일시
	private String delete_date;
	
	//기업명
	private String company_name;
	//기업핀테크서비스타입(=G002)(001:둘다,002:서비스제공자,003:앱개발자)
	private String company_service_type;
	/* 기업정보 - 계좌발급주체 Y : 자체발급, N : 코스콤 발급, X : 해당없음 */
	private String issue_vtaccount;
	
	//운영자 가입 상태
	private String operator_reg_status;
	//내선번호
	private String operator_tel_no;
	
	//loginCheck
	//운영자 패스워드 유효여부
	private String operator_password_yn;
	//비밀번호 변경 예정일 이내 여부
	private String operator_expire_password_yn;
	//비밀번호 변경 최종 예정일 이내 여부
	private String operator_final_password_yn;
	//탈퇴여부
	private String delete_yn;
	//비밀번호 실패종류
	private String operator_password_fail_type;
	
	
	/**
	 * id/pw 찾기
	 */
	//id/pw tab id
	private String tabId;
	private String searchName;
	private String searchId;
	private String searchEmail;
	
	//이메일인증 등 이메일발송 타입을 구분하기 위함
    private String emailSendType;
    private String operatorRegNo;
    
    public String getCompany_profile_reg_no() {
        return company_profile_reg_no;
    }
    public void setCompany_profile_reg_no(String company_profile_reg_no) {
        this.company_profile_reg_no = company_profile_reg_no;
    }
    public String getOperator_profile_reg_no() {
        return operator_profile_reg_no;
    }
    public void setOperator_profile_reg_no(String operator_profile_reg_no) {
        this.operator_profile_reg_no = operator_profile_reg_no;
    }
    public String getOperator_name_kor() {
        return operator_name_kor;
    }
    public void setOperator_name_kor(String operator_name_kor) {
        this.operator_name_kor = operator_name_kor;
    }
    public String getOperator_name_eng() {
        return operator_name_eng;
    }
    public void setOperator_name_eng(String operator_name_eng) {
        this.operator_name_eng = operator_name_eng;
    }
    public String getOperator_id() {
        return operator_id;
    }
    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }
    public String getOperator_password() {
        return operator_password;
    }
    public void setOperator_password(String operator_password) {
        this.operator_password = operator_password;
    }
    public String getOperator_temp_password_yn() {
        return operator_temp_password_yn;
    }
    public void setOperator_temp_password_yn(String operator_temp_password_yn) {
        this.operator_temp_password_yn = operator_temp_password_yn;
    }
    public String getOperator_chg_password_date() {
        return operator_chg_password_date;
    }
    public void setOperator_chg_password_date(String operator_chg_password_date) {
        this.operator_chg_password_date = operator_chg_password_date;
    }
    public String getOperator_expire_password_date() {
        return operator_expire_password_date;
    }
    public void setOperator_expire_password_date(String operator_expire_password_date) {
        this.operator_expire_password_date = operator_expire_password_date;
    }
    public String getOperator_final_password_date() {
        return operator_final_password_date;
    }
    public void setOperator_final_password_date(String operator_final_password_date) {
        this.operator_final_password_date = operator_final_password_date;
    }
    public int getOperator_password_fail_cnt() {
        return operator_password_fail_cnt;
    }
    public void setOperator_password_fail_cnt(int operator_password_fail_cnt) {
        this.operator_password_fail_cnt = operator_password_fail_cnt;
    }
    public String getOperator_phone_no() {
        return operator_phone_no;
    }
    public void setOperator_phone_no(String operator_phone_no) {
        this.operator_phone_no = operator_phone_no;
    }
    public String getOperator_email() {
        return operator_email;
    }
    public void setOperator_email(String operator_email) {
        this.operator_email = operator_email;
    }
    public String getOperator_email_auth_type() {
        return operator_email_auth_type;
    }
    public void setOperator_email_auth_type(String operator_email_auth_type) {
        this.operator_email_auth_type = operator_email_auth_type;
    }
    public String getOperator_email_auth_yn() {
        return operator_email_auth_yn;
    }
    public void setOperator_email_auth_yn(String operator_email_auth_yn) {
        this.operator_email_auth_yn = operator_email_auth_yn;
    }
    public String getOperator_email_auth_date() {
        return operator_email_auth_date;
    }
    public void setOperator_email_auth_date(String operator_email_auth_date) {
        this.operator_email_auth_date = operator_email_auth_date;
    }
    public String getOperator_reg_date() {
        return operator_reg_date;
    }
    public void setOperator_reg_date(String operator_reg_date) {
        this.operator_reg_date = operator_reg_date;
    }
    public String getDelete_date() {
        return delete_date;
    }
    public void setDelete_date(String delete_date) {
        this.delete_date = delete_date;
    }
    public String getOperator_reg_status() {
        return operator_reg_status;
    }
    public void setOperator_reg_status(String operator_reg_status) {
        this.operator_reg_status = operator_reg_status;
    }
    public String getOperator_tel_no() {
        return operator_tel_no;
    }
    public void setOperator_tel_no(String operator_tel_no) {
        this.operator_tel_no = operator_tel_no;
    }
    public String getOperator_password_yn() {
        return operator_password_yn;
    }
    public void setOperator_password_yn(String operator_password_yn) {
        this.operator_password_yn = operator_password_yn;
    }
    public String getOperator_expire_password_yn() {
        return operator_expire_password_yn;
    }
    public void setOperator_expire_password_yn(String operator_expire_password_yn) {
        this.operator_expire_password_yn = operator_expire_password_yn;
    }
    public String getOperator_final_password_yn() {
        return operator_final_password_yn;
    }
    public void setOperator_final_password_yn(String operator_final_password_yn) {
        this.operator_final_password_yn = operator_final_password_yn;
    }
    public String getDelete_yn() {
        return delete_yn;
    }
    public void setDelete_yn(String delete_yn) {
        this.delete_yn = delete_yn;
    }
    public String getOperator_password_fail_type() {
        return operator_password_fail_type;
    }
    public void setOperator_password_fail_type(String operator_password_fail_type) {
        this.operator_password_fail_type = operator_password_fail_type;
    }
    public String getTabId() {
        return tabId;
    }
    public void setTabId(String tabId) {
        this.tabId = tabId;
    }
    public String getSearchName() {
        return searchName;
    }
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
    public String getSearchId() {
        return searchId;
    }
    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }
    public String getSearchEmail() {
        return searchEmail;
    }
    public void setSearchEmail(String searchEmail) {
        this.searchEmail = searchEmail;
    }
    public String getEmailSendType() {
        return emailSendType;
    }
    public void setEmailSendType(String emailSendType) {
        this.emailSendType = emailSendType;
    }
    public String getOperatorRegNo() {
        return operatorRegNo;
    }
    public void setOperatorRegNo(String operatorRegNo) {
        this.operatorRegNo = operatorRegNo;
    }
    public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_service_type() {
        return company_service_type;
    }
    public void setCompany_service_type(String company_service_type) {
        this.company_service_type = company_service_type;
    }
	public String getIssue_vtaccount() {
		return issue_vtaccount;
	}
	public void setIssue_vtaccount(String issue_vtaccount) {
		this.issue_vtaccount = issue_vtaccount;
	}	
}