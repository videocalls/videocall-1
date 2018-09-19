
package kr.co.koscom.oppf.apt.pushmng.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.apt.mockup.service.MockUpVO;
import kr.co.koscom.oppf.apt.pushmng.service.PushVO;
import kr.co.koscom.oppf.apt.sptUsr.service.SptUserManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;

@Repository("PushDAO")
public class PushDAO extends ComAbstractDAO {
	
    /**
     * @Method Name        : selectPushList
     * @Method description : Push 목록을 조회한다.
     * @param              : PushVO
     * @return             : List<PushVO>
     * @throws             : Exception
     */
	public List<PushVO> selectPushList(PushVO pushVO) throws Exception{
        return (List<PushVO>) list("apt.PushDAO.selectPushList", pushVO);
    }
	
    /**
     * @Method Name        : selectPushListCnt
     * @Method description : Push 목록 총 갯수를 조회한다.
     * @param              : PushVO
     * @return             : int
     * @throws             : Exception
     */
	public int selectPushListCnt(PushVO pushVO) throws Exception{
        return (Integer) select("apt.PushDAO.selectPushListCnt", pushVO);
    }
	
	
    /**
     * @Method Name        : selectPushListCnt
     * @Method description : Push 목록 총 갯수를 조회한다.
     * @param              : PushVO
     * @return             : int
     * @throws             : Exception
     */
	public List<PushVO> selectDeviceTypeList(PushVO pushVO) throws Exception{
        return (List<PushVO>) list("apt.PushDAO.selectDeviceTypeList", pushVO);
    }
	
	
	
    /**
     * @Method Name        : insertPush
     * @Method description : 목업서비스를 등록한다.
     * @param              : PushVO
     * @return             : int
     * @throws             : Exception
     */
    public String insertPush(PushVO pushVO) throws Exception{
        return (String) insert("apt.PushDAO.insertPush", pushVO);
    }
    
    /**
     * @Method Name        : selectPushDtl
     * @Method description : Push 상세 조회한다.
     * @param              : PushVO
     * @return             : PushVO
     * @throws             : Exception
     */
	public PushVO selectPushDtl(PushVO pushVO) throws Exception{
        return (PushVO) select("apt.PushDAO.selectPushDtl", pushVO);
    }
	
	
    /**
     * @Method Name        : updatePush
     * @Method description : Push 수정한다.
     * @param              : PushVO
     * @return             : int
     * @throws             : Exception
     */
	public int updatePush(PushVO pushVO) throws Exception{
        return update("apt.PushDAO.updatePush", pushVO);
    }
	
	
    /**
     * @Method Name        : selectPushListExcel
     * @Method description : Push Excel 목록을 조회한다.
     * @param              : PushVO
     * @return             : List<PushVO>
     * @throws             : Exception
     */
	public List<PushVO> selectPushListExcel(PushVO pushVO) throws Exception{
        return (List<PushVO>) list("apt.PushDAO.selectPushListExcel", pushVO);
    }

}
