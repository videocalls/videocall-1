package kr.co.koscom.oppfm.intAcnt.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;


/**
 * FileName : TransactionResultRes
 *
 * Description : TransactionResult Response
 *
 * Created by LHT on 2017. 5. 23..
 */
@Data
public class TransactionResultRes extends CommonVO {

    private static final long serialVersionUID = 3100694743823565041L;
    private TransactionListRes transList;
    private CommonAccInfoRes accInfo;

}
