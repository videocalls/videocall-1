package kr.co.koscom.oppfm.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.koscom.oppfm.cmm.model.CommonVO;
import kr.co.koscom.oppfm.cmm.model.ValidationCreate;
import kr.co.koscom.oppfm.cmm.model.ValidationUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * AppCreateReq
 * <p>
 * Created by chungyeol.kim on 2017-05-23.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppCreateReq extends CommonVO {
    private static final long serialVersionUID = 1214190589180469991L;

    /* 일반회원 서비스 약관 프로파일 */
    @JsonIgnore
    private String customerRegNo;               // 회원 OP 등록 번호
    private String termsRegNo;                  // 약관 등록 번호
    private String subCompanyCodeId;            // 핀테크 기업 코드
    @JsonIgnore
    private String subCompanyName;              // 약관표기 핀테크 기업명
    @JsonIgnore
    private String termsPolicy;                 // 약관표기 동의 최종 기간
    private String termsAuthType;               // 약관 동의 방법
    @JsonIgnore
    private String termsChgDate;                // 약관 동의 일시
    @JsonIgnore
    private String termsStartDate;              // 약관 동의 시작 일시
    @JsonIgnore
    private String termsExpireDate;             // 약관 동의 종료 예정일
    @JsonIgnore
    private String termsAuthYn;                 // 동의서 동의 필요 여부
    @JsonIgnore
    private String termsFileRegNo;              // 동의서 파일 등록 번호
    @JsonIgnore
    private String termsFileStatus;             // 동의서 파일 처리 상태
    private String customerSignDn;              // 약관 동의 공인인증서
    private String customerSignData;            // 약관 동의 전자서명키
    private String customerTsaData;             // 약관 동의 시점확인키

    /* 일반회원 서비스(App) 프로파일 */
    private String appId;                       // 핀테크 기업 app ID
    @JsonIgnore
    private String serviceRegNo;                // 서비스 등록 번호

    /* 일반회원 ARS 인증 정보 */
    @JsonIgnore
    private String termsArsRegNo;               // 약관 ARS 등록 번호
    @JsonIgnore
    private String customerPhone;               // 회원 휴대폰 번호
    private String arsResultCd;                 // ARS 처리 결과코드
    private String arsResultMessage;            // ARS 결과 메세지
    private String arsTrCd;                     // ARS 거래 코드
    private String arsTxtNo;                    // ARS 전문고유번호
    private String arsRecordData;               // ARS 녹취 데이터

//    @NotBlank(message = "{NotBlank.validation.message}", groups = {ValidationCreate.class, ValidationUpdate.class})
    private List<AppAccountRes> accountList;

    @JsonIgnore
    private String termsType;                   // 제공 동의 종류 (common : (가)동의서, app : (나)동의서)

    private String reqHtml;                     // html 파일 내용

    private String termsCreatedYn;               // N 이면 이미 동의서 등록됨, Y 이면 동의서 등록해야함

    @JsonIgnore
    private String oldTermsRegNo;
}
