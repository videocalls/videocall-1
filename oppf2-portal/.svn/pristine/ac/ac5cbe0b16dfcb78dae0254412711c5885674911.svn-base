package kr.co.koscom.oppf.spt.cmm.web;

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
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegTermsContentVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SvcApplService;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SvcApplVO;


/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : SptMainController.java
* @Comment  : 일반사용자 Main Controller
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
public class SptMainController {
	
	private static final Logger log = Logger.getLogger(SptMainController.class);
	
	@Resource(name = "SvcApplService")
    private SvcApplService svcApplService;
	
	@Resource(name = "NotiService")
    private NotiService notiService;
	
	@Resource(name = "MbrRegService")
    private MbrRegService mbrRegService;

	
    /**
     * @Method Name        : mainView
     * @Method description : 일반사용자 main 페이지 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/spt/cmm/mainView.do",method = {RequestMethod.POST, RequestMethod.GET})
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
        
        //로그인 처리 되어있을 경우에만 셋팅
        if(loginVO != null){
        	String customerRegNo = loginVO.getCustomer_reg_no();
            if(!OppfStringUtil.isEmpty(customerRegNo)){
		        //동의서 동의 정보 조회
	        	MbrRegVO mbrRegVO = new MbrRegVO();
	        	mbrRegVO.setCustomerRegNo(customerRegNo);
		        List<MbrRegTermsContentVO> resultTermsList = mbrRegService.selectMainSptCustomerTermsList(mbrRegVO);
		        model.addAttribute("resultTermsList", resultTermsList);
            }
        }
        
        //moblie여부 셋팅
    	boolean isMobile = OppfStringUtil.isMobile(request);
        model.addAttribute("isMobile", isMobile);

        //메뉴관련 session 삭제
        //String systemKind = OppfSessionUtil.getSystemKind(request);        //system kind를 가져온다.
        request.getSession().setAttribute(systemKind+"ParamMenuTopVO", null);
        request.getSession().setAttribute(systemKind+"MenuTopList", null);
        request.getSession().setAttribute(systemKind+"CurrentMenuId", null);
        request.getSession().setAttribute(systemKind+"CurrentParentMenuId", null);
        request.getSession().setAttribute(systemKind+"ParentMenuId", null);
        
	    return "spt/cmm/sptMain";
	}
	
}
