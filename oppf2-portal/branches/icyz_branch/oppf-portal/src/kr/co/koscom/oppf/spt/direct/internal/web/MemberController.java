package kr.co.koscom.oppf.spt.direct.internal.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.koscom.oppf.cmm.signkorea.service.VerifyExtInfo;
import kr.co.koscom.oppf.spt.direct.internal.model.InternalResponse;
import kr.co.koscom.oppf.spt.direct.internal.model.MemberRequest;
import kr.co.koscom.oppf.spt.direct.internal.model.MemberResponse;
import kr.co.koscom.oppf.spt.direct.internal.model.MemberVerifyRequest;
import kr.co.koscom.oppf.spt.direct.internal.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(
            path = "/direct/common/member/{userCi}",
            method = RequestMethod.GET,
            produces = { "application/json;charset=UTF-8" },
            consumes = { "application/json;charset=UTF-8" })
    public MemberResponse getMemberInfo(@PathVariable String userCi) {
        return memberService.selectMember(userCi);
    }

    @RequestMapping(
            path = "/direct/common/member",
            method = RequestMethod.POST,
            produces = { "application/json;charset=UTF-8" },
            consumes = { "application/json;charset=UTF-8" })
    public InternalResponse insertMemberInfo(@RequestBody MemberRequest request) {
        memberService.insertMember(request);

        return InternalResponse.SUCCESS;
    }

    @RequestMapping(
            path = "/direct/common/memberput",
            method = RequestMethod.POST,
            produces = { "application/json;charset=UTF-8" },
            consumes = { "application/json;charset=UTF-8" })
    public InternalResponse updateMemberInfo(@RequestBody MemberRequest request) {
        memberService.updateMember(request);

        return InternalResponse.SUCCESS;
    }

    @RequestMapping(
            path = "/direct/common/member/verifyinfo",
            method = RequestMethod.POST,
            produces = { "application/json;charset=UTF-8" },
            consumes = { "application/json;charset=UTF-8" })
    public InternalResponse insertMemberVerifyInfo(@Valid @RequestBody MemberVerifyRequest request) {
        memberService.insertMemberVerify(request);

        return InternalResponse.SUCCESS;
    }

    @RequestMapping(
            path = "/direct/common/member/verifyinfoput",
            method = RequestMethod.POST,
            produces = { "application/json;charset=UTF-8" },
            consumes = { "application/json;charset=UTF-8" })
    public InternalResponse updateMemberVerifyInfo(@Valid @RequestBody MemberVerifyRequest request) {
        memberService.updateMemberVerify(request);

        return InternalResponse.SUCCESS;
    }

}
