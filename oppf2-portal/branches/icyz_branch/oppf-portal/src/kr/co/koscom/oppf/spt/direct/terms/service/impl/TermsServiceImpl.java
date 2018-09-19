package kr.co.koscom.oppf.spt.direct.terms.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.security.cert.X509Certificate;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.koscom.oppf.cmm.tsa.service.CmmTsaService;
import kr.co.koscom.oppf.cmm.util.CodeConstants;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.direct.common.exception.ApiException;
import kr.co.koscom.oppf.spt.direct.common.exception.ApiExceptionCode;
import kr.co.koscom.oppf.spt.direct.common.model.RequestResult;
import kr.co.koscom.oppf.spt.direct.common.model.Resp;
import kr.co.koscom.oppf.spt.direct.common.util.CommonCodeConstants;
import kr.co.koscom.oppf.spt.direct.company.dao.CompanyDAO;
import kr.co.koscom.oppf.spt.direct.company.dto.CompanyDTO;
import kr.co.koscom.oppf.spt.direct.internal.model.InternalResponse;
import kr.co.koscom.oppf.spt.direct.internal.model.MemberResponse;
import kr.co.koscom.oppf.spt.direct.terms.dao.TermsDAO;
import kr.co.koscom.oppf.spt.direct.terms.dto.TermsDTO;
import kr.co.koscom.oppf.spt.direct.terms.dto.TermsInfoDTO;
import kr.co.koscom.oppf.spt.direct.terms.model.AgreementContentResponse;
import kr.co.koscom.oppf.spt.direct.terms.model.AgreementInfo;
import kr.co.koscom.oppf.spt.direct.terms.model.Partner;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsContentRequest;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsContentResponse;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsContentResponse.FinanceTermsContentResponseBody;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsInfo;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsRevokeRequest;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsRevokeResponse;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsSearchRequest;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsSearchResponse;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsSearchResponse.FinanceTermsSearchResponseBody;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsTransmitRequest;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsTransmitResponse;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsTransmitResponse.FinanceTermsTransmitResponseBody;
import kr.co.koscom.oppf.spt.direct.terms.service.TermsService;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.AppService;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.AppVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceTermsFileProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.TermsPubCompanyVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.TermsVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.impl.AppDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TermsServiceImpl implements TermsService {

    @Autowired
    private TermsDAO termsDao;

    @Autowired
    private CompanyDAO companyDao;
    
    @Autowired
    private AppDAO appDAO;
    
    @Autowired
    private AppService appService;
    
    @Autowired
    private CmmTsaService cmmTsaService;

    @Override
    public TermsContentResponse selectTermsContent(TermsContentRequest request) {

        try {
            TermsDTO termsDto = termsDao.selectTerms(CommonCodeConstants.TERMS_TYPE_FINANCE); // 금융정보제공동의서

            if (termsDto == null) {
                throw new ApiException(ApiExceptionCode.NOT_FOUND_TERMS);
            }

            TermsContentResponse termsContentResponse = new TermsContentResponse();

            // data setting
            TermsInfo financeTermsInfo = new TermsInfo();

            Date currentDate = Calendar.getInstance(Locale.KOREA).getTime();
            String currentDateStr = DateFormatUtils.format(currentDate, "yyyyMMdd");
            Date expireDate = DateUtils.addDays(DateUtils.addYears(currentDate, 1), -1);
            String expireDateStr = DateFormatUtils.format(expireDate, "yyyyMMdd");

            // financeTermsInfo.setTermsStartDate(currentDateStr);
            // financeTermsInfo.setTermsEndDate(expireDateStr);
            // financeTermsInfo.setTermsDate(currentDateStr);
            financeTermsInfo.setCustomerBirthDay(
                    request.getFinanceTermsContentRequestBody().getRequestParameters().getCustomerBirthDay());
            financeTermsInfo.setCustomerEmail(
                    request.getFinanceTermsContentRequestBody().getRequestParameters().getCustomerEmail());
            financeTermsInfo.setCustomerPhone(
                    request.getFinanceTermsContentRequestBody().getRequestParameters().getCustomerPhone());
            financeTermsInfo.setCustomerUserNm(
                    request.getFinanceTermsContentRequestBody().getRequestParameters().getCustomerUserNm());
            financeTermsInfo.setCompanyList(
                    request.getFinanceTermsContentRequestBody().getRequestParameters().getCompanyList());

            financeTermsInfo.setVersion(termsDto.getTermsContentVer());

            financeTermsInfo.setText(makeTermContent(request.getPartner(), financeTermsInfo, termsDto.getTermsContent()));
            termsContentResponse.setFinanceTermsInfo(financeTermsInfo);

            // response body setting
            FinanceTermsContentResponseBody financeTermsContentResponseBody = new FinanceTermsContentResponseBody();
            financeTermsContentResponseBody
                    .setRequestParameters(request.getFinanceTermsContentRequestBody().getRequestParameters());
            RequestResult requestResult = new RequestResult();
            requestResult.setCount((long) 1);
            requestResult.setTotalCnt((long) 1);
            financeTermsContentResponseBody.setRequestResult(requestResult);

            // resp code/msg setting
            Resp resp = new Resp();
            resp.setRespCode(Integer.toString(ApiExceptionCode.SUCCESS.getCode()));
            resp.setRespMsg("success");

            termsContentResponse.setFinanceTermsContentResponseBody(financeTermsContentResponseBody);
            termsContentResponse.setResp(resp);

            return termsContentResponse;
        } catch (ApiException aex) {
            throw aex;
        } catch (Exception ex) {
            throw new ApiException(ApiExceptionCode.UNKNOWN_ERROR, ex);
        }
    }

    @Override
    public TermsSearchResponse selectTerms(TermsSearchRequest request) {
        try {      

            MemberResponse memberResponse = getMemberInfo(request.getCommonHeader().getCi());
            
            SptCustomerServiceTermsFileProfileVO searchServiceTermsFileProfileVO = new SptCustomerServiceTermsFileProfileVO();
            searchServiceTermsFileProfileVO.setCustomerRegNo(memberResponse.getCustomerRegNo());
            searchServiceTermsFileProfileVO.setTermsFileType(CodeConstants.TERMS_FILE_TYPE_002);        // 원본+서명 파일 (JWS signature)
            SptCustomerServiceTermsFileProfileVO termsFile = cmmTsaService.selectSptCustomerServiceTermsFileProfile(searchServiceTermsFileProfileVO);
            
            JsonNode signData = new ObjectMapper().readTree(new String(termsFile.getTermsFileData(), "UTF8"));
            
            if(signData == null) 
                throw new ApiException(ApiExceptionCode.NOT_FOUND_TERMS);
            
            String plainData = new String(Base64Utils.decodeFromUrlSafeString(signData.get("payload").asText()), "UTF8");

            if(plainData == null) 
                throw new ApiException(ApiExceptionCode.NOT_FOUND_TERMS);

            TermsInfoDTO termsInfoDto = termsDao.selectLastestCustomerServiceTermsInfo(memberResponse.getCustomerRegNo(), request.getPartner().getComId());
            
            if(termsInfoDto == null)
                throw new ApiException(ApiExceptionCode.NOT_FOUND_TERMS);
            
            List<TermsPubCompanyVO> termsPubcompanyList = termsDao.selectCustomerServiceTermsPubcompanyInfo(memberResponse.getCustomerRegNo(), termsInfoDto.getTermsRegNo());
            
            TermsInfo termsInfo = new TermsInfo();
            termsInfo.setText(plainData);
            termsInfo.setTermsDate(termsInfoDto.getTermsDate());
            termsInfo.setTermsStartDate(termsInfoDto.getTermsStartDate());
            termsInfo.setTermsEndDate(termsInfoDto.getTermsEndDate());
            termsInfo.setVersion(termsInfoDto.getVersion());
            
            termsInfo.setCustomerBirthDay(memberResponse.getCustomerBirthDay());
            termsInfo.setCustomerEmail(memberResponse.getCustomerEmail());
            termsInfo.setCustomerUserNm(memberResponse.getCustomerNameKor());
            termsInfo.setCustomerPhone(memberResponse.getCustomerPhone());
            
            List<String> companyList = new ArrayList<>();
            for(TermsPubCompanyVO pubcompany : termsPubcompanyList) {
                companyList.add(pubcompany.getPubCompanyCodeId());
            }
            
            termsInfo.setCompanyList(companyList);
            
            // response body setting
            FinanceTermsSearchResponseBody financeTermsSearchResponseBody = new FinanceTermsSearchResponseBody();
            RequestResult requestResult = new RequestResult();
            requestResult.setCount((long) 1);
            requestResult.setTotalCnt((long) 1);
            financeTermsSearchResponseBody.setRequestResult(requestResult);

            // resp code/msg setting
            Resp resp = new Resp();
            resp.setRespCode(Integer.toString(ApiExceptionCode.SUCCESS.getCode()));
            resp.setRespMsg("success");

            TermsSearchResponse termsSearchResponse = new TermsSearchResponse();
            termsSearchResponse.setFinanceTermsInfo(termsInfo);
            termsSearchResponse.setFinanceTermsSearchResponseBody(financeTermsSearchResponseBody);
            termsSearchResponse.setResp(resp);
            
            return termsSearchResponse;
        } catch (ApiException aex) {
            throw aex;
        } catch (Exception ex) {
            throw new ApiException(ApiExceptionCode.UNKNOWN_ERROR, ex);
        }
    }
    
    @Override
    public TermsTransmitResponse transmitTerms(TermsTransmitRequest request) {
        try {
            // STEP #1 : jws signature 검증
            // G/W에서 수행
            // TODO : 한번더 검증??
            String signData = new ObjectMapper().writeValueAsString(request.getJws());
            String plainData = new String(Base64Utils.decodeFromUrlSafeString(request.getJws().getPayload()), "UTF8");
            String jwsHeaderStr = new String(Base64Utils.decodeFromUrlSafeString(request.getJws().getProtected0()), "UTF8");
            String x509Str = new ObjectMapper().readTree(jwsHeaderStr).get("x5c").asText();
            

            if(x509Str == null)
                throw new ApiException(ApiExceptionCode.CERTIFICATE_FAIL);
            
            X509Certificate cert = X509Certificate.getInstance(Base64Utils.decodeFromString(x509Str));

            if(cert == null)
                throw new ApiException(ApiExceptionCode.CERTIFICATE_FAIL);


            // STEP #2 : 동의서 내용 생성
            // 수신 데이터
            String receivedText = request.getFinanceTermsTransmitRequestBody().getRequestParameters().getText();

            // 서버 생성 데이터
            TermsDTO termsDto = termsDao.selectTerms(CommonCodeConstants.TERMS_TYPE_FINANCE); // 금융정보제공동의서
            if (termsDto == null) {
                throw new ApiException(ApiExceptionCode.NOT_FOUND_TERMS);
            }

            TermsInfo generatedFinanceTermsInfo = new TermsInfo();

            // TODO : 동의서 날짜를 세팅??
            generatedFinanceTermsInfo.setCustomerBirthDay(
                    request.getFinanceTermsTransmitRequestBody().getRequestParameters().getCustomerBirthDay());
            generatedFinanceTermsInfo.setCustomerEmail(
                    request.getFinanceTermsTransmitRequestBody().getRequestParameters().getCustomerEmail());
            generatedFinanceTermsInfo.setCustomerPhone(
                    request.getFinanceTermsTransmitRequestBody().getRequestParameters().getCustomerPhone());
            generatedFinanceTermsInfo.setCustomerUserNm(
                    request.getFinanceTermsTransmitRequestBody().getRequestParameters().getCustomerUserNm());
            generatedFinanceTermsInfo.setCompanyList(
                    request.getFinanceTermsTransmitRequestBody().getRequestParameters().getCompanyList());

            generatedFinanceTermsInfo.setVersion(termsDto.getTermsContentVer());

            String generatedText = makeTermContent(request.getPartner(), generatedFinanceTermsInfo, termsDto.getTermsContent());
            
            // line ending normalize
            String generatedTextNormalize = generatedText.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
            String plainDataNormalize = plainData.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");

            log.debug("generatedTextNormalize : {}", generatedTextNormalize);
            log.debug("generatedTextNormalize : {}", plainDataNormalize);
            
            // STEP #3 : 동의서 내용 검증
            if (!generatedTextNormalize.equals(plainDataNormalize))
                throw new ApiException(ApiExceptionCode.NOT_MATCH_TEXT);

            // STEP #4 : 사용자 정보 저장 (rest)
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<String> requestEntity = null;
            String memberRequest = ""
                    + "{"
                    + "\"userCi\" : \"" + request.getCommonHeader().getCi() + "\","
                    + "\"userDn\" : \"" + request.getCommonHeader().getCertDn() + "\","
                    + "\"customerBirthDay\" : \"" + request.getFinanceTermsTransmitRequestBody().getRequestParameters().getCustomerBirthDay() + "\","
                    + "\"customerEmail\" : \"" + request.getFinanceTermsTransmitRequestBody().getRequestParameters().getCustomerEmail() + "\","
                    + "\"customerPhone\" : \"" + request.getFinanceTermsTransmitRequestBody().getRequestParameters().getCustomerPhone() + "\","
                    + "\"customerUserNm\" : \"" + request.getFinanceTermsTransmitRequestBody().getRequestParameters().getCustomerUserNm() + "\""
                    + "}";
            requestEntity = new HttpEntity<String>(memberRequest, httpHeaders);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setErrorHandler(new ResponseErrorHandler() {
                @Override
                public boolean hasError(ClientHttpResponse response) throws IOException {
                    return false;
                }

                @Override
                public void handleError(ClientHttpResponse response) throws IOException {
                }
            });

            ResponseEntity<InternalResponse> memberPostResponse = restTemplate.exchange(
                    "http://127.0.0.1:8080/direct/common/member",
                    HttpMethod.POST,
                    requestEntity,
                    InternalResponse.class);
            
            if(!InternalResponse.SUCCESS.equals(memberPostResponse.getBody())) 
                throw new ApiException(ApiExceptionCode.MEMBER_REGSTRATION_FAIL);
            
            log.info("memberPostResponse : {}", memberPostResponse.getBody().toString());

            // STEP #5 : 사용자 정보 fetch (rest)
            MemberResponse memberResponse = getMemberInfo(request.getCommonHeader().getCi());

            // STEP #6 : 동의서 / APP 저장
            TermsVO termsVO = new TermsVO();
            termsVO.setCustomerId(memberResponse.getCustomerId());
            termsVO.setCustomerRegNo(memberResponse.getCustomerRegNo());
            termsVO.setTermsCreatedYn("N");
            termsVO.setCustomerSignData(Base64Utils.encodeToString(signData.getBytes("UTF8")));
            termsVO.setCustomerSignDn(cert.getSubjectDN().toString());
            termsVO.setReqHtml(plainData);
            termsVO.setTermsAuthType("G032001");    // 동의서 타입 : 전자서명
            termsVO.setAppId(request.getPartner().getSvrId());      // app id
            termsVO.setSubCompanyCodeId(request.getPartner().getComId());  // 정보를 제공받는 기업 : 핀테크 업체
            termsVO.setPubCompanyList(request.getFinanceTermsTransmitRequestBody().getRequestParameters().getCompanyList());    // 정보 제공 기업 목록 : 금투사
            
            log.info("termsVO : {}", termsVO);
            
            // 가-동의서 저장
            appService.createCommonTerms(termsVO);

            // app 정보 조회
            AppVO searchAppVO = new AppVO();
            searchAppVO.setCustomerRegNo(memberResponse.getCustomerRegNo());
            searchAppVO.setAppId(request.getPartner().getSvrId());
            AppVO appVO = appDAO.selectAppDetail(searchAppVO);
            
            if(OppfStringUtil.isEmpty(appVO.getServiceRegNo())) {
                // 사용자 최초 서비스 등록 
                // 나-동의서 저장
                appService.createAppTerms(termsVO, false);
            } else {
                // 기존에 서비스 등록건이 존재하는 경우
                // 나-동의서 저장
                appService.createAppTerms(termsVO, true);
            }
            
            // response body setting
            FinanceTermsTransmitResponseBody financeTermsTransmitResponseBody = new FinanceTermsTransmitResponseBody();
            financeTermsTransmitResponseBody
                    .setRequestParameters(request.getFinanceTermsTransmitRequestBody().getRequestParameters());

            // resp code/msg setting
            Resp resp = new Resp();
            resp.setRespCode(Integer.toString(ApiExceptionCode.SUCCESS.getCode()));
            resp.setRespMsg("success");
            
            TermsTransmitResponse response = new TermsTransmitResponse();
            response.setFinanaceTermsTransmitResponseBody(financeTermsTransmitResponseBody);
            response.setResp(resp);
            
            return response;
        } catch (ApiException aex) {
            throw aex;
        } catch (Exception ex) {
            throw new ApiException(ApiExceptionCode.UNKNOWN_ERROR, ex);
        }
    }

    @Override
    public TermsRevokeResponse revokeTerms(TermsRevokeRequest request) {
        try {
            
            MemberResponse memberResponse = getMemberInfo(request.getCommonHeader().getCi());
            
            AppVO appVO = new AppVO();
            appVO.setCustomerRegNo(memberResponse.getCustomerRegNo());
            appVO.setAppId(request.getPartner().getSvrId());
            
            appService.removeApp(appVO);
            
            // resp code/msg setting
            Resp resp = new Resp();
            resp.setRespCode(Integer.toString(ApiExceptionCode.SUCCESS.getCode()));
            resp.setRespMsg("success");
            
            TermsRevokeResponse termsRevokeResponse = new TermsRevokeResponse();
            termsRevokeResponse.setResp(resp);
            return termsRevokeResponse;
        } catch (ApiException aex) {
            throw aex;
        } catch (Exception ex) {
            throw new ApiException(ApiExceptionCode.UNKNOWN_ERROR, ex);
        }
    }
    


    @Override
    public AgreementContentResponse selectAgreementContent(String termCode, String companyCode) {

        try {
            TermsDTO termsDto = null; 
            if(CommonCodeConstants.TERMS_TYPE_COMPANY_SERVICE.equals(termCode)) {
                // 기업 동의 - 서비스 이용약관
                termsDto = termsDao.selectTerms(termCode, companyCode);
            } else {
                termsDto = termsDao.selectTerms(termCode);
            }
            
            if(termsDto == null)
                throw new ApiException(ApiExceptionCode.NOT_FOUND_TERMS);
            
            AgreementInfo agreementInfo = new AgreementInfo();
            agreementInfo.setText(termsDto.getTermsContent());
            agreementInfo.setVersion(termsDto.getTermsContentVer());
            
            // resp code/msg setting
            Resp resp = new Resp();
            resp.setRespCode(Integer.toString(ApiExceptionCode.SUCCESS.getCode()));
            resp.setRespMsg("success");

            AgreementContentResponse agreementContentResponse = new AgreementContentResponse();
            agreementContentResponse.setAgreementInfo(agreementInfo);
            agreementContentResponse.setResp(resp); 
           
            return agreementContentResponse;
        } catch (ApiException aex) {
            throw aex;
        } catch (Exception ex) {
            throw new ApiException(ApiExceptionCode.UNKNOWN_ERROR, ex);
        }
    }
    

    private String makeTermContent(Partner partner, TermsInfo terms, String termContent) throws Exception {
        StringSubstitutor ss = new StringSubstitutor();

        HashMap<String, String> map = new HashMap<>();
        map.put("customerBirthDay", terms.getCustomerBirthDay());
        map.put("customerEmail", terms.getCustomerEmail());
        map.put("customerPhone", terms.getCustomerPhone());
        map.put("customerUserNm", terms.getCustomerUserNm());
        
        // 코스콤 기업 정보 (가-동의서)
        CompanyDTO koscom = companyDao.selectCompany("00995");
        // 핀테크 기업 정보 (나-동의서)
        CompanyDTO fintech = companyDao.selectCompany(partner.getComId());
        
        map.put("companyName", koscom.getCompanyNameKorAlias() + "," + fintech.getCompanyNameKorAlias());

        if (terms.getCompanyList().size() == 0) {
            throw new ApiException(ApiExceptionCode.INVALID_COMPANY_LIST);
        }

        List<CompanyDTO> companyList = companyDao.selectFinanceCompanyList(terms.getCompanyList());
        String companyListStr = "";
        for (CompanyDTO company : companyList) {
            if ("".equals(companyListStr)) {
                companyListStr += company.getCompanyNameKorAlias();
            } else {
                companyListStr += "," + company.getCompanyNameKorAlias();
            }
        }
        map.put("companyList", companyListStr);

        Date currentDate = Calendar.getInstance(Locale.KOREA).getTime();
        String currentDateStr = DateFormatUtils.format(currentDate, "yyyyMMdd");
        Date expireDate = DateUtils.addDays(DateUtils.addYears(currentDate, 1), -1);
        String expireDateStr = DateFormatUtils.format(expireDate, "yyyyMMdd");

        if (terms.getTermsDate() == null) {
            terms.setTermsDate(currentDateStr);
        }

        if (terms.getTermsStartDate() == null) {
            terms.setTermsStartDate(currentDateStr);
        }

        if (terms.getTermsEndDate() == null) {
            terms.setTermsEndDate(expireDateStr);
        }

        if (!isValidDate(terms.getTermsDate(), "yyyyMMdd")) {
            throw new ApiException(ApiExceptionCode.INVALID_DATE);
        }

        String termsDate = terms.getTermsDate();
        String termsDateStr = termsDate.substring(0, 4) + "년 " + termsDate.substring(4, 6) + "월 "
                + termsDate.substring(6, 8) + "일";
        map.put("termsDate", termsDateStr);

        if (!isValidDate(terms.getTermsStartDate(), "yyyyMMdd")) {
            throw new ApiException(ApiExceptionCode.INVALID_DATE);
        }

        String termsStartDate = terms.getTermsStartDate();
        String termsStartDateStr = termsStartDate.substring(0, 4) + "년 " + termsStartDate.substring(4, 6) + "월 "
                + termsStartDate.substring(6, 8) + "일";
        map.put("termsStartDate", termsStartDateStr);

        if (!isValidDate(terms.getTermsEndDate(), "yyyyMMdd")) {
            throw new ApiException(ApiExceptionCode.INVALID_DATE);
        }

        String termsEndDate = terms.getTermsEndDate();
        String termsEndDateStr = termsEndDate.substring(0, 4) + "년 " + termsEndDate.substring(4, 6) + "월 "
                + termsEndDate.substring(6, 8) + "일";
        map.put("termsEndDate", termsEndDateStr);

        return ss.replace(termContent, map);
    }

    private boolean isValidDate(String dateStr, String pattern) {
        try {
            DateUtils.parseDate(dateStr, new String[] { pattern });
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    private MemberResponse getMemberInfo(String ci) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
            }
        });
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, httpHeaders);
        
        ResponseEntity<MemberResponse> memberGetResponse = restTemplate.exchange(
                "http://127.0.0.1:8080/direct/common/member/" + ci,
                HttpMethod.GET,
                requestEntity,
                MemberResponse.class);
        
        if(memberGetResponse == null || !memberGetResponse.getStatusCode().equals(HttpStatus.OK)) 
            throw new ApiException(ApiExceptionCode.MEMBER_FETCH_FAIL);

        log.info("memberGetResponse : {}", memberGetResponse.getBody().toString());
        MemberResponse memberResponse = memberGetResponse.getBody();
        return memberResponse;
    }

}


