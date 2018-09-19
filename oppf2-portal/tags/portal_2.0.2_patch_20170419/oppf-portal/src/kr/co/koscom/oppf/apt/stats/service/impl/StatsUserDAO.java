package kr.co.koscom.oppf.apt.stats.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.apt.stats.service.StatsUserVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : StatsUserDAO.java
* @Comment  : 회원 통계 관리를 위한 위한 DAO
* @author   : 신동진
* @since    : 2016.07.10
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.07.10  신동진        최초생성
*
*/
@Repository("StatsUserDAO")
public class StatsUserDAO extends ComAbstractDAO{
    
    private static final Logger log = Logger.getLogger(StatsUserDAO.class);
    
    /**
     * @Method Name        : selectStatsUserSearchDate
     * @Method description : 회원 통계 목록의 기준일을 조회한다.
     * @param              : StatsUserVO
     * @return             : List<StatsUserVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<StatsUserVO> selectStatsUserSearchDate(StatsUserVO statsUserVO) throws Exception{
        return (List<StatsUserVO>) list("apt.StatsUserDAO.selectStatsUserSearchDate", statsUserVO);
    }
        
    /**
     * @Method Name        : selectStatsUserList
     * @Method description : 회원 통계 목록을 조회한다.
     * @param              : StatsUserVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectStatsUserList(StatsUserVO statsUserVO) throws Exception{
        return (List<HashMap>) list("apt.StatsUserDAO.selectStatsUserList", statsUserVO);
    }

}
