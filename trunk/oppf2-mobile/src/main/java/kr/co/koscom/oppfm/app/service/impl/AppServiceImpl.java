package kr.co.koscom.oppfm.app.service.impl;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.itextpdf.text.pdf.PdfWriter;

import groovy.transform.ThreadInterrupt;
import kr.co.koscom.oppfm.app.model.*;
import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ExternalInterfaceException;
import kr.co.koscom.oppfm.cmm.exception.InternalInterfaceException;
import kr.co.koscom.oppfm.cmm.model.EmailReqRes;
import kr.co.koscom.oppfm.cmm.service.EmailService;
import kr.co.koscom.oppfm.cmm.util.*;
import kr.co.koscom.oppfm.terms.dao.CommonTermsMapper;
import kr.co.koscom.oppfm.terms.model.CommonTermsRes;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;

import kr.co.koscom.oppfm.app.dao.AppMapper;
import kr.co.koscom.oppfm.app.service.AppService;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.login.model.LoginRes;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * AppServiceImpl
 * <p>
 * Created by chungyeol.kim on 2017-05-11.
 */
@Slf4j
@Service
public class AppServiceImpl implements AppService{

	@Autowired
	private AppMapper appMapper;

	@Autowired
	private CommonTermsMapper commonTermsMapper;
	
	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private EmailService emailService;

	@Value("#{config['Globals.ars.url']}")
	private String arsUrl;

	@Value("#{config['Globals.ars.secretKey']}")
	private String arsSecretKey;

	@Value("#{config['Globals.ars.trcd']}")
	private String arsTransactionCode;

	@Value("#{config['Globals.ars.orgcd']}")
	private String arsOrgCode;

	@Value("#{config['Globals.spt.webcontent.path']}")
	private String webContentPath;

	@Value("#{config['Globals.user.policy.password.final']}")
	private String policyPasswordFinal;

	@Value("#{config['Globals.domain.tsaconn']}")
	private String tsaConnUrl;

	@Value("#{config['Globals.sifmanageapi']}")
	private String sifApiUrl;

	@Value("#{config['project.server']}")
	private String projectServer;

	@Value("#{config['Globals.spt.domain']}")
	private String sptDomain;

	@Value("#{config['Globals.sslPort.spt']}")
	private String sptSslPort;

	@Value("#{config['Globals.domain.spt']}")
	private String sptDomainForEmail;

	@Value("#{config['Globals.ars.company.limit']}")
	private String arsCompanyLimit;

	@Value("#{config['Globals.ars.use.yn']}")
	private String arsUseYn;

	private static final String AUTH_INQUERY = "안녕하세요 코스콤입니다. ÐcustomerName 님이 보유하신 "
			+"ÐrsPubcompanyList 의 금융 거래 정보를 ÐtermsEndDate 까지"
			+"ÐsubcompanyName에 제공하는 것에 대해 동의하시겠습니까";

	/**
     * @Method Name        : selectAppList
     * @Method description : [핀테크 App 소개]핀테크 App 목록을 조회한다.
     * @param              : AppSearchReq, HttpServletRequest request
     * @return             : CommonResponse
     * @throws             : Exception
     */
	@Override
	public CommonResponse getAppList(AppSearchReq appSearchReq, HttpServletRequest request) {
		//loginRes를 이용하여 로그인 체크를 한다
		//로그인이 되어있을 경우 App소개 목록에서 신청한 App list를 조회한다.
		//로그인 이 되어있지 않을 경우 App소개 목록에서 전체 App list를 조회한다. 
		LoginRes loginRes = CookieUtil.getLoginInfo(request);
		Map<String, Object> body = new HashMap<>();
		String type = appSearchReq.getType();

		if(appSearchReq.getSearchKeyword() != null) {
			appSearchReq.setSearchKeyword(appSearchReq.getSearchKeyword().trim());
		}

		List<AppRes> appList;
		int appListTotalCount;
		if(!OppfStringUtil.isEmpty(type) && "intro".equals(type)){
			if(loginRes != null) {
				appSearchReq.setCustomerRegNo(loginRes.getCustomerRegNo());
			}
			appList = appMapper.selectAppList(appSearchReq);
			appListTotalCount = appMapper.selectAppListTotalCount(appSearchReq);
		}else {			
			if(loginRes != null) {
				if(!OppfStringUtil.isEmpty(type) && "request".equals(type)) {                      //로그인 후) 앱 신청을 눌러서 자신이 신청할 수 있는 앱 목록(type=request)
					appSearchReq.setCustomerRegNo(loginRes.getCustomerRegNo());
					appList=appMapper.selectRequestAppList(appSearchReq);
					appListTotalCount=appMapper.selectRequestAppListTotalCount(appSearchReq);
				} else {                   	 														//로그인 후) 신청한 앱 목록(type=list)
					appSearchReq.setCustomerRegNo(loginRes.getCustomerRegNo());
					appList = appMapper.selectMyAppList(appSearchReq);
					appListTotalCount =appMapper.selectMyAppListTotalCount(appSearchReq);
				}
			} else{ //로그인 전 - 신청가능한 앱 목록
				appList = appMapper.selectAppList(appSearchReq);
				appListTotalCount = appMapper.selectAppListTotalCount(appSearchReq);
			}			
		}

		for(AppRes appRes : appList) {
			appRes.setAppImageUrl(sptDomain + "/cmm/appImg/" + appRes.getAppId() + ".do");
		}

		body.put("appList", appList);
		body.put("appListTotalCount", appListTotalCount);
		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body, appSearchReq.getPageInfo(), appListTotalCount, appList.size());
	}
	
	
	/**
     * @Method Name        : getAppDtl
     * @Method description : [핀테크 App 소개]핀테크 App 상세 조회와 해당 App연계서비스를 조회한다.
     * @param              : String appId, HttpServletRequest request, String type(앱소개 에서 들어왔을때)
     * @return             : CommonResponse
     * @throws             : Exception
     */
	@Override
	public CommonResponse getAppDtl(String appId, HttpServletRequest request, String type) {
		LoginRes loginRes = CookieUtil.getLoginInfo(request);
		
		AppSearchReq appSearchReq = new AppSearchReq();
		appSearchReq.setAppId(appId);
		Map<String, Object> body = new HashMap<>();
		AppRes appDtl;
		List<AppCompanyRes> appCompanyList;
		List<AppAccountRes> appAccountList;
		
		if(loginRes != null) {
			appSearchReq.setCustomerRegNo(loginRes.getCustomerRegNo());
			appDtl = appMapper.selectMyAppDtl(appSearchReq);
			if(appDtl==null && OppfStringUtil.equals(type,"intro")){
				appDtl = appMapper.selectAppDtl(appSearchReq);
			}
			appCompanyList = appMapper.selectAppCompanyList(appSearchReq);
			appAccountList = appMapper.selectAppAccountList(appSearchReq);
			
	         String codeId = "";
	         List<AppAccountRes> appAccountResList = new ArrayList<>();           // 금투사 리스트
	        
	         for(AppAccountRes appAccountRes : appAccountList) {
	            if(!OppfStringUtil.equals(codeId, appAccountRes.getPubCompanyCodeId())) {
	               appAccountResList.add(appAccountRes);
	            }
	            codeId = appAccountRes.getPubCompanyCodeId();
	         }

	         for(AppAccountRes appAccountRes : appAccountResList) {
	            List<VtAccountRes> vtAccountResList = new ArrayList<>();
	            for(AppAccountRes accountRes : appAccountList) {
	               if(OppfStringUtil.equals(appAccountRes.getPubCompanyCodeId(), accountRes.getPubCompanyCodeId())) {
	                  VtAccountRes vtAccountRes = new VtAccountRes();
	                  vtAccountRes.setCustomerVtaccountNo(accountRes.getCustomerVtaccountNo());
	                  vtAccountRes.setCustomerVtaccountAlias(accountRes.getCustomerVtaccountAlias());
	                  vtAccountResList.add(vtAccountRes);
	               }
	            }
	            appAccountRes.setVtAccountResList(vtAccountResList);
	            appAccountRes.setIsAvailable(appAccountRes.getIsAvailable());
	         }
			body.put("AppAccountList", appAccountResList);
		}else {
			appDtl = appMapper.selectAppDtl(appSearchReq);
			appCompanyList = appMapper.selectAppCompanyList(appSearchReq);
		}

		if(appDtl != null) {
			appDtl.setAppImageUrl(sptDomain + "/cmm/appImg/" + appDtl.getAppId() + ".do");
		}

		body.put("appDtl", appDtl);
		body.put("AppCompanyList", appCompanyList);
		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
	}


	/**
     * @Method Name        : getAccountList
     * @Method description : [핀테크 App 신청]로그인 후)발급된 계좌 목록 전체 조회한다.
     * @param              : String appId, String type, HttpServletRequest request
     * @return             : CommonResponse
     * @throws             : Exception
     */
	@Override
	public CommonResponse getAccountList(String appId, String type, HttpServletRequest request) {
		LoginRes loginRes = CookieUtil.getLoginInfo(request);

		Map<String, Object> body = new HashMap<>();
		AppSearchReq appSearchReq = new AppSearchReq();
		appSearchReq.setAppId(appId);

		List<AppAccountRes> accountList;
		List<AppAccountRes> appAccountList;

		appSearchReq.setCustomerRegNo(loginRes.getCustomerRegNo());

		if(OppfStringUtil.equals("modify", type)) {                               //(type=modify)일때  신청한  App 신청정보 수정
			accountList = appMapper.selectAccountList(appSearchReq);              //개설한 가상계좌 목록 전체 (해당 App 서비스 연결 가능 가상계좌/서비스 연결 불가능 가상계좌  목록)
			appAccountList = appMapper.selectAppAccountList(appSearchReq);        //해당 App에 사용자가 연결한 가상계좌 목록)

			for(AppAccountRes accountRes : accountList) {
				for(AppAccountRes appAccountRes : appAccountList) {
					if(OppfStringUtil.equals(accountRes.getCustomerVtaccountNo(),
											 appAccountRes.getCustomerVtaccountNo())) { //가상계좌로 비교
						accountRes.setChecked(true);
						accountRes.setAccountRegNo(appAccountRes.getAccountRegNo());
						accountRes.setCustomerRealAccountNo(appAccountRes.getCustomerRealAccountNo());//해당 App에 연결된 계좌라면 Checked->true
					}
				}
			}
	        String codeId = "";
	        List<AppAccountRes> appAccountResList = new ArrayList<>();           
	        
	        for(AppAccountRes appAccountRes : accountList) {
	            if(!OppfStringUtil.equals(codeId, appAccountRes.getPubCompanyCodeId())) {
	               appAccountResList.add(appAccountRes);
	            }
	            codeId = appAccountRes.getPubCompanyCodeId();
	        }	
	         
	        for(AppAccountRes appAccountRes : appAccountResList) {
				List<VtAccountRes> vtAccountResList = new ArrayList<>();
				int checkedCount = 0;
				for(AppAccountRes accountRes : accountList) {
					if(OppfStringUtil.equals(appAccountRes.getPubCompanyCodeId(), accountRes.getPubCompanyCodeId())) {
						VtAccountRes vtAccountRes = new VtAccountRes();
						vtAccountRes.setChecked(accountRes.isChecked());
						vtAccountRes.setAccountRegNo(accountRes.getAccountRegNo());
						vtAccountRes.setCustomerVtaccountNo(accountRes.getCustomerVtaccountNo());
						vtAccountRes.setCustomerVtaccountAlias(accountRes.getCustomerVtaccountAlias());
						vtAccountRes.setCustomerRealAccountNo(accountRes.getCustomerRealAccountNo());
						vtAccountResList.add(vtAccountRes);
						if(accountRes.isChecked()) checkedCount++;
					}
				}
				appAccountRes.setVtAccountResList(vtAccountResList);
				appAccountRes.setIsAvailable(appAccountRes.getIsAvailable());
				if(checkedCount != vtAccountResList.size()) appAccountRes.setChecked(false);
		    }
			 body.put("accountList", appAccountResList);
		} else {                                                                  //(type=create)일때 신청할 앱에 연결할 가상계좌 조회
			accountList=appMapper.selectAccountList(appSearchReq);               //개설한 가상계좌 목록 전체 (해당 App 서비스 연결 가능 가상계좌/서비스 연결 불가능 가상계좌  목록)
	        String codeId = "";
	        List<AppAccountRes> appAccountResList = new ArrayList<>();           // 금투사 리스트
	        
	        for(AppAccountRes appAccountRes : accountList) {

				if(!OppfStringUtil.equals(codeId, appAccountRes.getPubCompanyCodeId())) {
	               appAccountResList.add(appAccountRes);
	            }
	            codeId = appAccountRes.getPubCompanyCodeId();
	        }

			for(AppAccountRes appAccountRes : appAccountResList) {
				List<VtAccountRes> vtAccountResList = new ArrayList<>();
				for(AppAccountRes accountRes : accountList) {
					if(OppfStringUtil.equals(appAccountRes.getPubCompanyCodeId(), accountRes.getPubCompanyCodeId())) {
						VtAccountRes vtAccountRes = new VtAccountRes();
						vtAccountRes.setCustomerVtaccountNo(accountRes.getCustomerVtaccountNo());
						vtAccountRes.setCustomerVtaccountAlias(accountRes.getCustomerVtaccountAlias());
						vtAccountRes.setCustomerRealAccountNo(accountRes.getCustomerRealAccountNo());
						vtAccountResList.add(vtAccountRes);
					}
				}
				appAccountRes.setVtAccountResList(vtAccountResList);
				appAccountRes.setIsAvailable(appAccountRes.getIsAvailable());
			}
	         body.put("accountList", appAccountResList);

		}

		// 앱정보
		AppTermsRes appTermsRes = appMapper.selectSubCompanyInfo(appId);
		body.put("appInfo", appTermsRes);

		// 연계서비스 목록
		List<AppCompanyRes> appCompanyList = appMapper.selectAppCompanyList(appSearchReq);
		body.put("appCompanyList", appCompanyList);
	
		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
	}

	@Override
	@Transactional
	public CommonResponse removeApp(HttpServletRequest request, String appId) {

		LoginRes loginRes = CookieUtil.getLoginInfo(request);

		// 폐기될 동의서 목록을 취득
		AppTermsRes appTermsRes = appMapper.selectCancelAppTerms(loginRes.getCustomerRegNo(), appId);

		Map<String,Object> body = new HashMap<>();

		if(appTermsRes != null) {

			List<String> appCount = appMapper.selectAppCountForTerms(loginRes.getCustomerRegNo(), appTermsRes.getTermsRegNo());

			// 앱 삭제 처리
			appMapper.deleteCustomerServiceProfile(loginRes.getCustomerRegNo(), appTermsRes.getServiceRegNo(), "");
			// 일반회원 서비스 계좌 프로파일 정보 삭제
			// spt_customer_service_account_profile 도 삭제 추가 2017-07-19
			appMapper.deleteCustomerServiceAccountProfile2(loginRes.getCustomerRegNo(), appTermsRes.getServiceRegNo());
			appMapper.insertCustomerServiceAccountProfileHist2(loginRes.getCustomerRegNo(), appTermsRes.getServiceRegNo());
			
			AppCreateReq appCreateReq = new AppCreateReq();
			appCreateReq.setCustomerRegNo(loginRes.getCustomerRegNo());
			appCreateReq.setServiceRegNo(appTermsRes.getServiceRegNo());
			appMapper.insertCustomerServiceProfileHist(appCreateReq);

			// 약관등록번호에 묶인 App 이 한개인 경우는 정보제공동의 폐기 아닌 경우 폐기하지 않는다
			// 수정중 제공동의를 받지 않은 금투사가 있는 경우는 폐기한다.
			if(appCount != null && appCount.size() == 1) {
				// 정보제공동의서 폐기 처리
				appMapper.deleteCustomerServiceTermsProfile(loginRes.getCustomerRegNo(), appTermsRes.getTermsRegNo());
				appCreateReq.setTermsRegNo(appTermsRes.getTermsRegNo());
				appMapper.insertCustomerServiceTermsProfileHist(appCreateReq);
				// 연계서버 API 연동
//				this.sendAppTerms(appTermsRes.getCustomerId(), appTermsRes.getTermsRegNo());
				body.put("termsRegNo", appTermsRes.getTermsRegNo());
			}
		}

		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
	}

	@Override
	@Transactional
	public CommonResponse removeAppTerms(HttpServletRequest request, String appId) {

		LoginRes loginRes = CookieUtil.getLoginInfo(request);

		AppTermsRes appTermsRes = appMapper.selectCancelAppTerms(loginRes.getCustomerRegNo(), appId);

		Map<String,Object> body = new HashMap<>();

		if(appTermsRes != null) {
			// 정보제공동의서 폐기 처리
			appMapper.deleteCustomerServiceTermsProfile(loginRes.getCustomerRegNo(), appTermsRes.getTermsRegNo());
			AppCreateReq appCreateReq = new AppCreateReq();
			appCreateReq.setCustomerRegNo(loginRes.getCustomerRegNo());
			appCreateReq.setTermsRegNo(appTermsRes.getTermsRegNo());
			appMapper.insertCustomerServiceTermsProfileHist(appCreateReq);

			// 앱 삭제 처리
			appMapper.deleteCustomerServiceProfile(loginRes.getCustomerRegNo(), "", appTermsRes.getTermsRegNo());
            // 일반회원 서비스 계좌 프로파일 정보 삭제
            // spt_customer_service_account_profile 도 삭제 추가 2017-07-19
            appMapper.deleteCustomerServiceAccountProfile2(loginRes.getCustomerRegNo(), appTermsRes.getServiceRegNo());
            appMapper.insertCustomerServiceAccountProfileHist2(loginRes.getCustomerRegNo(), appTermsRes.getServiceRegNo());

            appMapper.insertCustomerServiceProfileHist(appCreateReq);

			body.put("termsRegNo", appTermsRes.getTermsRegNo());
			// 연계서버 API 연동
//			this.sendAppTerms(appTermsRes.getCustomerId(), appTermsRes.getTermsRegNo());
		}

		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
	}

	@Override
	@Transactional
	public CommonResponse createApp(HttpServletRequest request, AppCreateReq appCreateReq, boolean isModify) {

		LoginRes loginRes = CookieUtil.getLoginInfo(request);
		appCreateReq.setCustomerRegNo(loginRes.getCustomerRegNo());

		// 이미 제공 동의 등록된 경우는 제공동의 등록 처리 패스
		if(OppfStringUtil.equals(appCreateReq.getTermsCreatedYn(), "N")) {

			appCreateReq.setTermsPolicy("12");

			// 전자서명인 경우
			if(OppfStringUtil.equals(CommonCodeConstants.TERMS_AUTH_TYPE_SIGN, appCreateReq.getTermsAuthType())) {
				// 제공동의 파일 생성 및 DB 데이터 등록
				this.createTermsFile(appCreateReq);
			}

			// 같은 앱개발사로 등록된 정보제공동의가 있는 경우
			String oldTermsRegNo = appMapper.selectCheckedAppTerms(loginRes.getCustomerRegNo(), "", appCreateReq.getSubCompanyCodeId());

			// 비회원인 경우 (가)동의서의 종료일과 (나)동의서의 종료일이 동일해야 한다.
			if(OppfStringUtil.equals(loginRes.getTemporaryMemberYn(), "Y")) {
				CommonTermsRes commonTermsRes = commonTermsMapper.selectCommonTermsInfo(loginRes.getCustomerRegNo());
				appCreateReq.setTermsExpireDate(commonTermsRes.getTermsExpireDate());
			}

			// 일반회원 서비스 약관 프로파일 번호 취득
			String termsRegNo = appMapper.selectMaxTermsRegNo(loginRes.getCustomerRegNo());
			appCreateReq.setTermsRegNo(termsRegNo);
			// 일반회원 서비스 약관 프로파일 데이터 등록
			appMapper.insertCustomerServiceTermsProfile(appCreateReq);
			// 일반회원 서비스 약관 프로파일 이력 데이터 등록
			appMapper.insertCustomerServiceTermsProfileHist(appCreateReq);

			if(appCreateReq.getAccountList() != null) {
				for(AppAccountRes appAccountRes : appCreateReq.getAccountList()) {
					int count = 0;
					for(VtAccountRes vtAccountRes : appAccountRes.getVtAccountResList()) {
						if(vtAccountRes.isChecked() && count == 0) {
							// 일반회원 서비스 약관 금투사 프로파일 번호 취득
							String termsPubCompanyRegNo = appMapper.selectMaxTermsPubCompanyRegNo(loginRes.getCustomerRegNo(), appCreateReq.getTermsRegNo());
							AppTermsPubCompanyProfileReq appTermsPubCompanyProfileReq = new AppTermsPubCompanyProfileReq();
							appTermsPubCompanyProfileReq.setTermsPubCompanyRegNo(termsPubCompanyRegNo);
							appTermsPubCompanyProfileReq.setCustomerRegNo(appCreateReq.getCustomerRegNo());
							appTermsPubCompanyProfileReq.setTermsRegNo(appCreateReq.getTermsRegNo());
							appTermsPubCompanyProfileReq.setPubCompanyCodeId(appAccountRes.getPubCompanyCodeId());
							appTermsPubCompanyProfileReq.setPubCompanyName(appAccountRes.getPubCompanyName());
							// 일반회원 서비스 약관 금투사 프로파일 데이터 등록
							appMapper.insertCustomerServiceTermsPubCompanyProfile(appTermsPubCompanyProfileReq);
							// 일반회원 서비스 약관 금투사 프로파일 이력 데이터 등록
							appMapper.insertCustomerServiceTermsPubCompanyProfileHist(appTermsPubCompanyProfileReq);
							count++;
						}
					}
				}
			}

			if(!OppfStringUtil.isEmpty(oldTermsRegNo)) {
				appCreateReq.setOldTermsRegNo(oldTermsRegNo);
				List<AppTermsPubCompanyProfileReq> oldPubCompanyList = appMapper.selectAppTermsPubCompanyInfoList(loginRes.getCustomerRegNo(), oldTermsRegNo);
				for(AppTermsPubCompanyProfileReq appTermsPubCompanyProfileReq : oldPubCompanyList) {
					appTermsPubCompanyProfileReq.setTermsRegNo(appCreateReq.getTermsRegNo());
					int result = appMapper.checkCreatedCustomerServiceTermsPubCompanyProfile(appTermsPubCompanyProfileReq);
					if(result < 1) {
						String termsPubCompanyRegNo = appMapper.selectMaxTermsPubCompanyRegNo(loginRes.getCustomerRegNo(), appCreateReq.getTermsRegNo());
						appTermsPubCompanyProfileReq.setTermsPubCompanyRegNo(termsPubCompanyRegNo);
						appTermsPubCompanyProfileReq.setTermsRegNo(appCreateReq.getTermsRegNo());
						// 일반회원 서비스 약관 금투사 프로파일 데이터 등록
						appMapper.insertCustomerServiceTermsPubCompanyProfile(appTermsPubCompanyProfileReq);
						// 일반회원 서비스 약관 금투사 프로파일 이력 데이터 등록
						appMapper.insertCustomerServiceTermsPubCompanyProfileHist(appTermsPubCompanyProfileReq);
					}
				}
				// 기존 동의서 폐기 처리
				// 정보제공동의서 폐기 처리
				appMapper.deleteCustomerServiceTermsProfile(loginRes.getCustomerRegNo(), oldTermsRegNo);
				AppCreateReq deleteTerms = new AppCreateReq();
				deleteTerms.setTermsRegNo(oldTermsRegNo);
				deleteTerms.setCustomerRegNo(loginRes.getCustomerRegNo());
				appMapper.insertCustomerServiceTermsProfileHist(appCreateReq);
				this.sendAppTerms(request, oldTermsRegNo);

				// 같은 앱 아이디로 앱 신청 되어있는 경우에는 기존 앱 정보도 폐기 할것
				String oldServiceRegNo = appMapper.selectOldServiceRegNo(loginRes.getCustomerRegNo(), oldTermsRegNo, appCreateReq.getAppId());
				if(!OppfStringUtil.isEmpty(oldServiceRegNo) && !isModify) {
					appMapper.deleteCustomerServiceProfile(loginRes.getCustomerRegNo(), oldServiceRegNo, oldTermsRegNo);
					
		            // 일반회원 서비스 계좌 프로파일 정보 삭제
		            // spt_customer_service_account_profile 도 삭제 추가 2017-07-19
		            appMapper.deleteCustomerServiceAccountProfile2(loginRes.getCustomerRegNo(), oldServiceRegNo);
		            appMapper.insertCustomerServiceAccountProfileHist2(loginRes.getCustomerRegNo(), oldServiceRegNo);

		            AppCreateReq removeApp = new AppCreateReq();
					removeApp.setCustomerRegNo(loginRes.getCustomerRegNo());
					removeApp.setServiceRegNo(oldServiceRegNo);
					appMapper.insertCustomerServiceProfileHist(removeApp);
				}

				// 기존 앱정보를 신규 제공동의 등록 번호로 업데이트
				List<String> appCount = appMapper.selectAppCountForTerms(loginRes.getCustomerRegNo(), oldTermsRegNo);
				for(String appId : appCount) {
					appMapper.updateCustomerServiceProfile(loginRes.getCustomerRegNo(), appId, appCreateReq.getTermsRegNo());
				}
			}

		}

		if(!isModify) {
			// 일반회원 서비스 프로파일 번호 취득
			String serviceRegNo = appMapper.selectMaxServiceRegNo(loginRes.getCustomerRegNo());
			appCreateReq.setServiceRegNo(serviceRegNo);
			// 일반회원 서비스 프로파일 데이터 등록
			appMapper.insertCustomerServiceProfile(appCreateReq);
			// 일반회원 서비스 프로파일 이력 데이터 등록
			appMapper.insertCustomerServiceProfileHist(appCreateReq);

			if(appCreateReq.getAccountList() != null) {
				for(AppAccountRes appAccountReq : appCreateReq.getAccountList()) {
					for(VtAccountRes vtAccountRes : appAccountReq.getVtAccountResList()) {
						if(vtAccountRes.isChecked()) {
							// 일반회원 서비스 계좌 프로파일 번호 취득
							String accountRegNo = appMapper.selectMaxServiceAccountRegNo(loginRes.getCustomerRegNo(), appCreateReq.getServiceRegNo());
							AppAccountProfileReq appAccountProfileReq = new AppAccountProfileReq();
							appAccountProfileReq.setAccountRegNo(accountRegNo);
							appAccountProfileReq.setServiceRegNo(appCreateReq.getServiceRegNo());
							appAccountProfileReq.setCustomerRegNo(appCreateReq.getCustomerRegNo());
							appAccountProfileReq.setAppId(appAccountReq.getAppId());
							appAccountProfileReq.setApiId(appAccountReq.getApiId());
							appAccountProfileReq.setCustomerRealAccountNo(vtAccountRes.getCustomerRealAccountNo());
							// 일반회원 서비스 계좌 프로파일 데이터 등록
							appMapper.insertCustomerServiceAccountProfile(appAccountProfileReq);
							// 일반회원 서비스 계좌 프로파일 이력 데이터 등록
							appMapper.insertCustomerServiceAccountProfileHist(appAccountProfileReq);
						}
					}
				}
			}
		}

		if(OppfStringUtil.equals(appCreateReq.getTermsCreatedYn(), "N")) {
			// ARS 인증인 경우
			if(OppfStringUtil.equals(CommonCodeConstants.TERMS_AUTH_TYPE_ARS, appCreateReq.getTermsAuthType())) {
				// ARS 인증 데이터 등록
				String termsArsRegNo = appMapper.selectMaxTermsArsRegNo(appCreateReq.getCustomerRegNo());
				appCreateReq.setTermsArsRegNo(termsArsRegNo);
				String phoneNumber = appMapper.selectEncCustomerPhoneNumber(appCreateReq.getCustomerRegNo());
				appCreateReq.setCustomerPhone(phoneNumber);
				appMapper.insertCustomerServiceArsProfile(appCreateReq);
				appMapper.insertCustomerServiceArsProfileHist(appCreateReq);
			}

			// 금융정보 제공 동의서 SIF 전송
//			this.sendAppTerms(loginRes.getCustomerId(), appCreateReq.getTermsRegNo());
		}

		// 이메일 발송
		this.sendEmail(appCreateReq, request);

		Map<String,Object> body = new HashMap<>();
		body.put("termsRegNo", appCreateReq.getTermsRegNo());
		if(!OppfStringUtil.isEmpty(appCreateReq.getOldTermsRegNo())) {
			body.put("oldTermsRegNo", appCreateReq.getOldTermsRegNo());
		}

		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
	}

	@Override
	@Transactional
	public CommonResponse modifyApp(HttpServletRequest request, AppCreateReq appCreateReq) {

		LoginRes loginRes = CookieUtil.getLoginInfo(request);
		appCreateReq.setCustomerRegNo(loginRes.getCustomerRegNo());

		Map<String,Object> sifResult = new HashMap<>();
		// (나)동의서를 신규로 받은건지 확인
		if(!OppfStringUtil.isEmpty(appCreateReq.getArsResultCd()) || !OppfStringUtil.isEmpty(appCreateReq.getCustomerSignData())) {
			// 기존 동의서를 폐기 한다. termsRegNo 취득
			// 신규 동의서를 발급한다. termsRegNo 취득
			// 신규 발급한 termsRegNo 로 기존의 테이블 정보를 업데이트 한다.

			// 폐기될 동의서 목록을 취득
			sifResult = this.reSyncAppTerms(request, appCreateReq, loginRes);

			// 이메일 발송
			AppCreateReq emailInfo = new AppCreateReq();
			emailInfo.setCustomerRegNo(loginRes.getCustomerRegNo());
			emailInfo.setTermsRegNo(sifResult.get("newTermsRegNo").toString());
			this.sendEmail(emailInfo, request);
		}

		String serviceRegNo = appMapper.selectServiceRegNo(loginRes.getCustomerRegNo(), appCreateReq.getAppId());
		// 일반회원 서비스 계좌 프로파일 데이터 등록 및 삭제
		if(appCreateReq.getAccountList() != null) {
			for(AppAccountRes appAccountRes : appCreateReq.getAccountList()) {
				for(VtAccountRes vtAccountRes : appAccountRes.getVtAccountResList()) {
					if(!OppfStringUtil.isEmpty(vtAccountRes.getAccountRegNo()) && !vtAccountRes.isChecked()) {
						// accountRegNo 가 있고, checked 가 false 인 경우는 삭제
						AppAccountProfileReq appAccountProfileReq = new AppAccountProfileReq();
						appAccountProfileReq.setCustomerRegNo(loginRes.getCustomerRegNo());
						appAccountProfileReq.setServiceRegNo(serviceRegNo);
						appAccountProfileReq.setAccountRegNo(vtAccountRes.getAccountRegNo());
						appMapper.deleteCustomerServiceAccountProfile(loginRes.getCustomerRegNo(), serviceRegNo, vtAccountRes.getAccountRegNo());
						appMapper.insertCustomerServiceAccountProfileHist(appAccountProfileReq);
					} else if(OppfStringUtil.isEmpty(vtAccountRes.getAccountRegNo()) && vtAccountRes.isChecked()) {
						// accountRegNo 가 없고, checked 가 true 인 경우는 등록
						String accountRegNo = appMapper.selectMaxServiceAccountRegNo(loginRes.getCustomerRegNo(), serviceRegNo);
						AppAccountProfileReq appAccountProfileReq = new AppAccountProfileReq();
						appAccountProfileReq.setCustomerRegNo(loginRes.getCustomerRegNo());
						appAccountProfileReq.setServiceRegNo(serviceRegNo);
						appAccountProfileReq.setAccountRegNo(accountRegNo);
						appAccountProfileReq.setAppId(appAccountRes.getAppId());
						appAccountProfileReq.setApiId(appAccountRes.getApiId());
						appAccountProfileReq.setCustomerRealAccountNo(vtAccountRes.getCustomerRealAccountNo());
						appMapper.insertCustomerServiceAccountProfile(appAccountProfileReq);
						appMapper.insertCustomerServiceAccountProfileHist(appAccountProfileReq);
					}
				}
			}
		}

		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, sifResult);
	}

	private Map<String,Object> reSyncAppTerms(HttpServletRequest request, AppCreateReq appCreateReq, LoginRes loginRes){
		Map<String,Object> result = new HashMap<>();
		AppTermsRes appTermsRes = appMapper.selectCancelAppTerms(loginRes.getCustomerRegNo(), appCreateReq.getAppId());
		if(appTermsRes != null) {

            // 정보제공동의서 폐기 처리
//            appMapper.deleteCustomerServiceTermsProfile(loginRes.getCustomerRegNo(), appTermsRes.getTermsRegNo());
//            appCreateReq.setTermsRegNo(appTermsRes.getTermsRegNo());
//            appMapper.insertCustomerServiceTermsProfileHist(appCreateReq);
			// 폐기된 동의서 번호 설정
//			result.put("oldTermsRegNo", appTermsRes.getTermsRegNo());
			// 금융정보 제공 동의서 SIF 전송
//            this.sendAppTerms(appTermsRes.getCustomerId(), appTermsRes.getTermsRegNo());

            this.createApp(request, appCreateReq, true);

            // 기존의 termsRegNo 를 새로 발급한 termsRegNo 로 업데이트
//            List<String> appCount = appMapper.selectAppCountForTerms(loginRes.getCustomerRegNo(), appTermsRes.getTermsRegNo());
//            for(String appId : appCount) {
//                appMapper.updateCustomerServiceProfile(loginRes.getCustomerRegNo(), appId, appCreateReq.getTermsRegNo());
//            }

			// 신규 등록된 동의서 번호 설정
			result.put("newTermsRegNo", appCreateReq.getTermsRegNo());
			result.put("oldTermsRegNo", appCreateReq.getOldTermsRegNo());

            // 금융정보 제공 동의서 SIF 전송
//            this.sendAppTerms(loginRes.getCustomerId(), appCreateReq.getTermsRegNo());
        }
		return result;
	}

	@Override
	@Transactional
	public CommonResponse createAppTerms(HttpServletRequest request, AppCreateReq appCreateReq, String appId) {

		LoginRes loginRes = CookieUtil.getLoginInfo(request);
		appCreateReq.setCustomerRegNo(loginRes.getCustomerRegNo());

		for(AppAccountRes appAccountRes : appCreateReq.getAccountList()) {
			for(VtAccountRes vtAccountRes : appAccountRes.getVtAccountResList()) {
				vtAccountRes.setChecked(true);
			}
		}

		// 동의서 재동의
		Map<String, Object> result = this.reSyncAppTerms(request, appCreateReq, loginRes);

		// 이메일 발송
		appCreateReq.setTermsRegNo(result.get("newTermsRegNo").toString());
		this.sendEmail(appCreateReq, request);

		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, result);
	}

	/**
	 * 약관 동의 파일 생성 및 약관 파일 DB 등록
	 * @param appCreateReq
	 * @throws Exception
     */
	@Override
	@Transactional
	public void createTermsFile(AppCreateReq appCreateReq) {

		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddhhmmss");

		String filePathFileName; 			//파일풀경로파일명(확장자뺀)
		String filePathOriginalFileName;	//원본파일풀경로파일명(확장자뺀)
		String makeHtmlContent; 			//html 내용
		String makePdfContent; 				//pdf 내용
		String makeTxtContent; 				//txt 내용

		//1.임시폴더생성작업
		//1-3.사용자등록번호 폴더 생성(tmpTsa/오늘날짜/사용자등록번호)
		String folderPath = webContentPath + "/upload/tmpTsa/" + sdf.format(today) + "/" + appCreateReq.getCustomerRegNo();
		this.mkDirTsa(folderPath);

		//2-2.파일명설정
		String baseFileName = appCreateReq.getCustomerRegNo() + "_" + sdf2.format(today);

		//2-3.(저장경로+파일명)설정
		filePathFileName = folderPath + "/" + baseFileName;

		//3.html파일생성
		//3-1.공인인증서 전자서명데이터 base64 디코딩작업
		/*if(!OppfStringUtil.isEmpty(appCreateReq.getCustomerSignData())){
			log.debug("3-1.공인인증서 전자서명데이터 base64 디코딩작업前:paramVO.getSignData() : {} ", appCreateReq.getCustomerSignData());
			String decodedSignData = OppfStringUtil.base64Decoding(appCreateReq.getCustomerSignData());
			log.debug("3-1.공인인증서 전자서명데이터 base64 디코딩작업後:decodedSignData : {} ", decodedSignData);

			appCreateReq.setCustomerSignData(decodedSignData);
		}*/
		makeHtmlContent = appCreateReq.getCustomerSignData();
		this.mkFileForHtmlTxt(filePathFileName, makeHtmlContent, ".html");

		//4.html파일을txt로 변환 (전자서명된값 Decoding)
		makeTxtContent = appCreateReq.getCustomerSignData();
		this.mkFileForHtmlTxt(filePathFileName, makeTxtContent, ".txt");

		//5.원본설정작업
		//5-1.원본파일명설정
		String baseFileNameOriginal = appCreateReq.getCustomerRegNo() +"_" + sdf2.format(today) + "-original";
		//5-2.원본(저장경로+파일명)설정
		filePathOriginalFileName = folderPath + "/" + baseFileNameOriginal;

		//6.원본html파일생성
//		makeHtmlContent = this.convertTermsHtml(appCreateReq);
		makeHtmlContent = appCreateReq.getReqHtml();
		this.mkFileForHtmlTxt(filePathOriginalFileName, makeHtmlContent, ".html");

		//7.원본html파일을pdf로 변환
		makePdfContent = makeHtmlContent;
		if(!OppfStringUtil.equals(projectServer, "local")) {
			this.htmlConvertPdf(filePathOriginalFileName,makePdfContent);
		}

		//8.TSA연동前값세팅
		//8-1.header셋팅
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		//8-2.url셋팅
		String tsaUrl = tsaConnUrl;

		//8-3.body값셋팅
		HttpEntity<String> requestEntity;
		requestEntity = new HttpEntity<>("{\"fileName\":"+filePathFileName+".txt"+"\"}", httpHeaders);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ResponseErrorHandler(){
			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				//System.out.println("hasError --> " + response.getStatusText());
				return false;
			}
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				//System.out.println("handleError --> " + response.getStatusText());
			}
		});

		//9.TSA연동後결과값세팅
		TsaRes responseBody = new TsaRes();

		if(!OppfStringUtil.equals(projectServer, "local")) {
			ResponseEntity<TsaRes> responseEntity;
			try {
				responseEntity = restTemplate.exchange(tsaUrl, HttpMethod.POST, requestEntity, TsaRes.class);
				responseBody = responseEntity.getBody();
			} catch (Exception e) {
				throw new CommonException(ErrorCodeConstants.FAIL_TSA_CONNECTION, null);
			}
		}

		String dataHash = "";
		if(!OppfStringUtil.isEmpty(responseBody.getHashValue())){
			dataHash = responseBody.getHashValue();
		} else {
			if(!OppfStringUtil.equals(projectServer, "local")) {
				throw new CommonException(ErrorCodeConstants.FAIL_TSA_CONNECTION, null);
			}
		}

		appCreateReq.setCustomerTsaData(dataHash);
		//10.약관파일DB저장
		List<AppTermsFileProfileReq> termsFileList = new ArrayList<>();
		AppTermsFileProfileReq appTermsFileProfileReq;
		//10-1.약관파일 DB 등록 값세팅
		File[] listFile = new File(folderPath).listFiles();
		if(listFile != null && listFile.length > 0){
			for(int i = 0; i<listFile.length; i++){
				if(listFile[i].isFile()){
					String currentFileName = listFile[i].getName(); //현재파일명
					String fileName = baseFileName + ".txt"; //[서명+원본]파일명
					String fileNameOriginal = baseFileName + "-original.pdf"; //[원본]파일명
					String fileNameReq = baseFileName + ".txt.req"; //[TSA응답결과:요청]파일명
					String fileNameRep = baseFileName + ".txt.rep"; //[TSA응답결과:응답]파일명

					byte[] file1Byte;
					try {
						file1Byte = Files.readAllBytes(listFile[i].toPath());
					} catch (IOException e) {
						throw new CommonException(ErrorCodeConstants.FAIL_CREATE_TERMS_FILE, null);
					}

					//[서명+원본]파일 인 경우
					if(fileName.equals(currentFileName)){
						appTermsFileProfileReq = new AppTermsFileProfileReq();

						//설정:회원OP등록번호
						appTermsFileProfileReq.setCustomerRegNo(appCreateReq.getCustomerRegNo());

						//설정:동의서파일종류[G027=(001:원본, 002:전자서명파일, 003:TSA원본, 004:TSA적용)]
						appTermsFileProfileReq.setTermsFileType(CommonCodeConstants.TERMS_FILE_TYPE_SIGN);

						//설정:동의서파일
						appTermsFileProfileReq.setTermsFileData(file1Byte);

						//설정:TSA결과해쉬값
						if(!OppfStringUtil.isEmpty(dataHash)){
							appTermsFileProfileReq.setDataHash(dataHash);
						}

						termsFileList.add(appTermsFileProfileReq);

						log.debug("currentFileName["+i+"]="+currentFileName);

						//[원본]파일 인 경우
					}else if(fileNameOriginal.equals(currentFileName)){
						appTermsFileProfileReq = new AppTermsFileProfileReq();

						//설정:회원OP등록번호
						appTermsFileProfileReq.setCustomerRegNo(appCreateReq.getCustomerRegNo());

						//설정:동의서파일종류[G027=(001:원본, 002:전자서명파일, 003:TSA원본, 004:TSA적용)]
						appTermsFileProfileReq.setTermsFileType(CommonCodeConstants.TERMS_FILE_TYPE_ORIGINAL);

						//설정:동의서파일
						appTermsFileProfileReq.setTermsFileData(file1Byte);

						//설정:TSA결과해쉬값
						if(!OppfStringUtil.isEmpty(dataHash)){
							appTermsFileProfileReq.setDataHash(dataHash);
						}

						termsFileList.add(appTermsFileProfileReq);

						log.debug("currentFileName["+i+"]="+currentFileName);

						//[req]파일 인 경우
					}else if(fileNameReq.equals(currentFileName)){
						appTermsFileProfileReq = new AppTermsFileProfileReq();

						//설정:회원OP등록번호
						appTermsFileProfileReq.setCustomerRegNo(appCreateReq.getCustomerRegNo());

						//설정:동의서파일종류[G027=(001:원본, 002:전자서명파일, 003:TSA원본, 004:TSA적용)]
						appTermsFileProfileReq.setTermsFileType(CommonCodeConstants.TERMS_FILE_TYPE_TSA_ORIGINAL);

						//설정:동의서파일
						appTermsFileProfileReq.setTermsFileData(file1Byte);

						//설정:TSA결과해쉬값
						if(!OppfStringUtil.isEmpty(dataHash)){
							appTermsFileProfileReq.setDataHash(dataHash);
						}

						termsFileList.add(appTermsFileProfileReq);

						log.debug("currentFileName["+i+"]="+currentFileName);

						//[rep]파일 인 경우
					}else if(fileNameRep.equals(currentFileName)){
						appTermsFileProfileReq = new AppTermsFileProfileReq();

						//설정:회원OP등록번호
						appTermsFileProfileReq.setCustomerRegNo(appCreateReq.getCustomerRegNo());

						//설정:동의서파일종류[G027=(001:원본, 002:전자서명파일, 003:TSA원본, 004:TSA적용)]
						appTermsFileProfileReq.setTermsFileType(CommonCodeConstants.TERMS_FILE_TYPE_TSA);

						//설정:동의서파일
						appTermsFileProfileReq.setTermsFileData(file1Byte);

						//설정:TSA결과해쉬값
						if(!OppfStringUtil.isEmpty(dataHash)){
							appTermsFileProfileReq.setDataHash(dataHash);
						}

						termsFileList.add(appTermsFileProfileReq);

						log.debug("currentFileName["+i+"]="+currentFileName);
					}
				}
			}
		}

		//10-3.약관파일 DB 등록 작업
		String termsFileRegNo = appMapper.selectMaxTermsFileRegNo();
		appCreateReq.setTermsFileRegNo(termsFileRegNo);

		for(AppTermsFileProfileReq createAppTermsFile : termsFileList) {
			// 일반회원 서비스 약관 파일 프로파일 번호 취득
			createAppTermsFile.setTermsFileRegNo(termsFileRegNo);
			// 일반회원 서비스 약관 파일 프로파일 데이터 등록
			appMapper.insertCustomerServiceTermsFileProfile(createAppTermsFile);
			// 일반회원 서비스 약관 파일 프로파일 이력 데이터 등록
			appMapper.insertCustomerServiceTermsFileProfileHist(createAppTermsFile);
		}

	}

	@Override
	public void sendAppTerms(HttpServletRequest request, String termsRegNo) {
		LoginRes loginRes = CookieUtil.getLoginInfo(request);
		String apiUrl = sifApiUrl + "/financeterms/terms/transmit/";
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		try{
			log.debug("customerRegNo : {}, termsRegNo : {}", loginRes.getCustomerId(), termsRegNo);
			// http://외부연계IP:8093/financeterms/terms/transmit/{사용자아이디}/{계약등록번호}
			apiUrl += loginRes.getCustomerId() + "/" + termsRegNo;
			//연계서버 금융정보 제공동의서 API 전송
			RestTemplateUtil.sendRestTemplate(apiUrl, httpHeaders, HttpMethod.POST, "{}");

		}catch (Exception e){
			throw new InternalInterfaceException(ErrorCodeConstants.TERMS_INTERNAL_CONNECTION_ERROR, new String[] {e.getMessage()}, "");
		}
	}

	/**
	 * html OR txt 파일 생성
	 * @param fileFullPathName
	 * @param makeHtmlContent
	 * @param fileExtension
     */
	private void mkFileForHtmlTxt(String fileFullPathName, String makeHtmlContent, String fileExtension){

		fileFullPathName += fileExtension;

		try{
			//파일객체 생성
			File tmpFile = new File(fileFullPathName);

			//true 지정 시 파일의 기존 내용에 이어서 작성
			FileWriter tmpFw = new FileWriter(tmpFile, false);

			//파일안에 문자열 쓰기
			tmpFw.write(makeHtmlContent);
			tmpFw.flush();

			//객체 닫기
			tmpFw.close();

		}catch(Exception e){
			throw new CommonException(ErrorCodeConstants.FAIL_CREATE_TERMS_FILE, null);
		}
	}

	/**
	 * 디렉토리 생성
	 * @param folderPathName
     */
	private void mkDirTsa(String folderPathName){
		File cFile = new File(folderPathName);
		if(!cFile.isDirectory()){
			cFile.mkdirs();
		}
	}

	/**
	 * 생성된 파일 삭제
	 * @param parentPath
     */
	private void rmDirTsa(String parentPath){

		if(!OppfStringUtil.isEmpty(parentPath)) {
			File file = new File(parentPath);
			String[] fnameList = file.list();
			String childPath;

			for (String aFnameList : fnameList) {
				childPath = parentPath + "/" + aFnameList;
				File f = new File(childPath);
				if (!f.isDirectory()) {
					f.delete();   //파일이면 바로 삭제
				} else {
					rmDirTsa(childPath);
				}
			}
			File f = new File(parentPath);
			f.delete();   //폴더는 맨 나중에 삭제
		}

	}

	/**
	 * PDF 파일 생성
	 * @param fileFullPathName
	 * @param makePdfContent
     */
	private void htmlConvertPdf(String fileFullPathName, String makePdfContent){

		PdfWriter pdfWriter;
		fileFullPathName += ".pdf";

		try{

			// create a new document
			Document document = new Document();

			// get Instance of the PDFWriter
			pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(fileFullPathName));

			document.setPageSize(PageSize.A4);

			document.open();

			HTMLWorker htmlWorker = new HTMLWorker(document);

			HashMap<String, Object> interfaceProps = new HashMap<String, Object>();

			StyleSheet styles = new StyleSheet();

			String fontFullPathName = webContentPath + "/font/malgun.ttf";

			DefaultFontProvider dfp = new DefaultFontProvider(fontFullPathName);
			interfaceProps.put(HTMLWorker.FONT_PROVIDER, dfp); // 폰트 파일 설정 (한글 나오게 하기 위해 설정 필요함)

			StringReader strReader = new StringReader(makePdfContent);
			List<Element> objects = htmlWorker.parseToList(strReader, styles, interfaceProps);

			for(int k=0; k<objects.size(); ++k){
				log.debug("objects.get("+k+")="+objects.get(k));
				document.add((Element) objects.get(k));
			}
			document.close();

			// close the writer
			pdfWriter.close();

		}catch(Exception e){
			throw new CommonException(ErrorCodeConstants.FAIL_CREATE_TERMS_FILE, null);
		}
	}

	@Override
	public CommonResponse checkTermsInfo(HttpServletRequest request, String appId, String checkedPubCompanyList, String subCompanyCodeId) {
		LoginRes loginRes = CookieUtil.getLoginInfo(request);
		//(나)동의서 동의 여부 체크
		String[] pubCompanyCodeList = OppfStringUtil.split(checkedPubCompanyList, ","); //체크되어있는 금투사 코드들을 배열에 넣어준다.
		String termsRegNo = "";
		for(String pubCompanyCodeId : pubCompanyCodeList) { //배열에 있는 금투사 코드를 이용하여 동의서에 있는지 여부를 체크한다.
			termsRegNo = appMapper.selectCheckedAppTerms(loginRes.getCustomerRegNo(), pubCompanyCodeId, subCompanyCodeId);
			//해당 금투사코드로 (나)동의서의 금투사코드와 비교한다.
			if(OppfStringUtil.isNull(termsRegNo)) { //(나)동의서가 없거나, 추가하려는 금투사가 없을경우
				return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.NOT_FOUND_TERMS, null);
			}
		}
		Map<String,Object> body = new HashMap<>();
		body.put("termsRegNo", termsRegNo);
		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
	}

	@Override
	public CommonResponse getTermsInfo(HttpServletRequest request, String appId, String checkedPubCompanyList, String type) {

		LoginRes loginRes = CookieUtil.getLoginInfo(request);

		AppTermsRes appTermsRes;
		AppTermsRes beforeAppTermsRes = null;
		if(OppfStringUtil.equals(type, "detail")) {
			appTermsRes = appMapper.selectAppTermsInfo(loginRes.getCustomerRegNo(), appId);
			// 사용자 정보제공동의서 기업정보 조회
			List<String> pubCompanyList = appMapper.selectAppTermsPubCompanyList(loginRes.getCustomerRegNo(), appTermsRes.getTermsRegNo());
			appTermsRes.setPubCompanyList(pubCompanyList);
		} else if(OppfStringUtil.equals(type, "reSync")) {
			CommonTermsRes commonTermsRes = commonTermsMapper.selectBaseCommonTerms(loginRes.getCustomerRegNo());
			appTermsRes = appMapper.selectAppTermsInfo(loginRes.getCustomerRegNo(), appId);
			appTermsRes.setTermsCreateDate(commonTermsRes.getTermsCreateDate());
			appTermsRes.setTermsStartDate(commonTermsRes.getTermsStartDate());
			appTermsRes.setTermsEndDate(commonTermsRes.getTermsEndDate());
			// 사용자 정보제공동의서 기업정보 조회
			List<String> pubCompanyList = appMapper.selectAppTermsPubCompanyList(loginRes.getCustomerRegNo(), appTermsRes.getTermsRegNo());
			appTermsRes.setPubCompanyList(pubCompanyList);

			// 이전 동의서 정보
			beforeAppTermsRes = appMapper.selectAppTermsInfo(loginRes.getCustomerRegNo(), appId);
			if(beforeAppTermsRes != null) {
				beforeAppTermsRes.setPubCompanyList(pubCompanyList);
			}

		} else {
			CommonTermsRes commonTermsRes = commonTermsMapper.selectBaseCommonTerms(loginRes.getCustomerRegNo());
			AppTermsRes subCompany = appMapper.selectSubCompanyInfo(appId);
			appTermsRes = new AppTermsRes();
			appTermsRes.setSubCompanyCodeId(subCompany.getSubCompanyCodeId());
			appTermsRes.setSubCompanyName(subCompany.getSubCompanyName());
			appTermsRes.setCustomerName(commonTermsRes.getCustomerName());
			appTermsRes.setCustomerEmail(commonTermsRes.getCustomerEmail());
			appTermsRes.setCustomerBirthDay(commonTermsRes.getCustomerBirthDay());
			appTermsRes.setCustomerPhone(commonTermsRes.getCustomerPhone());
			appTermsRes.setTermsCreateDate(commonTermsRes.getTermsCreateDate());
			appTermsRes.setTermsStartDate(commonTermsRes.getTermsStartDate());
			// 비회원인 경우 (가)동의서의 종료일과 (나)동의서의 종료일이 동일해야 한다.
			if(OppfStringUtil.equals(loginRes.getTemporaryMemberYn(), "Y")) {
				CommonTermsRes termsExpireDate = commonTermsMapper.selectCommonTermsInfo(loginRes.getCustomerRegNo());
				String expireDate = termsExpireDate.getTermsEndDate().substring(0,4) + "년 " +
									termsExpireDate.getTermsEndDate().substring(4,6) + "월 " +
									termsExpireDate.getTermsEndDate().substring(6,8) + "일";
				appTermsRes.setTermsEndDate(expireDate);
			} else {
				appTermsRes.setTermsEndDate(commonTermsRes.getTermsEndDate());
			}
			List<String> pubCompanyNames = new ArrayList<>();
			// 전체 금투사 리스트를 가져와서 셀렉트 된 금투사를 비교 하여 셋팅
			List<AppTermsPubCompanyProfileReq> pubCompanyList = commonTermsMapper.selectPubCompanyList();
			String[] pubCompanyCodeIds = checkedPubCompanyList.split(",");
			for(String pubCompanyCodeId : pubCompanyCodeIds) {
				for(AppTermsPubCompanyProfileReq pubCompany : pubCompanyList) {
					if(OppfStringUtil.equals(pubCompany.getPubCompanyCodeId(), pubCompanyCodeId)) {
						pubCompanyNames.add(pubCompany.getPubCompanyName());
					}
				}
			}
			appTermsRes.setPubCompanyList(pubCompanyNames);

			// 기존에 등록된 정보제공동의 내용이 있다면 기존 항목도 노출
			String termsRegNo = appMapper.selectCheckedAppTerms(loginRes.getCustomerRegNo(), "", subCompany.getSubCompanyCodeId());
			if(!OppfStringUtil.isEmpty(termsRegNo)) {
				List<String> createdPubCompanyNmList = appMapper.selectAppTermsPubCompanyList(loginRes.getCustomerRegNo(), termsRegNo);
				for(String createdPubCompany : createdPubCompanyNmList) {
					appTermsRes.getPubCompanyList().add(createdPubCompany);
				}
				HashSet<String> set = new HashSet<>(appTermsRes.getPubCompanyList());
				List<String> newPubCompanyNmList = new ArrayList<>(set);
				appTermsRes.setPubCompanyList(newPubCompanyNmList);

				// 이전 동의서가 존재하면
				beforeAppTermsRes = appMapper.selectAppTermsInfo(loginRes.getCustomerRegNo(), appId);
				if(beforeAppTermsRes != null) {
					// 사용자 정보제공동의서 기업정보 조회
					beforeAppTermsRes.setPubCompanyList(createdPubCompanyNmList);
				}
			}
		}

		Map<String,Object> body = new HashMap<>();
		body.put("appTermsInfo", appTermsRes);
		body.put("beforeAppTermsInfo", beforeAppTermsRes);
		body.put("arsUseYn", arsUseYn);
		body.put("arsCompanyLimit", Integer.parseInt(arsCompanyLimit));
		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
	}

	@Override
	public CommonResponse getTermsArsInfo(HttpServletRequest request) {
		LoginRes loginRes = CookieUtil.getLoginInfo(request);
		String phoneNumber = appMapper.selectCustomerPhoneNumber(loginRes.getCustomerRegNo());
		Map<String,Object> body = new HashMap<>();
		body.put("customerPhoneNumber", OppfStringUtil.maskedPhoneNum(phoneNumber));
		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
	}

	@Override
	public CommonResponse requestTermsArs(HttpServletRequest request, AppTermsArsReq appTermsArsReq) {
		LoginRes loginRes = CookieUtil.getLoginInfo(request);

		String phoneNumber = appMapper.selectCustomerPhoneNumber(loginRes.getCustomerRegNo());

		String termsEndDate = "";
		// 비회원인 경우 (가)동의서의 종료일과 (나)동의서의 종료일이 동일해야 한다.
		if(OppfStringUtil.equals(loginRes.getTemporaryMemberYn(), "Y")) {
			CommonTermsRes termsExpireDate = commonTermsMapper.selectCommonTermsInfo(loginRes.getCustomerRegNo());
			termsEndDate = termsExpireDate.getTermsEndDate().substring(0, 4) + "년 " +
					termsExpireDate.getTermsEndDate().substring(4, 6) + "월 " +
					termsExpireDate.getTermsEndDate().substring(6, 8) + "일";
		} else {
			CommonTermsRes commonTermsRes = commonTermsMapper.selectBaseCommonTerms(loginRes.getCustomerRegNo());
			termsEndDate = commonTermsRes.getTermsEndDate();
		}

		String date = OppfDateUtil.getCurrentDateTimeAsString("yyyyMMddkkmmss");
		String phone = OppfStringUtil.replace(phoneNumber, "-", "");
		String authInQuery = OppfStringUtil.replace(AUTH_INQUERY, "ÐcustomerName", loginRes.getCustomerNameKor());
		authInQuery = OppfStringUtil.replace(authInQuery, "ÐrsPubcompanyList", this.getPubCompanyList(appTermsArsReq));
		authInQuery = OppfStringUtil.replace(authInQuery, "ÐtermsEndDate", termsEndDate);
		authInQuery = OppfStringUtil.replace(authInQuery, "ÐsubcompanyName", appTermsArsReq.getSubCompanyName());
		String authNo = OppfStringUtil.getRandomStr('0', '9') + OppfStringUtil.getRandomStr('0', '9');

		String decodePayload = "{ \n" +
				"\"SECR_KEY\" : \""+arsSecretKey+"\",\n" +
				"\"TR_CD\" : \""+arsTransactionCode+"\",\n" +
				"\"ORG_CD\" : \""+arsOrgCode+"\",\n" +
				"\"DATE\" : \""+date+"\",\n" +
				"\"PHONE\" : \""+phone+"\",\n" +
				"\"AUTH_INQUERY\" : \""+authInQuery+"\",\n" +
				"\"AUTH_NO\" : \""+authNo+"\",\n" +
				"\"FILE_SAVE_YN\" : \"N\"\n" +
				"} ";

		String encodePayload;
		try {
			encodePayload = URLEncoder.encode(decodePayload, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new ExternalInterfaceException(ErrorCodeConstants.FAIL_ARS_AUTHORIZATION, null, "");
		}

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("JSONData", encodePayload);
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<MultiValueMap<String, String>> arsRequest = new HttpEntity<>(parameters, httpHeaders);
		RestTemplate rest = new RestTemplate();

		AppTermsArsRes appTermsArsRes = new AppTermsArsRes();

		try {
			String result = rest.postForObject(new URI(arsUrl), arsRequest, String.class);
			JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("RESP_DATA");

			// 처리 결과 코드
			appTermsArsRes.setArsResultCode(jsonObject.getString("RSLT_CD"));
			// 결과 메세지
			appTermsArsRes.setArsResultMessage(jsonObject.getString("RSLT_MSG"));
			// 거래코드
			appTermsArsRes.setArsTransactionCode(jsonArray.getJSONObject(0).getString("TR_CD"));
			// 전문고유번호
			appTermsArsRes.setArsTxtNo(jsonArray.getJSONObject(0).getString("TXT_NO"));
			//녹취데이터
			appTermsArsRes.setArsRecordData(jsonArray.getJSONObject(0).getString("RECORD_DATA"));
		} catch (Exception e) {
			log.debug("ars error");
			throw new ExternalInterfaceException(ErrorCodeConstants.FAIL_ARS_AUTHORIZATION, null, "");
		}

		Map<String,Object> body = new HashMap<>();
		body.put("arsTermsResultInfo", appTermsArsRes);
		log.debug("{}", appTermsArsRes);
		return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
	}

	/**
	 * 금투사 목록 취득
	 * @param appTermsArsReq
	 * @return
     */
	private String getPubCompanyList(AppTermsArsReq appTermsArsReq) {
		String pubCompanyList = "";
		for(AppAccountRes appAccountRes : appTermsArsReq.getAccountList()) {
			int count = 0;
			for(VtAccountRes vtAccountRes : appAccountRes.getVtAccountResList()) {
				if(count == 0 && vtAccountRes.isChecked()) {
					pubCompanyList += appAccountRes.getPubCompanyName();
					count++;
				}
			}
		}
		return pubCompanyList;
	}

	private void sendEmail(AppCreateReq appCreateReq, HttpServletRequest request) {

		//고객정보 가져오기
		EmailReqRes customerParamVO = new EmailReqRes();
		customerParamVO.setCustomerRegNo(appCreateReq.getCustomerRegNo());

		//고객정보 조회
		EmailReqRes customerVO = emailService.getEmailCustomerInfo(customerParamVO);

		//고객정보가 있을경우에 처리
		String customerName = "";
		String customerEmail = "";
		if(customerVO != null && !OppfStringUtil.isEmpty(customerVO.getCustomerName())){
			customerName = customerVO.getCustomerName();
			customerEmail = customerVO.getCustomerEmail();
		}

		EmailReqRes cmmEmailSendVO = new EmailReqRes();
		cmmEmailSendVO.setEmailSendType("G016006"); //이메일 유형 - com_email_temp_info 이메일 템플릿 조회

		cmmEmailSendVO.setReceiverName(customerName); //받는 사람 이름(실제 이메일에 셋팅됩니다.)
		cmmEmailSendVO.setReceiverEmail(customerEmail); //받는 사람 이메일(실제 이메일에 셋팅되고, 이 주소로 이메일이 발송됩니다.)
		cmmEmailSendVO.setReceiverKind("G018001"); //수신자 종류 - G018001:일반사용자, G018002:기업사용자
		cmmEmailSendVO.setReceiverId(appCreateReq.getCustomerRegNo()); //수신자 ID

		cmmEmailSendVO.setSenderKind("G017001"); //발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
		cmmEmailSendVO.setSenderId(appCreateReq.getCustomerRegNo()); //발신자 ID

		cmmEmailSendVO.setSendId(appCreateReq.getCustomerRegNo()); //최초 발신자 ID
		cmmEmailSendVO.setCreateId(appCreateReq.getCustomerRegNo()); //생성자ID
		cmmEmailSendVO.setUpdateId(appCreateReq.getCustomerRegNo()); //수정자ID

		String uriContextPath = "https://";
		String sptServerName = sptDomainForEmail;
		if(sptServerName.indexOf(":") >= 0){
			String [] tmpStr = sptServerName.split(":");
			uriContextPath += tmpStr[0] + ":" + sptSslPort;
		}else{
			uriContextPath += sptServerName;
		}
		cmmEmailSendVO.setUriContextPath(uriContextPath); //로그인 페이지 : https

		cmmEmailSendVO.setSystemKind("spt");//개인 포털로 셋팅[spt:개인, cpt:기업, apt:admin] -> session이 아닌 target이 있을 경우 셋팅

		cmmEmailSendVO.setCustomerRegNo(appCreateReq.getCustomerRegNo());	//회원 등록 번호
		cmmEmailSendVO.setTermsRegNo(appCreateReq.getTermsRegNo());			//약관 동의 번호

		//1.이메일 템플릿 조회 -> 2.이메일발송 유형 구분 -> 3.메일 발송 -> (성공시) -> 4.com_email_send_info 로그적재 관련
		emailService.sendEmail(cmmEmailSendVO, request);
	}
}

