package kr.co.koscom.oppfm.app.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * AppTermsArsRes
 * <p>
 * Created by chungyeol.kim on 2017-05-23.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppTermsArsRes extends CommonVO {
    private static final long serialVersionUID = -2656996923463757654L;

    private String arsResultCode;
    private String arsResultMessage;
    private String arsTransactionCode;
    private String arsTxtNo;
    private String arsRecordData;
}
