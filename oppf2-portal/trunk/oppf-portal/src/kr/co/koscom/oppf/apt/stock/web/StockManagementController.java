package kr.co.koscom.oppf.apt.stock.web;

import kr.co.koscom.oppf.apt.stock.service.StockManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * StockManagementController
 * <p>
 * Created by chungyeol.kim on 2017-03-08.
 */
@RestController
public class StockManagementController {

    @Resource(name = "StockManagementService")
    private StockManagementService stockManagementService;

    @RequestMapping(value="/apt/stock",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public void createOrUpdateStockItems() throws Exception {
        stockManagementService.createOrUpdateStockItems();
    }

}
