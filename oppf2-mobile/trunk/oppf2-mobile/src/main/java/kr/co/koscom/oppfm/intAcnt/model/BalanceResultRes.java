package kr.co.koscom.oppfm.intAcnt.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;


/**
 * FileName : BalanceResultRes
 *
 * Description : BalanceResult Response
 *
 * Created by LHT on 2017. 5. 23..
 */
@Data
public class BalanceResultRes extends CommonVO {

    private static final long serialVersionUID = -8918096674437779094L;
    private ResponseBodyRes balanceResponseBody;
    private BalanceListRes balanceList;
    private CommonAccInfoRes accInfo;

}
