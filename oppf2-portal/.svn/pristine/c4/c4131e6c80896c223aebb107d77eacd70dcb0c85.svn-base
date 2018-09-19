package kr.co.koscom.oppf.apt.stats.service.impl;

import kr.co.koscom.oppf.apt.stats.service.StatsTrafficVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * StatsServiceTrafficDAO
 * <p>
 * Created by chungyeol.kim on 2017-03-03.
 */
@Repository("StatsServiceTrafficDAO")
public class StatsServiceTrafficDAO extends ComAbstractDAO {
    public List<StatsTrafficVO> selectStatsTrafficApiNameList(StatsTrafficVO statsTrafficVO) throws Exception{
        return (List<StatsTrafficVO>) list("apt.StatsServiceTrafficDAO.selectStatsTrafficApiNameList", statsTrafficVO);
    }

    public List<StatsTrafficVO> selectStatsTrafficApiPlanNameList(StatsTrafficVO statsTrafficVO) throws Exception{
        return (List<StatsTrafficVO>) list("apt.StatsServiceTrafficDAO.selectStatsTrafficApiPlanNameList", statsTrafficVO);
    }

    public List<HashMap<String,Object>> selectStatsTrafficListForAll(StatsTrafficVO statsTrafficVO) throws Exception {
        return (List<HashMap<String,Object>>) list("apt.StatsServiceTrafficDAO.selectStatsTrafficListAll", statsTrafficVO);
    }

    public List<StatsTrafficVO> selectDayStdList(StatsTrafficVO statsTrafficVO) throws Exception {
        return (List<StatsTrafficVO>) list("apt.StatsServiceTrafficDAO.searchDayStdList", statsTrafficVO);
    }

    public List<HashMap<String,Object>> selectStatsTrafficList(StatsTrafficVO statsTrafficVO) throws Exception {
        return (List<HashMap<String,Object>>) list("apt.StatsServiceTrafficDAO.selectStatsTrafficList", statsTrafficVO);
    }
    
    public List<StatsTrafficVO> selectDayMonthYearStdList(StatsTrafficVO statsTrafficVO) throws Exception {
        return (List<StatsTrafficVO>) list("apt.StatsServiceTrafficDAO.selectDayMonthYearStdList", statsTrafficVO);
    }

    public List<StatsTrafficVO> selectStatsTrafficApiNameListForApp(StatsTrafficVO statsTrafficVO) throws Exception {
        return (List<StatsTrafficVO>) list("apt.StatsServiceTrafficDAO.selectStatsTrafficApiNameListForApp", statsTrafficVO);
    }

    public List<StatsTrafficVO> selectStatsTrafficSubCompanyListForApi(StatsTrafficVO statsTrafficVO) throws Exception {
        return (List<StatsTrafficVO>) list("apt.StatsServiceTrafficDAO.selectStatsTrafficSubCompanyListForApi", statsTrafficVO);
    }

}
