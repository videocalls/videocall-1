package kr.co.koscom.oppf.cmm.web;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.cmm.menu.service.CmmMenuService;
import kr.co.koscom.oppf.cmm.menu.service.CmmMenuVO;
import kr.co.koscom.oppf.cmm.menu.web.CmmMenuController;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfSessionUtil;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;


/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CmmIncludeController.java
* @Comment  : 공통 include Controller
* @author   : 신동진
* @since    : 2016.05.09
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.09  신동진        최초생성
*
*/
@Controller
public class CmmIncludeController {
    
    
    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
    
    @Resource(name = "CmmMenuService")
    private CmmMenuService cmmMenuService;
    
    private static final Logger log = Logger.getLogger(CmmIncludeController.class);
    
    //----------------------------- Head 관련 START -----------------------------
    
    /**
     * @Method Name        : includeHead
     * @Method description : 공통 include head 처리 - 접속 ip/port에 따라 각 업무페이지로 이동한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/includeHead.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String includeHead(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
        String systemKind = OppfSessionUtil.getSystemKind(request);        //system kind를 가져온다.
        String forwrdUri = "/apt/cmm/include/common-include-apt-head";
        
        if("spt".equals(systemKind)){
            forwrdUri = "/spt/cmm/include/common-include-spt-head";
        }else if("cpt".equals(systemKind)){
            forwrdUri = "/cpt/cmm/include/common-include-cpt-head";
        }else{
            forwrdUri = "/apt/cmm/include/common-include-apt-head";
        }
        
        return forwrdUri;
    }
    
    //----------------------------- Head 관련 END -----------------------------
    
    
    
    //----------------------------- Top 관련 START -----------------------------
    
    /**
     * @Method Name        : includeTop
     * @Method description : 공통 include top 처리 - 접속 ip/port에 따라 각 업무페이지로 이동한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/includeTop.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String includeTop(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
        //1.포워드URL 셋팅작업
        String systemKind = OppfSessionUtil.getSystemKind(request); //system kind를 가져온다.
        if("spt".equals(systemKind)){
            return "forward:/cmm/spt/includeTop.do";
        }else if("cpt".equals(systemKind)){
            return "forward:/cmm/cpt/includeTop.do";
        }else if("apt".equals(systemKind)){
            return "forward:/cmm/apt/includeTop.do";
        }
        return "";
    }
    
    /**
     * @Method Name        : includeTopSpt
     * @Method description : 공통 포털 include top 처리 - 접속 ip/port에 따라 각 업무페이지로 이동한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/cmm/spt/includeTop.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String includeTopSpt(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
        //moblie여부 셋팅
        boolean isMobile = OppfStringUtil.isMobile(request);
        String searchMobileYn = "N";        //모바일인지 여부
        if(isMobile) searchMobileYn = "Y";
        
        //1.포워드URL 셋팅작업
        String systemKind = OppfSessionUtil.getSystemKind(request);        //system kind를 가져온다.
        
        //2.메뉴TOP목록취득작업
        CmmMenuVO paramMenuTopVO = new CmmMenuVO();
        List<CmmMenuVO> menuTopList = null;
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        log.debug("2.메뉴TOP목록취득작업:"+systemKind+"ParamMenuTopVO");
        log.debug("2.메뉴TOP목록취득작업:"+systemKind+"MenuTopList");
        CmmMenuVO sesParamMenuTopVO    = (CmmMenuVO) request.getSession().getAttribute(systemKind+"ParamMenuTopVO");
        List<CmmMenuVO> sesMenuTopList = (List<CmmMenuVO>) request.getSession().getAttribute(systemKind+"MenuTopList");
        
        String sesSearchLoginYn = "";
        if(sesParamMenuTopVO != null && !OppfStringUtil.isEmpty(sesParamMenuTopVO.getSearchLoginYn())){
            sesSearchLoginYn = sesParamMenuTopVO.getSearchLoginYn();
        }
        //2-1.(세션로그인유무==Y 이고 로그인VO != null 이고 세션메뉴톱목록 != null ) 거나 
        //    (세션로그인유무==N 이고 로그인VO == null 이고 세션메뉴톱목록 != null ) 인경우 >>>[로그아웃상태인 경우 이거나 로그인에서 로그인] >>> DB에서 메뉴를 가져오지 않아도 되는 경우
        if( ("Y".equals(sesSearchLoginYn) && loginVO != null && sesMenuTopList.size() > 0 ) || 
                ("N".equals(sesSearchLoginYn) && loginVO == null && sesMenuTopList.size() > 0 ) ){
            log.debug("메뉴:2-1.세션메뉴취득:sesSearchLoginYn="+sesSearchLoginYn);
            log.debug("메뉴:2-1.세션메뉴취득:loginVO="+loginVO);
            log.debug("메뉴:2-1.세션메뉴취득:sesMenuTopList="+sesMenuTopList);
            paramMenuTopVO = sesParamMenuTopVO;
            menuTopList    = sesMenuTopList;
            
        //2-2.그외의 경우[로그아웃상태에서 로그인 이거나 로그인한상태에서 로그아웃된경우] >>> DB에서 메뉴를 가져와야 하는 경우
        }else{
            log.debug("메뉴:2-2.DB메뉴취득:sesSearchLoginYn="+sesSearchLoginYn);
            log.debug("메뉴:2-2.DB메뉴취득:loginVO="+loginVO);
            log.debug("메뉴:2-2.DB메뉴취득:sesMenuTopList="+sesMenuTopList);
            
            //2-2-1.메뉴TOP목록취득에 필요한 매개변수 값세팅
            //2-2-1-1.로그인정보가 없는 경우
            if(loginVO == null){
                paramMenuTopVO.setSearchLoginYn("N");
                
            //2-2-1-2.로그인정보가 있는 경우
            }else{
                paramMenuTopVO.setSearchLoginYn("Y");
            }
            paramMenuTopVO.setSearchSystemKindId(systemKind);
            
            //2-2-2.DB에서 메뉴TOP목록 취득
            paramMenuTopVO.setSearchMobileYn(searchMobileYn);    //모바일인지 여부에 따라 값 셋팅
            menuTopList = cmmMenuService.selectCmmMenuTopList(paramMenuTopVO);
        }
        
        //3.메뉴TOP목록의 이전,다음 트리레벨값셋팅
        String treeLvlNext = "1";
        String treeLvlBefore = "1";
        if(menuTopList != null){
            for(int i=0; i<menuTopList.size(); i++){
                //3-1.다음 트리레벨 설정
                if((i+1) != menuTopList.size()){
                    treeLvlNext = menuTopList.get(i+1).getTreeLvl();
                    menuTopList.get(i).setTreeLvlNext(treeLvlNext);
                }else{
                    menuTopList.get(i).setTreeLvlNext("1");
                }
                
                //3-2.이전 트리레벨 설정
                if(i == 0){
                    menuTopList.get(i).setTreeLvlBefore("1");
                }else{
                    treeLvlBefore = menuTopList.get(i-1).getTreeLvl();
                    menuTopList.get(i).setTreeLvlBefore(treeLvlBefore);
                }
            }
        }
        
        //4.메뉴TOP 값세팅
        model.addAttribute("paramMenuTopVO", paramMenuTopVO);
        model.addAttribute("menuTopList", menuTopList);
        request.getSession().setAttribute(systemKind+"ParamMenuTopVO", paramMenuTopVO);
        request.getSession().setAttribute(systemKind+"MenuTopList", menuTopList);
        
        //5.현재메뉴ID값셋팅[paramMenuId(넘어온메뉴ID)를 currentMenuId(현재메뉴ID)로 셋팅]
        String currentMenuId = "";
        if(!OppfStringUtil.isEmpty(request.getParameter("paramMenuId"))){
            currentMenuId = (String)request.getParameter("paramMenuId");
        }else{
            if(!OppfStringUtil.isEmpty((String)request.getSession().getAttribute(systemKind+"CurrentMenuId"))){
                currentMenuId = (String) request.getSession().getAttribute(systemKind+"CurrentMenuId");
            }
        }
        log.debug("5.현재메뉴ID값셋팅:currentMenuId="+currentMenuId);
        model.addAttribute("currentMenuId", currentMenuId);
        request.getSession().setAttribute(systemKind+"CurrentMenuId", currentMenuId);
        
        //6.현재부모메뉴ID취득
        String parentMenuId = "";
        if(menuTopList != null && !"".equals(currentMenuId)){
            for(int i=0; i<menuTopList.size(); i++){
                String currentMenuId_tmp = (String) menuTopList.get(i).getMenuId();
                if(currentMenuId.equals(currentMenuId_tmp)){
                    parentMenuId = (String) menuTopList.get(i).getParentMenuId();
                }
            }
        }
        log.debug("6.현재부모메뉴ID취득:parentMenuId="+parentMenuId);
        model.addAttribute("parentMenuId", parentMenuId);
        request.getSession().setAttribute(systemKind+"ParentMenuId", parentMenuId);
        
        return "/spt/cmm/include/common-include-spt-top";
    }
    
    /**
     * @Method Name        : includeTopCpt
     * @Method description : 공통 기업포털 include top 처리 - 접속 ip/port에 따라 각 업무페이지로 이동한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/cmm/cpt/includeTop.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String includeTopCpt(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
        //moblie여부 셋팅
        boolean isMobile = OppfStringUtil.isMobile(request);
        String searchMobileYn = "N";        //모바일인지 여부
        if(isMobile) searchMobileYn = "Y";
        
        //1.포워드URL 셋팅작업
        String systemKind = OppfSessionUtil.getSystemKind(request);        //system kind를 가져온다.
        
        //2.메뉴TOP목록취득작업
        CmmMenuVO paramMenuTopVO = new CmmMenuVO();
        List<CmmMenuVO> menuTopList = null;
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        log.debug("2.메뉴TOP목록취득작업:"+systemKind+"ParamMenuTopVO");
        log.debug("2.메뉴TOP목록취득작업:"+systemKind+"MenuTopList");
        CmmMenuVO sesParamMenuTopVO    = (CmmMenuVO) request.getSession().getAttribute(systemKind+"ParamMenuTopVO");
        List<CmmMenuVO> sesMenuTopList = (List<CmmMenuVO>) request.getSession().getAttribute(systemKind+"MenuTopList");
        
        String sesSearchLoginYn = "";
        if(sesParamMenuTopVO != null && !OppfStringUtil.isEmpty(sesParamMenuTopVO.getSearchLoginYn())){
            sesSearchLoginYn = sesParamMenuTopVO.getSearchLoginYn();
        }
        //2-1.(세션로그인유무==Y 이고 로그인VO != null 이고 세션메뉴톱목록 != null ) 거나 
        //    (세션로그인유무==N 이고 로그인VO == null 이고 세션메뉴톱목록 != null ) 인경우 >>>[로그아웃상태인 경우 이거나 로그인에서 로그인] >>> DB에서 메뉴를 가져오지 않아도 되는 경우
        if( ("Y".equals(sesSearchLoginYn) && loginVO != null && sesMenuTopList.size() > 0 ) || 
                ("N".equals(sesSearchLoginYn) && loginVO == null && sesMenuTopList.size() > 0 ) ){
            log.debug("메뉴:2-1.세션메뉴취득:sesSearchLoginYn="+sesSearchLoginYn);
            log.debug("메뉴:2-1.세션메뉴취득:loginVO="+loginVO);
            log.debug("메뉴:2-1.세션메뉴취득:sesMenuTopList="+sesMenuTopList);
            paramMenuTopVO = sesParamMenuTopVO;
            menuTopList    = sesMenuTopList;
            
            //2-2.그외의 경우[로그아웃상태에서 로그인 이거나 로그인한상태에서 로그아웃된경우] >>> DB에서 메뉴를 가져와야 하는 경우
        }else{
            log.debug("메뉴:2-2.DB메뉴취득:sesSearchLoginYn="+sesSearchLoginYn);
            log.debug("메뉴:2-2.DB메뉴취득:loginVO="+loginVO);
            log.debug("메뉴:2-2.DB메뉴취득:sesMenuTopList="+sesMenuTopList);
            
            //2-2-1.메뉴TOP목록취득에 필요한 매개변수 값세팅
            //2-2-1-1.로그인정보가 없는 경우
            if(loginVO == null){
                paramMenuTopVO.setSearchLoginYn("N");
                
                //2-2-1-2.로그인정보가 있는 경우
            }else{
                paramMenuTopVO.setSearchLoginYn("Y");
            }
            paramMenuTopVO.setSearchSystemKindId(systemKind);
            
            //2-2-2.DB에서 메뉴TOP목록 취득
            paramMenuTopVO.setSearchMobileYn(searchMobileYn);    //모바일인지 여부에 따라 값 셋팅
            menuTopList = cmmMenuService.selectCmmMenuTopList(paramMenuTopVO);
        }
        
        //3.메뉴TOP목록의 이전,다음 트리레벨값셋팅
        String treeLvlNext = "1";
        String treeLvlBefore = "1";
        if(menuTopList != null){
            for(int i=0; i<menuTopList.size(); i++){
                //3-1.다음 트리레벨 설정
                if((i+1) != menuTopList.size()){
                    treeLvlNext = menuTopList.get(i+1).getTreeLvl();
                    menuTopList.get(i).setTreeLvlNext(treeLvlNext);
                }else{
                    menuTopList.get(i).setTreeLvlNext("1");
                }
                
                //3-2.이전 트리레벨 설정
                if(i == 0){
                    menuTopList.get(i).setTreeLvlBefore("1");
                }else{
                    treeLvlBefore = menuTopList.get(i-1).getTreeLvl();
                    menuTopList.get(i).setTreeLvlBefore(treeLvlBefore);
                }
            }
        }
        
        //4.메뉴TOP 값세팅
        model.addAttribute("paramMenuTopVO", paramMenuTopVO);
        model.addAttribute("menuTopList", menuTopList);
        request.getSession().setAttribute(systemKind+"ParamMenuTopVO", paramMenuTopVO);
        request.getSession().setAttribute(systemKind+"MenuTopList", menuTopList);
        
        //5.현재메뉴ID값셋팅[paramMenuId(넘어온메뉴ID)를 currentMenuId(현재메뉴ID)로 셋팅]
        String currentMenuId = "";
        if(!OppfStringUtil.isEmpty(request.getParameter("paramMenuId"))){
            currentMenuId = (String)request.getParameter("paramMenuId");
        }else{
            if(!OppfStringUtil.isEmpty((String)request.getSession().getAttribute(systemKind+"CurrentMenuId"))){
                currentMenuId = (String) request.getSession().getAttribute(systemKind+"CurrentMenuId");
            }
        }
        log.debug("5.현재메뉴ID값셋팅:currentMenuId="+currentMenuId);
        model.addAttribute("currentMenuId", currentMenuId);
        request.getSession().setAttribute(systemKind+"CurrentMenuId", currentMenuId);
        
        //6.현재부모메뉴ID취득
        String parentMenuId = "";
        if(menuTopList != null && !"".equals(currentMenuId)){
            for(int i=0; i<menuTopList.size(); i++){
                String currentMenuId_tmp = (String) menuTopList.get(i).getMenuId();
                if(currentMenuId.equals(currentMenuId_tmp)){
                    parentMenuId = (String) menuTopList.get(i).getParentMenuId();
                }
            }
        }
        log.debug("6.현재부모메뉴ID취득:parentMenuId="+parentMenuId);
        model.addAttribute("parentMenuId", parentMenuId);
        request.getSession().setAttribute(systemKind+"ParentMenuId", parentMenuId);
        
        return "/cpt/cmm/include/common-include-cpt-top";
    }
    
    /**
     * @Method Name        : includeTopApt
     * @Method description : 공통 관리자포털 include top 처리 - 접속 ip/port에 따라 각 업무페이지로 이동한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/apt/includeTop.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String includeTopApt(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
        return "/apt/cmm/include/common-include-apt-top";
    }
    
    //----------------------------- Top 관련 END -----------------------------
    
    
    
    //----------------------------- Left 관련 START -----------------------------
    
    /**
     * @Method Name        : includeLeft
     * @Method description : 공통 include left(lnb) 처리 - 접속 ip/port에 따라 각 업무페이지로 이동한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/includeLeft.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String includeLeft(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
        //1.포워드URL 셋팅작업
        String systemKind = OppfSessionUtil.getSystemKind(request);        //system kind를 가져온다.
        if("spt".equals(systemKind)){
            return "forward:/cmm/spt/includeLeft.do";
        }else if("cpt".equals(systemKind)){
            return "forward:/cmm/cpt/includeLeft.do";
        }else if("apt".equals(systemKind)){
            return "forward:/cmm/apt/includeLeft.do";
        }
        return "";
    }
    
    /**
     * @Method Name        : includeLeftSpt
     * @Method description : 공통 include 포털 left(lnb) 처리 - 접속 ip/port에 따라 각 업무페이지로 이동한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/spt/includeLeft.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String includeLeftSpt(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
        
        //0.변수 및 객체선언
        List<String> searchMenuTypeList = new ArrayList<String>();
        
        //1.포워드URL 셋팅작업
        String systemKind = OppfSessionUtil.getSystemKind(request);        //system kind를 가져온다.
        
        //2.메뉴Left 세션정보취득
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        List<CmmMenuVO> menuLeftList = null;
        String currentMenuId = (String) request.getSession().getAttribute(systemKind+"CurrentMenuId");
        String parentMenuId  = (String) request.getSession().getAttribute(systemKind+"ParentMenuId");
        CmmMenuVO paramMenuLeftVO = new CmmMenuVO();
        
        //3.조회조건메뉴타입설정[G020:메뉴형태(001:login,002:menu,003:mypage,004:mypage-핀테크,005:mypage-금투사)]
        String uri = request.getServletPath();
        log.debug("3.조회조건메뉴타입설정:uri="+uri);
        String menuType = "G020002";
        
        if( uri.indexOf("sptLogin.jsp") > -1 ){ //로그인
            menuType = "G020001";
            parentMenuId = "01";
            currentMenuId = "01001";
            
        }else if( uri.indexOf("sptIdFind.jsp") > -1 ){ //아이디_비밀번호 찾기
            menuType = "G020001";
            parentMenuId = "01";
            currentMenuId = "01002";
            
        }else if( uri.indexOf("mbrReg") > -1 ){ //회원가입
            menuType = "G020001";
            parentMenuId = "01";
            currentMenuId = "01003";
        
        }else if( uri.indexOf("introFintech.jsp") > -1 ){ //금융투자 핀테크 포털 소개
            menuType = "G020002";
            parentMenuId = "02";
            currentMenuId = "02001";
            
        }else if( uri.indexOf("introPartner.jsp") > -1 ){ //참여사
            menuType = "G020002";
            parentMenuId = "02";
            currentMenuId = "02002";
            
        }else if( uri.indexOf("introFintechApp.jsp") > -1 ){ //App소개
            menuType = "G020002";
            parentMenuId = "03";
            currentMenuId = "03001";
            
        }else if( uri.indexOf("asumAcntIsu.jsp") > -1 ){ //가상계좌
            menuType = "G020002";
            parentMenuId = "06";
            currentMenuId = "06001";
            
        }else if( uri.indexOf("fintechSvcChoic.jsp") > -1  || uri.indexOf("fintechSvcAppChoic.jsp") > -1){ //핀테크 서비스 선택
            menuType = "G020002";
            parentMenuId = "06";
            currentMenuId = "06001";
            
        }else if( uri.indexOf("svcCompanyTermsConsnt.jsp") > -1 ){ //약관동의(기업)
            menuType = "G020002";
            parentMenuId = "06";
            currentMenuId = "06001";
            
        }else if( uri.indexOf("svcTermConsnt.jsp") > -1 ){ //정보제공동의
            menuType = "G020002";
            parentMenuId = "06";
            currentMenuId = "06001";
            
        }else if( uri.indexOf("svcApplComplt.jsp") > -1 ){ //서비스 신청 완료
            menuType = "G020002";
            parentMenuId = "06";
            currentMenuId = "06001";
            
        }else if( uri.indexOf("noti") > -1 ){ //공지사항
            parentMenuId = "04";
            currentMenuId = "04002";

        }else if( uri.indexOf("faq") > -1 ){ //자주묻는질문
            parentMenuId = "04";
            currentMenuId = "04001";
                
        }else if( uri.indexOf("Main") > -1 ){ //메인
            parentMenuId = "02";
            currentMenuId = "02001";
            
        }else if( uri.indexOf("myp") > -1 ){ //마이페이지
            menuType = "G020003";
            parentMenuId = "05";
            
            if( uri.indexOf("mypSvcApplList") > -1 ){ //마이페이지_신청서비스
                currentMenuId = "05002";   
            }

            if( uri.indexOf("asumAcnt") > -1 ){ //마이페이지_가상계좌
                currentMenuId = "05001";
            }
            
        }else if( uri.indexOf("sptMyPwConfrm") > -1 ){ //회원정보_비밀번호재입력
            menuType = "G020003";
            parentMenuId = "05";
            currentMenuId = "05004";
            
        }else if( uri.indexOf("sptMbrSeces") > -1 ){ //회원탈퇴_비밀번호재입력
            menuType = "G020003";
            parentMenuId = "05";
            currentMenuId = "05005";
            
        }else{
            model.addAttribute("alertMsg","유효하지 않은 URL 입니다.");
            model.addAttribute("moveUrl","/spt/cmm/mainView.do");
            return "/cmm/alertMsg/alertMsg";
        }
        
        paramMenuLeftVO.setSearchSystemKindId(systemKind);
        
        //4-1.특별한메뉴[로그인,마이페이지..]인 경우
        if("G020001".equals(menuType) || "G020003".equals(menuType)){ //G020:메뉴형태[001:login, 002:menu, 003:mypage, 004:mypage-핀테크, 005:mypage-금투사]
            searchMenuTypeList.add(menuType);
            paramMenuLeftVO.setSearchMenuTypeList(searchMenuTypeList);
            menuLeftList = cmmMenuService.selectCmmMenuLeftList(paramMenuLeftVO);
            //서버 모드
            String serverMode = OppfProperties.getProperty("Globals.integrated.account.mode");
            //로그인 정보가 있는 경우(상용서비스) 마이페이지 > 통합계좌 조회 메뉴 권한 체크
            if(loginVO != null && menuLeftList.size() > 0 && "prd".equals(serverMode)){
                // 통합계좌조회메뉴 사용여부가 N 이면 통합계좌조회 메뉴 romove.
                if("N".equals(loginVO.getIntegration_account_yn())){
                    for(CmmMenuVO menuVO : menuLeftList){
                        if(menuVO.getMenuUrl().indexOf("intAcnt.do") > 0){
                            menuLeftList.remove(menuVO);
                            break;
                        }
                    }
                }
            }
        //4-2.특별한메뉴가아닌 그외의경우
        }else{
            log.debug("4-2.특별한메뉴가아닌 그외의경우:menuType="+menuType);
            
            //4-2-1.세션부모메뉴ID가 없거나 세션현재메뉴ID가 없는 경우
            if(OppfStringUtil.isEmpty(parentMenuId) || OppfStringUtil.isEmpty(currentMenuId)){
                log.debug("4-2-1.세션부모메뉴ID가 없거나 세션현재메뉴ID가 없는 경우:parentMenuId="+parentMenuId);
                log.debug("4-2-1.세션부모메뉴ID가 없거나 세션현재메뉴ID가 없는 경우:currentMenuId="+currentMenuId);
                
                
            //4-2-2.세션부모메뉴ID있고세션현재메뉴ID가 있는 그외의 경우
            }else{
                log.debug("4-2-2.세션부모메뉴ID있고세션현재메뉴ID가 있는 그외의 경우:parentMenuId="+parentMenuId);
                log.debug("4-2-2.세션부모메뉴ID있고세션현재메뉴ID가 있는 그외의 경우:currentMenuId="+currentMenuId);
                
                //4-2-2-1.메뉴Left목록취득에 필요한 매개변수 값세팅 후 값취득
                //4-2-2-1-1.로그인정보가 없는 경우
                if(loginVO == null){
                    paramMenuLeftVO.setSearchLoginYn("N");
                    
                //4-2-2-1-2.로그인정보가 있는 경우
                }else{
                    paramMenuLeftVO.setSearchLoginYn("Y");
                }
                
                paramMenuLeftVO.setSearchParentMenuId(parentMenuId);
                menuLeftList = cmmMenuService.selectCmmMenuLeftList(paramMenuLeftVO);
                
            }//4-2-2-2.if close
        }//4-2.if close
        
        model.addAttribute("currentMenuId" , currentMenuId);
        model.addAttribute("menuLeftList"  , menuLeftList);
        model.addAttribute("parentMenuId"  , parentMenuId);
        
        return "/spt/cmm/include/common-include-spt-left";
    }
    
    /**
     * @Method Name        : includeLeftCpt
     * @Method description : 공통 include 기업포털 left(lnb) 처리 - 접속 ip/port에 따라 각 업무페이지로 이동한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/cpt/includeLeft.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String includeLeftCpt(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
        
        //0.변수 및 객체선언
        List<String> searchMenuTypeList = new ArrayList<String>();
        
        //1.포워드URL 셋팅작업
        String systemKind = OppfSessionUtil.getSystemKind(request);        //system kind를 가져온다.
        
        //2.메뉴Left 세션정보취득
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        List<CmmMenuVO> menuLeftList = null;
        String currentMenuId = (String) request.getSession().getAttribute(systemKind+"CurrentMenuId");
        String parentMenuId  = (String) request.getSession().getAttribute(systemKind+"ParentMenuId");
        CmmMenuVO paramMenuLeftVO = new CmmMenuVO();
        
        String companyServiceType = "";
        if(loginVO != null){
            companyServiceType = loginVO.getCompany_service_type(); //기업핀테크서비스타입(=G002)(001:둘다,002:서비스제공자,003:앱개발자)
        }
        
        
        //3.조회조건메뉴타입설정[G020:메뉴형태(001:login,002:menu,003:mypage,004:mypage-핀테크,005:mypage-금투사)]
        String uri = request.getServletPath();
        log.debug("3.조회조건메뉴타입설정:uri="+uri);
        String menuType = "G020002";
        
        if( uri.indexOf("Login.jsp") > -1 ){ //로그인
            menuType = "G020001"; 
            parentMenuId = "01";
            currentMenuId = "01001";
            searchMenuTypeList.add("G020001");
            
        }else if( uri.indexOf("cptIdFind.jsp") > -1 ){ //아이디_비밀번호 찾기
            menuType = "G020001";
            parentMenuId = "01";
            currentMenuId = "01002";
            searchMenuTypeList.add("G020001");
            
        }else if( uri.indexOf("mbrReg.jsp") > -1 ){ //회원가입
            menuType = "G020001";
            parentMenuId = "01";
            currentMenuId = "01003";
            searchMenuTypeList.add("G020001");
           
        }else if( uri.indexOf("introFintech.jsp") > -1 ){ //오픈플랫폼소개
            menuType = "G020002";
            parentMenuId = "02";
            currentMenuId = "02001";
            searchMenuTypeList.add("G020002");
            
        }else if( uri.indexOf("introOppfUse.jsp") > -1 ){ //오픈플랫폼이용안내
            menuType = "G020002";
            parentMenuId = "02";
            currentMenuId = "02003";
            searchMenuTypeList.add("G020002");
            
        }else if( uri.indexOf("introSiseSupport.jsp") > -1 ){ //시세정보 지원
            menuType = "G020002";
            parentMenuId = "02";
            currentMenuId = "02004";
            searchMenuTypeList.add("G020002");
            
            
        }else if( uri.indexOf("introPartner.jsp") > -1 ){ //참여사
            menuType = "G020002";
            parentMenuId = "02";
            currentMenuId = "02002";
            searchMenuTypeList.add("G020002");
            
        }else if( uri.indexOf("noti") > -1 ){ //공지사항
            parentMenuId = "04";
            currentMenuId = "04002";
            searchMenuTypeList.add("G020001");
            
        }else if( uri.indexOf("faq") > -1 ){ //자주묻는질문
            parentMenuId = "04";
            currentMenuId = "04001";
            searchMenuTypeList.add("G020001");
            
        }else if( uri.indexOf("Main") > -1 ){ //메인
            parentMenuId = "02";
            currentMenuId = "02001";
            searchMenuTypeList.add("G020001");
            
        }else if( uri.indexOf("introFintechApp.jsp") > -1 ){ //APP소개
            menuType = "G020002";
            parentMenuId = "03";
            currentMenuId = "03001";
            searchMenuTypeList.add("G020002");
            
        }else if( uri.indexOf("introSvcApi.jsp") > -1 ){ //API소개
            menuType = "G020002";
            parentMenuId = "06";
            currentMenuId = "06001";
            searchMenuTypeList.add("G020002");
            
        }else if( uri.indexOf("myp") > -1 ){ //마이페이지
            menuType = "G020003"; //G020:메뉴형태[001:login, 002:menu, 003:mypage, 004:mypage-핀테크, 005:mypage-금투사]
            parentMenuId = "05"; //마이페이지
            searchMenuTypeList.add("G020003");
            
            if( "G002001".equals(companyServiceType) ){ //둘다[기업핀테크서비스타입(=G002)(001:둘다,002:서비스제공자,003:앱개발자)]
                searchMenuTypeList.add("G020004");
                searchMenuTypeList.add("G020005");
                
            }else if( "G002002".equals(companyServiceType) ){ //서비스제공자[기업핀테크서비스타입(=G002)(001:둘다,002:서비스제공자,003:앱개발자)]
                searchMenuTypeList.add("G020004");
                
            }else if( "G002003".equals(companyServiceType) ){ //앱개발자[기업핀테크서비스타입(=G002)(001:둘다,002:서비스제공자,003:앱개발자)]
                searchMenuTypeList.add("G020005");
            }
            
        }else{
            model.addAttribute("alertMsg","유효하지 않은 URL 입니다.");
            model.addAttribute("moveUrl","/cpt/cmm/mainView.do");
            return "/cmm/alertMsg/alertMsg";
        }
        
        paramMenuLeftVO.setSearchSystemKindId(systemKind);
        
        //4-1.특별한메뉴[로그인,마이페이지..]인 경우
        if(!"G020002".equals(menuType)){ //G020:메뉴형태[001:login, 002:menu, 003:mypage, 004:mypage-핀테크, 005:mypage-금투사]
//            searchMenuTypeList.add(menuType);
            paramMenuLeftVO.setSearchMenuTypeList(searchMenuTypeList);
            menuLeftList = cmmMenuService.selectCmmMenuLeftList(paramMenuLeftVO);
            
        //4-2.특별한메뉴가아닌 그외의경우
        }else{
            log.debug("4-2.특별한메뉴가아닌 그외의경우:menuType="+menuType);
            
            //4-2-1.세션부모메뉴ID가 없거나 세션현재메뉴ID가 없는 경우
            if(OppfStringUtil.isEmpty(parentMenuId) || OppfStringUtil.isEmpty(currentMenuId)){
                log.debug("4-2-1.세션부모메뉴ID가 없거나 세션현재메뉴ID가 없는 경우:parentMenuId="+parentMenuId);
                log.debug("4-2-1.세션부모메뉴ID가 없거나 세션현재메뉴ID가 없는 경우:currentMenuId="+currentMenuId);
                
                
            //4-2-2.세션부모메뉴ID있고세션현재메뉴ID가 있는 그외의 경우
            }else{
                log.debug("4-2-2.세션부모메뉴ID있고세션현재메뉴ID가 있는 그외의 경우:parentMenuId="+parentMenuId);
                log.debug("4-2-2.세션부모메뉴ID있고세션현재메뉴ID가 있는 그외의 경우:currentMenuId="+currentMenuId);
                
                //4-2-2-1.메뉴Left목록취득에 필요한 매개변수 값세팅 후 값취득
                //4-2-2-1-1.로그인정보가 없는 경우
                if(loginVO == null){
                    paramMenuLeftVO.setSearchLoginYn("N");
                    
                //4-2-2-1-2.로그인정보가 있는 경우
                }else{
                    paramMenuLeftVO.setSearchLoginYn("Y");
                }
                
                paramMenuLeftVO.setSearchParentMenuId(parentMenuId);
                menuLeftList = cmmMenuService.selectCmmMenuLeftList(paramMenuLeftVO);
                
            }//4-2-2-2.if close
        }//4-2.if close
        
        model.addAttribute("currentMenuId" , currentMenuId);
        model.addAttribute("menuLeftList"  , menuLeftList);
        model.addAttribute("parentMenuId"  , parentMenuId);
        
        return "/cpt/cmm/include/common-include-cpt-left";
    }
    
    /**
     * @Method Name        : includeLeftApt
     * @Method description : 공통 include 관리자포털 left(lnb) 처리 - 접속 ip/port에 따라 각 업무페이지로 이동한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/apt/includeLeft.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String includeLeftApt(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
        //1.포워드URL 셋팅작업
        String systemKind = OppfSessionUtil.getSystemKind(request);        //system kind를 가져온다.
        
        //2.메뉴Left 세션정보취득
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        List<CmmMenuVO> menuLeftList = null;
        
        //3.현재메뉴ID값셋팅[paramMenuId(넘어온메뉴ID)를 currentMenuId(현재메뉴ID)로 셋팅]
        String currentMenuId = "";
        if(!OppfStringUtil.isEmpty(request.getParameter("paramMenuId"))){
            currentMenuId = (String)request.getParameter("paramMenuId");
        }else{
            if(!OppfStringUtil.isEmpty((String)request.getSession().getAttribute(systemKind+"CurrentMenuId"))){
                currentMenuId = (String) request.getSession().getAttribute(systemKind+"CurrentMenuId");
            }
        }
        log.debug("3.현재메뉴ID값셋팅:currentMenuId="+currentMenuId);
        request.getSession().setAttribute(systemKind+"CurrentMenuId", currentMenuId);
        model.addAttribute("currentMenuId", currentMenuId);
        
        //4.현재부모메뉴ID값셋팅[paramParentMenuId(넘어온메뉴ID)를 currentParentMenuId(현재메뉴ID)로 셋팅]
        String currentParentMenuId = "";
        if(!OppfStringUtil.isEmpty(request.getParameter("paramParentMenuId"))){
            currentParentMenuId = (String)request.getParameter("paramParentMenuId");
        }else{
            if(!OppfStringUtil.isEmpty((String)request.getSession().getAttribute(systemKind+"CurrentParentMenuId"))){
                currentParentMenuId = (String) request.getSession().getAttribute(systemKind+"CurrentParentMenuId");
            }
        }
        log.debug("4.현재부모메뉴ID값셋팅:currentParentMenuId="+currentParentMenuId);
        request.getSession().setAttribute(systemKind+"CurrentParentMenuId", currentParentMenuId);
        model.addAttribute("currentParentMenuId", currentParentMenuId);
        
        //5.메뉴 취득
        CmmMenuVO paramMenuLeftVO = new CmmMenuVO();
        paramMenuLeftVO.setSearchSystemKindId(systemKind);
        menuLeftList = cmmMenuService.selectCmmMenuTopList(paramMenuLeftVO);
        
        //6.메뉴Left목록의 이전,다음 트리레벨값셋팅
        String treeLvlNext = "1";
        String treeLvlBefore = "1";
        if(menuLeftList != null){
            for(int i=0; i<menuLeftList.size(); i++){
                //6-1.다음 트리레벨 설정
                if((i+1) != menuLeftList.size()){
                    treeLvlNext = menuLeftList.get(i+1).getTreeLvl();
                    menuLeftList.get(i).setTreeLvlNext(treeLvlNext);
                }else{
                    menuLeftList.get(i).setTreeLvlNext("1");
                }
                
                //6-2.이전 트리레벨 설정
                if(i == 0){
                    menuLeftList.get(i).setTreeLvlBefore("1");
                }else{
                    treeLvlBefore = menuLeftList.get(i-1).getTreeLvl();
                    menuLeftList.get(i).setTreeLvlBefore(treeLvlBefore);
                }
            }
        }
        String monitoring = OppfProperties.getProperty("Globals.kibana.url");
        model.addAttribute("menuLeftList"  , menuLeftList);
        model.addAttribute("monitoring"  , monitoring);
        
        return "/apt/cmm/include/common-include-apt-left";
    }
    
    //----------------------------- Left 관련 END -----------------------------
    
    
    
    //----------------------------- History 관련 START -----------------------------
    
    /**
     * @Method Name        : includeHistory
     * @Method description : 공통 include left(lnb) 처리 - 접속 ip/port에 따라 각 업무페이지로 이동한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/includeHistory.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String includeHistory(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
        //1.포워드URL 셋팅작업
        String systemKind = OppfSessionUtil.getSystemKind(request);        //system kind를 가져온다.
        String forwrdUri = "/apt/cmm/include/common-include-apt-history";
        if("spt".equals(systemKind)){
            forwrdUri = "/spt/cmm/include/common-include-spt-history";
        }else if("cpt".equals(systemKind)){
            forwrdUri = "/cpt/cmm/include/common-include-cpt-history";
        }else{
            forwrdUri = "/apt/cmm/include/common-include-apt-history";
        }
        
        return forwrdUri;
    }
    
    //----------------------------- History 관련 END -----------------------------
    
    
    
    //----------------------------- Footer 관련 START -----------------------------
    
    /**
     * @Method Name        : includeFooter
     * @Method description : 공통 include footer(lnb) 처리 - 접속 ip/port에 따라 각 업무페이지로 이동한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/includeFooter.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String includeFooter(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
        String systemKind = OppfSessionUtil.getSystemKind(request);        //system kind를 가져온다.    //system kind를 가져온다.
        String forwrdUri = "/apt/cmm/include/common-include-apt-footer";
        
        if("spt".equals(systemKind)){
            forwrdUri = "/spt/cmm/include/common-include-spt-footer";
        }else if("cpt".equals(systemKind)){
            forwrdUri = "/cpt/cmm/include/common-include-cpt-footer";
        }else{
            forwrdUri = "/apt/cmm/include/common-include-apt-footer";
        }
        
        return forwrdUri;
    }
    
    //----------------------------- Footer 관련 END -----------------------------
    
}
