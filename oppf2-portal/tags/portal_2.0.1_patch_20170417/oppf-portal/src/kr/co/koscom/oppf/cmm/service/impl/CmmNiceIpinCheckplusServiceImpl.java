package kr.co.koscom.oppf.cmm.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import Kisinfo.Check.IPIN2Client;
import kr.co.koscom.oppf.cmm.service.CmmNiceIpinCheckplusService;
import kr.co.koscom.oppf.cmm.service.CmmNiceIpinCheckplusVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.impl.MbrRegServiceImpl;


/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CmmNiceIpinCheckplusService.java
* @Comment  : 공통 NICE평가정보에서 제공하는 i-pin인증 및 휴대폰인증 기능을 제공하는 service
* @author   : 유제량
* @since    : 2016.06.01
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.01  유제량        최초생성
*
*/
@Service("CmmNiceIpinCheckplusService")
public class CmmNiceIpinCheckplusServiceImpl implements CmmNiceIpinCheckplusService{
    
    /*@Resource(name = "CmmEmailSendDAO")
    private CmmEmailSendDAO cmmEmailSendDAO;
    */
    private static final Logger log = Logger.getLogger(MbrRegServiceImpl.class);
    
    /**
     * @Method Name        : initIpinCert
     * @Method description : [회원가입:인증] 아이핀 인증을 한다.
     * @param              : MbrRegVO, HttpServletRequest
     * @return             : MbrRegVO
     * @throws             : Exception
     */
    public MbrRegVO initIpinCert(MbrRegVO mbrRegVO, HttpServletRequest request) throws Exception{
        MbrRegVO rsMbrRegVO = new MbrRegVO();
        /********************************************************************************************************************************************
        NICE평가정보 Copyright(c) KOREA INFOMATION SERVICE INC. ALL RIGHTS RESERVED            
            서비스명 : 가상주민번호서비스 (IPIN) 서비스
            페이지명 : 가상주민번호서비스 (IPIN) 호출 페이지
        *********************************************************************************************************************************************/
        
        String sSiteCode = OppfProperties.getProperty("Globals.nice.ipin.siteCode"); // IPIN 서비스 사이트 코드(NICE평가정보에서 발급한 사이트코드)
        String sSitePw = OppfProperties.getProperty("Globals.nice.ipin.sitePw"); // IPIN 서비스 사이트 패스워드(NICE평가정보에서 발급한 사이트패스워드)
            
        /*
        ┌ sReturnURL 변수에 대한 설명  ─────────────────────────────────────────────────────
            NICE평가정보 팝업에서 인증받은 사용자 정보를 암호화하여 귀사로 리턴합니다.
                         따라서 암호화된 결과 데이타를 리턴받으실 URL 정의해 주세요.
            
            * URL 은 http 부터 입력해 주셔야하며, 외부에서도 접속이 유효한 정보여야 합니다.
            * 당사에서 배포해드린 샘플페이지 중, ipin_process.jsp 페이지가 사용자 정보를 리턴받는 예제 페이지입니다.
            
            아래는 URL 예제이며, 귀사의 서비스 도메인과 서버에 업로드 된 샘플페이지 위치에 따라 경로를 설정하시기 바랍니다.
            예 - http://www.test.co.kr/ipin_process.jsp, https://www.test.co.kr/ipin_process.jsp, https://test.co.kr/ipin_process.jsp
        └────────────────────────────────────────────────────────────────────
        */
        String domainURI = "http://" + OppfProperties.getProperty("Globals.domain.spt");
        //String requestURL = request.getRequestURL().toString();
        String requestURL = request.getHeader("x-forwarded-proto").toString();
        log.debug("##############아이핀 인증 return url 접근 http/https 처리######################");
        log.debug("requestURL="+requestURL);
        if(OppfStringUtil.isEmpty(requestURL)) requestURL = request.getRequestURL().toString();
        log.debug("requestURL(request.getRequestURL())="+requestURL);
        
        if(requestURL.indexOf("https") >= 0){
        	//http -> https
        	domainURI = domainURI.replaceAll("http", "https");
        	
        	//포트 변환
        	domainURI = domainURI.replaceAll(OppfProperties.getProperty("Globals.port.spt"), OppfProperties.getProperty("Globals.sslPort.spt"));
        }else{
        	//https -> http
        	domainURI = domainURI.replaceAll("https", "http");
        	
        	//포트 변환
        	domainURI = domainURI.replaceAll(OppfProperties.getProperty("Globals.sslPort.spt"), OppfProperties.getProperty("Globals.port.spt"));
        }
        
        String sReturnURL = domainURI + "/cmm/ipin/ipinProcess.do";		//처리결과 URL
        
        log.debug("########################################################################");
        log.debug("domainURI="+domainURI);
        log.debug("sReturnURL="+sReturnURL);
        log.debug("########################################################################");
                
        /*
        ┌ sCPRequest 변수에 대한 설명  ─────────────────────────────────────────────────────
            [CP 요청번호]로 귀사에서 데이타를 임의로 정의하거나, 당사에서 배포된 모듈로 데이타를 생성할 수 있습니다.
            
            CP 요청번호는 인증 완료 후, 암호화된 결과 데이타에 함께 제공되며
            데이타 위변조 방지 및 특정 사용자가 요청한 것임을 확인하기 위한 목적으로 이용하실 수 있습니다.
            
            따라서 귀사의 프로세스에 응용하여 이용할 수 있는 데이타이기에, 필수값은 아닙니다.
        └────────────────────────────────────────────────────────────────────
        */
        String sCPRequest               = "";
        
        // 객체 생성
        IPIN2Client pClient = new IPIN2Client();
        
        // 앞서 설명드린 바와같이, CP 요청번호는 배포된 모듈을 통해 아래와 같이 생성할 수 있습니다.
        sCPRequest = pClient.getRequestNO(sSiteCode);
        
        // CP 요청번호를 세션에 저장합니다.
        // 현재 예제로 저장한 세션은 ipin_result.jsp 페이지에서 데이타 위변조 방지를 위해 확인하기 위함입니다.
        // 필수사항은 아니며, 보안을 위한 권고사항입니다.
//        session.setAttribute("CPREQUEST" , sCPRequest);
        request.getSession().setAttribute("CPREQUEST" , sCPRequest);
        
        // Method 결과값(iRtn)에 따라, 프로세스 진행여부를 파악합니다.
        int iRtn = pClient.fnRequest(sSiteCode, sSitePw, sCPRequest, sReturnURL);
        
        String sEncData                 = "";           // 암호화 된 데이타
        String sRtnMsg                  = "";           // 처리결과 메세지
        
        // Method 결과값에 따른 처리사항
        if (iRtn == 0)
        {
        
            // fnRequest 함수 처리시 업체정보를 암호화한 데이터를 추출합니다.
            // 추출된 암호화된 데이타는 당사 팝업 요청시, 함께 보내주셔야 합니다.
            sEncData = pClient.getCipherData();
            
            sRtnMsg = "정상 처리되었습니다.";
        
        }
        else if (iRtn == -1 || iRtn == -2)
        {
            sRtnMsg =   "배포해 드린 서비스 모듈 중, 귀사 서버환경에 맞는 모듈을 이용해 주시기 바랍니다.<BR>" +
                        "귀사 서버환경에 맞는 모듈이 없다면 ..<BR><B>iRtn 값, 서버 환경정보를 정확히 확인하여 메일로 요청해 주시기 바랍니다.</B>";
        }
        else if (iRtn == -9)
        {
            sRtnMsg = "입력값 오류 : fnRequest 함수 처리시, 필요한 4개의 파라미터값의 정보를 정확하게 입력해 주시기 바랍니다.";
        }
        else
        {
            sRtnMsg = "iRtn 값 확인 후, NICE평가정보 개발 담당자에게 문의해 주세요.";
        }
        
        rsMbrRegVO.setiRtn(iRtn);
        rsMbrRegVO.setsEncData(sEncData);
        rsMbrRegVO.setsRtnMsg(sRtnMsg);
        rsMbrRegVO.setDomainSpt(domainURI);
        
        return rsMbrRegVO;
    }
    
    /**
     * @Method Name        : ipinResult
     * @Method description : ipin_result.jsp를 호출한다.(아이핀인증 처리를 합니다.)
     * @param              : MbrRegVO
     * @param              : HttpServletRequest
     * @return             : CmmNiceIpinCheckplusVO
     * @throws             : Exception
     */
    public CmmNiceIpinCheckplusVO ipinResult(MbrRegVO mbrRegVO, HttpServletRequest request) throws Exception{
        
        CmmNiceIpinCheckplusVO resultVO = new CmmNiceIpinCheckplusVO();
        
        /********************************************************************************************************************************************
        NICE평가정보 Copyright(c) KOREA INFOMATION SERVICE INC. ALL RIGHTS RESERVED
            
            서비스명 : 가상주민번호서비스 (IPIN) 서비스
            페이지명 : 가상주민번호서비스 (IPIN) 결과 페이지
        *********************************************************************************************************************************************/
        
        String sSiteCode = OppfProperties.getProperty("Globals.nice.ipin.siteCode"); // IPIN 서비스 사이트 코드(NICE평가정보에서 발급한 사이트코드)
        String sSitePw = OppfProperties.getProperty("Globals.nice.ipin.sitePw"); // IPIN 서비스 사이트 패스워드(NICE평가정보에서 발급한 사이트패스워드)
        
        // 사용자 정보 및 CP 요청번호를 암호화한 데이타입니다.
        String sResponseData = requestReplace(request.getParameter("enc_data"), "encodeData");
        
        // CP 요청번호 : ipin_main.jsp 에서 세션 처리한 데이타
//        String sCPRequest = (String)session.getAttribute("CPREQUEST");
        String sCPRequest = (String)request.getSession().getAttribute("CPREQUEST");
            
        // 객체 생성
        IPIN2Client pClient = new IPIN2Client();
            
        /*
        ┌ 복호화 함수 설명  ──────────────────────────────────────────────────────────
            Method 결과값(iRtn)에 따라, 프로세스 진행여부를 파악합니다.
            
            fnResponse 함수는 결과 데이타를 복호화 하는 함수이며,
            'sCPRequest'값을 추가로 보내시면 CP요청번호 일치여부도 확인하는 함수입니다. (세션에 넣은 sCPRequest 데이타로 검증)
            
            따라서 귀사에서 원하는 함수로 이용하시기 바랍니다.
        └────────────────────────────────────────────────────────────────────
        */
        int iRtn = pClient.fnResponse(sSiteCode, sSitePw, sResponseData);
        //int iRtn = pClient.fnResponse(sSiteCode, sSitePw, sResponseData, sCPRequest);
        
        String sRtnMsg              = "";           // 처리결과 메세지
        String sVNumber             = pClient.getVNumber();         // 가상주민번호 (13자리이며, 숫자 또는 문자 포함)
        String sName                = pClient.getName();            // 이름
        String sDupInfo             = pClient.getDupInfo();         // 중복가입 확인값 (DI - 64 byte 고유값)
        String sAgeCode             = pClient.getAgeCode();         // 연령대 코드 (개발 가이드 참조)
        String sGenderCode          = pClient.getGenderCode();      // 성별 코드 (개발 가이드 참조)
        String sBirthDate           = pClient.getBirthDate();       // 생년월일 (YYYYMMDD)
        String sNationalInfo        = pClient.getNationalInfo();    // 내/외국인 정보 (개발 가이드 참조)
        String sCPRequestNum        = pClient.getCPRequestNO();     // CP 요청번호
        String sAuthInfo            = pClient.getAuthInfo();// 본인확인 수단 
        String sCoInfo1             = pClient.getCoInfo1();         // 연계정보 확인값 (CI - 88 byte 고유값)
        String sCIUpdate            = pClient.getCIUpdate();        // CI 갱신정보
        
        // Method 결과값에 따른 처리사항
        if (iRtn == 1)
        {
        
            /*
                다음과 같이 사용자 정보를 추출할 수 있습니다.
                사용자에게 보여주는 정보는, '이름' 데이타만 노출 가능합니다.
            
                사용자 정보를 다른 페이지에서 이용하실 경우에는
                보안을 위하여 암호화 데이타(sResponseData)를 통신하여 복호화 후 이용하실것을 권장합니다. (현재 페이지와 같은 처리방식)
                
                만약, 복호화된 정보를 통신해야 하는 경우엔 데이타가 유출되지 않도록 주의해 주세요. (세션처리 권장)
                form 태그의 hidden 처리는 데이타 유출 위험이 높으므로 권장하지 않습니다.
            */
            /*
            out.println("가상주민번호 : " + sVNumber + "<BR>");
            out.println("이름 : " + sName + "<BR>");
            out.println("중복가입 확인값 (DI) : " + sDupInfo + "<BR>");
            out.println("연령대 코드 : " + sAgeCode + "<BR>");
            out.println("성별 코드 : " + sGenderCode + "<BR>");
            out.println("생년월일 : " + sBirthDate + "<BR>");
            out.println("내/외국인 정보 : " + sNationalInfo + "<BR>");
            out.println("CP 요청번호 : " + sCPRequestNum + "<BR>");
            out.println("본인확인 수단 : " + sAuthInfo + "<BR>");
            out.println("연계정보 확인값 (CI) : " + sCoInfo1 + "<BR>");
            out.println("CI 갱신정보 : " + sCIUpdate + "<BR>");
            out.println("***** 복호화 된 정보가 정상인지 확인해 주시기 바랍니다.<BR><BR><BR><BR>");
            */
            log.info("가상주민번호 : " + sVNumber);
            log.info("이름 : " + sName);
            log.info("중복가입 확인값 (DI) : " + sDupInfo);
            log.info("연령대 코드 : " + sAgeCode);
            log.info("성별 코드 : " + sGenderCode);
            log.info("생년월일 : " + sBirthDate);
            log.info("내/외국인 정보 : " + sNationalInfo);
            log.info("CP 요청번호 : " + sCPRequestNum);
            log.info("본인확인 수단 : " + sAuthInfo);
            log.info("연계정보 확인값 (CI) : " + sCoInfo1);
            log.info("CI 갱신정보 : " + sCIUpdate);
            
            sRtnMsg = "정상 처리되었습니다.";            
        }
        else if (iRtn == -1 || iRtn == -4)
        {
            sRtnMsg =   "iRtn 값, 서버 환경정보를 정확히 확인하여 주시기 바랍니다.";
        }
        else if (iRtn == -6)
        {
            sRtnMsg =   "당사는 한글 charset 정보를 euc-kr 로 처리하고 있으니, euc-kr 에 대해서 허용해 주시기 바랍니다.<BR>" +
                        "한글 charset 정보가 명확하다면 ..<BR><B>iRtn 값, 서버 환경정보를 정확히 확인하여 메일로 요청해 주시기 바랍니다.</B>";
        }
        else if (iRtn == -9)
        {
            sRtnMsg = "입력값 오류 : fnResponse 함수 처리시, 필요한 파라미터값의 정보를 정확하게 입력해 주시기 바랍니다.";
        }
        else if (iRtn == -12)
        {
            sRtnMsg = "CP 비밀번호 불일치 : IPIN 서비스 사이트 패스워드를 확인해 주시기 바랍니다.";
        }
        else if (iRtn == -13)
        {
            sRtnMsg = "CP 요청번호 불일치 : 세션에 넣은 sCPRequest 데이타를 확인해 주시기 바랍니다.";
        }
        else
        {
            sRtnMsg = "iRtn 값 확인 후, NICE평가정보 개발 담당자에게 문의해 주세요.";
        }
        
        resultVO.setCustomerVerify(sCoInfo1);
        resultVO.setCustomerNameKor(sName);
//        log.debug("서비스:resultVO.getCustomerVerify() : " + resultVO.getCustomerVerify());
//        log.debug("서비스:resultVO.getCustomerNameKor() : " + resultVO.getCustomerNameKor());
        return resultVO;
    }
    
    /**
     * @Method Name        : initCheckplus
     * @Method description : [회원가입:인증] 휴대폰 인증을 한다.(안심본인인증)
     * @param              : MbrRegVO, HttpServletRequest
     * @return             : MbrRegVO
     * @throws             : Exception
     */
    public MbrRegVO initCheckplus(MbrRegVO mbrRegVO, HttpServletRequest request) throws Exception{
        MbrRegVO rsMbrRegVO = new MbrRegVO();
        
        NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();
        
        String sSiteCode = OppfProperties.getProperty("Globals.nice.checkplus.siteCode"); //NICE로부터 부여받은 사이트 코드
        String sSitePassword = OppfProperties.getProperty("Globals.nice.checkplus.sitePw"); //NICE로부터 부여받은 사이트 패스워드
        
        String sRequestNumber = "REQ0000000001";            // 요청 번호, 이는 성공/실패후에 같은 값으로 되돌려주게 되므로 
                                                            // 업체에서 적절하게 변경하여 쓰거나, 아래와 같이 생성한다.
        sRequestNumber = niceCheck.getRequestNO(sSiteCode);
        request.getSession().setAttribute("REQ_SEQ" , sRequestNumber);   // 해킹등의 방지를 위하여 세션을 쓴다면, 세션에 요청번호를 넣는다.
        
        String sAuthType = "M";          // 없으면 기본 선택화면, M: 핸드폰, C: 신용카드, X: 공인인증서
        
        String popgubun     = "N";      //Y : 취소버튼 있음 / N : 취소버튼 없음
        String customize    = "";           //없으면 기본 웹페이지 / Mobile : 모바일페이지
            
        // CheckPlus(본인인증) 처리 후, 결과 데이타를 리턴 받기위해 다음예제와 같이 http부터 입력합니다.
        String domainURI = "http://" + OppfProperties.getProperty("Globals.domain.spt");
        //String requestURL = request.getRequestURL().toString();
        String requestURL = request.getHeader("x-forwarded-proto").toString();
        log.debug("##############휴대폰 인증 return url 접근 http/https 처리######################");
        log.debug("requestURL="+requestURL);
        if(OppfStringUtil.isEmpty(requestURL)) requestURL = request.getRequestURL().toString();
        log.debug("requestURL(request.getRequestURL())="+requestURL);
        
        if(requestURL.indexOf("https") >= 0){
        	//http -> https
        	domainURI = domainURI.replaceAll("http", "https");
        	
        	//포트 변환
        	domainURI = domainURI.replaceAll(OppfProperties.getProperty("Globals.port.spt"), OppfProperties.getProperty("Globals.sslPort.spt"));
        }else{
        	//https -> http
        	domainURI = domainURI.replaceAll("https", "http");
        	
        	//포트 변환
        	domainURI = domainURI.replaceAll(OppfProperties.getProperty("Globals.sslPort.spt"), OppfProperties.getProperty("Globals.port.spt"));
        }
                
        String sReturnUrl = domainURI + "/cmm/checkPlusSafe/checkplusSuccess.do";	// 성공시 이동될 URL
        String sErrorUrl = domainURI + "/cmm/checkPlusSafe/checkplusFail.do";		// 실패시 이동될 URL
        
        log.debug("########################################################################");
        log.debug("domainURI="+domainURI);
        log.debug("sReturnURL="+sReturnUrl);
        log.debug("sReturnURL="+sErrorUrl);
        log.debug("########################################################################");
        
        // 입력될 plain 데이타를 만든다.
        String sPlainData = "7:REQ_SEQ" + sRequestNumber.getBytes().length + ":" + sRequestNumber +
                            "8:SITECODE" + sSiteCode.getBytes().length + ":" + sSiteCode +
                            "9:AUTH_TYPE" + sAuthType.getBytes().length + ":" + sAuthType +
                            "7:RTN_URL" + sReturnUrl.getBytes().length + ":" + sReturnUrl +
                            "7:ERR_URL" + sErrorUrl.getBytes().length + ":" + sErrorUrl +
                            "11:POPUP_GUBUN" + popgubun.getBytes().length + ":" + popgubun +
                            "9:CUSTOMIZE" + customize.getBytes().length + ":" + customize;
        
        String sMessage = "";
        String sEncData = "";
        
        int iReturn = niceCheck.fnEncode(sSiteCode, sSitePassword, sPlainData);
        if( iReturn == 0 )
        {
            sMessage = "정상 처리되었습니다.";
            sEncData = niceCheck.getCipherData();
        }
        else if( iReturn == -1)
        {
            sMessage = "암호화 시스템 에러입니다.";
        }    
        else if( iReturn == -2)
        {
            sMessage = "암호화 처리오류입니다.";
        }    
        else if( iReturn == -3)
        {
            sMessage = "암호화 데이터 오류입니다.";
        }    
        else if( iReturn == -9)
        {
            sMessage = "입력 데이터 오류입니다.";
        }    
        else
        {
            sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
        }
        
        rsMbrRegVO.setsMessage(sMessage);
        rsMbrRegVO.setsEncDataMobile(sEncData);
        
        return rsMbrRegVO;
    }
    
    /**
     * @Method Name        : checkplusSuccess
     * @Method description : [회원가입:인증] 휴대폰 인증 성공 결과값을 리턴한다.(안심본인인증)
     * @param              : MbrRegVO, HttpServletRequest
     * @return             : CmmNiceIpinCheckplusVO
     * @throws             : Exception
     */
    public CmmNiceIpinCheckplusVO checkplusSuccess(MbrRegVO mbrRegVO, HttpServletRequest request) throws Exception{
        
        CmmNiceIpinCheckplusVO resultVO = new CmmNiceIpinCheckplusVO();
        
        NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();

        String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");
        String sReserved1  = requestReplace(request.getParameter("param_r1"), "");
        String sReserved2  = requestReplace(request.getParameter("param_r2"), "");
        String sReserved3  = requestReplace(request.getParameter("param_r3"), "");

        String sSiteCode = OppfProperties.getProperty("Globals.nice.checkplus.siteCode"); //NICE로부터 부여받은 사이트 코드
        String sSitePassword = OppfProperties.getProperty("Globals.nice.checkplus.sitePw"); //NICE로부터 부여받은 사이트 패스워드

        String sCipherTime = "";                 // 복호화한 시간
        String sRequestNumber = "";          // 요청 번호
        String sResponseNumber = "";         // 인증 고유번호
        String sAuthType = "";                 // 인증 수단
        String sName = "";                           // 성명
        String sDupInfo = "";                        // 중복가입 확인값 (DI_64 byte)
        String sConnInfo = "";                   // 연계정보 확인값 (CI_88 byte)
        String sBirthDate = "";                  // 생일
        String sGender = "";                         // 성별
        String sNationalInfo = "";       // 내/외국인정보 (개발가이드 참조)
        String sMessage = "";
        String sPlainData = "";
        
        int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

        if( iReturn == 0 )
        {
            sPlainData = niceCheck.getPlainData();
            sCipherTime = niceCheck.getCipherDateTime();
            
            // 데이타를 추출합니다.
            java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);
            
            sRequestNumber  = (String)mapresult.get("REQ_SEQ");
            sResponseNumber = (String)mapresult.get("RES_SEQ");
            sAuthType           = (String)mapresult.get("AUTH_TYPE");
            sName                   = (String)mapresult.get("NAME");
            sBirthDate          = (String)mapresult.get("BIRTHDATE");
            sGender                 = (String)mapresult.get("GENDER");
            sNationalInfo   = (String)mapresult.get("NATIONALINFO");
            sDupInfo                = (String)mapresult.get("DI");
            sConnInfo           = (String)mapresult.get("CI");
            
            String session_sRequestNumber = (String)request.getSession().getAttribute("REQ_SEQ");
            if(!sRequestNumber.equals(session_sRequestNumber))
            {
                sMessage = "세션값이 다릅니다. 올바른 경로로 접근하시기 바랍니다.";
                sResponseNumber = "";
                sAuthType = "";
            }
        }
        else if( iReturn == -1)
        {
            sMessage = "복호화 시스템 에러입니다.";
        }    
        else if( iReturn == -4)
        {
            sMessage = "복호화 처리오류입니다.";
        }    
        else if( iReturn == -5)
        {
            sMessage = "복호화 해쉬 오류입니다.";
        }    
        else if( iReturn == -6)
        {
            sMessage = "복호화 데이터 오류입니다.";
        }    
        else if( iReturn == -9)
        {
            sMessage = "입력 데이터 오류입니다.";
        }    
        else if( iReturn == -12)
        {
            sMessage = "사이트 패스워드 오류입니다.";
        }    
        else
        {
            sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
        }
        
        resultVO.setCustomerVerify(sConnInfo);
        resultVO.setCustomerNameKor(sName);
        
        return resultVO;
    }
    
    /**
     * @Method Name        : checkplusFail
     * @Method description : [회원가입:인증] 휴대폰 인증 실패 결과값을 리턴한다.(안심본인인증)
     * @param              : MbrRegVO, HttpServletRequest
     * @return             : CmmNiceIpinCheckplusVO
     * @throws             : Exception
     */
    public CmmNiceIpinCheckplusVO checkplusFail(MbrRegVO mbrRegVO, HttpServletRequest request) throws Exception{
        
        CmmNiceIpinCheckplusVO resultVO = new CmmNiceIpinCheckplusVO();
        
        NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();

        String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");
        String sReserved1  = requestReplace(request.getParameter("param_r1"), "");
        String sReserved2  = requestReplace(request.getParameter("param_r2"), "");
        String sReserved3  = requestReplace(request.getParameter("param_r3"), "");

        String sSiteCode = OppfProperties.getProperty("Globals.nice.checkplus.siteCode"); //NICE로부터 부여받은 사이트 코드
        String sSitePassword = OppfProperties.getProperty("Globals.nice.checkplus.sitePw"); //NICE로부터 부여받은 사이트 패스워드

        String sCipherTime = "";                    // 복호화한 시간
        String sRequestNumber = "";             // 요청 번호
        String sErrorCode = "";                     // 인증 결과코드
        String sAuthType = "";                      // 인증 수단
        String sMessage = "";
        String sPlainData = "";
        
        int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

        if( iReturn == 0 )
        {
            sPlainData = niceCheck.getPlainData();
            sCipherTime = niceCheck.getCipherDateTime();
            
            // 데이타를 추출합니다.
            java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);
            
            sRequestNumber  = (String)mapresult.get("REQ_SEQ");
            sErrorCode          = (String)mapresult.get("ERR_CODE");
            sAuthType           = (String)mapresult.get("AUTH_TYPE");
            sMessage = "휴대폰인증에 실패하셨습니다.";
        }
        else if( iReturn == -1)
        {
            sMessage = "복호화 시스템 에러입니다.";
        }    
        else if( iReturn == -4)
        {
            sMessage = "복호화 처리오류입니다.";
        }    
        else if( iReturn == -5)
        {
            sMessage = "복호화 해쉬 오류입니다.";
        }    
        else if( iReturn == -6)
        {
            sMessage = "복호화 데이터 오류입니다.";
        }    
        else if( iReturn == -9)
        {
            sMessage = "입력 데이터 오류입니다.";
        }    
        else if( iReturn == -12)
        {
            sMessage = "사이트 패스워드 오류입니다.";
        }    
        else
        {
            sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
        }
        
        String checkplusFailChk = "checkplusFail";
        resultVO.setCheckplusFailChk(checkplusFailChk);
        resultVO.setsErrorCode(sErrorCode);
        resultVO.setsRtnMsg(sMessage);
        
        return resultVO;
    }
    
    /**
     * @Method Name        : requestReplace
     * @Method description : 요청 문자열을 코드 보안 정책에 맞춰 치환합니다.
     * @param              : paramValue
     * @param              : gubun
     * @return             : String
     * @throws             : Exception
     */
    public String requestReplace(String paramValue, String gubun) {
        String result = "";
        
        if (paramValue != null) {
            
            paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

            paramValue = paramValue.replaceAll("\\*", "");
            paramValue = paramValue.replaceAll("\\?", "");
            paramValue = paramValue.replaceAll("\\[", "");
            paramValue = paramValue.replaceAll("\\{", "");
            paramValue = paramValue.replaceAll("\\(", "");
            paramValue = paramValue.replaceAll("\\)", "");
            paramValue = paramValue.replaceAll("\\^", "");
            paramValue = paramValue.replaceAll("\\$", "");
            paramValue = paramValue.replaceAll("'", "");
            paramValue = paramValue.replaceAll("@", "");
            paramValue = paramValue.replaceAll("%", "");
            paramValue = paramValue.replaceAll(";", "");
            paramValue = paramValue.replaceAll(":", "");
            paramValue = paramValue.replaceAll("-", "");
            paramValue = paramValue.replaceAll("#", "");
            paramValue = paramValue.replaceAll("--", "");
            paramValue = paramValue.replaceAll("-", "");
            paramValue = paramValue.replaceAll(",", "");
            
            if(gubun != "encodeData"){
                paramValue = paramValue.replaceAll("\\+", "");
                paramValue = paramValue.replaceAll("/", "");
            paramValue = paramValue.replaceAll("=", "");
            }
            
            result = paramValue;
            
        }
        return result;
    }
    
}
