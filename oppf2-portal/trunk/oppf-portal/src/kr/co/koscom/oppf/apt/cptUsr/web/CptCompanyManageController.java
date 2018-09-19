package kr.co.koscom.oppf.apt.cptUsr.web;

import kr.co.koscom.oppf.apt.cptUsr.service.CptCompanyManageService;
import kr.co.koscom.oppf.apt.cptUsr.service.CptCompanyManageVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptCompanyManageController.java
* @Comment  : 기업정보 관리 Controller
* @author   : 신동진
* @since    : 2016.06.21
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.21  신동진        최초생성
*
*/
@Controller
public class CptCompanyManageController {

	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "CptCompanyManageService")
    private CptCompanyManageService cptCompanyManageService;
		
	
    /**
     * @Method Name        : sptUserManageList
     * @Method description : 기업정보관리 목록 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cptUsr/cptCompanyManageList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String cptCompanyManageList(@ModelAttribute("CptCompanyManageVO") CptCompanyManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/cptUsr/cptCompanyManageList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //셋팅 공통코드 : 기업역할
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G002");
        List<CmmFuncVO> companyServiceTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("companyServiceTypeList", companyServiceTypeList);
		
		model.addAttribute("CptCompanyManageVO", paramVO);
		
	    return modelView;
	}
		
	/**
     * @Method Name        : selectCptCompanyManageList
     * @Method description : 기업정보관리 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/selectCptCompanyManageList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectCptCompanyManageList(@ModelAttribute("CptCompanyManageVO") CptCompanyManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		
        //order
        paramVO.setListOrder("ifnull(a.exposure_order, 9999) asc, a.company_name_kor_alias asc");
        
        Map<String, Object> map = cptCompanyManageService.selectCptCompanyManageList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : cptCompanyManageListExcel
     * @Method description : 기업정보관리 목록 excel 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cptUsr/cptCompanyManageListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String cptCompanyManageListExcel(@ModelAttribute("CptCompanyManageVO") CptCompanyManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/cptUsr/cptCompanyManageListExcel";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //order
        paramVO.setListOrder("ifnull(a.exposure_order, 9999) asc, a.company_name_kor_alias asc");
        
        Map<String, Object> map = cptCompanyManageService.selectCptCompanyManageListExcel(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		
	    return modelView;
	}
	
	/**
     * @Method Name        : cptCompanyCodeListPopup
     * @Method description : 미 등록 기업 코드 팝업
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cptUsr/cptCompanyCodeListPopup.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String cptCompanyCodeListPopup(@ModelAttribute("CptCompanyManageVO") CptCompanyManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/cptUsr/cptCompanyCodeListPopup";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        
        //셋팅 공통코드 : 기업 코드 표 구분
        cmmFuncVO.setSystem_grp_code("G001");
        List<CmmFuncVO> companyCodeTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("companyCodeTypeList", companyCodeTypeList);
        
        //셋팅 공통코드 : 기업 종류
        cmmFuncVO.setSystem_grp_code("G014");
        List<CmmFuncVO> companyCodeKindList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("companyCodeKindList", companyCodeKindList);
        
        //셋팅 공통코드 : 기업 분류
        cmmFuncVO.setSystem_grp_code("G029");
        List<CmmFuncVO> companyDivCodeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("companyDivCodeList", companyDivCodeList);
		
		model.addAttribute("CptCompanyManageVO", paramVO);
		model.addAttribute("callBakFunc", "fn_addCompanyCallBack");
		
		
	    return modelView;
	}
	
	/**
     * @Method Name        : selectCptCompanyCodeListPopup
     * @Method description : 기업 코드 팝업 리스트 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cptUsr/selectCptCompanyCodePopupList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectCptCompanyCodePopupList(@ModelAttribute("CptCompanyManageVO") CptCompanyManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		
        //order
        paramVO.setListOrder("a.company_name_kor_alias asc,a.company_code_type asc,a.company_code_kind asc,a.company_div_code asc,a.company_code_reg_no desc");
        
        Map<String, Object> map = cptCompanyManageService.selectCptCompanyCodePopupList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : cptCompanyManageDtl
     * @Method description : 기업정보관리 상세 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cptUsr/cptCompanyManageDtl.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String cptCompanyManageDtl(@ModelAttribute("CptCompanyManageVO") CptCompanyManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/cptUsr/cptCompanyManageDtl";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //셋팅 공통코드 : 기업역할
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G002");
        List<CmmFuncVO> companyServiceTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("companyServiceTypeList", companyServiceTypeList);
		
        CptCompanyManageVO resultDetail = cptCompanyManageService.selectCptCompanyManageDtl(paramVO);
		//기업 토큰 라이프 
		if(null == resultDetail.getCompanyProfileRegNo())
			resultDetail.setTokenLifeTime(new Long(OppfProperties.getProperty("Globals.cpt.tokenLifeTime")));
        model.addAttribute("resultDetail", resultDetail);	//상세
        
		model.addAttribute("CptCompanyManageVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : saveCptCompanyManage
     * @Method description : 기업 저장
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/cptUsr/saveCptCompanyManage.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String saveCptCompanyManage(@ModelAttribute("CptCompanyManageVO") CptCompanyManageVO paramVO, @RequestParam("file") MultipartFile file, 
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
			paramVO.setCompanyCi(file.getBytes());
		}
		
		int result = 0;
		String companyProfileRegNo = paramVO.getCompanyProfileRegNo();
		//등록
		if(OppfStringUtil.isEmpty(companyProfileRegNo)){
			companyProfileRegNo = cptCompanyManageService.insertCptCompanyManage(paramVO);
			if(!"".equals(OppfStringUtil.isNullToString(companyProfileRegNo))) result = 1;
		
		//수정
		}else{
			result = cptCompanyManageService.updateCptCompanyManage(paramVO);
		}
		
		//파일 처리를 위해 map에 셋팅한다.
		//HashMap map = new HashMap();
		//map.put(key, value)
		JSONObject map = new JSONObject();
		map.put("result", result);
		map.put("companyProfileRegNo", companyProfileRegNo);
		
        model.addAttribute("result", map);
        model.addAttribute("callBackFunc", "fn_saveCallBack");
		
	    return modelView;
	}
		
}
