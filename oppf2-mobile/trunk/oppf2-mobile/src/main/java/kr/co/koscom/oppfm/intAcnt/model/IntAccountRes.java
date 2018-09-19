package kr.co.koscom.oppfm.intAcnt.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * FileName : IntAccountRes
 *
 * Description : IntAccount Response
 *
 * Created by LHT on 2017. 5. 23..
 */
@Data
public class IntAccountRes extends CommonVO {

    private static final long serialVersionUID = -6746280819742931568L;
    private String comId;
    private String vtAccNo;
    private String vtAccAlias;
    private String endpoint;
    private String companyEngName;

}
