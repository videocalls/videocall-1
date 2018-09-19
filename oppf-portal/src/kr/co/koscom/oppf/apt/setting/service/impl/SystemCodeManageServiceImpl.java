package kr.co.koscom.oppf.apt.setting.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.setting.service.SystemCodeManageService;
import kr.co.koscom.oppf.apt.setting.service.SystemCodeManageVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : NotiManageServiceImpl.java
* @Comment  : 관리자의 기업코드 관리를 위한 Service Impl 클래스
* @author   : 신동진
* @since    : 2016.05.16
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.16  신동진        최초생성
*
*/
@Service("SystemCodeManageService")
public class SystemCodeManageServiceImpl implements SystemCodeManageService{
    
    @Resource(name = "SystemCodeManageDAO")
    private SystemCodeManageDAO systemCodeManageDAO;
    
    
    /**
     * @Method Name        : selectSystemCodeList
     * @Method description : 공통코드 목록을 조회한다.
     * @param              : SystemCodeManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSystemCodeList(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	List<SystemCodeManageVO> result = systemCodeManageDAO.selectSystemCodeList(systemCodeManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }    
    
    /**
     * @Method Name        : selectSystemGrpCodeDtl
     * @Method description : 코드그룹의 상세정보를 조회한다.
     * @param              : SystemCodeManageVO
     * @return             : SystemCodeManageVO
     * @throws             : Exception
     */
    public SystemCodeManageVO selectSystemGrpCodeDtl(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return systemCodeManageDAO.selectSystemGrpCodeDtl(systemCodeManageVO);
    }
    
    /**
     * @Method Name        : chkSystemGrpCodeDtl
     * @Method description : 코드그룹의 저장가능 여부를 체크한다.
     * @param              : SystemCodeManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public int chkSystemGrpCodeDtl(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return systemCodeManageDAO.chkSystemGrpCodeDtl(systemCodeManageVO);
    }
    
    /**
     * @Method Name        : insertSystemGrpCode
     * @Method description : 코드그룹 등록
     * @param              : SystemCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSystemGrpCode(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return systemCodeManageDAO.insertSystemGrpCode(systemCodeManageVO);
    }
    
    /**
     * @Method Name        : updateSystemGrpCode
     * @Method description : 코드그룹 수정
     * @param              : SystemCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSystemGrpCode(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return systemCodeManageDAO.updateSystemGrpCode(systemCodeManageVO);
    }
    
    /**
     * @Method Name        : deleteSystemGrpCode
     * @Method description : 코드그룹 삭제
     * @param              : SystemCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int deleteSystemGrpCode(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	int result = systemCodeManageDAO.deleteSystemGrpCode(systemCodeManageVO);
    	result = systemCodeManageDAO.deleteSystemCode(systemCodeManageVO);
    	
    	return result;
    }
    
    /**
     * @Method Name        : selectSystemCodeDtl
     * @Method description : 코드의 상세정보를 조회한다.
     * @param              : SystemCodeManageVO
     * @return             : SystemCodeManageVO
     * @throws             : Exception
     */
    public SystemCodeManageVO selectSystemCodeDtl(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return systemCodeManageDAO.selectSystemCodeDtl(systemCodeManageVO);
    }
    
    /**
     * @Method Name        : chkSystemCodeDtl
     * @Method description : 코드의 저장가능 여부를 체크한다.
     * @param              : SystemCodeManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public int chkSystemCodeDtl(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return systemCodeManageDAO.chkSystemCodeDtl(systemCodeManageVO);
    }
    
    /**
     * @Method Name        : insertSystemCode
     * @Method description : 코드 등록
     * @param              : SystemCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSystemCode(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return systemCodeManageDAO.insertSystemCode(systemCodeManageVO);
    }
    
    /**
     * @Method Name        : updateSystemCode
     * @Method description : 코드 수정
     * @param              : SystemCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSystemCode(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return systemCodeManageDAO.updateSystemCode(systemCodeManageVO);
    }
    
    /**
     * @Method Name        : deleteSystemCode
     * @Method description : 코드 삭제
     * @param              : SystemCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteSystemCode(SystemCodeManageVO systemCodeManageVO) throws Exception{
    	return systemCodeManageDAO.deleteSystemCode(systemCodeManageVO);
    }
}
