package kr.co.koscom.oppf.apt.simul.service.impl;

import kr.co.koscom.oppf.apt.simul.service.SimulatorManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComSecondAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LSH on 2017. 3. 7..
 */
@Repository("SimulatorManageDAO")
public class SimulatorManageDAO extends ComSecondAbstractDAO{
    public List<SimulatorManageVO> selectSimulatorManageList(SimulatorManageVO simulatorManageVO) throws Exception{
        return (List<SimulatorManageVO>) list("apt.SimulatorManageDAO.selectSimulatorManageList", simulatorManageVO);
    }

    public int selectSimulatorManageListCnt(SimulatorManageVO simulatorManageVO) throws Exception{
        return (Integer) select("apt.SimulatorManageDAO.selectSimulatorManageListCnt", simulatorManageVO);
    }

    public List<SimulatorManageVO> simulatorManageListExcel(SimulatorManageVO simulatorManageVO) throws Exception{
        return (List<SimulatorManageVO>) list("apt.SimulatorManageDAO.simulatorManageListExcel", simulatorManageVO);
    }

    public List<SimulatorManageVO> simulatorCompPopupList(SimulatorManageVO simulatorManageVO) throws Exception{
        return (List<SimulatorManageVO>) list("apt.SimulatorManageDAO.simulatorCompPopupList", simulatorManageVO);
    }

    public int simulatorCompPopupListCnt(SimulatorManageVO simulatorManageVO) throws Exception{
        return (Integer) select("apt.SimulatorManageDAO.simulatorCompPopupListCnt", simulatorManageVO);
    }

    public SimulatorManageVO simulatorManageDtl(SimulatorManageVO simulatorManageVO) throws Exception{
        return (SimulatorManageVO) select("apt.SimulatorManageDAO.simulatorManageDtl", simulatorManageVO);
    }

    public int insertSimulatorManage(SimulatorManageVO simulatorManageVO) throws Exception{
        return (Integer) update("apt.SimulatorManageDAO.insertSimulatorManage", simulatorManageVO);
    }

    public int updateSimulatorManage(SimulatorManageVO simulatorManageVO) throws Exception{
        return (Integer) update("apt.SimulatorManageDAO.updateSimulatorManage", simulatorManageVO);
    }

    public int simulatorManageDtlCount(SimulatorManageVO simulatorManageVO) throws Exception{
        return (Integer) select("apt.SimulatorManageDAO.simulatorManageDtlCount", simulatorManageVO);
    }

    public int deleteSimulatorManage(SimulatorManageVO simulatorManageVO) throws Exception{
        return (Integer) delete("apt.SimulatorManageDAO.deleteSimulatorManage", simulatorManageVO);
    }
}
