package kr.co.koscom.oppf.apt.stats.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.stats.service.StatsServiceService;
import kr.co.koscom.oppf.apt.stats.service.StatsServiceVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : StatsServiceServiceImpl.java
* @Comment  : 회원 통계 관리를 위한 위한 Service
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
@Service("StatsServiceService")
public class StatsServiceServiceImpl implements StatsServiceService{
    
    @Resource(name = "StatsServiceDAO")
    private StatsServiceDAO statsServiceDAO;
    
    /**
     * @Method Name        : selectStatsServicePubCompanyList
     * @Method description : API 서비스 제공자 코드 조회
     * @param              : StatsServiceVO
     * @return             : List<StatsServiceVO>
     * @throws             : Exception
     */
    public List<StatsServiceVO> selectStatsServicePubCompanyList(StatsServiceVO statsServiceVO) throws Exception{
    	return statsServiceDAO.selectStatsServicePubCompanyList(statsServiceVO);
    }
    
    /**
     * @Method Name        : selectStatsServiceApiCategoryList
     * @Method description : API 서비스 구분 코드 조회
     * @param              : StatsServiceVO
     * @return             : List<StatsServiceVO>
     * @throws             : Exception
     */
    public List<StatsServiceVO> selectStatsServiceApiCategoryList(StatsServiceVO statsServiceVO) throws Exception{
    	return statsServiceDAO.selectStatsServiceApiCategoryList(statsServiceVO);
    }
    
    /**
     * @Method Name        : selectStatsServiceSubCompanyList
     * @Method description : 앱 개발자 코드 조회
     * @param              : StatsServiceVO
     * @return             : List<StatsServiceVO>
     * @throws             : Exception
     */
    public List<StatsServiceVO> selectStatsServiceSubCompanyList(StatsServiceVO statsServiceVO) throws Exception{
    	return statsServiceDAO.selectStatsServiceSubCompanyList(statsServiceVO);
    }
    
    /**
     * @Method Name        : selectStatsServiceAppNameList
     * @Method description : 앱이름 코드 조회
     * @param              : StatsServiceVO
     * @return             : List<StatsServiceVO>
     * @throws             : Exception
     */
    public List<StatsServiceVO> selectStatsServiceAppNameList(StatsServiceVO statsServiceVO) throws Exception{
    	return statsServiceDAO.selectStatsServiceAppNameList(statsServiceVO);
    }
    
    /**
     * @Method Name        : selectStatsServiceList
     * @Method description : 서비스 연결 통계 목록을 조회한다.
     * @param              : StatsServiceVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectStatsServiceList(StatsServiceVO statsServiceVO) throws Exception{
    	//2. header 셋팅
    	HashMap resultHeader = getHeaderInfo(statsServiceVO);
    	
    	//3. header 데이터를 이용하여 중복 쿼리 생성
    	statsServiceVO.setSql1(resultHeader.get("sql1").toString());
    	statsServiceVO.setSql2(resultHeader.get("sql2").toString());
    	statsServiceVO.setSql3(resultHeader.get("sql3").toString());
    	
    	statsServiceVO.setSearchTableCondition(resultHeader.get("searchTableCondition").toString());
    	
    	//4. 데이터 조회
    	List<HashMap> resultList = statsServiceDAO.selectStatsServiceList(statsServiceVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultHeader", resultHeader.get("header"));
		map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : getHeaderInfo
     * @Method description : 회원 통계 목록의 header 정보를 리턴한다.
     * @param              : StatsServiceVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    public HashMap getHeaderInfo(StatsServiceVO statsServiceVO) throws Exception{
    	HashMap list = new HashMap();
    	
    	List<HashMap> header = new ArrayList();
    	
    	StringBuffer sql1 = new StringBuffer();
    	StringBuffer sql2 = new StringBuffer();
    	StringBuffer sql3 = new StringBuffer();
    	
    	StringBuffer searchTableCondition = new StringBuffer();	//테이블 기본 조회 조건
    	
    	//1. Head용 조회 기준일 셋팅
    	List<StatsServiceVO> searchDateList = statsServiceDAO.selectStatsServiceSearchDate(statsServiceVO);
    	    	
    	//구분 : 시간
    	if("hourly".equals(statsServiceVO.getSearchType())){
    		//시간 : 최대 2 일 (조회일/시 기준 - 2일)
    		//ex ) and date_format(a.terms_chg_date, '%Y%m%d%H')
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d%H') - INTERVAL 47 HOUR, '%Y%m%d%H')
            //         and #searchDateTime#
    		searchTableCondition.append("and ");
    		searchTableCondition.append(" date_format(a.terms_chg_date, '%Y%m%d%H') ");
    		searchTableCondition.append(" between date_format(str_to_date('"+statsServiceVO.getSearchDateTime()+"', '%Y%m%d%H') - INTERVAL 47 HOUR, '%Y%m%d%H') ");
    		searchTableCondition.append(" and '"+statsServiceVO.getSearchDateTime()+"' ");
    		    		
    		//*********************************************************************
			//data 셋팅
			String headerDateString = "";
			for(int x=0; x<searchDateList.size(); x++){
				StatsServiceVO data = (StatsServiceVO) searchDateList.get(x);
				
				//*********************************************************************
				//header string 셋팅
				HashMap headerInfo = new HashMap();
				headerInfo.put("headerId", "data_"+data.getSearchStdDate());
				headerInfo.put("headerName", data.getSearchStdDateText());
				header.add(headerInfo);
				
				//*********************************************************************
				//sql 1
				//ex) format(ifnull(a.data_2016061715, 0), 0)     as data_2016061715
				sql1.append(",format(ifnull(a.data_"+data.getSearchStdDate()+", 0), 0)     as data_"+data.getSearchStdDate());
				
				//*********************************************************************
				//sql 2
				//ex)
				//sum(
				//    case when a.std_date = date_format(str_to_date('2016061715', '%Y%m%d%H'), '%Y%m%d%H')
				//    then 1 else 0 end 
				//) as data_2016061715
				sql2.append(",sum(");
				sql2.append(" case when a.std_date = date_format(str_to_date('"+data.getSearchStdDate()+"', '%Y%m%d%H'), '%Y%m%d%H') ");
				sql2.append(" then 1 else 0 end ");
				sql2.append(") as data_"+data.getSearchStdDate());
			}
			//*********************************************************************
			//sql 3
			//ex) date_format(a.terms_chg_date, '%Y%m%d%H') as std_date
			sql3.append(",date_format(a.terms_chg_date, '%Y%m%d%H') as std_date ");
    	
    	//구분 : 일
    	}else if("daily".equals(statsServiceVO.getSearchType())){
    		//일 : 최대 2개월 (조회 일 기준 - 60일)
    		//ex ) and date_format(a.terms_chg_date, '%Y%m%d%H')
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d') - INTERVAL 60 DAY, '%Y%m%d')
            //         and #searchDateTime#
    		searchTableCondition.append("and ");
    		searchTableCondition.append(" date_format(a.terms_chg_date, '%Y%m%d') ");
    		searchTableCondition.append(" between date_format(str_to_date('"+statsServiceVO.getSearchDateTime()+"', '%Y%m%d') - INTERVAL 60 DAY, '%Y%m%d') ");
    		searchTableCondition.append(" and '"+statsServiceVO.getSearchDateTime()+"' ");
    		
    		//*********************************************************************
			//data 셋팅
			String headerDateString = "";
			for(int x=0; x<searchDateList.size(); x++){
				StatsServiceVO data = (StatsServiceVO) searchDateList.get(x);
				
				//*********************************************************************
				//header string 셋팅
				HashMap headerInfo = new HashMap();
				headerInfo.put("headerId", "data_"+data.getSearchStdDate());
				headerInfo.put("headerName", data.getSearchStdDateText());
				header.add(headerInfo);
				
				//*********************************************************************
				//sql 1
				//ex) format(ifnull(a.data_20160617, 0), 0)     as data_20160617
				sql1.append(",format(ifnull(a.data_"+data.getSearchStdDate()+", 0), 0)     as data_"+data.getSearchStdDate());
				
				//*********************************************************************
				//sql 2
				//ex)
				//sum(
				//    case when a.std_date = date_format(str_to_date('20160617', '%Y%m%d'), '%Y%m%d')
				//    then 1 else 0 end 
				//) as data_20160617
				sql2.append(",sum(");
				sql2.append(" case when a.std_date = date_format(str_to_date('"+data.getSearchStdDate()+"', '%Y%m%d'), '%Y%m%d') ");
				sql2.append(" then 1 else 0 end ");
				sql2.append(") as data_"+data.getSearchStdDate());
			}    
			//*********************************************************************
			//sql 3
			//ex) date_format(a.terms_chg_date, '%Y%m%d') as std_date
			sql3.append(",date_format(a.terms_chg_date, '%Y%m%d') as std_date ");
    		
   		//구분 : 개월
    	}else if("monthly".equals(statsServiceVO.getSearchType())){
    		//개월 : 최대 2년 (조회 월 기준 - 23개월)
    		//ex ) and date_format(a.terms_chg_date, '%Y%m')
            //         and date_format(str_to_date(#searchDateTime#, '%Y%m%d') - INTERVAL 23 MONTH, '%Y%m')
            //         between date_format(str_to_date('#searchDateTime#', '%Y%m%d'), '%Y%m')
    		searchTableCondition.append("and ");
    		searchTableCondition.append(" date_format(a.terms_chg_date, '%Y%m') ");
    		searchTableCondition.append(" between date_format(str_to_date('"+statsServiceVO.getSearchDateTime()+"', '%Y%m%d') - INTERVAL 23 MONTH, '%Y%m') ");
    		searchTableCondition.append(" and date_format(str_to_date('"+statsServiceVO.getSearchDateTime()+"', '%Y%m%d'), '%Y%m') ");    		
    		
    		//*********************************************************************
			//data 셋팅
			String headerDateString = "";
			for(int x=0; x<searchDateList.size(); x++){
				StatsServiceVO data = (StatsServiceVO) searchDateList.get(x);
				
				//*********************************************************************
				//header string 셋팅
				HashMap headerInfo = new HashMap();
				headerInfo.put("headerId", "data_"+data.getSearchStdDate());
				headerInfo.put("headerName", data.getSearchStdDateText());
				header.add(headerInfo);
				
				//*********************************************************************
				//sql 1
				//ex) format(ifnull(a.data_201606, 0), 0)     as data_201606
				sql1.append(",format(ifnull(a.data_"+data.getSearchStdDate()+", 0), 0)     as data_"+data.getSearchStdDate());
				
				//*********************************************************************
				//sql 2
				//ex)
				//sum(
				//    case when a.std_date = date_format(str_to_date('201606', '%Y%m'), '%Y%m')
				//    then 1 else 0 end 
				//) as data_201606
				sql2.append(",sum(");
				sql2.append(" case when a.std_date = date_format(str_to_date('"+data.getSearchStdDate()+"', '%Y%m'), '%Y%m') ");
				sql2.append(" then 1 else 0 end ");
				sql2.append(") as data_"+data.getSearchStdDate());				
			}    
			
			//*********************************************************************
			//sql 3
			//ex) date_format(a.terms_chg_date, '%Y%m') as std_date
			sql3.append(",date_format(a.terms_chg_date, '%Y%m') as std_date ");
    	}
    	
    	//정보 셋팅
    	list.put("sql1", sql1.toString());
    	list.put("sql2", sql2.toString());
    	list.put("sql3", sql3.toString());
    	
    	list.put("header", header);
    	
    	list.put("searchTableCondition", searchTableCondition.toString());
    	    	
    	return list;
    }
        
}
