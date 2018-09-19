package kr.co.koscom.oppfm.login.service.impl;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppfm.account.model.SptCustomerCompanyTermsProfileRes;
import kr.co.koscom.oppfm.cmm.eversafe.EversafeKeypadUtil;
import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonCodeConstants;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.cmm.util.CookieUtil;
import kr.co.koscom.oppfm.cmm.util.OppfStringUtil;
import kr.co.koscom.oppfm.login.dao.LoginMapper;
import kr.co.koscom.oppfm.login.model.CertPasswordFailReq;
import kr.co.koscom.oppfm.login.model.CertPasswordFailRes;
import kr.co.koscom.oppfm.login.model.FindIdReq;
import kr.co.koscom.oppfm.login.model.LoginReq;
import kr.co.koscom.oppfm.login.model.LoginRes;
import kr.co.koscom.oppfm.login.model.ModifyPasswordReq;
import kr.co.koscom.oppfm.login.service.LoginService;
import kr.co.koscom.oppfm.member.dao.MemberMapper;
import kr.co.koscom.oppfm.member.model.MemberReq;
import kr.co.koscom.oppfm.terms.model.TermsRes;
import lombok.extern.slf4j.Slf4j;

/**
 * LoginServiceImpl
 * <p>
 * Created by chungyeol.kim on 2017-04-24.
 * * Modify by sh.lee on 2017-05-16.
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;


    @Autowired
    private MessageUtil messageUtil;

    @Value("#{config['Globals.user.policy.password.failCnt']}")
    String loginFailCount;

    @Resource(name = "config")
    private Properties properties;

    @Autowired
    private MemberMapper memberMapper;
    
    @Transactional
    public CommonResponse loginCheck(LoginReq loginReq, HttpServletResponse response){
        //1. 아이디를 통해 로그인정보 획득
        //2. 패스워드 유효여부 -> 패스워드 실패 시 실패 카운트 증적
        //int count = loginMapper.selectTest(loginReq);
        CommonResponse commonResponse = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
        LoginRes resultLoginRes = null;

        String customerPwd = "";

        if(!OppfStringUtil.isEmpty(loginReq.getCustomerPassword())){
        	
        	try {
        	    customerPwd = EversafeKeypadUtil.RSADecoderSecureText(loginReq.getCustomerPassword());
				loginReq.setCustomerPassword(customerPwd);
			} catch (GeneralSecurityException e) {
				throw new CommonException(ErrorCodeConstants.USER_PWD_SECURE_ERROR, null);				
			}
        	
        } else {
			//throw new CommonException(ErrorCodeConstants.USER_PWD_SECURE_ERROR, null);
        }

        /*
        * ID, password Login
        * */
        if (loginReq.getCustomerId() != null && loginReq.getCustomerPassword() != null &&
                !("").equals(loginReq.getCustomerId()) && !("").equals(loginReq.getCustomerPassword())) {

            resultLoginRes = loginMapper.selectLoginProfile(loginReq);
        }


        if (resultLoginRes != null) {
            loginReq.setCustomerRegNo(resultLoginRes.getCustomerRegNo());
            loginReq.setCustomerRegStatus(resultLoginRes.getCustomerRegStatus());
            loginReq.setCustomerPasswordFailCnt(resultLoginRes.getCustomerPasswordFailCnt());
            
            /*로그인 실패 횟수가 10 보다 작을경우 성공*/
            int failCount = Integer.parseInt(loginFailCount);

            /*로그인 정지 상태의 경우 예외처리*/
            if ("G005003".equals(resultLoginRes.getCustomerRegStatus())) {
                throw new CommonException(ErrorCodeConstants.MEMBER_STOP, null);
            }
            /*탈퇴처리 된 아이디의 경우 예외처리*/
            else if ("Y".equals(resultLoginRes.getCustomerWithdrawalProcYn()) || "G005004".equals(resultLoginRes.getCustomerRegStatus())) {
                throw new CommonException(ErrorCodeConstants.MEMBER_SECESSION, null);
            }
            /*아이디 미활성화 상태의 경우 예외처리*/
            else if ("G005001".equals(resultLoginRes.getCustomerRegStatus())) {
                throw new CommonException(ErrorCodeConstants.MEMBER_SECESSION, null);
            }
            /* 비밀번호 틀렸을 경우 (null일 경우 그냥 지나침) */
            else if (("N").equals(resultLoginRes.getCustomerPasswordYn())) {
                if (failCount < (resultLoginRes.getCustomerPasswordFailCnt() + 1)) {
               /*로그인 실패 횟수가 10보다 큰 경우 회원 정보 정지상태로 수정(계정 잠금)*/
                    //회원가입상태를 활성(G005002)에서 정지(G005003)로 바꿉니다.
                    loginReq.setCustomerPasswordFailType("Y");
                    loginReq.setCustomerRegStatus("G005003");
                    loginMapper.updateLoginFailCnt(loginReq);
                    

                    //로그인 hist 셋팅
                    loginMapper.insertLoginHist(loginReq);

                    //실패 예외처리
                    commonResponse = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.DO_NOT_MATCH_PWD_STOP, new String[]{resultLoginRes.getCustomerPasswordFailCnt() + ""});
                } else {
                 /*로그인 횟수 추가 카운트 쿼리 & 예외처리*/

                    //실패카운트 즉적
                    loginReq.setCustomerPasswordFailType("Y");
                    loginMapper.updateLoginFailCnt(loginReq);

                    //로그인 hist 셋팅
                    loginMapper.insertLoginHist(loginReq);

                    commonResponse = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.DO_NOT_MATCH_PWD, new String[]{resultLoginRes.getCustomerPasswordFailCnt() + 1 + ""});
                }

            }
            /*성공일경우*/
            else if ("Y".equals(resultLoginRes.getCustomerPasswordYn())
                    && "N".equals(resultLoginRes.getCustomerWithdrawalProcYn())) {

                log.debug("로그인 성공처리 ");
                if (failCount > resultLoginRes.getCustomerPasswordFailCnt()) {
                    /*성공일경우 카운트 다시 0*/
                	log.debug("성공일경우 카운트 다시 0 ");
                    if (0 < resultLoginRes.getCustomerPasswordFailCnt()) {
                        loginReq.setCustomerPasswordFailType("N");
                        loginMapper.updateLoginFailCnt(loginReq);
                        //로그인 hist 셋팅
                        loginMapper.insertLoginHist(loginReq);
                    }

                    /*
                    * 약관 재동의 리스트 존재 여부
                    * */
                    List<TermsRes> termsRes = loginMapper.selectTermsResList(resultLoginRes);
                    if(termsRes.size() != 0 ){
                        commonResponse.getBody().put("termsResult", termsRes);
                    }

                    List<SptCustomerCompanyTermsProfileRes> companyTermsRes = loginMapper.selectCompanyTermsResList(resultLoginRes);
                    if(companyTermsRes.size() != 0){
                        commonResponse.getBody().put("companyTermsResult", companyTermsRes);
                    }

                    MemberReq memberReq = new MemberReq();

                    if(null ==loginReq.getAppKey()){
                    	memberReq.setAppKey(null);	
                    } else {
                    	memberReq.setAppKey(loginReq.getAppKey());	
                    	
                    }
                    
                    if(null ==loginReq.getCompanyCodeId()){
                    	memberReq.setCompanyCodeId(null);	
                    } else {
                    	memberReq.setCompanyCodeId(loginReq.getCompanyCodeId());	
                    	
                    }
                    
                    
                    if(!OppfStringUtil.isEmpty(loginReq.getDeviceType())){
                    	
                    	if(CommonCodeConstants.MBR_MOBILE_OS_ADR.equals(loginReq.getDeviceType())){
                    		memberReq.setDeviceType(CommonCodeConstants.MBR_MOBILE_OS_TYPE_ADR);
                    	} else if(CommonCodeConstants.MBR_MOBILE_OS_IOS.equals(loginReq.getDeviceType())){
                    		memberReq.setDeviceType(CommonCodeConstants.MBR_MOBILE_OS_TYPE_IOS);
                    	} else {
                    		
                    	}
                    }
                    
                    memberReq.setCustomerRegNo(loginReq.getCustomerRegNo());
                    memberReq.setDeviceUniqueId(loginReq.getDeviceUniqueId());

            		int result = 0;
                    int res1 = memberMapper.insertSptMobileInfo(memberReq);
            	    if(res1 != 0){
            	        int res2 = memberMapper.insertSptMobileInfoHist(memberReq);
            	        result = res1 + res2;
            	    }else{
            	        throw new CommonException(ErrorCodeConstants.INTERNAL_SERVER_ERROR, null);
            	    }

                    //[customerJoinType:회원가입타입]웹에서 앱으로 로그인 할 경우 -> Web/Mobile로 수정
                    if(CommonCodeConstants.MBR_JOIN_TYPE_W.equals(resultLoginRes.getCustomerJoinType())) {
                        loginMapper.updateCustomerJoinType(resultLoginRes);
                    }
                    
                    /*로그인 쿠키 생성(공통)*/
                    CookieUtil.createLoginCookie(resultLoginRes, response);

                    commonResponse.getBody().put("loginRes", resultLoginRes);

                } else {
                /*비밀번호 맞아도 실패 횟수가 5보다 큰 정지 예외처리*/
                    throw new CommonException(ErrorCodeConstants.USER_STATUS_SUSPENSION, null);
                }
            }
        } else {
        	log.debug("아이디가 없는 경우NOT_FOUND_USER_ID");
            /*아이디가 없는 경우NOT_FOUND_USER_ID*/
            throw new CommonException(ErrorCodeConstants.NOT_FOUND_USER_ID, null);

        }

        return commonResponse;
    }

    /**
     * 공인인증서 로그인
     * */
    @Transactional
    public CommonResponse verifyLoginCheck(LoginReq loginReq, HttpServletResponse response) {
        //1. 아이디를 통해 로그인정보 획득
        //2. 패스워드 유효여부 -> 패스워드 실패 시 실패 카운트 증적
        //int count = loginMapper.selectTest(loginReq);
        CommonResponse commonResponse = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
        LoginRes resultLoginRes = null;

         /*
         * 공인인증서 로그인 시 서명 서비스단
         * */
        if(loginReq.getSignData() != null && !("").equals(loginReq.getSignData())){
            /*
            *  {
                   'result' : 1,'signData' : signedData,'customerDn' : userDn,
                   'customerId' : customerId, 'isEqualCookieCertDn' : res.isEqualCookieCertDn
                }
            * */
        }

        /*
        * 공인인증서 로그인(ID & DN) 값
        * */
        if (loginReq.getCustomerVerifyDn() != null && !("").equals(loginReq.getCustomerVerifyDn())){
            resultLoginRes = loginMapper.selectLoginProfileDn(loginReq);
        }



        if (resultLoginRes != null) {

            loginReq.setCustomerRegNo(resultLoginRes.getCustomerRegNo());
            loginReq.setCustomerRegStatus(resultLoginRes.getCustomerRegStatus());
            loginReq.setCustomerPasswordFailCnt(resultLoginRes.getCustomerPasswordFailCnt());

            log.debug("로그인 정지 상태의 경우 예외처리");
            /*로그인 정지 상태의 경우 예외처리*/
            if ("G005003".equals(resultLoginRes.getCustomerRegStatus())) {
                throw new CommonException(ErrorCodeConstants.USER_STATUS_SUSPENSION, null);
            }
            /*탈퇴처리 된 아이디의 경우 예외처리*/
            else if ("Y".equals(resultLoginRes.getCustomerWithdrawalProcYn()) || "G005004".equals(resultLoginRes.getCustomerRegStatus())) {
                throw new CommonException(ErrorCodeConstants.USER_STATUS_MORE_LEAVE, null);
            }
            /*아이디 미활성화 상태의 경우 예외처리*/
            else if ("G005001".equals(resultLoginRes.getCustomerRegStatus())) {
                throw new CommonException(ErrorCodeConstants.USER_STATUS_INACTIVE, null);
            }
            /* 공인인증서 이름과 등록된 이름이 다른 경우 */
            else if(loginReq.getCustomerVerifyDn().split(",")[0].indexOf(resultLoginRes.getCustomerNameKor()) < 0){
                throw new CommonException(ErrorCodeConstants.NOT_MATCH_INFO_VERIFY, null);
            }else if("N".equals(resultLoginRes.getCustomerVerifyYn())){
                throw new CommonException(ErrorCodeConstants.NOT_INSERT_VERIFY, null);
            }
            /*성공일경우*/
            else if ("Y".equals(resultLoginRes.getCustomerVerifyYn())
                && "N".equals(resultLoginRes.getCustomerWithdrawalProcYn())) {
                /*성공일경우 카운트 다시 0*/
                if (0 < resultLoginRes.getCustomerPasswordFailCnt()) {
                    loginReq.setCustomerPasswordFailType("N");
                    loginMapper.updateLoginFailCnt(loginReq);
                    //로그인 hist 셋팅
                    loginMapper.insertLoginHist(loginReq);
                }

                 /*
                 * 약관 재동의 리스트 존재 여부
                 * */
                List<TermsRes> termsRes = loginMapper.selectTermsResList(resultLoginRes);
                if(termsRes.size() != 0 ){
                    commonResponse.getBody().put("termsResult", termsRes);
                }

                /*
                * 기업약관
                * */
                List<SptCustomerCompanyTermsProfileRes> companyTermsRes = loginMapper.selectCompanyTermsResList(resultLoginRes);
                if(companyTermsRes.size() != 0){
                    commonResponse.getBody().put("companyTermsResult", companyTermsRes);
                }


                MemberReq memberReq = new MemberReq();

                if(null ==loginReq.getAppKey()){
                    memberReq.setAppKey(null);
                } else {
                    memberReq.setAppKey(loginReq.getAppKey());

                }

                if(null ==loginReq.getCompanyCodeId()){
                    memberReq.setCompanyCodeId(null);
                } else {
                    memberReq.setCompanyCodeId(loginReq.getCompanyCodeId());

                }

                if(!OppfStringUtil.isEmpty(loginReq.getDeviceType())){
                    
                    if(CommonCodeConstants.MBR_MOBILE_OS_ADR.equals(loginReq.getDeviceType())){
                        memberReq.setDeviceType(CommonCodeConstants.MBR_MOBILE_OS_TYPE_ADR);
                    } else if(CommonCodeConstants.MBR_MOBILE_OS_IOS.equals(loginReq.getDeviceType())){
                        memberReq.setDeviceType(CommonCodeConstants.MBR_MOBILE_OS_TYPE_IOS);
                    } else {
                        
                    }
                }

                memberReq.setCustomerRegNo(loginReq.getCustomerRegNo());
                memberReq.setDeviceUniqueId(loginReq.getDeviceUniqueId());

                int result = 0;
                int res1 = memberMapper.insertSptMobileInfo(memberReq);
                if(res1 != 0){
                    int res2 = memberMapper.insertSptMobileInfoHist(memberReq);
                    result = res1 + res2;
                }else{
                    throw new CommonException(ErrorCodeConstants.INTERNAL_SERVER_ERROR, null);
                }

                //[customerJoinType:회원가입타입]웹에서 앱으로 로그인 할 경우 -> Web/Mobile로 수정
                if(CommonCodeConstants.MBR_JOIN_TYPE_W.equals(resultLoginRes.getCustomerJoinType())) {
                    loginMapper.updateCustomerJoinType(resultLoginRes);
                }

                /*로그인 쿠키 생성(공통)*/
                CookieUtil.createLoginCookie(resultLoginRes, response);

                commonResponse.getBody().put("loginRes", resultLoginRes);

            }
        } else {
            log.debug("아이디와 인증서가 일치하지 않는 경우");
            /* 아이디와 인증서가 일치하지 않는 경우 */
            throw new CommonException(ErrorCodeConstants.NOT_MATCH_INFO, null);

        }

        return commonResponse;
    }


    /**
     * Find ID return값 ID 뒷부분 가림
     * */
    @Override
    public CommonResponse getFindLoginId(FindIdReq findIdReq) {
        CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);

        String findLoginId = null;
        if(findIdReq.getCustomerNameKor() != null && findIdReq.getCustomerEmail() != null){
            findLoginId = loginMapper.selectFindLoignId(findIdReq);

            if(findLoginId != null && findLoginId != ""){
                response.getBody().put("findLoginId", findLoginId);

                // ID 뒤에서 3번째 까지 * 처리
                // ex> testsu***
                String setHiddenId = findLoginId.substring(0, findLoginId.length()-3);
                setHiddenId = setHiddenId + "***";
                response.getBody().put("setHiddenId", setHiddenId);
            }else{
                throw new CommonException(ErrorCodeConstants.DO_NOT_MATCH_MEMBER, new String[] {"아이디", "이메일"});
            }
        }else{
            //nullPoint err 처리
            throw new CommonException(ErrorCodeConstants.NOT_FOUND_EXCEPTION, new String[] {"CustomerEmail & CustomerNameKor"});
        }

        return response;
    }

    /**
     * Modify password(Modify기능)
     * */
    @Transactional
    public CommonResponse modifyPassword(ModifyPasswordReq modifyPasswordReq) {
        CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);

        modifyPasswordReq.setCustomerExpirePasswordDate(properties.get("Globals.user.policy.password.final").toString());    //비밀번호 변경 예정일
        modifyPasswordReq.setCustomerFinalPasswordDate(properties.get("Globals.user.policy.password.expire").toString());    //비밀번호 변경 최종 예정일


        if(!OppfStringUtil.isEmpty(modifyPasswordReq.getCustomerRegNo()) && !OppfStringUtil.isEmpty(modifyPasswordReq.getCustomerPassword())) {
            //비밀번호 수정(정지된 경우 회원상태 수정)
            modifyPasswordReq.setCustomerExpirePasswordDate(properties.get("Globals.user.policy.password.final").toString());
            modifyPasswordReq.setCustomerFinalPasswordDate(properties.get("Globals.user.policy.password.expire").toString());

            /* 복호화 */
            try {
                String customerPassword = EversafeKeypadUtil.RSADecoderSecureText(modifyPasswordReq.getCustomerPassword());
                modifyPasswordReq.setCustomerPassword(customerPassword);
            } catch (GeneralSecurityException e) {
                throw new CommonException(ErrorCodeConstants.USER_PWD_SECURE_ERROR, null);
            }
            int rs = loginMapper.updatePassword(modifyPasswordReq);
        }else{
            //nullPoint err 처리
            throw new CommonException(ErrorCodeConstants.NOT_FOUND_EXCEPTION, new String[] {"CustomerPassword"});
        }

        LoginReq loginReq = new LoginReq();
        loginReq.setCustomerRegNo(modifyPasswordReq.getCustomerRegNo());
        //비밀번호 수정 hist
        loginMapper.insertLoginHist(loginReq);

        return response;
    }


    /**
     * LOGOUT (REMOVE COOKIE)
     * */
    @Override
    public CommonResponse logout(HttpServletResponse response) {
        CookieUtil.removeLoginCookie(response);

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
    }

    @Override
    public CommonResponse loginActionTest(LoginReq loginReq, HttpServletResponse response) {

        LoginRes resultLoginRes = loginMapper.selectTestLoginProfile(loginReq);



        if(resultLoginRes == null){
        	log.debug("아이디가 없는 경우NOT_FOUND_USER_ID");
            /*아이디가 없는 경우NOT_FOUND_USER_ID*/
            throw new CommonException(ErrorCodeConstants.NOT_FOUND_USER_ID, null);

        }
        /*로그인 쿠키 생성(공통)*/
        CookieUtil.createLoginCookie(resultLoginRes, response);

        Map<String, Object> body = new HashMap<>();
        body.put("loginRes", resultLoginRes);

        /*
        * 약관 재동의 리스트 존재 여부
        * */
        List<TermsRes> termsRes = loginMapper.selectTermsResList(resultLoginRes);
        if(termsRes.size() != 0 ){
            body.put("termsResult", termsRes);
        }

        List<SptCustomerCompanyTermsProfileRes> companyTermsRes = loginMapper.selectCompanyTermsResList(resultLoginRes);
        if(companyTermsRes.size() != 0){
            body.put("companyTermsResult", companyTermsRes);
        }

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

    /**
     * 비밀번호  찾기 전 공인인증서 인증(나중에 서명 추가)
     * */
    @Override
    public CommonResponse selectVerifyIdConfirm(LoginReq loginReq) {
        /*
         * 공인인증서 로그인 시 서명 서비스단
         * */
        if(loginReq.getSignData() != null && !("").equals(loginReq.getSignData())){
            /*
            *  {
                   'result' : 1,'signData' : signedData,'customerDn' : userDn,
                   'customerId' : customerId, 'isEqualCookieCertDn' : res.isEqualCookieCertDn
                }
            * */
        }

        LoginRes resultLoginRes = loginMapper.selectLoginProfileDn(loginReq);

        Map<String, Object> body = new HashMap<>();

        if(resultLoginRes != null){
            body.put("loginRes", resultLoginRes);
        }else{
            throw new CommonException( ErrorCodeConstants.EMPTY_INFO, new String[] {"공인인증서"});
        }

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

    /* (non-Javadoc)
     * @see kr.co.koscom.oppfm.login.service.LoginService#updateCertPasswordFailCnt(kr.co.koscom.oppfm.login.model.CertPasswordFailReq, javax.servlet.http.HttpServletResponse)
     */
    @Override
    @Transactional
    public CommonResponse updateCertPasswordFailCnt(CertPasswordFailReq certPasswordFailReq, HttpServletResponse response) {
        
        if( certPasswordFailReq.isPasswordFail()) {
            try {
                loginMapper.updatePasswordFailCnt(certPasswordFailReq);
            } catch(Exception e ) {
            }
        }
        CertPasswordFailRes certPasswordFailRes = loginMapper.selectPasswordFailCnt(certPasswordFailReq);
        if( certPasswordFailRes == null ) {
            certPasswordFailRes = new CertPasswordFailRes();
        }
//        /*로그인 쿠키 생성(공통)*/
//        CookieUtil.createLoginCookie(resultLoginRes, response);
        
        Map<String, Object> body = new HashMap<>();
        body.put("passwordFailCnt", certPasswordFailRes.getPasswordFailCnt());
        body.put("lockFailCount"  , loginFailCount);
//        body.put("loginFailCount", passwordFailCnt);
        
        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }



}
