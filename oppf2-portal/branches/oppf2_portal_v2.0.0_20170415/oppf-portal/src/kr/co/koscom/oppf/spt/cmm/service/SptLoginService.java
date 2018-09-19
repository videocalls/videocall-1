package kr.co.koscom.oppf.spt.cmm.service;

/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : SptLoginService.java
* @Comment  : 일반사용자 Login service
* @author   : 신동진
* @since    : 2016.04.28
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.04.28  신동진        최초생성
*
*/
public interface SptLoginService{
    
    /**
     * @Method Name        : selectLoginProfile
     * @Method description : 로그인 정보를 조회한다.
     * @param              : SptLoginVO
     * @return             : SptLoginVO
     * @throws             : Exception
     */
    public SptLoginVO selectLoginProfile(SptLoginVO sptLoginVO) throws Exception;
    
    /**
     * @Method Name        : updateLoginFailCnt
     * @Method description : 로그인 실패 카운트를 update한다.
     * @param              : SptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateLoginFailCnt(SptLoginVO sptLoginVO) throws Exception;

    /**
     * @Method Name        : insertLoginHist
     * @Method description : 로그인 hist를 insert한다.
     * @param              : SptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertLoginHist(SptLoginVO sptLoginVO) throws Exception;
    
    /**
     * @Method Name        : selectIdFind
     * @Method description : 아이디 찾기
     * @param              : SptLoginVO
     * @return             : SptLoginVO
     * @throws             : Exception
     */
    public SptLoginVO selectIdFind(SptLoginVO sptLoginVO) throws Exception;
    
    /**
     * @Method Name        : selectPwFind
     * @Method description : 비밀번호 찾기
     * @param              : SptLoginVO
     * @return             : SptLoginVO
     * @throws             : Exception
     */
    public SptLoginVO selectPwFind(SptLoginVO sptLoginVO) throws Exception;
    
    /**
     * @Method Name        : updateTmpPassword
     * @Method description : 임시비밀번호 발급
     * @param              : SptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateTmpPassword(SptLoginVO sptLoginVO) throws Exception;
    
    /**
     * @Method Name        : updateLoginFailCntHalt
     * @Method description : 로그인 실패 카운트를 update하고 회원상태를 활성에서 정지상태로 update합니다.
     * @param              : SptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateLoginFailCntHalt(SptLoginVO sptLoginVO) throws Exception;

}
