package kr.co.koscom.oppf.cmm.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplService;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplVO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.cmm.service.CmmEmailCronSvcTermsVO;
import kr.co.koscom.oppf.cmm.service.CmmEmailSendService;
import kr.co.koscom.oppf.cmm.service.CmmEmailSendVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmEmailSendController.java
* @Comment  : 공통 include Controller
* @author   : 신동진
* @since    : 2016.08.09
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.08.09  신동진        최초생성
*
*/
@Controller
public class CmmEmailSendController {
	private static final Logger log = Logger.getLogger(CmmEmailSendController.class);
	
    @Resource(name = "CmmEmailSendService")
    private CmmEmailSendService cmmEmailSendService;

	@Resource(name = "MypSvcApplService")
	private MypSvcApplService mypSvcApplService;

    /**
     * @Method Name        : cronSvcTermsEmail
     * @Method description : 매일 오전 01시 정보제공동의 대상자에게 메일을 발송한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/cronSvcTermsEmail.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String cronSvcTermsEmail(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
    	//정보제공 동의 확인(G016007) -> 00 : 성공, 01 : 실패, 02 : 처리대상 없음
//    	String result1 = procCronSvcTermsEmail(request, "G016007");
    	
    	//정보제공 동의 재동의(G016008) -> 00 : 성공, 01 : 실패, 02 : 처리대상 없음
    	String result2 = procCronSvcTermsEmail(request, "G016008");

		// 동의서 폐기
		this.cancelSvcAppTerms();

    	//성공
//    	model.addAttribute("result1", result1);
    	model.addAttribute("result2", result2);
    	
    	return "jsonView";
    }

	private void cancelSvcAppTerms() throws Exception {

		CmmEmailCronSvcTermsVO paramVO = new CmmEmailCronSvcTermsVO();
		paramVO.setSQL1("sysdate() > b.terms_expire_date");
		List<CmmEmailCronSvcTermsVO> termsList = cmmEmailSendService.selectCronSvcTermsList(paramVO);

		for(CmmEmailCronSvcTermsVO termsVO : termsList) {

			MypSvcApplVO mypSvcApplVO = new MypSvcApplVO();
			mypSvcApplVO.setCustomerRegNo(termsVO.getCustomerRegno());
			mypSvcApplVO.setCreateId("System");
			mypSvcApplVO.setTermsRegNo(termsVO.getTermsRegNo());

			int result = mypSvcApplService.cancelMypSvcApplTerms(mypSvcApplVO);
		}
	}

    /**
     * @Method Name        : procCronSvcTermsEmail
     * @Method description : 메일 발송 처리
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    public String procCronSvcTermsEmail(HttpServletRequest request, String emailSendType){
    	String result = "02";	//00 : 성공, 01 : 실패, 02 : 처리대상 없음
    	
    	CmmEmailCronSvcTermsVO paramVO = new CmmEmailCronSvcTermsVO();
    	StringBuffer sql1 = new StringBuffer();
    	
    	//정보제공 동의 확인(1년)
    	if("G016007".equals(emailSendType)){
	    	String termsFinal = OppfProperties.getProperty("Globals.user.policy.terms.final");
	    	int termsFinalYear = 0;	//Integer.parseInt(termsFinal) / 12;
	    		    	
	    	if(!OppfStringUtil.isEmpty(termsFinal)){
	    		termsFinalYear = (Integer.parseInt(termsFinal) / 12) - 1;	//최종년도 - 1년
	    		
	    		for(int i=1; i<=termsFinalYear; i++){
	    			sql1.append(" date_format(sysdate(), '%Y%m%d') = date_format(b.terms_start_date + INTERVAL "+i+" YEAR, '%Y%m%d') ");
	    			if(i < termsFinalYear){
	    				sql1.append(" or ");
	    			}
	    		}
	    	}
	    	/*
	    	//test용
	    	sql1 = new StringBuffer();
	    	sql1.append(" 1=1 ");
	    	*/

	    	paramVO.setSQL1(sql1.toString());
	    	
	    //정보제공 동의 확인(마지막년)
    	}else{
//    		sql1.append(" date_format(b.terms_expire_date - INTERVAL 1 YEAR, '%Y%m%d') ");

			sql1.append(" (date_format(sysdate(), '%Y%m%d') = date_format(b.terms_expire_date - INTERVAL 1 MONTH, '%Y%m%d'))\n");
			sql1.append(" OR (date_format(sysdate(), '%Y%m%d') = date_format(b.terms_expire_date - INTERVAL 3 DAY, '%Y%m%d')) ");

    		paramVO.setSQL1(sql1.toString());
    		
    		/*
    		//test용
	    	sql1 = new StringBuffer();
	    	sql1.append(" 1=1 ");
	    	*/
	    	
	    	paramVO.setSQL1(sql1.toString());
    	}
    	
    	paramVO.setEmailSendType(emailSendType);
    	
    	List<CmmEmailCronSvcTermsVO> termsList = null;
    	try{
	    	//정보제공동의 메일발송 대상 목록 조회
	    	termsList = cmmEmailSendService.selectCronSvcTermsList(paramVO);
    	}catch(Exception e){
    		result = "01";
    		log.debug("################정보제공 동의 email 발송  ###########################");
    		log.debug("정보제공동의 메일발송 대상 목록 조회 실패\ne="+e);
    		log.debug("###############################################################");
    	}
    	
    	if(termsList != null && termsList.size() > 0){
    		
    		StringBuffer termsHtml = new StringBuffer();
    		
    		String customerRegNo = "";
			
    		for(int row=0; row<termsList.size(); row++){
    			CmmEmailCronSvcTermsVO termsData = (CmmEmailCronSvcTermsVO) termsList.get(row);
    			
    			boolean sendFlag = false;
    			
    			//한개 행만 있을경우
    			if(termsList.size() == 1){
    				sendFlag = true;
    			}else{
    				//첫행이 아닐경우
    				if(row > 0){
    					//다음행으로 넘어간 경우 또는 마지막 행일 경우
    					if(!customerRegNo.equals(termsData.getCustomerRegno()) || (row + 1) == termsList.size()){
    						sendFlag = true;
    					}
    				}else{
    					//정보 셋팅
        				customerRegNo = termsData.getCustomerRegno();
    				}
    			}
    			
    			//발신 row 일 경우 : 1개행만 있을경우, 고객 동일정보의 마지막 행
    			if(sendFlag){
    				//html 리셋
    				termsHtml = new StringBuffer();
    				    				
    				//html 정보를 셋팅한다.
    				termsHtml.append(makeCronSvcTermsHtml(customerRegNo, termsList));
    				
    				//기존정보가 있을경우 메일 발송
    				if(!OppfStringUtil.isEmpty(customerRegNo) && !OppfStringUtil.isEmpty(termsHtml.toString())){
    					String customerName = "";
    					String customerEmail = "";
    					
    					//고객정보 가져오기
    					CmmEmailSendVO customerParamVO = new CmmEmailSendVO();
        				customerParamVO.setCustomerRegNo(customerRegNo);
        				
        				//고객정보 조회
        				CmmEmailSendVO customerVO = null;
        				try{
        					customerVO = cmmEmailSendService.selectCmmEmailCustomerInfo(customerParamVO);
        				}catch(Exception e){
        					result = "01";
        					log.debug("################정보제공 동의 email 발송  ###########################");
        					log.debug("고객정보 조회 실패");
        					log.debug("customerRegNo="+customerRegNo);
        					log.debug("emailSendType="+emailSendType);
        					log.debug("termsHtml="+termsHtml.toString());
        					log.debug("error="+e);
        		    		log.debug("###############################################################");
        				}
        				//고객정보가 있을경우에 처리
        				if(customerVO != null && !OppfStringUtil.isEmpty(customerVO.getCustomerName())){
        					customerName = customerVO.getCustomerName();
        					customerEmail = customerVO.getCustomerEmail();
        					
        					HashMap map = new HashMap();
        					map.put("customerRegNo", customerRegNo);
        					map.put("customerName", customerName);
        					map.put("customerEmail", customerEmail);
        					//test용
        					//map.put("customerEmail", "dj1521@naver.com");
        					map.put("emailSendType", emailSendType);
        					map.put("termsHtml", termsHtml.toString());
        					
        					log.debug("################정보제공 동의 email 발송 ###########################");
        					log.debug("customerRegNo="+customerRegNo);
        					log.debug("customerName="+customerName);
        					log.debug("customerEmail="+customerEmail);
        					log.debug("emailSendType="+emailSendType);
        					log.debug("termsHtml="+termsHtml.toString());
        					log.debug("#############################################################");
        					
        					//메일발송
        					result = cronSvcTermsEmailSand(request, map);
        				}
    				}
    				
    				//정보 셋팅
    				customerRegNo = termsData.getCustomerRegno();
    			}    			
    		}
    	}
    	
    	
    	return result;
    }
    
    /**
     * @Method Name        : makeCronSvcTermsHtml
     * @Method description : 메일의 동의서 정보의 html을 만든다.
     * @param              : map
     * 						 customerRegNo : 고객 reg no
     * 						 customerName  : 고객명
     *                       customerEmail : 고객 email
     *                       emailSendType : email 발신타입
     *                       termsHtml     : email 내용
     * @return             : String
     * @throws             : Exception
     */
    public String makeCronSvcTermsHtml(String customerRegNo, List<CmmEmailCronSvcTermsVO> termsList){
    	StringBuffer termsHtml = new StringBuffer();
    	
    	List<CmmEmailCronSvcTermsVO> dataList = new ArrayList<CmmEmailCronSvcTermsVO>();
    	
    	for(int i=0; i<termsList.size(); i++){
    		CmmEmailCronSvcTermsVO data = (CmmEmailCronSvcTermsVO) termsList.get(i);
    		
    		//해당 고객정보만의 list를 만든다.
    		if(customerRegNo.equals(data.getCustomerRegno())){
    			dataList.add(data);
    		}
    	}
    	
    	if(dataList != null){
    		String subcompanyCodeId = "";
    		String appId = "";
    		String pubcompanyCodeId = "";
    		    		
	    	for(int i=0; i<dataList.size(); i++){
	    		CmmEmailCronSvcTermsVO data = (CmmEmailCronSvcTermsVO) dataList.get(i);
	    		
	    		//subcompanyCodeIdFalg rowspan 했는지 여부
	    		boolean subcompanyCodeIdFalg = false;
	    		int subcompanyCodeIdRowspan = 0;
	    		
	    		termsHtml.append("<tr>");
	    		
	    		//핀테크 기업
	    		if(!subcompanyCodeId.equals(data.getSubcompanyCodeId())){
	    			int rowspanCnt = 0;
					for(int x=0; x<dataList.size(); x++){
						CmmEmailCronSvcTermsVO rowspanData = (CmmEmailCronSvcTermsVO) dataList.get(x);
						
						if(data.getSubcompanyCodeId().equals(rowspanData.getSubcompanyCodeId())){
							rowspanCnt++;
						}
					}
					
					termsHtml.append("<th rowspan='"+rowspanCnt+"' scope='row' style='font-weight:normal; color:#0c0c0c; border-bottom:1px solid #bec3c7; padding:8px;'>");
					termsHtml.append(data.getSubcompanyName());
					termsHtml.append("</th>");
					
					subcompanyCodeId = data.getSubcompanyCodeId();
					subcompanyCodeIdFalg = true;
					subcompanyCodeIdRowspan = rowspanCnt;
	    		}
	    		
	    		//핀테크 서비스
	    		if(subcompanyCodeId.equals(data.getSubcompanyCodeId()) && !appId.equals(data.getAppId())){
					int rowspanCnt = 0;
					for(int x=0; x<dataList.size(); x++){
						CmmEmailCronSvcTermsVO rowspanData = (CmmEmailCronSvcTermsVO) dataList.get(x);
						
						if(
							data.getSubcompanyCodeId().equals(rowspanData.getSubcompanyCodeId()) &&
							data.getAppId().equals(rowspanData.getAppId())
						){
							rowspanCnt++;
						}
					}
					
					termsHtml.append("<th rowspan='"+rowspanCnt+"' scope='row' style='font-weight:normal; color:#0c0c0c; border-bottom:1px solid #bec3c7; padding:8px;'>");
					termsHtml.append(data.getAppName());
					termsHtml.append("</th>");
					
					appId = data.getAppId();
				}
	    		
	    		//증권사 -> 같은 핀테크 서비스에 증권사가 다를경우
				if(subcompanyCodeId.equals(data.getSubcompanyCodeId()) && appId.equals(data.getAppId()) && !pubcompanyCodeId.equals(data.getPubcompanyCodeId())){
					int rowspanCnt = 0;
					for(int x=0; x<dataList.size(); x++){
						CmmEmailCronSvcTermsVO rowspanData = (CmmEmailCronSvcTermsVO) dataList.get(x);
						
						if(
							data.getSubcompanyCodeId().equals(rowspanData.getSubcompanyCodeId()) &&
						    data.getAppId().equals(rowspanData.getAppId()) &&
						    data.getPubcompanyCodeId().equals(rowspanData.getPubcompanyCodeId())
						){
							rowspanCnt++;
						}
					}
					
					termsHtml.append("<th rowspan='"+rowspanCnt+"' scope='row' style='font-weight:normal; color:#0c0c0c; border-bottom:1px solid #bec3c7; padding:8px;'>");
					termsHtml.append(data.getPubcompanyName());
					termsHtml.append("</th>");
					
					pubcompanyCodeId = data.getPubcompanyCodeId();
				}
	    		
				//가상계좌 -> 가상계좌 + <br> + 별칭
				termsHtml.append("<td style='color:#0c0c0c; border-bottom:1px solid #bec3c7; padding:8px;'>");
				termsHtml.append(data.getCustomerVtaccountNo());
				termsHtml.append("<br>/");
				termsHtml.append(data.getCustomerVtaccountAlias());
				termsHtml.append("</td>");
				
				if(subcompanyCodeIdFalg){
    				//핀테크 기업 1개당 약관 1개
    				termsHtml.append("<td rowspan='"+subcompanyCodeIdRowspan+"' style='color:#0c0c0c; border-bottom:1px solid #bec3c7; padding:8px;'>");
    				termsHtml.append(data.getTermsStartDate());
    				termsHtml.append("<br>~");
    				termsHtml.append(data.getTermsExpireDate());
					termsHtml.append("</td>");
				}
	    		
	    		
	    		termsHtml.append("</tr>");
	    		
	    	}
    	}
    	
    	
    	return termsHtml.toString();
    }
    
    /**
     * @Method Name        : cronSvcTermsEmailSand
     * @Method description : 대상자 메일 발송
     * @param              : map
     * 						 customerRegNo : 고객 reg no
     * 						 customerName  : 고객명
     *                       customerEmail : 고객 email
     *                       emailSendType : email 발신타입
     *                       termsHtml     : email 내용
     * @return             : String
     * @throws             : Exception
     */
    public String cronSvcTermsEmailSand(HttpServletRequest request, HashMap map){
    	String result = "00";
    	
    	String customerRegNo = OppfStringUtil.nullConvert(map.get("customerRegNo"));
    	String customerName = OppfStringUtil.nullConvert(map.get("customerName"));
    	String customerEmail = OppfStringUtil.nullConvert(map.get("customerEmail"));
    	String emailSendType = OppfStringUtil.nullConvert(map.get("emailSendType"));
    	String termsHtml = OppfStringUtil.nullConvert(map.get("termsHtml"));
    	
    	CmmEmailSendVO cmmEmailSendVO = new CmmEmailSendVO();
		cmmEmailSendVO.setEmailSendType(emailSendType); //이메일 유형 - com_email_temp_info 이메일 템플릿 조회
		
		cmmEmailSendVO.setReceiverName(customerName); //받는 사람 이름(실제 이메일에 셋팅됩니다.)
		cmmEmailSendVO.setReceiverEmail(customerEmail); //받는 사람 이메일(실제 이메일에 셋팅되고, 이 주소로 이메일이 발송됩니다.)			
		cmmEmailSendVO.setReceiverKind("G018001"); //수신자 종류 - G018001:일반사용자, G018002:기업사용자
		cmmEmailSendVO.setReceiverId(customerRegNo); //수신자 ID
		
		cmmEmailSendVO.setSenderKind("G017001"); //발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
		cmmEmailSendVO.setSenderId(customerRegNo); //발신자 ID
					
		cmmEmailSendVO.setSendId(customerRegNo); //최초 발신자 ID
		cmmEmailSendVO.setCreateId(customerRegNo); //생성자ID
		cmmEmailSendVO.setUpdateId(customerRegNo); //수정자ID
					
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
		
		cmmEmailSendVO.setTermsMailContent(termsHtml);
		try{
			//1.이메일 템플릿 조회 -> 2.이메일발송 유형 구분 -> 3.메일 발송 -> (성공시) -> 4.com_email_send_info 로그적재 관련
			CmmEmailSendVO eamilResultVO = cmmEmailSendService.cmmEmailSend(cmmEmailSendVO, request);
			if("100".equals(eamilResultVO.getMailCode())){ //데이터 연동 성공
				log.debug("################정보제공 동의 email 발송 성공#######################");
				log.debug("customerRegNo="+customerRegNo);
				log.debug("customerName="+customerName);
				log.debug("customerEmail="+customerEmail);
				log.debug("emailSendType="+emailSendType);
				log.debug("termsHtml="+termsHtml);
				log.debug("eamilResultVO.getMailCode()="+eamilResultVO.getMailCode());
				log.debug("#############################################################");
			}else{
				result = "01";
				log.debug("################정보제공 동의 email 발송 실패#######################");
				log.debug("customerRegNo="+customerRegNo);
				log.debug("customerName="+customerName);
				log.debug("customerEmail="+customerEmail);
				log.debug("emailSendType="+emailSendType);
				log.debug("termsHtml="+termsHtml);
				log.debug("eamilResultVO.getMailCode()="+eamilResultVO.getMailCode());
				log.debug("#############################################################");
			}
		}catch(Exception e){
			result = "01";
			log.debug("################정보제공 동의 email 발송 실패#######################");
			log.debug("customerRegNo="+customerRegNo);
			log.debug("customerName="+customerName);
			log.debug("customerEmail="+customerEmail);
			log.debug("emailSendType="+emailSendType);
			log.debug("termsHtml="+termsHtml);
			log.debug("e="+e);
			log.debug("#############################################################");
		}
		
		return result;
    }
    
}
