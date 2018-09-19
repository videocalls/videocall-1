package kr.co.koscom.oppf.cmm.phone.service.impl;

import kr.co.koscom.oppf.cmm.phone.service.CmmPhoneNumberCheckService;
import kr.co.koscom.oppf.cmm.phone.service.CmmPhoneNumberCheckVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;




/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmPhoneNumberCheckServiceImpl.java
* @Comment  : [PhoneCheck]정보관리를 위한 Service 클래스
* @author   : 포털 
* @since    : 
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------

*
*/
@Service("CmmPhoneNumberCheckService")
public class CmmPhoneNumberCheckServiceImpl implements CmmPhoneNumberCheckService{
    
    @Resource(name = "CmmPhoneNumberCheckDAO")
    private CmmPhoneNumberCheckDAO cmmPhoneNumberCheckDAO;
    
   /**
     * @Method Name        : selectCmmPhoneNumberCheckInfo
     * @Method description : [PhoneCheck]정보를 조회한다.
     * @param              : CmmPhoneNumberCheckVO
     * @return             : CmmPhoneNumberCheckVO
     * @throws             : Exception
     */
    @Override
    public CmmPhoneNumberCheckVO selectCmmPhoneNumberCheck(CmmPhoneNumberCheckVO paramVO) throws Exception{
        CmmPhoneNumberCheckVO rsCmmPhoneNumberCheckVO = (CmmPhoneNumberCheckVO) cmmPhoneNumberCheckDAO.selectCmmPhoneNumberCheck(paramVO);
        return rsCmmPhoneNumberCheckVO;
    }


  /**
    * @Method Name        : insertCmmPhoneNumberCheck
    * @Method description : [PhoneCheck]정보를 등록한다.
    * @param              : CmmPhoneNumberCheckVO
    * @return             : String regNo 등록번호
    * @throws             : Exception
    */
   @Override
   @Transactional
   public String  insertCmmPhoneNumberCheck(CmmPhoneNumberCheckVO paramVO) throws Exception{
       return (String)cmmPhoneNumberCheckDAO.insertCmmPhoneNumberCheck(paramVO);
   }
   
  /**
    * @Method Name        : updateCmmPhoneNumberCheck
    * @Method description : [PhoneCheck]정보를 수정한다.
    * @param              : CmmPhoneNumberCheckVO
    * @return             : int
    * @throws             : Exception
    */
   @Override
   @Transactional
   public int updateCmmPhoneNumberCheck(CmmPhoneNumberCheckVO paramVO) throws Exception{
       return cmmPhoneNumberCheckDAO.updateCmmPhoneNumberCheck(paramVO);
   }

}
