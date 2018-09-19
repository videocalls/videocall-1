package kr.co.koscom.oppfm.cmm.eversafe;

import java.io.IOException;

import kr.co.everspin.eversafe.EversafeClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EversafeAppSecurityClient {

//    private static EversafeAppSecurityClient instance = new EversafeAppSecurityClient();

    private static final String EverSafeApiServerUrl    = "api.dev.eversafe.co.kr";
    private static final int EverSafeApiServerPort      = 4442;

//    public static EversafeAppSecurityClient getInstance() {
//        return instance;
//    }

    public static void main(String args[]) {
        EversafeClient eversafeClient = null;
        try {
            
            log.debug("EversafeAppSecurityClient START");
            // 초기화 예제
            eversafeClient = EversafeClient.getInstance();
            eversafeClient.init(EverSafeApiServerUrl, EverSafeApiServerPort);
            eversafeClient.setSchema("http");
//            EversafeClient.getInstance().setMaxConcurrentConnection(8);
            // 최대 동시 연결 개수, 전체 쓰레드 갯수만큼 설정하도록 합니다.
//            eversafeClient.setConnectTimeout(5000); // 접속시 적용될 timeout
//            eversafeClient.setSocketTimeout(5000); // 접속이후 적용될 timeout
            
            boolean isValidSession = false;
            
            try {
                
                log.debug("EversafeClient.getInstance().healthCheck() :: {}", eversafeClient.healthCheck());
    
                isValidSession = eversafeClient.verifySession("sessionId", "sessionToken", "deviceId");
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
            
//            JSONObject deviceInfo = JWETokenVerifier.verifyJWEToken("sessionId", "sessionToken");
//            
//                    if (deviceInfo != null ||
//                    ((String)deviceInfo.get("deviceId")).equals("deviceId")) {
//                    // 토큰이 정상임. 요청을 정상적으로 수행한다.
//                        log.debug("토큰이 정상임. 요청을 정상적으로 수행한다.");
//                    } else {
//                    // 확인할 수 없는 토큰임. 요청을 reject 한다.
//                        log.debug("확인할 수 없는 토큰임. 요청을 reject 한다");
//                    }            
            
            
        } catch (Exception e) {
            
            log.debug("error 2 {}" ,e.toString());

        } finally {
            // TODO: handle finally clause
            // 해제 예제
            log.debug("EversafeAppSecurityClient SHUTDOWN");
            eversafeClient.shutdown();
        }
    }
    
}
