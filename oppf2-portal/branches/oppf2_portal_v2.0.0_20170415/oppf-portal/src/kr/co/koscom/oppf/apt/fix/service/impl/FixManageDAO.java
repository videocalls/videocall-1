package kr.co.koscom.oppf.apt.fix.service.impl;

import kr.co.koscom.oppf.apt.fix.service.FixManageVO;
import kr.co.koscom.oppf.apt.myp.aptMyInfo.web.AptMyInfoController;
import kr.co.koscom.oppf.apt.sptUsr.service.SptUserServiceTermsVO;
import kr.co.koscom.oppf.apt.stats.service.StatsTrafficVO;
import kr.co.koscom.oppf.cmm.service.impl.ComSecondAbstractDAO;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("FixManageDAO")
public class FixManageDAO extends ComSecondAbstractDAO {

    private static final Logger log = Logger.getLogger(AptMyInfoController.class);
    

    /**
     * @Method Name        : selectQueueList
     * @Method description : Fix Queue 관리에 등록된 Queue 리스트 조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
    public List<FixManageVO> selectQueueListCombo(FixManageVO fixManageVO) {
		
		return (List<FixManageVO>) list("apt.fix.selectQueueListCombo", fixManageVO);
		
	}
	

    /**
     * @Method Name        : fixBuySideListSearch
     * @Method description : buy-side 기업정보 리스트조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixBuySideListSearch(FixManageVO fixManageVO) {
		
		return (List<FixManageVO>) list("apt.fix.fixBuySideListSearch", fixManageVO);
		
	}
	

    /**
     * @Method Name        : fixBuySideListSearch
     * @Method description : buy-side 기업정보 리스트조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixBuySideListSearchCombo(FixManageVO fixManageVO) {
		
		return (List<FixManageVO>) list("apt.fix.fixBuySideListSearchCombo", fixManageVO);
		
	}
	

	/**
	 * @Method Name        : fixBuySideListSearch(LeeSunHa)
	 * @Method description : buy-side 기업정보 Simulator 검색조건 List
	 * @param              : FixManageVO
	 * @return             : List<FixManageVO>
	 * @throws             : Exception
	 */
	public List<FixManageVO> fixBuySideListCommon(FixManageVO fixManageVO) {

		return (List<FixManageVO>) list("apt.fix.fixBuySideListCommon", fixManageVO);

	}

    /**
     * @Method Name        : fixBuySideListSearch
     * @Method description : buy-side 기업정보 리스트 총 카운트
     * @param              : FixManageVO
     * @return             : int
     * @throws             : Exception
     */
	public int resultListTotalCount(FixManageVO fixManageVO) {
		
		return (Integer) select("apt.fix.resultListTotalCount", fixManageVO);
		
	}
	


	/**
     * @Method Name        : fixBuySideListSearchExcel
     * @Method description : buy-side 기업정보 리스트 엑셀출력
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixBuySideListSearchExcel(FixManageVO fixManageVO) {
		
		return (List<FixManageVO>) list("apt.fix.fixBuySideListSearchExcel", fixManageVO);
		
	}
	
	/**
     * @Method Name        : fixInitiatorListhExcel
     * @Method description : 
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixInitiatorListhExcel(FixManageVO fixManageVO) {
		
		return (List<FixManageVO>) list("apt.fix.fixInitiatorListSearchExcel", fixManageVO);
		
	}
	
	

    /**
     * @Method Name        : fixBuySideDtl
     * @Method description : buy-side 기업정보 상세조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public FixManageVO fixBuySideDtl(FixManageVO fixManageVO) {
		
		return (FixManageVO) select("apt.fix.fixBuySideDtl", fixManageVO);
		
	}
	

    /**
     * @Method Name        : countSenderCompId
     * @Method description : compID 중복 조회
     * @param              : FixManageVO
     * @return             : int
     * @throws             : Exception
     */
	public int countSenderCompId(FixManageVO fixManageVO) {
		
		return (Integer) select("apt.fix.countSenderCompId", fixManageVO);
		
	}
	

    /**
     * @Method Name        : selectCompanyIdCnt
     * @Method description : companyId 중복조회
     * @param              : FixManageVO
     * @return             : int
     * @throws             : Exception
     */
	public int selectCompanyIdCnt(FixManageVO fixManageVO) throws Exception {

		return (Integer) select("apt.fix.selectCompanyIdCnt", fixManageVO);
		
	}
	

    /**
     * @Method Name        : fixBuySideListSearchExcel
     * @Method description : buy-side 기업정보 리스트 엑셀출력
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixInitiatorListSearch(FixManageVO fixManageVO) {
		
		return (List<FixManageVO>) list("apt.fix.fixInitiatorListSearch", fixManageVO);
		
	}	

    /**
     * @Method Name        : fixInitiatorListSearchCombo
     * @Method description : buy-side 기업정보 리스트 엑셀출력
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixInitiatorListSearchCombo(FixManageVO fixManageVO) {
		
		return (List<FixManageVO>) list("apt.fix.fixInitiatorListSearchCombo", fixManageVO);
		
	}

    /**
     * fixSessionAddDtl
     * session 서버상세조회
     * @param fixManageVO
     * @return FixManageVO
     * @throws Exception
     */
	public FixManageVO fixSessionAddDtl(FixManageVO fixManageVO) {

		return (FixManageVO) select("apt.fix.fixSessionAddDtl", fixManageVO);	
	}
	
    /**
     * @Method Name        : fixServerIpSearch
     * @Method description : fix server ip search
     * @param              : FixManageVO
     * @return             : String
     * @throws             : Exception
     */
	public String fixServerIpSearch(FixManageVO fixManageVO) {

		return (String) select("apt.fix.selectServerIp", fixManageVO);
	}

    /**
     * Initiator 서버 IP 중복 조회
     * @param fixManageVO
     * @param request
     * @param model
     * @return 
     * @throws Exception
     */
	public int fixInitiatorServerIpDupChk(FixManageVO fixManageVO){
		
		return (Integer) select("apt.fix.fixInitiatorServerIpDupChk", fixManageVO);
		
	}
	

    /**
     * Initiator senderCompId 중복 조회
     * @param fixManageVO
     * @param model
     * @return 
     * @throws Exception
     */
	public int fixCompIdDupChk(FixManageVO fixManageVO){
		
		return (Integer) select("apt.fix.fixCompIdDupChk", fixManageVO);
		
	}
	

    /**
     * Socket Port 중복 조회
     * @param fixManageVO
     * @param model
     * @return 
     * @throws Exception
     */
	public int fixSocketDupChk(FixManageVO fixManageVO){
		
		return (Integer) select("apt.fix.fixSocketDupChk", fixManageVO);
		
	}
	

    /**
     * fixInitiatorUpdateSearchDtl
     * Initiator 서버상세조회
     * @param fixManageVO
     * @param request
     * @param model
     * @return fixInitiatorList
     * @throws Exception
     */
	public FixManageVO fixInitiatorUpdateSearchDtl(FixManageVO fixManageVO) {

		return (FixManageVO) select("apt.fix.fixInitiatorUpdateSearchDtl", fixManageVO);	
	}
	

    /**
     * @Method Name        : fixInitiatorCompSearch
     * @Method description : Initiator 기업정보 조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixInitiatorCompSearch(FixManageVO fixManageVO){
		
		return (List<FixManageVO>) list("apt.fix.fixInitiatorCompSearch", fixManageVO);
		
	}

    /**
     * @Method Name        : fixInitiatorCompSearchTotal
     * @Method description : Initiator 기업정보 조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public int fixInitiatorCompSearchTotal(FixManageVO fixManageVO){
		
		return  (Integer) select("apt.fix.fixInitiatorCompSearchTotal", fixManageVO);
		
	}

	
    /**
     * @Method Name        : fixCodeListSearch
     * @Method description : fix Code 리스트조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixCodeListSearch(FixManageVO fixManageVO){
		
		return  (List<FixManageVO>) list("apt.fix.fixCodeListSearch", fixManageVO);
		
	}

	/**
     * Fix Engine 통계 조회
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
    */ 
    public List<HashMap<String,Object>> statsFixEngineRequestSearch(StatsTrafficVO paramVO) {
		return  (List<HashMap<String,Object>>) list("apt.fix.statsFixEngineRequestSearch", paramVO);
	}

	/**
     * Fix Engine 통계 조회
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
    */ 
    public List<HashMap<String,Object>> statsFixEngineRequestSearchBuySide(StatsTrafficVO paramVO) {
		return  (List<HashMap<String,Object>>) list("apt.fix.statsFixEngineRequestSearchBuySide", paramVO);
	}

	/**
     * Fix Engine 통계 조회
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
    */ 
    public List<HashMap<String,Object>> statsFixEngineRequestSearchMsgType(StatsTrafficVO paramVO) {
		return  (List<HashMap<String,Object>>) list("apt.fix.statsFixEngineRequestSearchMsgType", paramVO);
	}
	
    


	/**
     * Fix Engine Reponse 통계 조회
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
    */ 
    public List<HashMap<String,Object>> statsFixEngineResponseSearch(StatsTrafficVO paramVO) {
		return  (List<HashMap<String,Object>>) list("apt.fix.statsFixEngineResponseSearch", paramVO);
	}

	/**
     * Fix Engine Reponse 통계 조회
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
    */ 
    public List<HashMap<String,Object>> statsFixEngineRequestSearchInitiator(StatsTrafficVO paramVO) {
		return  (List<HashMap<String,Object>>) list("apt.fix.statsFixEngineRequestSearchInitiator", paramVO);
	}

	/**
     * Fix Engine Reponse 통계 조회
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
    */ 
    public List<HashMap<String,Object>> statsFixEngineReponseSearchBuySide(StatsTrafficVO paramVO) {
		return  (List<HashMap<String,Object>>) list("apt.fix.statsFixEngineReponseSearchBuySide", paramVO);
	}


	/**
     * Fix Engine Reponse 통계 조회
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
    */ 
    public List<HashMap<String,Object>> statsFixEngineResponseSearchMsgType(StatsTrafficVO paramVO) {
		return  (List<HashMap<String,Object>>) list("apt.fix.statsFixEngineResponseSearchMsgType", paramVO);
	}
	
}
