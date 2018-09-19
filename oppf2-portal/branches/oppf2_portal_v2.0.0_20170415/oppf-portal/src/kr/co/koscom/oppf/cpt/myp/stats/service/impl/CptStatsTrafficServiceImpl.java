package kr.co.koscom.oppf.cpt.myp.stats.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.cpt.myp.stats.service.CptStatsTrafficService;
import kr.co.koscom.oppf.cpt.myp.stats.service.CptStatsTrafficVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptStatsTrafficServiceImpl.java
* @Comment  : 기업포털 Traffic 통계 관리를 위한 위한 Service
* @author   : 신동진
* @since    : 2016.08.19
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.08.19  신동진        최초생성
*
*/
@Service("CptStatsTrafficService")
public class CptStatsTrafficServiceImpl implements CptStatsTrafficService{
    
    @Resource(name = "CptStatsTrafficDAO")
    private CptStatsTrafficDAO cptStatsTrafficDAO;
        
    /*
     * 분 : 최대 2 시간 (기준일/시 기준 2시간)
     * 05 : 00 ~ 04					-> 00
     * 10 : 05 ~ 09					-> 05
     * 15 : 10 ~ 14					-> 10
     * 20 : 15 ~ 19					-> 15
     * 25 : 20 ~ 24					-> 20
     * 30 : 25 ~ 29					-> 25
     * 35 : 30 ~ 34					-> 30
     * 40 : 35 ~ 39					-> 35
     * 45 : 40 ~ 44					-> 40
     * 50 : 45 ~ 49					-> 45
     * 55 : 50 ~ 54					-> 50
     * 00 : 55 ~ 59 (이전시간)			-> 55
     */
    private final int POLICY_MIN_TIME = 2;			//조회 시간
    private final String [] POLICY_MIN_BASE = {
    	"00",
    	"05", 
    	"10",
    	"15",
    	"20",
    	"25",
    	"30",
    	"35",
    	"40",
    	"45",
    	"50",
    	"55"
    };												//조회 조건
    
    /**
     * @Method Name        : selectStatsTrafficPubCompanyList
     * @Method description : API 서비스 제공자 코드 조회
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    public List<CptStatsTrafficVO> selectStatsTrafficPubCompanyList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	return cptStatsTrafficDAO.selectStatsTrafficPubCompanyList(cptStatsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficApiCategoryList
     * @Method description : API 서비스 구분 코드 조회
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    public List<CptStatsTrafficVO> selectStatsTrafficApiCategoryList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	return cptStatsTrafficDAO.selectStatsTrafficApiCategoryList(cptStatsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficApiNameList
     * @Method description : API 서비스 이름 코드 조회
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    public List<CptStatsTrafficVO> selectStatsTrafficApiNameList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	return cptStatsTrafficDAO.selectStatsTrafficApiNameList(cptStatsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficApiServiceList
     * @Method description : 세부 API 서비스 코드 조회
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    public List<CptStatsTrafficVO> selectStatsTrafficApiServiceList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	return cptStatsTrafficDAO.selectStatsTrafficApiServiceList(cptStatsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficSubCompanyList
     * @Method description : 앱 개발자 코드 조회
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    public List<CptStatsTrafficVO> selectStatsTrafficSubCompanyList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	return cptStatsTrafficDAO.selectStatsTrafficSubCompanyList(cptStatsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficAppNameList
     * @Method description : 앱이름 코드 조회
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    public List<CptStatsTrafficVO> selectStatsTrafficAppNameList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	return cptStatsTrafficDAO.selectStatsTrafficAppNameList(cptStatsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficApiPlanNameList
     * @Method description : Plan 종류
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    public List<CptStatsTrafficVO> selectStatsTrafficApiPlanNameList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	return cptStatsTrafficDAO.selectStatsTrafficApiPlanNameList(cptStatsTrafficVO);
    }
    
    /**
     * @Method Name        : selectCompanyCodeId
     * @Method description : 로그인 사용자의 companyCodeId를 가져온다.
     * @param              : CptStatsTrafficVO
     * @return             : String
     * @throws             : Exception
     */
    public String selectCompanyCodeId(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	return cptStatsTrafficDAO.selectCompanyCodeId(cptStatsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficApiList
     * @Method description : API 트레픽 통계 목록을 조회한다.
     * @param              : CptStatsTrafficVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectStatsTrafficApiList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	//2. header 셋팅
    	HashMap resultHeader = getHeaderInfo(cptStatsTrafficVO);
    	
    	//3. header 데이터를 이용하여 중복 쿼리 생성
    	cptStatsTrafficVO.setSql1(resultHeader.get("sql1").toString());
    	cptStatsTrafficVO.setSql2(resultHeader.get("sql2").toString());
    	cptStatsTrafficVO.setSql3(resultHeader.get("sql3").toString());
    	
    	cptStatsTrafficVO.setTrafficTable(resultHeader.get("trafficTable").toString());
    	cptStatsTrafficVO.setTrafficTableCondition(resultHeader.get("trafficTableCondition").toString());
    	
    	//4. 데이터 조회
    	List<HashMap> resultList = null;
    	resultList = cptStatsTrafficDAO.selectStatsTrafficApiList(cptStatsTrafficVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultHeader", resultHeader.get("header"));
		map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectStatsTrafficAppList
     * @Method description : APP 트레픽 통계 목록을 조회한다.
     * @param              : CptStatsTrafficVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectStatsTrafficAppList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	//2. header 셋팅
    	HashMap resultHeader = getHeaderInfo(cptStatsTrafficVO);
    	
    	//3. header 데이터를 이용하여 중복 쿼리 생성
    	cptStatsTrafficVO.setSql1(resultHeader.get("sql1").toString());
    	cptStatsTrafficVO.setSql2(resultHeader.get("sql2").toString());
    	cptStatsTrafficVO.setSql3(resultHeader.get("sql3").toString());
    	
    	cptStatsTrafficVO.setTrafficTable(resultHeader.get("trafficTable").toString());
    	cptStatsTrafficVO.setTrafficTableCondition(resultHeader.get("trafficTableCondition").toString());
    	
    	//4. 데이터 조회
    	List<HashMap> resultList = cptStatsTrafficDAO.selectStatsTrafficAppList(cptStatsTrafficVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultHeader", resultHeader.get("header"));
		map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : getHeaderInfo
     * @Method description : 트레픽 통계 목록의 header 정보를 리턴한다.
     * @param              : CptStatsTrafficVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    public HashMap getHeaderInfo(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	HashMap list = new HashMap();
    	
    	List<HashMap> header = new ArrayList();
    	
    	StringBuffer sql1 = new StringBuffer();
    	StringBuffer sql2 = new StringBuffer();
    	StringBuffer sql3 = new StringBuffer();
    	
    	String trafficTable = "";									//조회 테이블
    	StringBuffer trafficTableCondition = new StringBuffer();	//테이블 기본 조회 조건
    	
    	//1. Head용 조회 기준일 셋팅
    	List<CptStatsTrafficVO> searchDateList = cptStatsTrafficDAO.selectStatsTrafficSearchDate(cptStatsTrafficVO);
    	    	
    	//구분 : 분
    	if("min".equals(cptStatsTrafficVO.getSearchTrafficType())){
    		//조회테이블 셋팅
    		trafficTable = "adm_api_traffic_min";
    		//분 : 최대 -2 시간 (조회 시간 기준 -2시간)
    		//ex ) and date_format(str_to_date(a.std_date, '%Y%m%d%H%i'), '%Y%m%d%H')
    		//		   between date_format(str_to_date(#searchDateTime#, '%Y%m%d%H') - INTERVAL 1 HOUR, '%Y%m%d%H')
            //         and #searchDateTime#
    		trafficTableCondition.append("and ");
    		trafficTableCondition.append(" date_format(str_to_date(a.std_date, '%Y%m%d%H%i'), '%Y%m%d%H') ");
    		trafficTableCondition.append(" between date_format(str_to_date('"+cptStatsTrafficVO.getSearchDateTime()+"', '%Y%m%d%H') - INTERVAL 1 HOUR, '%Y%m%d%H') ");
    		trafficTableCondition.append(" and '"+cptStatsTrafficVO.getSearchDateTime()+"' ");
    		        	    		
    		//분의 기준 시간 만큼, 조회조건을 생성한다.
    		for(int time=1; time<=POLICY_MIN_TIME; time++){
    			for(int i=0; i<POLICY_MIN_BASE.length; i++){
    				//*********************************************************************
    				//sql 1
    				//건수기준 ex) format(ifnull(min_1_05, 0), 0) as min_1_05,
    				sql1.append(",format(ifnull(min_"+time+"_"+POLICY_MIN_BASE[i]+", 0), 0) as min_"+time+"_"+POLICY_MIN_BASE[i]);
    				
    				//*********************************************************************
    				//sql 2
    				//건수기준 ex) sum(min_1_05) as min_1_05
    				sql2.append(",sum(min_"+time+"_"+POLICY_MIN_BASE[i]+") as min_"+time+"_"+POLICY_MIN_BASE[i]);
    				
    				//*********************************************************************
    				//sql 3 : 
    				// ex time1 ) 	case when a.std_date = date_format(str_to_date(concat('2016063014', '05'), '%Y%m%d%H%i'), '%Y%m%d%H%i')
    				//				then a.duration_data else 0 end as min_1_05,
    				// ex time2 ) 	case when a.std_date = date_format(str_to_date(concat('2016063014', '05'), '%Y%m%d%H%i') + INTERVAL 1 HOUR, '%Y%m%d%H%i')
                    //				then a.duration_data else 0 end as min_2_05,
    				String searchDateTime = cptStatsTrafficVO.getSearchDateTime();
    				if(time == POLICY_MIN_TIME){
    					sql3.append(",case when a.std_date = date_format(str_to_date(concat('"+searchDateTime+"', '"+POLICY_MIN_BASE[i]+"'), '%Y%m%d%H%i'), '%Y%m%d%H%i')");
    					sql3.append(" then a.duration_data else 0 end as min_"+time+"_"+POLICY_MIN_BASE[i]);
    				}else{
    					sql3.append(",case when a.std_date = date_format(str_to_date(concat('"+searchDateTime+"', '"+POLICY_MIN_BASE[i]+"'), '%Y%m%d%H%i') - INTERVAL "+time+" HOUR, '%Y%m%d%H%i')");
    					sql3.append(" then a.duration_data else 0 end as min_"+time+"_"+POLICY_MIN_BASE[i]);
    				}
    				
    				//*********************************************************************
    				//header string 셋팅
    				HashMap headerInfo = new HashMap();
    				
    				String headerDateString = "";
    				for(int x=0; x<searchDateList.size(); x++){
    					CptStatsTrafficVO data = (CptStatsTrafficVO) searchDateList.get(x);
    					if(time == (x+1)){
    						headerDateString = data.getSearchStdDate();
    					}
    					
    					//빈값을 위해 맨마지막 값을 넣어준다.
    					if("".equals(headerDateString)){
    						headerDateString = data.getSearchStdDate();
    					}
    				}
    				
    				headerInfo.put("headerId", "min_"+time+"_"+POLICY_MIN_BASE[i]);
    				headerInfo.put("headerName", headerDateString + ":"+POLICY_MIN_BASE[i]);
    				header.add(headerInfo);
    			}
    		}
    		
    	//구분 : 시간
    	}else if("hourly".equals(cptStatsTrafficVO.getSearchTrafficType())){
    		//조회테이블 셋팅
    		trafficTable = "adm_api_traffic_hourly";
    		//시간 : 최대 2 일 (조회일/시 기준 -2일)
    		//ex ) and date_format(str_to_date(a.std_date, '%Y%m%d%H'), '%Y%m%d%H') 
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d%H') - INTERVAL 47 HOUR, '%Y%m%d%H')
    		//         and #searchDateTime#
    		trafficTableCondition.append("and ");
    		trafficTableCondition.append(" date_format(str_to_date(a.std_date, '%Y%m%d%H'), '%Y%m%d%H') ");
    		trafficTableCondition.append(" between date_format(str_to_date('"+cptStatsTrafficVO.getSearchDateTime()+"', '%Y%m%d%H') - INTERVAL 47 HOUR, '%Y%m%d%H') ");
    		trafficTableCondition.append(" and '"+cptStatsTrafficVO.getSearchDateTime()+"' ");
    		
    		//*********************************************************************
			//data 셋팅
			String headerDateString = "";
			for(int x=0; x<searchDateList.size(); x++){
				CptStatsTrafficVO data = (CptStatsTrafficVO) searchDateList.get(x);
				
				//*********************************************************************
				//header string 셋팅
				HashMap headerInfo = new HashMap();
				headerInfo.put("headerId", "hourly_"+data.getSearchStdDate());
				headerInfo.put("headerName", data.getSearchStdDateText());
				header.add(headerInfo);
				
				//*********************************************************************
				//sql 1
				//건수기준 ex) format(ifnull(hourly_2016060913, 0), 0) as hourly_2016060913
				sql1.append(",format(ifnull(hourly_"+data.getSearchStdDate()+", 0), 0) as hourly_"+data.getSearchStdDate());
				
				//*********************************************************************
				//sql 2
				//건수기준 ex) sum(hourly_2016060913) as hourly_2016060913
				sql2.append(",sum(hourly_"+data.getSearchStdDate()+") as hourly_"+data.getSearchStdDate());
				
				//*********************************************************************
				//sql 3 : 
				// ex) 	case when a.std_date = date_format(str_to_date('2016060913', '%Y%m%d%H'), '%Y%m%d%H')
                //		then a.duration_data else 0 end as hourly_2016060913
				sql3.append(",case when a.std_date = date_format(str_to_date("+data.getSearchStdDate()+", '%Y%m%d%H'), '%Y%m%d%H')");
				sql3.append(" then a.duration_data else 0 end as hourly_"+data.getSearchStdDate());
				
			}    		
    	
    	//구분 : 일
    	}else if("daily".equals(cptStatsTrafficVO.getSearchTrafficType())){
    		//조회테이블 셋팅
    		trafficTable = "adm_api_traffic_daily";
    		//일 : 최대 2개월 (조회 일 기준 -60일)
    		//ex ) and date_format(str_to_date(a.std_date, '%Y%m%d'), '%Y%m%d') 
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d') - INTERVAL 60 DAY, '%Y%m%d')
            //         and #searchDateTime#
    		trafficTableCondition.append("and ");
    		trafficTableCondition.append(" date_format(str_to_date(a.std_date, '%Y%m%d'), '%Y%m%d') ");
    		trafficTableCondition.append(" between date_format(str_to_date('"+cptStatsTrafficVO.getSearchDateTime()+"', '%Y%m%d') - INTERVAL 60 DAY, '%Y%m%d') ");
    		trafficTableCondition.append(" and '"+cptStatsTrafficVO.getSearchDateTime()+"' ");
    		
    		//*********************************************************************
			//data 셋팅
			String headerDateString = "";
			for(int x=0; x<searchDateList.size(); x++){
				CptStatsTrafficVO data = (CptStatsTrafficVO) searchDateList.get(x);
				
				//*********************************************************************
				//header string 셋팅
				HashMap headerInfo = new HashMap();
				headerInfo.put("headerId", "daily_"+data.getSearchStdDate());
				headerInfo.put("headerName", data.getSearchStdDateText());
				header.add(headerInfo);
				
				//*********************************************************************
				//sql 1
				//건수기준 ex) format(ifnull(daily_20160609, 0), 0) as daily_20160609
				sql1.append(",format(ifnull(daily_"+data.getSearchStdDate()+", 0), 0) as daily_"+data.getSearchStdDate());
				
				//*********************************************************************
				//sql 2
				//건수기준 ex) sum(daily_20160609) as daily_20160609
				sql2.append(",sum(daily_"+data.getSearchStdDate()+") as daily_"+data.getSearchStdDate());
				
				//*********************************************************************
				//sql 3 : 
				// ex) 	case when a.std_date = date_format(str_to_date('20160609', '%Y%m%d'), '%Y%m%d')
                //		then a.duration_data else 0 end as daily_20160609
				sql3.append(",case when a.std_date = date_format(str_to_date("+data.getSearchStdDate()+", '%Y%m%d'), '%Y%m%d')");
				sql3.append(" then a.duration_data else 0 end as daily_"+data.getSearchStdDate());
				
			}    
    		
   		//구분 : 개월
    	}else if("monthly".equals(cptStatsTrafficVO.getSearchTrafficType())){
    		//조회테이블 셋팅
    		trafficTable = "adm_api_traffic_monthly";
    		//개월 : 최대 2년 (조회 월 기준 -23개월)
    		//ex ) and date_format(str_to_date(a.std_date, '%Y%m'), '%Y%m') 
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d') - INTERVAL 23 MONTH, '%Y%m')
            //         and date_format(str_to_date(#searchDateTime#, '%Y%m%d'), '%Y%m')
    		trafficTableCondition.append("and ");
    		trafficTableCondition.append(" date_format(str_to_date(a.std_date, '%Y%m%d'), '%Y%m') ");
    		trafficTableCondition.append(" between date_format(str_to_date('"+cptStatsTrafficVO.getSearchDateTime()+"', '%Y%m%d') - INTERVAL 23 MONTH, '%Y%m') ");
    		trafficTableCondition.append(" and date_format(str_to_date('"+cptStatsTrafficVO.getSearchDateTime()+"', '%Y%m%d'), '%Y%m') ");
    		
    		//*********************************************************************
			//data 셋팅
			String headerDateString = "";
			for(int x=0; x<searchDateList.size(); x++){
				CptStatsTrafficVO data = (CptStatsTrafficVO) searchDateList.get(x);
				
				//*********************************************************************
				//header string 셋팅
				HashMap headerInfo = new HashMap();
				headerInfo.put("headerId", "monthly_"+data.getSearchStdDate());
				headerInfo.put("headerName", data.getSearchStdDateText());
				header.add(headerInfo);
				
				//*********************************************************************
				//sql 1
				//건수기준 ex) format(ifnull(monthly_201606, 0), 0) as monthly_201606
				sql1.append(",format(ifnull(monthly_"+data.getSearchStdDate()+", 0), 0) as monthly_"+data.getSearchStdDate());
				
				//*********************************************************************
				//sql 2
				//건수기준 ex) sum(monthly_201606) as monthly_201606
				sql2.append(",sum(monthly_"+data.getSearchStdDate()+") as monthly_"+data.getSearchStdDate());
				
				//*********************************************************************
				//sql 3 : 
				// ex) 	case when a.std_date = date_format(str_to_date('201606', '%Y%m%d'), '%Y%m')
                //		then a.duration_data else 0 end as monthly_201606
				sql3.append(",case when a.std_date = date_format(str_to_date("+data.getSearchStdDate()+", '%Y%m%d'), '%Y%m')");
				sql3.append(" then a.duration_data else 0 end as monthly_"+data.getSearchStdDate());
				
			}    
    	}
    	
    	//정보 셋팅
    	list.put("sql1", sql1.toString());
    	list.put("sql2", sql2.toString());
    	list.put("sql3", sql3.toString());
    	
    	list.put("header", header);
    	
    	list.put("trafficTable", trafficTable);
    	list.put("trafficTableCondition", trafficTableCondition.toString());
    	    	
    	return list;
    }

	@Override
	public Map<String, Object> selectStatsTrafficList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception {
		Map<String,Object> map = new HashMap<>();

		// 쿼리 데이터 설정
		this.setServiceSearchInfo(cptStatsTrafficVO);

		// 결과 리스트
		List<HashMap<String,Object>> resultList;

		// 차트 및 그리드 에 노출되는 헤더 정보
		List<HashMap<String,Object>> searchCodeList;

		// 전체 검색 여부
		boolean isAllData = false;

		// 통계기준에 따른 조회조건
		if("appName".equals(cptStatsTrafficVO.getSearchStatsType()) && !"Y".equals(cptStatsTrafficVO.getSearchAppNameAllYn())) {
			// 서비스 제공자 기준
			resultList = cptStatsTrafficDAO.selectStatsTrafficList(cptStatsTrafficVO);
			searchCodeList = this.getChartDisplayNames(cptStatsTrafficVO.getAppNameList());

		} else if("apiName".equals(cptStatsTrafficVO.getSearchStatsType()) && !"Y".equals(cptStatsTrafficVO.getSearchApiNameAllYn())) {
			// API 서비스 이름 기준
			resultList = cptStatsTrafficDAO.selectStatsTrafficList(cptStatsTrafficVO);
			searchCodeList = this.getChartDisplayNames(cptStatsTrafficVO.getApiNameList());

		} else if("plan".equals(cptStatsTrafficVO.getSearchStatsType()) && !"Y".equals(cptStatsTrafficVO.getSearchApiPlanNameAllYn())) {
			// Plan 종류 기준
			resultList = cptStatsTrafficDAO.selectStatsTrafficList(cptStatsTrafficVO);
			searchCodeList = this.getChartDisplayNames(cptStatsTrafficVO.getApiPlanNameList());

		} else {
			// 전체
			resultList = cptStatsTrafficDAO.selectStatsTrafficListAll(cptStatsTrafficVO);

			searchCodeList = new ArrayList<>();
			HashMap<String,Object> header = new HashMap<>();
			header.put("dataField", "cntApiDurationTotal");
			header.put("displayText", "전체");
			searchCodeList.add(header);

			map.put("resultList", resultList);
			isAllData = true;
		}

		if(!isAllData) {
			map = this.getChartData(cptStatsTrafficVO, resultList, searchCodeList);
		}
		map.put("chartDisplayNames", searchCodeList);
		map.put("isAllData", isAllData);

		return map;
	}

	// 전체 검색이 아닌 경우의 데이터 설정
	private Map<String, Object> getChartData(CptStatsTrafficVO cptStatsTrafficVO, List<HashMap<String,Object>> resultList, List<HashMap<String,Object>> searchCodeList) throws Exception {
		Map<String, Object> result = new HashMap<>();

		// 날짜 정보 검색
		List<CptStatsTrafficVO> stdList = cptStatsTrafficDAO.selectDayStdList(cptStatsTrafficVO);

		// 최대 Data 수치 (차트에 사용)
		int maxValueData = 0;

		// 가공된 데이터 리스트
		List<HashMap<String,Object>> resultData = new ArrayList<>();

		// 검색 조건의 날짜만큼
		for(CptStatsTrafficVO stdData : stdList) {

			HashMap<String, Object> chartDataForDate = new HashMap<>();
			chartDataForDate.put("Date", stdData.getDate());

			// 전체 검색이 아닌 경우 해당 기준 코드 리스트 만큼
			for(HashMap<String,Object> code : searchCodeList) {
				String codeId = code.get("dataField").toString();
				boolean isDataCheck = false;

				// 검색 결과에서 매칭된 데이터 셋팅
				for(HashMap resultMap : resultList) {
					String stdDate = resultMap.get("stdDate").toString();
					String resultCode = resultMap.get("codeId").toString();
					if(stdData.getStdDate().equals(stdDate) && codeId.equals(resultCode)) {
						isDataCheck = true;
						if(maxValueData < Integer.parseInt(resultMap.get("cntApiDurationTotal").toString())) {
							maxValueData = Integer.parseInt(resultMap.get("cntApiDurationTotal").toString());
						}
						chartDataForDate.put("cntApiDurationTotal_"+codeId, resultMap.get("cntApiDurationTotal").toString());
						chartDataForDate.put("cntApiDurationY_"+codeId, resultMap.get("cntApiDurationY").toString());
						chartDataForDate.put("cntApiDurationN_"+codeId, resultMap.get("cntApiDurationN").toString());
						chartDataForDate.put("apiDurationY_"+codeId, resultMap.get("apiDurationY").toString());
						chartDataForDate.put("apiDurationN_"+codeId, resultMap.get("apiDurationN").toString());
						chartDataForDate.put("sifDurationY_"+codeId, resultMap.get("sifDurationY").toString());
						chartDataForDate.put("sifDurationN_"+codeId, resultMap.get("sifDurationN").toString());
					}
				}
				if(!isDataCheck) {
					chartDataForDate.put("cntApiDurationTotal_"+codeId, "0");
					chartDataForDate.put("cntApiDurationY_"+codeId, "0");
					chartDataForDate.put("cntApiDurationN_"+codeId, "0");
					chartDataForDate.put("apiDurationY_"+codeId, "0");
					chartDataForDate.put("apiDurationN_"+codeId, "0");
					chartDataForDate.put("sifDurationY_"+codeId, "0");
					chartDataForDate.put("sifDurationN_"+codeId, "0");
				}
			}
			resultData.add(chartDataForDate);
		}

		result.put("maxValueData", maxValueData);
		result.put("resultList", resultData);

		return result;
	}

	// 통계 기준에 따른 검색 쿼리 셋팅
	private void setServiceSearchInfo(CptStatsTrafficVO cptStatsTrafficVO) {

		// 통계기준에 따른 조회조건
		if("appName".equals(cptStatsTrafficVO.getSearchStatsType()) && !"Y".equals(cptStatsTrafficVO.getSearchPubCompanyCodeIdAllYn())) {
			cptStatsTrafficVO.setSql1(", B.app_id AS codeId, B.app_name AS codeName");
			cptStatsTrafficVO.setSql2("GROUP BY A.std_date, B.app_name, B.app_id \n ORDER BY B.app_name, A.std_date asc");
		} else if("apiName".equals(cptStatsTrafficVO.getSearchStatsType()) && !"Y".equals(cptStatsTrafficVO.getSearchApiNameAllYn())) {
			cptStatsTrafficVO.setSql1(", B.api_id AS codeId, B.api_name AS codeName");
			cptStatsTrafficVO.setSql2("GROUP BY A.std_date, B.api_name, B.api_id \n ORDER BY B.api_name, A.std_date asc");
		} else if("plan".equals(cptStatsTrafficVO.getSearchStatsType()) && !"Y".equals(cptStatsTrafficVO.getSearchApiPlanNameAllYn())) {
			cptStatsTrafficVO.setSql1(", B.api_plan_id AS codeId, B.code_name_kor AS codeName");
			cptStatsTrafficVO.setSql2("GROUP BY A.std_date, B.code_name_kor, B.api_plan_id \n ORDER BY B.code_name_kor, A.std_date asc");
		}

		cptStatsTrafficVO.setTrafficTable(this.getTrafficTableName(cptStatsTrafficVO.getSearchTrafficType()));
	}

	// 구분 별 검색 대상 테이블 설정
	private String getTrafficTableName(String searchType) {
		if("daily".equals(searchType)) {
			return "adm_api_traffic_daily";
		} else {
			return "adm_api_traffic_monthly";
		}
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
}
