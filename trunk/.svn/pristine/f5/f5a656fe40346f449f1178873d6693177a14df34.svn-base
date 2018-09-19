package kr.co.koscom.oppfm.account.model;

import java.util.List;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;


@Data
public class SvcApplRes extends CommonVO{
    
	private static final long serialVersionUID = 5350263178263028149L;

	
	/* 공통쓰임 관련 */
    private String companyCodeId;        /* 기업프로파일.기업코드 */
    private String customerRegNo;                /* 일반회원가상계좌프로파일.회원OP가입번호 */
        
    private String companyNameKor;       /* 기업프로파일.기업이름한글 */
    private String companyNameEng;       /* 기업프로파일.기업이름영문 */
    private String companyNameKorAlias;  /* 기업프로파일.기업이름Alias한글 */
    private String companyNameEngAlias;  /* 기업프로파일.기업이름Alias영문 */
    private String companyCodeIdNm;              /* 일반회원가상계좌프로파일.기업코드명 */
    private String customerRealaccountNo;        /* 일반회원가상계좌프로파일.실계좌번호 */
    private String customerRealaccountNoMask;    /* 일반회원가상계좌프로파일.실계좌번호 마스크 */
    private String customerRealaccountNoEncryption; /* 일반회원가상계좌프로파일.실계좌번호암호화 */
    private String customerVtaccountNo;          /* 일반회원가상계좌프로파일.가상계좌번호 */
    private String customerVtaccountAlias;       /* 일반회원가상계좌프로파일.가상계좌별칭 */
    private String customerVtaccountStatus;      /* 일반회원가상계좌프로파일.가상계좌상태 */
    private String customerRealaccountType;      /* 일반회원가상계좌프로파일.실계좌유형 */
    private String customerRealaccountTypeNm;    /* 일반회원가상계좌프로파일.실계좌유형명 */
    private String customerRealaccountTypeNmEng; /* 일반회원가상계좌프로파일.실계좌유형영문명 */
    private String customerVtaccountRegDate;     /* 일반회원가상계좌프로파일.가상계좌발급일시 */
    private String customerVtaccountExpireDate;  /* 일반회원가상계좌프로파일.가상계좌폐기일시 */

	private String customerWithdrawalProcYn;  /* 회원 탈퇴 처리 여부 */

    /**
     * 핀테크서비스신청:핀테크서비스선택
     */
    private String serviceRegNo;
    private String companyProfileRegNo;
    
    //금융투자사 정보
    private List<String> searchCompanyCodeId;
	private String searchCompanyCodeIdAllYn;
	
	//조회 appIds
	private List<String> searchAppId;
	
	//사용여부
	private String searchUseYn;
	
	//동의서 최종 기간
	private String termsPolicy;
	
	/* 메인 TOP 5용 */
	private String appId;
	private String appName;
	private String appDlUrl;
	private String appKey;

	/* 데이터 클랜징용 */
	private String termsRegNo;
	

	/* 검색(DEV)용 */
	private String searchCustomerRegNo;
	private String searchServiceRegNo;

}