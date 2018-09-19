package kr.co.koscom.oppf.spt.usr.mbrReg.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.cmm.service.AccListAttributeREQVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegTermsContentVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegTermsVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;

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
@Repository("MbrRegDAO")
public class MbrRegDAO extends ComAbstractDAO{
    
   /**
    * @Method Name        : selectCheckId
    * @Method description : [회원가입:ID중복확인]ID정보를 조회한다.
    * @param              : String
    * @return             : String
    * @throws             : Exception
    */
   public String selectCheckId(MbrRegVO paramVO) throws Exception{
       String rs = (String) select("usr.MbrRegDAO.selectCheckId",paramVO);
       return rs;
   }
   
   /**
    * @Method Name        : selectSptCustomerInfoProfile
    * @Method description : [회원가입:기본]정보를 조회한다.
    * @param              : MbrRegVO
    * @return             : MbrRegVO
    * @throws             : Exception
    */
   public MbrRegVO selectSptCustomerInfoProfile(MbrRegVO paramVO) throws Exception{
       return (MbrRegVO) select("usr.MbrRegDAO.selectSptCustomerInfoProfile",paramVO);
   }
   
   /**
    * @Method Name        : selectSptCustomerVerifyProfile
    * @Method description : [회원가입:인증]정보를 조회한다.
    * @param              : MbrRegVO
    * @return             : MbrRegVO
    * @throws             : Exception
    */
   public MbrRegVO selectSptCustomerVerifyProfile(MbrRegVO paramVO) throws Exception{
       return (MbrRegVO) select("usr.MbrRegDAO.selectSptCustomerVerifyProfile",paramVO);
   }
   
   /**
    * @Method Name        : selectSptWithDrawCustomerVerifyProfile
    * @Method description : 탈퇴회원 인증 정보를 조회한다.
    * @param              : MbrRegVO
    * @return             : int
    * @throws             : Exception
    */
   public int selectSptWithDrawCustomerVerifyProfile(MbrRegVO paramVO) throws Exception{
       return (int) select("usr.MbrRegDAO.selectSptWithDrawCustomerVerifyProfile",paramVO);
   }
   
   /**
    * @Method Name        : selectSptCustomerVerifyProfileList
    * @Method description : [회원가입:인증목록]정보를 조회한다.
    * @param              : MbrRegVO
    * @return             : List<MbrRegVO>
    * @throws             : Exception
    */
   @SuppressWarnings("unchecked")
   public List<MbrRegVO> selectSptCustomerVerifyProfileList(MbrRegVO paramVO) throws Exception{
       return (List<MbrRegVO>) list("usr.MbrRegDAO.selectSptCustomerVerifyProfileList",paramVO);
   }
   

   /**
    * @Method Name        : selectSptCustomerCiInfo
    * @Method description : [회원정보:기본&인증]정보를 조회한다.
    * @param paramVO
    * @return
    */
	public MbrRegVO selectSptCustomerCiInfo(AccListAttributeREQVO paramVO) throws Exception{
		return(MbrRegVO) select("usr.MbrRegDAO.selectSptCustomerCiInfo",paramVO);
	}
   
   /**
    * @Method Name        : selectSptCustomerTermsProfile
    * @Method description : [회원가입:약관]정보를 조회한다.
    * @param              : MbrRegTermsVO
    * @return             : MbrRegTermsVO
    * @throws             : Exception
    */
   public MbrRegVO selectSptCustomerTermsProfile(MbrRegTermsVO paramVO) throws Exception{
       return (MbrRegVO) select("usr.MbrRegDAO.selectSptCustomerTermsProfile",paramVO);
   }
   
   /**
    * @Method Name        : selectSptCustomerTermsProfileList
    * @Method description : [회원가입:약관목록]정보를 조회한다.
    * @param              : MbrRegTermsVO
    * @return             : MbrRegTermsVO
    * @throws             : Exception
    */
   @SuppressWarnings("unchecked")
   public List<MbrRegTermsVO> selectSptCustomerTermsProfileList(MbrRegTermsVO paramVO) throws Exception{
       return (List<MbrRegTermsVO>) list("usr.MbrRegDAO.selectSptCustomerTermsProfileList",paramVO);
   }
   
   /**
    * @Method Name        : selectSptCustomerTermsContentInfo
    * @Method description : [회원가입:약관내용]정보를 조회한다.
    * @param              : MbrRegTermsContentVO
    * @return             : MbrRegTermsContentVO
    * @throws Exception 
    */
   public MbrRegTermsContentVO selectSptCustomerTermsContentInfo(MbrRegTermsContentVO paramVO) throws Exception{
       return (MbrRegTermsContentVO) select("usr.MbrRegDAO.selectSptCustomerTermsContentInfo",paramVO);
   }
   
   /**
    * @Method Name        : selectSptCustomerTermsContentList
    * @Method description : [회원가입:약관내용목록]정보를 조회한다.
    * @param              : MbrRegTermsContentVO
    * @return             : List<MbrRegTermsContentVO>
    * @throws             : Exception
    */
   @SuppressWarnings("unchecked")
   public List<MbrRegTermsContentVO> selectSptCustomerTermsContentList(MbrRegTermsContentVO paramVO) throws Exception{
       return (List<MbrRegTermsContentVO>) list("usr.MbrRegDAO.selectSptCustomerTermsContentList",paramVO);
   }

   
   /**
    * @Method Name        : selectSptCustomerTermsContentList
    * @Method description : [회원가입:약관내용목록]정보를 조회한다.
    * @param              : MbrRegTermsContentVO
    * @return             : List<MbrRegTermsContentVO>
    * @throws             : Exception
    */
   @SuppressWarnings("unchecked")
   public List<MbrRegTermsVO> selectCustomerTermsContentList(MbrRegTermsContentVO paramVO) throws Exception{
       return (List<MbrRegTermsVO>) list("usr.MbrRegDAO.selectCustomerTermsContentList",paramVO);
   }
   
   /**
    * @Method Name        : selectSptCustomerProfileInfo
    * @Method description : [회원가입:기본&인증]정보를 조회한다.
    * @param              : MbrRegVO
    * @return             : MbrRegVO
    * @throws             : Exception
    */
   public MbrRegVO selectSptCustomerProfileInfo(MbrRegVO paramVO) throws Exception{
       return (MbrRegVO) select("usr.MbrRegDAO.selectSptCustomerProfileInfo",paramVO);
   }
    
    /**
     * @Method Name        : insertSptCustomerInfoProfile
     * @Method description : [회원가입:기본]정보등록을 한다.
     * @param              : MbrRegVO
     * @return             : String customerRegNo(회원OP등록번호)
     * @throws             : Exception
     */
    public String insertSptCustomerInfoProfile(MbrRegVO paramVO) throws Exception{
        String customerRegNo = (String) insert("usr.MbrRegDAO.insertSptCustomerInfoProfile", paramVO);
        return customerRegNo;
    }
    
    /**
     * @Method Name        : insertSptCustomerInfoProfileHist
     * @Method description : [회원가입:기본정보history]등록을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerInfoProfileHist(MbrRegVO paramVO) throws Exception{
        int rs = update("usr.MbrRegDAO.insertSptCustomerInfoProfileHist", paramVO);
        return rs;
    }
    
    /**
     * @Method Name        : insertSptCustomerVerifyProfile
     * @Method description : [회원가입:인증]정보등록을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerVerifyProfile(MbrRegVO paramVO) throws Exception{
        int rs = update("usr.MbrRegDAO.insertSptCustomerVerifyProfile", paramVO);
        return rs;
    }
    
    /**
     * @Method Name        : insertSptCustomerVerifyProfileHist
     * @Method description : [회원가입:인증hist]정보등록을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerVerifyProfileHist(MbrRegVO paramVO) throws Exception{
        int rs = update("usr.MbrRegDAO.insertSptCustomerVerifyProfileHist", paramVO);
        return rs;
    }
    
    /**
     * @Method Name        : insertSptCustomerTermsProfile
     * @Method description : [회원가입:약관]정보등록을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerTermsProfile(MbrRegTermsVO paramVO) throws Exception{
        int rs = update("usr.MbrRegDAO.insertSptCustomerTermsProfile", paramVO);
        return rs;
    }
    
    /**
     * @Method Name        : insertSptCustomerTermsProfileHist
     * @Method description : [회원가입:약관hist]정보등록을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerTermsProfileHist(MbrRegTermsVO paramVO) throws Exception{
        int rs = update("usr.MbrRegDAO.insertSptCustomerTermsProfileHist", paramVO);
        return rs;
    }
    
    /**
     * @Method Name        : updateSptCustomerInfoProfile
     * @Method description : [회원가입:기본]정보수정을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptCustomerInfoProfile(MbrRegVO paramVO) throws Exception{
        int rs = update("usr.MbrRegDAO.updateSptCustomerInfoProfile", paramVO);
        return rs;
    }
    
    /**
     * @Method Name        : updatesptCustomerInfoProfile
     * @Method description : [회원가입:인증]정보수정을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptCustomerVerifyProfile(MbrRegVO paramVO) throws Exception{
        int rs = update("usr.MbrRegDAO.updateSptCustomerVerifyProfile", paramVO);
        return rs;
    }
    
    /**
     * @Method Name        : updateSptCustomerTermsProfile
     * @Method description : [회원가입:약관]정보수정을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptCustomerTermsProfile(MbrRegTermsVO paramVO) throws Exception{
        int rs = update("usr.MbrRegDAO.updateSptCustomerTermsProfile", paramVO);
        return rs;
    }
    
    /**
     * @Method Name        : selectMainSptCustomerTermsList
     * @Method description : [메인]메인 접속 시 해당사용자의 약관동의 정보를 조회한다.
     * @param              : MbrRegVO
     * @return             : List<MbrRegTermsContentVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<MbrRegTermsContentVO> selectMainSptCustomerTermsList(MbrRegVO paramVO) throws Exception{
        return (List<MbrRegTermsContentVO>) list("usr.MbrRegDAO.selectMainSptCustomerTermsList",paramVO);
    }
    
    /**
     * @Method Name        : checkMainSptCustomerTermsList
     * @Method description : [메인]메인 접속 시 해당사용자의 약관동의 정보 저장 전 데이터 체크
     * @param              : MbrRegTermsVO
     * @return             : int
     * @throws             : Exception
     */
    public int checkMainSptCustomerTerms(MbrRegTermsVO paramVO) throws Exception{
        return (Integer) select("usr.MbrRegDAO.checkMainSptCustomerTerms",paramVO);
    }

    /**
     * @Method Name        : selectSptCustomerProfileInfo
     * @Method description : [회원정보:기본&인증]정보를 조회한다.
     * @param              : MbrRegVO
     * @return             : MbrRegVO
     * @throws             : Exception
     */
    public MbrRegVO selectSptCustomerVerifyProfileInfo(MbrRegVO paramVO) throws Exception{
        return (MbrRegVO) select("usr.MbrRegDAO.selectSptCustomerVerifyProfileInfo",paramVO);
    }

    /**
     * @Method Name        : selectMobileTermsList
     * @Method description : 통신사 약관 정보를 조회한다.
     * @param              : MbrRegTermsContentVO
     * @return             : List<MbrRegTermsContentVO>
     * @throws             : Exception
     */
    public List<MbrRegTermsContentVO> selectMobileTermsList(MbrRegTermsContentVO paramVO) throws Exception{
        return (List<MbrRegTermsContentVO>) list("usr.MbrRegDAO.selectSptCustomerTermsContentList",paramVO);
    }

    /**
     * selectCountDnVerifyInfo
     * 등록된 인증서 여부 조회
     * @param paramVO
     * @return
     */
	public int selectCountDnVerifyInfo(MbrRegVO paramVO) {
		return (int) select("usr.MbrRegDAO.selectCountDnVerifyInfo",paramVO);
	}

	public int updateUserInfo(MbrRegVO paramVO) {
        int rs = update("usr.MbrRegDAO.updateUserInfo", paramVO);
        return rs;
		
	}
	

    /**
     * @Method Name        : insertSptCustomerInfoProfileHist
     * @Method description : [회원가입:기본정보history]등록을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             :
     */
    public int insertSptCustomerHist(MbrRegVO paramVO){
        int rs = update("usr.MbrRegDAO.insertSptCustomerInfoProfileHist", paramVO);
        return rs;
    }


    /**
     * @Method Name        : selectMainCompanyTermsList
     * @Method description : [메인]메인 접속 시 기업 약관동의 정보를 조회한다.
     * @param              : MbrRegVO
     * @return             : List<MbrRegTermsContentVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<MbrRegTermsContentVO> selectMainCompanyTermsList(MbrRegVO paramVO) throws Exception{
        return (List<MbrRegTermsContentVO>) list("usr.MbrRegDAO.selectMainCompanyTermsList",paramVO);
    }

	public int checkCompanyTermsProfile(MbrRegTermsVO terms) {
		return (int) select("usr.MbrRegDAO.checkCompanyTermsProfile",terms);
	}

	public int updateCompanyTermsProfile(MbrRegTermsVO terms) {
		return update("usr.MbrRegDAO.updateCompanyTermsProfile",terms);
	}

	public int insertCompanyTermsProfile(MbrRegTermsVO terms) {
		return (int) insert("usr.MbrRegDAO.iinsertCompanyTermsProfile",terms);
	}

	public void insertCompanyTermsProfileHist(MbrRegTermsVO terms) {
		insert("usr.MbrRegDAO.insertCompanyTermsProfileHist",terms);
	}
    
    
}
