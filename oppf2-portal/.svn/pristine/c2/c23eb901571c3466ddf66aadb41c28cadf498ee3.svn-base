package kr.co.koscom.oppf.apt.cmm.service.impl;

import kr.co.koscom.oppf.apt.cmm.service.AptMainVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptMainDAO.java
* @Comment  : admin main 관리를 위한 위한 DAO
* @author   : 신동진
* @since    : 2016.07.15
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.07.15  신동진        최초생성
*
*/
@Repository("AptMainDAO")
public class AptMainDAO extends ComAbstractDAO{
    
    private static final Logger log = Logger.getLogger(AptMainDAO.class);
    
    /************************************************************************************************
	 * API 트래픽 현황 start
	 ***********************************************************************************************/
    
    /**
     * @Method Name        : selectMainStatsTraffic
     * @Method description : API 트래픽 요약 - 전체건수 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsTraffic(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsTraffic", aptMainVO);
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficDtl
     * @Method description : API 트래픽 요약 - 상세건수 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsTrafficDtl(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsTrafficDtl", aptMainVO);
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficApiTraffic
     * @Method description : API 서비스별 트래픽 현황(요약) 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsTrafficApiTraffic(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsTrafficApiTraffic", aptMainVO);
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficServiceTraffic
     * @Method description : 서비스 제동자별 트래픽 현황(요약) 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsTrafficServiceTraffic(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsTrafficServiceTraffic", aptMainVO);
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficAppTraffic
     * @Method description : 핀테크 앱 별 트래픽 현황(요약) 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsTrafficAppTraffic(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsTrafficAppTraffic", aptMainVO);
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficPlanTraffic
     * @Method description : 사용 Plan 별 트래픽 현황(요약) 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsTrafficPlanTraffic(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsTrafficPlanTraffic", aptMainVO);
    }
    
    /************************************************************************************************
	 * API 트래픽 현황 end
	 ***********************************************************************************************/
    
    /************************************************************************************************
	 * API 트래픽 상세 현황 start
	 ***********************************************************************************************/
    /**
     * @Method Name        : selectMainStatsTrafficDtlReqCnt
     * @Method description : API 트래픽 요약 - 요청 Count 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsTrafficDtlReqCnt(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsTrafficDtlReqCnt", aptMainVO);
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficDtlAvgTime
     * @Method description : API 트래픽 요약 - 평균 Time 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsTrafficDtlAvgTime(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsTrafficDtlAvgTime", aptMainVO);
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficDtlAccList
     * @Method description : API 트래픽 현황 - 누적 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsTrafficDtlAccList(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsTrafficDtlAccList", aptMainVO);
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficDtlSvcCategoryList
     * @Method description : API 트래픽 현황 - 개별(API 서비스 구분) 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsTrafficDtlSvcCategoryList(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsTrafficDtlSvcCategoryList", aptMainVO);
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficDtlSvcSubcompanyList
     * @Method description : API 트래픽 현황 - 개별(API 서비스  제공자) 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsTrafficDtlSvcSubcompanyList(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsTrafficDtlSvcSubcompanyList", aptMainVO);
    }
    
    /**
     * @Method Name        : selectMainStatsTrafficDtlSvcPubcompanyList
     * @Method description : API 트래픽 현황 - 개별(핀테크 앱) 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsTrafficDtlSvcPubcompanyList(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsTrafficDtlSvcPubcompanyList", aptMainVO);
    }
    /************************************************************************************************
	 * API 트래픽 상세 현황 end 
	 ***********************************************************************************************/
    
    /************************************************************************************************
	 * 회원가입 및 서비스 연결 현황 start
	 ***********************************************************************************************/
    
    /**
     * @Method Name        : selectMainStatsServiceUserService
     * @Method description : 회원 가입 및 서비스 연결 현황 요약 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsServiceUserService(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsServiceUserService", aptMainVO);
    }
    
    /**
     * @Method Name        : selectMainStatsServiceUserAccount
     * @Method description : 가상계좌 발급 회원 현황(요약) 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsServiceUserAccount(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsServiceUserAccount", aptMainVO);
    }
    
    /**
     * @Method Name        : selectMainStatsServiceUserApp
     * @Method description : 핀테크 앱 연결 회원 현황(요약) 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsServiceUserApp(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsServiceUserApp", aptMainVO);
    }
    
    /**
     * @Method Name        : selectMainStatsServiceSptUserList
     * @Method description : 금융투자 핀테크 포털 회원 가입 현황 요약 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsServiceSptUserList(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsServiceSptUserList", aptMainVO);
    }
    
    /**
     * @Method Name        : selectMainStatsServiceCptUserList
     * @Method description : 기업/금투사 포털 회원 가입 현황 요약 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsServiceCptUserList(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsServiceCptUserList", aptMainVO);
    }
    
    /**
     * @Method Name        : selectMainStatsServiceUserAccountList
     * @Method description : 금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 가상계좌 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsServiceUserAccountList(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsServiceUserAccountList", aptMainVO);
    }
    
    /**
     * @Method Name        : selectMainStatsServiceUserAccountCompanyList
     * @Method description : 금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 서비스 제공자 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsServiceUserAccountCompanyList(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsServiceUserAccountCompanyList", aptMainVO);
    }
    
    /**
     * @Method Name        : selectMainStatsServiceUserServiceAppList
     * @Method description : 금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 앱 개발자 조회
     * @param              : AptMainVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectMainStatsServiceUserServiceAppList(AptMainVO aptMainVO) throws Exception{
        return (List<HashMap>) list("apt.AptMainDAO.selectMainStatsServiceUserServiceAppList", aptMainVO);
    }
    
    /************************************************************************************************
	 * 회원가입 및 서비스 연결 현황 end
	 ***********************************************************************************************/

}
