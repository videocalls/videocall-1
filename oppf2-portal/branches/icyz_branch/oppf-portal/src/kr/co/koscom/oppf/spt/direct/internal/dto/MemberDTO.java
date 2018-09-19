package kr.co.koscom.oppf.spt.direct.internal.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;


@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
public class MemberDTO implements Serializable {
    private String customerRegNo;
    
    private String customerRegNoPrefix = "C";       //회원 OP 등록 번호 prefix( 일반사용자 : C, 기업 : O, admin : A )

    private String customerId;

    private String customerPassword;

    private boolean customerTempPasswordYn;

    private Date customerChgPasswordDate;

    private String customerExpirePasswordDate;

    private String customerFinalPasswordDate;

    private long customerOtpFailCnt;

    private long customerPasswordFailCnt;

    private String customerRegStatus;

    private String customerStep;

    private String customerNameKor;

    private String customerNameEng;

    private String customerPhone;

    private String customerEmail;

    private boolean customerEmailAuthYn;

    private Date customerEmailRegDate;

    private String customerBirthDay;

    private String customerSex;

    private boolean customerEmailReceiveYn;

    private Date customerEmailReceiveDate;

    private boolean customerMobilePushYn;

    private boolean customerDatasetLockYn;

    private boolean customerWithdrawalProcYn;
    
    private String customerJoinType;
    
    private boolean temporaryMemberYn;
    
    private boolean integrationAccountYn;

    private Date customerRegDate;
    
    private Date deleteDate;

    private Date createDate;

    private String createId;

    private Date updateDate;

    private String updateId;

    private String userCi;
    
    private String userDn;
    
    private String customerVerifyType;
    
    private String customerVerify;
    

}
