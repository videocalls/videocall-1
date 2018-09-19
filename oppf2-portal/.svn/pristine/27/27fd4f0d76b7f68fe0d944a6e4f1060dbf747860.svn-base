package kr.co.koscom.oppf.cmm.web;

import kr.co.koscom.oppf.cmm.service.CmmNiceIpinCheckplusService;
import kr.co.koscom.oppf.cmm.service.CmmNiceIpinCheckplusVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CmmFuncController.java
* @Comment  : 공통 기능을 제공하는 Controller
* @author   : 신동진
* @since    : 2016.05.04
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.04  신동진        최초생성
*
*/
@Controller
public class CmmNiceIpinCheckplusController {
	
	@Resource(name = "CmmNiceIpinCheckplusService")
	private CmmNiceIpinCheckplusService cmmNiceIpinCheckplusService;
	
	/* 동기식.do 요청 START */
	
	/**
     * @Method Name        : ipinProcess
     * @Method description : ipin_process.jsp를 호출한다.(아이핀인증 프로세스 처리를 합니다.)
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/ipin/ipinProcess.do",method = {RequestMethod.POST, RequestMethod.GET})    
	private String ipinProcess()throws Exception{
        return "cmm/ipin/ipin_process";
    }
    
    /**
     * @Method Name        : ipinResult
     * @Method description : ipin_result.jsp를 호출한다.(아이핀인증 처리를 합니다.)
     * @param              : MbrRegVO
     * @param              : HttpServletRequest
     * @param              : ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/ipin/ipinResult.do",method = {RequestMethod.POST, RequestMethod.GET})    
    private String ipinResult(
        @ModelAttribute("mbrRegVO") MbrRegVO paramVO
       ,HttpServletRequest request
       ,ModelMap model
    )throws Exception{
        CmmNiceIpinCheckplusVO resultVO = new CmmNiceIpinCheckplusVO();
        
        //회원가입 본인인증 부모화면으로 보낼 파라미터값을 만들어서 담아둔다.
        resultVO = cmmNiceIpinCheckplusService.ipinResult(paramVO, request);

        model.addAttribute("resultVO", resultVO); //CI값, 사용자이름
        
        return "cmm/ipin/ipin_result_send";        
    }
    
    /**
     * @Method Name        : checkplusSuccess
     * @Method description : 휴대폰 인증 성공 프로세스 처리를 합니다.
     * @param              : MbrRegVO
     * @param              : HttpServletRequest
     * @param              : ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/checkPlusSafe/checkplusSuccess.do",method = {RequestMethod.POST, RequestMethod.GET})    
    private String checkplusSuccess(
        @ModelAttribute("mbrRegVO") MbrRegVO paramVO
       ,HttpServletRequest request
       ,ModelMap model
    )throws Exception{
        CmmNiceIpinCheckplusVO resultVO = new CmmNiceIpinCheckplusVO();
        
        //회원가입 본인인증 부모화면으로 보낼 파라미터값을 만들어서 담아둔다.
        resultVO = cmmNiceIpinCheckplusService.checkplusSuccess(paramVO, request);

        model.addAttribute("resultVO", resultVO); //CI값, 사용자이름
        
        return "cmm/checkPlusSafe/checkplus_result";
    }
    
    /**
     * @Method Name        : checkplusFail
     * @Method description : 휴대폰 인증 실패 프로세스 처리를 합니다.
     * @param              : MbrRegVO
     * @param              : HttpServletRequest
     * @param              : ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/checkPlusSafe/checkplusFail.do",method = {RequestMethod.POST, RequestMethod.GET})    
    private String checkplusFail(
        @ModelAttribute("mbrRegVO") MbrRegVO paramVO
       ,HttpServletRequest request
       ,ModelMap model
    )throws Exception{
        CmmNiceIpinCheckplusVO resultVO = new CmmNiceIpinCheckplusVO();
        
        //회원가입 본인인증 부모화면으로 보낼 파라미터값을 만들어서 담아둔다.
        resultVO = cmmNiceIpinCheckplusService.checkplusFail(paramVO, request);

        model.addAttribute("resultVO", resultVO); //CI값, 사용자이름
        
        return "cmm/checkPlusSafe/checkplus_result";
    }
    
    /* 동기식.do 요청 END */

}
