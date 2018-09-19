package kr.co.koscom.oppf.apt.sptUsr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.sptUsr.service.SptUserManageService;
import kr.co.koscom.oppf.apt.sptUsr.service.SptUserManageVO;
import kr.co.koscom.oppf.apt.usr.mbrReg.service.NewMbrRegVO;
import kr.co.koscom.oppf.apt.usr.mbrReg.service.impl.NewMbrRegDAO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptUserManageServiceImpl.java
* @Comment  : 일반회원 관리를 위한  service impl
* @author   : 신동진
* @since    : 2016.06.20
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.20  신동진        최초생성
*
*/
@Service("SptUserManageService")
public class SptUserManageServiceImpl implements SptUserManageService{
    
    @Resource(name = "SptUserManageDAO")
    private SptUserManageDAO sptUserManageDAO;

	@Resource(name = "NewMbrRegDAO")
	private NewMbrRegDAO newMbrRegDAO;
    /**
     * @Method Name        : selectSptUserManageList
     * @Method description : 일반 회원 목록을 조회한다.
     * @param              : SptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSptUserManageList(SptUserManageVO sptUserManageVO) throws Exception{
    	List<SptUserManageVO> result = sptUserManageDAO.selectSptUserManageList(sptUserManageVO);
    	int totCnt = sptUserManageDAO.selectSptUserManageListCnt(sptUserManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
		map.put("totCnt", totCnt);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectSptUserManageListExcel
     * @Method description : 일반 회원 Excel 목록을 조회한다.
     * @param              : SptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSptUserManageListExcel(SptUserManageVO sptUserManageVO) throws Exception{
    	List<SptUserManageVO> result = sptUserManageDAO.selectSptUserManageListExcel(sptUserManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectSptUserManageDtl
     * @Method description : 일반회원관리 상세  조회
     * @param              : SptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSptUserManageDtl(SptUserManageVO sptUserManageVO) throws Exception{
    	SptUserManageVO resultDetail = sptUserManageDAO.selectSptUserManageDtl(sptUserManageVO);
    	
    	if(resultDetail != null){
	    	if(!OppfStringUtil.isEmpty(resultDetail.getTemporaryMemberYn())){
	    		if("Y".equals(resultDetail.getTemporaryMemberYn())){
	    			resultDetail.setCustomerId("-");
	    		}	
	    	}
    	}
    	
    	List<SptUserManageVO> resultDetailTermsList = sptUserManageDAO.selectSptUserManageDtlTermsList(sptUserManageVO);
    	List<SptUserManageVO> resultDetailSvcTermsList = sptUserManageDAO.selectSptUserManageDtlSvcTermsList(sptUserManageVO);
    	List<SptUserManageVO> resultDetailAccountList = sptUserManageDAO.selectSptUserManageDtlAccountList(sptUserManageVO);
    	SptUserManageVO deviceDetail = sptUserManageDAO.selectSptUserDeviceDtl(sptUserManageVO);
    	
    	if(resultDetailSvcTermsList != null){
	    	for(int i=0; i<resultDetailSvcTermsList.size(); i++){
	            String termsRegNo = resultDetailSvcTermsList.get(i).getTermsRegNo();
	            if(!OppfStringUtil.isEmpty(termsRegNo)){
	                termsRegNo = OppfStringUtil.base64Incoding(termsRegNo);
	                resultDetailSvcTermsList.get(i).setTermsRegNoEncryption(termsRegNo);
	            }
	        }
    	}
    	
    	// 검색조건 유지
    	resultDetail.setSearchCondition(sptUserManageVO.getSearchCondition());
    	resultDetail.setSearchCustomerRegStatus(sptUserManageVO.getSearchCustomerRegStatus());
    	resultDetail.setSearchDateFrom(sptUserManageVO.getSearchDateFrom());
    	resultDetail.setSearchDateTo(sptUserManageVO.getSearchDateTo());
    	resultDetail.setSearchDateType(sptUserManageVO.getSearchDateType());
    	resultDetail.setSearchKeyword(sptUserManageVO.getSearchKeyword());
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultDetail", resultDetail);
    	map.put("resultDetailTermsList", resultDetailTermsList);
    	map.put("resultDetailSvcTermsList", resultDetailSvcTermsList);
    	map.put("resultDetailAccountList", resultDetailAccountList);
    	map.put("deviceDetail", deviceDetail);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : updateSptUserManageDtl
     * @Method description : 일반회원 정보 수정
     * @param              : sptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateSptUserManageDtl(SptUserManageVO sptUserManageVO) throws Exception{
    	int result = sptUserManageDAO.updateSptUserManageDtl(sptUserManageVO);
    	result = sptUserManageDAO.insertSptUserManageDtlHist(sptUserManageVO);

    	return result;
    }

    /**
     * @Method Name        : selectSptWithdrawUserManageList
     * @Method description : 탈퇴 회원 목록을 조회한다.
     * @param              : SptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSptWithdrawUserManageList(SptUserManageVO sptUserManageVO) throws Exception{
    	List<SptUserManageVO> result = sptUserManageDAO.selectSptWithdrawUserManageList(sptUserManageVO);
    	int totCnt = sptUserManageDAO.selectSptWithdrawUserManageListCnt(sptUserManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
		map.put("totCnt", totCnt);
    	    	
        return map;
    }

    
    /**
     * @Method Name        : selectSptWithdrawUserManageDtl
     * @Method description : 탈퇴회원관리 상세  조회
     * @param              : SptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSptWithdrawUserManageDtl(SptUserManageVO sptUserManageVO) throws Exception{

    	SptUserManageVO resultDetail = sptUserManageDAO.selectSptWithdrawUserManageDtl(sptUserManageVO);

    	/* 탈퇴회원은 개인정보외의 데이터는 보관 하고 있기때문에 기존 대로 조회 */
    	List<SptUserManageVO> resultDetailTermsList = sptUserManageDAO.selectSptUserManageDtlTermsList(sptUserManageVO);
    	SptUserManageVO deviceDetail = sptUserManageDAO.selectSptUserDeviceDtl(sptUserManageVO);
    	
    	
    	// 검색조건 유지
    	resultDetail.setSearchCondition(sptUserManageVO.getSearchCondition());
    	resultDetail.setSearchCustomerRegStatus(sptUserManageVO.getSearchCustomerRegStatus());
    	resultDetail.setSearchDateFrom(sptUserManageVO.getSearchDateFrom());
    	resultDetail.setSearchDateTo(sptUserManageVO.getSearchDateTo());
    	resultDetail.setSearchDateType(sptUserManageVO.getSearchDateType());
    	resultDetail.setSearchKeyword(sptUserManageVO.getSearchKeyword());
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultDetail", resultDetail);
    	map.put("resultDetailTermsList", resultDetailTermsList);
    	map.put("deviceDetail", deviceDetail);
    	    	
        return map;
    }

    /**
     * @Method Name        : updateSptWithdrawUser
     * @Method description : 탈퇴처리
     * @param              : sptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateSptWithdrawUser(SptUserManageVO sptUserManageVO) throws Exception{
    	int result = sptUserManageDAO.updateSptWithdrawUser(sptUserManageVO);

    	NewMbrRegVO paramVO = new NewMbrRegVO();
    	
    	
    	if(null != sptUserManageVO.getCustomerWithdrawalProcYn()){
    		paramVO.setCustomerRegNo(sptUserManageVO.getCustomerRegNo());
    		newMbrRegDAO.updatesptCustomerInfoWithDrawProfile(paramVO);
    	}
    	
    	return result;
    }
    
}
