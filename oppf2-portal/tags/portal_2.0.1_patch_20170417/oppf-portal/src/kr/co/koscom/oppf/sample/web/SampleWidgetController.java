package kr.co.koscom.oppf.sample.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.sample.service.SampleService;
import kr.co.koscom.oppf.sample.service.SampleVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SampleWidgetController.java
* @Comment  : [jqWidgets]정보관리를 위한 Controller 클래스
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
public class SampleWidgetController {
	
    @Resource(name = "SampleService")
    private SampleService sampleService;
	
    private static final Logger log = Logger.getLogger(SampleWidgetController.class);
	
    /**
     * @Method Name        : sampleWidget
     * @Method description : [샘플jq위젯]정보 관리로 이동한다.
     * @param              : SampleVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/sample/sampleWidget.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String sampleWidgetIndex(@ModelAttribute("sampleVO") SampleVO sampleVO, ModelMap model)throws Exception{
	    model.addAttribute("sampleVO", sampleVO);
	    return "sample/sampleWidget";
	}
	
	   /**
     * @Method Name        : sampleUpload
     * @Method description : [샘플위젯:업로드]로 이동한다.
     * @param              : SampleVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping("/sample/sampleWidgetUpload.do")
    private String sampleWidgetUpload(@ModelAttribute("sampleVO") SampleVO sampleVO, ModelMap model)throws Exception{
        return "sample/sampleWidgetUpload";
    }

}
