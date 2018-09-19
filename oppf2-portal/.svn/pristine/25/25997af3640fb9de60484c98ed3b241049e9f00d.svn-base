package kr.co.koscom.oppf.spt.direct.company.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.spt.direct.company.dto.CompanyDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CompanyDAO extends ComAbstractDAO {

    public List<CompanyDTO> selectFinanceCompanyList(String contractedComId) throws SQLException {
        HashMap<String, String> param = new HashMap<>();
        param.put("contractedComId", contractedComId);
        return (List<CompanyDTO>) list("direct.CompanyDAO.selectFinanceCompany", param);
    }
    
    public List<CompanyDTO> selectFinanceCompanyList(List<String> comIdList) throws SQLException {
        HashMap<String, List<String>> param = new HashMap<>();
        param.put("comIdList", comIdList);
        return (List<CompanyDTO>) list("direct.CompanyDAO.selectFinanceCompany", param);
    }

    public CompanyDTO selectFinanceCompany(String comId) throws SQLException {
        HashMap<String, String> param = new HashMap<>();
        param.put("comId", comId);
        return (CompanyDTO) select("direct.CompanyDAO.selectFinanceCompany", param);
    }
    

    public CompanyDTO selectCompany(String comId) throws SQLException {
        HashMap<String, String> param = new HashMap<>();
        param.put("comId", comId);
        return (CompanyDTO) select("direct.CompanyDAO.selectCompany", param);
    }

}
