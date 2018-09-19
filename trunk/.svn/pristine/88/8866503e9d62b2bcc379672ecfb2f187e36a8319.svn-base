package kr.co.koscom.oppfm.app.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * AppTermsFileProfileReq
 * <p>
 * Created by chungyeol.kim on 2017-05-25.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppTermsFileProfileReq extends CommonVO {
    private static final long serialVersionUID = 1071605857079739585L;

    private String customerRegNo;               // 회원 OP 등록 번호
    private String termsFileType;               // 동의서 파일 종류
    private String termsFileRegNo;              // 동의서 파일 등록 번호
    private byte[] termsFileData;               // 동의서 파일
    private String dataHash;                    // TSA HASH
}
