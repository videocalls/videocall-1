package kr.co.koscom.oppf.cmm.service.impl;

import kr.co.koscom.oppf.apt.sptUsr.service.SptUserManageVO;
import kr.co.koscom.oppf.apt.sptUsr.service.impl.SptUserManageDAO;
import kr.co.koscom.oppf.cmm.IntegratedAccount.service.CmmSIFInternalService;
import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenService;
import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenVO;
import kr.co.koscom.oppf.cmm.service.CmmMemberDeleteService;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.spt.myp.asumAcnt.service.AsumAcntVO;
import kr.co.koscom.oppf.spt.myp.asumAcnt.service.impl.AsumAcntDAO;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplService;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author lee
 * @date 2017-04-10
 */
@Service("CmmMemberDeleteService")
public class CmmMemberDeleteServiceImpl implements CmmMemberDeleteService {

    @Resource(name = "SptUserManageDAO")
    private SptUserManageDAO sptUserManageDAO;
    @Resource(name = "AsumAcntDAO")
    private AsumAcntDAO asumAcntDAO;
    @Resource(name = "MbrRegService")
    private MbrRegService mbrRegService;
    @Resource(name = "ComOauthTokenService")
    private ComOauthTokenService comOauthTokenService;
    @Resource(name = "CmmSIFInternalService")
    private CmmSIFInternalService cmmSIFInternalService;
    @Resource(name = "MypSvcApplService")
    private MypSvcApplService mypSvcApplService;

    private static final Logger log = Logger.getLogger(CmmMemberDeleteServiceImpl.class);
    /**
     * @throws : Exception
     * @Method Name        : deleteMemberProcess
     * @Method description : 회원탍퇴시 연결된 가상계좌, 서비스, 정보제공 동의서 폐기
     */
    @Override
    public void deleteMemberProcess() throws Exception {
        //1. 탈퇴 처리 회원중 가상계좌 정보가 존재하는 회원 리스트
        List<SptUserManageVO> result = sptUserManageDAO.selectSptUserDeleteList();
        log.info("delete member List cnt : " + result.size());
        int deleteMemeberCnt = 0;
        int deleteAccountCnt = 0;

        //G/W 정보
        String apiKey = "";
        String Authorization = "";

        if(result.size() > 0){
            ComOauthTokenVO pComOauthTokenVO = new ComOauthTokenVO();
            ComOauthTokenVO rsComOauthTokenVO = comOauthTokenService.selectComOauthTokenInfo(pComOauthTokenVO);
            apiKey = OppfProperties.getProperty("Globals.oauth.token.clientId");
            Authorization = "Bearer " + rsComOauthTokenVO.getAccessToken();
        }

        for(SptUserManageVO userManageVO : result){

            //if(!"jinaboni".equals(userManageVO.getCustomerId())) continue;

            String customerRegNo = userManageVO.getCustomerRegNo();
            MbrRegVO pMbrRegVO = new MbrRegVO();
            pMbrRegVO.setCustomerRegNo(customerRegNo);
            pMbrRegVO.setCustomerVerifyType("G007001");
            List<MbrRegVO> rsVerifyList = mbrRegService.selectSptCustomerVerifyProfileList(pMbrRegVO);
            //2. 사용자 CI 정보가 존재하는 회원의 가상계좌 발급 정보 삭제 처리
            if(rsVerifyList.size() > 0){

                //사용자 정보
                String requestUserId = userManageVO.getCustomerId();
                String requestUniqueId = customerRegNo;
                String customerCi = rsVerifyList.get(0).getCustomerVerify();

                // generate request header
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
                httpHeaders.add("apiKey", apiKey);
                httpHeaders.add("Authorization", Authorization);
                httpHeaders.add("x-credential-userId", requestUserId);
                httpHeaders.add("x-api-requestId", requestUniqueId);
                httpHeaders.add("x-credential-ci", customerCi);
                httpHeaders.add("x-credential-dn", "");

                String exconn = OppfProperties.getProperty("Globals.domain.exconn");
                //개발/샌드박스(2차통합)/상용 모드
                String devMode = OppfProperties.getProperty("Globals.integrated.account.mode");
                String authorizationUrl = null;

                AsumAcntVO asumAcntVO = new AsumAcntVO();
                asumAcntVO.setCustomerRegNo(customerRegNo);
                //발급된 가상계좌 정보 리스트
                List<AsumAcntVO> asumAcntVOList = asumAcntDAO.selectSptDelCustAccList(asumAcntVO);
                int deleteMemeberAccountCnt = 0;
                log.info("delete member Account List cnt : " + asumAcntVOList.size());
                //가상계좌발급 리스트가 존재하는 경우 가상계좌 발급 삭제
                if(asumAcntVOList.size() > 0){
                    for(AsumAcntVO account : asumAcntVOList){
                        //기업명 별칭
                        String companyNameEngAlias = account.getCompanyCodeName();
                        //기업코드
                        String companyCode = account.getCompanyCodeId();

                        //샌드박스(2차통합)인경우만 외부연계서버2로 분기처리
                        //authorizationUrl = devMode.equals("snd") ? exconn+"/v2/common/account/vtnumber/delete/"+companyNameEngAlias : exconn+"/v1/common/account/vtnumber/delete/"+companyNameEngAlias;
                        authorizationUrl = exconn+"/v1/common/account/vtnumber/delete/"+companyNameEngAlias;

                        StringBuffer accountPayload = new StringBuffer();
                        accountPayload.append("{\"account\":[ {\"realAccNo\" : \"");
                        accountPayload.append(account.getCustomerRealaccountNo());
                        accountPayload.append("\", \"realAccType\" : \"");
                        accountPayload.append(account.getCustomerRealaccountTypeNmEng());
                        accountPayload.append("\", \"vtAccNo\" : \"");
                        accountPayload.append(account.getCustomerVtaccountNo());
                        accountPayload.append("\", \"vtAccAlias\" : \"");
                        accountPayload.append(account.getCustomerVtaccountAlias());
                        accountPayload.append("\" }],\"trCode\":\"DIS\"}");

                        ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(authorizationUrl, httpHeaders, HttpMethod.POST, accountPayload.toString());
                        // handle response
                        String responseBody = responseEntity.getBody();

                        //결과 body 가져오기
                        JSONObject jsonBody = new JSONObject(responseBody);
                        String statusCode = Integer.toString(responseEntity.getStatusCode().value());

                        if("200".equals(statusCode) && jsonBody != null){
                            JSONObject json = jsonBody.getJSONObject("result");
                            JSONArray jsonAccount = jsonBody.getJSONArray("account");
                            if(json != null && jsonAccount != null){
                                String status = (String) json.get("status");
                                String message = (String) json.get("message");

                                JSONObject jsonAccountObj = (JSONObject) jsonAccount.get(0);
                                String accountStatus = (String) jsonAccountObj.get("status");

                                log.info("############# 가상계좌 폐기 처리 상태 ################");
                                log.info("status="+status);
                                log.info("message="+message);
                                log.info("accountStatus="+accountStatus);
                                log.info("############# 가상계좌 폐기 data #################");
                                log.info("customerRegNo="+requestUniqueId);
                                log.info("companyCodeId="+companyCode);
                                log.info("customerRealaccountNo="+account.getCustomerRealaccountNo());
                                log.info("##############################################");

                                //성공 시에만 처리
                                //if("SUCCESS".equals(status) && "OK".equals(message)){
                                if("SUCCESS".equals(status) && !("FAILURE".equals(accountStatus) || "FAILED".equals(accountStatus))){
                                    MypSvcApplVO dataVO = new MypSvcApplVO();
                                    dataVO.setCustomerRegNo(requestUniqueId);
                                    dataVO.setCompanyCodeId(companyCode);
                                    dataVO.setCustomerRealaccountNo(account.getCustomerRealaccountNo());

                                    //가상계좌 폐기에 따른 서비스 or 정보제공동의 정보 수정
                                    mypSvcApplService.deleteCustomerAccountProc(dataVO);
                                    deleteAccountCnt++;
                                    deleteMemeberAccountCnt++;
                                }
                            }
                        }
                    } //for
                    log.info("delete Memeber Account Cnt : " + deleteMemeberAccountCnt);

                }else{
                    // 회원탈퇴 처리 완료시 업데이트
                    deleteMemeberCnt++;
                }
            }else{//2. 사용자 CI 정보가 미존재하는 경우 회원 탈퇴 처리
                // 회원탈퇴 처리 완료시 업데이트
                deleteMemeberCnt++;
            }
        }//for
        log.info("delete Memeber Cnt : " + deleteMemeberCnt);
        log.info("delete Account Cnt : " + deleteAccountCnt);
    }
}
