package kr.co.koscom.oppfm.account.model;

import java.util.List;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

@Data
public class AccountRes extends CommonVO{

	private static final long serialVersionUID = 5491698436874167067L;
	/* 공통쓰임 관련 */
    private String companyCodeId;        /* 기업코드.기업코드 */
    private String companyCodeName;        /* 기업코드.기업이름 */
	
	private String customerRegNo;                /* 일반회원가상계좌프로파일.회원OP가입번호 */
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
	
    private String companyCodeRegNo;     /* 기업코드.기업코드등록번호 */
    private String companyCodeType;      /* 기업코드.기업코드표구분 */
    private String companyCodeKind;      /* 기업코드.기업종류 */
    private String companyCodeKindNm;    /* 기업코드.기업종류명 */
    private String companyNameKor;       /* 기업코드.기업이름한글 */
    private String companyNameEng;       /* 기업코드.기업이름영문 */
    private String companyNameKorAlias;  /* 기업코드.기업이름Alias한글 */
    private String companyNameEngAlias;  /* 기업코드.기업이름Alias영문 */
    
    private String companyProfileRegNo;  
    
    private List<AccountRes> AccountResList;
}
