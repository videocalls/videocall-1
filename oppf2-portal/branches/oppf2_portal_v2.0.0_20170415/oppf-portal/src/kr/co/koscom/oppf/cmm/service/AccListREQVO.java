package kr.co.koscom.oppf.cmm.service;

import java.io.Serializable;
import java.util.List;

/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CommonLoginVO.java
* @Comment  : 공통 로그인 VO
* @author   : 신동진
* @since    : 2016.04.29
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.04.29  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class AccListREQVO extends ComDefaultListVO implements Serializable {
    private String realAccNo;
    private String realAccType;
    private String vtAccNo;
    private String vtAccAlias;
    
    public String getRealAccNo() {
        return realAccNo;
    }
    public void setRealAccNo(String realAccNo) {
        this.realAccNo = realAccNo;
    }
    public String getRealAccType() {
        return realAccType;
    }
    public void setRealAccType(String realAccType) {
        this.realAccType = realAccType;
    }
    public String getVtAccNo() {
        return vtAccNo;
    }
    public void setVtAccNo(String vtAccNo) {
        this.vtAccNo = vtAccNo;
    }
    public String getVtAccAlias() {
        return vtAccAlias;
    }
    public void setVtAccAlias(String vtAccAlias) {
        this.vtAccAlias = vtAccAlias;
    }
    
}
