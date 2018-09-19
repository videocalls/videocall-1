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
public class TermsPubCompanyVO extends ComDefaultListVO {
    private String customerRegNo;               // 회원 OP 등록 번호
    private String termsRegNo;                  // 약관 등록 번호
    private String termsPubCompanyRegNo;        // 약관 금투사 등록 번호
    private String pubCompanyCodeId;            // 금투사 기업 코드
    private String pubCompanyName;              // 약관 표기 금투사 기업명
    private String termsChgDate;                // 약관 동의 일시
}
