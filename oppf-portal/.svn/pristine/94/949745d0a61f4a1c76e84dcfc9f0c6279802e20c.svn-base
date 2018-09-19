package kr.co.koscom.oppf.apt.sptUsr.service.impl;

import kr.co.koscom.oppf.apt.sptUsr.service.AptUserEmailManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptUserEmailManageDAO.java
* @Comment  : admin 포털 메일 발송 이력 조회 Controller
* @author   : 유제량
* @since    : 2016.06.10
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.10  유제량        최초생성
*
*/
@Repository("AptUserEmailManageDAO")
public class AptUserEmailManageDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectAptUserEmailManageList
     * @Method description : [admin] 이메일 발송 이력을 조회한다.
     * @param              : AptUserEmailManageVO
     * @return             : List<AptUserEmailManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")  
    public List<AptUserEmailManageVO> selectAptUserEmailManageList(AptUserEmailManageVO aptUserEmailManageVO) throws Exception{
        return (List<AptUserEmailManageVO>) list("apt.AptUserEmailManageDAO.selectAptUserEmailManageList", aptUserEmailManageVO);
    }
    
    /**
     * @Method Name        : selectAptUserEmailManageListCnt
     * @Method description : [admin] 이메일 발송 이력 총갯수를 조회한다.
     * @param              : AptUserEmailManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectAptUserEmailManageListCnt(AptUserEmailManageVO aptUserEmailManageVO) throws Exception{
        return (Integer) select("apt.AptUserEmailManageDAO.selectAptUserEmailManageListCnt", aptUserEmailManageVO);
    }
    
    /**
     * @Method Name        : selectAptUserEmailManageDtl
     * @Method description : admin 포털 메일발송 이력조회 상세를 조회한다.
     * @param              : AptUserEmailManageVO
     * @return             : AptUserEmailManageVO
     * @throws             : Exception
     */
    public AptUserEmailManageVO selectAptUserEmailManageDtl(AptUserEmailManageVO aptUserEmailManageVO) throws Exception{
        return (AptUserEmailManageVO) select("apt.AptUserEmailManageDAO.selectAptUserEmailManageDtl", aptUserEmailManageVO);
    }
    
    /**
     * @Method Name        : updateAptUserEmailManage
     * @Method description : [admin] 이메일 발송 이력 정보 수정
     * @param              : AptUserEmailManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateAptUserEmailManage(AptUserEmailManageVO aptUserEmailManageVO) throws Exception{
    	return update("apt.AptUserEmailManageDAO.updateAptUserEmailManage", aptUserEmailManageVO);
    }
    
    /**
     * @Method Name        : selectAptUserEmailManageListExcel
     * @Method description : [admin] 이메일 발송 이력을 조회한다.(엑셀파일에 담을 정보이므로 페이징처리 없이 전부 가져 옵니다.)
     * @param              : AptUserEmailManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")  
    public List<AptUserEmailManageVO> selectAptUserEmailManageListExcel(AptUserEmailManageVO aptUserEmailManageVO) throws Exception{
        return (List<AptUserEmailManageVO>) list("apt.AptUserEmailManageDAO.selectAptUserEmailManageListExcel", aptUserEmailManageVO);
    }
    
    /**
     * @Method Name        : selectMbrCustomerCheck
     * @Method description : [admin] 관리자가 공통템플릿으로 메일발송을 할 때 등록되어 있는 사용자에게 이메일을 발송한 것인지 여부를 체크합니다.
     * @param              : AptUserEmailManageVO
     * @return             : String
     * @throws             : Exception
     */
    public String selectMbrCustomerCheck(AptUserEmailManageVO paramVO) throws Exception{
        String rs = (String) select("apt.AptUserEmailManageDAO.selectAptUserEmailManageCustomerCheck",paramVO);
        return rs;
    }
    
    /**
     * @Method Name        : selectMbrCustomerInfo
     * @Method description : [admin] 메일 발송 이력 조회 상세화면에서 '관리자 수정 메일발송'을 위한 정보 조회
     * @param              : AptUserEmailManageVO
     * @return             : AptUserEmailManageVO
     * @throws             : Exception
     */
    public AptUserEmailManageVO selectMbrCustomerInfo(AptUserEmailManageVO aptUserEmailManageVO) throws Exception{
        return (AptUserEmailManageVO) select("apt.AptUserEmailManageDAO.selectMbrCustomerInfo", aptUserEmailManageVO);
    }
    
//    /**
//     * @Method Name        : insertAptUsertHist
//     * @Method description : admin 포털 회원정보 수정 hist를 insert한다.
//     * @param              : AptUserManageVO
//     * @return             : int
//     * @throws             : Exception
//     */
//    public int insertAptUsertHist(AptUserManageVO userManageVO) throws Exception{
//    	return update("apt.AptUserManageDAO.insertAptUsertHist", userManageVO);
//    }
//    
//    /**
//     * @Method Name        : updateAptUserTmpPwd
//     * @Method description : 임시비밀번호 발급
//     * @param              : AptUserManageVO
//     * @return             : int
//     * @throws             : Exception
//     */
//    public int updateAptUserTmpPwd(AptUserManageVO userManageVO) throws Exception{
//    	return update("apt.AptUserManageDAO.updateAptUserTmpPwd", userManageVO);
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
//    	return (Integer) select("apt.AptUserManageDAO.selectAptUserIdChk", userManageVO);
//    }
//    
//    /**
//     * @Method Name        : insertAptUserReg
//     * @Method description : admin 포털 회원 등록
//     * @param              : AptUserManageVO
//     * @return             : int
//     * @throws             : Exception
//     */
//    public String insertAptUserReg(AptUserManageVO userManageVO) throws Exception{
//    	return (String) insert("apt.AptUserManageDAO.insertAptUserReg", userManageVO);
//    }
    
}
