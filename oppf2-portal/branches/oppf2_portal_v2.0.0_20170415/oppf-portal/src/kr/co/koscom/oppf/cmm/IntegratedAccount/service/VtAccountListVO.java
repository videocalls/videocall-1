package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

import java.util.List;

/**
 * @author lee
 * @date 2017-02-22
 */
public class VtAccountListVO {
    private List<VtAccountVO> vtAccList;

    public List<VtAccountVO> getVtAccList() {
        return vtAccList;
    }
    public void setVtAccList(List<VtAccountVO> vtAccList) {
        this.vtAccList = vtAccList;
    }
}
