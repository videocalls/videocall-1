package kr.co.koscom.oppf.spt.cmm.web;

import kr.co.koscom.oppf.cmm.noti.service.NotiService;
import kr.co.koscom.oppf.cmm.noti.service.NotiVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.util.OppfSessionUtil;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegTermsContentVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


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
	
	@Resource(name = "SvcApplService")
    private SvcApplService svcApplService;
	
	@Resource(name = "NotiService")
    private NotiService notiService;
	
	@Resource(name = "MbrRegService")
    private MbrRegService mbrRegService;

    @Resource(name = "AppService")
    private AppService appService;

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

        //메인에 팝업여부 Y인것도 표시 수정 2017.04.17 SHL
        //notiVO.setNoticePopYn("N");
        
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

		        //금투사기업약관 동의 정보 조회
		        List<MbrRegTermsContentVO> companyTermsList = mbrRegService.selectMainCompanyTermsList(mbrRegVO);
		        model.addAttribute("companyTermsList", companyTermsList);


                /*AppVO appVO = new AppVO();
                appVO.setCustomerRegNo(customerRegNo);
                TermsVO checkedCommonTerms = appService.checkedReSyncCommonTerms(appVO);
                model.addAttribute("checkedCommonTerms", checkedCommonTerms);*/
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
