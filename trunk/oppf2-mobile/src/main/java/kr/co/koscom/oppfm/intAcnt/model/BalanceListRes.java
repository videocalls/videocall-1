package kr.co.koscom.oppfm.intAcnt.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;


/**
 * FileName : BalanceListRes
 *
 * Description : BalanceListRes Response
 *
 * Created by LHT on 2017. 5. 23..
 */
@Data
public class BalanceListRes extends CommonVO {

    private static final long serialVersionUID = -3699833930552898622L;
    private BalanceRes balance;

}
