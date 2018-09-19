package kr.co.koscom.oppf.spt.myp.cert.service;

import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CertService.java
* @Comment  : [마이페이지:공인인증서]정보관리를 위한 Service 인터페이스
* @author   : 포털 이환덕
* @since    : 2016.05.02
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.02  이환덕        최초생성
*
*/
public interface CertService{

    /**
     * @Method Name        : updateSptCustomerVerifyAndTermsInfo
     * @Method description : [마이페이지:공인인증서+약관]정보를 수정을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateSptCustomerVerifyAndTermsInfo(MbrRegVO paramVO) throws Exception;
}
