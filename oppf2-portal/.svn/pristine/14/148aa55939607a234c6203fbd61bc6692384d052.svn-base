package kr.co.koscom.oppf.apt.myp.aptMyInfo.service.impl;

import kr.co.koscom.oppf.apt.myp.aptMyInfo.service.AptMyInfoVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptMyInfoDAO.java
* @Comment  : [개인회원정보]정보관리를 위한 DAO 클래스
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
@Repository("AptMyInfoDAO")
public class AptMyInfoDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectCheckPw
     * @Method description : [개인회원정보:ID중복확인]ID정보를 조회한다.
     * @param              : String
     * @return             : String
     * @throws             : Exception
     */
    public String selectCheckPw(AptMyInfoVO aptMyInfoVO) throws Exception{
        String rs = (String) select("apt.AptMyInfoDAO.selectCheckPw",aptMyInfoVO);
        return rs;
    }
    
    /**
     * @Method Name        : updateAptMyPwMod
     * @Method description : [개인회원정보:비밀번호변경]을 한다.
     * @param              : AptMyInfoVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateAptMyPwMod(AptMyInfoVO aptMyInfoVO) throws Exception{
        int rs = update("apt.AptMyInfoDAO.updateAptMyPwMod", aptMyInfoVO);
        return rs;
    }
    
    /**
     * @Method Name        : insertAptMyPwMod
     * @Method description : [개인회원정보:비밀번호변경]을 한다.(history update)
     * @param              : AptMyInfoVO
     * @return             : int
     * @throws             : Exception
     */
    public void insertAptMyPwMod(AptMyInfoVO aptMyInfoVO) throws Exception{
        insert("apt.AptMyInfoDAO.insertAptMyPwMod", aptMyInfoVO);         
    }
    
}
