package kr.co.koscom.oppfm.push.service.impl;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.JSONException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServer;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.PushedNotifications;
import kr.co.koscom.oppfm.push.service.PushService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class APNSPushServiceImpl implements PushService {
	private String apnsKeystore = null;
	private String apnsPasswd = null;
	private boolean production = false;
	
	public APNSPushServiceImpl(Properties properties) {
		Resource resource = new ClassPathResource(properties.get("Globals.push.apnsKeystore").toString());
		
		try {
			apnsKeystore = resource.getFile().getAbsolutePath();
		} catch (IOException e) {
		    log.error(e.toString());
			e.printStackTrace();
		}

		apnsPasswd = properties.get("Globals.push.apnsPasswd").toString();
		production = Boolean.parseBoolean(properties.get("Globals.push.apnsProduction").toString());
	}
	
	@Override
	public String send(String deviceToken, String message) {
		PushNotificationManager pushManager = new PushNotificationManager();
		AppleNotificationServer pushServer = null;
		try{
			pushServer = new AppleNotificationServerBasicImpl(apnsKeystore, apnsPasswd, production);
			pushManager.initializeConnection(pushServer);
		}
		catch (KeystoreException e) {
		    log.error(e.toString());
			e.printStackTrace();
		}
		catch (CommunicationException e) {
            log.error(e.toString());
			e.printStackTrace();
		}
		
		pushManager.setEnhancedNotificationFormatEnabled(false);
		
		int badgeCount = 1;
		
		String apnsMessageId = createApnsMessageId();
		
		// 김진수.
		// OPPF PUSH 메세지는 title과 data로 구성한다.
		// 전송 요청을 받은 데이터를 일단 서버에 저장한 하고 이 데이터를 조회할 수 있는 고유 키를 생성한 후 
		// 실제 PUSH 메세지에는 상태바 또는 Alert에 표시되는 타이틀과 메세지 조회용 고유 키를 보내도록 한다.
		PushNotificationPayload pusgPayload = PushNotificationPayload.complex();
		
		try {
			// 김진수. 기본적으로 단말기에 전송하는 메세지.
			pusgPayload.addAlert("메세지를 수신 하였습니다."); //  상태바 또는 Alert에 표시되는 타이틀
			pusgPayload.addCustomDictionary("data", message);
			pusgPayload.addBadge(badgeCount);
			pusgPayload.addSound("default");
			
			// 메세지 조회용 고유 키를 지정 
			//pusgPayload.addCustomDictionary("messageId", messageId);
		}
		catch (JSONException e) {
            log.error(e.toString());
			e.printStackTrace();
		}
		
		PushedNotifications pushNotifications = null;
		
		List<Device> devices = new ArrayList<Device>();
		
		Device device = new BasicDevice();
		device.setToken(deviceToken);
		device.setDeviceId("" + 0);
		devices.add(device);
		
		try {
			pushManager.restartConnection(pushServer);
		}
		catch (CommunicationException e) {
            log.error(e.toString());
			e.printStackTrace();
		}
		catch (KeystoreException e) {
            log.error(e.toString());
			e.printStackTrace();
		}
		
		try {
			pushNotifications = pushManager.sendNotifications(pusgPayload, devices);
		}
		catch (CommunicationException | KeystoreException e) {
            log.error(e.toString());
			e.printStackTrace();
		}
		
		int failed = 0;
	    int successful = 0;
		PushedNotification p = pushNotifications.get(0);
		if(p.isSuccessful()) {
			successful++;
		}
		else{
			failed++;
		}
		
		return successful + "";
	}
	
	private String createApnsMessageId() {
		return ""+ System.currentTimeMillis();
	}
}
