package kr.co.koscom.oppf.cmm.service.impl;

import kr.co.koscom.oppf.apt.sptUsr.service.SptUserManageVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CmmCronScheduleDAO.java
* @Comment  : Cron 스케줄 관련 공통 DAO
* @author   : 이희태
* @since    : 2017.06.19
* @version  : 1.0
* @see
*
*/
@Repository("CmmCronScheduleDAO")
public class CmmCronScheduleDAO extends ComAbstractDAO{

    /**
     * @Method Name        : selectSptUserDeleteList
     * @Method description : 탙퇴한 일반 회원중 발급한 가상계좌가 있는 회원 목록을 조회한다.
     * @return             : List<SptUserManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserManageVO> selectSptUserDeleteList() throws Exception{
        return (List<SptUserManageVO>) list("cmm.CmmCronScheduleDAO.selectSptUserDeleteList");
    }

    /**
     * @Method Name        : selectSptCustomerVerifyProfile
     * @Method description :  [회원가입:인증CI]정보를 조회한다.
     * @return             : SptUserManageVO
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public SptUserManageVO selectSptCustomerVerifyProfile(SptUserManageVO sptUserManageVO) throws Exception{
        return (SptUserManageVO)select("cmm.CmmCronScheduleDAO.selectSptCustomerVerifyProfile", sptUserManageVO);
    }

    /**
     * @Method Name        : updateSptUserManageDtl
     * @Method description : 일반회원정보 수정
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptUserWithdrawal(SptUserManageVO sptUserManageVO) throws Exception{
        return update("cmm.CmmCronScheduleDAO.updateSptUserWithdrawal", sptUserManageVO);
    }

    /**
     * @Method Name        : selectGuestMemberExpireList
     * @Method description : 비회원 인증 유효기만 만료 안내 메일 대상 리스트 3/1개월
     * @return             : List<SptUserManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserManageVO> selectGuestMemberExpireList() throws Exception{
        return (List<SptUserManageVO>) list("cmm.CmmCronScheduleDAO.selectGuestMemberExpireList");
    }

    /**
     * @Method Name        : selectGuestMemberAppList
     * @Method description : 비회원 이용중인 앱 리스트
     * @return             : List<SptUserManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserManageVO> selectGuestMemberAppList(SptUserManageVO sptUserManageVO) throws Exception{
        return (List<SptUserManageVO>) list("cmm.CmmCronScheduleDAO.selectGuestMemberAppList", sptUserManageVO);
    }

    /**
     * @Method Name        : selectDeleteGuestMemberList
     * @Method description : 비회원 가동의서 만료 후 자동 탈퇴 처리
     * @return             : List<SptUserManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserManageVO> selectDeleteGuestMemberList() throws Exception{
        return (List<SptUserManageVO>) list("cmm.CmmCronScheduleDAO.selectDeleteGuestMemberList");
    }

    /**
     * @Method Name        : insertLeaveGuestMemberInfo
     * @Method description : 비회원 정보 탈퇴 처리를 위한 데이터 이관
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public Object insertLeaveGuestMemberInfo(SptUserManageVO sptUserManageVO) throws Exception{
        return insert("cmm.CmmCronScheduleDAO.insertLeaveGuestMemberInfo", sptUserManageVO);
    }

    /**
     * @Method Name        : updateGuestMemberInfo
     * @Method description : 비회원 정보 탈퇴 업데이트 처리
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateGuestMemberInfo(SptUserManageVO sptUserManageVO) throws Exception{
        return update("cmm.CmmCronScheduleDAO.updateGuestMemberInfo", sptUserManageVO);
    }

    /**
     * @Method Name        : insertGuestMemberInfoHistory
     * @Method description : 비회원 정보 탈퇴 히스토리 업데이트 처리
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public Object insertGuestMemberInfoHistory(SptUserManageVO sptUserManageVO) throws Exception{
        return insert("cmm.CmmCronScheduleDAO.insertGuestMemberInfoHistory", sptUserManageVO);
    }

    /**
     * @Method Name        : insertLeaveGuestMemberVerifyInfo
     * @Method description : 비회원 Verify 정보 탈퇴 처리를 위한 데이터 이관
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public Object insertLeaveGuestMemberVerifyInfo(SptUserManageVO sptUserManageVO) throws Exception{
        return insert("cmm.CmmCronScheduleDAO.insertLeaveGuestMemberVerifyInfo", sptUserManageVO);
    }

    /**
     * @Method Name        : updateMemberVerifyInfo
     * @Method description : 회원 Verify 정보 초기화 업데이트 [탈퇴처리]
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateMemberVerifyInfo(SptUserManageVO sptUserManageVO) throws Exception{
        return update("cmm.CmmCronScheduleDAO.updateMemberVerifyInfo", sptUserManageVO);
    }

    /**
     * @Method Name        : updateMemberVerifyInfoHist
     * @Method description : 회원 Verify Hist 정보 초기화 업데이트 [탈퇴처리]
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateMemberVerifyInfoHist(SptUserManageVO sptUserManageVO) throws Exception{
        return update("cmm.CmmCronScheduleDAO.updateMemberVerifyInfoHist", sptUserManageVO);
    }

    /**
     * @Method Name        : updateMemberVerifyInfo
     * @Method description : 탈퇴회원 정보 초기화 업데이트
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateMemberInfo(SptUserManageVO sptUserManageVO) throws Exception{
        return update("cmm.CmmCronScheduleDAO.updateMemberInfo", sptUserManageVO);
    }

    /**
     * @Method Name        : updateMemberInfoHist
     * @Method description : 탈퇴회원 Hist 정보 초기화 업데이트
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateMemberInfoHist(SptUserManageVO sptUserManageVO) throws Exception{
        return update("cmm.CmmCronScheduleDAO.updateMemberInfoHist", sptUserManageVO);
    }

    /**
     * @Method Name        : selectDeleteMemberList
     * @Method description : 회원 탈퇴 3개월 물리 데이터 삭제 대상 리스트
     * @return             : List<SptUserManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserManageVO> selectDeleteMemberList() throws Exception{
        return (List<SptUserManageVO>) list("cmm.CmmCronScheduleDAO.selectDeleteMemberList");
    }

    /**
     * @Method Name        : deleteMemberVerifyInfoHist
     * @Method description : 탈퇴회원 Verify Hist 테이블 삭제
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteMemberVerifyInfoHist(SptUserManageVO sptUserManageVO) throws Exception{
        return delete("cmm.CmmCronScheduleDAO.deleteMemberVerifyInfoHist", sptUserManageVO);
    }

    /**
     * @Method Name        : deleteMemberVerifyInfo
     * @Method description : 탈퇴회원 Verify 테이블 삭제
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteMemberVerifyInfo(SptUserManageVO sptUserManageVO) throws Exception{
        return delete("cmm.CmmCronScheduleDAO.deleteMemberVerifyInfo", sptUserManageVO);
    }

    /**
     * @Method Name        : deleteMemberInfoHist
     * @Method description : 탈퇴회원 Hist 테이블 삭제
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteMemberInfoHist(SptUserManageVO sptUserManageVO) throws Exception{
        return delete("cmm.CmmCronScheduleDAO.deleteMemberInfoHist", sptUserManageVO);
    }

    /**
     * @Method Name        : deleteMemberInfo
     * @Method description : 탈퇴회원 테이블 삭제
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteMemberInfo(SptUserManageVO sptUserManageVO) throws Exception{
        return delete("cmm.CmmCronScheduleDAO.deleteMemberInfo", sptUserManageVO);
    }

}
