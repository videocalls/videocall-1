package kr.co.koscom.oppf.apt.dataSet.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;
import lombok.Data;

/**
 * Created by LSH on 2017. 2. 23..
 */
@Data
@SuppressWarnings("serial")
public class DataSetCommonVO extends ComDefaultListVO {

    /**
     * Data Set Common F.K
     * */
    private String customerRegNumber;           //회원등록번호
    private String companyCodeId;               //기업코드 ID
    private String customerRealaccountNumber;   //실제 계좌번호
    private String customerVtaccountNumber;     //가상계좌번호
    private String apiAccountDivision;          //tab구분


    private String btnDivision;                 //버튼모양 구분
    private int rowindex;                       //table row 구분
    private String tableName;                   //table name 구분

}
