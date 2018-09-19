package kr.co.koscom.oppf.spt.myp.asumAcnt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.spt.myp.asumAcnt.service.AsumAcntService;
import kr.co.koscom.oppf.spt.myp.asumAcnt.service.AsumAcntVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerAccountProfileVO;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AsumAcntServiceImpl.java
* @Comment  : [마이페이지:가상계좌]정보관리를 위한 Service 클래스
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
@Service("AsumAcntService")
public class AsumAcntServiceImpl implements AsumAcntService{
    
    @Resource(name = "AsumAcntDAO")
    private AsumAcntDAO asumAcntDAO;
    
    private static final Logger log = Logger.getLogger(AsumAcntServiceImpl.class);

   /**
     * @Method Name        : selectSptCustAccInfo
     * @Method description : [일반회원가상계좌+기업코드]정보를 조회한다.
     * @param              : AsumAcntVO
     * @return             : HashMap<String,Object>
     * @throws             : Exception
     */
    public HashMap<String,Object> selectSptCustAccInfo(AsumAcntVO paramVO) throws Exception{
        HashMap<String,Object> rsMap = new HashMap<String,Object>();
        int rsListTotCnt = asumAcntDAO.selectSptCustAccListTotalCount(paramVO);
        List<AsumAcntVO> rsList = (List<AsumAcntVO>) asumAcntDAO.selectSptCustAccList(paramVO);
        rsMap.put("rsTotCnt", rsListTotCnt);
        rsMap.put("rsList", rsList);
        
        return rsMap;
    }

    
   /**
     * @Method Name        : selectSptCustAccList
     * @Method description : [일반회원가상계좌+기업코드]목록정보를 조회한다.
     * @param              : AsumAcntVO
     * @return             : List<AsumAcntVO>
     * @throws             : Exception
     */
    public List<AsumAcntVO> selectSptCustAccList(AsumAcntVO paramVO) throws Exception{
        return (List<AsumAcntVO>) asumAcntDAO.selectSptCustAccList(paramVO);
    }
    
  /**
    * @Method Name        : updateSptCustomerAccountProfile
    * @Method description : [일반회원가상계좌]정보를 수정한다.
    * @param              : SptCustomerAccountProfileVO
    * @return             : int
    * @throws             : Exception
    */
   @Transactional
   public int updateSptCustomerAccountProfile(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception{
       int rs = 0;
       //1.일반회원가상계좌 DB삭제
       int rs1 = asumAcntDAO.updateSptCustomerAccountProfile(sptCustomerAccountProfileVO);
       int rs2 = asumAcntDAO.insertSptCustomerAccountProfileHist(sptCustomerAccountProfileVO);
       rs = rs1 + rs2;
       log.debug("1.일반회원가상계좌 DB수정後:rs="+rs);
       return rs;
   }
   
  /**
    * @Method Name        : deleteSptCustomerAccountProfile
    * @Method description : [일반회원가상계좌]정보를 삭제한다.
    * @param              : SptCustomerAccountProfileVO
    * @return             : int
    * @throws             : Exception
    */
   @Transactional
   public int deleteSptCustomerAccountProfile(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception{
       int rs = 0;
       //1.일반회원가상계좌 DB삭제
       int rs1 = asumAcntDAO.deleteSptCustomerAccountProfile(sptCustomerAccountProfileVO);
       int rs2 = asumAcntDAO.insertSptCustomerAccountProfileHist(sptCustomerAccountProfileVO);
       rs = rs1 + rs2;
       log.debug("1.일반회원가상계좌 DB삭제後:rs="+rs);
       return rs;
   }

}
