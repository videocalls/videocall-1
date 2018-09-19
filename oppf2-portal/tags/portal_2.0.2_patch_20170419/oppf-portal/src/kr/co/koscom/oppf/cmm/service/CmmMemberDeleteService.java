package kr.co.koscom.oppf.cmm.service;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축 2차
* @FileName : CmmMemberDeleteService.java
* @Comment  : 회원탍퇴시 연결된 가상계좌, 서비스, 정보제공 동의서 폐기 service
* @author   : 이희태
* @since    : 2017.04.10
* @version  : 1.0
* @see
*/
public interface CmmMemberDeleteService {
    
    /**
     * @Method Name        : deleteMemberProcess
     * @Method description : 회원탍퇴시 연결된 가상계좌, 서비스, 정보제공 동의서 폐기
     * @throws             : Exception
     */
    public void deleteMemberProcess() throws Exception;

}
