package kr.co.koscom.oppfm.member.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.koscom.oppfm.account.model.SptCustomerCompanyTermsProfileRes;
import kr.co.koscom.oppfm.cmm.model.CommonVO;
import kr.co.koscom.oppfm.cmm.model.ValidationSelect;
import kr.co.koscom.oppfm.terms.model.TermsRes;
import lombok.Data;


/**
 * UsrReq
 * 
 * @author 판광 on 2017-05-15.
 * * Modify by sh.lee on 2017-05-16.
 *
 */
@Data
public class MemberReq extends CommonVO {

	private static final long serialVersionUID = 4731998199226823876L;
	
	// 회원 등록 번호
   @NotNull(message = "{NotNull.validation.message}", groups = {ValidationSelect.class})
   private String customerRegNo;
    
   private String customerId;                      //회원 아이디

   private String customerPassword;                //회원 비밀번호
   
   private String newPassword;                		// 새 비밀번호

   private String customerPasswordConfirm;          //회원 비밀번호 확인

   @JsonIgnore
   private String customerExpirePasswordDate;      //비밀번호 변경 예정일

   @JsonIgnore
   private String customerFinalPasswordDate;       //비밀번호 변경 최종 예정일

   private String customerPhone="";                //회원 휴대폰 번호

   private String customerEmail="";                //회원 이메일 주소



   /* otp 정보 */
   private String customerOtpYn;
   private String customerOtpId;
   private String customerOtpAction;

   /* CI 정보 */
   @NotNull(message = "{NotNull.validation.message}", groups = {ValidationSelect.class})
   private String customerVerify;                  // CI, DN 값
   private String customerVerifyCi;                /* 회원 CI */
   private String customerVerifyDn;
   private String customerVerifyType; /* 회원검증종류  */
   private String customerVerifyDate; /* 회원검증일시 */
   private String customerVerifyDeleteDate;


   /*terms 정보*/
   private String customerTermsTypeName;      //회원동의종류명(제목)
   private String customerTermsType;          //회원동의종류
   private String customerTermsContentRegSeq;                  /* 동의서 약관내용 등록번호 */
   private String customerTermsSystemKind;                     /* 약관 시스템 구분 */
   private String customerTermsContentVer;                     /* 버전 */
   private String customerTermsContent;                        /* 내용 */
   private String customerEmailYn;            //동의서email고지여부
   private String customerEmailDate;          //동의서email고지일자

   private String customerTermsAuthYn;
   private String customerTermsDeleteDate;

   private List<TermsRes> customerTermsList;                   /* 이용약관 list */
   
   private String customerRegNoPrefix;             /* 일반회원일 경우 회원번호 : C 기업일경우 : O 관리자의경우 : A 비회원일경우 : T */


   private String customerTempPasswordYn;          /* 회원 비밀번호 임시여부 */
   private String customerChgPasswordDate;         /* 회원 비밀번호 변경일자 */
   private int customerPasswordFailCnt;            /* 비밀번호 실패횟수 */
   private String customerRegStatus;               /* 회원 가입 상태 */
   private int customerStep;                       /* 회원 가입 Step */
   private String customerNameKor;                 /* 회원명 한글 */
   private String customerNameEng;                 /* 회원명 영문 */
   private String customerEmailAuthYn;             /* 회원 이메일 인증여부 */
   private String customerEmailRegDate;            /* 회원 이메일 인증일시 */
   private String customerBirthDay;                /* 생년월일 */
   private String customerSex;                     /* 성별 */
   private String customerEmailReceiveYn;          /* 이메일 수신동의여부 */
   private String customerEmailReceiveDate;        /* 이메일 수신동의 여부일시 */
   private String customerRegDate;                 /* 회원가입일시 */
   private String deleteDate;                      /* 회원탈퇴일시 */
   private String temporaryMemberYn;				/* 임시회원여부 */
   
   private String deviceType;						/* 디바이스 구분 */
   private String deviceUniqueId;					/* 디바이스 식별 아이디 */
   private String companyCodeId;					/* 기업 코드 */
   private String appKey;							/* app_key */

   private List<SptCustomerCompanyTermsProfileRes> companyTermsList;


}
