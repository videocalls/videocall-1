package kr.co.koscom.oppfm.faq.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.faq.model.FaqReq;
import kr.co.koscom.oppfm.faq.model.FaqRes;
import kr.co.koscom.oppfm.faq.service.FaqService;
import springfox.documentation.annotations.ApiIgnore;

/**
 * FaqController
 * <p>
 * Created by hanbyul.lee on 2017-04-24.
 * Modified by Yoojin Han on 2017-05-18.
 * 
 */
@Api(tags="faq-controller", description="자주 묻는 질문")

@RestController
public class FaqController {

	@Autowired
	FaqService faqService;
	
	/**
     * @Method Name        : getFaqList
     * @Method description : [FAQ목록:목록]정보를 조회한다.
     * @param              : FaqReq
     */
	@ApiImplicitParams({
        @ApiImplicitParam(name ="searchKeyword", dataType ="String", paramType ="query", value = "검색어" ),
        @ApiImplicitParam(name ="searchType", dataType ="String", paramType ="query", value = "검색 조건 분류", defaultValue = "G040001"),
        @ApiImplicitParam(name ="page", dataType ="String", paramType ="query", value = "페이지", defaultValue = "1", required=true ),
        @ApiImplicitParam(name ="size", dataType ="String", paramType ="query", value = "페이지 당 사이즈", defaultValue = "10", required=true )
	})
	@ApiOperation(value = "faq목록 조회", notes="faq list api", response=CommonResponse.class)
	@RequestMapping(value = "/apis/faq", method = RequestMethod.GET)
	public CommonResponse getFaqList(@ApiIgnore FaqReq faqReq, HttpServletRequest request) {
		
	    if(null == faqReq.getPage() || "".equals(faqReq.getPage())){
            throw new CommonException(ErrorCodeConstants.REQUIRED_PARAMETER_ERROR, new String[] {"page"});
        }else if(null == faqReq.getSize() || "".equals(faqReq.getSize())){
            throw new CommonException(ErrorCodeConstants.REQUIRED_PARAMETER_ERROR, new String[] {"size"});
        }
	    
		return faqService.getFaqList(faqReq);
	}

	/**
     * @Method Name        : getfaqDtl
     * @Method description : [FAQ상세]상세 조회한다.
     * @param              : String faqSeq
     * 포털상에서 사용되지 않는 메소드 입니다.
     */
	@ApiOperation(value = "faq상세 정보 조회", notes="faq detail api", response=CommonResponse.class)
	@RequestMapping(value = "/apis/faq/{faqSeq}", method = RequestMethod.GET)
	public CommonResponse getfaqDtl(@ApiParam(value="faq 시퀀스", defaultValue="2017020702", required=true) @PathVariable String faqSeq)  {
		//test)faqSeq:2017020702
	    FaqReq faqReq = new FaqReq();
	    faqReq.setFaqSeq(faqSeq);
		return faqService.getFaqDetail(faqReq);
	}

}
