package kr.co.koscom.oppf.cmm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andwise.tm6.api.jars.automail.AutomailAPI;

import kr.co.koscom.oppf.cmm.service.CmmEmailCronSvcTermsVO;
import kr.co.koscom.oppf.cmm.service.CmmEmailSendService;
import kr.co.koscom.oppf.cmm.service.CmmEmailSendVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.CodeConstants;
import kr.co.koscom.oppf.cmm.util.OppfSessionUtil;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.impl.MbrRegServiceImpl;


/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CmmEmailSendServiceImpl.java
* @Comment  : 공통 이메일발송 기능을 제공하는 serviceImpl
* @author   : 유제량
* @since    : 2016.05.25
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.25  유제량        최초생성
*
*/
@Service("CmmEmailSendService")
public class CmmEmailSendServiceImpl implements CmmEmailSendService{
    
    @Resource(name = "CmmEmailSendDAO")
    private CmmEmailSendDAO cmmEmailSendDAO;
    
    private static final Logger log = Logger.getLogger(MbrRegServiceImpl.class);
    
    /**
     * @Method Name        : selectCmmComEmailSendInfo
     * @Method description : 이메일 템플릿을 조회해온다.
     * @param              : CmmEmailSendVO
     * @return             : CmmEmailSendVO
     * @throws             : Exception
     */
    @Transactional
    public CmmEmailSendVO selectCmmComEmailSendInfo(CmmEmailSendVO cmmEmailSendVO) throws Exception{
        log.info("------------- selectCmmComEmailSendInfo START ------------------------");
        CmmEmailSendVO rsCmmEmailSendVO = cmmEmailSendDAO.selectCmmComEmailSendInfo(cmmEmailSendVO);
        log.info("------------- selectCmmComEmailSendInfo END --------------------------");        
        return rsCmmEmailSendVO;
    }
    
    /**
     * @Method Name        : cmmEmailSend
     * @Method description : 공통 이메일발송을 한다.(성공시 로그 적재)
     * @param              : CmmEmailSendVO
     * @return             : CmmEmailSendVO
     * @throws             : Exception
     */
    @Transactional
    public CmmEmailSendVO cmmEmailSend(CmmEmailSendVO cmmEmailSendVO, HttpServletRequest request) throws Exception{
        log.info("------------- cmmEmailSend START ------------------------");
        
        CmmEmailSendVO rsCmmEmailSendVO = new CmmEmailSendVO(); //결과VO 생성
        CmmEmailSendVO rsCmmEmailTempVO = new CmmEmailSendVO(); //이메일 템플릿 결과VO 생성
        
        String systemKind = OppfSessionUtil.getSystemKind(request);     //system kind를 가져온다.
        if("".equals(OppfStringUtil.isNullToString(systemKind))){
            systemKind = "spt"; //회원가입단계는 로그인 상태가 아니므로 null값이 옵니다. 그 이외에는 system kind를 셋팅합니다.
        }
        if(!OppfStringUtil.isEmpty(cmmEmailSendVO.getSystemKind())){
            systemKind = cmmEmailSendVO.getSystemKind();
        }        
        String GlobalsManagerEmail = "Globals.manager.email."+systemKind;
        
        //1.이메일 템플릿 조회(이메일제목과 이메일내용을 가져옵니다)
        rsCmmEmailTempVO = selectCmmComEmailSendInfo(cmmEmailSendVO);
                
        //2.이메일발송 유형 구분(공통)
        cmmEmailSendVO = emailSendTypeDiv(cmmEmailSendVO, rsCmmEmailTempVO, request);
        
        AutomailAPI automailAPI = new AutomailAPI(); 
        automailAPI.setApiURL(OppfProperties.getProperty("Globals.automailAPI.apiURL")); //엔픽메일UI URL(필수/고정)
        automailAPI.setAutomailIDEncrypt(cmmEmailSendVO.getAutomailIDEncrypt()); //암호화된 자동메일ID(필수/변경가능)
        automailAPI.setUserCodeEncrypt(OppfProperties.getProperty("Globals.automailAPI.userCodeEncrypt")); //사용자코드(필수/고정)(썬더메일_엔픽 사에서 주는 안호화된 코드)
        automailAPI.setMailTitle(cmmEmailSendVO.getMailTitle());                 //메일 제목  
        automailAPI.setMailContent(cmmEmailSendVO.getMailContent());             //메일 내용-템플릿으로 고정함
        automailAPI.setSenderName(OppfProperties.getProperty("Globals.user.policy.password.super")); //보내는 사람 이름(전용:템플릿에서 이미 확정, 개별:여기서 셋팅한 값으로)
        automailAPI.setSenderEmail(OppfProperties.getProperty(GlobalsManagerEmail)); //보내는 사람 이메일(전용:템플릿에서 이미 확정, 개별:여기서 셋팅한 값으로)
        automailAPI.setReceiverName(cmmEmailSendVO.getReceiverName());           //받는 사람 이름
        automailAPI.setReceiverEmail(cmmEmailSendVO.getReceiverEmail());         //받는 사람 이메일(필수)
        automailAPI.setReturnMail(OppfProperties.getProperty(GlobalsManagerEmail)); //반송 이메일(필수)
        automailAPI.setOnetooneInfo(cmmEmailSendVO.getOnetooneInfo());           //일대일치환 정보
//        automailAPI.setReserveDate(cmmEmailSendVO.getReserveDate());           //예약발송 일시
        
        //3.메일 발송
        automailAPI.sendEmail();
        //System.out.println("auto code = " + automailAPI.getCode());
        //System.out.println("auto msg = " + automailAPI.getMsg());        
        rsCmmEmailSendVO.setMailCode(automailAPI.getCode());
        rsCmmEmailSendVO.setMailMsg(automailAPI.getMsg());
        
        if("100".equals(rsCmmEmailSendVO.getMailCode())){ //데이터 연동 성공
            if(OppfStringUtil.isEmpty(cmmEmailSendVO.getReSendId())){ //재발신자ID가 empty면 발송결과 셋팅을 합니다.                
                cmmEmailSendVO.setSendResult(CodeConstants.Mail_SEND_001); //발송결과
            }else{ //재발신자ID가 empty가 아니면 재발송결과 셋팅을 합니다.
                cmmEmailSendVO.setReSendResult(CodeConstants.Mail_SEND_001); //재발송결과
            }
        }else if("200".equals(rsCmmEmailSendVO.getMailCode())){ //필수 파라미터 미입력, 데이터 값 오류
            if(OppfStringUtil.isEmpty(cmmEmailSendVO.getReSendId())){                
                cmmEmailSendVO.setSendResult(CodeConstants.Mail_SEND_002);
            }else{
                cmmEmailSendVO.setReSendResult(CodeConstants.Mail_SEND_002);
            }
        }else if("300".equals(rsCmmEmailSendVO.getMailCode())){ //서버 오류
            if(OppfStringUtil.isEmpty(cmmEmailSendVO.getReSendId())){                
                cmmEmailSendVO.setSendResult(CodeConstants.Mail_SEND_003);
            }else{
                cmmEmailSendVO.setReSendResult(CodeConstants.Mail_SEND_003);
            }
        }else if("-400".equals(rsCmmEmailSendVO.getMailCode())){ //서버 통신 오류
            if(OppfStringUtil.isEmpty(cmmEmailSendVO.getReSendId())){                
                cmmEmailSendVO.setSendResult(CodeConstants.Mail_SEND_004);
            }else{
                cmmEmailSendVO.setReSendResult(CodeConstants.Mail_SEND_004);
            }
        }else if("500".equals(rsCmmEmailSendVO.getMailCode())){ //접근IP 오류
            if(OppfStringUtil.isEmpty(cmmEmailSendVO.getReSendId())){                
                cmmEmailSendVO.setSendResult(CodeConstants.Mail_SEND_005);
            }else{
                cmmEmailSendVO.setReSendResult(CodeConstants.Mail_SEND_005);
            }
        }else if("800".equals(rsCmmEmailSendVO.getMailCode())){ //포인트 확인 실패 또는 포인트 없음
            if(OppfStringUtil.isEmpty(cmmEmailSendVO.getReSendId())){                
                cmmEmailSendVO.setSendResult(CodeConstants.Mail_SEND_006);
            }else{
                cmmEmailSendVO.setReSendResult(CodeConstants.Mail_SEND_006);
            }
        }else if("900".equals(rsCmmEmailSendVO.getMailCode())){ //포인트 적용 실패
            if(OppfStringUtil.isEmpty(cmmEmailSendVO.getReSendId())){                
                cmmEmailSendVO.setSendResult(CodeConstants.Mail_SEND_007);
            }else{
                cmmEmailSendVO.setReSendResult(CodeConstants.Mail_SEND_007);
            }
        }
        
        //4.com_email_send_info(이메일발송 정보) 로그적재
        if(OppfStringUtil.isEmpty(cmmEmailSendVO.getEmailRegNo())){ //emailRegNo(이메일 발송 생성 번호)가 없어야 insert 대상입니다.
            cmmEmailSendDAO.insertCmmComEmailSendInfo(cmmEmailSendVO);
        }else{
            cmmEmailSendDAO.updateCmmComEmailSendInfo(cmmEmailSendVO);
        }
        
        log.info("------------- cmmEmailSend END --------------------------");
        
        return rsCmmEmailSendVO;
    }
    
    /**
     * @Method Name        : emailSendTypeDiv
     * @Method description : 이메일 템플릿을 공통에서 관리한다.(분기용)
     * @param              : CmmEmailSendVO
     * @return             : CmmEmailSendVO
     * @throws             : Exception
     */
    public CmmEmailSendVO emailSendTypeDiv(CmmEmailSendVO cmmEmailSendVO, CmmEmailSendVO rsCmmEmailTempVO, HttpServletRequest request) throws Exception{
        String systemKind = OppfSessionUtil.getSystemKind(request);		//system kind를 가져온다.
        if("".equals(OppfStringUtil.isNullToString(systemKind))){
            systemKind = "spt"; //회원가입단계는 로그인 상태가 아니므로 null값이 옵니다. 그 이외에는 system kind를 셋팅합니다.
        }
        if(!OppfStringUtil.isEmpty(cmmEmailSendVO.getSystemKind())){
        	systemKind = cmmEmailSendVO.getSystemKind();
        }
        
        String uriContextPath = request.getParameter("uriContextPath");
        log.debug("uriContextPath="+uriContextPath);
        if(!OppfStringUtil.isEmpty(uriContextPath)){
            cmmEmailSendVO.setUriContextPath(uriContextPath); //임시비밀번호
        }
        
        String GlobalsEmail = OppfProperties.getProperty("Globals.manager.email."+systemKind);
        String GlobalsEmailSendId = OppfProperties.getProperty("Globals.system.emailSendId."+systemKind);
        String GlobalsDomain = "http://" + OppfProperties.getProperty("Globals.domain."+systemKind);
        String GlobalsDomainName = OppfProperties.getProperty("Globals.domain."+systemKind + ".name");
        
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
        if("G016001".equals(div)){ //공통 템플릿
        	setAutomailIDEncrypt = OppfProperties.getProperty("Globals.automailAPI.automailIDEncrypt.G016001");
        	
            //어드민 메일 발송 이력 조회의 상세화면의 이메일컨텐츠를 관리자가 수정 후 메일발송을하는 경우 DB에서 컨텐츠를 가져오는게 아니라 화면에서 보내온 컨텐츠를 사용해야 합니다.
            if("Y".equals(cmmEmailSendVO.getManagerAdjYn())){                
                setMailContent = ManagerSetContent;
                //setAutomailIDEncrypt = OppfProperties.getProperty("Globals.automailAPI.automailIDEncrypt");
                setMailContent = OppfStringUtil.replace(setMailContent, "++[$receiverName]++", setReceiverName);
                setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomain]++", GlobalsDomain); //어드민포털에서 사용하므로 로그인 'apt'값이 무조건 옵니다.
                setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsEmail]++", GlobalsEmail);
                setMailContent = OppfStringUtil.replace(setMailContent, "++[$ManagerSetTitle]++", ManagerSetTitle); //관리자가 입력한 제목
                setMailContent = OppfStringUtil.replace(setMailContent, "++[$ManagerSetContent]++", ManagerSetContent); //관리자가 입력한 html 정보
                setMailContent = OppfStringUtil.replace(setMailContent, "++[$imgUrl]++", GlobalsDomain);
                setMailContent = OppfStringUtil.replace(setMailContent, "++[$imgUrl2]++", GlobalsDomain);
                cmmEmailSendVO.setMailTitle(ManagerSetTitle); //공통 템플릿은 제목을 DB에서 가져오지 않고 관리자가 APT에서 입력한 제목으로 합니다.
                setMailTitle = ManagerSetTitle; //이메일을 받는 사용자의 (naver등)메일 목록상의 타이틀자리에 들어갈 제목
            }else{
                //setAutomailIDEncrypt = OppfProperties.getProperty("Globals.automailAPI.automailIDEncrypt");
                setMailContent = OppfStringUtil.replace(setMailContent, "++[$receiverName]++", setReceiverName);
                setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomain]++", GlobalsDomain); //어드민포털에서 사용하므로 로그인 'apt'값이 무조건 옵니다.
                setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsEmail]++", GlobalsEmail);
                setMailContent = OppfStringUtil.replace(setMailContent, "++[$ManagerSetTitle]++", ManagerSetTitle); //관리자가 입력한 제목
                setMailContent = OppfStringUtil.replace(setMailContent, "++[$ManagerSetContent]++", ManagerSetContent); //관리자가 입력한 html 정보
                setMailContent = OppfStringUtil.replace(setMailContent, "++[$imgUrl]++", GlobalsDomain);
                setMailContent = OppfStringUtil.replace(setMailContent, "++[$imgUrl2]++", GlobalsDomain);
                cmmEmailSendVO.setMailTitle(ManagerSetTitle); //공통 템플릿은 제목을 DB에서 가져오지 않고 관리자가 APT에서 입력한 제목으로 합니다.
                setMailTitle = ManagerSetTitle; //이메일을 받는 사용자의 (naver등)메일 목록상의 타이틀자리에 들어갈 제목
            }
            
        }else if("G016002".equals(div)){ //회원 - 이메일 인증
            //setAutomailIDEncrypt = OppfProperties.getProperty("Globals.automailAPI.automailIDEncrypt");
        	setAutomailIDEncrypt = OppfProperties.getProperty("Globals.automailAPI.automailIDEncrypt.G016002");
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$receiverName]++", setReceiverName);
//            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomain]++", GlobalsDomain);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomain]++", cmmEmailSendVO.getUriContextPath());
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$customerRegNo]++", customerRegNo);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsEmail]++", GlobalsEmail);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomainName]++", GlobalsDomainName);
            cmmEmailSendVO.setSenderId(GlobalsEmailSendId);
            cmmEmailSendVO.setSendId(GlobalsEmailSendId);
            cmmEmailSendVO.setCreateId(GlobalsEmailSendId);
            cmmEmailSendVO.setUpdateId(GlobalsEmailSendId);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$imgUrl]++", GlobalsDomain);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$imgUrl2]++", GlobalsDomain);
            
        }else if("G016003".equals(div)){ //회원 - 회원가입 완료
            //setAutomailIDEncrypt = OppfProperties.getProperty("Globals.automailAPI.automailIDEncrypt");
        	setAutomailIDEncrypt = OppfProperties.getProperty("Globals.automailAPI.automailIDEncrypt.G016003");
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
            
        }else if("G016004".equals(div)){ // 회원 - 임시비밀번호 발급 안내
            //setAutomailIDEncrypt = OppfProperties.getProperty("Globals.automailAPI.automailIDEncrypt");
        	setAutomailIDEncrypt = OppfProperties.getProperty("Globals.automailAPI.automailIDEncrypt.G016004");
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$receiverName]++", setReceiverName);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$imsiPassword]++", customerPassword);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomain]++", cmmEmailSendVO.getUriContextPath());
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomain2]++", cmmEmailSendVO.getUriContextPath());
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsEmail]++", GlobalsEmail);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$GlobalsDomainName]++", GlobalsDomainName);
            
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$imgUrl]++", GlobalsDomain);
            setMailContent = OppfStringUtil.replace(setMailContent, "++[$imgUrl2]++", GlobalsDomain);
            
        }else if("G016005".equals(div)){ // 회원탈퇴 확인 메일
            //setAutomailIDEncrypt = OppfProperties.getProperty("Globals.automailAPI.automailIDEncrypt");
        	setAutomailIDEncrypt = OppfProperties.getProperty("Globals.automailAPI.automailIDEncrypt.G016005");
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
        	List<CmmEmailSendVO> termsList = cmmEmailSendDAO.selectCmmComEmailTermsList(cmmEmailSendVO);
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
        				CmmEmailSendVO data = (CmmEmailSendVO) termsList.get(i);
        				
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
        						CmmEmailSendVO rowspanData = (CmmEmailSendVO) termsList.get(x);
        						
        						if(data.getAppId().equals(rowspanData.getAppId())){
        							rowspanCnt++;
        						}
        					}
        					
        					termsHtml.append("<th rowspan='"+rowspanCnt+"' scope='row' style='font-weight:normal; color:#0c0c0c; border-bottom:1px solid #bec3c7; padding:8px;'>");
        					termsHtml.append(data.getAppName());
        					termsHtml.append("</th>");
        					
        					appId = data.getAppId();
        				}
        				
        				//증권사 -> 같은 핀테크 서비스에 증권사가 다를경우
        				if(appId.equals(data.getAppId()) && !pubcompanyCodeId.equals(data.getPubcompanyCodeId())){
        					int rowspanCnt = 0;
        					for(int x=0; x<termsList.size(); x++){
        						CmmEmailSendVO rowspanData = (CmmEmailSendVO) termsList.get(x);
        						
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
        	setAutomailIDEncrypt = OppfProperties.getProperty("Globals.automailAPI.automailIDEncrypt.G016006");
        	
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
        	//setAutomailIDEncrypt = OppfProperties.getProperty("Globals.automailAPI.automailIDEncrypt");            
        	setAutomailIDEncrypt = OppfProperties.getProperty("Globals.automailAPI.automailIDEncrypt.G016007");
        	
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
        	//setAutomailIDEncrypt = OppfProperties.getProperty("Globals.automailAPI.automailIDEncrypt");            
        	setAutomailIDEncrypt = OppfProperties.getProperty("Globals.automailAPI.automailIDEncrypt.G016008");
        	
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
    
    /**
     * @Method Name        : selectCmmComEmailSendInfoChk
     * @Method description : 이메일 발송이력을 조회해온다.
     * @param              : CmmEmailSendVO
     * @return             : String
     * @throws             : Exception
     */
    @Transactional
    public String selectCmmComEmailSendInfoChk(CmmEmailSendVO cmmEmailSendVO) throws Exception{
        String emailRegNo = cmmEmailSendDAO.selectCmmComEmailSendInfoChk(cmmEmailSendVO);
        
        return emailRegNo;        
    }
    
    /**
     * @Method Name        : selectCmmEmailCustomerInfo
     * @Method description : 사용자 정보를 조회한다.
     * @param              : CmmEmailSendVO
     * @return             : CmmEmailSendVO
     * @throws             : Exception
     */
    public CmmEmailSendVO selectCmmEmailCustomerInfo(CmmEmailSendVO cmmEmailSendVO) throws Exception{
    	return cmmEmailSendDAO.selectCmmEmailCustomerInfo(cmmEmailSendVO);
    }
    
    /**
     * @Method Name        : selectCronSvcTermsList
     * @Method description : 정보제공동의 메일발송 대상 목록 조회
     * @param              : CmmEmailSendVO
     * @return             : List<CmmEmailCronSvcTermsVO>
     * @throws             : Exception
     */
    public List<CmmEmailCronSvcTermsVO> selectCronSvcTermsList(CmmEmailCronSvcTermsVO cmmEmailCronSvcTermsVO) throws Exception{
    	return cmmEmailSendDAO.selectCronSvcTermsList(cmmEmailCronSvcTermsVO);
    }
}
