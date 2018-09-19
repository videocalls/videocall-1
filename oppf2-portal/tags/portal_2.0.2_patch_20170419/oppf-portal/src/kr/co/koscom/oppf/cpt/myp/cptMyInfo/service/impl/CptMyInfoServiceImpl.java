package kr.co.koscom.oppf.cpt.myp.cptMyInfo.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.cpt.myp.cptMyInfo.service.CptMyInfoService;
import kr.co.koscom.oppf.cpt.myp.cptMyInfo.service.CptMyInfoVO;
import kr.co.koscom.oppf.cpt.myp.cptMyInfo.web.CptMyInfoController;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptMyInfoServiceImpl.java
* @Comment  : [기업회원정보]정보관리를 위한 Service 클래스
* @author   : 포털 유제량
* @since    : 2016.06.29
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.29  유제량        최초생성
*
*/
@Service("CptMyInfoService")
public class CptMyInfoServiceImpl implements CptMyInfoService{
    
    @Resource(name = "CptMyInfoDAO")
    private CptMyInfoDAO cptMyInfoDAO;
    
    private static final Logger log = Logger.getLogger(CptMyInfoController.class);
    
    /**
     * @Method Name        : selectCptMyInfo
     * @Method description : [기업회원정보상세:상세]정보를 조회한다.
     * @param              : CptMyInfoVO
     * @return             : CptMyInfoVO
     * @throws             : Exception
     */
    @Transactional
    public CptMyInfoVO selectCptMyInfo(CptMyInfoVO cptMyInfoVO) throws Exception{   
        log.info("------------- selectCptMyInfo START ------------------------");
        CptMyInfoVO rs = cptMyInfoDAO.selectCptMyInfo(cptMyInfoVO);
        log.info("------------- selectCptMyInfo END --------------------------");
        return rs;
    }
    
    /**
     * @Method Name        : selectCheckPw
     * @Method description : [기업회원정보:ID중복확인]ID정보를 조회한다.
     * @param              : String
     * @return             : String
     * @throws             : Exception
     */
    @Transactional
    public String selectCheckPw(CptMyInfoVO cptMyInfoVO) throws Exception{
        log.info("------------- selectCheckPw START ------------------------");
        String rs = cptMyInfoDAO.selectCheckPw(cptMyInfoVO);
        log.info("------------- selectCheckPw END --------------------------");
        return rs;
    }
    
    /**
     * @Method Name        : updateCptMyInfo
     * @Method description : [기업회원정보:수정]을 한다.
     * @param              : CptMyInfoVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateCptMyInfo(CptMyInfoVO cptMyInfoVO) throws Exception{
        log.info("------------- updateCptMyInfo START ------------------------");
        //1.기본정보 DB등록
        int rs = cptMyInfoDAO.updateCptMyInfo(cptMyInfoVO);
        
        //2.기본정보hist DB등록
        cptMyInfoDAO.insertCptMyPwMod(cptMyInfoVO);
        
        //기업정보가 있으면 기업정보도 수정해준다.
        if(
        	!OppfStringUtil.isEmpty(cptMyInfoVO.getCompanyBizregNo()) ||
        	!OppfStringUtil.isEmpty(cptMyInfoVO.getCeoName()) ||
        	!OppfStringUtil.isEmpty(cptMyInfoVO.getCompanyPostNo()) ||
        	!OppfStringUtil.isEmpty(cptMyInfoVO.getCompanyAddress())
        ){
        	rs = cptMyInfoDAO.updateCptMyInfoCompanyProfile(cptMyInfoVO);
        	rs = cptMyInfoDAO.insertCptMyInfoCompanyProfileHist(cptMyInfoVO);
        }  
        
        
        log.info("------------- updateCptMyInfo END --------------------------");
        return rs;
    }
    
    /**
     * @Method Name        : updateCptMyPwMod
     * @Method description : [기업회원정보:비밀번호변경]을 한다.
     * @param              : CptMyInfoVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateCptMyPwMod(CptMyInfoVO cptMyInfoVO) throws Exception{
        log.info("------------- updateCptMyPwMod START ------------------------");
        //1.기본정보 DB등록
        int rs = cptMyInfoDAO.updateCptMyPwMod(cptMyInfoVO);
        
        //2.기본정보hist DB등록
        cptMyInfoDAO.insertCptMyPwMod(cptMyInfoVO);
        log.info("------------- updateCptMyPwMod END --------------------------");
        return rs;
    }
    
    /**
     * @Method Name        : selectCptOperatorInfoProfile
     * @Method description : [기업회원정보:기본]정보를 조회한다.
     * @param              : CptMyInfoVO
     * @return             : CptMyInfoVO
     * @throws             : Exception
     */
    @Transactional
    public CptMyInfoVO selectCptOperatorInfoProfile(CptMyInfoVO paramVO) throws Exception{
        return (CptMyInfoVO) cptMyInfoDAO.selectCptOperatorInfoProfile(paramVO);
    }
    
}
