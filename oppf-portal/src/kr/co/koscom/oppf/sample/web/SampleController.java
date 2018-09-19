package kr.co.koscom.oppf.sample.web;

import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.sample.service.SampleService;
import kr.co.koscom.oppf.sample.service.SampleVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SampleController.java
* @Comment  : [샘플]정보관리를 위한 Controller 클래스
* @author   : 포털 이환덕
* @since    : 2016.04.28
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2015.09.24  이환덕        최초생성
*
*/
@Controller
public class SampleController {
	
    @Resource(name = "SampleService")
    private SampleService sampleService;
    
    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
    /* DAO Sample START */
    
    /**
     * @Method Name        : sample
     * @Method description : [샘플]정보 관리로 이동한다.
     * @param              : SampleVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/sample/sample.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String sample(@ModelAttribute("sampleVO") SampleVO sampleVO, ModelMap model)throws Exception{
	    model.addAttribute("sampleVO", sampleVO);
	    return "sample/sample";
	}

    /**
     * @Method Name        : sampleModalPopup
     * @Method description : [샘플모달팝업]정보 관리로 이동한다.
     * @param              : SampleVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/sample/sampleModalPopup.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String sampleModalPopup(@ModelAttribute("sampleVO") SampleVO sampleVO, ModelMap model)throws Exception{
	    model.addAttribute("sampleVO", sampleVO);
	    return "sample/sampleModalPopup";
	}
	
	
    /**
     * @Method Name        : retrieveSampleList
     * @Method description : [샘플목록]정보 관리로 이동한다.
     * @param              : SampleVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/sample/retrieveSampleList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String retrieveSampleList(@ModelAttribute("sampleVO") SampleVO sampleVO, ModelMap model)throws Exception{
	    model.addAttribute("sampleVO", sampleVO);
	    return "sample/sampleList";
	}
	
    /**
     * @Method Name        : certHpSample
     * @Method description : [샘플]휴대폰인증 샘플페이지로 이동한다.
     * @param              : HttpServletRequest req, HttpServletResponse res, ModelMap model
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/sample/certHpSample.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String certHpSample(HttpServletRequest req, HttpServletResponse res, ModelMap model)throws Exception{
        String customerNameKor = (String) req.getParameter("customerNameKor");
        //셋팅 공통코드:휴대폰번호
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G011");//휴대폰
        List<CmmFuncVO> hpList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("hpList", hpList);
        return "sample/certHpSample";
    }
    
    /**
     * @Method Name        : certIpinSample
     * @Method description : [샘플]아이핀인증 샘플페이지로 이동한다.
     * @param              : HttpServletRequest req, HttpServletResponse res, ModelMap model
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/sample/certIpinSample.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String certIpinSample(HttpServletRequest req, HttpServletResponse res, ModelMap model)throws Exception{
        return "sample/certIpinSample";
    }
	
    /**
     * @Method Name        : retrieveSampleList
     * @Method description : [샘플목록:목록]정보를 조회한다.
     * @param              : SampleVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
	@RequestMapping("/sample/selectSampleList.ajax")
	private String selectSampleList(@ModelAttribute("sampleVO") SampleVO sampleVO, ModelMap model)throws Exception{
	    int resultListTotalCount = sampleService.selectSampleListTotalCount(sampleVO);
	    List<?> resultList = sampleService.selectSampleList(sampleVO);
        model.addAttribute("resultList", resultList);
        model.addAttribute("resultListTotalCount", resultListTotalCount);
        model.addAttribute("sampleVO", sampleVO);
        return "jsonView";
	}
	
    /**
     * @Method Name        : retrieveSampleDetail
     * @Method description : [샘플목록:상세]로 이동 및 조회한다.
     * @param              : SampleVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping("/sample/retrieveSampleDetail.do")
	private String retrieveSampleDetail(@ModelAttribute("sampleVO") SampleVO sampleVO, ModelMap model)throws Exception{
	    //조회수 증가
	    sampleService.updateSampleReadCount(sampleVO);
	    
	    //상세조회
	    SampleVO resultDetail = sampleService.selectSampleDetail(sampleVO);
	    
	    //이전글다음글
	    SampleVO befAftInfo = sampleService.selectSampleBeforeAfterInfo(sampleVO);
	    
	    model.addAttribute("sampleVO", sampleVO);
	    model.addAttribute("resultDetail", resultDetail);
	    model.addAttribute("befAftInfo", befAftInfo);
	    return "sample/sampleDetail";
	}
	
	/**
	 * @Method Name        : sampleUpload
	 * @Method description : [샘플:업로드]로 이동한다.
	 * @param              : SampleVO,ModelMap
	 * @return             : String
	 * @throws             : Exception
	 */
	@RequestMapping("/sample/sampleUpload.do")
	private String sampleUpload(@ModelAttribute("sampleVO") SampleVO sampleVO, ModelMap model)throws Exception{
	    return "sample/sampleUpload";
	}
	
    /**
     * @Method Name        : insertSample
     * @Method description : [샘플목록:등록]을 한다.
     * @param              : SampleVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
	@RequestMapping("/sample/insertSample.ajax")
	private String insertSample(@ModelAttribute("sampleVO") SampleVO sampleVO, ModelMap model)throws Exception{
	    int rs = sampleService.insertSample(sampleVO);
	    model.addAttribute("rs", rs);
	    return "jsonView";
	}
	
    /**
     * @Method Name        : updateSample
     * @Method description : [샘플목록:수정]을 한다.
     * @param              : SampleVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
	@RequestMapping("/sample/updateSample.ajax")
	private String updateSample(@ModelAttribute("sampleVO") SampleVO sampleVO, ModelMap model)throws Exception{
	    int rs = sampleService.updateSample(sampleVO);
	    model.addAttribute("rs", rs);
	    return "jsonView";
	}
	
    /**
     * @Method Name        : deleteSample
     * @Method description : [샘플목록:삭제]를 한다.
     * @param              : SampleVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
	@RequestMapping("/sample/deleteSample.ajax")
	private String deleteSample(@ModelAttribute("sampleVO") SampleVO sampleVO, ModelMap model)throws Exception{
	    int rs = sampleService.deleteSample(sampleVO);
	    model.addAttribute("rs", rs);
	    return "jsonView";
	}
	/* DAO Sample END */

}
