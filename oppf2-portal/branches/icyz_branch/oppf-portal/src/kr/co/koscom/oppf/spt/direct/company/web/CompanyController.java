package kr.co.koscom.oppf.spt.direct.company.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.koscom.oppf.spt.direct.company.model.CompanyResponse;
import kr.co.koscom.oppf.spt.direct.company.service.CompanyService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(
            path = "/direct/company/finance/search",
            method = RequestMethod.GET,
            produces = { "application/json;charset=UTF-8" },
            consumes = { "application/json;charset=UTF-8" })
    public CompanyResponse getFinanceCompanyList(@RequestHeader(value = "x-api-comId") String comId) {
        log.info("============== /direct/company/finance/search =================");
        return companyService.selectFinanceCompanyList(comId);
    }

}
