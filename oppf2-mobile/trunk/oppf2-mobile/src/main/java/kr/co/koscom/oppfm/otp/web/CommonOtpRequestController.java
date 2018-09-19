package kr.co.koscom.oppfm.otp.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import kr.co.koscom.oppfm.cmm.util.CookieUtil;
import kr.co.koscom.oppfm.login.model.LoginRes;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.otp.model.OtpReq;
import kr.co.koscom.oppfm.otp.model.OtpRes;
import kr.co.koscom.oppfm.otp.service.CommonOtpRequestService;

/**
 * ClassName : CommonOtpRequest
 * *
 * Description :
 * <p>
 * Created by LSH on 2017. 5. 24..
 */

@RestController
public class CommonOtpRequestController {


    @Autowired
    private MessageUtil messageUtil;

    @Resource(name = "config")
    private Properties properties;

    private static final String HEADER_NAME_API_KEY = "X-ApiKey";
    private static final String HEADER_NAME_SIGNATURE = "X-Signature";
    private static final String HASHING_ALGORITHM = "HmacSHA1";

    private static final String CHARACTER_ENCODING_TYPE = "utf-8";

    @Autowired
    private CommonOtpRequestService cmmOtpReqService;

    /**
     * @Method Name        : selectOtpRegist
     * @Method description : 사용자 OTP정보를 조회한다. -> 저장은 각 화면단에서 처리
     * @param              :
     * @return             : String
     * @throws             : Exception
     */

    @RequestMapping(value = "/apis/otp/check", method = RequestMethod.POST, consumes = {"application/json"})
    public CommonResponse otpCheckResult(@RequestBody OtpReq otpReq, HttpServletRequest request){
        CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);

        //url 정보
        String url = properties.get("Globals.otp.url").toString();
        String requestURL = url + properties.get("Globals.otp.uri.register").toString();

        //message
        String httpBodys = "{\"otp\":\"" + otpReq.getCustomerSendOtpId() + "\"}";

        // generate signature
        String secretKey = properties.get("Globals.otp.header.secretKey").toString();
        String apiKey = properties.get("Globals.otp.header.xApikey").toString();
        String signature;
        try {
            signature = generateSignature(httpBodys + apiKey, HASHING_ALGORITHM, secretKey, CHARACTER_ENCODING_TYPE);
        } catch (GeneralSecurityException | UnsupportedEncodingException e) {
            throw new CommonException(ErrorCodeConstants.REQUIRED_PARAMETER_ERROR, new String[] {"termsType"});
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
        if(json != null) {

            OtpRes otpRes = new OtpRes();

            otpRes.setOtpId((String)json.get("otp_id"));
            otpRes.setResult((String)json.get("result"));
            otpRes.setMessage((String)json.get("message"));

            /*result 가 success && message 가 ok이면 성공 화면단에서 다시 저장 API go*/

            response.getBody().put("otpRes", otpRes);
        }else{
            /* OTP 없음 에러 */
            response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.DO_NOT_MATCH, new String[] {"회원정보, OTP 비밀번호"});
        }
//        OtpRes otpRes = new OtpRes();
//
//        otpRes.setOtpId("id");
//        otpRes.setResult("success");
//        otpRes.setMessage("ok");
//
//        response.getBody().put("otpRes", otpRes);
        return response;
    }

    /**
     * @Method Name        : selectOtpRegist
     * @Method description : 사용자 OTP정보를 저장한다.
     * @param              :
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value = "/apis/otp/save", method = RequestMethod.POST, consumes = {"application/json"})
    private CommonResponse otpSaveResult(@RequestBody OtpReq otpReq, HttpServletRequest request){
        CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
        LoginRes loginRes = CookieUtil.getLoginInfo(request);
        otpReq.setCustomerRegNo(loginRes.getCustomerRegNo());
        int saveOtpResult = cmmOtpReqService.saveOtpProfile(otpReq);

        response.getBody().put("saveOtpResult", saveOtpResult);

        return response;
    }

    /**
     * @Method Name        : selectOtpRegist
     * @Method description : 사용자 OTP정보를 삭제한다.
     * @param              :
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value = "/apis/otp/delete", method = RequestMethod.POST, consumes = {"application/json"})
    private CommonResponse otpDeleteResult(OtpReq otpReq, HttpServletRequest request){
        CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
        LoginRes loginRes = CookieUtil.getLoginInfo(request);
        otpReq.setCustomerRegNo(loginRes.getCustomerRegNo());
        int deleteOtpResult = cmmOtpReqService.deleteOtpProfile(otpReq);

        response.getBody().put("deleteOtpResult", deleteOtpResult);

        return response;
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
