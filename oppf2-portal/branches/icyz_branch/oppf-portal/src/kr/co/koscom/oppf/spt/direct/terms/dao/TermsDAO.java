package kr.co.koscom.oppf.spt.direct.terms.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.spt.direct.terms.dto.TermsDTO;
import kr.co.koscom.oppf.spt.direct.terms.dto.TermsInfoDTO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.TermsPubCompanyVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TermsDAO extends ComAbstractDAO {

    public TermsDTO selectTerms(String termsType, String comId) throws SQLException {
        HashMap<String, String> param = new HashMap<>();
        param.put("termsType", termsType);
        param.put("comId", comId);
        return (TermsDTO) select("direct.TermsDAO.selectTerms", param);
    }

    public TermsDTO selectTerms(String termsType) throws SQLException {
        return selectTerms(termsType, "");
    }

    public TermsInfoDTO selectLastestCustomerServiceTermsInfo(String customerRegNo, String subcompanyCodeId) throws SQLException {
        HashMap<String, String> param = new HashMap<>();
        param.put("customerRegNo", customerRegNo);
        param.put("subcompanyCodeId", subcompanyCodeId);
        return (TermsInfoDTO) select("direct.TermsDAO.selectLastestCustomerServiceTermsInfo", param);
    }
    

    public List<TermsPubCompanyVO> selectCustomerServiceTermsPubcompanyInfo(String customerRegNo, String termsRegNo) throws SQLException {
        HashMap<String, String> param = new HashMap<>();
        param.put("customerRegNo", customerRegNo);
        param.put("termsRegNo", termsRegNo);
        return (List<TermsPubCompanyVO>) list("direct.TermsDAO.selectLastestCustomerServiceTermsPubcompanyInfo", param);
    }


}
