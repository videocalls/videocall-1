package kr.co.koscom.oppf.cpt.myp.cptMyInfo.service;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptMyInfoService.java
* @Comment  : [기업회원정보]정보관리를 위한 Service 인터페이스
* @author   : 포털 유제량
* @since    : 2016.06.29
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.29  유제량        최초생성
*
*/
public interface CptMyInfoService{
    
    /**
     * @Method Name        : selectCptMyInfo
     * @Method description : [기업회원정보상세:상세]정보를 조회한다.
     * @param              : CptMyInfoVO
     * @return             : CptMyInfoVO
     * @throws             : Exception
     */
    public CptMyInfoVO selectCptMyInfo(CptMyInfoVO cptMyInfoVO) throws Exception;
    
    /**
     * @Method Name        : selectCheckPw
     * @Method description : [기업회원정보:ID중복확인]ID정보를 조회한다.
     * @param              : String
     * @return             : String
     * @throws             : Exception
     */
    public String selectCheckPw(CptMyInfoVO cptMyInfoVO) throws Exception;
    
    /**
     * @Method Name        : updateCptMyInfo
     * @Method description : [기업회원정보:수정]을 한다.
     * @param              : CptMyInfoVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateCptMyInfo(CptMyInfoVO cptMyInfoVO) throws Exception;
    
    /**
     * @Method Name        : updateCptMyPwMod
     * @Method description : [기업회원정보:비밀번호변경]을 한다.
     * @param              : CptMyInfoVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateCptMyPwMod(CptMyInfoVO cptMyInfoVO) throws Exception;
    
    /**
     * @Method Name        : selectCptOperatorInfoProfile
     * @Method description : [기업회원정보:기본]정보를 조회한다.
     * @param              : CptMyInfoVO
     * @return             : CptMyInfoVO
     * @throws             : Exception
     */
    public CptMyInfoVO selectCptOperatorInfoProfile(CptMyInfoVO paramVO) throws Exception;
    
}
