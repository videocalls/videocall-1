package kr.co.koscom.oppf.apt.app.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;
import lombok.Data;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AppManageVO.java
* @Comment  : app 관리를 위한 위한 VO
* @author   : 신동진
* @since    : 2016.05.30
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.30  신동진        최초생성
*
*/
@Data
@SuppressWarnings("serial")
public class AppManageVO extends ComDefaultListVO{
	/**
	 * com_app_view
	 */
	private String appId;
	private String appName;
	private String appKey;
	private String appStatus;
	private String operatorRegId;
	private String operatorRegName;
	private String companyCodeId;
	
	private String appStatusName;
	private String companyNameKor;	
	
	/**
	 * com_app_info
	 */
	private String appCategory;
	private String appDescription;
	private String appContractCode;
	private String appContractDate;
	private String appTermsStartDate;
	private String appTermsExpireDate;
	private byte[] appIcon;
	private String appDlUrl;
	private String exposureYn;
    private int exposureOrder;
	
	private String appCategoryName;
	private String appContractCodeName;
	private String createIdName;
	
	private String isAppIcon;
	
	private String apiId;
	private String apiName;
	private String apiContractCode;
	private String apiContractCodeName;
	private String apiTermsStartDate;
	private String apiTermsExpireDate;


	/**
	 * ca_dpt_lrsapplication_view
	 * */
	private String keySecret;
	private String oauthCallbackUrl;
	private String oauthScope;
	private String oauthType;


	/**
	 * 검색조건
	 */
	//excel type = excel : 엑셀, down: 다운
	private String excelType;
		
	//app 구분
	private List<String> searchAppCategory;
	private String searchAppCategoryAllYn;
	
	//app 상태
	private List<String> searchAppStatus;
	private String searchAppStatusAllYn;
	
	//app 계약여부
	private List<String> searchAppContractCode;
	private String searchAppContractCodeAllYn;	
	
	//서비스 제공자
	private List<String> searchCompanyCodeId;
	private String searchCompanyCodeIdAllYn;
	
	//활성화 여부
	private List<String> searchExposureYn;
	private String searchExposureYnAllYn;
	
	//등록일
	private String searchDateFrom;
	private String searchDateTo;
	

}