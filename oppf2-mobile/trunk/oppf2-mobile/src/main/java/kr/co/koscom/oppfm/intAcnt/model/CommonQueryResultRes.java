package kr.co.koscom.oppfm.intAcnt.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * @author lee
 * @date 2017-06-28
 */
@Data
public class CommonQueryResultRes extends CommonVO {

    private static final long serialVersionUID = 5840962912654866090L;
    private int totalCnt;
    private int count;
    private String page;

}
