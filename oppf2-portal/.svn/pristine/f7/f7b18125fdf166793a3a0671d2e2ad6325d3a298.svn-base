package kr.co.koscom.oppf.spt.myp.sptMyInfo.service;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptMyInfoService.java
* @Comment  : [개인회원정보]정보관리를 위한 Service 인터페이스
* @author   : 포털 유제량
* @since    : 2016.05.09
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.09  유제량        최초생성
*
*/
public interface SptMyInfoService{
    
    /**
     * @Method Name        : selectSptMyInfo
     * @Method description : [개인회원정보상세:상세]정보를 조회한다.
     * @param              : SptMyInfoVO
     * @return             : SptMyInfoVO
     * @throws             : Exception
     */
    public SptMyInfoVO selectSptMyInfo(SptMyInfoVO sptMyInfoVO) throws Exception;
    
    /**
     * @Method Name        : selectCheckPw
     * @Method description : [개인회원정보:ID중복확인]ID정보를 조회한다.
     * @param              : String
     * @return             : String
     * @throws             : Exception
     */
    public String selectCheckPw(SptMyInfoVO sptMyInfoVO) throws Exception;
    
    /**
     * @Method Name        : updateSptMyInfo
     * @Method description : [개인회원정보:수정]을 한다.
     * @param              : SptMyInfoVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptMyInfo(SptMyInfoVO sptMyInfoVO) throws Exception;
    
    /**
     * @Method Name        : updateSptMyPwMod
     * @Method description : [개인회원정보:비밀번호변경]을 한다.
     * @param              : SptMyInfoVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptMyPwMod(SptMyInfoVO sptMyInfoVO) throws Exception;
    
    /**
     * @Method Name        : updateSptMbrSecesInfo
     * @Method description : [개인회원정보:회원탈퇴]를 한다.
     * @param              : SptMyInfoVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptMbrSecesInfo(SptMyInfoVO sptMyInfoVO) throws Exception;
    
    /**
     * @Method Name        : selectSptCustomerInfoProfile
     * @Method description : [개인회원정보:기본]정보를 조회한다.
     * @param              : SptMyInfoVO
     * @return             : SptMyInfoVO
     * @throws             : Exception
     */
    public SptMyInfoVO selectSptCustomerInfoProfile(SptMyInfoVO paramVO) throws Exception;
    
    /**
     * @Method Name        : selectSptCustomerProfile
     * @Method description : [개인회원정보:상세]정보를 조회한다.(탈퇴메일발송를 위한 정보를 조회해 온다.)
     * @param              : SptMyInfoVO
     * @return             : SptMyInfoVO
     * @throws             : Exception
     */
    public SptMyInfoVO selectSptCustomerProfile(SptMyInfoVO sptMyInfoVO) throws Exception;
    
}
