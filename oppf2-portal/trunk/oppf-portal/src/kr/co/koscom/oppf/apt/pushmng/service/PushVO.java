package kr.co.koscom.oppf.apt.pushmng.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;
import lombok.Data;

@Data
@SuppressWarnings("serial")
public class PushVO extends ComDefaultListVO{
	
	private String pushMessageRegno;					//Push메세지 등록번호
	private String pushMessageRegnoPrefix="P";					//Push메세지 등록번호
	private String pushMessageTitle;						//push메세지 제목
	private String pushMessageType;						//push메세지 구분
	private String totalCount;
	private String successCount;
	private String failCount;
	private String deviceType;								//디바이스 타입 코드
	private String codeNameKor;							//디바이스 타입 명
	private String contents;									//메세지 내용
	private String contentsUrl;
	private String sendType;									//전송유형
	private String sendYn;									//전송여부
	private String sendDate;									//전송 날짜
	private String createDate;								//등록일
	private String createId;									//등록자
	private String updateId;									//변경자
	private String updateDate;								//변경일
	
	
    /**
     * 검색조건
     * */
    private String searchCondition;             //키워드 검색 구분
    private String searchKeyword;               //키워드
    private String searchDateType;              //검색날짜 타입 : 등록일, 수정일
    private String searchDateFrom;              //작날짜
    private String searchDateTo;                //끝날짜
    
    private List<String> searchDeviceType;                
    private String searchDeviceTypeAllYn;
    
    private List<String> searchPushMessageType;     
    private String searchPushMessageTypeAllYn;
    
    private List<String> searchSendType;     
    private String searchSendTypeAllYn;
    
    private List<String> searchSendYn;     
    private String searchSendYnAllYn;


}
