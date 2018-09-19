package kr.co.koscom.oppf.cmm.otp.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.cmm.otp.service.CmmOtpReqVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmOtpReqDAO.java
* @Comment  : OTP 정보를 관리하기  위한 DAO 클래스
* @author   : 신동진
* @since    : 2016.08.01
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.08.01  신동진        최초생성
*
*/
@Repository("CmmOtpReqDAO")
public class CmmOtpReqDAO extends ComAbstractDAO{
    
    private static final Logger log = Logger.getLogger(CmmOtpReqDAO.class);
     
     /**
      * @Method Name        : checkOtpProfile
      * @Method description : OTP정보가 있는지 확인한다.
      * @param              : CmmOtpReqVO
      * @return             : int
      * @throws             : Exception
      */
     public int checkOtpProfile(CmmOtpReqVO cmmOtpReqVO) throws Exception{
         return (Integer) select("cmm.CmmOtpReqDAO.checkOtpProfile", cmmOtpReqVO);
     }
     
     /**
      * @Method Name        : insertOtpProfile
      * @Method description : OTP정보를 등록 한다.
      * @param              : CmmOtpReqVO
      * @return             : int
      * @throws             : Exception
      */
     public int insertOtpProfile(CmmOtpReqVO cmmOtpReqVO) throws Exception{
         return update("cmm.CmmOtpReqDAO.insertOtpProfile", cmmOtpReqVO);
     }
     
     /**
      * @Method Name        : updateOtpProfile
      * @Method description : OTP정보를 수정 한다.
      * @param              : CmmOtpReqVO
      * @return             : int
      * @throws             : Exception
      */
     public int updateOtpProfile(CmmOtpReqVO cmmOtpReqVO) throws Exception{
         return update("cmm.CmmOtpReqDAO.updateOtpProfile", cmmOtpReqVO);
     }
     
     /**
      * @Method Name        : deleteOtpProfile
      * @Method description : OTP정보를 폐기 한다.
      * @param              : CmmOtpReqVO
      * @return             : int
      * @throws             : Exception
      */
     public int deleteOtpProfile(CmmOtpReqVO cmmOtpReqVO) throws Exception{
         return update("cmm.CmmOtpReqDAO.deleteOtpProfile", cmmOtpReqVO);
     }
     
     /**
      * @Method Name        : insertOtpProfileHist
      * @Method description : OTP정보의 hist를 등록한다.
      * @param              : CmmOtpReqVO
      * @return             : int
      * @throws             : Exception
      */
     public int insertOtpProfileHist(CmmOtpReqVO cmmOtpReqVO) throws Exception{
         return update("cmm.CmmOtpReqDAO.insertOtpProfileHist", cmmOtpReqVO);
     }
     
}
