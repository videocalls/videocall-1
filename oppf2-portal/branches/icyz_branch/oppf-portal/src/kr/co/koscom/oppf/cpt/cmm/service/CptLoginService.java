package kr.co.koscom.oppf.cpt.cmm.service;

/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CptLoginService.java
* @Comment  : 기업사용자 Login service
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
public interface CptLoginService{
    
    /**
     * @Method Name        : selectLoginProfile
     * @Method description : 로그인 정보를 조회한다.
     * @param              : CptLoginVO
     * @return             : CptLoginVO
     * @throws             : Exception
     */
    public CptLoginVO selectLoginProfile(CptLoginVO cptLoginVO) throws Exception;
    
    /**
     * @Method Name        : updateLoginFailCnt
     * @Method description : 로그인 실패 카운트를 update한다.
     * @param              : LoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateLoginFailCnt(CptLoginVO cptLoginVO) throws Exception;

    /**
     * @Method Name        : insertLoginHist
     * @Method description : 로그인 hist를 insert한다.
     * @param              : LoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertLoginHist(CptLoginVO cptLoginVO) throws Exception;
    
    /**
     * @Method Name        : selectIdFind
     * @Method description : 아이디 찾기
     * @param              : CptLoginVO
     * @return             : CptLoginVO
     * @throws             : Exception
     */
    public CptLoginVO selectIdFind(CptLoginVO cptLoginVO) throws Exception;
    
    /**
     * @Method Name        : selectPwFind
     * @Method description : 비밀번호 찾기
     * @param              : CptLoginVO
     * @return             : CptLoginVO
     * @throws             : Exception
     */
    public CptLoginVO selectPwFind(CptLoginVO cptLoginVO) throws Exception;
    
    /**
     * @Method Name        : updateTmpPassword
     * @Method description : 임시비밀번호 발급
     * @param              : CptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateTmpPassword(CptLoginVO cptLoginVO) throws Exception;
}
