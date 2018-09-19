package kr.co.koscom.oppfm.login.service;

import javax.servlet.http.HttpServletResponse;

import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.login.model.CertPasswordFailReq;
import kr.co.koscom.oppfm.login.model.FindIdReq;
import kr.co.koscom.oppfm.login.model.LoginReq;
import kr.co.koscom.oppfm.login.model.ModifyPasswordReq;

/**
 * LoginService
 * <p>
 * Created by chungyeol.kim on 2017-04-24.
 * * Modify by sh.lee on 2017-05-16.
 */
public interface LoginService {
    CommonResponse loginCheck(LoginReq loginReq, HttpServletResponse response);

    CommonResponse verifyLoginCheck(LoginReq loginReq, HttpServletResponse response);

    CommonResponse getFindLoginId(FindIdReq findIdReq);

    CommonResponse modifyPassword(ModifyPasswordReq modifyPasswordReq);

    CommonResponse logout(HttpServletResponse response);

    CommonResponse loginActionTest(LoginReq loginReq, HttpServletResponse response);
    
    /**
     * 공인인증서 로그인 실패시 패스워드 실패 카운트 증가
     * @param response
     * @return 현재 패스워드 실패 카운트
     */
    public CommonResponse updateCertPasswordFailCnt(CertPasswordFailReq certPasswordFailReq, HttpServletResponse response);

    CommonResponse selectVerifyIdConfirm(LoginReq loginReq);
}
