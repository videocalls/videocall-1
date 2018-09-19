package kr.co.koscom.oppf.spt.cmm.web;

import kr.co.koscom.oppf.cmm.IntegratedAccount.service.CmmSIFInternalService;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.cmm.service.SptLoginVO;
import kr.co.koscom.oppf.spt.cmm.service.SptOauthLoginService;
import kr.co.koscom.oppf.spt.cmm.service.SptOauthLoginVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SvcApplService;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SvcApplVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;


/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : SptOauthLoginController.java
* @Comment  : 일반사용자 Oauth Login Controller
* @author   : 이희태
* @since    : 2017.02.21
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2017.02.21  이희태        최초생성
*
*/
@Slf4j
@Controller
public class SptOauthLoginController {
	
	@Resource(name = "SptOauthLoginService")
    private SptOauthLoginService sptOauthLoginService;
	@Resource(name = "CmmSIFInternalService")
	private CmmSIFInternalService cmmSIFInternalService;
	@Resource(name = "SvcApplService")
	private SvcApplService svcApplService;
	@Resource(name = "CmmFuncService")
	private CmmFuncService cmmFuncService;

    /**
     * @Method Name        : loginView
     * @Method description : admin login 페이지 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/spt/cmm/authorize",method = RequestMethod.GET)
	private String oauthLoginView(@ModelAttribute("SptOauthLoginVO") SptOauthLoginVO sptOauthLoginVO, HttpServletRequest request, ModelMap model)throws Exception{
		//moblie여부 셋팅
    	boolean isMobile = OppfStringUtil.isMobile(request);
        model.addAttribute("isMobile", isMobile);
		//서비스포털에서 로그인 시도시 로그인 아이디값 리턴
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if(loginVO != null){
			String customerId = loginVO.getId();
			if(!OppfStringUtil.isEmpty(customerId)){
				model.addAttribute("customerId", customerId);
			}
		}
		//서버 모드
		String serverMode = OppfProperties.getProperty("Globals.integrated.account.mode");
		model.addAttribute("serverMode", serverMode);

		//G/W 에서 받는 클라이언트 정보
		String sessionID = sptOauthLoginVO.getSessionID();
		String client_id = sptOauthLoginVO.getClientId();
		String scope = sptOauthLoginVO.getScope();
		if(null == sessionID || "".equals(sessionID) || null == client_id || "".equals(client_id)
				|| null == scope || "".equals(scope)){
			throw new Exception();
		}
		SvcApplVO svcApplVO = new SvcApplVO();
		svcApplVO.setAppId(client_id);
		svcApplVO = svcApplService.selectSvcAppInfo(svcApplVO);
		if(null == svcApplVO){
			throw new Exception();
		}
		sptOauthLoginVO.setApp_name(svcApplVO.getAppName());
		sptOauthLoginVO.setAppId(svcApplVO.getAppId());
		sptOauthLoginVO.setAppId(svcApplVO.getAppId());
		sptOauthLoginVO.setCompanyName(svcApplVO.getCompanyNameKor());
        model.addAttribute("paramVO", sptOauthLoginVO);
	    return "spt/cmm/oauthLogin";
	}

	/**
     * @Method Name        : loginIdOtpCheck
     * @Method description : loginID OTP 사용 체크
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/spt/cmm/loginIdOtpCheck.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String loginIdOtpCheck(@ModelAttribute("SptLoginVO") SptLoginVO sptLoginVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{

		SptLoginVO loginData = sptOauthLoginService.selectOtpCheck(sptLoginVO);
		model.addAttribute("loginVO", loginData);
			    
		return "jsonView";
	}

	/**
	 * @Method Name        : oauthLoginCheck
	 * @Method description : 인증서버를 통한 Oauth 로그인 체크
	 * @param              :
	 * @return             : String
	 * @throws             : Exception
	 */
	@RequestMapping(value="/spt/cmm/oauthLoginCheck.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String oauthLoginCheck(@ModelAttribute("SptOauthLoginVO") SptOauthLoginVO sptOauthLoginVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{

		//인증서버 헤더정보
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		// CA 공유 requestId
		httpHeaders.add("x-api-requestId", OppfProperties.getProperty("Globals.authentication.requestId"));
		// 호출한 클라이언트 Id
		httpHeaders.add("x-api-clientId", sptOauthLoginVO.getClientId());

		//인증서버
		String authenticationUrl = OppfProperties.getProperty("Globals.authentication.url");
		String payload = "{ \"userId\" : \""+sptOauthLoginVO.getCustomer_id()+"\" , \"password\" : \""+sptOauthLoginVO.getCustomer_password()+"\" }";
		ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(authenticationUrl, httpHeaders, HttpMethod.POST, payload);
		if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
			log.debug("++++++++++++++++++++++++++++++++++++ authentication success : {} ", responseEntity.getBody() );
			CmmFuncVO cmmFuncVO = new CmmFuncVO();
			cmmFuncVO.setSystem_grp_code("G036"); //Oauth Provider Scope 종류
			List<CmmFuncVO> scopeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
			// profile%20account scope 에 대한 검증
			for(CmmFuncVO vo : scopeList){
				//클라이언트 scope 유효성 체크, 허용하는 scope이 아닌경우 권한 확인 화면없이 바로 callback URL 리다이렉트 처리
				if(sptOauthLoginVO.getScope().indexOf(vo.getCode_name_kor()) > -1){
					model.addAttribute("scope", "00");
					break;
				}
			}
		}else{
			log.debug("++++++++++++++++++++++++++++++++++++ authentication failed : {} ", responseEntity.getBody() );
		}
		model.addAttribute("result", responseEntity.getStatusCode());

		return "jsonView";
	}

	/**
	 * @Method Name        : oauthScopeCheck
	 * @Method description : oauth 로그인 Socpe 컨펌창
	 * @param              :
	 * @return             : String
	 * @throws             : Exception
	 */
	@RequestMapping(value="/spt/cmm/authorize",method = RequestMethod.POST)
	private String oauthScopeCheck(@ModelAttribute("SptOauthLoginVO") SptOauthLoginVO sptOauthLoginVO, HttpServletRequest request, ModelMap model)throws Exception{
		CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G036"); //Oauth Provider Scope 종류
        List<CmmFuncVO> scopeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);

		model.addAttribute("paramVO", sptOauthLoginVO);
		model.addAttribute("scopeList", scopeList);
		return "spt/cmm/oauthScope";
	}

	/**
	 * @Method Name        : authorization
	 * @Method description : authorization code 요청
	 * @param              :
	 * @return             : String
	 * @throws             : Exception
	 */
	@RequestMapping(value="/spt/cmm/authorization.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String authorization(@ModelAttribute("SptOauthLoginVO") SptOauthLoginVO sptOauthLoginVO, ModelMap model)throws Exception{

		//헤더정보
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		//CA authorize Code 요청
		String authenticationUrl = OppfProperties.getProperty("Globals.oauth.authorize.ip");
		String payload = "sessionID="+sptOauthLoginVO.getSessionID()+"&username="+sptOauthLoginVO.getCustomer_id()+"&action=Grant";

		try{
			//Oauth 이력 관리
			SptLoginVO loginData = new SptLoginVO();
			loginData.setCustomer_id(sptOauthLoginVO.getCustomer_id());
			loginData = sptOauthLoginService.selectOtpCheck(loginData);
			sptOauthLoginVO.setCustomer_reg_no(loginData.getCustomerRegNo());
			//Oauth 이력 관리
			sptOauthLoginService.insertOauthProfile(sptOauthLoginVO);

			ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(authenticationUrl, httpHeaders, HttpMethod.POST, payload);
			if(HttpStatus.FOUND.equals(responseEntity.getStatusCode())){
				log.debug("++++++++++++++++++++++++++++++++++++ CallBack : {} ", responseEntity.getHeaders() );
				String location = String.valueOf(responseEntity.getHeaders().getLocation());
				model.addAttribute("result", "00");
				model.addAttribute("location", location);
			}else{
				log.debug("++++++++++++++++++++++++++++++++++++ authorize failed : {} ", responseEntity.getBody() );
				model.addAttribute("result", "01");
			}
		}catch (Exception e){
				log.debug("++++++++++++++++++++++++++++++++++++ Internal Error : {} ", e.getMessage() );
				model.addAttribute("result", "01");
		}

		return "jsonView";
	}

}
