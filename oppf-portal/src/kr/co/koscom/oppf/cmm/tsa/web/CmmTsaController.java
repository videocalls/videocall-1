package kr.co.koscom.oppf.cmm.tsa.web;

import kr.co.koscom.oppf.cmm.service.CmmEmailSendService;
import kr.co.koscom.oppf.cmm.service.CmmEmailSendVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.tsa.service.CmmTsaService;
import kr.co.koscom.oppf.cmm.tsa.service.CmmTsaVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmTsaController.java
* @Comment  : [공통회원동의서TSA연계]정보관리를 위한 Controller 클래스
* @author   : 포털 이환덕
* @since    : 2016.06.09
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.09  이환덕        최초생성
*
*/
@Slf4j
@Controller
public class CmmTsaController {
	
    @Resource(name = "CmmTsaService")
    private CmmTsaService cmmTsaService;
    
    @Resource(name = "CmmEmailSendService")
    private CmmEmailSendService cmmEmailSendService;
    
    /**
     * @Method Name        : reqTsa
     * @Method description : 정보제공 동의 및 TSA처리 & DB 저장
     * @param              : CmmTsaVO
     * @return             : 
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/tsa/reqTerms.ajax",method = {RequestMethod.POST})
	public String reqTsa(@ModelAttribute("CmmTsaVO") CmmTsaVO paramVO, HttpServletRequest req, ModelMap model) throws Exception{
	    HashMap<String,Object> rsMap = cmmTsaService.procTsa(paramVO);
	    String rsCd = (String) rsMap.get("rsCd");
	    String rsCdMsg = (String) rsMap.get("rsCdMsg");
	    
	    //메일 발송
		if("00".equals(rsCd)){
			String customerName = paramVO.getCustomerName();
			String customerEmail = paramVO.getCustomerEmail();
			
			//고객정보 가져오기
			CmmEmailSendVO customerParamVO = new CmmEmailSendVO();
			customerParamVO.setCustomerRegNo(paramVO.getCustomerRegNo());
			
			//고객정보 조회
			CmmEmailSendVO customerVO = null;
			try{
				customerVO = cmmEmailSendService.selectCmmEmailCustomerInfo(customerParamVO);
				
				//고객정보가 있을경우에 처리
				if(customerVO != null && !OppfStringUtil.isEmpty(customerVO.getCustomerName())){
					customerName = customerVO.getCustomerName();
					customerEmail = customerVO.getCustomerEmail();
				}
				
			}catch(Exception e){
				log.debug("################정보제공 동의 email 발송  ###########################");
				log.debug("고객정보 조회 실패 -> parameter로 받아온 정보로 send");
				log.debug("customerRegNo : {} ", paramVO.getCustomerRegNo());
				log.debug("customerName : {} ", customerName);
				log.debug("customerEmail : {} ", customerEmail);
				log.debug("error : {} ", e);
	    		log.debug("###############################################################");
			}
			
			CmmEmailSendVO cmmEmailSendVO = new CmmEmailSendVO();
			cmmEmailSendVO.setEmailSendType("G016006"); //이메일 유형 - com_email_temp_info 이메일 템플릿 조회
			
			cmmEmailSendVO.setReceiverName(customerName); //받는 사람 이름(실제 이메일에 셋팅됩니다.)
			cmmEmailSendVO.setReceiverEmail(customerEmail); //받는 사람 이메일(실제 이메일에 셋팅되고, 이 주소로 이메일이 발송됩니다.)			
			cmmEmailSendVO.setReceiverKind("G018001"); //수신자 종류 - G018001:일반사용자, G018002:기업사용자
			cmmEmailSendVO.setReceiverId(paramVO.getCustomerRegNo()); //수신자 ID
			
			cmmEmailSendVO.setSenderKind("G017001"); //발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
			cmmEmailSendVO.setSenderId(paramVO.getCustomerRegNo()); //발신자 ID
						
			cmmEmailSendVO.setSendId(paramVO.getCustomerRegNo()); //최초 발신자 ID
			cmmEmailSendVO.setCreateId(paramVO.getCustomerRegNo()); //생성자ID
			cmmEmailSendVO.setUpdateId(paramVO.getCustomerRegNo()); //수정자ID
						
			String uriContextPath = "https://";
			String sptServerName = OppfProperties.getProperty("Globals.domain.spt");
			if(sptServerName.indexOf(":") >= 0){
				String [] tmpStr = sptServerName.split(":");
				
				uriContextPath += tmpStr[0] + ":" + OppfProperties.getProperty("Globals.sslPort.spt");
			}else{
				uriContextPath += sptServerName;
			}		
			cmmEmailSendVO.setUriContextPath(uriContextPath); //로그인 페이지 : https
			
			cmmEmailSendVO.setSystemKind("spt");//개인 포털로 셋팅[spt:개인, cpt:기업, apt:admin] -> session이 아닌 target이 있을 경우 셋팅
			
			cmmEmailSendVO.setCustomerRegNo(paramVO.getCustomerRegNo());	//회원 등록 번호 
			cmmEmailSendVO.setTermsRegNo(paramVO.getTermsRegNo());			//약관 동의 번호
			
			//1.이메일 템플릿 조회 -> 2.이메일발송 유형 구분 -> 3.메일 발송 -> (성공시) -> 4.com_email_send_info 로그적재 관련
			CmmEmailSendVO eamilResultVO = cmmEmailSendService.cmmEmailSend(cmmEmailSendVO, req);
			if("100".equals(eamilResultVO.getMailCode())){ //데이터 연동 성공
				model.addAttribute("mailResult", "0");
			}else{
				model.addAttribute("mailResult", "-1");
			}
			
		}	
	    
		model.addAttribute("rsCd", rsCd);
		model.addAttribute("rsCdMsg", rsCdMsg);
		return "jsonView";
	}
	
	/**
     * @Method Name        : deleteFolderFile
     * @Method description : 정보제공 동의 및 TSA file 삭제
     * @param              : CmmTsaVO
     * @return             : 
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/tsa/deleteFolderFile.ajax",method = {RequestMethod.POST})
	public String deleteFolderFile(@ModelAttribute("CmmTsaVO") CmmTsaVO paramVO, HttpServletRequest req, ModelMap model) throws Exception{
	    
	    String customerRegNo = paramVO.getCustomerRegNo();
	    if(OppfStringUtil.isEmpty(customerRegNo)){
	        model.addAttribute("rsCd", "01");
	        model.addAttribute("rsCdMsg", "customerRegNo 폴더를 찾을 수 없습니다.customerRegNo="+customerRegNo);
	        return "jsonView";
	    }
	    
	    //12.삭제작업(폴더안의 폴더 또는 파일 삭제)
	    Date today = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    String delfolderPath = OppfProperties.getProperty("Globals.spt.webcontent.path")+"/upload/tmpTsa/"+sdf.format(today)+"/"+paramVO.getCustomerRegNo()+"/";
        log.debug("12.삭제작업(폴더안의 폴더 또는 파일 삭제):delfolderPath : {} ", delfolderPath);
        cmmTsaService.rmDirTsa(delfolderPath);
	    
	    model.addAttribute("rsCd", "00");
	    model.addAttribute("rsCdMsg", "정상적으로 삭제완료 되었습니다.");
	    return "jsonView";
	}
	
}
