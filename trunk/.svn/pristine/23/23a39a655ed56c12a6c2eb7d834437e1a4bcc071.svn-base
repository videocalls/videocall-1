package kr.co.koscom.oppfm.login.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * LoginReq
 * <p>
 * Created by chungyeol.kim on 2017-04-24.
 * * Modify by sh.lee on 2017-05-16.
 */
@Data
public class LoginReq extends CommonVO{


    private static final long serialVersionUID = -9208976156731117149L;

    //회원 OP 등록 번호
    @JsonIgnore
    private String customerRegNo;
    //회원 ID
    private String customerId;
    //회원 비밀번호
    private String customerPassword;

    //회원 가입 상태
    @JsonIgnore
    private String customerRegStatus;

    //비밀번호 실패 여부
    @JsonIgnore
    private String customerPasswordFailType;

    //비밀번호 실패 수
    @JsonIgnore
    private int customerPasswordFailCnt;

    //DN 값(공인인증서 로그인)
    private String customerVerifyDn;
    
    private String deviceType;						/* 디바이스 구분 */
    
    private String deviceUniqueId;					/* 디바이스 식별 아이디 */
    
    private String companyCodeId;					/* 기업 코드 */
    
    private String appKey;							/* app_key */
    
    private String osVersion;						/* OS 버전 */
    
    private String appVersion;						/* app 버젼 */

    //공인인증서 로그인 시 받을 파라미터
    private String signData;
    
    
}
