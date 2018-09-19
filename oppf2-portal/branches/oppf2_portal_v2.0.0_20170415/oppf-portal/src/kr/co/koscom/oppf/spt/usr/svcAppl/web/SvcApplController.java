package kr.co.koscom.oppf.spt.usr.svcAppl.web;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.koscom.oppf.cmm.ars.service.CmmArsService;
import kr.co.koscom.oppf.cmm.intro.service.IntroService;
import kr.co.koscom.oppf.cmm.intro.service.IntroVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenReqBodyVO;
import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenService;
import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfNumberUtil;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.cmm.util.OppfXssUtil;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplService;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
@Controller
public class SvcApplController {
    
    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
    
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

    private static final Logger log = Logger.getLogger(SvcApplController.class);
    
    /* 동기식.do 요청 START */
    
    /**
     * @Method Name        : asumAcntIsu
     * @Method description : [핀테크서비스신청:가상계좌발급]페이지로 이동한다.
     * @param              : SvcApplVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/usr/svcAppl/asumAcntIsu.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String asumAcntIsu(@ModelAttribute("SvcApplVO") SvcApplVO paramVO, HttpServletRequest request, ModelMap model,
                               RedirectAttributes redirectAttr)throws Exception{
        
        String exconn = OppfProperties.getProperty("Globals.domain.exconn");
        model.addAttribute("exconn", exconn);
        
        //modelView
        String modelView = "spt/usr/svcAppl/asumAcntIsu";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelView;
        }
        log.debug("customerRegNo="+customerRegNo);
        model.addAttribute("customerRegNo", customerRegNo);
        
        String customerId = loginVO.getId();
        if(OppfStringUtil.isEmpty(customerId)){
            return modelView;
        }
        model.addAttribute("customerId", customerId);
        
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
        
        //3.[금융투자사목록]정보 취득
        ComCompanyProfileVO pComCompanyProfileVO = new ComCompanyProfileVO();
        pComCompanyProfileVO.setCompanyServiceType("G002002");
        List<ComCompanyProfileVO> companyProfileList = svcApplService.selectComCompanyProfileList(pComCompanyProfileVO);
        model.addAttribute("companyProfileList", companyProfileList);
        
        //4.[일반회원가상계좌+기업코드]목록정보 취득
        SvcApplVO pSvcApplVO = new SvcApplVO();
        pSvcApplVO.setCustomerRegNo(customerRegNo);
        log.debug("4.[일반회원가상계좌+기업코드]목록정보 취득前:customerRegNo="+pSvcApplVO.getCustomerRegNo());
        List<SvcApplVO> sptCustAccList = svcApplService.selectSptCustAccList(pSvcApplVO);

        // [서비스 신청 간소화] appId 가 있을경우 가상계좌 목록 확인 후 서비스 신청 페이지로 리다이렉트 추가
        String appId = paramVO.getAppId();
        List<IntroVO> resultAppPubcompanyList = new ArrayList<>();
        if(null != appId && !"".equals(appId)){
            //핀테크 서비스 금투사 목록 조회
            resultAppPubcompanyList = introService.selectServiceAppPubcompanyList(appId);
        }

        for(int i=0; i<sptCustAccList.size(); i++){
            for(IntroVO introVO : resultAppPubcompanyList){
                if(introVO.getCompanyCodeId().equals(sptCustAccList.get(i).getCompanyCodeId())){
                    return "redirect:/usr/svcAppl/fintechSvcChoic.do?appId="+appId;
                }
            }
            String realaccountNo = sptCustAccList.get(i).getCustomerRealaccountNo();
            if(!OppfStringUtil.isEmpty(realaccountNo)){
                sptCustAccList.get(i).setCustomerRealaccountNoMask(OppfStringUtil.maskForAccNo(realaccountNo));
                sptCustAccList.get(i).setCustomerRealaccountNoEncryption(OppfStringUtil.base64Incoding(realaccountNo));
                log.debug("realaccountNo="+realaccountNo);
                log.debug("mask:realaccountNo="+sptCustAccList.get(i).getCustomerRealaccountNoMask());
                log.debug("encoding:realaccountNo="+sptCustAccList.get(i).getCustomerRealaccountNoEncryption());
            }
        }
        model.addAttribute("sptCustAccList", sptCustAccList);

        //5.oauthToken정보 취득
        ComOauthTokenVO pComOauthTokenVO = new ComOauthTokenVO();
        pComOauthTokenVO.setCustomerRegNo(customerRegNo);
        ComOauthTokenVO rsComOauthTokenVO = comOauthTokenService.selectComOauthTokenInfo(pComOauthTokenVO);
        model.addAttribute("rsComOauthTokenVO", rsComOauthTokenVO);
        
        return modelView;
    }
    
    
    /**
     * @Method Name        : svcApplReg
     * @Method description : [핀테크서비스신청:가상계좌발급팝업]페이지로 이동한다.
     * @param              : SvcApplVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/usr/svcAppl/registAsumAcntIsuPopup.do",method = {RequestMethod.POST})
    private String registAsumAcntIsuPopup(@ModelAttribute("ComCompanyProfileVO") ComCompanyProfileVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        log.info("------------- registAsumAcntIsuPopup.do START -------------------");
        
        
        String exconn = OppfProperties.getProperty("Globals.domain.exconn");
        model.addAttribute("exconn", exconn);
        
        //modelView
        String modelView = "spt/usr/svcAppl/registAsumAcntIsuPopup";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        
        //이슈사항:모달팝업으로 연경우 모달팝업부분만 이동
        //해결사항1.로그인팝업을 만들어서 모달팝업요청
        if(loginVO == null){
            return modelView;
        }
        
        log.debug("loginVO.getId()="+loginVO.getId());
        String customerId = loginVO.getId();
        if(OppfStringUtil.isEmpty(customerId)){
            return modelView;
        }
        model.addAttribute("customerId", customerId);
        
        log.debug("loginVO.getCustomer_reg_no()="+loginVO.getCustomer_reg_no());
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
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
        
        //3.[금융투자사목록]정보 취득
        ComCompanyProfileVO pComCompanyProfileVO = new ComCompanyProfileVO();
        pComCompanyProfileVO.setCompanyProfileRegNo(paramVO.getCompanyProfileRegNo());
        pComCompanyProfileVO.setCompanyCodeId(paramVO.getCompanyCodeId());
        ComCompanyProfileVO rsComCompanyProfileVO = svcApplService.selectComCompanyProfileInfo(pComCompanyProfileVO);
        model.addAttribute("rsComCompanyProfileVO", rsComCompanyProfileVO);
        
        //4.[일반회원가상계좌+기업코드]목록정보 취득
        SvcApplVO pSvcApplVO = new SvcApplVO();
        pSvcApplVO.setCustomerRegNo(customerRegNo);
        pSvcApplVO.setCompanyCodeId(paramVO.getCompanyCodeId());
        log.debug("4.[일반회원가상계좌+기업코드]목록정보 취득前:customerRegNo="+pSvcApplVO.getCustomerRegNo());
        log.debug("4.[일반회원가상계좌+기업코드]목록정보 취득前:companyCodeId="+pSvcApplVO.getCompanyCodeId());
        List<SvcApplVO> sptCustAccList = svcApplService.selectSptCustAccList(pSvcApplVO);
        model.addAttribute("sptCustAccList", sptCustAccList);
        
        //5.셋팅 공통코드:실계좌유형
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G010");
        List<CmmFuncVO> cmmAccTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        log.debug("5.셋팅 공통코드:실계좌유형 취득後:cmmAccTypeList="+cmmAccTypeList);
        model.addAttribute("cmmAccTypeList", cmmAccTypeList);
        
        //6.oauthToken정보 취득
        ComOauthTokenVO pComOauthTokenVO = new ComOauthTokenVO();
        pComOauthTokenVO.setCustomerRegNo(customerRegNo);
        ComOauthTokenVO rsComOauthTokenVO = comOauthTokenService.selectComOauthTokenInfo(pComOauthTokenVO);
        
        //7-1.DB에서 oauthToken정보를 취득하지 못한 경우
        if(rsComOauthTokenVO == null){
            //7-1-1.정적값셋팅
            String circumventYn = OppfProperties.getProperty("Globals.oauth.token.circumventYn"); //우회여부(Y:우회, N:우회안함)
            String clientId = OppfProperties.getProperty("Globals.oauth.token.clientId");
            String secretId = OppfProperties.getProperty("Globals.oauth.token.secretId");
            String scope = OppfProperties.getProperty("Globals.oauth.token.scope");
            String grantType = OppfProperties.getProperty("Globals.oauth.token.grantType");
            String Authorization = "Basic "+OppfStringUtil.base64Incoding(clientId+":"+secretId);
            
            //7-1-2.셋팅:헤더
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            httpHeaders.add("apiKey", clientId);
            httpHeaders.add("Authorization", Authorization);
            
            //7-1-3.셋팅:url
            String url = OppfProperties.getProperty("Globals.oauth.token.url");
            
            //7-1-4.요청parameter설정
            ComOauthTokenReqBodyVO reqBodyVO = new ComOauthTokenReqBodyVO();
            reqBodyVO.setGrant_type(grantType);
            reqBodyVO.setClientId(clientId);
            reqBodyVO.setClientSecret(secretId);
            reqBodyVO.setScope(scope);
            
            String reqStr = "grant_type="+grantType+"&clientId="+clientId+"&clientSecret="+secretId+"&scope="+scope;
            
            //7-1-5.전송처리
            HttpEntity<String> requestEntity = new HttpEntity<String>(reqStr, httpHeaders);
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            SSLContextBuilder builder = new SSLContextBuilder();
            
            try{
                builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(),SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

                CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
                Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", new PlainConnectionSocketFactory())
                        .register("https", sslsf)
                        .build();
                
                PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);

                CloseableHttpClient httpClient = HttpClients.custom()
                        .setSSLSocketFactory(sslsf)
                        .setConnectionManager(cm)
                        .build();
                requestFactory.setHttpClient(httpclient);
                RestTemplate restTemplate = new RestTemplate(requestFactory);
                
                restTemplate.setErrorHandler(new ResponseErrorHandler() {
                    @Override
                    public boolean hasError(ClientHttpResponse response) throws IOException {
                        return false;
                    }
                    @Override
                    public void handleError(ClientHttpResponse response) throws IOException {
                    }
                });
                ResponseEntity<ComOauthTokenVO> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ComOauthTokenVO.class);
                ComOauthTokenVO responseBody = responseEntity.getBody();
                log.debug("ok:responseBody="+responseEntity.getBody());
                log.debug("ok:access_token="+responseBody.getAccess_token());
                log.debug("ok:token_type="+responseBody.getToken_type());
                log.debug("ok:expires_in="+responseBody.getExpires_in());
                log.debug("ok:scope="+responseBody.getScope());
                
                //7-1-5-1.DB등록
                pComOauthTokenVO = new ComOauthTokenVO();
                pComOauthTokenVO.setCustomerRegNo(customerRegNo);
                pComOauthTokenVO.setClientId(clientId);
                pComOauthTokenVO.setSecretId(secretId);
                pComOauthTokenVO.setScope(responseBody.getScope());
                pComOauthTokenVO.setAccessToken(responseBody.getAccess_token());
                pComOauthTokenVO.setTokenType(responseBody.getToken_type());
                String expiresIn = String.valueOf(responseBody.getExpires_in());
                pComOauthTokenVO.setExpiresIn(expiresIn);
                String regNo = comOauthTokenService.insertComOauthToken(pComOauthTokenVO);
                log.debug("DB등록:regNo="+regNo);
                
                //7-1-5-2.DB등록후데이터취득
                pComOauthTokenVO = new ComOauthTokenVO();
                pComOauthTokenVO.setRegNo(regNo);
                rsComOauthTokenVO = comOauthTokenService.selectComOauthTokenInfo(pComOauthTokenVO);
                log.debug("DB조회:accessToken="+rsComOauthTokenVO.getAccessToken());
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        model.addAttribute("rsComOauthTokenVO", rsComOauthTokenVO);
        
        log.info("------------- registAsumAcntIsuPopup.do END -------------------");
        return modelView;
    }
    
    /**
     * @Method Name        : svcApplReg
     * @Method description : [핀테크서비스신청:가상계좌발급팝업2]페이지로 이동한다.
     * @param              : SvcApplVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/usr/svcAppl/registAsumAcntIsuPopup2.do",method = {RequestMethod.POST})
    private String registAsumAcntIsuPopup2(@ModelAttribute("ComCompanyProfileVO") ComCompanyProfileVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        
        log.info("------------- registAsumAcntIsuPopup2.do START -------------------");
        
        String exconn = OppfProperties.getProperty("Globals.domain.exconn");
        model.addAttribute("exconn", exconn);
        
        //modelView
        String modelView = "spt/usr/svcAppl/registAsumAcntIsuPopup2";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        
        //이슈사항:모달팝업으로 연경우 모달팝업부분만 이동
        //해결사항1.로그인팝업을 만들어서 모달팝업요청
        if(loginVO == null){
            return modelView;
        }
        
        log.debug("loginVO.getId()="+loginVO.getId());
        String customerId = loginVO.getId();
        if(OppfStringUtil.isEmpty(customerId)){
            return modelView;
        }
        model.addAttribute("customerId", customerId);
        
        log.debug("loginVO.getCustomer_reg_no()="+loginVO.getCustomer_reg_no());
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
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
        
        //3.[기업]정보 취득
//        log.debug("3.[금융투자사]취득前:paramVO.getCompanyCodeRegNo()="+paramVO.getCompanyCodeRegNo());
//        log.debug("3.[금융투자사]취득前:paramVO.getCompanyCodeId()="+paramVO.getCompanyCodeId());
//        ComCompanyCodeVO rsComCompanyCodeVO = svcApplService.selectComCompanyCodeInfo(paramVO);
//        model.addAttribute("rsComCompanyCodeVO", rsComCompanyCodeVO);
        
        //3.[금융투자사목록]정보 취득
        ComCompanyProfileVO pComCompanyProfileVO = new ComCompanyProfileVO();
        pComCompanyProfileVO.setCompanyProfileRegNo(paramVO.getCompanyProfileRegNo());
        pComCompanyProfileVO.setCompanyCodeId(paramVO.getCompanyCodeId());
        ComCompanyProfileVO rsComCompanyProfileVO = svcApplService.selectComCompanyProfileInfo(pComCompanyProfileVO);
        model.addAttribute("rsComCompanyProfileVO", rsComCompanyProfileVO);
        
        //4.[일반회원가상계좌+기업코드]목록정보 취득
        SvcApplVO pSvcApplVO = new SvcApplVO();
        pSvcApplVO.setCustomerRegNo(customerRegNo);
        pSvcApplVO.setCompanyCodeId(paramVO.getCompanyCodeId());
        log.debug("4.[일반회원가상계좌+기업코드]목록정보 취득前:customerRegNo="+pSvcApplVO.getCustomerRegNo());
        log.debug("4.[일반회원가상계좌+기업코드]목록정보 취득前:companyCodeId="+pSvcApplVO.getCompanyCodeId());
        List<SvcApplVO> sptCustAccList = svcApplService.selectSptCustAccList(pSvcApplVO);
        model.addAttribute("sptCustAccList", sptCustAccList);
        
        //5.셋팅 공통코드:실계좌유형
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G010");
        List<CmmFuncVO> cmmAccTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        log.debug("5.셋팅 공통코드:실계좌유형 취득後:cmmAccTypeList="+cmmAccTypeList);
        model.addAttribute("cmmAccTypeList", cmmAccTypeList);
        
        //6.oauthToken정보 취득
        ComOauthTokenVO pComOauthTokenVO = new ComOauthTokenVO();
        pComOauthTokenVO.setCustomerRegNo(customerRegNo);
        ComOauthTokenVO rsComOauthTokenVO = comOauthTokenService.selectComOauthTokenInfo(pComOauthTokenVO);
        model.addAttribute("rsComOauthTokenVO", rsComOauthTokenVO);
        
        log.info("------------- registAsumAcntIsuPopup.do END -------------------");
        return modelView;
    }
    
    /**
     * @Method Name        : fintechSvcChoic
     * @Method description : [핀테크서비스신청:핀테크서비스선택]페이지로 이동한다.
     * @param              : SvcApplVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/usr/svcAppl/fintechSvcChoic.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String fintechSvcChoic(@ModelAttribute("SvcApplVO") SvcApplVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String modelView = "spt/usr/svcAppl/fintechSvcChoic";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("paramVO", paramVO);
            return modelView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelView;
        }
        
        //사용자 정보 셋팅
        paramVO.setCustomerRegNo(customerRegNo);

        //특정 서비스 선택으로 진입시
        if(null != paramVO.getAppId() && !"".equals(paramVO.getAppId())){
            SptCustomerServiceProfileVO sptCustomerServiceProfileVO = svcApplService.selectFintechSvc(paramVO);
            //증권사 정보가 없으면 기존 서비스 신청 페이지 전달
            if(null!= sptCustomerServiceProfileVO){
                //서비스 미사용인 경우 사용처리 &&
                if("N".equals(sptCustomerServiceProfileVO.getUseYn())){
                    svcApplService.insertSelectFintechSvc(paramVO);
                }
                modelView = "spt/usr/svcAppl/fintechSvcAppChoic";
            }
        }

        //금투사 list
        List<SvcApplVO> companyCodeList = svcApplService.selectFintechSvcCompanyList(paramVO);
        model.addAttribute("companyCodeList", companyCodeList);

        //금투사 약관 미동의
        Map<String, Object> map = svcApplService.selectSvcCompanyTermsConsntList(paramVO);
        model.addAttribute("resultList", map.get("resultList"));

        model.addAttribute("loginVO", loginVO);
        model.addAttribute("paramVO", paramVO);
        
        return modelView;
    }
    
    /**
     * @Method Name        : selectFintechSvcList
     * @Method description : [핀테크서비스신청:핀테크서비스선택] 핀테크 서비스 선택 정보 조회, 가상계좌 정보 조회  
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/usr/svcAppl/selectFintechSvcList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectFintechSvcList(@ModelAttribute("SvcApplVO") SvcApplVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
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
        
        //사용자 정보 셋팅
        paramVO.setCustomerRegNo(customerRegNo);
        
		Map<String, Object> map = svcApplService.selectFintechSvcList(paramVO);
		
		model.addAttribute("resultSvcList", map.get("resultSvcList"));							//핀테크 서비스 목록
		model.addAttribute("resultSvcPubcompanyList", map.get("resultSvcPubcompanyList"));		//핀테크 서비스 금투사 목록
		model.addAttribute("resultSvcAccountList", map.get("resultSvcAccountList"));			//핀테크 서비스 가상계좌 목록
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : cancelSvcAppl
     * @Method description : [핀테크서비스신청:핀테크서비스선택] 서비스 해지
     * @param              : SvcApplVO
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/usr/svcAppl/cancelSvcAppl.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String cancelSvcAppl(@RequestBody SvcApplVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
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
        
        MypSvcApplVO mypSvcApplVO = new MypSvcApplVO();
        
        //사용자 정보 셋팅
        mypSvcApplVO.setCustomerRegNo(customerRegNo);
        mypSvcApplVO.setCreateId(customerRegNo);
        
        mypSvcApplVO.setServiceRegNo(paramVO.getServiceRegNo());
        
        int result = mypSvcApplService.cancelMypSvcAppl(mypSvcApplVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : insertFintechSvc
     * @Method description : [핀테크서비스신청:핀테크서비스선택] 핀테크 서비스 선택 정보를 등록한다.  
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/usr/svcAppl/insertFintechSvc.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String insertFintechSvc(@RequestBody SvcApplVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
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
        
        //사용자 정보 셋팅
        paramVO.setCustomerRegNo(customerRegNo);
        paramVO.setCreateId(customerRegNo);
        
        int result = svcApplService.insertFintechSvc(paramVO);
        model.addAttribute("result", result);
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectFintechSvcTermsList
     * @Method description : [핀테크서비스신청:핀테크서비스선택] 사용자의 약관정보를 가져온다.  
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/usr/svcAppl/selectFintechSvcTermsList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectFintechSvcTermsList(@RequestBody SvcApplVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
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
        
        //사용자 정보 셋팅
        paramVO.setCustomerRegNo(customerRegNo);
        
		Map<String, Object> map = svcApplService.selectFintechSvcTermsList(paramVO);
		
		model.addAttribute("resultSvcTermsList", map.get("resultSvcTermsList"));	//핀테크 서비스 약관 목록
		
		
	    return "jsonView";
	}

	/**
     * @Method Name        : saveFintechSvcTerms
     * @Method description : [핀테크서비스신청:핀테크서비스선택] 가상계좌 선택 정보를 저장한다.  
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/usr/svcAppl/saveFintechSvcTerms.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String saveFintechSvcTerms(@RequestBody SvcApplVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
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
        
        //사용자 정보 셋팅
        paramVO.setCustomerRegNo(customerRegNo);
        paramVO.setCreateId(customerRegNo);
        paramVO.setTermsPolicy(OppfProperties.getProperty("Globals.user.policy.terms.final"));
        
        int result = svcApplService.saveFintechSvcTerms(paramVO);
        model.addAttribute("result", result);
				
	    return "jsonView";
	}

	/**
     * @Method Name        : svcCompanyTermsConsnt
     * @Method description : [핀테크서비스신청:약관동의]페이지로 이동한다.
     * @param              : SvcApplVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/usr/svcAppl/svcCompanyTermsConsnt.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String svcCompanyTermsConsnt(@ModelAttribute("SvcApplVO") SvcApplVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String modelView = "spt/usr/svcAppl/svcCompanyTermsConsnt";
    	
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
        
        //금투사 list
        Map<String, Object> map = svcApplService.selectSvcCompanyTermsConsntList(paramVO);
        model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultAgreeCompanyList", map.get("resultAgreeCompanyList"));
        
        model.addAttribute("loginVO", loginVO);
        model.addAttribute("paramVO", paramVO);
        
        return modelView;
    }
    
    /**
     * @Method Name        : saveSvcCompanyTermsConsnt
     * @Method description : [핀테크서비스신청:약관동의] 기업약관을 저장한다. 
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/usr/svcAppl/saveSvcCompanyTermsConsnt.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String saveSvcCompanyTermsConsnt(@RequestBody SvcApplVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
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
        
        //사용자 정보 셋팅
        paramVO.setCustomerRegNo(customerRegNo);
        paramVO.setCreateId(customerRegNo);
                
        int result = svcApplService.saveSvcCompanyTermsConsnt(paramVO);
        model.addAttribute("result", result);
				
	    return "jsonView";
	}
    	
	/**
     * @Method Name        : svcTermConsnt
     * @Method description : [핀테크서비스신청:정보제공동의]페이지로 이동한다.
     * @param              : SvcApplVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/usr/svcAppl/svcTermConsnt.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String svcTermConsnt(@ModelAttribute("SvcApplVO") SvcApplVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String modelView = "spt/usr/svcAppl/svcTermConsnt";
    	
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
        
        return modelView;
    }
    
    /**
     * @Method Name        : selectSvcTermConsntList
     * @Method description : [핀테크서비스신청:정보제공동의] 정보제공동의 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/usr/svcAppl/selectSvcTermConsntList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectSvcTermConsntList(@ModelAttribute("SvcApplVO") SvcApplVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
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
        
        //사용자 정보 셋팅
        paramVO.setCustomerRegNo(customerRegNo);
        
		Map<String, Object> map = svcApplService.selectSvcTermConsntList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		
	    return "jsonView";
	}
	
    /**
     * @Method Name        : svcApplReg
     * @Method description : [핀테크서비스신청:정보제공동의팝업]페이지로 이동한다.
     * @param              : SvcApplVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/usr/svcAppl/svcTermConsntPopup.do",method = {RequestMethod.POST})
    private String svcTermConsnt(@ModelAttribute("ComCompanyCodeVO") ComCompanyCodeVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String modelView = "spt/usr/svcAppl/svcTermConsntPopup";
    	
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
        	if(OppfStringUtil.isEmpty(request.getParameter("customerRegNo"))){
        		return modelView;
        	}else{
        		customerRegNo = request.getParameter("customerRegNo");
        	}
        }
    	
        log.info("------------- registAsumAcntIsuPopup.do START -------------------");
        log.info("request.customerRegNo=>"+customerRegNo);
        log.info("request.termsRegNo=>"+request.getParameter("termsRegNo"));
        log.info("request.termsAuthYn=>"+request.getParameter("termsAuthYn"));
        log.info("request.callBakFunc=>"+request.getParameter("callBakFunc"));
        
        String termsRegNo = request.getParameter("termsRegNo");
        String termsAuthYn = request.getParameter("termsAuthYn");
        String callBakFunc = request.getParameter("callBakFunc");
        
        if(!OppfStringUtil.isEmpty(termsRegNo)){
            termsRegNo = OppfStringUtil.base64Decoding(termsRegNo);
        }
        
        //유효성검증처리
        log.debug("termsRegNo="+termsRegNo);
        if(!OppfNumberUtil.getNumberValidCheck(termsRegNo) || termsRegNo.length() != 11){
            model.addAttribute("alertMsg", "잘못된 접근 입니다.");
            model.addAttribute("popupYn", "Y");
            model.addAttribute("moveUrl","/usr/svcAppl/svcTermConsnt.do");
            return "/cmm/alertMsg/alertMsg";
        }
       //[s] termsAuthYn = "R" 에 대한 조건 추가  2017-04-07 한유진
//      if("Y".equals(termsAuthYn) || "N".equals(termsAuthYn)){	
        if("Y".equals(termsAuthYn) || "N".equals(termsAuthYn) || "R".equals(termsAuthYn)){	// "R" 추가 
       //[e] termsAuthYn = "R" 에 대한 조건 추가  2017-04-07 한유진
        }else{
            model.addAttribute("alertMsg", "잘못된 접근 입니다.");
            model.addAttribute("popupYn", "Y");
            model.addAttribute("moveUrl","/usr/svcAppl/svcTermConsnt.do");
            return "/cmm/alertMsg/alertMsg";
        }
        
        String regex = "^[~!@#$%<>^&*()-=+’]*$";
        if(callBakFunc.matches(regex)){
            model.addAttribute("alertMsg", "잘못된 접근 입니다.");
            model.addAttribute("popupYn", "Y");
            model.addAttribute("moveUrl","/usr/svcAppl/svcTermConsnt.do");
            return "/cmm/alertMsg/alertMsg";
        }
        
        //서비스 제공자 기업 id가 들어오면 해당 기업만보여준다.
        String pubcompanyCodeId = OppfStringUtil.isNullToString(request.getParameter("pubcompanyCodeId"));
        
        model.addAttribute("termsRegNo", termsRegNo);
        model.addAttribute("termsAuthYn", termsAuthYn);
        model.addAttribute("callBakFunc", callBakFunc);
                
        String exconn = OppfProperties.getProperty("Globals.domain.exconn");
        model.addAttribute("exconn", exconn);
        
        //1.로그인관련
        String customerId = loginVO.getId();
        model.addAttribute("customerId", customerId);
        model.addAttribute("customerRegNo", customerRegNo);
        
        //2.사용자 정보제공동의서 정보 DB조회
        SptCustomerServiceTermsProfileVO pTermsVO = new SptCustomerServiceTermsProfileVO();
        pTermsVO.setCustomerRegNo(customerRegNo);
        pTermsVO.setTermsRegNo(termsRegNo);
        pTermsVO.setTermsAuthYn(termsAuthYn);
        pTermsVO.setTermsPolicyYear(OppfProperties.getProperty("Globals.user.policy.password.final"));
        SptCustomerServiceTermsProfileVO rsTermsVO = svcApplService.selectSptCustomerServiceTermsProfile(pTermsVO);
        model.addAttribute("rsTermsVO", rsTermsVO);
        
        //[s] termsAuthYn ="R" 일 경우 유효기간 생성해서 addAttribute  2017-04-06 한유진
        if("R".equals(termsAuthYn)){
        	LocalDate date = LocalDate.now();
        	
        	int curYear = date.getYear();
        	int curMonth = date.getMonthValue();
        	int curDay = date.getDayOfMonth();
        	String curDate = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        	
        	//유효기간 정책 확인해서 expire_date 계산
        	int policyYear = Integer.parseInt(rsTermsVO.getTermsPolicyYear());
        	
        	date = date.plusYears(policyYear);
        	date = date.minusDays(1);
        	
        	int expYear = date.getYear();
        	int expMonth = date.getMonthValue();
        	int expDay = date.getDayOfMonth();

        	model.addAttribute("curDate", curDate);
        	model.addAttribute("curYear", curYear);
        	model.addAttribute("curMonth", curMonth);
        	model.addAttribute("curDay", curDay);
        	 
        	model.addAttribute("expYear", expYear );
           	model.addAttribute("expMonth", expMonth );
           	model.addAttribute("expDay", expDay );
        }
        //[e] termsAuthYn ="R" 일 경우 유효기간 생성해서 addAttribute  2017-04-06 한유진
        
        //3.사용자 정보제공동의서 기업정보 DB조회
        SptCustomerServiceTermsPubcompanyProfileVO pPubcompanyVO = new SptCustomerServiceTermsPubcompanyProfileVO();
        pPubcompanyVO.setCustomerRegNo(customerRegNo);
        pPubcompanyVO.setTermsRegNo(termsRegNo);
        pPubcompanyVO.setPubcompanyCodeId(pubcompanyCodeId);
        List<SptCustomerServiceTermsPubcompanyProfileVO> rsPubcompanyList = svcApplService.selectSptCustomerServiceTermsPubcompanyProfileList(pPubcompanyVO);
        model.addAttribute("rsPubcompanyList", rsPubcompanyList);
        
        //4.[사용자]정보 CI&DN 취득
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
        log.debug("2.[사용자]정보 취득後:customerCi="+customerCi);
        log.debug("2.[사용자]정보 취득後:customerDn="+customerDn);
        model.addAttribute("customerCi", customerCi);
        model.addAttribute("customerDn", customerDn);
        
        //5.본인인증단계(아이핀인증, 휴대폰인증)에서 저장한 사용자이름을 조회해 와서 공인인증등록시 공인인증서 DN값의 이름과 비교합니다.
        MbrRegVO requestCustomerInfoVO = new MbrRegVO();
        requestCustomerInfoVO.setCustomerRegNo(customerRegNo);
        MbrRegVO resultCustomerInfoVO = mbrRegService.selectSptCustomerInfoProfile(requestCustomerInfoVO);
        model.addAttribute("resultCustomerInfoVO", resultCustomerInfoVO);
        
        model.addAttribute("paramVO", paramVO);
        return modelView;
    }

    /**
     * @Method Name        : svcApplReg
     * @Method description : [핀테크서비스신청:정보제공동의팝업]ARS 검증 파일 다운
     * @param              : SvcApplVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/usr/svcAppl/svcTermArsDown.do",method = {RequestMethod.POST})
    private String svcTermArsDown(HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "spt/usr/svcAppl/svcTermArsDown";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        String termsRegNo = request.getParameter("termsRegNo");
        String result = cmmArsService.selectTermsArsRecordData(termsRegNo);
        log.debug("[ARS]녹취 데이터 정보 취득="+result);
        model.addAttribute("termsRegNo", termsRegNo);
        model.addAttribute("recordData", result);

        return modelView;
    }
    
    /**
     * @Method Name        : svcApplComplt
     * @Method description : [핀테크서비스신청:서비스신청완료]페이지로 이동한다.
     * @param              : SvcApplVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/usr/svcAppl/svcApplComplt.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String svcApplComplt(@ModelAttribute("SvcApplVO") SvcApplVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String modelView = "spt/usr/svcAppl/svcApplComplt";
    	
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
        
        return modelView;
    }
    
    /**
     * @Method Name        : selectSvcApplCompltList
     * @Method description : [핀테크서비스신청:서비스신청완료] 서비스신청완료 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/usr/svcAppl/selectSvcApplCompltList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectSvcApplCompltList(@ModelAttribute("SvcApplVO") SvcApplVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
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
        
        //사용자 정보 셋팅
        paramVO.setCustomerRegNo(customerRegNo);
        
		Map<String, Object> map = svcApplService.selectSvcApplCompltList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		
	    return "jsonView";
	}
    
    /* 동기식.do 요청 END */
    
    
    /* 비동기식.ajax 요청 START */
    /**
     * @Method Name        : insertSptCustomerAccountProfile
     * @Method description : [가상계좌발급]정보를 등록을 한다. 사용 X
     * @param              : MbrRegVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/usr/svcAppl/insertSptCustomerAccountProfile.ajax")
    private String registSptCustomerAccountProfile(@ModelAttribute("SptCustomerAccountProfileVO") SptCustomerAccountProfileVO paramVO, ModelMap model)throws Exception{
        log.info("------------- insertSptCustomerAccountProfile.ajax START -------------------");
        
        int rs = svcApplService.insertSptCustomerAccountProfile(paramVO);
        log.debug("rs="+rs);
        model.addAttribute("rs", rs);
        
        log.info("------------- insertSptCustomerAccountProfile.ajax END ---------------------");
        return "jsonView";
    }
    
    /**
     * @Method Name        : updateSptCustomerAccountProfile
     * @Method description : [가상계좌발급]정보를 수정을 한다. 사용 X
     * @param              : MbrRegVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/usr/svcAppl/updateSptCustomerAccountProfile.ajax")
    private String updateSptCustomerAccountProfile(@ModelAttribute("SptCustomerAccountProfileVO") SptCustomerAccountProfileVO paramVO, ModelMap model)throws Exception{
        log.info("------------- updateSptCustomerAccountProfile.ajax START -------------------");
        
        int rs = svcApplService.updateSptCustomerAccountProfile(paramVO);
        log.debug("rs="+rs);
        model.addAttribute("rs", rs);
        
        log.info("------------- updateSptCustomerAccountProfile.ajax END ---------------------");
        return "jsonView";
    }
    
    /**
     * @Method Name        : deleteSptCustomerAccountProfile
     * @Method description : [가상계좌발급]정보를 삭제를 한다. 사용 X
     * @param              : MbrRegVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/usr/svcAppl/deleteSptCustomerAccountProfile.ajax")
    private String deleteSptCustomerAccountProfile(@ModelAttribute("SptCustomerAccountProfileVO") SptCustomerAccountProfileVO paramVO, ModelMap model)throws Exception{
        log.info("------------- deleteSptCustomerAccountProfile.ajax START -------------------");
        
        int rs = svcApplService.deleteSptCustomerAccountProfile(paramVO);
        log.debug("rs="+rs);
        model.addAttribute("rs", rs);
        
        log.info("------------- deleteSptCustomerAccountProfile.ajax END ---------------------");
        return "jsonView";
    }
    /* 비동기식.ajax 요청 END */    
}
