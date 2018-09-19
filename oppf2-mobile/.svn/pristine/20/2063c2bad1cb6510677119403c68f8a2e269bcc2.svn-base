package kr.co.koscom.oppfm.account.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.koscom.oppfm.account.model.AccountReq;
import kr.co.koscom.oppfm.account.model.ComCompanyProfileReq;
import kr.co.koscom.oppfm.account.model.SptCustomerCompanyTermsProfileReq;
import kr.co.koscom.oppfm.account.service.AccountService;
import kr.co.koscom.oppfm.cmm.annotation.CheckAuth;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CookieUtil;
import kr.co.koscom.oppfm.commoncode.model.CommonCodeReq;
import kr.co.koscom.oppfm.login.model.LoginRes;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;


@Api(value = "Account-Controller", description = "가상계좌 관리")
@RequestMapping(value="/apis")
@RestController
@Slf4j
public class AccountController {

	@Autowired
	AccountService accountService;

	/**
	 * getAccountList
	 * 가상계좌 목록 조회
	 * 
	 * @param accountReq
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
    @ApiOperation(value="가상계좌 조회", response = CommonResponse.class)
    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public CommonResponse getAccounts(AccountReq accountReq,HttpServletRequest request) throws Exception{

        /*로그인 정보*/
        LoginRes loginInfo = CookieUtil.getLoginInfo(request);
        accountReq.setCustomerRegNo(loginInfo.getCustomerRegNo());
        
        
        return accountService.getAccountList(accountReq);
    }
	
	/**
	 * getAccountList
	 * 가상계좌 목록 조회
	 * 
	 * @param accountReq
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
    @ApiOperation(value="가상계좌 목록 조회", response = CommonResponse.class)
    @RequestMapping(value = "/accountList", method = RequestMethod.GET)
    public CommonResponse getAccountList(AccountReq accountReq,HttpServletRequest request) throws Exception{

        /*로그인 정보*/
        LoginRes loginInfo = CookieUtil.getLoginInfo(request);
        accountReq.setCustomerRegNo(loginInfo.getCustomerRegNo());
        
        
        return accountService.getCustomerVtAccList(accountReq);
    }
	
	/**
	 * getComCompanyProfileList
	 * 금융투자회사 리스트 조회
	 * @param accountReq
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
    @ApiOperation(value="금융투자회사 목록 조회", response = CommonResponse.class)
    @RequestMapping(value = "/account/companies", method = RequestMethod.GET)
    public CommonResponse getCompanyProfileList(@ApiIgnore ComCompanyProfileReq comCompanyProfileReq, @ApiIgnore HttpServletRequest request) throws Exception{

		comCompanyProfileReq.setCompanyServiceType("G002002");

		CommonResponse commonResponse = accountService.getCompanyProfileList(comCompanyProfileReq);
        return commonResponse;
        
    }
	

	
	/**
	 * getSptCustAccList
	 * [일반회원가상계좌+기업코드]가상계좌 목록 조회
	 * @param accountReq
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
    @ApiOperation(value="[일반회원가상계좌+기업코드]가상계좌 목록 조회", response = CommonResponse.class)
    @RequestMapping(value = "/account/{companyCodeId}/custAccouts", method = RequestMethod.GET)
    public CommonResponse getSptCustAccList(@PathVariable String companyCodeId,@RequestParam String companyProfileRegNo, @ApiIgnore HttpServletRequest request) throws Exception{

		/*로그인 정보 가져오는 방법(공통)*/
        LoginRes loginRes = CookieUtil.getLoginInfo(request);
		
        String customerRegNo = loginRes.getCustomerRegNo();
	    
	    CommonResponse commonResponse = accountService.getSptCustAccList(companyCodeId,companyProfileRegNo, customerRegNo);
	    
        return commonResponse;
        
    }
	

	

	
	/**
	 * getSptCustAccInfo
	 * [일반회원가상계좌+기업코드]계좌 직접 입력
	 * @param accountReq
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
    @ApiOperation(value="[일반회원가상계좌+기업코드]가상계좌 직접 입력", response = CommonResponse.class)
    @RequestMapping(value = "/account/{companyCodeId}/realAccInfo", method = RequestMethod.GET)
    public CommonResponse getSptCustAccInfo(@PathVariable String companyCodeId,@RequestParam String companyProfileRegNo, @ApiIgnore HttpServletRequest request) throws Exception{

		/*로그인 정보 가져오는 방법(공통)*/
        LoginRes loginRes = CookieUtil.getLoginInfo(request);
		
        String customerRegNo = loginRes.getCustomerRegNo();
	    
	    CommonResponse commonResponse = accountService.getSptCustAccInfo(companyCodeId,companyProfileRegNo, customerRegNo);
	    
        return commonResponse;
        
    }
	


	/**
	 * getAccCode
	 * 계좌유형 조회
	 * @param accountReq
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
    @ApiOperation(value="계좌유형 조회", response = CommonResponse.class)
    @RequestMapping(value = "/accCategory", method = RequestMethod.GET)
    public CommonResponse getAccCode(@ApiIgnore HttpServletRequest request) throws Exception{

        LoginRes loginRes = CookieUtil.getLoginInfo(request);
		
	    //셋팅 공통코드:실계좌유형
        CommonCodeReq commonCodeReq = new CommonCodeReq();
        commonCodeReq.setSystemGrpCode("G010");
	    
	    CommonResponse commonResponse = accountService.getCmmnFuncCode(commonCodeReq);
	    
        return commonResponse;
        
    }
	

    /**
     * @Method Name        : saveSvcCompanyTermsConsnt
     * @Method description : [핀테크서비스신청:약관동의] 기업약관을 저장한다. 
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/account/saveSvcCompanyTermsConsnt",method = {RequestMethod.POST, RequestMethod.GET})
	public CommonResponse saveSvcCompanyTermsConsnt(SptCustomerCompanyTermsProfileReq paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginRes loginRes = CookieUtil.getLoginInfo(request);
        
        String customerRegNo = loginRes.getCustomerRegNo();
        
        
        //사용자 정보 셋팅
        paramVO.setCustomerRegNo(customerRegNo);
        paramVO.setCreateId(customerRegNo);
        
        CommonResponse commonResponse = accountService.saveSvcCompanyTermsConsnt(paramVO);
        		
	    return commonResponse;
	}

	
	
	
}
