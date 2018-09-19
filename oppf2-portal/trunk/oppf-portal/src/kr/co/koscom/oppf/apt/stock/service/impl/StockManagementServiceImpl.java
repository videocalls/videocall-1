package kr.co.koscom.oppf.apt.stock.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.koscom.oppf.apt.stock.service.StockItemGroupVO;
import kr.co.koscom.oppf.apt.stock.service.StockManagementService;
import kr.co.koscom.oppf.cmm.IntegratedAccount.service.CmmSIFInternalService;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * StockManagementServiceImpl
 * <p>
 * Created by chungyeol.kim on 2017-03-08.
 */
@Slf4j
@Service("StockManagementService")
public class StockManagementServiceImpl implements StockManagementService {
    @Resource(name = "CmmSIFInternalService")
    private CmmSIFInternalService cmmSIFInternalService;

    @Autowired
    private StockManagementDAO stockManagementDAO;

    @Override
    @Transactional
    public void createOrUpdateStockItems() throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String targetUrlListString = OppfProperties.getProperty("Globals.stock.list");
        String[] urls = targetUrlListString.split("\\|");

        for(String url : urls) {
            String[] urlInfo = url.split(";");
            ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(urlInfo[0], httpHeaders, HttpMethod.GET, "");
            log.debug("stock url : {} ", urlInfo[0]);
            log.debug("stock market code : {} ", urlInfo[1]);
            if(HttpStatus.OK.equals(responseEntity.getStatusCode())) {
                // 모르는 property 에 대해 무시하고 넘어간다.
                ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                StockItemGroupVO stockItemGroupVO = objectMapper.readValue(responseEntity.getBody(), StockItemGroupVO.class);
                stockManagementDAO.insertUpdateStockItems(stockItemGroupVO, urlInfo[1]);
            }
        }

    }
}
