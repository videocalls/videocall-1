package kr.co.koscom.oppfm.intAcnt.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * FileName : PortfolioFundListRes
 *
 * Description : PortfolioFundList Response
 *
 * Created by LHT on 2017. 5. 23..
 */
@Data
public class PortfolioFundListRes extends CommonVO {

    private static final long serialVersionUID = 6552024236525916056L;
    private String fundCode;
    private String fundName;
    private Double qty;
    private Double earningRate;
    private String maturity;
    private String isinName;
    private String comName;
    private String vtAccNo;

}
