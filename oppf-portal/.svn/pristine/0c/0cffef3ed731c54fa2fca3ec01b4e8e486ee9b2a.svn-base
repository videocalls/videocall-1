package kr.co.koscom.oppf.cmm.phone.service.impl;

import kr.co.koscom.oppf.cmm.phone.service.CmmPhoneNumberCheckVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmPhoneNumberCheckDAO.java
* @Comment  : [PhoneCheck]정보관리를 위한 DAO 클래스
* @author   : 포털 
* @since    : 2018.08.29
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
*
*/
@Repository("CmmPhoneNumberCheckDAO")
public class CmmPhoneNumberCheckDAO extends ComAbstractDAO{
    
   /**
     * @Method Name        : selectCmmPhoneNumberCheckInfo
     * @Method description : [PhoneCheck]정보를 조회한다.
     * @param              : CmmPhoneNumberCheckVO
     * @return             : CmmPhoneNumberCheckVO
     * @throws             : Exception
     */
    public CmmPhoneNumberCheckVO selectCmmPhoneNumberCheck(CmmPhoneNumberCheckVO paramVO) throws Exception{
        CmmPhoneNumberCheckVO rsCmmPhoneNumberCheckVO = (CmmPhoneNumberCheckVO) select("cmm.phone.CmmPhoneNumberCheckDAO.selectCmmPhoneNumberCheck",paramVO);
        return rsCmmPhoneNumberCheckVO;
    }
   
  /**
    * @Method Name        : insertCmmPhoneNumberCheck
    * @Method description : [PhoneCheck]정보를 등록한다.
    * @param              : CmmPhoneNumberCheckVO
    * @return             : String reg_no 등록번호
    * @throws             : Exception
    */
    public String insertCmmPhoneNumberCheck(CmmPhoneNumberCheckVO paramVO) throws Exception{
        return (String) insert("cmm.phone.CmmPhoneNumberCheckDAO.insertCmmPhoneNumberCheck",paramVO);
    }
    
  /**
    * @Method Name        : updateCmmPhoneNumberCheck
    * @Method description : [PhoneCheck]정보를 수정한다.
    * @param              : CmmPhoneNumberCheckVO
    * @return             : int
    * @throws             : Exception
    */
    public int updateCmmPhoneNumberCheck(CmmPhoneNumberCheckVO paramVO) throws Exception{
        return (int) update("cmm.phone.CmmPhoneNumberCheckDAO.updateCmmPhoneNumberCheck",paramVO);
    }
}
