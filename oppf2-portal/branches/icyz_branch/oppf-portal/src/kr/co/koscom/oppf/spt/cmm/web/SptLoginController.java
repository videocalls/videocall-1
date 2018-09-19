package kr.co.koscom.oppf.spt.cmm.web;

import kr.co.koscom.oppf.cmm.service.*;
import kr.co.koscom.oppf.cmm.util.OppfSessionUtil;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.cmm.service.SptLoginService;
import kr.co.koscom.oppf.spt.cmm.service.SptLoginVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.AppService;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.AppVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.TermsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : SptLoginController.java
* @Comment  : 일반사용자 Login Controller
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
@Slf4j
@Controller
public class SptLoginController {
	
	@Resource(name = "SptLoginService")
    private SptLoginService sptLoginService;
	
	@Resource(name = "CmmFuncService")
	private CmmFuncService cmmFuncService;
	
	@Resource(name = "CmmEmailSendService")
    private CmmEmailSendService cmmEmailSendService;

	@Resource(name = "AppService")
	private AppService appService;
	
    /**
     * @Method Name        : loginView
     * @Method description : admin login 페이지 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/spt/cmm/loginView.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String loginView(@ModelAttribute("SptLoginVO") SptLoginVO sptLoginVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
		//moblie여부 셋팅
    	boolean isMobile = OppfStringUtil.isMobile(request);
        model.addAttribute("isMobile", isMobile);
		
	    model.addAttribute("paramVO", sptLoginVO);	    
	    return "spt/cmm/sptLogin";
	}
		
	/**
     * @Method Name        : loginCheck
     * @Method description : login 상태 체크
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/spt/cmm/loginCheck.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String loginCheck(@ModelAttribute("SptLoginVO") SptLoginVO sptLoginVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
		boolean loginFlag = false;		//로그인 실패여부
		
		//로그인 상태 체크
		//1. 아이디를 통해 로그인정보 획득 -> null인경우 아이디 없음
		//2. 패스워드 유효여부 -> 패스워드 실패 시 실패카운트 증적
		//3. 탈퇴여부 확인 -> 패스워드 성공해도 session생성안함 
		//4. 회원가입상태 확인 -> 활성화(G005002)일 경우에만 session 생성
		//5. session 생성 -> 로그인 성공 시 실패건수가 있었다면 리셋한다.
		SptLoginVO loginData = sptLoginService.selectLoginProfile(sptLoginVO);
		if(loginData != null){
			//패스워드 유효여부 -> 패스워드 실패 시 실패카운트 증적
			if("N".equals(loginData.getCustomer_password_yn())){
			    int failCnt = Integer.parseInt(OppfProperties.getProperty("Globals.user.policy.password.failCnt"));
                if(failCnt > loginData.getCustomer_password_fail_cnt()){ //회원의 비번 실패횟수가 정책횟수(예:5회) 보다 적으면
                    //패스워드 실패카운트 증적 -> 실패셋팅
                    loginData.setCustomer_password_fail_type("Y");
                    sptLoginService.updateLoginFailCnt(loginData);
                    
                    //사용자 정보 hist 셋팅
                    sptLoginService.insertLoginHist(loginData);
                    
                    //실패카운트 증적
                    failCnt = loginData.getCustomer_password_fail_cnt() + 1;
                    loginData.setCustomer_password_fail_cnt(failCnt);
                    
                }else{ //회원의 비번 실패횟수가 정책횟수(예:5회) 보다 크면(계정을 잠급니다.)
                    //패스워드 실패카운트 증적 -> 실패셋팅
                    loginData.setCustomer_password_fail_type("Y");
                    
                    //회원가입상태를 활성(G005002)에서 정지(G005003)로 바꿉니다.
                    loginData.setCustomer_reg_status("G005003");
                    sptLoginService.updateLoginFailCntHalt(loginData); //실패횟수도 1 올리고 회원상태를 정지상태로 만듭니다.
                    
                    //사용자 정보 hist 셋팅
                    sptLoginService.insertLoginHist(loginData);
                    
                    //실패카운트 증적
                    failCnt = loginData.getCustomer_password_fail_cnt() + 1;
                    loginData.setCustomer_password_fail_cnt(failCnt);
                    
                }
				
			}else{
				//회원 탈퇴가 아닌경우에만 로그인 session 생성
				if("N".equals(loginData.getDelete_yn())){
					//회원가입 상태 ->활성화(G005002)일 경우에만 session 생성
					if("G005002".equals(loginData.getCustomer_reg_status())){
					    //비밀번호 실패건수가 기준건수보다 크면 로그인을 하지 않는다.                     
                        int failCnt = Integer.parseInt(OppfProperties.getProperty("Globals.user.policy.password.failCnt"));
                        if(failCnt > loginData.getCustomer_password_fail_cnt()){ //회원의 비번 실패횟수가 정책횟수(예:5회) 보다 적으면
                            loginFlag = true; //로그인 성공(비번 실패횟수는 세션 저장시 리셋합니다.)
                            
                        }else{ //회원의 비번 실패횟수가 정책횟수(예:5회) 보다 크면(계정을 잠급니다.)
                            loginData.setCustomer_password_yn("N");
                            
                            //패스워드 실패카운트 증적 -> 실패셋팅
                            loginData.setCustomer_password_fail_type("Y");
                            
                            //회원가입상태를 활성(G005002)에서 정지(G005003)로 바꿉니다.
                            loginData.setCustomer_reg_status("G005003");
                            sptLoginService.updateLoginFailCntHalt(loginData); //실패횟수도 1 올리고 회원상태를 정지상태로 만듭니다.
                            
                            //사용자 정보 hist 셋팅
                            sptLoginService.insertLoginHist(loginData);
                            
                            //실패카운트 증적
                            failCnt = loginData.getCustomer_password_fail_cnt() + 1;
                            loginData.setCustomer_password_fail_cnt(failCnt);
                            
                        }
					//회원가입 후 최초 로그인상태는 비활성화(G005001)상태입니다.(회원가입완료 페이지로 들어가면 곧바로 활성화<G005002>상태로 update 합니다.)
					//회원가입 스텝이 G006005이면 회원가입 절차는 전부 진행한 경우이니 session 생성을 해 드립니다. - 2016.06.10 유제량 과장 
					}else if("G006005".equals(loginData.getCustomer_step()) && "G016002".equals(sptLoginVO.getEmailSendType())){
					    //비밀번호 실패건수가 기준건수보다 크면 로그인을 하지 않는다.                     
                        int failCnt = Integer.parseInt(OppfProperties.getProperty("Globals.user.policy.password.failCnt"));
                        if(failCnt > loginData.getCustomer_password_fail_cnt()){ //회원의 비번 실패횟수가 정책횟수(예:5회) 보다 적으면
                            loginFlag = true; //로그인 성공(비번 실패횟수는 세션 저장시 리셋합니다.)
                            
                        }else{ //회원의 비번 실패횟수가 정책횟수(예:5회) 보다 크면(계정을 잠급니다.)
                            loginData.setCustomer_password_yn("N");
                            
                            //패스워드 실패카운트 증적 -> 실패셋팅
                            loginData.setCustomer_password_fail_type("Y");
                            
                            //회원가입상태를 활성(G005002)에서 정지(G005003)로 바꿉니다.
                            loginData.setCustomer_reg_status("G005003");
                            sptLoginService.updateLoginFailCntHalt(loginData); //실패횟수도 1 올리고 회원상태를 정지상태로 만듭니다.
                            
                            //사용자 정보 hist 셋팅
                            sptLoginService.insertLoginHist(loginData);
                            
                            //실패카운트 증적
                            failCnt = loginData.getCustomer_password_fail_cnt() + 1;
                            loginData.setCustomer_password_fail_cnt(failCnt);
                            
                        }
					}
				}
			}
		}
		
		//로그인 성공 시 session 생성
		if(loginFlag){
			//로그인 성공 시 실패건수가 있었다면 리셋한다.
			if(loginData.getCustomer_password_fail_cnt() > 0){
				//성공 셋팅
				loginData.setCustomer_password_fail_type("N");
				sptLoginService.updateLoginFailCnt(loginData);
				
				//사용자 정보 hist 셋팅
				sptLoginService.insertLoginHist(loginData);
			}
			
			log.debug("loginData.getCustomer_join_type() {}"+loginData.getCustomerJoinType());
			
			// APP 가입자면 G039004 (APP/WEB)으로 업데이트 
			if("G039002".equals(loginData.getCustomerJoinType())){
				
				loginData.setCustomerJoinType("G039004");
				sptLoginService.updateCustomerJoinType(loginData);
			}
			 
			
			//session 생성
			kr.co.koscom.oppf.cmm.service.LoginVO cmmLoginVO = new kr.co.koscom.oppf.cmm.service.LoginVO();
			cmmLoginVO.setId(loginData.getCustomer_id());
			cmmLoginVO.setName_kor(loginData.getCustomer_name_kor());
			cmmLoginVO.setName_eng(loginData.getCustomer_name_eng());
			cmmLoginVO.setAuthKind("spt");
			
			//사용자 정보 셋팅
			cmmLoginVO.setCustomer_reg_no(loginData.getCustomer_reg_no());
			
			//임시비밀번호여부
			cmmLoginVO.setTemp_password_yn(loginData.getCustomer_temp_password_yn());
			//비밀번호 변경 예정일 이내 여부
			cmmLoginVO.setExpire_password_date_yn(loginData.getCustomer_expire_password_yn());
			//비밀번호 변경 최종 예정일 이내 여부
			cmmLoginVO.setFinal_password_date_yn(loginData.getCustomer_final_password_yn());
			//가용서버 통합계좌조회 메뉴 사용 여부
			cmmLoginVO.setIntegration_account_yn(loginData.getIntegration_account_yn());
			request.getSession().setAttribute("LoginVO", cmmLoginVO);

			AppVO appVO = new AppVO();
			appVO.setCustomerRegNo(cmmLoginVO.getCustomer_reg_no());
			TermsVO checkedCommonTerms = appService.checkedReSyncCommonTerms(appVO);
			model.addAttribute("checkedCommonTerms", checkedCommonTerms);

			//System.out.println("login-->succes~!!");
			
			//return "jsonView";
		}
		
		model.addAttribute("loginVO", loginData);		
			    
		return "jsonView";
	}
	
	/**
     * @Method Name        : sptIdpwFindTab
     * @Method description : 아이디 찾기/비밀번호찾기 tab 화면 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/spt/cmm/selectIdpwFindTab.do", method = {RequestMethod.POST, RequestMethod.GET})
    private String sptIdpwFindTab(@ModelAttribute("SptLoginVO") SptLoginVO sptLoginVO, ModelMap model)throws Exception{
        
        return "spt/cmm/sptIdpwFindTab";     
    }
	
	/**
     * @Method Name        : sptIdFind
     * @Method description : 아이디 찾기 화면 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/spt/cmm/selectIdFindView.do", method = {RequestMethod.POST, RequestMethod.GET})
    private String sptIdFindView(@ModelAttribute("SptLoginVO") SptLoginVO sptLoginVO, ModelMap model)throws Exception{
		//공통코드 셋팅
		CmmFuncVO cmmFuncVO = new CmmFuncVO();
		cmmFuncVO.setSystem_grp_code("G013");	//이메일
		List<CmmFuncVO> codeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
		model.addAttribute("emailList", codeList);
		
		//param
		model.addAttribute("SptLoginVO", sptLoginVO);
		model.addAttribute("tabId", "id");
        
        return "spt/cmm/sptIdFind";     
    }
	
	/**
     * @Method Name        : selectIdFind
     * @Method description : 아이디 찾기
     * @param              : 
     * @return             : SptLoginVO
     * @throws             : Exception
     */
	@RequestMapping(value="/spt/cmm/selectIdFind.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String selectIdFind(@ModelAttribute("SptLoginVO") SptLoginVO sptLoginVO, ModelMap model)throws Exception{
		SptLoginVO loginData = sptLoginService.selectIdFind(sptLoginVO);
		model.addAttribute("resultData", loginData);
        
        return "jsonView";
    }
    
	/**
     * @Method Name        : sptPwFindView
     * @Method description : 비밀번호 찾기 화면 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/spt/cmm/selectPwFindView.do", method = {RequestMethod.POST, RequestMethod.GET})
    private String sptPwFindView(@ModelAttribute("SptLoginVO") SptLoginVO sptLoginVO, ModelMap model)throws Exception{
    	//공통코드 셋팅
		CmmFuncVO cmmFuncVO = new CmmFuncVO();
		cmmFuncVO.setSystem_grp_code("G013");	//이메일
		List<CmmFuncVO> codeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
		model.addAttribute("emailList", codeList);
		
		//param
		model.addAttribute("SptLoginVO", sptLoginVO);
		model.addAttribute("tabId", "pw");
        
    	return "spt/cmm/sptIdFind";     
    }
	
    /**
     * @Method Name        : selectPwFind
     * @Method description : 비밀번호 찾기
     * @param              : 
     * @return             : SptLoginVO
     * @throws             : Exception
     */
	@RequestMapping(value="/spt/cmm/selectPwFind.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String selectPwFind(@ModelAttribute("SptLoginVO") SptLoginVO sptLoginVO, HttpServletRequest request, ModelMap model)throws Exception{
	    String systemKind = OppfSessionUtil.getSystemKind(request);		//system kind를 가져온다.
        if("".equals(OppfStringUtil.isNullToString(systemKind))){
            systemKind = "spt"; //회원가입단계는 로그인 상태가 아니므로 null값이 옵니다. 그 이외에는 system kind를 셋팅합니다.
        }        
        String GlobalsEmailSendId = "Globals.system.emailSendId."+systemKind;
        
	    CmmEmailSendVO cmmEmailSendVO = new CmmEmailSendVO();
	    
		SptLoginVO loginData = sptLoginService.selectPwFind(sptLoginVO);
		model.addAttribute("resultData", loginData);		
		
		//패스워드 찾기 성공 시에만 임시비밀번호 발급
		if(loginData != null){
			//임시비밀번호 발급
			char[] charSet = new char[]{				
					'0','1','2','3','4','5','6','7','8','9'
					,'a','b','c','d','e','f','g','h','i','j','k','l','m'
					,'n','o','p','q','r','s','t','u','v','w','x','y','z'
					};    	
			int len = Integer.parseInt(OppfProperties.getProperty("Globals.user.policy.password.tmpLength"));
			int idx = 0;
			StringBuffer sbuffer = new StringBuffer();
			
			for(int i=0; i<len; i++){
				idx = (int)(charSet.length*Math.random());
				sbuffer.append(charSet[idx]);
			}
			
			String imsiPassword =  sbuffer.toString();
			sptLoginVO.setCustomer_reg_no(loginData.getCustomer_reg_no());
			sptLoginVO.setCustomer_password(imsiPassword);
			//비밀번호 변경 예정 일
			sptLoginVO.setCustomer_expire_password_date(OppfProperties.getProperty("Globals.user.policy.password.expire"));
			sptLoginVO.setCustomer_final_password_date(OppfProperties.getProperty("Globals.user.policy.password.final"));
			
			//비밀번호 저장
			sptLoginService.updateTmpPassword(sptLoginVO);
			
			//사용자 정보 hist 셋팅
			sptLoginService.insertLoginHist(sptLoginVO);
	 		
			//이메일발송정보 셋팅
			cmmEmailSendVO.setEmailSendType("G016004"); //이메일 유형 - com_email_temp_info 이메일 템플릿 조회			
			cmmEmailSendVO.setReceiverName(sptLoginVO.getSearchName()); //받는 사람 이름(실제 이메일에 셋팅됩니다.)
			cmmEmailSendVO.setReceiverEmail(sptLoginVO.getSearchEmail()); //받는 사람 이메일(실제 이메일에 셋팅되고, 이 주소로 이메일이 발송됩니다.)
			cmmEmailSendVO.setSenderKind("G017003"); //발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
			cmmEmailSendVO.setSenderId(OppfProperties.getProperty(GlobalsEmailSendId)); //발신자 ID
			if(systemKind == "spt"){
			    cmmEmailSendVO.setReceiverKind("G018001"); //수신자 종류 - G018001:일반사용자, G018002:기업사용자
			}else{
			    cmmEmailSendVO.setReceiverKind("G018002"); //수신자 종류 - G018001:일반사용자, G018002:기업사용자
			}			
			//cmmEmailSendVO.setReceiverId(sptLoginVO.getSearchId()); //수신자 ID
			cmmEmailSendVO.setReceiverId(sptLoginVO.getCustomer_reg_no()); //수신자 ID
			cmmEmailSendVO.setSendId(OppfProperties.getProperty(GlobalsEmailSendId)); //최초 발신자 ID
			cmmEmailSendVO.setCreateId(OppfProperties.getProperty(GlobalsEmailSendId)); //생성자ID
			cmmEmailSendVO.setUpdateId(OppfProperties.getProperty(GlobalsEmailSendId)); //수정자ID
			cmmEmailSendVO.setCustomerPassword(imsiPassword); //임시비밀번호
			String uriContextPath = request.getParameter("uriContextPath");
			log.debug("uriContextPath : {} ", uriContextPath);
			if(!OppfStringUtil.isEmpty(uriContextPath)){
			    cmmEmailSendVO.setUriContextPath(uriContextPath); //임시비밀번호
			}
			
			//1.이메일 템플릿 조회 -> 2.이메일발송 유형 구분 -> 3.메일 발송 -> (성공시) -> 4.com_email_send_info 로그적재 관련
	        cmmEmailSendService.cmmEmailSend(cmmEmailSendVO, request);
	        sptLoginVO.setCustomer_password(null);
		}
		
        return "jsonView";
    }

}
