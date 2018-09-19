package kr.co.koscom.oppfm.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * AppTermsRes
 * <p>
 * Created by chungyeol.kim on 2017-05-19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppTermsRes extends CommonVO {

    private static final long serialVersionUID = -3232953573689607415L;

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
    private String appName;                 // 앱 이름

    private String termsAuthType;           // 인증 타입
    private String termsStatus;             // 인증 상태

    private String customerSignDn;          // 공인인증 DN 값
    private String customerSignData;        // 전사저명키 / ARS 인증번호
    private String customerTsaData;         // 시점확인키 / 인증 ID

    private String termsPolicyYear;         // 약관표기동의최종기간

    @JsonIgnore
    private String customerRegNo;
    @JsonIgnore
    private String customerId;
    @JsonIgnore
    private String serviceRegNo;

}
