package kr.co.koscom.oppf.cmm.service.impl;

import kr.co.koscom.oppf.apt.sptUsr.service.SptUserManageVO;
import kr.co.koscom.oppf.cmm.IntegratedAccount.service.CmmSIFInternalService;
import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenService;
import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenVO;
import kr.co.koscom.oppf.cmm.service.CmmEmailSendService;
import kr.co.koscom.oppf.cmm.service.CmmEmailSendVO;
import kr.co.koscom.oppf.cmm.service.CmmMemberService;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.myp.asumAcnt.service.AsumAcntVO;
import kr.co.koscom.oppf.spt.myp.asumAcnt.service.impl.AsumAcntDAO;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplService;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplVO;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author lee
 * @date 2017-04-10
 */
@Slf4j
@Service("CmmMemberService")
public class CmmMemberServiceImpl implements CmmMemberService {

    @Resource(name = "CmmCronScheduleDAO")
    private CmmCronScheduleDAO cmmCronScheduleDAO;
    @Resource(name = "AsumAcntDAO")
    private AsumAcntDAO asumAcntDAO;
    @Resource(name = "ComOauthTokenService")
    private ComOauthTokenService comOauthTokenService;
    @Resource(name = "CmmSIFInternalService")
    private CmmSIFInternalService cmmSIFInternalService;
    @Resource(name = "MypSvcApplService")
    private MypSvcApplService mypSvcApplService;
    @Resource(name = "CmmEmailSendService")
    private CmmEmailSendService cmmEmailSendService;

    /**
     * @throws : Exception
     * @Method Name        : deleteMemberProcess
     * @Method description : 회원탍퇴시 연결된 가상계좌, 서비스, 정보제공 동의서 폐기
     */
    @Override
    @Transactional
    public void deleteMemberProcess() throws Exception {
        //탈퇴 회원 테이블에 탈퇴 미처리 회원 리스트
        List<SptUserManageVO> result = cmmCronScheduleDAO.selectSptUserDeleteList();
        log.info("delete member List cnt : " + result.size());
        int deleteMemberCnt = 0;
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

            String customerRegNo = userManageVO.getCustomerRegNo();
            SptUserManageVO verifyInfo = cmmCronScheduleDAO.selectSptCustomerVerifyProfile(userManageVO);
            //2. 사용자 CI 정보가 존재하는 회원의 가상계좌 발급 정보 삭제 처리
            if(null != verifyInfo){

                //사용자 정보
                String requestUserId = userManageVO.getCustomerId();
                String requestUniqueId = customerRegNo;
                String customerCi = verifyInfo.getCustomerVerify();

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
                String authorizationUrl = null;

                AsumAcntVO asumAcntVO = new AsumAcntVO();
                asumAcntVO.setCustomerRegNo(customerRegNo);
                //발급된 가상계좌 정보 리스트
                List<AsumAcntVO> asumAcntVOList = asumAcntDAO.selectSptDelCustAccList(asumAcntVO);
                int deleteMemberAccountCnt = 0;
                log.info("delete member Account List cnt : " + asumAcntVOList.size());
                //가상계좌발급 리스트가 존재하는 경우 가상계좌 발급 삭제
                if(asumAcntVOList.size() > 0){
                    for(AsumAcntVO account : asumAcntVOList){
                        //기업명 별칭
                        String companyNameEngAlias = account.getCompanyCodeName();
                        //기업코드
                        String companyCode = account.getCompanyCodeId();
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

                                if("SUCCESS".equals(status) && !("FAILURE".equals(accountStatus) || "FAILED".equals(accountStatus))){
                                    MypSvcApplVO dataVO = new MypSvcApplVO();
                                    dataVO.setCustomerRegNo(requestUniqueId);
                                    dataVO.setCompanyCodeId(companyCode);
                                    dataVO.setCustomerRealaccountNo(account.getCustomerRealaccountNo());

                                    //가상계좌 폐기에 따른 서비스 or 정보제공동의 정보 수정
                                    mypSvcApplService.deleteCustomerAccountProc(dataVO);
                                    deleteAccountCnt++;
                                    deleteMemberAccountCnt++;
                                }
                            }
                        }
                    } //for
                    log.info("delete Memeber Account Cnt : " + deleteMemberAccountCnt);
                    // 가상계좌 전체 삭제가 완료되면 회원탈퇴 처리
                    if(asumAcntVOList.size() == deleteMemberAccountCnt){
                        // 회원탈퇴 처리 완료시 업데이트
                        cmmCronScheduleDAO.updateSptUserWithdrawal(userManageVO);
                        //회원 원본 Verify Hist 초기화
                        cmmCronScheduleDAO.updateMemberVerifyInfoHist(userManageVO);
                        //회원 원본 Verify 초기화
                        cmmCronScheduleDAO.updateMemberVerifyInfo(userManageVO);
                        deleteMemberCnt++;
                    }
                }else{
                    // 회원탈퇴 처리 완료시 업데이트
                    cmmCronScheduleDAO.updateSptUserWithdrawal(userManageVO);
                    //회원 원본 Verify Hist 초기화
                    cmmCronScheduleDAO.updateMemberVerifyInfoHist(userManageVO);
                    //회원 원본 Verify 초기화
                    cmmCronScheduleDAO.updateMemberVerifyInfo(userManageVO);
                    deleteMemberCnt++;
                }
            }else{//2. 사용자 CI 정보가 미존재하는 경우 회원 탈퇴 처리
                // 회원탈퇴 처리 완료시 업데이트
                cmmCronScheduleDAO.updateSptUserWithdrawal(userManageVO);
                //회원 원본 Verify Hist 초기화
                cmmCronScheduleDAO.updateMemberVerifyInfoHist(userManageVO);
                //회원 원본 Verify 초기화
                cmmCronScheduleDAO.updateMemberVerifyInfo(userManageVO);
                deleteMemberCnt++;
            }
        }//for
        log.info("delete Memeber Cnt : " + deleteMemberCnt);
        log.info("delete Account Cnt : " + deleteAccountCnt);
    }

    /**
     * @throws : Exception
     * @Method Name        : guestMemberExpireEMailSend
     * @Method description : 비회원 인증 유효기만 만료 안내 메일 전송 3/1개월
     */
    @Override
    @Transactional
    public void guestMemberExpireEMailSend(HttpServletRequest request) throws Exception {

        //비회원 인증 (가동의서) 유효기간 만료 대상자 리스트 3개월/1개월
        List<SptUserManageVO> result = cmmCronScheduleDAO.selectGuestMemberExpireList();
        log.info("guest member List cnt : " + result.size());
        int emailSendCnt = 0;

        for(SptUserManageVO userManageVO : result){
            SptUserManageVO vo = new SptUserManageVO();
            vo.setCustomerRegNo(userManageVO.getCustomerRegNo());
            //비회원 사용중인 app 리스트 조회
            List<SptUserManageVO> useAppList = cmmCronScheduleDAO.selectGuestMemberAppList(vo);
            log.info("guest member use App List cnt : " + useAppList.size());

            StringBuffer useAppsHtml = new StringBuffer();
            int idx = 0;
            for(SptUserManageVO app : useAppList){
                if(idx > 0){
                    useAppsHtml.append(", ");
                }
                useAppsHtml.append(app.getAppName());
                useAppsHtml.append("(");
                useAppsHtml.append(app.getSubcompanyName());
                useAppsHtml.append(")");
                idx++;
            }
            String customerRegNo = userManageVO.getCustomerRegNo();
            String customerName = userManageVO.getCustomerNameKor();
            String customerEmail = userManageVO.getCustomerEmail();

            CmmEmailSendVO cmmEmailSendVO = new CmmEmailSendVO();
            cmmEmailSendVO.setEmailSendType("G016009"); //이메일 유형 - com_email_temp_info 이메일 템플릿 조회 (비회원 인증 유효기간 만료 안내)

            cmmEmailSendVO.setReceiverName(customerName); //받는 사람 이름(실제 이메일에 셋팅됩니다.)
            cmmEmailSendVO.setReceiverEmail(customerEmail); //받는 사람 이메일(실제 이메일에 셋팅되고, 이 주소로 이메일이 발송됩니다.)
            cmmEmailSendVO.setReceiverKind("G018001"); //수신자 종류 - G018001:일반사용자, G018002:기업사용자
            cmmEmailSendVO.setReceiverId(customerRegNo); //수신자 ID

            cmmEmailSendVO.setSenderKind("G017001"); //발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
            cmmEmailSendVO.setSenderId(customerRegNo); //발신자 ID

            cmmEmailSendVO.setSendId(customerRegNo); //최초 발신자 ID
            cmmEmailSendVO.setCreateId(customerRegNo); //생성자ID
            cmmEmailSendVO.setUpdateId(customerRegNo); //수정자ID

            cmmEmailSendVO.setTermsStartDate(OppfStringUtil.addMinusChar(userManageVO.getTermsStartDate()));//가동의서 시작일자
            cmmEmailSendVO.setTermsExpireDate(OppfStringUtil.addMinusChar(userManageVO.getTermsExpireDate()));//가동의서 종료일자
            cmmEmailSendVO.setUseApps(useAppsHtml.toString());//이용중인 app 목록

            try{
                //1.이메일 템플릿 조회 -> 2.이메일발송 유형 구분 -> 3.메일 발송 -> (성공시) -> 4.com_email_send_info 로그적재 관련
                CmmEmailSendVO eamilResultVO = cmmEmailSendService.cmmEmailSend(cmmEmailSendVO, request);
                if("100".equals(eamilResultVO.getMailCode())){ //데이터 연동 성공
                    log.info("################비회원 인증 유효기간 만료 email 발송 성공#######################");
                    emailSendCnt++;
                }else{
                    log.info("################비회원 인증 유효기간 만료 email 발송 실패#######################");
                    log.info("eamilResultVO.getMailCode() : {}", eamilResultVO.getMailCode());
                }
            }catch(Exception e){
                log.info("################비회원 인증 유효기간 만료 email 발송 실패#######################");
                log.info("e : {}", e);
            }
        }
        log.info("Member List Count : " + result.size());
        log.info("Email Send Count : " + emailSendCnt);
    }

    /**
     * @throws : Exception
     * @Method Name        : deleteGuestMember
     * @Method description : 비회원 가동의서 만료 후 자동 탈퇴 처리
     */
    @Override
    @Transactional
    public void deleteGuestMember() throws Exception {

        //비회원 인증 (가동의서) 유효기간 종료로 인한 자동 탈퇴 리스트
        List<SptUserManageVO> result = cmmCronScheduleDAO.selectDeleteGuestMemberList();
        log.info("guest member delete List cnt : " + result.size());
        int deleteCnt = 0;

        for(SptUserManageVO userManageVO : result){
            //비회원 탈퇴 처리
            cmmCronScheduleDAO.updateGuestMemberInfo(userManageVO);
            //비회원 탈퇴 처리 히스토리 인서트
            cmmCronScheduleDAO.insertGuestMemberInfoHistory(userManageVO);
            //비회원 탈퇴 처리를 위한 데이터 이관
            cmmCronScheduleDAO.insertLeaveGuestMemberInfo(userManageVO);

            //CI 검증 데이터 이관
            userManageVO.setCustomerVerifyType("G007001");
            cmmCronScheduleDAO.insertLeaveGuestMemberVerifyInfo(userManageVO);
            // DN(공인인증) 데이터 이관
            userManageVO.setCustomerVerifyType("G007002");
            cmmCronScheduleDAO.insertLeaveGuestMemberVerifyInfo(userManageVO);

            //삭제 카운트
            deleteCnt++;
        }
        log.info("Delete Guest Member Count : " + deleteCnt);
    }

    /**
     * @throws : Exception
     * @Method Name        : deleteMember
     * @Method description : 탈퇴 회원 물리 데이터 삭제
     */
    @Override
    @Transactional
    public void deleteMember() throws Exception {

        //회원 탈퇴 후 3개월 이후 물리 데이터 삭제 대상 리스트
        List<SptUserManageVO> result = cmmCronScheduleDAO.selectDeleteMemberList();
        log.info("member delete List cnt : " + result.size());
        int deleteCnt = 0;

        for(SptUserManageVO userManageVO : result){
            //회원 Verify Hist 삭제
            cmmCronScheduleDAO.deleteMemberVerifyInfoHist(userManageVO);
            //회원 Verify 삭제
            cmmCronScheduleDAO.deleteMemberVerifyInfo(userManageVO);
            //회원 원본 Verify Hist 초기화
            cmmCronScheduleDAO.updateMemberVerifyInfoHist(userManageVO);
            //회원 원본 Verify 초기화
            cmmCronScheduleDAO.updateMemberVerifyInfo(userManageVO);
            //회원 Hist 삭제
            cmmCronScheduleDAO.deleteMemberInfoHist(userManageVO);
            //회원 삭제
            cmmCronScheduleDAO.deleteMemberInfo(userManageVO);
            //회원 원본 Hist 초기화
            cmmCronScheduleDAO.updateMemberInfoHist(userManageVO);
            //회원 원본 초기화
            cmmCronScheduleDAO.updateMemberInfo(userManageVO);

            deleteCnt++;
        }
        log.info("Delete Member Count : " + deleteCnt);
    }
}
