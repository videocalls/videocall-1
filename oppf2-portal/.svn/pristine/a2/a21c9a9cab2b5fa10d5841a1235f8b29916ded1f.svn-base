package kr.co.koscom.oppf.apt.simul.service.impl;

import kr.co.koscom.oppf.apt.simul.service.SimulatorManageService;
import kr.co.koscom.oppf.apt.simul.service.SimulatorManageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LSH on 2017. 3. 7..
 */
@Service("SimulatorManageService")
public class SimulatormanageServiceImpl implements SimulatorManageService {

    @Resource(name="SimulatorManageDAO")
    private SimulatorManageDAO simulatorManageDAO;

    @Override
    public Map<String, Object> selectSimulatorManageList(SimulatorManageVO paramVO) throws Exception {
        List<SimulatorManageVO> result = simulatorManageDAO.selectSimulatorManageList(paramVO);
        int totCnt = simulatorManageDAO.selectSimulatorManageListCnt(paramVO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultList", result);
        map.put("totCnt", totCnt);
        return map;
    }

    @Override
    public Map<String, Object> simulatorManageListExcel(SimulatorManageVO paramVO) throws Exception {
        List<SimulatorManageVO> result = simulatorManageDAO.simulatorManageListExcel(paramVO);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultList", result);
        return map;
    }

    @Override
    public Map<String, Object> simulatorCompPopupList(SimulatorManageVO paramVO) throws Exception {
        List<SimulatorManageVO> result = simulatorManageDAO.simulatorCompPopupList(paramVO);
        int totCnt = simulatorManageDAO.simulatorCompPopupListCnt(paramVO);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultList", result);
        map.put("totCnt", totCnt);
        return map;
    }

    @Override
    public SimulatorManageVO simulatorManageDtl(SimulatorManageVO paramVO) throws Exception {
        return simulatorManageDAO.simulatorManageDtl(paramVO);
    }

    @Override
    public int insertSimulatorManage(SimulatorManageVO paramVO) throws Exception {
        return simulatorManageDAO.insertSimulatorManage(paramVO);
    }

    @Override
    public int updateSimulatorManage(SimulatorManageVO paramVO) throws Exception {
        return simulatorManageDAO.updateSimulatorManage(paramVO);
    }

    @Override
    public int simulatorManageDtlCount(SimulatorManageVO paramVO) throws Exception {
        return simulatorManageDAO.simulatorManageDtlCount(paramVO);
    }

    @Override
    public int deleteSimulatorManage(SimulatorManageVO paramVO) throws Exception {
        return simulatorManageDAO.deleteSimulatorManage(paramVO);
    }
}
