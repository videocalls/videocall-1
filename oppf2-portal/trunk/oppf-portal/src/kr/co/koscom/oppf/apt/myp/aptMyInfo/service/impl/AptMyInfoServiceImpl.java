package kr.co.koscom.oppf.apt.myp.aptMyInfo.service.impl;

import kr.co.koscom.oppf.apt.myp.aptMyInfo.service.AptMyInfoService;
import kr.co.koscom.oppf.apt.myp.aptMyInfo.service.AptMyInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptMyInfoServiceImpl.java
* @Comment  : [개인회원정보]정보관리를 위한 Service 클래스
* @author   : 포털 유제량
* @since    : 2016.06.28
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.28  유제량        최초생성
*
*/
@Slf4j
@Service("AptMyInfoService")
public class AptMyInfoServiceImpl implements AptMyInfoService{
    
    @Resource(name = "AptMyInfoDAO")
    private AptMyInfoDAO aptMyInfoDAO;
    
    /**
     * @Method Name        : selectCheckPw
     * @Method description : [개인회원정보:ID중복확인]ID정보를 조회한다.
     * @param              : String
     * @return             : String
     * @throws             : Exception
     */
    @Transactional
    public String selectCheckPw(AptMyInfoVO aptMyInfoVO) throws Exception{
        log.info("------------- selectCheckPw START ------------------------");
        String rs = aptMyInfoDAO.selectCheckPw(aptMyInfoVO);
        log.info("------------- selectCheckPw END --------------------------");
        return rs;
    }
    
    /**
     * @Method Name        : updateAptMyPwMod
     * @Method description : [개인회원정보:비밀번호변경]을 한다.
     * @param              : AptMyInfoVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateAptMyPwMod(AptMyInfoVO aptMyInfoVO) throws Exception{
        log.info("------------- updateAptMyPwMod START ------------------------");
        //1.기본정보 DB등록
        int rs = aptMyInfoDAO.updateAptMyPwMod(aptMyInfoVO);
        
        //2.기본정보hist DB등록
        aptMyInfoDAO.insertAptMyPwMod(aptMyInfoVO);
        log.info("------------- updateAptMyPwMod END --------------------------");
        return rs;
    }
    
}
