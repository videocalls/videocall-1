package kr.co.koscom.oppfm.otp.service.impl;

import kr.co.koscom.oppfm.otp.dao.CommonOtpRequestMapper;
import kr.co.koscom.oppfm.otp.model.OtpReq;
import kr.co.koscom.oppfm.otp.service.CommonOtpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName : CommonOtpRequestServiceImpl
 * *
 * Description :
 * <p>
 * Created by LSH on 2017. 5. 24..
 */
@Service
public class CommonOtpRequestServiceImpl implements CommonOtpRequestService{

    @Autowired
    private CommonOtpRequestMapper cmmOtpReqMapper;

    /**
     * @Method Name        : saveOtpProfile
     * @Method description : OTP 정보를 저장한다.
     * @param              : OtpReq
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int saveOtpProfile(OtpReq otpReq) {
        int result = 0;

        //기존데이터가 있는지 여부 체크
        int chkCnt = cmmOtpReqMapper.cntCheckOtpProfile(otpReq);

        //chkCnt가 0이상이면 수정
        if(chkCnt > 0){
            result += cmmOtpReqMapper.updateOtpProfile(otpReq);
        }else{
            result += cmmOtpReqMapper.insertOtpProfile(otpReq);
        }

        if(result != 0){
            //hist 셋팅
            result += cmmOtpReqMapper.insertOtpProfileHist(otpReq);
        }

        return result;
    }

    /**
     * @Method Name        : deleteOtpProfile
     * @Method description : OTP 정보를 삭제한다.
     * @param              : OtpReq
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int deleteOtpProfile(OtpReq otpReq) {
        int result = 0;

        //기존데이터가 있는지 여부 체크
        int chkCnt = cmmOtpReqMapper.cntCheckOtpProfile(otpReq);

        //chkCnt가 0이상이면 수정
        if(chkCnt > 0){
            result = cmmOtpReqMapper.deleteOtpProfile(otpReq);
        }
        if(result != 0){
            //hist 셋팅
            result += cmmOtpReqMapper.insertOtpProfileHist(otpReq);
        }

        return result;
    }
}
