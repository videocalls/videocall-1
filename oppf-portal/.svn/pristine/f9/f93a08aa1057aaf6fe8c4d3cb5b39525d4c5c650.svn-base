package kr.co.koscom.oppf.apt.stats.web;

import kr.co.koscom.oppf.apt.stats.service.StatsUserService;
import kr.co.koscom.oppf.apt.stats.service.StatsUserVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : StatsUserController.java
* @Comment  : 회원 통계 관리를 위한 위한 Controller
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
@Controller
public class StatsUserController {
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "StatsUserService")
    private StatsUserService statsUserService;
	
	/**
     * @Method Name        : statsUserList
     * @Method description : 회원 통계 조회 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/stats/statsUserList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String statsUserList(@ModelAttribute("StatsUserVO") StatsUserVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/stats/statsUserList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //셋팅 공통코드 : 가입상태
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G005");
        List<CmmFuncVO> regStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("regStatusList", regStatusList);
        
        //param
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : selectStatsUserList
     * @Method description : 회원 통계 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/stats/selectStatsUserList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectStatsUserList(@ModelAttribute("StatsUserVO") StatsUserVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = statsUserService.selectStatsUserList(paramVO);
		model.addAttribute("resultHeader", map.get("resultHeader"));
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : statsUserListExcel
     * @Method description : 회원 통계 Excel 조회 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/stats/statsUserListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String statsUserListExcel(@ModelAttribute("StatsUserVO") StatsUserVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/stats/statsUserListExcel";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        Map<String, Object> map = statsUserService.selectStatsUserList(paramVO);
		model.addAttribute("resultHeader", map.get("resultHeader"));
		model.addAttribute("resultList", map.get("resultList"));
		        
        //param
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
}
