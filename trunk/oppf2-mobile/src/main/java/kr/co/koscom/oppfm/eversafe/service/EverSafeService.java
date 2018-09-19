package kr.co.koscom.oppfm.eversafe.service;

import kr.co.koscom.oppfm.eversafe.model.EverSafeReq;
import kr.co.koscom.oppfm.eversafe.model.EverSafeRes;


/**
 * @author unionman
 *
 */
public interface EverSafeService {

    /**
     * @Description EverSafe 보안 Token 확인
     * @param everSafeReq
     * @return
     */
    public EverSafeRes checkVerifySession(EverSafeReq everSafeReq);
    
}
