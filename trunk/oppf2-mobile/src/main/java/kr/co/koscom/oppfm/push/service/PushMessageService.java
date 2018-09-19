package kr.co.koscom.oppfm.push.service;

import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.push.model.PushMessageReq;

public interface PushMessageService {
    /**
     * 수신한 푸시 메세지 조회 getPushMessage
     */
    CommonResponse getPushMessage(PushMessageReq pushMessageReq);
    
     
    
	/*
	 * batchSendPush
	 * 1.전송할 메세지 조회
	 * 2.대상 회원 조회
	 * 3.전송 및 insert(push_message_receiver_info)
	 */
    CommonResponse batchSendPush();

}
