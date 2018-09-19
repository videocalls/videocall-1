package kr.co.koscom.oppf.apt.cptUsr.web;

import kr.co.koscom.oppf.apt.cptUsr.service.CptUserManageService;
import kr.co.koscom.oppf.apt.cptUsr.service.CptUserManageVO;
import kr.co.koscom.oppf.cmm.service.*;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
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
* @FileName : CptUserManageController.java
* @Comment  : 기업회원조회 관리 Controller
* @author   : 신동진
* @since    : 2016.06.24
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.24  신동진        최초생성
*
*/
@Controller
public class CptUserManageController {
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "CptUserManageService")
    private CptUserManageService cptUserManageService;
	
	@Resource(name = "CmmEmailSendService")
    private CmmEmailSendService cmmEmailSendService;
	
    /**
     * @Method Name        : cptUserManageList
     * @Method description : 기업회원조회 목록 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cptUsr/cptUserManageList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String cptUserManageList(@ModelAttribute("CptUserManageVO") CptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/cptUsr/cptUserManageList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //셋팅 공통코드 : 회원가입 상태
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G005");
        List<CmmFuncVO> operatorRegStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("operatorRegStatusList", operatorRegStatusList);
		
		model.addAttribute("CptUserManageVO", paramVO);
		
	    return modelView;
	}
		
	/**
     * @Method Name        : selectCptUserManageList
     * @Method description : 기업회원 관리목록 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cptUsr/selectCptUserManageList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectCptUserManageList(@ModelAttribute("CptUserManageVO") CptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		
        //order
        paramVO.setListOrder("b.exposure_order asc, b.company_name_kor_alias asc, ifnull(a.update_date, a.create_date) desc");
        
        Map<String, Object> map = cptUserManageService.selectCptUserManageList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : cptUserManageListExcel
     * @Method description : 기업회원조회  Excel 목록 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cptUsr/cptUserManageListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String cptUserManageListExcel(@ModelAttribute("CptUserManageVO") CptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/cptUsr/cptUserManageListExcel";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //order
        paramVO.setListOrder("b.exposure_order asc, b.company_name_kor_alias asc, ifnull(a.update_date, a.create_date) desc");
        
        Map<String, Object> map = cptUserManageService.selectCptUserManageListExcel(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("CptUserManageVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : cptUserManageDtl
     * @Method description : 기업회원조회 상세 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cptUsr/cptUserManageDtl.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String cptUserManageDtl(@ModelAttribute("CptUserManageVO") CptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/cptUsr/cptUserManageDtl";
        
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
        
        //셋팅 공통코드:상태
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G005");//상태
        List<CmmFuncVO> operatorRegStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("operatorRegStatusList", operatorRegStatusList);
        
        Map<String, Object> map = cptUserManageService.selectCptUserManageDtl(paramVO);
		
        model.addAttribute("resultDetail", map.get("resultDetail"));				//회원정보
        model.addAttribute("resultDetailCompany", map.get("resultDetailCompany"));	//기업정보
		
		model.addAttribute("CptUserManageVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : checkCptUserManageOperatorId
     * @Method description : 기업회원 ID 중복체크
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cptUsr/checkCptUserManageOperatorId.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String checkCptUserManageOperatorId(@ModelAttribute("CptUserManageVO") CptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		
        int result = cptUserManageService.checkCptUserManageOperatorId(paramVO);
		
        model.addAttribute("result", result);
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : checkCptUserManageOperatorEmail
     * @Method description : 기업회원 email 중복체크
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cptUsr/checkCptUserManageOperatorEmail.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String checkCptUserManageOperatorEmail(@ModelAttribute("CptUserManageVO") CptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		
        int result = cptUserManageService.checkCptUserManageOperatorEmail(paramVO);
		
        model.addAttribute("result", result);
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : saveCptUserManage
     * @Method description : 기업회원 정보 저장
     * @param              : 
     * @return             : AptUserManageVO
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cptUsr/saveCptUserManage.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String saveCptUserManage(@ModelAttribute("CptUserManageVO") CptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());        
        
        int result = 0;
        String operatorProfileRegNo = paramVO.getOperatorProfileRegNo();
		//등록
		if(OppfStringUtil.isEmpty(operatorProfileRegNo)){
			//등록시에는 임시비밀번호 발급 후 저장 완료 시 해당 사용자에게 메일을 발송한다.
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
			
			//비밀번호 셋팅
			paramVO.setOperatorPassword(imsiPassword);
			
			//비밀번호 변경 예정 일
			paramVO.setOperatorExpirePasswordDate(OppfProperties.getProperty("Globals.user.policy.password.expire"));
			paramVO.setOperatorFinalPasswordDate(OppfProperties.getProperty("Globals.user.policy.password.final"));
			
			operatorProfileRegNo = cptUserManageService.insertCptUserManage(paramVO);
			if(!"".equals(OppfStringUtil.isNullToString(operatorProfileRegNo))) result = 1;
			
			//메일 발송
			if(!"".equals(OppfStringUtil.isNullToString(operatorProfileRegNo))){
				try{
					//이메일발송정보 셋팅
					CmmEmailSendVO cmmEmailSendVO = new CmmEmailSendVO();
					cmmEmailSendVO.setEmailSendType("G016004"); //이메일 유형 - com_email_temp_info 이메일 템플릿 조회
					cmmEmailSendVO.setReceiverName(paramVO.getOperatorNameKor()); //받는 사람 이름(실제 이메일에 셋팅됩니다.)
					cmmEmailSendVO.setReceiverEmail(paramVO.getOperatorEmail()); //받는 사람 이메일(실제 이메일에 셋팅되고, 이 주소로 이메일이 발송됩니다.)
					cmmEmailSendVO.setSenderKind("G017001"); //발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
					cmmEmailSendVO.setSenderId(loginVO.getAdmin_profile_reg_no()); //발신자 ID
					cmmEmailSendVO.setReceiverKind("G018002"); //수신자 종류 - G018001:일반사용자, G018002:기업사용자, G018003 :admin
					cmmEmailSendVO.setReceiverId(operatorProfileRegNo); //수신자 ID
					cmmEmailSendVO.setSendId(loginVO.getAdmin_profile_reg_no()); //최초 발신자 ID
					cmmEmailSendVO.setCreateId(loginVO.getAdmin_profile_reg_no()); //생성자ID
					cmmEmailSendVO.setUpdateId(loginVO.getAdmin_profile_reg_no()); //수정자ID
					cmmEmailSendVO.setCustomerPassword(imsiPassword); //임시비밀번호
					
					String uriContextPath = "https://";
					String sptServerName = OppfProperties.getProperty("Globals.domain.cpt");
					if(sptServerName.indexOf(":") >= 0){
						String [] tmpStr = sptServerName.split(":");
						
						uriContextPath += tmpStr[0] + ":" + OppfProperties.getProperty("Globals.sslPort.cpt");
					}else{
						uriContextPath += sptServerName;
					}		
					cmmEmailSendVO.setUriContextPath(uriContextPath); //로그인 페이지 : https
					
					cmmEmailSendVO.setSystemKind("cpt");//개인 포털로 셋팅[spt:개인, cpt:기업, apt:admin] -> session이 아닌 target이 있을 경우 셋팅
					
					//메일 발송
					result = cptSendTempPasswordEmail(cmmEmailSendVO, request);
										
				}catch(Exception e){
					result = -1;
				}
			}
			
		}else{
			result = cptUserManageService.updateCptUserManage(paramVO);
		}
		
		model.addAttribute("result", result);
		model.addAttribute("operatorProfileRegNo", operatorProfileRegNo);
		
        return "jsonView";
    }
	
	/**
     * @Method Name        : saveCptUserManagePwd
     * @Method description : 기업회원 비밀번호 초기화 저장
     * @param              : 
     * @return             : AptUserManageVO
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cptUsr/saveCptUserManagePwd.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String saveCptUserManagePwd(@ModelAttribute("CptUserManageVO") CptUserManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());        
        
        int result = 0;
    
		//등록시에는 임시비밀번호 발급 후 저장 완료 시 해당 사용자에게 메일을 발송한다.
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
		
		//비밀번호 셋팅
		paramVO.setOperatorPassword(imsiPassword);
		
		//비밀번호 변경 예정 일
		paramVO.setOperatorExpirePasswordDate(OppfProperties.getProperty("Globals.user.policy.password.expire"));
		paramVO.setOperatorFinalPasswordDate(OppfProperties.getProperty("Globals.user.policy.password.final"));
		
		//비밀번호 저장
		result = cptUserManageService.saveCptUserManagePwd(paramVO);
		
		//System.out.println("result==>"+result);  
				
		//메일 발송
		if(result > 0){
			try{
				//이메일발송정보 셋팅
				CmmEmailSendVO cmmEmailSendVO = new CmmEmailSendVO();
				cmmEmailSendVO.setEmailSendType("G016004"); //이메일 유형 - com_email_temp_info 이메일 템플릿 조회
				cmmEmailSendVO.setReceiverName(paramVO.getOperatorNameKor()); //받는 사람 이름(실제 이메일에 셋팅됩니다.)
				cmmEmailSendVO.setReceiverEmail(paramVO.getOperatorEmail()); //받는 사람 이메일(실제 이메일에 셋팅되고, 이 주소로 이메일이 발송됩니다.)
				cmmEmailSendVO.setSenderKind("G017001"); //발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
				cmmEmailSendVO.setSenderId(loginVO.getAdmin_profile_reg_no()); //발신자 ID
				cmmEmailSendVO.setReceiverKind("G018002"); //수신자 종류 - G018001:일반사용자, G018002:기업사용자, G018003 :admin
				cmmEmailSendVO.setReceiverId(paramVO.getOperatorProfileRegNo()); //수신자 ID
				cmmEmailSendVO.setSendId(loginVO.getAdmin_profile_reg_no()); //최초 발신자 ID
				cmmEmailSendVO.setCreateId(loginVO.getAdmin_profile_reg_no()); //생성자ID
				cmmEmailSendVO.setUpdateId(loginVO.getAdmin_profile_reg_no()); //수정자ID
				cmmEmailSendVO.setCustomerPassword(imsiPassword); //임시비밀번호
				
				String uriContextPath = "https://";
				String sptServerName = OppfProperties.getProperty("Globals.domain.cpt");
				if(sptServerName.indexOf(":") >= 0){
					String [] tmpStr = sptServerName.split(":");
					
					uriContextPath += tmpStr[0] + ":" + OppfProperties.getProperty("Globals.sslPort.cpt");
				}else{
					uriContextPath += sptServerName;
				}		
				cmmEmailSendVO.setUriContextPath(uriContextPath); //로그인 페이지 : https
				
				cmmEmailSendVO.setSystemKind("cpt");//개인 포털로 셋팅[spt:개인, cpt:기업, apt:admin] -> session이 아닌 target이 있을 경우 셋팅
				
				//메일 발송
				result = cptSendTempPasswordEmail(cmmEmailSendVO, request);
									
			}catch(Exception e){
				result = -1;
			}
		}
			
		model.addAttribute("result", result);
		
        return "jsonView";
    }
	
	/**
     * @Method Name        : cptSendTempPasswordEmail
     * @Method description : 기업회원 임시비밀번호 메일 발송
     * @param              : 
     * @return             : int
     * @throws             : Exception
     */
	public int cptSendTempPasswordEmail(CmmEmailSendVO cmmEmailSendVO, HttpServletRequest request) throws Exception{
		int result = 0;
				
		//1.이메일 템플릿 조회 -> 2.이메일발송 유형 구분 -> 3.메일 발송 -> (성공시) -> 4.com_email_send_info 로그적재 관련
		CmmEmailSendVO eamilResultVO = cmmEmailSendService.cmmEmailSend(cmmEmailSendVO, request);
		if("100".equals(eamilResultVO.getMailCode())){ //데이터 연동 성공
			result = 1;
		}else{
			result = -1;
		}        
		
		return result;
	}
}
