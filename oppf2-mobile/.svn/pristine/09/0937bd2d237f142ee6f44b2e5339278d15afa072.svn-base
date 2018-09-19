package kr.co.koscom.oppfm.nice.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import CheckPlus.nice.MCheckPlus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.nice.model.NiceReq;
import kr.co.koscom.oppfm.nice.model.NiceRes;
import lombok.extern.slf4j.Slf4j;

/**
 * NiceController
 * <p>
 * Created by P.K Choi on 2017-05-11.
 */

@Api(value = "nice-controller", description = "휴대폰 본인확인(NICE 평가정보)")
@RequestMapping(value="/apis")
@RestController
@Slf4j
public class NiceController {

    @Autowired
    private MessageUtil messageUtil;


    @Value("#{config['Globals.nice.checkplus.siteCode']}")
    String siteCode;
    

    @Value("#{config['Globals.nice.checkplus.sitePw']}")
    String sitePw;
    
  
    /**
     * requestSafeAuth
     * 인증번호 전송
     * @param niceReq
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/requestSafeAuth", method = RequestMethod.POST)
  	public CommonResponse requestSafeAuth(@ApiParam(value = "휴대폰 본인 확인 전문", required = false) @RequestBody NiceReq niceReq) throws Exception {
    	
    	CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);

		Map<String, Object> body = new HashMap<>();
		
		String sCPRequest		= "";
		
		// 객체 생성
		MCheckPlus cpMobile = new MCheckPlus();

		// 주민등록 번호 앞자리 (6자리 추출)
		String sJumin = niceReq.getBirthDay().substring(2);
		// 2000년대생 구분
		String yearGbn = niceReq.getBirthDay().substring(0, 1);
		String jumin2 = "";
		// Y면 내국인
		if("Y".equals(niceReq.getNationality())){
			// 1800년대생
			if("18".equals(niceReq.getBirthDay().substring(0, 2))){
				// M이면 남자
				if("M".equals(niceReq.getSex())){
					sJumin = sJumin + "9";
					jumin2="9";
				} else {
					//F면 여자
					sJumin = sJumin + "0";
					jumin2="0";
				}
			} else {
				//1900 년대 생
				if("1".equals(yearGbn)){
					// M이면 남자
					if("M".equals(niceReq.getSex())){
						sJumin = sJumin + "1";
						jumin2="1";
					} else {
						//F면 여자
						sJumin = sJumin + "2";
						jumin2="2";
					}
				} else { // 2000년대생
					// M이면 남자
					if("M".equals(niceReq.getSex())){
						sJumin = sJumin + "3";
						jumin2="3";
					} else {
						//F면 여자
						sJumin = sJumin + "4";
						jumin2="4";
					}
				}
			}
		} else {
			//1900 년대 생
			if("1".equals(yearGbn)){
				// M이면 남자
				if("M".equals(niceReq.getSex())){
					sJumin = sJumin + "5";
					jumin2="5";
				} else {
					//F면 여자
					sJumin = sJumin + "6";
					jumin2="6";
				}
			} else { // 2000년대생
				// M이면 남자
				if("M".equals(niceReq.getSex())){
					sJumin = sJumin + "7";
					jumin2="7";
				} else {
					//F면 여자
					sJumin = sJumin + "8";
					jumin2="8";
				}
			}	
		}


		
		//주민번호 뒤번호 앞자리 가져오기
		String jumin1 = "031001";
		//String jumin2 = "3";
		String regYear = jumin2.substring(0,1);
		  
		String teen_year="";
		        
		             
		if ("3478".indexOf(regYear)!=-1){ //주민번호 뒷자리 첫번째가 3또는 4또는 7또는 8 이라면(7, 8은 우리나라 외국인 거주자)
			teen_year = "20" + jumin1;
    	} else if("1256".indexOf(regYear)!=-1){ 
			teen_year = "19" + jumin1;
    	} else {
    		teen_year="";
    	}
  	  
		if(!"".equals(teen_year)){
	  	  	int intJumin = Integer.parseInt(teen_year); //회원의 날짜
	  	     
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("ko", "KOREA"));
			Calendar cal = Calendar.getInstance();
			cal.add(1, -14);
			String strDate = formatter.format(cal.getTime());
			int intDate = Integer.parseInt(strDate); //14년 전의 날짜
			   			
			if(intJumin > intDate){
				throw new CommonException(ErrorCodeConstants.CHILD_AGE_REJECT, null);
			}
		}
		int iReturn = -1;
			iReturn = cpMobile.fnRequestSafeAuth(siteCode, sitePw, sJumin, URLEncoder.encode(niceReq.getSName(), "euc-kr"),niceReq.getSMobileCo(), niceReq.getSMobileNo(), niceReq.getSCPRequest());
		  
			// Method 결과값에 따른 처리사항
			if (iReturn == 0)
			{

				log.debug("RETURN_CODE = {}" , cpMobile.getReturnCode());              // 인증결과코드
				log.debug("REQ_SEQ = {} ", cpMobile.getRequestSEQ());                  // 요청 고유번호
				log.debug("RES_SEQ = {} ", cpMobile.getResponseSEQ());                 // 응답 고유번호

				//유효하지 않은 인증정보
				if("0012".equals(cpMobile.getReturnCode())){
					throw new CommonException(ErrorCodeConstants.NOT_AUTH_INFO, null);
				}
				
				NiceRes niceRes = new NiceRes();
				niceRes.setSResSeq(cpMobile.getResponseSEQ());

				body.put("niceRes", niceRes);
				body.put("niceReq", niceReq);

				response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
				
			}
			else if (iReturn == -7 || iReturn == -8)
			{
				log.debug("AUTH_ERROR=" + iReturn);
				log.debug("서버 네트웍크 및 방확벽 관련하여 아래 IP와 Port를 오픈해 주셔야 이용 가능합니다.");
				log.debug("IP : 121.131.196.200 / Port : 3700 ~ 3715");
				throw new CommonException(ErrorCodeConstants.NOT_CONNECTION, null);
			}
			else if (iReturn == -9 || iReturn == -10 || iReturn == 12)
			{
				log.debug("AUTH_ERROR = {} ", iReturn);
				log.debug("입력값 오류 : fnRequest 함수 처리시, 필요한 6개의 파라미터값의 정보를 정확하게 입력해 주시기 바랍니다.");
				throw new CommonException(ErrorCodeConstants.NO_PARAMETERS, null);
			}
			else
			{
				log.debug("AUTH_ERROR = {}", iReturn);
				log.debug("iReturn 값 확인 후, NICE평가정보 전산 담당자에게 문의해주세요.");
				throw new CommonException(ErrorCodeConstants.FAIL_NICE_PROGRESS, null);
			}

		return response;
    }
    

    
    /**
     * requestConfirm
     * 인증번호 확인
     * @param niceReq
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/requestConfirm", method = RequestMethod.POST)
  	public CommonResponse requestConfirm(@ApiParam(value = "휴대폰 본인 확인 전문", required = false) @RequestBody NiceReq niceReq) throws Exception {
    	
    	CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);

		Map<String, Object> body = new HashMap<>();
    	
    	// 객체 생성
    	MCheckPlus cpMobile = new MCheckPlus();
    	
    	int iReturn = -1;
    	iReturn = cpMobile.fnRequestConfirm(siteCode, sitePw, niceReq.getSResSeq(), niceReq.getSAuthNo(), niceReq.getSCPRequest());
    	
    	if (iReturn == 0)
    	{
    	
    		log.debug("RETURN_CODE = {}", cpMobile.getReturnCode());              // 응답코드
    		log.debug("CONFIRM_DATETIME = {}", cpMobile.getConfirmDateTime());    // 인증 완료시간
    		log.debug("REQ_SEQ = {}", cpMobile.getRequestSEQ());                  // 요청 고유번호
    		log.debug("RES_SEQ = {}", cpMobile.getResponseSEQ());                 // 응답 고유번호
    		log.debug("RES_CI = {}", cpMobile.getResponseCI());                   // 아이핀 연결정보(CI)
    		log.debug("RES_DI = {}", cpMobile.getResponseDI());                   // 아이핀 중복가입확인정보(DI)
    	
			NiceRes niceRes = new NiceRes();
			niceRes.setReturnCode(cpMobile.getReturnCode());
			niceRes.setConfirmDateTime(cpMobile.getConfirmDateTime());
			niceRes.setRequestSEQ(cpMobile.getRequestSEQ());
			niceRes.setResponseSEQ(cpMobile.getResponseSEQ());
			niceRes.setResponseCI(cpMobile.getResponseCI());
			niceRes.setResponseDI(cpMobile.getResponseDI());
		
			body.put("niceRes", niceRes);
			body.put("niceReq", niceReq);

			response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
			
    	}
    	else if (iReturn == -7 || iReturn == -8)
	    {
	    	log.debug("AUTH_ERROR=" + iReturn);
	    	log.debug("서버 네트웍크 및 방확벽 관련하여 아래 IP와 Port를 오픈해 주셔야 이용 가능합니다.");
	    	log.debug("IP : 121.131.196.200 / Port : 3700 ~ 3715");
			throw new CommonException(ErrorCodeConstants.NOT_CONNECTION, null);
		}
		else if (iReturn == -9 || iReturn == -10 || iReturn == 12)
		{
			log.debug("AUTH_ERROR = {}", iReturn);
			log.debug("입력값 오류 : fnRequest 함수 처리시, 필요한 5개의 파라미터값의 정보를 정확하게 입력해 주시기 바랍니다.");
			throw new CommonException(ErrorCodeConstants.NO_PARAMETERS, null);
		}
		else
		{
			log.debug("AUTH_ERROR = {}", iReturn);
			log.debug("iReturn 값 확인 후, NICE평가정보 전산 담당자에게 문의해주세요.");
			throw new CommonException(ErrorCodeConstants.FAIL_NICE_PROGRESS, null);
		}

		return response;
    }
    
    public static void main(String args[]){

    	  //주민번호 뒤번호 앞자리 가져오기
    	String jumin1 = "031001";
    	String jumin2 = "3";
    	  String regYear = jumin2.substring(0,1);
    	  
    	  String teen_year=null;
    	        
    	             
    	  if ("3478".indexOf(regYear)!=-1) //주민번호 뒷자리 첫번째가 3또는 4또는 7또는 8 이라면(7, 8은 우리나라 외국인 거주자)
    	    teen_year = "20" + jumin1;
    	  else 
    	          teen_year = "19" + jumin1;

    	  
    	  
    	  int intJumin = Integer.parseInt(teen_year); //회원의 날짜
    	        
    	     
    	     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("ko", "KOREA"));
    	        Calendar cal = Calendar.getInstance();
    	        cal.add(1, -14);
    	        String strDate = formatter.format(cal.getTime());
    	        int intDate = Integer.parseInt(strDate); //14년 전의 날짜
    	       
    	        System.out.println(intJumin > intDate ? "child" : "NO");

    	        if(intJumin > intDate){
        	        System.out.println("YES");
    	        } else {
        	        System.out.println("NO");
    	        	
    	        }
    	        System.out.println("intJumin="+intJumin);
    	        System.out.println("intDate="+intDate);

    }
    
}
