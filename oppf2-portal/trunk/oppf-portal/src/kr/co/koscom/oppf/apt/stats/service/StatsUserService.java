package kr.co.koscom.oppf.apt.stats.service;

import java.util.List;
import java.util.Map;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : StatsUserService.java
* @Comment  : 회원 통계 관리를 위한 위한 Service
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
public interface StatsUserService{
	
    /**
     * @Method Name        : selectStatsUserList
     * @Method description : 회원 통계 목록을 조회한다.
     * @param              : StatsUserVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectStatsUserList(StatsUserVO statsUserVO) throws Exception;
        
    
}
