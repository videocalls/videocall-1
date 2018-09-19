package kr.co.koscom.oppfm.terms.service.impl;

import kr.co.koscom.oppfm.app.model.AppCreateReq;
import kr.co.koscom.oppfm.app.model.AppTermsPubCompanyProfileReq;
import kr.co.koscom.oppfm.app.service.AppService;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.cmm.util.CookieUtil;
import kr.co.koscom.oppfm.cmm.util.OppfStringUtil;
import kr.co.koscom.oppfm.login.model.LoginRes;
import kr.co.koscom.oppfm.terms.dao.CommonTermsMapper;
import kr.co.koscom.oppfm.terms.model.CommonTermsRes;
import kr.co.koscom.oppfm.terms.service.CommonTermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CommonTermsServiceImpl
 * <p>
 * Created by chungyeol.kim on 2017-05-24.
 */
@Service
public class CommonTermsServiceImpl implements CommonTermsService {

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private CommonTermsMapper commonTermsMapper;

    @Autowired
    private AppService appService;

    @Override
    public CommonResponse getCommonTermsInfo(HttpServletRequest request) {
        LoginRes loginRes = CookieUtil.getLoginInfo(request);

//        CommonTermsRes commonTermsRes = commonTermsMapper.selectCommonTermsInfo(loginRes.getCustomerRegNo());
//        List<AppTermsPubCompanyProfileReq> commonTermsPubCompanyList = commonTermsMapper.selectCommonTermsPubCompanyList(loginRes.getCustomerRegNo(), commonTermsRes.getTermsRegNo());

        CommonTermsRes commonTermsRes = commonTermsMapper.selectBaseCommonTerms(loginRes.getCustomerRegNo());
        List<AppTermsPubCompanyProfileReq> commonTermsPubCompanyList = commonTermsMapper.selectPubCompanyList();
        List<String> pubCompanyList = new ArrayList<>();
        for(AppTermsPubCompanyProfileReq commonTermsPubCompany : commonTermsPubCompanyList) {
            pubCompanyList.add(commonTermsPubCompany.getPubCompanyName());
        }
        commonTermsRes.setPubCompanyList(pubCompanyList);

        Map<String,Object> body = new HashMap<>();
        body.put("commonTermsInfo", commonTermsRes);

        CommonTermsRes beforeTermsRes = commonTermsMapper.selectCommonTermsInfo(loginRes.getCustomerRegNo());
        if(beforeTermsRes != null) {
            List<AppTermsPubCompanyProfileReq> beforeTermsPubCompanyList = commonTermsMapper.selectCommonTermsPubCompanyList(loginRes.getCustomerRegNo(), beforeTermsRes.getTermsRegNo());
            List<String> beforePubCompanyList = new ArrayList<>();
            for(AppTermsPubCompanyProfileReq commonTermsPubCompany : beforeTermsPubCompanyList) {
                beforePubCompanyList.add(commonTermsPubCompany.getPubCompanyName());
            }
            beforeTermsRes.setPubCompanyList(beforePubCompanyList);
            Map<String,Object> day = commonTermsMapper.selectExpireDateForCommonTerms(loginRes.getCustomerRegNo());
            if(day != null) {
                beforeTermsRes.setDateCount(Integer.parseInt(day.get("dateCount").toString()));
            }
        }
        body.put("beforeCommonTermsInfo", beforeTermsRes);

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

    
    /**
     * (가)동의서 동의 여부 체크
     * @param request, String pubCompanyCodeId
     * @return
     */
	@Override
	public CommonResponse checkedCommonTerms(HttpServletRequest request, String pubCompanyCodeId) {
		LoginRes loginRes = CookieUtil.getLoginInfo(request);
        if(pubCompanyCodeId != null) {
            pubCompanyCodeId = pubCompanyCodeId.trim();
        }
        int checkedCommonTerms = commonTermsMapper.selectCheckedCommonTerms(loginRes.getCustomerRegNo(), pubCompanyCodeId);
        int checkedCustomerVerify = commonTermsMapper.selectCheckedCustomerVerify(loginRes.getCustomerRegNo());

        if(checkedCommonTerms < 1 && checkedCustomerVerify < 1) { // 동의서 정보와 인증서 정보가 없는 경우
            return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.NOT_FOUND_TERMS, null);
        } else if(checkedCommonTerms < 1) { // (가)동의서 정보가 없거나 유효기간이 지난 경우
            return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.EXPIRED_DATE_COMMON_TERMS, null);
        }

		//처음 앱 신청할 때(가)동의서가 있고 동의상태, 만료날짜>현재날짜, delete_date is null을 모두 만족할때
		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
	}

    @Override
    public CommonResponse checkedCommonTermsReSync(HttpServletRequest request) {
        LoginRes loginRes = CookieUtil.getLoginInfo(request);

        /*Map<String,Object> day = commonTermsMapper.selectExpireDateForCommonTerms(loginRes.getCustomerRegNo());
        if(day != null) {
            return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.EXPIRED_DATE_COMMON_TERMS, null);
        }*/

        int count = commonTermsMapper.selectExpireDateCheck(loginRes.getCustomerRegNo());
        if(count > 0) {
            return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.EXPIRED_DATE_COMMON_TERMS, null);
        } else {
            return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
        }

    }

    @Override
    @Transactional
    public CommonResponse createCommonTerms(HttpServletRequest request, AppCreateReq appCreateReq) {

        LoginRes loginRes = CookieUtil.getLoginInfo(request);
        appCreateReq.setCustomerRegNo(loginRes.getCustomerRegNo());

        // 기존 동의서 데이터 취득
        CommonTermsRes commonTermsRes = commonTermsMapper.selectCommonTermsInfo(loginRes.getCustomerRegNo());

        if(commonTermsRes != null) {
            // 기존 동의서 폐기
            commonTermsMapper.deleteCustomerCommonTermsProfile(loginRes.getCustomerRegNo(), commonTermsRes.getTermsRegNo());
            commonTermsMapper.insertCustomerCommonTermsProfileHist(loginRes.getCustomerRegNo(), commonTermsRes.getTermsRegNo());

            commonTermsMapper.deleteCustomerCommonTermsPubCompanyProfile(loginRes.getCustomerRegNo(), commonTermsRes.getTermsRegNo());
            commonTermsMapper.insertCustomerCommonTermsPubCompanyProfileHist(loginRes.getCustomerRegNo(), commonTermsRes.getTermsRegNo(), "");
        }

        // 신규 등록

        // 동의서 파일 작성 및 DB 등록
        appService.createTermsFile(appCreateReq);

        // 일반회원 범용 약관 프로파일 등록
        String termsRegNo = commonTermsMapper.selectMaxCommonTermsRegNo(loginRes.getCustomerRegNo());
        appCreateReq.setTermsRegNo(termsRegNo);
        commonTermsMapper.insertCustomerCommonTermsProfile(appCreateReq);
        // 일반회원 범용 약관 프로파일 이력 등록
        commonTermsMapper.insertCustomerCommonTermsProfileHist(loginRes.getCustomerRegNo(), termsRegNo);

        // 금투사 기업 목록 취득
        List<AppTermsPubCompanyProfileReq> pubCompanyList = commonTermsMapper.selectPubCompanyList();
        for(AppTermsPubCompanyProfileReq pubCompany : pubCompanyList) {
            // 일반회원 범용 약관 금투사 프로파일 키값 취득
            String termsPubCompanyRegNo = commonTermsMapper.selectMaxCommonTermsPubCompanyRegNo(loginRes.getCustomerRegNo(), termsRegNo);
            AppTermsPubCompanyProfileReq createPubCompany = new AppTermsPubCompanyProfileReq();
            createPubCompany.setCustomerRegNo(loginRes.getCustomerRegNo());
            createPubCompany.setTermsRegNo(termsRegNo);
            createPubCompany.setTermsPubCompanyRegNo(termsPubCompanyRegNo);
            createPubCompany.setPubCompanyCodeId(pubCompany.getPubCompanyCodeId());
            createPubCompany.setPubCompanyName(pubCompany.getPubCompanyName());
            // 일반회원 범용 약관 금투사 프로파일 등록
            commonTermsMapper.insertCustomerCommonTermsPubCompanyProfile(createPubCompany);
            // 일반회원 범용 약관 금투사 프로파일 이력 등록
            commonTermsMapper.insertCustomerCommonTermsPubCompanyProfileHist(loginRes.getCustomerRegNo(), termsRegNo, termsPubCompanyRegNo);
        }

        // 검증 테이블 데이터 등록
        int checkedCustomerVerify = commonTermsMapper.selectCheckedCustomerVerify(loginRes.getCustomerRegNo());
        if(checkedCustomerVerify < 1) {
            commonTermsMapper.insertCustomerVerifyProfile(loginRes.getCustomerRegNo(), appCreateReq.getCustomerSignDn());
            commonTermsMapper.insertCustomerVerifyProfileHist(loginRes.getCustomerRegNo());
        }

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
    }
}
