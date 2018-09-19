package kr.co.koscom.oppfm.login.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * @author unionman
 *
 */
@Data
public class CertPasswordFailRes extends CommonVO{


    /**
     * 
     */
    private static final long serialVersionUID = 2129186069401113048L;
    
    private int passwordFailCnt;
    
    
    
}
