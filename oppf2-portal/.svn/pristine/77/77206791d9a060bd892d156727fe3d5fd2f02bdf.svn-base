package kr.co.koscom.oppf.apt.fixMsg.service.impl;

import kr.co.koscom.oppf.apt.dataSet.service.DataSetManageVO;
import kr.co.koscom.oppf.apt.fixMsg.service.FixMessageVO;
import kr.co.koscom.oppf.apt.fixMsg.service.SearchFixMessageVO;
import kr.co.koscom.oppf.apt.myp.aptMyInfo.web.AptMyInfoController;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.cmm.service.impl.ComSecondAbstractDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LSH on 2017. 3. 2..
 */
@Repository("ViewFixMessageDAO")
public class ViewFixMessageDAO extends ComSecondAbstractDAO {
    private static final Logger log = Logger.getLogger(ViewFixMessageDAO.class);

    public List<FixMessageVO> selectFixMessageList(SearchFixMessageVO searchFixMessageVO) throws Exception{
        return (List<FixMessageVO>) list("apt.ViewFixMessageDAO.selectFixMessageList", searchFixMessageVO);
    }

    public int selectFixMessageListCnt(SearchFixMessageVO searchFixMessageVO) throws Exception{
        return (Integer) select("apt.ViewFixMessageDAO.selectFixMessageListCnt", searchFixMessageVO);
    }

    public List<FixMessageVO> selectFixMessageInitList(SearchFixMessageVO searchFixMessageVO) throws Exception{
        return (List<FixMessageVO>) list("apt.ViewFixMessageDAO.selectFixMessageInitList", searchFixMessageVO);
    }

    public int selectFixMessageListInitCnt(SearchFixMessageVO searchFixMessageVO) throws Exception{
        return (Integer) select("apt.ViewFixMessageDAO.selectFixMessageListInitCnt", searchFixMessageVO);
    }

    public List<FixMessageVO> viewFixMessageAccountExcel(SearchFixMessageVO searchFixMessageVO) throws Exception{
        return (List<FixMessageVO>) list("apt.ViewFixMessageDAO.viewFixMessageAccountExcel", searchFixMessageVO);
    }

    public List<FixMessageVO> viewFixMessageInitiatorExcel(SearchFixMessageVO searchFixMessageVO) {
        return (List<FixMessageVO>) list("apt.ViewFixMessageDAO.viewFixMessageInitiatorExcel", searchFixMessageVO);

    }

    public FixMessageVO selectAcceptorDtl(FixMessageVO paramVO) {
        return (FixMessageVO) select("apt.ViewFixMessageDAO.selectAcceptorDtl", paramVO);
    }

    public FixMessageVO selectInitiatorDtl(FixMessageVO paramVO) {
        return (FixMessageVO) select("apt.ViewFixMessageDAO.selectInitiatorDtl", paramVO);
    }

    public List<SearchFixMessageVO> selectCommonCodeName(SearchFixMessageVO paramVO) {
        return (List<SearchFixMessageVO>) list("apt.ViewFixMessageDAO.selectCommonCodeName", paramVO);
    }
}
