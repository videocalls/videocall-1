package kr.co.koscom.oppf.spt.direct.company.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.koscom.oppf.spt.direct.common.exception.ApiException;
import kr.co.koscom.oppf.spt.direct.common.exception.ApiExceptionCode;
import kr.co.koscom.oppf.spt.direct.common.model.RequestResult;
import kr.co.koscom.oppf.spt.direct.common.model.Resp;
import kr.co.koscom.oppf.spt.direct.company.dao.CompanyDAO;
import kr.co.koscom.oppf.spt.direct.company.dto.CompanyDTO;
import kr.co.koscom.oppf.spt.direct.company.model.Company;
import kr.co.koscom.oppf.spt.direct.company.model.CompanyResponse;
import kr.co.koscom.oppf.spt.direct.company.model.CompanyResponse.FinanceListResponseBody;
import kr.co.koscom.oppf.spt.direct.company.service.CompanyService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDAO companyDao;

    @Override
    public CompanyResponse selectFinanceCompanyList(String comId) {

        try {
            List<CompanyDTO> companyDtoList = companyDao.selectFinanceCompanyList(comId);

            if (companyDtoList == null) {
                throw new ApiException(ApiExceptionCode.NOT_FOUND_COMPANY);
            }

            CompanyResponse companyResponse = new CompanyResponse();
            
            FinanceListResponseBody financeListResponseBody = new FinanceListResponseBody();
            RequestResult requestResult = new RequestResult();
            requestResult.setCount(companyDtoList.size());
            requestResult.setTotalCnt(companyDtoList.size());
            financeListResponseBody.setRequestResult(requestResult);

            companyResponse.setFinanceListResponseBody(financeListResponseBody);
            
            List<Company> companyList = new ArrayList<>();
            for(CompanyDTO item : companyDtoList) {
                Company company = new Company();
                company.setComId(item.getCompanyCodeId());
                company.setComAlias(item.getCompanyNameEngAlias());
                company.setComName(item.getCompanyNameKorAlias());
                company.setAgreementVer(item.getCompanyTermsContentVer());
                company.setApiEndpoint("/v1/" + company.getComAlias() + "/direct");
                
                companyList.add(company);
            }
             
            companyResponse.setFinanceList(companyList);
            
            Resp resp = new Resp();
            resp.setRespCode(Integer.toString(ApiExceptionCode.SUCCESS.getCode()));
            resp.setRespMsg("success");
            
            companyResponse.setResp(resp);
            
            return companyResponse;
        } catch (ApiException aex) {
            throw aex;
        } catch (Exception ex) {
            throw new ApiException(ApiExceptionCode.UNKNOWN_ERROR, ex);
        }
    }
}
