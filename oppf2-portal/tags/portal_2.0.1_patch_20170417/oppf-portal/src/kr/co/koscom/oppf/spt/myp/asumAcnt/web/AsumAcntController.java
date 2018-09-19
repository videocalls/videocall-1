package kr.co.koscom.oppf.spt.myp.asumAcnt.web;

import java.util.HashMap;
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

import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenService;
import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.myp.asumAcnt.service.AsumAcntService;
import kr.co.koscom.oppf.spt.myp.asumAcnt.service.AsumAcntVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerAccountProfileVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SvcApplController.java
* @Comment  : [마이페이지:가상계좌]정보관리를 위한 Controller 클래스
* @author   : 포털 이환덕
* @since    : 2016.05.27
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.27  이환덕        최초생성
*
*/
@Controller
public class AsumAcntController {
    
    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
    
    @Resource(name = "AsumAcntService")
    private AsumAcntService asumAcntService;
    
    @Resource(name = "MbrRegService")
    private MbrRegService mbrRegService;
    
    @Resource(name = "ComOauthTokenService")
    private ComOauthTokenService comOauthTokenService;
    
    
    private static final Logger log = Logger.getLogger(AsumAcntController.class);
    
    /* 동기식.do 요청 START */
    
    /**
     * @Method Name        : asumAcnt
     * @Method description : [마이페이지:가상계좌발급]페이지로 이동한다.
     * @param              : AsumAcntVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/myp/asumAcnt/asumAcnt.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String asumAcnt(
       @ModelAttribute("AsumAcntVO") AsumAcntVO paramVO
       ,HttpServletRequest  request
       ,HttpServletResponse response
       ,ModelMap model
    )throws Exception{
        
        String exconn = OppfProperties.getProperty("Globals.domain.exconn");
        model.addAttribute("exconn", exconn);
        
        //1.로그인관련
        //String loginUri = "/cmm/commonLogin.do";
        
        //modelView
        String modelView = "spt/myp/asumAcnt/asumAcnt";
        
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            //return "forward:"+loginUri;
            return modelView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            //return "forward:"+loginUri;
            return modelView;
        }
        String customerId = loginVO.getId();
        if(OppfStringUtil.isEmpty(customerId)){
            return modelView;
        }
        
        model.addAttribute("customerId", customerId);
        model.addAttribute("customerRegNo", customerRegNo);
        model.addAttribute("loginVO", loginVO);
        model.addAttribute("paramVO", paramVO);
        
        //2.[사용자]정보 CI&DN 취득
        String customerCi = "";
        String customerDn = "";
        MbrRegVO pMbrRegVO = new MbrRegVO();
        pMbrRegVO.setCustomerRegNo(customerRegNo);
        List<MbrRegVO> rsVerifyList = mbrRegService.selectSptCustomerVerifyProfileList(pMbrRegVO);
        if(rsVerifyList != null){
            for(int i=0; i<rsVerifyList.size(); i++){
                String customerVerifyType = rsVerifyList.get(i).getCustomerVerifyType();
                if("G007001".equals(customerVerifyType)){ //G007:회원검증종류(001:본인인증,002;공인인증서등록)
                    customerCi = rsVerifyList.get(i).getCustomerVerify();
                    
                }else if("G007002".equals(customerVerifyType)){ //G007:회원검증종류(001:본인인증,002;공인인증서등록)
                    customerDn = rsVerifyList.get(i).getCustomerVerify();
                }
            }
        }
        log.debug("2.[사용자]정보 취득後:customerCi="+customerCi);
        log.debug("2.[사용자]정보 취득後:customerDn="+customerDn);
        model.addAttribute("customerCi", customerCi);
        model.addAttribute("customerDn", customerDn);
        
        //5.oauthToken정보 취득
        ComOauthTokenVO pComOauthTokenVO = new ComOauthTokenVO();
        pComOauthTokenVO.setCustomerRegNo(customerRegNo);
        ComOauthTokenVO rsComOauthTokenVO = comOauthTokenService.selectComOauthTokenInfo(pComOauthTokenVO);
        model.addAttribute("rsComOauthTokenVO", rsComOauthTokenVO);
        
        return modelView;
    }
    
    /* 동기식.do 요청 END */
    
    
    /* 비동기식.ajax 요청 START */
    
   /**
     * @Method Name        : selectAsumAcntList
     * @Method description : [마이페이지:가상계좌발급:목록]정보를 조회한다.
     * @param              : NotiVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/myp/asumAcnt/selectAsumAcntList.ajax")
    private String selectAsumAcntList(
        @ModelAttribute("AsumAcntVO") AsumAcntVO paramVO
       ,HttpServletRequest request
       ,ModelMap model
    )throws Exception{
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
        
        paramVO.setListOrder("ifnull(c.exposure_order, 'Z') asc, c.company_name_kor_alias asc, a.customer_vtaccount_reg_date desc");
        
        HashMap<String,Object> rsSptCustAccListMap = asumAcntService.selectSptCustAccInfo(paramVO);
        int sptCustAccListTotCnt = (int) rsSptCustAccListMap.get("rsTotCnt");
        List<AsumAcntVO> sptCustAccList = (List<AsumAcntVO>) rsSptCustAccListMap.get("rsList");
        for(int i=0; i<sptCustAccList.size(); i++){
            String realaccountNo = sptCustAccList.get(i).getCustomerRealaccountNo();
            if(!OppfStringUtil.isEmpty(realaccountNo)){
                sptCustAccList.get(i).setCustomerRealaccountNoMask(OppfStringUtil.maskForAccNo(realaccountNo));
                sptCustAccList.get(i).setCustomerRealaccountNoEncryption(OppfStringUtil.base64Incoding(realaccountNo));
                log.debug("realaccountNo="+realaccountNo);
                log.debug("mask:realaccountNo="+sptCustAccList.get(i).getCustomerRealaccountNoMask());
                log.debug("encoding:realaccountNo="+sptCustAccList.get(i).getCustomerRealaccountNoEncryption());
            }
        }
        model.addAttribute("resultListTotCnt", sptCustAccListTotCnt);
        model.addAttribute("resultList", sptCustAccList);
        model.addAttribute("paramVO", paramVO);
        return "jsonView";
    }
    
   /**
     * @Method Name        : updateSptCustomerAccountProfile
     * @Method description : [가상계좌발급]정보를 수정을 한다.
     * @param              : MbrRegVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/myp/asumAcnt/updateSptCustomerAccountProfile.ajax")
    private String updateSptCustomerAccountProfile(
        @ModelAttribute("SptCustomerAccountProfileVO") SptCustomerAccountProfileVO paramVO
       ,HttpServletRequest request
       ,ModelMap model
    )throws Exception{
        log.info("------------- updateSptCustomerAccountProfile.ajax START -------------------");
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
        
        int rs = asumAcntService.updateSptCustomerAccountProfile(paramVO);
        log.debug("rs="+rs);
        model.addAttribute("rs", rs);
        
        log.info("------------- updateSptCustomerAccountProfile.ajax END ---------------------");
        return "jsonView";
    }
    
   /**
     * @Method Name        : deleteSptCustomerAccountProfile
     * @Method description : [가상계좌발급]정보를 삭제를 한다.
     * @param              : MbrRegVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/myp/asumAcnt/deleteSptCustomerAccountProfile.ajax")
    private String deleteSptCustomerAccountProfile(
        @ModelAttribute("SptCustomerAccountProfileVO") SptCustomerAccountProfileVO paramVO
       ,HttpServletRequest request
       ,ModelMap model
    )throws Exception{
        log.info("------------- deleteSptCustomerAccountProfile.ajax START -------------------");
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
        
        int rs = asumAcntService.deleteSptCustomerAccountProfile(paramVO);
        log.debug("rs="+rs);
        model.addAttribute("rs", rs);
        
        log.info("------------- deleteSptCustomerAccountProfile.ajax END ---------------------");
        return "jsonView";
    }
    /* 비동기식.ajax 요청 END */
    
}
