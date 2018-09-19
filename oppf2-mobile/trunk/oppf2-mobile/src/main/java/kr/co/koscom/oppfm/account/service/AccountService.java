package kr.co.koscom.oppfm.account.service;

import kr.co.koscom.oppfm.account.model.AccountReq;
import kr.co.koscom.oppfm.account.model.ComCompanyProfileReq;
import kr.co.koscom.oppfm.account.model.ComOauthTokenReq;
import kr.co.koscom.oppfm.account.model.ComOauthTokenRes;
import kr.co.koscom.oppfm.account.model.SptCustomerCompanyTermsProfileReq;
import kr.co.koscom.oppfm.account.model.SptCustomerCompanyTermsProfileRes;
import kr.co.koscom.oppfm.account.model.SvcApplReq;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.commoncode.model.CommonCodeReq;

public interface AccountService {


	/**
     * @Method Name        : getAccountList 
     * @Method description : [마이페이지] 신청한 가상계좌 리스트를 조회한다.
     * @param              : AccountReq
     * @return             : CommonResponse
     * @throws             : Exception
     */
    public CommonResponse getAccountList(AccountReq accountReq) throws Exception;

    /**
     * getComCompanyProfileList
     * @param comCompanyProfileReq
     * @return
     * @throws Exception
     */
	public CommonResponse getCompanyProfileList(ComCompanyProfileReq comCompanyProfileReq) throws Exception;
    
	/**
	 * getSptCustAccList
	 * @param svcApplReq
	 * @return
	 * @throws Exception
	 */
	public CommonResponse getSptCustAccList(String companyCodeId, String companyProfileRegNo, String customerRegNo) throws Exception;

	/**
	 * 
	 * @param companyCodeId
	 * @param companyProfileRegNo
	 * @param customerRegNo
	 * @return
	 * @throws Exception
	 */
	public CommonResponse getSptCustAccInfo(String companyCodeId, String companyProfileRegNo, String customerRegNo) throws Exception;

	
	/**
	 * selectCmmnFuncCode
	 * @param smmFuncReq
	 * @return
	 * @throws Exception
	 */
	public CommonResponse getCmmnFuncCode(CommonCodeReq commonCodeReq) throws Exception;
	
	/**
	 * 
	 * @param accountReq
	 * @return
	 * @throws Exception
	 */
	public CommonResponse getCustomerVtAccList(AccountReq accountReq) throws Exception;

	/**
	 * 
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public ComOauthTokenRes selectComOauthTokenInfo(ComOauthTokenReq paramVO) throws Exception;

	/**
	 * 
	 * @param sptCustomerCompanyTermsProfileReq
	 * @return
	 */
	public SptCustomerCompanyTermsProfileRes selectSvcCompanyTermsConsntList(SptCustomerCompanyTermsProfileReq sptCustomerCompanyTermsProfileReq);
	
	/**
	 * 
	 * @param paramVO
	 * @return
	 */
	public CommonResponse saveSvcCompanyTermsConsnt(SptCustomerCompanyTermsProfileReq paramVO) throws Exception;
	
	
	
}
