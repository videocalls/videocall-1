package kr.co.koscom.oppf.spt.myp.asumAcnt.service.impl;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.spt.myp.asumAcnt.service.AsumAcntVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerAccountProfileVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SvcApplDAO.java
* @Comment  : [마이페이지:가상계좌]정보관리를 위한 DAO 클래스
* @author   : 포털 이환덕
* @since    : 2016.05.27
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.27  이환덕        최초생성
*
*/
@Repository("AsumAcntDAO")
public class AsumAcntDAO extends ComAbstractDAO{
    
  /**
    * @Method Name        : selectSptCustAccList
    * @Method description : [일반회원가상계좌+기업코드:총갯수]정보를 조회한다.
    * @param              : AsumAcntVO
    * @return             : int
    * @throws             : Exception
    */
   public int selectSptCustAccListTotalCount(AsumAcntVO paramVO) throws Exception{
       return (Integer) select("myp.AsumAcntDAO.selectSptCustAccListTotalCount",paramVO);
   }
   
  /**
    * @Method Name        : selectSptCustAccList
    * @Method description : [일반회원가상계좌+기업코드]목록정보를 조회한다.
    * @param              : AsumAcntVO
    * @return             : List<AsumAcntVO>
    * @throws             : Exception
    */
   @SuppressWarnings("unchecked")
   public List<AsumAcntVO> selectSptCustAccList(AsumAcntVO paramVO) throws Exception{
       return (List<AsumAcntVO>) list("myp.AsumAcntDAO.selectSptCustAccList",paramVO);
   }
   
  /**
    * @Method Name        : updateSptCustomerAccountProfile
    * @Method description : [일반회원가상계좌]정보를 수정한다.
    * @param              : SptCustomerAccountProfileVO
    * @return             : int
    * @throws             : Exception
    */
   public int updateSptCustomerAccountProfile(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception{
       return (int) update("myp.AsumAcntDAO.updateSptCustomerAccountProfile",sptCustomerAccountProfileVO);
   }
   
  /**
    * @Method Name        : deleteSptCustomerAccountProfile
    * @Method description : [일반회원가상계좌]정보를 삭제한다.
    * @param              : SptCustomerAccountProfileVO
    * @return             : int
    * @throws             : Exception
    */
   public int deleteSptCustomerAccountProfile(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception{
       return (int) update("myp.AsumAcntDAO.deleteSptCustomerAccountProfile",sptCustomerAccountProfileVO);
   }
   
  /**
    * @Method Name        : insertSptCustomerAccountProfileHist
    * @Method description : [일반회원가상계좌히스토리]정보를 등록한다.
    * @param              : SptCustomerAccountProfileVO
    * @return             : int
    * @throws             : Exception
    */
   public int insertSptCustomerAccountProfileHist(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception{
       return (int) update("myp.AsumAcntDAO.insertSptCustomerAccountProfileHist",sptCustomerAccountProfileVO);
   }

  /**
   * @Method Name        : selectSptDelCustAccList
   * @Method description : [탈퇴 일반회원가상계좌+기업코드]정보를 조회한다.
   * @param              : AsumAcntVO
   * @return             : List<AsumAcntVO>
   * @throws             : Exception
   */
    @SuppressWarnings("unchecked")
    public List<AsumAcntVO> selectSptDelCustAccList(AsumAcntVO paramVO) throws Exception{
        return (List<AsumAcntVO>) list("myp.AsumAcntDAO.selectSptDelCustAccList",paramVO);
    }

}
