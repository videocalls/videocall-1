package kr.co.koscom.oppf.apt.cmm.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : LoginVO.java
* @Comment  : admin의 login VO
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
public class AptLoginVO extends ComDefaultListVO{
	/**
	 * com_admin_profile
	 */
	//admin 프로파일 등록번호
	private String admin_profile_reg_no;
	//운영자명 한글
	private String admin_name_kor;
	//운영자명 영문
	private String admin_name_eng;
	//아이디
	private String admin_id;
	//비밀번호
	private String admin_password;
	//임시비밀번호 여부
	private String admin_temp_password_yn;
	//비밀번호 변경일시
	private String admin_chg_password_date;
	//비밀번호 변경 예정일
	private String admin_expire_password_date;
	//비밀번호 변경 최종 예정일
	private String admin_final_password_date;
	//비밀번호 실패횟수
	private int admin_password_fail_cnt ;
	//휴대폰번호(xxx-xxxx-xxxx)
	private String admin_phone_no;
	//email
	private String admin_email;
	//회원탈퇴 일시
	private String delete_date;
	
	//loginCheck
	//운영자 패스워드 유효여부
	private String admin_password_yn;
	//비밀번호 변경 예정일 이내 여부
	private String admin_expire_password_yn;
	//비밀번호 변경 최종 예정일 이내 여부
	private String admin_final_password_yn;
	//탈퇴여부
	private String delete_yn;
	//비밀번호 실패종류
	private String admin_password_fail_type;
	
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
    private String customerRegNo;
	
	public String getAdmin_profile_reg_no() {
		return admin_profile_reg_no;
	}
	public void setAdmin_profile_reg_no(String admin_profile_reg_no) {
		this.admin_profile_reg_no = admin_profile_reg_no;
	}
	public String getAdmin_name_kor() {
		return admin_name_kor;
	}
	public void setAdmin_name_kor(String admin_name_kor) {
		this.admin_name_kor = admin_name_kor;
	}
	public String getAdmin_name_eng() {
		return admin_name_eng;
	}
	public void setAdmin_name_eng(String admin_name_eng) {
		this.admin_name_eng = admin_name_eng;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
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
	public String getAdmin_phone_no() {
		return admin_phone_no;
	}
	public void setAdmin_phone_no(String admin_phone_no) {
		this.admin_phone_no = admin_phone_no;
	}
	public String getAdmin_email() {
		return admin_email;
	}
	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}
	public String getDelete_date() {
		return delete_date;
	}
	public void setDelete_date(String delete_date) {
		this.delete_date = delete_date;
	}
	public String getAdmin_password_yn() {
		return admin_password_yn;
	}
	public void setAdmin_password_yn(String admin_password_yn) {
		this.admin_password_yn = admin_password_yn;
	}
	public String getAdmin_expire_password_yn() {
		return admin_expire_password_yn;
	}
	public void setAdmin_expire_password_yn(String admin_expire_password_yn) {
		this.admin_expire_password_yn = admin_expire_password_yn;
	}
	public String getAdmin_final_password_yn() {
		return admin_final_password_yn;
	}
	public void setAdmin_final_password_yn(String admin_final_password_yn) {
		this.admin_final_password_yn = admin_final_password_yn;
	}
	public String getDelete_yn() {
		return delete_yn;
	}
	public void setDelete_yn(String delete_yn) {
		this.delete_yn = delete_yn;
	}
	public String getAdmin_password_fail_type() {
		return admin_password_fail_type;
	}
	public void setAdmin_password_fail_type(String admin_password_fail_type) {
		this.admin_password_fail_type = admin_password_fail_type;
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
    public String getCustomerRegNo() {
        return customerRegNo;
    }
    public void setCustomerRegNo(String customerRegNo) {
        this.customerRegNo = customerRegNo;
    }
	
}