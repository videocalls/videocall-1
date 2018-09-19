package kr.co.koscom.oppfm.login.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * ClassName : ModifyPasswordReq
 * *
 * Description :
 * <p>
 * Created by LSH on 2017. 5. 17..
 */
@Data
public class ModifyPasswordReq extends CommonVO {


    private static final long serialVersionUID = 3463175072044830297L;

    //입력한 비밀번호
    private String customerPassword;
    //비밀번호 변경예정 일
    @JsonIgnore
    private String customerExpirePasswordDate;
    //비밀번호 최종 변경 예정 일
    @JsonIgnore
    private String customerFinalPasswordDate;
    //regNo
    private String customerRegNo;

    private String customerRegStatus;
}
