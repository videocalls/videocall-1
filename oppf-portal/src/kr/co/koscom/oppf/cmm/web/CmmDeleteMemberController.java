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
 * @FileName : CmmDeleteMemberController.java
 * @Comment : 탈퇴 회원 물리 데이터 삭제 스케줄
 * @see
 * @since : 2017.06.22
 */
@RestController
public class CmmDeleteMemberController {

    @Resource(name = "CmmMemberService")
    private CmmMemberService cmmMemberService;

    @RequestMapping(value="/cmm/cron/deleteMember",method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public void deleteMember() throws Exception {
        cmmMemberService.deleteMember();
    }

}
