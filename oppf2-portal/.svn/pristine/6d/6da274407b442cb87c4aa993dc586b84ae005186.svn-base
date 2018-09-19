package kr.co.koscom.oppf.cmm.otp.web;

import kr.co.koscom.oppf.cmm.otp.service.CmmOtpReqService;
import kr.co.koscom.oppf.cmm.otp.service.CmmOtpReqVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmOtpReqController.java
* @Comment  : OTP 통신을 위한 Controller
* @author   : 신동진
* @since    : 2016.06.22
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.22  신동진        최초생성
*
*/
@Controller
public class CmmOtpReqController {
	private static final String HEADER_NAME_API_KEY = "X-ApiKey";
	private static final String HEADER_NAME_SIGNATURE = "X-Signature";
	private static final String HASHING_ALGORITHM = "HmacSHA1";
	
	private static final String CHARACTER_ENCODING_TYPE = "utf-8";
    
	@Resource(name = "CmmOtpReqService")
    private CmmOtpReqService cmmOtpReqService;
    
    /**
     * @Method Name        : selectOtpRegist
     * @Method description : 사용자 OTP정보를 조회한다. -> 저장은 각 화면단에서 처리  
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/otp/selectOtpRegist.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectOtpRegist(@RequestBody CmmOtpReqVO paramVO, HttpServletRequest  request, ModelMap model)throws Exception{
		String loginCheckYn = paramVO.getLoginCheckYn();
		if(OppfStringUtil.isEmpty(loginCheckYn)) loginCheckYn ="Y";
		
		//로그인 체크 필요 여부가 Y 일경우만 체크
		if("Y".equals(loginCheckYn)){
			//1.로그인관련
	        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
	        if(loginVO == null){
	        	model.addAttribute("error", "-1");
	        	return "jsonView";
	        }
		}
        
        //url 정보
        String url = OppfProperties.getProperty("Globals.otp.url");
        String requestURL = url + OppfProperties.getProperty("Globals.otp.uri.register");
        
        //message
        String httpBodys = "{\"otp\":\""+paramVO.getCustomerSendOtpId()+"\"}";
        
        // generate signature
        String secretKey = OppfProperties.getProperty("Globals.otp.header.secretKey");
        String apiKey = OppfProperties.getProperty("Globals.otp.header.xApikey");
 		String signature;
 		try {
 			signature = generateSignature(httpBodys + apiKey, HASHING_ALGORITHM, secretKey, CHARACTER_ENCODING_TYPE);
 		} catch (GeneralSecurityException | UnsupportedEncodingException e) {
 			model.addAttribute("error", "1");
        	return "jsonView";
 		}
 		
        //generate request header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_NAME_API_KEY, apiKey);
        httpHeaders.add(HEADER_NAME_SIGNATURE, signature);
        
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
                
        HttpEntity<String> requestEntity = new HttpEntity<String>(httpBodys, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        
        //send
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestURL, HttpMethod.POST, requestEntity, String.class);
        
        //response
        String strResBody = responseEntity.getBody();
        
        //success 시 OTP 정보 SAVE
        int saveResult = -1;
        JSONObject json = new JSONObject(strResBody);
        if(json != null){
        	//System.out.println("result=="+json.get("result"));
            //System.out.println("message=="+json.get("message"));
            //System.out.println("otp_id=="+json.get("otp_id"));
            
            String otpId = (String) json.get("otp_id");
            String result = (String) json.get("result");
            String message = (String) json.get("message");
            
            //성고시에만 처리
            if(result.equals("success") && message.equals("ok")){
	            CmmOtpReqVO cmmOtpReqVO = new CmmOtpReqVO();
	            cmmOtpReqVO.setCustomerRegNo(paramVO.getCustomerRegNo());
	            cmmOtpReqVO.setCustomerOtpId(otpId);
	            
	            saveResult = cmmOtpReqService.saveOtpProfile(cmmOtpReqVO);
            }
        }
        
        model.addAttribute("result", strResBody);
        model.addAttribute("saveResult", saveResult);
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : deleteOtpRegist
     * @Method description : 사용자 OTP정보를 삭제한다.  
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/otp/deleteOtpRegist.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String deleteOtpRegist(@RequestBody CmmOtpReqVO paramVO, HttpServletRequest  request, ModelMap model)throws Exception{
		String loginCheckYn = paramVO.getLoginCheckYn();
		if(OppfStringUtil.isEmpty(loginCheckYn)) loginCheckYn ="Y";
		
		//로그인 체크 필요 여부가 Y 일경우만 체크
		if("Y".equals(loginCheckYn)){
			//1.로그인관련
	        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
	        if(loginVO == null){
	        	model.addAttribute("error", "-1");
	        	return "jsonView";
	        }
		}

        //success 시 OTP 정보 삭제
		Map<String, Object> map = cmmOtpReqService.deleteOtpProfile(paramVO);
        
        model.addAttribute("result", map.get("result"));
        model.addAttribute("chkCnt", map.get("chkCnt"));
				
	    return "jsonView";
	}
	
	// generate signature
	private String generateSignature(String sourceEntity, String hashingAlgorithm, String seedKey, String encodingType)
			throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		Mac mac = Mac.getInstance(hashingAlgorithm);
		SecretKeySpec signKey = new SecretKeySpec(seedKey.getBytes(), hashingAlgorithm);
		mac.init(signKey);
		
		// hashing
		byte[] hashedEntity = mac.doFinal(sourceEntity.getBytes());
		
		// base64 and url encoding
		return URLEncoder.encode(Base64.getEncoder().encodeToString(hashedEntity), encodingType);
	}

}
