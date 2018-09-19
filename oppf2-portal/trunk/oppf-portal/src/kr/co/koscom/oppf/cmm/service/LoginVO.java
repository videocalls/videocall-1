package kr.co.koscom.oppf.cmm.service;

import java.io.Serializable;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CommonLoginVO.java
* @Comment  : 공통 로그인 VO
* @author   : 신동진
* @since    : 2016.04.29
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.04.29  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class LoginVO extends ComDefaultListVO implements Serializable {
	/**
	 * 기본공통
	 */
	//아이디
	private String id;
	//사용자명_kor
	private String name_kor;
	//사용자명_eng
	private String name_eng;
	
	//사용자 auth (spt:일반, cpt:기업, apt:admin)
	private String authKind;
	//임시비밀번호여부
	private String temp_password_yn;
	//비밀번호 변경 예정일 이내 여부
	private String expire_password_date_yn;
	//비밀번호 변경 최종 예정일 이내 여부
	private String final_password_date_yn;
	
	/**
	 * 화면별 필요 변수 셋팅
	 */
	/**
	 * -------------------------------------------------------------------------------------
	 * spt start
	 * -------------------------------------------------------------------------------------
	 */
	
	//회원 OP 등록 번호
	private String customer_reg_no;
	//가용서버 통합계좌조회 메뉴 사용 여부
	private String integration_account_yn;

	/**
	 * -------------------------------------------------------------------------------------
	 * spt end
	 * -------------------------------------------------------------------------------------
	 */
	/**
	 * -------------------------------------------------------------------------------------
	 * cpt start
	 * -------------------------------------------------------------------------------------
	 */

	//기업 프로파일 등록번호
	private String company_profile_reg_no;
	//기업 운영자 프로파일 등록번호
	private String operator_profile_reg_no;
	//기업명
	private String company_name;
	//기업 핀테크 서비스 타입[ 기업핀테크서비스타입(=G002)(001:둘다,002:서비스제공자,003:앱개발자) ]
	private String company_service_type;
	/* 기업정보 - 계좌발급주체 Y : 자체발급, N : 코스콤 발급, X : 해당없음 */
	private String issue_vtaccount;

	/**
	 * -------------------------------------------------------------------------------------
	 * cpt end
	 * -------------------------------------------------------------------------------------
	 */
	/**
	 * -------------------------------------------------------------------------------------
	 * apt start
	 * -------------------------------------------------------------------------------------
	 */

	//admin 프로파일 등록번호
	private String admin_profile_reg_no;

	/**
	 * -------------------------------------------------------------------------------------
	 * apt end
	 * -------------------------------------------------------------------------------------
	 */



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName_kor() {
		return name_kor;
	}
	public void setName_kor(String name_kor) {
		this.name_kor = name_kor;
	}
	public String getName_eng() {
		return name_eng;
	}
	public void setName_eng(String name_eng) {
		this.name_eng = name_eng;
	}
	public String getAuthKind() {
		return authKind;
	}
	public void setAuthKind(String authKind) {
		this.authKind = authKind;
	}
	public String getTemp_password_yn() {
		return temp_password_yn;
	}
	public void setTemp_password_yn(String temp_password_yn) {
		this.temp_password_yn = temp_password_yn;
	}
	public String getExpire_password_date_yn() {
		return expire_password_date_yn;
	}
	public void setExpire_password_date_yn(String expire_password_date_yn) {
		this.expire_password_date_yn = expire_password_date_yn;
	}
	public String getFinal_password_date_yn() {
		return final_password_date_yn;
	}
	public void setFinal_password_date_yn(String final_password_date_yn) {
		this.final_password_date_yn = final_password_date_yn;
	}
	public String getCustomer_reg_no() {
		return customer_reg_no;
	}
	public void setCustomer_reg_no(String customer_reg_no) {
		this.customer_reg_no = customer_reg_no;
	}
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
	public String getAdmin_profile_reg_no() {
		return admin_profile_reg_no;
	}
	public void setAdmin_profile_reg_no(String admin_profile_reg_no) {
		this.admin_profile_reg_no = admin_profile_reg_no;
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
	public String getIntegration_account_yn() {
		return integration_account_yn;
	}
	public void setIntegration_account_yn(String integration_account_yn) {
		this.integration_account_yn = integration_account_yn;
	}
}
