package kr.co.koscom.oppf.apt.mockup.web;

import kr.co.koscom.oppf.apt.mockup.service.MockUpService;
import kr.co.koscom.oppf.apt.mockup.service.MockUpVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
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
 * Created by LSH on 2017. 2. 10..
 */
@Controller
public class MockUpController {

    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;      //공통코드서비스

    @Resource(name="MockUpService")
    private MockUpService mockUpService;    //목업서비스


    /**
     * @Method Name        : mockUpServiceList
     * @Method description : 목업서비스 목록 이동
     * @param              :
     * @return             : String
     * @throws             : Exception
     */

    @RequestMapping(value="/apt/mock/mockUpServiceList.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String mockupServiceList(@ModelAttribute("MockUpVO") MockUpVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //modelView
        String modelView = "apt/mockup/mockUpServiceList";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        //공통코드
        CmmFuncVO cmmFuncVO = new CmmFuncVO();

        List<CmmFuncVO> pubcompanyCodeIdList = mockUpService.selectCompanyCodeList(cmmFuncVO);
        model.addAttribute("pubcompanyCodeIdList", pubcompanyCodeIdList);

        //httpStatus : httpStatus
        cmmFuncVO.setSystem_grp_code("G033");
        List<CmmFuncVO> httpStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("httpStatusList", httpStatusList);

        model.addAttribute("paramVO", paramVO);

        return modelView;
    }

    /**
     * @Method Name        : selectMockUpServiceList
     * @Method description : 목업 메시지 조회
     * @param              :
     * @return             : Strign
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/mock/selectMockUpServiceList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String selectMockUpServiceList(@ModelAttribute("MockUpVO") MockUpVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        Map<String, Object> map = mockUpService.selectMockUpServiceList(paramVO);

        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("resultListTotalCount", map.get("totCnt"));
        return "jsonView";
    }

    /**
     * @Method Name        : cptCompanyManageListExcel
     * @Method description : 목업 메시지 목록 excel 이동
     * @param              :
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/mock/mockUpServiceListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String mockUpServiceListExcel(@ModelAttribute("MockUpVO") MockUpVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "apt/mockup/mockUpServiceListExcel";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        Map<String, Object> map = mockUpService.selectMockUpServiceListExcel(paramVO);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("paramVO", paramVO);

        return modelView;
    }

    /**
     * @Method Name        : cptCompanyManageListDtl
     * @Method description : 목업 메시지 상세 조회한다
     * @param              :
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/mock/mockUpServiceDtl.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String mockUpServiceDtl(@ModelAttribute("MockUpVO") MockUpVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "apt/mockup/mockUpServiceDtl";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        //공통코드
        CmmFuncVO cmmFuncVO = new CmmFuncVO();

        //httpStatus : httpStatus
        cmmFuncVO.setSystem_grp_code("G033");
        List<CmmFuncVO> httpStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("httpStatusList", httpStatusList);

        MockUpVO resultDetail = mockUpService.selectMockUpServiceDtl(paramVO);

        model.addAttribute("resultDetail", resultDetail);
        model.addAttribute("paramVO", paramVO);

        return modelView;
    }

    /**
     * @Method Name        : saveMockUpService
     * @Method description : 목업 메시지 저장(입력, 수정)
     * @param              :
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/mock/saveMockUpService.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String saveMockUpService(@ModelAttribute("MockUpVO") MockUpVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());

        String mockupServiceNumber = paramVO.getMockupServiceNumber();
        //등록
        if(OppfStringUtil.isEmpty(mockupServiceNumber)){                            //Insert
            paramVO.setMockupUri("/mockupservice/" + paramVO.getMockupUri());       //URI 기본값 '/mockupservice/' 붙임

            mockupServiceNumber = mockUpService.insertMockupService(paramVO);
            model.addAttribute("insertAfter", mockupServiceNumber);
        }else{                                                                      //Update
            int result = mockUpService.updateMockUpService(paramVO);
            model.addAttribute("updateAfter", paramVO.getMockupServiceNumber());
            model.addAttribute("result", result);
        }
        return "jsonView";
    }


    /**
     * @Method Name        : deleteMockUpService
     * @Method description : 목업 메시지 삭제
     * @param              :
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/mock/deleteMockUpService.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String deleteMockUpService(@ModelAttribute("MockUpVO") MockUpVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        paramVO.setUpdateId(loginVO.getAdmin_profile_reg_no());

        int result = mockUpService.deleteMockUpService(paramVO);
        model.addAttribute("result", result);

        return "jsonView";
    }
}
