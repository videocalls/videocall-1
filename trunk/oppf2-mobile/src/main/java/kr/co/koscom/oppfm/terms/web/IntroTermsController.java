package kr.co.koscom.oppfm.terms.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.terms.model.IntroTermsInfoRes;
import kr.co.koscom.oppfm.terms.model.IntroTermsReq;
import kr.co.koscom.oppfm.terms.service.IntroTermsService;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;


/**
 * @author unionman
 *
 */
@Slf4j
@Api(value = "IntroTerms-Controller", description = "서비스 이용약관 및 개인정보취급방침", produces = "application/json")
@RestController
@RequestMapping("/apis")
public class IntroTermsController {

	@Autowired
	IntroTermsService termsService;
	

	/**
	 * 등록된 약관 및 업체별 약관 코드값 조회
	 * @param termsReq
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "서비스 이용약관 기본 및 업체 정보 조회", notes="서비스 이용약관 기본 및 업체 정보 조회 api", response=CommonResponse.class)
	@RequestMapping(value = "/terms/intro/services/compony", method = RequestMethod.GET)
	public CommonResponse getTermsServiceComponyList() {

	    return termsService.getTermsServiceComponyList();
	    
	}

	/**
	 * @param termsReq
	 * @param request
	 * @return
	 */
    @ApiImplicitParams({ 
            @ApiImplicitParam(name = "termsType", dataType = "String", paramType = "query", required = false, value = "약관 구분", defaultValue = "" ,allowableValues = "cust,comp"),
            @ApiImplicitParam(name = "companyCodeId", dataType = "String", paramType = "query", required = false, value = "기업 코드", defaultValue = ""),
            @ApiImplicitParam(name = "termsKey", dataType = "String", paramType = "query", required = false, value = "약관 번호", defaultValue = "") 
     })
	@ApiOperation(value = "서비스 이용약관 조회", notes="서비스 이용약관 list api", response=CommonResponse.class)
	@RequestMapping(value = "/terms/intro/services/contents", method = RequestMethod.GET)
	public CommonResponse getTermsServiceList(@ApiIgnore @Valid IntroTermsReq introTermsReq, HttpServletRequest request) {
        
        log.debug("** getTermsServiceList 조회");
        if(null == introTermsReq.getTermsType() || "".equals(introTermsReq.getTermsType())){
            throw new CommonException(ErrorCodeConstants.REQUIRED_PARAMETER_ERROR, new String[] {"termsType"});
        }else if("comp".equals(introTermsReq.getTermsType())){
            if(null == introTermsReq.getCompanyCodeId() || "".equals(introTermsReq.getCompanyCodeId())){
                throw new CommonException(ErrorCodeConstants.REQUIRED_PARAMETER_ERROR, new String[] {"companyCodeId"});
            }else if(null == introTermsReq.getTermsKey() || "".equals(introTermsReq.getTermsKey())){
                throw new CommonException(ErrorCodeConstants.REQUIRED_PARAMETER_ERROR, new String[] {"termsKey"});
            }
        }
	    
	    return termsService.getTermsServiceList(introTermsReq);
	}
	
	/**
	 * @param termsReq
	 * @param request
	 * @return
	 */
    @ApiImplicitParams({ 
        @ApiImplicitParam(name = "termsType", dataType = "String", paramType = "query", required = false, value = "약관 구분", defaultValue = "cust" ,allowableValues = "cust,comp"),
        @ApiImplicitParam(name = "companyCodeId", dataType = "String", paramType = "query", required = false, value = "기업 코드", defaultValue = ""),
        @ApiImplicitParam(name = "termsKey", dataType = "String", paramType = "query", required = false, value = "약관 번호", defaultValue = "") 
 })
	@ApiOperation(value = "개인정보취급방침 조회", notes="개인정보취급방침 조회 api", response=IntroTermsInfoRes.class)
	@RequestMapping(value = "/terms/intro/privacy", method = RequestMethod.GET)
	public CommonResponse getTermsPrivacy(@ApiIgnore IntroTermsReq introTermsReq, HttpServletRequest request) {
	    
	    
	    return termsService.getTermsServiceList(introTermsReq);
	}
	

}
