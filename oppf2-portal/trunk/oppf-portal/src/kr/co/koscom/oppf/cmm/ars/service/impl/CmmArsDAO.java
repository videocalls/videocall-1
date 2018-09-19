package kr.co.koscom.oppf.cmm.ars.service.impl;

import kr.co.koscom.oppf.cmm.ars.service.CmmArsVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceTermsProfileVO;
import org.springframework.stereotype.Repository;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축 2차
* @FileName : CmmArsDAO.java
* @Comment  : [공통회원동의서ARS연계]정보관리를 위한 DAO 클래스
* @author   : 포털 이희태
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
@Repository("CmmArsDAO")
public class CmmArsDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectTermsArsRegNo
     * @Method description : [일반회원서비스약관ARS프로파일]정보를 조회한다.
     * @param              : CmmArsVO
     * @return             : String
     * @throws             : Exception
     */
    public String selectTermsArsRegNo(CmmArsVO paramVO) throws Exception{
        return (String) select("cmm.ars.CmmArsDAO.selectTermsArsRegNo",paramVO);
    }
    
   /**
     * @Method Name        : insertSptCustomerServiceArsProfileHist
     * @Method description : [일반회원서비스약관파일프로파일Hist]정보를 등록한다.
     * @param              : CmmArsVO
     * @return             : int
     * @throws             : Exception
     */
     public int insertSptCustomerServiceArsProfileHist(CmmArsVO paramVO) throws Exception{
         return (int) update("cmm.ars.CmmArsDAO.insertSptCustomerServiceArsProfileHist",paramVO);
     }
     
    /**
      * @Method Name        : insertSptCustomerServiceTermsProfileHist
      * @Method description : [일반회원서비스약관프로파일Hist]정보를 등록한다.
      * @param              : SptCustomerServiceTermsProfileVO
      * @return             : int
      * @throws             : Exception
      */
     public int insertSptCustomerServiceTermsProfileHist(SptCustomerServiceTermsProfileVO paramVO) throws Exception{
         return (int) update("cmm.ars.CmmArsDAO.insertSptCustomerServiceTermsProfileHist",paramVO);
     }
     
     /**
      * @Method Name        : insertSptCustomerServiceArsProfile
      * @Method description : [일반회원서비스약관파일프로파일]정보를 등록한다.
      * @param              : CmmArsVO
      * @return             : String termsArsRegNo(동의서파일등록번호)
      * @throws             : Exception
      */
     public int insertSptCustomerServiceArsProfile(CmmArsVO paramVO) throws Exception{
    	 return (int) update("cmm.ars.CmmArsDAO.insertSptCustomerServiceArsProfile",paramVO);
     }
     
     /**
       * @Method Name        : updateSptCustomerServiceTermsProfile
       * @Method description : [일반회원서비스약관프로파일]정보를 수정한다.
       * @param              : SptCustomerServiceTermsProfileVO
       * @return             : int
       * @throws             : Exception
       */
      public int updateSptCustomerServiceTermsProfile(SptCustomerServiceTermsProfileVO paramVO) throws Exception{
          return (int) update("cmm.ars.CmmArsDAO.updateSptCustomerServiceTermsProfile",paramVO);
      }

    /**
     * @Method Name        : selectTermsArsRegNo
     * @Method description : [일반회원서비스약관ARS프로파일]ARS 녹취 데이더 조회
     * @param              : String
     * @return             : String
     * @throws             : Exception
     */
    public String selectTermsArsRecordData(String  termsRegNo) throws Exception{
        return (String) select("cmm.ars.CmmArsDAO.selectTermsArsRecordData",termsRegNo);
    }
}
