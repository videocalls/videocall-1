package kr.co.koscom.oppfm.commoncode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * CommonCodeReq
 * <p>
 * Created by Yoojin Han on 2017-05-11
 */

@Data
public class CommonCodeReq extends CommonVO {

    private static final long serialVersionUID = 1339827756435890000L;

    @JsonIgnore
    private String systemGrpCode;                              // 코드 그룹 ID
    private String systemCode;                                 // 코드 ID
    private String codeName;                                   // 코드명 - 조회조건
    private String codeExtend1;                                // 확장코드 1
    private String codeExtend2;                                // 확장코드 2
    private String codeExtend3;                                // 확장코드 3
    private String codeExtend4;                                // 확장코드 4
    private String codeExtend5;                                // 확장코드 5

    /**
     * 기업코드
     */
    private String searchCompanyServiceType;                   // 기업서비스타입 
    private String companyNameEngAlias;                        // 기업이름 Alias 영문

}
