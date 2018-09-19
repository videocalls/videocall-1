package kr.co.koscom.oppfm.push.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import kr.co.koscom.oppfm.cmm.util.RestTemplateUtil;
import kr.co.koscom.oppfm.push.service.PushService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FCMPushServiceImpl implements PushService {
	private String fcmServerUrl = null;
	private String fcmServerKey = null;
	
	public FCMPushServiceImpl(Properties properties) {
		fcmServerUrl = properties.get("Globals.push.fcmServerUrl").toString();
		fcmServerKey = properties.get("Globals.push.fcmServerKey").toString();
	}
	
	@Override
	public String send(String deviceToken, String message){
		List<String> deviceIds = new ArrayList<String>();
		deviceIds.add(deviceToken);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		httpHeaders.add("Authorization", "key=" + fcmServerKey);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("collapse_key", "score_update");
		
		//기기가 오프라인 상태인 경우 FCM 저장소에 메시지를 보관해야 하는 시간(초)을 지정
		//지원되는 최대 수명은 4주이며 기본값도 4주
		jsonObject.put("time_to_live", 300); // 5분
		
		// true로 설정되어 있으면 기기가 활성 상태가 될 때까지 메시지를 전송안 함 
		// 2016년 11월 15일자로 지원 중단됨
		// 기본값은 false
		jsonObject.put("delay_while_idle", false);
		
		// 메시지의 우선순위를 설정, 'normal' 및 'high'
		// 기본적으로 알림 메시지는 높은 우선순위로, 데이터 메시지는 보통 우선순위로 전송
		jsonObject.put("priority", "high");
		
		//전송할 디비아스 토큰들
		jsonObject.put("registration_ids", deviceIds);
		
		
		// 김진수.
		// OPPF PUSH 메세지는 title과 data로 구성한다.
		// 전송 요청을 받은 데이터를 일단 서버에 저장한 하고 이 데이터를 조회할 수 있는 고유 키를 생성한 후 
		// 실제 PUSH 메세지에는 상태바 또는 Alert에 표시되는 타이틀과 메세지 조회용 고유 키를 보내도록 한다.
		//JSONObject objData = new JSONObject();
		//objData.put("title", message);
		//objData.put("data", messageId);
		// jsonObject.put("data", objData);
		
		
		// data 페이로드
		JSONObject objData = new JSONObject();
		objData.put("data", message);
		jsonObject.put("data", objData);
		
		RestTemplateUtil restTemplateUtil = new RestTemplateUtil();
		ResponseEntity<String> responseEntity = restTemplateUtil.sendRestTemplate(fcmServerUrl, httpHeaders, HttpMethod.POST, jsonObject.toString());
		
		
		if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
			log.info(responseEntity.getBody());
			return responseEntity.getBody();
		}
		else{
			//log.debug("responseEntity failed : {} ", responseEntity.getBody());
		}
		
		return "";
	}
}
