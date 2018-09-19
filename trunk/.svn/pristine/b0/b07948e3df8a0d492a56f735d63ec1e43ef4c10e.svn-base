package kr.co.koscom.oppfm.signKorea.service;

import java.io.UnsupportedEncodingException;

import kr.co.koscom.oppfm.signKorea.model.CustomerCertDnReq;
import kr.co.koscom.oppfm.signKorea.model.SKSignedDataReq;
import kr.co.koscom.oppfm.signKorea.model.SKVerifyExtReq;
import kr.co.koscom.oppfm.signKorea.model.SKVerifyReq;
import kr.co.koscom.oppfm.signKorea.model.SKVerifyVO;

/**
 * SKVerifyService
 * <p>
 * Created by Yoojin Han on 2017-05-26.
 */

public interface SKVerifyService {

    /**
     * @Description       : 공인인증서 서버 유효성검증
     * @param skVerifyReq
     * @return
     * @throws UnsupportedEncodingException
     */
    public SKVerifyVO verifySign(SKVerifyReq skVerifyReq) throws UnsupportedEncodingException;

    /**
     * @MethodName        : verifySignExt
     * @Description       : 공인인증서 서버 유효성검증 처리
     * @param              : String,int,SKSignedDataReq
     * @return             : int
     * @throws             : Exception
     */
    public int verifySignExt(String verifydIp, int verifydPort, SKSignedDataReq pSKSignedDataReq, SKVerifyExtReq pSKVerifyExtReq);
    
    /**
     * DN 값으로 회원ID 조회 getCustomerId
     */
    public String getCustomerId(String userDn);

    /**
     * customerRegNo 값으로 회원ID의 등록된 DN 값 조회
     */
    public String getCustomerDn(String customerRegNo);
    
    /**
     * customerRegNo 값으로 회원ID의 등록된 DN 값 변경
     */
    public int updateCustomerDn(CustomerCertDnReq customerCertDnReq);
    
}
