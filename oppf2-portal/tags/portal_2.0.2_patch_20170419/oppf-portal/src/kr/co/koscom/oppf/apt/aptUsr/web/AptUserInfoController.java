package kr.co.koscom.oppf.apt.aptUsr.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.apt.aptUsr.service.AptUserInfoService;
import kr.co.koscom.oppf.apt.aptUsr.service.AptUserInfoVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptUserInfoController.java
* @Comment  : admin 포털 내정보 관리 Controller
* @author   : 신동진
* @since    : 2016.05.24
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.24  신동진        최초생성
*
*/
@Controller
public class AptUserInfoController {
	
	private static final Logger log = Logger.getLogger(AptUserInfoController.class);
	
	@Resource(name = "AptUserInfoService")
    private AptUserInfoService aptUserInfoService;
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	
    /**
     * @Method Name        : aptUserPwConfirm
     * @Method description : 내 정보 관리 password 확인 화면 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/aptUsr/aptUserInfoPwConfirm.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String aptUserList(@ModelAttribute("AptUserInfoVO") AptUserInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/aptUsr/aptUserInfoPwConfirm";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
		
		model.addAttribute("AptUserInfoVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : chkAptUserInfoPwConfirm
     * @Method description : admin 포탈 회원의 비밀번호 체크
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/aptUsr/chkAptUserInfoPwConfirm.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String chkAptUserInfoPwConfirm(@ModelAttribute("AptUserInfoVO") AptUserInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setAdminProfileRegNo(loginVO.getAdmin_profile_reg_no());
		
		int result = aptUserInfoService.chkAptUserInfoPwConfirm(paramVO);
        
		model.addAttribute("result", result);
		model.addAttribute("adminProfileRegNo", paramVO.getAdminProfileRegNo());
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : aptUserInfoDtl
     * @Method description : admin 포털 회원 상세
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/aptUsr/aptUserInfoDtl.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String aptUserDtl(@ModelAttribute("AptUserInfoVO") AptUserInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/aptUsr/aptUserInfoDtl";
        
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
		
        AptUserInfoVO resultDetail = aptUserInfoService.selectAptUserInfoDtl(paramVO);
		
		model.addAttribute("resultDetail", resultDetail);
		model.addAttribute("AptUserInfoVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : updateAptUserInfo
     * @Method description : admin 포털 회원정보 수정
     * @param              : 
     * @return             : AptUserManageVO
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/aptUsr/updateAptUserInfo.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String updateAptUserInfo(@ModelAttribute("AptUserInfoVO") AptUserInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setUpdateId(loginVO.getAdmin_profile_reg_no());
		
		//비밀번호 변경 예정 일
		paramVO.setAdminExpirePasswordDate(OppfProperties.getProperty("Globals.user.policy.password.expire"));
		paramVO.setAdminFinalPasswordDate(OppfProperties.getProperty("Globals.user.policy.password.final"));
				
		//비밀번호 변경 시
		if(!OppfStringUtil.isEmpty(paramVO.getAdminPassword())){
			int pwdResult = aptUserInfoService.chkAptUserInfoPwConfirm(paramVO);
			//기존비밀번호와 변경비밀번호가 같을 경우
			if(pwdResult > 0){
				model.addAttribute("pwdResult", pwdResult);
				
		        return "jsonView";
			}
		}
						
		//회원정보 수정
		int result = aptUserInfoService.updateAptUserInfo(paramVO);		 		
		model.addAttribute("result", result);
		
        return "jsonView";
    }
	
	
}
