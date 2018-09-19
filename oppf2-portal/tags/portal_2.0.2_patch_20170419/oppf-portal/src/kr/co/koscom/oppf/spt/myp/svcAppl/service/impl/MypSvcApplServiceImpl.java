package kr.co.koscom.oppf.spt.myp.svcAppl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.koscom.oppf.cmm.IntegratedAccount.service.CmmSIFInternalService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplService;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplVO;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : MypSvcApplServiceImpl.java
* @Comment  : [마이페이지>신청서비스]정보관리를 위한 ServiceImpl 클래스
* @author   : 신동진
* @since    : 2016.06.11
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.11  신동진        최초생성
*
*/
@Service("MypSvcApplService")
public class MypSvcApplServiceImpl implements MypSvcApplService{
    private static final Logger log = Logger.getLogger(MypSvcApplServiceImpl.class);
    
    @Resource(name = "MypSvcApplDAO")
    private MypSvcApplDAO mypSvcApplDAO;

	@Resource(name = "CmmSIFInternalService")
	private CmmSIFInternalService cmmSIFInternalService;

    /**
     * @Method Name        : selectMypSvcApplList
     * @Method description : [신청서비스] 신청서비스 목록을 조회한다.
     * @param              : SvcApplVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMypSvcApplList(MypSvcApplVO mypSvcApplVO) throws Exception{
    	List<MypSvcApplVO> resultList = mypSvcApplDAO.selectMypSvcApplList(mypSvcApplVO);
    	for(int i=0; i<resultList.size(); i++){
            String termsRegNo = resultList.get(i).getTermsRegNo();
            if(!OppfStringUtil.isEmpty(termsRegNo)){
                termsRegNo = OppfStringUtil.base64Incoding(termsRegNo);
                resultList.get(i).setTermsRegNoEncryption(termsRegNo);
            }
        }
    	    	
    	Map<String, Object> map = new HashMap<String, Object>();
   		map.put("resultList", resultList);
   		
   		return map;
    }
    
    /**
     * @Method Name        : selectFintechSvcDiscardList
     * @Method description : [신청서비스] 폐기 정보제공동의 목록을 조회한다.  
     * @param              : SvcApplVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectFintechSvcDiscardList(MypSvcApplVO mypSvcApplVO) throws Exception{
    	List<MypSvcApplVO> resultList = mypSvcApplDAO.selectFintechSvcDiscardList(mypSvcApplVO);
    	for(int i=0; i<resultList.size(); i++){
            String termsRegNo = resultList.get(i).getTermsRegNo();
            if(!OppfStringUtil.isEmpty(termsRegNo)){
                termsRegNo = OppfStringUtil.base64Incoding(termsRegNo);
                resultList.get(i).setTermsRegNoEncryption(termsRegNo);
            }
        }
    	    	
    	Map<String, Object> map = new HashMap<String, Object>();
   		map.put("resultList", resultList);
   		
   		return map;
    }
    
    /**
     * @Method Name        : cancelMypSvcAppl
     * @Method description : [신청서비스] 서비스 해지
     * @param              : SvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    public int cancelMypSvcAppl(MypSvcApplVO mypSvcApplVO) throws Exception{
    	int result = -1;
    	
    	//서비스 해지 시 폐기될 동의서 목록 -> 삭제되기 전에 가져온다.
    	List<MypSvcApplVO> termsList = mypSvcApplDAO.selectCancelMypSvcApplTermsList(mypSvcApplVO);
    	
    	//서비스 해지
    	result = mypSvcApplDAO.cancelMypSvcAppl(mypSvcApplVO);
    	result = mypSvcApplDAO.insertMypSvcApplHist(mypSvcApplVO);
    	
    	if(termsList != null){
    		for(int i=0; i<termsList.size(); i++){
    			MypSvcApplVO dataVO = (MypSvcApplVO) termsList.get(i);
    			dataVO.setCustomerRegNo(mypSvcApplVO.getCustomerRegNo());
    			dataVO.setCreateId(mypSvcApplVO.getCustomerRegNo());
    			
    			//동의서 폐기
    	    	result = mypSvcApplDAO.cancelMypSvcApplTerms(dataVO);
    	    	result = mypSvcApplDAO.insertMypSvcApplTermsHist(dataVO);

				cmmSIFInternalService.sendServiceTerms(dataVO.getCustomerId(), dataVO.getTermsRegNo());
				log.debug("금융정보제공 동의서 연계서버 API연동");
    		}
    	}
    	
    	return result;
    }
    
    /**
     * @Method Name        : cancelMypSvcApplTerms
     * @Method description : [신청서비스] 동의서 폐기
     * @param              : SvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    public int cancelMypSvcApplTerms(MypSvcApplVO mypSvcApplVO) throws Exception{
    	int result = -1;
    	
    	//해당 동의서 관련 서비스 해지
    	result = mypSvcApplDAO.updateCancelMypSvcAppl(mypSvcApplVO);
    	result = mypSvcApplDAO.updateCancelMypSvcApplHist(mypSvcApplVO);
    	
    	//동의서 폐기
    	result = mypSvcApplDAO.cancelMypSvcApplTerms(mypSvcApplVO);
    	result = mypSvcApplDAO.insertMypSvcApplTermsHist(mypSvcApplVO);
    	
    	return result;
    }
    
    /**
     * @Method Name        : deleteCustomerAccountProc
     * @Method description : [가상계좌 삭제 공통]서비스 신청 또는 마이페이지 가상계좌에서 가상계좌 삭제 시 연관 정보 처리
     * @param              : MypSvcApplVO
     * @return             : int
     * @throws             : Exception
     */
	@Transactional
	public int deleteCustomerAccountProc(MypSvcApplVO mypSvcApplVO) throws Exception{
		int result = 0;
		
		/**
		 * 1. 삭제한 가상계좌로 신청한 서비스 목록을 가져온다.
		 * 2. 서비스 목록에서 가상계좌가 삭제될 경우 서비스가 해지되어야 할 경우 서비스와 정보제공동의를 해지 또는 폐기한다.
		 * -> accountCnt <= 1 서비스 해지, termsCnt <= 정보제공동의 해지 
		 */
		//삭제한 가상계좌로 신청한 서비스 목록을 가져온다.
		List<MypSvcApplVO> list = (List<MypSvcApplVO>) mypSvcApplDAO.selectCustomerAccountProcList(mypSvcApplVO);
		if(list != null && list.size() > 0){
			for(int i=0; i<list.size(); i++){
				MypSvcApplVO data = (MypSvcApplVO) list.get(i);
				
				int accountCnt = OppfStringUtil.zeroConvert(data.getAccountCnt());
				int termsCnt = OppfStringUtil.zeroConvert(data.getTermsCnt());
				
				//데이터 처리 용 vo
				MypSvcApplVO paramVO = new MypSvcApplVO();
				paramVO.setCustomerRegNo(mypSvcApplVO.getCustomerRegNo());
				paramVO.setCreateId(mypSvcApplVO.getCustomerRegNo());
				
				paramVO.setServiceRegNo(data.getServiceRegNo());
				paramVO.setTermsRegNo(data.getTermsRegNo());
				
				paramVO.setCompanyCodeId(mypSvcApplVO.getCompanyCodeId());
				paramVO.setCustomerRealaccountNo(mypSvcApplVO.getCustomerRealaccountNo());
				
				//서비스 해지 대상
				if(accountCnt == 1){
					//서비스 해지
			    	result += mypSvcApplDAO.cancelMypSvcAppl(paramVO);
			    	result += mypSvcApplDAO.insertMypSvcApplHist(paramVO);
					
					//서비스 해지 시 서비스에 엮인 정보제공 동의도 1개일 경우 약관 해지
					if(termsCnt == 1){
						//동의서 폐기
				    	result += mypSvcApplDAO.cancelMypSvcApplTerms(paramVO);
				    	result += mypSvcApplDAO.insertMypSvcApplTermsHist(paramVO);

						cmmSIFInternalService.sendServiceTerms(data.getCustomerId(), data.getTermsRegNo());
						log.debug("금융정보제공 동의서 연계서버 API연동");
					}
				}
				
				//서비스 가상계좌 삭제 리스트를 조회한다.
				List<MypSvcApplVO> accountList = (List<MypSvcApplVO>) mypSvcApplDAO.selectCustomerAccountDeleteList(paramVO);
				if(accountList != null && accountList.size() > 0){
					for(int j=0; j<accountList.size(); j++){
						MypSvcApplVO accountData = (MypSvcApplVO) accountList.get(j);
						
						//서비스 가상계좌를 삭제한다.
						result += mypSvcApplDAO.deleteCustomerAccount(accountData);
				    	result += mypSvcApplDAO.insertCustomerAccountHist(accountData);
					}
				}				
			}
		}else{
			result = -1;
		}
		
		return result;
	}
}
