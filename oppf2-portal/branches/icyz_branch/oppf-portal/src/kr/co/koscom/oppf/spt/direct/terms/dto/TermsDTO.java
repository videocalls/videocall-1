package kr.co.koscom.oppf.spt.direct.terms.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class TermsDTO implements Serializable {

    private String termsType;
    private String termsContentRegSeq;
    private String termsContentVer;
    private String termsContent;

    // 금투사용 서비스 약관일 경우
    private String companyCodeId;

    private Date createDate;
    private String createId;
    private Date updateDate;
    private String updateId;

}
