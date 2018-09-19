package kr.co.koscom.oppf.apt.fix.service.impl;

import kr.co.koscom.oppf.apt.fix.service.FixManageService;
import kr.co.koscom.oppf.apt.fix.service.FixManageVO;
import kr.co.koscom.oppf.apt.fix.service.FixQueueVO;
import kr.co.koscom.oppf.apt.stats.service.StatsTrafficVO;
import kr.co.koscom.oppf.apt.stats.service.impl.StatsServiceTrafficDAO;
import kr.co.koscom.oppf.cmm.IntegratedAccount.service.CmmSIFInternalService;
import kr.co.koscom.oppf.cmm.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("FixManageService")
public class FixManageServiceImpl implements FixManageService{
    
    @Resource(name = "FixManageDAO")
    private FixManageDAO fixManageDAO;
    
	@Resource(name = "CmmSIFInternalService")
	private CmmSIFInternalService cmmSIFInternalService;

    @Autowired
    private StatsServiceTrafficDAO statsServiceTrafficDAO;

    
    /**
     * @Method Name        : selectQueueList
     * @Method description : Fix Queue 관리에 등록된 Queue 리스트 조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> selectQueueListCombo(FixManageVO fixManageVO) throws Exception{
		
		List<FixManageVO> queueList = fixManageDAO.selectQueueListCombo(fixManageVO);
		
		return queueList;
		
	}


    /**
     * @Method Name        : fixBuySideListSearch
     * @Method description : buy-side 기업정보 리스트조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixBuySideListSearch(FixManageVO fixManageVO) throws Exception {

		List<FixManageVO> list = fixManageDAO.fixBuySideListSearch(fixManageVO);

		return list;

	}
	
    /**
     * @Method Name        : fixBuySideListSearchCombo
     * @Method description : buy-side 기업정보 리스트조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixBuySideListSearchCombo(FixManageVO fixManageVO) throws Exception {

		List<FixManageVO> list = fixManageDAO.fixBuySideListSearchCombo(fixManageVO);

		return list;

	}


    /**
     * @Method Name        : fixBuySideListSearch
     * @Method description : buy-side 기업정보 리스트조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
    public List<FixManageVO> fixBuySideListCommon(FixManageVO fixManageVO) throws Exception {

        List<FixManageVO> list = fixManageDAO.fixBuySideListCommon(fixManageVO);

        return list;

    }

    /**
     * @Method Name        : resultListTotalCount
     * @Method description : buy-side 기업정보 리스트 총 카운트
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public int resultListTotalCount(FixManageVO fixManageVO) throws Exception {
		
		int cnt = fixManageDAO.resultListTotalCount(fixManageVO);
		
		return cnt;
		
	}
	
	

    /**
     * @Method Name        : fixBuySideListSearchExcel
     * @Method description : buy-side 기업정보 리스트 엑셀출력
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixBuySideListSearchExcel(FixManageVO fixManageVO) throws Exception {
		
		List<FixManageVO> list = fixManageDAO.fixBuySideListSearchExcel(fixManageVO);
		
		return list;
		
	}
	

    /**
     * @Method Name        : fixInitiatorListhExcel
     * @Method description : 
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixInitiatorListhExcel(FixManageVO fixManageVO) throws Exception {
		
		List<FixManageVO> list = fixManageDAO.fixInitiatorListhExcel(fixManageVO);
		
		return list;
		
	}
	
	
	

    /**
     * @Method Name        : fixBuySideDtl
     * @Method description : buy-side 기업정보 상세조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public FixManageVO fixBuySideDtl(FixManageVO fixManageVO) throws Exception {
		
		FixManageVO result = fixManageDAO.fixBuySideDtl(fixManageVO);
		
		return result;
		
	}
	

    /**
     * @Method Name        : countSenderCompId
     * @Method description : buy-side 기업정보 리스트 총 카운트
     * @param              : FixManageVO
     * @return             : int
     * @throws             : Exception
     */
	public int countSenderCompId(FixManageVO fixManageVO) throws Exception {
		
		int cnt = fixManageDAO.countSenderCompId(fixManageVO);
		
		return cnt;
		
	}

    /**
     * @Method Name        : selectCompanyIdCnt
     * @Method description : companyId 중복조회
     * @param              : FixManageVO
     * @return             : int
     * @throws             : Exception
     */
	public int selectCompanyIdCnt(FixManageVO fixManageVO) throws Exception {
		
		int cnt = fixManageDAO.selectCompanyIdCnt(fixManageVO);
		
		return cnt;
		
	}
	


    /**
     * @Method Name        : sendRestTemplate
     * @Method description : restTemplate
     * @param              : String, HttpMethod, payload
     * @return             : String
     * @throws             : Exception
     */
	public String sendRestTemplate(String rul, HttpMethod httpMethod, String payload) throws Exception {
		

		//인증서버 헤더정보
		HttpHeaders httpHeaders = new HttpHeaders();
		//httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		httpHeaders.setCacheControl("no-cache");

		//httpHeaders.add("x-credential-userId", loginVO.getAdmin_profile_reg_no());
		
		
		//CompID 변경 PUT
		ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(rul, httpHeaders, httpMethod, payload);

		if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
			//System.out.println(responseEntity.getStatusCode()); 
			log.debug("++++++++++++++++++++++++++++++++++++ authentication success : {} ", responseEntity.getBody() );
		}else{
			//System.out.println(responseEntity.getStatusCode()); 
			log.debug("++++++++++++++++++++++++++++++++++++ authentication failed : {} ", responseEntity.getBody() );
		}
		
		return responseEntity.getStatusCode().toString();
		
	}


    /**
     * @Method Name        : sendRestTemplate
     * @Method description : restTemplate
     * @param              : String, HttpMethod
     * @return             : String
     * @throws             : Exception
     */
	public List<FixQueueVO>  sendRestTemplateList(String rul, HttpMethod httpMethod, String payload) throws Exception {
		

		//인증서버 헤더정보
		HttpHeaders httpHeaders = new HttpHeaders();
		//httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));P
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		httpHeaders.setCacheControl("no-cache");

		//httpHeaders.add("x-credential-userId", loginVO.getAdmin_profile_reg_no());
		
		
		/*
		httpHeaders.add("x-api-requestId", OppfProperties.getProperty("Globals.authentication.requestId"));
		
		// 호출한 클라이언트 Id
		httpHeaders.add("x-api-clientId", "");
*/
		
		//CompID 변경 PUT
		ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(rul, httpHeaders, httpMethod, "");
		
		JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
		
		JSONObject object = jsonObject.getJSONObject("body");

		JsonUtils jUtil = new JsonUtils();
		JSONArray jArr = object.getJSONArray("queuelist");
		
		List<FixQueueVO> resultList = new ArrayList<FixQueueVO>();;
		
		for(int i =0; i < jArr.length(); i++){
			JSONObject json = jArr.getJSONObject(i);
			FixQueueVO vo = new FixQueueVO();

			vo.setQueueId(json.getString("queueId"));
			vo.setDequeueCount(json.getString("dequeueCount"));
			vo.setConsumerCount(json.getString("consumerCount"));
			vo.setPendingCount(json.getString("pendingCount"));
			vo.setEnqueueCount(json.getString("enqueueCount"));
			
			resultList.add(vo);
			
		}
		
		Map<String, Object> map = new HashMap<>();

		map.put("resultList", resultList);
        
        //map.put("statusCode", responseEntity.getStatusCode().toString());
        
		if(HttpStatus.OK.equals(responseEntity.getStatusCode())){ 
			log.debug("++++++++++++++++++++++++++++++++++++ authentication success : {} ", responseEntity.getBody() );
		}else{ 
			log.debug("++++++++++++++++++++++++++++++++++++ authentication failed : {} ", responseEntity.getBody() );
		}
		
		return resultList;
		
	}

    /**
     * @Method Name        : fixInitiatorListSearch
     * @Method description : Initiator 서버관리 리스트 조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixInitiatorListSearch(FixManageVO fixManageVO) throws Exception {

		List<FixManageVO> list = fixManageDAO.fixInitiatorListSearch(fixManageVO);
		
		return list;
		
	}
	
    /**
     * @Method Name        : fixInitiatorListSearchCombo
     * @Method description : Initiator 서버관리 리스트 조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixInitiatorListSearchCombo(FixManageVO fixManageVO) throws Exception {

		List<FixManageVO> list = fixManageDAO.fixInitiatorListSearchCombo(fixManageVO);
		
		return list;
		
	}


    /**
     * fixSessionAddDtl
     * session 서버상세조회
     * @param fixManageVO
     * @return FixManageVO
     * @throws Exception
     */
	public FixManageVO fixSessionAddDtl(FixManageVO fixManageVO) throws Exception {
		
		FixManageVO retult = fixManageDAO.fixSessionAddDtl(fixManageVO);
		
		return retult;
	}
    
	

    /**
     * @Method Name        : fixServerIpSearch
     * @Method description : fix server ip search
     * @param              : FixManageVO
     * @return             : String
     * @throws             : Exception
     */
	public String fixServerIpSearch(FixManageVO fixManageVO) throws Exception {

		String ip = fixManageDAO.fixServerIpSearch(fixManageVO);
		
		return ip;
		
	}
	

    /**
     * Initiator 서버 IP 중복 조회
     * @param fixManageVO
     * @param request
     * @param model
     * @return 
     * @throws Exception
     */
	public int fixInitiatorServerIpDupChk(FixManageVO fixManageVO) throws Exception {
		
		int cnt = fixManageDAO.fixInitiatorServerIpDupChk(fixManageVO);
		
		return cnt;
		
	}
	

    /**
     * senderCompId 중복 조회
     * @param fixManageVO
     * @param model
     * @return 
     * @throws Exception
     */
	public int fixCompIdDupChk(FixManageVO fixManageVO) throws Exception {
		
		int cnt = fixManageDAO.fixCompIdDupChk(fixManageVO);
		
		return cnt;
		
	}
	

    /**
     * Socket 중복 조회
     * @param fixManageVO
     * @param model
     * @return 
     * @throws Exception
     */
	public int fixSocketDupChk(FixManageVO fixManageVO) throws Exception {
		
		int cnt = fixManageDAO.fixSocketDupChk(fixManageVO);
		
		return cnt;
		
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
	public FixManageVO fixInitiatorUpdateSearchDtl(FixManageVO fixManageVO) throws Exception {
		
		FixManageVO retult = fixManageDAO.fixInitiatorUpdateSearchDtl(fixManageVO);
		
		return retult;
	}
    
	
	

    /**
     * @Method Name        : fixInitiatorCompSearch
     * @Method description : Initiator 기업정보 조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixInitiatorCompSearch(FixManageVO fixManageVO) throws Exception {

		List<FixManageVO> list = fixManageDAO.fixInitiatorCompSearch(fixManageVO);
		
		return list;
		
	}
    /**
     * @Method Name        : fixInitiatorCompSearchTotal
     * @Method description : Initiator 기업정보 조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public int fixInitiatorCompSearchTotal(FixManageVO fixManageVO) throws Exception {

		int cnt = fixManageDAO.fixInitiatorCompSearchTotal(fixManageVO);
		
		return cnt;
		
	}
	
    /**
     * @Method Name        : fixCodeListSearch
     * @Method description : fix code 리스트조회
     * @param              : FixManageVO
     * @return             : List<FixManageVO>
     * @throws             : Exception
     */
	public List<FixManageVO> fixCodeListSearch(FixManageVO fixManageVO) throws Exception {
		List<FixManageVO> list = fixManageDAO.fixCodeListSearch(fixManageVO);
		return list;
	}

	/**
     * Fix Engine 통계 조회
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
    */ 
    public Map<String, Object> statsFixEngineRequestSearch(StatsTrafficVO paramVO) throws Exception {
    	Map<String, Object> map = new HashMap<>();


        // 결과 리스트
        List<HashMap<String,Object>> resultList;

        // 차트 및 그리드 에 노출되는 헤더 정보
        List<HashMap<String,Object>> searchCodeList;

        // 전체 검색 여부
        boolean isAllData = false;
        paramVO.setTrafficTable(this.getRequestTableName(paramVO.getSearchTrafficType()));

        // 통계기준에 따른 조회조건
        if("buySide".equals(paramVO.getAnaType()) && !"Y".equals(paramVO.getSearchFixBuySideListAllYn())) {
            // 서비스 제공자 기준
        	resultList = fixManageDAO.statsFixEngineRequestSearchBuySide(paramVO);
            searchCodeList = this.getChartDisplayNames(paramVO.getFixBuySideList());
           // System.out.println("1-1"); 
        } else if("msgType".equals(paramVO.getAnaType()) && !"Y".equals(paramVO.getSearchMsgTypeCodeIdAllYn())) {
            // API 서비스 이름 기준
        	resultList = fixManageDAO.statsFixEngineRequestSearchMsgType(paramVO);
            searchCodeList = this.getChartDisplayNames(paramVO.getMsgTypeCodeIdList());
           // System.out.println("1-2"); 
        }else {
            // 전체
        	resultList = fixManageDAO.statsFixEngineRequestSearch(paramVO);

            searchCodeList = new ArrayList<>();
            HashMap<String,Object> header = new HashMap<>();
            header.put("dataField", "cntApiDurationTotal");
            header.put("displayText", "전체");
            searchCodeList.add(header);

            isAllData = true;
          //  System.out.println("1-3"); 
        }
        
        map.put("resultList", resultList);
		
		
		if(!isAllData) {
            map = this.getRequestChartData(paramVO, resultList, searchCodeList);
        }
        map.put("chartDisplayNames", searchCodeList);
        map.put("isAllData", isAllData);

        return map;
	}

    // 구분 별 검색 대상 테이블 설정
    private String getRequestTableName(String searchType) {
        if("min".equals(searchType)) {
            return "rs_send_traffic_min";
        } else if("hourly".equals(searchType)) {
            return "rs_send_traffic_hourly";
        } else if("daily".equals(searchType)) {
            return "rs_send_traffic_daily";
        } else {
            return "rs_send_traffic_monthly";
        }
    }

    // 구분 별 검색 대상 테이블 설정
    private String getReponseTableName(String searchType) {
        if("min".equals(searchType)) {
            return "fx_recv_traffic_min";
        } else if("hourly".equals(searchType)) {
            return "fx_recv_traffic_hourly";
        } else if("daily".equals(searchType)) {
            return "fx_recv_traffic_daily";
        } else {
            return "fx_recv_traffic_monthly";
        }
    }
    
    
    // 전체 검색이 아닌 경우의 데이터 설정
    private Map<String, Object> getRequestChartData(StatsTrafficVO statsTrafficVO, List<HashMap<String,Object>> resultList, List<HashMap<String,Object>> searchCodeList) throws Exception {
        Map<String, Object> result = new HashMap<>();

        // 날짜 정보 검색
        List<StatsTrafficVO> stdList = statsServiceTrafficDAO.selectDayMonthYearStdList(statsTrafficVO);

        // 최대 Data 수치 (차트에 사용)
        int maxValueData = 0;

        // 가공된 데이터 리스트
        List<HashMap<String,Object>> resultData = new ArrayList<>();

        // 검색 조건의 날짜만큼
        for(StatsTrafficVO stdData : stdList) {

            HashMap<String, Object> chartDataForDate = new HashMap<>();
            chartDataForDate.put("resultDt", stdData.getDate());

            // 전체 검색이 아닌 경우 해당 기준 코드 리스트 만큼
            for(HashMap<String,Object> code : searchCodeList) {
                String codeId = code.get("dataField").toString();
                boolean isDataCheck = false;

                // 검색 결과에서 매칭된 데이터 셋팅
                for(HashMap resultMap : resultList) {
                    String stdDate = resultMap.get("resultDt").toString();
                    String resultCode = resultMap.get("codeId").toString();
                    if(stdData.getStdDate().equals(stdDate) && codeId.equals(resultCode)) {
                        isDataCheck = true;
                        if(maxValueData < Integer.parseInt(resultMap.get("totCnt").toString())) {
                            maxValueData = Integer.parseInt(resultMap.get("totCnt").toString());
                        }
                        chartDataForDate.put("totCnt_"+codeId, resultMap.get("totCnt").toString());
                        chartDataForDate.put("sucCnt_"+codeId, resultMap.get("sucCnt").toString());
                        chartDataForDate.put("failCnt_"+codeId, resultMap.get("failCnt").toString());
                    }
                }
                if(!isDataCheck) {
                    chartDataForDate.put("totCnt_"+codeId, "0");
                    chartDataForDate.put("sucCnt_"+codeId, "0");
                    chartDataForDate.put("failCnt_"+codeId, "0");
                }
            }
            resultData.add(chartDataForDate);
        }

        result.put("maxValueData", maxValueData);
        result.put("resultList", resultData);

        return result;
    }

    
    
    // 통계 기준에 해당 되는 코드 리스트 정보 설정
    private List<HashMap<String,Object>> getChartDisplayNames(List<String> codes) {
        List<HashMap<String,Object>> codeList = new ArrayList<>();

        for(String code : codes) {
            String[] codeArray = code.split("/");
            HashMap<String,Object> header = new HashMap<>();
            header.put("dataField", codeArray[0]);
            header.put("displayText", codeArray[1]);
            codeList.add(header);
        }

        return codeList;
    }
    

	/**
     * Fix Engine 통계 조회
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
    */ 
    public Map<String, Object> statsFixEngineResponseSearch(StatsTrafficVO paramVO) throws Exception {
    	Map<String, Object> map = new HashMap<>();


        // 결과 리스트
        List<HashMap<String,Object>> resultList;

        // 차트 및 그리드 에 노출되는 헤더 정보
        List<HashMap<String,Object>> searchCodeList;

        // 전체 검색 여부
        boolean isAllData = false;

        paramVO.setTrafficTable(this.getReponseTableName(paramVO.getSearchTrafficType()));

        // 통계기준에 따른 조회조건
        if("initiator".equals(paramVO.getAnaType()) && !"Y".equals(paramVO.getSearchInitiatorListAllYn())) {
            // 서비스 제공자 기준
        	resultList = fixManageDAO.statsFixEngineRequestSearchInitiator(paramVO);
            searchCodeList = this.getChartDisplayNames(paramVO.getInitiatorList());
        } else if("buySide".equals(paramVO.getAnaType()) && !"Y".equals(paramVO.getSearchFixBuySideListAllYn())) {
            // 서비스 제공자 기준
        	resultList = fixManageDAO.statsFixEngineReponseSearchBuySide(paramVO);
            searchCodeList = this.getChartDisplayNames(paramVO.getFixBuySideList());
        } else if("msgType".equals(paramVO.getAnaType()) && !"Y".equals(paramVO.getSearchMsgTypeCodeIdAllYn())) {
            // API 서비스 이름 기준
        	resultList = fixManageDAO.statsFixEngineResponseSearchMsgType(paramVO);
            searchCodeList = this.getChartDisplayNames(paramVO.getMsgTypeCodeIdList());
        }else {
            // 전체
        	resultList = fixManageDAO.statsFixEngineResponseSearch(paramVO);

            searchCodeList = new ArrayList<>();
            HashMap<String,Object> header = new HashMap<>();
            header.put("dataField", "cntApiDurationTotal");
            header.put("displayText", "전체");
            searchCodeList.add(header);

            isAllData = true;
        }
        
        map.put("resultList", resultList);
		
		
		if(!isAllData) {
            map = this.getResponseChartData(paramVO, resultList, searchCodeList);
        }
        map.put("chartDisplayNames", searchCodeList);
        map.put("isAllData", isAllData);

        return map;
	}

    // 전체 검색이 아닌 경우의 데이터 설정
    private Map<String, Object> getResponseChartData(StatsTrafficVO statsTrafficVO, List<HashMap<String,Object>> resultList, List<HashMap<String,Object>> searchCodeList) throws Exception {
        Map<String, Object> result = new HashMap<>();

        // 날짜 정보 검색
        List<StatsTrafficVO> stdList = statsServiceTrafficDAO.selectDayMonthYearStdList(statsTrafficVO);

        // 최대 Data 수치 (차트에 사용)
        int maxValueData = 0;

        // 가공된 데이터 리스트
        List<HashMap<String,Object>> resultData = new ArrayList<>();

        // 검색 조건의 날짜만큼
        for(StatsTrafficVO stdData : stdList) {

            HashMap<String, Object> chartDataForDate = new HashMap<>();
            chartDataForDate.put("resultDt", stdData.getDate());

            // 전체 검색이 아닌 경우 해당 기준 코드 리스트 만큼
            for(HashMap<String,Object> code : searchCodeList) {
                String codeId = code.get("dataField").toString();
                boolean isDataCheck = false;

                // 검색 결과에서 매칭된 데이터 셋팅
                for(HashMap resultMap : resultList) {
                    String stdDate = resultMap.get("resultDt").toString();
                    String resultCode = resultMap.get("codeId").toString();
                    if(stdData.getStdDate().equals(stdDate) && codeId.equals(resultCode)) {
                        isDataCheck = true;
                        if(maxValueData < Integer.parseInt(resultMap.get("resultCnt").toString())) {
                            maxValueData = Integer.parseInt(resultMap.get("resultCnt").toString());
                        }
                        chartDataForDate.put("resultCnt_"+codeId, resultMap.get("resultCnt").toString());
                    }
                }
                if(!isDataCheck) {
                    chartDataForDate.put("resultCnt_"+codeId, "0");
                }
            }
            resultData.add(chartDataForDate);
        }

        result.put("maxValueData", maxValueData);
        result.put("resultList", resultData);

        return result;
    }

}
