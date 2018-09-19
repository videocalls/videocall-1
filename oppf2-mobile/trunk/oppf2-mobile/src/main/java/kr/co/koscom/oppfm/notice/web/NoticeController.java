package kr.co.koscom.oppfm.notice.web;

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
import kr.co.koscom.oppfm.notice.model.NoticeReq;
import kr.co.koscom.oppfm.notice.service.NoticeService;
import springfox.documentation.annotations.ApiIgnore;

/**
 * NoticeController
 * <p>
 * Created by Yoojin Han on 2017-04-20.
 */

@Api(tags = "notice-controller", description = "공지사항")
@RequestMapping(value="/apis")
@RestController
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    /**
     * 공지사항 목록 조회
     * @MethodName      : getNoticeList        
     * @param           : noticeReq
     * @return          : CommonResponse
     */
    /*
     * 기능별 필요 파라미터
     * 공통 ) page, size
     * 1. 공지사항 목록 조회 - 포털과 동일) -
     * 2. 공지사항 목록 조회 - 조건 조회시) searchType, searchKeyword
     * 3. 공지사항 목록 조회 - 팝업 제외시) noticePopYn(N)
     * 4. 공지사항 팝업 목록 조회 - noticePopYn(Y)
     */
    @ApiImplicitParams({
        @ApiImplicitParam(name ="searchKeyword", dataType ="String", paramType ="query", value = "검색어" ),
        @ApiImplicitParam(name ="searchType", dataType ="String", paramType ="query", value = "검색 조건", defaultValue = "G040001" ),
        @ApiImplicitParam(name ="page", dataType ="String", paramType ="query", value = "페이지", defaultValue = "1"),
        @ApiImplicitParam(name ="size", dataType ="String", paramType ="query", value = "페이지 당 사이즈", defaultValue = "10" ),
        @ApiImplicitParam(name ="noticePopYn", dataType ="String", paramType ="query", value = "공지사항 팝업 여부"),
    })
    @ApiOperation(value = "notice 목록 조회", response=CommonResponse.class)
    @RequestMapping(value="/notices", method = RequestMethod.GET)
  	public CommonResponse getNoticeList(@ApiIgnore NoticeReq noticeReq) throws Exception {
        
        if(null == noticeReq.getPage() || "".equals(noticeReq.getPage())){
            throw new CommonException(ErrorCodeConstants.REQUIRED_PARAMETER_ERROR, new String[] {"page"});
        }else if(null == noticeReq.getSize() || "".equals(noticeReq.getSize())){
            throw new CommonException(ErrorCodeConstants.REQUIRED_PARAMETER_ERROR, new String[] {"size"});
        }
        
    	return noticeService.getNotiList(noticeReq);
    }
    

    /**
     * 공지사항 상세 조회
     * @MethodName        : getNoticeDetail      
     * @param             : String noticeSeq
     * @return            : CommonResponse
     */
    @ApiOperation(value = "notice 상세 조회",  response=CommonResponse.class)
    @RequestMapping(value="/notice/{noticeSeq}", method = RequestMethod.GET)
    private CommonResponse getNoticeDetail(@ApiParam(value="공지사항 시퀀스", defaultValue="2016082410", required=true) @PathVariable String noticeSeq ) throws Exception {
    	NoticeReq noticeReq = new NoticeReq();
    	noticeReq.setNoticeSeq(noticeSeq);
    	
    	return noticeService.getNotiDetail(noticeReq);
    }

    
}
