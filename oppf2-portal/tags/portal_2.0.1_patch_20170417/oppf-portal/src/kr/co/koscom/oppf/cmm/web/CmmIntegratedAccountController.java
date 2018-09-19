package kr.co.koscom.oppf.cmm.web;

import com.google.gson.Gson;
import kr.co.koscom.oppf.cmm.IntegratedAccount.service.*;
import kr.co.koscom.oppf.cmm.exception.ApiExceptionMessage;
import kr.co.koscom.oppf.cmm.service.CmmIntegratedAccountService;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.CodeConstants;
import kr.co.koscom.oppf.cmm.util.CommonUtil;
import kr.co.koscom.oppf.spt.myp.intAcnt.service.impl.IntAcntWorker;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * The interface Cmm sif internal service.
 *
 * @author : 이희태
 * @version : 1.0
 * @Project : 자본시장 공동 핀테크 오픈플랫폼 구축 2차
 * @FileName : CmmIntegratedAccountController.java
 * @Comment : 통합계좌 조회 API Controller
 * @see
 * @since : 2017.02.13
 */
@RestController
public class CmmIntegratedAccountController {

    @Resource(name = "CmmSIFInternalService")
    private CmmSIFInternalService cmmSIFInternalService;
    @Resource(name = "CmmIntegratedAccountService")
    private CmmIntegratedAccountService cmmIntegratedAccountService;

    //Account Header
    private static String ACCESS_TOKEN = "2d77458f-a984-45d9-a3d0-877d2da3a9bc";
    private static final Logger log = Logger.getLogger(CmmIntegratedAccountController.class);

    /**
     * @Method Name        : getAuthorizationCallback
     * @Method description : 임시 테스트 통합계좌 Oauth 인증 callback Url
     * @param              : model
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping(
            value="/integration/oauthCallback"
            ,method = RequestMethod.GET)
    public Object getAuthorizationCallback(@RequestParam(value="code") String code,
                                           @RequestParam(value="scope") String scope) throws Exception{

        log.debug("++++++++++++++++++++++++++++++++++++ Authorization Code : " + code );
        log.debug("++++++++++++++++++++++++++++++++++++ scope : " + scope );
        if(null ==  code || null == scope){
            ApiExceptionMessage exMessage = new ApiExceptionMessage(CodeConstants.OP_SPT, ApiExceptionMessage.EX_REQUIRED_CHECK, "필수 항목 누락", "");
            return new ResponseEntity<ApiExceptionMessage>(exMessage, HttpStatus.BAD_REQUEST);
        }
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
        ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(apiUrl, httpHeaders, HttpMethod.POST, payload);
        if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
            JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
            String access_token =  jsonObject.getString("access_token");
            ACCESS_TOKEN = access_token;
            log.debug("++++++++++++++++++++++++++++++++++++ ACCESS_TOKEN : " + access_token );
        }else{
            log.debug("++++++++++++++++++++++++++++++++++++ responseEntity.getBody() : " + responseEntity.getBody() );
        }
        return HttpStatus.OK;
    }

   /**
    * @Method Name        : selectIntegratedAccountBalance
    * @Method description : 통합계좌 잔고조회
    * @param              : model
    * @return             : jsonView
    * @throws             : Exception
    */
    @RequestMapping(
            value="/integration/account/balance"
           ,method = RequestMethod.POST)
    public Object selectIntegratedAccountBalance(@RequestBody IntegratedAccountSearchRequestVO searchRequestVO,
                                                 @RequestHeader(value = "authorization" , required = false) String accessToken) throws Exception{
        log.debug(searchRequestVO);
        if(null ==  searchRequestVO.getCustomerId() ||
                null ==  searchRequestVO.getComId() ||
                null == searchRequestVO.getSrvId()  ||
                null == searchRequestVO.getAssetType()){
            ApiExceptionMessage exMessage = new ApiExceptionMessage(CodeConstants.OP_SPT, ApiExceptionMessage.EX_REQUIRED_CHECK, "필수 항목 누락", "");
            return new ResponseEntity<ApiExceptionMessage>(exMessage, HttpStatus.BAD_REQUEST);
        }

        String customerId = searchRequestVO.getCustomerId();
        String customerCi = "";
        String customerNameKor = "";
        MbrRegVO mbrRegVO = cmmIntegratedAccountService.selectSptCustomerVerifyProfileInfo(customerId);
        if(mbrRegVO != null){
            customerCi = mbrRegVO.getCustomerVerify();
            customerNameKor = mbrRegVO.getCustomerNameKor();
        }else{
            ApiExceptionMessage exMessage = new ApiExceptionMessage(CodeConstants.OP_SPT, ApiExceptionMessage.EX_NOT_FOUND_USER, "not found use", "");
            return new ResponseEntity<ApiExceptionMessage>(exMessage, HttpStatus.UNAUTHORIZED);
        }
        log.debug("[사용자]정보 취득後:customerCi="+customerCi);
        log.debug("[사용자]정보 취득後:customerNameKor="+customerNameKor);

        //핀테크 서비스 연계확인 계좌정보 리스트 조회
        VtAccountListVO vtAccountListVO = cmmIntegratedAccountService.selectTestApiCommonMemberConsentList(searchRequestVO.getComId(), searchRequestVO.getSrvId(), customerCi, customerNameKor);
        //앱 연결된 가상계좌 정보 없음
        if(vtAccountListVO.getVtAccList().size() < 1){
            log.debug("[사용자] 계좌정보 없음 ============================");
            ApiExceptionMessage exMessage = new ApiExceptionMessage(CodeConstants.OP_SPT, ApiExceptionMessage.EX_REQUIRED_CHECK, "No Content", "");
            return new ResponseEntity<ApiExceptionMessage>(exMessage, HttpStatus.NO_CONTENT);
        }

        //account API 헤더정보
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add("apiKey", OppfProperties.getProperty("Globals.integrated.account.apiKey"));
        log.debug("[사용자] accessToken : ============================");
        log.debug(accessToken);
        if(null == accessToken || "".equals(accessToken)){
            //회원고유 ACCESS_TOKEN 값 변경 예정
            httpHeaders.add("Authorization", "Bearer " + ACCESS_TOKEN);
        }else{
            //이준형과장 요청 17.3.2  외부 REST TOOL 에서 accessToken 발급시 우회 사용
            httpHeaders.add("Authorization", accessToken);
        }

        String ipAddr = CommonUtil.getLocalServerIp();
        String macAddr = "1866DA0D99D6";
        log.debug("[ipAddr] : ============================ : " + ipAddr);

        //Response 값 세팅
        InteGratedAccountBalanceVO inteGratedAccountResponseVO = new InteGratedAccountBalanceVO();
        List<Object> resultList= new ArrayList<>();
        List<CommonFailVO> failList = new ArrayList<>();
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
                String tmpAssetType = searchRequestVO.getAssetType();
                //대신증권 IP 예외처리
                if(vtAccountVO.getEndpoint().indexOf("daishin") > 0){
                    tmpIpAddr = "192168010001";
                }
                //ebest증권 assetType 예외처리
                if(vtAccountVO.getEndpoint().indexOf("ebest") > 0){
                    tmpAssetType = "CASH";
                }
                String payload = "{ \"partner\" : { \"comId\": \""+searchRequestVO.getComId()+"\",  \"srvId\": \""+searchRequestVO.getSrvId()+"\"}," +
                                 " \"commonHeader\" : { \"reqIdConsumer\": \"reqid-0001\",  \"ci\": \"" + customerCi + "\"}," +
                                 " \"devInfo\" : { \"ipAddr\": \""+tmpIpAddr+"\",  \"macAddr\": \""+macAddr+"\"}," +
                                 " \"accInfo\" : { \"vtAccNo\": \"" + vtAccNo + "\"}," +
                                 " \"balanceRequestBody\" : { \"queryType\" : { \"assetType\": \""+tmpAssetType+"\"}}}";
                //{증권사}/account/balance/search
                //"endpoint": "https://apigw.koscom.co.kr/v1/cyber/account/"
                String accountApiUrl = vtAccountVO.getEndpoint().replaceAll("apigw.koscom.co.kr","10.0.1.101") + "balance/search";
                IntAcntWorker IntAcntWorker = new IntAcntWorker(accountApiUrl, httpHeaders, payload, cmmSIFInternalService,
                        vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), "", "");
                futures.add(executor.submit(IntAcntWorker));

            }catch (Exception e){
                e.printStackTrace();
                failList.add(new CommonFailVO(vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), e.getMessage()));
                failCount++;
            }
        }

        //스레드 종료처리
        executor.shutdown();
        // 결과값 병합
        for (Future<IntAcntWorker> future : futures) {
            IntAcntWorker worker = future.get();
            if(HttpStatus.OK.equals(worker.getResponseEntity().getStatusCode())){
                JSONObject jsonObject =  new JSONObject(worker.getResponseEntity().getBody());
                BalanceResponseVO balanceResponseVO = new Gson().fromJson(jsonObject.toString(),BalanceResponseVO.class);
                resultList.add(balanceResponseVO);
                successCount++;
            }else{
                failList.add(new CommonFailVO(worker.getComId(), worker.getVtAccNo(), worker.getVtAccAlias(), worker.getResponseEntity().getBody()));
                failCount++;
            }
        }

        if(successCount > 0){
            //리스트 통합
            InteGratedAccountBalanceVO.BalanceResultVO integratedBalance = new InteGratedAccountBalanceVO.BalanceResultVO();
            MsgBalanceSummaryVO summary = new MsgBalanceSummaryVO();
            for(Object o : resultList){
                BalanceResponseVO balanceResponseVO = (BalanceResponseVO)o;
                BalanceVO balance = balanceResponseVO.getBalanceList().getBalance();
                if(null != balance.getSummary()){
                    cmmIntegratedAccountService.setBalanceSummary(summary, balance.getSummary());
                }
            }
            //기본정보
            integratedBalance.setSummary(summary);
            inteGratedAccountResponseVO.setTotalResult(integratedBalance);
        }

        //전체수행, 성공, 실패 count
        inteGratedAccountResponseVO.setTotalCount(totalCount);
        inteGratedAccountResponseVO.setSuccessCount(successCount);
        inteGratedAccountResponseVO.setFailCount(failCount);
        //success List
        inteGratedAccountResponseVO.setResultList(resultList);
        //fail List
        inteGratedAccountResponseVO.setFailList(failList);

        return inteGratedAccountResponseVO;
    }

    /**
     * @Method Name        : selectIntegratedAccountBalance
     * @Method description : 통합계좌 거래내역
     * @param              : model
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping(
            value="/integration/account/transaction"
            ,method = RequestMethod.POST)
    public Object selectIntegratedAccountTransaction(@RequestBody IntegratedAccountSearchRequestVO searchRequestVO,
                                                     @RequestHeader(value = "authorization" , required = false) String accessToken) throws Exception{
        log.debug(searchRequestVO);
        if(null ==  searchRequestVO.getCustomerId() ||
                null ==  searchRequestVO.getComId() ||
                null == searchRequestVO.getSrvId()){
            ApiExceptionMessage exMessage = new ApiExceptionMessage(CodeConstants.OP_SPT, ApiExceptionMessage.EX_REQUIRED_CHECK, "필수 항목 누락", "");
            return new ResponseEntity<ApiExceptionMessage>(exMessage, HttpStatus.BAD_REQUEST);
        }

        String customerId = searchRequestVO.getCustomerId();
        String customerCi = "";
        String customerNameKor = "";
        MbrRegVO mbrRegVO = cmmIntegratedAccountService.selectSptCustomerVerifyProfileInfo(customerId);
        if(mbrRegVO != null){
            customerCi = mbrRegVO.getCustomerVerify();
            customerNameKor = mbrRegVO.getCustomerNameKor();
        }else{
            ApiExceptionMessage exMessage = new ApiExceptionMessage(CodeConstants.OP_SPT, ApiExceptionMessage.EX_NOT_FOUND_USER, "not found use", "");
            return new ResponseEntity<ApiExceptionMessage>(exMessage, HttpStatus.UNAUTHORIZED);
        }
        log.debug("[사용자]정보 취득後:customerCi="+customerCi);
        log.debug("[사용자]정보 취득後:customerNameKor="+customerNameKor);

        //핀테크 서비스 연계확인 계좌정보 리스트 조회
        VtAccountListVO vtAccountListVO = cmmIntegratedAccountService.selectTestApiCommonMemberConsentList(searchRequestVO.getComId(), searchRequestVO.getSrvId(), customerCi, customerNameKor);
        //앱 연결된 가상계좌 정보 없음
        if(vtAccountListVO.getVtAccList().size() < 1){
            ApiExceptionMessage exMessage = new ApiExceptionMessage(CodeConstants.OP_SPT, ApiExceptionMessage.EX_REQUIRED_CHECK, "No Content", "");
            return new ResponseEntity<ApiExceptionMessage>(exMessage, HttpStatus.NO_CONTENT);
        }

        //account API 헤더정보
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add("apiKey", OppfProperties.getProperty("Globals.integrated.account.apiKey"));
        log.debug("[사용자] accessToken : ============================");
        log.debug(accessToken);
        if(null == accessToken || "".equals(accessToken)){
            //회원고유 ACCESS_TOKEN 값 변경 예정
            httpHeaders.add("Authorization", "Bearer " + ACCESS_TOKEN);
        }else{
            //이준형과장 요청 17.3.2  외부 REST TOOL 에서 accessToken 발급시 우회 사용
            httpHeaders.add("Authorization", accessToken);
        }

        String ipAddr = CommonUtil.getLocalServerIp();
        String macAddr = "1866DA0D99D6";

        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.add(cal.DATE, -7);
        //조회시작일자 종료일자 체크 (검색조건 없을경우 디폴트 7일 설정)
        String fromDate = searchRequestVO.getFromDate() == null ? date.format(cal.getTime()) : searchRequestVO.getFromDate();
        String toDate = searchRequestVO.getToDate() == null ? date.format(new Date()) : searchRequestVO.getToDate();

        //Response 값 세팅
        InteGratedAccountVO inteGratedAccountResponseVO = new InteGratedAccountVO();
        List<Object> resultList= new ArrayList<>();
        List<CommonFailVO> failList = new ArrayList<>();
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

                String isinCode = searchRequestVO.getIsinCode();
                String side = searchRequestVO.getSide();
                StringBuffer transactionHistoryRequestBody = new StringBuffer("");
                transactionHistoryRequestBody.append(" \"transactionHistoryRequestBody\" : { \"queryParams\" : { \"fromDate\": \""+fromDate+"\" , \"toDate\": \""+toDate+"\"");
                //종목코드
                if(null != isinCode)
                    transactionHistoryRequestBody.append(", \"isinCode\": \""+isinCode+"\"");
                //조회조건으로 값은:BID(매도), ASK(매수)
                if(null != side)
                    transactionHistoryRequestBody.append(", \"side\": \""+side+"\"");
                transactionHistoryRequestBody.append("}}}");

                String payload = "{ \"partner\" : { \"comId\": \""+searchRequestVO.getComId()+"\",  \"srvId\": \""+searchRequestVO.getSrvId()+"\"}," +
                        " \"commonHeader\" : { \"reqIdConsumer\": \"reqid-0001\",  \"ci\": \"" + customerCi + "\"}," +
                        " \"devInfo\" : { \"ipAddr\": \""+tmpIpAddr+"\",  \"macAddr\": \""+macAddr+"\"}," +
                        " \"accInfo\" : { \"vtAccNo\": \"" + vtAccNo + "\"},";
                payload += transactionHistoryRequestBody.toString();
                //{증권사}/account/transaction/search
                //"endpoint": "https://apigw.koscom.co.kr/v1/cyber/account/"
                String accountApiUrl = vtAccountVO.getEndpoint().replaceAll("apigw.koscom.co.kr","10.0.1.101") + "transaction/search";

                IntAcntWorker IntAcntWorker = new IntAcntWorker(accountApiUrl, httpHeaders, payload, cmmSIFInternalService,
                        vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), "", "");
                futures.add(executor.submit(IntAcntWorker));

            }catch (Exception e){
                e.printStackTrace();
                failList.add(new CommonFailVO(vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), e.getMessage()));
                failCount++;
            }
        }

        //스레드 종료처리
        executor.shutdown();
        // 결과값 병합
        for (Future<IntAcntWorker> future : futures) {
            IntAcntWorker worker = future.get();
            if(HttpStatus.OK.equals(worker.getResponseEntity().getStatusCode())){
                JSONObject jsonObject =  new JSONObject(worker.getResponseEntity().getBody());
                TransactionResponseVO transactionResponseVO = new Gson().fromJson(jsonObject.toString(),TransactionResponseVO.class);
                resultList.add(transactionResponseVO);
                successCount++;
            }else{
                failList.add(new CommonFailVO(worker.getComId(), worker.getVtAccNo(), worker.getVtAccAlias(), worker.getResponseEntity().getBody()));
                failCount++;
            }
        }

        //전체수행, 성공, 실패 count
        inteGratedAccountResponseVO.setTotalCount(totalCount);
        inteGratedAccountResponseVO.setSuccessCount(successCount);
        inteGratedAccountResponseVO.setFailCount(failCount);
        //success List
        inteGratedAccountResponseVO.setResultList(resultList);
        //fail List
        inteGratedAccountResponseVO.setFailList(failList);

        return inteGratedAccountResponseVO;
    }

    /**
     * @Method Name        : selectIntegratedAccountInterest
     * @Method description : 통합계좌 관심종목
     * @param              : model
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping(
            value="/integration/account/interest"
            ,method = RequestMethod.POST)
    public Object selectIntegratedAccountInterest(@RequestBody IntegratedAccountSearchRequestVO searchRequestVO,
                                                  @RequestHeader(value = "authorization" , required = false) String accessToken) throws Exception{
        log.debug(searchRequestVO);
        if(null ==  searchRequestVO.getCustomerId() ||
                null ==  searchRequestVO.getComId() ||
                null == searchRequestVO.getSrvId()){
            ApiExceptionMessage exMessage = new ApiExceptionMessage(CodeConstants.OP_SPT, ApiExceptionMessage.EX_REQUIRED_CHECK, "필수 항목 누락", "");
            return new ResponseEntity<ApiExceptionMessage>(exMessage, HttpStatus.BAD_REQUEST);
        }

        String customerId = searchRequestVO.getCustomerId();
        String customerCi = "";
        String customerNameKor = "";
        MbrRegVO mbrRegVO = cmmIntegratedAccountService.selectSptCustomerVerifyProfileInfo(customerId);
        if(mbrRegVO != null){
            customerCi = mbrRegVO.getCustomerVerify();
            customerNameKor = mbrRegVO.getCustomerNameKor();
        }else{
            ApiExceptionMessage exMessage = new ApiExceptionMessage(CodeConstants.OP_SPT, ApiExceptionMessage.EX_NOT_FOUND_USER, "not found use", "");
            return new ResponseEntity<ApiExceptionMessage>(exMessage, HttpStatus.UNAUTHORIZED);
        }
        log.debug("[사용자]정보 취득後:customerCi="+customerCi);
        log.debug("[사용자]정보 취득後:customerNameKor="+customerNameKor);

        //핀테크 서비스 연계확인 계좌정보 리스트 조회
        VtAccountListVO vtAccountListVO = cmmIntegratedAccountService.selectTestApiCommonMemberConsentList(searchRequestVO.getComId(), searchRequestVO.getSrvId(), customerCi, customerNameKor);
        //앱 연결된 가상계좌 정보 없음
        if(vtAccountListVO.getVtAccList().size() < 1) {
            ApiExceptionMessage exMessage = new ApiExceptionMessage(CodeConstants.OP_SPT, ApiExceptionMessage.EX_REQUIRED_CHECK, "No Content", "");
            return new ResponseEntity<ApiExceptionMessage>(exMessage, HttpStatus.NO_CONTENT);
        }

        //account API 헤더정보
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add("apiKey", OppfProperties.getProperty("Globals.integrated.account.apiKey"));
        log.debug("[사용자] accessToken : ============================");
        log.debug(accessToken);
        if(null == accessToken || "".equals(accessToken)){
            //회원고유 ACCESS_TOKEN 값 변경 예정
            httpHeaders.add("Authorization", "Bearer " + ACCESS_TOKEN);
        }else{
            //이준형과장 요청 17.3.2  외부 REST TOOL 에서 accessToken 발급시 우회 사용
            httpHeaders.add("Authorization", accessToken);
        }

        String ipAddr = CommonUtil.getLocalServerIp();
        String macAddr = "1866DA0D99D6";

        //Response 값 세팅
        InteGratedAccountVO inteGratedAccountResponseVO = new InteGratedAccountVO();
        List<Object> resultList= new ArrayList<>();
        List<CommonFailVO> failList = new ArrayList<>();
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
                String payload = "{ \"partner\" : { \"comId\": \""+searchRequestVO.getComId()+"\",  \"srvId\": \""+searchRequestVO.getSrvId()+"\"}," +
                        " \"commonHeader\" : { \"reqIdConsumer\": \"reqid-0001\",  \"ci\": \"" + customerCi + "\"}," +
                        " \"devInfo\" : { \"ipAddr\": \""+tmpIpAddr+"\",  \"macAddr\": \""+macAddr+"\"}," +
                        " \"accInfo\" : { \"vtAccNo\": \"" + vtAccNo + "\"}}";
                //{증권사}/account/interest/search
                //"endpoint": "https://apigw.koscom.co.kr/v1/cyber/account/"
                String accountApiUrl = vtAccountVO.getEndpoint().replaceAll("apigw.koscom.co.kr","10.0.1.101") + "interest/search";

                IntAcntWorker IntAcntWorker = new IntAcntWorker(accountApiUrl, httpHeaders, payload, cmmSIFInternalService,
                        vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), "", "");
                futures.add(executor.submit(IntAcntWorker));

            }catch (Exception e){
                e.printStackTrace();
                failList.add(new CommonFailVO(vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), e.getMessage()));
                failCount++;
            }
        }

        //스레드 종료처리
        executor.shutdown();
        // 결과값 병합
        for (Future<IntAcntWorker> future : futures) {
            IntAcntWorker worker = future.get();
            if(HttpStatus.OK.equals(worker.getResponseEntity().getStatusCode())){
                JSONObject jsonObject =  new JSONObject(worker.getResponseEntity().getBody());
                InterestResponseVO interestResponseVO = new Gson().fromJson(jsonObject.toString(),InterestResponseVO.class);
                resultList.add(interestResponseVO);
                successCount++;
            }else{
                failList.add(new CommonFailVO(worker.getComId(), worker.getVtAccNo(), worker.getVtAccAlias(), worker.getResponseEntity().getBody()));
                failCount++;
            }
        }

        //전체수행, 성공, 실패 count
        inteGratedAccountResponseVO.setTotalCount(totalCount);
        inteGratedAccountResponseVO.setSuccessCount(successCount);
        inteGratedAccountResponseVO.setFailCount(failCount);
        //success List
        inteGratedAccountResponseVO.setResultList(resultList);
        //fail List
        inteGratedAccountResponseVO.setFailList(failList);

        return inteGratedAccountResponseVO;
    }

    /**
     * @Method Name        : selectIntegratedAccountPortfolio
     * @Method description : 통합계좌 자산 포토폴리오
     * @param              : model
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping(
            value="/integration/account/portfolio"
            ,method = RequestMethod.POST)
    public Object selectIntegratedAccountPortfolio(@RequestBody IntegratedAccountSearchRequestVO searchRequestVO,
                                                   @RequestHeader(value = "authorization" , required = false) String accessToken) throws Exception{
        log.debug(searchRequestVO);
        if(null ==  searchRequestVO.getCustomerId() ||
                null ==  searchRequestVO.getComId() ||
                null == searchRequestVO.getSrvId()  ||
                null == searchRequestVO.getAssetType() ||
                null == searchRequestVO.getRspType()){
            ApiExceptionMessage exMessage = new ApiExceptionMessage(CodeConstants.OP_SPT, ApiExceptionMessage.EX_REQUIRED_CHECK, "필수 항목 누락", "");
            return new ResponseEntity<ApiExceptionMessage>(exMessage, HttpStatus.BAD_REQUEST);
        }

        String customerId = searchRequestVO.getCustomerId();
        String customerCi = "";
        String customerNameKor = "";
        MbrRegVO mbrRegVO = cmmIntegratedAccountService.selectSptCustomerVerifyProfileInfo(customerId);
        if(mbrRegVO != null){
            customerCi = mbrRegVO.getCustomerVerify();
            customerNameKor = mbrRegVO.getCustomerNameKor();
        }else{
            ApiExceptionMessage exMessage = new ApiExceptionMessage(CodeConstants.OP_SPT, ApiExceptionMessage.EX_NOT_FOUND_USER, "not found use", "");
            return new ResponseEntity<ApiExceptionMessage>(exMessage, HttpStatus.UNAUTHORIZED);
        }
        log.debug("[사용자]정보 취득後:customerCi="+customerCi);
        log.debug("[사용자]정보 취득後:customerNameKor="+customerNameKor);

        //핀테크 서비스 연계확인 계좌정보 리스트 조회
        VtAccountListVO vtAccountListVO = cmmIntegratedAccountService.selectTestApiCommonMemberConsentList(searchRequestVO.getComId(), searchRequestVO.getSrvId(), customerCi, customerNameKor);
        //앱 연결된 가상계좌 정보 없음
        if(vtAccountListVO.getVtAccList().size() < 1) {
            ApiExceptionMessage exMessage = new ApiExceptionMessage(CodeConstants.OP_SPT, ApiExceptionMessage.EX_REQUIRED_CHECK, "No Content", "");
            return new ResponseEntity<ApiExceptionMessage>(exMessage, HttpStatus.NO_CONTENT);
        }

        //account API 헤더정보
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add("apiKey", OppfProperties.getProperty("Globals.integrated.account.apiKey"));
        log.debug("[사용자] accessToken : ============================");
        log.debug(accessToken);
        if(null == accessToken || "".equals(accessToken)){
            //회원고유 ACCESS_TOKEN 값 변경 예정
            httpHeaders.add("Authorization", "Bearer " + ACCESS_TOKEN);
        }else{
            //이준형과장 요청 17.3.2  외부 REST TOOL 에서 accessToken 발급시 우회 사용
            httpHeaders.add("Authorization", accessToken);
        }

        String ipAddr = CommonUtil.getLocalServerIp();
        String macAddr = "1866DA0D99D6";

        //Response 값 세팅
        InteGratedAccountPortfolioVO inteGratedAccountResponseVO = new InteGratedAccountPortfolioVO();
        List<Object> resultList= new ArrayList<>();
        List<CommonFailVO> failList = new ArrayList<>();
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
                String tmpAssetType = searchRequestVO.getAssetType();
                //대신증권 IP 예외처리
                if(vtAccountVO.getEndpoint().indexOf("daishin") > 0){
                    tmpIpAddr = "192168010001";
                }
                //ebest증권 assetType 예외처리
                if(vtAccountVO.getEndpoint().indexOf("ebest") > 0){
                    tmpAssetType = "CASH";
                }
                String payload = "{ \"partner\" : { \"comId\": \""+searchRequestVO.getComId()+"\",  \"srvId\": \""+searchRequestVO.getSrvId()+"\"}," +
                        " \"commonHeader\" : { \"reqIdConsumer\": \"reqid-0001\",  \"ci\": \"" + customerCi + "\"}," +
                        " \"devInfo\" : { \"ipAddr\": \""+tmpIpAddr+"\",  \"macAddr\": \""+macAddr+"\"}," +
                        " \"accInfo\" : { \"vtAccNo\": \"" + vtAccNo + "\"}," +
                        " \"portfolioRequestBody\" : { \"queryType\" : { \"assetType\": \""+tmpAssetType+"\", \"rspType\": \""+searchRequestVO.getRspType()+"\"}}}";
                //{증권사}/account/portfolio/search
                //"endpoint": "https://apigw.koscom.co.kr/v1/cyber/account/"
                String accountApiUrl = vtAccountVO.getEndpoint().replaceAll("apigw.koscom.co.kr","10.0.1.101") + "portfolio/search";

                IntAcntWorker IntAcntWorker = new IntAcntWorker(accountApiUrl, httpHeaders, payload, cmmSIFInternalService,
                        vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), "", "");
                futures.add(executor.submit(IntAcntWorker));

            }catch (Exception e){
                e.printStackTrace();
                failList.add(new CommonFailVO(vtAccountVO.getComId(), vtAccNo, vtAccountVO.getVtAccAlias(), e.getMessage()));
                failCount++;
            }
        }

        //스레드 종료처리
        executor.shutdown();
        // 결과값 병합
        for (Future<IntAcntWorker> future : futures) {
            IntAcntWorker worker = future.get();
            if(HttpStatus.OK.equals(worker.getResponseEntity().getStatusCode())){
                JSONObject jsonObject =  new JSONObject(worker.getResponseEntity().getBody());
                PortfolioResponseVO portfolioResponseVO = new Gson().fromJson(jsonObject.toString(),PortfolioResponseVO.class);
                resultList.add(portfolioResponseVO);
                successCount++;
            }else{
                failList.add(new CommonFailVO(worker.getComId(), worker.getVtAccNo(), worker.getVtAccAlias(), worker.getResponseEntity().getBody()));
                failCount++;
            }
        }

        if(successCount > 0){
            //리스트 통합
            InteGratedAccountPortfolioVO.PortfolioResultVO integratedPortfolio = new InteGratedAccountPortfolioVO.PortfolioResultVO();
            MsgPortfolioCashVO cash = new MsgPortfolioCashVO();
            for(Object o : resultList){
                PortfolioResponseVO portfolioResponseVO = (PortfolioResponseVO)o;
                MsgPortfolioVO portfolio = portfolioResponseVO.getPortfolioList().getPortfolio();
                if(null != portfolio.getCash()){
                    cmmIntegratedAccountService.setPrtfolioCashSummary(cash, portfolio.getCash());
                }
            }
            //현금잔고비중
            integratedPortfolio.setCash(cash);
            inteGratedAccountResponseVO.setTotalResult(integratedPortfolio);
        }

        //전체수행, 성공, 실패 count
        inteGratedAccountResponseVO.setTotalCount(totalCount);
        inteGratedAccountResponseVO.setSuccessCount(successCount);
        inteGratedAccountResponseVO.setFailCount(failCount);
        //success List
        inteGratedAccountResponseVO.setResultList(resultList);
        //fail List
        inteGratedAccountResponseVO.setFailList(failList);

        return inteGratedAccountResponseVO;
    }
}