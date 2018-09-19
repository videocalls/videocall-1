package kr.co.koscom.oppf.cmm.faq.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.cmm.faq.service.FaqManageService;
import kr.co.koscom.oppf.cmm.faq.service.FaqManageVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.tsa.service.CmmTsaService;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceTermsFileProfileVO;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : FaqManageController.java
* @Comment  : 관리자의 FAQ 관리를 위한 Controller 클래스
* @author   : 신동진
* @since    : 2016.05.16
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.16  신동진        최초생성
*
*/
@Controller
public class FaqManageController {
	private static final Logger log = Logger.getLogger(FaqManageController.class);
    
    @Resource(name = "FaqManageService")
    private FaqManageService faqManageService;
    
    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
    @Resource(name = "CmmTsaService")
    private CmmTsaService cmmTsaService;
            
    /**
     * @Method Name        : FaqManageList
     * @Method description : FAQ 목록 페이지로 이동한다.
     * @param              : FaqManageVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/faq/faqManageList.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String faqManageList(@ModelAttribute("FaqManageVO") FaqManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String modelView = "apt/cmm/faq/faqManageList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
    	
    	//셋팅 공통코드 : 그룹노출구분
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G003");//그룹노출구분
        List<CmmFuncVO> faqKindList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("faqKindList", faqKindList);
        
        model.addAttribute("paramVO", paramVO);
        
        return modelView;
    }
    
    /**
     * @Method Name        : selectFaqManageList
     * @Method description : FAQ 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/faq/selectFaqManageList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectFaqManageList(@ModelAttribute("FaqManageVO") FaqManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = null;
		
		paramVO.setListOrder("a.faq_order asc, a.create_date desc, a.faq_title asc");
		
		map = faqManageService.selectFaqManageList(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectFaqManageListExcel
     * @Method description : FAQ 목록 Excel을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/faq/selectFaqManageListExcel.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectFaqManageListExcel(@ModelAttribute("FaqManageVO") FaqManageVO paramVO, ModelMap model)throws Exception{
		Map<String, Object> map = null;
		
		paramVO.setListOrder("a.faq_order asc, a.create_date desc, a.faq_title asc");
		paramVO.setExcelYn("Y"); //excel 여부 셋팅
		
		map = faqManageService.selectFaqManageListExcel(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : FaqManageSave
     * @Method description : FAQ 등록/수정 페이지 이동
     * @param              : FaqManageVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/faq/faqManageSave.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String faqManageSave(@ModelAttribute("FaqManageVO") FaqManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String modelView = "apt/cmm/faq/faqManageSave";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
    	//셋팅 공통코드 : 그룹노출구분
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G003");//그룹노출구분
        List<CmmFuncVO> faqKindList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("faqKindList", faqKindList);
        
        //상세조회
        FaqManageVO resultDetail = faqManageService.selectFaqManageDetail(paramVO);
        
        model.addAttribute("resultDetail", resultDetail);
        model.addAttribute("paramVO", paramVO);
        
        
        /**
         * 테스트 코드 s
         */
        
//        SptCustomerServiceTermsFileProfileVO sptCustomerServiceTermsFileProfileVO = new SptCustomerServiceTermsFileProfileVO();
//        sptCustomerServiceTermsFileProfileVO.setSearchCustomerRegNo("C20160725001");
//        sptCustomerServiceTermsFileProfileVO.setSearchTermsFileRegNo("20170322002");
//        sptCustomerServiceTermsFileProfileVO.setSearchTermsFileType("G027002");
//        List<SptCustomerServiceTermsFileProfileVO> datas = cmmTsaService.selectSptCustomerServiceTermsFileProfileList(sptCustomerServiceTermsFileProfileVO);
//        
//        log.debug( datas.get(0).getTermsFileData().toString());
//        
//
//        int ii = 0;
//        for(SptCustomerServiceTermsFileProfileVO data : datas ) {
//            byte[] buffers = data.getTermsFileData();
//            log.debug("buffers.length = " + buffers.length);
//        
//            String fileName = "C://myfile"+(ii++)+"2.txt";
//    
//            BufferedOutputStream bs = null;
//    
//            try {
//    
//                FileOutputStream fs = new FileOutputStream(new File(fileName));
//                bs = new BufferedOutputStream(fs);
//                bs.write(buffers);
//                bs.close();
//                bs = null;
//    
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//    
//            if (bs != null) try { bs.close(); } catch (Exception e) {}        
//        }
        
        
        /*
         * 테스트 코드 e
         */
        
        return modelView;
    }
    
    /**
     * @Method Name        : insertFaq
     * @Method description : FAQ 등록
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/faq/insertFaq.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String insertFaq(@ModelAttribute("FaqManageVO") FaqManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		int result = faqManageService.insertFaq(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : updateFaq
     * @Method description : FAQ 수정
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/faq/updateFaq.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String updateFaq(@ModelAttribute("FaqManageVO") FaqManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		int result = faqManageService.updateFaq(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : deleteFaq
     * @Method description : FAQ 삭제
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/faq/deleteFaq.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String deleteFaq(@ModelAttribute("FaqManageVO") FaqManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		int result = faqManageService.deleteFaq(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
    
}
