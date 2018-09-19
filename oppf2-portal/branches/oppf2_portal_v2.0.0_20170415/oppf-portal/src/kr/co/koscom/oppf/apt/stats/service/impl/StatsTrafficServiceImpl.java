package kr.co.koscom.oppf.apt.stats.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.stats.service.StatsTrafficService;
import kr.co.koscom.oppf.apt.stats.service.StatsTrafficVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : StatsTrafficServiceImpl.java
* @Comment  : Traffic 통계 관리를 위한 위한 Service
* @author   : 신동진
* @since    : 2016.07.10
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.07.10  신동진        최초생성
*
*/
@Service("StatsTrafficService")
public class StatsTrafficServiceImpl implements StatsTrafficService{
    
    @Resource(name = "StatsTrafficDAO")
    private StatsTrafficDAO statsTrafficDAO;
        
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
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    public List<StatsTrafficVO> selectStatsTrafficPubCompanyList(StatsTrafficVO statsTrafficVO) throws Exception{
    	return statsTrafficDAO.selectStatsTrafficPubCompanyList(statsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficApiCategoryList
     * @Method description : API 서비스 구분 코드 조회
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    public List<StatsTrafficVO> selectStatsTrafficApiCategoryList(StatsTrafficVO statsTrafficVO) throws Exception{
    	return statsTrafficDAO.selectStatsTrafficApiCategoryList(statsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficApiNameList
     * @Method description : API 서비스 이름 코드 조회
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    public List<StatsTrafficVO> selectStatsTrafficApiNameList(StatsTrafficVO statsTrafficVO) throws Exception{
    	return statsTrafficDAO.selectStatsTrafficApiNameList(statsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficApiServiceList
     * @Method description : 세부 API 서비스 코드 조회
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    public List<StatsTrafficVO> selectStatsTrafficApiServiceList(StatsTrafficVO statsTrafficVO) throws Exception{
    	//1. header 셋팅
    	HashMap resultHeader = getHeaderInfo(statsTrafficVO);
    	statsTrafficVO.setTrafficTableCondition(resultHeader.get("trafficTableCondition").toString());
    	
    	return statsTrafficDAO.selectStatsTrafficApiServiceList(statsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficSubCompanyList
     * @Method description : 앱 개발자 코드 조회
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    public List<StatsTrafficVO> selectStatsTrafficSubCompanyList(StatsTrafficVO statsTrafficVO) throws Exception{
    	return statsTrafficDAO.selectStatsTrafficSubCompanyList(statsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficAppNameList
     * @Method description : 앱이름 코드 조회
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    public List<StatsTrafficVO> selectStatsTrafficAppNameList(StatsTrafficVO statsTrafficVO) throws Exception{
    	return statsTrafficDAO.selectStatsTrafficAppNameList(statsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficApiPlanNameList
     * @Method description : Plan 종류
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    public List<StatsTrafficVO> selectStatsTrafficApiPlanNameList(StatsTrafficVO statsTrafficVO) throws Exception{
    	return statsTrafficDAO.selectStatsTrafficApiPlanNameList(statsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficList
     * @Method description : 트레픽 통계 목록을 조회한다.
     * @param              : StatsTrafficVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectStatsTrafficList(StatsTrafficVO statsTrafficVO) throws Exception{
    	//2. header 셋팅
    	HashMap resultHeader = getHeaderInfo(statsTrafficVO);
    	
    	//3. header 데이터를 이용하여 중복 쿼리 생성
    	statsTrafficVO.setSql1(resultHeader.get("sql1").toString());
    	statsTrafficVO.setSql2(resultHeader.get("sql2").toString());
    	statsTrafficVO.setSql3(resultHeader.get("sql3").toString());
    	
    	statsTrafficVO.setTrafficTable(resultHeader.get("trafficTable").toString());
    	statsTrafficVO.setTrafficTableCondition(resultHeader.get("trafficTableCondition").toString());
    	
    	//4. 데이터 조회
    	List<HashMap> resultList = null;
    	if("cnt".equals(statsTrafficVO.getSearchStatsType())){
    		resultList = statsTrafficDAO.selectStatsTrafficListCnt(statsTrafficVO);
    	}else{
    		resultList = statsTrafficDAO.selectStatsTrafficListDuration(statsTrafficVO);
    	}
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultHeader", resultHeader.get("header"));
		map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : getHeaderInfo
     * @Method description : 트레픽 통계 목록의 header 정보를 리턴한다.
     * @param              : StatsTrafficVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    public HashMap getHeaderInfo(StatsTrafficVO statsTrafficVO) throws Exception{
    	HashMap list = new HashMap();
    	
    	List<HashMap> header = new ArrayList();
    	
    	StringBuffer sql1 = new StringBuffer();
    	StringBuffer sql2 = new StringBuffer();
    	StringBuffer sql3 = new StringBuffer();
    	
    	String trafficTable = "";									//조회 테이블
    	StringBuffer trafficTableCondition = new StringBuffer();	//테이블 기본 조회 조건
    	
    	//1. Head용 조회 기준일 셋팅
    	List<StatsTrafficVO> searchDateList = statsTrafficDAO.selectStatsTrafficSearchDate(statsTrafficVO);
    	    	
    	//구분 : 분
    	if("min".equals(statsTrafficVO.getSearchTrafficType())){
    		//조회테이블 셋팅
    		trafficTable = "adm_api_traffic_min";
    		//분 : 최대 -2 시간 (조회 시간 기준 -2시간)
    		//ex ) and date_format(str_to_date(a.std_date, '%Y%m%d%H%i'), '%Y%m%d%H')
    		//		   between date_format(str_to_date(#searchDateTime#, '%Y%m%d%H') - INTERVAL 1 HOUR, '%Y%m%d%H')
            //         and #searchDateTime#
    		trafficTableCondition.append("and ");
    		trafficTableCondition.append(" date_format(str_to_date(a.std_date, '%Y%m%d%H%i'), '%Y%m%d%H') ");
    		trafficTableCondition.append(" between date_format(str_to_date('"+statsTrafficVO.getSearchDateTime()+"', '%Y%m%d%H') - INTERVAL 1 HOUR, '%Y%m%d%H') ");
    		trafficTableCondition.append(" and '"+statsTrafficVO.getSearchDateTime()+"' ");
    		        	    		
    		//분의 기준 시간 만큼, 조회조건을 생성한다.
    		for(int time=1; time<=POLICY_MIN_TIME; time++){
    			for(int i=0; i<POLICY_MIN_BASE.length; i++){
    				//*********************************************************************
    				//sql 1
    				//건수기준 ex) format(ifnull(min_1_05, 0), 0) as min_1_05,
    				if("cnt".equals(statsTrafficVO.getSearchStatsType())){
    					sql1.append(",format(ifnull(min_"+time+"_"+POLICY_MIN_BASE[i]+", 0), 0) as min_"+time+"_"+POLICY_MIN_BASE[i]);	
    				//응답시간 기준 ex) format(ifnull(min_1_05, 0), 3) as min_1_05,
    				}else{
    					sql1.append(",format(ifnull(min_"+time+"_"+POLICY_MIN_BASE[i]+", 0), 3) as min_"+time+"_"+POLICY_MIN_BASE[i]);
    				}
    				
    				//*********************************************************************
    				//sql 2
    				//건수기준 ex) sum(min_1_05) as min_1_05
    				if("cnt".equals(statsTrafficVO.getSearchStatsType())){
    					sql2.append(",sum(min_"+time+"_"+POLICY_MIN_BASE[i]+") as min_"+time+"_"+POLICY_MIN_BASE[i]);	
    				//응답시간 기준 ex) round(sum(min_1_05), 3) as min_1_05
    				}else{
    					sql2.append(",round(sum(min_"+time+"_"+POLICY_MIN_BASE[i]+"), 3) as min_"+time+"_"+POLICY_MIN_BASE[i]);
    				}
    				
    				//*********************************************************************
    				//sql 3 : 
    				// ex time1 ) 	case when a.std_date = date_format(str_to_date(concat('2016063014', '05'), '%Y%m%d%H%i'), '%Y%m%d%H%i')
    				//				then a.duration_data else 0 end as min_1_05,
    				// ex time2 ) 	case when a.std_date = date_format(str_to_date(concat('2016063014', '05'), '%Y%m%d%H%i') + INTERVAL 1 HOUR, '%Y%m%d%H%i')
                    //				then a.duration_data else 0 end as min_2_05,
    				String searchDateTime = statsTrafficVO.getSearchDateTime();
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
    					StatsTrafficVO data = (StatsTrafficVO) searchDateList.get(x);
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
    	}else if("hourly".equals(statsTrafficVO.getSearchTrafficType())){
    		//조회테이블 셋팅
    		trafficTable = "adm_api_traffic_hourly";
    		//시간 : 최대 2 일 (조회일/시 기준 -2일)
    		//ex ) and date_format(str_to_date(a.std_date, '%Y%m%d%H'), '%Y%m%d%H') 
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d%H') - INTERVAL 47 HOUR, '%Y%m%d%H')
    		//         and #searchDateTime#
    		trafficTableCondition.append("and ");
    		trafficTableCondition.append(" date_format(str_to_date(a.std_date, '%Y%m%d%H'), '%Y%m%d%H') ");
    		trafficTableCondition.append(" between date_format(str_to_date('"+statsTrafficVO.getSearchDateTime()+"', '%Y%m%d%H') - INTERVAL 47 HOUR, '%Y%m%d%H') ");
    		trafficTableCondition.append(" and '"+statsTrafficVO.getSearchDateTime()+"' ");
    		
    		//*********************************************************************
			//data 셋팅
			String headerDateString = "";
			for(int x=0; x<searchDateList.size(); x++){
				StatsTrafficVO data = (StatsTrafficVO) searchDateList.get(x);
				
				//*********************************************************************
				//header string 셋팅
				HashMap headerInfo = new HashMap();
				headerInfo.put("headerId", "hourly_"+data.getSearchStdDate());
				headerInfo.put("headerName", data.getSearchStdDateText());
				header.add(headerInfo);
				
				//*********************************************************************
				//sql 1
				//건수기준 ex) format(ifnull(hourly_2016060913, 0), 0) as hourly_2016060913
				if("cnt".equals(statsTrafficVO.getSearchStatsType())){
					sql1.append(",format(ifnull(hourly_"+data.getSearchStdDate()+", 0), 0) as hourly_"+data.getSearchStdDate());
				//응답시간 기준 ex) format(ifnull(hourly_2016060913, 0), 3) as hourly_2016060913
				}else{
					sql1.append(",format(ifnull(hourly_"+data.getSearchStdDate()+", 0), 3) as hourly_"+data.getSearchStdDate());
				}
				
				//*********************************************************************
				//sql 2
				//건수기준 ex) sum(hourly_2016060913) as hourly_2016060913
				if("cnt".equals(statsTrafficVO.getSearchStatsType())){
					sql2.append(",sum(hourly_"+data.getSearchStdDate()+") as hourly_"+data.getSearchStdDate());	
				//응답시간 기준 ex) round(sum(hourly_2016060913), 3) as hourly_2016060913
				}else{
					sql2.append(",round(sum(hourly_"+data.getSearchStdDate()+"), 3) as hourly_"+data.getSearchStdDate());	
				}
				
				//*********************************************************************
				//sql 3 : 
				// ex) 	case when a.std_date = date_format(str_to_date('2016060913', '%Y%m%d%H'), '%Y%m%d%H')
                //		then a.duration_data else 0 end as hourly_2016060913
				sql3.append(",case when a.std_date = date_format(str_to_date("+data.getSearchStdDate()+", '%Y%m%d%H'), '%Y%m%d%H')");
				sql3.append(" then a.duration_data else 0 end as hourly_"+data.getSearchStdDate());
				
			}    		
    	
    	//구분 : 일
    	}else if("daily".equals(statsTrafficVO.getSearchTrafficType())){
    		//조회테이블 셋팅
    		trafficTable = "adm_api_traffic_daily";
    		//일 : 최대 2개월 (조회 일 기준 -60일)
    		//ex ) and date_format(str_to_date(a.std_date, '%Y%m%d'), '%Y%m%d') 
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d') - INTERVAL 60 DAY, '%Y%m%d')
            //         and #searchDateTime#
    		trafficTableCondition.append("and ");
    		trafficTableCondition.append(" date_format(str_to_date(a.std_date, '%Y%m%d'), '%Y%m%d') ");
    		trafficTableCondition.append(" between date_format(str_to_date('"+statsTrafficVO.getSearchDateTime()+"', '%Y%m%d') - INTERVAL 60 DAY, '%Y%m%d') ");
    		trafficTableCondition.append(" and '"+statsTrafficVO.getSearchDateTime()+"' ");
    		
    		//*********************************************************************
			//data 셋팅
			String headerDateString = "";
			for(int x=0; x<searchDateList.size(); x++){
				StatsTrafficVO data = (StatsTrafficVO) searchDateList.get(x);
				
				//*********************************************************************
				//header string 셋팅
				HashMap headerInfo = new HashMap();
				headerInfo.put("headerId", "daily_"+data.getSearchStdDate());
				headerInfo.put("headerName", data.getSearchStdDateText());
				header.add(headerInfo);
				
				//*********************************************************************
				//sql 1
				//건수기준 ex) format(ifnull(daily_20160609, 0), 0) as daily_20160609
				if("cnt".equals(statsTrafficVO.getSearchStatsType())){
					sql1.append(",format(ifnull(daily_"+data.getSearchStdDate()+", 0), 0) as daily_"+data.getSearchStdDate());
				//응답시간 기준 ex) format(ifnull(daily_20160609, 0), 3) as daily_20160609
				}else{
					sql1.append(",format(ifnull(daily_"+data.getSearchStdDate()+", 0), 3) as daily_"+data.getSearchStdDate());
				}
				
				//*********************************************************************
				//sql 2
				//건수기준 ex) sum(daily_20160609) as daily_20160609
				if("cnt".equals(statsTrafficVO.getSearchStatsType())){
					sql2.append(",sum(daily_"+data.getSearchStdDate()+") as daily_"+data.getSearchStdDate());	
				//응답시간 기준 ex) round(sum(daily_20160609), 3) as daily_20160609
				}else{
					sql2.append(",round(sum(daily_"+data.getSearchStdDate()+"), 3) as daily_"+data.getSearchStdDate());	
				}
				
				//*********************************************************************
				//sql 3 : 
				// ex) 	case when a.std_date = date_format(str_to_date('20160609', '%Y%m%d'), '%Y%m%d')
                //		then a.duration_data else 0 end as daily_20160609
				sql3.append(",case when a.std_date = date_format(str_to_date("+data.getSearchStdDate()+", '%Y%m%d'), '%Y%m%d')");
				sql3.append(" then a.duration_data else 0 end as daily_"+data.getSearchStdDate());
				
			}    
    		
   		//구분 : 개월
    	}else if("monthly".equals(statsTrafficVO.getSearchTrafficType())){
    		//조회테이블 셋팅
    		trafficTable = "adm_api_traffic_monthly";
    		//개월 : 최대 2년 (조회 월 기준 -23개월)
    		//ex ) and date_format(str_to_date(a.std_date, '%Y%m'), '%Y%m') 
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d') - INTERVAL 23 MONTH, '%Y%m')
            //         and date_format(str_to_date(#searchDateTime#, '%Y%m%d'), '%Y%m')
    		trafficTableCondition.append("and ");
    		trafficTableCondition.append(" date_format(str_to_date(a.std_date, '%Y%m%d'), '%Y%m') ");
    		trafficTableCondition.append(" between date_format(str_to_date('"+statsTrafficVO.getSearchDateTime()+"', '%Y%m%d') - INTERVAL 23 MONTH, '%Y%m') ");
    		trafficTableCondition.append(" and date_format(str_to_date('"+statsTrafficVO.getSearchDateTime()+"', '%Y%m%d'), '%Y%m') ");
    		
    		//*********************************************************************
			//data 셋팅
			String headerDateString = "";
			for(int x=0; x<searchDateList.size(); x++){
				StatsTrafficVO data = (StatsTrafficVO) searchDateList.get(x);
				
				//*********************************************************************
				//header string 셋팅
				HashMap headerInfo = new HashMap();
				headerInfo.put("headerId", "monthly_"+data.getSearchStdDate());
				headerInfo.put("headerName", data.getSearchStdDateText());
				header.add(headerInfo);
				
				//*********************************************************************
				//sql 1
				//건수기준 ex) format(ifnull(monthly_201606, 0), 0) as monthly_201606
				if("cnt".equals(statsTrafficVO.getSearchStatsType())){
					sql1.append(",format(ifnull(monthly_"+data.getSearchStdDate()+", 0), 0) as monthly_"+data.getSearchStdDate());
				//응답시간 기준 ex) format(ifnull(monthly_201606, 0), 3) as monthly_201606
				}else{
					sql1.append(",format(ifnull(monthly_"+data.getSearchStdDate()+", 0), 3) as monthly_"+data.getSearchStdDate());
				}
				
				//*********************************************************************
				//sql 2
				//건수기준 ex) sum(monthly_201606) as monthly_201606
				if("cnt".equals(statsTrafficVO.getSearchStatsType())){
					sql2.append(",sum(monthly_"+data.getSearchStdDate()+") as monthly_"+data.getSearchStdDate());	
				//응답시간 기준 ex) round(sum(monthly_201606), 3) as monthly_201606
				}else{
					sql2.append(",round(sum(monthly_"+data.getSearchStdDate()+"), 3) as monthly_"+data.getSearchStdDate());	
				}
				
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
        
}
