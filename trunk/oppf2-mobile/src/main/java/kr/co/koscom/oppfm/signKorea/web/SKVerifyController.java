package kr.co.koscom.oppfm.signKorea.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.koscom.oppfm.cmm.annotation.CheckAuth;
import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.cmm.util.CookieUtil;
import kr.co.koscom.oppfm.cmm.util.OppfStringUtil;
import kr.co.koscom.oppfm.login.model.LoginRes;
import kr.co.koscom.oppfm.signKorea.model.CustomerCertDnReq;
import kr.co.koscom.oppfm.signKorea.model.SKVerifyReq;
import kr.co.koscom.oppfm.signKorea.model.SKVerifyVO;
import kr.co.koscom.oppfm.signKorea.service.SKVerifyService;
import lombok.extern.slf4j.Slf4j;

/**
 * SKVerifyController
 * <p>
 * Created by Yoojin Han on 2017-05-26.
 */

@Api(tags = "signkorea-verify-controller", description = "공인인증서")
@RestController
@RequestMapping(value = "/apis")
@Slf4j
public class SKVerifyController {
    
    @Autowired
    MessageUtil messageUtil;
    
    @Autowired
    SKVerifyService skVerifyService;
    
//    @Value("#{config['Globals.skverifyd.ip']}")
//    String globalsSkverifydIp;
    
    
    /**
     * 공인인증서 등록 여부 조회
     * @MethodName          selectSKVerify
     * @param               skVerifyReq
     * @return              CommonResponse
     * @throws Exception
     */
    @CheckAuth
    @ApiOperation(value = "공인인증서 등록 여부 조회", response = CommonResponse.class)
    @RequestMapping(value  = "/signKorea/isCustomerCertDn", method = RequestMethod.GET)
    private CommonResponse isCustomerCertDn(HttpServletRequest request)throws Exception{
    
        LoginRes loginRes = null;
        String userCertDn = null;
        Boolean isEqualCookieCertDn = false;
        String customerCertDnMsg = "정상";
        
        loginRes = CookieUtil.getLoginInfo(request);
        try {
            userCertDn = skVerifyService.getCustomerDn( loginRes.getCustomerRegNo() );
        } catch(Exception e) {
            log.error("**** 공인인증서가 중복 되었습니다.");            
            customerCertDnMsg = "공인인증서가 중복 되었습니다.";
            log.error(e.toString());            
        }


        CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
        
        // 등록된 Dn과 인증서 Dn 비교
        if( !OppfStringUtil.isEmpty(userCertDn) ){
            isEqualCookieCertDn = true;
        }
        
        response.getBody().put("customerCertDn"    ,isEqualCookieCertDn);
        response.getBody().put("customerCertDnMsg" ,customerCertDnMsg);
        
        return response;

    }
        
    /**
     * 공인인증서 등록 여부 조회
     * @MethodName          selectSKVerify
     * @param               skVerifyReq
     * @return              CommonResponse
     * @throws Exception
     */
    @CheckAuth
    @ApiOperation(value = "공인인증서 재등록/등록", response = CommonResponse.class)
    @RequestMapping(value  = "/signKorea/updateCustomerCertDn", method = RequestMethod.POST)
    private CommonResponse updateCustomerCertDn(@RequestBody CustomerCertDnReq customerCertDnReq
            , HttpServletRequest request)throws Exception{

        LoginRes loginRes = null;
        
        loginRes = CookieUtil.getLoginInfo(request);
        
        if(OppfStringUtil.isNull(customerCertDnReq.getCustomerDn())) {
            throw new CommonException(ErrorCodeConstants.REQUIRED_PARAMETER_ERROR, new String[] {"customerDn"});
        }
        
        customerCertDnReq.setCustomerRegNo(loginRes.getCustomerRegNo());
        int resultCnt = skVerifyService.updateCustomerDn( customerCertDnReq );
        
        CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
        
        response.getBody().put("resultCnt", resultCnt);
        
        return response;
        
    }
    
    /**
     * 공인인증서 서명 유효성 검사
     * @MethodName          selectSKVerify
     * @param               skVerifyReq
     * @return              CommonResponse
     * @throws Exception
     */
    @ApiOperation(value = "공인인증서 서명 유효성 검사", response = CommonResponse.class)
    @RequestMapping(value  = "/signKorea/verify", method = RequestMethod.POST)
    private CommonResponse selectSKVerify(@RequestBody SKVerifyReq skVerifyReq, HttpServletRequest request)throws Exception{
    
        LoginRes loginRes = null;
        String customerCertDn = null;
        String customerName = null;
        Boolean isEqualCookieCertDn = false;
        
        // 세션체크 사용자 조회
        try {
            loginRes = CookieUtil.getLoginInfo(request);
            customerCertDn = skVerifyService.getCustomerDn( loginRes.getCustomerRegNo() );
            customerName = loginRes.getCustomerNameKor();

        } catch(Exception e) { }
        
        if( loginRes != null ) {
            log.debug(loginRes.toString());
        }
        

        SKVerifyVO skVerifyVO = skVerifyService.verifySign(skVerifyReq);
        
        
        //서명검증CRLop코드
        //model.addAttribute("rs"   , rs);
        
        CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
        
        String customerId = null;
        if(skVerifyVO.getRs() > 0){
            response.getBody().put("result", skVerifyVO.getSignKoreaMsg());
        }else{
            response.getBody().put("result", skVerifyVO.getRs());

            // 공인인증서DN 으로 사용자아이디 조회.. 공인인증서 로그인시 필요~
            customerId = skVerifyService.getCustomerId(skVerifyReq.getCustomerDn());
        }
        
        response.getBody().put("loginCustomerId", customerId);
        response.getBody().put("loginCustomerName", customerName);
        response.getBody().put("rsMsg", skVerifyVO.getRsMsg());
        
        // 등록된 Dn과 인증서 Dn 비교
        if( OppfStringUtil.equals(skVerifyReq.getCustomerDn(), customerCertDn)){
            isEqualCookieCertDn = true;
        }
        
        response.getBody().put("isEqualCookieCertDn", isEqualCookieCertDn);
        
        return response;
    }
    
    
    

}
