package kr.co.koscom.oppf.apt.sptUsr.web;

import kr.co.koscom.oppf.apt.sptUsr.service.SptUserManageService;
import kr.co.koscom.oppf.apt.sptUsr.service.SptUserManageVO;
import kr.co.koscom.oppf.cmm.service.*;
import kr.co.koscom.oppf.cmm.util.CommonUtil;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.cmm.service.SptLoginService;
import kr.co.koscom.oppf.spt.cmm.service.SptLoginVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.AppService;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.TermsVO;
import lombok.extern.slf4j.Slf4j;

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
* @FileName : SptUserManageController.java
* @Comment  : admin 포털 일반 회원관리 Controller
* @author   : 신동진
* @since    : 2016.06.20
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.20  신동진        최초생성
*
*/
@Controller
@Slf4j
public class SptUserManageController {
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "SptUserManageService")
    private SptUserManageService sptUserManageService;
	
	@Resource(name = "SptLoginService")
    private SptLoginService sptLoginService;
		
	@Resource(name = "CmmEmailSendService")
    private CmmEmailSendService cmmEmailSendService;

    /**
     * @Method Name        : sptUserManageList
     * @Method description : 일반 회원 관리목록 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/sptUserManageList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String sptUserManageList(@ModelAttribute("SptUserManageVO") SptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/sptUsr/sptUserManageList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        //셋팅 공통코드 : 회원가입 상태
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G005");
        List<CmmFuncVO> customerRegStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("customerRegStatusList", customerRegStatusList);
		

        //셋팅 공통코드 : 회원 가입 경로
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G039");
        List<CmmFuncVO> customerJoinSection = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("customerJoinSection", customerJoinSection);
		

        //셋팅 공통코드 : 계정유형
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G042");
        List<CmmFuncVO> customerMemberStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("customerMemberStatusList", customerMemberStatusList);
		
        
		model.addAttribute("SptUserManageVO", paramVO);
		
		model.addAttribute("serverType",CommonUtil.checkServerType(OppfProperties.getProperty("Globals.server.type")));
		
	    return modelView;
	}
		
	/**
     * @Method Name        : selectSptUserManageList
     * @Method description : 일반 회원 관리목록 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/selectSptUserManageList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectSptUserManageList(@ModelAttribute("SptUserManageVO") SptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		
        //order
        paramVO.setListOrder("if(a.customer_reg_status = 'G005002', '0', a.customer_reg_status) asc, if(ifnull(a.customer_name_kor, 1) = 1, 1, 0) asc, ifnull(a.customer_name_kor, 'Z') asc, ifnull(a.update_date, a.create_date) desc");
        
        Map<String, Object> map = sptUserManageService.selectSptUserManageList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : sptUserManageListExcel
     * @Method description : 일반 회원 관리 Excel 목록 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/sptUserManageListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String sptUserManageListExcel(@ModelAttribute("SptUserManageVO") SptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/sptUsr/sptUserManageListExcel";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //order
        paramVO.setListOrder("if(a.customer_reg_status = 'G005002', '0', a.customer_reg_status) asc, if(ifnull(a.customer_name_kor, 1) = 1, 1, 0) asc, ifnull(a.customer_name_kor, 'Z') asc, ifnull(a.update_date, a.create_date) desc");
        
        Map<String, Object> map = sptUserManageService.selectSptUserManageListExcel(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("SptUserManageVO", paramVO);
		
	    return modelView;
	}
	

	
	/**
     * @Method Name        : sptUserManageDtl
     * @Method description : 일반회원관리 상세  이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/sptUserManageDtl.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String sptUserManageDtl(@ModelAttribute("SptUserManageVO") SptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/sptUsr/sptUserManageDtl";
        

		String serverType = CommonUtil.checkServerType(OppfProperties.getProperty("Globals.server.type"));
		
		// serverType Y일 경우 dev용 수정페이지로 이동
		if("Y".equals(serverType)){
			modelView = "apt/sptUsr/sptUserManageDtlDev";
		}
		
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        
        //셋팅 공통코드 : 회원가입 상태
        cmmFuncVO.setSystem_grp_code("G005");
        List<CmmFuncVO> customerRegStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("customerRegStatusList", customerRegStatusList);
        
        //셋팅 공통코드:이메일
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G013");//이메일
        List<CmmFuncVO> emailList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("emailList", emailList);
        
        //셋팅 공통코드:휴대폰번호
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G011");//휴대폰
        List<CmmFuncVO> hpList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("hpList", hpList);
        
        Map<String, Object> map = sptUserManageService.selectSptUserManageDtl(paramVO);
		
        model.addAttribute("resultDetail", map.get("resultDetail"));							//상세
        model.addAttribute("resultDetailTermsList", map.get("resultDetailTermsList"));			//약관동의 목록
        model.addAttribute("resultDetailSvcTermsList", map.get("resultDetailSvcTermsList"));	//금융정보 제공동의 목록
        model.addAttribute("resultDetailAccountList", map.get("resultDetailAccountList"));		//가상계좌 목록
        model.addAttribute("deviceDetail", map.get("deviceDetail"));							// 디바이스 정보
        
		model.addAttribute("SptUserManageVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : updateSptUserManageDtl
     * @Method description : 일반회원 정보 수정
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/updateSptUserManageDtl.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String updateSptUserManageDtl(@ModelAttribute("SptUserManageVO") SptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //사용자 regNo가 없는경우
        if(OppfStringUtil.isEmpty(paramVO.getCustomerRegNo())){
        	model.addAttribute("user", "-1");
        	return "jsonView";
        }
        
        //회원정보 수정
  		int result = sptUserManageService.updateSptUserManageDtl(paramVO);		 		
						
  		model.addAttribute("result", result);
	    return "jsonView";
	}
	
	/**
     * @Method Name        : saveSptUserManageDtlPwd
     * @Method description : 일반 회원 관리 임시비밀번호 발급
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/saveSptUserManageDtlPwd.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String saveSptUserManageDtlPwd(@ModelAttribute("SptUserManageVO") SptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //사용자 regNo가 없는경우
        if(OppfStringUtil.isEmpty(paramVO.getCustomerRegNo())){
        	model.addAttribute("user", "-1");
        	return "jsonView";
        }
		
        //사용자 email이 없는 경우
        if(OppfStringUtil.isEmpty(paramVO.getCustomerEmail())){
        	model.addAttribute("email", "-1");
        	return "jsonView";
        }
        
        //사용자 이름이 없는 경우
        if(OppfStringUtil.isEmpty(paramVO.getCustomerNameKor())){
        	model.addAttribute("name", "-1");
        	return "jsonView";
        }
        
        //회원정보 수정
  		int result = sptUserManageService.updateSptUserManageDtl(paramVO);
  	//	System.out.println("result="+result);
  		if(result <= -1){
  			model.addAttribute("userSave", "-1");
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
		
		SptLoginVO sptLoginVO = new SptLoginVO();
		//param정보 셋팅
		sptLoginVO.setCustomer_reg_no(paramVO.getCustomerRegNo());
		sptLoginVO.setCustomer_email(paramVO.getCustomerEmail());	
		sptLoginVO.setCustomer_name_kor(paramVO.getCustomerNameKor());
		
		//email 발송용 정보
		sptLoginVO.setSearchName(paramVO.getCustomerNameKor());
		sptLoginVO.setSearchEmail(paramVO.getCustomerEmail());
		sptLoginVO.setSearchId(paramVO.getCustomerRegNo());
		
		
		//비밀번호 셋팅
		sptLoginVO.setCustomer_password(imsiPassword);
		//비밀번호 변경 예정 일
		sptLoginVO.setCustomer_expire_password_date(OppfProperties.getProperty("Globals.user.policy.password.expire"));
		sptLoginVO.setCustomer_final_password_date(OppfProperties.getProperty("Globals.user.policy.password.final"));
		
		//비밀번호 저장
		sptLoginService.updateTmpPassword(sptLoginVO);
		
		//사용자 정보 hist 셋팅
		sptLoginService.insertLoginHist(sptLoginVO);
 		
		//이메일발송정보 셋팅
		CmmEmailSendVO cmmEmailSendVO = new CmmEmailSendVO();
		cmmEmailSendVO.setEmailSendType("G016004"); //이메일 유형 - com_email_temp_info 이메일 템플릿 조회
		cmmEmailSendVO.setReceiverName(sptLoginVO.getSearchName()); //받는 사람 이름(실제 이메일에 셋팅됩니다.)
		cmmEmailSendVO.setReceiverEmail(sptLoginVO.getSearchEmail()); //받는 사람 이메일(실제 이메일에 셋팅되고, 이 주소로 이메일이 발송됩니다.)
		cmmEmailSendVO.setSenderKind("G017001"); //발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
		cmmEmailSendVO.setSenderId(loginVO.getAdmin_profile_reg_no()); //발신자 ID
		cmmEmailSendVO.setReceiverKind("G018001"); //수신자 종류 - G018001:일반사용자, G018002:기업사용자
		//cmmEmailSendVO.setReceiverId(sptLoginVO.getSearchId()); //수신자 ID
		cmmEmailSendVO.setReceiverId(sptLoginVO.getCustomer_reg_no()); //수신자 ID
		cmmEmailSendVO.setSendId(loginVO.getAdmin_profile_reg_no()); //최초 발신자 ID
		cmmEmailSendVO.setCreateId(loginVO.getAdmin_profile_reg_no()); //생성자ID
		cmmEmailSendVO.setUpdateId(loginVO.getAdmin_profile_reg_no()); //수정자ID
		cmmEmailSendVO.setCustomerPassword(imsiPassword); //임시비밀번호
		
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
		
		//1.이메일 템플릿 조회 -> 2.이메일발송 유형 구분 -> 3.메일 발송 -> (성공시) -> 4.com_email_send_info 로그적재 관련
		CmmEmailSendVO eamilResultVO = cmmEmailSendService.cmmEmailSend(cmmEmailSendVO, request);
		if("100".equals(eamilResultVO.getMailCode())){ //데이터 연동 성공
			model.addAttribute("result", "0");
		}else{
			model.addAttribute("result", "-1");
		}        
				
	    return "jsonView";
	}
	
	
	

    /**
     * @Method Name        : sptWithdrawUserManageList
     * @Method description : 탈퇴 회원 관리목록 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/sptWithdrawUserManageList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String sptWithdrawUserManageList(@ModelAttribute("SptUserManageVO") SptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/sptUsr/sptWithdrawUserManageList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        //셋팅 공통코드 : 회원가입 상태
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G005");
        List<CmmFuncVO> customerRegStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("customerRegStatusList", customerRegStatusList);
		

        //셋팅 공통코드 : 회원 가입 경로
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G039");
        List<CmmFuncVO> customerJoinSection = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("customerJoinSection", customerJoinSection);
		

        //셋팅 공통코드 : 계정유형
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G042");
        List<CmmFuncVO> customerMemberStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("customerMemberStatusList", customerMemberStatusList);
		
        
		model.addAttribute("SptUserManageVO", paramVO);
		
		model.addAttribute("serverType",CommonUtil.checkServerType(OppfProperties.getProperty("Globals.server.type")));
		
	    return modelView;
	}
		
	/**
     * @Method Name        : selectSptWithdrawUserManageList
     * @Method description : 탈퇴 회원 관리목록 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/selectSptWithdrawUserManageList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectSptWithdrawUserManageList(@ModelAttribute("SptUserManageVO") SptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		
        //order
        paramVO.setListOrder("if(a.customer_reg_status = 'G005002', '0', a.customer_reg_status) asc, if(ifnull(a.customer_name_kor, 1) = 1, 1, 0) asc, ifnull(a.customer_name_kor, 'Z') asc, ifnull(a.update_date, a.create_date) desc");
        
        Map<String, Object> map = sptUserManageService.selectSptWithdrawUserManageList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : sptWithdrawUserManageListExcel
     * @Method description : 탈퇴 회원 관리 Excel 목록 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/sptWithdrawUserManageListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String sptWithdrawUserManageListExcel(@ModelAttribute("SptUserManageVO") SptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/sptUsr/sptWithdrawUserManageListExcel";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //order
        paramVO.setListOrder("if(a.customer_reg_status = 'G005002', '0', a.customer_reg_status) asc, if(ifnull(a.customer_name_kor, 1) = 1, 1, 0) asc, ifnull(a.customer_name_kor, 'Z') asc, ifnull(a.update_date, a.create_date) desc");
        
        Map<String, Object> map = sptUserManageService.selectSptUserManageListExcel(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("SptUserManageVO", paramVO);
		
	    return modelView;
	}
	

	
	/**
     * @Method Name        : sptWithdrawUserManageDtl
     * @Method description : 탈퇴회원관리 상세  이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/sptWithdrawUserManageDtl.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String sptWithdrawUserManageDtl(@ModelAttribute("SptUserManageVO") SptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/sptUsr/sptWithdrawUserManageDtl";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        
        //셋팅 공통코드 : 회원가입 상태
        cmmFuncVO.setSystem_grp_code("G005");
        List<CmmFuncVO> customerRegStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("customerRegStatusList", customerRegStatusList);
        
        //셋팅 공통코드:이메일
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G013");//이메일
        List<CmmFuncVO> emailList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("emailList", emailList);
        
        //셋팅 공통코드:휴대폰번호
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G011");//휴대폰
        List<CmmFuncVO> hpList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("hpList", hpList);
        
        Map<String, Object> map = sptUserManageService.selectSptWithdrawUserManageDtl(paramVO);
		
        model.addAttribute("resultDetail", map.get("resultDetail"));							//상세
        model.addAttribute("resultDetailTermsList", map.get("resultDetailTermsList"));			//약관동의 목록
        model.addAttribute("deviceDetail", map.get("deviceDetail"));							// 디바이스 정보
        
		model.addAttribute("SptUserManageVO", paramVO);
		
	    return modelView;
	}
	
	

	/**
     * @Method Name        : updateSptWithdrawUser
     * @Method description : 탈퇴 처리
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/updateSptWithdrawUser.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String updateSptWithdrawUser(@ModelAttribute("SptUserManageVO") SptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //사용자 regNo가 없는경우
        if(OppfStringUtil.isEmpty(paramVO.getCustomerRegNo())){
        	model.addAttribute("user", "-1");
        	return "jsonView";
        }
        
        //회원정보 수정
  		int result = sptUserManageService.updateSptWithdrawUser(paramVO);		 		
						
  		model.addAttribute("result", result);
	    return "jsonView";
	}


}
