package kr.co.koscom.oppf.cmm.service;

import com.google.gson.Gson;
import kr.co.koscom.oppf.cmm.IntegratedAccount.service.*;
import kr.co.koscom.oppf.cmm.exception.ApiExceptionMessage;
import kr.co.koscom.oppf.cmm.util.CodeConstants;
import kr.co.koscom.oppf.cmm.util.CommonFunction;
import kr.co.koscom.oppf.spt.myp.intAcnt.service.impl.IntAcntWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author lee
 * @date 2017-02-09
 */

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:resources/spring/appServlet/servlet-context.xml"})
public class CmmSIFInternalServiceTest {
    //test 통합계좌서비스 API KEY
    private static final String TEST_APIKEY = "l7xxdd6ff2ba74944dfdb6c69643d12256a6";

    private static final String TEST_AUTHORIZATION = "Bearer ";
    private static final String TEST_ACCESSTOKEN = "52545431-9237-4dde-8fea-416cff41a9a4";
    @Resource(name = "CmmSIFInternalService")
    private CmmSIFInternalService cmmSIFInternalService;
    @Resource(name = "CmmIntegratedAccountService")
    private CmmIntegratedAccountService cmmIntegratedAccountService;

    @Test
    public void test_IntegratedAccountBalance() throws Exception{
        log.info("------------- IntegratedAccountBalance Test START ---------------");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("apiKey", TEST_APIKEY);
        httpHeaders.add("Authorization", TEST_AUTHORIZATION + TEST_ACCESSTOKEN);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        String exconn = "https://sandbox-apigw.koscom.co.kr/v1/sk/account/balance/search";

        /*******************************  통합계좌 조회 시작*******************************/
        InteGratedAccountBalanceViewVO inteGratedAccountResponseVO = new InteGratedAccountBalanceViewVO();
        List<Object> resultList= new ArrayList<>();
        List<CommonFailViewVO> failList = new ArrayList<>();
        int totalCount = 0;
        int successCount = 0;
        int failCount = 0;
        //ExecutorService 생성 (멀티 스레드)
        ExecutorService executor = Executors.newFixedThreadPool(4);
        //결과를 저장할 리스트
        List<Future<IntAcntWorker>> futures = new ArrayList<Future<IntAcntWorker>>();
        String comId = "00025";
        String vtAccNo = "170638777348700001";
        String vtAccAlias = "sk";
        int index = 4;
        for(int i = 0 ;i < index ; i++){
            try{
                String payload = "";

                if(i == 0){
                    payload = "{\n" +
                            "  \"partner\": {\n" +
                            "    \"comId\": \"00995\",\n" +
                            "    \"srvId\": \"297\"\n" +
                            "  },\n" +
                            "  \"commonHeader\": {\n" +
                            "    \"ci\": \"ci20170609145733\",\n" +
                            "    \"reqIdConsumer\": \"reqid-0001\"\n" +
                            "  },\n" +
                            "  \"devInfo\": {\n" +
                            "    \"ipAddr\": \"192168010001\",\n" +
                            "    \"macAddr\": \"1866DA0D99D6\"\n" +
                            "  },\n" +
                            "  \"accInfo\": {\n" +
                            "\t\"vtAccNo\": \"170638777348700001\"\n" +
                            "  },\n" +
                            "  \"balanceRequestBody\": {\n" +
                            "\t\"queryType\": {\n" +
                            "      \"assetType\": \"CASH\",\n" +
                            "      \"count\": 100\n" +
                            "    }\n" +
                            "  }\n" +
                            "}";
                }else if(i == 1){
                    payload = "{\n" +
                            "  \"partner\": {\n" +
                            "    \"comId\": \"00995\",\n" +
                            "    \"srvId\": \"297\"\n" +
                            "  },\n" +
                            "  \"commonHeader\": {\n" +
                            "    \"ci\": \"ci20170609145733\",\n" +
                            "    \"reqIdConsumer\": \"reqid-0001\"\n" +
                            "  },\n" +
                            "  \"devInfo\": {\n" +
                            "    \"ipAddr\": \"192168010001\",\n" +
                            "    \"macAddr\": \"1866DA0D99D6\"\n" +
                            "  },\n" +
                            "  \"accInfo\": {\n" +
                            "\t\"vtAccNo\": \"170638777348700001\"\n" +
                            "  },\n" +
                            "  \"balanceRequestBody\": {\n" +
                            "\t\"queryType\": {\n" +
                            "      \"assetType\": \"EQTY\",\n" +
                            "      \"count\": 100\n" +
                            "    }\n" +
                            "  }\n" +
                            "}";
                }else if(i == 2){
                    payload = "{\n" +
                            "  \"partner\": {\n" +
                            "    \"comId\": \"00995\",\n" +
                            "    \"srvId\": \"297\"\n" +
                            "  },\n" +
                            "  \"commonHeader\": {\n" +
                            "    \"ci\": \"ci20170609145733\",\n" +
                            "    \"reqIdConsumer\": \"reqid-0001\"\n" +
                            "  },\n" +
                            "  \"devInfo\": {\n" +
                            "    \"ipAddr\": \"192168010001\",\n" +
                            "    \"macAddr\": \"1866DA0D99D6\"\n" +
                            "  },\n" +
                            "  \"accInfo\": {\n" +
                            "\t\"vtAccNo\": \"170638777348700001\"\n" +
                            "  },\n" +
                            "  \"balanceRequestBody\": {\n" +
                            "\t\"queryType\": {\n" +
                            "      \"assetType\": \"FUND\",\n" +
                            "      \"count\": 100\n" +
                            "    }\n" +
                            "  }\n" +
                            "}";
                }else if(i == 3){
                    payload = "{\n" +
                            "  \"partner\": {\n" +
                            "    \"comId\": \"00995\",\n" +
                            "    \"srvId\": \"297\"\n" +
                            "  },\n" +
                            "  \"commonHeader\": {\n" +
                            "    \"ci\": \"ci20170609145733\",\n" +
                            "    \"reqIdConsumer\": \"reqid-0001\"\n" +
                            "  },\n" +
                            "  \"devInfo\": {\n" +
                            "    \"ipAddr\": \"192168010001\",\n" +
                            "    \"macAddr\": \"1866DA0D99D6\"\n" +
                            "  },\n" +
                            "  \"accInfo\": {\n" +
                            "\t\"vtAccNo\": \"170638777348700001\"\n" +
                            "  },\n" +
                            "  \"balanceRequestBody\": {\n" +
                            "\t\"queryType\": {\n" +
                            "      \"assetType\": \"ETC\",\n" +
                            "      \"count\": 100\n" +
                            "    }\n" +
                            "  }\n" +
                            "}";
                }

                IntAcntWorker IntAcntWorker = new IntAcntWorker(exconn, httpHeaders, payload, cmmSIFInternalService,
                        comId, vtAccNo, vtAccAlias, "", "");
                futures.add(executor.submit(IntAcntWorker));
            }catch (Exception e){
                log.debug(e.getMessage());
                CommonFailViewVO failViewVO = new CommonFailViewVO(comId, vtAccNo, vtAccAlias, e.getMessage());
                failViewVO.setComName(CommonFunction.companyName(comId));
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

        //전체수행, 성공, 실패 count
        inteGratedAccountResponseVO.setTotalCount(totalCount);
        inteGratedAccountResponseVO.setSuccessCount(successCount);
        inteGratedAccountResponseVO.setFailCount(failCount);
        //success List
        inteGratedAccountResponseVO.setResultList(resultList);
        //fail List
        inteGratedAccountResponseVO.setFailList(failList);

        log.debug(inteGratedAccountResponseVO.toString());

    }

    @Test
    public void test_IntegratedAccountBalanceDataSet() throws Exception{
        log.info("------------- IntegratedAccountBalanceDataSet Test START ---------------");
        /***********************************************************************************
        통합계좌 잔고 조회 -
        회원이 통합계좌 서비스로 신청한 가상계좌 정보로 여러 건의 매핑된 증권사별 통합계좌 잔고조회 화면을 출력
         ***********************************************************************************/

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String exconn = "http://localhost:8443";
        String authorizationUrl = exconn+"/test/account/balance/search";
        Map success = new HashMap<>();
        Map fail = new HashMap<String,String>();

        try{
            String payload = "{ \"balanceRequestBody\" : { \"accInfo\": {\"vtAccNo\": \"123\"}}}";
            ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(authorizationUrl, httpHeaders, HttpMethod.POST, payload);
            if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
                JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
                BalanceResponseVO balanceResponseVO = new Gson().fromJson(jsonObject.toString(),BalanceResponseVO.class);
                success.put("123",balanceResponseVO);
            }else{
                fail.put("123","");
            }

            payload = "{ \"balanceRequestBody\" : { \"accInfo\": {\"vtAccNo\": \"160665474469800099\"}}}";
            responseEntity = cmmSIFInternalService.sendRestTemplate(authorizationUrl, httpHeaders, HttpMethod.POST, payload);
            if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
                JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
                BalanceResponseVO balanceResponseVO = new Gson().fromJson(jsonObject.toString(),BalanceResponseVO.class);
                success.put("160665474469800099",balanceResponseVO);
            }else{
                fail.put("160665474469800099","");
            }

        }catch (Exception e){
           e.printStackTrace();
        }
        log.debug("success : {}", success);
        log.debug("fail : {}", fail);
    }

    @Test
    public void test_IntegratedAccountInterestDataSet() throws Exception{
        log.info("------------- AccountInterestDataSet Test START ---------------");
        /***********************************************************************************
         통합계좌 관심종목 조회 -
         회원이 통합계좌 서비스로 신청한 가상계좌 정보로 여러 건의 매핑된 증권사별 통합계좌 관심종목 화면을 출력
         ***********************************************************************************/

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String exconn = "http://localhost:8443";
        String authorizationUrl = exconn+"/test/account/interest/search";
        Map success = new HashMap<>();
        Map fail = new HashMap<String,String>();

        try{
            String payload = "{ \"interestSymbolListRequestBody\" : { \"accInfo\": {\"vtAccNo\": \"160624362225600099\"}}}";
            ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(authorizationUrl, httpHeaders, HttpMethod.POST, payload);
            if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
                JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
                InterestResponseVO interestResponseVO = new Gson().fromJson(jsonObject.toString(),InterestResponseVO.class);
                success.put("160624362225600099",interestResponseVO);
            }else{
                fail.put("160624362225600099","");
            }
        }catch (Exception e){
            e.printStackTrace();
            fail.put("160624362225600099","");
        }


        try{
            String payload = "{ \"interestSymbolListRequestBody\" : { \"accInfo\": {\"vtAccNo\": \"160613251214500099\"}}}";
            ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(authorizationUrl, httpHeaders, HttpMethod.POST, payload);
            if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
                JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
                InterestResponseVO interestResponseVO = new Gson().fromJson(jsonObject.toString(),InterestResponseVO.class);
                success.put("160613251214500099",interestResponseVO);
            }else{
                fail.put("160613251214500099","");
            }
        }catch (Exception e){
            e.printStackTrace();
            fail.put("160613251214500099","");
        }

        log.debug("success : {}", success);
        log.debug("portfolioFail : {}", fail);
    }

    @Test
    public void test_IntegratedAccountPortfolioDataSet() throws Exception{
        log.info("------------- IntegratedAccountPortfolioDataSet Test START ---------------");
        /***********************************************************************************
         통합계좌 자산 포토폴리오 조회 -
         회원이 통합계좌 서비스로 신청한 가상계좌 정보로 여러 건의 매핑된 증권사별 통합계좌 자산포토폴리오 화면을 출력
         ***********************************************************************************/

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String exconn = "http://localhost:8443";
        String authorizationUrl = exconn+"/test/account/portfolio/search";
        Map success = new HashMap<>();
        Map fail = new HashMap<String,String>();

            try{
                String payload = "{ \"portfolioRequestBody\" : { \"accInfo\": {\"vtAccNo\": \"160624362225600099\"}}}";
                ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(authorizationUrl, httpHeaders, HttpMethod.POST, payload);
                if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
                    JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
                    PortfolioResponseVO portfolioResponseVO = new Gson().fromJson(jsonObject.toString(),PortfolioResponseVO.class);
                    success.put("160624362225600099",portfolioResponseVO);
                }else{
                    fail.put("160624362225600099","");
                }
            }catch (Exception e){
                e.printStackTrace();
                fail.put("160624362225600099","");
            }

            try{
                String payload = "{ \"portfolioRequestBody\" : { \"accInfo\": {\"vtAccNo\": \"160612734676100099\"}}}";
                ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(authorizationUrl, httpHeaders, HttpMethod.POST, payload);
                if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
                    JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
                    PortfolioResponseVO portfolioResponseVO = new Gson().fromJson(jsonObject.toString(),PortfolioResponseVO.class);
                    success.put("160612734676100099",portfolioResponseVO);
                }else{
                    fail.put("160612734676100099","");
                }
            }catch (Exception e){
                e.printStackTrace();
                fail.put("160612734676100099","");
            }

        log.debug("success : {}", success);
        log.debug("fail : {}", fail);
    }

    @Test
    public void test_IntegratedAccountTransactionDataSet() throws Exception{
        log.info("------------- IntegratedAccountTransactionDataSet Test START ---------------");
        /***********************************************************************************
         통합계좌 거내래역 조회 -
         회원이 통합계좌 서비스로 신청한 가상계좌 정보로 여러 건의 매핑된 증권사별 통합계좌 거래내역 화면을 출력
         ***********************************************************************************/

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String exconn = "http://localhost:8443";
        String authorizationUrl = exconn+"/test/account/transaction/search";
        Map success = new HashMap<>();
        Map fail = new HashMap<String,String>();

        try{
            String payload = "{ \"transactionHistoryRequestBody\" : { \"accInfo\": {\"vtAccNo\": \"160624362225600099\"}}}";
            ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(authorizationUrl, httpHeaders, HttpMethod.POST, payload);
            if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
                JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
                TransactionResponseVO transactionResponseVO = new Gson().fromJson(jsonObject.toString(),TransactionResponseVO.class);
                success.put("160624362225600099",transactionResponseVO);
            }else{
                fail.put("160624362225600099","");
            }
        }catch (Exception e){
            e.printStackTrace();
            fail.put("160624362225600099","");
        }


        try{
            String payload = "{ \"transactionHistoryRequestBody\" : { \"accInfo\": {\"vtAccNo\": \"123\"}}}";
            ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(authorizationUrl, httpHeaders, HttpMethod.POST, payload);
            if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
                JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
                TransactionResponseVO transactionResponseVO = new Gson().fromJson(jsonObject.toString(),TransactionResponseVO.class);
                success.put("123",transactionResponseVO);
            }else{
                fail.put("123","");
            }
        }catch (Exception e){
            e.printStackTrace();
            fail.put("123","");
        }

        log.debug("success : {}", success);
        log.debug("fail : {}", fail);
    }
}
