package kr.co.koscom.oppfm.intAcnt.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * @author lee
 * @date 2017-06-28
 */
@Data
public class ResponseBodyRes extends CommonVO {

    private static final long serialVersionUID = 1479589552691621505L;
    private CommonQueryTypeRes queryType;
    private CommonQueryResultRes queryResult;

}
