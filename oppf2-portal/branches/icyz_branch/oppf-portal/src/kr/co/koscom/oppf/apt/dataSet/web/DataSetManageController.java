package kr.co.koscom.oppf.apt.dataSet.web;

import kr.co.koscom.oppf.apt.dataSet.service.*;
import kr.co.koscom.oppf.apt.dataSet.service.impl.DataSetManageDAO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
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
 * Created by LSH on 2017. 2. 21..
 */
@Controller
public class DataSetManageController {

    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;      //공통코드서비스

    @Resource(name = "DataSetManageService")
    private DataSetManageService dataSetManageService;

    /**
     * @Method Name        : dataSetManagementList
     * @Method description : 테스트 데이터 관리 목록 화면 이동
     * @param              :
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value = "/apt/data/dataSetManagementList.do", method = {RequestMethod.POST, RequestMethod.GET})
    private String dataSetManagementList(@ModelAttribute("DataSetManageVO") DataSetManageVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //modelView
        String modelView = "/apt/dataSet/dataSetManagementList";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            return modelView;
        }

        //셋팅 공통코드 : 서비스 제공자
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSearchCompanyServiceType("G002002");
        List<CmmFuncVO> pubcompanyCodeIdList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
        model.addAttribute("pubcompanyCodeIdList", pubcompanyCodeIdList);

        model.addAttribute("paramVO", paramVO);

        return modelView;
    }

    /**
     * @Method Name        : selectDataSetManagementList
     * @Method description : 테스트 데이터 관리 목록 조회
     * @param              :
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value = "/apt/data/dataSetManagementList.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String selectDataSetManagementList(@ModelAttribute("DataSetManageVO") DataSetManageVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        Map<String, Object> map = dataSetManageService.selectDataSetManagementList(paramVO);

        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("resultListTotalCount", map.get("totCnt"));

        return "jsonView";
    }

    /**
     * @Method Name        : dataSetManagementExcel
     * @Method description : 테스트 데이터 관리 Excel 다운로드 화면
     * @param              :
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value = "/apt/data/dataSetManagementExcel.do", method = {RequestMethod.POST, RequestMethod.GET})
    private String dataSetManagementExcel(@ModelAttribute("DataSetManageVO") DataSetManageVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //modelView
        String modelView = "/apt/dataSet/dataSetManagementExcel";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            return modelView;
        }

        Map<String, Object> map = dataSetManageService.selectDataSetManagementExcel(paramVO);

        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("paramVO", paramVO);

        return modelView;
    }

    /**
     * @Method Name        : insertDataSetManagement
     * @Method description : 테스트 데이터 비활성화 -> 활성화
     * @param              :
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value = "/apt/data/insertDataSetManagement.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String insertDataSetManagement(@ModelAttribute("DataSetManageVO") DataSetManageVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());

        dataSetManageService.insertDataSetManagement(paramVO);

        model.addAttribute("paramVO", paramVO); //회원번호, 기업코드, 실계좌, 가상계좌 form으로 보냄

        return "jsonView";
    }

    /**
     * @Method Name        : dataSetManagementDtl
     * @Method description : 테스트 데이터 관리 상세 화면
     * @param              :
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value = "/apt/data/dataSetManagementDtl.do", method = {RequestMethod.POST, RequestMethod.GET})
    private String dataSetManagementDtl(@ModelAttribute("DataSetManageVO") DataSetManageVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        String modelView = "/apt/dataSet/dataSetManagementDtl";
        /*API 구분이 필요!!*/

        //로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            model.addAttribute("error", "-1");
            return modelView;
        }

        //기본 api를 list 화면단에서 balance로 지정해놓고 수정할 때마다 parameter로 받을 예정
        String apiAccountDivision = paramVO.getApiAccountDivision();

        DataSetManageVO dataSetManagementDtl = dataSetManageService.dataSetManagementDtl(paramVO);

        model.addAttribute("dataSetManagementDtl", dataSetManagementDtl);
        model.addAttribute("apiAccountDivision", apiAccountDivision);
        model.addAttribute("paramVO", paramVO);

        return modelView;
    }

    /**
     * @Method Name        : dataSetResultList
     * @Method description : 테스트 데이터 관리 목록 조회
     * @param              :
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value = "/apt/data/dataSetResultList.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String dataSetResultList(@ModelAttribute("DataSetManageVO") DataSetManageVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        String apiAccountDivision = paramVO.getApiAccountDivision();

        Map<String, Object> map = dataSetManageService.dataSetResultList(paramVO);

        model.addAttribute("resultList", map);
        model.addAttribute("selectSummary", map.get("selectSummary"));

        model.addAttribute("balSelectEquityList", map.get("balSelectEquityList"));
        model.addAttribute("balSelectFundList", map.get("balSelectFundList"));
        model.addAttribute("balSelectEtcList", map.get("balSelectEtcList"));

        model.addAttribute("portSelectEquityList", map.get("portSelectEquityList"));
        model.addAttribute("portSelectFundList", map.get("portSelectFundList"));
        model.addAttribute("portSelectEtcList", map.get("portSelectEtcList"));

        model.addAttribute("selectTransactionList", map.get("selectTransactionList"));
        model.addAttribute("selectInterestList", map.get("selectInterestList"));
        model.addAttribute("apiAccountDivision", apiAccountDivision);
        model.addAttribute("paramVO", paramVO);
        return "jsonView";
    }

    /*******************************************
     * 상세 화면 각 테이블 수정, 삭제 Event
     *******************************************/

    /**
     * summary 수정
     */
    @RequestMapping(value = "/apt/data/updateSummary.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String updateSummary(@ModelAttribute("DataSetSummaryVO") DataSetSummaryVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
        dataSetManageService.updateSummary(paramVO);

        return "jsonView";
    }

    /**
     * Equity 수정
     */
    @RequestMapping(value = "/apt/data/updateEquity.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String updateEquity(@ModelAttribute("DataSetEquityVO") DataSetEquityVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());

        dataSetManageService.updateEquity(paramVO);

        return "jsonView";
    }

    /**
     * Equity 삭제
     */
    @RequestMapping(value = "/apt/data/deleteEquity.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String deleteEquity(@ModelAttribute("DataSetEquityVO") DataSetEquityVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //1.로그인관련

        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        String apiAccountDivision = paramVO.getApiAccountDivision();
        model.addAttribute("apiAccountDivision", apiAccountDivision);

        paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());

        dataSetManageService.deleteEquity(paramVO);

        return "jsonView";
    }

    /**
     * fund 수정
     */
    @RequestMapping(value = "/apt/data/updateFund.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String updateFund(@ModelAttribute("DataSetFundVO") DataSetFundVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());

        dataSetManageService.updateFund(paramVO);

        return "jsonView";
    }

    /**
     * fund 삭제
     */
    @RequestMapping(value = "/apt/data/deleteFund.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String deleteFund(@ModelAttribute("DataSetFundVO") DataSetFundVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());

        dataSetManageService.deleteFund(paramVO);

        return "jsonView";
    }

    /**
     * Etc 수정
     */
    @RequestMapping(value = "/apt/data/updateEtc.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String updateEtc(@ModelAttribute("DataSetEtcVO") DataSetEtcVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());

        dataSetManageService.updateEtc(paramVO);

        return "jsonView";
    }

    /**
     * Etc 삭제
     */
    @RequestMapping(value = "/apt/data/deleteEtc.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String deleteEtc(@ModelAttribute("DataSetEtcVO") DataSetEtcVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());

        dataSetManageService.deleteEtc(paramVO);

        return "jsonView";
    }

    /**
     * transacion 수정
     */
    @RequestMapping(value = "/apt/data/updateTransaction.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String updateTransaction(@ModelAttribute("DataSetTransactionVO") DataSetTransactionVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        String apiAccountDivision = paramVO.getApiAccountDivision();
        model.addAttribute("apiAccountDivision", apiAccountDivision);

        paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());

        dataSetManageService.updateTransaction(paramVO);

        return "jsonView";
    }

    /**
     * transacion 삭제
     */
    @RequestMapping(value = "/apt/data/deleteTransaction.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String deleteTransaction(@ModelAttribute("DataSetManageVO") DataSetTransactionVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        String apiAccountDivision = paramVO.getApiAccountDivision();
        model.addAttribute("apiAccountDivision", apiAccountDivision);

        paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());

        dataSetManageService.deleteTransaction(paramVO);

        return "jsonView";
    }

    /**
     * group 수정
     */
    @RequestMapping(value = "/apt/data/updateGroup.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String updateGroup(@ModelAttribute("DataSetGroupVO") DataSetGroupVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        String apiAccountDivision = paramVO.getApiAccountDivision();
        model.addAttribute("apiAccountDivision", apiAccountDivision);

        paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());

        dataSetManageService.updateGroup(paramVO);

        return "jsonView";
    }

    /**
     * group 삭제
     */
    @RequestMapping(value = "/apt/data/deleteGroup.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String deleteGroup(@ModelAttribute("DataSetGroupVO") DataSetGroupVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        String apiAccountDivision = paramVO.getApiAccountDivision();
        model.addAttribute("apiAccountDivision", apiAccountDivision);

        paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());

        dataSetManageService.deleteGroup(paramVO);

        return "jsonView";
    }




    /**
     * @Method Name        : dataSetDisable
     * @Method description : 테스트 데이터 데이터셋 비활성화
     * @param              :
     * @return             : String
     * @throws             : Exception
     */

    @RequestMapping(value = "/apt/data/dataSetDisable.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String dataSetDisable(@ModelAttribute("DataSetManageVO") DataSetManageVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        dataSetManageService.deleteGroupManager(paramVO);

        return "jsonView";
    }


    /**
     * @Method Name        : dataSetCopyPopup
     * @Method description : 테스트 데이터 Copy Popup 화면이동
     * @param              :
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value = "/apt/data/dataSetCopyPopup.do", method = {RequestMethod.POST, RequestMethod.GET})
    private String dataSetCopyPopup(@ModelAttribute("DataSetManageVO") DataSetManageVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        String modelView = "/apt/dataSet/dataSetCopyPopup";
        /*API 구분이 필요!!*/

        //로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            model.addAttribute("error", "-1");
            return modelView;
        }

        //기본 api를 list 화면단에서 balance로 지정해놓고 수정할 때마다 parameter로 받을 예정
        String apiAccountDivision = paramVO.getApiAccountDivision();

        DataSetManageVO dataSetManagementDtl = dataSetManageService.dataSetManagementDtl(paramVO);

        model.addAttribute("dataSetManagementDtl", dataSetManagementDtl);
        model.addAttribute("apiAccountDivision", apiAccountDivision);
        model.addAttribute("paramVO", paramVO);

        return modelView;
    }

    /**
     * @Method Name        : dataSetCopyAccount
     * @Method description : 테스트 데이터 Copy
     * @param              :
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value = "/apt/data/dataSetCopyAccount.ajax", method = {RequestMethod.POST, RequestMethod.GET})
    private String dataSetCopyAccount(@ModelAttribute("DataSetManageVO") DataSetManageVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if (loginVO == null) {
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        dataSetManageService.dataSetCopyAccount(paramVO);

        return "jsonView";
    }
}