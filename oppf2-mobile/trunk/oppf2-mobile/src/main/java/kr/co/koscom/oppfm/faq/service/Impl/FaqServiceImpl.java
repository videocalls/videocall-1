package kr.co.koscom.oppfm.faq.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.faq.dao.FaqMapper;
import kr.co.koscom.oppfm.faq.model.FaqReq;
import kr.co.koscom.oppfm.faq.model.FaqRes;
import kr.co.koscom.oppfm.faq.service.FaqService;

/**
 * FaqServiceImpl
 * <p>
 * Created by hanbyul.lee on 2017-04-24.
 */
@Service
public class FaqServiceImpl implements FaqService {
	
	@Autowired
	private FaqMapper faqMapper;
	
	@Autowired
	MessageUtil messageUtil;
	
	/**
     * @Method Name        : getFaqList
     * @Method description : [FAQ목록:목록]정보를 조회한다.
     * @param              : FaqReq
     * @return             : CommonResponse
     */
	@Override
    @Transactional
    public CommonResponse getFaqList(FaqReq faqReq) {
		Map<String, Object> body = new HashMap<>();
        int faqListTotalCount = faqMapper.selectFaqListTotalCount(faqReq);
        List<FaqRes> faqList = faqMapper.selectFaqList(faqReq);
        
        body.put("faqList", faqList);

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body, 
                faqReq.getPageInfo(), faqListTotalCount, faqList.size());
	}

	
	/**
     * @Method Name        : getFaqDetail
     * @Method description : [FAQ상세:상세]정보를 조회한다.
     * @param              : FaqReq faqReq
     * @return             : CommonResponse
     */
	@Override
    public CommonResponse getFaqDetail(FaqReq faqReq) {
	    
		Map<String, Object> body = new HashMap<>();
		List<FaqRes> faqDetail = faqMapper.selectFaqList(faqReq);
		
		if(!(ObjectUtils.isEmpty(faqDetail))){
		    body.put("faqDetail", faqDetail);
		}else{
		    throw new CommonException(ErrorCodeConstants.NOT_FOUND_EXCEPTION,new String[] {"faq Detail"});
		}
        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

}
