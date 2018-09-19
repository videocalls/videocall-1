package kr.co.koscom.oppf.spt.myp.sptMyInfo.web;

import kr.co.koscom.oppf.cmm.service.*;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.cmm.service.SptLoginService;
import kr.co.koscom.oppf.spt.myp.sptMyInfo.service.SptMyInfoService;
import kr.co.koscom.oppf.spt.myp.sptMyInfo.service.SptMyInfoVO;
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
* @FileName : SptMyInfoController.java
* @Comment  : [개인회원정보]정보관리를 위한 Controller 클래스
* @author   : 포털 유제량
* @since    : 2016.05.09
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.09  유제량        최초생성
*
*/
@Controller
public class SptMyInfoController {
    @Resource(name = "SptMyInfoService")
    private SptMyInfoService sptMyInfoService;
    
    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
    
    @Resource(name = "MypSvcApplService")
    private MypSvcApplService mypSvcApplService;
    
    @Resource(name = "MbrRegService")
    private MbrRegService mbrRegService;
    
    @Resource(name = "SptLoginService")
    private SptLoginService sptLoginService;
	
    @Resource(name = "CmmEmailSendService")
    private CmmEmailSendService cmmEmailSendService;
    
    /* 동기식.do 요청 START */
    
    /**
     * @Method Name        : sptMyPwConfrm
     * @Method description : [개인회원정보]페이지로 이동한다.(회원정보 확인 전 비밀번호 확인)
     * @param              : SptMyInfoVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/spt/myp/sptMyInfo/sptMyPwConfrm.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String sptMyPwConfrm(@ModelAttribute("sptMyInfoVO") SptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String modelView = "spt/myp/sptMyInfo/sptMyPwConfrm";
    	
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelView;
        }
    	
        model.addAttribute("paramVO", paramVO);
        
        return modelView;
    }
    
    /**
     * @Method Name        : sptMyInfo
     * @Method description : [개인회원정보]페이지로 이동한다.
     * @param              : SptMyInfoVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/spt/myp/sptMyInfo/sptMyInfo.do", method = {RequestMethod.POST, RequestMethod.GET})
    private String sptMyInfo(@ModelAttribute("sptMyInfoVO") SptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	//modelView
        String modelView = "spt/myp/sptMyInfo/sptMyInfo";
    	
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelView;
        }
        
        //사용자 정보 셋팅
        paramVO.setCustomerRegNo(customerRegNo);
        
        //상세조회        
        SptMyInfoVO resultDetail = sptMyInfoService.selectSptMyInfo(paramVO);
        
        paramVO.setCustomerNameKor(resultDetail.getCustomerNameKor());
        paramVO.setCustomerId(resultDetail.getCustomerId());
        paramVO.setCustomerBirthDay(resultDetail.getCustomerBirthDay());
        paramVO.setCustomerSex(resultDetail.getCustomerSex()); //공통코드G012(001:남자,002:여자)
        paramVO.setCustomerEmail(resultDetail.getCustomerEmail());
        paramVO.setCustomerPhone(resultDetail.getCustomerPhone());
        paramVO.setCustomerRegDate(resultDetail.getCustomerRegDate());
        paramVO.setCustomerEmailReceiveYn(resultDetail.getCustomerEmailReceiveYn());
        
        //dn정보 셋팅
        paramVO.setCustomerVerifyDn(resultDetail.getCustomerVerifyDn());
        
        //otp정보 셋팅
        paramVO.setCustomerOtpYn(resultDetail.getCustomerOtpYn());
        paramVO.setCustomerOtpId(resultDetail.getCustomerOtpId());
        
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
        
        //셋팅 공통코드:성별
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G012");//성별
        List<CmmFuncVO> sexList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        if(sexList != null){
            for(int i=0; i<sexList.size(); i++){
                String code   = (String)sexList.get(i).getSystem_code();
                String codeNm = (String)sexList.get(i).getCode_name_kor();
                if(code.equals(paramVO.getCustomerSex())){
                    paramVO.setCustomerSexName(codeNm);
                    //paramVO.setCustomerSexName(code);
                }
            }
        }
        model.addAttribute("sexList", sexList);
        model.addAttribute("paramVO", paramVO);
        
        return modelView;
    }
    
    /**
     * @Method Name        : sptMyPwMod
     * @Method description : [개인회원정보]비밀번호변경 팝업창을 오픈한다.
     * @param              : SptMyInfoVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/spt/myp/sptMyInfo/sptMyPwMod.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String sptMyPwMod(@ModelAttribute("sptMyInfoVO") SptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "spt/myp/sptMyInfo/sptMyPwMod";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelView;
        }
        
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
     * @Method Name        : sptMyPwModNextChange
     * @Method description : [개인회원정보]비밀번호변경 팝업창에서 다음에 변경 클릭 시 호출 -> 메인이동
     * @param              : SptMyInfoVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/spt/myp/sptMyInfo/sptMyPwModNextChange.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String sptMyPwModNextChange(@ModelAttribute("sptMyInfoVO") SptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "redirect:/spt/cmm/mainView.do";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("PwModLoginVO");
        if(loginVO == null){
        	return modelView;
        }
        
        //login session 셋팅
        request.getSession().setAttribute("LoginVO", loginVO);
        
        return modelView;
    }
    
    /**
     * @Method Name        : sptMbrSeces
     * @Method description : [개인회원정보]회원탈퇴 회원정보확인 페이지로 이동한다.
     * @param              : SptMyInfoVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/spt/myp/sptMyInfo/sptMbrSeces.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String sptMbrSeces(@ModelAttribute("sptMyInfoVO") SptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String modelView = "spt/myp/sptMyInfo/sptMbrSeces";
    	
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelView;
        }

        model.addAttribute("loginVO", loginVO);
        
        return modelView;
    }
    
    /**
     * @Method Name        : sptMbrSecesConfrm
     * @Method description : [개인회원정보]회원탈퇴 비밀번호확인 페이지로 이동한다.(회원탈퇴 전 비밀번호 확인)
     * @param              : SptMyInfoVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/spt/myp/sptMyInfo/sptMbrSecesConfrm.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String sptMbrSecesConfrm(@ModelAttribute("sptMyInfoVO") SptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "spt/myp/sptMyInfo/sptMbrSecesConfrm";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelView;
        }
        
        model.addAttribute("loginVO", loginVO);
        
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
    @RequestMapping("/spt/myp/sptMyInfo/checkPw.ajax")
    private String checkId(@ModelAttribute("sptMyInfoVO") SptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
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
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
    	
        //regno 셋팅
        paramVO.setCustomerRegNo(customerRegNo);
        
        String ids = sptMyInfoService.selectCheckPw(paramVO);
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
     * @Method Name        : updateSptMyInfo
     * @Method description : [개인회원정보:수정]을 한다.
     * @param              : SptMyInfoVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/spt/myp/sptMyInfo/updateSptMyInfo.ajax")
    private String updateSptMyInfo(@ModelAttribute("sptMyInfoVO") SptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }        
        
        //regno 셋팅
        paramVO.setCustomerRegNo(customerRegNo);
        
        //상세조회        
        SptMyInfoVO resultDetail = sptMyInfoService.selectSptCustomerInfoProfile(paramVO);
        model.addAttribute("resultDetail", resultDetail);
        
        //비밀번호 변경 예정 일
        paramVO.setCustomer_expire_password_date(OppfProperties.getProperty("Globals.user.policy.password.expire"));
        paramVO.setCustomer_final_password_date(OppfProperties.getProperty("Globals.user.policy.password.final"));
        int rs = sptMyInfoService.updateSptMyInfo(paramVO);
        model.addAttribute("rs", rs);
        
        //session 삭제
        request.getSession().setAttribute("LoginVO", null); //로그인 정보
        
        return "jsonView";
    }
    
    /**
     * @Method Name        : updateSptMyPwMod
     * @Method description : [개인회원정보:비밀번호변경]을 한다.
     * @param              : SptMyInfoVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/spt/myp/sptMyInfo/updateSptMyPwMod.ajax")
    private String updateSptMyPwMod(@ModelAttribute("sptMyInfoVO") SptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
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
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }  
        
        //session reg no
        paramVO.setCustomerRegNo(customerRegNo);
        
        //비밀번호 변경 예정 일
        paramVO.setCustomer_expire_password_date(OppfProperties.getProperty("Globals.user.policy.password.expire"));
        paramVO.setCustomer_final_password_date(OppfProperties.getProperty("Globals.user.policy.password.final"));
        int rs = sptMyInfoService.updateSptMyPwMod(paramVO);        
        model.addAttribute("rs", rs);
        
        return "jsonView";
    }
    
    /**
     * @Method Name        : updateSptMbrSecesInfo
     * @Method description : [개인회원정보:회원탈퇴]를 한다.
     * @param              : SptMyInfoVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/spt/myp/sptMyInfo/updateSptMbrSeces.ajax")
    private String updateSptMbrSeces(@ModelAttribute("sptMyInfoVO") SptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        int rs = sptMyInfoService.updateSptMbrSecesInfo(paramVO);
        
        SptMyInfoVO rsParamVO = sptMyInfoService.selectSptCustomerProfile(paramVO);
        model.addAttribute("rsParamVO", rsParamVO);
        
        return "jsonView";
    }
    
    /**
     * @Method Name        : updateSptMbrSecesEmail
     * @Method description : [개인회원정보:회원탈퇴]를 한다.(탈퇴메일발송을 한다.)
     * @param              : SptMyInfoVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/spt/myp/sptMyInfo/updateSptMbrSecesEmail.ajax")
    private String updateSptMbrSecesEmail(@ModelAttribute("cmmEmailSendVO") CmmEmailSendVO cmmEmailSendVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        
        //1.이메일 템플릿 조회 -> 2.이메일발송 유형 구분 -> 3.메일 발송 -> (성공시) -> 4.com_email_send_info 로그적재 관련
        cmmEmailSendService.cmmEmailSend(cmmEmailSendVO, request); 
        
        return "jsonView";
    }
    
    /**
     * @Method Name        : checkPwProcs
     * @Method description : [개인회원정보:ID중복확인]ID정보를 조회 후 회원정보 페이지 분기처리를 한다.
     * @param              : SptMyInfoVO,HttpServletRequest,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/spt/myp/sptMyInfo/checkPwProcs.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String checkPwProcs(@ModelAttribute("sptMyInfoVO") SptMyInfoVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        
        //regno 셋팅
        paramVO.setCustomerRegNo(customerRegNo);
        
        String ids = sptMyInfoService.selectCheckPw(paramVO);
        String checkPw = "0";
        if(null == ids || ids.length()==0){
            checkPw = "0";
        }else{
            if(!"1".equals(ids)){
                checkPw = ids;
                model.addAttribute("rtCheckPw", checkPw);
                return "forward:/spt/myp/sptMyInfo/sptMyPwConfrm.do";
            }else{                
                return "forward:/spt/myp/sptMyInfo/sptMyInfo.do";
            }            
        }
        
        return "forward:/spt/myp/sptMyInfo/sptMyPwConfrm.do";
    }
    
    /* 비동기식.ajax 요청 END */

}
