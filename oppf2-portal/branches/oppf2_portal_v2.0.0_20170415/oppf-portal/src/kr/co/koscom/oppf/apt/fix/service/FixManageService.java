package kr.co.koscom.oppf.apt.fix.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpMethod;

import kr.co.koscom.oppf.apt.fix.service.FixManageVO;
import kr.co.koscom.oppf.apt.stats.service.StatsTrafficVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;

public interface FixManageService{

    /**
     * @Method Name        : selectQueueList
     * @Method description : Fix Queue 관리에 등록된 Queue 리스트 조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public  List<FixManageVO>  selectQueueListCombo(FixManageVO fixManageVO) throws Exception;
    

    /**
     * @Method Name        : fixBuySideListSearch
     * @Method description : buy-side 기업정보 리스트조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixBuySideListSearch(FixManageVO fixManageVO) throws Exception;

	
    /**
     * @Method Name        : fixBuySideListSearchCombo
     * @Method description : buy-side 기업정보 리스트조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixBuySideListSearchCombo(FixManageVO fixManageVO) throws Exception;

    /**
     * fixSessionAddDtl
     * session 서버상세조회
     * @param fixManageVO
     * @return FixManageVO
     * @throws Exception
     */
	public FixManageVO fixSessionAddDtl(FixManageVO fixManageVO) throws Exception;

    /**
     * @Method Name        : fixBuySideListSearch
     * @Method description : buy-side 기업정보 리스트 총 카운트
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public int resultListTotalCount(FixManageVO fixManageVO) throws Exception;
	

    /**
     * @Method Name        : selectCompanyIdCnt
     * @Method description : companyId 중복조회
     * @param              : FixManageVO
     * @return             : int
     * @throws             : Exception
     */
	public int selectCompanyIdCnt(FixManageVO fixManageVO) throws Exception;

    /**
     * @Method Name        : fixBuySideListSearchExcel
     * @Method description : buy-side 기업정보 리스트 엑셀출력
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixBuySideListSearchExcel(FixManageVO fixManageVO) throws Exception;

    /**
     * @Method Name        : fixInitiatorListhExcel
     * @Method description : 
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixInitiatorListhExcel(FixManageVO fixManageVO) throws Exception;
	
    /**
     * @Method Name        : fixBuySideDtl
     * @Method description : buy-side 기업정보 상세조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public FixManageVO fixBuySideDtl(FixManageVO fixManageVO) throws Exception;
	

    /**
     * @Method Name        : countSenderCompId
     * @Method description : buy-side 기업정보 리스트 총 카운트
     * @param              : FixManageVO
     * @return             : int
     * @throws             : Exception
     */
	public int countSenderCompId(FixManageVO fixManageVO) throws Exception;


    /**
     * @param loginVO 
     * @Method Name        : sendRestTemplate
     * @Method description : restTemplate
     * @param              : String, HttpMethod
     * @return             : String
     * @throws             : Exception
     */
	public String sendRestTemplate(String string, HttpMethod post, String payload) throws Exception;


    /**
     * @Method Name        : fixInitiatorListSearch
     * @Method description : Initiator 서버관리 리스트 조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixInitiatorListSearch(FixManageVO fixManageVO) throws Exception;

    /**
     * @Method Name        : fixInitiatorListSearchCombo
     * @Method description : Initiator 서버관리 리스트 조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixInitiatorListSearchCombo(FixManageVO fixManageVO) throws Exception;
	
    /**
     * @Method Name        : fixServerIpSearch
     * @Method description : fix server ip search
     * @param              : FixManageVO
     * @return             : String
     * @throws             : Exception
     */
	public String fixServerIpSearch(FixManageVO fixManageVO) throws Exception;
	
    /**
     * Initiator 서버 IP 중복 조회
     * @param fixManageVO
     * @param request
     * @param model
     * @return 
     * @throws Exception
     */
	public int fixInitiatorServerIpDupChk(FixManageVO fixManageVO) throws Exception;

    /**
     * senderCompId 중복 조회
     * @param fixManageVO
     * @param model
     * @return 
     * @throws Exception
     */
	public int fixCompIdDupChk(FixManageVO fixManageVO) throws Exception;

    /**
     * Socket Port 중복 조회
     * @param fixManageVO
     * @param model
     * @return 
     * @throws Exception
     */
	public int fixSocketDupChk(FixManageVO fixManageVO) throws Exception ;
	
    /**
     * fixInitiatorUpdateSearchDtl
     * Initiator 서버상세조회
     * @param fixManageVO
     * @param request
     * @param model
     * @return fixInitiatorList
     * @throws Exception
     */
	public FixManageVO fixInitiatorUpdateSearchDtl(FixManageVO fixManageVO) throws Exception;
	

    /**
     * @Method Name        : fixInitiatorCompSearch
     * @Method description : Initiator 기업정보 조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixInitiatorCompSearch(FixManageVO fixManageVO) throws Exception;
	

    /**
     * @Method Name        : fixInitiatorCompSearchTotal
     * @Method description : Initiator 기업정보 조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public int fixInitiatorCompSearchTotal(FixManageVO fixManageVO) throws Exception;

    /**
     * @Method Name        : fixCodeListSearch
     * @Method description : fix Code 리스트조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixCodeListSearch(FixManageVO fixManageVO) throws Exception;

	/**
     * Fix Engine 통계 조회
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
    */ 
	public Map<String, Object>  statsFixEngineRequestSearch(StatsTrafficVO paramVO) throws Exception ;

	/**
     * Fix Engine 통계 조회
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
    */ 
    public Map<String, Object> statsFixEngineResponseSearch(StatsTrafficVO paramVO) throws Exception;

	/**
	 * Fix Engine 통계 조회
	 * @param paramVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	List<FixManageVO> fixBuySideListCommon(FixManageVO fixManageVO) throws Exception;

    /**
     * @Method Name        : sendRestTemplate
     * @Method description : restTemplate
     * @param              : String, HttpMethod
     * @return             : String
     * @throws             : Exception
     */
	public List<FixQueueVO> sendRestTemplateList(String rul, HttpMethod httpMethod, String payload) throws Exception;
}
