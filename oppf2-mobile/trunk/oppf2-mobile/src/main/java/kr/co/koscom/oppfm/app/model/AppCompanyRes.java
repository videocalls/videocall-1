package kr.co.koscom.oppfm.app.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

@Data
public class AppCompanyRes extends CommonVO{
	private static final long serialVersionUID = 5791712901732009899L;
	/*해당 App 연계 서비스 response*/
	private String appId; //app id
	private String companyCodeId; //기업코드
	private String companyName;  //기업이름
	private String apiId;  //api id
	private String apiName;
}
