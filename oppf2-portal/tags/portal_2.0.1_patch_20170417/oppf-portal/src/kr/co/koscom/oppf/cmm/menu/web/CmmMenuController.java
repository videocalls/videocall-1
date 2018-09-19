package kr.co.koscom.oppf.cmm.menu.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.cmm.menu.service.CmmMenuService;
import kr.co.koscom.oppf.cmm.menu.service.CmmMenuVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmMenuController.java
* @Comment  : 공통메뉴 관리를 위한 Controller
* @author   : 이환덕
* @since    : 2016.05.24
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.24  이환덕        최초생성
*
*/
@Controller
public class CmmMenuController {
	
	private static final Logger log = Logger.getLogger(CmmMenuController.class);
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "CmmMenuService")
    private CmmMenuService cmmMenuService;
	
	/**
     * @Method Name        : selectCmmMenuTopList
     * @Method description : 메뉴Top목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/selectCmmMenuTopList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectCmmMenuTopList(@ModelAttribute("CmmMenuVO") CmmMenuVO paramVO, ModelMap model)throws Exception{
	    paramVO.setSearchSystemKindId("spt");
	    List<CmmMenuVO> resultList = cmmMenuService.selectCmmMenuTopList(paramVO);
		model.addAttribute("resultList", resultList);
	    return "jsonView";
	}
	
	/**
	 * @Method Name        : selectCmmMenuLeftList
	 * @Method description : 메뉴Top목록을 조회한다.
	 * @param              : 
	 * @return             : String
	 * @throws             : Exception
	 */
	@RequestMapping(value="/cmm/selectCmmMenuLeftList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectCmmMenuLeftList(@ModelAttribute("CmmMenuVO") CmmMenuVO paramVO, ModelMap model)throws Exception{
	    paramVO.setSearchSystemKindId("spt");
	    List<CmmMenuVO> resultList = cmmMenuService.selectCmmMenuLeftList(paramVO);
	    model.addAttribute("resultList", resultList);
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectCmmMenuDtl
     * @Method description : 메뉴의 상세정보를 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/selectCmmMenuDtl.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectCmmMenuDtl(@ModelAttribute("CmmMenuVO") CmmMenuVO paramVO, ModelMap model)throws Exception{
		//상세조회
		CmmMenuVO resultDetail = cmmMenuService.selectCmmMenuDtl(paramVO);
        
        model.addAttribute("resultDetail", resultDetail);
		
	    return "jsonView";
	}
	

}
