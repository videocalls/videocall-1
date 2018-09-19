package kr.co.koscom.oppf.cmm.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.cmm.faq.service.FaqManageVO;
import kr.co.koscom.oppf.cmm.service.CmmEmailCronSvcTermsVO;
import kr.co.koscom.oppf.cmm.service.CmmEmailSendVO;

/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CmmEmailSendDAO.java
* @Comment  : 공통 이메일발송 기능을 제공하는 DAO
* @author   : 유제량
* @since    : 2016.05.25
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.25  유제량        최초생성
*
*/
@Repository("CmmEmailSendDAO")
public class CmmEmailSendDAO extends ComAbstractDAO{
    
    private static final Logger log = Logger.getLogger(CmmEmailSendDAO.class);
    
    /**
     * @Method Name        : selectCmmComEmailSendInfo
     * @Method description : 이메일 템플릿을 조회해온다.
     * @param              : CmmEmailSendVO
     * @return             : CmmEmailSendVO
     * @throws             : Exception
     */
    public CmmEmailSendVO selectCmmComEmailSendInfo(CmmEmailSendVO cmmEmailSendVO) throws Exception{        
        return (CmmEmailSendVO) select("cmm.CmmEmailSendDAO.selectCmmComEmailSendInfo", cmmEmailSendVO);
    }
    
    /**
     * @Method Name        : insertCmmComEmailSendInfo
     * @Method description : com_email_send_info(이메일발송 정보) 로그적재
     * @param              : CmmEmailSendVO
     * @return             : String
     * @throws             : Exception
     */
    public String insertCmmComEmailSendInfo(CmmEmailSendVO cmmEmailSendVO) throws Exception{        
        return (String) insert("cmm.CmmEmailSendDAO.insertCmmComEmailSendInfo", cmmEmailSendVO);
    }
    
    /**
     * @Method Name        : updateCmmComEmailSendInfo
     * @Method description : com_email_send_info(이메일발송 정보) 로그갱신
     * @param              : CmmEmailSendVO
     * @return             : String
     * @throws             : Exception
     */
    public int updateCmmComEmailSendInfo(CmmEmailSendVO cmmEmailSendVO) throws Exception{
        return (Integer) update("cmm.CmmEmailSendDAO.updateCmmComEmailSendInfo", cmmEmailSendVO);
    }
    
    /**
     * @Method Name        : selectCmmComEmailSendInfoChk
     * @Method description : 이메일 발송이력을 조회해온다.
     * @param              : CmmEmailSendVO
     * @return             : String
     * @throws             : Exception
     */
    public String selectCmmComEmailSendInfoChk(CmmEmailSendVO cmmEmailSendVO) throws Exception{        
        return (String) select("cmm.CmmEmailSendDAO.selectCmmComEmailSendInfoChk", cmmEmailSendVO);
    }
    
    /**
     * @Method Name        : selectCmmComEmailTermsList
     * @Method description : 고객의 정보제공동의 정보를 조회한다.
     * @param              : CmmEmailSendVO
     * @return             : CmmEmailSendVO
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CmmEmailSendVO> selectCmmComEmailTermsList(CmmEmailSendVO cmmEmailSendVO) throws Exception{  
    	//System.out.println("-------------------------------------------------------------");
		//System.out.println("CmmTsaServiceImpl.CmmEmailSendDAO.setTermsRegNo()="+cmmEmailSendVO.getTermsRegNo());
		//System.out.println("-------------------------------------------------------------");
        return (List<CmmEmailSendVO>) list("cmm.CmmEmailSendDAO.selectCmmComEmailTermsList", cmmEmailSendVO);
    }
    
    /**
     * @Method Name        : selectCmmEmailCustomerInfo
     * @Method description : 사용자 정보를 조회한다.
     * @param              : CmmEmailSendVO
     * @return             : CmmEmailSendVO
     * @throws             : Exception
     */
    public CmmEmailSendVO selectCmmEmailCustomerInfo(CmmEmailSendVO cmmEmailSendVO) throws Exception{  
        return (CmmEmailSendVO) select("cmm.CmmEmailSendDAO.selectCmmEmailCustomerInfo", cmmEmailSendVO);
    }
    
    /**
     * @Method Name        : selectCronSvcTermsList
     * @Method description : 정보제공동의 메일발송 대상 목록 조회
     * @param              : CmmEmailSendVO
     * @return             : List<CmmEmailCronSvcTermsVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CmmEmailCronSvcTermsVO> selectCronSvcTermsList(CmmEmailCronSvcTermsVO cmmEmailCronSvcTermsVO) throws Exception{  
        return (List<CmmEmailCronSvcTermsVO>) list("cmm.CmmEmailSendDAO.selectCronSvcTermsList", cmmEmailCronSvcTermsVO);
    }
}
