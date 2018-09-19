package kr.co.koscom.oppfm.terms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.cmm.util.OppfStringUtil;
import kr.co.koscom.oppfm.terms.dao.IntroTermsMapper;
import kr.co.koscom.oppfm.terms.model.IntroTermsComponyRes;
import kr.co.koscom.oppfm.terms.model.IntroTermsInfoRes;
import kr.co.koscom.oppfm.terms.model.IntroTermsReq;
import kr.co.koscom.oppfm.terms.service.IntroTermsService;
import lombok.extern.slf4j.Slf4j;



/**
 * @author unionman
 *
 */
@Service
@Slf4j
public class IntroTermsServiceImpl implements IntroTermsService {

    @Autowired
    private IntroTermsMapper introTermsMapper;
    
    @Autowired
    MessageUtil messageUtil;

    /* (non-Javadoc)
     * @see kr.co.koscom.oppfm.terms.service.TermsService#getTermsServiceList()
     */
    @Override
    public CommonResponse getTermsServiceList(IntroTermsReq termsReq) {
        // TODO Auto-generated method stub

        CommonResponse response = new CommonResponse();
        Map<String, Object> body = new HashMap<>();

        log.debug("termsReq.getTermsType() == {} ", termsReq.getTermsType());
        
        IntroTermsInfoRes introTermsInfoRes = null;
        
        if( OppfStringUtil.equals("cust", termsReq.getTermsType()) ) {
            introTermsInfoRes = introTermsMapper.selectTermsServiceContentsDefault(termsReq);
        } else {
            introTermsInfoRes = introTermsMapper.selectTermsServiceContentsCompony(termsReq);
        }
        
        if(introTermsInfoRes == null ){
            throw new CommonException(ErrorCodeConstants.NOT_FOUND_EXCEPTION, new String[] { "termsContents" });
        }
        body.put("termsContents", introTermsInfoRes);

        // 참조 쿼리     <select id="cmm.IntroDAO.selectIntroServiceTermsCodeList" parameterClass="IntroVO" resultClass="IntroVO">
        response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
        return  response;
    }

    
    
    /* (non-Javadoc)
     * @see kr.co.koscom.oppfm.terms.service.TermsService#getTermsServiceComponyList()
     */
    @Override
    public CommonResponse getTermsServiceComponyList() {
        // TODO Auto-generated method stub
        CommonResponse response = new CommonResponse();
        Map<String, Object> body = new HashMap<>();

        List<IntroTermsComponyRes> datas = introTermsMapper.selectTermsServiceComponyList(); 
        body.put("termsComponyList", datas);

        response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
        return  response;
    }

}
