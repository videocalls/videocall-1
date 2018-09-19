package kr.co.koscom.oppfm.push.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.member.model.MemberPushReq;
import kr.co.koscom.oppfm.push.dao.PushMessageMapper;
import kr.co.koscom.oppfm.push.model.PushMessageReq;
import kr.co.koscom.oppfm.push.model.PushMessageRes;
import kr.co.koscom.oppfm.push.service.PushMessageService;
import kr.co.koscom.oppfm.push.service.PushService;
import lombok.extern.slf4j.Slf4j;

/**
 * PushMessageServiceImpl
 * <p>
 * Created by Yoojin Han on 2017-05-19.
 */

@Slf4j
@Service
public class PushMessageServiceImpl implements PushMessageService {
    
    @Autowired
    PushMessageMapper pushMessageMapper;
    
    @Autowired
    MessageUtil messageUtil;
    
	@Resource(name = "config")
	private Properties properties;

    /**
     * 푸시 메세지 조회 getPushMessage
     */
    @Override
    public CommonResponse getPushMessage(PushMessageReq pushMessageReq) {

        Map<String , Object> body = new HashMap<>();
        /*
         * selectPushMessage();
         * PushMessageRes pushMsgList 일 경우, exception 처리
         */
        PushMessageRes pushMessage = pushMessageMapper.selectPushMessage(pushMessageReq);
        pushMessage.setPushMessageType( pushMessage.getPushMessageType().toLowerCase());
        body.put("pushMessage", pushMessage);
        
        if (pushMessage == null) {
            throw new CommonException(ErrorCodeConstants.NOT_FOUND_EXCEPTION, new String[] { "pushMessageRegNo" });
        }
        
        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }
    
    
    
	@Override
	@Transactional
	public CommonResponse batchSendPush() {
		/*
		 * 1.전송할 메세지 조회
		 * 2.대상 회원 조회
		 * 3.전송 및 insert(push_message_receiver_info)
		 * */
		List<PushMessageRes> sendPushList =pushMessageMapper.selectSendPushMessage();   		 //1.전송할 메세지 조회
		
		List<PushMessageRes>	 deviceInfoList = pushMessageMapper.selectMemberPush();			 //2.대상 회원 조회

		PushMessageReq reciverInfo = new PushMessageReq();
		PushMessageReq pushMessageReq = new PushMessageReq();
		
		PushService	androidSend =  new FCMPushServiceImpl(properties);								//안드로이드 전송 서비스
		PushService	iosSend 	= new APNSPushServiceImpl(properties);										//ios 전송 서비스
		
		for (PushMessageRes memberInfo : deviceInfoList) {														//회원 리스트
			if(memberInfo.getDeviceType().equals("G038002")){													//대상 회원- android
				for (PushMessageRes sendPushMessage : sendPushList) {										//보낼 pushMessageList
						if(sendPushMessage.getDeviceType().equals("G038001") || sendPushMessage.getDeviceType().equals("G038002")){     //deviceType - 전체, android
							try{//이한별 전임
								//3.전송 및 insert(push_message_receiver_info)
								androidSend.send(memberInfo.getDeviceUniqueId(), sendPushMessage.getPushMessageRegNo());				

								reciverInfo.setCustomerRegNo(memberInfo.getCustomerRegNo());
								reciverInfo.setPushMessageRegNo(sendPushMessage.getPushMessageRegNo());
								reciverInfo.setSendYn("Y");
								pushMessageMapper.insertPushMessageReciverInfo(reciverInfo);
								//push_message_reciver_info 테이블에 insert
								
								pushMessageReq.setPushMessageRegNo(sendPushMessage.getPushMessageRegNo());
								pushMessageReq.setSendYn("Y");
								pushMessageMapper.updatePushMessageInfo(pushMessageReq);
								//push_message_info 테이블에 send_yn(전송여부)를 update
							}
							catch(Exception e){
								log.error(e.toString());
								reciverInfo.setCustomerRegNo(memberInfo.getCustomerRegNo());
								reciverInfo.setPushMessageRegNo(sendPushMessage.getPushMessageRegNo());
								reciverInfo.setSendYn("N");
								pushMessageMapper.insertPushMessageReciverInfo(reciverInfo);								
							}
					}	
				}
			}else{//대상 회원- Ios
				for (PushMessageRes sendPushMessage : sendPushList) {										//보낼 pushMessageList
					if(sendPushMessage.getDeviceType().equals("G038001") || sendPushMessage.getDeviceType().equals("G038003")){     //pushMessageType - 전체, Ios
							try{
								//3.전송 및 insert(push_message_receiver_info)
								String resultStr = iosSend.send(memberInfo.getDeviceUniqueId(), sendPushMessage.getPushMessageRegNo());
								String resultYn = "Y";
								if("0".equals(resultStr)) {
								    resultYn = "N";
								}
								
								reciverInfo.setCustomerRegNo(memberInfo.getCustomerRegNo());
								reciverInfo.setPushMessageRegNo(sendPushMessage.getPushMessageRegNo());
								reciverInfo.setSendYn(resultYn);
								pushMessageMapper.insertPushMessageReciverInfo(reciverInfo);
								//push_message_reciver_info 테이블에 insert
								
								pushMessageReq.setPushMessageRegNo(sendPushMessage.getPushMessageRegNo());
								pushMessageReq.setSendYn(resultYn);
								pushMessageMapper.updatePushMessageInfo(pushMessageReq);
								//push_message_info 테이블에 send_yn(전송여부)를 update
							}
							catch(Exception e){
                                log.error(e.toString());
								reciverInfo.setCustomerRegNo(memberInfo.getCustomerRegNo());
								reciverInfo.setPushMessageRegNo(sendPushMessage.getPushMessageRegNo());
								reciverInfo.setSendYn("N");
								pushMessageMapper.insertPushMessageReciverInfo(reciverInfo);								
							}
					}	
				}						
			}
			
/*			if(memberInfo.getDeviceType().equals("G038003")){//대상 회원- Ios
				for (PushMessageRes sendPushMessage : sendPushList) {										//보낼 pushMessageList
					if(sendPushMessage.getDeviceType().equals("G038001") || sendPushMessage.getDeviceType().equals("G038003")){     //pushMessageType - 전체, Ios
						if(memberInfo.getDeviceUniqueId().equals("b6e194962a20c953dd4948a403bfac01bd471bbdf57b7e800f427ac05edc7617")){
						try{
							//3.전송 및 insert(push_message_receiver_info)
							iosSend.send(memberInfo.getDeviceUniqueId(), sendPushMessage.getPushMessageRegNo());
							
							reciverInfo.setCustomerRegNo(memberInfo.getCustomerRegNo());
							reciverInfo.setPushMessageRegNo(sendPushMessage.getPushMessageRegNo());
							reciverInfo.setSendYn("Y");
							pushMessageMapper.insertPushMessageReciverInfo(reciverInfo);
							//push_message_reciver_info 테이블에 insert
							
							pushMessageReq.setPushMessageRegNo(sendPushMessage.getPushMessageRegNo());
							pushMessageReq.setSendYn("Y");
							pushMessageMapper.updatePushMessageInfo(pushMessageReq);
							//push_message_info 테이블에 send_yn(전송여부)를 update
						}
						catch(Exception e){
							e.printStackTrace();
						}
						}
					}	
				}						
			}*/
		}
		
		
		Map<String , Object> body = new HashMap<>();
		body.put("sendPushList", sendPushList);
		body.put("deviceInfoList", deviceInfoList);
		
		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
	}

	
    
}
