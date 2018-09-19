package kr.co.koscom.oppfm.account.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;



@Data
public class ComOauthTokenRes extends CommonVO{

    private static final long serialVersionUID = 417831148051723940L;

	/* 공통 */
    private String customerRegNo; //사용자번호
    
    /* DB정보 & Response OAuth Token */
    private String regNo;       //등록번호(yyyymmdd000)
    private String accessToken; //엑세스토큰
    private String clientId;    //클라이언트ID(=API Key)
    private String secretId;    //클라이언트시크릿ID
    private String scope;       //유효기간
    private String useYn;       //사용여부(Y:사용,N:사용안함)
    private String deleteDate;  //삭제일시
    private String updateDate;  //수정일시
    private String updateId;    //수정자ID
    private String createDate;  //생성일시
    private String createId;    //생성자ID
    private String tokenType;
    private String expiresIn;
    
    /* Response OAuth Token */
    private String access_token;
    private String token_type;
    private int expires_in;
    
}