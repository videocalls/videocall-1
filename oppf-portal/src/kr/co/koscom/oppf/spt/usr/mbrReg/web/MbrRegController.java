package kr.co.koscom.oppf.spt.usr.mbrReg.web;

import kr.co.koscom.oppf.cmm.service.*;
import kr.co.koscom.oppf.cmm.util.OppfSessionUtil;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegTermsContentVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.impl.MbrRegDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : MbrRegController.java
* @Comment  : [회원가입]정보관리를 위한 Controller 클래스
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
@Slf4j
@Controller
public class MbrRegController {
    
    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
    
    @Resource(name = "MbrRegService")
    private MbrRegService mbrRegService;
    
    @Resource(name = "CmmEmailSendService")
    private CmmEmailSendService cmmEmailSendService;
    
    @Resource(name = "CmmNiceIpinCheckplusService")
    private CmmNiceIpinCheckplusService cmmNiceIpinCheckplusService;
    
    @Resource(name = "MbrRegDAO")
    private MbrRegDAO mbrRegDAO;
    
    /* 동기식.do 요청 START */
    
    /**
     * @Method Name        : mbrRegReg
     * @Method description : [회원가입]페이지로 이동한다.
     * @param              : MbrRegVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/usr/mbrReg/mbrReg.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String mbrReg(
        @ModelAttribute("mbrRegVO") MbrRegVO paramVO
       ,HttpServletRequest request
       ,ModelMap model
    )throws Exception{
        
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        MbrRegVO resultIpinVO = new MbrRegVO();
        MbrRegVO resultMobileVO = new MbrRegVO();
        
        //1.필터링-회원OP등록번호
        String customerRegNo = (String) paramVO.getCustomerRegNo();
        log.debug("1.필터링-회원OP등록번호:customerRegNo : {} ", customerRegNo);
        if(OppfStringUtil.isEmpty(customerRegNo)){
            //1-1.[아이핀]init정보
            resultIpinVO = cmmNiceIpinCheckplusService.initIpinCert(paramVO, request);
            model.addAttribute("resultIpinVO", resultIpinVO);
            
            //1-2.[휴대폰인증]init정보
            resultMobileVO = cmmNiceIpinCheckplusService.initCheckplus(paramVO, request);        
            model.addAttribute("resultMobileVO", resultMobileVO);
            return "spt/usr/mbrReg/mbrReg1";
        }
        
        //2.필터링-회원검증값
        String customerVerify = (String) paramVO.getCustomerVerify();
        log.debug("2.필터링-회원검증값:customerVerify : {} ", customerVerify);
        if(OppfStringUtil.isEmpty(customerVerify)){
            //2-1.[아이핀]init정보
            resultIpinVO = cmmNiceIpinCheckplusService.initIpinCert(paramVO, request);
            model.addAttribute("resultIpinVO", resultIpinVO);
            
            //2-2.[휴대폰인증]init정보
            resultMobileVO = cmmNiceIpinCheckplusService.initCheckplus(paramVO, request);        
            model.addAttribute("resultMobileVO", resultMobileVO);            
            return "spt/usr/mbrReg/mbrReg1";
        }
        
        //3.회원가입스텝
        String customerStep = (String) paramVO.getCustomerStep(); //회원 가입 Step:G006(001:본인인증, 002:공인인증서등록, 003:약관동의, 004:개인정보입력, 005:이메일인증, 006:임시비밀번호발급, 007:임시비밀번호수정)
        
        //3-1.넘어온 스텝이 null인 경우
        if(OppfStringUtil.isEmpty(customerStep)){
            
            //3-1-1.회원검증값(customerVerify)으로 DB조회 후 회원가입스텝(customerStep)정보취득
            MbrRegVO resultVO = mbrRegService.selectSptCustomerProfileInfo(paramVO);
            if(resultVO != null){
                customerStep = resultVO.getCustomerStep();
            }
            
            //3-1-2.필터링-회원가입스텝
            if(OppfStringUtil.isEmpty(customerStep)){
                log.debug("3-1-2.필터링-회원가입스텝:customerStep : {} ", customerStep);
                //3-1-2-1.[아이핀]init정보
                resultIpinVO = cmmNiceIpinCheckplusService.initIpinCert(paramVO, request);
                model.addAttribute("resultIpinVO", resultIpinVO);
                
                //3-1-2-2.[휴대폰인증]init정보
                resultMobileVO = cmmNiceIpinCheckplusService.initCheckplus(paramVO, request);        
                model.addAttribute("resultMobileVO", resultMobileVO);                
                return "spt/usr/mbrReg/mbrReg1";
            }
            
            //3-1-3.회원가입스텝 다음단계
//            int codeSeqNext = 1;
//            cmmFuncVO = new CmmFuncVO();
//            cmmFuncVO.setSystem_grp_code("G006"); //회원 가입 Step:G006(001:본인인증, 002:공인인증서등록, 003:약관동의, 004:개인정보입력, 005:이메일인증, 006:임시비밀번호발급, 007:임시비밀번호수정)
//            List<CmmFuncVO> stepList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
            
            //3-1-4.현재 code_seq 취득을 위한 for문
//            for(int i=0; i<stepList.size(); i++){
//                String systemCode = stepList.get(i).getSystem_code();
//                if(systemCode.equals(customerStep)){
//                    codeSeqNext  = (stepList.get(i).getCode_seq())+1;
//                }
//            }
            
            //3-1-5.다음 system_code 취득을 위한 for문
//            for(int i=0; i<stepList.size(); i++){
//                int codeSeq = stepList.get(i).getCode_seq();
//                if(codeSeqNext == codeSeq){
//                    customerStep = stepList.get(i).getSystem_code();
//                }
//            }
            
            //3-1-6.회원가입스텝 값 셋팅
            paramVO.setCustomerStep(customerStep);
        }
        
        //4.view단 paramVO설정
        model.addAttribute("paramVO", paramVO);
        
        //5.회원가입스텝(customerStep)에 따른 화면단페이지 안내
        //5-1.회원가입스텝(customerStep)이 null이 아닌 경우
        if(!OppfStringUtil.isEmpty(customerStep)){
            
            //5-1-1.[본인인증]인 경우
            if("G006001".equals(customerStep)){ 
                log.info("view호출:5-1-1.[본인인증]:customerStep : {} ", customerStep);
                
                //5-1-2.[아이핀]init정보
                resultIpinVO = cmmNiceIpinCheckplusService.initIpinCert(paramVO, request);
                model.addAttribute("resultIpinVO", resultIpinVO);
                
                //5-1-3.[휴대폰인증]init정보
                resultMobileVO = cmmNiceIpinCheckplusService.initCheckplus(paramVO, request);        
                model.addAttribute("resultMobileVO", resultMobileVO);
                
                return "spt/usr/mbrReg/mbrReg1";
                
            
            //5-1-2.[공인인증서등록]인 경우
            }else if("G006002".equals(customerStep)){
                log.info("view호출:5-1-2.[공인인증서등록]:customerStep : {} ", customerStep);
                
                //5-1-2-1.약관내용호출(G008010:공인인증서등록및약관동의)
//                MbrRegTermsContentVO pMbrRegTermsContentVO = new MbrRegTermsContentVO();
//                MbrRegTermsContentVO rspMbrRegTermsContentVO = new MbrRegTermsContentVO(); 
//                pMbrRegTermsContentVO.setCustomerTermsType("G008010");
//                rspMbrRegTermsContentVO = mbrRegService.selectSptCustomerTermsContentInfo(pMbrRegTermsContentVO);
//                if(rspMbrRegTermsContentVO != null){
//                log.debug("5-1-2-1.약관내용호출:약관제목="+rspMbrRegTermsContentVO.getCustomerTermsTypeName());
//                log.debug("5-1-2-1.약관내용호출:약관내용="+rspMbrRegTermsContentVO.getCustomerTermsContent());
//                }
//                model.addAttribute("rspMbrRegTermsContentVO", rspMbrRegTermsContentVO);
                
                //5-1-2-1.약관목록취득(G008001:서비스이용약관, G008002:개인정보취급방침, G008003:개인정보수집이용동의, G008010:공인인증서등록및약관동의)
                MbrRegTermsContentVO pMbrRegTermsContentVO = new MbrRegTermsContentVO();
                List<String> searchTempList = new ArrayList<String>();
                searchTempList.add("G008010");
                pMbrRegTermsContentVO.setSearchCustomerTermsTypeList(searchTempList);
                List<MbrRegTermsContentVO> termsContentList = mbrRegService.selectSptCustomerTermsContentList(pMbrRegTermsContentVO);
                model.addAttribute("termsContentList", termsContentList);
                
                //5-1-2-2.본인인증단계(아이핀인증, 휴대폰인증)에서 저장한 사용자이름을 조회해 와서 공인인증등록시 공인인증서 DN값의 이름과 비교합니다.
                MbrRegVO requestCustomerInfoVO = new MbrRegVO();
                requestCustomerInfoVO.setCustomerRegNo(customerRegNo);
                MbrRegVO resultCustomerInfoVO = mbrRegService.selectSptCustomerInfoProfile(requestCustomerInfoVO);
                model.addAttribute("resultCustomerInfoVO", resultCustomerInfoVO);
                
                return "spt/usr/mbrReg/mbrReg2";
            
            
            //5-1-3.[약관동의]인 경우
            }else if("G006003".equals(customerStep)){
                log.info("view호출:5-1-3.[약관동의]:customerStep : {} ", customerStep);
                
                //5-1-3-1.약관목록취득(G008001:서비스이용약관, G008002:개인정보취급방침, G008003:개인정보수집이용동의, G008010:공인인증서등록및약관동의)
                MbrRegTermsContentVO pMbrRegTermsContentVO = new MbrRegTermsContentVO();
                List<String> searchTempList = new ArrayList<String>();
                searchTempList.add("G008001");
                searchTempList.add("G008002");
                searchTempList.add("G008003");
                pMbrRegTermsContentVO.setSearchCustomerTermsTypeList(searchTempList);
                List<MbrRegTermsContentVO> termsContentList = mbrRegService.selectSptCustomerTermsContentList(pMbrRegTermsContentVO);
                if(termsContentList != null){
                    for(int i=0; i<termsContentList.size(); i++){
                        log.debug("5-1-3-1.약관목록취득:제목["+i+"]="+termsContentList.get(i).getCustomerTermsTypeName());
                        log.debug("5-1-3-1.약관목록취득:내용["+i+"]="+termsContentList.get(i).getCustomerTermsContent());
                    }
                }
                model.addAttribute("termsContentList", termsContentList);
                
                return "spt/usr/mbrReg/mbrReg3";
            
            
            //5-1-4.[개인정보입력]인 경우
            }else if("G006004".equals(customerStep)){
                
                //기본정보취득
                MbrRegVO rsMbrRegVO = mbrRegService.selectSptCustomerProfileInfo(paramVO);
                String customerNameKor = (String) rsMbrRegVO.getCustomerNameKor();
                
                //값셋팅
                paramVO.setCustomerRegNoPrefix("C"); //회원 OP 등록 번호 prefix( 일반사용자 : C, 기업 : O, admin : A )
                paramVO.setCustomerNameKor(customerNameKor);
                
                //DN값 조회
                paramVO.setCustomerVerifyType("G007002");
                List<MbrRegVO> customerVerifyList = mbrRegService.selectSptCustomerVerifyProfileList(paramVO);
                if(customerVerifyList != null){
                	paramVO.setCustomerVerifyDn(customerVerifyList.get(0).getCustomerVerify());
                }
                
                //셋팅 공통코드:이메일
                cmmFuncVO = new CmmFuncVO();
                cmmFuncVO.setSystem_grp_code("G013");//이메일
                List<CmmFuncVO> emailList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
                model.addAttribute("emailList", emailList);
                
                //셋팅 공통코드:휴대폰번호
                cmmFuncVO = new CmmFuncVO();
                cmmFuncVO.setSystem_grp_code("G011");//휴대폰
                List<CmmFuncVO> hpList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
                model.addAttribute("hpList", hpList);
                
                //셋팅 공통코드:성별
                cmmFuncVO = new CmmFuncVO();
                cmmFuncVO.setSystem_grp_code("G012");//성별
                List<CmmFuncVO> sexList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
                if(sexList != null){
                    for(int i=0; i<sexList.size(); i++){
                        String code   = (String)sexList.get(i).getSystem_code();
                        String codeNm = (String)sexList.get(i).getCode_name_kor();
                        if(code.equals(paramVO.getCustomerSex())){
                            paramVO.setCustomerSexName(codeNm);
                        }
                    }
                }
                model.addAttribute("sexList", sexList);
                
                model.addAttribute("paramVO", paramVO);
                log.info("view호출:5-1-4.[개인정보입력]:customerStep : {} ", customerStep);
                
                return "spt/usr/mbrReg/mbrReg4";
            
            
            //5-1-5.[이메일인증]인 경우
            }else if("G006005".equals(customerStep)){
                log.info("view호출:5-1-5.[이메일인증]:customerStep : {} ", customerStep);
                
                MbrRegVO requestCustomerInfoVO = new MbrRegVO();
                requestCustomerInfoVO.setCustomerRegNo(customerRegNo);
                MbrRegVO resultVO = mbrRegService.selectSptCustomerInfoProfile(requestCustomerInfoVO);
                model.addAttribute("resultVO", resultVO);
                
                return "spt/usr/mbrReg/mbrReg5";
            
            //5-1-6.그외의 경우 [ 회원가입스텝(customerStep)값이 해당코드에 없는 경우 ]
            }else{
                log.info("view호출:5-1-6.그외의 경우:customerStep : {} ", customerStep);
                
                //5-1-6-1.[아이핀]init정보
                resultIpinVO = cmmNiceIpinCheckplusService.initIpinCert(paramVO, request);
                model.addAttribute("resultIpinVO", resultIpinVO);
                
                //5-1-6-2.[휴대폰인증]init정보
                resultMobileVO = cmmNiceIpinCheckplusService.initCheckplus(paramVO, request);        
                model.addAttribute("resultMobileVO", resultMobileVO);
                
                return "spt/usr/mbrReg/mbrReg1";
            }
            
        
        //5-2.그외의 경우 [ 회원가입스텝(customerStep)값이 null인  경우]
        }else{
            log.info("view호출:5-2.그외의 경우:customerStep : {} ", customerStep);
            
            //5-2-1.[아이핀]init정보
            resultIpinVO = cmmNiceIpinCheckplusService.initIpinCert(paramVO, request);
            model.addAttribute("resultIpinVO", resultIpinVO);
            
            //5-2-2.[휴대폰인증]init정보
            resultMobileVO = cmmNiceIpinCheckplusService.initCheckplus(paramVO, request);        
            model.addAttribute("resultMobileVO", resultMobileVO);
            
            return "spt/usr/mbrReg/mbrReg1";
        }
    }
    
    /**
     * @Method Name        : beforeMbrReg6
     * @Method description : [회원가입]이메일수신 후 포털 로그인을 하고 회원가입 완료 페이지로 이동한다. -> 이동 전에 로직 처리
     * @param              : MbrRegVO,HttpServletRequest,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/usr/mbrReg/beforeMbrReg6.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String beforeMbrReg6(@ModelAttribute("mbrRegVO") MbrRegVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{        
        //modelView
        String modelView = "redirect:/usr/mbrReg/mbrReg6.do";
        
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelView;
        }
        
        //회원가입 완료 페이지로 보낼 사용자정보 조회        
        MbrRegVO resultVO = mbrRegService.selectSptCustomerInfoProfile(paramVO);
        if(resultVO != null && !OppfStringUtil.isEmpty(resultVO.getCustomerRegNo())){
        	//이메일 인증 처리 완료 시 처리 안함.
        	if(!"Y".equals(resultVO.getCustomerEmailAuthYn())){
        		//회원 정보 수정
        		MbrRegVO dataVO = new MbrRegVO();
        		dataVO.setCustomerRegNo(resultVO.getCustomerRegNo());
        		dataVO.setCustomerRegStatus("G005002");			//회원 가입 상태
        		dataVO.setCustomerEmailAuthYn("Y");				//회원 이메일 인증여부
        		dataVO.setCustomerEmailRegDate("sysdate");		//회원 이메일 인증일시
        		dataVO.setCustomerRegDate("sysdate");			//회원가입일시
        		dataVO.setCustomerStep("G006005");				//회원 가입 Step:G006(001:본인인증, 002:공인인증서등록, 003:약관동의, 004:개인정보입력, 005:이메일인증, 006:임시비밀번호발급, 007:임시비밀번호수정)
        		
        		int result = mbrRegDAO.updateSptCustomerInfoProfile(dataVO);
        		//메일 발송
        		if(result >= 0){
        			CmmEmailSendVO cmmEmailSendVO = new CmmEmailSendVO();
        			cmmEmailSendVO.setCustomerRegNo(resultVO.getCustomerRegNo());
        			cmmEmailSendVO.setEmailSendType("G016003");							//이메일 유형 - com_email_temp_info 이메일 템플릿 조회 
        			cmmEmailSendVO.setEmailVer("1");									//이메일 버전 - com_email_temp_info 이메일 템플릿 조회  
        			cmmEmailSendVO.setReceiverName(resultVO.getCustomerNameKor());		//받는 사람 이름(실제 이메일에 셋팅됩니다.)
        			
        			String maskName = resultVO.getCustomerNameKor();
        			if(!OppfStringUtil.isEmpty(maskName)){
        				maskName = OppfStringUtil.replaceMask(maskName, 1, maskName.length()-1);
        			}
        			cmmEmailSendVO.setReceiverName2(maskName);						    //받는 사람 이름(실제 이메일에 셋팅됩니다.) -> 마스킹된 이름
        			
        			cmmEmailSendVO.setReceiverEmail(resultVO.getCustomerEmail());
        			String maskEmail = resultVO.getCustomerEmail();
        			if(!OppfStringUtil.isEmpty(maskEmail)){
        				String [] tmpMaskEmail = OppfStringUtil.split(maskEmail, "@");
        				if(tmpMaskEmail != null){
        					maskEmail = OppfStringUtil.replaceMask(tmpMaskEmail[0], 1, tmpMaskEmail[0].length()-1);
        					maskEmail += "@" + tmpMaskEmail[1];
        				}else{
        					maskEmail = OppfStringUtil.replaceMask(maskEmail, 1, maskEmail.length()-1);
        				}
        			}
        			cmmEmailSendVO.setReceiverEmail2(maskEmail);
        			
        			cmmEmailSendVO.setSenderKind("G017003");							//발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
        			cmmEmailSendVO.setReceiverKind("G018001");							//수신자 종류 - G018001:일반사용자, G018002:기업사용자
        			cmmEmailSendVO.setReceiverId(resultVO.getCustomerRegNo());			//수신자 ID(DB저장용)
        			
        			String maskCustomerId = resultVO.getCustomerId();
        			if(!OppfStringUtil.isEmpty(maskCustomerId)){
        				maskCustomerId = OppfStringUtil.replaceMask(maskCustomerId, 1, maskCustomerId.length()-1);
        			}
        			cmmEmailSendVO.setReceiverId2(maskCustomerId);						//수신자 ID(실제 메일발송시 고객에게 보여질 ID)
        			cmmEmailSendVO.setMbrComplt("Y");
        			
        			String uriContextPath = "https://";
        			String sptServerName = OppfProperties.getProperty("Globals.domain.spt");
        			if(sptServerName.indexOf(":") >= 0){
        				String [] tmpStr = sptServerName.split(":");
        				
        				uriContextPath += tmpStr[0] + ":" + OppfProperties.getProperty("Globals.sslPort.spt");
        			}else{
        				uriContextPath += sptServerName;
        			}
        			cmmEmailSendVO.setUriContextPath(uriContextPath);
        			
        			//1.이메일 템플릿 조회 -> 2.이메일발송 유형 구분 -> 3.메일 발송 -> (성공시) -> 4.com_email_send_info 로그적재 관련
        	        cmmEmailSendService.cmmEmailSend(cmmEmailSendVO, request);
        		}
        	}
        }        
        return modelView;
    }
     
    /**
     * @Method Name        : mbrReg6
     * @Method description : [회원가입]이메일수신 후 포털 로그인을 하고 회원가입 완료 페이지로 이동한다.
     * @param              : MbrRegVO,HttpServletRequest,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/usr/mbrReg/mbrReg6.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String mbrReg6(@ModelAttribute("mbrRegVO") MbrRegVO paramVO,HttpServletRequest request,ModelMap model)throws Exception{        
        //modelView
        String modelView = "spt/usr/mbrReg/mbrReg6";
        
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelView;
        }
        
        paramVO.setCustomerRegNo(customerRegNo);
        
        //회원가입 완료 페이지로 보낼 사용자정보 조회        
        MbrRegVO resultVO = mbrRegService.selectSptCustomerInfoProfile(paramVO);
        model.addAttribute("loginVO", loginVO);
        model.addAttribute("resultVO", resultVO);
                
        return modelView;
    }
    
    /**
     * @Method Name        : mbrRegReg
     * @Method description : [회원가입]개인정보 입력 페이지로 이동
     * @param              : MbrRegVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    //@RequestMapping(value="/usr/mbrReg/mbrReg4.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String mbrReg4(
        @ModelAttribute("mbrRegVO") MbrRegVO paramVO
       ,ModelMap model
    )throws Exception{
    	//기본정보취득
        MbrRegVO rsMbrRegVO = mbrRegService.selectSptCustomerProfileInfo(paramVO);
        String customerNameKor = "";
        if(rsMbrRegVO != null) customerNameKor = (String) rsMbrRegVO.getCustomerNameKor();
        
        //값셋팅
        paramVO.setCustomerRegNoPrefix("C"); //회원 OP 등록 번호 prefix( 일반사용자 : C, 기업 : O, admin : A )
        paramVO.setCustomerNameKor(customerNameKor);
        
        //DN값 조회
        paramVO.setCustomerVerifyType("G007002");
        List<MbrRegVO> customerVerifyList = mbrRegService.selectSptCustomerVerifyProfileList(paramVO);
        if(customerVerifyList != null){
        	paramVO.setCustomerVerifyDn(customerVerifyList.get(0).getCustomerVerify());
        }
        
        //셋팅 공통코드:이메일
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G013");//이메일
        List<CmmFuncVO> emailList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("emailList", emailList);
        
        //셋팅 공통코드:휴대폰번호
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G011");//휴대폰
        List<CmmFuncVO> hpList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("hpList", hpList);
        
        //셋팅 공통코드:성별
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G012");//성별
        List<CmmFuncVO> sexList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        if(sexList != null){
            for(int i=0; i<sexList.size(); i++){
                String code   = (String)sexList.get(i).getSystem_code();
                String codeNm = (String)sexList.get(i).getCode_name_kor();
                if(code.equals(paramVO.getCustomerSex())){
                    paramVO.setCustomerSexName(codeNm);
                }
            }
        }
        model.addAttribute("sexList", sexList);
        
        model.addAttribute("paramVO", paramVO);
        
        return "spt/usr/mbrReg/mbrReg4";
    }
    
    //@RequestMapping(value="/usr/mbrReg/mbrReg3.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String mbrReg3(
        @ModelAttribute("mbrRegVO") MbrRegVO paramVO
       ,ModelMap model
    )throws Exception{
        //5-1-3-1.약관목록취득(G008001:서비스이용약관, G008002:개인정보취급방침, G008003:개인정보수집이용동의, G008010:공인인증서등록및약관동의)
        MbrRegTermsContentVO pMbrRegTermsContentVO = new MbrRegTermsContentVO();
        List<String> searchTempList = new ArrayList<String>();
        searchTempList.add("G008001");
        searchTempList.add("G008002");
        searchTempList.add("G008003");
        pMbrRegTermsContentVO.setSearchCustomerTermsTypeList(searchTempList);
        List<MbrRegTermsContentVO> termsContentList = mbrRegService.selectSptCustomerTermsContentList(pMbrRegTermsContentVO);
        if(termsContentList != null){
            for(int i=0; i<termsContentList.size(); i++){
                log.debug("5-1-3-1.약관목록취득:제목["+i+"]="+termsContentList.get(i).getCustomerTermsTypeName());
                log.debug("5-1-3-1.약관목록취득:내용["+i+"]="+termsContentList.get(i).getCustomerTermsContent());
            }
        }
        model.addAttribute("termsContentList", termsContentList);
        model.addAttribute("paramVO", paramVO);
        return "spt/usr/mbrReg/mbrReg3";
    }
    
    /**
     * @Method Name        : 아이핀인증, 이메일발송 테스트용
     * @Method description : [회원가입]페이지로 이동한다.
     * @param              : MbrRegVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    //@RequestMapping(value="/usr/mbrReg/mbrReg2.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String mbrReg2(
        @ModelAttribute("mbrRegVO") MbrRegVO paramVO
       ,HttpServletRequest request
       ,ModelMap model
    )throws Exception{
//        //[아이핀]init정보
//        MbrRegVO resultIpinVO = cmmNiceIpinCheckplusService.initIpinCert(paramVO, request);
//        model.addAttribute("resultIpinVO", resultIpinVO);
//        //[휴대폰인증]init정보
//        MbrRegVO resultMobileVO = cmmNiceIpinCheckplusService.initCheckplus(paramVO, request);        
//        model.addAttribute("resultMobileVO", resultMobileVO);
//        return "spt/usr/mbrReg/mbrReg1";
        String systemKind = OppfSessionUtil.getSystemKind(request);		//system kind를 가져온다.        
        return "spt/usr/mbrReg/mbrReg2";
    }
    
    /**
     * @Method Name        : customerTermsListPopup
     * @Method description : [메인-로그인시]변경약관 (재)동의 팝업 이동.
     * @param              : MbrRegVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/usr/mbrReg/customerTermsListPopup.do",method = {RequestMethod.POST})
    private String customerTermsListPopup(@ModelAttribute("mbrRegVO") MbrRegVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String modelView = "spt/usr/mbrReg/customerTermsListPopup";
        
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelView;
        }
        paramVO.setCustomerRegNo(customerRegNo);
        
        //약관 목록 조회
        List<MbrRegTermsContentVO> resultTermsList = mbrRegService.selectMainSptCustomerTermsList(paramVO);
        
        model.addAttribute("resultTermsList", resultTermsList);

        //금투사기업약관 동의 정보 조회
        List<MbrRegTermsContentVO> companyTermsList = mbrRegService.selectMainCompanyTermsList(paramVO);
        model.addAttribute("companyTermsList", companyTermsList);
        
        
        return modelView;
    }
    
    /* 동기식.do 요청 END */
    
    
    /* 비동기식.ajax 요청 START */
    
    /**
     * @Method Name        : checkId
     * @Method description : [회원가입:ID중복확인]ID정보를 조회한다.
     * @param              : NotiVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/usr/mbrReg/checkId.ajax")
    private String checkId(
        @ModelAttribute("mbrRegVO") MbrRegVO paramVO
       ,ModelMap model
    )throws Exception{
        String ids = mbrRegService.selectCheckId(paramVO);
        String chkSe = "N";
        if(null == ids || ids.length()==0){
            chkSe = "Y";
        }else{
            chkSe = "N";
        }
        model.addAttribute("chkSe", chkSe);
        return "jsonView";
    }
    
    /**
     * @Method Name        : saveMbrReg1
     * @Method description : [회원가입:1.본인인증]정보를 저장을 한다.
     * @param              : MbrRegVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/usr/mbrReg/saveMbrReg1.ajax")
    private String saveMbrReg1(
        @ModelAttribute("mbrRegVO") MbrRegVO paramVO
       ,ModelMap model
    )throws Exception{
        
  	
        log.info("------------- saveMbrReg1.ajax START -------------------");
//       MbrRegVO returnVO = mbrRegService.procMbrReg1(paramVO);
        
//        model.addAttribute("returnVO", returnVO);
        log.info("------------- saveMbrReg1.ajax END ---------------------");
        return "jsonView";
    }
    
    /**
     * @Method Name        : saveMbrReg2
     * @Method description : [회원가입:2.공인인증서등록]정보를 저장을 한다.
     * @param              : MbrRegVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/usr/mbrReg/saveMbrReg2.ajax")
    private String saveMbrReg2(
//        @ModelAttribute("mbrRegVO") MbrRegVO paramVO
        @RequestBody MbrRegVO paramVO
       ,ModelMap model
    )throws Exception{
        
        
        log.info("------------- saveMbrReg2.ajax START -------------------");
        for(int i=0; i<paramVO.getCustomerTermsList().size(); i++){
            log.info("getCustomerTermsType["+i+"]="+paramVO.getCustomerTermsList().get(i).getCustomerTermsType());
        }
        int rs = mbrRegService.procMbrReg2(paramVO);
        
        model.addAttribute("rs", rs);
        log.info("------------- saveMbrReg2.ajax END ---------------------");
        return "jsonView";
    }
    
    /**
     * @Method Name        : saveMbrReg3
     * @Method description : [회원가입:3.약관동의]정보를 저장을 한다.
     * @param              : MbrRegVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/usr/mbrReg/saveMbrReg3.ajax")
    private String saveMbrReg3(
//        @ModelAttribute("mbrRegVO") MbrRegVO paramVO
        @RequestBody MbrRegVO paramVO
       ,ModelMap model
    )throws Exception{
        
        log.info("------------- saveMbrReg3.ajax START -------------------");
        int rs = mbrRegService.procMbrReg3(paramVO);
        model.addAttribute("rs", rs);
        log.info("------------- saveMbrReg3.ajax END ---------------------");
        return "jsonView";
    }
    
    /**
     * @Method Name        : saveMbrReg4
     * @Method description : [회원가입:4.개인정보입력]정보를 저장을 한다.
     * @param              : MbrRegVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/usr/mbrReg/saveMbrReg4.ajax")
    private String saveMbrReg4(
        @ModelAttribute("mbrRegVO") MbrRegVO paramVO
       ,ModelMap model
    )throws Exception{
        
        log.info("------------- saveMbrReg4.ajax START -------------------");
        int rs = mbrRegService.procMbrReg4(paramVO);
        
        model.addAttribute("rs", rs);
        log.info("------------- saveMbrReg4.ajax END ---------------------");
        return "jsonView";
    }
    
    /**
     * @Method Name        : saveMbrReg5
     * @Method description : [회원가입:5.인증메일발송]정보를 저장을 한다.
     * @param              : MbrRegVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/usr/mbrReg/saveMbrReg5.ajax")
    private String saveMbrReg5(
        @ModelAttribute("mbrRegVO") MbrRegVO paramVO
       ,ModelMap model
    )throws Exception{
        log.info("------------- saveMbrReg5.ajax START -------------------");
        int rs = mbrRegService.procMbrReg5(paramVO);
        
        model.addAttribute("rs", rs);
        log.info("------------- saveMbrReg5.ajax END ---------------------");
        return "jsonView";
    }
    
    /**
     * @Method Name        : sendMbrReg4Email
     * @Method description : [회원가입:4.개인정보입력]이메일발송 프로세스를 진행한다.
     * @param              : CmmEmailSendVO
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/usr/mbrReg/sendMbrReg4Email.ajax")
    private String sendMbrReg4Email(
        @ModelAttribute("cmmEmailSendVO") CmmEmailSendVO cmmEmailSendVO
       ,HttpServletRequest request
    )throws Exception{
        log.info("------------- sendMbrReg4Email.ajax START -------------------");
        String emailRegNo = cmmEmailSendService.selectCmmComEmailSendInfoChk(cmmEmailSendVO);
        //회원가입인증메일을 통해 회원가입을 완료하는 경우
        if(!OppfStringUtil.isEmpty(cmmEmailSendVO.getMbrComplt())){
            cmmEmailSendVO.setEmailRegNo(""); //이메일발송정보 테이블에 insert
        
        //회원가입 5단계의 인증메일발송 단계로 들어가 메일발송을 알리는 경우
        }else{
            if(OppfStringUtil.isEmpty(emailRegNo)){ //최초로 회원가입 5단계에 들어와서 인증메일을 발송하는 경우
                cmmEmailSendVO.setEmailRegNo(""); //이메일발송정보 테이블에 insert
            }else{ //이미 회원가입 4단계까지 완료하고 이메일이 발송되었는데 회원이 확인을 하지 않고 다시 회원가입으로 들어와서 다시 인증메일이 발송되는 경우
                cmmEmailSendVO.setEmailRegNo(emailRegNo); //이메일발송정보 테이블에 update
            }
        }
        //1.이메일 템플릿 조회 -> 2.이메일발송 유형 구분 -> 3.메일 발송 -> (성공시) -> 4.com_email_send_info 로그적재 관련
        cmmEmailSendService.cmmEmailSend(cmmEmailSendVO, request);        
        log.info("------------- sendMbrReg4Email.ajax END ---------------------");
        return "jsonView";
    }
    
    /**
     * @Method Name        : saveMbrReg6
     * @Method description : [회원가입:6.회원가입 완료]정보를 저장을 한다.
     * @param              : MbrRegVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/usr/mbrReg/saveMbrReg6.ajax")
    private String saveMbrReg6(
        @ModelAttribute("mbrRegVO") MbrRegVO paramVO
    )throws Exception{
        log.info("------------- saveMbrReg6.ajax START -------------------");
        mbrRegDAO.updateSptCustomerInfoProfile(paramVO);
        log.info("------------- saveMbrReg6.ajax END ---------------------");
        return "jsonView";
    }
    
    /**
     * @Method Name        : saveMbrRegChk
     * @Method description : [회원가입:1.본인인증] 본인인증 저장을 하기 전에 기존에 가입된 여부를 확인합니다.
     * @param              : MbrRegVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/usr/mbrReg/saveMbrRegChk.ajax")
    private String saveMbrRegChk(
        @ModelAttribute("mbrRegVO") MbrRegVO paramVO
       ,ModelMap model
    )throws Exception{
        log.info("------------- saveMbrRegChk.ajax START -------------------");
        //주석처리
        //MbrRegVO returnVO = mbrRegService.selectSptCustomerVerifyProfile(paramVO);
        //model.addAttribute("returnVO", returnVO);
        log.info("------------- saveMbrRegChk.ajax END ---------------------");
        return "jsonView";
    }
    
    /**
     * @Method Name        : saveMbrRegChk2
     * @Method description : [회원가입:1.본인인증] 기존에 가입된 여부를 확인한 후의 처리입니다. 
     * @param              : MbrRegVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @RequestMapping("/usr/mbrReg/saveMbrRegChk2.ajax")
    private String saveMbrRegChk2(
        @ModelAttribute("mbrRegVO") MbrRegVO paramVO
       ,ModelMap model
    )throws Exception{
        log.info("------------- saveMbrRegChk2.ajax START -------------------");
        //기존에 가입된 여부를 확인하기 위한 사용자정보 조회
        MbrRegVO resultVO = mbrRegService.selectSptCustomerInfoProfile(paramVO);        
        model.addAttribute("resultVO", resultVO);
        log.info("------------- saveMbrRegChk2.ajax END ---------------------");
        return "jsonView";
    }
    
    /**
     * @Method Name        : saveCustomerTermsListPopup
     * @Method description : [메인-로그인시]변경약관 (재)동의 팝업 저장
     * @param              : MbrRegVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/usr/mbrReg/saveCustomerTermsListPopup.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String saveCustomerTermsListPopup(@RequestBody MbrRegVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //사용자 정보 셋팅
        paramVO.setCustomerRegNo(customerRegNo);
        paramVO.setCreateId(customerRegNo);
        
        int result = mbrRegService.saveCustomerTermsListPopup(paramVO);
        model.addAttribute("result", result);
        
        return "jsonView";
    }
    
    /* 비동기식.ajax 요청 END */
}
