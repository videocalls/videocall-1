package kr.co.koscom.oppf.cmm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CmmEmailSendService.java
* @Comment  : 공통 이메일발송 기능을 제공하는 service
* @author   : 유제량
* @since    : 2016.05.25
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.25  유제량        최초생성
*
*/
public interface CmmEmailSendService{
    
    /**
     * @Method Name        : cmmEmailSend
     * @Method description : 공통 이메일발송을 한다.
     * @param              : CmmEmailSendVO
     * @return             : CmmEmailSendVO
     * @throws             : Exception
     */
    public CmmEmailSendVO cmmEmailSend(CmmEmailSendVO cmmEmailSendVO, HttpServletRequest request) throws Exception;
    
    /**
     * @Method Name        : selectCmmComEmailSendInfoChk
     * @Method description : 이메일 발송이력을 조회해온다.
     * @param              : CmmEmailSendVO
     * @return             : String
     * @throws             : Exception
     */
    public String selectCmmComEmailSendInfoChk(CmmEmailSendVO cmmEmailSendVO) throws Exception;
    
    /**
     * @Method Name        : selectCmmEmailCustomerInfo
     * @Method description : 사용자 정보를 조회한다.
     * @param              : CmmEmailSendVO
     * @return             : CmmEmailSendVO
     * @throws             : Exception
     */
    public CmmEmailSendVO selectCmmEmailCustomerInfo(CmmEmailSendVO cmmEmailSendVO) throws Exception;
    
    /**
     * @Method Name        : selectCronSvcTermsList
     * @Method description : 정보제공동의 메일발송 대상 목록 조회
     * @param              : CmmEmailSendVO
     * @return             : List<CmmEmailCronSvcTermsVO>
     * @throws             : Exception
     */
    public List<CmmEmailCronSvcTermsVO> selectCronSvcTermsList(CmmEmailCronSvcTermsVO cmmEmailCronSvcTermsVO) throws Exception;
    
}
