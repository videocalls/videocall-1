package kr.co.koscom.oppf.cmm.ars.web;

import kr.co.koscom.oppf.cmm.IntegratedAccount.service.CmmSIFInternalService;
import kr.co.koscom.oppf.cmm.ars.service.CmmArsService;
import kr.co.koscom.oppf.cmm.ars.service.CmmArsVO;
import kr.co.koscom.oppf.cmm.service.CmmEmailSendService;
import kr.co.koscom.oppf.cmm.service.CmmEmailSendVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축 2차
* @FileName : CmmArsController.java
* @Comment  : [공통회원동의서ARS연계]정보관리를 위한 Controller 클래스
* @author   : 포털 이희태
* @since    : 2017.03.04
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2017.03.04  이희태        최초생성
*
*/
@Slf4j
@Controller
public class CmmArsController {
	
    @Resource(name = "CmmArsService")
    private CmmArsService cmmArsService;
    
    @Resource(name = "CmmEmailSendService")
    private CmmEmailSendService cmmEmailSendService;

	@Resource(name = "CmmSIFInternalService")
	private CmmSIFInternalService cmmSIFInternalService;

	private static final String AUTH_INQUERY = "안녕하세요 코스콤입니다. ÐcustomerName 님이 보유하신 "
			+"ÐrsPubcompanyList 의 금융 거래 정보를 ÐtermsEndDate 까지 "
			+"ÐsubcompanyName에 제공하는 것에 대해 동의하시겠습니까";

	/**
	 * @Method Name        : svcTermConsntArs
	 * @Method description : [핀테크서비스신청:정보제공동의팝업]페이지로 이동한다.
	 * @param              : SvcApplVO,ModelMap
	 * @return             : String
	 * @throws             : Exception
	 */
	@RequestMapping(value="/usr/svcAppl/svcTermConsntArsPopup.do",method = {RequestMethod.POST})
	private String svcTermConsntArs(@ModelAttribute("CmmArsVO") CmmArsVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
//		String modelView = "spt/usr/svcAppl/svcTermConsntArsPopup";
		String modelView = "spt/usr/svcAppl/appTermsArsPopup";

		//1.로그인관련
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if(loginVO == null){
			return modelView;
		}

		String customerRegNo = loginVO.getCustomer_reg_no();
		if(OppfStringUtil.isEmpty(customerRegNo)){
			if(OppfStringUtil.isEmpty(request.getParameter("customerRegNo"))){
				return modelView;
			}else{
				customerRegNo = request.getParameter("customerRegNo");
			}
		}
		//전화번호 마스크처리
		paramVO.setIncodingCustomerPhone(OppfStringUtil.maskedPhoneNum(paramVO.getCustomerPhone()));

		//1.로그인관련
		String customerId = loginVO.getId();
		model.addAttribute("customerId", customerId);
		model.addAttribute("customerRegNo", customerRegNo);
		// ARS 진행 스텝
		paramVO.setArsStep("1");
		// ARS 회원정보
		model.addAttribute("paramVO", paramVO);

		return modelView;
	}

	/**
	 * @Method Name        : arsRequest
	 * @Method description : ars 전화요청
	 * @param              : CmmArsVO
	 * @return             :
	 * @throws             : Exception
	 */
	@RequestMapping(value="/cmm/ars/arsRequest.ajax",method = {RequestMethod.POST})
	public String arsRequest(@ModelAttribute("CmmArsVO") CmmArsVO paramVO, HttpServletRequest req, ModelMap model) throws Exception{
		HttpHeaders httpHeaders = new HttpHeaders();
		String apiUrl = OppfProperties.getProperty("Globals.ars.url");
		String secretKey = OppfProperties.getProperty("Globals.ars.secretKey");
		String trCd = OppfProperties.getProperty("Globals.ars.trcd");
		String orgCd = OppfProperties.getProperty("Globals.ars.orgcd");
		String date = new SimpleDateFormat("yyyyMMddkkmmss").format(new Date());
		String phone = paramVO.getCustomerPhone().replaceAll("-","");
		String rsPubcompanyList = paramVO.getRsPubcompanyList().replaceAll("_"," ");
		String authInquery = OppfStringUtil.replace(AUTH_INQUERY, "ÐcustomerName", paramVO.getCustomerName());
		authInquery = OppfStringUtil.replace(authInquery, "ÐrsPubcompanyList", rsPubcompanyList);
		authInquery = OppfStringUtil.replace(authInquery, "ÐtermsEndDate", paramVO.getTermsEndDate());
		authInquery = OppfStringUtil.replace(authInquery, "ÐsubcompanyName", paramVO.getSubcompanyName());
		String authNo = OppfStringUtil.getRandomStr('0','9') + OppfStringUtil.getRandomStr('0','9');
		String decodePayload = "{ \n" +
				"\"SECR_KEY\" : \""+secretKey+"\",\n" +
				"\"TR_CD\" : \""+trCd+"\",\n" +
				"\"ORG_CD\" : \""+orgCd+"\",\n" +
				"\"DATE\" : \""+date+"\",\n" +
				"\"PHONE\" : \""+phone+"\",\n" +
				"\"AUTH_INQUERY\" : \""+authInquery+"\",\n" +
				"\"AUTH_NO\" : \""+authNo+"\",\n" +
				"\"FILE_SAVE_YN\" : \"N\"\n" +
				"} ";
		String encodePayload = URLEncoder.encode(decodePayload, "UTF-8");
		log.debug("payload : {} ", decodePayload);
		log.debug("encodePayload : {} ", encodePayload);

		String payload = "JSONData="+encodePayload;

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("JSONData", encodePayload);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, httpHeaders);
		RestTemplate rest = new RestTemplate();
		try{
			String result = rest.postForObject(new URI(apiUrl), request, String.class);
			log.debug("++++++++++++++++++++++++++++++++++++ ARS Interface response  : {} ", result );
			JSONObject jsonObject = new JSONObject(result);
			JSONArray  jsonArray = jsonObject.getJSONArray("RESP_DATA");
			//처리결과코드
			model.addAttribute("arsRsltCd", jsonObject.getString("RSLT_CD"));
			//결과메세지
			model.addAttribute("arsRsltMsg", jsonObject.getString("RSLT_MSG"));
			//거래코드
			model.addAttribute("arsTrCd", jsonArray.getJSONObject(0).getString("TR_CD"));
			//전문고유번호
			model.addAttribute("arsTxtNo", jsonArray.getJSONObject(0).getString("TXT_NO"));
			//녹취데이터
			model.addAttribute("arsRecordData", jsonArray.getJSONObject(0).getString("RECORD_DATA"));
		}catch (Exception e){
			log.debug("++++++++++++++++++++++++++++++++++++ ARS Interface Fail : {} ", e.getMessage() );
			//내부통신오류
			model.addAttribute("arsRsltCd", "9980");
		}

		return "jsonView";
	}

    /**
     * @Method Name        : reqTerms
     * @Method description : 정보제공 동의 및 ARS처리 & DB 저장
     * @param              : CmmArsVO
     * @return             :
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/ars/reqTerms.ajax",method = {RequestMethod.POST})
	public String reqTerms(@ModelAttribute("CmmArsVO") CmmArsVO paramVO, HttpServletRequest req, ModelMap model) throws Exception{
	    HashMap<String,Object> rsMap = cmmArsService.procArs(paramVO);
	    String rsCd = (String) rsMap.get("rsCd");
	    String rsCdMsg = (String) rsMap.get("rsCdMsg");
		model.addAttribute("rsCd", rsCd);
		model.addAttribute("rsCdMsg", rsCdMsg);

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

		return "jsonView";
	}
}
