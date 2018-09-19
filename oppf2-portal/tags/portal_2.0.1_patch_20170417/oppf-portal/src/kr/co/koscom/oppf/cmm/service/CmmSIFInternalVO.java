package kr.co.koscom.oppf.cmm.service;

import java.io.Serializable;
import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
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
public class CmmSIFInternalVO extends ComDefaultListVO implements Serializable {
    
    private List<AccListAttributeVO> accList;
    
    private String readyState = "";
    
    private String status = "";
    
    private String statusText = "";

    public List<AccListAttributeVO> getAccList() {
        return accList;
    }

    public void setAccList(List<AccListAttributeVO> accList) {
        this.accList = accList;
    }

    public String getReadyState() {
        return readyState;
    }

    public void setReadyState(String readyState) {
        this.readyState = readyState;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
    
}
