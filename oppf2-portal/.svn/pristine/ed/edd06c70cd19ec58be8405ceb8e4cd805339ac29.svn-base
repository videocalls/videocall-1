package kr.co.koscom.oppf.spt.usr.svcAppl.service.impl;

import kr.co.koscom.oppf.cmm.IntegratedAccount.service.CmmSIFInternalService;
import kr.co.koscom.oppf.cmm.intro.service.IntroVO;
import kr.co.koscom.oppf.cmm.intro.service.impl.IntroDAO;
import kr.co.koscom.oppf.cmm.service.CmmEmailSendService;
import kr.co.koscom.oppf.cmm.service.CmmEmailSendVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.tsa.service.CmmTsaResVO;
import kr.co.koscom.oppf.cmm.tsa.service.CmmTsaService;
import kr.co.koscom.oppf.cmm.tsa.service.CmmTsaVO;
import kr.co.koscom.oppf.cmm.tsa.service.impl.CmmTsaDAO;
import kr.co.koscom.oppf.cmm.util.CodeConstants;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * AppServiceImpl
 * <p>
 * Created by chungyeol.kim on 2017-06-15.
 */
@Slf4j
@Service("AppService")
public class AppServiceImpl implements AppService {

    @Resource(name = "AppDAO")
    private AppDAO appDAO;

    @Resource(name = "CmmTsaDAO")
    private CmmTsaDAO cmmTsaDAO;

    @Resource(name = "IntroDAO")
    private IntroDAO introDAO;

    @Resource(name = "SvcApplDAO")
    private SvcApplDAO svcApplDAO;

    @Resource(name = "CmmTsaService")
    private CmmTsaService cmmTsaService;

    @Resource(name = "CmmSIFInternalService")
    private CmmSIFInternalService cmmSIFInternalService;

    @Resource(name = "CmmEmailSendService")
    private CmmEmailSendService cmmEmailSendService;

    @Override
    public List<AppVO> getAppList(AppVO appVO) {
        return appDAO.selectAppList(appVO);
    }

    @Override
    public int getAppListTotalCount(AppVO appVO) {
        return appDAO.selectAppListTotalCount(appVO);
    }

    @Override
    public AppVO getAppDetail(AppVO appVO) {
        AppVO appDetail = appDAO.selectAppDetail(appVO);
        appDetail.setTermsRegNo(OppfStringUtil.base64Incoding(appDetail.getTermsRegNo()));
        return appDetail;
    }

    @Override
    public List<IntroVO> getAppPubCompanyList(AppVO appVO) throws Exception {
        IntroVO introVO = new IntroVO();
        List<String> appIdList = new ArrayList<>();
        appIdList.add(appVO.getAppId());
        introVO.setSearchAppId(appIdList);
        return introDAO.selectIntroFintechAppPubcompanyList(introVO);
    }

    @Override
    public List<AppAccountVO> getAppAccountList(AppVO appVO) {
        return appDAO.selectAppAccountList(appVO);
    }

    @Override
    public List<AppAccountVO> getAccountList(AppVO appVO) {
        return appDAO.selectAccountList(appVO);
    }

    @Override
    @Transactional
    public int removeApp(AppVO appVO) {

        int result = -1;

        // 폐기될 동의서 목록을 취득
        AppVO deleteTargetTerms = appDAO.selectAppDeleteTargetTerms(appVO);

        if(deleteTargetTerms != null) {

            // 동의서에 묶여있는 앱 개수
            List<String> appCount = appDAO.selectAppCountForTerms(deleteTargetTerms);

            // 앱 삭제 처리
            result = appDAO.deleteServiceProfile(deleteTargetTerms);
            result = appDAO.insertServiceProfileHist(deleteTargetTerms);

            AppAccountVO appAccountVO = new AppAccountVO();
            appAccountVO.setCustomerRegNo   (deleteTargetTerms.getCustomerRegNo()   );
            appAccountVO.setServiceRegNo    (deleteTargetTerms.getServiceRegNo()    );
            result = appDAO.deleteCustomerServiceAccountProfileAll(appAccountVO);
            
            // 약관등록번호에 묶인 App 이 한개인 경우는 정보제공동의 폐기하지 않는다.
            if(appCount != null && appCount.size() == 1) {
                // 정보제공동의서 폐기 처리
                result = appDAO.deleteServiceTermsProfile(deleteTargetTerms);
                result = appDAO.insertServiceTermsProfileHist(deleteTargetTerms);
                // 연계서버 API 연동
                cmmSIFInternalService.sendServiceTerms(deleteTargetTerms.getCustomerId(), deleteTargetTerms.getTermsRegNo());
            }

        }

        return result;
    }

    @Override
    public int checkedCommonTerms(AppVO appVO) {

        int checkedCommonTerms = appDAO.checkedCommonTerms(appVO);
        int checkedVerify = appDAO.checkedCustomerVerify(appVO);

        if(checkedCommonTerms < 1 && checkedVerify < 1) {
            return -1;
        } else if(checkedCommonTerms < 1) {
            return 0;
        }

        return 1;
    }

    @Override
    public TermsVO checkedReSyncCommonTerms(AppVO appVO) {
        return appDAO.checkedReSyncCommonTerms(appVO);
    }

    @Override
    public Map<String, Object> getCommonTermsInfo(AppVO appVO) {

        TermsVO commonTerms;
        List<AppAccountVO> pubCompanyList;
        if(!OppfStringUtil.isEmpty(appVO.getType()) && "apt".equals(appVO.getType())) {
            TermsVO aptSearchVO = new TermsVO();
            aptSearchVO.setCustomerRegNo(appVO.getCustomerRegNo());
            commonTerms = appDAO.selectCommonTermsInfo(aptSearchVO);
            pubCompanyList = appDAO.selectBeforeCommonTermsPubCompanyList(commonTerms);
        } else {
            commonTerms = appDAO.selectBaseCommonTerms(appVO);
            pubCompanyList = appDAO.selectCommonTermsPubCompanyList();
        }
        Map<String, Object> result = new HashMap<>();
        result.put("commonTerms", commonTerms);
        result.put("pubCompanyList", pubCompanyList);

        // 이전 동의서가 있는 경우
        TermsVO searchTermsVO = new TermsVO();
        searchTermsVO.setCustomerRegNo(appVO.getCustomerRegNo());
        TermsVO beforeCommonTerms = appDAO.selectCommonTermsInfo(searchTermsVO);
        result.put("beforeCommonTerms", beforeCommonTerms);
        if(beforeCommonTerms != null) {
            List<AppAccountVO> beforePubCompanyList = appDAO.selectBeforeCommonTermsPubCompanyList(beforeCommonTerms);
            result.put("beforePubCompanyList", beforePubCompanyList);
            TermsVO checkedCommon = appDAO.checkedReSyncCommonTerms(appVO);
            if(checkedCommon != null) {
                result.put("expireDataCount", checkedCommon.getDateCount());
            }
        }
        return result;
    }

    @Override
    public Map<String,Object> removeCommonTerms(AppVO appVO) {
        Map<String, Object> rsMap = new HashMap<>();

        // 기존 동의서 데이터 취득
        TermsVO termsVO = new TermsVO();
        termsVO.setCustomerRegNo(appVO.getCustomerRegNo());
        TermsVO commonTerms = appDAO.selectCommonTermsInfo(termsVO);

        if(commonTerms != null) {
            appDAO.deleteCustomerCommonTermsProfile(commonTerms);
            appDAO.insertCustomerCommonTermsProfileHist(commonTerms);

            appDAO.deleteCustomerCommonTermsPubCompanyProfile(commonTerms);
            TermsPubCompanyVO termsPubCompanyVO = new TermsPubCompanyVO();
            termsPubCompanyVO.setCustomerRegNo(commonTerms.getCustomerRegNo());
            termsPubCompanyVO.setTermsRegNo(commonTerms.getTermsRegNo());
            appDAO.insertCustomerCommonTermsPubCompanyProfileHist(termsPubCompanyVO);
        }

        rsMap.put("rsCd", "00");
        rsMap.put("rsCdMsg", "동의서가 정상적으로 폐기 되었습니다.");
        return rsMap;
    }

    @Transactional
    private HashMap<String, Object> createTermsFile(TermsVO paramVO) throws Exception{
        HashMap<String, Object> rsMap = new HashMap<String, Object>();
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddhhmmss");

        String filePathfileName = ""; //파일풀경로파일명(확장자뺀)
        String filePathOriginalfileName = ""; //원본파일풀경로파일명(확장자뺀)
        String makeHtmlContent  = ""; //만들html내용
        String makePdfContent   = ""; //만들pdf내용
        String makeTxtContent   = ""; //만들txt내용

        String oriSignData = paramVO.getCustomerSignData();	//오리지널 signData -> 암호화된 값

        //년수취득
        paramVO.setTermsPolicyYear(OppfProperties.getProperty("Globals.user.policy.password.final"));

        //1.임시폴더생성작업
        String folderPath = ""; //임시폴더경로및폴더명
        //1-1.tmpTsa폴더 생성
        folderPath = OppfProperties.getProperty("Globals.spt.webcontent.path")+"/upload/tmpTsa";
        HashMap<String, Object> rsMapForder1 = cmmTsaService.mkDirTsa(folderPath);
        //1-2.오늘날짜 폴더 생성(tmpTsa/오늘날짜)
        folderPath = OppfProperties.getProperty("Globals.spt.webcontent.path")+"/upload/tmpTsa/"+sdf.format(today);
        HashMap<String, Object> rsMapForder2 = cmmTsaService.mkDirTsa(folderPath);
        //1-3.사용자등록번호 폴더 생성(tmpTsa/오늘날짜/사용자등록번호)
        folderPath = OppfProperties.getProperty("Globals.spt.webcontent.path")+"/upload/tmpTsa/"+sdf.format(today)+"/"+paramVO.getCustomerRegNo();
        HashMap<String, Object> rsMapForder3 = cmmTsaService.mkDirTsa(folderPath);

        //2.설정작업
        //2-1.파일경로설정
        paramVO.setFilePath(folderPath);

        //2-2.파일명설정
        paramVO.setFileName(paramVO.getCustomerRegNo()+"_"+sdf2.format(today));

        //2-3.(저장경로+파일명)설정
        filePathfileName = paramVO.getFilePath()+"/"+paramVO.getFileName();

        //3.html파일생성
        //3-1.공인인증서 전자서명데이터 base64 디코딩작업
        if(!OppfStringUtil.isEmpty(paramVO.getCustomerSignData())){
            log.debug("3-1.공인인증서 전자서명데이터 base64 디코딩작업前:paramVO.getSignData() : {} ", paramVO.getCustomerSignData());
            String decodedSignData = OppfStringUtil.base64Decoding(paramVO.getCustomerSignData());
            log.debug("3-1.공인인증서 전자서명데이터 base64 디코딩작업後:decodedSignData : {} ", decodedSignData);
            paramVO.setCustomerSignData(decodedSignData);
        }
        makeHtmlContent = paramVO.getCustomerSignData();
        HashMap<String, Object> rsMap1 = cmmTsaService.mkHtml(filePathfileName,makeHtmlContent);

        //4.html파일을txt로 변환 (전자서명된값 Decoding)
        makeTxtContent = paramVO.getCustomerSignData();
        HashMap<String, Object> rsMap2 = cmmTsaService.mkTxt(filePathfileName,makeTxtContent);
        String rsCd2 = (String) rsMap2.get("rsCd");

        //5.원본설정작업
        //5-1.원본파일명설정
        paramVO.setFileNameOriginal(paramVO.getCustomerRegNo()+"_"+sdf2.format(today)+paramVO.getFileNmMarkOriginal());
        //5-2.원본(저장경로+파일명)설정
        filePathOriginalfileName = paramVO.getFilePath()+"/"+paramVO.getFileNameOriginal();

        //6.원본html파일생성
        makeHtmlContent = paramVO.getReqHtml();
        HashMap<String, Object> rsMap1ori = cmmTsaService.mkHtml(filePathOriginalfileName,makeHtmlContent);
        String rsCd1ori = (String) rsMap1ori.get("rsCd");

        //7.원본html파일을pdf로 변환
        makePdfContent = paramVO.getReqHtml();
        HashMap<String, Object> rsMap2ori = cmmTsaService.htmlCovertPdf(filePathOriginalfileName,makePdfContent);

        //8.TSA연동前값세팅
        //8-1.header셋팅
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //8-2.url셋팅
        String tsaUrl = OppfProperties.getProperty("Globals.domain.tsaconn");
        log.debug("5-2.url셋팅:tsaUrl : {} ", tsaUrl);
        log.debug("5-2.TSA파일명 : {} ", filePathfileName+".pdf");
        //8-3.body값셋팅
        HttpEntity<String> requestEntity = null;
        requestEntity = new HttpEntity<String>("{\"fileName\":"+filePathfileName+".txt"+"\"}", httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ResponseErrorHandler(){
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                rsMap.put("rsCd", "52");
                rsMap.put("rsCdMsg", "TSA연동요청중에 에러가 발생했습니다.");
                return false;
            }
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                rsMap.put("rsCd", "53");
                rsMap.put("rsCdMsg", "TSA연동요청중에 에러가 발생했습니다.");
            }
        });


        CmmTsaResVO responseBody = new CmmTsaResVO();

        String serverType = OppfProperties.getProperty("Globals.server.type");
        if(!serverType.equals("loc")) {
            ResponseEntity<CmmTsaResVO> responseEntity = restTemplate.exchange(tsaUrl, HttpMethod.POST, requestEntity, CmmTsaResVO.class);
            responseBody = responseEntity.getBody();
        }

        //9.TSA연동後결과값세팅
        String dataHash = "";
        if(!OppfStringUtil.isEmpty(responseBody.getHashValue())){
            dataHash = responseBody.getHashValue();
        } else {
            if(!serverType.equals("loc")) {
                rsMap.put("rsCd", "54");
                rsMap.put("rsCdMsg", "TSA연동요청에 실패하였습니다.");
            }
        }
        paramVO.setCustomerTsaData(dataHash);
        paramVO.setResTsaHashValue(dataHash);

        //10.약관파일DB저장
        List<SptCustomerServiceTermsFileProfileVO> termsFileList = new ArrayList<SptCustomerServiceTermsFileProfileVO>();
        SptCustomerServiceTermsFileProfileVO pScstfpVO = new SptCustomerServiceTermsFileProfileVO();
        //10-1.약관파일 DB등록 값세팅
        File[] listFile = new File(paramVO.getFilePath()).listFiles();
        if(listFile.length > 0){
            for(int i = 0; i<listFile.length; i++){
                if(listFile[i].isFile()){
                    String currentFileName = listFile[i].getName(); //현재파일명
                    String fileName = paramVO.getFileName()+".txt"; //[서명+원본]파일명
                    String fileNameOriginal = paramVO.getFileName()+"-original.pdf"; //[원본]파일명
                    String fileNameReq = paramVO.getFileName()+".txt.req"; //[TSA응답결과:요청]파일명
                    String fileNameRep = paramVO.getFileName()+".txt.rep"; //[TSA응답결과:응답]파일명
                    //InputStream termsFileData = new FileInputStream(listFile[i]);
                    //long length1 = listFile[i].length();
                    //byte[] file1Byte = new byte[(int) length1];
                    byte[] file1Byte = Files.readAllBytes(listFile[i].toPath());

//                    log.debug("currentFileName["+i+"]="+currentFileName);
//                    log.debug("fileNameReq["+i+"]="+fileNameReq);
//                    log.debug("fileNameRep["+i+"]="+fileNameRep);

                    //[서명+원본]파일 인 경우
                    if(fileName.equals(currentFileName)){
                        pScstfpVO = new SptCustomerServiceTermsFileProfileVO();

                        //설정:회원OP등록번호
                        pScstfpVO.setCustomerRegNo(paramVO.getCustomerRegNo());

                        //설정:동의서파일종류[G027=(001:원본, 002:전자서명파일, 003:TSA원본, 004:TSA적용)]
                        pScstfpVO.setTermsFileType(CodeConstants.TERMS_FILE_TYPE_002);

                        //설정:동의서파일
                        pScstfpVO.setTermsFileData(file1Byte);

                        //설정:TSA결과해쉬값
                        if(!OppfStringUtil.isEmpty(paramVO.getResTsaHashValue())){
                            pScstfpVO.setDataHash(paramVO.getResTsaHashValue());
                        }

                        //vo를list에추가
                        termsFileList.add(pScstfpVO);

                        log.debug("currentFileName["+i+"]="+currentFileName);

                        //[원본]파일 인 경우
                    }else if(fileNameOriginal.equals(currentFileName)){
                        pScstfpVO = new SptCustomerServiceTermsFileProfileVO();

                        //설정:회원OP등록번호
                        pScstfpVO.setCustomerRegNo(paramVO.getCustomerRegNo());

                        //설정:동의서파일종류[G027=(001:원본, 002:전자서명파일, 003:TSA원본, 004:TSA적용)]
                        pScstfpVO.setTermsFileType(CodeConstants.TERMS_FILE_TYPE_001);

                        //설정:동의서파일
                        pScstfpVO.setTermsFileData(file1Byte);

                        //설정:TSA결과해쉬값
                        if(!OppfStringUtil.isEmpty(paramVO.getResTsaHashValue())){
                            pScstfpVO.setDataHash(paramVO.getResTsaHashValue());
                        }

                        //vo를list에추가
                        termsFileList.add(pScstfpVO);

                        log.debug("currentFileName["+i+"]="+currentFileName);

                        //[req]파일 인 경우
                    }else if(fileNameReq.equals(currentFileName)){
                        pScstfpVO = new SptCustomerServiceTermsFileProfileVO();

                        //설정:회원OP등록번호
                        pScstfpVO.setCustomerRegNo(paramVO.getCustomerRegNo());

                        //설정:동의서파일종류[G027=(001:원본, 002:전자서명파일, 003:TSA원본, 004:TSA적용)]
                        pScstfpVO.setTermsFileType(CodeConstants.TERMS_FILE_TYPE_003);

                        //설정:동의서파일
                        pScstfpVO.setTermsFileData(file1Byte);

                        //설정:TSA결과해쉬값
                        if(!OppfStringUtil.isEmpty(paramVO.getResTsaHashValue())){
                            pScstfpVO.setDataHash(paramVO.getResTsaHashValue());
                        }

                        //vo를list에추가
                        termsFileList.add(pScstfpVO);

                        log.debug("currentFileName["+i+"]="+currentFileName);

                        //[rep]파일 인 경우
                    }else if(fileNameRep.equals(currentFileName)){
                        pScstfpVO = new SptCustomerServiceTermsFileProfileVO();

                        //설정:회원OP등록번호
                        pScstfpVO.setCustomerRegNo(paramVO.getCustomerRegNo());

                        //설정:동의서파일종류[G027=(001:원본, 002:전자서명파일, 003:TSA원본, 004:TSA적용)]
                        pScstfpVO.setTermsFileType(CodeConstants.TERMS_FILE_TYPE_004);

                        //설정:동의서파일
                        pScstfpVO.setTermsFileData(file1Byte);

                        //설정:TSA결과해쉬값
                        if(!OppfStringUtil.isEmpty(paramVO.getResTsaHashValue())){
                            pScstfpVO.setDataHash(paramVO.getResTsaHashValue());
                        }

                        //vo를list에추가
                        termsFileList.add(pScstfpVO);

                        log.debug("currentFileName["+i+"]="+currentFileName);
                    }
                }
            }
        }

        //10-3.약관파일 DB등록 작업
        CmmTsaVO cmmTsaVO = new CmmTsaVO();
        String termsFileRegNo = cmmTsaDAO.selectTermsFileRegNo(cmmTsaVO);
        paramVO.setTermsFileRegNo(termsFileRegNo);
        for(SptCustomerServiceTermsFileProfileVO data : termsFileList) {
            data.setTermsFileRegNo(termsFileRegNo);
            cmmTsaDAO.insertSptCustomerServiceTermsFileProfile(data);
            cmmTsaDAO.insertSptCustomerServiceTermsFileProfileHist(data);
        }

        paramVO.setCustomerSignData(oriSignData);

        rsMap.put("rsCd", "00");
        rsMap.put("rsCdMsg", "tsa 작업이 정상적으로 완료 되었습니다.");
        return rsMap;
    }

    @Override
    @Transactional
    public Map<String,Object> createCommonTerms(TermsVO termsVO) throws Exception {

        Map<String,Object> rsMap = this.createTermsFile(termsVO);
        String rsCd = (String) rsMap.get("rsCd");

        if(!"00".equals(rsCd)) {
            return rsMap;
        }

        // 기존 동의서 데이터 취득
        TermsVO commonTerms = appDAO.selectCommonTermsInfo(termsVO);

        if(commonTerms != null) {
            appDAO.deleteCustomerCommonTermsProfile(commonTerms);
            appDAO.insertCustomerCommonTermsProfileHist(commonTerms);

            appDAO.deleteCustomerCommonTermsPubCompanyProfile(commonTerms);
            TermsPubCompanyVO termsPubCompanyVO = new TermsPubCompanyVO();
            termsPubCompanyVO.setCustomerRegNo(commonTerms.getCustomerRegNo());
            termsPubCompanyVO.setTermsRegNo(commonTerms.getTermsRegNo());
            appDAO.insertCustomerCommonTermsPubCompanyProfileHist(termsPubCompanyVO);
        }

        // 일반회원 범용 약관 프로파일 등록
        String termsRegNo = appDAO.selectMaxCommonTermsRegNo(termsVO);
        termsVO.setTermsRegNo(termsRegNo);
        appDAO.insertCustomerCommonTermsProfile(termsVO);
        appDAO.insertCustomerCommonTermsProfileHist(termsVO);

        List<AppAccountVO> pubCompanyList = appDAO.selectCommonTermsPubCompanyList();
        for(AppAccountVO pubCompany : pubCompanyList) {
            String termsPubCompanyRegNo = appDAO.selectMaxCommonTermsPubCompanyRegNo(termsVO);
            TermsPubCompanyVO termsPubCompanyVO = new TermsPubCompanyVO();
            termsPubCompanyVO.setCustomerRegNo(termsVO.getCustomerRegNo());
            termsPubCompanyVO.setTermsRegNo(termsRegNo);
            termsPubCompanyVO.setTermsPubCompanyRegNo(termsPubCompanyRegNo);
            termsPubCompanyVO.setPubCompanyCodeId(pubCompany.getPubCompanyCodeId());
            termsPubCompanyVO.setPubCompanyName(pubCompany.getPubCompanyName());
            appDAO.insertCustomerCommonTermsPubCompanyProfile(termsPubCompanyVO);
            appDAO.insertCustomerCommonTermsPubCompanyProfileHist(termsPubCompanyVO);
        }

        AppVO search = new AppVO();
        search.setCustomerRegNo(termsVO.getCustomerRegNo());
        int checkedCustomerVerify = appDAO.checkedCustomerVerify(search);
        if(checkedCustomerVerify < 1) {
            appDAO.insertCustomerVerifyProfile(termsVO);
            appDAO.insertCustomerVerifyProfileHist(termsVO);
        }

        return rsMap;
    }

    @Override
    public String checkedAppTerms(AppVO appVO) {

        String termsRegNo = "";
        for(String pubCompanyCodeId : appVO.getCheckedPubCompanyList()) {
            appVO.setPubCompanyCodeId(pubCompanyCodeId);
            termsRegNo = appDAO.checkedAppTerms(appVO);
            if(OppfStringUtil.isEmpty(termsRegNo)) {
                return "";
            }
        }

        return termsRegNo;
    }

    @Override
    public Map<String, Object> getAppTermsInfo(AppVO appVO) {
        Map<String, Object> result = new HashMap<>();

        TermsVO appTerms;
        TermsVO beforeAppTerms = null;
        if(OppfStringUtil.isEmpty(appVO.getTermsRegNo())) {
            appTerms = appDAO.selectBaseCommonTerms(appVO);
            appTerms.setTermsAuthYn("Y");

            AppVO subCompanyInfo = appDAO.selectSubCompanyInfo(appVO);
            appTerms.setSubCompanyCodeId(subCompanyInfo.getSubCompanyCodeId());
            appTerms.setSubCompanyName(subCompanyInfo.getSubCompanyName());

            List<String> pubCompanyNames = new ArrayList<>();
            List<AppAccountVO> pubCompanyList = appDAO.selectCommonTermsPubCompanyList();
            for(String pubCompanyCodeId : appVO.getCheckedPubCompanyList()) {
                for(AppAccountVO pubCompany : pubCompanyList) {
                    if(pubCompany.getPubCompanyCodeId().equals(pubCompanyCodeId)) {
                        pubCompanyNames.add(pubCompany.getPubCompanyName());
                    }
                }
            }
            appTerms.setPubCompanyList(pubCompanyNames);

            // 기존에 등록된 정보제공 동의가 있는지 확인 후 해당 항목 추가
            String termsRegNo = appDAO.checkedAppTerms(appVO);
            if(!OppfStringUtil.isEmpty(termsRegNo)) {
                AppVO searchVO = new AppVO();
                searchVO.setCustomerRegNo(appVO.getCustomerRegNo());
                searchVO.setTermsRegNo(termsRegNo);
                List<String> createPubCompanyNmList = appDAO.selectAppTermsPubCompanyList(searchVO);
                for(String createPubCompany : createPubCompanyNmList) {
                    appTerms.getPubCompanyList().add(createPubCompany);
                }
                HashSet<String> set = new HashSet<>(appTerms.getPubCompanyList());
                List<String> newPubCompanyNmList = new ArrayList<>(set);
                appTerms.setPubCompanyList(newPubCompanyNmList);

                // 이전 동의서 정보
                beforeAppTerms = appDAO.selectAppTermsInfo(appVO);
                if(beforeAppTerms != null) {
                    beforeAppTerms.setPubCompanyList(createPubCompanyNmList);
                }
            }
        } else {
//            if(!OppfStringUtil.isEmpty(appVO.getType()) && appVO.getType().equals("myPage")) {
                appVO.setTermsRegNo(OppfStringUtil.base64Decoding(appVO.getTermsRegNo()));
//            }

            if(!OppfStringUtil.isEmpty(appVO.getAppId())) {
                appTerms = appDAO.selectAppTermsInfo(appVO);
            } else {
                appTerms = appDAO.selectAppTermsInfoForDiscard(appVO);
            }
            appTerms.setTermsAuthYn("N");
            if(!OppfStringUtil.isEmpty(appVO.getType()) && appVO.getType().equals("reSync")) {
                TermsVO termsVO = appDAO.selectBaseCommonTerms(appVO);
                appTerms.setTermsCreateDate(termsVO.getTermsCreateDate());
                appTerms.setTermsStartDate(termsVO.getTermsStartDate());
                appTerms.setTermsEndDate(termsVO.getTermsEndDate());
                appTerms.setTermsAuthYn("Y");

                // 이전 동의서 정보
                beforeAppTerms = appDAO.selectAppTermsInfo(appVO);
            }

            List<String> pubCompanyList = appDAO.selectAppTermsPubCompanyList(appVO);;
            // 2018.01.08 서비스 제공자 동의서 조회시 (pubCompanyCodeId) 해당 서비스 제공자만 표시
            if (!OppfStringUtil.isEmpty(appVO.getPubCompanyCodeId()))
            {
            	pubCompanyList.clear();
            	List<TermsPubCompanyVO> pubCompanyInfoList = appDAO.selectAppTermsPubCompanyInfoList(appVO);
                for(TermsPubCompanyVO pubCompanyInfo : pubCompanyInfoList) {
                	if (pubCompanyInfo.getPubCompanyCodeId().equals(appVO.getPubCompanyCodeId()))
                		pubCompanyList.add(pubCompanyInfo.getPubCompanyName());
//                	else
//              		pubCompanyList.add("*****");
                }
            }
            else
            	pubCompanyList = appDAO.selectAppTermsPubCompanyList(appVO);
            appTerms.setPubCompanyList(pubCompanyList);
           

            if(beforeAppTerms != null) {
                beforeAppTerms.setPubCompanyList(pubCompanyList);
            }
        }

        result.put("appTermsInfo", appTerms);
        result.put("beforeAppTerms", beforeAppTerms);

        return result;
    }

    @Override
    @Transactional
    public Map<String, Object> createApp(TermsVO termsVO, boolean isModify, HttpServletRequest req) throws Exception {

        Map<String, Object> rsMap = new HashMap<>();

        if(termsVO.getTermsCreatedYn().equals("N")) {

            termsVO.setTermsPolicy("12");

            // 전자서명인 경우
            if("G032001".equals(termsVO.getTermsAuthType())) {
                rsMap = this.createTermsFile(termsVO);
                String rsCd = (String) rsMap.get("rsCd");

                if(!"00".equals(rsCd)) {
                    return rsMap;
                }
            }

            // 같은 앱개발사로 등록된 정보제공동의가 있는 경우
            AppVO oldTermsCheck = new AppVO();
            oldTermsCheck.setCustomerRegNo(termsVO.getCustomerRegNo());
            oldTermsCheck.setSubCompanyCodeId(termsVO.getSubCompanyCodeId());
            String oldTermsRegNo = appDAO.checkedAppTerms(oldTermsCheck);

            // 일반회원 서비스 약관 프로파일 번호 취득
            String termsRegNo = appDAO.selectMaxTermsRegNo(termsVO);
            termsVO.setTermsRegNo(termsRegNo);
            // 일반회원 서비스 약관 프로파일 데이터 등록
            appDAO.insertCustomerServiceTermsProfile(termsVO);
            // 일반회원 서비스 약관 프로파일 이력 데이터 등록
            appDAO.insertCustomerServiceTermsProfileHist(termsVO);

            if(termsVO.getAppAccountList() != null) {
                List<String> createPubCompany = new ArrayList<>();
                for(AppAccountVO appAccountVO : termsVO.getAppAccountList()) {
                    if(appAccountVO.isChecked()) {
                        createPubCompany.add(appAccountVO.getPubCompanyCodeId());
                    }
                }
                HashSet<String> set = new HashSet<>(createPubCompany);
                List<String> checkedPubCompanyList = new ArrayList<>(set);

                for(String checkedPubCompany : checkedPubCompanyList) {
                    int count = 0;
                    for(AppAccountVO appAccountVO : termsVO.getAppAccountList()) {
                        if(appAccountVO.getPubCompanyCodeId().equals(checkedPubCompany) && count == 0) {
                            // 일반회원 서비스 약관 금투사 프로파일 번호 취득
                            String termsPubCompanyRegNo = appDAO.selectMaxTermsPubCompanyRegNo(termsVO);
                            TermsPubCompanyVO termsPubCompanyVO = new TermsPubCompanyVO();
                            termsPubCompanyVO.setTermsRegNo(termsVO.getTermsRegNo());
                            termsPubCompanyVO.setTermsPubCompanyRegNo(termsPubCompanyRegNo);
                            termsPubCompanyVO.setCustomerRegNo(termsVO.getCustomerRegNo());
                            termsPubCompanyVO.setPubCompanyName(appAccountVO.getPubCompanyName());
                            termsPubCompanyVO.setPubCompanyCodeId(appAccountVO.getPubCompanyCodeId());
                            // 일반회원 서비스 약관 금투사 프로파일 데이터 등록
                            appDAO.insertCustomerServiceTermsPubCompanyProfile(termsPubCompanyVO);
                            // 일반회원 서비스 약관 금투사 프로파일 이력 데이터 등록
                            appDAO.insertCustomerServiceTermsPubCompanyProfileHist(termsPubCompanyVO);
                            count++;
                        }
                    }
                }
            }

            if(!OppfStringUtil.isEmpty(oldTermsRegNo)) {
                AppVO searchPubCompany = new AppVO();
                searchPubCompany.setCustomerRegNo(termsVO.getCustomerRegNo());
                searchPubCompany.setTermsRegNo(oldTermsRegNo);
                List<TermsPubCompanyVO> oldPubCompanyList = appDAO.selectAppTermsPubCompanyInfoList(searchPubCompany);
                for(TermsPubCompanyVO oldPubCompany : oldPubCompanyList) {
                    oldPubCompany.setTermsRegNo(termsVO.getTermsRegNo());
                    int result = appDAO.checkCreatedCustomerServiceTermsPubCompanyProfile(oldPubCompany);
                    if(result < 1) {
                        String termsPubCompanyRegNo = appDAO.selectMaxTermsPubCompanyRegNo(termsVO);
                        oldPubCompany.setTermsPubCompanyRegNo(termsPubCompanyRegNo);
                        oldPubCompany.setTermsRegNo(termsVO.getTermsRegNo());
                        // 일반회원 서비스 약관 금투사 프로파일 데이터 등록
                        appDAO.insertCustomerServiceTermsPubCompanyProfile(oldPubCompany);
                        // 일반회원 서비스 약관 금투사 프로파일 이력 데이터 등록
                        appDAO.insertCustomerCommonTermsPubCompanyProfileHist(oldPubCompany);
                    }
                }
                // 기존 동의서 폐기 처리
                // 정보제공동의서 폐기 처리
                appDAO.deleteServiceTermsProfile(searchPubCompany);
                TermsVO oldTerms = new TermsVO();
                oldTerms.setCustomerRegNo(termsVO.getCustomerRegNo());
                oldTerms.setTermsRegNo(oldTermsRegNo);
                appDAO.insertCustomerServiceTermsProfileHist(oldTerms);
                cmmSIFInternalService.sendServiceTerms(termsVO.getCustomerId(), oldTermsRegNo);

                // 같은 앱 아이디로 앱 신청 되어있는 경우에는 기존 앱 정보도 폐기 할것
                oldTerms.setAppId(termsVO.getAppId());
                String oldServiceRegNo = appDAO.selectOldServiceRegNo(oldTerms);
                if(!OppfStringUtil.isEmpty(oldServiceRegNo) && !isModify) {
                    AppVO deleteApp = new AppVO();
                    deleteApp.setCustomerRegNo(termsVO.getCustomerRegNo());
                    deleteApp.setServiceRegNo(oldServiceRegNo);
                    deleteApp.setTermsRegNo(oldTermsRegNo);
                    appDAO.deleteServiceProfile(deleteApp);
                    appDAO.insertServiceProfileHist(deleteApp);
                    // 서비스 연결 계좌정보 삭제
                    AppAccountVO appAccountVO = new AppAccountVO();
                    appAccountVO.setCustomerRegNo   (deleteApp.getCustomerRegNo()   );
                    appAccountVO.setServiceRegNo    (deleteApp.getServiceRegNo()    );
                    appDAO.deleteCustomerServiceAccountProfileAll(appAccountVO);
                }

                // 기존 앱정보를 신규 제공동의 등록 번호로 업데이트
                List<String> appCount = appDAO.selectAppCountForTerms(searchPubCompany);
                for(String appId : appCount) {
//                    if(!appId.equals(termsVO.getAppId())) {
                        TermsVO updateVO = new TermsVO();
                        updateVO.setCustomerRegNo(termsVO.getCustomerRegNo());
                        updateVO.setAppId(appId);
                        updateVO.setTermsRegNo(termsVO.getTermsRegNo());
                        appDAO.updateCustomerServiceProfile(updateVO);
//                    }
                }
            }
        }

        if(!isModify) {
            // 일반회원 서비스 프로파일 번호 취득
            String serviceRegNo = appDAO.selectMaxServiceRegNo(termsVO);
            termsVO.setServiceRegNo(serviceRegNo);
            // 일반회원 서비스 프로파일 데이터 등록
            appDAO.insertCustomerServiceProfile(termsVO);
            // 일반회원 서비스 프로파일 이력 데이터 등록
            appDAO.insertCustomerServiceProfileHist(termsVO);

            if(termsVO.getAppAccountList() != null) {
                for(AppAccountVO createAccount : termsVO.getAppAccountList()) {
                    if(createAccount.isChecked()) {
                        // 일반회원 서비스 계좌 프로파일 번호 취득
                        String accountRegNo = appDAO.selectMaxServiceAccountRegNo(termsVO);
                        createAccount.setAccountRegNo(accountRegNo);
                        createAccount.setServiceRegNo(serviceRegNo);
                        createAccount.setCustomerRegNo(termsVO.getCustomerRegNo());
                        // 일반회원 서비스 계좌 프로파일 데이터 등록
                        appDAO.insertCustomerServiceAccountProfile(createAccount);
                        // 일반회원 서비스 계좌 프로파일 이력 데이터 등록
                        appDAO.insertCustomerServiceAccountProfileHist(createAccount);
                    }
                }
            }
        }

        if(termsVO.getTermsCreatedYn().equals("N")) {
            if("G032002".equals(termsVO.getTermsAuthType())) {
                // ARS 인증 데이터 등록
                String termsArsRegNo = appDAO.selectMaxTermsArsRegNo(termsVO);
                termsVO.setTermsArsRegNo(termsArsRegNo);
                termsVO.setCustomerPhone(appDAO.selectEncCustomerPhoneNumber(termsVO));
                appDAO.insertCustomerServiceArsProfile(termsVO);
                appDAO.insertCustomerServiceArsProfileHist(termsVO);
            }

            cmmSIFInternalService.sendServiceTerms(termsVO.getCustomerId(), termsVO.getTermsRegNo());
            this.sendEmail(termsVO, req);
        }

        rsMap.put("rsCd", "00");
        rsMap.put("rsCdMsg", "tsa 작업이 정상적으로 완료 되었습니다.");
        return rsMap;
    }

    @Override
    public Map<String, Object> modifyApp(TermsVO termsVO, HttpServletRequest req) throws Exception {

        Map<String, Object> rsMap = new HashMap<>();

        // (나)동의서를 신규로 받은건지 확인
        if(!OppfStringUtil.isEmpty(termsVO.getArsResultCd()) || !OppfStringUtil.isEmpty(termsVO.getCustomerSignData())) {
            rsMap = this.createApp(termsVO, true, req);
            String rsCd = (String) rsMap.get("rsCd");

            if(!"00".equals(rsCd)) {
                return rsMap;
            }
        }

        if(!OppfStringUtil.isEmpty(termsVO.getServiceRegNo())) {
            // 일반회원 서비스 계좌 프로파일 데이터 등록 및 삭제
            if(termsVO.getAppAccountList() != null) {
                for(AppAccountVO appAccountVO : termsVO.getAppAccountList()) {
                    appAccountVO.setServiceRegNo(termsVO.getServiceRegNo());
                    appAccountVO.setCustomerRegNo(termsVO.getCustomerRegNo());
                    if(!OppfStringUtil.isEmpty(appAccountVO.getAccountRegNo()) && !appAccountVO.isChecked()) {
                        // accountRegNo 가 있고, checked 가 false 인 경우는 삭제
                        appDAO.deleteCustomerServiceAccountProfile(appAccountVO);
                        appDAO.insertCustomerServiceAccountProfileHist(appAccountVO);
                    } else if(OppfStringUtil.isEmpty(appAccountVO.getAccountRegNo()) && appAccountVO.isChecked()) {
                        // accountRegNo 가 없고, checked 가 true 인 경우는 등록
                        String accountRegNo = appDAO.selectMaxServiceAccountRegNo(termsVO);
                        appAccountVO.setAccountRegNo(accountRegNo);
                        appDAO.insertCustomerServiceAccountProfile(appAccountVO);
                        appDAO.insertCustomerServiceAccountProfileHist(appAccountVO);
                    }
                }
            }
        }

        rsMap.put("rsCd", "00");
        return rsMap;
    }

    private void sendEmail(TermsVO paramVO, HttpServletRequest req) throws Exception {
        String customerName = paramVO.getCustomerName();
        String customerEmail = paramVO.getCustomerEmail();

        //고객정보 가져오기
        CmmEmailSendVO customerParamVO = new CmmEmailSendVO();
        customerParamVO.setCustomerRegNo(paramVO.getCustomerRegNo());

        //고객정보 조회
        CmmEmailSendVO customerVO = null;
        try{
            customerVO = cmmEmailSendService.selectCmmEmailCustomerInfo(customerParamVO);

            //고객정보가 있을경우에 처리
            if(customerVO != null && !OppfStringUtil.isEmpty(customerVO.getCustomerName())){
                customerName = customerVO.getCustomerName();
                customerEmail = customerVO.getCustomerEmail();
            }

        }catch(Exception e){
            log.debug("################정보제공 동의 email 발송  ###########################");
            log.debug("고객정보 조회 실패 -> parameter로 받아온 정보로 send");
            log.debug("customerRegNo : {} ", paramVO.getCustomerRegNo());
            log.debug("customerName : {} ", customerName);
            log.debug("customerEmail : {} ", customerEmail);
            log.debug("error : {} ", e);
            log.debug("###############################################################");
        }

        CmmEmailSendVO cmmEmailSendVO = new CmmEmailSendVO();
        cmmEmailSendVO.setEmailSendType("G016006"); //이메일 유형 - com_email_temp_info 이메일 템플릿 조회

        cmmEmailSendVO.setReceiverName(customerName); //받는 사람 이름(실제 이메일에 셋팅됩니다.)
        cmmEmailSendVO.setReceiverEmail(customerEmail); //받는 사람 이메일(실제 이메일에 셋팅되고, 이 주소로 이메일이 발송됩니다.)
        cmmEmailSendVO.setReceiverKind("G018001"); //수신자 종류 - G018001:일반사용자, G018002:기업사용자
        cmmEmailSendVO.setReceiverId(paramVO.getCustomerRegNo()); //수신자 ID

        cmmEmailSendVO.setSenderKind("G017001"); //발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
        cmmEmailSendVO.setSenderId(paramVO.getCustomerRegNo()); //발신자 ID

        cmmEmailSendVO.setSendId(paramVO.getCustomerRegNo()); //최초 발신자 ID
        cmmEmailSendVO.setCreateId(paramVO.getCustomerRegNo()); //생성자ID
        cmmEmailSendVO.setUpdateId(paramVO.getCustomerRegNo()); //수정자ID

        String uriContextPath = "https://";
        String sptServerName = OppfProperties.getProperty("Globals.domain.spt");
        if(sptServerName.indexOf(":") >= 0){
            String [] tmpStr = sptServerName.split(":");

            uriContextPath += tmpStr[0] + ":" + OppfProperties.getProperty("Globals.sslPort.spt");
        }else{
            uriContextPath += sptServerName;
        }
        cmmEmailSendVO.setUriContextPath(uriContextPath); //로그인 페이지 : https

        cmmEmailSendVO.setSystemKind("spt");//개인 포털로 셋팅[spt:개인, cpt:기업, apt:admin] -> session이 아닌 target이 있을 경우 셋팅

        cmmEmailSendVO.setCustomerRegNo(paramVO.getCustomerRegNo());	//회원 등록 번호
        cmmEmailSendVO.setTermsRegNo(paramVO.getTermsRegNo());			//약관 동의 번호

        //1.이메일 템플릿 조회 -> 2.이메일발송 유형 구분 -> 3.메일 발송 -> (성공시) -> 4.com_email_send_info 로그적재 관련
        CmmEmailSendVO eamilResultVO = cmmEmailSendService.cmmEmailSend(cmmEmailSendVO, req);
    }

}
