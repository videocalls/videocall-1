package kr.co.koscom.oppf.apt.usr.mbrReg.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.koscom.oppf.cmm.util.CodeConstants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.sptUsr.service.SptSvcApplVO;
import kr.co.koscom.oppf.apt.sptUsr.service.SptUserManageVO;
import kr.co.koscom.oppf.apt.sptUsr.service.impl.SptSvcApplDAO;
import kr.co.koscom.oppf.apt.usr.mbrReg.service.NewMbrRegService;
import kr.co.koscom.oppf.apt.usr.mbrReg.service.NewMbrRegTermsVO;
import kr.co.koscom.oppf.apt.usr.mbrReg.service.NewMbrRegVO;
import kr.co.koscom.oppf.cmm.otp.service.CmmOtpReqService;
import kr.co.koscom.oppf.cmm.otp.service.CmmOtpReqVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.tsa.service.CmmTsaVO;
import kr.co.koscom.oppf.cmm.tsa.service.impl.CmmTsaDAO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerCompanyTermsProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceAccountProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceTermsFileProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceTermsProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceTermsPubcompanyProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SvcApplVO;

@Service("NewMbrRegService")
public class NewMbrRegServiceImpl implements NewMbrRegService {

	@Resource(name = "NewMbrRegDAO")
	private NewMbrRegDAO newMbrRegDAO;

	@Resource(name = "SptSvcApplDAO")
	private SptSvcApplDAO sptSvcApplDAO;

	@Resource(name = "CmmTsaDAO")
	private CmmTsaDAO cmmTsaDAO;

	@Resource(name = "CmmOtpReqService")
    private CmmOtpReqService cmmOtpReqService;
    

	private static final Logger log = Logger.getLogger(NewMbrRegServiceImpl.class);

	/**
	 * @Method Name : procMbrReg1
	 * @Method description : [회원가입]정보를 저장을 한다.
	 * @param :
	 *            NewMbrRegVO
	 * @return : NewMbrRegVO
	 * @throws :
	 *             Exception
	 */
	@Transactional
	public int saveNewMbrReg(NewMbrRegVO paramVO) throws Exception {
		String customerRegNo = "";
		NewMbrRegVO returnNewMbrRegVO = new NewMbrRegVO();
		int rs = 0;
		int rs1 = 0;
		int rs2 = 0;
		int rs3 = 0;
		int rs4 = 0;
		int rs5 = 0;

		// 회원가입:기본]정보등록을 한다
		customerRegNo = insertSptCustomerInfoProfile(paramVO);
		rs = rs + 1;
		paramVO.setCustomerRegNo(customerRegNo);

		// [회원가입:기본]정보 히스토리를 등록 한다
		rs4 = insertSptCustomerInfoProfileHist(paramVO);

		// 1-1-2.CI 인증 DB등록

		paramVO.setCustomerVerify(paramVO.getCiCustomerVerify());
		paramVO.setCustomerVerifyType("G007001");
		rs1 = insertSptCustomerVerifyProfile(paramVO);

		// 1-1-3.공인인증 DB등록
		paramVO.setCustomerVerify(paramVO.getDnCustomerVerify());
		paramVO.setCustomerVerifyType("G007002");
		rs2 = insertSptCustomerVerifyProfile(paramVO);

		// 3.약관 동의 정보 저장
		rs3 = insertSptCustomerTermsProfile(paramVO);

		// 5.리턴값셋팅
		returnNewMbrRegVO.setCustomerRegNo(customerRegNo);
		returnNewMbrRegVO.setCustomerStep(paramVO.getCustomerStep());

		log.debug("saveCustomerTermsListPopup");
		rs = rs + rs1 + rs2 + rs3;
		return rs;

	}

	/**
	 * @Method Name : insertSptCustomerInfoProfile
	 * @Method description : [회원가입:기본]정보등록을 한다
	 * @param :
	 *            NewMbrRegVO
	 * @return : String
	 * @throws :
	 *             Exception
	 */
	@Transactional
	public String insertSptCustomerInfoProfile(NewMbrRegVO paramVO) throws Exception {

		log.info("======================================================");
		// 1-1-1.기본정보 DB등록
		log.info("============= 1-1-1.기본정보 DB등록前 param정보=============");
		log.debug("회원가입Step(paramVO.customerStep)=" + paramVO.getCustomerStep());
		log.debug("회원휴대폰번호(paramVO.customerPhone)=" + paramVO.getCustomerPhone());
		log.debug("회원명한글(paramVO.customerNameKor)=" + paramVO.getCustomerNameKor());
		String customerRegNo = newMbrRegDAO.insertSptCustomerInfoProfile(paramVO);
		log.debug("1-1-1.기본정보 DB등록後:customerRegNo=" + customerRegNo);

		return customerRegNo;
	}

	/**
	 * @Method Name : insertSptCustomerInfoProfileHist
	 * @Method description : [회원가입:기본]정보 히스토리를 등록 한다
	 * @param :
	 *            NewMbrRegVO
	 * @return : int
	 * @throws :
	 *             Exception
	 */
	@Transactional
	public int insertSptCustomerInfoProfileHist(NewMbrRegVO paramVO) throws Exception {
		int rs = 0;
		// 2.기본정보hist DB등록
		log.info("============= 2.기본정보hist DB등록前 param정보=============");
		log.debug("회원OP등록번호(paramVO.customerRegNo)=" + paramVO.getCustomerRegNo());
		rs = newMbrRegDAO.insertSptCustomerInfoProfileHist(paramVO);
		log.debug("2.기본정보hist DB등록後:rs3=" + rs);
		log.info("======================================================");

		return rs;
	}

	/**
	 * @Method Name : insertSptCustomerInfoProfileHist
	 * @Method description : [회원가입:기본]인증 DB등록
	 * @param :
	 *            NewMbrRegVO
	 * @return : int
	 * @throws :
	 *             Exception
	 */
	@Transactional
	public int insertSptCustomerVerifyProfile(NewMbrRegVO paramVO) throws Exception {
		int rs = 0;
		int rs1 = 0;

		// log.info("============= 1-1-2.CI 인증 DB등록前 param정보=============");

		int rtnCnt = newMbrRegDAO.chkSptCustomerVerifyProfile(paramVO);

		if (rtnCnt > 0) {
			rs = newMbrRegDAO.updateSptCustomerVerifyProfile(paramVO);
		} else {
			rs = newMbrRegDAO.insertSptCustomerVerifyProfile(paramVO);
		}
		rs = newMbrRegDAO.insertSptCustomerVerifyProfileHist(paramVO);

		log.debug("1-1-3.CI 인증 DB등록後");

		// 3.인증hist DB등록
		// log.info("============= 3.인증hist DB등록前 param정보===============");
		rtnCnt = newMbrRegDAO.chkSptCustomerVerifyProfile(paramVO);

		if (rtnCnt > 0) {
			rs = newMbrRegDAO.updateSptCustomerVerifyProfile(paramVO);
		} else {
			rs = newMbrRegDAO.insertSptCustomerVerifyProfile(paramVO);
		}
		rs = newMbrRegDAO.insertSptCustomerVerifyProfileHist(paramVO);

		log.debug("3.인증hist DB등록後");
		return rs + rs1;
	}

	/**
	 * @Method Name : insertSptCustomerInfoProfileHist
	 * @Method description : [회원가입:기본]약관 동의 정보 저장
	 * @param :
	 *            NewMbrRegVO
	 * @return : int
	 * @throws :
	 *             Exception
	 */
	@Transactional
	public int insertSptCustomerTermsProfile(NewMbrRegVO paramVO) throws Exception {
		int rs = 0;

		int rs2 = 0;

		List<NewMbrRegTermsVO> paramTermsList = makeTermsList(paramVO);

		if (paramTermsList != null && paramTermsList.size() > 0) {
			for (int i = 0; i < paramTermsList.size(); i++) {
				NewMbrRegTermsVO mbrRegTermsVO = (NewMbrRegTermsVO) paramTermsList.get(i);
				mbrRegTermsVO.setCustomerRegNo(paramVO.getCustomerRegNo());
				int rs1 = 0;

				log.debug("customerRegNo[" + i + "]=" + mbrRegTermsVO.getCustomerRegNo());
				log.debug("customerTermsType[" + i + "]=" + mbrRegTermsVO.getCustomerTermsType());
				log.debug("customerTermsContentRegSeq[" + i + "]=" + mbrRegTermsVO.getCustomerTermsContentRegSeq());
				log.debug("customerTermsAuthYn[" + i + "]=" + mbrRegTermsVO.getCustomerTermsAuthYn());

				int chk = newMbrRegDAO.checkMainSptCustomerTerms(mbrRegTermsVO);

				// 기존정보가 있으면 update
				if (chk > 0) {
					rs1 = newMbrRegDAO.updateSptCustomerTermsProfile(mbrRegTermsVO);
					// 없으면 insert
				} else {
					rs1 = newMbrRegDAO.insertSptCustomerTermsProfile(mbrRegTermsVO);
				}
				rs = rs + rs1;
				// 2.약관hist DB등록
				newMbrRegDAO.insertSptCustomerTermsProfileHist(mbrRegTermsVO);

				log.debug("saveCustomerTermsListPopup");
				log.debug("insertSptCustomerTermsProfileHist");

			}
		}
		return rs;
	}

	/**
	 * @Method Name : makeTermsList
	 * @Method description : 약관 정보를 list를 생성
	 * @param :
	 *            NewMbrRegVO
	 * @return : List<NewMbrRegTermsVO>
	 * @throws :
	 *             Exception
	 */
	public List<NewMbrRegTermsVO> makeTermsList(NewMbrRegVO paramVO) throws Exception {

		List<NewMbrRegTermsVO> paramTermsList = new ArrayList();
		NewMbrRegTermsVO mbrRegTerms = new NewMbrRegTermsVO();
		String customerRegNo = paramVO.getCustomerRegNo();

		// 약관 리스트 생성

		// 서비스 이용약관
		mbrRegTerms.setCustomerRegNo(customerRegNo);
		mbrRegTerms.setCustomerTermsType("G008001");
		mbrRegTerms.setCustomerTermsAuthYn(paramVO.getServiceTerms());
		paramTermsList.add(mbrRegTerms);
		mbrRegTerms = new NewMbrRegTermsVO();

		// 개인정보수집 및 이용동의
		mbrRegTerms.setCustomerRegNo(customerRegNo);
		mbrRegTerms.setCustomerTermsType("G008002");
		mbrRegTerms.setCustomerTermsAuthYn(paramVO.getIndTerms());
		paramTermsList.add(mbrRegTerms);
		mbrRegTerms = new NewMbrRegTermsVO();

		// 개인정보 제3자 제공 동의
		mbrRegTerms.setCustomerRegNo(customerRegNo);
		mbrRegTerms.setCustomerTermsType("G008003");
		mbrRegTerms.setCustomerTermsAuthYn(paramVO.getThirdProvideTerms());
		paramTermsList.add(mbrRegTerms);
		mbrRegTerms = new NewMbrRegTermsVO();

		// 공인인증서 등록 약관동의
		mbrRegTerms.setCustomerRegNo(customerRegNo);
		mbrRegTerms.setCustomerTermsType("G008010");
		mbrRegTerms.setCustomerTermsAuthYn(paramVO.getCertiTerms());
		paramTermsList.add(mbrRegTerms);
		mbrRegTerms = new NewMbrRegTermsVO();

		return paramTermsList;
	}

	/**
	 * @Method Name : updateSptUserManageDtlDev
	 * @Method description : 일반회원 정보 수정
	 * @param :
	 *            sptUserManageVO
	 * @return : int
	 * @throws :
	 *             Exception
	 */
	@Transactional
	public int updateSptUserManageDtlDev(NewMbrRegVO paramVO) throws Exception {
		//
		int result = newMbrRegDAO.updateSptUserManageDtlDev(paramVO);

		result = newMbrRegDAO.insertSptCustomerInfoProfileHist(paramVO);

		// 1-1-2.CI 인증 DB등록

		paramVO.setCustomerVerify(paramVO.getCiCustomerVerify());
		paramVO.setCustomerVerifyType("G007001");

		result = insertSptCustomerVerifyProfile(paramVO);

		// 1-1-3.공인인증 DB등록
		paramVO.setCustomerVerify(paramVO.getDnCustomerVerify());
		paramVO.setCustomerVerifyType("G007002");
		result = insertSptCustomerVerifyProfile(paramVO);

		// 3.약관 동의 정보 저장
		result = insertSptCustomerTermsProfile(paramVO);

        CmmOtpReqVO cmmOtpReqVO = new CmmOtpReqVO();
        cmmOtpReqVO.setCustomerRegNo(paramVO.getCustomerRegNo());
        cmmOtpReqVO.setCustomerOtpId(paramVO.getCustomerOtpId());
        
        if("Y".equals(paramVO.getCustomerOtpYn())){
        	result = cmmOtpReqService.saveOtpProfileDev(cmmOtpReqVO);
        } else {
        	cmmOtpReqService.deleteOtpProfileDev(cmmOtpReqVO);
        }
        
		
		return result;
	}

	/**
	 * @Method Name : selectMemberInfo
	 * @Method description : [기본]회원정보를 조회한다.
	 * @param :
	 *            NewMbrRegVO
	 * @return : NewMbrRegVO
	 * @throws :
	 *             Exception
	 */
	public NewMbrRegVO selectMemberInfo(NewMbrRegVO paramVO) throws Exception {

		return newMbrRegDAO.selectMemberInfo(paramVO);

	}

	/**
	 * @Method Name : saveFintechSvcTerms
	 * @Method description : [핀테크서비스선택] 가상계좌 선택 정보를 저장한다.
	 * @param :
	 *            SvcApplVO
	 * @return : int
	 * @throws :
	 *             Exception
	 */
	@Transactional
	public int saveFintechSvcTerms(SptSvcApplVO sptSvcApplVO) throws Exception {
		int result = -1;
		String customerRegNo = sptSvcApplVO.getCustomerRegNo();
		String serviceRegNo = sptSvcApplVO.getServiceRegNo();

		/**
		 * 1. 일반회원서비스 계좌 프로파일 정보 등록/수정/삭제 2. 일반회원서비스 약관 프로파일 정보 등록 3. 일반회원서비스 약관
		 * 금투사 프로파일 정보 등록
		 * 
		 * 모든 처리가 끝난후 데이터 클랜징을 한다. 클랜징 대상 1. 새로 추가한 서비스 중 기존에 동일한 핀테크기업에 동일한
		 * 금투사로 정보제공동의가 있을 경우 정보제공동의를 같이쓰게 해줘야 한다.
		 */

		log.debug("기본 param을 가져온다. start");
		// 기본 param을 가져온다.
		List<SptCustomerServiceAccountProfileVO> accountProfile = sptSvcApplVO.getSptCustomerServiceAccountProfileVO();
		List<SptCustomerServiceTermsProfileVO> termsProfile = sptSvcApplVO.getSptCustomerServiceTermsProfileVO();
		List<SptCustomerServiceTermsPubcompanyProfileVO> termsPubcompanyProfile = sptSvcApplVO
				.getSptCustomerServiceTermsPubcompanyProfileVO();
		log.debug("기본 param을 가져온다. end");

		if (accountProfile != null && accountProfile.size() > 0 && termsProfile != null && termsProfile.size() > 0
				&& termsPubcompanyProfile != null && termsPubcompanyProfile.size() > 0) {
			// 1. 일반회원서비스 계좌 프로파일 정보 등록/수정/삭제
			for (int i = 0; i < accountProfile.size(); i++) {
				SptCustomerServiceAccountProfileVO accountVO = (SptCustomerServiceAccountProfileVO) accountProfile
						.get(i);
				accountVO.setCustomerRegNo(customerRegNo);
				//accountVO.setServiceRegNo(serviceRegNo);
				accountVO.setCreateId(customerRegNo);
				//System.out.println("accountVO.getActionType() ="+accountVO.getActionType());
				// 일반회원서비스 계좌 프로파일 정보 정보를 저장한다.
				if (!"".equals(accountVO.getActionType())) {
					if ("I".equals(accountVO.getActionType())) {
						String accountRegNo = sptSvcApplDAO.insertSptCustomerServiceAccountProfile(accountVO);
						accountVO.setAccountRegNo(accountRegNo);
					} else {
						result = sptSvcApplDAO.updateSptCustomerServiceAccountProfile(accountVO);
					}

					// 일반회원서비스 계좌 프로파일 hist 정보를 등록한다.
					result = sptSvcApplDAO.insertSptCustomerServiceAccountProfileHist(accountVO);
				}
			}

			// 2. 일반회원서비스 약관 프로파일 정보 등록
			for (int i = 0; i < termsProfile.size(); i++) {
				SptCustomerServiceTermsProfileVO termsVO = (SptCustomerServiceTermsProfileVO) termsProfile.get(i);
				String subcompanyCodeId = termsVO.getSubcompanyCodeId();
				String termsRegNo = "";

				// 기본정보 셋팅
				termsVO.setCustomerRegNo(customerRegNo);
				termsVO.setCreateId(customerRegNo);
				termsVO.setTermsPolicy(sptSvcApplVO.getTermsPolicy());

				log.debug("termsVO.getActionType() = " + termsVO.getActionType());
				log.debug("subcompanyCodeId = " + subcompanyCodeId);

				if ("I".equals(termsVO.getActionType())) {
					// 약관정보 등록
					termsRegNo = sptSvcApplDAO.insertSptCustomerServiceTermsProfile(termsVO);
					termsVO.setTermsRegNo(termsRegNo);
					result = sptSvcApplDAO.insertSptCustomerServiceTermsProfileHist(termsVO);

					for (int j = 0; j < termsPubcompanyProfile.size(); j++) {
						SptCustomerServiceTermsPubcompanyProfileVO termsPubcompanyVO = (SptCustomerServiceTermsPubcompanyProfileVO) termsPubcompanyProfile
								.get(j);
						// 같은 subcompanyCodeId일때
						log.debug(
								"termsPubcompanyVO.getSubcompanyCodeId() = " + termsPubcompanyVO.getSubcompanyCodeId());
						if (subcompanyCodeId.equals(termsPubcompanyVO.getSubcompanyCodeId())) {
							String termsPubcompanyRegNo = "";
							// 기본정보 셋팅
							termsPubcompanyVO.setCustomerRegNo(customerRegNo);
							termsPubcompanyVO.setCreateId(customerRegNo);
							termsPubcompanyVO.setTermsRegNo(termsRegNo);

							termsPubcompanyRegNo = sptSvcApplDAO
									.insertSptCustomerServiceTermsPubcompanyProfile(termsPubcompanyVO);
							termsPubcompanyVO.setTermsPubcompanyRegNo(termsPubcompanyRegNo);
							result = sptSvcApplDAO
									.insertSptCustomerServiceTermsPubcompanyProfileHist(termsPubcompanyVO);
						}

					}

					// 3. 일반회원 서비스 프로파일 약관등록번호 update
					SptCustomerServiceProfileVO serviceProfileVO = new SptCustomerServiceProfileVO();
					serviceProfileVO.setCustomerRegNo(customerRegNo);
					serviceProfileVO.setCreateId(customerRegNo);
					serviceProfileVO.setTermsRegNo(termsRegNo);
					serviceProfileVO.setCompanyCodeId(subcompanyCodeId);

					// 일반회원 서비스 프로파일 정보를 수정한다.
					result = sptSvcApplDAO.updateSptCustomerServiceProfile(serviceProfileVO);
					// 일반회원 서비스 프로파일 hist 정보를 등록한다.
					result = sptSvcApplDAO.updateSptCustomerServiceProfileHist(serviceProfileVO);
				} else if ("D".equals(termsVO.getActionType())) {
					// 3. 일반회원 서비스 프로파일 약관등록번호 update -> terms를 null처리한다.
					SptCustomerServiceProfileVO serviceProfileVO = new SptCustomerServiceProfileVO();
					serviceProfileVO.setCustomerRegNo(customerRegNo);
					serviceProfileVO.setCreateId(customerRegNo);
					serviceProfileVO.setTermsRegNo(null);
					serviceProfileVO.setCompanyCodeId(subcompanyCodeId);

					// 일반회원 서비스 프로파일 정보를 수정한다.
					result = sptSvcApplDAO.updateSptCustomerServiceProfile(serviceProfileVO);
					// 일반회원 서비스 프로파일 hist 정보를 등록한다.
					result = sptSvcApplDAO.updateSptCustomerServiceProfileHist(serviceProfileVO);
				}
			}
		}

		// 데이터 클랜징 작업 시작 -> 로직으로 풀기 힘든 정보를 데이터 조회/수정 처리를 통해 알맞은 정보로 셋팅한다.
		// 1. 새로 추가한 서비스 중 기존에 동일한 핀테크기업에 동일한 금투사로 정보제공동의가 있을 경우 정보제공동의를 같이쓰게
		// 해줘야 한다.
		// 서비스의 정보제공동의 클랭징 목록을 조회한다.
		List<SvcApplVO> svcTermsCleanList = sptSvcApplDAO.selectFintechSvcTermsCleanList(sptSvcApplVO);
		if (svcTermsCleanList != null) {
			for (int i = 0; i < svcTermsCleanList.size(); i++) {
				SvcApplVO cleanData = (SvcApplVO) svcTermsCleanList.get(i);

				SptCustomerServiceProfileVO serviceProfileVO = new SptCustomerServiceProfileVO();
				serviceProfileVO.setCustomerRegNo(customerRegNo);
				serviceProfileVO.setCreateId(customerRegNo);
				serviceProfileVO.setServiceRegNo(cleanData.getServiceRegNo());
				serviceProfileVO.setTermsRegNo(cleanData.getTermsRegNo());

				// 서비스 프로파일에 정보제공 동의 번호를 update한다.
				result = sptSvcApplDAO.updateSptCustomerServiceProfileSvcTermsClean(serviceProfileVO);
				log.debug(
						"END~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				// 서비스 프로파일 hist 정보를 등록한다.
				serviceProfileVO = new SptCustomerServiceProfileVO();
				result = sptSvcApplDAO.insertSptCustomerServiceProfileHist(serviceProfileVO);
			}
		}

		return result;
	}

	/**
	 * @Method Name : selectSvcCompanyTermsConsntList
	 * @Method description : [약관동의] 약관동의 목록을 조회한다.
	 * @param :
	 *            SvcApplVO
	 * @return : Map<String, Object>
	 * @throws :
	 *             Exception
	 */
	public Map<String, Object> selectSvcCompanyTermsConsntList(SptSvcApplVO sptSvcApplVO) throws Exception {
		List<SptCustomerCompanyTermsProfileVO> resultList = sptSvcApplDAO.selectSvcCompanyTermsConsntList(sptSvcApplVO);
		List<SptCustomerCompanyTermsProfileVO> resultAgreeCompanyList = sptSvcApplDAO
				.selectSvcCompanyTermsConsntAgreeCompanyList(sptSvcApplVO);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", resultList);
		map.put("resultAgreeCompanyList", resultAgreeCompanyList);

		return map;
	}

	/**
	 * @Method Name : saveSvcCompanyTermsConsnt
	 * @Method description : [약관동의] 기업약관을 저장한다.
	 * @param :
	 *            SvcApplVO
	 * @return : int
	 * @throws :
	 *             Exception
	 */
	@Transactional
	public int saveSvcCompanyTermsConsnt(SptSvcApplVO sptSvcApplVO) throws Exception {
		int result = -1;
		String customerRegNo = sptSvcApplVO.getCustomerRegNo();
		String companyTermsType = "G031001"; // 기업 서비스 이용약관

		/**
		 * 1. 기업약관 정보 확인 2. 기업약관 정보 등록 or 수정
		 */

		// 기본 param을 가져온다.
		List<SptCustomerCompanyTermsProfileVO> termsList = sptSvcApplVO.getSptCustomerCompanyTermsProfileVO();
		if (termsList != null && termsList.size() > 0) {
			for (int i = 0; i < termsList.size(); i++) {
				SptCustomerCompanyTermsProfileVO data = (SptCustomerCompanyTermsProfileVO) termsList.get(i);

				// 기본정보 셋팅
				data.setCustomerRegNo(customerRegNo);
				data.setCompanyTermsType(companyTermsType);
				data.setCreateId(customerRegNo);
				data.setCompanyTermsAuthYn("Y");

				// 1. 기업약관 정보 확인
				int cnt = sptSvcApplDAO.checkSptCustomerCompanyTermsProfile(data);

				// 기존에 있으면 update
				if (cnt > 0) {
					result = sptSvcApplDAO.updateSptCustomerCompanyTermsProfile(data);

					// 있으면 insert
				} else {
					result = sptSvcApplDAO.insertSptCustomerCompanyTermsProfile(data);
				}

				// hist insert
				result = sptSvcApplDAO.insertSptCustomerCompanyTermsProfileHist(data);
			}
		}

		return result;
	}

	/**
	 * @Method Name : selectSvcTermConsntList
	 * @Method description : [정보제공동의] 정보제공동의 목록을 조회한다.
	 * @param :
	 *            SvcApplVO
	 * @return : Map<String, Object>
	 * @throws :
	 *             Exception
	 */
	public Map<String, Object> selectSvcTermConsntList(SptSvcApplVO sptSvcApplVO) throws Exception {
		List<SptCustomerServiceAccountProfileVO> resultList = null;

		// 핀테크 서비스 약관 목록 조회
		resultList = sptSvcApplDAO.selectSvcTermConsntList(sptSvcApplVO);
		for (int i = 0; i < resultList.size(); i++) {
			String termsRegNo = resultList.get(i).getTermsRegNo();
			if (!OppfStringUtil.isEmpty(termsRegNo)) {
				termsRegNo = OppfStringUtil.base64Incoding(termsRegNo);
				resultList.get(i).setTermsRegNoEncryption(termsRegNo);
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", resultList);

		return map;
	}

	/**
	 * @Method Name : procTsa
	 * @Method description : TSA처리
	 * @param :
	 *            CmmTsaVO
	 * @return : HashMap<String,Object>
	 * @throws :
	 *             Exception
	 */
	@Transactional
	public HashMap<String, Object> procTsa(CmmTsaVO paramVO) throws Exception {
		HashMap<String, Object> rsMap = new HashMap<String, Object>();
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddhhmmss");

		String filePathfileName = ""; // 파일풀경로파일명(확장자뺀)
		String filePathOriginalfileName = ""; // 원본파일풀경로파일명(확장자뺀)
		String makeHtmlContent = ""; // 만들html내용
		String makePdfContent = ""; // 만들pdf내용
		String makeTxtContent = ""; // 만들txt내용

		String oriSignData = paramVO.getSignData(); // 오리지널 signData -> 암호화된 값

		// 년수취득
		paramVO.setTermsPolicyYear(OppfProperties.getProperty("Globals.user.policy.password.final"));

		// 1.임시폴더생성작업
		String folderPath = ""; // 임시폴더경로및폴더명

		// 1-1.tmpTsa폴더 생성
		folderPath = OppfProperties.getProperty("Globals.spt.webcontent.path") + "/upload/tmpTsa";
		HashMap<String, Object> rsMapForder1 = mkDirTsa(folderPath);
		// 1-2.오늘날짜 폴더 생성(tmpTsa/오늘날짜)
		folderPath = OppfProperties.getProperty("Globals.spt.webcontent.path") + "/upload/tmpTsa/" + sdf.format(today);
		HashMap<String, Object> rsMapForder2 = mkDirTsa(folderPath);
		// 1-3.사용자등록번호 폴더 생성(tmpTsa/오늘날짜/사용자등록번호)
		folderPath = OppfProperties.getProperty("Globals.spt.webcontent.path") + "/upload/tmpTsa/" + sdf.format(today)
				+ "/" + paramVO.getCustomerRegNo();
		HashMap<String, Object> rsMapForder3 = mkDirTsa(folderPath);

		// 2.설정작업
		// 2-1.파일경로설정
		paramVO.setFilePath(folderPath);

		// 2-2.파일명설정
		paramVO.setFileName(paramVO.getCustomerRegNo() + "_" + sdf2.format(today));

		// 2-3.(저장경로+파일명)설정
		filePathfileName = paramVO.getFilePath() + "/" + paramVO.getFileName();

		// 5.원본설정작업
		// 5-1.원본파일명설정
		paramVO.setFileNameOriginal(
				paramVO.getCustomerRegNo() + "_" + sdf2.format(today) + paramVO.getFileNmMarkOriginal());

		
		// 10.약관파일DB저장
		List<SptCustomerServiceTermsFileProfileVO> termsFileList = new ArrayList<SptCustomerServiceTermsFileProfileVO>();
		SptCustomerServiceTermsFileProfileVO pScstfpVO = new SptCustomerServiceTermsFileProfileVO();
		

		
		// 10-1.약관파일 DB등록 강제 값세팅
        
		  
		//[서명+원본]파일 인 경우
		pScstfpVO = new SptCustomerServiceTermsFileProfileVO();
		//설정:회원OP등록번호
		pScstfpVO.setCustomerRegNo(paramVO.getCustomerRegNo());
		//설정:동의서파일종류[G027=(001:원본, 002:전자서명파일, 003:TSA원본, 004:TSA적용)]
		pScstfpVO.setTermsFileType(CodeConstants.TERMS_FILE_TYPE_002);
		//설정:동의서파일
		pScstfpVO.setTermsFileData(null);
		//설정:TSA결과해쉬값
		pScstfpVO.setDataHash(paramVO.getResTsaHashValue());
		
		//vo를list에추가
		termsFileList.add(pScstfpVO);
		
		//[원본]파일 인 경우
		pScstfpVO = new SptCustomerServiceTermsFileProfileVO();
		
		//설정:회원OP등록번호
		pScstfpVO.setCustomerRegNo(paramVO.getCustomerRegNo());
		
		//설정:동의서파일종류[G027=(001:원본, 002:전자서명파일, 003:TSA원본, 004:TSA적용)]
		pScstfpVO.setTermsFileType(CodeConstants.TERMS_FILE_TYPE_001);
		
		//설정:동의서파일
		pScstfpVO.setTermsFileData(null);
		
		//설정:TSA결과해쉬값
		if(!OppfStringUtil.isEmpty(paramVO.getResTsaHashValue())){
		    pScstfpVO.setDataHash(paramVO.getResTsaHashValue());
		}
		
		//vo를list에추가
		termsFileList.add(pScstfpVO);
		
		
		//[req]파일 인 경우
		pScstfpVO = new SptCustomerServiceTermsFileProfileVO();
		
		//설정:회원OP등록번호
		pScstfpVO.setCustomerRegNo(paramVO.getCustomerRegNo());
		
		//설정:동의서파일종류[G027=(001:원본, 002:전자서명파일, 003:TSA원본, 004:TSA적용)]
		pScstfpVO.setTermsFileType(CodeConstants.TERMS_FILE_TYPE_003);
		
		//설정:동의서파일
		pScstfpVO.setTermsFileData(null);
		
		//설정:TSA결과해쉬값
		if(!OppfStringUtil.isEmpty(paramVO.getResTsaHashValue())){
		    pScstfpVO.setDataHash(paramVO.getResTsaHashValue());
		}
		
		//vo를list에추가
		termsFileList.add(pScstfpVO);
		
		
		//[rep]파일 인 경우
		pScstfpVO = new SptCustomerServiceTermsFileProfileVO();
		
		//설정:회원OP등록번호
		pScstfpVO.setCustomerRegNo(paramVO.getCustomerRegNo());
		
		//설정:동의서파일종류[G027=(001:원본, 002:전자서명파일, 003:TSA원본, 004:TSA적용)]
		pScstfpVO.setTermsFileType(CodeConstants.TERMS_FILE_TYPE_004);
		
		//설정:동의서파일
		pScstfpVO.setTermsFileData(null);
		
		//설정:TSA결과해쉬값
		if(!OppfStringUtil.isEmpty(paramVO.getResTsaHashValue())){
		    pScstfpVO.setDataHash(paramVO.getResTsaHashValue());
		}
		
		//vo를list에추가
		    termsFileList.add(pScstfpVO);
		 
   
       
        //10-2.약관파일 DB등록 작업前 필터링처리
        if(termsFileList.size() != 4){
            //메세지 설정[성공]
            rsMap.put("rsCd", "101");
            rsMap.put("rsCdMsg", "DB에 등록 할 파일의 갯수가 잘못되었습니다.termsFileList.size()="+termsFileList.size());
            return rsMap;
        }
		
		// 10-3.약관파일 DB등록 작업
		String termsFileRegNo = sptSvcApplDAO.selectTermsFileRegNo(paramVO);
		if (OppfStringUtil.isEmpty(termsFileRegNo)) {
			// 메세지 설정[성공]
			rsMap.put("rsCd", "102");
			rsMap.put("rsCdMsg", "정보제공 동의 파일 등록번호 생성 실패.");
			return rsMap;
		}

		int dbInsertCnt = 0;
		for (int i = 0; i < termsFileList.size(); i++) {
			SptCustomerServiceTermsFileProfileVO data = (SptCustomerServiceTermsFileProfileVO) termsFileList.get(i);
			data.setTermsFileRegNo(termsFileRegNo);

			int termsfileRs = insertSptCustomerServiceTermsFileProfile(data);
			log.debug("termsfileRs=" + termsfileRs);
			dbInsertCnt += termsfileRs;
		}

		// 11.약관상태값 DB수정
		SptCustomerServiceTermsProfileVO pScstpVO = new SptCustomerServiceTermsProfileVO();
		// 11-1.약관상태값 DB수정前값세팅
		pScstpVO.setCustomerRegNo(paramVO.getCustomerRegNo());
		pScstpVO.setTermsRegNo(paramVO.getTermsRegNo());
		pScstpVO.setTermsFileRegNo(termsFileRegNo);
		pScstpVO.setTermsStartDate(paramVO.getTermsStartDate());
		pScstpVO.setTermsPolicy(paramVO.getTermsPolicy());
		pScstpVO.setTermsAuthYn("N");
		pScstpVO.setTermsFileStatus("G028030"); // 동의서파일처리상태[G028=(010:원본등록성공,
												// 011:원본등록실패, 020:전자서명파일성공,
												// 021:전자서명파일실패, 030:TSA처리성공,
												// 031:TSA처리실패)]

		// 동의서명정보 셋팅
		pScstpVO.setCustomerSignDn(paramVO.getDn());
		pScstpVO.setCustomerSignData(oriSignData);
		pScstpVO.setCustomerTsaData("본 동의서는 테스트용입니다.");

		log.debug("11-2.약관상태값 DB수정後:customerSignDn=" + pScstpVO.getCustomerSignDn());
		log.debug("11-2.약관상태값 DB수정後:customerSignData=" + pScstpVO.getCustomerSignData());
		log.debug("11-2.약관상태값 DB수정後:customerTsaData=" + pScstpVO.getCustomerTsaData());

		// 11-2.약관상태값 DB수정
		int termsRs = updateSptCustomerServiceTermsProfile(pScstpVO);
		log.debug("11-2.약관상태값 DB수정後:termsRs=" + termsRs);

		// 12.삭제작업(폴더안의 폴더 또는 파일 삭제)
		// String delfolderPath =
		// OppfProperties.getProperty("Globals.spt.webcontent.path")+"/upload/tmpTsa/"+sdf.format(today)+"/"+paramVO.getCustomerRegNo()+"/";
		// log.debug("12.삭제작업(폴더안의 폴더 또는 파일 삭제):delfolderPath="+delfolderPath);
		// rmDirTsa(delfolderPath);

		// 메세지 설정[성공]
		rsMap.put("rsCd", "00");
		rsMap.put("rsCdMsg", "tsa 작업이 정상적으로 완료 되었습니다.");
		return rsMap;
	}

	/**
	 * @Method Name : mkDirTsa
	 * @Method description : TSA처리를 위한 임시 폴더를 생성
	 * @param :
	 *            folderPathName
	 * @return : HashMap<String,Object>
	 * @throws :
	 *             Exception
	 */
	@Override
	public HashMap<String, Object> mkDirTsa(String folderPathName) {
		HashMap<String, Object> rsMap = new HashMap<String, Object>();

		//File cFile = new File(folderPathName);
		//if (!cFile.isDirectory()) {
			//cFile.mkdirs();
			// 메세지 설정[성공]
			rsMap.put("rsCd", "00");
			rsMap.put("rsCdMsg", "해당폴더가 정상적으로 만들어졌습니다.folderPathName=" + folderPathName);
		//} else {
			// 메세지 설정[성공]
		//	rsMap.put("rsCd", "01");
		//	rsMap.put("rsCdMsg", "해당폴더가 존재합니다.folderPathName=" + folderPathName);
		//}
		return rsMap;
	}

	/**
	 * @Method Name : insertSptCustomerServiceTermsFileProfile
	 * @Method description : [일반회원서비스약관파일프로파일]정보를 등록한다.
	 * @param :
	 *            SptCustomerServiceTermsFileProfileVO
	 * @return : int
	 * @throws :
	 *             Exception
	 */
	@Transactional
	public int insertSptCustomerServiceTermsFileProfile(SptCustomerServiceTermsFileProfileVO paramVO) throws Exception {
		int rs = 0;
		int rs1 = 0;
		int rs2 = 0;
		log.debug("동의서파일등록前데이터:customerRegNo=" + paramVO.getCustomerRegNo());
		log.debug("동의서파일등록前데이터:termsFileRegNo=" + paramVO.getTermsFileRegNo());
		log.debug("동의서파일등록前데이터:termsFileType=" + paramVO.getTermsFileType());
		log.debug("동의서파일등록前데이터:termsFileData=" + paramVO.getTermsFileData());

		rs1 = cmmTsaDAO.insertSptCustomerServiceTermsFileProfile(paramVO);
		rs2 = cmmTsaDAO.insertSptCustomerServiceTermsFileProfileHist(paramVO);

		rs = rs1 + rs2;
		return rs;
	}

	/**
	 * @Method Name : updateSptCustomerServiceTermsProfile
	 * @Method description : [일반회원서비스약관프로파일]정보를 수정한다.
	 * @param :
	 *            SptCustomerServiceTermsProfileVO
	 * @return : int
	 * @throws :
	 *             Exception
	 */
	@Transactional
	public int updateSptCustomerServiceTermsProfile(SptCustomerServiceTermsProfileVO paramVO) throws Exception {
		int rs = 0;
		int rs1 = 0;
		int rs2 = 0;
		log.debug("동의서파일등록前데이터:customerRegNo=" + paramVO.getCustomerRegNo());
		log.debug("동의서파일등록前데이터:termsRegNo=" + paramVO.getTermsRegNo());
		log.debug("동의서파일등록前데이터:termsStartDate=" + paramVO.getTermsStartDate());
		log.debug("동의서파일등록前데이터:termsPolicy=" + paramVO.getTermsPolicy());
		log.debug("동의서파일등록前데이터:termsAuthYn=" + paramVO.getTermsAuthYn());
		log.debug("동의서파일등록前데이터:termsFileStatus=" + paramVO.getTermsFileStatus());
		rs1 = cmmTsaDAO.updateSptCustomerServiceTermsProfile(paramVO);
		rs2 = cmmTsaDAO.insertSptCustomerServiceTermsProfileHist(paramVO);

		rs = rs1 + rs2;
		return rs;
	}



	/**
	 * @Method Name : deleteAccountSvcInfo
	 * @Method description : 가상계좌 서비스 연동 삭제
	 * @param :
	 *            SvcApplVO
	 * @return : int
	 * @throws :
	 *             Exception
	 */
	@Transactional
	public int deleteAccountSvcInfo(SptSvcApplVO sptSvcApplVO) throws Exception {
		int result = 0;
	
		int rscnt = 0;
		
		log.debug("0. SptCustomerServiceAccountProfile 삭제");
		rscnt = sptSvcApplDAO.selectCntSptCustomerServiceAccountProfile(sptSvcApplVO);
		
		log.debug("1. SptCustomerServiceAccountProfile 삭제");
		result=sptSvcApplDAO.deleteSptCustomerServiceAccountProfile(sptSvcApplVO);
		
		log.debug("2. SptCustomerServiceAccountProfileHist 삭제");
		result= result + sptSvcApplDAO.deleteSptCustomerServiceAccountProfileHist(sptSvcApplVO);
		
		if(rscnt<2){
			log.debug("7. SptCustomerServiceProfile update");
			sptSvcApplDAO.updateSptCustomerServiceProfile(sptSvcApplVO);
		}
		
		/*
		log.debug("3. SptCustomerServiceTermsProfile 삭제");
		sptSvcApplDAO.deleteSptCustomerServiceTermsProfile(sptSvcApplVO);
		
		log.debug("4. SptCustomerServiceTermsProfileHist 삭제");
		sptSvcApplDAO.deleteSptCustomerServiceTermsProfileHist(sptSvcApplVO);
		
		log.debug("5. SptCustomerServiceTermsPubcompanyProfile 삭제");
		sptSvcApplDAO.deleteSptCustomerServiceTermsPubcompanyProfile(sptSvcApplVO);
		
		log.debug("6. SptCustomerServiceTermsPubcompanyProfileHist 삭제");
		sptSvcApplDAO.deleteSptCustomerServiceTermsPubcompanyProfileHist(sptSvcApplVO);
		

		*/
		

		return result;
	}
	

	/**
	 * @Method Name : selectCntSptCustomerServiceAccountProfile
	 * @Method description : 계좌 삭제 가능 여부
	 * @param : SvcApplVO
	 * @return : int
	 * @throws : Exception
	 */
	@Override
	public int selectCntSptCustomerServiceAccountProfile(SptSvcApplVO sptSvcApplVO) throws Exception {
		int result = 0;
		
		log.debug("0. SptCustomerServiceAccountProfile 삭제");
		result = sptSvcApplDAO.selectCntSptCustomerServiceAccountProfile(sptSvcApplVO);
		
		return result;
	}


    /**
     * @Method Name        : sptUserVerifyDupChk
     * @Method description : 인증값 중복 체크
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @Transactional
    public int sptUserVerifyDupChk(NewMbrRegVO paramVO) throws Exception{
    	int result = newMbrRegDAO.sptUserVerifyDupChk(paramVO);
    	
    	return result;
    }
    
}
