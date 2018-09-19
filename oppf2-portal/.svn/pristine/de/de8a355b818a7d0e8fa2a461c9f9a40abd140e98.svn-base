package kr.co.koscom.oppf.apt.cmm.web;

import kr.co.koscom.oppf.apt.cmm.service.AptMainService;
import kr.co.koscom.oppf.apt.cmm.service.AptMainVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.util.OppfSessionUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : AptMainController.java
* @Comment  : admin Main Controller
* @author   : 신동진
* @since    : 2016.04.28
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.04.28  신동진        최초생성
*
*/
@Controller
public class AptMainController {
	
	private static final Logger log = Logger.getLogger(AptMainController.class);
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "AptMainService")
    private AptMainService aptMainService;
	
    /**
     * @Method Name        : mainView
     * @Method description : admin main 페이지 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/mainView.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String loginView(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/cmm/aptMain";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
		
		//메뉴관련 session 삭제
		String systemKind = OppfSessionUtil.getSystemKind(request);        //system kind를 가져온다.
		request.getSession().setAttribute(systemKind+"ParamMenuTopVO", null);
        request.getSession().setAttribute(systemKind+"MenuTopList", null);
        request.getSession().setAttribute(systemKind+"CurrentMenuId", null);
        request.getSession().setAttribute(systemKind+"CurrentParentMenuId", null);
        request.getSession().setAttribute(systemKind+"ParentMenuId", null);
        
	    return modelView;
	}
	
	/**
     * @Method Name        : mainStatsTraffic
     * @Method description : admin main의 API 트래픽 현황 호출
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/mainStatsTraffic.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String mainStatsTraffic(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/cmm/mainStatsTraffic";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //공통코드 : api 구분
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G026");
        List<CmmFuncVO> apiCategoryList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("apiCategoryList", apiCategoryList);

		//API 서비스 제공자
		cmmFuncVO = new CmmFuncVO();
		cmmFuncVO.setSearchCompanyServiceType("G002002");
		List<CmmFuncVO> pubCompanyList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
		model.addAttribute("pubCompanyList", pubCompanyList);

		//앱 개발자
		cmmFuncVO = new CmmFuncVO();
		cmmFuncVO.setSearchCompanyServiceType("G002003");
		List<CmmFuncVO> subCompanyList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
		model.addAttribute("subCompanyList", subCompanyList);
        
	    return modelView;
	}
	
	/**
     * @Method Name        : selectMainStatsTraffic
     * @Method description : admin main의 API 트래픽 현황 > API 트래픽 요약 - 전체건수 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsTraffic.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsTraffic(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsTraffic(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectMainStatsTrafficDtl
     * @Method description : admin main의 API 트래픽 현황 > API 트래픽 요약 - 상세건수 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsTrafficDtl.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsTrafficDtl(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsTrafficDtl(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectMainStatsTrafficApiTraffic
     * @Method description : admin main의 API 트래픽 현황 > API 서비스별 트래픽 현황(요약) 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsTrafficApiTraffic.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsTrafficApiTraffic(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsTrafficApiTraffic(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectMainStatsTrafficServiceTraffic
     * @Method description : admin main의 API 트래픽 현황 > 서비스 제동자별 트래픽 현황(요약) 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsTrafficServiceTraffic.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsTrafficServiceTraffic(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsTrafficServiceTraffic(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectMainStatsTrafficAppTraffic
     * @Method description : admin main의 API 트래픽 현황 > 핀테크 앱 별 트래픽 현황(요약) 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsTrafficAppTraffic.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsTrafficAppTraffic(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsTrafficAppTraffic(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectMainStatsTrafficPlanTraffic
     * @Method description : admin main의 API 트래픽 현황 > 사용 Plan 별 트래픽 현황(요약) 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsTrafficPlanTraffic.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsTrafficPlanTraffic(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsTrafficPlanTraffic(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : mainStatsTrafficDtl
     * @Method description : admin main의 API 트래픽 상세 현황 호출
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/mainStatsTrafficDtl.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String mainStatsTrafficDtl(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/cmm/mainStatsTrafficDtl";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //셋팅 공통코드 : api 구분
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G026");
        List<CmmFuncVO> apiCategoryList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("apiCategoryList", apiCategoryList);

		//API 서비스 제공자
		cmmFuncVO = new CmmFuncVO();
		cmmFuncVO.setSearchCompanyServiceType("G002002");
		List<CmmFuncVO> pubCompanyList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
		model.addAttribute("pubCompanyList", pubCompanyList);

		//앱 개발자
		cmmFuncVO = new CmmFuncVO();
		cmmFuncVO.setSearchCompanyServiceType("G002003");
		List<CmmFuncVO> subCompanyList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
		model.addAttribute("subCompanyList", subCompanyList);
        
	    return modelView;
	}
	
	/**
     * @Method Name        : selectMainStatsTrafficDtlReqCnt
     * @Method description : admin main의 API 트래픽 상세 현황 > API 트래픽 요약 - 요청 Count 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsTrafficDtlReqCnt.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsTrafficDtlReqCnt(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsTrafficDtlReqCnt(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectMainStatsTrafficDtlAvgTime
     * @Method description : admin main의 API 트래픽 상세 현황 > API 트래픽 요약 - 평균 Time 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsTrafficDtlAvgTime.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsTrafficDtlAvgTime(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsTrafficDtlAvgTime(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectMainStatsTrafficDtlAvgTime
     * @Method description : admin main의 API 트래픽 상세 현황 > API 트래픽 현황 - 누적 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsTrafficDtlAccList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsTrafficDtlAccList(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsTrafficDtlAccList(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectMainStatsTrafficDtlSvcCategoryList
     * @Method description : admin main의 API 트래픽 상세 현황 > API 트래픽 현황 - 개별(API 서비스 구분) 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsTrafficDtlSvcCategoryList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsTrafficDtlSvcCategoryList(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsTrafficDtlSvcCategoryList(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectMainStatsTrafficDtlSvcSubcompanyList
     * @Method description : admin main의 API 트래픽 상세 현황 > API 트래픽 현황 - 개별(API 서비스  제공자) 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsTrafficDtlSvcSubcompanyList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsTrafficDtlSvcSubcompanyList(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsTrafficDtlSvcSubcompanyList(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectMainStatsTrafficDtlSvcPubcompanyList
     * @Method description : admin main의 API 트래픽 상세 현황 > API 트래픽 현황 - 개별(핀테크 앱) 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsTrafficDtlSvcPubcompanyList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsTrafficDtlSvcPubcompanyList(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsTrafficDtlSvcPubcompanyList(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : mainStatsService
     * @Method description : admin main의 회원 가입 및 서비스 연결 현황 호출
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/mainStatsService.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String mainStatsService(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/cmm/mainStatsService";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
		
        
	    return modelView;
	}
	
	/**
     * @Method Name        : selectMainStatsServiceUserService
     * @Method description : admin main의 회원 가입 및 서비스 연결 현황 > 회원 가입 및 서비스 연결 현황 요약 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsServiceUserService.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsServiceUserService(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsServiceUserService(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectMainStatsServiceUserAccount
     * @Method description : admin main의 회원 가입 및 서비스 연결 현황 > 가상계좌 발급 회원 현황(요약) 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsServiceUserAccount.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsServiceUserAccount(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsServiceUserAccount(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectMainStatsServiceUserApp
     * @Method description : admin main의 회원 가입 및 서비스 연결 현황 > 핀테크 앱 연결 회원 현황(요약) 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsServiceUserApp.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsServiceUserApp(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsServiceUserApp(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectMainStatsServiceSptUserList
     * @Method description : admin main의 회원 가입 및 서비스 연결 현황 > 금융투자 핀테크 포털 회원 가입 현황 요약 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsServiceSptUserList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsServiceSptUserList(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsServiceSptUserList(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectMainStatsServiceCptUserList
     * @Method description : admin main의 회원 가입 및 서비스 연결 현황 > 기업/금투사 포털 회원 가입 현황 요약 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsServiceCptUserList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsServiceCptUserList(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsServiceCptUserList(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectMainStatsServiceUserAccountList
     * @Method description : admin main의 회원 가입 및 서비스 연결 현황 > 금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 가상계좌 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsServiceUserAccountList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsServiceUserAccountList(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsServiceUserAccountList(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectMainStatsServiceUserAccountCompanyList
     * @Method description : admin main의 회원 가입 및 서비스 연결 현황 > 금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 서비스 제공자 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsServiceUserAccountCompanyList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsServiceUserAccountCompanyList(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsServiceUserAccountCompanyList(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectMainStatsServiceUserServiceAppList
     * @Method description : admin main의 회원 가입 및 서비스 연결 현황 > 금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 앱 개발자 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cmm/selectMainStatsServiceUserServiceAppList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMainStatsServiceUserServiceAppList(@ModelAttribute("AptMainVO") AptMainVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = aptMainService.selectMainStatsServiceUserServiceAppList(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
}
