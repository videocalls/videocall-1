package kr.co.koscom.oppf.spt.usr.svcAppl.service.impl;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.AppAccountVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.AppVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.TermsPubCompanyVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.TermsVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AppDAO
 * <p>
 * Created by chungyeol.kim on 2017-06-15.
 */
@Repository("AppDAO")
public class AppDAO extends ComAbstractDAO {

    @SuppressWarnings("unchecked")
    public List<AppVO> selectAppList(AppVO appVO) {
        return (List<AppVO>) list("usr.AppDAO.selectAppList", appVO);
    }

    public int selectAppListTotalCount(AppVO appVO) {
        return (Integer) select("usr.AppDAO.selectAppListTotalCount", appVO);
    }

    public AppVO selectAppDetail(AppVO appVO) {
        return (AppVO) select("usr.AppDAO.selectAppDetail", appVO);
    }

    @SuppressWarnings("unchecked")
    public List<AppAccountVO> selectAppAccountList(AppVO appVO) {
        return (List<AppAccountVO>) list("usr.AppDAO.selectAppAccountList", appVO);
    }

    @SuppressWarnings("unchecked")
    public List<AppAccountVO> selectAccountList(AppVO appVO) {
        return (List<AppAccountVO>) list("usr.AppDAO.selectAccountList", appVO);
    }

    @SuppressWarnings("unchecked")
    public AppVO selectAppDeleteTargetTerms(AppVO appVO) {
        return (AppVO) select("usr.AppDAO.selectAppDeleteTargetTerms", appVO);
    }

    @SuppressWarnings("unchecked")
    public List<String> selectAppCountForTerms(AppVO appVO) {
        return (List<String>) list("usr.AppDAO.selectAppCountForTerms", appVO);
    }

    public int deleteServiceProfile(AppVO appVO) {
        return (int) update("usr.AppDAO.deleteServiceProfile", appVO);
    }

    public int insertServiceProfileHist(AppVO appVO) {
        return (int) update("usr.AppDAO.insertServiceProfileHist", appVO);
    }

    public int deleteServiceTermsProfile(AppVO appVO) {
        return (int) update("usr.AppDAO.deleteServiceTermsProfile", appVO);
    }

    public int insertServiceTermsProfileHist(AppVO appVO) {
        return (int) update("usr.AppDAO.insertServiceTermsProfileHist", appVO);
    }

    public int checkedCommonTerms(AppVO appVO) {
        return (Integer) select("usr.AppDAO.checkedCommonTerms", appVO);
    }

    public TermsVO checkedReSyncCommonTerms(AppVO appVO) {
        return (TermsVO) select("usr.AppDAO.checkedReSyncCommonTerms", appVO);
    }

    public int checkedCustomerVerify(AppVO appVO) {
        return (Integer) select("usr.AppDAO.checkedCustomerVerify", appVO);
    }

    public TermsVO selectBaseCommonTerms(AppVO appVO) {
        return (TermsVO) select("usr.AppDAO.selectBaseCommonTerms", appVO);
    }

    @SuppressWarnings("unchecked")
    public List<AppAccountVO> selectCommonTermsPubCompanyList() {
        return (List<AppAccountVO>) list("usr.AppDAO.selectCommonTermsPubCompanyList");
    }

    public String selectMaxCommonTermsRegNo(TermsVO termsVO) {
        return (String) select("usr.AppDAO.selectMaxCommonTermsRegNo", termsVO);
    }

    public String selectMaxCommonTermsPubCompanyRegNo(TermsVO termsVO) {
        return (String) select("usr.AppDAO.selectMaxCommonTermsPubCompanyRegNo", termsVO);
    }

    public int insertCustomerCommonTermsProfile(TermsVO termsVO) {
        return (int) update("usr.AppDAO.insertCustomerCommonTermsProfile", termsVO);
    }

    public int insertCustomerCommonTermsProfileHist(TermsVO termsVO) {
        return (int) update("usr.AppDAO.insertCustomerCommonTermsProfileHist", termsVO);
    }

    public int insertCustomerCommonTermsPubCompanyProfile(TermsPubCompanyVO termsPubCompanyVO) {
        return (int) update("usr.AppDAO.insertCustomerCommonTermsPubCompanyProfile", termsPubCompanyVO);
    }

    public int insertCustomerCommonTermsPubCompanyProfileHist(TermsPubCompanyVO termsPubCompanyVO) {
        return (int) update("usr.AppDAO.insertCustomerCommonTermsPubCompanyProfileHist", termsPubCompanyVO);
    }

    public int insertCustomerVerifyProfile(TermsVO termsVO) {
        return (int) update("usr.AppDAO.insertCustomerVerifyProfile", termsVO);
    }

    public int insertCustomerVerifyProfileHist(TermsVO termsVO) {
        return (int) update("usr.AppDAO.insertCustomerVerifyProfileHist", termsVO);
    }

    public int deleteCustomerCommonTermsProfile(TermsVO termsVO) {
        return (int) update("usr.AppDAO.deleteCustomerCommonTermsProfile", termsVO);
    }

    public int deleteCustomerCommonTermsPubCompanyProfile(TermsVO termsVO) {
        return (int) update("usr.AppDAO.deleteCustomerCommonTermsPubCompanyProfile", termsVO);
    }

    public TermsVO selectCommonTermsInfo(TermsVO termsVO) {
        return (TermsVO) select("usr.AppDAO.selectCommonTermsInfo", termsVO);
    }

    public String checkedAppTerms(AppVO appVO) {
        return (String) select("usr.AppDAO.checkedAppTerms", appVO);
    }

    public AppVO selectSubCompanyInfo(AppVO appVO) {
        return (AppVO) select("usr.AppDAO.selectSubCompanyInfo", appVO);
    }

    @SuppressWarnings("unchecked")
    public List<String> selectAppTermsPubCompanyList(AppVO appVO) {
        return (List<String>) list("usr.AppDAO.selectAppTermsPubCompanyList", appVO);
    }

    @SuppressWarnings("unchecked")
    public List<TermsPubCompanyVO> selectAppTermsPubCompanyInfoList(AppVO appVO) {
        return (List<TermsPubCompanyVO>) list("usr.AppDAO.selectAppTermsPubCompanyInfoList", appVO);
    }

    public int checkCreatedCustomerServiceTermsPubCompanyProfile(TermsPubCompanyVO termsPubCompanyVO) {
        return (Integer) select("usr.AppDAO.checkCreatedCustomerServiceTermsPubCompanyProfile", termsPubCompanyVO);
    }

    public String selectMaxServiceRegNo(TermsVO termsVO) {
        return (String) select("usr.AppDAO.selectMaxServiceRegNo", termsVO);
    }

    public String selectMaxServiceAccountRegNo(TermsVO termsVO) {
        return (String) select("usr.AppDAO.selectMaxServiceAccountRegNo", termsVO);
    }

    public String selectMaxTermsRegNo(TermsVO termsVO) {
        return (String) select("usr.AppDAO.selectMaxTermsRegNo", termsVO);
    }

    public String selectMaxTermsPubCompanyRegNo(TermsVO termsVO) {
        return (String) select("usr.AppDAO.selectMaxTermsPubCompanyRegNo", termsVO);
    }

    public String selectMaxTermsArsRegNo(TermsVO termsVO) {
        return (String) select("usr.AppDAO.selectMaxTermsArsRegNo", termsVO);
    }

    public int insertCustomerServiceProfile(TermsVO termsVO) {
        return (int) update("usr.AppDAO.insertCustomerServiceProfile", termsVO);
    }

    public int insertCustomerServiceProfileHist(TermsVO termsVO) {
        return (int) update("usr.AppDAO.insertCustomerServiceProfileHist", termsVO);
    }

    public int insertCustomerServiceAccountProfile(AppAccountVO appAccountVO) {
        return (int) update("usr.AppDAO.insertCustomerServiceAccountProfile", appAccountVO);
    }

    public int insertCustomerServiceAccountProfileHist(AppAccountVO appAccountVO) {
        int resultcnt = update("usr.AppDAO.insertCustomerServiceAccountProfileHist", appAccountVO);
        return resultcnt;
    }

    public int insertCustomerServiceTermsProfile(TermsVO termsVO) {
        return (int) update("usr.AppDAO.insertCustomerServiceTermsProfile", termsVO);
    }

    public int insertCustomerServiceTermsProfileHist(TermsVO termsVO) {
        return (int) update("usr.AppDAO.insertCustomerServiceTermsProfileHist", termsVO);
    }

    public int insertCustomerServiceTermsPubCompanyProfile(TermsPubCompanyVO termsPubCompanyVO) {
        return (int) update("usr.AppDAO.insertCustomerServiceTermsPubCompanyProfile", termsPubCompanyVO);
    }

    public int insertCustomerServiceTermsPubCompanyProfileHist(TermsPubCompanyVO termsPubCompanyVO) {
        return (int) update("usr.AppDAO.insertCustomerServiceTermsPubCompanyProfileHist", termsPubCompanyVO);
    }

    public int insertCustomerServiceArsProfile(TermsVO termsVO) {
        return (int) update("usr.AppDAO.insertCustomerServiceArsProfile", termsVO);
    }

    public int insertCustomerServiceArsProfileHist(TermsVO termsVO) {
        return (int) update("usr.AppDAO.insertCustomerServiceArsProfileHist", termsVO);
    }

    public int updateCustomerServiceProfile(TermsVO termsVO) {
        return (int) update("usr.AppDAO.updateCustomerServiceProfile", termsVO);
    }

    public int deleteCustomerServiceAccountProfile(AppAccountVO appAccountVO) {
        return (int) update("usr.AppDAO.deleteCustomerServiceAccountProfile", appAccountVO);
    }

    public int deleteCustomerServiceAccountProfileAll(AppAccountVO appAccountVO) {
        int resultcnt = update("usr.AppDAO.deleteCustomerServiceAccountProfileAll", appAccountVO);
        resultcnt    += update("usr.AppDAO.insertCustomerServiceAccountProfileHistAll", appAccountVO);
        return resultcnt;
    }
    
    public TermsVO selectAppTermsInfo(AppVO appVO) {
        return (TermsVO) select("usr.AppDAO.selectAppTermsInfo",appVO);
    }

    public String selectEncCustomerPhoneNumber(TermsVO termsVO) {
        return (String) select("usr.AppDAO.selectEncCustomerPhoneNumber", termsVO);
    }

    public TermsVO selectAppTermsInfoForDiscard(AppVO appVO) {
        return (TermsVO) select("usr.AppDAO.selectAppTermsInfoForDiscard",appVO);
    }

    @SuppressWarnings("unchecked")
    public List<AppAccountVO> selectBeforeCommonTermsPubCompanyList(TermsVO termsVO) {
        return (List<AppAccountVO>) list("usr.AppDAO.selectBeforeCommonTermsPubCompanyList", termsVO);
    }

    public String selectOldServiceRegNo(TermsVO termsVO) {
        return (String) select("usr.AppDAO.selectOldServiceRegNo", termsVO);
    }
}
