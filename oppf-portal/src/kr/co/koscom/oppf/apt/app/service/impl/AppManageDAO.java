package kr.co.koscom.oppf.apt.app.service.impl;

import kr.co.koscom.oppf.apt.app.service.AppManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AppManageDAO.java
* @Comment  : app 관리를 위한 DAO
* @author   : 신동진
* @since    : 2016.05.30
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.30  신동진        최초생성
*
*/
@Repository("AppManageDAO")
public class AppManageDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectAppManageListTotalCount
     * @Method description : app 목록의 총개수를 조회한다.
     * @param              : AppManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectAppManageListTotalCount(AppManageVO appManageVO) throws Exception{
        return (Integer) select("apt.AppManageDAO.selectAppManageListTotalCount", appManageVO);
    }

    /**
     * @Method Name        : selectAppManageList
     * @Method description : app 목록을 조회한다.
     * @param              : AppManageVO
     * @return             : List<AppManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<AppManageVO> selectAppManageList(AppManageVO appManageVO) throws Exception{
        return (List<AppManageVO>) list("apt.AppManageDAO.selectAppManageList", appManageVO);
    }
    
    /**
     * @Method Name        : selectAppManageListExcel
     * @Method description : app excel 목록을 조회한다.
     * @param              : AppManageVO
     * @return             : List<AppManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<AppManageVO> selectAppManageListExcel(AppManageVO appManageVO) throws Exception{
        return (List<AppManageVO>) list("apt.AppManageDAO.selectAppManageListExcel", appManageVO);
    }
    
    /**
     * @Method Name        : selectAppManageDtl
     * @Method description : app 상세정보를 조회한다.
     * @param              : AppManageVO
     * @return             : AppManageVO
     * @throws             : Exception
     */
    public AppManageVO selectAppManageDtl(AppManageVO appManageVO) throws Exception{
        return (AppManageVO) select("apt.AppManageDAO.selectAppManageDtl", appManageVO);
    }
    
    /**
     * @Method Name        : selectAppManageDtlApiList
     * @Method description : app 상세의 서비스제공자 목록을 조회한다. 
     * @param              : AppManageVO
     * @return             : List<AppManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<AppManageVO> selectAppManageDtlApiList(AppManageVO appManageVO) throws Exception{
        return (List<AppManageVO>) list("apt.AppManageDAO.selectAppManageDtlApiList", appManageVO);
    }
    
    /**
     * @Method Name        : selectAppManageChkCnt
     * @Method description : app 저장 시 기존데이터가 있는지 여부를 가져온다.
     * @param              : AppManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectAppManageChkCnt(AppManageVO appManageVO) throws Exception{
        return (Integer) select("apt.AppManageDAO.selectAppManageChkCnt", appManageVO);
    }
    
    /**
     * @Method Name        : insertAppManage
     * @Method description : app 등록한다.
     * @param              : AppManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertAppManage(AppManageVO appManageVO) throws Exception{
        return (Integer) update("apt.AppManageDAO.insertAppManage", appManageVO);
    }
    
    /**
     * @Method Name        : updateAppManage
     * @Method description : app 수정한다.
     * @param              : AppManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateAppManage(AppManageVO appManageVO) throws Exception{
        return (Integer) update("apt.AppManageDAO.updateAppManage", appManageVO);
    }
    
    /**
     * @Method Name        : insertAppManageHist
     * @Method description : app hist 정보를 insert한다.
     * @param              : AppManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertAppManageHist(AppManageVO appManageVO) throws Exception{
        return (Integer) update("apt.AppManageDAO.insertAppManageHist", appManageVO);
    }
}
