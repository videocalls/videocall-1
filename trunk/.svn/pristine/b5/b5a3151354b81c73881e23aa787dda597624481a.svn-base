package kr.co.koscom.oppfm.commoncode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * CommonCodeRes
 * <p>
 * Created by Yoojin Han on 2017-05-11
 */

@Data
public class CommonCodeRes extends CommonVO {

    private static final long serialVersionUID = -1814904608132469622L;
    
    @JsonIgnore
    private String systemGrpCode;                              // 코드 그룹 ID
    private String systemCode;                                 // 코드 ID
    private String searchResCode;                              // 검색결과 공통코드(코드그룹ID + 코드ID)
//    private String codeName;                                  // 코드명 - 조회조건
    private String codeExtend1;                                // 확장코드 1
    private String codeExtend2;                                // 확장코드 2
    private String codeExtend3;                                // 확장코드 3
    private String codeExtend4;                                // 확장코드 4
    private String codeExtend5;                                // 확장코드 5
    
    private String codeNameKor;                                // 코드 명 한글
    private String codeNameEng;                                // 코드 명 영문
    
}


