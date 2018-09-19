package kr.co.koscom.oppfm.login.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.login.model.CertPasswordFailReq;
import kr.co.koscom.oppfm.login.model.FindIdReq;
import kr.co.koscom.oppfm.login.model.LoginReq;
import kr.co.koscom.oppfm.login.model.ModifyPasswordReq;
import kr.co.koscom.oppfm.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;

/**
 * LoginController
 * <p>
 * Created by chungyeol.kim on 2017-04-24.
 * Modify by sh.lee on 2017-05-16.
 */
@Api(description = "로그인", produces = "application/json")
@RestController
@RequestMapping(value="/apis")
@Slf4j
public class LoginController {


    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    LoginService loginService;


    /**
     * 로그인(아이디, 비밀번호)
     * */
    @ApiOperation(value = "로그인", notes="로그인", response = CommonResponse.class)
    @RequestMapping(value = "/login", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    public CommonResponse loginAction(@RequestBody LoginReq loginReq, HttpServletResponse response) {
        log.debug("아이디 = {}",loginReq.getCustomerId());
        return loginService.loginCheck(loginReq, response);
    }

    /**
     * 공인인증서 로그인 (아이디, DN)
     * */
    @ApiOperation(value = "공인인증서로그인", notes="공인인증서로그인", response = CommonResponse.class)
    @RequestMapping(value = "/login/verify", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    public CommonResponse verifyLoginAction(@RequestBody LoginReq loginReq, HttpServletResponse response) {
        log.debug("아이디 = {}",loginReq.getCustomerId());
        return loginService.verifyLoginCheck(loginReq, response);
    }
    /**
     * LogOut(Remove Cookie)
     * */
    @RequestMapping(value = "/logout", method = {RequestMethod.POST})
    public CommonResponse logout(HttpServletResponse response) {
        return loginService.logout(response);
    }

    /**
     * 아이디 찾기
     * */
    @RequestMapping(value = "/find/id", method = {RequestMethod.POST})
    public CommonResponse getFindLoginId(@RequestBody FindIdReq findIdReq) {
        /*
        * 1. 이름 & 이메일 null 체크
        * 2. 이름, 이메일, 탈퇴여부로 아이디 조회
        * */

        return loginService.getFindLoginId(findIdReq);
    }


    /**
     * 공인인증서 인증
     * */
    @RequestMapping(value = "/confirmVerify", method = {RequestMethod.POST})
    public CommonResponse selectVerifyIdConfirm(@RequestBody LoginReq loginReq) {
        return loginService.selectVerifyIdConfirm(loginReq);
    }

    /**
     * 비밀번호 수정
     * */
    @RequestMapping(value = "/password", method = {RequestMethod.POST})
    public CommonResponse moodifyPassword(@RequestBody ModifyPasswordReq modifyPasswordReq) {
        /*
        * 1. op번호 & 비밀번호 null 체크
        * 2. 비밀번호 수정 쿼리
        * */

        return loginService.modifyPassword(modifyPasswordReq);
    }

    /**
     * 아이디만 입력해도 로그인 가능한 API
     * */
    @RequestMapping(value = "/login/test", method = {RequestMethod.POST})
    public CommonResponse loginActionTest(@RequestBody LoginReq loginReq, HttpServletResponse response) {
        return loginService.loginActionTest(loginReq, response);
    }

    /**
     * 공인인증서 로그인 실패시 패스워드 실패 카운트 증가
     * */
    @ApiOperation(value = "공인인증서 로그인 실패시 패스워드 실패 카운트 증가", response = CommonResponse.class)
    @RequestMapping(value = "/login/updateCertPasswordFailCnt", method = {RequestMethod.POST})
    public CommonResponse updateCertPasswordFailCnt(@RequestBody CertPasswordFailReq certPasswordFailReq, HttpServletResponse response) {
        
        return loginService.updateCertPasswordFailCnt(certPasswordFailReq, response);
    }
    


}
