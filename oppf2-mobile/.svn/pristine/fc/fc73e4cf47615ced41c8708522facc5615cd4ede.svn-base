package kr.co.koscom.oppfm.intAcnt.service.impl;

import com.google.gson.Gson;
import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.*;
import kr.co.koscom.oppfm.intAcnt.dao.IntAccountMapper;
import kr.co.koscom.oppfm.intAcnt.model.*;
import kr.co.koscom.oppfm.intAcnt.service.*;
import kr.co.koscom.oppfm.member.model.MemberVerifyRes;
import kr.co.koscom.oppfm.oauth.dao.OauthMapper;
import kr.co.koscom.oppfm.oauth.model.OauthAppReq;
import kr.co.koscom.oppfm.oauth.model.OauthAppRes;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * FileName : IntAccountServiceImpl
 *
 * Description : IntAccountServiceImpl
 *
 * Created by LHT on 2017. 5. 16..
 */
@Slf4j
@Service
public class IntAccountServiceImpl implements IntAccountService {

    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private IntAccountMapper intAccountMapper;
    @Autowired
    private OauthMapper oauthMapper;
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private InterestService interestService;

    @Value("#{config['Globals.integrated.account.apiKey']}")
    String accountApiKey;
    @Value("#{config['Globals.northbound.url']}")
    String northboundUrl;
    //핀테크 기업 코드
    @Value("#{config['Globals.integrated.account.comId']}")
    String comId;
    //핀테크 서비스 코드
    @Value("#{config['Globals.integrated.account.srvId']}")
    String srvId;
    //통합계좌 개발/상용 모드
    @Value("#{config['Globals.integrated.account.mode']}")
    String devMode;
    @Value("#{config['Globals.integrated.account.scope']}")
    String intAccountScope;
    // 도메인 서버 아이피
    @Value("#{config['Globals.mobile.domain.nslookup.ip']}")
    String serverNslookupIp;
    @Value("#{config['Globals.spt.domain']}")
    String sptDomain;
    @Value("#{config['Globals.integrated.account.allCompany']}")
    String allAssetCompanys;

    private static final String ERROR_CATEGORY = "op-spt";
    private static final String ERROR_NOT_FOUND_ACCOUNT = "[관리자문의] 가상계좌정보가 존재하지 않습니다";

    /**
     * getOauthInfo
     *
     * @param intAccountReq
     */
    @Override
    public CommonResponse getIntAccountList(IntAccountReq intAccountReq) {
        //통합계좌조회 APP ID
        intAccountReq.setAppId(srvId);
        //통합계좌 증권사 연결 리스트
        List<AppAccountProfileRes> resList = intAccountMapper.selectIntAccountList(intAccountReq);
        Map<String, Object> body = new HashMap<>();

        if(null == resList || resList.size() == 0){
            //통합계좌 APP 미신청 상태
            throw new CommonException(ErrorCodeConstants.NOT_FOUND_INT_ACCOUNT, new String[]{srvId});
        }else{
            //통합계좌 APP access_token 정보 조회
            IntAccountTokenRes intAccountTokenRes = intAccountMapper.selectIntAccountOauthToken(intAccountReq.getCustomerRegNo());
            //AccessToken 유효성 검증
            if(null != intAccountTokenRes) {
                HttpStatus httpStatus = getAccessTokenCheck(intAccountTokenRes.getAccessToken());
                //400 Bad Request "error_description" : "Access Token does not exist(expired, revoked, replaced, unknown...)"
                if (HttpStatus.BAD_REQUEST.equals(httpStatus) || HttpStatus.UNAUTHORIZED.equals(httpStatus)) {
                    //AccessToken 만료
                    throw new CommonException(ErrorCodeConstants.INT_TOKEN_EXPIRATION, null);
                }else{
                    //금투사별 계좌 정보 임시 리스트
                    List<AppAccountProfileRes> accountList = new ArrayList<AppAccountProfileRes>();
                    //response에 리턴 할 통합계좌 APP 연결정보
                    List<AppCompanyProfileRes> appCompanyProfileRes = new ArrayList<AppCompanyProfileRes>();

                    //계좌연결 카운트
                    int accountCnt = 0;
                    //미등록 연결 계좌 카운트
                    int noLinkAccountCnt = 0;
                    //금투사 코드
                    String pubCompanyCode = "";
                    String pubCompanyName = "";
                    for(AppAccountProfileRes accountProfileRes : resList){
                        // 미등록 계좌 카운트
                        if("N".equals(accountProfileRes.getUseYn())){
                            noLinkAccountCnt ++;
                        }else{
                            // 연결 계좌 카운트
                            accountCnt++;
                            // 증권사별 목록
                            if(!pubCompanyCode.equals(accountProfileRes.getPubcompanyCodeId())){
                                //연결계좌 리스트가 0이상이면 증권사 정보에 추가
                                if(accountList.size() > 0){
                                    AppCompanyProfileRes res = new AppCompanyProfileRes();
                                    res.setCompanyCodeId(pubCompanyCode);
                                    res.setCompanyNameEng(pubCompanyName);
                                    res.setAccountList(accountList);
                                    appCompanyProfileRes.add(res);
                                }
                                accountList = new ArrayList<AppAccountProfileRes>();
                                //증권사 코드 key
                                pubCompanyCode = accountProfileRes.getPubcompanyCodeId();
                                pubCompanyName = accountProfileRes.getPubcompanyName();
                                //통합계좌 승인 리스트 추가
                                accountList.add(accountProfileRes);
                            }else{
                                //통합계좌 승인 리스트 추가
                                accountList.add(accountProfileRes);
                            }
                        }
                    }

                    if(accountList.size() > 0){
                        AppCompanyProfileRes res = new AppCompanyProfileRes();
                        res.setCompanyCodeId(pubCompanyCode);
                        res.setCompanyNameEng(pubCompanyName);
                        res.setAccountList(accountList);
                        appCompanyProfileRes.add(res);
                    }
                    Map<String, Integer> result = new HashMap<>();
                    //App ID
                    result.put("appId", new Integer(srvId));
                    //연결 계좌수
                    result.put("accountCnt", accountCnt);
                    //미등록 계좌수
                    result.put("noLinkAccountCnt", noLinkAccountCnt);
                    body.put("data", result);
                    body.put("resultList", appCompanyProfileRes);
                }
            }else{
                //AccessToken Error
                throw new CommonException(ErrorCodeConstants.NOT_FOUND_INT_TOKEN, null);
            }
        }

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

    /**
     * getIntAccountAppInfo
     */
    @Override
    public CommonResponse getIntAccountAppInfo() {
        Map<String, Object> body = new HashMap<>();
        OauthAppReq oauthAppReq = new OauthAppReq();
        oauthAppReq.setClientId(accountApiKey);
        OauthAppRes oauthAppRes = oauthMapper.selectSvcAppInfo(oauthAppReq);
        oauthAppRes.setScope(intAccountScope);
        oauthAppRes.setAppImg(sptDomain + "/cmm/appImg/" + oauthAppRes.getAppId() + ".do");
        body.put("data", oauthAppRes);

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

    /**
     * getBalanceList
     *
     * @param memberVerifyRes
     */
    @Override
    public CommonResponse getBalanceList(IntAccountReq intAccountReq, MemberVerifyRes memberVerifyRes) throws Exception {

        Map<String, Object> body = new HashMap<>();
        String customerCi = memberVerifyRes.getCustomerVerify();
        String customerNameKor = memberVerifyRes.getCustomerNameKor();
        log.debug("[사용자]정보 취득後:customerCi : {} ", customerCi);
        log.debug("[사용자]정보 취득後:customerNameKor : {} ", customerNameKor);
        intAccountReq.setAppId(srvId);
        intAccountReq.setCustomerRegNo(memberVerifyRes.getCustomerRegNo());
        //서비스포털 연결 계좌 리스트정보
        IntAccountListRes devIntAccountListRes = getCommonMemberConsentList(intAccountReq);
        //핀테크 서비스 연계확인 계좌정보 리스트 조회 (상용 모드)
        IntAccountListRes intAccountListRes = "prd".equals(devMode) ? getCommonMemberConsentListApi(intAccountReq, comId, srvId, customerCi, customerNameKor)
                : devIntAccountListRes.clone();
        //통합계좌 APP access_token 정보 조회
        IntAccountTokenRes intAccountTokenRes = intAccountMapper.selectIntAccountOauthToken(memberVerifyRes.getCustomerRegNo());
        IntAccountBalanceRes intAccountBalanceRes = getBalance(intAccountTokenRes, intAccountListRes, devIntAccountListRes, customerCi);
        //body response
        body.put("data", intAccountBalanceRes);

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

    /**
     * getTransactionList
     *
     * @param intAccountReq
     * @param memberVerifyRes
     */
    @Override
    public CommonResponse getTransactionList(IntAccountReq intAccountReq, MemberVerifyRes memberVerifyRes) throws Exception {

        Map<String, Object> body = new HashMap<>();
        String customerCi = memberVerifyRes.getCustomerVerify();
        String customerNameKor = memberVerifyRes.getCustomerNameKor();
        log.debug("[사용자]정보 취득後:customerCi : {} ", customerCi);
        log.debug("[사용자]정보 취득後:customerNameKor : {} ", customerNameKor);
        intAccountReq.setAppId(srvId);
        intAccountReq.setCustomerRegNo(memberVerifyRes.getCustomerRegNo());
        //서비스포털 연결 계좌 리스트정보
        IntAccountListRes devIntAccountListRes = getCommonMemberConsentList(intAccountReq);
        IntAccountListRes intAccountListRes = "prd".equals(devMode) ? getCommonMemberConsentListApi(intAccountReq, comId, srvId, customerCi, customerNameKor)
                : devIntAccountListRes.clone();
        //통합계좌 APP access_token 정보 조회
        IntAccountTokenRes intAccountTokenRes = intAccountMapper.selectIntAccountOauthToken(memberVerifyRes.getCustomerRegNo());
        IntAccountResultRes intAccountResultRes = getTransaction(intAccountTokenRes, intAccountListRes, devIntAccountListRes, intAccountReq, customerCi);
        //body response
        body.put("data", intAccountResultRes);

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

    /**
     * getPortfolioList
     *
     * @param intAccountReq
     * @param memberVerifyRes
     */
    @Override
    public CommonResponse getPortfolioList(IntAccountReq intAccountReq, MemberVerifyRes memberVerifyRes) throws Exception {

        Map<String, Object> body = new HashMap<>();
        String customerCi = memberVerifyRes.getCustomerVerify();
        String customerNameKor = memberVerifyRes.getCustomerNameKor();
        log.debug("[사용자]정보 취득後:customerCi : {} ", customerCi);
        log.debug("[사용자]정보 취득後:customerNameKor : {} ", customerNameKor);
        intAccountReq.setAppId(srvId);
        intAccountReq.setCustomerRegNo(memberVerifyRes.getCustomerRegNo());
        //서비스포털 연결 계좌 리스트정보
        IntAccountListRes devIntAccountListRes = getCommonMemberConsentList(intAccountReq);
        IntAccountListRes intAccountListRes = "prd".equals(devMode) ? getCommonMemberConsentListApi(intAccountReq, comId, srvId, customerCi, customerNameKor)
                : devIntAccountListRes.clone();
        //통합계좌 APP access_token 정보 조회
        IntAccountTokenRes intAccountTokenRes = intAccountMapper.selectIntAccountOauthToken(memberVerifyRes.getCustomerRegNo());
        IntAccountResultRes intAccountResultRes = getPortfolio(intAccountTokenRes, intAccountListRes, devIntAccountListRes, intAccountReq, customerCi);
        //body response
        body.put("data", intAccountResultRes);

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

    /**
     * getInterestList
     *
     * @param intAccountReq
     * @param memberVerifyRes
     */
    @Override
    public CommonResponse getInterestList(IntAccountReq intAccountReq, MemberVerifyRes memberVerifyRes) throws Exception {

        Map<String, Object> body = new HashMap<>();
        String customerCi = memberVerifyRes.getCustomerVerify();
        String customerNameKor = memberVerifyRes.getCustomerNameKor();
        log.debug("[사용자]정보 취득後:customerCi : {} ", customerCi);
        log.debug("[사용자]정보 취득後:customerNameKor : {} ", customerNameKor);
        intAccountReq.setAppId(srvId);
        intAccountReq.setCustomerRegNo(memberVerifyRes.getCustomerRegNo());
        //서비스포털 연결 계좌 리스트정보
        IntAccountListRes devIntAccountListRes = getCommonMemberConsentList(intAccountReq);
        IntAccountListRes intAccountListRes = "prd".equals(devMode) ? getCommonMemberConsentListApi(intAccountReq, comId, srvId, customerCi, customerNameKor)
                : devIntAccountListRes.clone();
        //통합계좌 APP access_token 정보 조회
        IntAccountTokenRes intAccountTokenRes = intAccountMapper.selectIntAccountOauthToken(memberVerifyRes.getCustomerRegNo());
        IntAccountResultRes intAccountResultRes = getInterest(intAccountTokenRes, intAccountListRes, devIntAccountListRes, intAccountReq, customerCi);
        //body response
        body.put("data", intAccountResultRes);

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

    private HttpStatus getAccessTokenCheck(String accessToken){
        //account API 헤더정보
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add("apiKey", accountApiKey);
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        String accountApiUrl = northboundUrl + "/v1/daishin/account/balance/search";
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil();
        ResponseEntity<String> responseEntity = restTemplateUtil.sendRestTemplate(accountApiUrl, httpHeaders, HttpMethod.POST, "");

        return responseEntity.getStatusCode();
    }

    /**
    통합계좌 잔고조회
    * @param intAccountTokenRes
    * @param intAccountListRes
    * @return
    */
    private IntAccountBalanceRes getBalance(IntAccountTokenRes intAccountTokenRes, IntAccountListRes intAccountListRes, IntAccountListRes devIntAccountListRes, String customerCi) throws Exception {

        IntAccountBalanceRes intAccountBalanceRes = new IntAccountBalanceRes();
        HttpHeaders httpHeaders = getCommonHeaders(intAccountTokenRes.getAccessToken());

        List<Object> resultList= new ArrayList<>();
        List<CommonFailRes> failList = new ArrayList<>();
        int totalCount = 0;
        int successCount = 0;
        int failCount = 0;

        //ExecutorService 생성
        ExecutorService executorService = Executors.newFixedThreadPool(intAccountListRes.getVtAccList().size() == 0 ? 1 : intAccountListRes.getVtAccList().size());
        //결과를 저장할 리스트
        List<Future<IntAccountWorkerUtil>> futures = new ArrayList<Future<IntAccountWorkerUtil>>();
        //회원 가상계좌 리스트
        for(IntAccountRes vtAccountVO : intAccountListRes.getVtAccList()){
            //가상계좌번호
            String vtAccNo = vtAccountVO.getVtAccNo();
            totalCount++;
            try{
                //All 증권사
                if(allAssetCompanys.indexOf(vtAccountVO.getComId()) > -1){
                    String payload = "{ \"partner\" : { \"comId\": \""+comId+"\",  \"srvId\": \""+srvId+"\"}," +
                            " \"commonHeader\" : { \"reqIdConsumer\": \"reqid-0001\",  \"ci\": \"" + customerCi + "\"}," +
                            " \"devInfo\" : { \"ipAddr\": \""+CommonUtil.getRequestIp(vtAccountVO.getEndpoint(), serverNslookupIp)+"\",  \"macAddr\": \"1866DA0D99D6\"}," +
                            " \"accInfo\" : { \"vtAccNo\": \"" + vtAccNo + "\"}," +
                            " \"balanceRequestBody\" : { \"queryType\" : { \"assetType\": \"ALL\"}}}";
                    //상용모드:개발모드
                    String accountApiUrl = "prd".equals(devMode) ? vtAccountVO.getEndpoint().replaceAll("apigw.koscom.co.kr","10.0.1.101") + "balance/search"
                            :  vtAccountVO.getEndpoint() + "balance/search";

                    IntAccountWorkerUtil workerUtil = new IntAccountWorkerUtil(accountApiUrl, httpHeaders, payload, new RestTemplateUtil(),
                            vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias());
                    futures.add(executorService.submit(workerUtil));
                }else{
                    String[] assetTypes = {"CASH","EQTY","FUND","ETC"};
                    for(String asset : assetTypes){
                        String payload = "{ \"partner\" : { \"comId\": \""+comId+"\",  \"srvId\": \""+srvId+"\"}," +
                                " \"commonHeader\" : { \"reqIdConsumer\": \"reqid-0001\",  \"ci\": \"" + customerCi + "\"}," +
                                " \"devInfo\" : { \"ipAddr\": \""+CommonUtil.getRequestIp(vtAccountVO.getEndpoint(), serverNslookupIp)+"\",  \"macAddr\": \"1866DA0D99D6\"}," +
                                " \"accInfo\" : { \"vtAccNo\": \"" + vtAccNo + "\"}," +
                                " \"balanceRequestBody\" : { \"queryType\" : { \"assetType\": \""+asset+"\", \"count\":100}}}";
                        //상용모드:개발모드
                        String accountApiUrl = "prd".equals(devMode) ? vtAccountVO.getEndpoint().replaceAll("apigw.koscom.co.kr","10.0.1.101") + "balance/search"
                                :  vtAccountVO.getEndpoint() + "balance/search";

                        IntAccountWorkerUtil workerUtil = new IntAccountWorkerUtil(accountApiUrl, httpHeaders, payload, new RestTemplateUtil(),
                                vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias());
                        futures.add(executorService.submit(workerUtil));
                    }
                }
            }catch (Exception e){
                log.info("++++++++++++++++++++++++++++++++++++ Balance AccountAPi Error : {} ", e.getMessage() );
                CommonFailRes commonFailRes = new CommonFailRes(vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), e.getMessage());
                commonFailRes.setComName(CommonFunctionUtil.companyName(vtAccountVO.getComId()));
                failList.add(commonFailRes);
                failCount++;
            }
        }

        //스레드 종료처리
        executorService.shutdown();
        // 결과값 병합
        balanceService.setBalanceResultData(futures, resultList, failList);

        successCount = resultList.size();
        failCount = failList.size();

        if("prd".equals(devMode)){
            //요청한 계좌정보가 서비스 연결 계좌 리스트에 존재하지 않는경우 실패 처리 (API 계약 해지, 증권사 계약해지)
            setLinkAccountList(devIntAccountListRes.getVtAccList(), intAccountListRes.getVtAccList());
            int commonFailCount = setCommonAccountFail(devIntAccountListRes.getVtAccList(), failList);
            totalCount += commonFailCount;
            failCount += commonFailCount;
        }

        //전체수행, 성공, 실패 count
        intAccountBalanceRes.setTotalCount(totalCount);
        intAccountBalanceRes.setSuccessCount(successCount);
        intAccountBalanceRes.setFailCount(failCount);
        //success List
        intAccountBalanceRes.setResultList(resultList);
        //fail List
        intAccountBalanceRes.setFailList(failList);

        if(successCount > 0){
            //리스트 통합
            IntAccountBalanceRes.BalanceResult integratedBalance = new IntAccountBalanceRes.BalanceResult();
            BalanceSummaryRes summary = new BalanceSummaryRes();
            for(Object o : intAccountBalanceRes.getResultList()){
                BalanceResultRes balanceResultRes = (BalanceResultRes)o;
                BalanceRes balance = balanceResultRes.getBalanceList().getBalance();
                if(null != balance.getSummary()){
                    balanceService.setBalanceSummary(summary, balance.getSummary());
                }
            }
            //기본정보
            integratedBalance.setSummary(summary);
            intAccountBalanceRes.setTotalResult(integratedBalance);
        }

        return intAccountBalanceRes;
    }

    /**
     통합계좌 거래내역
     * @param intAccountTokenRes
     * @param intAccountListRes
     * @return
     */
    private IntAccountResultRes getTransaction(IntAccountTokenRes intAccountTokenRes, IntAccountListRes intAccountListRes, IntAccountListRes devIntAccountListRes, IntAccountReq intAccountReq, String customerCi) throws Exception {

        IntAccountResultRes intAccountResultRes = new IntAccountResultRes();
        HttpHeaders httpHeaders = getCommonHeaders(intAccountTokenRes.getAccessToken());

        List<Object> resultList= new ArrayList<>();
        List<CommonFailRes> failList = new ArrayList<>();
        int totalCount = 0;
        int successCount = 0;
        int failCount= 0;

        //조회시작일자 종료일자
        String fromDate = intAccountReq.getSearchDateFrom().replaceAll("-","");
        String toDate = intAccountReq.getSearchDateTo().replaceAll("-","");

        //ExecutorService 생성
        ExecutorService executorService = Executors.newFixedThreadPool(intAccountListRes.getVtAccList().size() == 0 ? 1 : intAccountListRes.getVtAccList().size());
        //결과를 저장할 리스트
        List<Future<IntAccountWorkerUtil>> futures = new ArrayList<Future<IntAccountWorkerUtil>>();
        //회원 가상계좌 리스트
        for(IntAccountRes vtAccountVO : intAccountListRes.getVtAccList()){
            //가상계좌번호
            String vtAccNo = vtAccountVO.getVtAccNo();
            totalCount++;
            try{
                String payload = "{ \"partner\" : { \"comId\": \""+comId+"\",  \"srvId\": \""+srvId+"\"}," +
                        " \"commonHeader\" : { \"reqIdConsumer\": \"reqid-0001\",  \"ci\": \"" + customerCi + "\"}," +
                        " \"devInfo\" : { \"ipAddr\": \""+CommonUtil.getRequestIp(vtAccountVO.getEndpoint(), serverNslookupIp)+"\",  \"macAddr\": \"1866DA0D99D6\"}," +
                        " \"accInfo\" : { \"vtAccNo\": \"" + vtAccNo + "\"}," +
                        " \"transactionHistoryRequestBody\" : { \"queryParams\" : { \"fromDate\": \""+fromDate+"\", \"toDate\": \""+toDate+"\", \"isinCode\": \"\", \"side\": \"ALL\", \"count\":100}}}";
                //상용모드:개발모드
                String accountApiUrl = "prd".equals(devMode) ? vtAccountVO.getEndpoint().replaceAll("apigw.koscom.co.kr","10.0.1.101") + "transaction/search"
                        :  vtAccountVO.getEndpoint() + "transaction/search";

                IntAccountWorkerUtil workerUtil = new IntAccountWorkerUtil(accountApiUrl, httpHeaders, payload, new RestTemplateUtil(),
                        vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias());
                futures.add(executorService.submit(workerUtil));

            }catch (Exception e){
                log.info("++++++++++++++++++++++++++++++++++++ Transaction AccountAPi Error : {} ", e.getMessage() );
                CommonFailRes commonFailRes = new CommonFailRes(vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), e.getMessage());
                commonFailRes.setComName(CommonFunctionUtil.companyName(vtAccountVO.getComId()));
                failList.add(commonFailRes);
                failCount++;
            }
        }

        //스레드 종료처리
        executorService.shutdown();
        // 결과값 병합
        transactionService.setTransactionResultData(futures, resultList, failList);
        successCount = resultList.size();
        failCount = failList.size();

        if("prd".equals(devMode)){
            //요청한 계좌정보가 서비스 연결 계좌 리스트에 존재하지 않는경우 실패 처리 (API 계약 해지, 증권사 계약해지)
            setLinkAccountList(devIntAccountListRes.getVtAccList(), intAccountListRes.getVtAccList());
            int commonFailCount = setCommonAccountFail(devIntAccountListRes.getVtAccList(), failList);
            totalCount += commonFailCount;
            failCount += commonFailCount;
        }

        //전체수행, 성공, 실패 count
        intAccountResultRes.setTotalCount(totalCount);
        intAccountResultRes.setSuccessCount(successCount);
        intAccountResultRes.setFailCount(failCount);
        //success List
        intAccountResultRes.setResultList(resultList);
        //fail List
        intAccountResultRes.setFailList(failList);

        return intAccountResultRes;
    }

    /**
     통합계좌 포트폴리오
     * @param intAccountTokenRes
     * @param intAccountListRes
     * @return
     */
    private IntAccountResultRes getPortfolio(IntAccountTokenRes intAccountTokenRes, IntAccountListRes intAccountListRes, IntAccountListRes devIntAccountListRes, IntAccountReq intAccountReq, String customerCi) throws Exception {

        IntAccountResultRes intAccountResultRes = new IntAccountResultRes();
        HttpHeaders httpHeaders = getCommonHeaders(intAccountTokenRes.getAccessToken());

        List<Object> resultList= new ArrayList<>();
        List<CommonFailRes> failList = new ArrayList<>();
        int totalCount = 0;
        int successCount = 0;
        int failCount= 0;

        //ExecutorService 생성
        ExecutorService executorService = Executors.newFixedThreadPool(intAccountListRes.getVtAccList().size() == 0 ? 1 : intAccountListRes.getVtAccList().size());
        //결과를 저장할 리스트
        List<Future<IntAccountWorkerUtil>> futures = new ArrayList<Future<IntAccountWorkerUtil>>();
        //회원 가상계좌 리스트
        for(IntAccountRes vtAccountVO : intAccountListRes.getVtAccList()){
            //가상계좌번호
            String vtAccNo = vtAccountVO.getVtAccNo();
            totalCount++;
            try{
                //All 증권사
                if(allAssetCompanys.indexOf(vtAccountVO.getComId()) > -1){
                    String payload = "{ \"partner\" : { \"comId\": \""+comId+"\",  \"srvId\": \""+srvId+"\"}," +
                            " \"commonHeader\" : { \"reqIdConsumer\": \"reqid-0001\",  \"ci\": \"" + customerCi + "\"}," +
                            " \"devInfo\" : { \"ipAddr\": \""+CommonUtil.getRequestIp(vtAccountVO.getEndpoint(), serverNslookupIp)+"\",  \"macAddr\": \"1866DA0D99D6\"}," +
                            " \"accInfo\" : { \"vtAccNo\": \"" + vtAccNo + "\"}," +
                            " \"portfolioRequestBody\" : { \"queryType\" : { \"assetType\": \"ALL\", \"rspType\": \"QTY\"}}}";
                    //상용모드:개발모드
                    String accountApiUrl = "prd".equals(devMode) ? vtAccountVO.getEndpoint().replaceAll("apigw.koscom.co.kr","10.0.1.101") + "portfolio/search"
                            :  vtAccountVO.getEndpoint() + "portfolio/search";

                    IntAccountWorkerUtil workerUtil = new IntAccountWorkerUtil(accountApiUrl, httpHeaders, payload, new RestTemplateUtil(),
                            vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias());
                    futures.add(executorService.submit(workerUtil));
                }else{
                    String[] assetTypes = {"EQTY","FUND","ETC"};
                    for(String asset : assetTypes){
                        String payload = "{ \"partner\" : { \"comId\": \""+comId+"\",  \"srvId\": \""+srvId+"\"}," +
                                " \"commonHeader\" : { \"reqIdConsumer\": \"reqid-0001\",  \"ci\": \"" + customerCi + "\"}," +
                                " \"devInfo\" : { \"ipAddr\": \""+CommonUtil.getRequestIp(vtAccountVO.getEndpoint(), serverNslookupIp)+"\",  \"macAddr\": \"1866DA0D99D6\"}," +
                                " \"accInfo\" : { \"vtAccNo\": \"" + vtAccNo + "\"}," +
                                " \"portfolioRequestBody\" : { \"queryType\" : { \"assetType\": \""+asset+"\", \"rspType\": \"QTY\", \"count\":100}}}";
                        //상용모드:개발모드
                        String accountApiUrl = "prd".equals(devMode) ? vtAccountVO.getEndpoint().replaceAll("apigw.koscom.co.kr","10.0.1.101") + "portfolio/search"
                                :  vtAccountVO.getEndpoint() + "portfolio/search";

                        IntAccountWorkerUtil workerUtil = new IntAccountWorkerUtil(accountApiUrl, httpHeaders, payload, new RestTemplateUtil(),
                                vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias());
                        futures.add(executorService.submit(workerUtil));
                    }
                }
            }catch (Exception e){
                log.info("++++++++++++++++++++++++++++++++++++ Portfolio AccountAPi Error : {} ", e.getMessage() );
                CommonFailRes commonFailRes = new CommonFailRes(vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), e.getMessage());
                commonFailRes.setComName(CommonFunctionUtil.companyName(vtAccountVO.getComId()));
                failList.add(commonFailRes);
                failCount++;
            }
        }

        //스레드 종료처리
        executorService.shutdown();
        // 결과값 병합
        portfolioService.setPortfolioResultData(futures, resultList, failList);
        successCount = resultList.size();
        failCount = failList.size();

        if("prd".equals(devMode)){
            //요청한 계좌정보가 서비스 연결 계좌 리스트에 존재하지 않는경우 실패 처리 (API 계약 해지, 증권사 계약해지)
            setLinkAccountList(devIntAccountListRes.getVtAccList(), intAccountListRes.getVtAccList());
            int commonFailCount = setCommonAccountFail(devIntAccountListRes.getVtAccList(), failList);
            totalCount += commonFailCount;
            failCount += commonFailCount;
        }

        //전체수행, 성공, 실패 count
        intAccountResultRes.setTotalCount(totalCount);
        intAccountResultRes.setSuccessCount(successCount);
        intAccountResultRes.setFailCount(failCount);
        //success List
        intAccountResultRes.setResultList(resultList);
        //fail List
        intAccountResultRes.setFailList(failList);

        return intAccountResultRes;
    }

    /**
     통합계좌 관심종목
     * @param intAccountTokenRes
     * @param intAccountListRes
     * @return
     */
    private IntAccountResultRes getInterest(IntAccountTokenRes intAccountTokenRes, IntAccountListRes intAccountListRes, IntAccountListRes devIntAccountListRes, IntAccountReq intAccountReq, String customerCi) throws Exception {

        IntAccountResultRes intAccountResultRes = new IntAccountResultRes();
        HttpHeaders httpHeaders = getCommonHeaders(intAccountTokenRes.getAccessToken());

        List<Object> resultList= new ArrayList<>();
        List<CommonFailRes> failList = new ArrayList<>();
        int totalCount = 0;
        int successCount = 0;
        int failCount= 0;

        //ExecutorService 생성
        ExecutorService executorService = Executors.newFixedThreadPool(intAccountListRes.getVtAccList().size() == 0 ? 1 : intAccountListRes.getVtAccList().size());
        //결과를 저장할 리스트
        List<Future<IntAccountWorkerUtil>> futures = new ArrayList<Future<IntAccountWorkerUtil>>();
        //회원 가상계좌 리스트
        for(IntAccountRes vtAccountVO : intAccountListRes.getVtAccList()){
            //가상계좌번호
            String vtAccNo = vtAccountVO.getVtAccNo();
            totalCount++;
            try{
                String payload = "{ \"partner\" : { \"comId\": \""+comId+"\",  \"srvId\": \""+srvId+"\"}," +
                        " \"commonHeader\" : { \"reqIdConsumer\": \"reqid-0001\",  \"ci\": \"" + customerCi + "\"}," +
                        " \"devInfo\" : { \"ipAddr\": \""+CommonUtil.getRequestIp(vtAccountVO.getEndpoint(), serverNslookupIp)+"\",  \"macAddr\": \"1866DA0D99D6\"}," +
                        " \"accInfo\" : { \"vtAccNo\": \"" + vtAccNo + "\"}}";
                //상용모드:개발모드
                String accountApiUrl = "prd".equals(devMode) ? vtAccountVO.getEndpoint().replaceAll("apigw.koscom.co.kr","10.0.1.101") + "interest/search"
                        :  vtAccountVO.getEndpoint() + "interest/search";

                IntAccountWorkerUtil workerUtil = new IntAccountWorkerUtil(accountApiUrl, httpHeaders, payload, new RestTemplateUtil(),
                        vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias());
                futures.add(executorService.submit(workerUtil));

            }catch (Exception e){
                log.info("++++++++++++++++++++++++++++++++++++ Interest AccountAPi Error : {} ", e.getMessage() );
                CommonFailRes commonFailRes = new CommonFailRes(vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), e.getMessage());
                commonFailRes.setComName(CommonFunctionUtil.companyName(vtAccountVO.getComId()));
                failList.add(commonFailRes);
                failCount++;
            }
        }

        //스레드 종료처리
        executorService.shutdown();
        // 결과값 병합
        interestService.setInterestResultData(futures, resultList, failList);
        successCount = resultList.size();
        failCount = failList.size();

        if("prd".equals(devMode)){
            //요청한 계좌정보가 서비스 연결 계좌 리스트에 존재하지 않는경우 실패 처리 (API 계약 해지, 증권사 계약해지)
            setLinkAccountList(devIntAccountListRes.getVtAccList(), intAccountListRes.getVtAccList());
            int commonFailCount = setCommonAccountFail(devIntAccountListRes.getVtAccList(), failList);
            totalCount += commonFailCount;
            failCount += commonFailCount;
        }

        //전체수행, 성공, 실패 count
        intAccountResultRes.setTotalCount(totalCount);
        intAccountResultRes.setSuccessCount(successCount);
        intAccountResultRes.setFailCount(failCount);
        //success List
        intAccountResultRes.setResultList(resultList);
        //fail List
        intAccountResultRes.setFailList(failList);

        return intAccountResultRes;
    }

    private HttpHeaders getCommonHeaders(String token){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add("apiKey", accountApiKey);
        httpHeaders.add("Authorization", "Bearer " + token);
        return httpHeaders;
    }

    /**
     핀테크 서비스 연계확인 계좌정보 리스트 조회(개발용)
     * @param intAccountReq
     * @return
     */
    private IntAccountListRes getCommonMemberConsentList(IntAccountReq intAccountReq){
        IntAccountListRes intAccountListRes = new IntAccountListRes();
        List<IntAccountRes> intAccountRes = intAccountMapper.selectFinTechIntAccountList(intAccountReq);
        for(IntAccountRes accountRes : intAccountRes){
            String endPoint = northboundUrl + "/v1/" + accountRes.getCompanyEngName() + "/account/";
            accountRes.setEndpoint(endPoint);
        }
        intAccountListRes.setVtAccList(intAccountRes);
        return intAccountListRes;
    }

    private IntAccountListRes getCommonMemberConsentListApi(IntAccountReq intAccountReq, String comId, String srvId, String customerCi, String customerNameKor){
        //account API 헤더정보
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add("apiKey", accountApiKey);
        //핀테크 서비스 연계확인 API
        String consentApiUr = northboundUrl + "/v1/common/member/consent/search";
        String payload = "{ \"partner\" : { \"comId\": \""+comId+"\",  \"srvId\": \""+srvId+"\"}," +
                " \"commonHeader\" : { \"reqIdConsumer\": \"reqid-0001\",  \"ci\": \"" + customerCi + "\"}," +
                " \"body\" : { \"korName\": \""+customerNameKor+"\"}}";
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil();
        ResponseEntity<String> responseEntity = restTemplateUtil.sendRestTemplate(consentApiUr, httpHeaders, HttpMethod.POST, payload);
        JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
        IntAccountListRes intAccountListRes = new Gson().fromJson(jsonObject.toString(),IntAccountListRes.class);

        //검색
        if(!"0".equals(intAccountReq.getVtAccountNo()) || !"0".equals(intAccountReq.getCompanyCodeId())){
            List<IntAccountRes> tempVtAccList = intAccountListRes.getVtAccList();
            List<IntAccountRes> vtAccList = new ArrayList<>();
            for(IntAccountRes vtAccountVO :  tempVtAccList){
                //특정 증권사 계좌 리스트 조회
                if(!"0".equals(intAccountReq.getCompanyCodeId()) && "0".equals(intAccountReq.getVtAccountNo())){
                    if(intAccountReq.getCompanyCodeId().equals(vtAccountVO.getComId())){
                        vtAccList.add(vtAccountVO);
                    }
                }else{
                    // 선택 계좌만 조회
                    if(intAccountReq.getVtAccountNo().equals(vtAccountVO.getVtAccNo())){
                        vtAccList.add(vtAccountVO);
                        break;
                    }
                }
            }
            //선택된 증권사 계좌 리스트 적용
            intAccountListRes.setVtAccList(vtAccList);
        }

        return intAccountListRes;
    }

    private int setCommonAccountFail(List<IntAccountRes> accountList, List<CommonFailRes> failList) throws Exception {
        int failCount = 0;
        if(accountList != null && accountList.size() > 0){
            for(IntAccountRes vtAccNo : accountList){
                JSONObject failJson = new JSONObject();
                failJson.put("category", ERROR_CATEGORY);
                failJson.put("message", ERROR_NOT_FOUND_ACCOUNT);
                CommonFailRes failViewVO = new CommonFailRes(vtAccNo.getComId(), vtAccNo.getVtAccNo(), vtAccNo.getVtAccAlias(), failJson.toString());
                failViewVO.setComName(CommonFunctionUtil.companyName(vtAccNo.getComId()));
                failList.add(failViewVO);
                failCount++;
            }
        }
        return failCount;
    }

    //서비스 연결 계좌 리스트 비교 (API 계약 해지, 증권사 계약해지)
    private void setLinkAccountList(List<IntAccountRes> internalAccountList, List<IntAccountRes> apiAccountList){
        if(null != apiAccountList && apiAccountList.size() > 0){
            List<IntAccountRes> tempList = new ArrayList<>();
            // 서비스 포털에서 조회한 통합계좌 리스트
            for(IntAccountRes accountVO: internalAccountList){
                // 핀테크 연계서버 API 조회 통합계좌 리스트
                for(IntAccountRes vo : apiAccountList){
                    if(accountVO.getVtAccNo().equals(vo.getVtAccNo())){
                        tempList.add(accountVO);
                        break;
                    }
                }
            }
            internalAccountList.removeAll(tempList);
        }
    }
}
