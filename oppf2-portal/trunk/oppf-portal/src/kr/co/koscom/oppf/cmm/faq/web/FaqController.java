package kr.co.koscom.oppf.cmm.faq.web;

import kr.co.koscom.oppf.cmm.faq.service.FaqService;
import kr.co.koscom.oppf.cmm.faq.service.FaqVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.cmm.util.OppfXssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : FaqController.java
* @Comment  : [FAQ]정보관리를 위한 Controller 클래스
* @author   : 포털 유제량
* @since    : 2016.05.02
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.02  유제량        최초생성
*
*/
@Slf4j
@Controller
public class FaqController {
	
    @Resource(name = "FaqService")
    private FaqService faqService;
	
    /* 동기식.do 요청 START */
    
   /**
     * @Method Name        : faqList
     * @Method description : [FAQ목록]페이지로 이동한다.
     * @param              : FaqVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/faq/faqList.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String faqList(
        @ModelAttribute("faqVO") FaqVO paramVO 
       ,HttpServletRequest request
       ,ModelMap model
    )throws Exception{
    	//moblie여부 셋팅
    	boolean isMobile = OppfStringUtil.isMobile(request);
        model.addAttribute("isMobile", isMobile);
    	
        log.info("------------- faqList.do START ------------------------");
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        model.addAttribute("loginVO", loginVO);
        model.addAttribute("paramVO", paramVO);
        log.info("------------- faqList.do END --------------------------");
        return "cmm/faq/faqList";
    }
    
    /**
     * @Method Name        : faqDtl
     * @Method description : [FAQ상세]페이지 이동 및 조회한다.
     * @param              : FaqVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping("/cmm/faq/faqDtl.do")
    private String faqDtl(@ModelAttribute("faqVO") FaqVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//moblie여부 셋팅
    	boolean isMobile = OppfStringUtil.isMobile(request);
        model.addAttribute("isMobile", isMobile);
    	
        log.info("------------- faqDtl.do START ------------------------");
        //조회수 증가
        faqService.updateFaqReadCount(paramVO);
        
        //상세조회
        FaqVO resultDetail = faqService.selectFaqDetail(paramVO);
        
        //이전글다음글
        FaqVO befAftInfo = faqService.selectFaqBeforeAfterInfo(paramVO);
        
        model.addAttribute("paramVO", paramVO);
        model.addAttribute("resultDetail", resultDetail);
        model.addAttribute("befAftInfo", befAftInfo);
        log.info("------------- faqDtl.do END --------------------------");
        return "cmm/faq/faqDtl";
    }
    
    /**
     * @Method Name        : faqSave
     * @Method description : [FAQ등록]페이지로 이동한다.
     * @param              : FaqVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/faq/faqSave.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String faqSave(@ModelAttribute("faqVO") FaqVO paramVO, ModelMap model)throws Exception{
        log.info("------------- faqSave.do START ------------------------");
        model.addAttribute("pageMode", "INSERT");
        model.addAttribute("paramVO", paramVO);
        log.info("------------- faqSave.do END --------------------------");
        return "cmm/faq/faqSave";
    }
    
    /**
     * @Method Name        : faqMod
     * @Method description : [FAQ수정]페이지로 이동한다.
     * @param              : FaqVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/faq/faqMod.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String faqMod(@ModelAttribute("faqVO") FaqVO paramVO, ModelMap model)throws Exception{
        log.info("------------- faqMod.do START ------------------------");
        //상세조회
        FaqVO resultDetail = faqService.selectFaqDetail(paramVO);
        model.addAttribute("resultDetail", resultDetail);
        model.addAttribute("paramVO", paramVO);
        model.addAttribute("pageMode", "UPDATE");
        model.addAttribute("paramVO", paramVO);
        log.info("------------- faqMod.do END --------------------------");
        return "cmm/faq/faqSave";
    }
    
    /* 동기식.do 요청 END */
	
    
    /* 비동기식.ajax 요청 START */
    
    /**
     * @Method Name        : selectFaqList
     * @Method description : [FAQ목록:목록]정보를 조회한다.
     * @param              : FaqVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
	@RequestMapping("/cmm/faq/selectFaqList.ajax")
	private String selectFaqList(@ModelAttribute("faqVO") FaqVO paramVO, ModelMap model)throws Exception{
	    log.info("------------- selectFaqList.ajax START ------------------------");
	    

	    
        //XSS보안처리
        String searchKeyword = paramVO.getSearchKeyword();
        if(!OppfStringUtil.isEmpty(searchKeyword)){
            paramVO.setSearchKeyword(OppfXssUtil.stripXSSex(searchKeyword));
        }
        String searchCondition = paramVO.getSearchCondition();
        if(!OppfStringUtil.isEmpty(searchCondition)){
            paramVO.setSearchCondition(OppfXssUtil.stripXSSex(searchCondition));
        }
        
        // XSS 보안처리 추가 - hellolee
        String listOrder = paramVO.getListOrder();
        if(!OppfStringUtil.isEmpty(listOrder)){
           paramVO.setListOrder(OppfXssUtil.stripXSSex(listOrder));
        }        
        
	    int resultListTotalCount = faqService.selectFaqListTotalCount(paramVO);
	    List<?> resultList = faqService.selectFaqList(paramVO);
        model.addAttribute("resultList", resultList);
        model.addAttribute("resultListTotalCount", resultListTotalCount);
        model.addAttribute("paramVO", paramVO);
        log.info("------------- selectFaqList.ajax END --------------------------");
        return "jsonView";
	}
	
    /**
     * @Method Name        : insertFaq
     * @Method description : [FAQ목록:등록]을 한다.
     * @param              : FaqVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
	@RequestMapping("/cmm/faq/insertFaq.ajax")
	private String insertFaq(@ModelAttribute("faqVO") FaqVO paramVO, ModelMap model)throws Exception{
	    log.info("------------- insertFaq.ajax START ------------------------");
	    int rs = faqService.insertFaq(paramVO);
	    model.addAttribute("rs", rs);
	    log.info("------------- insertFaq.ajax END --------------------------");
	    return "jsonView";
	}
	
    /**
     * @Method Name        : updateFaq
     * @Method description : [FAQ목록:수정]을 한다.
     * @param              : FaqVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
	@RequestMapping("/cmm/faq/updateFaq.ajax")
	private String updateFaq(@ModelAttribute("faqVO") FaqVO paramVO, ModelMap model)throws Exception{
	    log.info("------------- updateFaq.ajax START ------------------------");
	    int rs = faqService.updateFaq(paramVO);
	    model.addAttribute("rs", rs);
	    log.info("------------- updateFaq.ajax END --------------------------");
	    return "jsonView";
	}
	
    /**
     * @Method Name        : deleteFaq
     * @Method description : [FAQ목록:삭제]를 한다.
     * @param              : FaqVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
	@RequestMapping("/cmm/faq/deleteFaq.ajax")
	private String deleteFaq(@ModelAttribute("faqVO") FaqVO paramVO, ModelMap model)throws Exception{
	    log.info("------------- deleteFaq.ajax START ------------------------");
	    int rs = faqService.deleteFaq(paramVO);
	    model.addAttribute("rs", rs);
	    log.info("------------- deleteFaq.ajax END --------------------------");
	    return "jsonView";
	}
	
	/* 비동기식.ajax 요청 END */

}
