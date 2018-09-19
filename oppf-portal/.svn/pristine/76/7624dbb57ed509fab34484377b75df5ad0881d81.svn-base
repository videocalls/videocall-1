package kr.co.koscom.oppf.cpt.myp.stats.web;

import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.cpt.myp.stats.service.CptStatsTrafficService;
import kr.co.koscom.oppf.cpt.myp.stats.service.CptStatsTrafficVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptStatsTrafficController.java
* @Comment  : 기업포털의 Traffic 통계 관리를 위한 위한 Controller
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
@Controller
public class CptStatsTrafficController {
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "CptStatsTrafficService")
    private CptStatsTrafficService cptStatsTrafficService;
	
	/**
     * @Method Name        : changePubCompanyList
     * @Method description : API 서비스 제공자 변경 시 하위 select 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/stats/changePubCompanyList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String changePubCompanyList(@ModelAttribute("CptStatsTrafficVO") CptStatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //API 서비스 이름
        List<CptStatsTrafficVO> apiNameList = cptStatsTrafficService.selectStatsTrafficApiNameList(paramVO);
        model.addAttribute("apiNameList", apiNameList);
        
        //세부 API 서비스
        List<CptStatsTrafficVO> apiServiceList = cptStatsTrafficService.selectStatsTrafficApiServiceList(paramVO);
        model.addAttribute("apiServiceList", apiServiceList);
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : changeApiCategoryList
     * @Method description : API 서비스 구분 변경 시 하위 select 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/stats/changeApiCategoryList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String changeApiCategoryList(@ModelAttribute("CptStatsTrafficVO") CptStatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //API 서비스 이름
        List<CptStatsTrafficVO> apiNameList = cptStatsTrafficService.selectStatsTrafficApiNameList(paramVO);
        model.addAttribute("apiNameList", apiNameList);
        
        //세부 API 서비스
        List<CptStatsTrafficVO> apiServiceList = cptStatsTrafficService.selectStatsTrafficApiServiceList(paramVO);
        model.addAttribute("apiServiceList", apiServiceList);
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : changeApiNameList
     * @Method description : API 서비스 이름 변경 시 하위 select 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	/*@RequestMapping(value="/cpt/myp/stats/changeApiNameList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String changeApiNameList(@ModelAttribute("CptStatsTrafficVO") CptStatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //세부 API 서비스
        List<CptStatsTrafficVO> apiServiceList = cptStatsTrafficService.selectStatsTrafficApiServiceList(paramVO);
        model.addAttribute("apiServiceList", apiServiceList);
				
	    return "jsonView";
	}*/
	
	/**
     * @Method Name        : changeSubCompanyList
     * @Method description : 앱 개발자 변경 시 하위 select 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/stats/changeSubCompanyList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String changeSubCompanyList(@ModelAttribute("CptStatsTrafficVO") CptStatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //앱이름
        List<CptStatsTrafficVO> appNameList = cptStatsTrafficService.selectStatsTrafficAppNameList(paramVO);
        model.addAttribute("appNameList", appNameList);
				
	    return "jsonView";
	}

	/**
     * @Method Name        : statsTrafficApiList
     * @Method description : API 트레픽 통계 조회 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/stats/statsTrafficApiList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String statsTrafficList(@ModelAttribute("CptStatsTrafficVO") CptStatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "cpt/myp/stats/statsTrafficApiList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String companyProfileRegNo = loginVO.getCompany_profile_reg_no();
        String operatorProfileRegNo = loginVO.getOperator_profile_reg_no();
        String companyCodeId = "";
        if(OppfStringUtil.isEmpty(operatorProfileRegNo)){
            return modelView;
        }
        paramVO.setCompanyProfileRegNo(companyProfileRegNo);
        paramVO.setOperatorProfileRegNo(operatorProfileRegNo);
        companyCodeId = cptStatsTrafficService.selectCompanyCodeId(paramVO);	//로그인 사용자의 companyCodeId를 가져온다.
        if(OppfStringUtil.isEmpty(companyCodeId)){
            return modelView;
        }
        paramVO.setCompanyCodeId(companyCodeId);

        //API 서비스 제공자 -> API 서비스 이름을 가져오기 위해 pubcompanyCodeId를 셋팅한다.
        paramVO.setSearchPubCompanyCodeIdAllYn("N");
        
        List<String> searchPubCompanyCodeId = new ArrayList();
        searchPubCompanyCodeId.add(companyCodeId);
        paramVO.setSearchPubCompanyCodeId(searchPubCompanyCodeId);
        
        //API 서비스 이름
        List<CptStatsTrafficVO> apiNameList = cptStatsTrafficService.selectStatsTrafficApiNameList(paramVO);
        model.addAttribute("apiNameList", apiNameList);

        // 위의 API 를 사용하는 APP 개발자
        List<String> searchApiName = new ArrayList<>();
        for(CptStatsTrafficVO apiName : apiNameList) {
            searchApiName.add(apiName.getApiId());
        }
        paramVO.setSearchApiName(searchApiName);
        List<CptStatsTrafficVO> subCompanyList = cptStatsTrafficService.selectStatsTrafficSubCompanyListForApi(paramVO);
        model.addAttribute("subCompanyList", subCompanyList);

        //Plan 종류
//        List<CptStatsTrafficVO> apiPlanNameList = cptStatsTrafficService.selectStatsTrafficApiPlanNameList(paramVO);
//        model.addAttribute("apiPlanNameList", apiPlanNameList);
        
        //param
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}

    @RequestMapping(value="/cpt/myp/stats/changeApiNameList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String changeApiNameList(@ModelAttribute("CptStatsTrafficVO") CptStatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        if("Y".equals(paramVO.getSearchApiNameAllYn())) {
            paramVO.setCompanyProfileRegNo(loginVO.getCompany_profile_reg_no());
            paramVO.setOperatorProfileRegNo(loginVO.getOperator_profile_reg_no());
            String companyCodeId = cptStatsTrafficService.selectCompanyCodeId(paramVO);	//로그인 사용자의 companyCodeId를 가져온다.
            paramVO.setCompanyCodeId(companyCodeId);

            //API 서비스 제공자 -> API 서비스 이름을 가져오기 위해 pubcompanyCodeId를 셋팅한다.
            paramVO.setSearchPubCompanyCodeIdAllYn("N");

            List<String> searchPubCompanyCodeId = new ArrayList();
            searchPubCompanyCodeId.add(companyCodeId);
            paramVO.setSearchPubCompanyCodeId(searchPubCompanyCodeId);

            //API 서비스 이름
            List<CptStatsTrafficVO> apiNameList = cptStatsTrafficService.selectStatsTrafficApiNameList(paramVO);
            model.addAttribute("apiNameList", apiNameList);

            // 위의 API 를 사용하는 APP 개발자
            List<String> searchApiName = new ArrayList<>();
            for(CptStatsTrafficVO apiName : apiNameList) {
                searchApiName.add(apiName.getApiId());
            }
            paramVO.setSearchApiName(searchApiName);
            paramVO.setSearchApiNameAllYn("N");
        }

        //앱개발자
        List<CptStatsTrafficVO> subCompanyList = cptStatsTrafficService.selectStatsTrafficSubCompanyListForApi(paramVO);
        model.addAttribute("subCompanyList", subCompanyList);

        return "jsonView";
    }
	
	/**
     * @Method Name        : selectStatsTrafficApiList
     * @Method description : API 트레픽 통계 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/stats/selectStatsTrafficApiList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectStatsTrafficApiList(@ModelAttribute("CptStatsTrafficVO") CptStatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
                
		/*Map<String, Object> map = cptStatsTrafficService.selectStatsTrafficApiList(paramVO);
		model.addAttribute("resultHeader", map.get("resultHeader"));
		model.addAttribute("resultList", map.get("resultList"));*/

        List<String> pubCompanyCode = new ArrayList<>();
        pubCompanyCode.add(paramVO.getCompanyCodeId());
        paramVO.setSearchPubCompanyCodeId(pubCompanyCode);
        paramVO.setSearchPubCompanyCodeIdAllYn("N");
        Map<String, Object> map = cptStatsTrafficService.selectStatsTrafficList(paramVO);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("chartDisplayNames", map.get("chartDisplayNames"));
        model.addAttribute("maxValueData", map.get("maxValueData"));
        model.addAttribute("isAllData", map.get("isAllData"));
        model.addAttribute("paramVO", paramVO);

		model.addAttribute("paramVO", paramVO);
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : statsTrafficApiListExcel
     * @Method description : Api 트레픽 통계 Excel 조회 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/stats/statsTrafficApiListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String statsTrafficApiListExcel(@ModelAttribute("CptStatsTrafficVO") CptStatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "cpt/myp/stats/statsTrafficApiListExcel";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        List<String> pubCompanyCode = new ArrayList<>();
        pubCompanyCode.add(paramVO.getCompanyCodeId());
        paramVO.setSearchPubCompanyCodeId(pubCompanyCode);
        paramVO.setSearchPubCompanyCodeIdAllYn("N");
        Map<String, Object> map = cptStatsTrafficService.selectStatsTrafficList(paramVO);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("chartDisplayNames", map.get("chartDisplayNames"));
        model.addAttribute("maxValueData", map.get("maxValueData"));
        model.addAttribute("isAllData", map.get("isAllData"));
        model.addAttribute("paramVO", paramVO);

        model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : statsTrafficAppList
     * @Method description : APP. 트레픽 통계 조회 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/stats/statsTrafficAppList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String statsTrafficAppList(@ModelAttribute("CptStatsTrafficVO") CptStatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "cpt/myp/stats/statsTrafficAppList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String companyProfileRegNo = loginVO.getCompany_profile_reg_no();
        String operatorProfileRegNo = loginVO.getOperator_profile_reg_no();
        String companyCodeId = "";
        if(OppfStringUtil.isEmpty(operatorProfileRegNo)){
            return modelView;
        }
        paramVO.setCompanyProfileRegNo(companyProfileRegNo);
        paramVO.setOperatorProfileRegNo(operatorProfileRegNo);
        companyCodeId = cptStatsTrafficService.selectCompanyCodeId(paramVO);	//로그인 사용자의 companyCodeId를 가져온다.
        if(OppfStringUtil.isEmpty(companyCodeId)){
            return modelView;
        }        
        paramVO.setCompanyCodeId(companyCodeId);
        
        //공통코드
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        
        //앱 개발자
        paramVO.setSearchSubCompanyCodeIdAllYn("N");
        
        List<String> searchSubCompanyCodeId = new ArrayList<>();
        searchSubCompanyCodeId.add(companyCodeId);
        paramVO.setSearchSubCompanyCodeId(searchSubCompanyCodeId);
        
        //앱이름
        List<CptStatsTrafficVO> appNameList = cptStatsTrafficService.selectStatsTrafficAppNameList(paramVO);
        model.addAttribute("appNameList", appNameList);

        // 해당 앱이 사용하는 API 서비스 이름
        List<String> appIdList = new ArrayList<>();
        for(CptStatsTrafficVO appInfo : appNameList) {
            appIdList.add(appInfo.getAppId());
        }
        paramVO.setSearchAppName(appIdList);
        List<CptStatsTrafficVO> apiNameList = cptStatsTrafficService.selectStatsTrafficApiNameListForApp(paramVO);
        model.addAttribute("apiNameList", apiNameList);
        //Plan 종류
//        List<CptStatsTrafficVO> apiPlanNameList = cptStatsTrafficService.selectStatsTrafficApiPlanNameList(paramVO);
//        model.addAttribute("apiPlanNameList", apiPlanNameList);
		        
        //param
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}

    @RequestMapping(value="/cpt/myp/stats/changeAppNameList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String changeAppNameList(@ModelAttribute("CptStatsTrafficVO") CptStatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        if("Y".equals(paramVO.getSearchAppNameAllYn())) {
            String companyProfileRegNo = loginVO.getCompany_profile_reg_no();
            String operatorProfileRegNo = loginVO.getOperator_profile_reg_no();
            paramVO.setCompanyProfileRegNo(companyProfileRegNo);
            paramVO.setOperatorProfileRegNo(operatorProfileRegNo);
            String companyCodeId = cptStatsTrafficService.selectCompanyCodeId(paramVO);    //로그인 사용자의 companyCodeId를 가져온다.
            paramVO.setCompanyCodeId(companyCodeId);

            //앱 개발자
            paramVO.setSearchSubCompanyCodeIdAllYn("N");

            List<String> searchSubCompanyCodeId = new ArrayList<>();
            searchSubCompanyCodeId.add(companyCodeId);
            paramVO.setSearchSubCompanyCodeId(searchSubCompanyCodeId);

            //앱이름
            List<CptStatsTrafficVO> appNameList = cptStatsTrafficService.selectStatsTrafficAppNameList(paramVO);
            model.addAttribute("appNameList", appNameList);

            // 해당 앱이 사용하는 API 서비스 이름
            List<String> appIdList = new ArrayList<>();
            for (CptStatsTrafficVO appInfo : appNameList) {
                appIdList.add(appInfo.getAppId());
            }
            paramVO.setSearchAppName(appIdList);
            paramVO.setSearchAppNameAllYn("N");
        }

        //앱이름
        List<CptStatsTrafficVO> apiNameList = cptStatsTrafficService.selectStatsTrafficApiNameListForApp(paramVO);
        model.addAttribute("apiNameList", apiNameList);

        return "jsonView";
    }
	
	/**
     * @Method Name        : selectStatsTrafficAppList
     * @Method description : APP 트레픽 통계 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/stats/selectStatsTrafficAppList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectStatsTrafficAppList(@ModelAttribute("CptStatsTrafficVO") CptStatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		/*Map<String, Object> map = cptStatsTrafficService.selectStatsTrafficAppList(paramVO);
		model.addAttribute("resultHeader", map.get("resultHeader"));
		model.addAttribute("resultList", map.get("resultList"));
		
		model.addAttribute("paramVO", paramVO);*/

        List<String> subCompanyCode = new ArrayList<>();
        subCompanyCode.add(paramVO.getCompanyCodeId());
        paramVO.setSearchSubCompanyCodeId(subCompanyCode);
        paramVO.setSearchSubCompanyCodeIdAllYn("N");
        Map<String, Object> map = cptStatsTrafficService.selectStatsTrafficList(paramVO);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("chartDisplayNames", map.get("chartDisplayNames"));
        model.addAttribute("maxValueData", map.get("maxValueData"));
        model.addAttribute("isAllData", map.get("isAllData"));
        model.addAttribute("paramVO", paramVO);
				
	    return "jsonView";
	}
		
	/**
     * @Method Name        : statsTrafficAppListExcel
     * @Method description : App 트레픽 통계 Excel 조회 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/stats/statsTrafficAppListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String statsTrafficAppListExcel(@ModelAttribute("CptStatsTrafficVO") CptStatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "cpt/myp/stats/statsTrafficAppListExcel";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        List<String> subCompanyCode = new ArrayList<>();
        subCompanyCode.add(paramVO.getCompanyCodeId());
        paramVO.setSearchSubCompanyCodeId(subCompanyCode);
        paramVO.setSearchSubCompanyCodeIdAllYn("N");
        Map<String, Object> map = cptStatsTrafficService.selectStatsTrafficList(paramVO);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("chartDisplayNames", map.get("chartDisplayNames"));
        model.addAttribute("maxValueData", map.get("maxValueData"));
        model.addAttribute("isAllData", map.get("isAllData"));
        model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
}
