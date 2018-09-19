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
 * @FileName : CmmDeleteMemberProcessController.java
 * @Comment : 회원탍퇴시 연결된 가상계좌, 서비스, 정보제공 동의서 폐기 API Controller
 * @see
 * @since : 2017.04.10
 */
@RestController
public class CmmDeleteMemberProcessController {

    @Resource(name = "CmmMemberService")
    private CmmMemberService cmmMemberService;

    @RequestMapping(value="/cmm/cron/deleteMemberProcess",method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public void deleteMemberProcess() throws Exception {
        cmmMemberService.deleteMemberProcess();
    }

}
