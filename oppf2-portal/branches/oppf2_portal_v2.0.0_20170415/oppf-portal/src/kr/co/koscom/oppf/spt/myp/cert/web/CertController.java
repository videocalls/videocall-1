package kr.co.koscom.oppf.spt.myp.cert.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.myp.asumAcnt.service.AsumAcntVO;
import kr.co.koscom.oppf.spt.myp.cert.service.CertService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegTermsContentVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SvcApplController.java
* @Comment  : [마이페이지:공인인증서]정보관리를 위한 Controller 클래스
* @author   : 포털 이환덕
* @since    : 2016.05.30
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.30  이환덕        최초생성
*
*/
@Controller
public class CertController {
    
    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
    
    @Resource(name = "MbrRegService")
    private MbrRegService mbrRegService;
    
    @Resource(name = "CertService")
    private CertService certService;
    
    private static final Logger log = Logger.getLogger(CertController.class);
    
    /* 동기식.do 요청 START */
    
    /**
     * @Method Name        : cert
     * @Method description : [마이페이지:공인인증서]관리페이지로 이동한다.
     * @param              : AsumAcntVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/myp/cert/cert.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String cert(
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
        String modelView = "spt/myp/cert/cert";
        
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
        
        //3.약관목록취득(G008001:서비스이용약관, G008002:개인정보취급방침, G008003:개인정보수집이용동의, G008010:공인인증서등록및약관동의)
        MbrRegTermsContentVO pMbrRegTermsContentVO = new MbrRegTermsContentVO();
        List<String> searchTempList = new ArrayList<String>();
        searchTempList.add("G008010");
        pMbrRegTermsContentVO.setSearchCustomerTermsTypeList(searchTempList);
        List<MbrRegTermsContentVO> termsContentList = mbrRegService.selectSptCustomerTermsContentList(pMbrRegTermsContentVO);
        model.addAttribute("termsContentList", termsContentList);
        
        //4.본인인증단계(아이핀인증, 휴대폰인증)에서 저장한 사용자이름을 조회해 와서 공인인증등록시 공인인증서 DN값의 이름과 비교합니다.
        MbrRegVO requestCustomerInfoVO = new MbrRegVO();
        requestCustomerInfoVO.setCustomerRegNo(customerRegNo);
        MbrRegVO resultCustomerInfoVO = mbrRegService.selectSptCustomerInfoProfile(requestCustomerInfoVO);
        model.addAttribute("resultCustomerInfoVO", resultCustomerInfoVO);
        
        //return "spt/myp/cert/cert";
        return modelView;
    }
    
    /* 동기식.do 요청 END */
    
    
    /* 비동기식.ajax 요청 START */
   /**
     * @Method Name        : updateSptCustomerVerifyAndTermsInfo
     * @Method description : [마이페이지:공인인증서+약관]정보를 수정을 한다.
     * @param              : MbrRegVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/myp/cert/updateSptCustomerVerifyAndTermsInfo.ajax")
    private String updateSptCustomerVerifyAndTermsInfo(
         @RequestBody MbrRegVO paramVO
        ,HttpServletRequest request
        ,ModelMap model
    )throws Exception{
        log.info("------------- updateSptCustomerVerifyProfile.ajax START -------------------");
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
        
        int rs = certService.updateSptCustomerVerifyAndTermsInfo(paramVO);
        log.debug("rs="+rs);
        model.addAttribute("rs", rs);
        
        log.info("------------- updateSptCustomerVerifyProfile.ajax END ---------------------");
        return "jsonView";
    }
    
    /* 비동기식.ajax 요청 END */
    
}
