package kr.co.koscom.oppf.cmm.web;

import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;


/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CmmFuncController.java
* @Comment  : 공통 기능을 제공하는 Controller
* @author   : 신동진
* @since    : 2016.05.04
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.04  신동진        최초생성
*
*/
@Controller
public class CmmFuncController {
	
	@Resource(name = "CmmFuncService")
	private CmmFuncService cmmFuncService;
	
	/**
     * @Method Name        : selectCmmnFuncCode
     * @Method description : 공통코드를 조회한다.
     * @param              : searchVO
     * @param              : cmmnFuncVO
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/cmm/selectCmmnFuncCode.do")
    public String selectCmmnFuncCode(@ModelAttribute("searchVO") CmmFuncVO cmmFuncVO, ModelMap model) throws Exception 
    {
		
		model.addAttribute("cmmnCodeList", cmmFuncService.selectCmmnFuncCode(cmmFuncVO));
		
		return "jsonView";
		
    }

}
