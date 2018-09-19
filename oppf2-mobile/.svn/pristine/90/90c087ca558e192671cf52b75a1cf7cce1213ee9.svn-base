package kr.co.koscom.oppfm.app.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AppAccountRes extends CommonVO {

	private static final long serialVersionUID = 2243294947111482197L;
	/*App에 연결된 가상계좌 Res*/
	private String appId; //app id
	private String apiId;  //api id
	private String apiName;
	private String pubCompanyName;
	private String customerVtaccountNo;
	private String customerVtaccountAlias;
	private String customerVtaccountStatus;
	private String isAvailable;  //해당 App에 연결할 수 있는 가상계좌(금투사) 여부
	private boolean checked;    //해당 App에 연결이 되어있는지 비교하요 셋팅
	private String pubCompanyCodeId;
	private String customerRealAccountNo;
	private String actionType;
	private String accountRegNo;
	private List<VtAccountRes> vtAccountResList;
}
