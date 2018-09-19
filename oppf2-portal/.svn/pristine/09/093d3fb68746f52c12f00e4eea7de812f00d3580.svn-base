package kr.co.koscom.oppf.spt.myp.sptMyInfo.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.myp.sptMyInfo.service.SptMyInfoService;
import kr.co.koscom.oppf.spt.myp.sptMyInfo.service.SptMyInfoVO;
import kr.co.koscom.oppf.spt.myp.sptMyInfo.web.SptMyInfoController;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptMyInfoServiceImpl.java
* @Comment  : [개인회원정보]정보관리를 위한 Service 클래스
* @author   : 포털 유제량
* @since    : 2016.05.09
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.09  유제량        최초생성
*
*/
@Service("SptMyInfoService")
public class SptMyInfoServiceImpl implements SptMyInfoService{
    
    @Resource(name = "SptMyInfoDAO")
    private SptMyInfoDAO sptMyInfoDAO;
    
    private static final Logger log = Logger.getLogger(SptMyInfoController.class);
    
    /**
     * @Method Name        : selectSptMyInfo
     * @Method description : [개인회원정보상세:상세]정보를 조회한다.
     * @param              : SptMyInfoVO
     * @return             : SptMyInfoVO
     * @throws             : Exception
     */
    @Transactional
    public SptMyInfoVO selectSptMyInfo(SptMyInfoVO sptMyInfoVO) throws Exception{   
        log.info("------------- selectSptMyInfo START ------------------------");
        SptMyInfoVO rs = sptMyInfoDAO.selectSptMyInfo(sptMyInfoVO);
        log.info("------------- selectSptMyInfo END --------------------------");
        return rs;
    }
    
    /**
     * @Method Name        : selectCheckPw
     * @Method description : [개인회원정보:ID중복확인]ID정보를 조회한다.
     * @param              : String
     * @return             : String
     * @throws             : Exception
     */
    @Transactional
    public String selectCheckPw(SptMyInfoVO sptMyInfoVO) throws Exception{
        log.info("------------- selectCheckPw START ------------------------");
        String rs = sptMyInfoDAO.selectCheckPw(sptMyInfoVO);
        log.info("------------- selectCheckPw END --------------------------");
        return rs;
    }
    
    /**
     * @Method Name        : updateSptMyInfo
     * @Method description : [개인회원정보:수정]을 한다.
     * @param              : SptMyInfoVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateSptMyInfo(SptMyInfoVO sptMyInfoVO) throws Exception{
        log.info("------------- updateSptMyInfo START ------------------------");
        //1.기본정보 DB등록
        int rs = sptMyInfoDAO.updateSptMyInfo(sptMyInfoVO);
        
        //2.기본정보hist DB등록
        sptMyInfoDAO.insertSptMyPwMod(sptMyInfoVO);
        
        
        log.info("------------- updateSptMyInfo END --------------------------");
        return rs;
    }
    
    /**
     * @Method Name        : updateSptMyPwMod
     * @Method description : [개인회원정보:비밀번호변경]을 한다.
     * @param              : SptMyInfoVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateSptMyPwMod(SptMyInfoVO sptMyInfoVO) throws Exception{
        log.info("------------- updateSptMyPwMod START ------------------------");
        //1.기본정보 DB등록
        int rs = sptMyInfoDAO.updateSptMyPwMod(sptMyInfoVO);
        
        //2.기본정보hist DB등록
        sptMyInfoDAO.insertSptMyPwMod(sptMyInfoVO);
        log.info("------------- updateSptMyPwMod END --------------------------");
        return rs;
    }
    
    /**
     * @Method Name        : updateSptMbrSecesInfo
     * @Method description : [개인회원정보:회원탈퇴]를 한다.
     * @param              : SptMyInfoVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateSptMbrSecesInfo(SptMyInfoVO sptMyInfoVO) throws Exception{
        log.info("------------- updateSptMbrSecesInfo START ------------------------");
        //1.기본정보 DB등록
        int rs = sptMyInfoDAO.updateSptMbrSecesInfo(sptMyInfoVO);
        
        //2.기본정보hist DB등록
        sptMyInfoDAO.insertSptMyPwMod(sptMyInfoVO);
        log.info("------------- updateSptMbrSecesInfo END --------------------------");
        return rs;
    }
    
    /**
     * @Method Name        : selectSptCustomerInfoProfile
     * @Method description : [개인회원정보:기본]정보를 조회한다.
     * @param              : SptMyInfoVO
     * @return             : SptMyInfoVO
     * @throws             : Exception
     */
    @Transactional
    public SptMyInfoVO selectSptCustomerInfoProfile(SptMyInfoVO paramVO) throws Exception{
        return (SptMyInfoVO) sptMyInfoDAO.selectSptCustomerInfoProfile(paramVO);
    }
    
    /**
     * @Method Name        : selectSptCustomerProfile
     * @Method description : [개인회원정보상세:상세]정보를 조회한다.(탈퇴메일발송를 위한 정보를 조회해 온다.)
     * @param              : SptMyInfoVO
     * @return             : SptMyInfoVO
     * @throws             : Exception
     */
    @Transactional
    public SptMyInfoVO selectSptCustomerProfile(SptMyInfoVO paramVO) throws Exception{   
        return (SptMyInfoVO) sptMyInfoDAO.selectSptCustomerProfile(paramVO);
    }
    
}
