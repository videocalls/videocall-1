package kr.co.koscom.oppf.apt.sptUsr.service.impl;

import kr.co.koscom.oppf.apt.sptUsr.service.SptUserServiceVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptUserServiceDAO.java
* @Comment  : 서비스 연결 현황 관리 DAO
* @author   : 신동진
* @since    : 2016.06.30
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.30  신동진        최초생성
*
*/
@Repository("SptUserServiceDAO")
public class SptUserServiceDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectSptUserServiceAppList
     * @Method description : 앱이름 목록 조회
     * @param              : SptUserServiceVO
     * @return             : List<SptUserServiceVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserServiceVO> selectSptUserServiceAppList(SptUserServiceVO sptUserServiceVO) throws Exception{
        return (List<SptUserServiceVO>) list("apt.SptUserServiceDAO.selectSptUserServiceAppList", sptUserServiceVO);
    }
    
    /**
     * @Method Name        : selectSptUserServiceList
     * @Method description : 서비스 연결 현황 목록 조회
     * @param              : SptUserServiceVO
     * @return             : List<SptUserServiceVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserServiceVO> selectSptUserServiceList(SptUserServiceVO sptUserServiceVO) throws Exception{
        return (List<SptUserServiceVO>) list("apt.SptUserServiceDAO.selectSptUserServiceList", sptUserServiceVO);
    }
    
    /**
     * @Method Name        : selectSptUserServiceListCnt
     * @Method description : 서비스 연결 현황목록의 total cnt를 조회한다.
     * @param              : SptUserServiceVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectSptUserServiceListCnt(SptUserServiceVO sptUserServiceVO) throws Exception{
        return (Integer) select("apt.SptUserServiceDAO.selectSptUserServiceListCnt", sptUserServiceVO);
    }
    
    /**
     * @Method Name        : selectSptUserServiceListExcel
     * @Method description : 서비스 연결 현황 Excel 목록 조회
     * @param              : SptUserServiceVO
     * @return             : List<SptUserServiceVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserServiceVO> selectSptUserServiceListExcel(SptUserServiceVO sptUserServiceVO) throws Exception{
        return (List<SptUserServiceVO>) list("apt.SptUserServiceDAO.selectSptUserServiceListExcel", sptUserServiceVO);
    }
}
