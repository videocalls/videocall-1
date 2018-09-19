package kr.co.koscom.oppfm.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.model.EmailReqRes;
import kr.co.koscom.oppfm.login.model.LoginRes;
import kr.co.koscom.oppfm.member.model.MemberReq;

/**
 * MemberService
 * 
 * @author 판광 on 2017-05-15.
 * * Modify by sh.lee on 2017-05-17.
 *
 */
public interface MemberService {
   
    /* 회원 상세 조회 */
    CommonResponse getUserInfo(MemberReq memberReq);

    /* 회원 정보 수정 */
	CommonResponse modifyUserInfo(MemberReq memberReq);

    CommonResponse getCheckPw(MemberReq memberReq, HttpServletRequest request);
    
    CommonResponse changePassword(MemberReq memberReq, HttpServletRequest request);

    CommonResponse createMember(MemberReq createMemberReq);

    CommonResponse removeMember(LoginRes loginInfo, HttpServletResponse response);

    /* 임시회원 가입여부 조회 */
    CommonResponse getGuestMember(String verify, HttpServletResponse response);

    CommonResponse getDuplicationCheckCi(MemberReq memberReq);

    CommonResponse getDuplicationCheckId(MemberReq memberReq);

    CommonResponse getDuplicationCheckEmail(MemberReq memberReq);
	
    /* 임시회원 인증 */
	CommonResponse temporaryMember(MemberReq createMemberReq, HttpServletResponse response);

    CommonResponse passwordConfirm(MemberReq memberReq);

	CommonResponse getFindUserId(MemberReq memberReq);
	
	/**
	 * 모바일 push 메세지 수신 여부 변경
	 * @param memberReq
	 * @return
	 */
	public CommonResponse changeMobilePush(LoginRes loginInfo);


    CommonResponse updateMemberTermsProfile(MemberReq memberReq);

    CommonResponse sendMemberEmail(EmailReqRes emailVO, HttpServletRequest request);
}
