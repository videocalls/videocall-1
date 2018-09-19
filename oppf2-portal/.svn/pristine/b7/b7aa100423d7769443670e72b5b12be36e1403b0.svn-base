package kr.co.koscom.oppf.sample.service.impl;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.sample.service.SampleVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SampleDAO.java
* @Comment  : [샘플]정보관리를 위한 DAO 클래스
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
@Repository("SampleDAO")
public class SampleDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectSampleListTotalCount
     * @Method description : [샘플목록:총갯수]정보를 조회한다.
     * @param              : SampleVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectSampleListTotalCount(SampleVO sampleVO) throws Exception{
        return (Integer) select("SampleDAO.selectSampleListTotalCount", sampleVO);
    }

    /**
     * @Method Name        : selectSampleList
     * @Method description : [샘플목록:목록]정보를 조회한다.
     * @param              : SampleVO
     * @return             : List<SampleVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SampleVO> selectSampleList(SampleVO sampleVO) throws Exception{
        return (List<SampleVO>) list("SampleDAO.selectSampleList", sampleVO);
    }
    
    /**
     * @Method Name        : selectSampleDetail
     * @Method description : [샘플상세:상세]정보를 조회한다.
     * @param              : SampleVO
     * @return             : SampleVO
     * @throws             : Exception
     */
    public SampleVO selectSampleDetail(SampleVO sampleVO) throws Exception{
        return (SampleVO) select("SampleDAO.selectSampleDetail", sampleVO);
    }
    
    /**
     * @Method Name        : selectSampleBeforeAfterInfo
     * @Method description : [샘플상세:이전글다음글]정보를 조회한다.
     * @param              : SampleVO
     * @return             : SampleVO
     * @throws             : Exception
     */
    public SampleVO selectSampleBeforeAfterInfo(SampleVO sampleVO) throws Exception{
        return (SampleVO) select("SampleDAO.selectSampleBeforeAfterInfo", sampleVO);
    }
    
    /**
     * @Method Name        : updateSampleReadCount
     * @Method description : [샘플목록:해당건]을 클릭 시 조회수 증가
     * @param              : SampleVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSampleReadCount(SampleVO sampleVO) throws Exception{
        int rs = update("SampleDAO.updateSampleReadCount", sampleVO);
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
        return (Integer) select("SampleDAO.selectSampleReadCount", sampleVO);
    }
    
    /**
     * @Method Name        : insertSample
     * @Method description : [샘플목록:등록]을 한다.
     * @param              : SampleVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSample(SampleVO sampleVO) throws Exception{
        int rs = update("SampleDAO.insertSample", sampleVO);
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
        int rs = update("SampleDAO.updateSample", sampleVO);
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
        int rs = update("SampleDAO.deleteSample", sampleVO);
        return rs;
    }
    
}
