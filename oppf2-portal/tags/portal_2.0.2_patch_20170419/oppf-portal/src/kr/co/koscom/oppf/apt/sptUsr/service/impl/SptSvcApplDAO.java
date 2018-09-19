package kr.co.koscom.oppf.apt.sptUsr.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.apt.sptUsr.service.SptSvcApplVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.cmm.tsa.service.CmmTsaVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.ComCompanyCodeVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.ComCompanyProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerAccountProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerCompanyTermsProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceAccountProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceTermsProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceTermsPubcompanyProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SvcApplVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.impl.SvcApplDAO;

@Repository("SptSvcApplDAO")
public class SptSvcApplDAO extends ComAbstractDAO{
    
    private static final Logger log = Logger.getLogger(SptSvcApplDAO.class);
   
    /**
	 * @Method Name        : selectSptCustAccList
	 * @Method description : [일반회원가상계좌+기업코드]목록정보를 조회한다.
	 * @param              : SvcApplVO
	 * @return             : List<SvcApplVO>
	 * @throws             : Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SvcApplVO> selectSptCustAccList(SptSvcApplVO sptSvcApplVO) throws Exception{
		return (List<SvcApplVO>) list("spt.SptSvcApplDAO.selectSptCustAccList",sptSvcApplVO);
	}
   
    /**
     * @Method Name        : selectSptCustAccInfo
     * @Method description : [일반회원가상계좌+기업코드]정보를 조회한다.
     * @param              : SvcApplVO
     * @return             : SvcApplVO
     * @throws             : Exception
     */
	public SvcApplVO selectSptCustAccInfo(SptSvcApplVO sptSvcApplVO) throws Exception{
		return (SvcApplVO) select("spt.SptSvcApplDAO.selectSptCustAccList",sptSvcApplVO);
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
        return (List<SptCustomerAccountProfileVO>) list("spt.SptSvcApplDAO.selectSptCustomerAccountProfileList",sptCustomerAccountProfileVO);
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
        return (List<ComCompanyCodeVO>) list("spt.SptSvcApplDAO.selectComCompanyCodeList",comCompanyCodeVO);
    }
   
    /**
     * @Method Name        : selectComCompanyCodeList
     * @Method description : [기업코드]정보를 조회한다.
     * @param              : ComCompanyCodeVO
     * @return             : ComCompanyCodeVO
     * @throws             : Exception
     */
    public ComCompanyCodeVO selectComCompanyCodeInfo(ComCompanyCodeVO comCompanyCodeVO) throws Exception{
        return (ComCompanyCodeVO) select("spt.SptSvcApplDAO.selectComCompanyCodeList",comCompanyCodeVO);
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
        return (List<ComCompanyProfileVO>) list("spt.SptSvcApplDAO.selectComCompanyProfileList",paramVO);
    }
    
    /**
     * @Method Name        : selectComCompanyProfileInfo
     * @Method description : [기업프로파일]정보를 조회한다.
     * @param              : ComCompanyProfileVO
     * @return             : ComCompanyProfileVO
     * @throws             : Exception
     */
    public ComCompanyProfileVO selectComCompanyProfileInfo(ComCompanyProfileVO paramVO) throws Exception{
        return (ComCompanyProfileVO) select("spt.SptSvcApplDAO.selectComCompanyProfileInfo",paramVO);
    }
   
   /**
    * @Method Name        : insertSptCustomerAccountProfile
    * @Method description : [일반회원가상계좌]정보를 등록한다.
    * @param              : SptCustomerAccountProfileVO
    * @return             : int
    * @throws             : Exception
    */
    public int insertSptCustomerAccountProfile(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception{
        return (int) update("spt.SptSvcApplDAO.insertSptCustomerAccountProfile",sptCustomerAccountProfileVO);
    }
   
   /**
    * @Method Name        : updateSptCustomerAccountProfile
    * @Method description : [일반회원가상계좌]정보를 수정한다.
    * @param              : SptCustomerAccountProfileVO
    * @return             : int
    * @throws             : Exception
    */
    public int updateSptCustomerAccountProfile(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception{
        return (int) update("spt.SptSvcApplDAO.updateSptCustomerAccountProfile",sptCustomerAccountProfileVO);
    }
   
   /**
    * @Method Name        : deleteSptCustomerAccountProfile
    * @Method description : [일반회원가상계좌]정보를 삭제한다.
    * @param              : SptCustomerAccountProfileVO
    * @return             : int
    * @throws             : Exception
    */
    public int deleteSptCustomerAccountProfile(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception{
        return (int) update("spt.SptSvcApplDAO.deleteSptCustomerAccountProfile",sptCustomerAccountProfileVO);
    }
   
   /**
    * @Method Name        : insertSptCustomerAccountProfileHist
    * @Method description : [일반회원가상계좌히스토리]정보를 등록한다.
    * @param              : SptCustomerAccountProfileVO
    * @return             : int
    * @throws             : Exception
    */
    public int insertSptCustomerAccountProfileHist(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception{
        return (int) update("spt.SptSvcApplDAO.insertSptCustomerAccountProfileHist",sptCustomerAccountProfileVO);
    }
       
    /**
     * @Method Name        : selectFintechSvcCompanyList
     * @Method description : [핀테크서비스 선택] 사용자 기준 금투사 목록을 가져온다.
     * @param              : SvcApplVO
     * @return             : List<SvcApplVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SvcApplVO> selectFintechSvcCompanyList(SptSvcApplVO sptSvcApplVO) throws Exception{
        return (List<SvcApplVO>) list("spt.SptSvcApplDAO.selectFintechSvcCompanyList",sptSvcApplVO);
    }
   
	 /**
	  * @Method Name        : selectFintechSvcCompanyList
	  * @Method description : [핀테크서비스 선택] 핀테크 서비스 목록
	  * @param              : SvcApplVO
	  * @return             : List<SptCustomerServiceProfileVO>
	  * @throws             : Exception
	  */
	@SuppressWarnings("unchecked")
	public List<SptCustomerServiceProfileVO> selectFintechSvcList(SptSvcApplVO sptSvcApplVO) throws Exception{
		return (List<SptCustomerServiceProfileVO>) list("spt.SptSvcApplDAO.selectFintechSvcList",sptSvcApplVO);
    }
    
	/**
	 * @Method Name        : selectFintechSvcPubcompanyList
	 * @Method description : [핀테크서비스 선택] 핀테크 서비스 금투사 목록
	 * @param              : SvcApplVO
	 * @return             : List<SptCustomerServiceProfileVO>
	 * @throws             : Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComCompanyProfileVO> selectFintechSvcPubcompanyList(SptSvcApplVO sptSvcApplVO) throws Exception{
	    return (List<ComCompanyProfileVO>) list("spt.SptSvcApplDAO.selectFintechSvcPubcompanyList",sptSvcApplVO);
	}
	
	/**
	 * @Method Name        : selectFintechSvcAccountList
	 * @Method description : [핀테크서비스 선택] 가상계좌 목록
	 * @param              : SvcApplVO
	 * @return             : List<SptCustomerServiceProfileVO>
	 * @throws             : Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SptCustomerServiceAccountProfileVO> selectFintechSvcAccountList(SptSvcApplVO sptSvcApplVO) throws Exception{
	    return (List<SptCustomerServiceAccountProfileVO>) list("spt.SptSvcApplDAO.selectFintechSvcAccountList",sptSvcApplVO);
	}
	
    /**
     * @Method Name        : selectSptCustomerServiceTermsProfile
     * @Method description : [일반회원서비스약관프로파일]정보를 조회한다.
     * @param              : SptCustomerServiceTermsProfileVO
     * @return             : SptCustomerServiceTermsProfileVO
     * @throws             : Exception
     */
    public SptCustomerServiceTermsProfileVO selectSptCustomerServiceTermsProfile(SptCustomerServiceTermsProfileVO paramVO) throws Exception{
        return (SptCustomerServiceTermsProfileVO) select("spt.SptSvcApplDAO.selectSptCustomerServiceTermsProfile",paramVO);
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
        return (List<SptCustomerServiceTermsPubcompanyProfileVO>) list("spt.SptSvcApplDAO.selectSptCustomerServiceTermsPubcompanyProfileList",paramVO);
    }
	
	/**
     * @Method Name        : insertSptCustomerServiceProfileHist
     * @Method description : [핀테크서비스 선택] 일반회원 서비스 프로파일 hist 정보를 등록한다.
     * @param              : SptCustomerServiceProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerServiceProfileHist(SptCustomerServiceProfileVO sptCustomerServiceProfileVO) throws Exception{
        return (int) update("spt.SptSvcApplDAO.insertSptCustomerServiceProfileHist", sptCustomerServiceProfileVO);
    }
	
	/**
     * @Method Name        : insertSptCustomerServiceProfile
     * @Method description : [핀테크서비스 선택] 일반회원 서비스 프로파일 정보를 등록한다.
     * @param              : SptCustomerServiceProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public String insertSptCustomerServiceProfile(SptCustomerServiceProfileVO sptCustomerServiceProfileVO) throws Exception{
        return (String) insert("spt.SptSvcApplDAO.insertSptCustomerServiceProfile", sptCustomerServiceProfileVO);
    }
        
    /**
	 * @Method Name        : selectFintechSvcTermsList
	 * @Method description : [핀테크서비스선택] 사용자의 약관정보를 가져온다.
	 * @param              : SvcApplVO
	 * @return             : List<SptCustomerServiceTermsPubcompanyProfileVO>
	 * @throws             : Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SptCustomerServiceTermsPubcompanyProfileVO> selectFintechSvcTermsList(SptSvcApplVO sptSvcApplVO) throws Exception{
	    return (List<SptCustomerServiceTermsPubcompanyProfileVO>) list("spt.SptSvcApplDAO.selectFintechSvcTermsList",sptSvcApplVO);
	}
	
	/**
     * @Method Name        : selectFintechSvcTermsCustomer
     * @Method description : [핀테크서비스선택] 약관표시될 사용자 정보를 조회한다.
     * @param              : SvcApplVO
     * @return             : SvcApplVO
     * @throws             : Exception
     */
    public SvcApplVO selectFintechSvcTermsCustomer(SptSvcApplVO sptSvcApplVO) throws Exception{
        return (SvcApplVO) select("spt.SptSvcApplDAO.selectFintechSvcTermsCustomer",sptSvcApplVO);
    }
    
    /**
     * @Method Name        : selectFintechSvcTermsCompany
     * @Method description : [핀테크서비스선택] 약관표시될 기업 정보를 조회한다.
     * @param              : SvcApplVO
     * @return             : SvcApplVO
     * @throws             : Exception
     */
    public SvcApplVO selectFintechSvcTermsCompany(SptSvcApplVO sptSvcApplVO) throws Exception{
        return (SvcApplVO) select("spt.SptSvcApplDAO.selectFintechSvcTermsCompany",sptSvcApplVO);
    }
    
	/**
     * @Method Name        : insertSptCustomerServiceAccountProfileHist
     * @Method description : [핀테크서비스 선택] 일반회원서비스 계좌 프로파일 hist 정보를 등록한다.
     * @param              : SptCustomerServiceAccountProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerServiceAccountProfileHist(SptCustomerServiceAccountProfileVO sptCustomerServiceAccountProfileVO) throws Exception{
        return (int) update("spt.SptSvcApplDAO.insertSptCustomerServiceAccountProfileHist", sptCustomerServiceAccountProfileVO);
    }
	
	/**
     * @Method Name        : insertSptCustomerServiceAccountProfile
     * @Method description : [핀테크서비스 선택] 일반회원서비스 계좌 프로파일 정보 정보를 등록한다.
     * @param              : SptCustomerServiceAccountProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public String insertSptCustomerServiceAccountProfile(SptCustomerServiceAccountProfileVO sptCustomerServiceAccountProfileVO) throws Exception{
        return (String) insert("spt.SptSvcApplDAO.insertSptCustomerServiceAccountProfile", sptCustomerServiceAccountProfileVO);
    }
    
    /**
     * @Method Name        : updateSptCustomerServiceAccountProfile
     * @Method description : [핀테크서비스 선택] 일반회원서비스 계좌 프로파일 정보 정보를 수정한다.
     * @param              : SptCustomerServiceAccountProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptCustomerServiceAccountProfile(SptCustomerServiceAccountProfileVO sptCustomerServiceAccountProfileVO) throws Exception{
        return (Integer) update("spt.SptSvcApplDAO.updateSptCustomerServiceAccountProfile", sptCustomerServiceAccountProfileVO);
    }
    
    /**
     * @Method Name        : insertSptCustomerServiceTermsProfile
     * @Method description : [핀테크서비스 선택] 일반회원 사비스 약관 프로파일 정보를 등록한다.
     * @param              : SptCustomerServiceTermsProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public String insertSptCustomerServiceTermsProfile(SptCustomerServiceTermsProfileVO sptCustomerServiceTermsProfileVO) throws Exception{
        return (String) insert("spt.SptSvcApplDAO.insertSptCustomerServiceTermsProfile", sptCustomerServiceTermsProfileVO);
    }
    
    /**
     * @Method Name        : insertSptCustomerServiceTermsProfileHist
     * @Method description : [핀테크서비스 선택] 일반회원 사비스 약관 프로파일 hist 정보를 등록한다.
     * @param              : SptCustomerServiceTermsProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerServiceTermsProfileHist(SptCustomerServiceTermsProfileVO sptCustomerServiceTermsProfileVO) throws Exception{
        return (int) update("spt.SptSvcApplDAO.insertSptCustomerServiceTermsProfileHist", sptCustomerServiceTermsProfileVO);
    }
    
    /**
     * @Method Name        : insertSptCustomerServiceTermsPubcompanyProfile
     * @Method description : [핀테크서비스 선택] 일반회원 사비스 약관 금투사 프로파일 정보를 등록한다.
     * @param              : SptCustomerServiceTermsPubcompanyProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public String insertSptCustomerServiceTermsPubcompanyProfile(SptCustomerServiceTermsPubcompanyProfileVO sptCustomerServiceTermsPubcompanyProfileVO) throws Exception{
        return (String) insert("spt.SptSvcApplDAO.insertSptCustomerServiceTermsPubcompanyProfile", sptCustomerServiceTermsPubcompanyProfileVO);
    }
    
    /**
     * @Method Name        : insertSptCustomerServiceTermsPubcompanyProfileHist
     * @Method description : [핀테크서비스 선택] 일반회원 사비스 약관 금투사 프로파일 hist 정보를 등록한다.
     * @param              : SptCustomerServiceTermsPubcompanyProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerServiceTermsPubcompanyProfileHist(SptCustomerServiceTermsPubcompanyProfileVO sptCustomerServiceTermsPubcompanyProfileVO) throws Exception{
        return (int) update("spt.SptSvcApplDAO.insertSptCustomerServiceTermsPubcompanyProfileHist", sptCustomerServiceTermsPubcompanyProfileVO);
    }
       
    /**
     * @Method Name        : updateSptCustomerServiceProfile
     * @Method description : [핀테크서비스 선택] 일반회원서비스 프로파일 정보를 수정한다.
     * @param              : sptCustomerServiceProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptCustomerServiceProfile(SptCustomerServiceProfileVO sptCustomerServiceProfileVO) throws Exception{
        return (Integer) update("spt.SptSvcApplDAO.updateSptCustomerServiceProfile", sptCustomerServiceProfileVO);
    }
    
    /**
     * @Method Name        : updateSptCustomerServiceProfileHist
     * @Method description : [핀테크서비스 선택] 일반회원 서비스 프로파일 hist 정보를 등록한다.
     * @param              : SptCustomerServiceProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptCustomerServiceProfileHist(SptCustomerServiceProfileVO sptCustomerServiceProfileVO) throws Exception{
        return (int) update("spt.SptSvcApplDAO.updateSptCustomerServiceProfileHist", sptCustomerServiceProfileVO);
    }
    
    /**
     * @Method Name        : selectFintechSvcTermsCleanList
     * @Method description : [핀테크서비스 선택] 서비스의 정보제공동의 클랭징 목록을 조회한다.
     * @param              : SvcApplVO
     * @return             : List<SvcApplVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SvcApplVO> selectFintechSvcTermsCleanList(SptSvcApplVO sptSvcApplVO) throws Exception{
        return (List<SvcApplVO>) list("spt.SptSvcApplDAO.selectFintechSvcTermsCleanList",sptSvcApplVO);
    }
    
    /**
     * @Method Name        : spt.SptSvcApplDAO.updateSptCustomerServiceProfileSvcTermsClean
     * @Method description : [핀테크서비스 선택] 서비스 프로파일에 정보제공 동의 번호를 update한다.
     * @param              : SptCustomerServiceProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptCustomerServiceProfileSvcTermsClean(SptCustomerServiceProfileVO sptCustomerServiceProfileVO) throws Exception{
        return (int) update("spt.SptSvcApplDAO.updateSptCustomerServiceProfileSvcTermsClean", sptCustomerServiceProfileVO);
    }
    
    /**
     * @Method Name        : selectSvcTermConsntList
     * @Method description : [약관동의] 기업 약관동의 대상 목록을 조회한다.
     * @param              : SvcApplVO
     * @return             : List<SptCustomerServiceAccountProfileVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptCustomerCompanyTermsProfileVO> selectSvcCompanyTermsConsntList(SptSvcApplVO sptSvcApplVO) throws Exception{
        return (List<SptCustomerCompanyTermsProfileVO>) list("spt.SptSvcApplDAO.selectSvcCompanyTermsConsntList",sptSvcApplVO);
    }
        
    /**
     * @Method Name        : selectSvcCompanyTermsConsntAgreeCompanyList
     * @Method description : [약관동의] 기업 약관동의한 기업 목록을 조회한다.
     * @param              : SvcApplVO
     * @return             : List<SptCustomerServiceAccountProfileVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptCustomerCompanyTermsProfileVO> selectSvcCompanyTermsConsntAgreeCompanyList(SptSvcApplVO sptSvcApplVO) throws Exception{
        return (List<SptCustomerCompanyTermsProfileVO>) list("spt.SptSvcApplDAO.selectSvcCompanyTermsConsntAgreeCompanyList",sptSvcApplVO);
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
        return (Integer) select("spt.SptSvcApplDAO.checkSptCustomerCompanyTermsProfile",sptCustomerCompanyTermsProfileVO);
    }
    
    /**
     * @Method Name        : insertSptCustomerCompanyTermsProfile
     * @Method description : [약관동의] 기업 약관 정보를 insert.
     * @param              : SptCustomerCompanyTermsProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerCompanyTermsProfile(SptCustomerCompanyTermsProfileVO sptCustomerCompanyTermsProfileVO) throws Exception{
        return (Integer) update("spt.SptSvcApplDAO.insertSptCustomerCompanyTermsProfile", sptCustomerCompanyTermsProfileVO);
    }
    
    /**
     * @Method Name        : updateSptCustomerCompanyTermsProfile
     * @Method description : [약관동의] 기업 약관 정보를 update.
     * @param              : SptCustomerCompanyTermsProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptCustomerCompanyTermsProfile(SptCustomerCompanyTermsProfileVO sptCustomerCompanyTermsProfileVO) throws Exception{
        return (Integer) update("spt.SptSvcApplDAO.updateSptCustomerCompanyTermsProfile", sptCustomerCompanyTermsProfileVO);
    }
    
    /**
     * @Method Name        : insertSptCustomerCompanyTermsProfileHist
     * @Method description : [약관동의] 기업 약관 정보의 hist를 insert.
     * @param              : SptCustomerCompanyTermsProfileVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptCustomerCompanyTermsProfileHist(SptCustomerCompanyTermsProfileVO sptCustomerCompanyTermsProfileVO) throws Exception{
        return (Integer) update("spt.SptSvcApplDAO.insertSptCustomerCompanyTermsProfileHist", sptCustomerCompanyTermsProfileVO);
    }
        
    /**
     * @Method Name        : selectSvcTermConsntList
     * @Method description : [정보제공동의] 정보제공동의 목록을 조회한다.
     * @param              : SvcApplVO
     * @return             : List<SptCustomerServiceAccountProfileVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptCustomerServiceAccountProfileVO> selectSvcTermConsntList(SptSvcApplVO sptSvcApplVO) throws Exception{
        return (List<SptCustomerServiceAccountProfileVO>) list("spt.SptSvcApplDAO.selectSvcTermConsntList",sptSvcApplVO);
    }
    
    /**
     * @Method Name        : selectSvcApplCompltList
     * @Method description : [서비스신청완료] 서비스신청완료 목록을 조회한다.
     * @param              : SvcApplVO
     * @return             : List<SptCustomerServiceAccountProfileVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptCustomerServiceAccountProfileVO> selectSvcApplCompltList(SptSvcApplVO sptSvcApplVO) throws Exception{
        return (List<SptCustomerServiceAccountProfileVO>) list("spt.SptSvcApplDAO.selectSvcApplCompltList",sptSvcApplVO);
    }
    
    /**
	 * @Method Name        : selectMainSvcAppList
	 * @Method description : [메인] 메인의 핀테크 서비스 목록(TOP 5)을 조회한다.
	 * @param              : SvcApplVO
	 * @return             : List<SvcApplVO>
	 * @throws             : Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SvcApplVO> selectMainSvcAppList(SptSvcApplVO sptSvcApplVO) throws Exception{
		return (List<SvcApplVO>) list("spt.SptSvcApplDAO.selectMainSvcAppList",sptSvcApplVO);
	}
	
    /**
     * @Method Name        : selectTermsFileRegNo
     * @Method description : [일반회원서비스약관파일프로파일]정보를 조회한다.
     * @param              : SptCustomerServiceTermsFileProfileVO
     * @return             : List<SptCustomerServiceTermsFileProfileVO>
     * @throws             : Exception
     */
    public String selectTermsFileRegNo(CmmTsaVO paramVO) throws Exception{
        return (String) select("spt.SptSvcApplDAO.selectTermsFileRegNo",paramVO);
    }

    /**
     * @Method Name        : deleteSptCustomerServiceAccountProfile
     * @Method description : 가상계좌 서비스 연동 삭제
     * @param              : sptSvcApplVO
     * @return             : int
     * @throws             : Exception
     */
	public int selectCntSptCustomerServiceAccountProfile(SptSvcApplVO sptSvcApplVO) {
		return (int) select("spt.SptSvcApplDAO.selectCntSptCustomerServiceAccountProfile",sptSvcApplVO);
	}
	
	/**
     * @Method Name        : deleteSptCustomerServiceAccountProfile
     * @Method description : 가상계좌 서비스 연동 삭제
     * @param              : sptSvcApplVO
     * @return             : int
     * @throws             : Exception
     */
	public int deleteSptCustomerServiceAccountProfile(SptSvcApplVO sptSvcApplVO) {
		return (int) delete("spt.SptSvcApplDAO.deleteSptCustomerServiceAccountProfile",sptSvcApplVO);
	}


    /**
     * @Method Name        : deleteSptCustomerServiceAccountProfileHist
     * @Method description : 가상계좌 서비스 연동 삭제
     * @param              : sptSvcApplVO
     * @return             : int
     * @throws             : Exception
     */
	public int deleteSptCustomerServiceAccountProfileHist(SptSvcApplVO sptSvcApplVO) {
		return (int) delete("spt.SptSvcApplDAO.deleteSptCustomerServiceAccountProfileHist",sptSvcApplVO);
	}


    /**
     * @Method Name        : deleteSptCustomerServiceTermsProfile
     * @Method description : 가상계좌 서비스 연동 삭제
     * @param              : sptSvcApplVO
     * @return             : int
     * @throws             : Exception
     */
	public int deleteSptCustomerServiceTermsProfile(SptSvcApplVO sptSvcApplVO) {
		return (int) delete("spt.SptSvcApplDAO.deleteSptCustomerServiceTermsProfile",sptSvcApplVO);
	}


    /**
     * @Method Name        : deleteSptCustomerServiceTermsProfileHist
     * @Method description : 가상계좌 서비스 연동 삭제
     * @param              : sptSvcApplVO
     * @return             : int
     * @throws             : Exception
     */
	public int deleteSptCustomerServiceTermsProfileHist(SptSvcApplVO sptSvcApplVO) {
		return (int) delete("spt.SptSvcApplDAO.deleteSptCustomerServiceTermsProfileHist",sptSvcApplVO);
	}


    /**
     * @Method Name        : deleteSptCustomerServiceTermsPubcompanyProfile
     * @Method description : 가상계좌 서비스 연동 삭제
     * @param              : sptSvcApplVO
     * @return             : int
     * @throws             : Exception
     */
	public int deleteSptCustomerServiceTermsPubcompanyProfile(SptSvcApplVO sptSvcApplVO) {
		return (int) delete("spt.SptSvcApplDAO.deleteSptCustomerServiceTermsPubcompanyProfile",sptSvcApplVO);
	}


    /**
     * @Method Name        : deleteSptCustomerServiceTermsPubcompanyProfileHist
     * @Method description : 가상계좌 서비스 연동 삭제
     * @param              : sptSvcApplVO
     * @return             : int
     * @throws             : Exception
     */
	public int deleteSptCustomerServiceTermsPubcompanyProfileHist(SptSvcApplVO sptSvcApplVO) {
		return (int) delete("spt.SptSvcApplDAO.deleteSptCustomerServiceTermsPubcompanyProfileHist",sptSvcApplVO);
	}


    /**
     * @Method Name        : updateSptCustomerServiceProfile
     * @Method description : 가상계좌 서비스 연동 삭제
     * @param              : sptSvcApplVO
     * @return             : int
     * @throws             : Exception
     */
	public int updateSptCustomerServiceProfile(SptSvcApplVO sptSvcApplVO) {
		return (int) update("spt.SptSvcApplDAO.deleteSptCustomerServiceProfile",sptSvcApplVO);
	}
    
}
