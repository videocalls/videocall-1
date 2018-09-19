package kr.co.koscom.oppfm.account.model;

import kr.co.koscom.oppfm.cmm.model.CommonSearchReq;
import lombok.Data;

@Data
public class AccountReq extends CommonSearchReq {

	private static final long serialVersionUID = -6010794151811914742L;
	private String customerRegNo;				/* 일반회원가상계좌프로파일.회원OP가입번호 */
	private String customerVtaccountNo;			/* 일반회원가상계좌프로파일.가상계좌번호 */
    private String listOrder = "a.create_date desc"; //페이징목록정렬
    private String companyCodeRegNo = "";
    private String companyProfileRegNo = "";
}
