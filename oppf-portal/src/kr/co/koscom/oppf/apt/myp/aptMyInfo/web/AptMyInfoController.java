package kr.co.koscom.oppf.apt.myp.aptMyInfo.web;

import kr.co.koscom.oppf.apt.myp.aptMyInfo.service.AptMyInfoService;
import kr.co.koscom.oppf.apt.myp.aptMyInfo.service.AptMyInfoVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptMyInfoController.java
* @Comment  : [개인회원정보]정보관리를 위한 Controller 클래스
* @author   : 포털 유제량
* @since    : 2016.06.28
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.28  유제량        최초생성
*
*/
@Controller
public class AptMyInfoController {
    @Resource(name = "AptMyInfoService")
    private AptMyInfoService aptMyInfoService;
    
    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
    
    @Resource(name = "MypSvcApplService")
    private MypSvcApplService mypSvcApplService;
    
    @Resource(name = "MbrRegService")
    private MbrRegService mbrRegService;
	
    /* 동기식.do 요청 START */
    
    /**
     * @Method Name        : aptMyPwMod
     * @Method description : [개인회원정보]비밀번호변경 팝업창을 오픈한다.
     * @param              : AptMyInfoVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/myp/aptMyInfo/aptMyPwMod.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String aptMyPwMod(@ModelAttribute("aptMyInfoVO") AptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "apt/myp/aptMyInfo/aptMyPwMod";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String adminProfileRegNo = loginVO.getAdmin_profile_reg_no();
        if(OppfStringUtil.isEmpty(adminProfileRegNo)){
            return modelView;
        }
        
        model.addAttribute("paramVO", paramVO);
        
        return modelView;
    }
    
    /* 동기식.do 요청 END */
    
    
    /* 비동기식.ajax 요청 START */
    
    /**
     * @Method Name        : checkId
     * @Method description : [개인회원정보:ID중복확인]ID정보를 조회한다.
     * @param              : NotiVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/apt/myp/aptMyInfo/checkPw.ajax")
    private String checkId(@ModelAttribute("aptMyInfoVO") AptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        String adminProfileRegNo = loginVO.getAdmin_profile_reg_no();
        if(OppfStringUtil.isEmpty(adminProfileRegNo)){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        paramVO.setAdminProfileRegNo(adminProfileRegNo);
    	
        String ids = aptMyInfoService.selectCheckPw(paramVO);
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
     * @Method Name        : updateAptMyPwMod
     * @Method description : [개인회원정보:비밀번호변경]을 한다.
     * @param              : AptMyInfoVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/apt/myp/aptMyInfo/updateAptMyPwMod.ajax")
    private String updateAptMyPwMod(@ModelAttribute("aptMyInfoVO") AptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        String adminProfileRegNo = loginVO.getAdmin_profile_reg_no();
        if(OppfStringUtil.isEmpty(adminProfileRegNo)){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }  
        
        //session reg no
        paramVO.setAdminProfileRegNo(adminProfileRegNo);
        
        
        //비밀번호 변경 예정 일
        paramVO.setAdmin_expire_password_date(OppfProperties.getProperty("Globals.user.policy.password.expire"));
        paramVO.setAdmin_final_password_date(OppfProperties.getProperty("Globals.user.policy.password.final"));
        int rs = aptMyInfoService.updateAptMyPwMod(paramVO);        
        model.addAttribute("rs", rs);
        
        return "jsonView";
    }
    
    /* 비동기식.ajax 요청 END */

}
