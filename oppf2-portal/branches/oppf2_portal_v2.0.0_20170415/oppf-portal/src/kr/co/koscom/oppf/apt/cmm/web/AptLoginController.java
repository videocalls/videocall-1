package kr.co.koscom.oppf.apt.cmm.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.apt.cmm.service.AptLoginService;
import kr.co.koscom.oppf.apt.cmm.service.AptLoginVO;
import kr.co.koscom.oppf.cmm.service.CmmEmailSendService;
import kr.co.koscom.oppf.cmm.service.CmmEmailSendVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfSessionUtil;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;


/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : AptLoginController.java
* @Comment  : admin Login Controller
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
public class AptLoginController {
	
	private static final Logger log = Logger.getLogger(AptLoginController.class);
	
	@Resource(name = "AptLoginService")
    private AptLoginService aptLoginService;
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "CmmEmailSendService")
    private CmmEmailSendService cmmEmailSendService;
	
    /**
     * @Method Name        : loginView
     * @Method description : admin login 페이지 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/loginView.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String loginView(@ModelAttribute("AptLoginVO") AptLoginVO aptLoginVO,HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
		model.addAttribute("paramVO", aptLoginVO);
	    return "apt/cmm/aptLogin";
	}
	
	/**
     * @Method Name        : loginCheck
     * @Method description : login 상태 체크
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/loginCheck.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String loginCheck(@ModelAttribute("AptLoginVO") AptLoginVO aptLoginVO, HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
		boolean loginFlag = false;		//로그인 실패여부
		
		//로그인 상태 체크
		//1. 아이디를 통해 로그인정보 획득 -> null인경우 아이디 없음
		//2. 패스워드 유효여부 -> 패스워드 실패 시 실패카운트 증적
		//3. 탈퇴여부 확인 -> 패스워드 성공해도 session생성안함 
		//4. session 생성 -> 로그인 성공 시 실패건수가 있었다면 리셋한다.
		AptLoginVO loginData = aptLoginService.selectLoginProfile(aptLoginVO);
		if(loginData != null){
			//패스워드 유효여부 -> 패스워드 실패 시 실패카운트 증적
			if("N".equals(loginData.getAdmin_password_yn())){
				//패스워드 실패카운트 증적 -> 실패셋팅
				loginData.setAdmin_password_fail_type("Y");
				aptLoginService.updateLoginFailCnt(loginData);
				
				//사용자 정보 hist 셋팅
				aptLoginService.insertLoginHist(loginData);
				
				//실패카운트 증적
				int failCnt = loginData.getAdmin_password_fail_cnt() + 1;
				loginData.setAdmin_password_fail_cnt(failCnt);
			}else{
				//회원 탈퇴가 아닌경우에만 로그인 session 생성
				if("N".equals(loginData.getDelete_yn())){
					//비밀번호 실패건수가 기준건수보다 크면 로그인을 하지 않는다.
//					int failCnt = Integer.parseInt(OppfProperties.getProperty("Globals.user.policy.password.failCnt"));
//					if(failCnt > loginData.getAdmin_password_fail_cnt()){
//						loginFlag = true;
//					}
					//실패건수가 있더라도 원래 비밀번호값이랑 동일하면 로그인 성공
                    loginFlag = true;
				}
			}
		}
		
		//로그인 성공 시 session 생성
		if(loginFlag){
			//로그인 성공 시 실패건수가 있었다면 리셋한다.
			if(loginData.getAdmin_password_fail_cnt() > 0){
				//성공 셋팅
				loginData.setAdmin_password_fail_type("N");
				aptLoginService.updateLoginFailCnt(loginData);
				
				//사용자 정보 hist 셋팅
				aptLoginService.insertLoginHist(loginData);
			}
			
			//session 생성
			kr.co.koscom.oppf.cmm.service.LoginVO cmmLoginVO = new kr.co.koscom.oppf.cmm.service.LoginVO();
			cmmLoginVO.setId(loginData.getAdmin_id());
			cmmLoginVO.setName_kor(loginData.getAdmin_name_kor());
			cmmLoginVO.setName_eng(loginData.getAdmin_name_eng());
			cmmLoginVO.setAuthKind("apt");
			
			//사용자 정보
			cmmLoginVO.setAdmin_profile_reg_no(loginData.getAdmin_profile_reg_no());
			
			//임시비밀번호여부
			cmmLoginVO.setTemp_password_yn(loginData.getAdmin_temp_password_yn());
			//비밀번호 변경 예정일 이내 여부
			cmmLoginVO.setExpire_password_date_yn(loginData.getAdmin_expire_password_yn());
			//비밀번호 변경 최종 예정일 이내 여부
			cmmLoginVO.setFinal_password_date_yn(loginData.getAdmin_final_password_yn());
			
			request.getSession().setAttribute("LoginVO", cmmLoginVO);
			
			//return "jsonView";
		}
		
		model.addAttribute("loginVO", loginData);		
			    
		return "jsonView";
	}
	
	/**
     * @Method Name        : aptIdpwFindTab
     * @Method description : 아이디 찾기/비밀번호찾기 tab 화면 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/cmm/selectIdpwFindTab.do", method = {RequestMethod.POST, RequestMethod.GET})
    private String aptIdpwFindTab(@ModelAttribute("AptLoginVO") AptLoginVO aptLoginVO, ModelMap model)throws Exception{
        
        return "apt/cmm/aptIdpwFindTab";     
    }
    
    /**
     * @Method Name        : aptIdFind
     * @Method description : 아이디 찾기 화면 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/cmm/selectIdFindView.do", method = {RequestMethod.POST, RequestMethod.GET})
    private String aptIdFindView(@ModelAttribute("AptLoginVO") AptLoginVO aptLoginVO, ModelMap model)throws Exception{
        //공통코드 셋팅
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G013");   //이메일
        List<CmmFuncVO> codeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("emailList", codeList);
        
        //param
        model.addAttribute("AptLoginVO", aptLoginVO);
        model.addAttribute("tabId", "id");
        
        return "apt/cmm/aptIdFind";     
    }
    
    /**
     * @Method Name        : selectIdFind
     * @Method description : 아이디 찾기
     * @param              : 
     * @return             : AptLoginVO
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/cmm/selectIdFind.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String selectIdFind(@ModelAttribute("AptLoginVO") AptLoginVO aptLoginVO, ModelMap model)throws Exception{
        AptLoginVO loginData = aptLoginService.selectIdFind(aptLoginVO);
        model.addAttribute("resultData", loginData);
        
        return "jsonView";
    }
    
    /**
     * @Method Name        : aptPwFindView
     * @Method description : 비밀번호 찾기 화면 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/cmm/selectPwFindView.do", method = {RequestMethod.POST, RequestMethod.GET})
    private String aptPwFindView(@ModelAttribute("AptLoginVO") AptLoginVO aptLoginVO, ModelMap model)throws Exception{
        //공통코드 셋팅
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G013");   //이메일
        List<CmmFuncVO> codeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("emailList", codeList);
        
        //param
        model.addAttribute("AptLoginVO", aptLoginVO);
        model.addAttribute("tabId", "pw");
        
        return "apt/cmm/aptIdFind";     
    }
    
    /**
     * @Method Name        : selectPwFind
     * @Method description : 비밀번호 찾기
     * @param              : 
     * @return             : AptLoginVO
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/cmm/selectPwFind.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String selectPwFind(@ModelAttribute("AptLoginVO") AptLoginVO aptLoginVO, HttpServletRequest request, ModelMap model)throws Exception{
        String systemKind = OppfSessionUtil.getSystemKind(request);     //system kind를 가져온다.
        if("".equals(OppfStringUtil.isNullToString(systemKind))){
            systemKind = "apt"; //회원가입단계는 로그인 상태가 아니므로 null값이 옵니다. 그 이외에는 system kind를 셋팅합니다.
        }        
        String GlobalsEmailSendId = "Globals.system.emailSendId."+systemKind;
        
        CmmEmailSendVO cmmEmailSendVO = new CmmEmailSendVO();
        
        AptLoginVO loginData = aptLoginService.selectPwFind(aptLoginVO);
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
            aptLoginVO.setAdmin_profile_reg_no(loginData.getAdmin_profile_reg_no());
            aptLoginVO.setAdmin_password(imsiPassword);
            //비밀번호 변경 예정 일
            aptLoginVO.setAdmin_expire_password_date(OppfProperties.getProperty("Globals.user.policy.password.expire"));
            aptLoginVO.setAdmin_final_password_date(OppfProperties.getProperty("Globals.user.policy.password.final"));
            
            //비밀번호 저장
            aptLoginService.updateTmpPassword(aptLoginVO);
            
            //사용자 정보 hist 셋팅
            aptLoginService.insertLoginHist(aptLoginVO);
            
            //이메일발송정보 셋팅
            cmmEmailSendVO.setEmailSendType("G016004"); //이메일 유형 - com_email_temp_info 이메일 템플릿 조회           
            cmmEmailSendVO.setReceiverName(aptLoginVO.getSearchName()); //받는 사람 이름(실제 이메일에 셋팅됩니다.)
            cmmEmailSendVO.setReceiverEmail(aptLoginVO.getSearchEmail()); //받는 사람 이메일(실제 이메일에 셋팅되고, 이 주소로 이메일이 발송됩니다.)
            cmmEmailSendVO.setSenderKind("G017003"); //발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
            cmmEmailSendVO.setSenderId(OppfProperties.getProperty(GlobalsEmailSendId)); //발신자 ID
            if(systemKind == "apt"){
                cmmEmailSendVO.setReceiverKind("G018001"); //수신자 종류 - G018001:일반사용자, G018002:기업사용자
            }else{
                cmmEmailSendVO.setReceiverKind("G018002"); //수신자 종류 - G018001:일반사용자, G018002:기업사용자
            }
            cmmEmailSendVO.setReceiverId(aptLoginVO.getAdmin_profile_reg_no()); //수신자 ID
            cmmEmailSendVO.setSendId(OppfProperties.getProperty(GlobalsEmailSendId)); //최초 발신자 ID
            cmmEmailSendVO.setCreateId(OppfProperties.getProperty(GlobalsEmailSendId)); //생성자ID
            cmmEmailSendVO.setUpdateId(OppfProperties.getProperty(GlobalsEmailSendId)); //수정자ID
            cmmEmailSendVO.setCustomerPassword(imsiPassword); //임시비밀번호
            
            //1.이메일 템플릿 조회 -> 2.이메일발송 유형 구분 -> 3.메일 발송 -> (성공시) -> 4.com_email_send_info 로그적재 관련
            cmmEmailSendService.cmmEmailSend(cmmEmailSendVO, request);
        }
        
        return "jsonView";
    }
	
}
