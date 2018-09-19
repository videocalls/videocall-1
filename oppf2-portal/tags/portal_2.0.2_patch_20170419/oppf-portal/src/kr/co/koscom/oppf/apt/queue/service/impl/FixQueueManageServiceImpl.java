package kr.co.koscom.oppf.apt.queue.service.impl;

import kr.co.koscom.oppf.apt.queue.service.FixQueueManageService;
import kr.co.koscom.oppf.apt.queue.service.FixQueueManageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by LSH on 2017. 3. 8..
 */
@Service("FixQueueManageService")
public class FixQueueManageServiceImpl implements FixQueueManageService{

    @Resource(name="FixQueueManageDAO")
    private FixQueueManageDAO fixQueueManageDAO;

    @Override
    public Map<String, Object> selectFixQueueManageList(FixQueueManageVO paramVO) throws Exception {
        List<FixQueueManageVO> result = fixQueueManageDAO.selectFixQueueManageList(paramVO);
        int totCnt = fixQueueManageDAO.selectFixQueueManageListCnt(paramVO);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultList", result);
        map.put("totCnt", totCnt);
        return map;
    }

    @Override
    public List<FixQueueManageVO> selectInitiatorServerIdList(FixQueueManageVO paramVO) throws Exception {
        return fixQueueManageDAO.selectInitiatorServerIdList(paramVO);
    }

    @Override
    public Map<String, Object> selectQueueCompanyList(FixQueueManageVO paramVO) throws Exception {
        List<FixQueueManageVO> result = fixQueueManageDAO.selectQueueCompanyList(paramVO);
        int totCnt = fixQueueManageDAO.selectQueueCompanyListCnt(paramVO);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultList", result);
        map.put("totCnt", totCnt);
        return map;
    }

    @Override
    public Map<String, Object> selectFixQueueManageListExcel(FixQueueManageVO paramVO) throws Exception{
        List<FixQueueManageVO> result = fixQueueManageDAO.selectFixQueueManageListExcel(paramVO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultList", result);
        return map;
    }

}
