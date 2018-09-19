package kr.co.koscom.oppf.spt.usr.svcAppl.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * AppVO
 * <p>
 * Created by chungyeol.kim on 2017-06-15.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppAccountVO extends ComDefaultListVO {
    private String appId; //app id
    private String apiId;  //api id
    private String apiName;
    private String pubCompanyName;
    private String pubCompanyNameEng;
    private String customerVtaccountNo;
    private String customerVtaccountAlias;
    private String customerVtaccountStatus;
    private String isAvailable;  //해당 App에 연결할 수 있는 가상계좌(금투사) 여부
    private boolean checked;    //해당 App에 연결이 되어있는지 비교하요 셋팅
    private String pubCompanyCodeId;
    private String customerRealAccountNo;
    private String customerRealAccountNoEncryption;
    private String customerRealAccountType;
    private String customerRealAccountTypeName;
    private String customerRealAccountTypeNameEng;
    private String actionType;
    private String accountRegNo;
    private String serviceRegNo;
    private String customerRegNo;
}
