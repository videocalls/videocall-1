package kr.co.koscom.oppf.apt.myp.aptMyInfo.service;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptMyInfoService.java
* @Comment  : [개인회원정보]정보관리를 위한 Service 인터페이스
* @author   : 포털 유제량
* @since    : 2016.06.28
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.28  유제량        최초생성
*
*/
public interface AptMyInfoService{
    
    /**
     * @Method Name        : selectCheckPw
     * @Method description : [개인회원정보:ID중복확인]ID정보를 조회한다.
     * @param              : String
     * @return             : String
     * @throws             : Exception
     */
    public String selectCheckPw(AptMyInfoVO aptMyInfoVO) throws Exception;
    
    /**
     * @Method Name        : updateAptMyPwMod
     * @Method description : [개인회원정보:비밀번호변경]을 한다.
     * @param              : AptMyInfoVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateAptMyPwMod(AptMyInfoVO aptMyInfoVO) throws Exception;
    
}
