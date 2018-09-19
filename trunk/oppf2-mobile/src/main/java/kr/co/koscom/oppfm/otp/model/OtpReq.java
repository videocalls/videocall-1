package kr.co.koscom.oppfm.otp.model;

import lombok.Data;

/**
 * ClassName : OtpReq
 * *
 * Description :
 * <p>
 * Created by LSH on 2017. 5. 24..
 */
@Data
public class OtpReq {
    /* 화면에서 받았을 때 Check 시 사용 */
    private String customerSendOtpId;

    /* DB에 저장할 때 사용 */
    private String customerRegNo;
    private String customerOtpId;

}
