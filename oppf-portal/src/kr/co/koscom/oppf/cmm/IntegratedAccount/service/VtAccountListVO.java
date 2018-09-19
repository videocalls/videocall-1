package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lee
 * @date 2017-02-22
 */
public class VtAccountListVO implements Cloneable{
    private List<VtAccountVO> vtAccList;

    public List<VtAccountVO> getVtAccList() {
        return vtAccList;
    }
    public void setVtAccList(List<VtAccountVO> vtAccList) {
        this.vtAccList = vtAccList;
    }

    public VtAccountListVO clone() throws CloneNotSupportedException{
        VtAccountListVO vtAccountListVO = (VtAccountListVO)super.clone();
        List<VtAccountVO> vtAccList = new ArrayList<VtAccountVO>();
        for(VtAccountVO vo : vtAccountListVO.getVtAccList()){
            vtAccList.add(vo);
        }
        vtAccountListVO.setVtAccList(vtAccList);
        return vtAccountListVO;
    }

}
