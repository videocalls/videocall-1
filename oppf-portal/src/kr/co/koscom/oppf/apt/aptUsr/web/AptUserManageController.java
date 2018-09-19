package kr.co.koscom.oppf.apt.aptUsr.web;

import kr.co.koscom.oppf.apt.aptUsr.service.AptUserManageService;
import kr.co.koscom.oppf.apt.aptUsr.service.AptUserManageVO;
import kr.co.koscom.oppf.cmm.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptUserManageController.java
* @Comment  : admin 포털 회원관리 Controller
* @author   : 신동진
* @since    : 2016.05.09
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.09  신동진        최초생성
*
*/
@Controller
public class AptUserManageController {
	
	@Resource(name = "AptUserManageService")
    private AptUserManageService aptUserManageService;
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "CmmEmailSendService")
    private CmmEmailSendService cmmEmailSendService;
	
	
    /**
     * @Method Name        : aptUserList
     * @Method description : admin 포털 회원관리목록 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/aptUsr/aptUserList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String aptUserList(@ModelAttribute("AptUserManageVO") AptUserManageVO userManageVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/aptUsr/aptUserList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
		
		model.addAttribute("AptUserManageVO", userManageVO);
		
	    return modelView;
	}
		
	/**
     * @Method Name        : selectAptUserList
     * @Method description : admin 포털 회원관리목록 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/aptUsr/selectAptUserList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectAptUserList(@ModelAttribute("AptUserManageVO") AptUserManageVO userManageVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		
		Map<String, Object> map = aptUserManageService.selectAptUserList(userManageVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : aptUserListExcel
     * @Method description : admin 포털 회원관리목록 excel 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/aptUsr/aptUserListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String aptUserListExcel(@ModelAttribute("AptUserManageVO") AptUserManageVO userManageVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/aptUsr/aptUserListExcel";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        Map<String, Object> map = aptUserManageService.selectAptUserListExcel(userManageVO);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("AptUserManageVO", userManageVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : aptUserDtl
     * @Method description : admin 포털 회원관리 상세
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/aptUsr/aptUserDtl.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String aptUserDtl(@ModelAttribute("AptUserManageVO") AptUserManageVO userManageVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/aptUsr/aptUserDtl";
    	
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
		
		//셋팅 공통코드:이메일
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G013");//이메일
        List<CmmFuncVO> emailList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("emailList", emailList);
        
        //셋팅 공통코드:휴대폰번호
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G011");//휴대폰
        List<CmmFuncVO> hpList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("hpList", hpList);
		
		AptUserManageVO resultDetail = aptUserManageService.selectAptUserDtl(userManageVO);
		
		model.addAttribute("resultDetail", resultDetail);
		model.addAttribute("AptUserManageVO", userManageVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : updateAptUser
     * @Method description : admin 포털 회원정보 수정
     * @param              : 
     * @return             : AptUserManageVO
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/aptUsr/updateAptUser.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String updateAptUser(@ModelAttribute("AptUserManageVO") AptUserManageVO userManageVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		userManageVO.setUpdateId(loginVO.getAdmin_profile_reg_no());
						
		//회원정보 수정
		int result = aptUserManageService.updateAptUser(userManageVO);		 		
		model.addAttribute("result", result);
		
        return "jsonView";
    }
	
	/**
     * @Method Name        : updateAptUserTmpPwd
     * @Method description : 임시비밀번호 발급
     * @param              : 
     * @return             : AptUserManageVO
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/aptUsr/updateAptUserTmpPwd.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String updateAptUserTmpPwd(@ModelAttribute("AptUserManageVO") AptUserManageVO userManageVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		
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
		userManageVO.setAdminPassword(imsiPassword);
		userManageVO.setUpdateId(loginVO.getAdmin_profile_reg_no());
		
		//비밀번호 변경 예정 일
		userManageVO.setAdminExpirePasswordDate(OppfProperties.getProperty("Globals.user.policy.password.expire"));
		userManageVO.setAdminFinalPasswordDate(OppfProperties.getProperty("Globals.user.policy.password.final"));
						
		//비밀번호 저장
		int result = aptUserManageService.updateAptUserTmpPwd(userManageVO);
		 		
		//메일 발송
		if(result > 0){
			//이메일발송정보 셋팅
			CmmEmailSendVO cmmEmailSendVO = new CmmEmailSendVO();
			cmmEmailSendVO.setEmailSendType("G016004"); //이메일 유형 - com_email_temp_info 이메일 템플릿 조회
			cmmEmailSendVO.setReceiverName(userManageVO.getAdminNameKor()); //받는 사람 이름(실제 이메일에 셋팅됩니다.)
			cmmEmailSendVO.setReceiverEmail(userManageVO.getAdminEmail()); //받는 사람 이메일(실제 이메일에 셋팅되고, 이 주소로 이메일이 발송됩니다.)
			cmmEmailSendVO.setSenderKind("G017001"); //발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
			cmmEmailSendVO.setSenderId(loginVO.getAdmin_profile_reg_no()); //발신자 ID
			cmmEmailSendVO.setReceiverKind("G018003"); //수신자 종류 - G018001:일반사용자, G018002:기업사용자, G018003 :admin
			cmmEmailSendVO.setReceiverId(userManageVO.getAdminProfileRegNo()); //수신자 ID
			cmmEmailSendVO.setSendId(loginVO.getAdmin_profile_reg_no()); //최초 발신자 ID
			cmmEmailSendVO.setCreateId(loginVO.getAdmin_profile_reg_no()); //생성자ID
			cmmEmailSendVO.setUpdateId(loginVO.getAdmin_profile_reg_no()); //수정자ID
			cmmEmailSendVO.setCustomerPassword(imsiPassword); //임시비밀번호
			
			String uriContextPath = "http://" + OppfProperties.getProperty("Globals.domain.apt");
			cmmEmailSendVO.setUriContextPath(uriContextPath); //로그인 페이지 : https
			
			cmmEmailSendVO.setSystemKind("apt");//개인 포털로 셋팅[spt:개인, cpt:기업, apt:admin] -> session이 아닌 target이 있을 경우 셋팅
			
			//1.이메일 템플릿 조회 -> 2.이메일발송 유형 구분 -> 3.메일 발송 -> (성공시) -> 4.com_email_send_info 로그적재 관련
			CmmEmailSendVO eamilResultVO = cmmEmailSendService.cmmEmailSend(cmmEmailSendVO, request);
			if("100".equals(eamilResultVO.getMailCode())){ //데이터 연동 성공
				result = 1;
			}else{
				result = -1;
			}
			
		}		
		
		model.addAttribute("result", result);
		
        return "jsonView";
    }
	
	/**
     * @Method Name        : aptUserReg
     * @Method description : admin 포털 회원관리 등록
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/aptUsr/aptUserReg.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String aptUserReg(@ModelAttribute("AptUserManageVO") AptUserManageVO userManageVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/aptUsr/aptUserReg";
    	
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
		
		//셋팅 공통코드:이메일
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G013");//이메일
        List<CmmFuncVO> emailList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("emailList", emailList);
        
        //셋팅 공통코드:휴대폰번호
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G011");//휴대폰
        List<CmmFuncVO> hpList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("hpList", hpList);
		
		model.addAttribute("AptUserManageVO", userManageVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : selectAptUserIdChk
     * @Method description : admin 포털 id 중복확인
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/aptUsr/selectAptUserIdChk.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectAptUserIdChk(@ModelAttribute("AptUserManageVO") AptUserManageVO userManageVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		int result = aptUserManageService.selectAptUserIdChk(userManageVO);
		
		model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : insertAptUserReg
     * @Method description : admin 포털 회원 등록
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/aptUsr/insertAptUserReg.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String insertAptUserReg(@ModelAttribute("AptUserManageVO") AptUserManageVO userManageVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		userManageVO.setCreateId(loginVO.getAdmin_profile_reg_no());
				
		int result = aptUserManageService.insertAptUserReg(userManageVO);
		
		model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
}
