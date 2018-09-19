package kr.co.koscom.oppf.cmm.web;

import kr.co.koscom.oppf.cmm.service.CmmMemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * @author : 이희태
 * @version : 1.0
 * @Project : 자본시장 공동 핀테크 오픈플랫폼 구축 2차
 * @FileName : CmmDeleteGuestMemberController.java
 * @Comment : 비회원 가동의서 만료 후 자동 탈퇴 처리
 * @see
 * @since : 2017.06.19
 */
@RestController
public class CmmDeleteGuestMemberController {

    @Resource(name = "CmmMemberService")
    private CmmMemberService cmmMemberService;

    @RequestMapping(value="/cmm/cron/deleteGuestMember",method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public void deleteGuestMember() throws Exception {
        cmmMemberService.deleteGuestMember();
    }

}
