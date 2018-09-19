package kr.co.koscom.oppfm.push.web;


import java.util.Arrays;
import java.util.Properties;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.cmm.util.RestTemplateUtil;
import kr.co.koscom.oppfm.push.model.PushMessageReq;
import kr.co.koscom.oppfm.push.model.PushReq;
import kr.co.koscom.oppfm.push.service.PushMessageService;
import kr.co.koscom.oppfm.push.service.PushService;
import kr.co.koscom.oppfm.push.service.impl.APNSPushServiceImpl;
import kr.co.koscom.oppfm.push.service.impl.FCMPushServiceImpl;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * PushController
 * <p>
 * Created by 
 */
@Slf4j
@Api(tags = "push-controller", description = "푸쉬", produces = "application/json")
@RestController
public class PushController {
	
	@Resource(name = "config")
	private Properties properties;
	
	@Autowired
    private MessageUtil messageUtil;

	@Autowired
    PushMessageService pushMessageService;
	
	@RequestMapping(value="/apis/push", method = RequestMethod.POST)
	public CommonResponse send(@ApiParam(value = "푸시 전문", required = true) @RequestBody PushReq param) {
		PushService pm = null;
		
		if(param.getDeviceType().equals("android")){
			pm = new FCMPushServiceImpl(properties);
		}
		else{
			pm = new APNSPushServiceImpl(properties);
		}
		
		try{
			pm.send(param.getDeviceToken(), param.getMessage());
		}
		catch(Exception e){
		    log.error(e.toString());
			e.printStackTrace();
		}

		CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
    	
        response.getBody().put("result", "SUCCESS");
        return response;
	}

	/*
	* REST 통신 테스트
	*/
	@RequestMapping(value = "/apis/push/test", method = RequestMethod.GET)
	public String pushTest(){
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		String testUrl = "http://localhost:8090/apis/login";
		String payload = "{ \"customer_id\" : \"laststart\" , \"customer_password\" : \"dmfwlfh3rk!\" }";

		RestTemplateUtil restTemplateUtil = new RestTemplateUtil();
		ResponseEntity<String> responseEntity = restTemplateUtil.sendRestTemplate(testUrl, httpHeaders, HttpMethod.POST, payload);

		if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
			log.debug("++++++++++++++++++++++++++++++++++++ responseEntity success : {} ", responseEntity.getBody() );
		}else{
			log.debug("++++++++++++++++++++++++++++++++++++ responseEntity failed : {} ", responseEntity.getBody() );
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("customer_id", "laststart");
		jsonObject.put("customer_password", "dmfwlfh3rk!");

		responseEntity = restTemplateUtil.sendRestTemplate(testUrl, httpHeaders, HttpMethod.POST, jsonObject.toString());

		if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
			log.debug("++++++++++++++++++++++++++++++++++++ responseEntity success : {} ", responseEntity.getBody() );
		}else{
			log.debug("++++++++++++++++++++++++++++++++++++ responseEntity failed : {} ", responseEntity.getBody() );
		}

		return "jsonView";
	}
	
	/**
	 * 푸시 메시지 조회
	 * @MethodName          getPushMessage
	 * @param               String pushMessageRegNo
	 * @return              CommonResponse
	 */
	@ApiOperation(value = "푸시 메시지 조회",  response=CommonResponse.class)
    @RequestMapping(value="/apis/push/message" , method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name ="pushMessageRegNo", dataType ="String", paramType ="query", value = "메세지 등록번호 sample(P20170611000,P20170612000)")
    })
    public CommonResponse getPushMessage(@ApiIgnore PushMessageReq pushMessageReq){
        
        /* 임시
         * 1. 푸시 타입 체크 
         *    
         * 2. 푸시 메시지 조회
         */

        return pushMessageService.getPushMessage(pushMessageReq);
    }
	
	
	/**
	 * 푸시 메시지 조회
	 * @MethodName          batchSendPush
	 * @param               
	 * @return              
	 */
	@ApiOperation(value = "푸시 메세지 전송(batch)",  response=CommonResponse.class)
    @RequestMapping(value="/apis/push/sendbatch" , method = RequestMethod.GET)
    public CommonResponse batchSendPush(){
		
        return pushMessageService.batchSendPush();
    }
	
	
	
	
	
	
	
	
	
	
	
}
