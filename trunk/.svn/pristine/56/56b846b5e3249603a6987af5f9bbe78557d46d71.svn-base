package kr.co.koscom.oppfm.terms.model;

import org.hibernate.validator.constraints.NotEmpty;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author unionman
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class IntroTermsReq extends CommonVO{

    public static interface ValidationIntroTermsReq {}
    /**
     * 
     */
    private static final long serialVersionUID = 88778395639206737L;
    
    @NotEmpty(groups = { ValidationIntroTermsReq.class}, message = "약관 구분 누락")
    private String termsType;
    @NotEmpty(groups = { ValidationIntroTermsReq.class}, message = "기업 코드 누락")
    private String companyCodeId;
    @NotEmpty(groups = { ValidationIntroTermsReq.class}, message = "약관 번호 누락")
    private String termsKey;

}
