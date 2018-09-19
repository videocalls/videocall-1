package kr.co.koscom.oppf.cmm.signkorea.web;

import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.signkorea.service.SKSignedDataInfo;
import kr.co.koscom.oppf.cmm.signkorea.service.SKVerifyExtInfo;
import kr.co.koscom.oppf.cmm.signkorea.service.SKVerifyService;
import kr.co.koscom.oppf.cmm.signkorea.service.SKVerifyVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmMenuController.java
* @Comment  : 공통메뉴 관리를 위한 Controller
* @author   : 이환덕
* @since    : 2016.05.24
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.24  이환덕        최초생성
*
*/
@Slf4j
@Controller
public class SKVerifyController {
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "SKVerifyServiceImpl")
    public SKVerifyService sKVerifyService;
	
	/**
     * @Method Name        : selectSKVerify
     * @Method description : 공인인증서 서버 유효성검증을 조회 한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(
	    value  = "/cmm/selectSKVerify.ajax"
	   ,method = RequestMethod.POST
	)
	private String selectSKVerify(
	    @RequestBody SKVerifyVO paramVO
	   ,HttpServletRequest request
	   ,ModelMap model
    )throws Exception{
	    
	    int rs = 0;
	    int rs2 = 0;
	    String signKoreaMsg = "";
	    
	    SKSignedDataInfo pSKSignedDataInfo = new SKSignedDataInfo();
	    SKVerifyExtInfo  pSKVerifyExtInfo  = new SKVerifyExtInfo();
	    
	    
	    //1.ip설정
	    String verifydIp = OppfProperties.getProperty("Globals.skverifyd.ip");
	    if(!OppfStringUtil.isEmpty(paramVO.getDemonIp())){
	        verifydIp = paramVO.getDemonIp();
	    }
	    log.debug("1.ip설정:verifydIp : {} ", verifydIp);
	    
	    //2.포트설정
	    int verifydPort = 7900;
	    if(!OppfStringUtil.isEmpty(paramVO.getDemonIp())){
	        verifydPort = paramVO.getDemonPort();
	    }
	    log.debug("2.포트설정:verifydPort : {} ", verifydPort);
	    
	    //3.data설정
	    String signData = paramVO.getSignData();
	    log.debug("3.data설정:signData : {} ", signData);
	    
	    String datas = "";
	    if(!OppfStringUtil.isEmpty(signData)){
	        pSKSignedDataInfo.setOpcode((byte) 46);
	        datas = signData;
	        pSKSignedDataInfo.setSigned_data(datas.getBytes());
	    }
	    log.debug("3.data설정:datas : {} ", datas);
	    
	    
	    //4.검증데몬으로 검증 요청함수(ip,port,검증할데이터입력클래스,검증후인증서세부정보클래스)
		rs = sKVerifyService.verifySignExt(
		     verifydIp
		    ,verifydPort
		    ,pSKSignedDataInfo
		    ,pSKVerifyExtInfo
		);
		log.debug("4.검증데몬으로검증요청함수:rs(OCSP) : {} ", rs);
		
		//5.검증결과처리
		String rsMsg = "";
		if(rs == 0){
		    rsMsg = "OCSP 성공";
		    
		//OCSP 검증 실패 CRL 검증 전환처리
		}else if(rs == 1){
			log.debug("4. pSKSignedDataInfo.errmsg : {} ", pSKSignedDataInfo.errmsg);
			
		    int errcode = Integer.parseInt(pSKSignedDataInfo.errmsg.substring(0,4));
		    
		    signKoreaMsg += "\nOCSP code="+errcode;
		    
		    //OCSP 통신오류일 경우 CRL 검증으로 자동 전환
		    if(4000 < errcode && errcode < 5000){
		        pSKSignedDataInfo.setOpcode((byte) 18);
		        
		        //4.검증데몬으로 검증 요청함수(ip,port,검증할데이터입력클래스,검증후인증서세부정보클래스)
		        rs2 = sKVerifyService.verifySignExt(
		             verifydIp
		            ,verifydPort
		            ,pSKSignedDataInfo
		            ,pSKVerifyExtInfo
		        );
		        if(rs2 == 0){
		            rsMsg = "CRL 성공";
		            
		            log.debug("4. pSKVerifyExtInfo.Issuer : {} ", pSKVerifyExtInfo.Issuer);
		            log.debug("4. pSKVerifyExtInfo.policy : {} ", pSKVerifyExtInfo.policy);
		            log.debug("4. pSKVerifyExtInfo.Subject : {} ", pSKVerifyExtInfo.Subject);
		            
		        //검증실패
		        }else if(rs2 == 1){
		        	
		        	signKoreaMsg += "\nCRL code="+errcode;
		            
		        //CRL 전환 처리에서의 데몬 통신 실패
		        }else{
		            
		        }
		        
		        rs = rs2;
		        
		    //검증실패
		    }else{
		        
		    }
		    
		}else{
		    if(!OppfStringUtil.isEmpty(pSKSignedDataInfo.getErrmsg())){
                
		        rsMsg = OppfStringUtil.getEncdDcd(pSKSignedDataInfo.getErrmsg(),"euc-kr","UTF-8");
		        rsMsg = new String(pSKSignedDataInfo.getErrmsg().getBytes("MS949"), "UTF-8");
		        log.debug("rsMsg : {} ", rsMsg);
		        rsMsg = new String(pSKSignedDataInfo.getErrmsg().getBytes("euc-kr"), "UTF-8");
		        log.debug("rsMsg : {} ", rsMsg);
		        rsMsg = new String(pSKSignedDataInfo.getErrmsg().getBytes("8859_1"), "UTF-8");
		        log.debug("rsMsg : {} ", rsMsg);
		        rsMsg = new String(pSKSignedDataInfo.getErrmsg().getBytes("ISO_8859_1"), "UTF-8");
		        log.debug("rsMsg : {} ", rsMsg);
		        rsMsg = new String(pSKSignedDataInfo.getErrmsg().getBytes("KSC5601"), "UTF-8");
		        log.debug("rsMsg : {} ", rsMsg);
		        rsMsg = new String(pSKSignedDataInfo.getErrmsg().getBytes("euc-kr"), "UTF-8");
		        log.debug("rsMsg : {} ", rsMsg);
		        rsMsg = new String(pSKSignedDataInfo.getErrmsg().getBytes("euc-kr"), "UTF-8");
		        log.debug("rsMsg : {} ", rsMsg);
		        rsMsg = new String(pSKSignedDataInfo.getErrmsg().getBytes("ISO-8859-1"), "UTF-8");
		        log.debug("rsMsg : {} ", rsMsg);
		        
		    }
		    log.debug("5.rs:공인인증서유효성검증실패:pSKSignedDataInfo.getErrmsg() : {} ", pSKSignedDataInfo.getErrmsg());
		    log.debug("5.rs:공인인증서유효성검증실패:status_flag : {} ", paramVO.getsKSignedDataInfo().getStatus_flag());
		    log.debug("5.rs:공인인증서유효성검증실패:errormsg : {} ", paramVO.getsKSignedDataInfo().getErrmsg());
		}
		
		log.debug("4.검증데몬으로검증요청함수:rs(CRL) : {} ", rs);
		log.debug("4.검증데몬으로검증요청함수:rsMsg : {} ", rsMsg);
		
		//서명검증CRLop코드
		//model.addAttribute("rs"   , rs);
		if(rs > 0){
			model.addAttribute("rs"   , signKoreaMsg);
		}else{
			model.addAttribute("rs"   , rs);
		}
		
        model.addAttribute("rsMsg", rsMsg);
		
	    return "jsonView";
	}
	

}
