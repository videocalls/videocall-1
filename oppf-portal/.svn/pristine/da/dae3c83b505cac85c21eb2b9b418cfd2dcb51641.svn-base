package kr.co.koscom.oppf.spt.myp.intAcnt.service.impl;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.spt.myp.intAcnt.service.SptCustomerAcntOauthTokenVO;
import org.springframework.stereotype.Repository;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축 2차
* @FileName : IntAcntOauthDAO.java
* @Comment  : [마이페이지:통합계좌]정보관리를 위한 DAO 클래스
* @author   : 포털 이희태
* @since    : 2017.02.15
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2017.02.15  이희태        최초생성
*
*/
@Repository("IntAcntOauthDAO")
public class IntAcntOauthDAO extends ComAbstractDAO{
    
    /**
    * @Method Name        : selectCustomerAcntOauthToken
    * @Method description : [일반회원통합계좌]Oauth정보를 조회한다.
    * @param              : SptCustomerAcntOauthTokenVO
    * @return             : SptCustomerAcntOauthTokenVO
    * @throws             : Exception
    */
    public SptCustomerAcntOauthTokenVO selectCustomerAcntOauthToken(SptCustomerAcntOauthTokenVO sptCustomerAcntOauthTokenVO) throws Exception{
        return (SptCustomerAcntOauthTokenVO) select("spt.myp.intAcnt.IntAcntOauthDAO.selectCustomerAcntOauthToken", sptCustomerAcntOauthTokenVO);
    }

    /**
     * @Method Name        : insertCustomerAcntOauthToken
     * @Method description : [일반회원통합계좌]정보를 등록한다.
     * @param              : SptCustomerAcntOauthTokenVO
     * @throws             : Exception
     */
    public String insertCustomerAcntOauthToken(SptCustomerAcntOauthTokenVO paramVO) throws Exception{
        return (String) insert("spt.myp.intAcnt.IntAcntOauthDAO.insertCustomerAcntOauthToken",paramVO);
    }

    /**
     * @Method Name        : deleteCustomerAcntOauthToken
     * @Method description : [일반회원통합계좌]정보를 삭제한다.
     * @param              : SptCustomerAcntOauthTokenVO
     * @throws             : Exception
     */
    public int deleteCustomerAcntOauthToken(SptCustomerAcntOauthTokenVO paramVO) throws Exception{
        return  update("spt.myp.intAcnt.IntAcntOauthDAO.deleteCustomerAcntOauthToken",paramVO);
    }

}
