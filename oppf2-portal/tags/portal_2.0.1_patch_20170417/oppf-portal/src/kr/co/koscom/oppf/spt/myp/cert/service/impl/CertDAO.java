package kr.co.koscom.oppf.spt.myp.cert.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : MbrRegDAO.java
* @Comment  : [마이페이지:공인인증서]정보관리를 위한 DAO 클래스
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
@Repository("CertDAO")
public class CertDAO extends ComAbstractDAO{
    
    private static final Logger log = Logger.getLogger(CertDAO.class);
    
//    /**
//     * @Method Name        : updateSptCustomerVerifyProfile
//     * @Method description : [마이페이지:공인인증서:인증정보]수정을 한다.
//     * @param              : MbrRegVO
//     * @return             : int
//     * @throws             : Exception
//     */
//    public int updateSptCustomerVerifyProfile(MbrRegVO mbrRegVO) throws Exception{
//        int rs = update("cert.CertDAO.updateSptCustomerVerifyProfile", mbrRegVO);
//        return rs;
//    }
//    
//    /**
//     * @Method Name        : insertSptCustomerVerifyProfileHist
//     * @Method description : [마이페이지:공인인증서:인증hist정보]등록을 한다.
//     * @param              : MbrRegVO
//     * @return             : int
//     * @throws             : Exception
//     */
//    public int insertSptCustomerVerifyProfileHist(MbrRegVO mbrRegVO) throws Exception{
//        int rs = update("cert.CertDAO.insertSptCustomerVerifyProfileHist", mbrRegVO);
//        return rs;
//    }
    
    
}
