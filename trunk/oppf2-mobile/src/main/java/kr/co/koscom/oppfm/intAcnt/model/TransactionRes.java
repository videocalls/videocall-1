package kr.co.koscom.oppfm.intAcnt.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * @author lee
 * @date 2017-05-25
 */
@Data
public class TransactionRes extends CommonVO {

    private static final long serialVersionUID = 1354487824662116589L;
    private String isinCode;
    private String transDate;
    private String transType;
    private Long changeAmt;
    private Long changeQty;
    private Long qty;
    private String isinName;
    private String comName;
    private String vtAccNo;

}
