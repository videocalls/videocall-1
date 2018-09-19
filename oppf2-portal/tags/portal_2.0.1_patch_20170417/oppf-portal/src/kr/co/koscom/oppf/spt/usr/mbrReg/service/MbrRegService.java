package kr.co.koscom.oppf.spt.usr.mbrReg.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.AccListAttributeREQVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : MbrRegService.java
* @Comment  : [회원가입]정보관리를 위한 Service 인터페이스
* @author   : 포털 이환덕
* @since    : 2016.05.02
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.02  이환덕        최초생성
*
*/
public interface MbrRegService{
    
    /**
     * @Method Name        : procMbrReg1
     * @Method description : [회원가입:본인인증]정보 처리를 한다.
     * @param              : MbrRegVO
     * @return             : MbrRegVO
     * @throws             : Exception
     */
    public MbrRegVO procMbrReg1(MbrRegVO paramVO) throws Exception;
    
    /**
     * @Method Name        : procMbrReg2
     * @Method description : [회원가입:공인인증서등록]정보 처리를 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int procMbrReg2(MbrRegVO paramVO) throws Exception;
    
    /**
     * @Method Name        : procMbrReg3
     * @Method description : [회원가입:약관동의]정보 처리를 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int procMbrReg3(MbrRegVO paramVO) throws Exception;
    
    /**
     * @Method Name        : procMbrReg4
     * @Method description : [회원가입:개인정보입력]정보 처리를 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int procMbrReg4(MbrRegVO paramVO) throws Exception;
        
    /**
     * @Method Name        : procMbrReg5
     * @Method description : [회원가입:이메일인증]정보 처리를 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int procMbrReg5(MbrRegVO paramVO) throws Exception;
    
    
    /**
     * @Method Name        : selectCheckId
     * @Method description : [회원가입:ID중복확인]ID정보를 조회한다.
     * @param              : String
     * @return             : String
     * @throws             : Exception
     */
    public String selectCheckId(MbrRegVO paramVO) throws Exception;
    
    /**
     * @Method Name        : selectSptCustomerInfoProfile
     * @Method description : [회원가입:기본]정보를 조회한다.
     * @param              : MbrRegVO
     * @return             : MbrRegVO
     * @throws             : Exception
     */
    public MbrRegVO selectSptCustomerInfoProfile(MbrRegVO paramVO) throws Exception;
    
    /**
     * @Method Name        : selectSptCustomerVerifyProfile
     * @Method description : [회원가입:인증]정보를 조회한다.
     * @param              : MbrRegVO
     * @return             : MbrRegVO
     * @throws             : Exception
     */
    public MbrRegVO selectSptCustomerVerifyProfile(MbrRegVO paramVO) throws Exception;
    
    /**
     * @Method Name        : selectSptCustomerVerifyProfileList
     * @Method description : [회원가입:인증목록]정보를 조회한다.
     * @param              : MbrRegVO
     * @return             : List<MbrRegVO>
     * @throws             : Exception
     */
    public List<MbrRegVO> selectSptCustomerVerifyProfileList(MbrRegVO paramVO) throws Exception;
    
    /**
     * @Method Name        : selectSptCustomerProfileInfo
     * @Method description : [회원가입:기본&인증]정보를 조회한다.
     * @param              : MbrRegVO
     * @return             : MbrRegVO
     * @throws             : Exception
     */
    public MbrRegVO selectSptCustomerProfileInfo(MbrRegVO paramVO) throws Exception;
    
    /**
     * @Method Name        : selectSptCustomerVerifyProfile
     * @Method description : [회원가입:약관]정보를 조회한다.
     * @param              : MbrRegTermsVO
     * @return             : MbrRegTermsVO
     * @throws             : Exception
     */
    public MbrRegVO selectSptCustomerTermsProfile(MbrRegTermsVO paramVO) throws Exception;

    /**
     * @Method Name        : selectSptCustomerTermsContentInfo
     * @Method description : [회원가입:약관내용]정보를 조회한다.
     * @param              : MbrRegTermsContentVO
     * @return             : MbrRegTermsContentVO
     * @throws Exception 
     */
    public MbrRegTermsContentVO selectSptCustomerTermsContentInfo(MbrRegTermsContentVO paramVO) throws Exception;
    
    /**
     * @Method Name        : selectSptCustomerTermsContentList
     * @Method description : [회원가입:약관내용목록]정보를 조회한다.
     * @param              : MbrRegTermsContentVO
     * @return             : List<MbrRegTermsContentVO>
     * @throws             : Exception
     */
    public List<MbrRegTermsContentVO> selectSptCustomerTermsContentList(MbrRegTermsContentVO paramVO) throws Exception;
    
    
    /**
     * @Method Name        : insertSptCustomerInfoProfile
     * @Method description : [회원가입:기본]정보등록을 한다.
     * @param              : MbrRegVO
     * @return             : String customerRegNo(회원OP등록번호)
     * @throws             : Exception
     */
    public String insertSptCustomerInfoProfile(MbrRegVO paramVO) throws Exception;
    
    /**
     * @Method Name        : insertSptCustomerInfoProfileHist
     * @Method description : [회원가입:기본정보history]등록을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerInfoProfileHist(MbrRegVO paramVO) throws Exception;
    
    /**
     * @Method Name        : insertSptCustomerVerifyProfile
     * @Method description : [회원가입:인증]정보등록을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerVerifyProfile(MbrRegVO paramVO) throws Exception;
    
    /**
     * @Method Name        : insertSptCustomerTermsProfile
     * @Method description : [회원가입:약관]정보등록을 한다.
     * @param              : paramVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerTermsProfile(MbrRegVO paramVO) throws Exception;
    
    
    /**
     * @Method Name        : updatesptCustomerInfoProfile
     * @Method description : [회원가입:기본]정보수정을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptCustomerInfoProfile(MbrRegVO paramVO) throws Exception;
    
    /**
     * @Method Name        : updatesptCustomerInfoProfile
     * @Method description : [회원가입:인증]정보수정을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptCustomerVerifyProfile(MbrRegVO paramVO) throws Exception;
    
    /**
     * @Method Name        : updateSptCustomerTermsProfile
     * @Method description : [회원가입:약관]정보수정을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptCustomerTermsProfile(MbrRegVO paramVO) throws Exception;

    
    /**
     * @Method Name        : selectMainSptCustomerTermsList
     * @Method description : [메인]메인 접속 시 해당사용자의 약관동의 정보를 조회한다.
     * @param              : MbrRegVO
     * @return             : List<MbrRegTermsContentVO>
     * @throws             : Exception
     */
    public List<MbrRegTermsContentVO> selectMainSptCustomerTermsList(MbrRegVO paramVO) throws Exception;
    
    /**
     * @Method Name        : saveCustomerTermsListPopup
     * @Method description : [메인-로그인시]변경약관 (재)동의 팝업 저장
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int saveCustomerTermsListPopup(MbrRegVO paramVO) throws Exception;

    /**
    * @Method Name        : selectSptCustomerProfileInfo
    * @Method description : [회원정보:기본&인증]정보를 조회한다.
    * @param              : MbrRegVO
    * @return             : MbrRegVO
    * @throws             : Exception
    */
    public MbrRegVO selectSptCustomerVerifyProfileInfo(MbrRegVO paramVO) throws Exception;

    /**
     * @Method Name        : selectSptCustomerCiInfo
     * @Method description : [회원정보:기본&인증]정보를 조회한다.
     * @param paramVO
     * @return
     */
	public MbrRegVO selectSptCustomerCiInfo(AccListAttributeREQVO paramVO) throws Exception;
    
}
