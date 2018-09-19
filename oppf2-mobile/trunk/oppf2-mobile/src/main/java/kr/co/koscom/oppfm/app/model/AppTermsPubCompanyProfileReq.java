package kr.co.koscom.oppfm.app.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * AppTermsPubCompanyProfileReq
 * <p>
 * Created by chungyeol.kim on 2017-05-25.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppTermsPubCompanyProfileReq extends CommonVO {
    private static final long serialVersionUID = -8096679038170173037L;

    private String customerRegNo;               // 회원 OP 등록 번호
    private String termsRegNo;                  // 약관 등록 번호
    private String termsPubCompanyRegNo;        // 약관 금투사 등록 번호
    private String pubCompanyCodeId;            // 금투사 기업 코드
    private String pubCompanyName;              // 약관 표기 금투사 기업명
    private String termsChgDate;                // 약관 동의 일시
}
