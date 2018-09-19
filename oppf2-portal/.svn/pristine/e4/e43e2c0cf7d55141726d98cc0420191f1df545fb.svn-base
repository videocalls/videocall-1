package kr.co.koscom.oppf.cmm.web;

import kr.co.koscom.oppf.cmm.service.CmmMemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author : 이희태
 * @version : 1.0
 * @Project : 자본시장 공동 핀테크 오픈플랫폼 구축 2차
 * @FileName : CmmGuestMemberExpireEmailSendController.java
 * @Comment : 비회원 인증 유효기만 만료 안내 메일 전송 3/1개월 Controller
 * @see
 * @since : 2017.06.19
 */
@RestController
public class CmmGuestMemberExpireEmailSendController {

    @Resource(name = "CmmMemberService")
    private CmmMemberService cmmMemberService;

    @RequestMapping(value="/cmm/cron/guestMemberMailSend",method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public void guestMemberMailSend(HttpServletRequest request) throws Exception {
        cmmMemberService.guestMemberExpireEMailSend(request);
    }

}
