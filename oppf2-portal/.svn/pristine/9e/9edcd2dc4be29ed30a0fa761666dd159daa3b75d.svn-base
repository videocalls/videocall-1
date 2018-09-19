package kr.co.koscom.oppf.cmm.noti.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.koscom.oppf.cmm.noti.service.NotiFileManageVO;
import kr.co.koscom.oppf.cmm.noti.service.NotiManageService;
import kr.co.koscom.oppf.cmm.noti.service.NotiManageVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : NotiManageController.java
* @Comment  : 관리자의 공지사항 관리를 위한 Controller 클래스
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
public class NotiManageController {
	private static final Logger log = Logger.getLogger(NotiManageController.class);
    
    @Resource(name = "NotiManageService")
    private NotiManageService notiManageService;
    
    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
        
    /**
     * @Method Name        : notiManageList
     * @Method description : 공지사항 목록 페이지로 이동한다.
     * @param              : NotiManageVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/noti/notiManageList.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String notiManageList(@ModelAttribute("NotiManageVO") NotiManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String modelView = "apt/cmm/noti/notiManageList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
    	//셋팅 공통코드 : 그룹노출구분
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G003");//그룹노출구분
        List<CmmFuncVO> noticeKindList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("noticeKindList", noticeKindList);
        
        model.addAttribute("paramVO", paramVO);
        
        return modelView;
    }
    
    /**
     * @Method Name        : selectNotiManageList
     * @Method description : 공지사항 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/noti/selectNotiManageList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectNotiManageList(@ModelAttribute("NotiManageVO") NotiManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = null;
		
		paramVO.setListOrder("a.sort_date desc");
		
		map = notiManageService.selectNotiManageList(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectNotiManageListExcel
     * @Method description : 공지사항 Excel 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/noti/selectNotiManageListExcel.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectNotiManageListExcel(@ModelAttribute("NotiManageVO") NotiManageVO paramVO, ModelMap model)throws Exception{
		Map<String, Object> map = null;
		
		paramVO.setListOrder("a.sort_date desc");
		paramVO.setExcelYn("Y"); //excel 여부 셋팅
		
		map = notiManageService.selectNotiManageListExcel(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : NotiManageSave
     * @Method description : 공지사항 등록/수정 페이지 이동
     * @param              : NotiManageVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/noti/notiManageSave.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String notiManageSave(@ModelAttribute("NotiManageVO") NotiManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String modelView = "apt/cmm/noti/notiManageSave";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
    	//셋팅 공통코드 : 그룹노출구분
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G003");//그룹노출구분
        List<CmmFuncVO> noticeKindList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("noticeKindList", noticeKindList);
        
        //상세조회
        NotiManageVO resultDetail = notiManageService.selectNotiManageDetail(paramVO);
        //파일 ID가 있을 경우 파일 정보를 조회한다.
        List<NotiFileManageVO> fileList = null;
        if(resultDetail != null){
	        if(!OppfStringUtil.isEmpty(resultDetail.getFileId())){
	        	fileList = notiManageService.selectNotiManageDetailFileList(resultDetail);
	        	
	        }
        }
        
        model.addAttribute("resultDetail", resultDetail);
        model.addAttribute("fileList", fileList);
        model.addAttribute("paramVO", paramVO);
        
        return modelView;
    }
    
    /**
     * @Method Name        : insertNoti
     * @Method description : 공지사항 등록
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/noti/insertNoti.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String insertNoti(@ModelAttribute("NotiManageVO") NotiManageVO paramVO, @RequestParam("file") MultipartFile [] files, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "cmm/fileView";
        
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	JSONObject map = new JSONObject();
        	map.put("error", "-1");
        	
        	model.addAttribute("error", "-1");
        	return modelView;
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		//System.out.println("------------------------------------");
		//System.out.println("files.length="+files.length);
		
		List<String> fileNames = paramVO.getFileName();
		List<String> fileSizes = paramVO.getFileSize();
		List<String> fileExts = paramVO.getFileExt();
		
		if(files.length > 0 && fileNames != null && fileSizes != null && fileExts != null){
			List<NotiFileManageVO> fileList = new ArrayList();
			
			for(int i=0;i<files.length; i++){
				if(files[i].getBytes().length > 0){
					//System.out.println("files["+i+"]="+files[i].getBytes());
					
					//파일 정보 셋팅
					NotiFileManageVO fileData = new NotiFileManageVO();
					fileData.setFileName(fileNames.get(i));
					fileData.setFileSize(fileSizes.get(i));
					fileData.setFileExtention(fileExts.get(i));
					fileData.setFileData(files[i].getBytes());
					
					fileData.setCreateId(loginVO.getAdmin_profile_reg_no());
					
					fileList.add(fileData);
				}
			}
			
			paramVO.setFileList(fileList);
		}
		//System.out.println("------------------------------------");
		
		try{
			int result = notiManageService.insertNoti(paramVO);
	        //파일 처리를 위해 map에 셋팅한다.
	  		//HashMap map = new HashMap();
	  		//map.put(key, value)
	  		JSONObject map = new JSONObject();
	  		map.put("result", result);
	  		
	  		model.addAttribute("result", map);
	  		model.addAttribute("callBackFunc", "fn_saveCallBack");
		}catch(Exception e){
			log.debug(e);
			
			JSONObject map = new JSONObject();
	  		map.put("result", "-1");
	  		
	  		model.addAttribute("result", map);
	  		model.addAttribute("callBackFunc", "fn_saveCallBack");
		}
		
	    return modelView;
	}
	
	/**
     * @Method Name        : updateNoti
     * @Method description : 공지사항 수정
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/noti/updateNoti.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String updateNoti(@ModelAttribute("NotiManageVO") NotiManageVO paramVO, @RequestParam("file") MultipartFile [] files, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "cmm/fileView";
        
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	JSONObject map = new JSONObject();
        	map.put("error", "-1");
        	
        	model.addAttribute("error", "-1");
        	return modelView;
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		//System.out.println("------------------------------------");
		//System.out.println("files.length="+files.length);
		
		List<String> fileNames = paramVO.getFileName();
		List<String> fileSizes = paramVO.getFileSize();
		List<String> fileExts = paramVO.getFileExt();
		
		if(files.length > 0 && fileNames != null && fileSizes != null && fileExts != null){
			List<NotiFileManageVO> fileList = new ArrayList();
			
			for(int i=0;i<files.length; i++){
				if(files[i].getBytes().length > 0){
					//System.out.println("files["+i+"]="+files[i].getBytes());
					
					//파일 정보 셋팅
					NotiFileManageVO fileData = new NotiFileManageVO();
					fileData.setFileName(fileNames.get(i));
					fileData.setFileSize(fileSizes.get(i));
					fileData.setFileExtention(fileExts.get(i));
					fileData.setFileData(files[i].getBytes());
					
					fileData.setCreateId(loginVO.getAdmin_profile_reg_no());
					
					fileList.add(fileData);
				}
			}
			
			paramVO.setFileList(fileList);
		}
		//System.out.println("------------------------------------");
		
		try{
			int result = notiManageService.updateNoti(paramVO);
	        //파일 처리를 위해 map에 셋팅한다.
	  		//HashMap map = new HashMap();
	  		//map.put(key, value)
	  		JSONObject map = new JSONObject();
	  		map.put("result", result);
	  		
	  		model.addAttribute("result", map);
	  		model.addAttribute("callBackFunc", "fn_saveCallBack");
		}catch(Exception e){
			log.debug(e);
			
			JSONObject map = new JSONObject();
	  		map.put("result", "-1");
	  		
	  		model.addAttribute("result", map);
	  		model.addAttribute("callBackFunc", "fn_saveCallBack");
		}
		
	    return modelView;
	}
	
	/**
     * @Method Name        : deleteNoti
     * @Method description : 공지사항 삭제
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/noti/deleteNoti.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String deleteNoti(@ModelAttribute("NotiManageVO") NotiManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		int result = notiManageService.deleteNoti(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
    
}
