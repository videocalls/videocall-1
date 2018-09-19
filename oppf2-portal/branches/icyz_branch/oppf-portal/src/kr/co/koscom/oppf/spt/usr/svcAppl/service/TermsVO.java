package kr.co.koscom.oppf.spt.usr.svcAppl.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * AppVO
 * <p>
 * Created by chungyeol.kim on 2017-06-15.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TermsVO extends ComDefaultListVO {
    private String customerName;            // 사용자 이름
    private String customerBirthDay;        // 사용사 생년월일
    private String customerPhone;           // 사용자 전화번호
    private String customerEmail;           // 사용자 이메일

    private List<String> pubCompanyList;    // 정보제공 금투사 이름 리스트
    private String termsRegNo;              // 동의서 등록 번호
    private String termsCreateDate;         // 제공동의 등록일
    private String termsStartDate;          // 제공동의 유효기간 시작일
    private String termsEndDate;            // 제공동의 유효기간 종료일

    private String subCompanyCodeId;        // 정보를 제공 받을 기업 코드
    private String subCompanyName;          // 정보를 제공 받을 기업 이름

    private String termsAuthType;           // 인증 타입
    private String termsStatus;             // 인증 상태

    private String customerSignDn;          // 공인인증 DN 값
    private String customerSignData;        // 전사저명키 / ARS 인증번호
    private String customerTsaData;         // 시점확인키 / 인증 ID

    private String termsPolicyYear;         // 약관표기동의최종기간
    
    private String customerRegNo;               // 회원 OP 등록 번호

    private String termsPolicy;                 // 약관표기 동의 최종 기간
    private String termsChgDate;                // 약관 동의 일시
    private String termsExpireDate;             // 약관 동의 종료 예정일
    private String termsAuthYn;                 // 동의서 동의 필요 여부
    private String termsFileRegNo;              // 동의서 파일 등록 번호
    private String termsFileStatus;             // 동의서 파일 처리 상태

    /* 일반회원 서비스(App) 프로파일 */
    private String appId;                       // 핀테크 기업 app ID
    private String serviceRegNo;                // 서비스 등록 번호

    /* 일반회원 ARS 인증 정보 */
    private String termsArsRegNo;               // 약관 ARS 등록 번호
    private String arsResultCd;                 // ARS 처리 결과코드
    private String arsResultMessage;            // ARS 결과 메세지
    private String arsTrCd;                     // ARS 거래 코드
    private String arsTxtNo;                    // ARS 전문고유번호
    private String arsRecordData;               // ARS 녹취 데이터

    private String termsType;                   // 제공 동의 종류 (common : (가)동의서, app : (나)동의서)
    private String reqHtml;                     // html 파일 내용
    private String termsCreatedYn;               // N 이면 이미 동의서 등록됨, Y 이면 동의서 등록해야함
    private String oldTermsRegNo;

    private String filePath;
    private String fileName;
    private String fileNameOriginal;
    private String fileNmMarkOriginal = "-original";
    private String resTsaHashValue;

    private List<AppAccountVO> appAccountList;
    private String customerId;
    private int dateCount;
}
