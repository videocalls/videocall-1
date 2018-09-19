package kr.co.koscom.oppfm.app.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * VtAccountRes
 * <p>
 * Created by chungyeol.kim on 2017-05-31.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VtAccountRes extends CommonVO {
    private static final long serialVersionUID = -2910213111376657008L;
    private String customerVtaccountNo;
    private String customerVtaccountAlias;
    private String customerVtaccountStatus;
    private String isAvailable;  //해당 App에 연결할 수 있는 가상계좌(금투사) 여부
    private boolean checked;    //해당 App에 연결이 되어있는지 비교하요 셋팅
    private String customerRealAccountNo;
    private String actionType;
    private String accountRegNo;
}
