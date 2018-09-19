package kr.co.koscom.oppf.cmm.ars.service;

import java.util.HashMap;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축 2차
* @FileName : CmmArsService.java
* @Comment  : [공통회원동의서ARS연계]정보관리를 위한 Service 인터페이스
* @author   : 포털 이환덕
* @since    : 2017.03.04
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2017.03.04  이희태        최초생성
*
*/
public interface CmmArsService {
    
   /**
     * @Method Name        : procArs
     * @Method description : ARS처리
     * @param              : CmmArsVO
     * @return             : HashMap<String,Object>
     * @throws             : Exception
     */
   public HashMap<String,Object> procArs(CmmArsVO cmmArsVO)throws Exception;

   /**
    * @Method Name        : selectTermsArsRecordData
    * @Method description : ARS녹취 데이터 조회
    * @param              : String
    * @return             : String
    * @throws             : Exception
    */
   public String selectTermsArsRecordData(String  termsRegNo) throws Exception;

}
