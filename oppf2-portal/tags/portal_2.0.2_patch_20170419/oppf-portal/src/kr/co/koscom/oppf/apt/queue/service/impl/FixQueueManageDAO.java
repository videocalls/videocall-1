package kr.co.koscom.oppf.apt.queue.service.impl;

import kr.co.koscom.oppf.apt.queue.service.FixQueueManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComSecondAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LSH on 2017. 3. 8..
 */
@Repository("FixQueueManageDAO")
public class FixQueueManageDAO extends ComSecondAbstractDAO{
    public List<FixQueueManageVO> selectFixQueueManageList(FixQueueManageVO paramVO) throws Exception{
        return (List<FixQueueManageVO>) list("apt.FixQueueManageDAO.selectFixQueueManageList", paramVO);
    }

    public int selectFixQueueManageListCnt(FixQueueManageVO paramVO) throws Exception{
        return (Integer) select("apt.FixQueueManageDAO.selectFixQueueManageListCnt", paramVO);
    }

    public List<FixQueueManageVO> selectInitiatorServerIdList(FixQueueManageVO paramVO) {
        return (List<FixQueueManageVO>) list("apt.FixQueueManageDAO.selectInitiatorServerIdList", paramVO);
    }

    public List<FixQueueManageVO> selectQueueCompanyList(FixQueueManageVO paramVO) {
        return (List<FixQueueManageVO>) list("apt.FixQueueManageDAO.selectQueueCompanyList", paramVO);
    }

    public int selectQueueCompanyListCnt(FixQueueManageVO paramVO) throws Exception{
        return (Integer) select("apt.FixQueueManageDAO.selectQueueCompanyListCnt", paramVO);
    }

    public List<FixQueueManageVO> selectFixQueueManageListExcel(FixQueueManageVO paramVO) throws Exception{
        return (List<FixQueueManageVO>) list("apt.FixQueueManageDAO.selectFixQueueManageListExcel", paramVO);
    }
}
