package kr.co.koscom.oppf.cmm.service;

import javax.servlet.http.HttpServletRequest;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축 2차
* @FileName : CmmMemberDeleteService.java
* @Comment  : 회원 스케줄러 service
* @author   : 이희태
* @since    : 2017.04.10
* @version  : 1.0
* @see
*/
public interface CmmMemberService {
    
    /**
     * @Method Name        : deleteMemberProcess
     * @Method description : 회원탍퇴시 연결된 가상계좌, 서비스, 정보제공 동의서 폐기
     * @throws             : Exception
     */
    public void deleteMemberProcess() throws Exception;

    /**
     * @Method Name        : guestMemberExpireEMailSend
     * @Method description : 비회원 인증 유효기만 만료 안내 메일 전송 3/1개월
     * @throws             : Exception
     */
    public void guestMemberExpireEMailSend(HttpServletRequest request) throws Exception;

    /**
     * @Method Name        : deleteGuestMember
     * @Method description : 비회원 가동의서 만료 후 자동 탈퇴 처리
     * @throws             : Exception
     */
    public void deleteGuestMember() throws Exception;

    /**
     * @Method Name        : deleteMember
     * @Method description : 탈퇴 회원 물리 데이터 삭제
     * @throws             : Exception
     */
    public void deleteMember() throws Exception;
}
