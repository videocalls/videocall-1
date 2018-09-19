package kr.co.koscom.oppf.apt.sptUsr.web;

import kr.co.koscom.oppf.apt.sptUsr.service.AptUserEmailManageService;
import kr.co.koscom.oppf.apt.sptUsr.service.AptUserEmailManageVO;
import kr.co.koscom.oppf.cmm.service.*;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : UserEmailManageController.java
* @Comment  : admin 포털 메일 발송 이력 조회 Controller
* @author   : 유제량
* @since    : 2016.06.10
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.10  유제량        최초생성
*
*/
@Slf4j
@Controller
public class UserEmailManageController {
	
	@Resource(name = "AptUserEmailManageService")
    private AptUserEmailManageService aptUserEmailManageService;
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "CmmEmailSendService")
    private CmmEmailSendService cmmEmailSendService;
	
	/* 동기식.do 요청 START */
	
	/**
     * @Method Name        : aptUserEmailManageList
     * @Method description : admin 포털 메일발송 이력조회 화면 이동
     * @param              : AptUserManageVO, ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/sptUsr/userEmailManageList.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String aptUserEmailManageList(@ModelAttribute("aptUserEmailManageVO") AptUserEmailManageVO aptUserEmailManageVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "apt/sptUsr/aptUserEmailManageList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String adminProfileRegNo = loginVO.getAdmin_profile_reg_no();
        if(OppfStringUtil.isEmpty(adminProfileRegNo)){
            return modelView;
        }
        
        //셋팅 공통코드
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        //셋팅 공통코드 : 발신자 구분
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G017");
        List<CmmFuncVO> schKindList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("schKindList", schKindList);
        
        //셋팅 공통코드 : 발신자 구분
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G016");
        List<CmmFuncVO> emailSendTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("emailSendTypeList", emailSendTypeList);
        
        
        model.addAttribute("AptUserEmailManageVO", aptUserEmailManageVO);
        
        return modelView;
    }
    
    /**
     * @Method Name        : aptUserEmailManageDtl
     * @Method description : admin 포털 메일발송 이력조회 상세 화면 이동
     * @param              : AptUserManageVO, ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/sptUsr/userEmailManageDtl.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String aptUserEmailManageDtl(@ModelAttribute("aptUserEmailManageVO") AptUserEmailManageVO aptUserEmailManageVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "apt/sptUsr/aptUserEmailDtl";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String adminProfileRegNo = loginVO.getAdmin_profile_reg_no();
        if(OppfStringUtil.isEmpty(adminProfileRegNo)){
            return modelView;
        }
        
        //셋팅 공통코드:발신자종류
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G017");//발신자종류
        List<CmmFuncVO> senderKindList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("senderKindList", senderKindList);
        
        //셋팅 공통코드:이메일
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G013");//이메일
        List<CmmFuncVO> emailList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("emailList", emailList);
        
        //셋팅 공통코드:이메일유형
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G016");//이메일유형
        List<CmmFuncVO> emailSendTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("emailSendTypeList", emailSendTypeList);
        
        AptUserEmailManageVO resultDetail = aptUserEmailManageService.selectAptUserEmailManageDtl(aptUserEmailManageVO);
        resultDetail.setPageIndex(aptUserEmailManageVO.getPageIndex());
        model.addAttribute("resultDetail", resultDetail);
        
        return modelView;
    }
    
    /**
     * @Method Name        : selectUserEmailManageListExcel
     * @Method description : 메일 발송 이력 excel 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/sptUsr/selectUserEmailManageListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String selectUserEmailManageListExcel(@ModelAttribute("aptUserEmailManageVO") AptUserEmailManageVO aptUserEmailManageVO, ModelMap model)throws Exception{
        Map<String, Object> map = aptUserEmailManageService.selectAptUserEmailManageListExcel(aptUserEmailManageVO);
        model.addAttribute("resultList", map.get("resultList"));
        
        return "apt/sptUsr/aptUserEmailManageListExcel";
    }
    
    /**
     * @Method Name        : userEmailManageListPopup
     * @Method description : 관리자 메일발송 팝업
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/sptUsr/aptUserEmailManageListPopup.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String userEmailManageListPopup(@ModelAttribute("aptUserEmailManageVO") AptUserEmailManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        
        //modelView
        String modelView = "apt/sptUsr/aptUserEmailManageListPopup";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //셋팅 공통코드:이메일
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G013");//이메일
        List<CmmFuncVO> emailList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("emailList", emailList);
        
        model.addAttribute("AptUserEmailManageVO", paramVO);
        
        return modelView;
    }
    
    /* 동기식.do 요청 END */
    
    
    /* 비동기식.ajax 요청 START */
    
    /**
     * @Method Name        : selectAptUserEmailManageList
     * @Method description : [admin] 이메일 발송 이력을 조회한다.
     * @param              : AptUserEmailManageVO
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/apt/sptUsr/selectAptUserEmailManageList.ajax")
    private String selectAptUserEmailManageList(@ModelAttribute("aptUserEmailManageVO") AptUserEmailManageVO aptUserEmailManageVO, HttpServletRequest request, ModelMap model)throws Exception{
        log.info("------------- selectAptUserEmailManageList.ajax START -------------------");
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        String adminProfileRegNo = loginVO.getAdmin_profile_reg_no();
        if(OppfStringUtil.isEmpty(adminProfileRegNo)){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        
        aptUserEmailManageVO.setListOrder("a.rownum asc");
        
        //List<AptUserEmailManageVO> returnVO = aptUserEmailManageService.selectAptUserEmailManageList(aptUserEmailManageVO);
        Map<String, Object> map = aptUserEmailManageService.selectAptUserEmailManageList(aptUserEmailManageVO);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("resultListTotalCount", map.get("totCnt"));        
        log.info("------------- selectAptUserEmailManageList.ajax END ---------------------");
        return "jsonView";
    }
    
    /**
     * @Method Name        : updateAptUserEmailManage
     * @Method description : [admin] 이메일 발송 이력 정보 수정
     * @param              : AptUserEmailManageVO, HttpServletRequest, ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/sptUsr/updateAptUserEmailManage.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String updateAptUserEmailManage(@ModelAttribute("aptUserEmailManageVO") AptUserEmailManageVO aptUserEmailManageVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        String adminProfileRegNo = loginVO.getAdmin_profile_reg_no();
        if(OppfStringUtil.isEmpty(adminProfileRegNo)){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        
        aptUserEmailManageVO.setUpdateId(adminProfileRegNo); //업데이트ID는 어드민 관리자 ID로 저장됩니다.
                                
        //이메일 발송 이력 정보 수정
        int result = aptUserEmailManageService.updateAptUserEmailManage(aptUserEmailManageVO);              
        model.addAttribute("result", result);
        
        return "jsonView";
    }
    
    /**
     * @Method Name        : updateAptUserEmailManageBaseEmailSend
     * @Method description : [admin] 관리자 메일발송(기본템플릿으로)
     * @param              : AptUserEmailManageVO, HttpServletRequest, ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/sptUsr/updateAptUserEmailManageBaseEmailSend.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String updateAptUserEmailManageBaseEmailSend(@ModelAttribute("aptUserEmailManageVO") AptUserEmailManageVO aptUserEmailManageVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        String adminProfileRegNo = loginVO.getAdmin_profile_reg_no();
        if(OppfStringUtil.isEmpty(adminProfileRegNo)){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        
        aptUserEmailManageVO.setUpdateId(adminProfileRegNo); //업데이트ID는 어드민 관리자 ID로 저장됩니다.
                        
        //이메일 발송 이력 정보 수정
//        int result = aptUserEmailManageService.updateAptUserEmailManage(aptUserEmailManageVO);              
//        model.addAttribute("result", result);
        
        //관리자가 공통템플릿으로 메일발송을 할 때 등록되어 있는 사용자에게 이메일을 발송한 것인지 여부를 체크합니다.
        /*String ids = aptUserEmailManageService.selectMbrCustomerCheck(aptUserEmailManageVO);
        String checkPw = "0";
        if(null == ids || ids.length()==0){
            checkPw = "0";
        }else{
            checkPw = ids;
        }
        model.addAttribute("checkPw", checkPw);*/
        
        //이메일발송정보 셋팅
        CmmEmailSendVO cmmEmailSendVO = new CmmEmailSendVO();
        cmmEmailSendVO.setEmailSendType("G016001"); //이메일 유형 - com_email_temp_info 이메일 템플릿 조회        
        cmmEmailSendVO.setReceiverName(aptUserEmailManageVO.getReceiverName()); //받는 사람 이름(실제 이메일에 셋팅됩니다.)
        cmmEmailSendVO.setReceiverEmail(aptUserEmailManageVO.getReceiverEmail()); //받는 사람 이메일(실제 이메일에 셋팅되고, 이 주소로 이메일이 발송됩니다.)
        cmmEmailSendVO.setSenderKind("G017001"); //발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
        cmmEmailSendVO.setSenderId(adminProfileRegNo); //발신자 ID
        cmmEmailSendVO.setReceiverKind("G018001"); //수신자 종류 - G018001:일반사용자, G018002:기업사용자        
        //cmmEmailSendVO.setReceiverId(aptUserEmailManageVO.getCustomerRegNo()); //수신자 ID
        cmmEmailSendVO.setSendId(adminProfileRegNo); //최초 발신자 ID
        cmmEmailSendVO.setCreateId(adminProfileRegNo); //생성자ID
        cmmEmailSendVO.setUpdateId(adminProfileRegNo); //수정자ID
        
        cmmEmailSendVO.setManagerSetTitle(aptUserEmailManageVO.getEmailTitle()); //어드민 메일발송관리에서 관리자가 입력한 제목
        cmmEmailSendVO.setManagerSetContent(aptUserEmailManageVO.getEmailContent()); //어드민 메일발송관리에서 관리자가 입력한 내용
        
        //1.이메일 템플릿 조회 -> 2.이메일발송 유형 구분 -> 3.메일 발송 -> (성공시) -> 4.com_email_send_info 로그적재 관련
        CmmEmailSendVO eamilResultVO = cmmEmailSendService.cmmEmailSend(cmmEmailSendVO, request);
        if("100".equals(eamilResultVO.getMailCode())){ //데이터 연동 성공
            model.addAttribute("result", "0");
        }else{
            model.addAttribute("result", "-1");
        }
        
        return "jsonView";
    }
    
    /**
     * @Method Name        : updateAptUserEmailManageDtlEmailSend
     * @Method description : [admin] 이메일 발송 이력 정보(상세화면) 수정 및 관리자 메일발송
     * @param              : AptUserEmailManageVO, HttpServletRequest, ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/sptUsr/updateAptUserEmailManageDtlEmailSend.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String updateAptUserEmailManageDtlEmailSend(@ModelAttribute("aptUserEmailManageVO") AptUserEmailManageVO aptUserEmailManageVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        String adminProfileRegNo = loginVO.getAdmin_profile_reg_no();
        if(OppfStringUtil.isEmpty(adminProfileRegNo)){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        
        aptUserEmailManageVO.setUpdateId(adminProfileRegNo); //업데이트ID는 어드민 관리자 ID로 저장됩니다.
                        
        //이메일 발송 이력 정보 수정
//        int result = aptUserEmailManageService.updateAptUserEmailManage(aptUserEmailManageVO);              
//        model.addAttribute("result", result);
        
        //메일 발송 이력 조회 상세화면에서 이메일컨텐츠 부분 수정 후 '관리자 수정 메일발송'을 클릭한 후의 프로세스
        //화면에서 넘어온 수신자이메일 값으로 spt_customer_info_profile 테이블을 조회하여 썬더메일발송시 필요한 정보를 가져옵니다.(수신자이름, 수신자ID)
        AptUserEmailManageVO resultSearchVO = aptUserEmailManageService.selectMbrCustomerInfo(aptUserEmailManageVO);
        String receiverName = "";
        String receiverRegNo = "";
        if(resultSearchVO != null){
        	receiverName = resultSearchVO.getReceiverName();
        	receiverRegNo = resultSearchVO.getCustomerRegNo();
        }else{
            receiverName = " ";     // receiverName : Null이거나, 공백일경우 메일전송 안됨
            receiverRegNo = null;
        }
        
        //이메일발송정보 셋팅
        CmmEmailSendVO cmmEmailSendVO = new CmmEmailSendVO();

//        cmmEmailSendVO.setEmailRegNo(aptUserEmailManageVO.getEmailRegNo());
        
        cmmEmailSendVO.setEmailSendType("G016001"); //이메일 유형 - com_email_temp_info 이메일 템플릿 조회        
        cmmEmailSendVO.setManagerAdjYn("Y");
        cmmEmailSendVO.setReceiverName(receiverName); //받는 사람 이름(실제 이메일에 셋팅됩니다.)
        cmmEmailSendVO.setReceiverEmail(aptUserEmailManageVO.getReceiverEmail()); //받는 사람 이메일(실제 이메일에 셋팅되고, 이 주소로 이메일이 발송됩니다.)
        cmmEmailSendVO.setSenderKind("G017001"); //발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
        cmmEmailSendVO.setSenderId(adminProfileRegNo); //발신자 ID
        cmmEmailSendVO.setReceiverKind("G018001"); //수신자 종류 - G018001:일반사용자, G018002:기업사용자        
        cmmEmailSendVO.setReceiverId(receiverRegNo); //수신자 ID
        cmmEmailSendVO.setSendId(adminProfileRegNo); //최초 발신자 ID
        cmmEmailSendVO.setCreateId(adminProfileRegNo); //생성자ID
        cmmEmailSendVO.setUpdateId(adminProfileRegNo); //수정자ID
        
        cmmEmailSendVO.setManagerSetTitle(aptUserEmailManageVO.getEmailTitle()); //어드민 메일발송관리에서 관리자가 입력한 제목
        cmmEmailSendVO.setManagerSetContent(aptUserEmailManageVO.getEmailContent()); //어드민 메일발송관리에서 관리자가 입력한 내용


        //1.이메일 템플릿 조회 -> 2.이메일발송 유형 구분 -> 3.메일 발송 -> (성공시) -> 4.com_email_send_info 로그적재 관련
        CmmEmailSendVO eamilResultVO = cmmEmailSendService.cmmEmailSend(cmmEmailSendVO, request);
        if("100".equals(eamilResultVO.getMailCode())){ //데이터 연동 성공
            model.addAttribute("result", "0");
        }else{
            model.addAttribute("result", "-1");
        }
        
        return "jsonView";
    }
    
    /* 비동기식.ajax 요청 END */
}
