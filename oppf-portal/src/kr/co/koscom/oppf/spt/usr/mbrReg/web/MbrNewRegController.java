package kr.co.koscom.oppf.spt.usr.mbrReg.web;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import CheckPlus.nice.MCheckPlus;
import kr.co.koscom.oppf.cmm.phone.service.CmmPhoneNumberCheckService;
import kr.co.koscom.oppf.cmm.phone.service.CmmPhoneNumberCheckVO;
import kr.co.koscom.oppf.cmm.service.CmmEmailSendService;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.CmmNiceIpinCheckplusService;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegTermsContentVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegTermsVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.NiceReqVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.NiceResVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.impl.MbrRegDAO;
import lombok.extern.slf4j.Slf4j;


/**
 * @Project : 자본시장 공동 핀테크 오픈플랫폼 구축
 * @FileName : MbrNewRegController.java
 * @Comment : [회원가입]정보관리를 위한 Controller 클래스
 * @author : 포털 이환덕
 * @since : 2016.05.02
 * @version : 1.0
 * @see
 *
 * 		<< 개정이력(Modification Information) >> 수정일 수정자 수정내용 ----------- ------
 *      ---------- 2016.05.02 이환덕 최초생성
 *
 */
@Slf4j
@Controller
public class MbrNewRegController {

	@Resource(name = "CmmFuncService")
	private CmmFuncService cmmFuncService;

	@Resource(name = "MbrRegService")
	private MbrRegService mbrRegService;

	@Resource(name = "CmmEmailSendService")
	private CmmEmailSendService cmmEmailSendService;

	@Resource(name = "CmmNiceIpinCheckplusService")
	private CmmNiceIpinCheckplusService cmmNiceIpinCheckplusService;

	@Resource(name = "MbrRegDAO")
	private MbrRegDAO mbrRegDAO;
	
	private NiceReqVO m_niceReqVO;
	private NiceResVO m_niceResVO;

	@Value("0")
	private int m_iReturn;

	// 본인확인 보안 강화 - 본 번호 체크
	@Resource(name = "CmmPhoneNumberCheckService")
	private CmmPhoneNumberCheckService cmmPhoneNumberCheckService;

	/* 동기식.do 요청 START */

	/**
	 * @Method Name : mbrRegReg
	 * @Method description : [회원가입]약관동의 페이지로 이동한다.
	 * @param :
	 *            MbrRegVO,ModelMap
	 * @return : String
	 * @throws :
	 *             Exception
	 */
	@RequestMapping(value = "/usr/mbrReg/mbrNewReg.do", method = { RequestMethod.POST, RequestMethod.GET })
	private String mbrReg(@ModelAttribute("mbrRegVO") MbrRegVO paramVO, HttpServletRequest request, ModelMap model)
			throws Exception {

		CmmFuncVO cmmFuncVO = new CmmFuncVO();
		MbrRegVO resultIpinVO = new MbrRegVO();
		MbrRegVO resultMobileVO = new MbrRegVO();

		// 5-1-3-1.약관목록취득(G008001:서비스이용약관, G008002:개인정보취급방침, G008003:개인정보수집이용동의,
		// G008010:공인인증서등록및약관동의)
		MbrRegTermsContentVO pMbrRegTermsContentVO = new MbrRegTermsContentVO();
		List<String> searchTempList = new ArrayList<String>();
		searchTempList.add("G008001");
		searchTempList.add("G008002");
		searchTempList.add("G008003");
		pMbrRegTermsContentVO.setSearchCustomerTermsTypeList(searchTempList);
		List<MbrRegTermsContentVO> termsContentList = mbrRegService
				.selectSptCustomerTermsContentList(pMbrRegTermsContentVO);

		model.addAttribute("termsContentList", termsContentList);

		model.addAttribute("paramVO", paramVO);

		return "spt/usr/mbrReg/mbrJoin1";

	}

	/**
	 * @Method Name : mbrRegReg
	 * @Method description : [회원가입]페이지로 이동한다.
	 * @param :
	 *            MbrRegVO,ModelMap
	 * @return : String
	 * @throws :
	 *             Exception
	 */
	@RequestMapping(value = "/usr/mbrReg/mbrNewReg2.do", method = { RequestMethod.POST, RequestMethod.GET })
	private String mbrReg2(@ModelAttribute MbrRegVO paramVO, HttpServletRequest request, ModelMap model)
			throws Exception {

		// 회원가입 스텝별 유효성 검증
		// 1.필터링-회원CI검증값
		String customerVerify = (String) paramVO.getCustomerVerify();
		log.debug("1.필터링-회원CI검증값 : customerVerify = {}", customerVerify);
		if (OppfStringUtil.isEmpty(customerVerify)) {
			return "redirect:/usr/mbrReg/mbrNewReg.do";
		}
		// 회원가입 스텝별 유효성 검증

		CmmFuncVO cmmFuncVO = new CmmFuncVO();
		// 셋팅 공통코드:이메일
		cmmFuncVO = new CmmFuncVO();
		cmmFuncVO.setSystem_grp_code("G013");// 이메일
		List<CmmFuncVO> emailList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
		model.addAttribute("emailList", emailList);
		if (!OppfStringUtil.isEmpty(paramVO.getCustomerEmail())) {

			String[] email = paramVO.getCustomerEmail().split("@");

			String email1 = email[0];
			String email2 = email[1];

			paramVO.setEmail1(email1);
			paramVO.setEmail2(email2);

		}

		model.addAttribute("paramVO", paramVO);

		return "spt/usr/mbrReg/mbrJoin2";

	}

	/**
	 * @Method Name : mbrNewReg2
	 * @Method description : [회원가입:2.개인정보입력]정보를 저장을 한다.
	 * @param :
	 *            MbrRegVO,ModelMap
	 * @return : jsonView
	 * @throws :
	 *             Exception
	 */
	@RequestMapping(value = "/usr/mbrReg/mbrNewReg2.ajax", method = { RequestMethod.POST, RequestMethod.GET })
	private String mbrNewReg2(@RequestBody MbrRegVO paramVO, ModelMap model) throws Exception {

		log.info("------------- saveMbrReg4.ajax START -------------------");

		MbrRegTermsContentVO pMbrRegTermsContentVO = new MbrRegTermsContentVO();
		List<String> searchTempList = new ArrayList<String>();
		searchTempList.add("G008001");
		searchTempList.add("G008002");
		searchTempList.add("G008003");
		pMbrRegTermsContentVO.setSearchCustomerTermsTypeList(searchTempList);
		List<MbrRegTermsVO> termsContentList = mbrRegService.selectCustomerTermsContentList(pMbrRegTermsContentVO);

		String customerNameKor = paramVO.getCustomerNameKor();
		log.debug("customerNameKor = {}", customerNameKor);
		paramVO.setCustomerTermsList(termsContentList);
		
		// 회원 가입전 휴대폰 본인확인 이름/전화번호/CI 값이 일치 하지 않으면 가입 처리 불가
		log.debug("휴대폰 본인확인 결과 코드( 정상 : 0000 ) : m_niceResVO.returnCode = {} ", m_niceResVO.getReturnCode());
		log.debug("휴대폰 번호 확인 (before) : {} ", m_niceReqVO.getSmobileNo());
		log.debug("휴대폰 번호 확인 (after) : {} ", paramVO.getCustomerPhone());		
		log.debug("휴대폰 사용자 이름 (before) : {} ", m_niceReqVO.getSname());
		log.debug("휴대폰 사용자 이름 (after) : {} ", paramVO.getCustomerNameKor());
		log.debug("휴대폰 사용자 CI (before) : {} ", m_niceResVO.getResponseCI() );
		log.debug("휴대폰 사용자 CI (after) : {} ", paramVO.getCustomerVerify() );				
	
		
		String customerRegNo = "";
		if (m_niceResVO.getReturnCode().equals("0000")				
				&& m_niceReqVO.getSname().equals(paramVO.getCustomerNameKor())
				&& m_niceResVO.getResponseCI().equals(paramVO.getCustomerVerify()) )
		{
			customerRegNo = mbrRegService.createMember(paramVO);
		}
		else
		{
			log.error("휴대폰 본인인증 실패 - 입력과 일치하지 않음");
		}

		model.addAttribute("customerRegNo", customerRegNo);
		model.addAttribute("customerNameKor", customerNameKor);
		model.addAttribute("paramVO", paramVO);
		log.info("------------- saveMbrReg4.ajax END ---------------------");
		return "jsonView";
	}

	/**
	 * @Method Name : mbrRegReg
	 * @Method description : [회원가입]가입완료 페이지로 이동한다.
	 * @param :
	 *            MbrRegVO,ModelMap
	 * @return : String
	 * @throws :
	 *             Exception
	 */
	@RequestMapping(value = "/usr/mbrReg/mbrNewReg3.do", method = { RequestMethod.POST, RequestMethod.GET })
	private String mbrReg3(@ModelAttribute MbrRegVO paramVO, HttpServletRequest request, ModelMap model)
			throws Exception {

		// 회원가입 스텝별 유효성 검증

		// 2.필터링-회원 이메일 입력 확인
		String customerId = (String) paramVO.getCustomerId();
		log.debug("2.필터링-회원 아이디 입력 확인 : customerId = {} ", customerId);
		String customerEmail = (String) paramVO.getCustomerEmail();
		log.debug("3.필터링-회원 이메일 입력 확인 : customerEmail = {} ", customerEmail);
		if (OppfStringUtil.isEmpty(customerId) | OppfStringUtil.isEmpty(customerEmail)) {
			return "redirect:/usr/mbrReg/mbrNewReg2.do";
		}
		// 회원가입 스텝별 유효성 검증

		model.addAttribute("paramVO", paramVO);

		return "spt/usr/mbrReg/mbrJoin3";

	}

	/**
	 * @Method Name : mobileCheckAuth
	 * @Method description : [회원가입]휴대폰 인증 페이지로 이동한다.
	 * @param :
	 *            MbrRegVO,ModelMap
	 * @return : String
	 * @throws :
	 *             Exception
	 */
	@RequestMapping(value = "/usr/mbrReg/mobileCheckAuth.do", method = { RequestMethod.POST, RequestMethod.GET })
	private String mobileCheckAuth(@ModelAttribute("mbrRegVO") MbrRegVO paramVO, HttpServletRequest request,
			ModelMap model) throws Exception {

		// 셋팅 공통코드:통신사 코드
		CmmFuncVO cmmFuncVO = new CmmFuncVO();
		cmmFuncVO = new CmmFuncVO();
		cmmFuncVO.setSystem_grp_code("G044");// 통신사
		List<CmmFuncVO> mobileCompList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
		model.addAttribute("mobileCompList", mobileCompList);

		Map<String, Object> map = mbrRegService.selectMobileTermsList();

		model.addAttribute("skTermsList", map.get("skTermsList"));
		model.addAttribute("ktTermsList", map.get("ktTermsList"));
		model.addAttribute("lgTermsList", map.get("lgTermsList"));

		return "spt/usr/mbrReg/mobileCheckAuth";

	}

	/**
	 * @Method Name : getMobileTerms
	 * @Method description : [회원가입]통신사 약관을 조회한다. 이동한다.
	 * @param :
	 *            MbrRegVO,ModelMap
	 * @return : String
	 * @throws :
	 *             Exception
	 */
	@RequestMapping(value = "/usr/mobileTerms", method = { RequestMethod.GET })
	private String getMobileTerms(HttpServletRequest request, ModelMap model) throws Exception {

		Map<String, Object> map = mbrRegService.selectMobileTermsList();

		model.addAttribute("skTermsList", map.get("skTermsList"));
		model.addAttribute("lgTermsList", map.get("lgTermsList"));
		model.addAttribute("lgTermsList", map.get("lgTermsList"));

		return "jsonView";
	}

	/**
	 * @Method Name : saveMbrRegChk
	 * @Method description : [회원가입:본인인증] 본인인증 값으로 기존에 가입된 여부를 확인합니다.
	 * @param :
	 *            MbrRegVO,ModelMap
	 * @return : jsonView
	 * @throws :
	 *             Exception
	 */
	@RequestMapping("/usr/mbrReg/chkMbrCiChk.ajax")
	private String chkMbrCiChk(@ModelAttribute("mbrRegVO") MbrRegVO paramVO, ModelMap model) throws Exception {
		
		model.addAttribute("returnVO", -1);
		// 휴대폰 본인인증 결과 코드 & 입력한 휴대폰 전화번호 & 입력한 이름 정합성 확인
		if (m_niceResVO.getReturnCode().equals("0000")
				&& m_niceReqVO.getSmobileNo().equals(paramVO.getCustomerPhone()) 
				&& m_niceReqVO.getSname().equals(paramVO.getCustomerNameKor()))
		{
					
			log.info("------------- saveMbrRegChk.ajax START -------------------");
			int withDrawCnt = mbrRegService.selectSptWithDrawCustomerVerifyProfile(paramVO);
			model.addAttribute("withDrawCnt", withDrawCnt);
	
			MbrRegVO returnVO = mbrRegService.selectSptCustomerVerifyProfile(paramVO);
			model.addAttribute("returnVO", returnVO);
			log.info("------------- saveMbrRegChk.ajax END ---------------------");
			
		}

		return "jsonView";
	}

	/**
     * requestSafeAuth
     * 인증번호 전송
     * @param niceReq
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/requestSafeAuth.ajax", method = RequestMethod.POST)
  	public String requestSafeAuth(NiceReqVO niceReqVO,HttpServletRequest request, ModelMap model) throws Exception {
		
		String sCPRequest		= "";
		
		m_niceReqVO  = new NiceReqVO();
		m_niceReqVO.setSmobileNo(niceReqVO.getSmobileNo());			// 휴대폰 번호
		m_niceReqVO.setSname(niceReqVO.getSname());					// 이름
		
				
		// 객체 생성
		MCheckPlus cpMobile = new MCheckPlus();

		// 주민등록 번호 앞자리 (6자리 추출)
		String sJumin = niceReqVO.getBirthDay().substring(2);
		// 2000년대생 구분
		String yearGbn = niceReqVO.getBirthDay().substring(0, 1);
		String jumin2 = "";
		// Y면 내국인
		if("Y".equals(niceReqVO.getNationality())){
			// 1800년대생
			if("18".equals(niceReqVO.getBirthDay().substring(0, 2))){
				// M이면 남자
				if("M".equals(niceReqVO.getSex())){
					sJumin = sJumin + "9";
					jumin2="9";
				} else {
					//F면 여자
					sJumin = sJumin + "0";
					jumin2="0";
				}
			} else {
				//1900 년대 생
				if("1".equals(yearGbn)){
					// M이면 남자
					if("M".equals(niceReqVO.getSex())){
						sJumin = sJumin + "1";
						jumin2="1";
					} else {
						//F면 여자
						sJumin = sJumin + "2";
						jumin2="2";
					}
				} else { // 2000년대생
					// M이면 남자
					if("M".equals(niceReqVO.getSex())){
						sJumin = sJumin + "3";
						jumin2="3";
					} else {
						//F면 여자
						sJumin = sJumin + "4";
						jumin2="4";
					}
				}
			}
		} else {
			//1900 년대 생
			if("1".equals(yearGbn)){
				// M이면 남자
				if("M".equals(niceReqVO.getSex())){
					sJumin = sJumin + "5";
					jumin2="5";
				} else {
					//F면 여자
					sJumin = sJumin + "6";
					jumin2="6";
				}
			} else { // 2000년대생
				// M이면 남자
				if("M".equals(niceReqVO.getSex())){
					sJumin = sJumin + "7";
					jumin2="7";
				} else {
					//F면 여자
					sJumin = sJumin + "8";
					jumin2="8";
				}
			}	
		}


		
		//주민번호 뒤번호 앞자리 가져오기
		String jumin1 = "031001";
		//String jumin2 = "3";
		String regYear = jumin2.substring(0,1);
		  
		String teen_year="";
		        
		             
		if ("3478".indexOf(regYear)!=-1){ //주민번호 뒷자리 첫번째가 3또는 4또는 7또는 8 이라면(7, 8은 우리나라 외국인 거주자)
			teen_year = "20" + jumin1;
    	} else if("1256".indexOf(regYear)!=-1){ 
			teen_year = "19" + jumin1;
    	} else {
    		teen_year="";
    	}
  	  
		if(!"".equals(teen_year)){
	  	  	int intJumin = Integer.parseInt(teen_year); //회원의 날짜
	  	     
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("ko", "KOREA"));
			Calendar cal = Calendar.getInstance();
			cal.add(1, -14);
			String strDate = formatter.format(cal.getTime());
			int intDate = Integer.parseInt(strDate); //14년 전의 날짜
			   			
			if(intJumin > intDate){
				model.addAttribute("iReturn", "9127");
			}
		}

        String siteCode = OppfProperties.getProperty("Globals.nice.checkplus.siteCode"); //NICE로부터 부여받은 사이트 코드
        String sitePw = OppfProperties.getProperty("Globals.nice.checkplus.sitePw"); //NICE로부터 부여받은 사이트 패스워드

 
		int iReturn = 0;        
        
		try
		{
	        // 본인확인 보안 강화 - 본 번호 체크
	        // 1. 등록된 전화번호 확인
	        CmmPhoneNumberCheckVO pCmmPhoneNumberCheckVO = new CmmPhoneNumberCheckVO();
	        pCmmPhoneNumberCheckVO.setPhoneNumber(niceReqVO.getSmobileNo());
	        pCmmPhoneNumberCheckVO = cmmPhoneNumberCheckService.selectCmmPhoneNumberCheck(pCmmPhoneNumberCheckVO);
	        
	        // 2. 전화번호 등록
	        if (pCmmPhoneNumberCheckVO == null)
	        {
	        	pCmmPhoneNumberCheckVO = new CmmPhoneNumberCheckVO();
	        	pCmmPhoneNumberCheckVO.setPhoneNumber(niceReqVO.getSmobileNo());
	        	String regNo = cmmPhoneNumberCheckService.insertCmmPhoneNumberCheck(pCmmPhoneNumberCheckVO);
	        }
	        // 3. 전화번호 호출 건수 체크
	        else
	        {
	        	// 4. 1분에 5건 이상 호출시
	        	pCmmPhoneNumberCheckVO.setPhoneNumber(niceReqVO.getSmobileNo());
	        	cmmPhoneNumberCheckService.updateCmmPhoneNumberCheck(pCmmPhoneNumberCheckVO);
	        	
	        	if (pCmmPhoneNumberCheckVO.getIncreaseYn().equals("Y") && pCmmPhoneNumberCheckVO.getCnt() > 5)
	        	{
	        		//	error : 휴대폰 본인인증 서비스를 1분에 10회 이상을 초과 할 수 없습니다.
	        		iReturn = -99;
	        	}
	        }
   		} catch (Exception e) {
    		log.debug("휴대폰 본인확인 호출 건수 체크{}", e);
    	}        
		
        	if (iReturn == 0)
        		iReturn = cpMobile.fnRequestSafeAuth(siteCode, sitePw, sJumin, URLEncoder.encode(niceReqVO.getSname(), "euc-kr"),niceReqVO.getSmobileCo(), niceReqVO.getSmobileNo(), niceReqVO.getScpRequest());
		  
			// Method 결과값에 따른 처리사항
			if (iReturn == 0)
			{

				log.debug("RETURN_CODE = {}" , cpMobile.getReturnCode());              // 인증결과코드
				log.debug("REQ_SEQ = {} ", cpMobile.getRequestSEQ());                  // 요청 고유번호
				log.debug("RES_SEQ = {} ", cpMobile.getResponseSEQ());                 // 응답 고유번호

				
				NiceResVO niceRes = new NiceResVO();
				niceRes.setSresSeq(cpMobile.getResponseSEQ());
				niceRes.setReturnCode(cpMobile.getReturnCode());

				model.addAttribute("niceRes", niceRes);
				
			}
			else if (iReturn == -7 || iReturn == -8)
			{
				log.debug("AUTH_ERROR=" + iReturn);
				log.debug("서버 네트웍크 및 방확벽 관련하여 아래 IP와 Port를 오픈해 주셔야 이용 가능합니다.");
				log.debug("IP : 121.131.196.200 / Port : 3700 ~ 3715");

				model.addAttribute("iReturn", iReturn);
				
			}
			else if (iReturn == -9 || iReturn == -10 || iReturn == 12)
			{
				log.debug("AUTH_ERROR = {} ", iReturn);
				log.debug("입력값 오류 : fnRequest 함수 처리시, 필요한 6개의 파라미터값의 정보를 정확하게 입력해 주시기 바랍니다.");

				model.addAttribute("iReturn", iReturn);
				
			}
			else if (iReturn == -99)
			{
				log.debug("AUTH_ERROR = {} ", iReturn);
				log.debug("인증 횟수 초과 : 휴대폰 본인인증 서비스를 1분에 5회 이상 초과 할 수 없습니다.");

				model.addAttribute("iReturn", iReturn);				
			}
			else
			{
				log.debug("AUTH_ERROR = {}", iReturn);
				log.debug("iReturn 값 확인 후, NICE평가정보 전산 담당자에게 문의해 주세요.");

				model.addAttribute("iReturn", iReturn);
				
			}
			

				
	    return "jsonView";
	}

	/**
	 * requestConfirm 인증번호 확인
	 * 
	 * @param niceReqVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/requestConfirm.ajax", method = RequestMethod.POST)
	public String requestConfirm(NiceReqVO niceReq, HttpServletRequest request, ModelMap model) throws Exception {

		// 객체 생성
		MCheckPlus cpMobile = new MCheckPlus();

		int iReturn = -1;

		String siteCode = OppfProperties.getProperty("Globals.nice.checkplus.siteCode"); // NICE로부터
																							// 부여받은
																							// 사이트
																							// 코드
		String sitePw = OppfProperties.getProperty("Globals.nice.checkplus.sitePw"); // NICE로부터
																						// 부여받은
																						// 사이트
																						// 패스워드

		iReturn = cpMobile.fnRequestConfirm(siteCode, sitePw, niceReq.getSresSeq(), niceReq.getSauthNo(),
				niceReq.getScpRequest());
		
		m_iReturn = iReturn; 
		m_niceResVO = new NiceResVO();
		
		if (iReturn == 0) {
			log.debug("RETURN_CODE = {}", cpMobile.getReturnCode()); // 응답코드
			log.debug("CONFIRM_DATETIME = {}", cpMobile.getConfirmDateTime()); // 인증
																				// 완료시간
			log.debug("REQ_SEQ = {}", cpMobile.getRequestSEQ()); // 요청 고유번호
			log.debug("RES_SEQ = {}", cpMobile.getResponseSEQ()); // 응답 고유번호
			log.debug("RES_CI = {}", cpMobile.getResponseCI()); // 아이핀 연결정보(CI)
			log.debug("RES_DI = {}", cpMobile.getResponseDI()); // 아이핀
																// 중복가입확인정보(DI)

			NiceResVO niceRes = new NiceResVO();
			niceRes.setReturnCode(cpMobile.getReturnCode());
			niceRes.setConfirmDateTime(cpMobile.getConfirmDateTime());
			niceRes.setRequestSEQ(cpMobile.getRequestSEQ());
			niceRes.setResponseSEQ(cpMobile.getResponseSEQ());
			niceRes.setResponseCI(cpMobile.getResponseCI());
			niceRes.setResponseDI(cpMobile.getResponseDI());
			
			m_niceResVO.setReturnCode(cpMobile.getReturnCode());
			m_niceResVO.setConfirmDateTime(cpMobile.getConfirmDateTime());
			m_niceResVO.setRequestSEQ(cpMobile.getRequestSEQ());
			m_niceResVO.setResponseSEQ(cpMobile.getResponseSEQ());
			m_niceResVO.setResponseCI(cpMobile.getResponseCI());
			m_niceResVO.setResponseDI(cpMobile.getResponseDI());
			
			model.addAttribute("niceRes", niceRes);
			
		} else if (iReturn == -7 || iReturn == -8) {
			log.debug("AUTH_ERROR=" + iReturn);
			log.debug("서버 네트웍크 및 방확벽 관련하여 아래 IP와 Port를 오픈해 주셔야 이용 가능합니다.");
			log.debug("IP : 121.131.196.200 / Port : 3700 ~ 3715");

			model.addAttribute("iReturn", iReturn);
		} else if (iReturn == -9 || iReturn == -10 || iReturn == 12) {
			log.debug("AUTH_ERROR = {}", iReturn);
			log.debug("입력값 오류 : fnRequest 함수 처리시, 필요한 5개의 파라미터값의 정보를 정확하게 입력해 주시기 바랍니다.");

			model.addAttribute("iReturn", iReturn);
		} else {
			log.debug("AUTH_ERROR = {}", iReturn);
			log.debug("iReturn 값 확인 후, NICE평가정보 전산 담당자에게 문의해 주세요.");

			model.addAttribute("iReturn", iReturn);
		}

		return "jsonView";
	}

	/**
	 * @Method Name : chkMbrCiChkForUpdate
	 * @Method description : [마이페이지:본인인증] 본인인증 값으로 회원 정보를 수정한다.
	 * @param :
	 *            MbrRegVO,ModelMap
	 * @return : jsonView
	 * @throws :
	 *             Exception
	 */
	@RequestMapping("/usr/mbrReg/chkMbrCiChkForUpdate.ajax")
	private String chkMbrCiChkForUpdate(@ModelAttribute("mbrRegVO") MbrRegVO paramVO, HttpServletRequest request,
			ModelMap model) throws Exception {

		String customerPhone = paramVO.getCustomerAuthPhone();
		String customerNameKor = paramVO.getCustomerAuthNameKor();
		String customerVerify = paramVO.getCustomerAuthVerify();

		MbrRegVO mbrRegVO = new MbrRegVO();
		mbrRegVO.setCustomerVerify(customerVerify);
		MbrRegVO returnVO = mbrRegService.selectSptCustomerVerifyProfile(mbrRegVO);

		int rsCnt1 = 0;
		int rsCnt2 = 0;
		if (returnVO != null) {

			mbrRegVO = new MbrRegVO();
			mbrRegVO.setCustomerVerify(customerVerify);
			mbrRegVO.setCustomerRegNo(returnVO.getCustomerRegNo());
			mbrRegVO.setCustomerNameKor(customerNameKor);
			mbrRegVO.setCustomerPhone(customerPhone);

			mbrRegVO.setCustomerRegStatus(null);
			mbrRegVO.setCustomerStep(null);
			mbrRegVO.setCustomerEmailAuthYn(null); /* 회원 이메일 인증여부 */
			mbrRegVO.setCustomerEmailReceiveYn(null); /* 이메일 수신동의여부 */

			rsCnt1 = mbrRegService.updateSptCustomerInfoProfile(mbrRegVO);
			rsCnt2 = mbrRegService.insertSptCustomerInfoProfileHist(mbrRegVO);
		}

		model.addAttribute("returnVO", returnVO);
		model.addAttribute("rsCnt", rsCnt1 + rsCnt2);

		// session 삭제
		request.getSession().setAttribute("LoginVO", null); // 로그인 정보

		return "jsonView";
	}
}
