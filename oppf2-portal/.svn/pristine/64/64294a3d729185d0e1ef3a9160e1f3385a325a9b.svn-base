package kr.co.koscom.oppf.spt.myp.cert.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.spt.myp.cert.service.CertService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.impl.MbrRegDAO;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : MbrRegServiceImpl.java
* @Comment  : [마이페이지:공인인증서]정보관리를 위한 Service 클래스
* @author   : 포털 이환덕
* @since    : 2016.05.30
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.30  이환덕        최초생성
*
*/
@Service("CertService")
public class CertServiceImpl implements CertService{
    
    @Resource(name = "MbrRegService")
    private MbrRegService mbrRegService;
    
    @Resource(name = "MbrRegDAO")
    private MbrRegDAO mbrRegDAO;
    
    
    private static final Logger log = Logger.getLogger(CertServiceImpl.class);
    
    /**
     * @Method Name        : updateSptCustomerVerifyAndTermsInfo
     * @Method description : [마이페이지:공인인증서+약관]정보를 수정을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateSptCustomerVerifyAndTermsInfo(MbrRegVO paramVO) throws Exception{
        int rs = 0;
        
        //1.약관정보수정&약관Hist정보등록
        log.debug("1.약관정보수정&약관Hist정보등록前:paramVO.getCustomerTermsList="+paramVO.getCustomerTermsList());
        int rs1 = mbrRegService.updateSptCustomerTermsProfile(paramVO);
        log.debug("1.약관정보수정&약관Hist정보등록:rs1="+rs1);
        
        //2.인증정보수정
        int rs2 = mbrRegDAO.updateSptCustomerVerifyProfile(paramVO);
        log.debug("2.인증정보수정:rs2="+rs2);
        
        //3.인증정보Hist등록
        int rs3 = mbrRegDAO.insertSptCustomerVerifyProfileHist(paramVO);
        log.debug("3.인증정보Hist등록:rs3="+rs3);
        
        //5.결과리턴
        rs = rs1 + rs2 + rs3;
        log.debug("5.결과리턴:rs="+rs);
        return rs;
    }
    
}
