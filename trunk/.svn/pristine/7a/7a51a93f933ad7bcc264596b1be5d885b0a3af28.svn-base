package kr.co.koscom.oppfm.account.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppfm.account.dao.AccountMapper;
import kr.co.koscom.oppfm.account.model.AccountReq;
import kr.co.koscom.oppfm.account.model.AccountRes;
import kr.co.koscom.oppfm.account.model.ComCompanyProfileReq;
import kr.co.koscom.oppfm.account.model.ComCompanyProfileRes;
import kr.co.koscom.oppfm.account.model.ComOauthTokenReq;
import kr.co.koscom.oppfm.account.model.ComOauthTokenRes;
import kr.co.koscom.oppfm.account.model.SptCustomerCompanyTermsProfileReq;
import kr.co.koscom.oppfm.account.model.SptCustomerCompanyTermsProfileRes;
import kr.co.koscom.oppfm.account.model.SvcApplReq;
import kr.co.koscom.oppfm.account.model.SvcApplRes;
import kr.co.koscom.oppfm.account.service.AccountService;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.commoncode.dao.CommonCodeMapper;
import kr.co.koscom.oppfm.commoncode.model.CommonCodeReq;
import kr.co.koscom.oppfm.commoncode.model.CommonCodeRes;
import kr.co.koscom.oppfm.member.dao.MemberMapper;
import kr.co.koscom.oppfm.member.model.MemberReq;
import kr.co.koscom.oppfm.member.model.MemberRes;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountMapper accountMapper;
	
	@Autowired
	CommonCodeMapper commonCodeMapper;

    @Autowired
    private MemberMapper memberMapper;
    
	@Autowired
	MessageUtil messageUtil;

	/**
	 * @Method Name : selectAccountList
	 * @Method description : [마이페이지] 일반회원가상계좌+기업코드 목록정보를 조회한다.
	 * @param :
	 *            AccountReq
	 * @return : CommonResponse
	 * @throws :
	 *             Exception
	 */
	@Override
	public CommonResponse getAccountList(AccountReq accountReq) throws Exception {
		
		Map<String, Object> body = new HashMap<>();

		// 금융투자사목록 정보 취득
		ComCompanyProfileReq pComCompanyProfileVO = new ComCompanyProfileReq();
        pComCompanyProfileVO.setCompanyProfileRegNo(accountReq.getCompanyProfileRegNo());
        pComCompanyProfileVO.setCompanyCodeId(accountReq.getCustomerRegNo());
        ComCompanyProfileRes rsComCompanyProfileVO = accountMapper.selectComCompanyProfileInfo(pComCompanyProfileVO);
        body.put("ComCompanyProfileRes", rsComCompanyProfileVO);

        // oauthToken정보 취득
        ComOauthTokenReq pComOauthTokenVO = new ComOauthTokenReq();
        pComOauthTokenVO.setCustomerRegNo(accountReq.getCustomerRegNo());
        ComOauthTokenRes rsComOauthTokenVO = accountMapper.selectComOauthTokenInfo(pComOauthTokenVO);
        body.put("rsComOauthTokenVO", rsComOauthTokenVO);

        // 회원 상세 정보 조회
        MemberReq memberReq = new MemberReq(); 
        memberReq.setCustomerRegNo(accountReq.getCustomerRegNo());
		MemberRes memberRes = memberMapper.selectUserInfo(memberReq);
        body.put("memberRes", memberRes);
		
        
		List<AccountRes> resultAccountList = accountMapper.selectAccountList(accountReq);
		int accountListTotalCount = accountMapper.selectAccountListTotalCount(accountReq);
		body.put("resultAccountList", resultAccountList);
		

		
		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body, accountReq.getPageInfo(), accountListTotalCount, resultAccountList.size());
	}

	/**
	 * @Method Name : selectAccountList
	 * @Method description : [마이페이지] 일반회원가상계좌+기업코드 목록정보를 조회한다.
	 * @param :
	 *            AccountReq
	 * @return : CommonResponse
	 * @throws :
	 *             Exception
	 */
	@Override
	public CommonResponse getCompanyProfileList(ComCompanyProfileReq comCompanyProfileReq) throws Exception {
		
		Map<String, Object> body = new HashMap<>();

		log.debug("comCompanyProfileReq = {}",comCompanyProfileReq.getCompanyServiceType());

        List<ComCompanyProfileRes> comCompanyProfileResList = accountMapper.selectComCompanyProfileList(comCompanyProfileReq);
        
		
		body.put("comCompanyProfileResList", comCompanyProfileResList);

		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
		
	}


	/**
	 * @Method Name : getSptCustAccList
	 * @Method description : [마이페이지] 일반회원가상계좌+기업코드 목록정보를 조회한다.
	 * @param :
	 *            AccountReq
	 * @return : CommonResponse
	 * @throws :
	 *             Exception
	 */
	@Override
	public CommonResponse getSptCustAccList(String companyCodeId, String companyProfileRegNo, String customerRegNo) throws Exception {
		
		Map<String, Object> body = new HashMap<>();

        log.debug("getSptCustAccList");
		// 금융투자사목록 정보 취득
		ComCompanyProfileReq pComCompanyProfileVO = new ComCompanyProfileReq();
        pComCompanyProfileVO.setCompanyProfileRegNo(companyProfileRegNo);
        pComCompanyProfileVO.setCompanyCodeId(companyCodeId);
        ComCompanyProfileRes rsComCompanyProfileVO = accountMapper.selectComCompanyProfileInfo(pComCompanyProfileVO);
        body.put("ComCompanyProfileRes", rsComCompanyProfileVO);

        CommonCodeReq commonCodeReq = new CommonCodeReq();
        commonCodeReq.setSystemGrpCode("G010");
		List<CommonCodeRes> cmmAccTypeList = commonCodeMapper.selectCommonCode(commonCodeReq);
		body.put("cmmAccTypeList", cmmAccTypeList);
		
       		
        // 일반회원가상계좌+기업코드 목록정보 취득
		SvcApplReq svcApplReq = new SvcApplReq();
		svcApplReq.setCustomerRegNo(customerRegNo);
		svcApplReq.setCompanyCodeId(companyCodeId);

		List<SvcApplRes> sptCustAccList = accountMapper.selectSptCustAccList(svcApplReq);
		body.put("sptCustAccList", sptCustAccList);
		
        // oauthToken정보 취득
        ComOauthTokenReq pComOauthTokenVO = new ComOauthTokenReq();
        pComOauthTokenVO.setCustomerRegNo(customerRegNo);
        ComOauthTokenRes rsComOauthTokenVO = accountMapper.selectComOauthTokenInfo(pComOauthTokenVO);
        body.put("rsComOauthTokenVO", rsComOauthTokenVO);

        // 회원 상세 정보 조회
        MemberReq memberReq = new MemberReq(); 
        memberReq.setCustomerRegNo(customerRegNo);
		MemberRes memberRes = memberMapper.selectUserInfo(memberReq);
        body.put("memberRes", memberRes);

        // 약관 정보 조회 
        log.debug("약관 정보 조회 ");
        SptCustomerCompanyTermsProfileReq sptCustomerCompanyTermsProfileReq = new SptCustomerCompanyTermsProfileReq();
        sptCustomerCompanyTermsProfileReq.setCustomerRegNo(customerRegNo);
        sptCustomerCompanyTermsProfileReq.setCompanyCodeId(companyCodeId);
        SptCustomerCompanyTermsProfileRes sptCustomerCompanyTermsProfileRes = selectSvcCompanyTermsConsntList(sptCustomerCompanyTermsProfileReq);
        body.put("sptCustomerCompanyTermsProfileRes", sptCustomerCompanyTermsProfileRes);

		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
		
	}
	


	/**
	 * @Method Name : getSptCustAccList
	 * @Method description : [마이페이지] 일반회원가상계좌+기업코드 목록정보를 조회한다.
	 * @param :
	 *            AccountReq
	 * @return : CommonResponse
	 * @throws :
	 *             Exception
	 */
	@Override
	public CommonResponse getSptCustAccInfo(String companyCodeId, String companyProfileRegNo, String customerRegNo) throws Exception {
		
		Map<String, Object> body = new HashMap<>();


        
		// 금융투자사목록 정보 취득
		ComCompanyProfileReq pComCompanyProfileVO = new ComCompanyProfileReq();
        pComCompanyProfileVO.setCompanyProfileRegNo(companyProfileRegNo);
        pComCompanyProfileVO.setCompanyCodeId(companyCodeId);
        ComCompanyProfileRes rsComCompanyProfileVO = accountMapper.selectComCompanyProfileInfo(pComCompanyProfileVO);
        body.put("ComCompanyProfileRes", rsComCompanyProfileVO);
        
        
        CommonCodeReq commonCodeReq = new CommonCodeReq();
        commonCodeReq.setSystemGrpCode("G010");
		List<CommonCodeRes> cmmAccTypeList = commonCodeMapper.selectCommonCode(commonCodeReq);
		body.put("cmmAccTypeList", cmmAccTypeList);
		
       		
        // oauthToken정보 취득
        log.debug("oauthToken정보 취득");
        ComOauthTokenReq pComOauthTokenVO = new ComOauthTokenReq();
        pComOauthTokenVO.setCustomerRegNo(customerRegNo);
        ComOauthTokenRes rsComOauthTokenVO = accountMapper.selectComOauthTokenInfo(pComOauthTokenVO);
        body.put("rsComOauthTokenVO", rsComOauthTokenVO);

        // 회원 상세 정보 조회
        log.debug("회원 상세 정보 조회");
        MemberReq memberReq = new MemberReq(); 
        memberReq.setCustomerRegNo(customerRegNo);
		MemberRes memberRes = memberMapper.selectUserInfo(memberReq);
        body.put("memberRes", memberRes);
        

        // 약관 정보 조회 
        log.debug("약관 정보 조회 ");
        SptCustomerCompanyTermsProfileReq sptCustomerCompanyTermsProfileReq = new SptCustomerCompanyTermsProfileReq();
        sptCustomerCompanyTermsProfileReq.setCustomerRegNo(customerRegNo);
        sptCustomerCompanyTermsProfileReq.setCompanyCodeId(companyCodeId);
        SptCustomerCompanyTermsProfileRes sptCustomerCompanyTermsProfileRes = selectSvcCompanyTermsConsntList(sptCustomerCompanyTermsProfileReq);
        body.put("sptCustomerCompanyTermsProfileRes", sptCustomerCompanyTermsProfileRes);

		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
		
	}
	
	
	@Override
	public CommonResponse getCmmnFuncCode(CommonCodeReq commonCodeReq) throws Exception{
		
		Map<String, Object> body = new HashMap<>();

		log.debug("commonCodeReq.getSystemGrpCode =  {} ", commonCodeReq.getSystemGrpCode());
		List<CommonCodeRes> cmmAccTypeList = commonCodeMapper.selectCommonCode(commonCodeReq);
		
		body.put("cmmAccTypeList", cmmAccTypeList);

		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
		
	}
	

	@Override
	public CommonResponse getCustomerVtAccList(AccountReq accountReq) throws Exception {
		
		Map<String, Object> body = new HashMap<>();

		List<AccountRes> list = new ArrayList();
		
		List<AccountRes> compList = accountMapper.selectCompList(accountReq);
		
		int compCnt = 0;
		int vtAccountCnt = 0;
		
		if(compList != null && compList.size() > 0){
			compCnt = compList.size();
			for(int i=0; i<compList.size(); i++){
				AccountRes res = new AccountRes();
				res = (AccountRes)compList.get(i);
								
				AccountReq req = new AccountReq();
				req.setCustomerRegNo(accountReq.getCustomerRegNo());
				req.setCompanyCodeRegNo(res.getCompanyCodeRegNo());
				List<AccountRes> compVtAccountList = accountMapper.selectCompVtAccountList(req);
				
				if(compList != null && compVtAccountList.size() > 0){
					vtAccountCnt = vtAccountCnt + compVtAccountList.size();
					res.setAccountResList(compVtAccountList);
				}
				
				list.add(res);
				
			}
			
		}		
		

		body.put("resultAccountList", list);
		body.put("compCnt", compCnt);
		body.put("vtAccountCnt", vtAccountCnt);
		
		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
	}



   /**
     * @Method Name        : selectComOauthTokenInfo
     * @Method description : [OAuthToken]정보를 조회한다.
     * @param              : ComOauthTokenVO
     * @return             : ComOauthTokenVO
     * @throws             : Exception
     */
    @Override
    public ComOauthTokenRes selectComOauthTokenInfo(ComOauthTokenReq paramVO) throws Exception{
        ComOauthTokenRes comOauthTokenRes = accountMapper.selectTokenInfo(paramVO);
        return comOauthTokenRes;
    }
    
    
    
    @Override
    public SptCustomerCompanyTermsProfileRes selectSvcCompanyTermsConsntList(SptCustomerCompanyTermsProfileReq sptCustomerCompanyTermsProfileReq){
    	SptCustomerCompanyTermsProfileRes sptCustomerCompanyTermsProfileRes = accountMapper.selectSvcCompanyTermsConsntList(sptCustomerCompanyTermsProfileReq);
        return sptCustomerCompanyTermsProfileRes;
    }

    

    /**
     * 
     */
    @Transactional
	public CommonResponse saveSvcCompanyTermsConsnt(SptCustomerCompanyTermsProfileReq paramVO) throws Exception{
		int result = -1;
        
        
        /**
         * 1. 기업약관 정보 확인
         * 2. 기업약관 정보 등록 or 수정
         */
        
        //기본 param을 가져온다.
		//기본정보 셋팅
		String companyTermsType = "G031001";		//기업 서비스 이용약관
		paramVO.setCompanyTermsType(companyTermsType);
		paramVO.setCompanyTermsAuthYn("Y");
		
		//1. 기업약관 정보 확인
		int cnt = accountMapper.checkSptCustomerCompanyTermsProfile(paramVO);
		
		//기존에 있으면 update
		if(cnt > 0){
			result = accountMapper.updateSptCustomerCompanyTermsProfile(paramVO);
			
	    //있으면 insert
		}else{
			result = accountMapper.insertSptCustomerCompanyTermsProfile(paramVO);
		}
		//hist insert
		result = accountMapper.insertSptCustomerCompanyTermsProfileHist(paramVO);

		
		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
	}
}
