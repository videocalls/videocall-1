package kr.co.koscom.oppfm.cmm.interceptor;

import kr.co.koscom.oppfm.cmm.annotation.CheckAuth;
import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.util.CookieUtil;
import kr.co.koscom.oppfm.login.model.LoginRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CommonInterceptor
 * <p> 공통 Interceptor
 * Created by chungyeol.kim on 2017-04-27.
 * * Modify by sh.lee on 2017-05-16.
 */
@Slf4j
public class CommonInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.debug(request.getRequestURL().toString());
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            CheckAuth auth = method.getMethodAnnotation(CheckAuth.class);

            /* 로그인 처리 */
            if(auth != null){
                LoginRes loginRes = CookieUtil.getLoginInfo(request);
                if(loginRes == null){
                    throw new CommonException(ErrorCodeConstants.NEED_LOGIN, null);
                }
            }
        }

        /* 로그인 쿠키 유지시간 연장 */
        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            // 쿠키에서 로그인 쿠키 추출
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("OPPF_MEMBER")) {
                    log.debug("========================= CookieTimeReset ===========================");
                    cookie.setPath("/");
                    cookie.setMaxAge(60*10);
                    response.addCookie(cookie);
                }
            }
        }

        log.debug("========================= preHandle =======================================");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("========================= postHandle =======================================");
    }
}
