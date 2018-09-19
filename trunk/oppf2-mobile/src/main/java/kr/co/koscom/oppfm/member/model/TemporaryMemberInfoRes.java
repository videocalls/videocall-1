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
public class TemporaryMemberInfoRes extends CommonVO {

	private static final long serialVersionUID = 8190738583281991094L;

	// 회원 등록 번호
	private String customerRegNo;
	
	// 회원 아이디
	private String customerId;
	
	// 회원 등록 상태
	private String customerRegStatus;
	
	// 회원 이름
	private String customerNameKor;
	
	// 회원 탈퇴 처리 여부
	private String customerWithdrawalProcYn;
	
	// 회원 가입 경로
	private String customerJoinType;
	
	// 임시 회원 여부
	private String temporaryMemberYn;
	
	// 회원 CI
	private String customerVerifyCi;
}
