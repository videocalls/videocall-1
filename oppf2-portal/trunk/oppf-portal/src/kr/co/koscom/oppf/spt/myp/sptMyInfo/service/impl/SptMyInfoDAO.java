package kr.co.koscom.oppf.spt.myp.sptMyInfo.service.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.cmm.util.CodeConstants;
import kr.co.koscom.oppf.spt.myp.sptMyInfo.service.SptMyInfoVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptMyInfoDAO.java
* @Comment  : [개인회원정보]정보관리를 위한 DAO 클래스
* @author   : 포털 유제량
* @since    : 2016.05.09
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.09  유제량        최초생성
*
*/
@Repository("SptMyInfoDAO")
public class SptMyInfoDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectSptMyInfo
     * @Method description : [개인회원정보상세:상세]정보를 조회한다.
     * @param              : SptMyInfoVO
     * @return             : SptMyInfoVO
     * @throws             : Exception
     */
    public SptMyInfoVO selectSptMyInfo(SptMyInfoVO sptMyInfoVO) throws Exception{
        return (SptMyInfoVO) select("spt.SptMyInfoDAO.selectSptMyInfo", sptMyInfoVO);
    }
    
    /**
     * @Method Name        : selectCheckPw
     * @Method description : [개인회원정보:ID중복확인]ID정보를 조회한다.
     * @param              : String
     * @return             : String
     * @throws             : Exception
     */
    public String selectCheckPw(SptMyInfoVO sptMyInfoVO) throws Exception{
        String rs = (String) select("spt.SptMyInfoDAO.selectCheckPw",sptMyInfoVO);
        return rs;
    }
     
     /**
      * @Method Name        : updateSptMyInfo
      * @Method description : [개인회원정보:수정]을 한다.
      * @param              : SptMyInfoVO
      * @return             : int
      * @throws             : Exception
      */
     public int updateSptMyInfo(SptMyInfoVO sptMyInfoVO) throws Exception{
         int rs = update("spt.SptMyInfoDAO.updateSptMyInfo", sptMyInfoVO);
         return rs;
     }
     
     /**
      * @Method Name        : updateSptMyPwMod
      * @Method description : [개인회원정보:비밀번호변경]을 한다.
      * @param              : SptMyInfoVO
      * @return             : int
      * @throws             : Exception
      */
     public int updateSptMyPwMod(SptMyInfoVO sptMyInfoVO) throws Exception{
         int rs = update("spt.SptMyInfoDAO.updateSptMyPwMod", sptMyInfoVO);
         return rs;
     }
     
     /**
      * @Method Name        : insertSptMyPageMod
      * @Method description : [개인회원정보:비밀번호변경]을 한다.(history update)
      * @param              : SptMyInfoVO
      * @return             : int
      * @throws             : Exception
      */
     public void insertSptMyPageMod(SptMyInfoVO sptMyInfoVO) throws Exception{
         insert("spt.SptMyInfoDAO.insertSptMyPageMod", sptMyInfoVO);         
     }
     
     /**
      * @Method Name        : updateSptMbrSecesInfo
      * @Method description : [개인회원정보:회원탈퇴]를 한다.
      * @param              : SptMyInfoVO
      * @return             : int
      * @throws             : Exception
      */
     @Transactional
     public int updateSptMbrSecesInfo(SptMyInfoVO sptMyInfoVO) throws Exception{
    	 int rs = update("spt.SptMyInfoDAO.updateSptMbrSecesInfo", sptMyInfoVO);
    	 insert("spt.SptMyInfoDAO.insertSptMyPageMod", sptMyInfoVO);   
    	 insert("spt.SptMyInfoDAO.moveMemberInfo", sptMyInfoVO);
    	 sptMyInfoVO.setCustomerVerifyType(CodeConstants.VERIFY_TYPE_CI.toString());
    	 insert("spt.SptMyInfoDAO.insertLeaveMemberInfo", sptMyInfoVO);
    	 sptMyInfoVO.setCustomerVerifyType(CodeConstants.VERIFY_TYPE_DN.toString());
    	 insert("spt.SptMyInfoDAO.insertLeaveMemberInfo", sptMyInfoVO);
         return rs;
     }
     
     /**
      * @Method Name        : selectSptCustomerInfoProfile
      * @Method description : [개인회원정보:기본]정보를 조회한다.
      * @param              : SptMyInfoVO
      * @return             : SptMyInfoVO
      * @throws             : Exception
      */
     public SptMyInfoVO selectSptCustomerInfoProfile(SptMyInfoVO paramVO) throws Exception{
         return (SptMyInfoVO) select("spt.SptMyInfoDAO.selectSptCustomerInfoProfile",paramVO);
     }
     
     /**
      * @Method Name        : selectSptCustomerProfile
      * @Method description : [개인회원정보상세:상세]정보를 조회한다.(탈퇴메일발송를 위한 정보를 조회해 온다.)
      * @param              : SptMyInfoVO
      * @return             : SptMyInfoVO
      * @throws             : Exception
      */
     public SptMyInfoVO selectSptCustomerProfile(SptMyInfoVO paramVO) throws Exception{
         return (SptMyInfoVO) select("spt.SptMyInfoDAO.selectSptCustomerProfile",paramVO);
     }
    
}
