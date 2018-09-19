package kr.co.koscom.oppfm.cmm.service.impl;

import com.andwise.tm6.api.jars.automail.AutomailAPI;
import kr.co.koscom.oppfm.cmm.dao.EmailMapper;
import kr.co.koscom.oppfm.cmm.model.EmailReqRes;
import kr.co.koscom.oppfm.cmm.service.EmailService;
import kr.co.koscom.oppfm.cmm.util.CommonCodeConstants;
import kr.co.koscom.oppfm.cmm.util.OppfStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * EmailServiceImpl
 * <p>
 * Created by chungyeol.kim on 2017-06-15.
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailMapper emailMapper;

    @Value("#{config['Globals.manager.email']}")
    private String managerEmail;

    @Value("#{config['Globals.automailAPI.apiURL']}")
    private String autoMailApiUrl;

    @Value("#{config['Globals.automailAPI.userCodeEncrypt']}")
    private String userCodeEncrypt;

    @Value("#{config['Globals.user.policy.password.super']}")
    private String passwordSuper;

    @Value("#{config['Globals.automailAPI.automailIDEncrypt.G016003']}")
    private String automailIDEncryptG016003;

    @Value("#{config['Globals.automailAPI.automailIDEncrypt.G016005']}")
    private String automailIDEncryptG016005;

    @Value("#{config['Globals.automailAPI.automailIDEncrypt.G016006']}")
    private String automailIDEncryptG016006;

    @Value("#{config['Globals.automailAPI.automailIDEncrypt.G016007']}")
    private String automailIDEncryptG016007;

    @Value("#{config['Globals.automailAPI.automailIDEncrypt.G016008']}")
    private String automailIDEncryptG016008;

    @Value("#{config['Globals.system.emailSendId.spt']}")
    private String emailSendId;

    @Value("#{config['Globals.domain.spt.name']}")
    private String domainName;

    @Value("#{config['Globals.domain.spt']}")
    private String domain;

    @Override
    public EmailReqRes sendEmail(EmailReqRes emailReqRes, HttpServletRequest request) {
        log.info("------------- cmmEmailSend START ------------------------");

        EmailReqRes rsEmailReqRes = new EmailReqRes(); //결과VO 생성
        EmailReqRes rsEmailReqResTemp = new EmailReqRes(); //이메일 템플릿 결과VO 생성

        //1.이메일 템플릿 조회(이메일제목과 이메일내용을 가져옵니다)
        rsEmailReqResTemp = emailMapper.selectSendEmailInfo(emailReqRes);

        //2.이메일발송 유형 구분(공통)
        emailReqRes = emailSendTypeDiv(emailReqRes, rsEmailReqResTemp, request);

        AutomailAPI automailAPI = new AutomailAPI();
        automailAPI.setApiURL(autoMailApiUrl); //엔픽메일UI URL(필수/고정)
        automailAPI.setAutomailIDEncrypt(emailReqRes.getAutomailIDEncrypt()); //암호화된 자동메일ID(필수/변경가능)
        automailAPI.setUserCodeEncrypt(userCodeEncrypt); //사용자코드(필수/고정)(썬더메일_엔픽 사에서 주는 안호화된 코드)
        automailAPI.setMailTitle(emailReqRes.getMailTitle());                 //메일 제목
        automailAPI.setMailContent(emailReqRes.getMailContent());             //메일 내용-템플릿으로 고정함
        automailAPI.setSenderName(passwordSuper); //보내는 사람 이름(전용:템플릿에서 이미 확정, 개별:여기서 셋팅한 값으로)
        automailAPI.setSenderEmail(managerEmail); //보내는 사람 이메일(전용:템플릿에서 이미 확정, 개별:여기서 셋팅한 값으로)
        automailAPI.setReceiverName(emailReqRes.getReceiverName());           //받는 사람 이름
        automailAPI.setReceiverEmail(emailReqRes.getReceiverEmail());         //받는 사람 이메일(필수)
        automailAPI.setReturnMail(managerEmail); //반송 이메일(필수)
        automailAPI.setOnetooneInfo(emailReqRes.getOnetooneInfo());           //일대일치환 정보

        //3.메일 발송
        automailAPI.sendEmail();
        rsEmailReqRes.setMailCode(automailAPI.getCode());
        rsEmailReqRes.setMailMsg(automailAPI.getMsg());

        if("100".equals(rsEmailReqRes.getMailCode())){ //데이터 연동 성공
            if(OppfStringUtil.isEmpty(emailReqRes.getReSendId())){ //재발신자ID가 empty면 발송결과 셋팅을 합니다.
                emailReqRes.setSendResult(CommonCodeConstants.Mail_SEND_001); //발송결과
            }else{ //재발신자ID가 empty가 아니면 재발송결과 셋팅을 합니다.
                emailReqRes.setReSendResult(CommonCodeConstants.Mail_SEND_001); //재발송결과
            }
        }else if("200".equals(rsEmailReqRes.getMailCode())){ //필수 파라미터 미입력, 데이터 값 오류
            if(OppfStringUtil.isEmpty(emailReqRes.getReSendId())){
                emailReqRes.setSendResult(CommonCodeConstants.Mail_SEND_002);
            }else{
                emailReqRes.setReSendResult(CommonCodeConstants.Mail_SEND_002);
            }
        }else if("300".equals(rsEmailReqRes.getMailCode())){ //서버 오류
            if(OppfStringUtil.isEmpty(emailReqRes.getReSendId())){
                emailReqRes.setSendResult(CommonCodeConstants.Mail_SEND_003);
            }else{
                emailReqRes.setReSendResult(CommonCodeConstants.Mail_SEND_003);
            }
        }else if("-400".equals(rsEmailReqRes.getMailCode())){ //서버 통신 오류
            if(OppfStringUtil.isEmpty(emailReqRes.getReSendId())){
                emailReqRes.setSendResult(CommonCodeConstants.Mail_SEND_004);
            }else{
                emailReqRes.setReSendResult(CommonCodeConstants.Mail_SEND_004);
            }
        }else if("500".equals(rsEmailReqRes.getMailCode())){ //접근IP 오류
            if(OppfStringUtil.isEmpty(emailReqRes.getReSendId())){
                emailReqRes.setSendResult(CommonCodeConstants.Mail_SEND_005);
            }else{
                emailReqRes.setReSendResult(CommonCodeConstants.Mail_SEND_005);
            }
        }else if("800".equals(rsEmailReqRes.getMailCode())){ //포인트 확인 실패 또는 포인트 없음
            if(OppfStringUtil.isEmpty(emailReqRes.getReSendId())){
                emailReqRes.setSendResult(CommonCodeConstants.Mail_SEND_006);
            }else{
                emailReqRes.setReSendResult(CommonCodeConstants.Mail_SEND_006);
            }
        }else if("900".equals(rsEmailReqRes.getMailCode())){ //포인트 적용 실패
            if(OppfStringUtil.isEmpty(emailReqRes.getReSendId())){
                emailReqRes.setSendResult(CommonCodeConstants.Mail_SEND_007);
            }else{
                emailReqRes.setReSendResult(CommonCodeConstants.Mail_SEND_007);
            }
        }

        //4.com_email_send_info(이메일발송 정보) 로그적재
        if(OppfStringUtil.isEmpty(emailReqRes.getEmailRegNo())){ //emailRegNo(이메일 발송 생성 번호)가 없어야 insert 대상입니다.
            emailMapper.inseretSendEmailInfo(emailReqRes);
        }else{
            emailMapper.updateSendEmailInfo(emailReqRes);
        }

        log.info("------------- cmmEmailSend END --------------------------");

        return rsEmailReqRes;
    }

    private EmailReqRes emailSendTypeDiv(EmailReqRes cmmEmailSendVO, EmailReqRes rsCmmEmailTempVO, HttpServletRequest request) {

        String uriContextPath = request.getParameter("uriContextPath");
        log.debug("uriContextPath : {} ", uriContextPath);
        if(!OppfStringUtil.isEmpty(uriContextPath)){
            cmmEmailSendVO.setUriContextPath(uriContextPath); //임시비밀번호
        }

        String GlobalsEmail = managerEmail;
        String GlobalsEmailSendId = emailSendId;
        String GlobalsDomain = "http://" + domain;
        String GlobalsDomainName = domainName;

        String setAutomailIDEncrypt = "";
        String setReceiverName = cmmEmailSendVO.getReceiverName();
        String setMailTitle = "";
        String setMailContent = "";
        if(!"Y".equals(cmmEmailSendVO.getManagerAdjYn())){
            setMailTitle = OppfStringUtil.replace(rsCmmEmailTempVO.getEmailTitle(), "++[$GlobalsDomainName]++", GlobalsDomainName);
            setMailContent = rsCmmEmailTempVO.getEmailContent();
        }
        String setReceiverEmail = cmmEmailSendVO.getReceiverEmail();
        String setOnetooneInfo = "";
        //String setReserveDate = "2016-05-24 14:40:00";
        String customerRegNo = cmmEmailSendVO.getCustomerRegNo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String customerPassword = cmmEmailSendVO.getCustomerPassword();
        String ManagerSetTitle = cmmEmailSendVO.getManagerSetTitle(); //어드민 메일발송관리에서 관리자가 입력한 제목
        String ManagerSetContent = cmmEmailSendVO.getManagerSetContent(); //어드민 메일발송관리에서 관리자가 입력한 내용


        String div = cmmEmailSendVO.getEmailSendType();
        if("G016003".equals(div)){ //회원 - 회원가입 완료
            setAutomailIDEncrypt = automailIDEncryptG016003;
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$receiverName]++", setReceiverName);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$customerName]++", cmmEmailSendVO.getReceiverName2());
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$customerId]++", cmmEmailSendVO.getReceiverId2());
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$customerEmail]++", cmmEmailSendVO.getReceiverEmail2());
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$customerRegDd]++", sdf.format(date));
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomain]++", GlobalsDomain);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomain2]++", GlobalsDomain);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$imgUrl]++", GlobalsDomain);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$imgUrl2]++", GlobalsDomain);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsEmail]++", GlobalsEmail);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomainName]++", GlobalsDomainName);
            cmmEmailSendVO.setSenderId(GlobalsEmailSendId);
            cmmEmailSendVO.setSendId(GlobalsEmailSendId);
            cmmEmailSendVO.setCreateId(GlobalsEmailSendId);
            cmmEmailSendVO.setUpdateId(GlobalsEmailSendId);

        }else if("G016005".equals(div)){ // 회원탈퇴 확인 메일
            setAutomailIDEncrypt = automailIDEncryptG016005;
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$receiverName]++", setReceiverName);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$customerName]++", cmmEmailSendVO.getReceiverName2());
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$customerId]++", cmmEmailSendVO.getReceiverId2());
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$customerEmail]++", cmmEmailSendVO.getReceiverEmail2());
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$customerSecesDd]++", sdf.format(date));
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomain]++", GlobalsDomain);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsEmail]++", GlobalsEmail);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomainName]++", GlobalsDomainName);

            setMailContent = OppfStringUtil.replace(setMailContent, "++[$imgUrl]++", GlobalsDomain);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$imgUrl2]++", GlobalsDomain);

            cmmEmailSendVO.setSenderId(GlobalsEmailSendId);
            cmmEmailSendVO.setSendId(GlobalsEmailSendId);
            cmmEmailSendVO.setCreateId(GlobalsEmailSendId);
            cmmEmailSendVO.setUpdateId(GlobalsEmailSendId);

        }else if("G016006".equals(div)){ // 정보제공 동의 및 내용변경
            List<EmailReqRes> termsList = emailMapper.selectEmailTermsList(cmmEmailSendVO);
            StringBuffer termsHtml = new StringBuffer();
            termsHtml.append("<tr>");
            termsHtml.append("<td colspan='5' style='color:#0c0c0c; border-bottom:1px solid #bec3c7; padding:8px;'>조회 된 금융거래 정보제공 동의 정보가 없습니다.</td>");
            termsHtml.append("</tr>");

            //정보 셋팅
            if(termsList != null){
                if(termsList.size() > 0){
                    termsHtml = new StringBuffer();

                    String appId = "";
                    String pubcompanyCodeId = "";

                    for(int i=0; i<termsList.size(); i++){
                        EmailReqRes data = (EmailReqRes) termsList.get(i);

                        // appIdFlag  새로운 핀테크 서비스명 판단 변수  by YJ
                        boolean appIdFlag = false;

                        termsHtml.append("<tr>");

                        //핀테크 기업
                        if(i == 0){
                            //핀테크 기업 1개당 약관 1개
                            termsHtml.append("<th rowspan='"+termsList.size()+"' scope='row' style='font-weight:normal; color:#0c0c0c; border-bottom:1px solid #bec3c7; padding:8px;'>");
                            termsHtml.append(data.getSubcompanyName());
                            termsHtml.append("</th>");
                        }

                        //핀테크 서비스
                        if(!appId.equals(data.getAppId())){
                            int rowspanCnt = 0;
                            for(int x=0; x<termsList.size(); x++){
                                EmailReqRes rowspanData = (EmailReqRes) termsList.get(x);

                                if(data.getAppId().equals(rowspanData.getAppId())){
                                    rowspanCnt++;
                                }
                            }

                            termsHtml.append("<th rowspan='"+rowspanCnt+"' scope='row' style='font-weight:normal; color:#0c0c0c; border-bottom:1px solid #bec3c7; padding:8px;'>");
                            termsHtml.append(data.getAppName());
                            termsHtml.append("</th>");

                            appId = data.getAppId();
                            appIdFlag = true;
                        }

                        //[s] 다른 핀테스 서비스에 증권사가 동일할 경우 금융투자회사에 증권사 출력 로직  2017.04.19  한유진
                        if(appIdFlag){		//appId 명이 바뀔경우
                            int rowspanCnt = 0;
                            for(int x=0; x<termsList.size(); x++){
                                EmailReqRes rowspanData = (EmailReqRes) termsList.get(x);

                                if(
                                        data.getAppId().equals(rowspanData.getAppId()) &&
                                                data.getPubcompanyCodeId().equals(rowspanData.getPubcompanyCodeId())
                                        ){
                                    rowspanCnt++;
                                }
                            }

                            termsHtml.append("<th rowspan='"+rowspanCnt+"' scope='row' style='font-weight:normal; color:#0c0c0c; border-bottom:1px solid #bec3c7; padding:8px;'>");
                            termsHtml.append(data.getPubcompanyName());
                            termsHtml.append("</th>");

                            pubcompanyCodeId = data.getPubcompanyCodeId();

                            //증권사 -> 같은 핀테크 서비스에 증권사가 다를경우
                        }else{		//appId 명이 동일할경우
                            if(appId.equals(data.getAppId()) && !pubcompanyCodeId.equals(data.getPubcompanyCodeId())){
                                int rowspanCnt = 0;
                                for(int x=0; x<termsList.size(); x++){
                                    EmailReqRes rowspanData = (EmailReqRes) termsList.get(x);

                                    if(
                                            data.getAppId().equals(rowspanData.getAppId()) &&
                                                    data.getPubcompanyCodeId().equals(rowspanData.getPubcompanyCodeId())
                                            ){
                                        rowspanCnt++;
                                    }
                                }

                                termsHtml.append("<th rowspan='"+rowspanCnt+"' scope='row' style='font-weight:normal; color:#0c0c0c; border-bottom:1px solid #bec3c7; padding:8px;'>");
                                termsHtml.append(data.getPubcompanyName());
                                termsHtml.append("</th>");

                                pubcompanyCodeId = data.getPubcompanyCodeId();
                            }
                        }
                        //[e] 다른 핀테스 서비스에 증권사가 동일할 경우 금융투자회사에 증권사 출력 로직  2017.04.19  한유진

                        //가상계좌 -> 가상계좌 + <br> + 별칭
                        termsHtml.append("<td style='color:#0c0c0c; border-bottom:1px solid #bec3c7; padding:8px;'>");
                        termsHtml.append(data.getCustomerVtaccountNo());
                        termsHtml.append("<br>/");
                        termsHtml.append(data.getCustomerVtaccountAlias());
                        termsHtml.append("</td>");

                        //약관동의 (유효기간) -> 약관 1개
                        if(i == 0){
                            //핀테크 기업 1개당 약관 1개
                            termsHtml.append("<td rowspan='"+termsList.size()+"' style='color:#0c0c0c; border-bottom:1px solid #bec3c7; padding:8px;'>");
                            termsHtml.append(data.getTermsStartDate());
                            termsHtml.append("<br>~");
                            termsHtml.append(data.getTermsExpireDate());
                            termsHtml.append("</td>");
                        }

                        termsHtml.append("</tr>");

                    }
                }
            }

            //setAutomailIDEncrypt = OppfProperties.getProperty("Globals.automailAPI.automailIDEncrypt");
            setAutomailIDEncrypt = automailIDEncryptG016006;

            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomain]++", cmmEmailSendVO.getUriContextPath());
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomainName]++", GlobalsDomainName);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsEmail]++", GlobalsEmail);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$imgUrl]++", GlobalsDomain);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$imgUrl2]++", GlobalsDomain);

            //terms정보 셋팅
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$termsContent]++", termsHtml.toString());

            cmmEmailSendVO.setSenderId(GlobalsEmailSendId);
            cmmEmailSendVO.setSendId(GlobalsEmailSendId);
            cmmEmailSendVO.setCreateId(GlobalsEmailSendId);
            cmmEmailSendVO.setUpdateId(GlobalsEmailSendId);

        }else if("G016007".equals(div)){ // 정보제공 동의 확인
            setAutomailIDEncrypt = automailIDEncryptG016007;

            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomain]++", cmmEmailSendVO.getUriContextPath());
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomainName]++", GlobalsDomainName);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsEmail]++", GlobalsEmail);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$imgUrl]++", GlobalsDomain);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$imgUrl2]++", GlobalsDomain);

            //terms정보 셋팅
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$termsContent]++", cmmEmailSendVO.getTermsMailContent());

            cmmEmailSendVO.setSenderId(GlobalsEmailSendId);
            cmmEmailSendVO.setSendId(GlobalsEmailSendId);
            cmmEmailSendVO.setCreateId(GlobalsEmailSendId);
            cmmEmailSendVO.setUpdateId(GlobalsEmailSendId);

        }else if("G016008".equals(div)){ // 정보제공 동의 재동의
            setAutomailIDEncrypt = automailIDEncryptG016008;

            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomain]++", cmmEmailSendVO.getUriContextPath());
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomainName]++", GlobalsDomainName);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsEmail]++", GlobalsEmail);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$imgUrl]++", GlobalsDomain);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$imgUrl2]++", GlobalsDomain);

            //terms정보 셋팅
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$termsContent]++", cmmEmailSendVO.getTermsMailContent());

            cmmEmailSendVO.setSenderId(GlobalsEmailSendId);
            cmmEmailSendVO.setSendId(GlobalsEmailSendId);
            cmmEmailSendVO.setCreateId(GlobalsEmailSendId);
            cmmEmailSendVO.setUpdateId(GlobalsEmailSendId);

        }

        setOnetooneInfo = "[$receiverName]Ð"+setReceiverName+"æ[$mailTitle]Ð"+setMailTitle+"æ[$emailContent]Ð"+setMailContent+"æ";

        /* 이메일발송 관련 */
        cmmEmailSendVO.setAutomailIDEncrypt(setAutomailIDEncrypt); //이메일템플릿에 따른 암호값입니다.
        cmmEmailSendVO.setMailTitle(setMailTitle); //이메일발송시 셋팅될 이메일제목
        cmmEmailSendVO.setMailContent(setMailContent); //이메일발송시 셋팅될 이메일내용
        cmmEmailSendVO.setReceiverName(setReceiverName); //이메일발송시 셋팅될 수신인 이름
        cmmEmailSendVO.setReceiverEmail(setReceiverEmail); //이메일발송시 지정될 수신인의 이메일 주소
        cmmEmailSendVO.setOnetooneInfo(setOnetooneInfo); //1:1매핑(엔픽에 저장한 템플릿과 매핑될) 정보
        //cmmEmailSendVO.setReserveDate(setReserveDate);

        cmmEmailSendVO.setSenderKind(cmmEmailSendVO.getSenderKind());             //발신자 종류
        cmmEmailSendVO.setSenderId(cmmEmailSendVO.getSenderId());                 //발신자 ID
        cmmEmailSendVO.setReceiverKind(cmmEmailSendVO.getReceiverKind());         //수신자 종류
        cmmEmailSendVO.setReceiverId(cmmEmailSendVO.getReceiverId());             //수신자 ID
        cmmEmailSendVO.setReceiverEmail(setReceiverEmail);                        //수신자 email
        cmmEmailSendVO.setEmailTitle(setMailTitle);                               //이메일 제목
        cmmEmailSendVO.setEmailContent(setMailContent);                           //이메일 내용
        cmmEmailSendVO.setSendId(cmmEmailSendVO.getSendId());                     //최초 발신자 ID
        cmmEmailSendVO.setCreateId(cmmEmailSendVO.getCreateId());                 //생성자 ID
        cmmEmailSendVO.setUpdateId(cmmEmailSendVO.getUpdateId());                 //변경자 ID

        cmmEmailSendVO.setEmailRegNo(cmmEmailSendVO.getEmailRegNo());             //이메일 발송 생성 번호
        cmmEmailSendVO.setReSendId(cmmEmailSendVO.getReSendId());                 //재발신자 ID

        return cmmEmailSendVO;
    }

    @Override
    public EmailReqRes getEmailCustomerInfo(EmailReqRes emailReqRes) {
        return emailMapper.selectEmailCustomerInfo(emailReqRes);
    }

}
