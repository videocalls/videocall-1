package kr.co.koscom.oppfm.login.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * @author unionman
 *
 */
@Data
public class CertPasswordFailReq extends CommonVO{



    /**
     * 
     */
    private static final long serialVersionUID = 8134845926688858060L;
    

    //DN 값(공인인증서 로그인)
    private String customerVerifyDn;
    // 패스워드 실패여부
    private boolean isPasswordFail = false;
    // 패스워드 실패횟수
    private int currentTryCount;
    
    
    
}
