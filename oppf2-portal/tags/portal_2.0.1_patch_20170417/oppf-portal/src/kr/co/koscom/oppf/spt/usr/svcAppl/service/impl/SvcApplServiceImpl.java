package kr.co.koscom.oppf.spt.usr.svcAppl.service.impl;

import kr.co.koscom.oppf.apt.usr.mbrReg.service.NewMbrRegVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SvcApplServiceImpl.java
* @Comment  : [서비스신청]정보관리를 위한 Service 클래스
* @author   : 포털 이환덕
* @since    : 2016.05.16
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.16  이환덕        최초생성
*
*/
@Service("SvcApplService")
public class SvcApplServiceImpl implements SvcApplService{
    
    @Resource(name = "SvcApplDAO")
    private SvcApplDAO svcApplDAO;
    
    private static final Logger log = Logger.getLogger(SvcApplServiceImpl.class);

    /**
     * @Method Name        : selectSptCustAccList
     * @Method description : [일반회원가상계좌+기업코드]목록정보를 조회한다.
     * @param              : SvcApplVO
     * @return             : List<SvcApplVO>
     * @throws             : Exception
     */
    public List<SvcApplVO> selectSptCustAccList(SvcApplVO svcApplVO) throws Exception{
        return (List<SvcApplVO>) svcApplDAO.selectSptCustAccList(svcApplVO);
    }
    
    /**
     * @Method Name        : selectSptCustAccInfo
     * @Method description : [일반회원가상계좌+기업코드]정보를 조회한다.
     * @param              : SvcApplVO
     * @return             : SvcApplVO
     * @throws             : Exception
     */
    public SvcApplVO selectSptCustAccInfo(SvcApplVO svcApplVO) throws Exception{
        return (SvcApplVO) svcApplDAO.selectSptCustAccInfo(svcApplVO);
    }

  /**
    * @Method Name        : selectComCompanyCodeList
    * @Method description : [기업코드목록]정보를 조회한다.
    * @param              : ComCompanyCodeVO
    * @return             : List<ComCompanyCodeVO>
    * @throws             : Exception
    */
   public List<ComCompanyCodeVO> selectComCompanyCodeList(ComCompanyCodeVO comCompanyCodeVO) throws Exception{
       //1.기업코드목록 DB조회
       List<ComCompanyCodeVO> rsList = (List<ComCompanyCodeVO>) svcApplDAO.selectComCompanyCodeList(comCompanyCodeVO);
       log.debug("1.기업코드목록 DB조회後:rsList="+rsList);
       return rsList;
   }
   
   /**
    * @Method Name        : selectComCompanyProfileList
    * @Method description : [기업프로파일목록]정보를 조회한다.
    * @param              : ComCompanyProfileVO
    * @return             : List<ComCompanyProfileVO>
    * @throws             : Exception
    */
    @SuppressWarnings("unchecked")
    public List<ComCompanyProfileVO> selectComCompanyProfileList(ComCompanyProfileVO paramVO) throws Exception{
        List<ComCompanyProfileVO> rsList = (List<ComCompanyProfileVO>) svcApplDAO.selectComCompanyProfileList(paramVO);
        return rsList;
    }
    
    /**
     * @Method Name        : selectComCompanyProfileInfo
     * @Method description : [기업프로파일]정보를 조회한다.
     * @param              : ComCompanyProfileVO
     * @return             : ComCompanyProfileVO
     * @throws             : Exception
     */
    public ComCompanyProfileVO selectComCompanyProfileInfo(ComCompanyProfileVO paramVO) throws Exception{
        return (ComCompanyProfileVO) svcApplDAO.selectComCompanyProfileInfo(paramVO);
    }
   
   /**
    * @Method Name        : selectComCompanyCodeInfo
    * @Method description : [기업코드]정보를 조회한다.
    * @param              : ComCompanyCodeVO
    * @return             : ComCompanyCodeVO
    * @throws             : Exception
    */
   public ComCompanyCodeVO selectComCompanyCodeInfo(ComCompanyCodeVO comCompanyCodeVO) throws Exception{
       //1.기업코드 DB조회
       ComCompanyCodeVO rsComCompanyCodeVO = (ComCompanyCodeVO) svcApplDAO.selectComCompanyCodeInfo(comCompanyCodeVO);
       return rsComCompanyCodeVO;
   }
   
   
  /**
    * @Method Name        : selectsptCustomerAccountProfileList
    * @Method description : [일반회원가상계좌목록]정보를 조회한다.
    * @param              : SptCustomerAccountProfileVO
    * @return             : List<SptCustomerAccountProfileVO>
    * @throws             : Exception
    */
   public List<SptCustomerAccountProfileVO> selectSptCustomerAccountProfileList(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception{
       //1.일반회원가상계좌목록 DB조회
       List<SptCustomerAccountProfileVO> rsList = (List<SptCustomerAccountProfileVO>) svcApplDAO.selectSptCustomerAccountProfileList(sptCustomerAccountProfileVO);
       log.debug("1.일반회원가상계좌목록 DB조회後:rsList="+rsList);
       return rsList;
   }
   
   /**
    * @Method Name        : insertSptCustomerAccountProfile
    * @Method description : [일반회원가상계좌]정보를 등록한다. -> 사용 X
    * @param              : SptCustomerAccountProfileVO
    * @return             : int
    * @throws             : Exception
    */
   @Transactional
   public int insertSptCustomerAccountProfile(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception{
       int rs = 0;
       //1.일반회원가상계좌 DB삭제
       int rs1 = svcApplDAO.insertSptCustomerAccountProfile(sptCustomerAccountProfileVO);
       int rs2 = svcApplDAO.insertSptCustomerAccountProfileHist(sptCustomerAccountProfileVO);
       rs = rs1 + rs2;
       log.debug("1.일반회원가상계좌 DB등록後:rs="+rs);
       return rs;
   }
   
   /**
    * @Method Name        : updateSptCustomerAccountProfile
    * @Method description : [일반회원가상계좌]정보를 수정한다.  -> 사용 X
    * @param              : SptCustomerAccountProfileVO
    * @return             : int
    * @throws             : Exception
    */
   @Transactional
   public int updateSptCustomerAccountProfile(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception{
       int rs = 0;
       //1.일반회원가상계좌 DB삭제
       int rs1 = svcApplDAO.updateSptCustomerAccountProfile(sptCustomerAccountProfileVO);
       int rs2 = svcApplDAO.insertSptCustomerAccountProfileHist(sptCustomerAccountProfileVO);
       rs = rs1 + rs2;
       log.debug("1.일반회원가상계좌 DB수정後:rs="+rs);
       return rs;
   }
   
   /**
    * @Method Name        : deleteSptCustomerAccountProfile
    * @Method description : [일반회원가상계좌]정보를 삭제한다. -> 사용 X
    * @param              : SptCustomerAccountProfileVO
    * @return             : int
    * @throws             : Exception
    */
   @Transactional
   public int deleteSptCustomerAccountProfile(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception{
       int rs = 0;
       //1.일반회원가상계좌 DB삭제
       int rs1 = svcApplDAO.deleteSptCustomerAccountProfile(sptCustomerAccountProfileVO);
       int rs2 = svcApplDAO.insertSptCustomerAccountProfileHist(sptCustomerAccountProfileVO);
       rs = rs1 + rs2;
       log.debug("1.일반회원가상계좌 DB삭제後:rs="+rs);
       return rs;
   }
      
   /**
    * @Method Name        : selectFintechSvcCompanyList
    * @Method description : [핀테크서비스 선택] 사용자 기준 금투사 목록을 가져온다.
    * @param              : SvcApplVO
    * @return             : List<SvcApplVO>
    * @throws             : Exception
    */
    public List<SvcApplVO> selectFintechSvcCompanyList(SvcApplVO svcApplVO) throws Exception{
    	return (List<SvcApplVO>) svcApplDAO.selectFintechSvcCompanyList(svcApplVO);
    }
   
   /**
    * @Method Name        : selectFintechSvcList
    * @Method description : [핀테크서비스 선택] 핀테크 서비스 정보 목록을 가져온다.
    * @param              : SvcApplVO
    * @return             : Map<String, Object>
    * @throws             : Exception
    */
    public Map<String, Object> selectFintechSvcList(SvcApplVO svcApplVO) throws Exception{
    	List<SptCustomerServiceProfileVO> resultSvcList = null;							//핀테크 서비스 목록
    	List<ComCompanyProfileVO> resultSvcPubcompanyList = null;						//핀테크 서비스 금투사 목록
    	List<SptCustomerServiceAccountProfileVO> resultSvcAccountList = null;			//핀테크 서비스 가상계좌 목록
    	
    	//핀테크 서비스 목록 조회
    	resultSvcList = svcApplDAO.selectFintechSvcList(svcApplVO);
    	if(resultSvcList != null && resultSvcList.size() > 0){
    		List<String> searchAppId = new ArrayList<String>();
    		
    		//조회된 appIds를 셋팅한다.
    		for(int i=0; i<resultSvcList.size(); i++){
    			SptCustomerServiceProfileVO data = (SptCustomerServiceProfileVO) resultSvcList.get(i);
    			searchAppId.add(data.getAppId());
    		}
    		svcApplVO.setSearchAppId(searchAppId);
    		
    		//핀테크 서비스 금투사 목록 조회
    		resultSvcPubcompanyList = svcApplDAO.selectFintechSvcPubcompanyList(svcApplVO);
    		
    		//가상계좌 목록 조회
        	resultSvcAccountList = svcApplDAO.selectFintechSvcAccountList(svcApplVO);
    	}
    	
    	Map<String, Object> map = new HashMap<String, Object>();
   		map.put("resultSvcList", resultSvcList);
   		map.put("resultSvcPubcompanyList", resultSvcPubcompanyList);
   		map.put("resultSvcAccountList", resultSvcAccountList);
   		
   		return map;
    }
    
    /**
     * @Method Name        : insertFintechSvc
     * @Method description : [핀테크서비스 선택] 핀테크 서비스 선택 정보를 등록한다.
     * @param              : SvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int insertFintechSvc(SvcApplVO svcApplVO) throws Exception{
        int result = -1;
        
        List<SptCustomerServiceProfileVO> list = svcApplVO.getSptCustomerServiceProfileList();
        if(list != null && list.size() > 0){
        	for(int i=0; i<list.size(); i++){
        		SptCustomerServiceProfileVO vo = (SptCustomerServiceProfileVO) list.get(i);
        		vo.setCustomerRegNo(svcApplVO.getCreateId());
        		vo.setCreateId(svcApplVO.getCreateId());
        		
        		//일반회원 서비스 프로파일 정보를 등록한다.
        		String serviceRegNo = svcApplDAO.insertSptCustomerServiceProfile(vo);
        		vo.setServiceRegNo(serviceRegNo);
        		
        		//일반회원 서비스 프로파일 hist 정보를 등록한다.
        		result = svcApplDAO.insertSptCustomerServiceProfileHist(vo);
        	}
        }
        
        return result;
    }
    
    /**
     * @Method Name        : selectFintechSvcTermsList
     * @Method description : [핀테크서비스선택] 사용자의 약관정보를 가져온다. 
     * @param              : SvcApplVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectFintechSvcTermsList(SvcApplVO svcApplVO) throws Exception{
     	List<SptCustomerServiceTermsPubcompanyProfileVO> resultSvcTermsList = null;
     	
     	//핀테크 서비스 약관 목록 조회
     	resultSvcTermsList = svcApplDAO.selectFintechSvcTermsList(svcApplVO);
     	
     	Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultSvcTermsList", resultSvcTermsList);
		
		return map;
     }
    
    /**
     * @Method Name        : saveFintechSvcTerms
     * @Method description : [핀테크서비스선택] 가상계좌 선택 정보를 저장한다.
     * @param              : SvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int saveFintechSvcTerms(SvcApplVO svcApplVO) throws Exception{
        int result = -1;
        String customerRegNo = svcApplVO.getCustomerRegNo();
        
        /**
         * 1. 일반회원서비스 계좌 프로파일 정보 등록/수정/삭제
         * 2. 일반회원서비스 약관 프로파일 정보 등록
         * 3. 일반회원서비스 약관 금투사  프로파일 정보 등록
         * 
         * 모든 처리가 끝난후 데이터 클랜징을 한다.
         * 클랜징 대상
         * 1. 새로 추가한 서비스 중 기존에 동일한 핀테크기업에 동일한 금투사로 정보제공동의가 있을 경우 정보제공동의를 같이쓰게 해줘야 한다.
         */
        
        //System.out.println("기본 param을 가져온다. start");
        //기본 param을 가져온다.
        List<SptCustomerServiceAccountProfileVO> accountProfile = svcApplVO.getSptCustomerServiceAccountProfileVO();
        List<SptCustomerServiceTermsProfileVO> termsProfile = svcApplVO.getSptCustomerServiceTermsProfileVO();
        List<SptCustomerServiceTermsPubcompanyProfileVO> termsPubcompanyProfile = svcApplVO.getSptCustomerServiceTermsPubcompanyProfileVO();
        //System.out.println("기본 param을 가져온다. end");
        
        if(
        	accountProfile != null && accountProfile.size() > 0 &&
        	termsProfile != null && termsProfile.size() > 0 &&
        	termsPubcompanyProfile != null && termsPubcompanyProfile.size() > 0
        ){          	
	        //1. 일반회원서비스 계좌 프로파일 정보 등록/수정/삭제
        	for(int i=0; i<accountProfile.size(); i++){
        		SptCustomerServiceAccountProfileVO accountVO = (SptCustomerServiceAccountProfileVO) accountProfile.get(i);
        		accountVO.setCustomerRegNo(customerRegNo);
        		accountVO.setCreateId(customerRegNo);
        		
        		//일반회원서비스 계좌 프로파일 정보 정보를 저장한다.
        		if(!"".equals(accountVO.getActionType())){
        			if("I".equals(accountVO.getActionType())){
        				String accountRegNo = svcApplDAO.insertSptCustomerServiceAccountProfile(accountVO);
        				accountVO.setAccountRegNo(accountRegNo);
        			}else{
        				result = svcApplDAO.updateSptCustomerServiceAccountProfile(accountVO);
        			}
        			
        			//일반회원서비스 계좌 프로파일 hist 정보를 등록한다.
            		result = svcApplDAO.insertSptCustomerServiceAccountProfileHist(accountVO);
        		}
        	}
	        
	        //2. 일반회원서비스 약관 프로파일 정보 등록
        	for(int i=0; i<termsProfile.size(); i++){
        		SptCustomerServiceTermsProfileVO termsVO = (SptCustomerServiceTermsProfileVO) termsProfile.get(i);
        		String subcompanyCodeId = termsVO.getSubcompanyCodeId();
        		String termsRegNo = "";
        		
        		//기본정보 셋팅
        		termsVO.setCustomerRegNo(customerRegNo);
        		termsVO.setCreateId(customerRegNo);
        		termsVO.setTermsPolicy(svcApplVO.getTermsPolicy());
        		
        		if("I".equals(termsVO.getActionType())){
	        		//약관정보 등록
	        		termsRegNo = svcApplDAO.insertSptCustomerServiceTermsProfile(termsVO);
	        		termsVO.setTermsRegNo(termsRegNo);
	        		result = svcApplDAO.insertSptCustomerServiceTermsProfileHist(termsVO);
	        		
	        		for(int j=0; j<termsPubcompanyProfile.size(); j++){
	        			SptCustomerServiceTermsPubcompanyProfileVO termsPubcompanyVO = (SptCustomerServiceTermsPubcompanyProfileVO) termsPubcompanyProfile.get(j);
	        			//같은 subcompanyCodeId일때
	        			if(subcompanyCodeId.equals(termsPubcompanyVO.getSubcompanyCodeId())){
	        				String termsPubcompanyRegNo = "";
	        				//기본정보 셋팅
	        				termsPubcompanyVO.setCustomerRegNo(customerRegNo);
	        				termsPubcompanyVO.setCreateId(customerRegNo);
	        				termsPubcompanyVO.setTermsRegNo(termsRegNo);
	        				        				
	        				termsPubcompanyRegNo = svcApplDAO.insertSptCustomerServiceTermsPubcompanyProfile(termsPubcompanyVO);
	        				termsPubcompanyVO.setTermsPubcompanyRegNo(termsPubcompanyRegNo);
	                		result = svcApplDAO.insertSptCustomerServiceTermsPubcompanyProfileHist(termsPubcompanyVO);
	        			}
	        	
	        		}
	        		
	        		//3. 일반회원 서비스 프로파일 약관등록번호 update
	        		SptCustomerServiceProfileVO serviceProfileVO = new SptCustomerServiceProfileVO();
	        		serviceProfileVO.setCustomerRegNo(customerRegNo);
	        		serviceProfileVO.setCreateId(customerRegNo);
	        		serviceProfileVO.setTermsRegNo(termsRegNo);
	        		serviceProfileVO.setCompanyCodeId(subcompanyCodeId);
	        		
	        		//일반회원 서비스 프로파일 정보를 수정한다.
	        		result = svcApplDAO.updateSptCustomerServiceProfile(serviceProfileVO);
	        		//일반회원 서비스 프로파일 hist 정보를 등록한다.
	        		result = svcApplDAO.updateSptCustomerServiceProfileHist(serviceProfileVO);
        		}else if("D".equals(termsVO.getActionType())){
        			//3. 일반회원 서비스 프로파일 약관등록번호 update -> terms를 null처리한다.
            		SptCustomerServiceProfileVO serviceProfileVO = new SptCustomerServiceProfileVO();
            		serviceProfileVO.setCustomerRegNo(customerRegNo);
            		serviceProfileVO.setCreateId(customerRegNo);
            		serviceProfileVO.setTermsRegNo(null);
            		serviceProfileVO.setCompanyCodeId(subcompanyCodeId);
            		
            		//일반회원 서비스 프로파일 정보를 수정한다.
            		result = svcApplDAO.updateSptCustomerServiceProfile(serviceProfileVO);
            		//일반회원 서비스 프로파일 hist 정보를 등록한다.
            		result = svcApplDAO.updateSptCustomerServiceProfileHist(serviceProfileVO);        			
        		}
        	}
        }
        
        //데이터 클랜징 작업 시작 -> 로직으로 풀기 힘든 정보를 데이터 조회/수정 처리를 통해 알맞은 정보로 셋팅한다.
        //1. 새로 추가한 서비스 중 기존에 동일한 핀테크기업에 동일한 금투사로 정보제공동의가 있을 경우 정보제공동의를 같이쓰게 해줘야 한다.
        //서비스의 정보제공동의 클랭징 목록을 조회한다.
        List<SvcApplVO> svcTermsCleanList = svcApplDAO.selectFintechSvcTermsCleanList(svcApplVO);
        if(svcTermsCleanList != null){
        	for(int i=0; i<svcTermsCleanList.size(); i++){
        		SvcApplVO cleanData = (SvcApplVO) svcTermsCleanList.get(i);
        		
        		SptCustomerServiceProfileVO serviceProfileVO = new SptCustomerServiceProfileVO();
        		serviceProfileVO.setCustomerRegNo(customerRegNo);
        		serviceProfileVO.setCreateId(customerRegNo);
        		serviceProfileVO.setServiceRegNo(cleanData.getServiceRegNo());
        		serviceProfileVO.setTermsRegNo(cleanData.getTermsRegNo());
        		
        		//서비스 프로파일에 정보제공 동의 번호를 update한다.
        		result = svcApplDAO.updateSptCustomerServiceProfileSvcTermsClean(serviceProfileVO);
        		//System.out.println("END~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        		//서비스 프로파일 hist 정보를 등록한다.
        		serviceProfileVO = new SptCustomerServiceProfileVO();
        		result = svcApplDAO.insertSptCustomerServiceProfileHist(serviceProfileVO);
        	}
        }
        
        return result;
    }
    
    /**
     * @Method Name        : selectSvcCompanyTermsConsntList
     * @Method description : [약관동의] 약관동의 목록을 조회한다.
     * @param              : SvcApplVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSvcCompanyTermsConsntList(SvcApplVO svcApplVO) throws Exception{
     	List<SptCustomerCompanyTermsProfileVO> resultList = svcApplDAO.selectSvcCompanyTermsConsntList(svcApplVO);
     	List<SptCustomerCompanyTermsProfileVO> resultAgreeCompanyList = svcApplDAO.selectSvcCompanyTermsConsntAgreeCompanyList(svcApplVO);
     	
     	Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", resultList);
		map.put("resultAgreeCompanyList", resultAgreeCompanyList);
		
		return map;
     }
    
    /**
     * @Method Name        : saveSvcCompanyTermsConsnt
     * @Method description : [약관동의] 기업약관을 저장한다.
     * @param              : SvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int saveSvcCompanyTermsConsnt(SvcApplVO svcApplVO) throws Exception{
        int result = -1;
        String customerRegNo = svcApplVO.getCustomerRegNo();
        String companyTermsType = "G031001";		//기업 서비스 이용약관
        
        /**
         * 1. 기업약관 정보 확인
         * 2. 기업약관 정보 등록 or 수정
         */
        
        //기본 param을 가져온다.
        List<SptCustomerCompanyTermsProfileVO> termsList = svcApplVO.getSptCustomerCompanyTermsProfileVO();
        if(termsList != null && termsList.size() > 0){
        	for(int i=0; i<termsList.size(); i++){
        		SptCustomerCompanyTermsProfileVO data = (SptCustomerCompanyTermsProfileVO) termsList.get(i);
        		
        		//기본정보 셋팅
        		data.setCustomerRegNo(customerRegNo);
        		data.setCompanyTermsType(companyTermsType);
        		data.setCreateId(customerRegNo);
        		data.setCompanyTermsAuthYn("Y");
        		
        		//1. 기업약관 정보 확인
        		int cnt = svcApplDAO.checkSptCustomerCompanyTermsProfile(data);
        		
        		//기존에 있으면 update
        		if(cnt > 0){
        			result = svcApplDAO.updateSptCustomerCompanyTermsProfile(data);
        			
        	    //있으면 insert
        		}else{
        			result = svcApplDAO.insertSptCustomerCompanyTermsProfile(data);
        		}
        		
        		//hist insert
        		result = svcApplDAO.insertSptCustomerCompanyTermsProfileHist(data);
        	}
        }
        
        return result;
    }
    
    /**
     * @Method Name        : selectSvcTermConsntList
     * @Method description : [정보제공동의] 정보제공동의 목록을 조회한다.
     * @param              : SvcApplVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSvcTermConsntList(SvcApplVO svcApplVO) throws Exception{
     	List<SptCustomerServiceAccountProfileVO> resultList = null;
     	
     	//핀테크 서비스 약관 목록 조회
     	resultList = svcApplDAO.selectSvcTermConsntList(svcApplVO);
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
     * @Method Name        : selectSptCustomerServiceTermsProfile
     * @Method description : [일반회원서비스약관프로파일]정보를 조회한다.
     * @param              : SptCustomerServiceTermsPubcompanyProfileVO
     * @return             : SptCustomerServiceTermsPubcompanyProfileVO
     * @throws             : Exception
     */
    @Override
    public SptCustomerServiceTermsProfileVO selectSptCustomerServiceTermsProfile(SptCustomerServiceTermsProfileVO paramVO) throws Exception{
        return (SptCustomerServiceTermsProfileVO) svcApplDAO.selectSptCustomerServiceTermsProfile(paramVO);
    }
    
    /**
     * @Method Name        : selectSptCustomerServiceTermsPubcompanyProfileList
     * @Method description : [일반회원서비스약관금투사프로파일목록]정보를 조회한다.
     * @param              : SptCustomerServiceTermsPubcompanyProfileVO
     * @return             : List<SptCustomerServiceTermsPubcompanyProfileVO>
     * @throws             : Exception
     */
    @Override
    public List<SptCustomerServiceTermsPubcompanyProfileVO> selectSptCustomerServiceTermsPubcompanyProfileList(SptCustomerServiceTermsPubcompanyProfileVO paramVO) throws Exception{
        return (List<SptCustomerServiceTermsPubcompanyProfileVO>) svcApplDAO.selectSptCustomerServiceTermsPubcompanyProfileList(paramVO);
    }
    
    /**
     * @Method Name        : selectSvcApplCompltList
     * @Method description : [서비스신청완료] 서비스신청완료 목록을 조회한다.
     * @param              : SvcApplVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSvcApplCompltList(SvcApplVO svcApplVO) throws Exception{
     	List<SptCustomerServiceAccountProfileVO> resultList = null;
     	
     	//핀테크 서비스 약관 목록 조회
     	resultList = svcApplDAO.selectSvcApplCompltList(svcApplVO);
     	
     	Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", resultList);
		
		return map;
     }
    
    /**
     * @Method Name        : selectMainSvcAppList
     * @Method description : [메인] 메인의 핀테크 서비스 목록(TOP 5)을 조회한다.
     * @param              : SvcApplVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainSvcAppList(SvcApplVO svcApplVO) throws Exception{
    	List<SvcApplVO> resultList = null;
     	
     	//핀테크 서비스 약관 목록 조회
     	resultList = svcApplDAO.selectMainSvcAppList(svcApplVO);
     	
     	Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", resultList);
		
		return map;
    }
    

    /**
     * @Method Name        : selectFintechSvcList
     * @Method description : [핀테크서비스 선택] 핀테크 서비스 정보 목록을 가져온다.
     * @param              : SvcApplVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
     public Map<String, Object> selectFintechSvcListDev(SvcApplVO svcApplVO, NewMbrRegVO paramVO) throws Exception{
     	List<SptCustomerServiceProfileVO> resultSvcList = null;							//핀테크 서비스 목록
     	List<ComCompanyProfileVO> resultSvcPubcompanyList = null;						//핀테크 서비스 금투사 목록
     	List<SptCustomerServiceAccountProfileVO> resultSvcAccountList = null;			//핀테크 서비스 가상계좌 목록
     	
     	//핀테크 서비스 목록 조회
     	resultSvcList = svcApplDAO.selectFintechSvcList(svcApplVO);
     	if(resultSvcList != null && resultSvcList.size() > 0){
     		List<String> searchAppId = new ArrayList<String>();
     		
     		//조회된 appIds를 셋팅한다.
     		for(int i=0; i<resultSvcList.size(); i++){
     			SptCustomerServiceProfileVO data = (SptCustomerServiceProfileVO) resultSvcList.get(i);
     			searchAppId.add(data.getAppId());
     		}
     		svcApplVO.setSearchAppId(searchAppId);
     		
     		//핀테크 서비스 금투사 목록 조회
     		resultSvcPubcompanyList = svcApplDAO.selectFintechSvcPubcompanyList(svcApplVO);
     		
     		//가상계좌 목록 조회;
     		if(!OppfStringUtil.isEmpty(paramVO.getSearchAppId())){
	     		searchAppId = new ArrayList<String>();
	            searchAppId.add(paramVO.getSearchAppId());
	     		svcApplVO.setSearchAppId(searchAppId);
     		}
     		
         	resultSvcAccountList = svcApplDAO.selectFintechSvcAccountList(svcApplVO);
     	}
     	
     	Map<String, Object> map = new HashMap<String, Object>();
    		map.put("resultSvcList", resultSvcList);
    		map.put("resultSvcPubcompanyList", resultSvcPubcompanyList);
    		map.put("resultSvcAccountList", resultSvcAccountList);
    		
    		return map;
     }

	/**
	 * @Method Name        : selectSvcAppInfo
	 * @Method description : [Oauth] 앱 정보.
	 * @param              : SvcApplVO
	 * @return             : SvcApplVO
	 * @throws             : Exception
	 */
	@Override
	public SvcApplVO selectSvcAppInfo(SvcApplVO svcApplVO) throws Exception {
		return svcApplDAO.selectSvcAppInfo(svcApplVO);
	}

    
    /**
     * @Method Name        : insertFintechSvcDev
     * @Method description : [핀테크서비스 선택] 핀테크 서비스 선택 정보를 등록한다.
     * @param              : SvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int insertFintechSvcDev(SvcApplVO svcApplVO) throws Exception{
        int result = -1;
        
        int cnt = svcApplDAO.selectSptCustomerServiceProfileCnt(svcApplVO);
        
		//일반회원 서비스 프로파일 정보를 등록한다.
        if(cnt < 1){
	 		String serviceRegNo = svcApplDAO.insertSptCustomerServiceProfileDev(svcApplVO);
	 		svcApplVO.setServiceRegNo(serviceRegNo);
	        result = svcApplDAO.insertSptCustomerServiceProfileHistDev(svcApplVO);
        }
       
       return result;
    }

	/**
	 * @Method Name        : selectFintechSvc
	 * @Method description : [핀테크서비스 선택] 핀테크 서비스 선택 여부
	 * @param              : SvcApplVO
	 * @return             : SptCustomerServiceProfileVO
	 * @throws             : Exception
	 */
	@Override
	public SptCustomerServiceProfileVO selectFintechSvc(SvcApplVO svcApplVO) throws Exception {
		return svcApplDAO.selectFintechSvc(svcApplVO);
	}

	/**
	 * @Method Name        : insertSelectFintechSvc
	 * @Method description : [핀테크서비스 선택] 선택하고 들어온 핀테크 서비스 선택 정보를 등록한다.
	 * @param              : SvcApplVO
	 * @return             : void
	 * @throws             : Exception
	 */
	@Override
	@Transactional
	public void insertSelectFintechSvc(SvcApplVO svcApplVO) throws Exception {
		SptCustomerServiceProfileVO vo = new SptCustomerServiceProfileVO();
		vo.setCustomerRegNo(svcApplVO.getCustomerRegNo());
		vo.setCreateId(svcApplVO.getCustomerRegNo());
		vo.setAppId(svcApplVO.getAppId());
		//일반회원 서비스 프로파일 정보를 등록한다.
		String serviceRegNo = svcApplDAO.insertSptCustomerServiceProfile(vo);
		vo.setServiceRegNo(serviceRegNo);

		//일반회원 서비스 프로파일 hist 정보를 등록한다.
		svcApplDAO.insertSptCustomerServiceProfileHist(vo);
	}

	/**
	 * @Method Name        : selectFintechSvcIntAcntAccountList
	 * @Method description : [핀테크서비스 선택] 통합계좌 가상계좌 목록
	 * @param              : SvcApplVO
	 * @return             : List<SptCustomerServiceProfileVO>
	 * @throws             : Exception
	 */
	@Override
	public List<SptCustomerServiceAccountProfileVO> selectFintechSvcIntAcntAccountList(SvcApplVO svcApplVO) throws Exception {
		return svcApplDAO.selectFintechSvcIntAcntAccountList(svcApplVO);
	}

}
