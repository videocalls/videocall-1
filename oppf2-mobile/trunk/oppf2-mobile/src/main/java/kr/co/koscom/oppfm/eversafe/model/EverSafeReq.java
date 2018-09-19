package kr.co.koscom.oppfm.eversafe.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * @author unionman
 *
 */
@Data
public class EverSafeReq extends CommonVO{
	
    /**
     * 
     */
    private static final long serialVersionUID = 42561182816423025L;
    
    private String sessionId;
    private String sessionToken;
    private String deviceId;
}
