package kr.co.koscom.oppf.cmm.tsa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import kr.co.koscom.oppf.cmm.IntegratedAccount.service.CmmSIFInternalService;
import kr.co.koscom.oppf.cmm.util.CodeConstants;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.itextpdf.text.pdf.PdfWriter;

import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.tsa.service.CmmTsaResVO;
import kr.co.koscom.oppf.cmm.tsa.service.CmmTsaService;
import kr.co.koscom.oppf.cmm.tsa.service.CmmTsaVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.sample.DefaultFontProvider;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceTermsFileProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceTermsProfileVO;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmTsaServiceImpl.java
* @Comment  : [공통회원동의서TSA연계]정보관리를 위한 Service 클래스
* @author   : 포털 이환덕
* @since    : 2016.06.09
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.09  이환덕        최초생성
*
*/
@Service("CmmTsaService")
public class CmmTsaServiceImpl implements CmmTsaService{
    
    @Resource(name = "CmmTsaDAO")
    private CmmTsaDAO cmmTsaDAO;
    @Resource(name = "CmmSIFInternalService")
    private CmmSIFInternalService cmmSIFInternalService;

    private static final Logger log = Logger.getLogger(CmmTsaServiceImpl.class);

   /**
     * @Method Name        : procTsa
     * @Method description : TSA처리
     * @param              : CmmTsaVO
     * @return             : HashMap<String,Object>
     * @throws             : Exception
     */
    @Override
    @Transactional
    public HashMap<String, Object> procTsa(CmmTsaVO paramVO)throws Exception{
        HashMap<String, Object> rsMap = new HashMap<String, Object>();
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddhhmmss");
        
        String filePathfileName = ""; //파일풀경로파일명(확장자뺀)
        String filePathOriginalfileName = ""; //원본파일풀경로파일명(확장자뺀)
        String makeHtmlContent  = ""; //만들html내용
        String makePdfContent   = ""; //만들pdf내용
        String makeTxtContent   = ""; //만들txt내용
        
        String oriSignData = paramVO.getSignData();	//오리지널 signData -> 암호화된 값
        
        //년수취득
        paramVO.setTermsPolicyYear(OppfProperties.getProperty("Globals.user.policy.password.final"));
        
        //1.임시폴더생성작업
        String folderPath = ""; //임시폴더경로및폴더명
        //1-1.tmpTsa폴더 생성
        folderPath = OppfProperties.getProperty("Globals.spt.webcontent.path")+"/upload/tmpTsa";
        HashMap<String, Object> rsMapForder1 = mkDirTsa(folderPath);
        //1-2.오늘날짜 폴더 생성(tmpTsa/오늘날짜)
        folderPath = OppfProperties.getProperty("Globals.spt.webcontent.path")+"/upload/tmpTsa/"+sdf.format(today);
        HashMap<String, Object> rsMapForder2 = mkDirTsa(folderPath);
        //1-3.사용자등록번호 폴더 생성(tmpTsa/오늘날짜/사용자등록번호)
        folderPath = OppfProperties.getProperty("Globals.spt.webcontent.path")+"/upload/tmpTsa/"+sdf.format(today)+"/"+paramVO.getCustomerRegNo();
        HashMap<String, Object> rsMapForder3 = mkDirTsa(folderPath);
        
        //2.설정작업
        //2-1.파일경로설정
        paramVO.setFilePath(folderPath);
        
        //2-2.파일명설정
        paramVO.setFileName(paramVO.getCustomerRegNo()+"_"+sdf2.format(today));
        
        //2-3.(저장경로+파일명)설정
        filePathfileName = paramVO.getFilePath()+"/"+paramVO.getFileName();
        
        //3.html파일생성
        //3-1.공인인증서 전자서명데이터 base64 디코딩작업
        if(!OppfStringUtil.isEmpty(paramVO.getSignData())){
            log.debug("3-1.공인인증서 전자서명데이터 base64 디코딩작업前:paramVO.getSignData()="+paramVO.getSignData());
            String decodedSignData = OppfStringUtil.base64Decoding(paramVO.getSignData());
            log.debug("3-1.공인인증서 전자서명데이터 base64 디코딩작업後:decodedSignData="+decodedSignData);
            
            paramVO.setSignData(decodedSignData);
        }
        makeHtmlContent = paramVO.getSignData();
        HashMap<String, Object> rsMap1 = mkHtml(filePathfileName,makeHtmlContent);
        String rsCd1 = (String) rsMap1.get("rsCd");
        if(!"00".equals(rsCd1)){
            return rsMap1;
        }
        
        
/*
 		//4.html파일을pdf로 변환
        makePdfContent = paramVO.getSignData();
        HashMap<String, Object> rsMap2 = htmlCovertPdf(filePathfileName,makePdfContent);
        String rsCd2 = (String) rsMap2.get("rsCd");
        if(!"00".equals(rsCd2)){
            return rsMap2;
        }
*/
      //4.html파일을txt로 변환 (전자서명된값 Decoding)
        makeTxtContent = paramVO.getSignData();
        HashMap<String, Object> rsMap2 = mkTxt(filePathfileName,makeTxtContent);
        String rsCd2 = (String) rsMap2.get("rsCd");
        if(!"00".equals(rsCd2)){
            return rsMap2;
        }
        
        //5.원본설정작업
        //5-1.원본파일명설정
        paramVO.setFileNameOriginal(paramVO.getCustomerRegNo()+"_"+sdf2.format(today)+paramVO.getFileNmMarkOriginal());
        //5-2.원본(저장경로+파일명)설정
        filePathOriginalfileName = paramVO.getFilePath()+"/"+paramVO.getFileNameOriginal();
        
        //6.원본html파일생성
        makeHtmlContent = paramVO.getReqHtml();
        HashMap<String, Object> rsMap1ori = mkHtml(filePathOriginalfileName,makeHtmlContent);
        String rsCd1ori = (String) rsMap1ori.get("rsCd");
        if(!"00".equals(rsCd1ori)){
            return rsMap1ori;
        }
        
        //7.원본html파일을pdf로 변환
        makePdfContent = paramVO.getReqHtml();
        HashMap<String, Object> rsMap2ori = htmlCovertPdf(filePathOriginalfileName,makePdfContent);
        String rsCd2ori = (String) rsMap2ori.get("rsCd");
        if(!"00".equals(rsCd2ori)){
            return rsMap2ori;
        }
        

        //8.TSA연동前값세팅
        //8-1.header셋팅
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //8-2.url셋팅
        String tsaUrl = OppfProperties.getProperty("Globals.domain.tsaconn");
        log.debug("5-2.url셋팅:tsaUrl="+tsaUrl);
        log.debug("5-2.TSA파일명="+filePathfileName+".pdf");
        //8-3.body값셋팅
        HttpEntity<String> requestEntity = null;
        requestEntity = new HttpEntity<String>("{\"fileName\":"+filePathfileName+".txt"+"\"}", httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ResponseErrorHandler(){
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                //System.out.println("hasError --> " + response.getStatusText());
                rsMap.put("rsCd", "52");
                rsMap.put("rsCdMsg", "TSA연동요청중에 에러가 발생했습니다.");
                
                //삭제작업(폴더안의 폴더 또는 파일 삭제)
//                String delfolderPath = OppfProperties.getProperty("Globals.spt.webcontent.path")+"/upload/tmpTsa/"+sdf.format(today)+"/"+paramVO.getCustomerRegNo()+"/";
//                log.debug("hasError:삭제작업(폴더안의 폴더 또는 파일 삭제):delfolderPath="+delfolderPath);
//                rmDirTsa(delfolderPath);
                
                return false;
            }
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                //System.out.println("handleError --> " + response.getStatusText());
                rsMap.put("rsCd", "53");
                rsMap.put("rsCdMsg", "TSA연동요청중에 에러가 발생했습니다.");
                //삭제작업(폴더안의 폴더 또는 파일 삭제)
//                String delfolderPath = OppfProperties.getProperty("Globals.spt.webcontent.path")+"/upload/tmpTsa/"+sdf.format(today)+"/"+paramVO.getCustomerRegNo()+"/";
//                log.debug("handleError:삭제작업(폴더안의 폴더 또는 파일 삭제):delfolderPath="+delfolderPath);
//                rmDirTsa(delfolderPath);
            }
        });
        ResponseEntity<CmmTsaResVO> responseEntity = restTemplate.exchange(tsaUrl, HttpMethod.POST, requestEntity, CmmTsaResVO.class);
        
        
        //9.TSA연동後결과값세팅
        CmmTsaResVO responseBody = responseEntity.getBody();
        if(!OppfStringUtil.isEmpty(responseBody.getHashValue())){
            paramVO.setResTsaHashValue(responseBody.getHashValue());
            log.debug("9.TSA연동後결과 성공:responseBody.getHashValue()="+responseBody.getHashValue());
        }else{
            rsMap.put("rsCd", "54");
            rsMap.put("rsCdMsg", "TSA연동요청에 실패하였습니다.responseBody.getHashValue()="+responseBody.getHashValue());
            log.debug("9.TSA연동後결과 실패:responseBody.getHashValue()="+responseBody.getHashValue());
            return rsMap;
        }

        
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

        //10-2.약관파일 DB등록 작업前 필터링처리
        if(termsFileList.size() != 4){
            //메세지 설정[성공]
            rsMap.put("rsCd", "101");
            rsMap.put("rsCdMsg", "DB에 등록 할 파일의 갯수가 잘못되었습니다.termsFileList.size()="+termsFileList.size());
            return rsMap;
        }

        //10-3.약관파일 DB등록 작업
        String termsFileRegNo = cmmTsaDAO.selectTermsFileRegNo(paramVO);
        if(OppfStringUtil.isEmpty(termsFileRegNo)){
        	//메세지 설정[성공]
            rsMap.put("rsCd", "102");
            rsMap.put("rsCdMsg", "정보제공 동의 파일 등록번호 생성 실패.");
            return rsMap;
        }
        
        
        int dbInsertCnt = 0;
        for(int i=0; i<termsFileList.size(); i++){
        	SptCustomerServiceTermsFileProfileVO data = (SptCustomerServiceTermsFileProfileVO) termsFileList.get(i);
        	data.setTermsFileRegNo(termsFileRegNo);
        	
            int termsfileRs = insertSptCustomerServiceTermsFileProfile(data);
            log.debug("termsfileRs="+termsfileRs);
            dbInsertCnt += termsfileRs;
        }
        

        //10-3.약관파일 DB등록 작업後 필터링처리
        /*
        if(dbInsertCnt != 4){
            //메세지 설정[성공]
            rsMap.put("rsCd", "102");
            rsMap.put("rsCdMsg", "DB에 등록후 결과갯수가 잘못되었습니다.dbInsertCnt="+dbInsertCnt);
            
            //10-3-1.이미 등록된 약관파일 롤백작업
            return rsMap;
        }
        */

        
        //11.약관상태값 DB수정
        SptCustomerServiceTermsProfileVO pScstpVO = new SptCustomerServiceTermsProfileVO();
        //11-1.약관상태값 DB수정前값세팅
        pScstpVO.setCustomerRegNo(paramVO.getCustomerRegNo());
        pScstpVO.setTermsRegNo(paramVO.getTermsRegNo());
        pScstpVO.setTermsFileRegNo(termsFileRegNo);
        pScstpVO.setTermsStartDate(paramVO.getTermsStartDate());
        pScstpVO.setTermsPolicy(paramVO.getTermsPolicy());
        pScstpVO.setTermsAuthYn("N");
        pScstpVO.setTermsFileStatus("G028030"); //동의서파일처리상태[G028=(010:원본등록성공, 011:원본등록실패, 020:전자서명파일성공, 021:전자서명파일실패, 030:TSA처리성공, 031:TSA처리실패)]
        
        //동의서명정보 셋팅
        pScstpVO.setCustomerSignDn(paramVO.getDn());
        pScstpVO.setCustomerSignData(oriSignData);
        pScstpVO.setCustomerTsaData(paramVO.getResTsaHashValue());
        
        log.debug("11-2.약관상태값 DB수정後:customerSignDn="+pScstpVO.getCustomerSignDn());
        log.debug("11-2.약관상태값 DB수정後:customerSignData="+pScstpVO.getCustomerSignData());
        log.debug("11-2.약관상태값 DB수정後:customerTsaData="+pScstpVO.getCustomerTsaData());
        
        //11-2.약관상태값 DB수정
        int termsRs = updateSptCustomerServiceTermsProfile(pScstpVO);
        log.debug("11-2.약관상태값 DB수정後:termsRs="+termsRs);
        
        //12.삭제작업(폴더안의 폴더 또는 파일 삭제)
//        String delfolderPath = OppfProperties.getProperty("Globals.spt.webcontent.path")+"/upload/tmpTsa/"+sdf.format(today)+"/"+paramVO.getCustomerRegNo()+"/";
//        log.debug("12.삭제작업(폴더안의 폴더 또는 파일 삭제):delfolderPath="+delfolderPath);
//        rmDirTsa(delfolderPath);

        cmmSIFInternalService.sendServiceTerms(paramVO.getCustomerId(), paramVO.getTermsRegNo());
        log.debug("금융정보제공 동의서 연계서버 API연동");
        //메세지 설정[성공]
        rsMap.put("rsCd", "00");
        rsMap.put("rsCdMsg", "tsa 작업이 정상적으로 완료 되었습니다.");
        return rsMap;
    }
    
   /**
     * @Method Name        : mkDirTsa
     * @Method description : TSA처리를 위한 임시 폴더를 생성
     * @param              : folderPathName
     * @return             : HashMap<String,Object>
     * @throws             : Exception
     */
    @Override
    public HashMap<String, Object> mkDirTsa(String folderPathName){
        HashMap<String, Object> rsMap = new HashMap<String, Object>();
        
        File cFile = new File(folderPathName);
        if(!cFile.isDirectory()){
            cFile.mkdirs();
            //메세지 설정[성공]
            rsMap.put("rsCd", "00");
            rsMap.put("rsCdMsg", "해당폴더가 정상적으로 만들어졌습니다.folderPathName="+folderPathName);
        }else{
            //메세지 설정[성공]
            rsMap.put("rsCd", "01");
            rsMap.put("rsCdMsg", "해당폴더가 존재합니다.folderPathName="+folderPathName);
        }
        return rsMap;
    }
    
   /**
     * @Method Name        : rmDirTsa
     * @Method description : TSA처리를 위한 임시폴더안의 하위폴더 및 파일들을 삭제(재귀메소드)
     * @param              : folderPathFileName
     * @return             : void
     * @throws             : Exception
     */
    @Override
    public void rmDirTsa(String parentPath){
        log.debug("rmDirTsa:parentPath="+parentPath);
        if(OppfStringUtil.isEmpty(parentPath)){
            log.debug("삭제할 폴더 또는 파일이 없습니다.parentPath="+parentPath);
            
        }else{
            File file = new File(parentPath);
            String[] fnameList = file.list();
            int fCnt = fnameList.length;
            String childPath = "";
            
            for(int i=0; i<fCnt; i++) {
              childPath = parentPath+"/"+fnameList[i];
              File f = new File(childPath);
              if( ! f.isDirectory()) {
                f.delete();   //파일이면 바로 삭제
              }
              else {
                  rmDirTsa(childPath);
              }
            }
            File f = new File(parentPath);
            f.delete();   //폴더는 맨 나중에 삭제
        }
    }
    
   /**
     * @Method Name        : mkHtml
     * @Method description : String reqHtml 을 html파일 make
     * @param              : fileFullPathName, makeHtmlContent
     * @return             : HashMap<String,Object>
     * @throws             : Exception
     */
    @Override
    public HashMap<String, Object> mkHtml(String fileFullPathName, String makeHtmlContent){
        HashMap<String, Object> rsMap = new HashMap<String, Object>();
 
        if(OppfStringUtil.isEmpty(fileFullPathName)){
            //메세지 설정[실패]
            rsMap.put("rsCd", "11");
            rsMap.put("rsCdMsg", "경로파일명이 없습니다.fileFullPathName="+fileFullPathName);
            return rsMap;
        }
        
        fileFullPathName += ".html";
        
        try{
            //파일객체 생성
            File tmpFile = new File(fileFullPathName);
            
            //true 지정 시 파일의 기존 내용에 이어서 작성
            FileWriter tmpFw = new FileWriter(tmpFile, false);
            
            //파일안에 문자열 쓰기
            tmpFw.write(makeHtmlContent);
            tmpFw.flush();
            
            //객체 닫기
            tmpFw.close();
            
            //메세지 설정[성공]
            rsMap.put("rsCd", "00");
            rsMap.put("rsCdMsg", "html 파일 쓰기가 정상적으로 완료 되었습니다.");
            
        }catch(Exception e){
            e.printStackTrace();
            //메세지 설정[실패]
            rsMap.put("rsCd", "12");
            rsMap.put("rsCdMsg", "파일작성 과정중에 에러가 발생했습니다.");
        }

        return rsMap;
    }

    /**
     * @Method Name        : mkTxt
     * @Method description : String makeContent 을 txt파일 make
     * @param              : fileFullPathName, makeContent
     * @return             : HashMap<String,Object>
     * @throws             : Exception
     */
    @Override
    public HashMap<String, Object> mkTxt(String fileFullPathName, String makeContent){
        HashMap<String, Object> rsMap = new HashMap<String, Object>();
 
        if(OppfStringUtil.isEmpty(fileFullPathName)){
            //메세지 설정[실패]
            rsMap.put("rsCd", "11");
            rsMap.put("rsCdMsg", "경로파일명이 없습니다.fileFullPathName="+fileFullPathName);
            return rsMap;
        }
        
        fileFullPathName += ".txt";
        
        try{
            //파일객체 생성
            File tmpFile = new File(fileFullPathName);
            
            //true 지정 시 파일의 기존 내용에 이어서 작성
            FileWriter tmpFw = new FileWriter(tmpFile, false);
            
            //파일안에 문자열 쓰기
            tmpFw.write(makeContent);
            tmpFw.flush();
            
            //객체 닫기
            tmpFw.close();
            
            //메세지 설정[성공]
            rsMap.put("rsCd", "00");
            rsMap.put("rsCdMsg", "txt 파일 쓰기가 정상적으로 완료 되었습니다.");
            
        }catch(Exception e){
            e.printStackTrace();
            //메세지 설정[실패]
            rsMap.put("rsCd", "12");
            rsMap.put("rsCdMsg", "파일작성 과정중에 에러가 발생했습니다.");
        }

        return rsMap;
    }
    
    
   /**
     * @Method Name        : htmlCovertPdf
     * @Method description : html파일을 pdf로 파일변환
     * @param              : fileFullPathName, makePdfContent
     * @return             : HashMap<String,Object>
     * @throws             : Exception
     */
    @Override
    public HashMap<String, Object> htmlCovertPdf(String fileFullPathName, String makePdfContent){
        HashMap<String, Object> rsMap = new HashMap<String, Object>();
        
        PdfWriter pdfWriter = null;
        
        if(OppfStringUtil.isEmpty(fileFullPathName)){
            rsMap.put("rsCd", "21");
            rsMap.put("rsCdMsg", "경로파일명이 없습니다.fileFullPathName="+fileFullPathName);
            return rsMap;
        }
        
        fileFullPathName += ".pdf";
        
        try{

            // create a new document
            Document document = new Document();

            // get Instance of the PDFWriter
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(fileFullPathName));

            document.setPageSize(PageSize.A4);

            document.open();

            HTMLWorker htmlWorker = new HTMLWorker(document);

            HashMap<String, Object> interfaceProps = new HashMap<String, Object>();

            StyleSheet styles = new StyleSheet();
            
            String fontFullPathName = OppfProperties.getProperty("Globals.spt.webcontent.path")+"/font/malgun.ttf";

            DefaultFontProvider dfp = new DefaultFontProvider(fontFullPathName);            
            interfaceProps.put(HTMLWorker.FONT_PROVIDER, dfp); // 폰트 파일 설정 (한글 나오게 하기 위해 설정 필요함)
            
            StringReader strReader = new StringReader(makePdfContent);
            List<Element> objects = htmlWorker.parseToList(strReader, styles, interfaceProps);

            for(int k=0; k<objects.size(); ++k){
                log.debug("objects.get("+k+")="+objects.get(k));
                document.add((Element) objects.get(k));
            }
            document.close();
            
            // close the writer
            pdfWriter.close();
            
            //메세지 설정[성공]
            rsMap.put("rsCd", "00");
            rsMap.put("rsCdMsg", "html 파일 쓰기가 정상적으로 완료 되었습니다.");
            
        }catch(Exception e){
            e.printStackTrace();
            //메세지 설정[실패]
            rsMap.put("rsCd", "22");
            rsMap.put("rsCdMsg", "파일작성 과정중에 에러가 발생했습니다.");
        }
        
        return rsMap;
    }

    /**
     * @Method Name        : selectSptCustomerServiceTermsFileProfileList
     * @Method description : [일반회원서비스약관파일프로파일]정보를 조회한다.
     * @param              : SptCustomerServiceTermsFileProfileVO
     * @return             : List<SptCustomerServiceTermsFileProfileVO>
     * @throws             : Exception
     */
    public List<SptCustomerServiceTermsFileProfileVO> selectSptCustomerServiceTermsFileProfileList(SptCustomerServiceTermsFileProfileVO paramVO) throws Exception{
        String customerRegNo = paramVO.getCustomerRegNo();
        if(!OppfStringUtil.isEmpty(customerRegNo)){
            paramVO.setSearchCustomerRegNo(customerRegNo);
        }
        
        String termsFileType = paramVO.getTermsFileType();
        if(!OppfStringUtil.isEmpty(termsFileType)){
            paramVO.setTermsFileType(termsFileType);
        }
        
        String termsFileRegNo = paramVO.getTermsFileRegNo();
        if(!OppfStringUtil.isEmpty(termsFileRegNo)){
            paramVO.setTermsFileRegNo(termsFileRegNo);
        }
        
        List<SptCustomerServiceTermsFileProfileVO> resultList = cmmTsaDAO.selectSptCustomerServiceTermsFileProfileList(paramVO);
        return resultList;
    }
    
  /**
    * @Method Name        : insertSptCustomerServiceTermsFileProfile
    * @Method description : [일반회원서비스약관파일프로파일]정보를 등록한다.
    * @param              : SptCustomerServiceTermsFileProfileVO
    * @return             : int
    * @throws             : Exception
    */
   @Transactional
   public int insertSptCustomerServiceTermsFileProfile(SptCustomerServiceTermsFileProfileVO paramVO) throws Exception{
       int rs = 0;
       int rs1 = 0;
       int rs2 = 0;
       log.debug("동의서파일등록前데이터:customerRegNo="+paramVO.getCustomerRegNo());
       log.debug("동의서파일등록前데이터:termsFileRegNo="+paramVO.getTermsFileRegNo());
       log.debug("동의서파일등록前데이터:termsFileType="+paramVO.getTermsFileType());
       log.debug("동의서파일등록前데이터:termsFileData="+paramVO.getTermsFileData());
       
       rs1 = cmmTsaDAO.insertSptCustomerServiceTermsFileProfile(paramVO);              
       rs2 = cmmTsaDAO.insertSptCustomerServiceTermsFileProfileHist(paramVO);
       
       rs = rs1 + rs2;
       return rs;
   }
     
   /**
     * @Method Name        : updateSptCustomerServiceTermsFileProfile
     * @Method description : [일반회원서비스약관파일프로파일]정보를 수정한다.
     * @param              : SptCustomerServiceTermsFileProfileVO
     * @return             : int
     * @throws             : Exception
     */
   @Transactional
   public int updateSptCustomerServiceTermsFileProfile(SptCustomerServiceTermsFileProfileVO paramVO) throws Exception{
       int rs = 0;
       int rs1 = 0;
       int rs2 = 0;
       
       rs1 = cmmTsaDAO.updateSptCustomerServiceTermsFileProfile(paramVO);
       rs2 = cmmTsaDAO.insertSptCustomerServiceTermsFileProfileHist(paramVO);
       
       rs = rs1 + rs2;
       return rs;
   }
   
  /**
    * @Method Name        : updateSptCustomerServiceTermsProfile
    * @Method description : [일반회원서비스약관프로파일]정보를 수정한다.
    * @param              : SptCustomerServiceTermsProfileVO
    * @return             : int
    * @throws             : Exception
    */
   @Transactional
   public int updateSptCustomerServiceTermsProfile(SptCustomerServiceTermsProfileVO paramVO) throws Exception{
       int rs = 0;
       int rs1 = 0;
       int rs2 = 0;
       log.debug("동의서파일등록前데이터:customerRegNo="+paramVO.getCustomerRegNo());
       log.debug("동의서파일등록前데이터:termsRegNo="+paramVO.getTermsRegNo());
       log.debug("동의서파일등록前데이터:termsStartDate="+paramVO.getTermsStartDate());
       log.debug("동의서파일등록前데이터:termsPolicy="+paramVO.getTermsPolicy());
       log.debug("동의서파일등록前데이터:termsAuthYn="+paramVO.getTermsAuthYn());
       log.debug("동의서파일등록前데이터:termsFileStatus="+paramVO.getTermsFileStatus());
       rs1 = cmmTsaDAO.updateSptCustomerServiceTermsProfile(paramVO);
       rs2 = cmmTsaDAO.insertSptCustomerServiceTermsProfileHist(paramVO);
       
       rs = rs1 + rs2;
       return rs;
   }

    
    

}
