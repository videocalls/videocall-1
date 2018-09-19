package kr.co.koscom.oppf.apt.fixMsg.service.impl;

import kr.co.koscom.oppf.apt.fixMsg.service.FixMessageVO;
import kr.co.koscom.oppf.apt.fixMsg.service.SearchFixMessageVO;
import kr.co.koscom.oppf.apt.fixMsg.service.ViewFixMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LSH on 2017. 3. 2..
 */
@Service("ViewFixMessageService")
public class ViewFixMessageServiceImpl implements ViewFixMessageService{
    @Resource(name = "ViewFixMessageDAO")
    private ViewFixMessageDAO viewFixMessageDAO;


    @Override
    public Map<String, Object> selectFixMessageList(SearchFixMessageVO searchFixMessageVO) throws Exception {
        List<FixMessageVO> result = viewFixMessageDAO.selectFixMessageList(searchFixMessageVO);
        int totCnt = viewFixMessageDAO.selectFixMessageListCnt(searchFixMessageVO);


        Map<String, Object> map = new HashMap<String, Object>();

        map.put("resultList", result);
        map.put("totCnt", totCnt);

        return map;
    }

    @Override
    public Map<String, Object> viewInitFixMessageList(SearchFixMessageVO searchFixMessageVO) throws Exception {
        List<FixMessageVO> result = viewFixMessageDAO.selectFixMessageInitList(searchFixMessageVO);
        int totCnt = viewFixMessageDAO.selectFixMessageListInitCnt(searchFixMessageVO);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("resultList", result);
        map.put("totCnt", totCnt);

        return map;
    }

    @Override
    public Map<String, Object> viewFixMessageAccountExcel(SearchFixMessageVO paramVO) throws Exception {
        List<FixMessageVO> result = viewFixMessageDAO.viewFixMessageAccountExcel(paramVO);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultList", result);
        return map;
    }

    @Override
    public Map<String, Object> viewFixMessageInitiatorExcel(SearchFixMessageVO paramVO) throws Exception {
        List<FixMessageVO> result = viewFixMessageDAO.viewFixMessageInitiatorExcel(paramVO);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultList", result);
        return map;
    }

    @Override
    public FixMessageVO viewFixMessageDtlAcceptorPopup(FixMessageVO paramVO) throws Exception {
        return viewFixMessageDAO.selectAcceptorDtl(paramVO);
    }

    @Override
    public FixMessageVO viewFixMessageDtlInitiatorPopup(FixMessageVO paramVO) throws Exception {
        return viewFixMessageDAO.selectInitiatorDtl(paramVO);
    }

    @Override
    public List<SearchFixMessageVO> selectCommonCodeName(SearchFixMessageVO paramVO) throws Exception {
        return viewFixMessageDAO.selectCommonCodeName(paramVO);
    }


}
