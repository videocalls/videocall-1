package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

/**
 * @author lee
 * @date 2017-02-22
 */
public class VtAccountVO {
    private String comId;
    private String vtAccNo;
    private String vtAccAlias;
    private String endpoint;

    public String getComId() {
        return comId;
    }
    public void setComId(String comId) {
        this.comId = comId;
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
    public String getEndpoint() {
        return endpoint;
    }
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
