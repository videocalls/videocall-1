package kr.co.koscom.oppfm.terms.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

import java.util.List;

/**
 * ClassName : MobileTerms
 * *
 * Description :
 * <p>
 * Created by LSH on 2017. 5. 18..
 */
@Data
public class MobileTerms extends CommonVO{

	private static final long serialVersionUID = 869877636293577244L;

    private String mobileTermsType;							/* 약관 종류 */
    private String mobileTermsContent;						/* 약관 내용 */
    private String mobileTermsSubject;						/* 약관 제목 */


}
