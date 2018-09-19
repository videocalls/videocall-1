package kr.co.koscom.oppf.cmm.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import kr.co.koscom.oppf.cmm.service.OppfProperties;

/**
 * @Project : 자본시장 공동 핀테크 오픈플랫폼 구축
 * @FileName : OppfSessionUtil.java
 * @Comment : session 관련 util
 * @author : 신동진
 * @since : 2016.06.23
 * @version : 1.0
 *
 *          << 개정이력(Modification Information) >> 수정일 수정자 수정내용 ----------- ------
 *          ---------- 2016.06.23 신동진 최초생성
 *
 */
public class OppfSessionUtil {

    /**
     * @Method Name : getSystemKind
     * @Method description : session의 system kind를 return 한다.
     * @param :
     *            HttpServletRequest
     * @return : String
     * @throws :
     *             Exception
     */
    public static String getSystemKind(HttpServletRequest request) {
        String systemKind = (String) request.getSession().getAttribute("SYSTEM_KIND"); // system
                                                                                       // kind를
                                                                                       // 가져온다.

        // system kind가 null일 경우 system kind를 셋팅한다.
        if (systemKind == null || OppfStringUtil.isEmpty(systemKind)) {

            // domain set
            String[] domainArr = OppfProperties.getProperty("Globals.domain.arr").split("\\|");

            if (domainArr != null) {
                String targetUri = request.getServerName();
                if (request.getServerPort() > 0) {
                    targetUri += ":" + request.getServerPort();
                }

                for (int i = 0; i < domainArr.length; i++) {
                    String tmpDomain = OppfProperties.getProperty("Globals.domain.sep." + domainArr[i]);

                    // 구분자값이 여러개 일 경우(| 로 구분)
                    if (tmpDomain.indexOf("|") > -1) {
                        String[] tmpDomainArr = tmpDomain.split("\\|");
                        if (tmpDomainArr != null) {
                            for (int j = 0; j < tmpDomainArr.length; j++) {
                                // 접속 domain값이 있다면
                                if (targetUri.indexOf(tmpDomainArr[j]) > -1) {
                                    request.getSession().setAttribute("SYSTEM_KIND", domainArr[i]);

                                    systemKind = domainArr[i];
                                }
                            }
                        }
                    } else {
                        // 접속 domain값이 있다면
                        if (targetUri.indexOf(tmpDomain) > -1) {
                            request.getSession().setAttribute("SYSTEM_KIND", domainArr[i]);

                            systemKind = domainArr[i];
                        }
                    }
                }
            }
        }

        return systemKind;
    }

    public static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

}
