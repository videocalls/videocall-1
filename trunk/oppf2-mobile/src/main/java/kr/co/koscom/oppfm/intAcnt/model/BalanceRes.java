package kr.co.koscom.oppfm.intAcnt.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

import java.util.List;


/**
 * FileName : BalanceRes
 *
 * Description : Balance Response
 *
 * Created by LHT on 2017. 5. 23..
 */
@Data
public class BalanceRes extends CommonVO {

    private static final long serialVersionUID = 647471595335900263L;
    private BalanceSummaryRes summary;
    private List<BalanceEquityListRes> equityList;
    private List<BalanceFundListRes> fundList;
    private List<BalanceEtcListRes> etcList;
}
