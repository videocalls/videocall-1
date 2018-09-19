package kr.co.koscom.oppf.spt.usr.svcAppl.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * AppVO
 * <p>
 * Created by chungyeol.kim on 2017-06-15.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppVO extends ComDefaultListVO {
    private String isAvailable;  //신청가능여부
    private String appStatus; //app상태(공통코드.G022)-Disable:001, Enable:002, Disabled by Admin:003, Rejected:004
    private String appId; //app id
    private String appName; //app 이름
    private String appCategory; //app구분(공통코드.G025)-시세:001, 뉴스:002, 관심종목:003, 거래종목:004, 잔고현황:005, 포트폴리오:006
    private String appCategoryName; //app구분코드 이름 ->code_name_kor As appCategoryName
    private String appDescription; //app 설명
    private String companyCodeId; //기업코드
    private String companyName;  //기업이름
    private String serviceRegNo; //서비스 등록 번호
    private String apiId;  //api id
    private String apiName;
    private String reSyncYn;  //재동의 필요 여부  "Y","N"
    private String termsStartDate;
    private String termsEndDate;
    private String appImageUrl;
    private String searchAppCategory;
    private String searchAppCreatedYn;
    private String customerRegNo;
    private String customerId;
    private int sort;
    private String termsRegNo;
    private String companyProfileRegNo;
    private String type;
    private List<String> checkedPubCompanyList;
    private String checkedPubCompany;
    private String subCompanyCodeId;
    private String subCompanyName;
    private String pubCompanyCodeId;
    private String pubCompanyName;
    private List<AppAccountVO> appAccountList;
}
