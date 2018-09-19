package kr.co.koscom.oppf.cmm.tsa.service.impl;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.cmm.tsa.service.CmmTsaVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceTermsFileProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceTermsProfileVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmTsaDAO.java
* @Comment  : [공통회원동의서TSA연계]정보관리를 위한 DAO 클래스
* @author   : 포털 이환덕
* @since    : 2016.06.09
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.09  이환덕        최초생성
*
*/
@Repository("CmmTsaDAO")
public class CmmTsaDAO extends ComAbstractDAO{
    
   /**
     * @Method Name        : selectSptCustomerServiceTermsFileProfileList
     * @Method description : [일반회원서비스약관파일프로파일]정보를 조회한다.
     * @param              : SptCustomerServiceTermsFileProfileVO
     * @return             : List<SptCustomerServiceTermsFileProfileVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptCustomerServiceTermsFileProfileVO> selectSptCustomerServiceTermsFileProfileList(SptCustomerServiceTermsFileProfileVO paramVO) throws Exception{
        return (List<SptCustomerServiceTermsFileProfileVO>) list("cmm.tsa.CmmTsaDAO.selectSptCustomerServiceTermsFileProfileList",paramVO);
    }
    
    /**
     * @Method Name        : selectSptCustomerServiceTermsFileProfileList
     * @Method description : [일반회원서비스약관파일프로파일]최신정보를 조회한다.
     * @param              : SptCustomerServiceTermsFileProfileVO
     * @return             : SptCustomerServiceTermsFileProfileVO
     * @throws             : Exception
     */
    public SptCustomerServiceTermsFileProfileVO selectSptCustomerServiceTermsFileProfile(SptCustomerServiceTermsFileProfileVO paramVO) throws Exception{
        return (SptCustomerServiceTermsFileProfileVO) select("cmm.tsa.CmmTsaDAO.selectSptCustomerServiceTermsFileProfile",paramVO);
    }
    
    
    /**
     * @Method Name        : selectTermsFileRegNo
     * @Method description : [일반회원서비스약관파일프로파일]정보를 조회한다.
     * @param              : SptCustomerServiceTermsFileProfileVO
     * @return             : List<SptCustomerServiceTermsFileProfileVO>
     * @throws             : Exception
     */
    public String selectTermsFileRegNo(CmmTsaVO paramVO) throws Exception{
        return (String) select("cmm.tsa.CmmTsaDAO.selectTermsFileRegNo",paramVO);
    }
    
   /**
     * @Method Name        : insertSptCustomerServiceTermsFileProfileHist
     * @Method description : [일반회원서비스약관파일프로파일Hist]정보를 등록한다.
     * @param              : SptCustomerServiceTermsFileProfileVO
     * @return             : int
     * @throws             : Exception
     */
     public int insertSptCustomerServiceTermsFileProfileHist(SptCustomerServiceTermsFileProfileVO paramVO) throws Exception{
         return (int) update("cmm.tsa.CmmTsaDAO.insertSptCustomerServiceTermsFileProfileHist",paramVO);
     }
     
    /**
      * @Method Name        : insertSptCustomerServiceTermsProfileHist
      * @Method description : [일반회원서비스약관프로파일Hist]정보를 등록한다.
      * @param              : SptCustomerServiceTermsProfileVO
      * @return             : int
      * @throws             : Exception
      */
     public int insertSptCustomerServiceTermsProfileHist(SptCustomerServiceTermsProfileVO paramVO) throws Exception{
         return (int) update("cmm.tsa.CmmTsaDAO.insertSptCustomerServiceTermsProfileHist",paramVO);
     }
     
     /**
      * @Method Name        : insertSptCustomerServiceTermsFileProfile
      * @Method description : [일반회원서비스약관파일프로파일]정보를 등록한다.
      * @param              : SptCustomerServiceTermsFileProfileVO
      * @return             : String termsFileRegNo(동의서파일등록번호)
      * @throws             : Exception
      */
     public int insertSptCustomerServiceTermsFileProfile(SptCustomerServiceTermsFileProfileVO paramVO) throws Exception{
    	 return (int) update("cmm.tsa.CmmTsaDAO.insertSptCustomerServiceTermsFileProfile",paramVO);
     }
     
    /**
      * @Method Name        : updateSptCustomerServiceTermsFileProfile
      * @Method description : [일반회원서비스약관파일프로파일]정보를 수정한다.
      * @param              : SptCustomerServiceTermsFileProfileVO
      * @return             : int
      * @throws             : Exception
      */
      public int updateSptCustomerServiceTermsFileProfile(SptCustomerServiceTermsFileProfileVO paramVO) throws Exception{
          return (int) update("cmm.tsa.CmmTsaDAO.updateSptCustomerServiceTermsFileProfile",paramVO);
      }
      
     /**
       * @Method Name        : updateSptCustomerServiceTermsProfile
       * @Method description : [일반회원서비스약관프로파일]정보를 수정한다.
       * @param              : SptCustomerServiceTermsProfileVO
       * @return             : int
       * @throws             : Exception
       */
      public int updateSptCustomerServiceTermsProfile(SptCustomerServiceTermsProfileVO paramVO) throws Exception{
          return (int) update("cmm.tsa.CmmTsaDAO.updateSptCustomerServiceTermsProfile",paramVO);
      }

}
