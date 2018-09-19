package kr.co.koscom.oppfm.member.service.impl;

import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppfm.account.model.SptCustomerCompanyTermsProfileRes;
import kr.co.koscom.oppfm.cmm.eversafe.EversafeKeypadUtil;
import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.model.EmailReqRes;
import kr.co.koscom.oppfm.cmm.service.EmailService;
import kr.co.koscom.oppfm.cmm.util.CommonCodeConstants;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.cmm.util.CookieUtil;
import kr.co.koscom.oppfm.cmm.util.OppfStringUtil;
import kr.co.koscom.oppfm.login.model.LoginRes;
import kr.co.koscom.oppfm.member.dao.MemberMapper;
import kr.co.koscom.oppfm.member.model.MemberReq;
import kr.co.koscom.oppfm.member.model.MemberRes;
import kr.co.koscom.oppfm.member.model.TemporaryMemberInfoRes;
import kr.co.koscom.oppfm.member.service.MemberService;
import kr.co.koscom.oppfm.otp.model.OtpReq;
import kr.co.koscom.oppfm.otp.service.CommonOtpRequestService;
import kr.co.koscom.oppfm.terms.model.TermsRes;
import lombok.extern.slf4j.Slf4j;


/**
 * MemberServiceImpl
 * 
 * @author 판광 on 2017-05-15.
 * * Modify by sh.lee on 2017-05-17.
 *
 */
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private MemberMapper memberMapper;

	@Resource(name = "config")
	private Properties properties;

	/* OTP 정보 저장 service */
	@Autowired
	private CommonOtpRequestService cmmOtpReqService;

	@Autowired
	private EmailService emailService;

	/**
	 * 회원 상세 조회
	 * */
	@Override
	public CommonResponse getUserInfo(MemberReq memberReq){
		
		MemberRes memberRes = memberMapper.selectUserInfo(memberReq);

        Map<String, Object> body = new HashMap<>();
        body.put("memberRes", memberRes);
		
		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
	}
	
    /**
	 * 회원 정보 수정
	 * */
	@Transactional
	public CommonResponse modifyUserInfo(MemberReq memberReq){


		if(!OppfStringUtil.isEmpty(memberReq.getCustomerPassword())){
			memberReq.setCustomerExpirePasswordDate(properties.get("Globals.user.policy.password.expire").toString());
			memberReq.setCustomerFinalPasswordDate(properties.get("Globals.user.policy.password.final").toString());

            /* 복호화 */
            try {
                String customerPassword = EversafeKeypadUtil.RSADecoderSecureText(memberReq.getCustomerPassword());
                memberReq.setCustomerPassword(customerPassword);
            } catch (GeneralSecurityException e) {
                throw new CommonException(ErrorCodeConstants.USER_PWD_SECURE_ERROR, null);
            }
		}

		memberReq.setCustomerPhone(OppfStringUtil.phoneConvertMinus(memberReq.getCustomerPhone()));

		// OTP 정보 등록
		/*
		if(null != memberReq.getCustomerOtpId() && "Y".equals(memberReq.getCustomerOtpYn())){
			OtpReq otpReq = new OtpReq();
			otpReq.setCustomerOtpId(memberReq.getCustomerOtpId());
			otpReq.setCustomerRegNo(memberReq.getCustomerRegNo());
			cmmOtpReqService.saveOtpProfile(otpReq);
		}else if("N".equals(memberReq.getCustomerOtpYn())){
			OtpReq otpReq = new OtpReq();
			otpReq.setCustomerRegNo(memberReq.getCustomerRegNo());
			cmmOtpReqService.deleteOtpProfile(otpReq);
		}
		*/

		int rs = memberMapper.updateUserInfo(memberReq);

		// update 실패시 에러처리
		if (rs == 0) {
			throw new CommonException(ErrorCodeConstants.REQUIRED_PARAMETER_ERROR, null);
		} else {
			//성공 시 히스토리
			memberMapper.insertMemberHist(memberReq);
		}
		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
		
	}
	
	/**
     * 비밀번호 일치여부 확인
     */
	@Override
	public CommonResponse getCheckPw(MemberReq memberReq, HttpServletRequest request) {
		
		String severType = properties.get("Globals.server.type").toString();

        try {
            if(!OppfStringUtil.isEmpty(memberReq.getCustomerPassword())){
                String customerPassword = EversafeKeypadUtil.RSADecoderSecureText(memberReq.getCustomerPassword());
                memberReq.setCustomerPassword(customerPassword);
            }else{
                throw new CommonException(ErrorCodeConstants.PWD_NULL, null);
            }
        } catch (GeneralSecurityException e) {
            throw new CommonException(ErrorCodeConstants.USER_PWD_SECURE_ERROR, null);
        }
		String cfPwdResult= memberMapper.selectCheckPw(memberReq);

		if(cfPwdResult.equals("N")){
			//실패
			throw new CommonException(ErrorCodeConstants.FAIL_PASS_WORD, null);
		}
		Map<String, Object> body = new HashMap<>();
		body.put("cfPasswordResult", cfPwdResult);

		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
	}

	/**
     * 비밀번호 변경
     */
	@Transactional
	public CommonResponse changePassword(MemberReq memberReq, HttpServletRequest request) {

		try {
			if(!OppfStringUtil.isEmpty(memberReq.getNewPassword())){
				memberReq.setCustomerPassword(EversafeKeypadUtil.RSADecoderSecureText(memberReq.getNewPassword()));
			}
			if(!OppfStringUtil.isEmpty(memberReq.getCustomerPasswordConfirm())){
				memberReq.setCustomerPasswordConfirm(EversafeKeypadUtil.RSADecoderSecureText(memberReq.getCustomerPasswordConfirm()));
			}

		} catch (GeneralSecurityException e) {
			throw new CommonException(ErrorCodeConstants.USER_PWD_SECURE_ERROR, null);
		}


//8~15자의 영문 대/소문자, 숫자, 특수문자 중 3개 이상의 조합이어야 합니다.

		Map<String, Object> body = new HashMap<>();

		// 현재 비밀번호와 새 비밀번호가 동일 err
		if(!OppfStringUtil.isEmpty(memberReq.getCustomerPassword())){
			MemberRes memberRes = memberMapper.selectUserInfo(memberReq);

			boolean newPasswordValid = true;
			if("Y".equals(memberRes.getCustomerPasswordYn())){
				body.put("newPassword", messageUtil.getMessage("9141", null));
//				body.put("newPassword", ErrorCodeConstants.NEW_PWD_CONFIRM);

			}else if(!"Y".equals(memberRes.getCustomerPasswordYn())){
				int pwChkCnt1 = 0;
				if(memberReq.getCustomerPassword().matches(".*[0-9]+.*")){
					// 숫자있음
					pwChkCnt1 = 1;
				}
				int pwChkCnt2 = 0;
				if(memberReq.getCustomerPassword().matches(".*[A-Z]+.*")){
					// 영어 대문자 있음
					pwChkCnt2 = 1;
				}
				int pwChkCnt3 = 0;
				if(memberReq.getCustomerPassword().matches(".*[a-z]+.*")){
					// 영어 소문자 있음
					pwChkCnt3 = 1;
				}

				int pwChkCnt4 = 0;
				if(memberReq.getCustomerPassword().matches(".*[~,!,\",@,#,$,%,<,>,^,&,*,(,),.,?,_,-,+,=,',`,|,/,:,\\},\\{,\\[,\\]]+.*")){
					// 특수문자 있음
					pwChkCnt4 = 1;
				}
				int pwChkCnt = pwChkCnt1 + pwChkCnt2 + pwChkCnt3 + pwChkCnt4;
				if(memberReq.getCustomerPassword().length() <= 7 || memberReq.getCustomerPassword().length() >= 16){
					// 비밀번호는 8자 이상 15자 이하입니다.
					newPasswordValid = false;
				}
				else if(pwChkCnt <= 2){
					// 비밀번호는 영문 대/소문자, 숫자, 특수문자 중 3개이상의 조합이여야만 합니다.
					newPasswordValid = false;
				}

				if(!newPasswordValid){
					body.put("newPassword", messageUtil.getMessage("9152", null));
				}
			}


			if(body.size() == 0){
				body.put("newPassword", "200");
			}

		}




		// 새 비밀번호와 비밀번호 확인 비동일 err
		if(!OppfStringUtil.isEmpty(memberReq.getCustomerPassword()) && !OppfStringUtil.isEmpty(memberReq.getCustomerPasswordConfirm()) ){
			if(!memberReq.getCustomerPassword().equals(memberReq.getCustomerPasswordConfirm())){
				body.put("newPasswordConfirm", messageUtil.getMessage("9129", null));
//				body.put("newPasswordConfirm", ErrorCodeConstants.NEW_PWD_COMPARE_ERROR);
			}else if(memberReq.getCustomerPassword().equals(memberReq.getCustomerPasswordConfirm())){
				body.put("newPasswordConfirm", "200");
			}
		}

		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
	}


	/**
	 * 회원 등록
	 * */
	@Transactional
	public CommonResponse createMember(MemberReq memberReq) {
		


		memberReq.setCustomerPhone(OppfStringUtil.phoneConvertMinus(memberReq.getCustomerPhone()));

		// 비회원일시
		if("Y".equals(memberReq.getTemporaryMemberYn())){
			
			// 비회원(임시회원 여부 N으로 변경)
			memberReq.setTemporaryMemberYn("N");
			modifyUserInfo(memberReq);
			
		} else {

			/* 비밀번호 복호화 */
			if(!OppfStringUtil.isEmpty(memberReq.getCustomerPassword()) && OppfStringUtil.isEmpty(memberReq.getCustomerPasswordConfirm())) {
				try {
					String customerPassword = EversafeKeypadUtil.RSADecoderSecureText(memberReq.getCustomerPassword());
					memberReq.setCustomerPassword(customerPassword);
				} catch (GeneralSecurityException e) {
					throw new CommonException(ErrorCodeConstants.USER_PWD_SECURE_ERROR, null);
				}
			}else{
				//비밀번호를 입력해 주세요 alert창
				throw new CommonException(ErrorCodeConstants.USER_PWD_SECURE_ERROR, null);
			}
	
			/* 회원 정보 등록 */
			memberReq.setCustomerRegNoPrefix("C");
			memberMapper.insertMember(memberReq);
			log.debug("========================================= 등록한 회원정보 : {}", memberReq.getCustomerRegNo());
			
			/* CI 정보 등록 */
			if(memberReq.getCustomerVerifyCi() != null) {
				memberReq.setCustomerVerifyType(CommonCodeConstants.VERIFY_TYPE_CI);
				memberReq.setCustomerVerify(memberReq.getCustomerVerifyCi());
				insertMemberVerifyProfile(memberReq);
	            log.debug("========================================= 등록한 CI 정보 : {}", memberReq.getCustomerVerifyCi());
			}
	
			/* DN 정보 등록 */
			if(memberReq.getCustomerVerifyDn() != null) {
				memberReq.setCustomerVerifyType(CommonCodeConstants.VERIFY_TYPE_DN);
				memberReq.setCustomerVerify(memberReq.getCustomerVerifyDn());
				insertMemberVerifyProfile(memberReq);
	            log.debug("========================================= 등록한 DN 정보 : {}", memberReq.getCustomerVerifyDn());
			}

		}

		/* 약관 동의 등록 */
		insertMemberTermsProfile(memberReq);
		log.debug("========================================= 등록한 회원동의 정보 : {}", memberReq.getCustomerTermsList());


		/* OTP 정보 등록 */
	/*
		if(null != memberReq.getCustomerOtpId()){
			OtpReq otpReq = new OtpReq();
			otpReq.setCustomerOtpId(memberReq.getCustomerOtpId());
			otpReq.setCustomerRegNo(memberReq.getCustomerRegNo());
			cmmOtpReqService.saveOtpProfile(otpReq);
		}
	*/

		

        
        if(!OppfStringUtil.isEmpty(memberReq.getDeviceType())){
        	
        	if(CommonCodeConstants.MBR_MOBILE_OS_ADR.equals(memberReq.getDeviceType())){
        		memberReq.setDeviceType(CommonCodeConstants.MBR_MOBILE_OS_TYPE_ADR);
        	} else if(CommonCodeConstants.MBR_MOBILE_OS_IOS.equals(memberReq.getDeviceType())){
        		memberReq.setDeviceType(CommonCodeConstants.MBR_MOBILE_OS_TYPE_IOS);
        	} else {
        		
        	}
        }
        
		
		/* 일반회원 모바일 정보 */
		insertSptMobileInfo(memberReq);

		Map<String, Object> body = new HashMap<>();
		body.put("customerRegNo", memberReq.getCustomerRegNo());
		
		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
	}

	/**
	 * 약관동의 입력
	 * */
	@Transactional
	private int insertMemberTermsProfile(MemberReq memberReq) {
        int result = 0;
        if(memberReq.getCustomerTermsList().size() > 0){
            for (TermsRes terms : memberReq.getCustomerTermsList()) {
				terms.setCustomerRegNo(memberReq.getCustomerRegNo());
                int res1 = memberMapper.insertUpdateMemberTermsProfile(terms);
                log.debug("===========================insertMemberTermsProfile TermsType : {}", terms.getCustomerTermsType());
                if(res1 != 0){
                    int res2 = memberMapper.insertMemberTermsProfileHist(memberReq);
                    result = res1 + res2;
                    log.debug("===========================insertMemberTermsProfile rs : {}", result);

                }else{
                    throw new CommonException(ErrorCodeConstants.INTERNAL_SERVER_ERROR, null);
                }
            }
		}else{
            // 500 err
            throw new CommonException(ErrorCodeConstants.INTERNAL_SERVER_ERROR, null);
        }
		return result;
	}

	/**
	 * CI, DN 값 입력
	 * */
	@Transactional
	public int insertMemberVerifyProfile(MemberReq memberReq){
		int result = memberMapper.insertMemberVerifyProfile(memberReq);
		if(result != 0){
			result += memberMapper.insertMemberVerifyProfileHist(memberReq);
            log.debug("===========================insertMemberTermsProfile rs : {}", result);
		}else{
			throw new CommonException(ErrorCodeConstants.INTERNAL_SERVER_ERROR, null);
		}
		return result;
	}


	/**
	 * 회원 탈퇴
	 * */
	@Transactional
	public CommonResponse removeMember(LoginRes loginInfo, HttpServletResponse response) {
		
		//int result = memberMapper.removeMember(loginInfo);
		MemberReq memberReq = new MemberReq();
		memberReq.setCustomerRegNo(loginInfo.getCustomerRegNo());

		memberReq.setCustomerRegStatus(CommonCodeConstants.MBR_WITHDRAW_STATUS.toString());
		
		// 회원 테이블 update
		int result = memberMapper.updateSptMbrSecesInfo(memberReq);
		
		// 탈퇴 회원 데이터 이관
		result = memberMapper.moveMemberInfo(memberReq);
		if(result == 0){
			throw new CommonException(ErrorCodeConstants.FAIL_REMOVE_MEMBER, null);
		}
		
		// 히스토리 테이블 insert
		memberMapper.insertMemberHist(memberReq);

		// CI 검증 데이터 이관
		memberReq.setCustomerVerifyType(CommonCodeConstants.VERIFY_TYPE_CI.toString());
		result = memberMapper.insertLeaveMemberInfo(memberReq);
		if(result == 0){
			throw new CommonException(ErrorCodeConstants.FAIL_REMOVE_MEMBER, null);
		}
		
		// DN(공인인증) 데이터 이관
		memberReq.setCustomerVerifyType(CommonCodeConstants.VERIFY_TYPE_DN.toString());
		result = memberMapper.insertLeaveMemberInfo(memberReq);
		// 공인인증 데이터는 없을 수도 있기 때문에 탈퇴 밸리데이션에서 제외
		

		// 탈퇴 완료 후 로그아웃
        CookieUtil.removeLoginCookie(response);
        
		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
	}



	/**
	 * 회원가입 전 본인확인 조회
	 * */
	@Override
	public CommonResponse getGuestMember(String verify, HttpServletResponse response) {

		// 본인인증 CI 값을 이용하여 회원 정보 조회
		LoginRes loginRes = memberMapper.selectGuestMember(verify);

		//회원정보가 null 이 아니면 임시회원등록이 완료된 회원 , 회원정보 쿠키 저장
		if(null != loginRes){
			/*로그인 쿠키 생성(공통)*/
			CookieUtil.createLoginCookie(loginRes, response);
		}else{
			throw new CommonException(ErrorCodeConstants.NOT_FOUND_GUEST_USER_ID, null);
		}

		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
	}

	/**
	 * CI 중복체크
	 * */
	@Override
	public CommonResponse getDuplicationCheckCi(MemberReq memberReq) {
		CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);;

		/* CI 값 null valid */
		if(memberReq.getCustomerVerify() != null){
			
			// 탈퇴한 회원이 있는지 조회
			int withdrawCnt = memberMapper.selectwithDrawMemberCheckCi(memberReq);
			
			if(withdrawCnt > 0) {
				throw new CommonException(ErrorCodeConstants.MEMBER_STATUS_WITHDRAW, null);
			} else {
			
				MemberRes memberRes = memberMapper.selectDuplicationCheckCi(memberReq);
	
				if(memberRes != null) {
					//비회원이 일반회원가입 한 경우 입력값 넣어줘야 함
					if("Y".equals(memberRes.getTemporaryMemberYn()) && "G005002".equals(memberRes.getCustomerRegStatus())){
						response.getBody().put("memberRes", memberRes);
					}else if("G005002".equals(memberRes.getCustomerRegStatus())){		//일반회원가입을 이미 한 경우
						throw new CommonException(ErrorCodeConstants.VARIFY_OVERLAP, null);
					}else if("G005004".equals(memberRes.getCustomerRegStatus())){		//탈퇴 회원인 경우(오류사항)
						throw new CommonException(ErrorCodeConstants.WITHRAW_VARIFY_OVERLAP, null);
					}else if(!"G005002".equals(memberRes.getCustomerRegStatus())){		//회원상태 활성화가 아닌경우 관리자에게 문의
						throw new CommonException(ErrorCodeConstants.INQUIRE_ADMIN, null);
					}
					
				}else{
				/* 새로운 회원 가입 */
					response.getBody().put("newMember", "new");
				}
			}
		}else{
			throw new CommonException(ErrorCodeConstants.REQUIRED_PARAMETER_ERROR, new String[] {"verify"});
		}

			return response;
	}

	/**
	 * ID 중복체크
	 * */
	@Override
	public CommonResponse getDuplicationCheckId(MemberReq memberReq) {
		CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
		String resultCheckId = null;

		/* ID 값 null valid */
		if(!OppfStringUtil.isEmpty(memberReq.getCustomerId())){
		//if(memberReq.getCustomerId() != null || "".equals(memberReq.getCustomerId())){
			resultCheckId = memberMapper.selectDuplicationCheckId(memberReq);

			if(null == resultCheckId || resultCheckId.length()==0){
			/* 아이디 중복 없음(200) */
				response.getBody().put("customerId", memberReq.getCustomerId());
			}else{
			/* 중복되는 값이 있음 */
				response =  CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.OVERLAP_INFO, new String[] {"아이디"});
			}
		}else{
			/* 입력 안됨 */
			response =  CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.EMPTY_INFO, new String[] {"아이디"});
		}

		return response;
	}

	/**
	 * Email 중복체크
	 * */
	@Override
	public CommonResponse getDuplicationCheckEmail(MemberReq memberReq) {
		CommonResponse response = null;
		String resultCheckEmail = null;

		/* ID 값 null valid */
		if(!OppfStringUtil.isEmpty(memberReq.getCustomerEmail())){
			resultCheckEmail = memberMapper.selectDuplicationCheckEmail(memberReq);

			if(null == resultCheckEmail || resultCheckEmail.length()==0){
			/* Email 중복 없음(200)*/
				response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
				response.getBody().put("customerEmail", memberReq.getCustomerEmail());
			}else{
			/* 중복되는 값이 있음 */
				response =  CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.OVERLAP_INFO, new String[] {"이메일"});
			}
		}else{
			/* 입력 안됨 */
			response =  CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.EMPTY_INFO, new String[] {"이메일"});
		}

		return response;
	}


	/**
	 * 임시회원 회원가입 및 로그인
	 * */
	@Transactional
	public CommonResponse temporaryMember(MemberReq memberReq, HttpServletResponse response) {

		TemporaryMemberInfoRes temporaryMemberInfoRes = memberMapper.selecTemporaryMemberInfo(memberReq);
		CommonResponse commonResponse = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);

		if(temporaryMemberInfoRes == null){
			//String.format("%s%09d00001", sdf.format(new Date()), Random..nextInt(999999999)

			// 비회원 ID, PASSWORD는 임시로 생성 됨 추후 삭제 
			Random random = null;
		    SimpleDateFormat sdf = new SimpleDateFormat("MMddhhmmss");
			String format = String.format("%s", sdf.format(new Date()));			
			memberReq.setCustomerId("Z"+format);
			memberReq.setCustomerPassword("Z"+format);
			
			int dupCnt = memberMapper.selectFindUserId(memberReq);
			
			while(dupCnt > 0){
				
				sdf = new SimpleDateFormat("MMddhhmmss");
				format = String.format("%s", sdf.format(new Date()));			
				memberReq.setCustomerId("Z"+format);
				memberReq.setCustomerPassword("Z"+format);
				
				dupCnt = memberMapper.selectFindUserId(memberReq);
				
			}
			
			
			
			log.info("아이디 {}", memberReq.getCustomerId());
			
			/* 
			 * 회원 정보 등록 (비회원 customer_reg_no = T 로 생성에서 C로 변경 
			 * 사유 비회원이 정회원 전화시 customer_reg_no 형식 변경문제
			 * 비회원 여부는 temporary_member_yn 값으로 판단
			 *  */
			
			// 휴대폰 번호 - 추가
			memberReq.setCustomerPhone(OppfStringUtil.phoneConvertMinus(memberReq.getCustomerPhone()));
			
			
			memberReq.setCustomerRegNoPrefix("C");
			memberMapper.insertMember(memberReq);
			log.debug("========================================= 등록한 회원정보 : {}", memberReq.getCustomerRegNo());
	
			/* 약관 동의 등록 */
			insertMemberTermsProfile(memberReq);
	        log.debug("========================================= 등록한 회원동의 정보 : {}", memberReq.getCustomerTermsList());
	
			/* CI 정보 등록 */
			if(memberReq.getCustomerVerifyCi() != null) {
				memberReq.setCustomerVerifyType(CommonCodeConstants.VERIFY_TYPE_CI);
				memberReq.setCustomerVerify(memberReq.getCustomerVerifyCi());
				insertMemberVerifyProfile(memberReq);
	            log.debug("========================================= 등록한 CI 정보 : {}", memberReq.getCustomerVerifyCi());
			} else {

				throw new CommonException(ErrorCodeConstants.NOT_FOUND_MEMBER_CI, null);
			}

			LoginRes loginRes = memberMapper.selectGuestMemberInfo(memberReq.getCustomerId());
			// 쿠키 생성
	        CookieUtil.createLoginCookie(loginRes, response);
			commonResponse.getBody().put("loginRes", loginRes);
		} else if(temporaryMemberInfoRes != null 
				&& "Y".equals(temporaryMemberInfoRes.getTemporaryMemberYn())){// 회원 정보가 있고 비회원 인경우

			LoginRes loginRes = memberMapper.selectGuestMemberInfo(temporaryMemberInfoRes.getCustomerId());
	        CookieUtil.createLoginCookie(loginRes, response);
			commonResponse.getBody().put("loginRes", loginRes);
		} else {
			// 그 외의 경우 예외 처리
			throw new CommonException(ErrorCodeConstants.TEMP_MEMBER_CREATE_FAIL, null);
		}
		
		return commonResponse;
	}

	/**
	 * 비밀번호 유효성 검사
	 * */
	@Override
	public CommonResponse passwordConfirm(MemberReq memberReq) {
		CommonResponse commonResponse = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);

		String customerPwd = "";
		String customerPwdConfirm = "";

        //비밀번호 Error Message Y/N
        boolean pwdError = false;
        //비밀번호 확인 Error Message Y/N
        boolean pwdConfirmError = false;
        //비밀번호 Error 메세지
        String pwdErrorMsg = "";
        //비밀번호 확인 Error 메세지
        String pwdConfirmErrorMsg = "";


		if(!OppfStringUtil.isEmpty(memberReq.getCustomerPassword()) && !OppfStringUtil.isEmpty(memberReq.getCustomerPasswordConfirm())){
        //비밀번호와 비밀번호 확인 둘다 올 경우
			try {
			    customerPwd = EversafeKeypadUtil.RSADecoderSecureText(memberReq.getCustomerPassword());
				customerPwdConfirm = EversafeKeypadUtil.RSADecoderSecureText(memberReq.getCustomerPasswordConfirm());
			} catch (GeneralSecurityException e) {
				throw new CommonException(ErrorCodeConstants.USER_PWD_SECURE_ERROR, null);
			}
			/*
			* 비밀번호 유효성 검사
			* */
			if(customerPwd != null && !"".equals(customerPwd)){
				int pwChkCnt1 = 0;
				if(customerPwd.matches(".*[0-9]+.*")){
					// 숫자있음
					pwChkCnt1 = 1;
				}
				int pwChkCnt2 = 0;
				if(customerPwd.matches(".*[A-Z]+.*")){
					// 영어 대문자 있음
					pwChkCnt2 = 1;
				}
				int pwChkCnt3 = 0;
				if(customerPwd.matches(".*[a-z]+.*")){
					// 영어 소문자 있음
					pwChkCnt3 = 1;
				}

				int pwChkCnt4 = 0;
				if(customerPwd.matches(".*[~,!,\",@,#,$,%,<,>,^,&,*,(,),.,?,_,-,+,=,',`,|,/,:,\\},\\{,\\[,\\]]+.*")){
					// 특수문자 있음
					pwChkCnt4 = 1;
				}
				int pwChkCnt = pwChkCnt1 + pwChkCnt2 + pwChkCnt3 + pwChkCnt4;
				if(customerPwd.length() <= 7 || customerPwd.length() >= 16){
					// 비밀번호는 8자 이상 15자 이하입니다.
					pwdError = true;
					pwdErrorMsg = messageUtil.getMessage("9152", null);
				}else if(pwChkCnt <= 2){
					// 비밀번호는 영문 대/소문자, 숫자, 특수문자 중 3개이상의 조합이여야만 합니다.
					pwdError = true;
					pwdErrorMsg = messageUtil.getMessage("9152", null);
				}else{
					pwdError = false;
					pwdErrorMsg = "";
				}
			}
			/*
			* 비밀번호 확인 일치 여부
			* */
			if(!customerPwdConfirm.equals(customerPwd)){
				pwdConfirmError = true;
				pwdConfirmErrorMsg = "비밀번호가 일치하지 않습니다.";
			}
		} else if(!OppfStringUtil.isEmpty(memberReq.getCustomerPassword()) && OppfStringUtil.isEmpty(memberReq.getCustomerPasswordConfirm())){
		//비밀번호만 왔을경우
			try {
			    customerPwd = EversafeKeypadUtil.RSADecoderSecureText(memberReq.getCustomerPassword());
			} catch (GeneralSecurityException e) {
				throw new CommonException(ErrorCodeConstants.USER_PWD_SECURE_ERROR, null);
			}
			pwdConfirmError = true;
			pwdConfirmErrorMsg = "";

			/*
			* 비밀번호 유효성 검사
			* */
			if(customerPwd != null && !"".equals(customerPwd)){
				int pwChkCnt1 = 0;
				if(customerPwd.matches(".*[0-9]+.*")){
					// 숫자있음
					pwChkCnt1 = 1;
				}
				int pwChkCnt2 = 0;
				if(customerPwd.matches(".*[A-Z]+.*")){
					// 영어 대문자 있음
					pwChkCnt2 = 1;
				}
				int pwChkCnt3 = 0;
				if(customerPwd.matches(".*[a-z]+.*")){
					// 영어 소문자 있음
					pwChkCnt3 = 1;
				}

				int pwChkCnt4 = 0;
				if(customerPwd.matches(".*[~,!,\",@,#,$,%,<,>,^,&,*,(,),.,?,_,-,+,=,',`,|,/,:,\\},\\{,\\[,\\]]+.*")){
					// 특수문자 있음
					pwChkCnt4 = 1;
				}
				int pwChkCnt = pwChkCnt1 + pwChkCnt2 + pwChkCnt3 + pwChkCnt4;
				if(customerPwd.length() <= 7 || customerPwd.length() >= 16){
					// 비밀번호는 8자 이상 15자 이하입니다.
					pwdError = true;
					pwdErrorMsg = messageUtil.getMessage("9152", null);
				}

				else if(customerPwd.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")){
					// 비밀번호안에 한글은 사용할 수 없습니다.
					pwdError = true;
					pwdErrorMsg = messageUtil.getMessage("9153", null);
				}
				else if(pwChkCnt <= 2){
					// 비밀번호는 영문 대/소문자, 숫자, 특수문자 중 3개이상의 조합이여야만 합니다.
					pwdError = true;
					pwdErrorMsg = messageUtil.getMessage("9152", null);
				}else{
					pwdError = false;
					pwdErrorMsg = "";
				}
			}

		} else {
			//throw new CommonException(ErrorCodeConstants.USER_PWD_SECURE_ERROR, null);
		}
        commonResponse.getBody().put("passwordError", pwdError);
        commonResponse.getBody().put("passwordErrorMsg", pwdErrorMsg);
        commonResponse.getBody().put("passwordConfirmError", pwdConfirmError);
        commonResponse.getBody().put("passwordConfirmErrorMsg", pwdConfirmErrorMsg);
		return commonResponse;
	}


	/**
	 * 일반회원 모바일 정보 
	 * */
	@Transactional
	public int insertSptMobileInfo(MemberReq memberReq) {
		int result = 0;
		
		
        int res1 = memberMapper.insertSptMobileInfo(memberReq);
	    if(res1 != 0){
	        int res2 = memberMapper.insertSptMobileInfoHist(memberReq);
	        result = res1 + res2;
	    }else{
	        throw new CommonException(ErrorCodeConstants.INTERNAL_SERVER_ERROR, null);
	    }
	 
		return result;
	}

	


    /**
     * 아이디 및 해당아이디에 공인 인증서 등록 여부 조회
     * */
	@Override
	public CommonResponse getFindUserId(MemberReq memberReq){
		
		CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
		int resultIdCnt = 0;
		int resultSignCnt = 0;

		
		/* ID 값 null valid */
		if(!OppfStringUtil.isEmpty(memberReq.getCustomerId())){
			// id 중복
			resultIdCnt = memberMapper.selectFindUserId(memberReq);

			if(resultIdCnt>0){
				// 공인인증서 등록정보 
				resultSignCnt = memberMapper.selectFindSignId(memberReq);
				if(resultSignCnt>0){
                    LoginRes loginRes = memberMapper.selectMember(memberReq.getCustomerId());
                    response.getBody().put("memberRes", loginRes);

                    // 등록된 아이디 및 공인인증서 등록이 되어 있으면 정상 종료
					return response;
				} else {
					// 등록된 인증서가 없습니다. NOT_FOUND_MEMBER_ID
					response =  CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.NOT_FOUND_ID_SIGN, new String[] {"인증서"});
				}	
			} else {
				response =  CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.NOT_FOUND_USER_ID, null);
				//response =  CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.NOT_FOUND_MEMBER_ID, null);
				
			}
		}else{
			/* 입력 안됨 */
			response =  CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.EMPTY_INFO, new String[] {"아이디"});
		}

		return response;
	}
	
    /**
     * 모바일 push 메세지 수신 여부 변경
     * @param
     * @return
     */
    public CommonResponse changeMobilePush(LoginRes loginInfo) {

        int resultIdCnt = memberMapper.changeMobilePush(loginInfo);
        
        MemberReq memberReq = new MemberReq();
        memberReq.setCustomerRegNo(loginInfo.getCustomerRegNo());
        
        if(resultIdCnt != 0){
            resultIdCnt = memberMapper.insertSptMobileInfoHist(memberReq);
        }else{
            throw new CommonException(ErrorCodeConstants.INTERNAL_SERVER_ERROR, null);
        }

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);

    }

    /**
	 * 약관 재 동의
	 * */
	@Transactional
	public CommonResponse updateMemberTermsProfile(MemberReq memberReq) {
		int result = 0;
		if(memberReq.getCustomerTermsList() != null){
			if(memberReq.getCustomerTermsList().size() > 0){
				for (TermsRes terms : memberReq.getCustomerTermsList()) {
					terms.setCustomerRegNo(memberReq.getCustomerRegNo());
					int res1 = memberMapper.insertUpdateMemberTermsProfile(terms);
					log.debug("===========================insertMemberTermsProfile TermsType : {}", terms.getCustomerTermsType());
					if(res1 != 0){
						int res2 = memberMapper.insertMemberTermsProfileHist(memberReq);
						result = res1 + res2;
						log.debug("===========================insertMemberTermsProfile rs : {}", result);

					}else{
						throw new CommonException(ErrorCodeConstants.INTERNAL_SERVER_ERROR, null);
					}
				}
			}else{
				// 500 err
				throw new CommonException(ErrorCodeConstants.INTERNAL_SERVER_ERROR, null);
			}

		}

		int res = 0;
		if(memberReq.getCompanyTermsList() != null){
			if(memberReq.getCompanyTermsList().size() > 0){
				for (SptCustomerCompanyTermsProfileRes terms : memberReq.getCompanyTermsList()) {
					terms.setCustomerRegNo(memberReq.getCustomerRegNo());
					int cnt = memberMapper.checkCompanyTermsProfile(terms);
					if(cnt > 0){
						res = memberMapper.updateCompanyTermsProfile(terms);
					}else{
						res = memberMapper.insertCompanyTermsProfile(terms);
					}

					if(res > 0){
						int res2 = memberMapper.insertCompanyTermsProfileHist(terms);
					}
				}
			}else{
				// 500 err
				throw new CommonException(ErrorCodeConstants.INTERNAL_SERVER_ERROR, null);
			}

		}



		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
	}

	@Override
	public CommonResponse sendMemberEmail(EmailReqRes emailVO, HttpServletRequest request) {

		EmailReqRes emailReqRes = emailService.sendEmail(emailVO, request);




		return  CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
	}

}
