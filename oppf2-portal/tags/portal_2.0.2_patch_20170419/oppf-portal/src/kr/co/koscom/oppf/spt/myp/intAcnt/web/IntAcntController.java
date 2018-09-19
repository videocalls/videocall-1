package kr.co.koscom.oppf.spt.myp.intAcnt.web;

import com.google.gson.Gson;
import kr.co.koscom.oppf.cmm.IntegratedAccount.service.*;
import kr.co.koscom.oppf.cmm.exception.ApiExceptionMessage;
import kr.co.koscom.oppf.cmm.service.CmmIntegratedAccountService;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.CodeConstants;
import kr.co.koscom.oppf.cmm.util.CommonFunction;
import kr.co.koscom.oppf.cmm.util.CommonUtil;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.myp.intAcnt.service.IntAcntOauthService;
import kr.co.koscom.oppf.spt.myp.intAcnt.service.IntAcntSearchVO;
import kr.co.koscom.oppf.spt.myp.intAcnt.service.SptCustomerAcntOauthTokenVO;
import kr.co.koscom.oppf.spt.myp.intAcnt.service.impl.IntAcntWorker;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceAccountProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SvcApplService;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SvcApplVO;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Project  : 코스콤 오픈플렛폼
 * @FileName : IntAcntController.java
 * @Comment  : 통합계좌 조회 Controller
 * @author   : 이희태
 * @since    : 2017.03.07
 * @version  : 1.0
 * @see
 *
 * << 개정이력(Modification Information) >>
 * 수정일                수정자        수정내용
 * ----------- ------  ----------
 * 2017.03.07  이희태        최초생성
 *
 */
@Controller
public class IntAcntController {

    @Resource(name = "CmmSIFInternalService")
    private CmmSIFInternalService cmmSIFInternalService;
    @Resource(name = "IntAcntOauthService")
    private IntAcntOauthService intAcntOauthService;
    @Resource(name = "SvcApplService")
    private SvcApplService svcApplService;
    @Resource(name = "CmmIntegratedAccountService")
    private CmmIntegratedAccountService cmmIntegratedAccountService;

    private static final Logger log = Logger.getLogger(IntAcntController.class);

    /**
     * @Method Name        : getAuthorizationCallback
     * @Method description : 통합계좌조회 Oauth 인증 callback Url
     * @param              : model
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping(
            value="/spt/myp/intAcnt/callback"
            ,method = RequestMethod.GET)
    public String getAuthorizationCallback(@RequestParam(value="code") String code,
                                           @RequestParam(value="scope") String scope,
                                           HttpServletRequest request,
                                           ModelMap model) throws Exception{
        log.debug("++++++++++++++++++++++++++++++++++++ Authorization Code : " + code );
        log.debug("++++++++++++++++++++++++++++++++++++ scope : " + scope );
        String modelView = "spt/myp/intAcnt/intAcntCallBack";
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelView;
        }
        String customerId = loginVO.getId();
        if(OppfStringUtil.isEmpty(customerId)){
            return modelView;
        }
        model.addAttribute("loginVO", loginVO);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // G/W access_token 요청 URL
        String apiUrl = OppfProperties.getProperty("Globals.oauth.token.url");
        String authorizationCode = code;
        String clientId = OppfProperties.getProperty("Globals.integrated.account.clientId");
        String clientSecret = OppfProperties.getProperty("Globals.integrated.account.secretId");
        String redirectUri = OppfProperties.getProperty("Globals.integrated.account.callBackUrl");
        String grantType = OppfProperties.getProperty("Globals.integrated.account.grantType");

        String payload = "code="+authorizationCode+"&client_id="+clientId+"&client_secret="+clientSecret+"&redirect_uri="+redirectUri+"&grant_type="+grantType;
        try{
            ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(apiUrl, httpHeaders, HttpMethod.POST, payload);
            if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
                JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
                String access_token =  jsonObject.getString("access_token");
                String token_type =  jsonObject.getString("token_type");
                String expires_in =  String.valueOf(jsonObject.getInt("expires_in"));
                String refresh_token =  jsonObject.getString("refresh_token");
                String rsp_scope =  jsonObject.getString("scope");

                SptCustomerAcntOauthTokenVO sptCustomerAcntOauthTokenVO = new SptCustomerAcntOauthTokenVO();
                sptCustomerAcntOauthTokenVO.setCustomerRegNo(customerRegNo);
                sptCustomerAcntOauthTokenVO.setAccessToken(access_token);
                sptCustomerAcntOauthTokenVO.setRefreshToken(refresh_token);
                sptCustomerAcntOauthTokenVO.setScope(rsp_scope);
                sptCustomerAcntOauthTokenVO.setTokenType(token_type);
                sptCustomerAcntOauthTokenVO.setExpiresIn(expires_in);
                //OauthToken 정보 초기화
                intAcntOauthService.deleteCustomerAcntOauthToken(sptCustomerAcntOauthTokenVO);
                //OauthToken 정보 등록
                intAcntOauthService.insertCustomerAcntOauthToken(sptCustomerAcntOauthTokenVO);
                model.addAttribute("result", "00");
                log.debug("++++++++++++++++++++++++++++++++++++ ACCESS_TOKEN : " + access_token );
            }else{
                log.debug("++++++++++++++++++++++++++++++++++++ responseEntity.getBody() : " + responseEntity.getBody() );
                model.addAttribute("result", "01");
            }
        }catch (Exception e){
                log.debug("++++++++++++++++++++++++++++++++++++ Internal Error : " + e.getMessage() );
                model.addAttribute("result", "99");
        }

        return modelView;
    }

    /* @Method Name        : intAcntPopup
    * @Method description : 통합계좌조회 로그인 팝업안내
    * @param              : model
    * @return             : jsonView
    * @throws             : Exception
    */
    @RequestMapping(value="/spt/myp/intAcnt/intAcntPopup.do",method = {RequestMethod.GET, RequestMethod.POST})
    public String intAcntPopup(@RequestParam(value="url") String url,
                               HttpServletRequest request, ModelMap model) throws Exception{
        //modelView
        String modelView = "spt/myp/intAcnt/intAcntPopup";

        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        model.addAttribute("loginVO", loginVO);
        model.addAttribute("url", url.replaceAll("amp;",""));

        return modelView;
    }

    /* @Method Name        : intAcnt
    * @Method description : 통합계좌조회 메인화면
    * @param              : model
    * @return             : jsonView
    * @throws             : Exception
    */
    @RequestMapping(value="/spt/myp/intAcnt/intAcnt.do",method = {RequestMethod.GET, RequestMethod.POST})
    public String intAcnt(HttpServletRequest request, ModelMap model) throws Exception{

        //modelView
        String modelView = "spt/myp/intAcnt/intAcnt";

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

        SvcApplVO svcApplVO = new SvcApplVO();
        svcApplVO.setAppId(OppfProperties.getProperty("Globals.integrated.account.srvId"));
        svcApplVO.setCustomerRegNo(customerRegNo);
        model.addAttribute("appId", svcApplVO.getAppId());

        //회원이 신청한 통합계좌조회 APP 리스트 조회 (서비스 신청 여부, 가상계좌 사용여부)
        List<SptCustomerServiceAccountProfileVO> accountProfileList = svcApplService.selectFintechSvcIntAcntAccountList(svcApplVO);
        model.addAttribute("accountProfileList", accountProfileList);
        if(null != accountProfileList && accountProfileList.size() > 0){
            Map<String, Object> companyMap = new HashMap<String, Object>();
            List<SptCustomerServiceAccountProfileVO> accountList = new ArrayList<>();
            int accountCnt = 0;
            int noLinkAccountCnt = 0;
            String pubCompanyCode = "";
            for(SptCustomerServiceAccountProfileVO sptCustomerServiceAccountProfileVO  : accountProfileList){
                // 미등록 계좌 카운트
                if("N".equals(sptCustomerServiceAccountProfileVO.getUseYn())){
                    noLinkAccountCnt ++;
                }else{
                    // 연결 계좌 카운트
                    accountCnt++;
                    // 증권사별 목록
                    if(!pubCompanyCode.equals(sptCustomerServiceAccountProfileVO.getPubcompanyCodeId())){
                        //연결계좌 리스트가 0이상이면 증권사 map에 추가
                        if(accountList.size() > 0){
                            companyMap.put(pubCompanyCode, accountList);
                        }
                        accountList = new ArrayList<>();
                        //증권사 코드 key
                        pubCompanyCode = sptCustomerServiceAccountProfileVO.getPubcompanyCodeId();
                        //통합계좌 승인 리스트 추가
                        accountList.add(sptCustomerServiceAccountProfileVO);
                    }else{
                        //통합계좌 승인 리스트 추가
                        accountList.add(sptCustomerServiceAccountProfileVO);
                    }
                }
            }
            //통합계좌 승인 리스트 확인
            if(accountList.size() > 0){
                companyMap.put(pubCompanyCode, accountList);
            }
            //증권사별 계좌 리스트
            model.addAttribute("companyMap", companyMap);
            //연결계좌수
            model.addAttribute("accountCnt", accountCnt);
            //미등록 계좌수
            model.addAttribute("noLinkAccountCnt", noLinkAccountCnt);
        }

        //회원 통합계좌 APP accessToken 조회
        SptCustomerAcntOauthTokenVO paramVO = new SptCustomerAcntOauthTokenVO();
        paramVO.setCustomerRegNo(customerRegNo);
        SptCustomerAcntOauthTokenVO sptCustomerAcntOauthTokenVO = intAcntOauthService.selectCustomerAcntOauthToken(paramVO, true);

        model.addAttribute("oauthVO", sptCustomerAcntOauthTokenVO);
        model.addAttribute("oauthLoginUrl", OppfProperties.getProperty("Globals.oauth.authorize.url"));
        model.addAttribute("callbackUrl", OppfProperties.getProperty("Globals.integrated.account.callBackUrl"));
        model.addAttribute("clientId", OppfProperties.getProperty("Globals.integrated.account.clientId"));
        model.addAttribute("scope", OppfProperties.getProperty("Globals.integrated.account.scope"));

        return modelView;
    }

    /**
     * @Method Name        : selectBalance
     * @Method description : [통합계좌조회]잔고 조회
     * @param              : IntAcntSearchVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/spt/myp/intAcnt/balance.ajax")
    private String selectBalance(@ModelAttribute("IntAcntSearchVO") IntAcntSearchVO paramVO,
                                 HttpServletRequest request,
                                 ModelMap model)throws Exception{

        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            //로그인 에러
            model.addAttribute("result", "01");
            return "jsonView";
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        String customerId = loginVO.getId();
        String customerCi = "";
        String customerNameKor = "";

        MbrRegVO mbrRegVO = cmmIntegratedAccountService.selectSptCustomerVerifyProfileInfo(customerId);

        if(mbrRegVO != null){
            customerCi = mbrRegVO.getCustomerVerify();
            customerNameKor = mbrRegVO.getCustomerNameKor();
            log.debug("[사용자]정보 취득後:customerCi="+customerCi);
            log.debug("[사용자]정보 취득後:customerNameKor="+customerNameKor);
        }else{
            //사용자정보 에러
            model.addAttribute("result", "02");
            return "jsonView";
        }

        //핀테크 기업 코드
        String comId = OppfProperties.getProperty("Globals.integrated.account.comId");
        //핀테크 서비스 코드
        String srvId = OppfProperties.getProperty("Globals.integrated.account.srvId");
        //통합계좌 개발/상용 모드
        String devMode = OppfProperties.getProperty("Globals.integrated.account.mode");

        SvcApplVO svcApplVO = new SvcApplVO();
        svcApplVO.setAppId(OppfProperties.getProperty("Globals.integrated.account.srvId"));
        svcApplVO.setCustomerRegNo(customerRegNo);
        //서비스포털 연결 계좌 리스트정보
        VtAccountListVO linkAccountListVO = cmmIntegratedAccountService.selectCommonMemberConsentListDev(svcApplVO, paramVO);

        VtAccountListVO vtAccountListVO;
        //상용환경
        if("prd".equals(devMode)){
            //핀테크 서비스 연계확인 계좌정보 리스트 조회 (상용 모드)
            vtAccountListVO = cmmIntegratedAccountService.selectCommonMemberConsentList(comId, srvId, customerCi, customerNameKor, paramVO);
        }else{
            vtAccountListVO = linkAccountListVO.clone();
        }

        //회원 통합계좌 APP accessToken 조회
        SptCustomerAcntOauthTokenVO tokenVO = new SptCustomerAcntOauthTokenVO();
        tokenVO.setCustomerRegNo(customerRegNo);
        SptCustomerAcntOauthTokenVO sptCustomerAcntOauthTokenVO = intAcntOauthService.selectCustomerAcntOauthToken(tokenVO, false);

        //account API 헤더정보
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add("apiKey", OppfProperties.getProperty("Globals.integrated.account.apiKey"));
        httpHeaders.add("Authorization", "Bearer " + sptCustomerAcntOauthTokenVO.getAccessToken());

        String ipAddr = CommonUtil.getLocalServerIp();
        String macAddr = "1866DA0D99D6";

        /*******************************  통합계좌 조회 시작*******************************/
        InteGratedAccountBalanceViewVO inteGratedAccountResponseVO = new InteGratedAccountBalanceViewVO();
        List<Object> resultList= new ArrayList<>();
        List<CommonFailViewVO> failList = new ArrayList<>();
        int totalCount = 0;
        int successCount = 0;
        int failCount = 0;
        //ExecutorService 생성 (멀티 스레드)
        ExecutorService executor = Executors.newFixedThreadPool(vtAccountListVO.getVtAccList().size() == 0 ? 1 : vtAccountListVO.getVtAccList().size());
        //결과를 저장할 리스트
        List<Future<IntAcntWorker>> futures = new ArrayList<Future<IntAcntWorker>>();
        //회원 가상계좌 리스트
        for(VtAccountVO vtAccountVO : vtAccountListVO.getVtAccList()){
            //가상계좌번호
            String vtAccNo = vtAccountVO.getVtAccNo();
            totalCount++;
            try{
                String tmpIpAddr = ipAddr;
                String assetType = "";
                //대신증권 IP 예외처리
                if(vtAccountVO.getEndpoint().indexOf("daishin") > 0){
                    tmpIpAddr = "192168010001";
                }
                //ebest증권 assetType 예외처리
                if(vtAccountVO.getEndpoint().indexOf("ebest") > 0){
                    assetType = "CASH";
                }else{
                    assetType = "ALL";
                }
                String payload = "{ \"partner\" : { \"comId\": \""+comId+"\",  \"srvId\": \""+srvId+"\"}," +
                        " \"commonHeader\" : { \"reqIdConsumer\": \"reqid-0001\",  \"ci\": \"" + customerCi + "\"}," +
                        " \"devInfo\" : { \"ipAddr\": \""+tmpIpAddr+"\",  \"macAddr\": \""+macAddr+"\"}," +
                        " \"accInfo\" : { \"vtAccNo\": \"" + vtAccNo + "\"}," +
                        " \"balanceRequestBody\" : { \"queryType\" : { \"assetType\": \""+assetType+"\"}}}";
                //상용모드:개발모드
                String accountApiUrl = "prd".equals(devMode) ? vtAccountVO.getEndpoint().replaceAll("apigw.koscom.co.kr","10.0.1.101") + "balance/search"
                        :  vtAccountVO.getEndpoint() + "balance/search";

                IntAcntWorker IntAcntWorker = new IntAcntWorker(accountApiUrl, httpHeaders, payload, cmmSIFInternalService,
                        vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), "", "");
                futures.add(executor.submit(IntAcntWorker));

            }catch (Exception e){
                log.debug(e.getMessage());
                CommonFailViewVO failViewVO = new CommonFailViewVO(vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), e.getMessage());
                failViewVO.setComName(CommonFunction.companyName(vtAccountVO.getComId()));
                failList.add(failViewVO);
                failCount++;
            }
        }
        //스레드 종료처리
        executor.shutdown();
        // 결과값 병합
        for (Future<IntAcntWorker> future : futures) {
            IntAcntWorker worker = future.get();
            if(null == worker.getResponseEntity()){
                CommonFailViewVO failViewVO = new CommonFailViewVO(worker.getComId(), worker.getVtAccNo(), worker.getVtAccAlias(),
                        new Gson().toJson(new ApiExceptionMessage(CodeConstants.OP_SPT, ApiExceptionMessage.EX_SEND_CHECK, "SocketTimeoutException: Read timed out", "")));
                failViewVO.setComName(CommonFunction.companyName(worker.getComId()));
                failList.add(failViewVO);
                failCount++;
            }else if(HttpStatus.OK.equals(worker.getResponseEntity().getStatusCode())){
                JSONObject jsonObject =  new JSONObject(worker.getResponseEntity().getBody());
                BalanceResponseViewVO balanceResponseVO = new Gson().fromJson(jsonObject.toString(),BalanceResponseViewVO.class);
                resultList.add(cmmIntegratedAccountService.setBalanceResponseVO(balanceResponseVO, worker.getComId(), worker.getVtAccNo()));
                successCount++;
            }else{
                CommonFailViewVO failViewVO = new CommonFailViewVO(worker.getComId(), worker.getVtAccNo(), worker.getVtAccAlias(), worker.getResponseEntity().getBody());
                failViewVO.setComName(CommonFunction.companyName(worker.getComId()));
                failList.add(failViewVO);
                failCount++;
            }
        }

        if(successCount > 0){
            //리스트 통합
            InteGratedAccountBalanceViewVO.BalanceResultVO integratedBalance = new InteGratedAccountBalanceViewVO.BalanceResultVO();
            MsgBalanceSummaryVO summary = new MsgBalanceSummaryVO();
            for(Object o : resultList){
                BalanceResponseViewVO balanceResponseVO = (BalanceResponseViewVO)o;
                BalanceViewVO balance = balanceResponseVO.getBalanceList().getBalance();
                if(null != balance.getSummary()){
                    cmmIntegratedAccountService.setBalanceSummary(summary, balance.getSummary());
                }
            }
            //기본정보
            integratedBalance.setSummary(summary);
            inteGratedAccountResponseVO.setTotalResult(integratedBalance);
        }

        //상용환경
        if("prd".equals(devMode)){
            //요청한 계좌정보가 서비스 연결 계좌 리스트에 존재하지 않는경우 실패 처리 (API 계약 해지, 증권사 계약해지)
            cmmIntegratedAccountService.setLinkAccountList(linkAccountListVO.getVtAccList(), vtAccountListVO.getVtAccList());
            int commonFailCount = cmmIntegratedAccountService.setCommonAccountFail(linkAccountListVO.getVtAccList(), failList);
            totalCount += commonFailCount;
            failCount += commonFailCount;
        }

        //전체수행, 성공, 실패 count
        inteGratedAccountResponseVO.setTotalCount(totalCount);
        inteGratedAccountResponseVO.setSuccessCount(successCount);
        inteGratedAccountResponseVO.setFailCount(failCount);
        //success List
        inteGratedAccountResponseVO.setResultList(resultList);
        //fail List
        inteGratedAccountResponseVO.setFailList(failList);
        /*******************************  통합계좌 조회 END*******************************/

        //조회성공
        model.addAttribute("result", "00");
        model.addAttribute("data", inteGratedAccountResponseVO);

        return "jsonView";
    }

    /**
     * @Method Name        : selectBalance
     * @Method description : [통합계좌조회]거래내역 조회
     * @param              : IntAcntSearchVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/spt/myp/intAcnt/transaction.ajax")
    private String selectTransaction(@ModelAttribute("IntAcntSearchVO") IntAcntSearchVO paramVO,
                                 HttpServletRequest request,
                                 ModelMap model)throws Exception{

        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            //로그인 에러
            model.addAttribute("result", "01");
            return "jsonView";
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        String customerId = loginVO.getId();
        String customerCi = "";
        String customerNameKor = "";

        MbrRegVO mbrRegVO = cmmIntegratedAccountService.selectSptCustomerVerifyProfileInfo(customerId);

        if(mbrRegVO != null){
            customerCi = mbrRegVO.getCustomerVerify();
            customerNameKor = mbrRegVO.getCustomerNameKor();
            log.debug("[사용자]정보 취득後:customerCi="+customerCi);
            log.debug("[사용자]정보 취득後:customerNameKor="+customerNameKor);
        }else{
            //사용자정보 에러
            model.addAttribute("result", "02");
            return "jsonView";
        }

        //핀테크 기업 코드
        String comId = OppfProperties.getProperty("Globals.integrated.account.comId");
        //핀테크 서비스 코드
        String srvId = OppfProperties.getProperty("Globals.integrated.account.srvId");
        //통합계좌 개발/상용 모드
        String devMode = OppfProperties.getProperty("Globals.integrated.account.mode");

        SvcApplVO svcApplVO = new SvcApplVO();
        svcApplVO.setAppId(OppfProperties.getProperty("Globals.integrated.account.srvId"));
        svcApplVO.setCustomerRegNo(customerRegNo);
        //서비스포털 연결 계좌 리스트정보
        VtAccountListVO linkAccountListVO = cmmIntegratedAccountService.selectCommonMemberConsentListDev(svcApplVO, paramVO);

        VtAccountListVO vtAccountListVO;
        //상용환경
        if("prd".equals(devMode)){
            //핀테크 서비스 연계확인 계좌정보 리스트 조회 (상용 모드)
            vtAccountListVO = cmmIntegratedAccountService.selectCommonMemberConsentList(comId, srvId, customerCi, customerNameKor, paramVO);
        }else{
            vtAccountListVO = linkAccountListVO.clone();
        }

        //회원 통합계좌 APP accessToken 조회
        SptCustomerAcntOauthTokenVO tokenVO = new SptCustomerAcntOauthTokenVO();
        tokenVO.setCustomerRegNo(customerRegNo);
        SptCustomerAcntOauthTokenVO sptCustomerAcntOauthTokenVO = intAcntOauthService.selectCustomerAcntOauthToken(tokenVO, false);

        //account API 헤더정보
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add("apiKey", OppfProperties.getProperty("Globals.integrated.account.apiKey"));
        httpHeaders.add("Authorization", "Bearer " + sptCustomerAcntOauthTokenVO.getAccessToken());

        String ipAddr = CommonUtil.getLocalServerIp();
        String macAddr = "1866DA0D99D6";

        //조회시작일자 종료일자
        String fromDate = paramVO.getSearchDateFrom().replaceAll("-","");
        String toDate = paramVO.getSearchDateTo().replaceAll("-","");

        /*******************************  통합계좌 조회 시작*******************************/
        InteGratedAccountViewVO inteGratedAccountResponseVO = new InteGratedAccountViewVO();
        List<Object> resultList= new ArrayList<>();
        List<CommonFailViewVO> failList = new ArrayList<>();
        int totalCount = 0;
        int successCount = 0;
        int failCount = 0;
        //ExecutorService 생성 (멀티 스레드)
        ExecutorService executor = Executors.newFixedThreadPool(vtAccountListVO.getVtAccList().size());
        //결과를 저장할 리스트
        List<Future<IntAcntWorker>> futures = new ArrayList<Future<IntAcntWorker>>();
        //회원 가상계좌 리스트
        for(VtAccountVO vtAccountVO : vtAccountListVO.getVtAccList()){
            //가상계좌번호
            String vtAccNo = vtAccountVO.getVtAccNo();
            totalCount++;
            try{
                String tmpIpAddr = ipAddr;
                String assetType = "";
                //대신증권 IP 예외처리
                if(vtAccountVO.getEndpoint().indexOf("daishin") > 0){
                    tmpIpAddr = "192168010001";
                }
                //종목 코드/이름 (종목 이름인 경우 코드로 변환)
                String searchIsinType = paramVO.getSearchIsinType();
                String isinCode = paramVO.getSearchIsin();
                String paramIsinCode = "";
                String side = "";

                if(!"".equals(isinCode)){
                    //종목코드 확인
                    if("code".equals(searchIsinType)){
                        isinCode = isinCode.toUpperCase();
                    }
                }

                String payload = "{ \"partner\" : { \"comId\": \""+comId+"\",  \"srvId\": \""+srvId+"\"}," +
                        " \"commonHeader\" : { \"reqIdConsumer\": \"reqid-0001\",  \"ci\": \"" + customerCi + "\"}," +
                        " \"devInfo\" : { \"ipAddr\": \""+tmpIpAddr+"\",  \"macAddr\": \""+macAddr+"\"}," +
                        " \"accInfo\" : { \"vtAccNo\": \"" + vtAccNo + "\"}," +
                        " \"transactionHistoryRequestBody\" : { \"queryParams\" : { \"fromDate\": \""+fromDate+"\", \"toDate\": \""+toDate+"\", \"isinCode\": \""+paramIsinCode+"\", \"side\": \""+side+"\"}}}";
                //상용모드:개발모드
                String accountApiUrl = "prd".equals(devMode) ? vtAccountVO.getEndpoint().replaceAll("apigw.koscom.co.kr","10.0.1.101") + "transaction/search"
                        :  vtAccountVO.getEndpoint() + "transaction/search";

                IntAcntWorker IntAcntWorker = new IntAcntWorker(accountApiUrl, httpHeaders, payload, cmmSIFInternalService,
                        vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), searchIsinType, isinCode);
                futures.add(executor.submit(IntAcntWorker));

            }catch (Exception e){
                log.debug(e.getMessage());
                CommonFailViewVO failViewVO = new CommonFailViewVO(vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), e.getMessage());
                failViewVO.setComName(CommonFunction.companyName(vtAccountVO.getComId()));
                failList.add(failViewVO);
                failCount++;
            }
        }

        //스레드 종료처리
        executor.shutdown();
        // 결과값 병합
        for (Future<IntAcntWorker> future : futures) {
            IntAcntWorker worker = future.get();
            if(null == worker.getResponseEntity()){
                CommonFailViewVO failViewVO = new CommonFailViewVO(worker.getComId(), worker.getVtAccNo(), worker.getVtAccAlias(),
                        new Gson().toJson(new ApiExceptionMessage(CodeConstants.OP_SPT, ApiExceptionMessage.EX_SEND_CHECK, "SocketTimeoutException: Read timed out", "")));
                failViewVO.setComName(CommonFunction.companyName(worker.getComId()));
                failList.add(failViewVO);
                failCount++;
            }else if(HttpStatus.OK.equals(worker.getResponseEntity().getStatusCode())){
                JSONObject jsonObject =  new JSONObject(worker.getResponseEntity().getBody());
                TransactionResponseViewVO transactionResponseVO = new Gson().fromJson(jsonObject.toString(),TransactionResponseViewVO.class);
                resultList.add(cmmIntegratedAccountService.setTransactionResponseVO(transactionResponseVO, worker.getComId(), worker.getVtAccNo(), worker.getSearchIsinType(), worker.getIsinCode()));
                successCount++;
            }else{
                CommonFailViewVO failViewVO = new CommonFailViewVO(worker.getComId(), worker.getVtAccNo(), worker.getVtAccAlias(), worker.getResponseEntity().getBody());
                failViewVO.setComName(CommonFunction.companyName(worker.getComId()));
                failList.add(failViewVO);
                failCount++;
            }
        }

        //상용환경
        if("prd".equals(devMode)){
            //요청한 계좌정보가 서비스 연결 계좌 리스트에 존재하지 않는경우 실패 처리 (API 계약 해지, 증권사 계약해지)
            cmmIntegratedAccountService.setLinkAccountList(linkAccountListVO.getVtAccList(), vtAccountListVO.getVtAccList());
            int commonFailCount = cmmIntegratedAccountService.setCommonAccountFail(linkAccountListVO.getVtAccList(), failList);
            totalCount += commonFailCount;
            failCount += commonFailCount;
        }

        //전체수행, 성공, 실패 count
        inteGratedAccountResponseVO.setTotalCount(totalCount);
        inteGratedAccountResponseVO.setSuccessCount(successCount);
        inteGratedAccountResponseVO.setFailCount(failCount);
        //success List
        inteGratedAccountResponseVO.setResultList(resultList);
        //fail List
        inteGratedAccountResponseVO.setFailList(failList);
        /*******************************  통합계좌 조회 END*******************************/

        //조회성공
        model.addAttribute("result", "00");
        model.addAttribute("data", inteGratedAccountResponseVO);

        return "jsonView";
    }

    /**
     * @Method Name        : selectBalance
     * @Method description : [통합계좌조회]거래내역 조회 Excel
     * @param              : IntAcntSearchVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/spt/myp/intAcnt/transactionExcel.do")
    private String selectTransactionExcel(@ModelAttribute("IntAcntSearchVO") IntAcntSearchVO paramVO,
                                     HttpServletRequest request,
                                     ModelMap model)throws Exception{
        String modelView = "spt/myp/intAcnt/intAcntTransactionExcel";

        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            //로그인 에러
            return modelView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        String customerId = loginVO.getId();
        String customerCi = "";
        String customerNameKor = "";

        MbrRegVO mbrRegVO = cmmIntegratedAccountService.selectSptCustomerVerifyProfileInfo(customerId);

        if(mbrRegVO != null){
            customerCi = mbrRegVO.getCustomerVerify();
            customerNameKor = mbrRegVO.getCustomerNameKor();
            log.debug("[사용자]정보 취득後:customerCi="+customerCi);
            log.debug("[사용자]정보 취득後:customerNameKor="+customerNameKor);
        }else{
            //사용자정보 에러
            model.addAttribute("result", "02");
            return modelView;
        }

        //핀테크 기업 코드
        String comId = OppfProperties.getProperty("Globals.integrated.account.comId");
        //핀테크 서비스 코드
        String srvId = OppfProperties.getProperty("Globals.integrated.account.srvId");
        //통합계좌 개발/상용 모드
        String devMode = OppfProperties.getProperty("Globals.integrated.account.mode");

        SvcApplVO svcApplVO = new SvcApplVO();
        svcApplVO.setAppId(OppfProperties.getProperty("Globals.integrated.account.srvId"));
        svcApplVO.setCustomerRegNo(customerRegNo);
        //서비스포털 연결 계좌 리스트정보
        VtAccountListVO linkAccountListVO = cmmIntegratedAccountService.selectCommonMemberConsentListDev(svcApplVO, paramVO);

        VtAccountListVO vtAccountListVO;
        //상용환경
        if("prd".equals(devMode)){
            //핀테크 서비스 연계확인 계좌정보 리스트 조회 (상용 모드)
            vtAccountListVO = cmmIntegratedAccountService.selectCommonMemberConsentList(comId, srvId, customerCi, customerNameKor, paramVO);
        }else{
            vtAccountListVO = linkAccountListVO.clone();
        }

        //회원 통합계좌 APP accessToken 조회
        SptCustomerAcntOauthTokenVO tokenVO = new SptCustomerAcntOauthTokenVO();
        tokenVO.setCustomerRegNo(customerRegNo);
        SptCustomerAcntOauthTokenVO sptCustomerAcntOauthTokenVO = intAcntOauthService.selectCustomerAcntOauthToken(tokenVO, false);

        //account API 헤더정보
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add("apiKey", OppfProperties.getProperty("Globals.integrated.account.apiKey"));
        httpHeaders.add("Authorization", "Bearer " + sptCustomerAcntOauthTokenVO.getAccessToken());

        String ipAddr = CommonUtil.getLocalServerIp();
        String macAddr = "1866DA0D99D6";

        //조회시작일자 종료일자
        String fromDate = paramVO.getSearchDateFrom().replaceAll("-","");
        String toDate = paramVO.getSearchDateTo().replaceAll("-","");

        /*******************************  통합계좌 조회 시작*******************************/
        List<TransactionResponseViewVO> resultList= new ArrayList<TransactionResponseViewVO>();
        //ExecutorService 생성 (멀티 스레드)
        ExecutorService executor = Executors.newFixedThreadPool(vtAccountListVO.getVtAccList().size());
        //결과를 저장할 리스트
        List<Future<IntAcntWorker>> futures = new ArrayList<Future<IntAcntWorker>>();
        //회원 가상계좌 리스트
        for(VtAccountVO vtAccountVO : vtAccountListVO.getVtAccList()){
            //가상계좌번호
            String vtAccNo = vtAccountVO.getVtAccNo();
            try{
                String tmpIpAddr = ipAddr;
                String assetType = "";
                //대신증권 IP 예외처리
                if(vtAccountVO.getEndpoint().indexOf("daishin") > 0){
                    tmpIpAddr = "192168010001";
                }
                //종목 코드/이름 (종목 이름인 경우 코드로 변환)
                String searchIsinType = paramVO.getSearchIsinType();
                String isinCode = paramVO.getSearchIsin();
                String paramIsinCode = "";
                String side = "";

                if(!"".equals(isinCode)){
                    //종목코드 확인
                    if("code".equals(searchIsinType)){
                        isinCode = isinCode.toUpperCase();
                    }
                }

                String payload = "{ \"partner\" : { \"comId\": \""+comId+"\",  \"srvId\": \""+srvId+"\"}," +
                        " \"commonHeader\" : { \"reqIdConsumer\": \"reqid-0001\",  \"ci\": \"" + customerCi + "\"}," +
                        " \"devInfo\" : { \"ipAddr\": \""+tmpIpAddr+"\",  \"macAddr\": \""+macAddr+"\"}," +
                        " \"accInfo\" : { \"vtAccNo\": \"" + vtAccNo + "\"}," +
                        " \"transactionHistoryRequestBody\" : { \"queryParams\" : { \"fromDate\": \""+fromDate+"\", \"toDate\": \""+toDate+"\", \"isinCode\": \""+paramIsinCode+"\", \"side\": \""+side+"\"}}}";

                //상용모드:개발모드
                String accountApiUrl = "prd".equals(devMode) ? vtAccountVO.getEndpoint().replaceAll("apigw.koscom.co.kr","10.0.1.101") + "transaction/search"
                        :  vtAccountVO.getEndpoint() + "transaction/search";

                IntAcntWorker IntAcntWorker = new IntAcntWorker(accountApiUrl, httpHeaders, payload, cmmSIFInternalService,
                        vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), searchIsinType, isinCode);
                futures.add(executor.submit(IntAcntWorker));

            }catch (Exception e){
                log.debug(e.getMessage());
            }
        }

        //스레드 종료처리
        executor.shutdown();
        // 결과값 병합
        for (Future<IntAcntWorker> future : futures) {
            IntAcntWorker worker = future.get();
            if(null != worker.getResponseEntity() && HttpStatus.OK.equals(worker.getResponseEntity().getStatusCode())){
                JSONObject jsonObject =  new JSONObject(worker.getResponseEntity().getBody());
                TransactionResponseViewVO transactionResponseVO = new Gson().fromJson(jsonObject.toString(),TransactionResponseViewVO.class);
                resultList.add(cmmIntegratedAccountService.setTransactionResponseVO(transactionResponseVO, worker.getComId(), worker.getVtAccNo(), worker.getSearchIsinType(), worker.getIsinCode()));
            }
        }

        /*******************************  통합계좌 조회 END*******************************/
        //조회성공
        model.addAttribute("resultList", resultList);

        return modelView;
    }

    /**
     * @Method Name        : selectBalance
     * @Method description : [통합계좌조회]포토폴리오 조회
     * @param              : IntAcntSearchVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/spt/myp/intAcnt/portfolio.ajax")
    private String selectPortfolio(@ModelAttribute("IntAcntSearchVO") IntAcntSearchVO paramVO,
                                 HttpServletRequest request,
                                 ModelMap model)throws Exception{

        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            //로그인 에러
            model.addAttribute("result", "01");
            return "jsonView";
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        String customerId = loginVO.getId();
        String customerCi = "";
        String customerNameKor = "";

        MbrRegVO mbrRegVO = cmmIntegratedAccountService.selectSptCustomerVerifyProfileInfo(customerId);

        if(mbrRegVO != null){
            customerCi = mbrRegVO.getCustomerVerify();
            customerNameKor = mbrRegVO.getCustomerNameKor();
            log.debug("[사용자]정보 취득後:customerCi="+customerCi);
            log.debug("[사용자]정보 취득後:customerNameKor="+customerNameKor);
        }else{
            //사용자정보 에러
            model.addAttribute("result", "02");
            return "jsonView";
        }

        //핀테크 기업 코드
        String comId = OppfProperties.getProperty("Globals.integrated.account.comId");
        //핀테크 서비스 코드
        String srvId = OppfProperties.getProperty("Globals.integrated.account.srvId");
        //통합계좌 개발/상용 모드
        String devMode = OppfProperties.getProperty("Globals.integrated.account.mode");

        SvcApplVO svcApplVO = new SvcApplVO();
        svcApplVO.setAppId(OppfProperties.getProperty("Globals.integrated.account.srvId"));
        svcApplVO.setCustomerRegNo(customerRegNo);
        //서비스포털 연결 계좌 리스트정보
        VtAccountListVO linkAccountListVO = cmmIntegratedAccountService.selectCommonMemberConsentListDev(svcApplVO, paramVO);

        VtAccountListVO vtAccountListVO;
        //상용환경
        if("prd".equals(devMode)){
            //핀테크 서비스 연계확인 계좌정보 리스트 조회 (상용 모드)
            vtAccountListVO = cmmIntegratedAccountService.selectCommonMemberConsentList(comId, srvId, customerCi, customerNameKor, paramVO);
        }else{
            vtAccountListVO = linkAccountListVO.clone();
        }

        //회원 통합계좌 APP accessToken 조회
        SptCustomerAcntOauthTokenVO tokenVO = new SptCustomerAcntOauthTokenVO();
        tokenVO.setCustomerRegNo(customerRegNo);
        SptCustomerAcntOauthTokenVO sptCustomerAcntOauthTokenVO = intAcntOauthService.selectCustomerAcntOauthToken(tokenVO, false);

        //account API 헤더정보
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add("apiKey", OppfProperties.getProperty("Globals.integrated.account.apiKey"));
        httpHeaders.add("Authorization", "Bearer " + sptCustomerAcntOauthTokenVO.getAccessToken());

        String ipAddr = CommonUtil.getLocalServerIp();
        String macAddr = "1866DA0D99D6";

        /*******************************  통합계좌 조회 시작*******************************/
        InteGratedAccountViewVO inteGratedAccountResponseVO = new InteGratedAccountViewVO();
        List<Object> resultList= new ArrayList<>();
        List<CommonFailViewVO> failList = new ArrayList<>();
        int totalCount = 0;
        int successCount = 0;
        int failCount = 0;
        //ExecutorService 생성 (멀티 스레드)
        ExecutorService executor = Executors.newFixedThreadPool(vtAccountListVO.getVtAccList().size());
        //결과를 저장할 리스트
        List<Future<IntAcntWorker>> futures = new ArrayList<Future<IntAcntWorker>>();
        //회원 가상계좌 리스트
        for(VtAccountVO vtAccountVO : vtAccountListVO.getVtAccList()){
            //가상계좌번호
            String vtAccNo = vtAccountVO.getVtAccNo();
            totalCount++;
            try{
                String tmpIpAddr = ipAddr;
                String assetType = "";
                //대신증권 IP 예외처리
                if(vtAccountVO.getEndpoint().indexOf("daishin") > 0){
                    tmpIpAddr = "192168010001";
                }
                //ebest증권 assetType 예외처리
                if(vtAccountVO.getEndpoint().indexOf("ebest") > 0){
                    assetType = "CASH";
                }else{
                    assetType = "ALL";
                }
                String payload = "{ \"partner\" : { \"comId\": \""+comId+"\",  \"srvId\": \""+srvId+"\"}," +
                        " \"commonHeader\" : { \"reqIdConsumer\": \"reqid-0001\",  \"ci\": \"" + customerCi + "\"}," +
                        " \"devInfo\" : { \"ipAddr\": \""+tmpIpAddr+"\",  \"macAddr\": \""+macAddr+"\"}," +
                        " \"accInfo\" : { \"vtAccNo\": \"" + vtAccNo + "\"}," +
                        " \"portfolioRequestBody\" : { \"queryType\" : { \"assetType\": \""+assetType+"\", \"rspType\": \"QTY\"}}}";
                //상용모드:개발모드
                String accountApiUrl = "prd".equals(devMode) ? vtAccountVO.getEndpoint().replaceAll("apigw.koscom.co.kr","10.0.1.101") + "portfolio/search"
                        :  vtAccountVO.getEndpoint() + "portfolio/search";

                IntAcntWorker IntAcntWorker = new IntAcntWorker(accountApiUrl, httpHeaders, payload, cmmSIFInternalService,
                        vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), "", "");
                futures.add(executor.submit(IntAcntWorker));

            }catch (Exception e){
                log.debug(e.getMessage());
                CommonFailViewVO failViewVO = new CommonFailViewVO(vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), e.getMessage());
                failViewVO.setComName(CommonFunction.companyName(vtAccountVO.getComId()));
                failList.add(failViewVO);
                failCount++;
            }
        }

        //스레드 종료처리
        executor.shutdown();
        // 결과값 병합
        for (Future<IntAcntWorker> future : futures) {
            IntAcntWorker worker = future.get();
            if(null == worker.getResponseEntity()){
                CommonFailViewVO failViewVO = new CommonFailViewVO(worker.getComId(), worker.getVtAccNo(), worker.getVtAccAlias(),
                        new Gson().toJson(new ApiExceptionMessage(CodeConstants.OP_SPT, ApiExceptionMessage.EX_SEND_CHECK, "SocketTimeoutException: Read timed out", "")));
                failViewVO.setComName(CommonFunction.companyName(worker.getComId()));
                failList.add(failViewVO);
                failCount++;
            }else if(HttpStatus.OK.equals(worker.getResponseEntity().getStatusCode())){
                JSONObject jsonObject =  new JSONObject(worker.getResponseEntity().getBody());
                PortfolioResponseViewVO portfolioResponseVO = new Gson().fromJson(jsonObject.toString(),PortfolioResponseViewVO.class);
                resultList.add(cmmIntegratedAccountService.setPortfolioResponseVO(portfolioResponseVO, worker.getComId(), worker.getVtAccNo()));
                successCount++;
            }else{
                CommonFailViewVO failViewVO = new CommonFailViewVO(worker.getComId(), worker.getVtAccNo(), worker.getVtAccAlias(), worker.getResponseEntity().getBody());
                failViewVO.setComName(CommonFunction.companyName(worker.getComId()));
                failList.add(failViewVO);
                failCount++;
            }
        }

        //상용환경
        if("prd".equals(devMode)){
            //요청한 계좌정보가 서비스 연결 계좌 리스트에 존재하지 않는경우 실패 처리 (API 계약 해지, 증권사 계약해지)
            cmmIntegratedAccountService.setLinkAccountList(linkAccountListVO.getVtAccList(), vtAccountListVO.getVtAccList());
            int commonFailCount = cmmIntegratedAccountService.setCommonAccountFail(linkAccountListVO.getVtAccList(), failList);
            totalCount += commonFailCount;
            failCount += commonFailCount;
        }

        //전체수행, 성공, 실패 count
        inteGratedAccountResponseVO.setTotalCount(totalCount);
        inteGratedAccountResponseVO.setSuccessCount(successCount);
        inteGratedAccountResponseVO.setFailCount(failCount);
        //success List
        inteGratedAccountResponseVO.setResultList(resultList);
        //fail List
        inteGratedAccountResponseVO.setFailList(failList);
        /*******************************  통합계좌 조회 END*******************************/

        //조회성공
        model.addAttribute("result", "00");
        model.addAttribute("data", inteGratedAccountResponseVO);

        return "jsonView";
    }

    /**
     * @Method Name        : selectInterest
     * @Method description : [통합계좌조회]관심종목 조회
     * @param              : IntAcntSearchVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/spt/myp/intAcnt/interest.ajax")
    private String selectInterest(@ModelAttribute("IntAcntSearchVO") IntAcntSearchVO paramVO,
                                   HttpServletRequest request,
                                   ModelMap model)throws Exception{

        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            //로그인 에러
            model.addAttribute("result", "01");
            return "jsonView";
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        String customerId = loginVO.getId();
        String customerCi = "";
        String customerNameKor = "";

        MbrRegVO mbrRegVO = cmmIntegratedAccountService.selectSptCustomerVerifyProfileInfo(customerId);

        if(mbrRegVO != null){
            customerCi = mbrRegVO.getCustomerVerify();
            customerNameKor = mbrRegVO.getCustomerNameKor();
            log.debug("[사용자]정보 취득後:customerCi="+customerCi);
            log.debug("[사용자]정보 취득後:customerNameKor="+customerNameKor);
        }else{
            //사용자정보 에러
            model.addAttribute("result", "02");
            return "jsonView";
        }

        //핀테크 기업 코드
        String comId = OppfProperties.getProperty("Globals.integrated.account.comId");
        //핀테크 서비스 코드
        String srvId = OppfProperties.getProperty("Globals.integrated.account.srvId");
        //통합계좌 개발/상용 모드
        String devMode = OppfProperties.getProperty("Globals.integrated.account.mode");

        SvcApplVO svcApplVO = new SvcApplVO();
        svcApplVO.setAppId(OppfProperties.getProperty("Globals.integrated.account.srvId"));
        svcApplVO.setCustomerRegNo(customerRegNo);
        //서비스포털 연결 계좌 리스트정보
        VtAccountListVO linkAccountListVO = cmmIntegratedAccountService.selectCommonMemberConsentListDev(svcApplVO, paramVO);

        VtAccountListVO vtAccountListVO;
        //상용환경
        if("prd".equals(devMode)){
            //핀테크 서비스 연계확인 계좌정보 리스트 조회 (상용 모드)
            vtAccountListVO = cmmIntegratedAccountService.selectCommonMemberConsentList(comId, srvId, customerCi, customerNameKor, paramVO);
        }else{
            vtAccountListVO = linkAccountListVO.clone();
        }

        //회원 통합계좌 APP accessToken 조회
        SptCustomerAcntOauthTokenVO tokenVO = new SptCustomerAcntOauthTokenVO();
        tokenVO.setCustomerRegNo(customerRegNo);
        SptCustomerAcntOauthTokenVO sptCustomerAcntOauthTokenVO = intAcntOauthService.selectCustomerAcntOauthToken(tokenVO, false);

        //account API 헤더정보
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add("apiKey", OppfProperties.getProperty("Globals.integrated.account.apiKey"));
        httpHeaders.add("Authorization", "Bearer " + sptCustomerAcntOauthTokenVO.getAccessToken());

        String ipAddr = CommonUtil.getLocalServerIp();
        String macAddr = "1866DA0D99D6";

        /*******************************  통합계좌 조회 시작*******************************/
        InteGratedAccountViewVO inteGratedAccountResponseVO = new InteGratedAccountViewVO();
        List<Object> resultList= new ArrayList<>();
        List<CommonFailViewVO> failList = new ArrayList<>();
        int totalCount = 0;
        int successCount = 0;
        int failCount = 0;
        //ExecutorService 생성 (멀티 스레드)
        ExecutorService executor = Executors.newFixedThreadPool(vtAccountListVO.getVtAccList().size());
        //결과를 저장할 리스트
        List<Future<IntAcntWorker>> futures = new ArrayList<Future<IntAcntWorker>>();
        //회원 가상계좌 리스트
        for(VtAccountVO vtAccountVO : vtAccountListVO.getVtAccList()){
            //가상계좌번호
            String vtAccNo = vtAccountVO.getVtAccNo();
            totalCount++;
            try{
                String tmpIpAddr = ipAddr;
                //대신증권 IP 예외처리
                if(vtAccountVO.getEndpoint().indexOf("daishin") > 0){
                    tmpIpAddr = "192168010001";
                }

                String payload = "{ \"partner\" : { \"comId\": \""+comId+"\",  \"srvId\": \""+srvId+"\"}," +
                        " \"commonHeader\" : { \"reqIdConsumer\": \"reqid-0001\",  \"ci\": \"" + customerCi + "\"}," +
                        " \"devInfo\" : { \"ipAddr\": \""+tmpIpAddr+"\",  \"macAddr\": \""+macAddr+"\"}," +
                        " \"accInfo\" : { \"vtAccNo\": \"" + vtAccNo + "\"}}";

                //상용모드:개발모드
                String accountApiUrl = "prd".equals(devMode) ? vtAccountVO.getEndpoint().replaceAll("apigw.koscom.co.kr","10.0.1.101") + "interest/search"
                        :  vtAccountVO.getEndpoint() + "interest/search";

                IntAcntWorker IntAcntWorker = new IntAcntWorker(accountApiUrl, httpHeaders, payload, cmmSIFInternalService,
                        vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), "", "");
                futures.add(executor.submit(IntAcntWorker));

            }catch (Exception e){
                log.debug(e.getMessage());
                CommonFailViewVO failViewVO = new CommonFailViewVO(vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), e.getMessage());
                failViewVO.setComName(CommonFunction.companyName(vtAccountVO.getComId()));
                failList.add(failViewVO);
                failCount++;
            }
        }

        //스레드 종료처리
        executor.shutdown();
        // 결과값 병합
        for (Future<IntAcntWorker> future : futures) {
            IntAcntWorker worker = future.get();
            if(null == worker.getResponseEntity()){
                CommonFailViewVO failViewVO = new CommonFailViewVO(worker.getComId(), worker.getVtAccNo(), worker.getVtAccAlias(),
                        new Gson().toJson(new ApiExceptionMessage(CodeConstants.OP_SPT, ApiExceptionMessage.EX_SEND_CHECK, "SocketTimeoutException: Read timed out", "")));
                failViewVO.setComName(CommonFunction.companyName(worker.getComId()));
                failList.add(failViewVO);
                failCount++;
            }else if(HttpStatus.OK.equals(worker.getResponseEntity().getStatusCode())){
                JSONObject jsonObject =  new JSONObject(worker.getResponseEntity().getBody());
                InterestResponseViewVO interestResponseVO = new Gson().fromJson(jsonObject.toString(),InterestResponseViewVO.class);
                resultList.add(cmmIntegratedAccountService.setInterestResponseVO(interestResponseVO, worker.getComId()));
                successCount++;
            }else{
                CommonFailViewVO failViewVO = new CommonFailViewVO(worker.getComId(), worker.getVtAccNo(), worker.getVtAccAlias(), worker.getResponseEntity().getBody());
                failViewVO.setComName(CommonFunction.companyName(worker.getComId()));
                failList.add(failViewVO);
                failCount++;
            }
        }

        //상용환경
        if("prd".equals(devMode)){
            //요청한 계좌정보가 서비스 연결 계좌 리스트에 존재하지 않는경우 실패 처리 (API 계약 해지, 증권사 계약해지)
            cmmIntegratedAccountService.setLinkAccountList(linkAccountListVO.getVtAccList(), vtAccountListVO.getVtAccList());
            int commonFailCount = cmmIntegratedAccountService.setCommonAccountFail(linkAccountListVO.getVtAccList(), failList);
            totalCount += commonFailCount;
            failCount += commonFailCount;
        }

        //전체수행, 성공, 실패 count
        inteGratedAccountResponseVO.setTotalCount(totalCount);
        inteGratedAccountResponseVO.setSuccessCount(successCount);
        inteGratedAccountResponseVO.setFailCount(failCount);
        //success List
        inteGratedAccountResponseVO.setResultList(resultList);
        //fail List
        inteGratedAccountResponseVO.setFailList(failList);
        /*******************************  통합계좌 조회 END*******************************/

        //조회성공
        model.addAttribute("result", "00");
        model.addAttribute("data", inteGratedAccountResponseVO);

        return "jsonView";
    }

}
