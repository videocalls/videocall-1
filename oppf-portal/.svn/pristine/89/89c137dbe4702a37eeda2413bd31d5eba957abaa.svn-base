package kr.co.koscom.oppf.spt.usr.svcAppl.web;

import kr.co.koscom.oppf.cmm.ars.service.CmmArsService;
import kr.co.koscom.oppf.cmm.intro.service.IntroService;
import kr.co.koscom.oppf.cmm.intro.service.IntroVO;
import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenService;
import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.cmm.util.OppfXssUtil;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SvcApplController.java
* @Comment  : [서비스신청]정보관리를 위한 Controller 클래스
* @author   : 포털 이환덕
* @since    : 2016.05.16
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.16  이환덕        최초생성
*
*/
@Slf4j
@Controller
public class AppController {
    
    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
    
    @Resource(name = "AppService")
    private AppService appService;

    @Resource(name = "SvcApplService")
    private SvcApplService svcApplService;
    
    @Resource(name = "MbrRegService")
    private MbrRegService mbrRegService;
    
    @Resource(name = "MypSvcApplService")
    private MypSvcApplService mypSvcApplService;
    
    @Resource(name = "ComOauthTokenService")
    private ComOauthTokenService comOauthTokenService;

    @Resource(name = "IntroService")
    private IntroService introService;

    @Resource(name = "CmmArsService")
    private CmmArsService cmmArsService;

    /**
     * 앱 사용 신청 화면으로 이동한다.
     * @param paramVO
     * @param request
     * @param model
     * @param redirectAttr
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/usr/svcAppl/appList.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String getAppListPage(@ModelAttribute("AppVO") AppVO paramVO, HttpServletRequest request, ModelMap model,
                               RedirectAttributes redirectAttr) throws Exception {

        String modelAndView = "spt/usr/svcAppl/appList";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("paramVO", paramVO);
            return modelAndView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelAndView;
        }

        //셋팅 공통코드 : app 구분
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G025");
        List<CmmFuncVO> appCategoryList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("appCategoryList", appCategoryList);

        paramVO.setPageRowsize(20);
        model.addAttribute("paramVO", paramVO);
        model.addAttribute("loginVO", loginVO);

        return modelAndView;
    }

    @RequestMapping(value="/usr/svcAppl/appList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String getAppList(@ModelAttribute("AppVO") AppVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {

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

        paramVO.setCustomerRegNo(customerRegNo);

        model.addAttribute("resultAppList", appService.getAppList(paramVO));
        model.addAttribute("resultAppListTotalCount", appService.getAppListTotalCount(paramVO));

        return "jsonView";
    }

    @RequestMapping(value="/usr/svcAppl/appDetail.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String getAppDetailPage(@ModelAttribute("AppVO") AppVO paramVO, HttpServletRequest request, ModelMap model,
                                  RedirectAttributes redirectAttr) throws Exception {

        String modelAndView = "spt/usr/svcAppl/appDetail";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("paramVO", paramVO);
            return modelAndView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelAndView;
        }
        paramVO.setCustomerRegNo(customerRegNo);

        AppVO appVO = appService.getAppDetail(paramVO);
        List<IntroVO> pubCompanyList = appService.getAppPubCompanyList(appVO);

        model.addAttribute("appDetail", appVO);
        model.addAttribute("appPubCompanyList", pubCompanyList);

        if(!OppfStringUtil.isEmpty(appVO.getServiceRegNo())) {
            // 신청완료된 앱의 경우 연결된 계좌정보 조회한다.
            model.addAttribute("appAccountList", appService.getAppAccountList(paramVO));
        }

        model.addAttribute("paramVO", paramVO);
        model.addAttribute("loginVO", loginVO);

        return modelAndView;
    }

    @RequestMapping(value="/usr/svcAppl/appDelete.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String getAppDelete(@ModelAttribute("AppVO") AppVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {

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
        paramVO.setCustomerRegNo(customerRegNo);

        appService.removeApp(paramVO);

        return "jsonView";
    }

    @RequestMapping(value="/usr/svcAppl/appAccountSelect.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String getAppAccountSelectPage(@ModelAttribute("AppVO") AppVO paramVO, HttpServletRequest request, ModelMap model,
                                    RedirectAttributes redirectAttr) throws Exception {

        String modelAndView = "spt/usr/svcAppl/appAccountSelect";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("paramVO", paramVO);
            return modelAndView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelAndView;
        }
        paramVO.setCustomerRegNo(customerRegNo);
        model.addAttribute("customerRegNo", customerRegNo);

        String customerId = loginVO.getId();
        if(OppfStringUtil.isEmpty(customerId)){
            return modelAndView;
        }
        model.addAttribute("customerId", customerId);

        AppVO appVO = appService.getAppDetail(paramVO);
        model.addAttribute("appDetail", appVO);

        ComCompanyProfileVO pComCompanyProfileVO = new ComCompanyProfileVO();
        pComCompanyProfileVO.setCompanyServiceType("G002002");
        List<ComCompanyProfileVO> companyProfileList = svcApplService.selectComCompanyProfileList(pComCompanyProfileVO);
        model.addAttribute("companyProfileList", companyProfileList);

        List<AppAccountVO> accountList = appService.getAccountList(paramVO);
        for(AppAccountVO appAccountVO : accountList) {
            appAccountVO.setCustomerRealAccountNoEncryption(OppfStringUtil.base64Incoding(appAccountVO.getCustomerRealAccountNoEncryption()));
        }

        if(!OppfStringUtil.isEmpty(appVO.getServiceRegNo())) {
            // 신청완료된 앱의 경우 연결된 계좌정보 조회한다.
            List<AppAccountVO> myAppAccountList = appService.getAppAccountList(paramVO);
            for(AppAccountVO appAccountVO : accountList) {
                for(AppAccountVO myAccount : myAppAccountList) {
                    if(appAccountVO.getCustomerVtaccountNo().equals(myAccount.getCustomerVtaccountNo())) {
                        appAccountVO.setChecked(true);
                        appAccountVO.setAccountRegNo(myAccount.getAccountRegNo());
                        appAccountVO.setCustomerRealAccountNo(myAccount.getCustomerRealAccountNo());
                    }
                }
            }
        }

        model.addAttribute("appAccountList", accountList);
        model.addAttribute("paramVO", paramVO);
        model.addAttribute("loginVO", loginVO);

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
        log.debug("2.[사용자]정보 취득後:customerCi : {} ", customerCi);
        log.debug("2.[사용자]정보 취득後:customerDn : {} ", customerDn);
        model.addAttribute("customerCi", customerCi);
        model.addAttribute("customerDn", customerDn);

        //5.oauthToken정보 취득
        ComOauthTokenVO pComOauthTokenVO = new ComOauthTokenVO();
        pComOauthTokenVO.setCustomerRegNo(customerRegNo);
        ComOauthTokenVO rsComOauthTokenVO = comOauthTokenService.selectComOauthTokenInfo(pComOauthTokenVO);
        model.addAttribute("rsComOauthTokenVO", rsComOauthTokenVO);

        return modelAndView;
    }

    @RequestMapping(value="/usr/svcAppl/checkedCommonTerms.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String checkedCommonTerms(@ModelAttribute("AppVO") AppVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {

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
        paramVO.setCustomerRegNo(customerRegNo);

        int result = appService.checkedCommonTerms(paramVO);
        model.addAttribute("result", result);

        model.addAttribute("paramVO", paramVO);

        return "jsonView";
    }

    @RequestMapping(value="/usr/svcAppl/checkedReSyncCommonTerms.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String checkedReSyncCommonTerms(@ModelAttribute("AppVO") AppVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {

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
        paramVO.setCustomerRegNo(customerRegNo);

        TermsVO  checkedCommonTerms= appService.checkedReSyncCommonTerms(paramVO);
        if(checkedCommonTerms != null) {
            model.addAttribute("expireDay", checkedCommonTerms.getDateCount());
        } else {
            model.addAttribute("expireDay", null);
        }

        model.addAttribute("paramVO", paramVO);

        return "jsonView";
    }

    @RequestMapping(value="/usr/svcAppl/commonTermsPopup.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String getCommonTermsPopup(@ModelAttribute("AppVO") AppVO paramVO, HttpServletRequest request, ModelMap model,
                                           RedirectAttributes redirectAttr) throws Exception {
        String modelView = "spt/usr/svcAppl/commonTermsPopup";

        model.addAttribute("paramVO", paramVO);
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(!OppfStringUtil.isEmpty(paramVO.getCustomerRegNo()) && !OppfStringUtil.isEmpty(paramVO.getType()) && "apt".equals(paramVO.getType())) {
            customerRegNo = paramVO.getCustomerRegNo();
        }

        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelView;
        }
        paramVO.setCustomerRegNo(customerRegNo);
        model.addAttribute("customerRegNo", customerRegNo);
        model.addAttribute("customerName", loginVO.getName_kor());

        String callBakFunc = request.getParameter("callBakFunc");
        model.addAttribute("callBakFunc", callBakFunc);

        Map<String, Object> result = appService.getCommonTermsInfo(paramVO);
        model.addAttribute("commonTerms", result.get("commonTerms"));
        model.addAttribute("pubCompanyList", result.get("pubCompanyList"));

        model.addAttribute("beforeCommonTerms", result.get("beforeCommonTerms"));
        if(result.containsKey("beforePubCompanyList")) {
            model.addAttribute("beforePubCompanyList", result.get("beforePubCompanyList"));
        } else {
            model.addAttribute("beforePubCompanyList", null);
        }

        if(result.containsKey("expireDataCount")) {
            model.addAttribute("expireDataCount", result.get("expireDataCount"));
        } else {
            model.addAttribute("expireDataCount", null);
        }

        //2.[사용자]정보 CI&DN 취득
        String customerCi = "";
        String customerDn = "";
        MbrRegVO pMbrRegVO = new MbrRegVO();
        pMbrRegVO.setCustomerRegNo(customerRegNo);
        pMbrRegVO.setCustomerVerifyType("X");
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
        log.debug("2.[사용자]정보 취득後:customerCi : {} ", customerCi);
        log.debug("2.[사용자]정보 취득後:customerDn : {} ", customerDn);
        model.addAttribute("customerCi", customerCi);
        model.addAttribute("customerDn", customerDn);

        return modelView;
    }

    @RequestMapping(value="/usr/svcAppl/createCommonTerms.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String createCommonTerms(@ModelAttribute("TermsVO") TermsVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {

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
        paramVO.setCustomerRegNo(customerRegNo);

        Map<String,Object> rsMap = appService.createCommonTerms(paramVO);
        String rsCd = (String) rsMap.get("rsCd");
        String rsCdMsg = (String) rsMap.get("rsCdMsg");

        model.addAttribute("rsCd", rsCd);
        model.addAttribute("rsCdMsg", rsCdMsg);

        return "jsonView";
    }

    @RequestMapping(value="/usr/svcAppl/removeCommonTerms.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String removeCommonTerms(@ModelAttribute("AppVO") AppVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(!OppfStringUtil.isEmpty(paramVO.getCustomerRegNo()) && !OppfStringUtil.isEmpty(paramVO.getType()) && "apt".equals(paramVO.getType())) {
            customerRegNo = paramVO.getCustomerRegNo();
        }
        if(OppfStringUtil.isEmpty(customerRegNo)){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        paramVO.setCustomerRegNo(customerRegNo);

        Map<String, Object> rsMap = appService.removeCommonTerms(paramVO);

        String rsCd = (String) rsMap.get("rsCd");
        String rsCdMsg = (String) rsMap.get("rsCdMsg");

        model.addAttribute("rsCd", rsCd);
        model.addAttribute("rsCdMsg", rsCdMsg);

        return "jsonView";
    }

    @RequestMapping(value="/usr/svcAppl/checkedAppTerms.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String checkedAppTerms(@RequestBody AppVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {

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
        paramVO.setCustomerRegNo(customerRegNo);

        HashSet<String> set = new HashSet<>(paramVO.getCheckedPubCompanyList());
        List<String> pubCompanyCodeIdList = new ArrayList<>(set);
        paramVO.setCheckedPubCompanyList(pubCompanyCodeIdList);

        String termsRegNo = appService.checkedAppTerms(paramVO);

        model.addAttribute("termsRegNo", termsRegNo);
        model.addAttribute("paramVO", paramVO);

        return "jsonView";
    }

    @RequestMapping(value="/usr/svcAppl/appTermsPopup.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String getAppTermsPopup(@ModelAttribute("AppVO") AppVO paramVO, HttpServletRequest request, ModelMap model,
                                       RedirectAttributes redirectAttr) throws Exception {
        String modelView = "spt/usr/svcAppl/appTermsPopup";

        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("paramVO", paramVO);
            return modelView;
        }
        model.addAttribute("LoginVO", loginVO);

        // 64bit encoding 
        // appId, subCompanyCodeId, checkedPubCompany
        
        // XSS보안처리
        String appId = paramVO.getAppId();
        if(!OppfStringUtil.isEmpty(appId)){
            appId = OppfStringUtil.base64Decoding(appId);	
            paramVO.setAppId(OppfXssUtil.stripXSSex(appId));
        }

        String subCompanyCodeId = paramVO.getSubCompanyCodeId();
        if(!OppfStringUtil.isEmpty(subCompanyCodeId)){
            subCompanyCodeId = OppfStringUtil.base64Decoding(subCompanyCodeId);	
            paramVO.setSubCompanyCodeId(OppfXssUtil.stripXSSex(subCompanyCodeId));
        }
        
        String checkedPubCompany = paramVO.getCheckedPubCompany();
        if(!OppfStringUtil.isEmpty(checkedPubCompany)){
        	checkedPubCompany = OppfStringUtil.base64Decoding(checkedPubCompany);	
            paramVO.setCheckedPubCompany(OppfXssUtil.stripXSSex(checkedPubCompany));
        }
        
        // XSS보안처리
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(!OppfStringUtil.isEmpty(paramVO.getCustomerRegNo())) {
            customerRegNo = paramVO.getCustomerRegNo();
        }
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelView;
        }
        paramVO.setCustomerRegNo(customerRegNo);
        model.addAttribute("customerRegNo", customerRegNo);

        String customerId = loginVO.getId();
        if(OppfStringUtil.isEmpty(customerId)){
            return modelView;
        }
        model.addAttribute("customerId", customerId);

        String callBakFunc = request.getParameter("callBakFunc");
        model.addAttribute("callBakFunc", callBakFunc);
        
        

        if(!OppfStringUtil.isEmpty(paramVO.getCheckedPubCompany())) {
            // XSS보안처리
        	paramVO.setCheckedPubCompany(OppfXssUtil.stripXSSex( paramVO.getCheckedPubCompany() ));
        	
            List<String> pubCompanyList = Arrays.asList(paramVO.getCheckedPubCompany().split(","));
            HashSet<String> set = new HashSet<>(pubCompanyList);
            List<String> pubCompanyCodeIdList = new ArrayList<>(set);
            paramVO.setCheckedPubCompanyList(pubCompanyCodeIdList);
        }

        Map<String, Object> result = appService.getAppTermsInfo(paramVO);
        TermsVO termsVO = (TermsVO) result.get("appTermsInfo");
        model.addAttribute("appTermsInfo", termsVO);

        TermsVO beforeTermsInfo = (TermsVO) result.get("beforeAppTerms");
        model.addAttribute("beforeAppTerms", beforeTermsInfo);

        model.addAttribute("termsAuthYn", termsVO.getTermsAuthYn());

        //2.[사용자]정보 CI&DN 취득
        String customerCi = "";
        String customerDn = "";
        MbrRegVO pMbrRegVO = new MbrRegVO();
        pMbrRegVO.setCustomerRegNo(customerRegNo);
        pMbrRegVO.setCustomerVerifyType("X");
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
        log.debug("2.[사용자]정보 취득後:customerCi : {} ", customerCi);
        log.debug("2.[사용자]정보 취득後:customerDn : {} ", customerDn);
        model.addAttribute("customerCi", customerCi);
        model.addAttribute("customerDn", customerDn);

        //5.본인인증단계(아이핀인증, 휴대폰인증)에서 저장한 사용자이름을 조회해 와서 공인인증등록시 공인인증서 DN값의 이름과 비교합니다.
        MbrRegVO requestCustomerInfoVO = new MbrRegVO();
        requestCustomerInfoVO.setCustomerRegNo(customerRegNo);
        MbrRegVO resultCustomerInfoVO = mbrRegService.selectSptCustomerInfoProfile(requestCustomerInfoVO);
        model.addAttribute("resultCustomerInfoVO", resultCustomerInfoVO);

        String arsUseYn = OppfProperties.getProperty("Globals.terms.ars.use.yn");
        model.addAttribute("arsUseYn", arsUseYn);

        String arsUseCount = OppfProperties.getProperty("Globals.terms.ars.use.count");
        model.addAttribute("arsUseCount", arsUseCount);

        model.addAttribute("paramVO", paramVO);

        return modelView;
    }

    @RequestMapping(value="/usr/svcAppl/createApp.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String createApp(@RequestBody TermsVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {

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
        paramVO.setCustomerRegNo(customerRegNo);
        String customerId = loginVO.getId();
        if(OppfStringUtil.isEmpty(customerId)){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        paramVO.setCustomerId(customerId);

        Map<String, Object> rsMap;
        if(OppfStringUtil.isEmpty(paramVO.getServiceRegNo())) {
            // 신규 등록
            rsMap = appService.createApp(paramVO, false, request);
        } else {
            // 수정
            rsMap = appService.modifyApp(paramVO, request);
        }

        String rsCd = (String) rsMap.get("rsCd");
        String rsCdMsg = (String) rsMap.get("rsCdMsg");

        model.addAttribute("rsCd", rsCd);
        model.addAttribute("rsCdMsg", rsCdMsg);

        return "jsonView";
    }

    @RequestMapping(value="/usr/svcAppl/appCreateResult.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String getAppCreateResult(@ModelAttribute("AppVO") AppVO paramVO, HttpServletRequest request, ModelMap model,
                                    RedirectAttributes redirectAttr) throws Exception {
        String modelView = "spt/usr/svcAppl/appCreateResult";

        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("paramVO", paramVO);
            return modelView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelView;
        }
        paramVO.setCustomerRegNo(customerRegNo);

        AppVO appVO = appService.getAppDetail(paramVO);

        model.addAttribute("appDetail", appVO);

        if(!OppfStringUtil.isEmpty(appVO.getServiceRegNo())) {
            // 신청완료된 앱의 경우 연결된 계좌정보 조회한다.
            List<AppAccountVO> appAccountList = appService.getAppAccountList(paramVO);

            List<String> pubCompanyCodeIdList = new ArrayList<>();
            String pubCompanyCodeId = "";
            for(AppAccountVO appAccountVO : appAccountList) {
                if(!pubCompanyCodeId.equals(appAccountVO.getPubCompanyCodeId())) {
                    pubCompanyCodeIdList.add(appAccountVO.getPubCompanyCodeId());
                }
                pubCompanyCodeId = appAccountVO.getPubCompanyCodeId();
            }

            List<Map<String,Object>> accountResultList = new ArrayList<>();
            Map<String,Object> pubCompany;
            List<AppAccountVO> accountListForCompany;
            for(String companyCodeId : pubCompanyCodeIdList) {
                pubCompany = new HashMap<>();
                accountListForCompany = new ArrayList<>();
                int count = 0;
                for(AppAccountVO appAccountVO : appAccountList) {
                    if(companyCodeId.equals(appAccountVO.getPubCompanyCodeId())) {
                        if(count == 0) {
                            pubCompany.put("pubCompanyName", appAccountVO.getPubCompanyName());
                        }
                        accountListForCompany.add(appAccountVO);
                        count++;
                    }
                }
                pubCompany.put("accountList", accountListForCompany);
                accountResultList.add(pubCompany);
            }

            model.addAttribute("appAccountList", accountResultList);
        }

        return modelView;
    }

    @RequestMapping(value="/usr/svcAppl/createAppTerms.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String createAppTerms(@RequestBody TermsVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {

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
        paramVO.setCustomerRegNo(customerRegNo);
        String customerId = loginVO.getId();
        if(OppfStringUtil.isEmpty(customerId)){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        paramVO.setCustomerId(customerId);
        AppVO appVO = new AppVO();
        appVO.setCustomerRegNo(customerRegNo);
        appVO.setAppId(paramVO.getAppId());
        List<AppAccountVO> appAccountList = appService.getAppAccountList(appVO);
        if(appAccountList != null) {
            for(AppAccountVO appAccountVO : appAccountList) {
                appAccountVO.setChecked(true);
            }
        }
        paramVO.setAppAccountList(appAccountList);
        Map<String, Object> rsMap = appService.createApp(paramVO, true, request);

        String rsCd = (String) rsMap.get("rsCd");
        String rsCdMsg = (String) rsMap.get("rsCdMsg");

        model.addAttribute("rsCd", rsCd);
        model.addAttribute("rsCdMsg", rsCdMsg);

        return "jsonView";
    }
}
