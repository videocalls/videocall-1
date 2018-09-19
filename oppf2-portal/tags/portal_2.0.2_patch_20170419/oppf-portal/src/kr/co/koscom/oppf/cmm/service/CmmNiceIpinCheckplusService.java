package kr.co.koscom.oppf.cmm.service;

import javax.servlet.http.HttpServletRequest;

import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;

/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CmmNiceIpinCheckplusService.java
* @Comment  : 공통 NICE평가정보에서 제공하는 i-pin인증 및 휴대폰인증 기능을 제공하는 service
* @author   : 유제량
* @since    : 2016.06.01
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.01  유제량        최초생성
*
*/
public interface CmmNiceIpinCheckplusService{
    
    /**
     * @Method Name        : initIpinCert
     * @Method description : [회원가입:인증] 아이핀 인증을 한다.
     * @param              : MbrRegVO, HttpServletRequest
     * @return             : MbrRegVO
     * @throws             : Exception
     */
    public MbrRegVO initIpinCert(MbrRegVO mbrRegVO, HttpServletRequest request) throws Exception;
    
    /**
     * @Method Name        : prnIpinCert
     * @Method description : [회원가입:인증] 아이핀 인증을 한다.
     * @param              : MbrRegVO, HttpServletRequest
     * @return             : CmmNiceIpinCheckplusVO
     * @throws             : Exception
     */
    public CmmNiceIpinCheckplusVO ipinResult(MbrRegVO mbrRegVO, HttpServletRequest request) throws Exception;
    
    /**
     * @Method Name        : initCheckplus
     * @Method description : [회원가입:인증] 휴대폰 인증을 한다.(안심본인인증)
     * @param              : MbrRegVO, HttpServletRequest
     * @return             : MbrRegVO
     * @throws             : Exception
     */
    public MbrRegVO initCheckplus(MbrRegVO mbrRegVO, HttpServletRequest request) throws Exception;
    
    /**
     * @Method Name        : checkplusSuccess
     * @Method description : [회원가입:인증] 휴대폰 인증 성공 결과값을 리턴한다.(안심본인인증)
     * @param              : MbrRegVO, HttpServletRequest
     * @return             : CmmNiceIpinCheckplusVO
     * @throws             : Exception
     */
    public CmmNiceIpinCheckplusVO checkplusSuccess(MbrRegVO mbrRegVO, HttpServletRequest request) throws Exception;
    
    /**
     * @Method Name        : checkplusFail
     * @Method description : [회원가입:인증] 휴대폰 인증 실패 결과값을 리턴한다.(안심본인인증)
     * @param              : MbrRegVO, HttpServletRequest
     * @return             : CmmNiceIpinCheckplusVO
     * @throws             : Exception
     */
    public CmmNiceIpinCheckplusVO checkplusFail(MbrRegVO mbrRegVO, HttpServletRequest request) throws Exception;
    
}
