package kr.co.koscom.oppfm.commoncode.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.commoncode.model.CommonCodeReq;
import kr.co.koscom.oppfm.commoncode.service.CommonCodeService;

/**
 * CommonCodeController
 * <p>
 * Created by Yoojin Han on 2017-05-11
 */

@Api(tags = "commonCode-controller", description = "코드 세팅 > 공통/기업 ")
@RequestMapping(value="/apis")
@RestController
public class CommonCodeController {
	
	@Autowired
	CommonCodeService commonCodeService;
	
	
	/**
	 * 공통 코드 조회
	 * @MethodName          getCommonCode
	 * @param               systemGrpCode 
	 * @return              CommonResponse ( commonCodeList, systemGrpCodeDesc )
	 */
	@ApiOperation(value = "공통 코드 조회", response=CommonResponse.class)
	@RequestMapping(value="/commonCodes/{systemGrpCode}" , method = RequestMethod.GET)
	public CommonResponse getCommonCode(@ApiParam(value = "시스템 그룹 코드", defaultValue = "G001", required=true) @PathVariable String systemGrpCode){
	    
	    CommonCodeReq commonCodeReq = new CommonCodeReq();
	    commonCodeReq.setSystemGrpCode(systemGrpCode);
	    
	    return commonCodeService.getCommonCode(commonCodeReq);
	}
	
	
	/**
	 * 공통 코드 상세 조회
	 * @MethodName          getCommonCodeDetail
	 * @param               systemGrpCode 
	 * @param               systemCode
	 * @return              CommonResponse
	 */
	@ApiOperation(value = "공통 코드 상세 조회", response=CommonResponse.class)
	@RequestMapping(value="/commonCode/{systemGrpCode}/{systemCode}" , method = RequestMethod.GET)
	public CommonResponse getCommonCodeDetail(
	        @ApiParam(value = "시스템 그룹 코드", defaultValue = "G001", required = true) @PathVariable String systemGrpCode, 
	        @ApiParam(value="시스템 코드" , defaultValue= "001", required=true) @PathVariable String systemCode){
	    
	    CommonCodeReq commonCodeReq = new CommonCodeReq();
        commonCodeReq.setSystemGrpCode(systemGrpCode);
        commonCodeReq.setSystemCode(systemCode);
        
	    return commonCodeService.getCommonCodeDetail(commonCodeReq);
	}
	
	
	   /**
     * 공통 코드 상세 조회
     * 공통 코드 7자리로 조회
     * @MethodName          getCommonCodeDetailFullCode
     * @param               commonCode (7자리)
     * @return              CommonResponse
     */
	@ApiOperation(value = "공통 코드 상세 조회-7자리", response=CommonResponse.class)
    @RequestMapping(value="/commonCode/{commonCode}" , method = RequestMethod.GET)
    public CommonResponse getCommonCodeDetailFullCode(@ApiParam(value = "시스템 그룹코드+시스템 코드", defaultValue = "G001002", required=true) @PathVariable String commonCode){

        String systemGrpCode = commonCode.substring(0,4);
        String systemCode = commonCode.substring(commonCode.length()-3,commonCode.length());

        CommonCodeReq commonCodeReq = new CommonCodeReq();
        commonCodeReq.setSystemGrpCode(systemGrpCode);
        commonCodeReq.setSystemCode(systemCode);
        
        return commonCodeService.getCommonCodeDetail(commonCodeReq);
    }
	
    
	/**
	 * 기업 코드 조회
	 * @MethodName 			getCompanyCodeList
	 * @param 				companyServiceType   // 기업핀테크서비스타입(=G002 + (001:둘다, 002:서비스제공자, 003:앱 개발자))
	 * @param               companyNameEngAlias  // 기업이름 Alias 영문 
	 * @return				CommonResponse (companyCodeList)
	 */
	@ApiOperation(value = "기업 코드 조회", response=CommonResponse.class)
	@RequestMapping(value="/commonCode/companys" , method = RequestMethod.GET)
	public CommonResponse getCompanyCodeList(
	        @ApiParam(value = "기업 핀테크서비스 타입 코드", required = false) @RequestParam(required=false) String companyServiceType ,
	        @ApiParam(value = "기업 이름 Alias 영문", required = false) @RequestParam(required=false) String companyNameEngAlias ){

	    CommonCodeReq commonCodeReq = new CommonCodeReq();
		commonCodeReq.setSearchCompanyServiceType(companyServiceType);
		commonCodeReq.setCompanyNameEngAlias(companyNameEngAlias);
		
		return commonCodeService.getCompanyCodeList(commonCodeReq);
	}
	
    /**
     * 기업 코드 조회
     * (기업 이름 Alias 영문 으로 조회)
     * @MethodName          getCompanyCode
     * @param               companyNameEngAlias 
     * @return              CommonResponse ( companyCodeList )
     */
/*    @ApiImplicitParams({
        @ApiImplicitParam(name ="companyNameEngAlias", dataType ="String", paramType ="query", value = "기업 이름 Alias 영문", defaultValue = "koscom", required=true )
    })
    @RequestMapping(value="/commonCode/company" , method = RequestMethod.GET)
    public CommonResponse getCompanyCode(@ApiParam() String companyNameEngAlias ){
        
        CommonCodeReq commonCodeReq = new CommonCodeReq();
        commonCodeReq.setCompanyNameEngAlias(companyNameEngAlias);
        
        return commonCodeService.getCompanyCodeList(commonCodeReq);
    }*/

}
