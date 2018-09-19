package kr.co.koscom.oppf.apt.usr.mbrReg.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.sptUsr.service.SptSvcApplVO;
import kr.co.koscom.oppf.apt.sptUsr.service.SptUserManageVO;
import kr.co.koscom.oppf.cmm.tsa.service.CmmTsaVO;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceTermsFileProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceTermsProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SvcApplVO;

/**
 * @Project : 자본시장 공동 핀테크 오픈플랫폼 구축
 * @FileName : NewMbrRegService.java
 * @Comment : [회원가입]정보관리를 위한 Service 인터페이스
 * @author :
 * @since : 2016.05.02
 * @version : 1.0
 * @see
 *
 * 		<< 개정이력(Modification Information) >> 수정일 수정자 수정내용 ----------- ------
 *      ---------- 2016.05.02 이환덕 최초생성
 *
 */
public interface NewMbrRegService {

	/**
	 * @Method Name : procMbrReg1
	 * @Method description : [회원가입]정보를 저장을 한다.
	 * @param :
	 *            NewMbrRegVO
	 * @return : NewMbrRegVO
	 * @throws :
	 *             Exception
	 */
	public int saveNewMbrReg(NewMbrRegVO paramVO) throws Exception;

	/**
	 * @Method Name : insertSptCustomerInfoProfile
	 * @Method description : [회원가입:기본]정보등록을 한다
	 * @param :
	 *            NewMbrRegVO
	 * @return : String
	 * @throws :
	 *             Exception
	 */
	public String insertSptCustomerInfoProfile(NewMbrRegVO paramVO) throws Exception;

	/**
	 * @Method Name : insertSptCustomerInfoProfileHist
	 * @Method description : [회원가입:기본]정보 히스토리를 등록 한다
	 * @param :
	 *            NewMbrRegVO
	 * @return : int
	 * @throws :
	 *             Exception
	 */
	public int insertSptCustomerInfoProfileHist(NewMbrRegVO paramVO) throws Exception;

	/**
	 * @Method Name : insertSptCustomerInfoProfileHist
	 * @Method description : [회원가입:기본]인증 DB등록
	 * @param :
	 *            NewMbrRegVO
	 * @return : int
	 * @throws :
	 *             Exception
	 */
	public int insertSptCustomerVerifyProfile(NewMbrRegVO paramVO) throws Exception;

	/**
	 * @Method Name : insertSptCustomerInfoProfileHist
	 * @Method description : [회원가입:기본]약관 동의 정보 저장
	 * @param :
	 *            NewMbrRegVO
	 * @return : int
	 * @throws :
	 *             Exception
	 */
	public int insertSptCustomerTermsProfile(NewMbrRegVO paramVO) throws Exception;

	/**
	 * @Method Name : makeTermsList
	 * @Method description : 약관 정보를 list를 생성
	 * @param :
	 *            NewMbrRegVO
	 * @return : List<NewMbrRegTermsVO>
	 * @throws :
	 *             Exception
	 */
	public List<NewMbrRegTermsVO> makeTermsList(NewMbrRegVO paramVO) throws Exception;

	/**
	 * @Method Name : updateSptUserManageDtlDev
	 * @Method description : 일반회원 정보 수정
	 * @param :
	 *            sptUserManageVO
	 * @return : int
	 * @throws :
	 *             Exception
	 */
	public int updateSptUserManageDtlDev(NewMbrRegVO paramVO) throws Exception;

	/**
	 * @Method Name : selectMemberInfo
	 * @Method description : [기본]회원정보를 조회한다.
	 * @param :
	 *            NewMbrRegVO
	 * @return : NewMbrRegVO
	 * @throws :
	 *             Exception
	 */
	public NewMbrRegVO selectMemberInfo(NewMbrRegVO paramVO) throws Exception;

	/**
	 * @Method Name : saveFintechSvcTerms
	 * @Method description : [핀테크서비스선택] 가상계좌 선택 정보를 저장한다.
	 * @param :
	 *            SvcApplVO
	 * @return : int
	 * @throws :
	 *             Exception
	 */
	public int saveFintechSvcTerms(SptSvcApplVO sptSvcApplVO) throws Exception;

	/**
	 * @Method Name : selectSvcCompanyTermsConsntList
	 * @Method description : [약관동의] 약관동의 목록을 조회한다.
	 * @param :
	 *            SvcApplVO
	 * @return : Map<String, Object>
	 * @throws :
	 *             Exception
	 */
	public Map<String, Object> selectSvcCompanyTermsConsntList(SptSvcApplVO sptSvcApplVO) throws Exception;

	/**
	 * @Method Name : saveSvcCompanyTermsConsnt
	 * @Method description : [약관동의] 기업약관을 저장한다.
	 * @param :
	 *            SvcApplVO
	 * @return : int
	 * @throws :
	 *             Exception
	 */
	public int saveSvcCompanyTermsConsnt(SptSvcApplVO sptSvcApplVO) throws Exception;

	/**
	 * @Method Name : selectSvcTermConsntList
	 * @Method description : [정보제공동의] 정보제공동의 목록을 조회한다.
	 * @param :
	 *            SvcApplVO
	 * @return : Map<String, Object>
	 * @throws :
	 *             Exception
	 */
	public Map<String, Object> selectSvcTermConsntList(SptSvcApplVO sptSvcApplVO) throws Exception;

	/**
	 * @Method Name : procTsa
	 * @Method description : TSA처리
	 * @param :
	 *            CmmTsaVO
	 * @return : HashMap<String,Object>
	 * @throws :
	 *             Exception
	 */
	public HashMap<String, Object> procTsa(CmmTsaVO paramVO) throws Exception;

	/**
	 * @Method Name : mkDirTsa
	 * @Method description : TSA처리를 위한 임시 폴더를 생성
	 * @param :
	 *            folderPathName
	 * @return : HashMap<String,Object>
	 * @throws :
	 *             Exception
	 */
	public HashMap<String, Object> mkDirTsa(String folderPathName);

	/**
	 * @Method Name : insertSptCustomerServiceTermsFileProfile
	 * @Method description : [일반회원서비스약관파일프로파일]정보를 등록한다.
	 * @param :
	 *            SptCustomerServiceTermsFileProfileVO
	 * @return : int
	 * @throws :
	 *             Exception
	 */
	public int insertSptCustomerServiceTermsFileProfile(SptCustomerServiceTermsFileProfileVO paramVO) throws Exception;

	/**
	 * @Method Name : updateSptCustomerServiceTermsProfile
	 * @Method description : [일반회원서비스약관프로파일]정보를 수정한다.
	 * @param :
	 *            SptCustomerServiceTermsProfileVO
	 * @return : int
	 * @throws :
	 *             Exception
	 */
	public int updateSptCustomerServiceTermsProfile(SptCustomerServiceTermsProfileVO paramVO) throws Exception;

	/**
	 * @Method Name : deleteAccountSvcInfo
	 * @Method description : 가상계좌 서비스 연동 삭제
	 * @param :
	 *            SvcApplVO
	 * @return : int
	 * @throws :
	 *             Exception
	 */
	public int deleteAccountSvcInfo(SptSvcApplVO sptSvcApplVO) throws Exception;


	/**
	 * @Method Name : selectCntSptCustomerServiceAccountProfile
	 * @Method description : 계좌 삭제 가능 여부
	 * @param : SvcApplVO
	 * @return : int
	 * @throws : Exception
	 */
	public int selectCntSptCustomerServiceAccountProfile(SptSvcApplVO sptSvcApplVO) throws Exception;

    /**
     * @Method Name        : sptUserVerifyDupChk
     * @Method description : 인증값 중복 체크
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @Transactional
    public int sptUserVerifyDupChk(NewMbrRegVO paramVO) throws Exception;
}
