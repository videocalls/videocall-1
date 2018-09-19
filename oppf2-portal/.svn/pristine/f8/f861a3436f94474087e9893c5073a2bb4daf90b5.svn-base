package kr.co.koscom.oppf.cmm.qna.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.cmm.qna.service.QnaVO;
import kr.co.koscom.oppf.cmm.qna.service.QnaService;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : QnaController.java
* @Comment  : [Q&A]정보관리를 위한 Controller 클래스
* @author   : 포털 이환덕
* @since    : 2016.05.02
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.02  이환덕        최초생성
*
*/
@Controller
public class QnaController {
	
    @Resource(name = "QnaService")
    private QnaService qnaService;
	
    private static final Logger log = Logger.getLogger(QnaController.class);
	
    /* 동기식.do 요청 START */
    
    /**
     * @Method Name        : qna
     * @Method description : [Q&A목록]페이지로 이동한다.
     * @param              : QnaVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/qna/qnaList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String qnaList(@ModelAttribute("qnaVO") QnaVO paramVO, ModelMap model)throws Exception{
	    model.addAttribute("paramVO", paramVO);
	    return "cmm/qna/qnaList";
	}
	
    /**
     * @Method Name        : qnaDtl
     * @Method description : [Q&A상세]페이지 이동 및 조회한다.
     * @param              : QnaVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping("/qna/qnaDtl.do")
    private String qnaDtl(@ModelAttribute("qnaVO") QnaVO paramVO, ModelMap model)throws Exception{
        //조회수 증가
        qnaService.updateQnaReadCount(paramVO);
        
        //상세조회
        QnaVO resultDetail = qnaService.selectQnaDetail(paramVO);
        
        //이전글다음글
        QnaVO befAftInfo = qnaService.selectQnaBeforeAfterInfo(paramVO);
        
        model.addAttribute("paramVO", paramVO);
        model.addAttribute("resultDetail", resultDetail);
        model.addAttribute("befAftInfo", befAftInfo);
        return "cmm/qna/qnaDtl";
    }
	
    /**
     * @Method Name        : qnaReg
     * @Method description : [공지사항등록]페이지로 이동한다.
     * @param              : QnaVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/qna/qnaReg.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String qnaReg(@ModelAttribute("qnaVO") QnaVO paramVO, ModelMap model)throws Exception{
        model.addAttribute("paramVO", paramVO);
        return "cmm/qna/qnaReg";
    }
    
    /**
     * @Method Name        : qnaMod
     * @Method description : [공지사항수정]페이지로 이동한다.
     * @param              : QnaVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/qna/qnaMod.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String qnaMod(@ModelAttribute("qnaVO") QnaVO paramVO, ModelMap model)throws Exception{
        model.addAttribute("paramVO", paramVO);
        return "cmm/qna/qnaMod";
    }
    
    /* 동기식.do 요청 END */
	
    
    /* 비동기식.ajax 요청 START */
    
    /**
     * @Method Name        : selectQnaList
     * @Method description : [Q&A목록:목록]정보를 조회한다.
     * @param              : QnaVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
	@RequestMapping("/qna/selectQnaList.ajax")
	private String selectQnaList(@ModelAttribute("qnaVO") QnaVO paramVO, ModelMap model)throws Exception{
	    int resultListTotalCount = qnaService.selectQnaListTotalCount(paramVO);
	    List<?> resultList = qnaService.selectQnaList(paramVO);
        model.addAttribute("resultList", resultList);
        model.addAttribute("resultListTotalCount", resultListTotalCount);
        model.addAttribute("paramVO", paramVO);
        return "jsonView";
	}
	
    /**
     * @Method Name        : insertQna
     * @Method description : [Q&A목록:등록]을 한다.
     * @param              : QnaVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
	@RequestMapping("/qna/insertQna.ajax")
	private String insertQna(@ModelAttribute("qnaVO") QnaVO paramVO, ModelMap model)throws Exception{
	    int rs = qnaService.insertQna(paramVO);
	    model.addAttribute("rs", rs);
	    return "jsonView";
	}
	
    /**
     * @Method Name        : updateQna
     * @Method description : [Q&A목록:수정]을 한다.
     * @param              : QnaVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
	@RequestMapping("/qna/updateQna.ajax")
	private String updateQna(@ModelAttribute("qnaVO") QnaVO paramVO, ModelMap model)throws Exception{
	    int rs = qnaService.updateQna(paramVO);
	    model.addAttribute("rs", rs);
	    return "jsonView";
	}
	
    /**
     * @Method Name        : deleteQna
     * @Method description : [Q&A목록:삭제]를 한다.
     * @param              : QnaVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
	@RequestMapping("/qna/deleteQna.ajax")
	private String deleteQna(@ModelAttribute("qnaVO") QnaVO paramVO, ModelMap model)throws Exception{
	    int rs = qnaService.deleteQna(paramVO);
	    model.addAttribute("rs", rs);
	    return "jsonView";
	}
	
	/* 비동기식.ajax 요청 END */

}
