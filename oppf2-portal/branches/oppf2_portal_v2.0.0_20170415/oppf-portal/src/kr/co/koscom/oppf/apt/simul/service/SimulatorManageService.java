package kr.co.koscom.oppf.apt.simul.service;

import java.util.Map;

/**
 * Created by LSH on 2017. 3. 6..
 */
public interface SimulatorManageService {
    Map<String,Object> selectSimulatorManageList(SimulatorManageVO paramVO) throws Exception;

    Map<String,Object> simulatorManageListExcel(SimulatorManageVO paramVO) throws Exception;

    Map<String,Object> simulatorCompPopupList(SimulatorManageVO paramVO) throws Exception;

    SimulatorManageVO simulatorManageDtl(SimulatorManageVO paramVO) throws Exception;

    int insertSimulatorManage(SimulatorManageVO paramVO) throws Exception;

    int updateSimulatorManage(SimulatorManageVO paramVO) throws Exception;

    int simulatorManageDtlCount(SimulatorManageVO paramVO) throws Exception;

    int deleteSimulatorManage(SimulatorManageVO paramVO) throws Exception;
}
