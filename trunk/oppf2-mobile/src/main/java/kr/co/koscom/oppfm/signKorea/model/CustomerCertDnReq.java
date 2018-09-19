package kr.co.koscom.oppfm.signKorea.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author unionman
 *
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class CustomerCertDnReq extends CommonVO{
    
    /**
     * 
     */
    private static final long serialVersionUID = -5448042193826446472L;
    
    
    /** 사용자 Dn 정보 */
    private String customerDn;
    
    /** 회원 OP 등록 번호 */
    @JsonIgnore
    private String customerRegNo;
    /** 회원 ID */
    @JsonIgnore
    private String customerId;

    
}
