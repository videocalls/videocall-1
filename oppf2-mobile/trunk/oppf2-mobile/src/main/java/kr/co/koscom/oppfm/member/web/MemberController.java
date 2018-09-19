package kr.co.koscom.oppfm.member.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.koscom.oppfm.cmm.annotation.CheckAuth;
import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.model.EmailReqRes;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.cmm.util.CookieUtil;
import kr.co.koscom.oppfm.cmm.util.OppfStringUtil;
import kr.co.koscom.oppfm.login.model.LoginRes;
import kr.co.koscom.oppfm.member.model.MemberPushReq;
import kr.co.koscom.oppfm.member.model.MemberReq;
import kr.co.koscom.oppfm.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * NotiController
 * <p>
 * Created by Yoojin Han on 2017-04-20.
 * * Modify by sh.lee on 2017-05-17.
 */
@Api(value = "Member-controller", description = "설정 > 회원정보")
@RequestMapping(value="/apis")
@RestController
@Slf4j
public class MemberController {

    @Autowired
    private MessageUtil messageUtil;
    
    @Autowired
    MemberService memberService;


    /**
     * 회원 상세 정보 조회
     * @param  : MemberReq
     * @return
     * @throws : Exception
     */
    @CheckAuth
    @RequestMapping(value="/member", method = RequestMethod.GET)
  	public CommonResponse getUserInfo(@ApiIgnore HttpServletRequest request) throws Exception {

        /*로그인 정보*/
        LoginRes loginInfo = CookieUtil.getLoginInfo(request);

        MemberReq memberReq = new MemberReq(); 
        
        memberReq.setCustomerRegNo(loginInfo.getCustomerRegNo());
        
    	CommonResponse response = memberService.getUserInfo(memberReq);
    	
         return response;
    }
    

    /**
     * 회원 정보 수정
     * @param : MemberReq
     * @return
     * @throws : Exception
     */
    @CheckAuth
    @RequestMapping(value="/memberput", method = RequestMethod.POST)
  	public CommonResponse modifyMember(@RequestBody MemberReq memberReq, @ApiIgnore HttpServletRequest request) throws Exception {

    	/*로그인 정보*/
        LoginRes loginInfo = CookieUtil.getLoginInfo(request);

        memberReq.setCustomerRegNo(loginInfo.getCustomerRegNo());

        return memberService.modifyUserInfo(memberReq);
    }

    /**
     * 비밀번호 확인
     * */
    @CheckAuth
    @RequestMapping(value="/member/confirmPassword", method = RequestMethod.POST)
  	public CommonResponse getCheckPw(@RequestBody MemberReq memberReq, @ApiIgnore HttpServletRequest request) throws Exception {
    	
        /*로그인 정보*/
        LoginRes loginInfo = CookieUtil.getLoginInfo(request);

        memberReq.setCustomerRegNo(loginInfo.getCustomerRegNo());

         return memberService.getCheckPw(memberReq, request);
    }


    /**
     * 비밀번호 변경
     * */
    @CheckAuth
    @RequestMapping(value="/member/changePassword", method = RequestMethod.POST)
  	public CommonResponse changePassword(@RequestBody MemberReq memberReq, @ApiIgnore HttpServletRequest request) throws Exception {
    	
        /*로그인 정보*/
        LoginRes loginInfo = CookieUtil.getLoginInfo(request);

        memberReq.setCustomerRegNo(loginInfo.getCustomerRegNo());

         return memberService.changePassword(memberReq, request);
    }

    /**
     * 회원 가입
     * */
    @RequestMapping(value="/member", method = RequestMethod.POST)
    public CommonResponse createMember(@RequestBody MemberReq MemberReq, @ApiIgnore HttpServletRequest request) throws Exception {
        return memberService.createMember(MemberReq);
    }

    /**
     * 임시회원 가입여부 조회(기간만료된 회원인지 체크)
    * */
    @ApiOperation(value = "임시회원 가입여부 조회", response = CommonResponse.class)
    @RequestMapping(value="/guestMember", method = RequestMethod.GET)
    public CommonResponse getGuestMember(@RequestParam(value="verify", defaultValue="") String verify, @ApiIgnore HttpServletResponse response) throws Exception {

        if(null == verify || "".equals(verify)){
            throw new CommonException(ErrorCodeConstants.REQUIRED_PARAMETER_ERROR, new String[] {"verify"});
        }

        return memberService.getGuestMember(verify, response);
    }

    /**
     * 기존 가입 이력 조회(CI 중복체크)
     * */
    @RequestMapping(value="/member/duplicationCheck/ci", method = RequestMethod.POST)
    public CommonResponse getDuplicationCheckCi(@RequestBody MemberReq memberReq, @ApiIgnore HttpServletRequest request) throws Exception {
        return memberService.getDuplicationCheckCi(memberReq);
    }

    /**
     * 아이디 중복체크
     * */
    @RequestMapping(value="/member/duplicationCheck/Id", method = RequestMethod.POST)
    public CommonResponse getDuplicationCheckId(@RequestBody MemberReq memberReq, @ApiIgnore HttpServletRequest request) throws Exception {
        return memberService.getDuplicationCheckId(memberReq);
    }

    /**
     * 이메일 중복체크
     * */
    @RequestMapping(value="/member/duplicationCheck/email", method = RequestMethod.POST)
    public CommonResponse getDuplicationCheckEmail(@RequestBody MemberReq memberReq, @ApiIgnore HttpServletRequest request) throws Exception {
        return memberService.getDuplicationCheckEmail(memberReq);
    }

    /**
     * 회원정보 수정 이메일 중복체크
     * */
    @CheckAuth
    @RequestMapping(value="/member/update/duplication/email", method = RequestMethod.POST)
    public CommonResponse getDupCheckEmailForUpdate(@RequestBody MemberReq memberReq, @ApiIgnore HttpServletRequest request) throws Exception {

        /*로그인 정보*/
        LoginRes loginInfo = CookieUtil.getLoginInfo(request);
        memberReq.setCustomerRegNo(loginInfo.getCustomerRegNo());
        
        return memberService.getDuplicationCheckEmail(memberReq);
    }



    /**
     * 회원 탈퇴
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @CheckAuth
    @RequestMapping(value="/memberdelete", method = RequestMethod.POST)
    public CommonResponse removeMember(@ApiIgnore HttpServletRequest request, @ApiIgnore HttpServletResponse response) throws Exception {

        /*로그인 정보*/
        LoginRes loginInfo = CookieUtil.getLoginInfo(request);

        return memberService.removeMember(loginInfo,response);
    }

    /**
     * 비 회원 인증
     * */
    @RequestMapping(value="/temporaryMember", method = RequestMethod.POST)
    public CommonResponse temporaryMember(@RequestBody MemberReq memberReq, @ApiIgnore HttpServletRequest request, @ApiIgnore HttpServletResponse response) throws Exception {
        return memberService.temporaryMember(memberReq, response);
    }

    /**
     * 비밀번호 입력 에러메시지
     * */
    @RequestMapping(value="/valid/password", method = RequestMethod.POST)
    public CommonResponse passwordValid(@RequestBody MemberReq memberReq) throws Exception {
        return memberService.passwordConfirm(memberReq);
    }

    

    /**
     * 아이디 및 해당아이디에 공인 인증서 등록 여부 조회
     * 등록된 아이디 및 공인인증서 등록이 되어 있으면 성공 리턴
     * @param request
     * @return
     * @throws Exception
     */

    @ApiOperation(value = "아이디 및 해당아이디에 공인 인증서 등록 여부 조회", response = CommonResponse.class)
    @RequestMapping(value="/member/signKorea/{customerId}/findId", method = RequestMethod.GET)
  	public CommonResponse getFindUserId(@PathVariable String customerId, @ApiIgnore HttpServletRequest request) throws Exception {
    	
    	CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
    	
    	if(OppfStringUtil.isEmpty(customerId)){
    		response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.NOT_FOUND_USER_ID, null);
    	}
        
    	MemberReq memberReq = new MemberReq(); 
        memberReq.setCustomerId(customerId);
    	response = memberService.getFindUserId(memberReq);
    	
         return response;
    }
    
    /**
     * 모바일 Push 수신여부 변경
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @CheckAuth
    @RequestMapping(value="/member/changeMobilePush", method = RequestMethod.POST)
    public CommonResponse changeMobilePush(@RequestBody MemberPushReq memberPushReq, @ApiIgnore HttpServletRequest request) throws Exception {
        CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);

        /*로그인 정보*/
        LoginRes loginInfo = CookieUtil.getLoginInfo(request);
        /*로그인 정보*/
        loginInfo.setCustomerMobilePushYn(memberPushReq.getCustomerMobilePushYn());
        
        return memberService.changeMobilePush(loginInfo);
    }

    /**
     * 약관 재 동의
     * */
    @CheckAuth
    @RequestMapping(value="member/terms/again", method = RequestMethod.POST)
    public CommonResponse modifyMemberTermsProfile(@RequestBody MemberReq memberReq, @ApiIgnore HttpServletRequest request) throws Exception {

        /*로그인 정보*/
        LoginRes loginInfo = CookieUtil.getLoginInfo(request);
        /*로그인 정보*/
        memberReq.setCustomerRegNo(loginInfo.getCustomerRegNo());



        return memberService.updateMemberTermsProfile(memberReq);
    }

    /**
     * 회원가입 완료 메일
     * */
    @RequestMapping(value="member/mail", method = RequestMethod.POST)
    public CommonResponse insertMemberMail(@RequestBody EmailReqRes emailVO, @ApiIgnore HttpServletRequest request) throws Exception {
        return memberService.sendMemberEmail(emailVO, request);
    }
}
