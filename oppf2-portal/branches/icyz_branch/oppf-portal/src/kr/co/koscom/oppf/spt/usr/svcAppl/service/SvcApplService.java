package kr.co.koscom.oppf.spt.usr.svcAppl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.usr.mbrReg.service.NewMbrRegVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SvcApplService.java
* @Comment  : [서비스신청]정보관리를 위한 Service 인터페이스
* @author   : 포털 이환덕
* @since    : 2016.05.16
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.16  이환덕        최초생성
*
*/
public interface SvcApplService{
    
   
   /**
     * @Method Name        : selectSptCustAccList
     * @Method description : [일반회원가상계좌+기업코드]목록정보를 조회한다.
     * @param              : SvcApplVO
     * @return             : List<SvcApplVO>
     * @throws             : Exception
     */
    public List<SvcApplVO> selectSptCustAccList(SvcApplVO svcApplVO) throws Exception;

    /**
     * @Method Name        : selectSvcCompanyTermsConsntList
     * @Method description : 금투사 기업 약관 정보 조회
     * @param              : SvcApplVO
     * @return             : List<SptCustomerCompanyTermsProfileVO>
     * @throws             : Exception
     */
    public SptCustomerCompanyTermsProfileVO selectSvcMemberCompanyTermsConsntList(SvcApplVO svcApplVO) throws Exception;
    
   /**
     * @Method Name        : selectSptCustAccInfo
     * @Method description : [일반회원가상계좌+기업코드]정보를 조회한다.
     * @param              : SvcApplVO
     * @return             : SvcApplVO
     * @throws             : Exception
     */
    public SvcApplVO selectSptCustAccInfo(SvcApplVO svcApplVO) throws Exception;
    
   /**
     * @Method Name        : selectComCompanyCodeList
     * @Method description : [기업코드목록]정보를 조회한다.
     * @param              : ComCompanyCodeVO
     * @return             : List<ComCompanyCodeVO>
     * @throws             : Exception
     */
    public List<ComCompanyCodeVO> selectComCompanyCodeList(ComCompanyCodeVO comCompanyCodeVO) throws Exception;
    
   /**
     * @Method Name        : selectComCompanyProfileList
     * @Method description : [기업프로파일목록]정보를 조회한다.
     * @param              : ComCompanyProfileVO
     * @return             : List<ComCompanyProfileVO>
     * @throws             : Exception
     */
     public List<ComCompanyProfileVO> selectComCompanyProfileList(ComCompanyProfileVO paramVO) throws Exception;
     
    /**
      * @Method Name        : selectComCompanyProfileInfo
      * @Method description : [기업프로파일]정보를 조회한다.
      * @param              : ComCompanyProfileVO
      * @return             : ComCompanyProfileVO
      * @throws             : Exception
      */
     public ComCompanyProfileVO selectComCompanyProfileInfo(ComCompanyProfileVO paramVO) throws Exception;
    
   /**
     * @Method Name        : selectComCompanyCodeInfo
     * @Method description : [기업코드]정보를 조회한다.
     * @param              : ComCompanyCodeVO
     * @return             : ComCompanyCodeVO
     * @throws             : Exception
     */
    public ComCompanyCodeVO selectComCompanyCodeInfo(ComCompanyCodeVO comCompanyCodeVO) throws Exception;
    
   /**
     * @Method Name        : selectsptCustomerAccountProfileList
     * @Method description : [일반회원가상계좌목록]정보를 조회한다.
     * @param              : SptCustomerAccountProfileVO
     * @return             : List<SptCustomerAccountProfileVO>
     * @throws             : Exception
     */
    public List<SptCustomerAccountProfileVO> selectSptCustomerAccountProfileList(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception;
    
   /**
     * @Method Name        : insertSptCustomerAccountProfile
     * @Method description : [일반회원가상계좌]정보를 등록한다. 사용 X
     * @param              : SptCustomerAccountProfileVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int insertSptCustomerAccountProfile(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception;
    
   /**
     * @Method Name        : updateSptCustomerAccountProfile
     * @Method description : [일반회원가상계좌]정보를 수정한다. -> 사용 X
     * @param              : SptCustomerAccountProfileVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateSptCustomerAccountProfile(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception;
    
   /**
     * @Method Name        : deleteSptCustomerAccountProfile
     * @Method description : [일반회원가상계좌]정보를 삭제한다. 사용 X
     * @param              : SptCustomerAccountProfileVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int deleteSptCustomerAccountProfile(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception;
        
    /**
     * @Method Name        : selectFintechSvcCompanyList
     * @Method description : [핀테크서비스 선택] 사용자 기준 금투사 목록을 가져온다.
     * @param              : SvcApplVO
     * @return             : List<SvcApplVO>
     * @throws             : Exception
     */
    public List<SvcApplVO> selectFintechSvcCompanyList(SvcApplVO svcApplVO) throws Exception;
    
    /**
     * @Method Name        : selectFintechSvcList
     * @Method description : [핀테크서비스 선택] 핀테크 서비스 정보 목록을 가져온다.
     * @param              : SvcApplVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectFintechSvcList(SvcApplVO svcApplVO) throws Exception;
    
    /**
     * @Method Name        : insertFintechSvc
     * @Method description : [핀테크서비스 선택] 핀테크 서비스 선택 정보를 등록한다.
     * @param              : SvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int insertFintechSvc(SvcApplVO svcApplVO) throws Exception;
    
    /**
     * @Method Name        : selectFintechSvcTermsList
     * @Method description : [핀테크서비스선택] 사용자의 약관정보를 가져온다. 
     * @param              : SvcApplVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectFintechSvcTermsList(SvcApplVO svcApplVO) throws Exception;
    
    /**
     * @Method Name        : saveFintechSvcTerms
     * @Method description : [핀테크서비스선택] 가상계좌 선택 정보를 저장한다.
     * @param              : SvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int saveFintechSvcTerms(SvcApplVO svcApplVO) throws Exception;
    
    /**
     * @Method Name        : selectSvcTermsConsntList
     * @Method description : [약관동의] 약관동의 목록을 조회한다.
     * @param              : SvcApplVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSvcCompanyTermsConsntList(SvcApplVO svcApplVO) throws Exception;

    /**
     * @Method Name        : saveSvcCompanyTermsConsnt
     * @Method description : [약관동의] 기업약관을 저장한다.
     * @param              : SvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int saveSvcCompanyTermsConsnt(SvcApplVO svcApplVO) throws Exception;

    /**
     * @Method Name        : saveMemberCompanyTermsConsnt
     * @Method description : [약관동의] 기업약관을 저장한다.
     * @param              : SvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int saveMemberCompanyTermsConsnt(SptCustomerCompanyTermsProfileVO sptCustomerCompanyTermsProfileVO) throws Exception;
    /**
     * @Method Name        : selectSvcTermConsntList
     * @Method description : [정보제공동의] 정보제공동의 목록을 조회한다.
     * @param              : SvcApplVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSvcTermConsntList(SvcApplVO svcApplVO) throws Exception;
    
    /**
     * @Method Name        : selectSptCustomerServiceTermsProfile
     * @Method description : [일반회원서비스약관프로파일]정보를 조회한다.
     * @param              : SptCustomerServiceTermsProfileVO
     * @return             : SptCustomerServiceTermsProfileVO
     * @throws             : Exception
     */
    public SptCustomerServiceTermsProfileVO selectSptCustomerServiceTermsProfile(SptCustomerServiceTermsProfileVO paramVO) throws Exception;
    
    /**
     * @Method Name        : selectSptCustomerServiceTermsPubcompanyProfileList
     * @Method description : [일반회원서비스약관금투사프로파일목록]정보를 조회한다.
     * @param              : SptCustomerServiceTermsPubcompanyProfileVO
     * @return             : List<SptCustomerServiceTermsPubcompanyProfileVO>
     * @throws             : Exception
     */
    public List<SptCustomerServiceTermsPubcompanyProfileVO> selectSptCustomerServiceTermsPubcompanyProfileList(SptCustomerServiceTermsPubcompanyProfileVO paramVO) throws Exception;
    
    /**
     * @Method Name        : selectSvcApplCompltList
     * @Method description : [서비스신청완료] 서비스신청완료 목록을 조회한다.
     * @param              : SvcApplVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSvcApplCompltList(SvcApplVO svcApplVO) throws Exception;
    
    /**
     * @Method Name        : selectMainSvcAppList
     * @Method description : [메인] 메인의 핀테크 서비스 목록(TOP 5)을 조회한다.
     * @param              : SvcApplVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainSvcAppList(SvcApplVO svcApplVO) throws Exception;

    /**
     * @Method Name        : selectFintechSvcList
     * @Method description : [핀테크서비스 선택] 핀테크 서비스 정보 목록을 가져온다.
     * @param              : SvcApplVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
     public Map<String, Object> selectFintechSvcListDev(SvcApplVO svcApplVO, NewMbrRegVO paramVO) throws Exception;

    /**
     * @Method Name        : selectSvcAppInfo
     * @Method description : [Oauth] 앱 정보.
     * @param              : SvcApplVO
     * @return             : SvcApplVO
     * @throws             : Exception
     */
    public SvcApplVO selectSvcAppInfo(SvcApplVO svcApplVO) throws Exception;
    
    /**
     * @Method Name        : insertFintechSvcDev
     * @Method description : [핀테크서비스 선택] 핀테크 서비스 선택 정보를 등록한다.
     * @param              : SvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertFintechSvcDev(SvcApplVO svcApplVO) throws Exception;

   /**
    * @Method Name        : selectFintechSvc
    * @Method description : [핀테크서비스 선택] 핀테크 서비스 선택 여부
    * @param              : SvcApplVO
    * @return             : SptCustomerServiceProfileVO
    * @throws             : Exception
    */
   public SptCustomerServiceProfileVO selectFintechSvc(SvcApplVO svcApplVO) throws Exception;

  /**
   * @Method Name        : insertSelectFintechSvc
   * @Method description : [핀테크서비스 선택] 선택하고 들어온 핀테크 서비스 선택 정보를 등록한다.
   * @param              : SvcApplVO
   * @return             : void
   * @throws             : Exception
   */
  public void insertSelectFintechSvc(SvcApplVO svcApplVO) throws Exception;

    /**
     * @Method Name        : selectFintechSvcIntAcntAccountList
     * @Method description : [핀테크서비스 선택] 통합계좌 가상계좌 목록
     * @param              : SvcApplVO
     * @return             : List<SptCustomerServiceProfileVO>
     * @throws             : Exception
     */
     public List<SptCustomerServiceAccountProfileVO> selectFintechSvcIntAcntAccountList(SvcApplVO svcApplVO) throws Exception;

}
