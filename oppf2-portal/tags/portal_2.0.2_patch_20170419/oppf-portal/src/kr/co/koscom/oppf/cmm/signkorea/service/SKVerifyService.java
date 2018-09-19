package kr.co.koscom.oppf.cmm.signkorea.service;

import org.springframework.stereotype.Repository;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmMenuService.java
* @Comment  : 공통메뉴 관리를 위한 service
* @author   : 이환덕
* @since    : 2016.05.24
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.24  이환덕        최초생성
*
*/
public interface SKVerifyService{
    
    
    public int verifySign(String verifyd_ip, int verifyd_port, SKSignedDataInfo result);
    
    public int setValidateOp(String verifyd_ip, int verifyd_port, String Op);
    
    /**
     * @Method Name        : verifySign
     * @Method description : 공인인증서 서버 유효성검증 처리를 한다.
     * @param              : String,int,SKSignedDataInfoVO
     * @return             : int
     * @throws             : Exception
     */
    public int verifySignExt(String verifyd_ip, int verifyd_port, SKSignedDataInfo pSKSignedDataInfo, SKVerifyExtInfo pSKVerifyExtInfo);
    
    public int cipher(String demon_ip, int demon_port, SKCipherInfo cipherInfo);
    
    public int verifySignCipher(String verifyd_ip, int verifyd_port, SKSignedDataInfo result, SKVerifyExtInfo ext);
    
    public int certstat( String ivsp_ip, int ivsp_port, SKIvsmcCert cert);
    
    public int dnquery(String ivsp_ip, int ivsp_port, SKIvsmcCert cert);


}
