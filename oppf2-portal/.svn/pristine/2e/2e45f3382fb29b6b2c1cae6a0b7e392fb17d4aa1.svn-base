package kr.co.koscom.oppf.apt.app.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.koscom.oppf.apt.app.service.AppManageService;
import kr.co.koscom.oppf.apt.app.service.AppManageVO;

import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AppManageController.java
* @Comment  : app 관리를 위한 Controller
* @author   : 신동진
* @since    : 2016.05.30
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.30  신동진        최초생성
*
*/
@Controller
public class AppManageController {
	
	private static final Logger log = Logger.getLogger(AppManageController.class);
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "AppManageService")
    private AppManageService appManageService;
	
	/**
     * @Method Name        : appManageList
     * @Method description : app 조회 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/app/appManageList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String appManageList(@ModelAttribute("AppManageVO") AppManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/app/appManageList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
		
		//셋팅 공통코드 : app 구분
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G025");
        List<CmmFuncVO> appCategoryList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("appCategoryList", appCategoryList);
        
        //셋팅 공통코드 : app 상태
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G022");
        List<CmmFuncVO> appStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("appStatusList", appStatusList);
        
        //셋팅 공통코드 : app 계약여부
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G023");
        List<CmmFuncVO> appContractCodeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("appContractCodeList", appContractCodeList);
        
        //셋팅 공통코드 : 서비스 제공자 
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSearchCompanyServiceType("G002003");
        List<CmmFuncVO> companyCodeList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
        model.addAttribute("companyCodeList", companyCodeList);
                
        //param
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : selectAppManageList
     * @Method description : app 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/app/selectAppManageList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectAppManageList(@ModelAttribute("AppManageVO") AppManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		
		Map<String, Object> map = appManageService.selectAppManageList(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectAppManageListExcel
     * @Method description : app excel 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/app/selectAppManageListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectAppManageListExcel(@ModelAttribute("AppManageVO") AppManageVO paramVO, ModelMap model)throws Exception{
		Map<String, Object> map = appManageService.selectAppManageListExcel(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
		
		return "apt/app/appManageListExcel";
	}
	
	/**
     * @Method Name        : appManageDtl
     * @Method description : app 상세 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/app/appManageDtl.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String appManageDtl(@ModelAttribute("AppManageVO") AppManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
            String modelView = "apt/app/appManageDtl";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
		
		//셋팅 공통코드 : app 구분
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G025");
        List<CmmFuncVO> appCategoryList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("appCategoryList", appCategoryList);
        
        //셋팅 공통코드 : app 계약여부
        cmmFuncVO.setSystem_grp_code("G023");
        List<CmmFuncVO> appContractCodeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("appContractCodeList", appContractCodeList);
                
        //상세조회
        AppManageVO resultDetail = appManageService.selectAppManageDtl(paramVO);
        List<AppManageVO> resultApiList = appManageService.selectAppManageDtlApiList(paramVO);
        
        model.addAttribute("resultDetail", resultDetail);
        model.addAttribute("resultApiList", resultApiList);
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : saveAppManage
     * @Method description : app 저장
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/app/saveAppManage.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String saveAppManage(@ModelAttribute("AppManageVO") AppManageVO paramVO, @RequestParam("file") MultipartFile file, 
			HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "cmm/fileView";
        
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	JSONObject map = new JSONObject();
        	map.put("error", "-1");
        	
        	model.addAttribute("result", map);
        	return modelView;
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		//첨부파일 셋팅
		if(file.getBytes().length > 0){
			paramVO.setAppIcon(file.getBytes());
		}		
		
		int result = appManageService.saveAppManage(paramVO);
		
		//파일 처리를 위해 map에 셋팅한다.
		//HashMap map = new HashMap();
		//map.put(key, value)
		JSONObject map = new JSONObject();
		map.put("result", result);
		
        model.addAttribute("result", map);
        model.addAttribute("callBackFunc", "fn_saveCallBack");
		
	    return modelView;
	}
	
}
