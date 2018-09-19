package kr.co.koscom.oppfm.intAcnt.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

import java.util.List;


/**
 * FileName : InterestGroupListRes
 *
 * Description : InterestGroupList Response
 *
 * Created by LHT on 2017. 5. 23..
 */
@Data
public class InterestGroupListRes extends CommonVO {

    private static final long serialVersionUID = 5381254254519317205L;
    private List<InterestGroupRes> group;

}
