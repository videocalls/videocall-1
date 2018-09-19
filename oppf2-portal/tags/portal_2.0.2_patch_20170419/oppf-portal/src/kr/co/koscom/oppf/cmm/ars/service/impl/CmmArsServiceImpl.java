package kr.co.koscom.oppf.cmm.ars.service.impl;

import kr.co.koscom.oppf.cmm.IntegratedAccount.service.CmmSIFInternalService;
import kr.co.koscom.oppf.cmm.ars.service.CmmArsService;
import kr.co.koscom.oppf.cmm.ars.service.CmmArsVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceTermsProfileVO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축 2차
* @FileName : CmmArsServiceImpl.java
* @Comment  : [공통회원동의서ARS연계]정보관리를 위한 Service 클래스
* @author   : 포털 이희태
* @since    : 2017.03.04
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2017.03.04  이희태        최초생성
*
*/
@Service("CmmArsService")
public class CmmArsServiceImpl implements CmmArsService{
    
    @Resource(name = "CmmArsDAO")
    private CmmArsDAO cmmArsDAO;
    @Resource(name = "CmmSIFInternalService")
    private CmmSIFInternalService cmmSIFInternalService;

    private static final Logger log = Logger.getLogger(CmmArsServiceImpl.class);

   /**
     * @Method Name        : procArs
     * @Method description : ARS처리
     * @param              : CmmArsVO
     * @return             : HashMap<String,Object>
     * @throws             : Exception
     */
    @Override
    @Transactional
    public HashMap<String, Object> procArs(CmmArsVO paramVO)throws Exception{
        HashMap<String, Object> rsMap = new HashMap<String, Object>();

        //약관상태값 DB수정
        SptCustomerServiceTermsProfileVO pScstpVO = new SptCustomerServiceTermsProfileVO();
        //약관상태값 DB수정前값세팅
        pScstpVO.setCustomerRegNo(paramVO.getCustomerRegNo());
        pScstpVO.setTermsRegNo(paramVO.getTermsRegNo());
        pScstpVO.setTermsStartDate(paramVO.getTermsStartDate());
        pScstpVO.setTermsPolicy(paramVO.getTermsPolicy());
        pScstpVO.setTermsAuthYn("N");
        //약관상태값 DB수정
        cmmArsDAO.updateSptCustomerServiceTermsProfile(pScstpVO);
        cmmArsDAO.insertSptCustomerServiceTermsProfileHist(pScstpVO);
        log.debug("약관상태값 DB수정後:termsRs="+pScstpVO);
        //ARS 인증값 DB 저장
        String termsArsRegNo =  cmmArsDAO.selectTermsArsRegNo(paramVO);
        paramVO.setTermsArsRegNo(termsArsRegNo);
        paramVO.setCustomerPhone(paramVO.getCustomerPhone());
        cmmArsDAO.insertSptCustomerServiceArsProfile(paramVO);
        cmmArsDAO.insertSptCustomerServiceArsProfileHist(paramVO);
        log.debug("ARS DB등록後:ars="+paramVO);
        cmmSIFInternalService.sendServiceTerms(paramVO.getCustomerId(), paramVO.getTermsRegNo());
        log.debug("금융정보제공 동의서 연계서버 API연동");
        //메세지 설정[성공]
        rsMap.put("rsCd", "00");
        rsMap.put("rsCdMsg", "ARS 작업이 정상적으로 완료 되었습니다.");
        return rsMap;
    }

    /**
     * @Method Name        : selectTermsArsRecordData
     * @Method description : ARS녹취 데이터 조회
     * @param              : String
     * @return             : String
     * @throws             : Exception
     */
    @Override
    public String selectTermsArsRecordData(String termsRegNo) throws Exception {
        return cmmArsDAO.selectTermsArsRecordData(termsRegNo);
    }
}
