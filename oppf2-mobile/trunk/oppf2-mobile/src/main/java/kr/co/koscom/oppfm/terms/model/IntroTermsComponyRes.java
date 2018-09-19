package kr.co.koscom.oppfm.terms.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author unionman
 *
 */


@Data
@EqualsAndHashCode(callSuper=false)
public class IntroTermsComponyRes  extends CommonVO{

    /**
     * 
     */
    private static final long serialVersionUID = 1061750351789831692L;
    
    private String termsType;
    private String companyCodeId;
    private String termsKey;
    private String companyName;

    
}
