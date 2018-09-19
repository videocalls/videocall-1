package kr.co.koscom.oppfm.eversafe.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.co.everspin.eversafe.EversafeClient;
import kr.co.koscom.oppfm.eversafe.model.EverSafeReq;
import kr.co.koscom.oppfm.eversafe.model.EverSafeRes;
import kr.co.koscom.oppfm.eversafe.service.EverSafeService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class EverSafeServiceImpl implements EverSafeService{
    
//    private static final String EverSafeApiServerUrl    = "api.dev.eversafe.co.kr";
//    private static final int EverSafeApiServerPort      = 4442;

    @Value("#{config['Globals.everspin.url']}")
    String everSafeApiServerUrl;
    
    @Value("#{config['Globals.everspin.port']}")
    int everSafeApiServerPort;
    
    /**
     * @Description EverSafe 보안 Token 확인
     * @param everSafeReq
     * @return
     */
    public EverSafeRes checkVerifySession(EverSafeReq everSafeReq) {
        EverSafeRes everSafeRes = new EverSafeRes();
        boolean isValidSession = true;
        boolean isAlive = false;
        
        log.debug("*** ever safe ***");
        EversafeClient eversafeClient = null;
        try {
            
            log.debug("EversafeAppSecurityClient START");
            // 초기화 예제
            eversafeClient = EversafeClient.getInstance();
            eversafeClient.init(everSafeApiServerUrl, everSafeApiServerPort);
            eversafeClient.setSchema("http");

            
            try {
                // 서버 Health check
                isAlive = eversafeClient.healthCheck();
                log.debug("EversafeClient.getInstance().healthCheck() :: {}", isAlive );
                
                if(isAlive) {
                    try {
                            isValidSession = eversafeClient.verifySession(everSafeReq.getSessionId()
                                                                        ,everSafeReq.getSessionToken()
                                                                        ,everSafeReq.getDeviceId() );
                        } catch (Exception e) {
                            isAlive = false;
                            log.debug("error {}" ,e.toString());
                        }
                    
                } else { // 비상모드 처리
                    isValidSession = true;
                    log.debug("비상모드로 정상 처리 함");
                    
                }
    
            } catch (IOException e) {
                // 통신 장애로 인한 검증 실패. 비상모드 지원을 위해서는 정상 세션으로 판별한다.
                isValidSession = true;
                log.debug("error 1 {}" ,e.toString());
            }
            
            if (isValidSession) {
                // 토큰이 정상임. 요청을 정상적으로 수행한다.
                log.debug("토큰이 정상임");
            } else {
                log.debug("토큰이 비정상임");
            }
            
            
        } catch (Exception e) {
            log.debug(e.toString());
        } finally {
            // TODO: handle finally clause
            log.debug("EversafeAppSecurityClient SHUTDOWN");
            eversafeClient.shutdown();
        }
        
        everSafeRes.setIsValidSession(isValidSession);
        return everSafeRes;
    }
}
