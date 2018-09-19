package kr.co.koscom.oppf.cmm.phone.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.cmm.phone.service.CmmPhoneNumberCheckVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmPhoneNumberService.java
* @Comment  : [PhoneCheck]정보관리를 위한 Service 인터페이스
* @author   : 포털 이환덕
* @since    : 2016.05.16
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.16  이환덕        최초생성
*
*/
public interface CmmPhoneNumberCheckService{
    
   
   /**
     * @Method Name        : selectCmmPhoneNumberInfo
     * @Method description : [PhoneCheck]정보를 조회한다.
     * @param              : CmmPhoneNumberVO
     * @return             : CmmPhoneNumberVO
     * @throws             : Exception
     */
    public CmmPhoneNumberCheckVO selectCmmPhoneNumberCheck(CmmPhoneNumberCheckVO paramVO) throws Exception;


   /**
     * @Method Name        : insertCmmPhoneNumber
     * @Method description : [PhoneCheck]정보를 등록한다.
     * @param              : CmmPhoneNumberVO
     * @return             : String regNo 등록번호
     * @throws             : Exception
     */
    @Transactional
    public String insertCmmPhoneNumberCheck(CmmPhoneNumberCheckVO paramVO) throws Exception;
 
    /**
     * @Method Name        : updateCmmPhoneNumber
     * @Method description : [PhoneCheck]정보를 수정한다.
     * @param              : CmmPhoneNumberVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateCmmPhoneNumberCheck(CmmPhoneNumberCheckVO paramVO) throws Exception;
 
}
