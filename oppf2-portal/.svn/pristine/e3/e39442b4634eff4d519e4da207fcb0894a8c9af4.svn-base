package kr.co.koscom.oppf.apt.mockup.service.impl;

import kr.co.koscom.oppf.apt.mockup.service.MockUpVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LSH on 2017. 2. 10..
 */
@Repository("MockUpDAO")
public class MockUpDAO extends ComAbstractDAO {

    /**
     * @Method Name        : selectMockUpServiceList
     * @Method description : 목업서비스 목록을 조회한다.
     * @param              : MockUpVO
     * @return             : List<MockUpVO>
     * @throws             : Exception
     */
    public List<MockUpVO> selectMockUpServiceList(MockUpVO mockUpVO) throws Exception{
        return (List<MockUpVO>) list("apt.MockUpDAO.selectMockUpServiceList", mockUpVO);
    }

    /**
     * @Method Name        : selectMockUpServiceListCnt
     * @Method description : 목업서비스 목록의 총 개수를 조회한다.
     * @param              : MockUpVO
     * @return             : MockUpVO
     * @throws             : Exception
     */
    public int selectMockUpServiceListCnt(MockUpVO mockUpVO) throws Exception{
        return (Integer) select("apt.MockUpDAO.selectMockUpServiceListCnt", mockUpVO);
    }

    /**
     * @Method Name        : selectMockUpServiceListExcel
     * @Method description : 목업서비스 excel 목록을 조회한다.
     * @param              : MockUpVO
     * @return             : List<MockUpVO>
     * @throws             : Exception
     */
    public List<MockUpVO> selectMockUpServiceListExcel(MockUpVO mockUpVO) throws Exception{
        return (List<MockUpVO>) list("apt.MockUpDAO.selectMockUpServiceListExcel", mockUpVO);
    }

    /**
     * @Method Name        : selectMockUpServiceListExcel
     * @Method description : 목업서비스 상세 정보를 조회한다.
     * @param              : MockUpVO
     * @return             : MockUpVO
     * @throws             : Exception
     */
    public MockUpVO selectMockUpServiceDtl(MockUpVO mockUpVO) throws Exception{
        return (MockUpVO) select("apt.MockUpDAO.selectMockUpServiceDtl", mockUpVO);
    }

    /**
     * @Method Name        : selectMockUpServiceCnt
     * @Method description : mockup message 저장 시 기존데이터가 있는지 여부를 가져온다.
     * @param              : MockUpVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectMockUpServiceCnt(MockUpVO mockUpVO) throws Exception{
        return (Integer) select("apt.MockUpDAO.selectMockUpServiceCnt", mockUpVO);
    }

    /**
     * @Method Name        : insertMockUpService
     * @Method description : 목업서비스를 등록한다.
     * @param              : MockUpVO
     * @return             : int
     * @throws             : Exception
     */
    public String insertMockUpService(MockUpVO mockUpVO) throws Exception{
        return (String) insert("apt.MockUpDAO.insertMockUpService", mockUpVO);
    }

    /**
     * @Method Name        : updateMockUpService
     * @Method description : 목업서비스를 수정한다.
     * @param              : MockUpVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateMockUpService(MockUpVO mockUpVO) throws Exception{
        return update("apt.MockUpDAO.updateMockUpService", mockUpVO);
    }

    /**
     * @Method Name        : deleteMockUpService
     * @Method description : 목업서비스를 삭제한다
     * @param              : MockUpVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteMockUpService(MockUpVO mockUpVO) throws Exception{
        return update("apt.MockUpDAO.deleteMockUpService", mockUpVO);
    }

    /**
     * @Method Name        : insertMockUpServiceHist
     * @Method description : 목업서비스 hist 정보를 insert 한다.
     * @param              : MockUpVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertMockUpServiceHist(MockUpVO mockUpVO) throws Exception{
        return update("apt.MockUpDAO.insertMockUpServiceHist", mockUpVO);
    }

    public List<CmmFuncVO> selectCompanyCodeList(CmmFuncVO cmmFuncVO) throws Exception{
        return (List<CmmFuncVO>) list("apt.MockUpDAO.selectCompanyCodeList", cmmFuncVO);
    }
}
