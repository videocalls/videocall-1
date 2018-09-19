package kr.co.koscom.oppfm.account.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;


@Data
public class SvcApplReq extends CommonVO{

	private static final long serialVersionUID = 3580658916662189337L;

	/* 공통쓰임 관련 */
    private String companyCodeId;        /* 기업프로파일.기업코드 */
    private String customerRegNo;                /* 일반회원가상계좌프로파일.회원OP가입번호 */
        

}