package kr.co.koscom.oppfm.cmm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.login.model.LoginRes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName : CookieUtil
 * *
 * Description :
 * <p>
 * Created by LSH on 2017. 5. 11..
 */
@Slf4j
public class CookieUtil {

    @Autowired
    private MessageUtil messageUtil;

    /**
     * Cookie 값 가져오기
     *
     * @return
     */
    public static LoginRes getLoginInfo(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        LoginRes loginInfo = null;

        //쿠키가 없는 경우 에러
        if (cookies == null || cookies.length == 0) {
            log.debug("NOT COOKIES -----------> 로그인 에러 ");
        } else {
            String memberInfoCookie = null;

            // 쿠키에서 로그인 정보를 추출
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("OPPF_MEMBER")) {
                    memberInfoCookie = cookie.getValue();
                }
            }

            //OPPF_MEMBER 이름을 가진 쿠키가 없는 경우 에러
            if (memberInfoCookie == null) {
                log.debug("NOT COOKIES -----------> 로그인 에러 ");
            } else {
                log.debug("GET COOKIES -----------> {}", memberInfoCookie);

                String decodeText = new String(Base64.decodeBase64(memberInfoCookie.getBytes()));
                ObjectMapper mapper = new ObjectMapper();

                try {
                    loginInfo = mapper.readValue(decodeText, LoginRes.class);
                } catch (IOException e) {
                    log.debug("NOT COOKIES -----------> 로그인 에러 ");
                }
                log.debug("GET COOKIES -----------> {}", loginInfo);
            }
        }
        return loginInfo;
    }

    /**
     *
     * Create Login Cookie
     * OPPF_MEMBER 쿠키값 생성
     *
     *
     * */
     public static void createLoginCookie(LoginRes loginRes, HttpServletResponse response){
         JSONObject loginInfo = new JSONObject(loginRes);
         String loginInfojsonStr = loginInfo.toString();
         log.debug("jsonStr -----------> {}", loginInfojsonStr);
         log.debug("SET COOKIES -----------> {}", loginRes.getCustomerId());
         log.debug("SET COOKIES -----------> {}", loginInfo.getString("customerId"));

         String encode = new String(Base64.encodeBase64(loginInfojsonStr.getBytes()));

         Cookie cookie = new Cookie("OPPF_MEMBER", encode);
         cookie.setPath("/");
         cookie.setMaxAge(60*10);
         response.addCookie(cookie);

     }

     /**
      *
      * Delete Login Cookie
      * OPPF_MEMBER 쿠키값 삭제
      *
      *
      * */
     public static void removeLoginCookie(HttpServletResponse response){
         Cookie loginCookie = new Cookie("OPPF_MEMBER", null);
         loginCookie.setPath("/");
         loginCookie.setMaxAge(0);
         response.addCookie(loginCookie);
     }
}
