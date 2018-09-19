package kr.co.koscom.oppf.spt.usr.svcAppl.service.impl;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SvcApplDAO.java
* @Comment  : [서비스신청]정보관리를 위한 DAO 클래스
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
@Repository("SvcApplDAO")
public class SvcApplDAO extends ComAbstractDAO{

    /**
	 * @Method Name        : selectSptCustAccList
	 * @Method description : [일반회원가상계좌+기업코드]목록정보를 조회한다.
	 * @param              : SvcApplVO
	 * @return             : List<SvcApplVO>
	 * @throws             : Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SvcApplVO> selectSptCustAccList(SvcApplVO svcApplVO) throws Exception{
		return (List<SvcApplVO>) list("usr.SvcApplDAO.selectSptCustAccList",svcApplVO);
	}
	
    /**
     * @Method description : 금투사 기업 약관 정보 조회
     * @param              : SvcApplVO
     * @return             : List<SvcApplVO>
     * @throws             : Exception
	 */
	@SuppressWarnings("unchecked")
	public SptCustomerCompanyTermsProfileVO selectSvcMemberCompanyTermsConsntList(SvcApplVO svcApplVO) throws Exception{
		return (SptCustomerCompanyTermsProfileVO) select("usr.SvcApplDAO.selectSvcMemberCompanyTermsConsntList",svcApplVO);
	}
	
    /**
     * @Method Name        : selectSptCustAccInfo
     * @Method description : [일반회원가상계좌+기업코드]정보를 조회한다.
     * @param              : SvcApplVO
     * @return             : SvcApplVO
     * @throws             : Exception
     */
	public SvcApplVO selectSptCustAccInfo(SvcApplVO svcApplVO) throws Exception{
		return (SvcApplVO) select("usr.SvcApplDAO.selectSptCustAccList",svcApplVO);
    }
   
    /**
     * @Method Name        : selectsptCustomerAccountProfileList
     * @Method description : [일반회원가상계좌목록]정보를 조회한다.
     * @param              : SptCustomerAccountProfileVO
     * @return             : List<SptCustomerAccountProfileVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptCustomerAccountProfileVO> selectSptCustomerAccountProfileList(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception{
        return (List<SptCustomerAccountProfileVO>) list("usr.SvcApplDAO.selectSptCustomerAccountProfileList",sptCustomerAccountProfileVO);
    }
   
    /**
     * @Method Name        : selectComCompanyCodeList
     * @Method description : [기업코드목록]정보를 조회한다.
     * @param              : ComCompanyCodeVO
     * @return             : List<ComCompanyCodeVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<ComCompanyCodeVO> selectComCompanyCodeList(ComCompanyCodeVO comCompanyCodeVO) throws Exception{
        return (List<ComCompanyCodeVO>) list("usr.SvcApplDAO.selectComCompanyCodeList",comCompanyCodeVO);
    }
   
    /**
     * @Method Name        : selectComCompanyCodeList
     * @Method description : [기업코드]정보를 조회한다.
     * @param              : ComCompanyCodeVO
     * @return             : ComCompanyCodeVO
     * @throws             : Exception
     */
    public ComCompanyCodeVO selectComCompanyCodeInfo(ComCompanyCodeVO comCompanyCodeVO) throws Exception{
        return (ComCompanyCodeVO) select("usr.SvcApplDAO.selectComCompanyCodeList",comCompanyCodeVO);
    }
    
    /**
     * @Method Name        : selectComCompanyProfileList
     * @Method description : [기업프로파일목록]정보를 조회한다.
     * @param              : ComCompanyProfileVO
     * @return             : List<ComCompanyProfileVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<ComCompanyProfileVO> selectComCompanyProfileList(ComCompanyProfileVO paramVO) throws Exception{
        return (List<ComCompanyProfileVO>) list("usr.SvcApplDAO.selectComCompanyProfileList",paramVO);
    }
    
    /**
     * @Method Name        : selectComCompanyProfileInfo
     * @Method description : [기업프로파일]정보를 조회한다.
     * @param              : ComCompanyProfileVO
     * @return             : ComCompanyProfileVO
     * @throws             : Exception
     */
    public ComCompanyProfileVO selectComCompanyProfileInfo(ComCompanyProfileVO paramVO) throws Exception{
        return (ComCompanyProfileVO) select("usr.SvcApplDAO.selectComCompanyProfileInfo",paramVO);
    }
   
   /**
    * @Method Name        : insertSptCustomerAccountProfile
    * @Method description : [일반회원가상계좌]정보를 등록한다.
    * @param              : SptCustomerAccountProfileVO
    * @return             : int
    * @throws             : Exception
    */
    public int insertSptCustomerAccountProfile(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception{
        return (int) update("usr.SvcApplDAO.insertSptCustomerAccountProfile",sptCustomerAccountProfileVO);
    }
   
   /**
    * @Method Name        : updateSptCustomerAccountProfile
    * @Method description : [일반회원가상계좌]정보를 수정한다.
    * @param              : SptCustomerAccountProfileVO
    * @return             : int
    * @throws             : Exception
    */
    public int updateSptCustomerAccountProfile(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception{
        return (int) update("usr.SvcApplDAO.updateSptCustomerAccountProfile",sptCustomerAccountProfileVO);
    }
   
   /**
    * @Method Name        : deleteSptCustomerAccountProfile
    * @Method description : [일반회원가상계좌]정보를 삭제한다.
    * @param              : SptCustomerAccountProfileVO
    * @return             : int
    * @throws             : Exception
    */
    public int deleteSptCustomerAccountProfile(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception{
        return (int) update("usr.SvcApplDAO.deleteSptCustomerAccountProfile",sptCustomerAccountProfileVO);
    }
   
   /**
    * @Method Name        : insertSptCustomerAccountProfileHist
    * @Method description : [일반회원가상계좌히스토리]정보를 등록한다.
    * @param              : SptCustomerAccountProfileVO
    * @return             : int
    * @throws             : Exception
    */
    public int insertSptCustomerAccountProfileHist(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception{
        return (int) update("usr.SvcApplDAO.insertSptCustomerAccountProfileHist",sptCustomerAccountProfileVO);
    }
       
    /**
     * @Method Name        : selectFintechSvcCompanyList
     * @Method description : [핀테크서비스 선택] 사용자 기준 금투사 목록을 가져온다.
     * @param              : SvcApplVO
     * @return             : List<SvcApplVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SvcApplVO> selectFintechSvcCompanyList(SvcApplVO svcApplVO) throws Exception{
        return (List<SvcApplVO>) list("usr.SvcApplDAO.selectFintechSvcCompanyList",svcApplVO);
    }
   
	 /**
	  * @Method Name        : selectFintechSvcCompanyList
	  * @Method description : [핀테크서비스 선택] 핀테크 서비스 목록
	  * @param              : SvcApplVO
	  * @return             : List<SptCustomerServiceProfileVO>
	  * @throws             : Exception
	  */
	@SuppressWarnings("unchecked")
	public List<SptCustomerServiceProfileVO> selectFintechSvcList(SvcApplVO svcApplVO) throws Exception{
		return (List<SptCustomerServiceProfileVO>) list("usr.SvcApplDAO.selectFintechSvcList",svcApplVO);
    }
    
	/**
	 * @Method Name        : selectFintechSvcPubcompanyList
	 * @Method description : [핀테크서비스 선택] 핀테크 서비스 금투사 목록
	 * @param              : SvcApplVO
	 * @return             : List<SptCustomerServiceProfileVO>
	 * @throws             : Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComCompanyProfileVO> selectFintechSvcPubcompanyList(SvcApplVO svcApplVO) throws Exception{
	    return (List<ComCompanyProfileVO>) list("usr.SvcApplDAO.selectFintechSvcPubcompanyList",svcApplVO);
	}
	
	/**
	 * @Method Name        : selectFintechSvcAccountList
	 * @Method description : [핀테크서비스 선택] 가상계좌 목록
	 * @param              : SvcApplVO
	 * @return             : List<SptCustomerServiceProfileVO>
	 * @throws             : Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SptCustomerServiceAccountProfileVO> selectFintechSvcAccountList(SvcApplVO svcApplVO) throws Exception{
	    return (List<SptCustomerServiceAccountProfileVO>) list("usr.SvcApplDAO.selectFintechSvcAccountList",svcApplVO);
	}
	
    /**
     * @Method Name        : selectSptCustomerServiceTermsProfile
     * @Method description : [일반회원서비스약관프로파일]정보를 조회한다.
     * @param              : SptCustomerServiceTermsProfileVO
     * @return             : SptCustomerServiceTermsProfileVO
     * @throws             : Exception
     */
    public SptCustomerServiceTermsProfileVO selectSptCustomerServiceTermsProfile(SptCustomerServiceTermsProfileVO paramVO) throws Exception{
        return (SptCustomerServiceTermsProfileVO) select("usr.SvcApplDAO.selectSptCustomerServiceTermsProfile",paramVO);
    }
    
    /**
     * @Method Name        : selectSptCustomerServiceTermsPubcompanyProfileList
     * @Method description : [일반회원서비스약관금투사프로파일목록]정보를 조회한다.
     * @param              : SptCustomerServiceTermsPubcompanyProfileVO
     * @return             : List<SptCustomerServiceTermsPubcompanyProfileVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptCustomerServiceTermsPubcompanyProfileVO> selectSptCustomerServiceTermsPubcompanyProfileList(SptCustomerServiceTermsPubcompanyProfileVO paramVO) throws Exception{
        return (List<SptCustomerServiceTermsPubcompanyProfileVO>) list("usr.SvcApplDAO.selectSptCustomerServiceTermsPubcompanyProfileList",paramVO);
    }
	
	/**
     * @Method Name        : insertSptCustomerServiceProfileHist
     * @Method description : [핀테크서비스 선택] 일반회원 서비스 프로파일 hist 정보를 등록한다.
     * @param              : SptCustomerServiceProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerServiceProfileHist(SptCustomerServiceProfileVO sptCustomerServiceProfileVO) throws Exception{
        return (int) update("usr.SvcApplDAO.insertSptCustomerServiceProfileHist", sptCustomerServiceProfileVO);
    }
	
	/**
     * @Method Name        : insertSptCustomerServiceProfile
     * @Method description : [핀테크서비스 선택] 일반회원 서비스 프로파일 정보를 등록한다.
     * @param              : SptCustomerServiceProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public String insertSptCustomerServiceProfile(SptCustomerServiceProfileVO sptCustomerServiceProfileVO) throws Exception{
        return (String) insert("usr.SvcApplDAO.insertSptCustomerServiceProfile", sptCustomerServiceProfileVO);
    }
        
    /**
	 * @Method Name        : selectFintechSvcTermsList
	 * @Method description : [핀테크서비스선택] 사용자의 약관정보를 가져온다.
	 * @param              : SvcApplVO
	 * @return             : List<SptCustomerServiceTermsPubcompanyProfileVO>
	 * @throws             : Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SptCustomerServiceTermsPubcompanyProfileVO> selectFintechSvcTermsList(SvcApplVO svcApplVO) throws Exception{
	    return (List<SptCustomerServiceTermsPubcompanyProfileVO>) list("usr.SvcApplDAO.selectFintechSvcTermsList",svcApplVO);
	}
	
	/**
     * @Method Name        : selectFintechSvcTermsCustomer
     * @Method description : [핀테크서비스선택] 약관표시될 사용자 정보를 조회한다.
     * @param              : SvcApplVO
     * @return             : SvcApplVO
     * @throws             : Exception
     */
    public SvcApplVO selectFintechSvcTermsCustomer(SvcApplVO svcApplVO) throws Exception{
        return (SvcApplVO) select("usr.SvcApplDAO.selectFintechSvcTermsCustomer",svcApplVO);
    }
    
    /**
     * @Method Name        : selectFintechSvcTermsCompany
     * @Method description : [핀테크서비스선택] 약관표시될 기업 정보를 조회한다.
     * @param              : SvcApplVO
     * @return             : SvcApplVO
     * @throws             : Exception
     */
    public SvcApplVO selectFintechSvcTermsCompany(SvcApplVO svcApplVO) throws Exception{
        return (SvcApplVO) select("usr.SvcApplDAO.selectFintechSvcTermsCompany",svcApplVO);
    }
    
	/**
     * @Method Name        : insertSptCustomerServiceAccountProfileHist
     * @Method description : [핀테크서비스 선택] 일반회원서비스 계좌 프로파일 hist 정보를 등록한다.
     * @param              : SptCustomerServiceAccountProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerServiceAccountProfileHist(SptCustomerServiceAccountProfileVO sptCustomerServiceAccountProfileVO) throws Exception{
        return (int) update("usr.SvcApplDAO.insertSptCustomerServiceAccountProfileHist", sptCustomerServiceAccountProfileVO);
    }
	
	/**
     * @Method Name        : insertSptCustomerServiceAccountProfile
     * @Method description : [핀테크서비스 선택] 일반회원서비스 계좌 프로파일 정보 정보를 등록한다.
     * @param              : SptCustomerServiceAccountProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public String insertSptCustomerServiceAccountProfile(SptCustomerServiceAccountProfileVO sptCustomerServiceAccountProfileVO) throws Exception{
        return (String) insert("usr.SvcApplDAO.insertSptCustomerServiceAccountProfile", sptCustomerServiceAccountProfileVO);
    }
    
    /**
     * @Method Name        : updateSptCustomerServiceAccountProfile
     * @Method description : [핀테크서비스 선택] 일반회원서비스 계좌 프로파일 정보 정보를 수정한다.
     * @param              : SptCustomerServiceAccountProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptCustomerServiceAccountProfile(SptCustomerServiceAccountProfileVO sptCustomerServiceAccountProfileVO) throws Exception{
        return (Integer) update("usr.SvcApplDAO.updateSptCustomerServiceAccountProfile", sptCustomerServiceAccountProfileVO);
    }
    
    /**
     * @Method Name        : insertSptCustomerServiceTermsProfile
     * @Method description : [핀테크서비스 선택] 일반회원 사비스 약관 프로파일 정보를 등록한다.
     * @param              : SptCustomerServiceTermsProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public String insertSptCustomerServiceTermsProfile(SptCustomerServiceTermsProfileVO sptCustomerServiceTermsProfileVO) throws Exception{
        return (String) insert("usr.SvcApplDAO.insertSptCustomerServiceTermsProfile", sptCustomerServiceTermsProfileVO);
    }
    
    /**
     * @Method Name        : insertSptCustomerServiceTermsProfileHist
     * @Method description : [핀테크서비스 선택] 일반회원 사비스 약관 프로파일 hist 정보를 등록한다.
     * @param              : SptCustomerServiceTermsProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerServiceTermsProfileHist(SptCustomerServiceTermsProfileVO sptCustomerServiceTermsProfileVO) throws Exception{
        return (int) update("usr.SvcApplDAO.insertSptCustomerServiceTermsProfileHist", sptCustomerServiceTermsProfileVO);
    }
    
    /**
     * @Method Name        : insertSptCustomerServiceTermsPubcompanyProfile
     * @Method description : [핀테크서비스 선택] 일반회원 사비스 약관 금투사 프로파일 정보를 등록한다.
     * @param              : SptCustomerServiceTermsPubcompanyProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public String insertSptCustomerServiceTermsPubcompanyProfile(SptCustomerServiceTermsPubcompanyProfileVO sptCustomerServiceTermsPubcompanyProfileVO) throws Exception{
        return (String) insert("usr.SvcApplDAO.insertSptCustomerServiceTermsPubcompanyProfile", sptCustomerServiceTermsPubcompanyProfileVO);
    }
    
    /**
     * @Method Name        : insertSptCustomerServiceTermsPubcompanyProfileHist
     * @Method description : [핀테크서비스 선택] 일반회원 사비스 약관 금투사 프로파일 hist 정보를 등록한다.
     * @param              : SptCustomerServiceTermsPubcompanyProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerServiceTermsPubcompanyProfileHist(SptCustomerServiceTermsPubcompanyProfileVO sptCustomerServiceTermsPubcompanyProfileVO) throws Exception{
        return (int) update("usr.SvcApplDAO.insertSptCustomerServiceTermsPubcompanyProfileHist", sptCustomerServiceTermsPubcompanyProfileVO);
    }
       
    /**
     * @Method Name        : updateSptCustomerServiceProfile
     * @Method description : [핀테크서비스 선택] 일반회원서비스 프로파일 정보를 수정한다.
     * @param              : sptCustomerServiceProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptCustomerServiceProfile(SptCustomerServiceProfileVO sptCustomerServiceProfileVO) throws Exception{
        return (Integer) update("usr.SvcApplDAO.updateSptCustomerServiceProfile", sptCustomerServiceProfileVO);
    }
    
    /**
     * @Method Name        : updateSptCustomerServiceProfileHist
     * @Method description : [핀테크서비스 선택] 일반회원 서비스 프로파일 hist 정보를 등록한다.
     * @param              : SptCustomerServiceProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptCustomerServiceProfileHist(SptCustomerServiceProfileVO sptCustomerServiceProfileVO) throws Exception{
        return (int) update("usr.SvcApplDAO.updateSptCustomerServiceProfileHist", sptCustomerServiceProfileVO);
    }
    
    /**
     * @Method Name        : selectFintechSvcTermsCleanList
     * @Method description : [핀테크서비스 선택] 서비스의 정보제공동의 클랭징 목록을 조회한다.
     * @param              : SvcApplVO
     * @return             : List<SvcApplVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SvcApplVO> selectFintechSvcTermsCleanList(SvcApplVO svcApplVO) throws Exception{
        return (List<SvcApplVO>) list("usr.SvcApplDAO.selectFintechSvcTermsCleanList",svcApplVO);
    }
    
    /**
     * @Method Name        : usr.SvcApplDAO.updateSptCustomerServiceProfileSvcTermsClean
     * @Method description : [핀테크서비스 선택] 서비스 프로파일에 정보제공 동의 번호를 update한다.
     * @param              : SptCustomerServiceProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptCustomerServiceProfileSvcTermsClean(SptCustomerServiceProfileVO sptCustomerServiceProfileVO) throws Exception{
        return (int) update("usr.SvcApplDAO.updateSptCustomerServiceProfileSvcTermsClean", sptCustomerServiceProfileVO);
    }
    
    /**
     * @Method Name        : selectSvcTermConsntList
     * @Method description : [약관동의] 기업 약관동의 대상 목록을 조회한다.
     * @param              : SvcApplVO
     * @return             : List<SptCustomerServiceAccountProfileVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptCustomerCompanyTermsProfileVO> selectSvcCompanyTermsConsntList(SvcApplVO svcApplVO) throws Exception{
        return (List<SptCustomerCompanyTermsProfileVO>) list("usr.SvcApplDAO.selectSvcCompanyTermsConsntList",svcApplVO);
    }
        
    /**
     * @Method Name        : selectSvcCompanyTermsConsntAgreeCompanyList
     * @Method description : [약관동의] 기업 약관동의한 기업 목록을 조회한다.
     * @param              : SvcApplVO
     * @return             : List<SptCustomerServiceAccountProfileVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptCustomerCompanyTermsProfileVO> selectSvcCompanyTermsConsntAgreeCompanyList(SvcApplVO svcApplVO) throws Exception{
        return (List<SptCustomerCompanyTermsProfileVO>) list("usr.SvcApplDAO.selectSvcCompanyTermsConsntAgreeCompanyList",svcApplVO);
    }
        
    /**
     * @Method Name        : checkSptCustomerCompanyTermsProfile
     * @Method description : [약관동의] 기업 약관 저장 전 기업약관 정보 확인
     * @param              : SvcApplVO
     * @return             : SptCustomerCompanyTermsProfileVO
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public int checkSptCustomerCompanyTermsProfile(SptCustomerCompanyTermsProfileVO sptCustomerCompanyTermsProfileVO) throws Exception{
        return (Integer) select("usr.SvcApplDAO.checkSptCustomerCompanyTermsProfile",sptCustomerCompanyTermsProfileVO);
    }
    
    /**
     * @Method Name        : insertSptCustomerCompanyTermsProfile
     * @Method description : [약관동의] 기업 약관 정보를 insert.
     * @param              : SptCustomerCompanyTermsProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerCompanyTermsProfile(SptCustomerCompanyTermsProfileVO sptCustomerCompanyTermsProfileVO) throws Exception{
        return (Integer) update("usr.SvcApplDAO.insertSptCustomerCompanyTermsProfile", sptCustomerCompanyTermsProfileVO);
    }
    
    /**
     * @Method Name        : updateSptCustomerCompanyTermsProfile
     * @Method description : [약관동의] 기업 약관 정보를 update.
     * @param              : SptCustomerCompanyTermsProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptCustomerCompanyTermsProfile(SptCustomerCompanyTermsProfileVO sptCustomerCompanyTermsProfileVO) throws Exception{
        return (Integer) update("usr.SvcApplDAO.updateSptCustomerCompanyTermsProfile", sptCustomerCompanyTermsProfileVO);
    }
    
    /**
     * @Method Name        : insertSptCustomerCompanyTermsProfileHist
     * @Method description : [약관동의] 기업 약관 정보의 hist를 insert.
     * @param              : SptCustomerCompanyTermsProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerCompanyTermsProfileHist(SptCustomerCompanyTermsProfileVO sptCustomerCompanyTermsProfileVO) throws Exception{
        return (Integer) update("usr.SvcApplDAO.insertSptCustomerCompanyTermsProfileHist", sptCustomerCompanyTermsProfileVO);
    }
        
    /**
     * @Method Name        : selectSvcTermConsntList
     * @Method description : [정보제공동의] 정보제공동의 목록을 조회한다.
     * @param              : SvcApplVO
     * @return             : List<SptCustomerServiceAccountProfileVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptCustomerServiceAccountProfileVO> selectSvcTermConsntList(SvcApplVO svcApplVO) throws Exception{
        return (List<SptCustomerServiceAccountProfileVO>) list("usr.SvcApplDAO.selectSvcTermConsntList",svcApplVO);
    }
    
    /**
     * @Method Name        : selectSvcApplCompltList
     * @Method description : [서비스신청완료] 서비스신청완료 목록을 조회한다.
     * @param              : SvcApplVO
     * @return             : List<SptCustomerServiceAccountProfileVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptCustomerServiceAccountProfileVO> selectSvcApplCompltList(SvcApplVO svcApplVO) throws Exception{
        return (List<SptCustomerServiceAccountProfileVO>) list("usr.SvcApplDAO.selectSvcApplCompltList",svcApplVO);
    }
    
    /**
	 * @Method Name        : selectMainSvcAppList
	 * @Method description : [메인] 메인의 핀테크 서비스 목록(TOP 5)을 조회한다.
	 * @param              : SvcApplVO
	 * @return             : List<SvcApplVO>
	 * @throws             : Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SvcApplVO> selectMainSvcAppList(SvcApplVO svcApplVO) throws Exception{
		return (List<SvcApplVO>) list("usr.SvcApplDAO.selectMainSvcAppList",svcApplVO);
	}

    /**
     * @Method Name        : selectSvcAppInfo
     * @Method description : [Oauth] 앱 정보.
     * @param              : SvcApplVO
     * @return             : SvcApplVO
     * @throws             : Exception
     */
    public SvcApplVO selectSvcAppInfo(SvcApplVO svcApplVO) throws Exception{
        return (SvcApplVO) select("usr.SvcApplDAO.selectSvcAppInfo",svcApplVO);
    }
	
	/**
     * @Method Name        : insertSptCustomerServiceProfile
     * @Method description : [핀테크서비스 선택] 일반회원 서비스 프로파일 정보를 등록한다.
     * @param              : SptCustomerServiceProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public String insertSptCustomerServiceProfileDev(SvcApplVO svcApplVO) throws Exception{
        return (String) insert("usr.SvcApplDAO.insertSptCustomerServiceProfileDev", svcApplVO);
    }

	/**
     * @Method Name        : insertSptCustomerServiceProfileHistDev
     * @Method description : [핀테크서비스 선택] 일반회원 서비스 프로파일 hist 정보를 등록한다.
     * @param              : SptCustomerServiceProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerServiceProfileHistDev(SvcApplVO svcApplVO) throws Exception{
        return (int) update("usr.SvcApplDAO.insertSptCustomerServiceProfileHistDev", svcApplVO);
    }
    
    /**
     * @Method Name        : selectSptCustomerServiceProfileCnt
     * @Method description : [핀테크서비스 선택] 일반회원 서비스 프로파일 정보를 등록한다.
     * @param              : SptCustomerServiceProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectSptCustomerServiceProfileCnt(SvcApplVO svcApplVO) throws Exception{
        return (int) select("usr.SvcApplDAO.selectSptCustomerServiceProfileCnt", svcApplVO);
    }

    /**
     * @Method Name        : selectFintechSvcCompanyList
     * @Method description : [핀테크서비스 선택] 핀테크 서비스 선택 여부
     * @param              : SvcApplVO
     * @return             : SptCustomerServiceProfileVO
     * @throws             : Exception
     */
    public SptCustomerServiceProfileVO selectFintechSvc(SvcApplVO svcApplVO) throws Exception{
        return (SptCustomerServiceProfileVO) select("usr.SvcApplDAO.selectFintechSvc",svcApplVO);
    }

    /**
     * @Method Name        : selectFintechSvcIntAcntAccountList
     * @Method description : [핀테크서비스 선택] 통합계좌 가상계좌 목록
     * @param              : SvcApplVO
     * @return             : List<SptCustomerServiceProfileVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptCustomerServiceAccountProfileVO> selectFintechSvcIntAcntAccountList(SvcApplVO svcApplVO) throws Exception{
        return (List<SptCustomerServiceAccountProfileVO>) list("usr.SvcApplDAO.selectFintechSvcIntAcntAccountList",svcApplVO);
    }

}
