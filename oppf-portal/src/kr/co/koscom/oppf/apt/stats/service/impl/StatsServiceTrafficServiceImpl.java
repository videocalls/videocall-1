package kr.co.koscom.oppf.apt.stats.service.impl;

import kr.co.koscom.oppf.apt.stats.service.StatsServiceTrafficService;
import kr.co.koscom.oppf.apt.stats.service.StatsTrafficVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * StatsServiceTrafficServiceImpl
 * <p>
 * Created by chungyeol.kim on 2017-03-03.
 */
@Service("StatsServiceTrafficService")
public class StatsServiceTrafficServiceImpl implements StatsServiceTrafficService {

    @Autowired
    private StatsServiceTrafficDAO statsServiceTrafficDAO;

    @Override
    public List<StatsTrafficVO> selectStatsTrafficApiNameList(StatsTrafficVO statsTrafficVO) throws Exception {
        return statsServiceTrafficDAO.selectStatsTrafficApiNameList(statsTrafficVO);
    }

    @Override
    public List<StatsTrafficVO> selectStatsTrafficApiPlanNameList(StatsTrafficVO statsTrafficVO) throws Exception {
        return statsServiceTrafficDAO.selectStatsTrafficApiPlanNameList(statsTrafficVO);
    }

    @Override
    public List<StatsTrafficVO> selectStatsTrafficApiNameListForApp(StatsTrafficVO statsTrafficVO) throws Exception {
        return statsServiceTrafficDAO.selectStatsTrafficApiNameListForApp(statsTrafficVO);
    }

    @Override
    public List<StatsTrafficVO> selectStatsTrafficSubCompanyListForApi(StatsTrafficVO statsTrafficVO) throws Exception {
        return statsServiceTrafficDAO.selectStatsTrafficSubCompanyListForApi(statsTrafficVO);
    }

    @Override
    public Map<String, Object> selectStatsTrafficList(StatsTrafficVO statsTrafficVO) throws Exception{

        Map<String, Object> map = new HashMap<>();

        // 쿼리 데이터 설정
        this.setServiceSearchInfo(statsTrafficVO);

        // 결과 리스트
        List<HashMap<String,Object>> resultList;

        // 차트 및 그리드 에 노출되는 헤더 정보
        List<HashMap<String,Object>> searchCodeList;

        // 전체 검색 여부
        boolean isAllData = false;

        // 통계기준에 따른 조회조건
        if("pubCompany".equals(statsTrafficVO.getSearchStatsType()) && !"Y".equals(statsTrafficVO.getSearchPubCompanyCodeIdAllYn())) {
            // 서비스 제공자 기준
            resultList = statsServiceTrafficDAO.selectStatsTrafficList(statsTrafficVO);
            searchCodeList = this.getChartDisplayNames(statsTrafficVO.getPubCompanyList());

        } else if("apiName".equals(statsTrafficVO.getSearchStatsType()) && !"Y".equals(statsTrafficVO.getSearchApiNameAllYn())) {
            // API 서비스 이름 기준
            resultList = statsServiceTrafficDAO.selectStatsTrafficList(statsTrafficVO);
            searchCodeList = this.getChartDisplayNames(statsTrafficVO.getApiNameList());

        } else if("subCompany".equals(statsTrafficVO.getSearchStatsType()) && !"Y".equals(statsTrafficVO.getSearchSubCompanyCodeIdAllYn())) {
            // APP 개발자 기준
            resultList = statsServiceTrafficDAO.selectStatsTrafficList(statsTrafficVO);
            searchCodeList = this.getChartDisplayNames(statsTrafficVO.getSubCompanyList());

        } else if("plan".equals(statsTrafficVO.getSearchStatsType()) && !"Y".equals(statsTrafficVO.getSearchApiPlanNameAllYn())) {
            // Plan 종류 기준
            resultList = statsServiceTrafficDAO.selectStatsTrafficList(statsTrafficVO);
            searchCodeList = this.getChartDisplayNames(statsTrafficVO.getApiPlanNameList());

        } else {
            // 전체
            resultList = statsServiceTrafficDAO.selectStatsTrafficListForAll(statsTrafficVO);

            searchCodeList = new ArrayList<>();
            HashMap<String,Object> header = new HashMap<>();
            header.put("dataField", "cntApiDurationTotal");
            header.put("displayText", "전체");
            searchCodeList.add(header);

            map.put("resultList", resultList);
            isAllData = true;
        }

        if(!isAllData) {
            map = this.getChartData(statsTrafficVO, resultList, searchCodeList);
        }
        map.put("chartDisplayNames", searchCodeList);
        map.put("isAllData", isAllData);

        return map;
    }

    // 통계 기준에 따른 검색 쿼리 셋팅
    private void setServiceSearchInfo(StatsTrafficVO statsTrafficVO) {

        // 통계기준에 따른 조회조건
        if("pubCompany".equals(statsTrafficVO.getSearchStatsType()) && !"Y".equals(statsTrafficVO.getSearchPubCompanyCodeIdAllYn())) {
            statsTrafficVO.setSql1(", B.pub_company_code_id AS codeId, B.pub_company_name AS codeName");
            statsTrafficVO.setSql2("GROUP BY A.std_date, B.pub_company_name, B.pub_company_code_id \n ORDER BY B.pub_company_name, A.std_date asc");
        } else if("apiName".equals(statsTrafficVO.getSearchStatsType()) && !"Y".equals(statsTrafficVO.getSearchApiNameAllYn())) {
            statsTrafficVO.setSql1(", B.api_id AS codeId, B.api_name AS codeName");
            statsTrafficVO.setSql2("GROUP BY A.std_date, B.api_name, B.api_id \n ORDER BY B.api_name, A.std_date asc");
        } else if("subCompany".equals(statsTrafficVO.getSearchStatsType()) && !"Y".equals(statsTrafficVO.getSearchSubCompanyCodeIdAllYn())) {
            statsTrafficVO.setSql1(", B.sub_company_code_id AS codeId, B.sub_company_name AS codeName");
            statsTrafficVO.setSql2("GROUP BY A.std_date, B.sub_company_name, B.sub_company_code_id \n ORDER BY B.sub_company_name, A.std_date asc");
        } else if("plan".equals(statsTrafficVO.getSearchStatsType()) && !"Y".equals(statsTrafficVO.getSearchApiPlanNameAllYn())) {
            statsTrafficVO.setSql1(", B.api_plan_id AS codeId, B.code_name_kor AS codeName");
            statsTrafficVO.setSql2("GROUP BY A.std_date, B.code_name_kor, B.api_plan_id \n ORDER BY B.code_name_kor, A.std_date asc");
        }

        statsTrafficVO.setTrafficTable(this.getTrafficTableName(statsTrafficVO.getSearchTrafficType()));
    }

    // 전체 검색이 아닌 경우의 데이터 설정
    private Map<String, Object> getChartData(StatsTrafficVO statsTrafficVO, List<HashMap<String,Object>> resultList, List<HashMap<String,Object>> searchCodeList) throws Exception {
        Map<String, Object> result = new HashMap<>();

        // 날짜 정보 검색
        List<StatsTrafficVO> stdList = statsServiceTrafficDAO.selectDayStdList(statsTrafficVO);

        // 최대 Data 수치 (차트에 사용)
        int maxValueData = 0;

        // 가공된 데이터 리스트
        List<HashMap<String,Object>> resultData = new ArrayList<>();

        // 검색 조건의 날짜만큼
        for(StatsTrafficVO stdData : stdList) {

            HashMap<String, Object> chartDataForDate = new HashMap<>();
            chartDataForDate.put("Date", stdData.getDate());
            chartDataForDate.put("dateForChart", stdData.getDateForChart());
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

    // 구분 별 검색 대상 테이블 설정
    private String getTrafficTableName(String searchType) {
        if("min".equals(searchType)) {
            return "adm_api_traffic_min";
        } else if("hourly".equals(searchType)) {
            return "adm_api_traffic_hourly";
        } else if("daily".equals(searchType)) {
            return "adm_api_traffic_daily";
        } else {
            return "adm_api_traffic_monthly";
        }
    }

    @Override
    public Map<String, Object> selectStatsAppTrafficList(StatsTrafficVO statsTrafficVO) throws Exception {
        Map<String, Object> map = new HashMap<>();

        // 쿼리 데이터 설정
        this.setAppSearchInfo(statsTrafficVO);

        // 결과 리스트
        List<HashMap<String,Object>> resultList;

        // 차트 및 그리드 에 노출되는 헤더 정보
        List<HashMap<String,Object>> searchCodeList;

        // 전체 검색 여부
        boolean isAllData = false;

        // 통계기준에 따른 조회조건
        if("company".equals(statsTrafficVO.getSearchStatsType()) && !"Y".equals(statsTrafficVO.getSearchSubCompanyCodeIdAllYn())) {
            // APP 개발자 기준
            resultList = statsServiceTrafficDAO.selectStatsTrafficList(statsTrafficVO);
            searchCodeList = this.getChartDisplayNames(statsTrafficVO.getSubCompanyList());

        } else if("appName".equals(statsTrafficVO.getSearchStatsType()) && !"Y".equals(statsTrafficVO.getSearchAppNameAllYn())) {
            // APP 이름 기준
            resultList = statsServiceTrafficDAO.selectStatsTrafficList(statsTrafficVO);
            searchCodeList = this.getChartDisplayNames(statsTrafficVO.getAppNameList());

        } else if("apiName".equals(statsTrafficVO.getSearchStatsType()) && !"Y".equals(statsTrafficVO.getSearchApiNameAllYn())) {
            // API 서비스 이름 기준
            resultList = statsServiceTrafficDAO.selectStatsTrafficList(statsTrafficVO);
            searchCodeList = this.getChartDisplayNames(statsTrafficVO.getApiNameList());

        } else if("plan".equals(statsTrafficVO.getSearchStatsType()) && !"Y".equals(statsTrafficVO.getSearchApiPlanNameAllYn())) {
            // Plan 종류 기준
            resultList = statsServiceTrafficDAO.selectStatsTrafficList(statsTrafficVO);
            searchCodeList = this.getChartDisplayNames(statsTrafficVO.getApiPlanNameList());

        } else {
            // 전체
            resultList = statsServiceTrafficDAO.selectStatsTrafficListForAll(statsTrafficVO);

            searchCodeList = new ArrayList<>();
            HashMap<String,Object> header = new HashMap<>();
            header.put("dataField", "cntApiDurationTotal");
            header.put("displayText", "전체");
            searchCodeList.add(header);

            map.put("resultList", resultList);
            isAllData = true;
        }

        if(!isAllData) {
            map = this.getChartData(statsTrafficVO, resultList, searchCodeList);
        }
        map.put("chartDisplayNames", searchCodeList);
        map.put("isAllData", isAllData);

        return map;
    }

    // 통계 기준에 따른 검색 쿼리 셋팅
    private void setAppSearchInfo(StatsTrafficVO statsTrafficVO) {

        // 통계기준에 따른 조회조건
        if("company".equals(statsTrafficVO.getSearchStatsType()) && !"Y".equals(statsTrafficVO.getSearchSubCompanyCodeIdAllYn())) {
            statsTrafficVO.setSql1(", B.sub_company_code_id AS codeId, B.sub_company_name AS codeName");
            statsTrafficVO.setSql2("GROUP BY A.std_date, B.sub_company_name, B.sub_company_code_id \n ORDER BY B.sub_company_name, A.std_date asc");
        } else if("appName".equals(statsTrafficVO.getSearchStatsType()) && !"Y".equals(statsTrafficVO.getSearchAppNameAllYn())) {
            statsTrafficVO.setSql1(", B.app_id AS codeId, B.app_name AS codeName");
            statsTrafficVO.setSql2("GROUP BY A.std_date, B.app_name, B.app_id \n ORDER BY B.app_name, A.std_date asc");
        } else if("apiName".equals(statsTrafficVO.getSearchStatsType()) && !"Y".equals(statsTrafficVO.getSearchApiNameAllYn())) {
            statsTrafficVO.setSql1(", B.api_id AS codeId, B.api_name AS codeName");
            statsTrafficVO.setSql2("GROUP BY A.std_date, B.api_name, B.api_id \n ORDER BY B.api_name, A.std_date asc");
        } else if("plan".equals(statsTrafficVO.getSearchStatsType()) && !"Y".equals(statsTrafficVO.getSearchApiPlanNameAllYn())) {
            statsTrafficVO.setSql1(", B.api_plan_id AS codeId, B.code_name_kor AS codeName");
            statsTrafficVO.setSql2("GROUP BY A.std_date, B.code_name_kor, B.api_plan_id \n ORDER BY B.code_name_kor, A.std_date asc");
        }

        statsTrafficVO.setTrafficTable(this.getTrafficTableName(statsTrafficVO.getSearchTrafficType()));
    }
}
