package kr.co.koscom.oppfm.intAcnt.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * FileName : PortfolioEquityListRes
 *
 * Description : PortfolioEquityList Response
 *
 * Created by LHT on 2017. 5. 23..
 */
@Data
public class PortfolioEquityListRes extends CommonVO {

    private static final long serialVersionUID = -468690807292785369L;
    private String assetType;
    private String isinCode;
    private Double qty;
    private Double earningRate;
    private String isinName;
    private String comName;
    private String vtAccNo;

}
