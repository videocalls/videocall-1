package kr.co.koscom.oppf.cpt.cmm.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.cmm.noti.service.NotiService;
import kr.co.koscom.oppf.cmm.noti.service.NotiVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfSessionUtil;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SvcApplService;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SvcApplVO;


/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CptMainController.java
* @Comment  : 기업사용자 Main Controller
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
public class CptMainController {
	
	private static final Logger log = Logger.getLogger(CptMainController.class);
	
	@Resource(name = "SvcApplService")
    private SvcApplService svcApplService;
	
	@Resource(name = "NotiService")
    private NotiService notiService;
	
    /**
     * @Method Name        : mainView
     * @Method description : 기업사용자 main 페이지 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/cmm/mainView.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String loginView(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        model.addAttribute("loginVO", loginVO);
        
        //핀테크 서비스 (top 5)
        SvcApplVO svcApplVO = new SvcApplVO();
        Map<String, Object> svcApplMap = svcApplService.selectMainSvcAppList(svcApplVO);
        model.addAttribute("resultAppList", svcApplMap.get("resultList"));
        
        //공지사항
        NotiVO notiVO = new NotiVO();
        notiVO.setPageIndex(1);
        notiVO.setPageRowsize(4);
        
        notiVO.setNoticePopYn("N");
        
        notiVO.setListOrder("a.notice_kind asc, a.create_date desc");
        
      //2016.09.09 이준형과장 요청 - 미 로그인 시에도 노출 
        //if(loginVO != null){
        	String systemKind = OppfSessionUtil.getSystemKind(request);		//system kind를 가져온다.
        	notiVO.setSearchKind(systemKind);
        //}
        List<?> resultNotiList = notiService.selectNotiList(notiVO);        
        model.addAttribute("resultBordNotiList", resultNotiList);
        
        //메뉴관련 session 삭제
		//String systemKind = OppfSessionUtil.getSystemKind(request);        //system kind를 가져온다.
		request.getSession().setAttribute(systemKind+"ParamMenuTopVO", null);
		request.getSession().setAttribute(systemKind+"MenuTopList", null);
		request.getSession().setAttribute(systemKind+"CurrentMenuId", null);
		request.getSession().setAttribute(systemKind+"CurrentParentMenuId", null);
		request.getSession().setAttribute(systemKind+"ParentMenuId", null);
		
		//유효하지않은 URL 체크 invalidUrlFlag(Y:유효하지않은URL)
		String invalidUrlFlag = (String) model.get("invalidUrlFlag");
		model.addAttribute("invalidUrlFlag",invalidUrlFlag);
                
	    return "cpt/cmm/cptMain";
	}
	
}
