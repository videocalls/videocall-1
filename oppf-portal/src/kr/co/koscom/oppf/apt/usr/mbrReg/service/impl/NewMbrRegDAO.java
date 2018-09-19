package kr.co.koscom.oppf.apt.usr.mbrReg.service.impl;

import kr.co.koscom.oppf.apt.sptUsr.service.SptUserManageVO;
import kr.co.koscom.oppf.apt.usr.mbrReg.service.NewMbrRegTermsContentVO;
import kr.co.koscom.oppf.apt.usr.mbrReg.service.NewMbrRegTermsVO;
import kr.co.koscom.oppf.apt.usr.mbrReg.service.NewMbrRegVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : MbrRegDAO.java
* @Comment  : [회원가입]정보관리를 위한 DAO 클래스
* @author   : 포털 이환덕
* @since    : 2016.05.02
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.02  이환덕        최초생성
*
*/
@Repository("NewMbrRegDAO")
public class NewMbrRegDAO extends ComAbstractDAO{
    
   /**
    * @Method Name        : selectCheckId
    * @Method description : [회원가입:ID중복확인]ID정보를 조회한다.
    * @param              : String
    * @return             : String
    * @throws             : Exception
    */
   public String selectCheckId(NewMbrRegVO paramVO) throws Exception{
       String rs = (String) select("apt.usr.NewMbrRegDAO.selectCheckId",paramVO);
       return rs;
   }
   
   /**
    * @Method Name        : insertSptCustomerInfoProfileHist
    * @Method description : [회원가입:기본정보history]등록을 한다.
    * @param              : NewMbrRegVO
    * @return             : int
    * @throws             : Exception
    */
   public int insertSptCustomerInfoProfileHist(NewMbrRegVO paramVO) throws Exception{
       int rs = update("apt.usr.NewMbrRegDAO.insertSptCustomerInfoProfileHist", paramVO);
       return rs;
   }   
   
   /**
    * @Method Name        : insertSptCustomerVerifyProfile
    * @Method description : [회원가입:인증]정보등록을 한다.
    * @param              : NewMbrRegVO
    * @return             : int
    * @throws             : Exception
    */
   public int insertSptCustomerVerifyProfile(NewMbrRegVO paramVO) throws Exception{
       int rs = update("apt.usr.NewMbrRegDAO.insertSptCustomerVerifyProfile", paramVO);
       return rs;
   }
   
   /**
    * @Method Name        : insertSptCustomerVerifyProfileHist
    * @Method description : [회원가입:인증hist]정보등록을 한다.
    * @param              : NewMbrRegVO
    * @return             : int
    * @throws             : Exception
    */
   public int insertSptCustomerVerifyProfileHist(NewMbrRegVO paramVO) throws Exception{
       int rs = update("apt.usr.NewMbrRegDAO.insertSptCustomerVerifyProfileHist", paramVO);
       return rs;
   }

   /**
    * @Method Name        : checkMainSptCustomerTermsList
    * @Method description : [메인]메인 접속 시 해당사용자의 약관동의 정보 저장 전 데이터 체크
    * @param              : NewMbrRegTermsVO
    * @return             : int
    * @throws             : Exception
    */
   public int checkMainSptCustomerTerms(NewMbrRegTermsVO paramVO) throws Exception{
       return (Integer) select("apt.usr.NewMbrRegDAO.checkMainSptCustomerTerms",paramVO);
   }
   
   /**
    * @Method Name        : updateSptCustomerTermsProfile
    * @Method description : [회원가입:약관]정보수정을 한다.
    * @param              : NewMbrRegVO
    * @return             : int
    * @throws             : Exception
    */
   public int updateSptCustomerTermsProfile(NewMbrRegTermsVO paramVO) throws Exception{
       int rs = update("apt.usr.NewMbrRegDAO.updateSptCustomerTermsProfile", paramVO);
       return rs;
   }
   
   /**
    * @Method Name        : insertSptCustomerTermsProfile
    * @Method description : [회원가입:약관]정보등록을 한다.
    * @param              : NewMbrRegVO
    * @return             : int
    * @throws             : Exception
    */
   public int insertSptCustomerTermsProfile(NewMbrRegTermsVO paramVO) throws Exception{
       int rs = update("apt.usr.NewMbrRegDAO.insertSptCustomerTermsProfile", paramVO);
       return rs;
   }
   
   /**
    * @Method Name        : insertSptCustomerTermsProfileHist
    * @Method description : [회원가입:약관hist]정보등록을 한다.
    * @param              : NewMbrRegVO
    * @return             : int
    * @throws             : Exception
    */
   public int insertSptCustomerTermsProfileHist(NewMbrRegTermsVO paramVO) throws Exception{
       int rs = update("apt.usr.NewMbrRegDAO.insertSptCustomerTermsProfileHist", paramVO);
       return rs;
   }
   /**
    * @Method Name        : updateSptUserManageDtl
    * @Method description : 일반회원정보 수정
    * @param              : SptUserManageVO
    * @return             : int
    * @throws             : Exception
    */
   public int updateSptUserManageDtlDev(NewMbrRegVO paramVO) throws Exception{
   	return update("apt.usr.NewMbrRegDAO.updateSptCustomerInfoProfile", paramVO);
   }
   
   /**
    * @Method Name        : insertSptUserManageDtlHist
    * @Method description : 일반회원정보 수정 hist를 insert한다.
    * @param              : SptUserManageVO
    * @return             : int
    * @throws             : Exception
    */
   public int insertSptUserManageDtlHist(SptUserManageVO sptUserManageVO) throws Exception{
   	return update("apt.SptUserManageDAO.insertSptUserManageDtlHist", sptUserManageVO);
   }
   
   /**
    * @Method Name        : updatesptCustomerInfoProfile
    * @Method description : [회원가입:인증]정보수정을 한다.
    * @param              : NewMbrRegVO
    * @return             : int
    * @throws             : Exception
    */
   public int updateSptCustomerVerifyProfile(NewMbrRegVO paramVO) throws Exception{
       int rs = update("apt.usr.NewMbrRegDAO.updateSptCustomerVerifyProfile", paramVO);
       return rs;
   }
   
   /**
    * @Method Name        : updatesptCustomerInfoWithDrawProfile
    * @Method description : [회원가입:인증]정보  탈퇴처리를 한다.
    * @param              : NewMbrRegVO
    * @return             : int
    * @throws             : Exception
    */
   public int updatesptCustomerInfoWithDrawProfile(NewMbrRegVO paramVO) throws Exception{
       int rs = update("apt.usr.NewMbrRegDAO.updatesptCustomerInfoWithDrawProfile", paramVO);
       return rs;
   }

   /**
    * @Method Name        : chkSptCustomerVerifyProfile
    * @Method description : [회원가입:인증]기존정보 있는지 확인한다
    * @param              : NewMbrRegVO
    * @return             : int
    * @throws             : Exception
    */
   public int chkSptCustomerVerifyProfile(NewMbrRegVO paramVO) throws Exception{
       int rs = (Integer) select("apt.usr.NewMbrRegDAO.chkSptCustomerVerifyProfile", paramVO);
       return rs;
   }
   
   /**
    * @Method Name        : selectMemberInfo
    * @Method description : [기본]회원정보를 조회한다.
    * @param              : NewMbrRegVO
    * @return             : NewMbrRegVO
    * @throws             : Exception
    */
   public NewMbrRegVO selectMemberInfo(NewMbrRegVO paramVO) throws Exception{
       return (NewMbrRegVO) select("apt.usr.NewMbrRegDAO.selectMemberInfo",paramVO);
   }
   
   
   
   

   
   
   
   
   
   
   
   
   /**
    * @Method Name        : selectSptCustomerInfoProfile
    * @Method description : [회원가입:기본]정보를 조회한다.
    * @param              : NewMbrRegVO
    * @return             : NewMbrRegVO
    * @throws             : Exception
    */
   public NewMbrRegVO selectSptCustomerInfoProfile(NewMbrRegVO paramVO) throws Exception{
       return (NewMbrRegVO) select("apt.usr.NewMbrRegDAO.selectSptCustomerInfoProfile",paramVO);
   }
   
   /**
    * @Method Name        : selectSptCustomerVerifyProfile
    * @Method description : [회원가입:인증]정보를 조회한다.
    * @param              : NewMbrRegVO
    * @return             : NewMbrRegVO
    * @throws             : Exception
    */
   public NewMbrRegVO selectSptCustomerVerifyProfile(NewMbrRegVO paramVO) throws Exception{
       return (NewMbrRegVO) select("apt.usr.NewMbrRegDAO.selectSptCustomerVerifyProfile",paramVO);
   }
   
   /**
    * @Method Name        : selectSptCustomerVerifyProfileList
    * @Method description : [회원가입:인증목록]정보를 조회한다.
    * @param              : NewMbrRegVO
    * @return             : List<NewMbrRegVO>
    * @throws             : Exception
    */
   @SuppressWarnings("unchecked")
public List<NewMbrRegVO> selectSptCustomerVerifyProfileList(NewMbrRegVO paramVO) throws Exception{
       return (List<NewMbrRegVO>) list("apt.usr.NewMbrRegDAO.selectSptCustomerVerifyProfileList",paramVO);
   }
   
   /**
    * @Method Name        : selectSptCustomerTermsProfile
    * @Method description : [회원가입:약관]정보를 조회한다.
    * @param              : MbrRegTermsVO
    * @return             : MbrRegTermsVO
    * @throws             : Exception
    */
   public NewMbrRegVO selectSptCustomerTermsProfile(NewMbrRegTermsVO paramVO) throws Exception{
       return (NewMbrRegVO) select("apt.usr.NewMbrRegDAO.selectSptCustomerTermsProfile",paramVO);
   }
   
   /**
    * @Method Name        : selectSptCustomerTermsProfileList
    * @Method description : [회원가입:약관목록]정보를 조회한다.
    * @param              : NewMbrRegTermsVO
    * @return             : NewMbrRegTermsVO
    * @throws             : Exception
    */
   @SuppressWarnings("unchecked")
   public List<NewMbrRegTermsVO> selectSptCustomerTermsProfileList(NewMbrRegTermsVO paramVO) throws Exception{
       return (List<NewMbrRegTermsVO>) list("apt.usr.NewMbrRegDAO.selectSptCustomerTermsProfileList",paramVO);
   }
   
   /**
    * @Method Name        : selectSptCustomerTermsContentInfo
    * @Method description : [회원가입:약관내용]정보를 조회한다.
    * @param              : NewMbrRegTermsContentVO
    * @return             : NewMbrRegTermsContentVO
    * @throws Exception 
    */
   public NewMbrRegTermsContentVO selectSptCustomerTermsContentInfo(NewMbrRegTermsContentVO paramVO) throws Exception{
       return (NewMbrRegTermsContentVO) select("apt.usr.NewMbrRegDAO.selectSptCustomerTermsContentInfo",paramVO);
   }
   
   /**
    * @Method Name        : selectSptCustomerTermsContentList
    * @Method description : [회원가입:약관내용목록]정보를 조회한다.
    * @param              : NewMbrRegTermsContentVO
    * @return             : List<NewMbrRegTermsContentVO>
    * @throws             : Exception
    */
   @SuppressWarnings("unchecked")
   public List<NewMbrRegTermsContentVO> selectSptCustomerTermsContentList(NewMbrRegTermsContentVO paramVO) throws Exception{
       return (List<NewMbrRegTermsContentVO>) list("apt.usr.NewMbrRegDAO.selectSptCustomerTermsContentList",paramVO);
   }
   
   /**
    * @Method Name        : selectSptCustomerProfileInfo
    * @Method description : [회원가입:기본&인증]정보를 조회한다.
    * @param              : NewMbrRegVO
    * @return             : NewMbrRegVO
    * @throws             : Exception
    */
   public NewMbrRegVO selectSptCustomerProfileInfo(NewMbrRegVO paramVO) throws Exception{
       return (NewMbrRegVO) select("apt.usr.NewMbrRegDAO.selectSptCustomerProfileInfo",paramVO);
   }
    
    /**
     * @Method Name        : insertSptCustomerInfoProfile
     * @Method description : [회원가입:기본]정보등록을 한다.
     * @param              : NewMbrRegVO
     * @return             : String customerRegNo(회원OP등록번호)
     * @throws             : Exception
     */
    public String insertSptCustomerInfoProfile(NewMbrRegVO paramVO) throws Exception{
        String customerRegNo = (String) insert("apt.usr.NewMbrRegDAO.insertSptCustomerInfoProfile", paramVO);
        return customerRegNo;
    }
    
    /**
     * @Method Name        : updateSptCustomerInfoProfile
     * @Method description : [회원가입:기본]정보수정을 한다.
     * @param              : NewMbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptCustomerInfoProfile(NewMbrRegVO paramVO) throws Exception{
        int rs = update("apt.usr.NewMbrRegDAO.updateSptCustomerInfoProfile", paramVO);
        return rs;
    }
    /**
     * @Method Name        : selectMainSptCustomerTermsList
     * @Method description : [메인]메인 접속 시 해당사용자의 약관동의 정보를 조회한다.
     * @param              : NewMbrRegVO
     * @return             : List<NewMbrRegTermsContentVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<NewMbrRegTermsContentVO> selectMainSptCustomerTermsList(NewMbrRegVO paramVO) throws Exception{
        return (List<NewMbrRegTermsContentVO>) list("apt.usr.NewMbrRegDAO.selectMainSptCustomerTermsList",paramVO);
    }

    /**
     * @Method Name        : sptUserVerifyDupChk
     * @Method description : 인증값 중복 체크
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @Transactional
    public int sptUserVerifyDupChk(NewMbrRegVO paramVO){
    	return (int) select("apt.usr.sptUserVerifyDupChk", paramVO);
    }
}
