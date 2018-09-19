package kr.co.koscom.oppf.cmm.aos2.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.cmm.menu.service.CmmMenuService;
import kr.co.koscom.oppf.cmm.menu.service.CmmMenuVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.signkorea.service.SKSignedDataInfo;
import kr.co.koscom.oppf.cmm.signkorea.service.SKVerifyExtInfo;
import kr.co.koscom.oppf.cmm.signkorea.service.SKVerifyVO;
import kr.co.koscom.oppf.cmm.signkorea.service.SKVerifyService;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmMenuController.java
* @Comment  : 공통 보안3종(PC방화벽,키보드보안,백신) 관리를 위한 Controller
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
public class AOS2Controller {
    
    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;

    
    /**
     * @Method Name        : certIndex
     * @Method description : [공통:보안3종(PC방화벽,키보드보안,백신)설치안내페이지]페이지로 이동한다.
     * @param              : request,response,model
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/aos2/certIndex.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String certIndex(
        HttpServletRequest  request
       ,HttpServletResponse response
       ,ModelMap model
    )throws Exception{
        
        return "cmm/aos2/certIndex";
    }

}
