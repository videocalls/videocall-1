package kr.co.koscom.oppf.apt.setting.service.impl;

import kr.co.koscom.oppf.apt.setting.service.SystemCodeManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SystemCodeManageDAO.java
* @Comment  : 관리자의 공통코드 관리를 위한 DAO 클래스
* @author   : 신동진
* @since    : 2016.05.16
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.16  신동진        최초생성
*
*/
@Repository("SystemCodeManageDAO")
public class SystemCodeManageDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectSystemCodeList
     * @Method description : 공통코드 목록을 조회한다.
     * @param              : SystemCodeManageVO
     * @return             : List<SystemCodeManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SystemCodeManageVO> selectSystemCodeList(SystemCodeManageVO systemCodeManageVO) throws Exception{
        return (List<SystemCodeManageVO>) list("apt.SystemCodeManageDAO.selectSystemCodeList", systemCodeManageVO);
    }
    
    /**
     * @Method Name        : selectSystemGrpCodeDtl
     * @Method description : 코드그룹의 상세정보를 조회한다.
     * @param              : SystemCodeManageVO
     * @return             : SystemCodeManageVO
     * @throws             : Exception
     */
    public SystemCodeManageVO selectSystemGrpCodeDtl(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return (SystemCodeManageVO) select("apt.SystemCodeManageDAO.selectSystemGrpCodeDtl", systemCodeManageVO);
    }
    
    /**
     * @Method Name        : chkSystemGrpCodeDtl
     * @Method description : 코드그룹의 저장가능 여부를 체크한다.
     * @param              : SystemCodeManageVO
     * @return             : List<CompanyCodeManageVO>
     * @throws             : Exception
     */
    public int chkSystemGrpCodeDtl(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return (Integer) select("apt.SystemCodeManageDAO.chkSystemGrpCodeDtl", systemCodeManageVO);
    }
    
    /**
     * @Method Name        : insertSystemGrpCode
     * @Method description : 코드그룹 등록
     * @param              : SystemCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSystemGrpCode(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return update("apt.SystemCodeManageDAO.insertSystemGrpCode", systemCodeManageVO);
    }
    
    /**
     * @Method Name        : updateSystemGrpCode
     * @Method description : 코드그룹 수정
     * @param              : SystemCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSystemGrpCode(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return update("apt.SystemCodeManageDAO.updateSystemGrpCode", systemCodeManageVO);
    }
    
    /**
     * @Method Name        : deleteSystemGrpCode
     * @Method description : 코드그룹 삭제
     * @param              : SystemCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteSystemGrpCode(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return delete("apt.SystemCodeManageDAO.deleteSystemGrpCode", systemCodeManageVO);
    }
    
    /**
     * @Method Name        : selectSystemCodeDtl
     * @Method description : 코드의 상세정보를 조회한다.
     * @param              : SystemCodeManageVO
     * @return             : SystemCodeManageVO
     * @throws             : Exception
     */
    public SystemCodeManageVO selectSystemCodeDtl(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return (SystemCodeManageVO) select("apt.SystemCodeManageDAO.selectSystemCodeDtl", systemCodeManageVO);
    }
    
    /**
     * @Method Name        : chkSystemCodeDtl
     * @Method description : 코드의 저장가능 여부를 체크한다.
     * @param              : SystemCodeManageVO
     * @return             : List<CompanyCodeManageVO>
     * @throws             : Exception
     */
    public int chkSystemCodeDtl(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return (Integer) select("apt.SystemCodeManageDAO.chkSystemCodeDtl", systemCodeManageVO);
    }
    
    /**
     * @Method Name        : insertSystemCode
     * @Method description : 코드 등록
     * @param              : SystemCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSystemCode(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return update("apt.SystemCodeManageDAO.insertSystemCode", systemCodeManageVO);
    }
    
    /**
     * @Method Name        : updateSystemCode
     * @Method description : 코드 수정
     * @param              : SystemCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSystemCode(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return update("apt.SystemCodeManageDAO.updateSystemCode", systemCodeManageVO);
    }
    
    /**
     * @Method Name        : deleteSystemCode
     * @Method description : 코드 삭제
     * @param              : SystemCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteSystemCode(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return delete("apt.SystemCodeManageDAO.deleteSystemCode", systemCodeManageVO);
    }
}
