package kr.co.koscom.oppfm.member.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * MemberVerifyRes
 * 
 * @author LHT on 2017-05-23.
 *
 */


@Data
public class MemberVerifyRes extends CommonVO {

	private static final long serialVersionUID = -6952438708281575073L;
	private String customerRegNo;
	private String customerId;
	private String customerVerifyType;
	private String customerVerify;
	private String customerVerifyDate;
	private String deleteDate;
	private String customerNameKor;

}
