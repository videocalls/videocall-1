package kr.co.koscom.oppfm.terms.service.impl;

import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonCodeConstants;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.cmm.util.OppfStringUtil;
import kr.co.koscom.oppfm.terms.dao.TermsContentsMapper;
import kr.co.koscom.oppfm.terms.model.MobileTerms;
import kr.co.koscom.oppfm.terms.model.TermsReq;
import kr.co.koscom.oppfm.terms.model.TermsRes;
import kr.co.koscom.oppfm.terms.service.TermsContentsService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

/**
 * ClassName : TermsContentsServiceImpl
 * *
 * Description :
 * <p>
 * Created by LSH on 2017. 5. 18..
 */
@Service
public class TermsContentsServiceImpl implements TermsContentsService{

    @Autowired
    private TermsContentsMapper termsContentsMapper;

    @Autowired
    private MessageUtil messageUtil;

	@Resource(name = "config")
	private Properties properties;
	

    /**
     * 회원 약관
     */
    @Override
    public CommonResponse getTermsContents(TermsReq termsReq) {


        TermsReq req = getCustomerTermsType(termsReq);
        

        List<TermsRes> termsList = termsContentsMapper.selectTermsContentsList(req);
        
        Map<String, Object> body = new HashMap<>();
        body.put("termsList", termsList);
        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

	/**
     * 약관 상세
     */
    @Override
    public CommonResponse getTermsContentDetail(TermsReq termsReq) {
        Map<String, Object> body = new HashMap<>();

        TermsRes termsDetail = termsContentsMapper.selectTermsContentDetail(termsReq);

        body.put("termsDetail", termsDetail);
        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }
    
    /**
     * 회원 동의 종류
     * @param termsReq
     * @return
     */
    public TermsReq getCustomerTermsType(TermsReq termsReq){

        List<String> customerTermsType = new ArrayList<String>();


        if(OppfStringUtil.isEmpty(termsReq.getCustomerTermsSystemKind()) || 
        		CommonCodeConstants.GRP_TYPE_SVC.equals(termsReq.getCustomerTermsSystemKind())
		){
        	
        	termsReq.setCustomerTermsSystemKind(CommonCodeConstants.GRP_TYPE_SVC);
//        	서비스이용약관
            customerTermsType.add(CommonCodeConstants.TERMS_TYPE_SERVICE);
//          개인정보 수집 이용
            customerTermsType.add(CommonCodeConstants.TERMS_TYPE_INDIVIDUAL);
//          제3자 제공
            customerTermsType.add(CommonCodeConstants.TERMS_TYPE_THIRD);
        	
        	//termsReq.setCustomerTermsSystemKind("G003002");
        } else {

            customerTermsType.add(CommonCodeConstants.TERMS_TYPE_SERVICE);
            customerTermsType.add(CommonCodeConstants.TERMS_TYPE_INDIVIDUAL);
            customerTermsType.add(CommonCodeConstants.TERMS_TYPE_THIRD);
            
        }
        

        termsReq.setSearchCustomerTermsTypeList(customerTermsType);
        
    	return termsReq;
    }
    

    /**
     * 통신사 이용 약관 조회
     */
	public CommonResponse getMobileTerms(MobileTerms mobileTerms) {


        Map<String, Object> body = new HashMap<>();
        try{

        	String mobileCopany = "";
        	for(int t=0; t<3; t++){

                List<MobileTerms> termsList = new ArrayList();

        		if(t==0) mobileCopany = "skm";
        		if(t==1) mobileCopany = "ktm";
        		if(t==2) mobileCopany = "lgm";
        		
	        	for(int i=1; i<5; i++){
		            Document doc = Jsoup.connect("http://203.234.219.124/app/agree/app_agree_m_"+mobileCopany+".jsp?gubun=0"+i).get(); 
		            String div = doc.getElementsByTag("body").toString();
		            div = div.replaceAll("<body>", "<div>");
		            div = div.replaceAll("</body>", "</div>");
		            
		            MobileTerms term = new MobileTerms();
		            term.setMobileTermsContent(div);
		            term.setMobileTermsType("G04200"+i);

		            /** 임시 하드 코딩 **/
		        	if(i==1) term.setMobileTermsSubject("개인정보 수집 이용 동의");
		        	if(i==2) term.setMobileTermsSubject("고유식별정보 처리 동의");
		        	if(i==3) term.setMobileTermsSubject("통신사 이용약관 동의");
		        	if(i==4) term.setMobileTermsSubject("서비스 이용약관 동의");
		            
		            termsList.add(term);	            
	        	}

	        	if(t==0) body.put("skTermsList", termsList);
	        	if(t==1) body.put("ktTermsList", termsList);
	        	if(t==2) body.put("lgTermsList", termsList);
	        	
        	}
        	
        }catch (Exception e){
            throw new CommonException(ErrorCodeConstants.GET_MOBILE_TERMS_ERROR, null);
        }
        
        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

    /**
     * 서비스 이용약관,  재동의
     * */

}
