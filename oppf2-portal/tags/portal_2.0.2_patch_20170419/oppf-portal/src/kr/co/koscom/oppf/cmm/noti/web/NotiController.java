package kr.co.koscom.oppf.cmm.noti.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.cmm.noti.service.NotiFileManageVO;
import kr.co.koscom.oppf.cmm.noti.service.NotiManageService;
import kr.co.koscom.oppf.cmm.noti.service.NotiManageVO;
import kr.co.koscom.oppf.cmm.noti.service.NotiService;
import kr.co.koscom.oppf.cmm.noti.service.NotiVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.cmm.util.OppfXssUtil;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : NotiController.java
* @Comment  : [공지사항]정보관리를 위한 Controller 클래스
* @author   : 포털 이환덕
* @since    : 2016.05.02
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.02  이환덕        최초생성
* 2016.05.13  신동진        admin 관리 추가
*
*/
@Controller
public class NotiController {
    
    @Resource(name = "NotiService")
    private NotiService notiService;
    
    @Resource(name = "NotiManageService")
    private NotiManageService notiManageService;
    
    private static final Logger log = Logger.getLogger(NotiController.class);
    
    /* 동기식.do 요청 START */
    
    /**
     * @Method Name        : notiList
     * @Method description : [공지사항목록]페이지로 이동한다.
     * @param              : NotiVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/noti/notiList.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String notiList(
        @ModelAttribute("notiVO") NotiVO paramVO
       ,HttpServletRequest request
       ,ModelMap model
    )throws Exception{
    	//moblie여부 셋팅
    	boolean isMobile = OppfStringUtil.isMobile(request);
        model.addAttribute("isMobile", isMobile);
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");        
        model.addAttribute("loginVO", loginVO);
        model.addAttribute("paramVO", paramVO);
        
        return "cmm/noti/notiList";
    }
    
    /**
     * @Method Name        : notiDtl
     * @Method description : [공지사항상세]페이지 이동 및 조회한다.
     * @param              : NotiVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping("/cmm/noti/notiDtl.do")
    private String notiDtl(
        @ModelAttribute("notiVO") NotiVO paramVO
       ,HttpServletRequest request
       ,ModelMap model
    )throws Exception{
    	//moblie여부 셋팅
    	boolean isMobile = OppfStringUtil.isMobile(request);
        model.addAttribute("isMobile", isMobile);
        
        //조회수 증가
        notiService.updateNotiReadCount(paramVO);
        
        //상세조회
        NotiVO resultDetail = notiService.selectNotiDetail(paramVO);
        
        //파일 ID가 있을 경우 파일 정보를 조회한다.
        List<NotiFileManageVO> fileList = null;
        if(resultDetail != null){
	        if(!OppfStringUtil.isEmpty(resultDetail.getFileId())){
	        	NotiManageVO fileVO = new NotiManageVO();
	        	fileVO.setFileId(resultDetail.getFileId());
	        	
	        	fileList = notiManageService.selectNotiManageDetailFileList(fileVO);   	
	        }
        }
        
        //이전글다음글
        NotiVO befAftInfo = notiService.selectNotiBeforeAfterInfo(paramVO);
        
        //로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        
        model.addAttribute("loginVO", loginVO);
        model.addAttribute("paramVO", paramVO);
        model.addAttribute("resultDetail", resultDetail);
        model.addAttribute("fileList", fileList);
        model.addAttribute("befAftInfo", befAftInfo);
        return "cmm/noti/notiDtl";
    }
    
    /**
     * @Method Name        : notiReg
     * @Method description : [공지사항등록]페이지로 이동한다.
     * @param              : NotiVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/noti/notiReg.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String notiReg(
        @ModelAttribute("notiVO") NotiVO paramVO
       ,ModelMap model
    )throws Exception{
        
        model.addAttribute("pageMode", "INSERT");
        model.addAttribute("paramVO", paramVO);
        return "cmm/noti/notiSave";
    }
    
    /**
     * @Method Name        : notiMod
     * @Method description : [공지사항수정]페이지로 이동한다.
     * @param              : NotiVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/noti/notiMod.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String notiMod(
        @ModelAttribute("notiVO") NotiVO paramVO
       ,ModelMap model
    )throws Exception{
        
        //상세조회
        NotiVO resultDetail = notiService.selectNotiDetail(paramVO);
        model.addAttribute("resultDetail", resultDetail);
        model.addAttribute("paramVO", paramVO);
        model.addAttribute("pageMode", "UPDATE");
        return "cmm/noti/notiSave";
    }
    
    /**
     * @Method Name        : notiDtlPop
     * @Method description : [공지사항]팝업창으로 이동한다.
     * @param              : NotiVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/noti/notiDtlPop.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String notiDtlPop(
        @ModelAttribute("notiVO") NotiVO paramVO
       ,ModelMap model
    )throws Exception{
        //상세조회
        NotiVO resultDetail = notiService.selectNotiDetail(paramVO);  
        //파일 ID가 있을 경우 파일 정보를 조회한다.
        List<NotiFileManageVO> fileList = null;
        if(resultDetail != null){
	        if(!OppfStringUtil.isEmpty(resultDetail.getFileId())){
	        	NotiManageVO fileVO = new NotiManageVO();
	        	fileVO.setFileId(resultDetail.getFileId());
	        	
	        	fileList = notiManageService.selectNotiManageDetailFileList(fileVO);   	
	        }
        }
        
        model.addAttribute("paramVO", paramVO);
        model.addAttribute("resultDetail", resultDetail);
        model.addAttribute("fileList", fileList);
        
        return "cmm/noti/notiDtlPop";
    }
    
    /* 동기식.do 요청 END */
    
    
    /* 비동기식.ajax 요청 START */
    
    /**
     * @Method Name        : selectNotiList
     * @Method description : [공지사항목록:목록]정보를 조회한다.
     * @param              : NotiVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/cmm/noti/selectNotiList.ajax")
    private String selectNotiList(
        @ModelAttribute("notiVO") NotiVO paramVO
       ,ModelMap model
    )throws Exception{
        
        //XSS보안처리
        String searchKeyword = paramVO.getSearchKeyword();
        if(!OppfStringUtil.isEmpty(searchKeyword)){
            paramVO.setSearchKeyword(OppfXssUtil.stripXSS(searchKeyword));
        }
        String searchCondition = paramVO.getSearchCondition();
        if(!OppfStringUtil.isEmpty(searchCondition)){
            paramVO.setSearchCondition(OppfXssUtil.stripXSS(searchCondition));
        }
        
    	//notice
        int resultListTotalCount = notiService.selectNotiListTotalCount(paramVO);
        List<?> resultList = notiService.selectNotiList(paramVO);
    	
    	//첫페이지만 보임
    	if(paramVO.getPageIndex() <= 1){
    		paramVO.setNoticePopYn("N");
    		paramVO.setNoticeFixYn("Y");
    		
	    	//fix notice
	    	List<?> resultListFix = notiService.selectNotiListFix(paramVO);
	        model.addAttribute("resultListFix", resultListFix);
    	}
    	
        model.addAttribute("resultList", resultList);
        model.addAttribute("resultListTotalCount", resultListTotalCount);        
        model.addAttribute("paramVO", paramVO);
        return "jsonView";
    }
    
    /**
     * @Method Name        : selectNotiListFix
     * @Method description : [공지사항목록:Fix]정보를 조회한다.
     * @param              : NotiVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/cmm/noti/selectNotiListFix.ajax")
    private String selectNotiListFix(
        @ModelAttribute("notiVO") NotiVO paramVO
       ,ModelMap model
    )throws Exception{
        
        int resultListTotalCount = notiService.selectNotiListTotalCount(paramVO);
        List<?> resultListFix = notiService.selectNotiListFix(paramVO);
        model.addAttribute("resultListFix", resultListFix);
        model.addAttribute("resultListTotalCount", resultListTotalCount);        
        model.addAttribute("paramVO", paramVO);
        return "jsonView";
    }
    
   
    /**
     * @Method Name        : selectNotiListPop
     * @Method description : [공지사항목록:팝업]정보를 조회한다.
     * @param              : NotiVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/cmm/noti/selectNotiListPop.ajax")
    private String selectNotiListPop(
        @ModelAttribute("notiVO") NotiVO paramVO
       ,ModelMap model
    )throws Exception{
        
        List<?> resultList = notiService.selectNotiListPop(paramVO);
        model.addAttribute("resultList", resultList);        
        model.addAttribute("paramVO", paramVO);
        return "jsonView";
    }
    
    /* 비동기식.ajax 요청 END */
}
