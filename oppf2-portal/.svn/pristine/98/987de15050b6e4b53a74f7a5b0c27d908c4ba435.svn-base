package kr.co.koscom.oppf.cmm.otp.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.cmm.otp.service.CmmOtpReqService;
import kr.co.koscom.oppf.cmm.otp.service.CmmOtpReqVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmOtpReqServiceImpl.java
* @Comment  : OTP 정보를 관리하기  위한 ServiceImpl 클래스
* @author   : 신동진
* @since    : 2016.08.01
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.08.01  신동진        최초생성
*
*/
@Service("CmmOtpReqService")
public class CmmOtpReqServiceImpl implements CmmOtpReqService{
    
    @Resource(name = "CmmOtpReqDAO")
    private CmmOtpReqDAO cmmOtpReqDAO;
    
    private static final Logger log = Logger.getLogger(CmmOtpReqServiceImpl.class);
        
    /**
     * @Method Name        : saveOtpProfile
     * @Method description : OTP 정보를 저장한다.
     * @param              : CmmOtpReqVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int saveOtpProfile(CmmOtpReqVO cmmOtpReqVO) throws Exception{
    	int result = 0;
        
    	//기존데이터가 있는지 여부 체크
    	int chkCnt = cmmOtpReqDAO.checkOtpProfile(cmmOtpReqVO);
    	
    	//chkCnt가 0이상이면 수정
		if(chkCnt > 0){
			result = cmmOtpReqDAO.updateOtpProfile(cmmOtpReqVO);
		}else{
			result = cmmOtpReqDAO.insertOtpProfile(cmmOtpReqVO);
		}
		//hist 셋팅
		result = cmmOtpReqDAO.insertOtpProfileHist(cmmOtpReqVO);
        
        return result;
    }
    
    /**
     * @Method Name        : deleteOtpProfile
     * @Method description : OTP 정보를 삭제한다.
     * @param              : CmmOtpReqVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public Map<String, Object> deleteOtpProfile(CmmOtpReqVO cmmOtpReqVO) throws Exception{
    	int result = 0;
        
    	//기존데이터가 있는지 여부 체크
    	int chkCnt = cmmOtpReqDAO.checkOtpProfile(cmmOtpReqVO);
    	
    	//chkCnt가 0이상이면 삭제
		if(chkCnt > 0){
			result = cmmOtpReqDAO.deleteOtpProfile(cmmOtpReqVO);
			//hist 셋팅
			result = cmmOtpReqDAO.insertOtpProfileHist(cmmOtpReqVO);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("result", result);
		map.put("chkCnt", chkCnt);		
        
        return map;
    }
    
}
