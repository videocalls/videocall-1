package kr.co.koscom.oppf.apt.sptUsr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.sptUsr.service.AptUserEmailManageService;
import kr.co.koscom.oppf.apt.sptUsr.service.AptUserEmailManageVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptUserEmailManageServiceImpl.java
* @Comment  : admin 포털 메일 발송 이력 조회 Controller
* @author   : 유제량
* @since    : 2016.06.10
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.10  유제량        최초생성
*
*/
@Service("AptUserEmailManageService")
public class AptUserEmailManageServiceImpl implements AptUserEmailManageService{
    
    @Resource(name = "AptUserEmailManageDAO")
    private AptUserEmailManageDAO aptUserEmailManageDAO;
        
    /**
     * @Method Name        : selectAptUserEmailManageList
     * @Method description : [admin] 이메일 발송 이력을 조회한다.
     * @param              : AptUserEmailManageVO
     * @return             : List<AptUserEmailManageVO>
     * @throws             : Exception
     */
    @Transactional
    public Map<String, Object> selectAptUserEmailManageList(AptUserEmailManageVO paramVO) throws Exception{
        //return (List<AptUserEmailManageVO>) aptUserEmailManageDAO.selectAptUserEmailManageList(paramVO);
        List<AptUserEmailManageVO> result = aptUserEmailManageDAO.selectAptUserEmailManageList(paramVO);
        int totCnt = aptUserEmailManageDAO.selectAptUserEmailManageListCnt(paramVO);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultList", result);
        map.put("totCnt", totCnt);
        return map;
    }
    
    /**
     * @Method Name        : selectAptUserEmailManageDtl
     * @Method description : admin 포털 메일발송 이력조회 상세를 조회한다.
     * @param              : AptUserEmailManageVO
     * @return             : AptUserEmailManageVO
     * @throws             : Exception
     */
    @Transactional
    public AptUserEmailManageVO selectAptUserEmailManageDtl(AptUserEmailManageVO aptUserEmailManageVO) throws Exception{
    	return aptUserEmailManageDAO.selectAptUserEmailManageDtl(aptUserEmailManageVO);
    }
    
    /**
     * @Method Name        : updateAptUserEmailManage
     * @Method description : [admin] 이메일 발송 이력 정보 수정
     * @param              : AptUserEmailManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateAptUserEmailManage(AptUserEmailManageVO aptUserEmailManageVO) throws Exception{
    	int result = aptUserEmailManageDAO.updateAptUserEmailManage(aptUserEmailManageVO);
    	//result = aptUserEmailManageDAO.insertAptUsertHist(aptUserEmailManageVO);
    	
    	return result;
    }
    
    /**
     * @Method Name        : selectAptUserEmailManageListExcel
     * @Method description : [admin] 이메일 발송 이력을 조회한다.(엑셀파일에 담을 정보이므로 페이징처리 없이 전부 가져 옵니다.)
     * @param              : AptUserEmailManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    @Transactional
    public Map<String, Object> selectAptUserEmailManageListExcel(AptUserEmailManageVO paramVO) throws Exception{
        //return (List<AptUserEmailManageVO>) aptUserEmailManageDAO.selectAptUserEmailManageListExcel(paramVO);
        List<AptUserEmailManageVO> result = aptUserEmailManageDAO.selectAptUserEmailManageListExcel(paramVO);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultList", result);
        
        return map;
    }
    
    /**
     * @Method Name        : selectMbrCustomerCheck
     * @Method description : [admin] 관리자가 공통템플릿으로 메일발송을 할 때 등록되어 있는 사용자에게 이메일을 발송한 것인지 여부를 체크합니다.
     * @param              : AptUserEmailManageVO
     * @return             : String
     * @throws             : Exception
     */
    @Transactional
    public String selectMbrCustomerCheck(AptUserEmailManageVO paramVO) throws Exception{
        String rs = aptUserEmailManageDAO.selectMbrCustomerCheck(paramVO);        
        return rs;
    }
    
    /**
     * @Method Name        : selectMbrCustomerInfo
     * @Method description : [admin] 메일 발송 이력 조회 상세화면에서 '관리자 수정 메일발송'을 위한 정보 조회
     * @param              : AptUserEmailManageVO
     * @return             : AptUserEmailManageVO
     * @throws             : Exception
     */
    @Transactional
    public AptUserEmailManageVO selectMbrCustomerInfo(AptUserEmailManageVO aptUserEmailManageVO) throws Exception{
        return aptUserEmailManageDAO.selectMbrCustomerInfo(aptUserEmailManageVO);
    }
    
//    /**
//     * @Method Name        : updateAptUserTmpPwd
//     * @Method description : 임시비밀번호 발급
//     * @param              : AptUserManageVO
//     * @return             : int
//     * @throws             : Exception
//     */
//    @Transactional
//    public int updateAptUserTmpPwd(AptUserManageVO userManageVO) throws Exception{
//    	int result = aptUserManageDAO.updateAptUserTmpPwd(userManageVO);
//    	result = aptUserManageDAO.insertAptUsertHist(userManageVO);
//    	
//    	return result;
//    }
//    
//    /**
//     * @Method Name        : selectAptUserIdChk
//     * @Method description : admin 포털 id 중복확인
//     * @param              : AptUserManageVO
//     * @return             : int
//     * @throws             : Exception
//     */
//    public int selectAptUserIdChk(AptUserManageVO userManageVO) throws Exception{
//    	return aptUserManageDAO.selectAptUserIdChk(userManageVO);
//    }
//    
//    /**
//     * @Method Name        : insertAptUserReg
//     * @Method description : admin 포털 회원 등록
//     * @param              : AptUserManageVO
//     * @return             : int
//     * @throws             : Exception
//     */
//    @Transactional
//    public int insertAptUserReg(AptUserManageVO userManageVO) throws Exception{
//    	String adminProfileRegNo = aptUserManageDAO.insertAptUserReg(userManageVO);
//    	userManageVO.setAdminProfileRegNo(adminProfileRegNo);
//    	int result = aptUserManageDAO.insertAptUsertHist(userManageVO);
//    	
//    	return result;
//    }
    
    
}
