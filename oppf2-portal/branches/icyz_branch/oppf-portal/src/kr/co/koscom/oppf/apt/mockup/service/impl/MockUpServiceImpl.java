package kr.co.koscom.oppf.apt.mockup.service.impl;

import kr.co.koscom.oppf.apt.mockup.service.MockUpService;
import kr.co.koscom.oppf.apt.mockup.service.MockUpVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LSH on 2017. 2. 10..
 */
@Service("MockUpService")
public class MockUpServiceImpl implements MockUpService{
    @Resource(name="MockUpDAO")
    private MockUpDAO mockUpDAO;

    /**
     * @Method Name        : selectMockUpServiceList
     * @Method description : 목업 메시지 목록을 조회한다.
     * @param              : MockUpVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    @Override
    public Map<String, Object> selectMockUpServiceList(MockUpVO mockUpVO) throws Exception{
        List<MockUpVO> result = mockUpDAO.selectMockUpServiceList(mockUpVO);
        int totCnt = mockUpDAO.selectMockUpServiceListCnt(mockUpVO);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultList", result);
        map.put("totCnt", totCnt);

        return map;
    }

    /**
     * @Method Name        : selectMockUpServiceList
     * @Method description : 목업 메시지 Elxcel 목록을 조회한다.
     * @param              : MockUpVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    @Override
    public Map<String, Object> selectMockUpServiceListExcel(MockUpVO mockUpVO) throws Exception{
        List<MockUpVO> result = mockUpDAO.selectMockUpServiceListExcel(mockUpVO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultList", result);
        return map;
    }

    /**
     * @Method Name        : selectMockUpServiceList
     * @Method description : 목업 메시지 상세 정보를 조회한다
     * @param              : MockUpVO
     * @return             : MockUpVO
     * @throws             : Exception
     */
    @Override
    public MockUpVO selectMockUpServiceDtl(MockUpVO mockUpVO) throws Exception{
        return mockUpDAO.selectMockUpServiceDtl(mockUpVO);
    }

    /**
     * @Method Name        : deleteMockUpServiceList
     * @Method description : 목업 메시지 삭제한다.
     * @param              : MockUpVO
     * @return             : int
     * @throws             : Exception
     */
    @Override
    public int deleteMockUpService(MockUpVO mockUpVO) throws Exception {
        int result = mockUpDAO.deleteMockUpService(mockUpVO);
        if(result!=0){
            mockUpDAO.insertMockUpServiceHist(mockUpVO);
        }
        return result;
    }

    /**
     * @Method Name        : insertMockupService
     * @Method description : 목업 메시지 저장한다.
     * @param              : MockUpVO
     * @return             : String
     * @throws             : Exception
     */
    @Override
    public String insertMockupService(MockUpVO mockUpVO) throws Exception {
        String mockupServiceNumber = "";
        mockupServiceNumber = mockUpDAO.insertMockUpService(mockUpVO);
        if(!OppfStringUtil.isEmpty(mockupServiceNumber)){
            mockUpDAO.insertMockUpServiceHist(mockUpVO);
        }
        return mockupServiceNumber;
    }

    /**
     * @Method Name        : insertMockupService
     * @Method description : 목업 메시지 수정한다.
     * @param              : MockUpVO
     * @return             : int
     * @throws             : Exception
     */
    @Override
    public int updateMockUpService(MockUpVO mockUpVO) throws Exception {
        int result = mockUpDAO.updateMockUpService(mockUpVO);
        if(result!=0){
            mockUpDAO.insertMockUpServiceHist(mockUpVO);
        }
        return result;
    }

    /**
     * @Method Name        : insertMockupService
     * @Method description : 검색창에 기업코드 이름을 나열한다.
     * @param              : MockUpVO
     * @return             : List<CommFuncVO></CommFuncVO>
     * @throws             : Exception
     */
    @Override
    public List<CmmFuncVO> selectCompanyCodeList(CmmFuncVO cmmFuncVO) throws Exception {
        return mockUpDAO.selectCompanyCodeList(cmmFuncVO);
    }

}
