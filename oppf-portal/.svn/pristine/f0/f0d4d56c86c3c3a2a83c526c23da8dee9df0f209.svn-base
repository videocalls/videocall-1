package kr.co.koscom.oppf.apt.cmm.service.impl;

import kr.co.koscom.oppf.apt.cmm.service.AptMainService;
import kr.co.koscom.oppf.apt.cmm.service.AptMainVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.impl.CmmFuncDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptMainServiceImpl.java
* @Comment  : admin main 관리를 위한 위한 Service
* @author   : 신동진
* @since    : 2016.07.15
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.07.15  신동진        최초생성
*
*/
@Service("AptMainService")
public class AptMainServiceImpl implements AptMainService{
    
    @Resource(name = "AptMainDAO")
    private AptMainDAO aptMainDAO;
    
    @Resource(name = "CmmFuncDAO")
    private CmmFuncDAO cmmFuncDAO;
    
    /************************************************************************************************
	 * API 트래픽 현황 start
	 ***********************************************************************************************/
    
    /**
     * @Method Name        : selectMainStatsTraffic
     * @Method description : API 트래픽 요약 - 전체건수 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTraffic(AptMainVO aptMainVO) throws Exception{
    	//1. tablecondition 셋팅
    	HashMap resultTableCondition = getMainStatsTrafficTableCondition(aptMainVO);
    	
    	//2. tablecondition 데이터를 이용하여 tablecondition 셋팅
    	aptMainVO.setSearchTable(resultTableCondition.get("searchTable").toString());
    	aptMainVO.setSearchTableCondition(resultTableCondition.get("searchTableCondition").toString());
    	
    	//3. 데이터 조회
    	List<HashMap> resultList = aptMainDAO.selectMainStatsTraffic(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficDtl
     * @Method description : API 트래픽 요약 - 상세건수 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficDtl(AptMainVO aptMainVO) throws Exception{
    	//1. tablecondition 셋팅
    	HashMap resultTableCondition = getMainStatsTrafficTableCondition(aptMainVO);
    	
    	//2. tablecondition 데이터를 이용하여 tablecondition 셋팅
    	aptMainVO.setSearchTable(resultTableCondition.get("searchTable").toString());
    	aptMainVO.setSearchTableCondition(resultTableCondition.get("searchTableCondition").toString());
    	
    	//3. header 정보셋팅
    	CmmFuncVO cmmFuncVO = new CmmFuncVO();
    	cmmFuncVO.setSystem_grp_code("G026");
    	List<CmmFuncVO> headerList = cmmFuncDAO.selectCmmnFuncCode(cmmFuncVO);
    	
    	StringBuffer sql1 = new StringBuffer();		//, ifnull(b.cnt_api_duration_G026001, 0) as cntApiDurationG026001,
    	StringBuffer sql2 = new StringBuffer();		//, sum(case when b.api_category = 'G026001' then a.cnt_api_duration else 0 end) as cnt_api_duration_G026001
    	
    	if(headerList != null){
    		for(int i=0; i<headerList.size(); i++){
    			CmmFuncVO data = (CmmFuncVO) headerList.get(i);
    			
    			//sql 1 setting 
    			sql1.append(", ifnull(b.cnt_api_duration_"+data.getSystem_code()+", 0) as cntApiDuration"+data.getSystem_code());
    			
    			//sql 2 setting
    			sql2.append(", sum(case when b.api_category = '"+data.getSystem_code()+"' then a.cnt_api_duration else 0 end) as cnt_api_duration_"+data.getSystem_code());
    		}
    	}
    	
    	aptMainVO.setSql1(sql1.toString());
    	aptMainVO.setSql2(sql2.toString());
    	
    	//4. 데이터 조회
    	List<HashMap> resultList = aptMainDAO.selectMainStatsTrafficDtl(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficApiTraffic
     * @Method description : API 서비스별 트래픽 현황(요약) 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficApiTraffic(AptMainVO aptMainVO) throws Exception{
    	//1. tablecondition 셋팅
    	HashMap resultTableCondition = getMainStatsTrafficTableCondition(aptMainVO);
    	
    	//2. tablecondition 데이터를 이용하여 tablecondition 셋팅
    	aptMainVO.setSearchTable(resultTableCondition.get("searchTable").toString());
    	aptMainVO.setSearchTableCondition(resultTableCondition.get("searchTableCondition").toString());
    	
    	//3. 데이터 조회
    	List<HashMap> resultList = aptMainDAO.selectMainStatsTrafficApiTraffic(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficServiceTraffic
     * @Method description : 서비스 제동자별 트래픽 현황(요약) 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficServiceTraffic(AptMainVO aptMainVO) throws Exception{
    	//1. tablecondition 셋팅
    	HashMap resultTableCondition = getMainStatsTrafficTableCondition(aptMainVO);
    	
    	//2. tablecondition 데이터를 이용하여 tablecondition 셋팅
    	aptMainVO.setSearchTable(resultTableCondition.get("searchTable").toString());
    	aptMainVO.setSearchTableCondition(resultTableCondition.get("searchTableCondition").toString());
    	
    	//3. 데이터 조회
    	List<HashMap> resultList = aptMainDAO.selectMainStatsTrafficServiceTraffic(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficAppTraffic
     * @Method description : 핀테크 앱 별 트래픽 현황(요약) 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficAppTraffic(AptMainVO aptMainVO) throws Exception{
    	//1. tablecondition 셋팅
    	HashMap resultTableCondition = getMainStatsTrafficTableCondition(aptMainVO);
    	
    	//2. tablecondition 데이터를 이용하여 tablecondition 셋팅
    	aptMainVO.setSearchTable(resultTableCondition.get("searchTable").toString());
    	aptMainVO.setSearchTableCondition(resultTableCondition.get("searchTableCondition").toString());
    	
    	//3. 데이터 조회
    	List<HashMap> resultList = aptMainDAO.selectMainStatsTrafficAppTraffic(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficPlanTraffic
     * @Method description : 사용 Plan 별 트래픽 현황(요약) 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficPlanTraffic(AptMainVO aptMainVO) throws Exception{
    	//1. tablecondition 셋팅
    	HashMap resultTableCondition = getMainStatsTrafficTableCondition(aptMainVO);
    	
    	//2. tablecondition 데이터를 이용하여 tablecondition 셋팅
    	aptMainVO.setSearchTable(resultTableCondition.get("searchTable").toString());
    	aptMainVO.setSearchTableCondition(resultTableCondition.get("searchTableCondition").toString());
    	
    	//3. 데이터 조회
    	List<HashMap> resultList = aptMainDAO.selectMainStatsTrafficPlanTraffic(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
        
    /**
     * @Method Name        : getMainStatsTrafficTableCondition
     * @Method description : API 트래픽 현황의 table condition을 가져온다.
     * @param              : aptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    public HashMap getMainStatsTrafficTableCondition(AptMainVO aptMainVO) throws Exception{
    	HashMap list = new HashMap();
    	
    	String searchTable = "";									//조회 테이블
    	StringBuffer searchTableCondition = new StringBuffer();	//테이블 기본 조회 조건
    	
    	//통계의 데이터 포멧(60컬럼 기준으로 잡는다.)    	
    	//구분 : 분
    	if("min".equals(aptMainVO.getSearchType())){
    		//조회테이블 셋팅
    		searchTable = "adm_api_traffic_min";
    		//분 : 조회시간 기준 - 5시간(12 * 5)
    		//ex ) and date_format(str_to_date(a.std_date, '%Y%m%d%H%i'), '%Y%m%d%H') between
    		//         date_format(str_to_date(#searchDateTime#, '%Y%m%d%H') - INTERVAL 5 HOUR, '%Y%m%d%H')
            //         and #searchDateTime#
//    		searchTableCondition.append("and ");
//    		searchTableCondition.append(" date_format(str_to_date(a.std_date, '%Y%m%d%H%i'), '%Y%m%d%H') between ");
//    		searchTableCondition.append(" date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d%H') - INTERVAL 4 HOUR, '%Y%m%d%H') ");
//    		searchTableCondition.append(" and '"+aptMainVO.getSearchDateTime()+"' ");

			searchTableCondition.append("and (a.std_date between '" + aptMainVO.getSearchDateTimeFrom() + "' and '" + aptMainVO.getSearchDateTimeTo() + "')");
    		    		
    	//구분 : 시간
    	}else if("hourly".equals(aptMainVO.getSearchType())){
    		//조회테이블 셋팅
    		searchTable = "adm_api_traffic_hourly";
    		//시간 : 조회시간 기준 - 59시간(60 시간)
    		//ex ) and date_format(str_to_date(a.std_date, '%Y%m%d%H'), '%Y%m%d%H') 
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d%H') - INTERVAL 59 HOUR, '%Y%m%d%H')
            //         and #searchDateTime#
//    		searchTableCondition.append("and ");
//    		searchTableCondition.append(" date_format(str_to_date(a.std_date, '%Y%m%d%H'), '%Y%m%d%H') between ");
//    		searchTableCondition.append(" date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d%H') - INTERVAL 59 HOUR, '%Y%m%d%H') ");
//    		searchTableCondition.append(" and '"+aptMainVO.getSearchDateTime()+"' ");

			searchTableCondition.append("and (a.std_date between '" + aptMainVO.getSearchDateTimeFrom() + "' and '" + aptMainVO.getSearchDateTimeTo() + "')");
    		    		
    	//구분 : 일
    	}else if("daily".equals(aptMainVO.getSearchType())){
    		//조회테이블 셋팅
    		searchTable = "adm_api_traffic_daily";
    		//일 : 조회시간 기준 - 59일
    		//ex ) and date_format(str_to_date(a.std_date, '%Y%m%d'), '%Y%m%d') 
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d') - INTERVAL 59 DAY, '%Y%m%d')
            //         and #searchDateTime#
//    		searchTableCondition.append("and ");
//    		searchTableCondition.append(" date_format(str_to_date(a.std_date, '%Y%m%d'), '%Y%m%d') ");
//    		searchTableCondition.append(" between date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d') - INTERVAL 59 DAY, '%Y%m%d') ");
//    		searchTableCondition.append(" and '"+aptMainVO.getSearchDateTime()+"' ");

			searchTableCondition.append("and (a.std_date between '" + aptMainVO.getSearchDateTimeFrom() + "' and '" + aptMainVO.getSearchDateTimeTo() + "')");

   		//구분 : 개월
    	}else if("monthly".equals(aptMainVO.getSearchType())){
    		//조회테이블 셋팅
    		searchTable = "adm_api_traffic_monthly";
    		//개월 : 조회시간 기준 - 59개월
    		//ex ) and date_format(str_to_date(a.std_date, '%Y%m'), '%Y%m') 
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d') - INTERVAL 59 MONTH, '%Y%m')
            //         and date_format(str_to_date(#searchDateTime#, '%Y%m%d'), '%Y%m')
//    		searchTableCondition.append("and ");
//    		searchTableCondition.append(" date_format(str_to_date(a.std_date, '%Y%m%d'), '%Y%m') ");
//    		searchTableCondition.append(" between date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d') - INTERVAL 59 MONTH, '%Y%m') ");
//    		searchTableCondition.append(" and date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d'), '%Y%m') ");

			searchTableCondition.append("and (a.std_date between '" + aptMainVO.getSearchDateTimeFrom() + "' and '" + aptMainVO.getSearchDateTimeTo() + "')");
    	}
    	
    	//정보 셋팅
    	list.put("searchTable", searchTable);
    	list.put("searchTableCondition", searchTableCondition.toString());
    	    	
    	return list;
    }
    
    /************************************************************************************************
	 * API 트래픽 현황 end
	 ***********************************************************************************************/
    
    /************************************************************************************************
	 * API 트래픽 상세 현황 start
	 ***********************************************************************************************/
    /**
     * @Method Name        : selectMainStatsTrafficDtlReqCnt
     * @Method description : API 트래픽 요약 - 요청 Count 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficDtlReqCnt(AptMainVO aptMainVO) throws Exception{
    	//1. tablecondition 셋팅
    	HashMap resultTableCondition = getMainStatsTrafficTableCondition(aptMainVO);
    	
    	//2. tablecondition 데이터를 이용하여 tablecondition 셋팅
    	aptMainVO.setSearchTable(resultTableCondition.get("searchTable").toString());
    	aptMainVO.setSearchTableCondition(resultTableCondition.get("searchTableCondition").toString());
    	
    	//3. header 정보셋팅
    	CmmFuncVO cmmFuncVO = new CmmFuncVO();
    	cmmFuncVO.setSystem_grp_code("G026");
    	List<CmmFuncVO> headerList = cmmFuncDAO.selectCmmnFuncCode(cmmFuncVO);
    	
    	StringBuffer sql1 = new StringBuffer();		//, ifnull(b.cnt_api_duration_G026001, 0) as cntApiDurationG026001,
    	StringBuffer sql2 = new StringBuffer();		//, sum(case when b.api_category = 'G026001' then a.cnt_api_duration else 0 end) as cnt_api_duration_G026001
    	
    	if(headerList != null){
    		for(int i=0; i<headerList.size(); i++){
    			CmmFuncVO data = (CmmFuncVO) headerList.get(i);
    			
    			//sql 1 setting 
    			sql1.append(", ifnull(b.cnt_api_duration_"+data.getSystem_code()+", 0) as cntApiDuration"+data.getSystem_code());
    			
    			//sql 2 setting
    			sql2.append(", sum(case when b.api_category = '"+data.getSystem_code()+"' then a.cnt_api_duration else 0 end) as cnt_api_duration_"+data.getSystem_code());
    		}
    	}
    	
    	aptMainVO.setSql1(sql1.toString());
    	aptMainVO.setSql2(sql2.toString());
    	
    	//4. 데이터 조회
    	List<HashMap> resultList = aptMainDAO.selectMainStatsTrafficDtlReqCnt(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficDtlAvgTime
     * @Method description : API 트래픽 요약 - 평균 Time 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficDtlAvgTime(AptMainVO aptMainVO) throws Exception{
    	//1. tablecondition 셋팅
    	HashMap resultTableCondition = getMainStatsTrafficTableCondition(aptMainVO);
    	
    	//2. tablecondition 데이터를 이용하여 tablecondition 셋팅
    	aptMainVO.setSearchTable(resultTableCondition.get("searchTable").toString());
    	aptMainVO.setSearchTableCondition(resultTableCondition.get("searchTableCondition").toString());
    	
    	//3. header 정보셋팅
    	CmmFuncVO cmmFuncVO = new CmmFuncVO();
    	cmmFuncVO.setSystem_grp_code("G026");
    	List<CmmFuncVO> headerList = cmmFuncDAO.selectCmmnFuncCode(cmmFuncVO);
    	
    	StringBuffer sql1 = new StringBuffer();
    	StringBuffer sql2 = new StringBuffer();
    	
    	if(headerList != null){
    		for(int i=0; i<headerList.size(); i++){
    			CmmFuncVO data = (CmmFuncVO) headerList.get(i);
    			
    			//sql 1 setting 
//    			sql1.append(", round(ifnull(round(ifnull(b.avg_api_duration_"+data.getSystem_code()+", 0) / ifnull(b.cnt_sum, 0), 3), 0), 2) as avgApiDuration"+data.getSystem_code());
				sql1.append(", round(ifnull(round(ifnull(b.avg_api_duration_"+data.getSystem_code()+", 0) / ifnull(b.cnt_api_duration_"+data.getSystem_code()+", 0), 3), 0), 2) as avgApiDuration"+data.getSystem_code());
    			
    			//sql 2 setting
//    			sql2.append(", sum(case when b.api_category = '"+data.getSystem_code()+"' then a.avg_api_duration else 0 end) as avg_api_duration_"+data.getSystem_code());
				sql2.append(", sum(case when b.api_category = '"+data.getSystem_code()+"' then a.avg_api_duration else 0 end) as avg_api_duration_"+data.getSystem_code());
				sql2.append(", sum(case when b.api_category = '"+data.getSystem_code()+"' then a.cnt_api_duration else 0 end) as cnt_api_duration_"+data.getSystem_code());
    		}
    	}
    	
    	aptMainVO.setSql1(sql1.toString());
    	aptMainVO.setSql2(sql2.toString());
    	
    	//4. 데이터 조회
    	List<HashMap> resultList = aptMainDAO.selectMainStatsTrafficDtlAvgTime(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficDtlAvgTime
     * @Method description : API 트래픽 현황 - 누적 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficDtlAccList(AptMainVO aptMainVO) throws Exception{
    	
    	List<HashMap> resultList = aptMainDAO.selectMainStatsTrafficDtlAccList(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficDtlSvcCategoryList
     * @Method description : API 트래픽 현황 - 개별(API 서비스 구분) 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficDtlSvcCategoryList(AptMainVO aptMainVO) throws Exception{
    	
    	List<HashMap> resultList = aptMainDAO.selectMainStatsTrafficDtlSvcCategoryList(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficDtlSvcSubcompanyList
     * @Method description : API 트래픽 현황 - 개별(API 서비스  제공자) 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficDtlSvcSubcompanyList(AptMainVO aptMainVO) throws Exception{
    	
    	List<HashMap> resultList = aptMainDAO.selectMainStatsTrafficDtlSvcSubcompanyList(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficDtlSvcPubcompanyList
     * @Method description : API 트래픽 현황 - 개별(핀테크 앱) 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficDtlSvcPubcompanyList(AptMainVO aptMainVO) throws Exception{
    	
    	List<HashMap> resultList = aptMainDAO.selectMainStatsTrafficDtlSvcPubcompanyList(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
    /************************************************************************************************
	 * API 트래픽 상세 현황 end 
	 ***********************************************************************************************/
    
    /************************************************************************************************
	 * 회원가입 및 서비스 연결 현황 start
	 ***********************************************************************************************/
    
    /**
     * @Method Name        : selectMainStatsServiceUserService
     * @Method description : 회원 가입 및 서비스 연결 현황 요약 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsServiceUserService(AptMainVO aptMainVO) throws Exception{
    	//1. tablecondition 셋팅
    	HashMap resultTableCondition = getMainStatsServiceTableCondition(aptMainVO);
    	
    	//2. tablecondition 데이터를 이용하여 tablecondition 셋팅
    	aptMainVO.setSearchTableCondition1(resultTableCondition.get("searchTableCondition1").toString());
    	aptMainVO.setSearchTableCondition2(resultTableCondition.get("searchTableCondition2").toString());
    	
    	aptMainVO.setSql1(resultTableCondition.get("sql1").toString());
    	aptMainVO.setSql2(resultTableCondition.get("sql2").toString());
    	
    	//3. 데이터 조회
    	List<HashMap> resultList = aptMainDAO.selectMainStatsServiceUserService(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectMainStatsServiceUserAccount
     * @Method description : 가상계좌 발급 회원 현황(요약) 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsServiceUserAccount(AptMainVO aptMainVO) throws Exception{
    	//1. tablecondition 셋팅
    	HashMap resultTableCondition = getMainStatsServiceTableCondition(aptMainVO);
    	
    	//2. tablecondition 데이터를 이용하여 tablecondition 셋팅
    	aptMainVO.setSearchTableCondition1(resultTableCondition.get("searchTableCondition1").toString());
    	    	
    	//3. 데이터 조회
    	List<HashMap> resultList = aptMainDAO.selectMainStatsServiceUserAccount(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectMainStatsServiceUserApp
     * @Method description : 핀테크 앱 연결 회원 현황(요약) 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsServiceUserApp(AptMainVO aptMainVO) throws Exception{
    	//1. tablecondition 셋팅
    	HashMap resultTableCondition = getMainStatsServiceTableCondition(aptMainVO);
    	
    	//2. tablecondition 데이터를 이용하여 tablecondition 셋팅
    	aptMainVO.setSearchTableCondition2(resultTableCondition.get("searchTableCondition2").toString());
    	    	
    	//3. 데이터 조회
    	List<HashMap> resultList = aptMainDAO.selectMainStatsServiceUserApp(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectMainStatsServiceSptUserList
     * @Method description : 금융투자 핀테크 포털 회원 가입 현황 요약 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsServiceSptUserList(AptMainVO aptMainVO) throws Exception{
    	//1. 데이터 조회
    	List<HashMap> resultList = aptMainDAO.selectMainStatsServiceSptUserList(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectMainStatsServiceCptUserList
     * @Method description : 기업/금투사 포털 회원 가입 현황 요약 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsServiceCptUserList(AptMainVO aptMainVO) throws Exception{
    	//1. 데이터 조회
    	List<HashMap> resultList = aptMainDAO.selectMainStatsServiceCptUserList(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectMainStatsServiceUserAccountList
     * @Method description : 금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 가상계좌 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsServiceUserAccountList(AptMainVO aptMainVO) throws Exception{
    	//1. 데이터 조회
    	List<HashMap> resultList = aptMainDAO.selectMainStatsServiceUserAccountList(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectMainStatsServiceUserAccountCompanyList
     * @Method description : 금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 서비스 제공자 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsServiceUserAccountCompanyList(AptMainVO aptMainVO) throws Exception{
    	//1. 데이터 조회
    	List<HashMap> resultList = aptMainDAO.selectMainStatsServiceUserAccountCompanyList(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectMainStatsServiceUserServiceAppList
     * @Method description : 금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 앱 개발자 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsServiceUserServiceAppList(AptMainVO aptMainVO) throws Exception{
    	//1. 데이터 조회
    	List<HashMap> resultList = aptMainDAO.selectMainStatsServiceUserServiceAppList(aptMainVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
    	    	
        return map;
    }

    @Override
    public Map<String, Object> selectMainStatsServiceUserServiceCreateDate(AptMainVO aptMainVO) throws Exception {
        //1. tablecondition 셋팅
        HashMap resultTableCondition = getMainStatsServiceTableConditionCreateDate(aptMainVO);

        //2. tablecondition 데이터를 이용하여 tablecondition 셋팅
        aptMainVO.setSearchTableCondition1(resultTableCondition.get("searchTableCondition1").toString());
        aptMainVO.setSearchTableCondition2(resultTableCondition.get("searchTableCondition2").toString());

        aptMainVO.setSql1(resultTableCondition.get("sql1").toString());
        aptMainVO.setSql2(resultTableCondition.get("sql2").toString());

        //3. 데이터 조회
        List<HashMap> resultList = aptMainDAO.selectMainStatsServiceUserService(aptMainVO);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultList", resultList);

        return map;
    }

    /**
     * @Method Name        : getMainStatsServiceTableCondition
     * @Method description : 회원 가입 및 서비스 연결 현황의 table condition을 가져온다.
     * @param              : aptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    public HashMap getMainStatsServiceTableCondition(AptMainVO aptMainVO) throws Exception{
    	HashMap list = new HashMap();
    	
    	StringBuffer sql1 = new StringBuffer();
    	StringBuffer sql2 = new StringBuffer();
    	
    	StringBuffer searchTableCondition1 = new StringBuffer();	//테이블 기본 조회 조건
    	StringBuffer searchTableCondition2 = new StringBuffer();	//테이블 기본 조회 조건
    	
    	//통계의 데이터 포멧(60컬럼 기준으로 잡는다.)
    	//구분 : 시간
    	if("hourly".equals(aptMainVO.getSearchType())){
    		//시간 : 조회시간 기준 - 59시간(60 시간)
    		//ex ) and date_format(ifnull(a.update_date, a.create_date), '%Y%m%d%H')
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d%H') - INTERVAL 59 HOUR, '%Y%m%d%H')
            //         and #searchDateTime#
    		searchTableCondition1.append("and ");
    		searchTableCondition1.append(" date_format(ifnull(a.create_date, a.update_date), '%Y%m%d%H') ");
    		searchTableCondition1.append(" between date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d%H') - INTERVAL 59 HOUR, '%Y%m%d%H') ");
    		searchTableCondition1.append(" and '"+aptMainVO.getSearchDateTime()+"' ");
    		
    		//시간 : 조회시간 기준 - 59시간(60 시간)
    		//ex ) and date_format(a.terms_chg_date, '%Y%m%d%H')
    		//		   between date_format(str_to_date(#searchDateTime#, '%Y%m%d%H') + INTERVAL 1 DAY, '%Y%m%d%H')
            //         and #searchDateTime#
    		searchTableCondition2.append("and ");
    		searchTableCondition2.append(" date_format(a.terms_chg_date, '%Y%m%d%H') ");
    		searchTableCondition2.append(" between date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d%H') - INTERVAL 59 HOUR, '%Y%m%d%H') ");
    		searchTableCondition2.append(" and '"+aptMainVO.getSearchDateTime()+"' ");
    		
    		//*********************************************************************
			//sql 1
    		//ex) ,date_format(ifnull(a.update_date, a.create_date), '%Y%m%d%H') as std_date
    		sql1.append(",date_format(ifnull(a.create_date, a.update_date), '%Y%m%d%H') as std_date");
    		
    		//*********************************************************************
			//sql 2
    		//ex) ,date_format(a.terms_chg_date, '%Y%m%d%H') as std_date
    		sql2.append(",date_format(a.terms_chg_date, '%Y%m%d%H') as std_date");
    		
    	//구분 : 일
    	}else if("daily".equals(aptMainVO.getSearchType())){
    		//일 : 조회시간 기준 - 59일
    		//ex ) and date_format(ifnull(a.update_date, a.create_date), '%Y%m%d')
    		//         between date_format(str_to_date(#searchDateTime#, '%Y%m%d') - INTERVAL 59 DAY, '%Y%m%d')
            //         and #searchDateTime#
    		searchTableCondition1.append("and ");
    		searchTableCondition1.append(" date_format(ifnull(a.create_date, a.update_date), '%Y%m%d') ");
    		searchTableCondition1.append(" between date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d') - INTERVAL 59 DAY, '%Y%m%d') ");
    		searchTableCondition1.append(" and '"+aptMainVO.getSearchDateTime()+"' ");
    		
    		//일 : 조회시간 기준 - 59일
    		//ex ) and date_format(a.terms_chg_date, '%Y%m%d%H')
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d') - INTERVAL 59 DAY, '%Y%m%d')
    		//		   and #searchDateTime#
    		searchTableCondition2.append("and ");
    		searchTableCondition2.append(" date_format(a.terms_chg_date, '%Y%m%d') ");
    		searchTableCondition2.append(" between date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d') - INTERVAL 59 DAY, '%Y%m%d') ");
    		searchTableCondition2.append(" and '"+aptMainVO.getSearchDateTime()+"' ");
    		
    		//*********************************************************************
			//sql 1
    		//ex) ,date_format(ifnull(a.update_date, a.create_date), '%Y%m%d') as std_date
    		sql1.append(",date_format(ifnull(a.create_date, a.update_date), '%Y%m%d') as std_date");
    		
    		//*********************************************************************
			//sql 2
    		//ex) ,date_format(a.terms_chg_date, '%Y%m%d') as std_date
    		sql2.append(",date_format(a.terms_chg_date, '%Y%m%d') as std_date");
    		    		
   		//구분 : 개월
    	}else if("monthly".equals(aptMainVO.getSearchType())){
    		//개월 : 조회시간 기준 - 59개월
    		//ex ) and date_format(ifnull(a.update_date, a.create_date), '%Y%m')
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d') - INTERVAL 59 MONTH, '%Y%m')
    		//		   and date_format(str_to_date('#searchDateTime#', '%Y%m%d'), '%Y%m')
    		searchTableCondition1.append("and ");
    		searchTableCondition1.append(" date_format(ifnull(a.create_date, a.update_date), '%Y%m') ");
    		searchTableCondition1.append(" between date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d') - INTERVAL 59 MONTH, '%Y%m') ");
    		searchTableCondition1.append(" and date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d'), '%Y%m') ");
    		
    		//개월 : 조회시간 기준 - 59개월
    		//ex ) and date_format(a.terms_chg_date, '%Y%m')
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d') - INTERVAL 59 MONTH, '%Y%m')
    		//		   and date_format(str_to_date('#searchDateTime#', '%Y%m%d'), '%Y%m')
    		searchTableCondition2.append("and ");
    		searchTableCondition2.append(" date_format(a.terms_chg_date, '%Y%m') ");
    		searchTableCondition2.append(" between date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d') - INTERVAL 59 MONTH, '%Y%m') ");
    		searchTableCondition2.append(" and date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d'), '%Y%m') ");
    		
    		//*********************************************************************
			//sql 1
    		//ex) ,date_format(ifnull(a.update_date, a.create_date), '%Y%m') as std_date
    		sql1.append(",date_format(ifnull(a.create_date, a.update_date), '%Y%m') as std_date");
    		
    		//*********************************************************************
			//sql 2
    		//ex) ,date_format(a.terms_chg_date, '%Y%m') as std_date
    		sql2.append(",date_format(a.terms_chg_date, '%Y%m') as std_date");
    		
    	}
    	
    	//정보 셋팅
    	list.put("searchTableCondition1", searchTableCondition1.toString());
    	list.put("searchTableCondition2", searchTableCondition2.toString());
    	
    	list.put("sql1", sql1.toString());
    	list.put("sql2", sql2.toString());
    	    	    	
    	return list;
    }


    /**
     * Create Date 별로 만들어달라는 요청
     * */
    public HashMap getMainStatsServiceTableConditionCreateDate(AptMainVO aptMainVO) throws Exception{
        HashMap list = new HashMap();

        StringBuffer sql1 = new StringBuffer();
        StringBuffer sql2 = new StringBuffer();

        StringBuffer searchTableCondition1 = new StringBuffer();	//테이블 기본 조회 조건
        StringBuffer searchTableCondition2 = new StringBuffer();	//테이블 기본 조회 조건

        //통계의 데이터 포멧(60컬럼 기준으로 잡는다.)
        //구분 : 시간
        if("hourly".equals(aptMainVO.getSearchType())){
            //시간 : 조회시간 기준 - 59시간(60 시간)
            //ex ) and date_format(ifnull(a.update_date, a.create_date), '%Y%m%d%H')
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d%H') - INTERVAL 59 HOUR, '%Y%m%d%H')
            //         and #searchDateTime#
            searchTableCondition1.append("and ");
            searchTableCondition1.append(" date_format(a.create_date, '%Y%m%d%H') ");
            searchTableCondition1.append(" between date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d%H') - INTERVAL 59 HOUR, '%Y%m%d%H') ");
            searchTableCondition1.append(" and '"+aptMainVO.getSearchDateTime()+"' ");

            //시간 : 조회시간 기준 - 59시간(60 시간)
            //ex ) and date_format(a.terms_chg_date, '%Y%m%d%H')
            //		   between date_format(str_to_date(#searchDateTime#, '%Y%m%d%H') + INTERVAL 1 DAY, '%Y%m%d%H')
            //         and #searchDateTime#
            searchTableCondition2.append("and ");
            searchTableCondition2.append(" date_format(a.terms_chg_date, '%Y%m%d%H') ");
            searchTableCondition2.append(" between date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d%H') - INTERVAL 59 HOUR, '%Y%m%d%H') ");
            searchTableCondition2.append(" and '"+aptMainVO.getSearchDateTime()+"' ");

            //*********************************************************************
            //sql 1
            //ex) ,date_format(ifnull(a.update_date, a.create_date), '%Y%m%d%H') as std_date
            sql1.append(",date_format(a.create_date, '%Y%m%d%H') as std_date");

            //*********************************************************************
            //sql 2
            //ex) ,date_format(a.terms_chg_date, '%Y%m%d%H') as std_date
            sql2.append(",date_format(a.terms_chg_date, '%Y%m%d%H') as std_date");

            //구분 : 일
        }else if("daily".equals(aptMainVO.getSearchType())){
            //일 : 조회시간 기준 - 59일
            //ex ) and date_format(ifnull(a.update_date, a.create_date), '%Y%m%d')
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d') - INTERVAL 59 DAY, '%Y%m%d')
            //         and #searchDateTime#
            searchTableCondition1.append("and ");
            searchTableCondition1.append(" date_format(a.create_date, '%Y%m%d') ");
            searchTableCondition1.append(" between date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d') - INTERVAL 59 DAY, '%Y%m%d') ");
            searchTableCondition1.append(" and '"+aptMainVO.getSearchDateTime()+"' ");

            //일 : 조회시간 기준 - 59일
            //ex ) and date_format(a.terms_chg_date, '%Y%m%d%H')
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d') - INTERVAL 59 DAY, '%Y%m%d')
            //		   and #searchDateTime#
            searchTableCondition2.append("and ");
            searchTableCondition2.append(" date_format(a.terms_chg_date, '%Y%m%d') ");
            searchTableCondition2.append(" between date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d') - INTERVAL 59 DAY, '%Y%m%d') ");
            searchTableCondition2.append(" and '"+aptMainVO.getSearchDateTime()+"' ");

            //*********************************************************************
            //sql 1
            //ex) ,date_format(ifnull(a.update_date, a.create_date), '%Y%m%d') as std_date
            sql1.append(",date_format(a.create_date, '%Y%m%d') as std_date");

            //*********************************************************************
            //sql 2
            //ex) ,date_format(a.terms_chg_date, '%Y%m%d') as std_date
            sql2.append(",date_format(a.terms_chg_date, '%Y%m%d') as std_date");

            //구분 : 개월
        }else if("monthly".equals(aptMainVO.getSearchType())){
            //개월 : 조회시간 기준 - 59개월
            //ex ) and date_format(ifnull(a.update_date, a.create_date), '%Y%m')
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d') - INTERVAL 59 MONTH, '%Y%m')
            //		   and date_format(str_to_date('#searchDateTime#', '%Y%m%d'), '%Y%m')
            searchTableCondition1.append("and ");
            searchTableCondition1.append(" date_format(a.create_date, '%Y%m') ");
            searchTableCondition1.append(" between date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d') - INTERVAL 59 MONTH, '%Y%m') ");
            searchTableCondition1.append(" and date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d'), '%Y%m') ");

            //개월 : 조회시간 기준 - 59개월
            //ex ) and date_format(a.terms_chg_date, '%Y%m')
            //         between date_format(str_to_date(#searchDateTime#, '%Y%m%d') - INTERVAL 59 MONTH, '%Y%m')
            //		   and date_format(str_to_date('#searchDateTime#', '%Y%m%d'), '%Y%m')
            searchTableCondition2.append("and ");
            searchTableCondition2.append(" date_format(a.terms_chg_date, '%Y%m') ");
            searchTableCondition2.append(" between date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d') - INTERVAL 59 MONTH, '%Y%m') ");
            searchTableCondition2.append(" and date_format(str_to_date('"+aptMainVO.getSearchDateTime()+"', '%Y%m%d'), '%Y%m') ");

            //*********************************************************************
            //sql 1
            //ex) ,date_format(ifnull(a.update_date, a.create_date), '%Y%m') as std_date
            sql1.append(",date_format(a.create_date, '%Y%m') as std_date");

            //*********************************************************************
            //sql 2
            //ex) ,date_format(a.terms_chg_date, '%Y%m') as std_date
            sql2.append(",date_format(a.terms_chg_date, '%Y%m') as std_date");

        }

        //정보 셋팅
        list.put("searchTableCondition1", searchTableCondition1.toString());
        list.put("searchTableCondition2", searchTableCondition2.toString());

        list.put("sql1", sql1.toString());
        list.put("sql2", sql2.toString());

        return list;
    }
        
    /************************************************************************************************
	 * 회원가입 및 서비스 연결 현황 end
	 ***********************************************************************************************/
}
