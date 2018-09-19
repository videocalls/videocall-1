package kr.co.koscom.oppf.apt.queue.service;

import java.util.List;
import java.util.Map;

/**
 * Created by LSH on 2017. 3. 8..
 */
public interface FixQueueManageService {
    Map<String,Object> selectFixQueueManageList(FixQueueManageVO paramVO) throws Exception;

    List<FixQueueManageVO> selectInitiatorServerIdList(FixQueueManageVO paramVO) throws Exception;

    Map<String,Object> selectQueueCompanyList(FixQueueManageVO paramVO) throws Exception;

    public Map<String, Object> selectFixQueueManageListExcel(FixQueueManageVO paramVO) throws Exception;
}
