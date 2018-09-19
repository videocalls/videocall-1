package kr.co.koscom.oppf.apt.sptUsr.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.apt.sptUsr.service.SptUserAccountVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptUserAccountDAO.java
* @Comment  : 일반회원 가상계좌 관리 DAO
* @author   : 신동진
* @since    : 2016.06.30
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.30  신동진        최초생성
*
*/
@Repository("SptUserAccountDAO")
public class SptUserAccountDAO extends ComAbstractDAO{
    
    private static final Logger log = Logger.getLogger(SptUserAccountDAO.class);
        
    /**
     * @Method Name        : selectSptUserServiceList
     * @Method description : 가상계좌 목록 조회
     * @param              : SptUserAccountVO
     * @return             : List<SptUserAccountVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserAccountVO> selectSptUserAccountList(SptUserAccountVO sptUserAccountVO) throws Exception{
        return (List<SptUserAccountVO>) list("apt.SptUserAccountDAO.selectSptUserAccountList", sptUserAccountVO);
    }
    
    /**
     * @Method Name        : selectSptUserServiceListCnt
     * @Method description : 가상계좌 목록의 total cnt를 조회한다.
     * @param              : SptUserAccountVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectSptUserAccountListCnt(SptUserAccountVO sptUserAccountVO) throws Exception{
        return (Integer) select("apt.SptUserAccountDAO.selectSptUserAccountListCnt", sptUserAccountVO);
    }
    
    /**
     * @Method Name        : selectSptUserServiceListExcel
     * @Method description : 가상계좌 excel 목록 조회
     * @param              : SptUserAccountVO
     * @return             : List<SptUserAccountVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserAccountVO> selectSptUserAccountListExcel(SptUserAccountVO sptUserAccountVO) throws Exception{
        return (List<SptUserAccountVO>) list("apt.SptUserAccountDAO.selectSptUserAccountListExcel", sptUserAccountVO);
    }
}
