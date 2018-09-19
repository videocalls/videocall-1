package kr.co.koscom.oppfm.signKorea.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * SKVerifyReq
 * <p>
 * Created by Yoojin Han on 2017-05-26.
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class SKVerifyReq extends CommonVO{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1972687272110476454L;

    //결과
    @JsonIgnore
    private int ret = 0;
    
    //데몬 프로세스가 실행되고 있는 PORT
    @JsonIgnore
    private String demonIp = "127.0.0.1";
    
    //데몬 프로세스가 실행되고 있는 서버 IP
    @JsonIgnore
    private int demonPort = 7900; 
    
    //클라이언트에서 넘어온 전자서명데이터
    //private String signData = "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";
    private String signData;
    
    
    //클라이언트에서 넘어온 R값
    //private String rValue = "H8Py0IU2rQVfRj51qO/NdCNcbXs=";
    @JsonIgnore
    private String rvalue;
    
    //클라이언트에서 넘어온 신원확인정보 값
    //private String userSsn = "1298520172";
    @JsonIgnore
    private String userSsn;
    
    //서명검증 및 신원확인 CRL op코드(signData + "$" + rValue + "$" + userSsn)
    @JsonIgnore
    private String datas = "";
    
    @JsonIgnore
    private SKSignedDataReq skSignedDataReq;
    
    @JsonIgnore
    public SKVerifyExtReq skVerifyExtReq;
    
    //회원아이디 조회를 위한 DN 값
    public String customerDn;

}
