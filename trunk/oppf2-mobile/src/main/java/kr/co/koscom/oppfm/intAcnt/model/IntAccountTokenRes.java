package kr.co.koscom.oppfm.intAcnt.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * FileName : IntAccountTokenRes
 *
 * Description : IntAccountToken Response
 *
 * Created by LHT on 2017. 5. 19..
 */
@Data
public class IntAccountTokenRes extends CommonVO {

    private static final long serialVersionUID = 8090526525424916678L;
    private String accessToken; //엑세스토큰
    private String refreshToken; //리플레쉬토큰
    private String scope;       //스코프
    private String tokenType;       //토큰타입
    private String expiresIn;   //유효기간
    private String useYn;       //사용여부(Y:사용,N:사용안함)
    private String deleteDate;  //삭제일시

}
