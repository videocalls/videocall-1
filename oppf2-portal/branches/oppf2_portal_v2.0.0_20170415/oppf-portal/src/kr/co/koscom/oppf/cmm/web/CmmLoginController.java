package kr.co.koscom.oppf.cmm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfSessionUtil;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;


/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CmmLoginController.java
* @Comment  : 공통 Login Controller
* @author   : 신동진
* @since    : 2016.04.28
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.04.28  신동진        최초생성
*
*/
@Controller
public class CmmLoginController {
	
	private static final Logger log = Logger.getLogger(CmmLoginController.class);
	
	/**
     * @Method Name        : selectSystemKind
     * @Method description : 공통 System kind 처리
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/selectSystemKind.do",method = {RequestMethod.POST, RequestMethod.GET})
	private void selectSystemKind(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String systemKind = OppfSessionUtil.getSystemKind(request);		//system kind를 가져온다.
		
		//system kind가 null일 경우 system kind를 셋팅한다.
		if(systemKind == null){
			
			//domain set
			String [] domainArr = OppfProperties.getProperty("Globals.domain.arr").split("\\|");
			
			if(domainArr != null){
				String targetUri = request.getServerName();
				if(request.getServerPort() > 0){
					targetUri += ":" + request.getServerPort();
				}
				
				for(int i=0; i<domainArr.length; i++){
					String tmpDomain = OppfProperties.getProperty("Globals.domain.sep."+domainArr[i]);
									
					//접속 domain이 같다면..
					/*
					if(targetUri.equals(tmpDomain)){
						request.getSession().setAttribute("SYSTEM_KIND", domainArr[i]);
					}
					*/
					//접속 domain값이 있다면
					if(targetUri.indexOf(tmpDomain) > -1){
						request.getSession().setAttribute("SYSTEM_KIND", domainArr[i]);
					}
				}
			}
		}
	}
	
    /**
     * @Method Name        : commonLogin
     * @Method description : 공통 login 처리 - 접속 ip/port에 따라 각 업무페이지로 이동한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/commonLogin.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String commonLogin(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
		String systemKind = OppfSessionUtil.getSystemKind(request);	//system kind를 가져온다.
		String forwrdUri = "";
		
		//https 요청처리
        int port = request.getServerPort();
        int gPort = Integer.parseInt(OppfProperties.getProperty("Globals.sslPort."+systemKind));
        //https 요청인 경우
        if(port == gPort){
            forwrdUri = "http://"+OppfProperties.getProperty("Globals.domain."+systemKind);
        }
		
		//systemKind is null
		if(OppfStringUtil.isEmpty(systemKind)){
			selectSystemKind(request, response);
			systemKind = OppfSessionUtil.getSystemKind(request);	//system kind를 가져온다.
		}
		log.debug("공통로그인:systemKind="+systemKind);
		
		//session의 유무에 따라 없으면 0번, 있으면 1번 페이지로 이동
		String [] tmpForwardArr = OppfProperties.getProperty("Globals.domain."+systemKind+".main").split("\\|");
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if(tmpForwardArr.length > 0){
		    log.debug("공통로그인:tmpForwardArr.length="+tmpForwardArr.length);
		}
		log.debug("공통로그인:loginVO="+loginVO);
		
		//로그인
		if(loginVO != null && !"".equals(loginVO.getId())){
			forwrdUri += tmpForwardArr[0];
		}else{
			forwrdUri += tmpForwardArr[1];
		}
		log.debug("공통로그인:forwrdUri="+forwrdUri);
	    return "redirect:" + forwrdUri;
//	    return "forward:" + forwrdUri;
	}
	
	/**
     * @Method Name        : loginView
     * @Method description : 공통 loginView forward 처리 -> systemKind에 따라 로그인 페이지 forward
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/loginView.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String loginView(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
		String systemKind = OppfSessionUtil.getSystemKind(request);	//system kind를 가져온다.
		String forwrdUri = "/" + systemKind + "/cmm/loginView.do";
		
	    return "forward:" + forwrdUri;
	}
		
	/**
     * @Method Name        : commonLogout
     * @Method description : 공통 logout 처리
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/commonLogout.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String commonLogout(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
		//session 삭제
		request.getSession().setAttribute("LoginVO", null);		//로그인 정보
		
		//메뉴관련 session 삭제
		String systemKind = OppfSessionUtil.getSystemKind(request);        //system kind를 가져온다.
		request.getSession().setAttribute(systemKind+"ParamMenuTopVO", null);
        request.getSession().setAttribute(systemKind+"MenuTopList", null);
        request.getSession().setAttribute(systemKind+"CurrentMenuId", null);
        request.getSession().setAttribute(systemKind+"CurrentParentMenuId", null);
        request.getSession().setAttribute(systemKind+"ParentMenuId", null);
		
	    return "forward:/cmm/commonLogin.do";
	}

}
