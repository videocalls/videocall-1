package kr.co.koscom.oppfm.intAcnt.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * FileName : PortfolioEtcListRes
 *
 * Description : PortfolioEtcList Response
 *
 * Created by LHT on 2017. 5. 23..
 */
@Data
public class PortfolioEtcListRes extends CommonVO {

    private static final long serialVersionUID = 4827529908813257497L;
    private String assetType;
    private String assetName;
    private Double qty;
    private Double earningRate;
    private String isinCode;
    private String isinName;
    private String comName;
    private String vtAccNo;

}
