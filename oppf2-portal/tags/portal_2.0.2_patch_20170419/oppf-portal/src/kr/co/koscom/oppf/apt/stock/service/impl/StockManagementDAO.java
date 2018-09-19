package kr.co.koscom.oppf.apt.stock.service.impl;

import kr.co.koscom.oppf.apt.stock.service.StockItemGroupVO;
import kr.co.koscom.oppf.apt.stock.service.StockItemVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * StockManagementDAO
 * <p>
 * Created by chungyeol.kim on 2017-03-08.
 */
@Repository("StockManagementDAO")
public class StockManagementDAO extends ComAbstractDAO {
    public int insertUpdateStockItems(StockItemGroupVO stockItemGroupVO, String marketCd) {
        Map<String,Object> param = new HashMap<>();
        param.put("isuLists", stockItemGroupVO.getIsuLists());
        param.put("marketCd", marketCd);
        return update("apt.StockManagementDAO.insertUpdateStockItem", param);
    }

    public List<StockItemVO> selectStockItemList() {
        return (List<StockItemVO>) list("apt.StockManagementDAO.selectStockItemList");
    }
}
