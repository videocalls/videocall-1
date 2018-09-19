package kr.co.koscom.oppfm.cmm.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * EmailReqRes
 * <p>
 * Created by chungyeol.kim on 2017-06-15.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EmailReqRes extends CommonVO {
    /* 이메일발송(엔픽_썬더메일) 관련 */
    private String apiURL;                    //자동메일 연동 URL(필수)
    private String automailIDEncrypt;         //자동메일 템플릿ID 암호화값(필수)
    private String userCodeEncrypt;           //사용자 코드(필수)
    private String receiverName;              //받는 사람 이름(부분필수)
    private String receiverName2;             //회원가입완료 단계에서 마스킹처리 받는 이름
    private String mailTitle;                 //메일 제목(부분필수)
    private String mailContent;               //메일 내용(부분필수)
    private String senderName;                //보내는 사람 이름(부분필수)
    private String senderEmail;               //보내는 이메일(부분필수)
    private String receiverEmail;             //받는 사람 이메일(필수)
    private String receiverEmail2;            //회원가입완료 단계에서 마스킹처리 받는 이메일
    private String returnMail;                //반송 이메일(필수)
    private String onetooneInfo;              //일대일치환 정보
    private String reserveDate;               //예약발송 일시

    private String customerRegNo;             //회원 OP 등록 번호
    private String customerPassword;           //비밀번호찾기 단계에서 받는 회원비밀번호

    /* 이메일발송 결과 관련 */
    private String mailCode;                  //이메일발송 리턴코드
    private String mailMsg ;                  //이메일발송 리턴메시지

    /* com_email_send_info(이메일발송 정보) 로그적재 관련 */
    private String emailRegNo;                //이메일 발송 생성 번호
    private String emailSendType;             //이메일 유형
    private String emailVer;                  //이메일 버전
    private String senderKind;                //발신자 종류
    private String senderId;                  //발신자 ID
    private String receiverKind;              //수신자 종류
    private String receiverId;                //수신자 ID
    private String receiverId2;               //회원가입완료 단계에서 마스킹처리 받는 수신자 ID
    private String emailTitle;                //이메일 제목
    private String emailContent;              //이메일 내용
    private String sendDate;                  //최초 발신일자
    private String sendId;                    //최초 발신자 ID
    private String reSendCount;               //재발신 건수
    private String reSendDate;                //재발신 일자
    private String reSendId;                  //재발신자 ID
    private String sendResult;                //발송결과
    private String sendResultDate;            //발송결과일자
    private String reSendResult;              //재발송결과
    private String reSendResultDate;          //재발송결과일자

    private String managerSetTitle;           //어드민 메일발송관리에서 관리자가 입력한 제목
    private String managerSetContent;         //어드민 메일발송관리에서 관리자가 입력한 내용
    private String managerAdjYn;              //어드민 메일발송관리 상세화면에서 수정했는지 여부

    private String systemKind;						//임의로 system kind값을 셋팅 시 이값으로 systemKind판별
    private String mbrComplt;                       //회원가입완료시 1회 메일발송은 insert가 되게함
    private String uriContextPath;                  //uriContextPath
    private String managerSetTbody;                 //금융거래 정보제공 동의 내용

    /* 금융정보 제공동의 */
    private String customerName;
    private String customerEmail;

    private String termsMailContent;

    private String serviceRegNo;
    private String termsRegNo;
    private String termsAuthYn;
    private String subcompanyCodeId;
    private String subcompanyName;
    private String appId;
    private String appName;
    private String pubcompanyCodeId;
    private String pubcompanyName;
    private String apiId;
    private String apiName;
    private String customerVtaccountNo;
    private String customerVtaccountAlias;
    private String termsStartDate;
    private String termsExpireDate;
}
