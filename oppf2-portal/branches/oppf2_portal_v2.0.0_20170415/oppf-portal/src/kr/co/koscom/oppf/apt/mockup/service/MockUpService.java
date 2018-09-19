package kr.co.koscom.oppf.apt.mockup.service;

import kr.co.koscom.oppf.cmm.service.CmmFuncVO;

import java.util.List;
import java.util.Map;

/**
 * Created by LSH on 2017. 2. 10..
 */
public interface MockUpService {

    /**
     * @Method Name        : selectMockUpServiceList
     * @Method description : 목업 메시지 목록을 조회한다.
     * @param              : MockUpVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    Map<String,Object> selectMockUpServiceList(MockUpVO mockUpVO) throws Exception;

    /**
     * @Method Name        : selectMockUpServiceList
     * @Method description : 목업 메시지 Elxcel 목록을 조회한다.
     * @param              : MockUpVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    Map<String,Object> selectMockUpServiceListExcel(MockUpVO mockUpVO) throws Exception;

    /**
     * @Method Name        : selectMockUpServiceList
     * @Method description : 목업 메시지 상세 정보를 조회한다
     * @param              : MockUpVO
     * @return             : MockUpVO
     * @throws             : Exception
     */
    MockUpVO selectMockUpServiceDtl(MockUpVO mockUpVO) throws Exception;
//
//    /**
//     * @Method Name        : saveMockUpServiceList
//     * @Method description : 목업 메시지 저장한다.
//     * @param              : MockUpVO
//     * @return             : int
//     * @throws             : Exception
//     */
//    int saveMockUpService(MockUpVO mockUpVO) throws Exception;

    /**
     * @Method Name        : deleteMockUpServiceList
     * @Method description : 목업 메시지 삭제한다.
     * @param              : MockUpVO
     * @return             : int
     * @throws             : Exception
     */
    int deleteMockUpService(MockUpVO mockUpVO) throws Exception;

    /**
     * @Method Name        : deleteMockUpServiceList
     * @Method description : 목업 메시지 입력한다.
     * @param              : MockUpVO
     * @return             : String
     * @throws             : Exception
     */
    String insertMockupService(MockUpVO paramVO) throws Exception ;

    /**
     * @Method Name        : deleteMockUpServiceList
     * @Method description : 목업 메시지 수정한다.
     * @param              : MockUpVO
     * @return             : int
     * @throws             : Exception
     */
    int updateMockUpService(MockUpVO paramVO) throws Exception ;

    /**
     * @Method Name        : deleteMockUpServiceList
     * @Method description : 검색창에 기업코드 이름을 나열한다.
     * @param              : MockUpVO
     * @return             : List<CommFuncVO></CommFuncVO>
     * @throws             : Exception
     */
    List<CmmFuncVO> selectCompanyCodeList(CmmFuncVO cmmFuncVO) throws Exception ;
}
