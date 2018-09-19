package kr.co.koscom.oppf.spt.direct.terms.model;

import java.io.Serializable;

import kr.co.koscom.oppf.spt.direct.common.model.Resp;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class TermsRevokeResponse implements Serializable {
    
    private Resp resp;

}
