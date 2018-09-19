package kr.co.koscom.oppf.cpt.myp.cptMyInfo.web;

import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.cpt.myp.cptMyInfo.service.CptMyInfoService;
import kr.co.koscom.oppf.cpt.myp.cptMyInfo.service.CptMyInfoVO;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptMyInfoController.java
* @Comment  : [기업회원정보]정보관리를 위한 Controller 클래스
* @author   : 포털 유제량
* @since    : 2016.06.29
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.29  유제량        최초생성
*
*/
@Controller
public class CptMyInfoController {
    @Resource(name = "CptMyInfoService")
    private CptMyInfoService cptMyInfoService;
    
    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
    
    @Resource(name = "MypSvcApplService")
    private MypSvcApplService mypSvcApplService;
    
    @Resource(name = "MbrRegService")
    private MbrRegService mbrRegService;
	
    /* 동기식.do 요청 START */
    
    /**
     * @Method Name        : cptMyPwConfrm
     * @Method description : [기업회원정보]페이지로 이동한다.(회원정보 확인 전 비밀번호 확인)
     * @param              : CptMyInfoVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cpt/myp/cptMyInfo/cptMyPwConfrm.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String cptMyPwConfrm(@ModelAttribute("cptMyInfoVO") CptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String modelView = "cpt/myp/cptMyInfo/cptMyPwConfrm";
    	
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String companyProfileRegNo = loginVO.getCompany_profile_reg_no();
        String operatorProfileRegNo = loginVO.getOperator_profile_reg_no();
        if(OppfStringUtil.isEmpty(operatorProfileRegNo)){
            return modelView;
        }
        
        //사용자 정보 셋팅
        paramVO.setCompanyProfileRegNo(companyProfileRegNo);
        paramVO.setOperatorProfileRegNo(operatorProfileRegNo);
    	
        model.addAttribute("paramVO", paramVO);
        
        return modelView;
    }
    
    /**
     * @Method Name        : cptMyInfo
     * @Method description : [기업회원정보]페이지로 이동한다.
     * @param              : CptMyInfoVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cpt/myp/cptMyInfo/cptMyInfo.do", method = {RequestMethod.POST, RequestMethod.GET})
    private String cptMyInfo(@ModelAttribute("cptMyInfoVO") CptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	//modelView
        String modelView = "cpt/myp/cptMyInfo/cptMyInfo";
    	
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String companyProfileRegNo = loginVO.getCompany_profile_reg_no();
        String operatorProfileRegNo = loginVO.getOperator_profile_reg_no();
        if(OppfStringUtil.isEmpty(operatorProfileRegNo)){
            return modelView;
        }
        
        //사용자 정보 셋팅
        paramVO.setCompanyProfileRegNo(companyProfileRegNo);
        paramVO.setOperatorProfileRegNo(operatorProfileRegNo);
        
        //상세조회        
        CptMyInfoVO resultDetail = cptMyInfoService.selectCptMyInfo(paramVO);
        
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
        
        model.addAttribute("paramVO", resultDetail);
        
        return modelView;
    }
    
    /**
     * @Method Name        : cptMyPwMod
     * @Method description : [기업회원정보]비밀번호변경 팝업창을 오픈한다.
     * @param              : CptMyInfoVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cpt/myp/cptMyInfo/cptMyPwMod.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String cptMyPwMod(@ModelAttribute("cptMyInfoVO") CptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "cpt/myp/cptMyInfo/cptMyPwMod";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String companyProfileRegNo = loginVO.getCompany_profile_reg_no();
        String operatorProfileRegNo = loginVO.getOperator_profile_reg_no();
        if(OppfStringUtil.isEmpty(operatorProfileRegNo)){
            return modelView;
        }
        
        //사용자 정보 셋팅
        paramVO.setCompanyProfileRegNo(companyProfileRegNo);
        paramVO.setOperatorProfileRegNo(operatorProfileRegNo);
        
        model.addAttribute("loginVO", loginVO);
        model.addAttribute("paramVO", paramVO);
        
        //login session 삭제
        request.getSession().setAttribute("LoginVO", null);			//로그인 정보
        
        LoginVO pwModLoginVO = (LoginVO) request.getSession().getAttribute("PwModLoginVO");
        if(pwModLoginVO != null) request.getSession().setAttribute("PwModLoginVO", null);
        
        request.getSession().setAttribute("PwModLoginVO", loginVO);	//임시비밀번호 용 로그인 session 셋팅
        
        return modelView;
    }
    
    /**
     * @Method Name        : cptMyPwModNextChange
     * @Method description : [기업회원정보]비밀번호변경 팝업창에서 다음에 변경 클릭 시 호출 -> 메인이동
     * @param              : CptMyInfoVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cpt/myp/cptMyInfo/cptMyPwModNextChange.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String cptMyPwModNextChange(@ModelAttribute("cptMyInfoVO") CptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "redirect:/cpt/cmm/mainView.do";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("PwModLoginVO");
        if(loginVO == null){
        	return modelView;
        }
        
        //login session 셋팅
        request.getSession().setAttribute("LoginVO", loginVO);
        
        return modelView;
    }
    
    /* 동기식.do 요청 END */
    
    
    /* 비동기식.ajax 요청 START */
    
    /**
     * @Method Name        : checkId
     * @Method description : [기업회원정보:ID중복확인]ID정보를 조회한다.
     * @param              : NotiVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/cpt/myp/cptMyInfo/checkPw.ajax")
    private String checkId(@ModelAttribute("cptMyInfoVO") CptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	LoginVO pwModLoginVO = (LoginVO) request.getSession().getAttribute("PwModLoginVO");
        	if(pwModLoginVO == null){
	        	model.addAttribute("error", "-1");
	        	return "jsonView";
        	}else{
        		loginVO = pwModLoginVO;
        	}
        }
        String companyProfileRegNo = loginVO.getCompany_profile_reg_no();
        String operatorProfileRegNo = loginVO.getOperator_profile_reg_no();
        if(OppfStringUtil.isEmpty(operatorProfileRegNo)){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
    	
        //사용자 정보 셋팅
        paramVO.setCompanyProfileRegNo(companyProfileRegNo);
        paramVO.setOperatorProfileRegNo(operatorProfileRegNo);
        
        String ids = cptMyInfoService.selectCheckPw(paramVO);
        String checkPw = "0";
        if(null == ids || ids.length()==0){
            checkPw = "0";
        }else{
            checkPw = ids;
        }
        model.addAttribute("checkPw", checkPw);
        
        return "jsonView";
    }
    
    /**
     * @Method Name        : updateCptMyInfo
     * @Method description : [기업회원정보:수정]을 한다.
     * @param              : CptMyInfoVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/cpt/myp/cptMyInfo/updateCptMyInfo.ajax")
    private String updateCptMyInfo(@ModelAttribute("cptMyInfoVO") CptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        String companyProfileRegNo = loginVO.getCompany_profile_reg_no();
        String operatorProfileRegNo = loginVO.getOperator_profile_reg_no();
        if(OppfStringUtil.isEmpty(operatorProfileRegNo)){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }        
        
        //regno 셋팅
        paramVO.setCompanyProfileRegNo(companyProfileRegNo);
        paramVO.setOperatorProfileRegNo(operatorProfileRegNo);
        
        //상세조회
        CptMyInfoVO resultDetail = cptMyInfoService.selectCptOperatorInfoProfile(paramVO);
        model.addAttribute("resultDetail", resultDetail);
        
        //비밀번호 변경 예정 일
        paramVO.setOperator_expire_password_date(OppfProperties.getProperty("Globals.user.policy.password.expire"));
        paramVO.setOperator_final_password_date(OppfProperties.getProperty("Globals.user.policy.password.final"));
        int rs = cptMyInfoService.updateCptMyInfo(paramVO);
        model.addAttribute("rs", rs);
        
        //session 삭제
        request.getSession().setAttribute("LoginVO", null); //로그인 정보
        
        return "jsonView";
    }
    
    /**
     * @Method Name        : updateCptMyPwMod
     * @Method description : [기업회원정보:비밀번호변경]을 한다.
     * @param              : CptMyInfoVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/cpt/myp/cptMyInfo/updateCptMyPwMod.ajax")
    private String updateCptMyPwMod(@ModelAttribute("cptMyInfoVO") CptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	LoginVO pwModLoginVO = (LoginVO) request.getSession().getAttribute("PwModLoginVO");
        	if(pwModLoginVO == null){
	        	model.addAttribute("error", "-1");
	        	return "jsonView";
        	}else{
        		loginVO = pwModLoginVO;
        	}
        }
        String companyProfileRegNo = loginVO.getCompany_profile_reg_no();
        String operatorProfileRegNo = loginVO.getOperator_profile_reg_no();
        if(OppfStringUtil.isEmpty(companyProfileRegNo) || OppfStringUtil.isEmpty(operatorProfileRegNo)){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }  
        
        //login session 
        paramVO.setCompanyProfileRegNo(companyProfileRegNo);
        paramVO.setOperatorProfileRegNo(operatorProfileRegNo);
        
        //비밀번호 변경 예정 일
        paramVO.setOperator_expire_password_date(OppfProperties.getProperty("Globals.user.policy.password.expire"));
        paramVO.setOperator_final_password_date(OppfProperties.getProperty("Globals.user.policy.password.final"));
        int rs = cptMyInfoService.updateCptMyPwMod(paramVO);        
        model.addAttribute("rs", rs);
        
        return "jsonView";
    }
    
    /**
     * @Method Name        : checkPwProcs
     * @Method description : [기업회원정보:ID중복확인]ID정보를 조회 후 회원정보 페이지 분기처리를 한다.
     * @param              : CptMyInfoVO,HttpServletRequest,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cpt/myp/cptMyInfo/checkPwProcs.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String checkPwProcs(@ModelAttribute("cptMyInfoVO") CptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        String companyProfileRegNo = loginVO.getCompany_profile_reg_no();
        String operatorProfileRegNo = loginVO.getOperator_profile_reg_no();
        if(OppfStringUtil.isEmpty(operatorProfileRegNo)){
            model.addAttribute("error", "-1");
            return "jsonView";
        }        
        
        //regno 셋팅
        paramVO.setCompanyProfileRegNo(companyProfileRegNo);
        paramVO.setOperatorProfileRegNo(operatorProfileRegNo);
        
        String ids = cptMyInfoService.selectCheckPw(paramVO);
        String checkPw = "0";
        if(null == ids || ids.length()==0){
            checkPw = "0";
        }else{
            if(!"1".equals(ids)){
                checkPw = ids;
                model.addAttribute("rtCheckPw", checkPw);
                return "forward:/cpt/myp/cptMyInfo/cptMyPwConfrm.do";
            }else{                
                return "forward:/cpt/myp/cptMyInfo/cptMyInfo.do";
            }
        }
        
        return "forward:/cpt/myp/cptMyInfo/cptMyPwConfrm.do";        
    }
    
    /* 비동기식.ajax 요청 END */

}
