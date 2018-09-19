package kr.co.koscom.oppf.sample.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.koscom.oppf.sample.service.SampleService;
import kr.co.koscom.oppf.sample.service.SampleVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SampleServiceImpl.java
* @Comment  : [샘플]정보관리를 위한 Service 클래스
* @author   : 포털 이환덕
* @since    : 2016.04.28
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2015.09.24  이환덕        최초생성
*
*/
@Service("SampleService")
public class SampleServiceImpl implements SampleService{
    
    @Resource(name = "SampleDAO")
    private SampleDAO sampleDAO;
    
    /**
     * @Method Name        : selectSampleListTotalCount
     * @Method description : [샘플목록:총갯수]정보를 조회한다.
     * @param              : SampleVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectSampleListTotalCount(SampleVO sampleVO) throws Exception{
        int totalCount = sampleDAO.selectSampleListTotalCount(sampleVO);
        return totalCount;
    }

    /**
     * @Method Name        : selectSampleList
     * @Method description : [샘플목록:목록]정보를 조회한다.
     * @param              : SampleVO
     * @return             : List<SampleVO>
     * @throws             : Exception
     */
    public List<SampleVO> selectSampleList(SampleVO sampleVO) throws Exception{
        List<SampleVO> resultList = sampleDAO.selectSampleList(sampleVO);
        return resultList;
    }
    
    /**
     * @Method Name        : selectSampleDetail
     * @Method description : [샘플상세:상세]정보를 조회한다.
     * @param              : SampleVO
     * @return             : SampleVO
     * @throws             : Exception
     */
    public SampleVO selectSampleDetail(SampleVO sampleVO) throws Exception{
        SampleVO result = sampleDAO.selectSampleDetail(sampleVO);
        return result;
    }
    
    /**
     * @Method Name        : selectSampleBeforeAfterInfo
     * @Method description : [샘플상세:이전글다음글]정보를 조회한다.
     * @param              : SampleVO
     * @return             : SampleVO
     * @throws             : Exception
     */
    public SampleVO selectSampleBeforeAfterInfo(SampleVO sampleVO) throws Exception{
        SampleVO result = sampleDAO.selectSampleBeforeAfterInfo(sampleVO);
        return result;
    }
    
    /**
     * @Method Name        : updateSampleReadCount
     * @Method description : [샘플목록:해당건]을 클릭 시 조회수 증가
     * @param              : SampleVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSampleReadCount(SampleVO sampleVO) throws Exception{
        int rs = sampleDAO.updateSampleReadCount(sampleVO);
        return rs;
    }
    
    /**
     * @Method Name        : selectReadCount
     * @Method description : [샘플목록:해당건]조회수 증가 후 조회수 취득
     * @param              : SampleVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectSampleReadCount(SampleVO sampleVO) throws Exception{       
        int totalReadCount = sampleDAO.selectSampleReadCount(sampleVO);
        return totalReadCount;
    }
    
    /**
     * @Method Name        : insertSample
     * @Method description : [샘플목록:등록]을 한다.
     * @param              : SampleVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSample(SampleVO sampleVO) throws Exception{
        int rs = sampleDAO.insertSample(sampleVO);
        return rs;
    }
    
    /**
     * @Method Name        : updateSample
     * @Method description : [샘플상세:수정]을 한다.
     * @param              : SampleVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSample(SampleVO sampleVO) throws Exception{
        int rs = sampleDAO.updateSample(sampleVO);
        return rs;
    }
    
    /**
     * @Method Name        : deleteSample
     * @Method description : [샘플상세:삭제]를 한다.
     * @param              : SampleVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteSample(SampleVO sampleVO) throws Exception{
        int rs = sampleDAO.deleteSample(sampleVO);
        return rs;
    }
    
}
