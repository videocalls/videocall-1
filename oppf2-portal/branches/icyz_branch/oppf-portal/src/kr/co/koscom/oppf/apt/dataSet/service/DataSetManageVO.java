package kr.co.koscom.oppf.apt.dataSet.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;
import lombok.Data;

import java.util.List;

/**
 * Created by LSH on 2017. 2. 21..
 */
@Data
@SuppressWarnings("serial")
public class DataSetManageVO  extends ComDefaultListVO {

    private String customerRegNumber;               //회원번호
    private String customerNameKor;                 //이름
    private String customerId;                      //ID
    private String customerRegStatus;               //계정상태
    private String companyCodeId;                   //서비스 제공자
    private String customerRealaccountType;         //계좌유형
    private String customerVtaccountNumber;         //가상계좌번호
    private String dataSetYn;                       //데이터 유무
    private String customerRealaccountNumber;       //실제 계좌번호
    private String customerVtaccountAlias;          //계좌별칭
    private String customerDatasetLockYn;


    /* 공통코드 이름 */
    private String customerRegStatusName;           //계정상태이름
    private String companyNameKorAlias;             //기업 이름
    private String customerRealaccountTypeName;     //계좌유형 이름


    /**
     * DataSet type(DataSet Table Select)
     * */
    private String datasetType;


    /**
     * 검색조건
     * */
    private List<String> searchPubcompanyCodeId;
    private String searchPubcompanyCodeIdAllYn;

    private List<String> searchDataSetYn;
    private String searchDataSetYnAllYn;

    private String searchDateType;
    private String searchDateFrom;
    private String searchDateTo;


    /**
     * copy
     * */
    private String copyCustomerRegNumber;               //회원번호
    private String copyCustomerId;                      //ID
    private String copyCompanyCodeId;                   //서비스 제공자
    private String copyCustomerVtaccountNumber;         //가상계좌번호
    private String copyCustomerRealaccountNumber;       //실제 계좌번호


    /*상세 화면 시 api(selectBox) 구분을 위한 param*/
    private String apiAccountDivision;


}
