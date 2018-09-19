package kr.co.koscom.oppf.apt.aptUsr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.aptUsr.service.AptUserManageService;
import kr.co.koscom.oppf.apt.aptUsr.service.AptUserManageVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptUserManageServiceImpl.java
* @Comment  : admin 포털 회원관리  serviceImpl
* @author   : 신동진
* @since    : 2016.05.10
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.10  신동진        최초생성
*
*/
@Service("AptUserManageService")
public class AptUserManageServiceImpl implements AptUserManageService{
    
    @Resource(name = "AptUserManageDAO")
    private AptUserManageDAO aptUserManageDAO;
        
    /**
     * @Method Name        : selectAptUserList
     * @Method description : admin 포털 회원 목록을 조회한다.
     * @param              : AptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectAptUserList(AptUserManageVO userManageVO) throws Exception{
    	List<AptUserManageVO> result = aptUserManageDAO.selectAptUserList(userManageVO);
    	int totCnt = aptUserManageDAO.selectAptUserListCnt(userManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
		map.put("totCnt", totCnt);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectAptUserListExcel
     * @Method description : admin 포털 회원 Excel 목록을 조회한다.
     * @param              : AptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectAptUserListExcel(AptUserManageVO userManageVO) throws Exception{
    	List<AptUserManageVO> result = aptUserManageDAO.selectAptUserListExcel(userManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
		    	
        return map;
    }
    
    /**
     * @Method Name        : selectAptUserDtl
     * @Method description : admin 포털 회원 상세를 조회한다.
     * @param              : AptUserManageVO
     * @return             : AptUserManageVO
     * @throws             : Exception
     */
    public AptUserManageVO selectAptUserDtl(AptUserManageVO userManageVO) throws Exception{
    	return aptUserManageDAO.selectAptUserDtl(userManageVO);
    }
    
    /**
     * @Method Name        : updateAptUser
     * @Method description : admin 포털 회원정보 수정
     * @param              : AptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateAptUser(AptUserManageVO userManageVO) throws Exception{
    	int result = aptUserManageDAO.updateAptUser(userManageVO);
    	result = aptUserManageDAO.insertAptUsertHist(userManageVO);
    	
    	return result;
    }
    
    /**
     * @Method Name        : updateAptUserTmpPwd
     * @Method description : 임시비밀번호 발급
     * @param              : AptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateAptUserTmpPwd(AptUserManageVO userManageVO) throws Exception{
    	int result = aptUserManageDAO.updateAptUserTmpPwd(userManageVO);
    	result = aptUserManageDAO.insertAptUsertHist(userManageVO);
    	
    	return result;
    }
    
    /**
     * @Method Name        : selectAptUserIdChk
     * @Method description : admin 포털 id 중복확인
     * @param              : AptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectAptUserIdChk(AptUserManageVO userManageVO) throws Exception{
    	return aptUserManageDAO.selectAptUserIdChk(userManageVO);
    }
    
    /**
     * @Method Name        : insertAptUserReg
     * @Method description : admin 포털 회원 등록
     * @param              : AptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int insertAptUserReg(AptUserManageVO userManageVO) throws Exception{
    	String adminProfileRegNo = aptUserManageDAO.insertAptUserReg(userManageVO);
    	userManageVO.setAdminProfileRegNo(adminProfileRegNo);
    	int result = aptUserManageDAO.insertAptUsertHist(userManageVO);
    	
    	return result;
    }
    
    
}
