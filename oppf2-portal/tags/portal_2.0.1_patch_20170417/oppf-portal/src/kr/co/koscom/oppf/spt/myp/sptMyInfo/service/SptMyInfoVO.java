package kr.co.koscom.oppf.spt.myp.sptMyInfo.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptMyInfoVO.java
* @Comment  : [개인회원정보]정보관리를 위한 VO 클래스
* @author   : 포털 유제량
* @since    : 2016.05.09
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.09  유제량        최초생성
*
*/
@SuppressWarnings("serial")
public class SptMyInfoVO extends ComDefaultListVO{
    
    private String customerRegNo="";                 //회원 OP 등록 번호            
    private String customerId="";                    //회원 아이디                
    private String customerPassword="";              //회원 비밀번호              
    private String customerTempPasswordYn="";        //회원 비밀번호 임시여부     
    private String customerChgPasswordDate="";       //회원 비밀번호 변경일자     
    private String customerExpirePasswordDate="";    //비밀번호 변경 예정일
    private String customerFinalPasswordDate="";     //비밀번호 변경 최종 예정일  
    private String customerPasswordFailCnt="";       //비밀번호 실패횟수          
    private String customerRegStatus="";             //회원 가입 상태             
    private String customerStep="";                  //회원 가입 Step             
    private String customerNameKor="";               //회원명 한글                
    private String customerNameEng="";               //회원명 영문                
    private String customerPhone="";                 //회원 휴대폰 번호           
    private String customerEmail="";                 //회원 이메일 주소           
    private String customerEmailAuthYn="";           //회원 이메일 인증여부       
    private String customerEmailRegDate="";          //회원 이메일 인증일시       
    private String customerBirthDay="";              //생년월일                   
    private String customerSex="";                   //성별                       
    private String customerEmailReceiveYn="";        //이메일 수신동의여부        
    private String customerEmailReceiveDate="";      //이메일 수신동의 여부일시   
    private String customerRegDate="";               //회원가입일시               
    private String deleteDate="";                    //회원탈퇴일시               
    private String createDate="";                    //생성일시                   
    private String createId="";                      //생성자 ID                  
    private String updateDate="";                    //변경일시                   
    private String updateId="";                      //변경자 ID
    
    private String customerSexName="";               //성별이름
    
    //회원 비밀번호 임시여부
    private String customer_temp_password_yn;
    //회원 비밀번호 변경일자
    private String customer_chg_password_date;
    //비밀번호 변경 예정일
    private String customer_expire_password_date;
    //비밀번호 변경 최종 예정일
    private String customer_final_password_date;
    //비밀번호 실패횟수
    private int customer_password_fail_cnt;
    
    //DN 정보
    private String customerVerifyDn;
    
    /* otp 정보 */
    private String customerOtpYn;
    private String customerOtpId;
    private String customerOtpAction;

    public String getCustomerRegNo() {
        return customerRegNo;
    }

    public void setCustomerRegNo(String customerRegNo) {
        this.customerRegNo = customerRegNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerTempPasswordYn() {
        return customerTempPasswordYn;
    }

    public void setCustomerTempPasswordYn(String customerTempPasswordYn) {
        this.customerTempPasswordYn = customerTempPasswordYn;
    }

    public String getCustomerChgPasswordDate() {
        return customerChgPasswordDate;
    }

    public void setCustomerChgPasswordDate(String customerChgPasswordDate) {
        this.customerChgPasswordDate = customerChgPasswordDate;
    }

    public String getCustomerExpirePasswordDate() {
        return customerExpirePasswordDate;
    }

    public void setCustomerExpirePasswordDate(String customerExpirePasswordDate) {
        this.customerExpirePasswordDate = customerExpirePasswordDate;
    }

    public String getCustomerFinalPasswordDate() {
        return customerFinalPasswordDate;
    }

    public void setCustomerFinalPasswordDate(String customerFinalPasswordDate) {
        this.customerFinalPasswordDate = customerFinalPasswordDate;
    }

    public String getCustomerPasswordFailCnt() {
        return customerPasswordFailCnt;
    }

    public void setCustomerPasswordFailCnt(String customerPasswordFailCnt) {
        this.customerPasswordFailCnt = customerPasswordFailCnt;
    }

    public String getCustomerRegStatus() {
        return customerRegStatus;
    }

    public void setCustomerRegStatus(String customerRegStatus) {
        this.customerRegStatus = customerRegStatus;
    }

    public String getCustomerStep() {
        return customerStep;
    }

    public void setCustomerStep(String customerStep) {
        this.customerStep = customerStep;
    }

    public String getCustomerNameKor() {
        return customerNameKor;
    }

    public void setCustomerNameKor(String customerNameKor) {
        this.customerNameKor = customerNameKor;
    }

    public String getCustomerNameEng() {
        return customerNameEng;
    }

    public void setCustomerNameEng(String customerNameEng) {
        this.customerNameEng = customerNameEng;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerEmailAuthYn() {
        return customerEmailAuthYn;
    }

    public void setCustomerEmailAuthYn(String customerEmailAuthYn) {
        this.customerEmailAuthYn = customerEmailAuthYn;
    }

    public String getCustomerEmailRegDate() {
        return customerEmailRegDate;
    }

    public void setCustomerEmailRegDate(String customerEmailRegDate) {
        this.customerEmailRegDate = customerEmailRegDate;
    }

    public String getCustomerBirthDay() {
        return customerBirthDay;
    }

    public void setCustomerBirthDay(String customerBirthDay) {
        this.customerBirthDay = customerBirthDay;
    }

    public String getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(String customerSex) {
        this.customerSex = customerSex;
    }

    public String getCustomerEmailReceiveYn() {
        return customerEmailReceiveYn;
    }

    public void setCustomerEmailReceiveYn(String customerEmailReceiveYn) {
        this.customerEmailReceiveYn = customerEmailReceiveYn;
    }

    public String getCustomerEmailReceiveDate() {
        return customerEmailReceiveDate;
    }

    public void setCustomerEmailReceiveDate(String customerEmailReceiveDate) {
        this.customerEmailReceiveDate = customerEmailReceiveDate;
    }

    public String getCustomerRegDate() {
        return customerRegDate;
    }

    public void setCustomerRegDate(String customerRegDate) {
        this.customerRegDate = customerRegDate;
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

    public String getCustomerSexName() {
        return customerSexName;
    }

    public void setCustomerSexName(String customerSexName) {
        this.customerSexName = customerSexName;
    }
    public String getCustomer_temp_password_yn() {
        return customer_temp_password_yn;
    }

    public void setCustomer_temp_password_yn(String customer_temp_password_yn) {
        this.customer_temp_password_yn = customer_temp_password_yn;
    }

    public String getCustomer_chg_password_date() {
        return customer_chg_password_date;
    }

    public void setCustomer_chg_password_date(String customer_chg_password_date) {
        this.customer_chg_password_date = customer_chg_password_date;
    }

    public String getCustomer_expire_password_date() {
        return customer_expire_password_date;
    }

    public void setCustomer_expire_password_date(String customer_expire_password_date) {
        this.customer_expire_password_date = customer_expire_password_date;
    }

    public String getCustomer_final_password_date() {
        return customer_final_password_date;
    }

    public void setCustomer_final_password_date(String customer_final_password_date) {
        this.customer_final_password_date = customer_final_password_date;
    }

    public int getCustomer_password_fail_cnt() {
        return customer_password_fail_cnt;
    }

    public void setCustomer_password_fail_cnt(int customer_password_fail_cnt) {
        this.customer_password_fail_cnt = customer_password_fail_cnt;
    }
    
	public String getCustomerVerifyDn() {
		return customerVerifyDn;
	}

	public void setCustomerVerifyDn(String customerVerifyDn) {
		this.customerVerifyDn = customerVerifyDn;
	}

	public String getCustomerOtpYn() {
		return customerOtpYn;
	}

	public void setCustomerOtpYn(String customerOtpYn) {
		this.customerOtpYn = customerOtpYn;
	}

	public String getCustomerOtpId() {
		return customerOtpId;
	}

	public void setCustomerOtpId(String customerOtpId) {
		this.customerOtpId = customerOtpId;
	}

	public String getCustomerOtpAction() {
		return customerOtpAction;
	}

	public void setCustomerOtpAction(String customerOtpAction) {
		this.customerOtpAction = customerOtpAction;
	}
	
}