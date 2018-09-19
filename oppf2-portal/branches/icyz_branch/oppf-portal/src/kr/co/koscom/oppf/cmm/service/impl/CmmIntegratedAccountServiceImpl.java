package kr.co.koscom.oppf.cmm.service.impl;

import com.google.gson.Gson;
import kr.co.koscom.oppf.cmm.IntegratedAccount.service.*;
import kr.co.koscom.oppf.cmm.exception.ApiExceptionMessage;
import kr.co.koscom.oppf.cmm.service.CmmIntegratedAccountService;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.CodeConstants;
import kr.co.koscom.oppf.cmm.util.CommonFunction;
import kr.co.koscom.oppf.spt.myp.intAcnt.service.IntAcntSearchVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.impl.MbrRegDAO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceAccountProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SvcApplService;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SvcApplVO;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : 이희태
 * @version : 1.0
 * @Project : 자본시장 공동 핀테크 오픈플랫폼 구축 2차
 * @FileName : CmmIntegratedAccountService.java
 * @Comment : 통합계좌 DATA SET 기능을 제공하는 service
 * @see
 * @since : 2017.02.13
 */
@Slf4j
@Service("CmmIntegratedAccountService")
public class CmmIntegratedAccountServiceImpl implements CmmIntegratedAccountService {

    @Resource(name = "MbrRegDAO")
    private MbrRegDAO mbrRegDAO;
    @Resource(name = "SvcApplService")
    private SvcApplService svcApplService;
    /**
     * @Method Name        : setBalanceSummary
     * @Method description : 잔고조회 summary 통합 기능
     * @param              : MsgBalanceSummaryVO 통합
     * @param              : MsgBalanceSummaryVO
     */
    @Override
    public void setBalanceSummary(MsgBalanceSummaryVO msgBalanceSummaryVO_org, MsgBalanceSummaryVO msgBalanceSummaryVO) {
        //현금잔고
        msgBalanceSummaryVO_org.setCashBalance(isNullToLong(msgBalanceSummaryVO_org.getCashBalance()) + isNullToLong(msgBalanceSummaryVO.getCashBalance()));
        //대용금
        msgBalanceSummaryVO_org.setSubstitute(isNullToLong(msgBalanceSummaryVO_org.getSubstitute()) + isNullToLong(msgBalanceSummaryVO.getSubstitute()));
        //미수/미납금
        msgBalanceSummaryVO_org.setReceivable(isNullToLong(msgBalanceSummaryVO_org.getReceivable()) + isNullToLong(msgBalanceSummaryVO.getReceivable()));
        //대용증거금
        msgBalanceSummaryVO_org.setSubsMargin(isNullToLong(msgBalanceSummaryVO_org.getSubsMargin()) + isNullToLong(msgBalanceSummaryVO.getSubsMargin()));
        //대출/신용금
        msgBalanceSummaryVO_org.setLoanCredit(isNullToLong(msgBalanceSummaryVO_org.getLoanCredit()) + isNullToLong(msgBalanceSummaryVO.getLoanCredit()));
        //유가증권매수금액
        msgBalanceSummaryVO_org.setValAtTrade(isNullToLong(msgBalanceSummaryVO_org.getValAtTrade()) + isNullToLong(msgBalanceSummaryVO.getValAtTrade()));
        //유가증권평가금액
        msgBalanceSummaryVO_org.setValueAtCur(isNullToLong(msgBalanceSummaryVO_org.getValueAtCur()) + isNullToLong(msgBalanceSummaryVO.getValueAtCur()));
        //유가증권평가손익
        msgBalanceSummaryVO_org.setProLoss(isNullToLong(msgBalanceSummaryVO_org.getProLoss()) + isNullToLong(msgBalanceSummaryVO.getProLoss()));
        //총평가금액
        msgBalanceSummaryVO_org.setTotalAccVal(isNullToLong(msgBalanceSummaryVO_org.getTotalAccVal()) + isNullToLong(msgBalanceSummaryVO.getTotalAccVal()));
        //출금가능액
        msgBalanceSummaryVO_org.setCashAvWithdraw(isNullToLong(msgBalanceSummaryVO_org.getCashAvWithdraw()) + isNullToLong(msgBalanceSummaryVO.getCashAvWithdraw()));
    }

    /**
     * @Method Name        : setPrtfolioCashSummary
     * @Method description : 자산 포토폴리오 현금 summary 통합 기능
     * @param              : MsgBalanceSummaryVO 통합
     * @param              : MsgBalanceSummaryVO
     */
    @Override
    public void setPrtfolioCashSummary(MsgPortfolioCashVO msgPortfolioCashVO_org, MsgPortfolioCashVO msgPortfolioCashVO) {
        //전체 자산 중 현금잔고 또는 비중
        msgPortfolioCashVO_org.setAmt(isNullToLong(msgPortfolioCashVO_org.getAmt()) + isNullToLong(msgPortfolioCashVO.getAmt()));
    }

    /**
     * @Method Name        : setPrtfolioCashSummary
     * @Method description : 회원 CI, 한글 이름 조회
     * @param              : customerId
     */
    @Override
    public MbrRegVO selectSptCustomerVerifyProfileInfo(String customerId) throws Exception {
        MbrRegVO pMbrRegVO = new MbrRegVO();
        pMbrRegVO.setCustomerId(customerId);
        return (MbrRegVO) mbrRegDAO.selectSptCustomerVerifyProfileInfo(pMbrRegVO);
    }

    /**
     * @Method Name        : selectTestApiCommonMemberConsentList
     * @Method description : 핀테크 서비스 연계확인 계좌정보 리스트 조회(TEST API용)
     * @param              : comId
     * @param              : srvId
     * @param              : customerCi
     * @param              : customerNameKor
     */
    @Override
    public VtAccountListVO selectTestApiCommonMemberConsentList(String comId, String srvId, String customerCi, String customerNameKor) throws Exception {
        VtAccountListVO responseBody = new VtAccountListVO();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add("apiKey", OppfProperties.getProperty("Globals.integrated.account.apiKey"));
        //핀테크 서비스 연계확인 API
        String consentApiUr = OppfProperties.getProperty("Globals.domain.exconn") +  "/v1/common/member/consent/search";
        String payload = "{ \"partner\" : { \"comId\": \""+comId+"\",  \"srvId\": \""+srvId+"\"}," +
                " \"commonHeader\" : { \"reqIdConsumer\": \"reqid-0001\",  \"ci\": \"" + customerCi + "\"}," +
                " \"body\" : { \"korName\": \""+customerNameKor+"\"}}";

        HttpEntity<String> requestEntity = new HttpEntity<String>(payload, httpHeaders);
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
            ResponseEntity<VtAccountListVO> responseEntity = restTemplate.exchange(consentApiUr, HttpMethod.POST, requestEntity, VtAccountListVO.class);
            responseBody = responseEntity.getBody();
            log.debug("ok:responseBody : {} ", responseEntity.getBody());

        }catch(Exception e){
            e.printStackTrace();
        }
        return responseBody;
    }

    /**
     * @Method Name        : selectCommonMemberConsentSearch
     * @Method description : 핀테크 서비스 연계확인 계좌정보 리스트 조회
     * @param              : comId
     * @param              : srvId
     * @param              : customerCi
     * @param              : customerNameKor
     */
    @Override
    public VtAccountListVO selectCommonMemberConsentList(String comId, String srvId, String customerCi, String customerNameKor, IntAcntSearchVO paramVO) throws Exception {
        VtAccountListVO responseBody = new VtAccountListVO();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add("apiKey", OppfProperties.getProperty("Globals.integrated.account.apiKey"));
        //핀테크 서비스 연계확인 API
        String consentApiUr = OppfProperties.getProperty("Globals.domain.exconn") +  "/v1/common/member/consent/search";
        String payload = "{ \"partner\" : { \"comId\": \""+comId+"\",  \"srvId\": \""+srvId+"\"}," +
                         " \"commonHeader\" : { \"reqIdConsumer\": \"reqid-0001\",  \"ci\": \"" + customerCi + "\"}," +
                         " \"body\" : { \"korName\": \""+customerNameKor+"\"}}";

        HttpEntity<String> requestEntity = new HttpEntity<String>(payload, httpHeaders);
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
            ResponseEntity<VtAccountListVO> responseEntity = restTemplate.exchange(consentApiUr, HttpMethod.POST, requestEntity, VtAccountListVO.class);
            responseBody = responseEntity.getBody();
            log.debug("ok:responseBody : {} ", responseEntity.getBody());
            //조회 조간 가상계좌리스트 셋
            searchVtAccountList(responseBody, paramVO);

        }catch(Exception e){
            e.printStackTrace();
        }
        return responseBody;
    }

    /**
     * @param svcApplVO
     * @Method Name        : selectCommonMemberConsentSearch
     * @Method description : 핀테크 서비스 연계확인 계좌정보 리스트 조회(개발용)
     */
    @Override
    public VtAccountListVO selectCommonMemberConsentListDev(SvcApplVO svcApplVO, IntAcntSearchVO paramVO) throws Exception {
        //회원이 신청한 통합계좌조회 APP 리스트 조회 (서비스 신청 여부, 가상계좌 사용여부)
        List<SptCustomerServiceAccountProfileVO> accountProfileList = svcApplService.selectFintechSvcIntAcntAccountList(svcApplVO);
        VtAccountListVO vtAccountListVO = new VtAccountListVO();
        List<VtAccountVO> vtAccList = new ArrayList<>();
        for(SptCustomerServiceAccountProfileVO accountProfileVO : accountProfileList){
            //서비스 제공동의가 완료된 가상계좌 매핑된 리스트만 허용
            if("Y".equals(accountProfileVO.getUseYn())){
                VtAccountVO accountVO = new VtAccountVO();
                accountVO.setComId(accountProfileVO.getPubcompanyCodeId());
                accountVO.setVtAccNo(accountProfileVO.getCustomerVtaccountNo());
                // 통합테스트 샌드박스에서 G/W를 v2 버전으로 호출하도록 분기
                /*String endPoint = "snd".equals(devMode) ? OppfProperties.getProperty("Globals.domain.exconn") + "/v2/" + accountProfileVO.getPubcompanyEngName() + "/account/"
                        :  OppfProperties.getProperty("Globals.domain.exconn") + "/v1/" + accountProfileVO.getPubcompanyEngName() + "/account/";*/
                String endPoint = OppfProperties.getProperty("Globals.domain.exconn") + "/v1/" + accountProfileVO.getPubcompanyEngName() + "/account/";

                accountVO.setEndpoint(endPoint);
                vtAccList.add(accountVO);
            }
        }
        vtAccountListVO.setVtAccList(vtAccList);
        //조회 조간 가상계좌리스트 셋
        searchVtAccountList(vtAccountListVO, paramVO);
        return vtAccountListVO;
    }

    /**
     * @Method Name        : setBalanceResponseVO
     * @Method description : 잔고조회 result List view set
     * @param              : BalanceViewVO
     * @param              : String comId
     * @param              : String vtAccNo
     */
    @Override
    public BalanceResponseViewVO setBalanceResponseVO(BalanceResponseViewVO balanceResponseViewVO, String comId, String vtAccNo) throws Exception {

        if(null != balanceResponseViewVO && null != balanceResponseViewVO.getBalanceList().getBalance()){
            BalanceViewVO balanceViewVO = balanceResponseViewVO.getBalanceList().getBalance();
            //주식 리스트
            List<MsgBalanceEquityListViewVO> equityList = balanceViewVO.getEquityList();
            for(MsgBalanceEquityListViewVO vo : equityList){
                vo.setVtAccNo(vtAccNo);
                vo.setAssetType(CommonFunction.equityAssetTypeName(vo.getAssetType()));
                vo.setComName(CommonFunction.companyName(comId));
                vo.setIsinName(CommonFunction.isinName(vo.getIsinCode()));
            }
            //펀드 리스트
            List<MsgBalanceFundListViewVO> fundList = balanceViewVO.getFundList();
            for(MsgBalanceFundListViewVO vo : fundList){
                vo.setVtAccNo(vtAccNo);
                vo.setComName(CommonFunction.companyName(comId));
            }
            //기타 리스트
            List<MsgBalanceEtcListViewVO> etcList = balanceViewVO.getEtcList();
            for(MsgBalanceEtcListViewVO vo : etcList){
                vo.setVtAccNo(vtAccNo);
                vo.setAssetType(CommonFunction.etcAssetTypeName(vo.getAssetType()));
                vo.setComName(CommonFunction.companyName(comId));
            }
        }

        return balanceResponseViewVO;
    }

    /**
     * @Method Name        : setTransactionResponseVO
     * @Method description : 거래내역 result List view set
     * @param              : TransactionResponseViewVO
     * @param              : String comId
     * @param              : String vtAccNo
     * @param              : String searchIsinType
     * @param              : String isinCode
     */
    @Override
    public TransactionResponseViewVO setTransactionResponseVO(TransactionResponseViewVO transactionResponseViewVO, String comId, String vtAccNo, String searchIsinType, String isinCode) throws Exception {

        if(null != transactionResponseViewVO && null != transactionResponseViewVO.getTransList().getTransaction()){
            List<MsgTransactionViewVO> resultTransactionViewVOList = new ArrayList<>();
            List<MsgTransactionViewVO> transactionViewVOList = transactionResponseViewVO.getTransList().getTransaction();
                for(MsgTransactionViewVO vo : transactionViewVOList){
                    vo.setComName(CommonFunction.companyName(comId));
                    vo.setIsinName(CommonFunction.isinName(vo.getIsinCode()));
                    vo.setTransType(CommonFunction.transTypeName(vo.getTransType()));
                    vo.setVtAccNo(vtAccNo);
                    //종목 검색 체크
                    if(null != isinCode && "".equals(isinCode)){
                        resultTransactionViewVOList.add(vo);
                    }else{
                        if("name".equals(searchIsinType)){
                            //종목명 검색이 일치할 경우만 리스트 추가
                            if(vo.getIsinName().indexOf(isinCode) > -1){
                                resultTransactionViewVOList.add(vo);
                            }
                        }else if("code".equals(searchIsinType)){
                            //종목코드 검색이 일치할 경우만 리스트 추가
                            if(vo.getIsinCode().indexOf(isinCode) > -1){
                                resultTransactionViewVOList.add(vo);
                            }
                        }
                    }
                }
            transactionResponseViewVO.getTransList().setTransaction(resultTransactionViewVOList);
        }
        return transactionResponseViewVO;
    }

    /**
     * @Method Name        : setPortfolioResponseVO
     * @Method description : 포토폴리오 result List view set
     * @param              : PortfolioResponseViewVO
     * @param              : String comId
     * @param              : String vtAccNo
     */
    @Override
    public PortfolioResponseViewVO setPortfolioResponseVO(PortfolioResponseViewVO portfolioResponseViewVO, String comId, String vtAccNo) throws Exception {

        if(null != portfolioResponseViewVO && null != portfolioResponseViewVO.getPortfolioList().getPortfolio()){
            MsgPortfolioViewVO portfolioViewVO = portfolioResponseViewVO.getPortfolioList().getPortfolio();
            //주식 리스트
            List<MsgPortfolioEquityListViewVO> equityList = portfolioViewVO.getEquityList();
            for(MsgPortfolioEquityListViewVO vo : equityList){
                vo.setVtAccNo(vtAccNo);
                vo.setComName(CommonFunction.companyName(comId));
                vo.setIsinName(CommonFunction.isinName(vo.getIsinCode()));
                vo.setAssetType(CommonFunction.equityAssetTypeName(vo.getAssetType()));
            }
            //펀드 리스트
            List<MsgPortfolioFundListViewVO> fundList = portfolioViewVO.getFundList();
            for(MsgPortfolioFundListViewVO vo : fundList){
                vo.setVtAccNo(vtAccNo);
                vo.setComName(CommonFunction.companyName(comId));
            }
            //기타 리스트
            List<MsgPortfolioEtcListViewVO> etcList = portfolioViewVO.getEtcList();
            for(MsgPortfolioEtcListViewVO vo : etcList){
                vo.setVtAccNo(vtAccNo);
                vo.setAssetType(CommonFunction.etcAssetTypeName(vo.getAssetType()));
                vo.setComName(CommonFunction.companyName(comId));
            }
        }

        return portfolioResponseViewVO;
    }

    /**
     * @Method Name        : setInterestResponseVO
     * @Method description : 포토폴리오 result List view set
     * @param              : InterestResponseViewVO
     * @param              : String comId
     */
    @Override
    public InterestResponseViewVO setInterestResponseVO(InterestResponseViewVO interestResponseViewVO, String comId) throws Exception {

        if(null != interestResponseViewVO && null != interestResponseViewVO.getInterestSymbolListResponseBody().getGroupList().getGroup()){
            for(MsgInterestGroupViewVO vo : interestResponseViewVO.getInterestSymbolListResponseBody().getGroupList().getGroup()){
                vo.setComName(CommonFunction.companyName(comId));
                List<String> isinNameList = new ArrayList<>();
                    for(String isinCode : vo.getIsinCode()){
                        isinNameList.add(CommonFunction.isinName(isinCode));
                    }
                vo.setIsinName(isinNameList);
            }
        }
        return interestResponseViewVO;
    }

    /**
     * @param accountList
     * @param failList
     * @Method Name        : setCommonAccountFail
     * @Method description : 요청한 계좌정보가 서비스 연결 계좌 리스트에 존재하지 않는경우 실패 처리 (API 계약 해지, 증권사 계약해지)
     */
    @Override
    public int setCommonAccountFail(List<VtAccountVO> accountList, List<CommonFailViewVO> failList) throws Exception {
        int failCount = 0;
        if(accountList != null && accountList.size() > 0){
           for(VtAccountVO vtAccNo : accountList){
               CommonFailViewVO failViewVO = new CommonFailViewVO(vtAccNo.getComId(), vtAccNo.getVtAccNo(), vtAccNo.getVtAccAlias(),
                       new Gson().toJson(new ApiExceptionMessage(CodeConstants.OP_SPT, ApiExceptionMessage.EX_NOT_LINK_ACCOUNT, "[관리자문의] 가상계좌정보가 존재하지 않습니다", "")));
               failViewVO.setComName(CommonFunction.companyName(vtAccNo.getComId()));
               failList.add(failViewVO);
               failCount++;
           }
        }
        return failCount;
    }

    /**
     * @param internalAccountList
     * @param apiAccountList
     * @Method Name        : setLinkAccountList
     * @Method description : 서비스 연결 계좌 리스트 비교 (API 계약 해지, 증권사 계약해지)
     */
    @Override
    public void setLinkAccountList(List<VtAccountVO> internalAccountList, List<VtAccountVO> apiAccountList) throws Exception {
        if(null != apiAccountList && apiAccountList.size() > 0){
            List<VtAccountVO> tempList = new ArrayList<>();
            // 서비스 포털에서 조회한 통합계좌 리스트
            for(VtAccountVO accountVO: internalAccountList){
                // 핀테크 연계서버 API 조회 통합계좌 리스트
                for(VtAccountVO vo : apiAccountList){
                    if(accountVO.getVtAccNo().equals(vo.getVtAccNo())){
                        tempList.add(accountVO);
                        break;
                    }
                }
            }
            internalAccountList.removeAll(tempList);
        }
    }

    public VtAccountListVO searchVtAccountList(VtAccountListVO vtAccountListVO, IntAcntSearchVO paramVO) throws Exception {
        if(null == vtAccountListVO){
            return  vtAccountListVO;
        }

        //검색
        if(null != paramVO.getAccountNo() && !"".equals(paramVO.getAccountNo())
                || null!= paramVO.getCompanyId() && !"".equals(paramVO.getCompanyId())){
            List<VtAccountVO> tempVtAccList = vtAccountListVO.getVtAccList();
            List<VtAccountVO> vtAccList = new ArrayList<>();
            for(VtAccountVO vtAccountVO :  tempVtAccList){
                //특정 증권사 계좌 리스트 조회
                if("".equals(paramVO.getAccountNo()) && !"".equals(paramVO.getCompanyId())){
                    if(paramVO.getCompanyId().equals(vtAccountVO.getComId())){
                        vtAccList.add(vtAccountVO);
                    }
                }else{
                    // 선택 계좌만 조회
                    if(paramVO.getAccountNo().equals(vtAccountVO.getVtAccNo())){
                        vtAccList.add(vtAccountVO);
                        break;
                    }
                }
            }
            //선택된 증권사 계좌 리스트 적용
            vtAccountListVO.setVtAccList(vtAccList);
        }else{
            return  vtAccountListVO;
        }

        return vtAccountListVO;
    }

    public Long isNullToLong(Long value){
        Long returnLong = 0L;
        if(value != null)
            returnLong = value;
        return  returnLong;
    }
}
