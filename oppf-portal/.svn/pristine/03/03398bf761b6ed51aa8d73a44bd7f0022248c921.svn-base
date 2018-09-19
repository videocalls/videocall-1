package kr.co.koscom.oppf.apt.dataSet.service.impl;

import kr.co.koscom.oppf.apt.dataSet.service.*;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LSH on 2017. 2. 21..
 */
@Repository("DataSetManageDAO")
public class DataSetManageDAO  extends ComAbstractDAO {

    public List<DataSetManageVO> selectDataSetManagementList(DataSetManageVO paramVO) throws Exception{
        return (List<DataSetManageVO>) list("apt.DataSetManageDAO.selectDataSetManagementList", paramVO);
    }

    public int selectDataSetManagementListCount(DataSetManageVO paramVO) throws Exception{
        return (Integer) select("apt.DataSetManageDAO.selectDataSetManagementListCount", paramVO);
    }

    public int insertDataSetManagement(DataSetManageVO paramVO) throws Exception{
        return update("apt.DataSetManageDAO.insertDataSetManagement", paramVO);
    }

    /**
     * @Method Name        : selectSummary
     * @Method description : DataSet Summary Table 조회
     * @param              : DataSetManageVO
     * @return             : DataSetSummaryVO
     * @throws             : Exception
     */
    public DataSetSummaryVO selectSummary(DataSetManageVO paramVO) {
        return (DataSetSummaryVO) select("apt.DataSetManageDAO.selectSummary", paramVO);
    }

    /**
     * @Method Name        : selectEquityList
     * @Method description : DataSet Equity Table 조회
     * @param              : DataSetManageVO
     * @return             : List<DataSetEquityVO>
     * @throws             : Exception
     */
    public List<DataSetEquityVO> selectEquityList(DataSetManageVO paramVO) {
        return (List<DataSetEquityVO>) list("apt.DataSetManageDAO.balSelectEquityList", paramVO);
    }

    /**
     * @Method Name        : selectFundList
     * @Method description : DataSet Fund Table 조회
     * @param              : DataSetManageVO
     * @return             : List<DataSetFundVO>
     * @throws             : Exception
     */
    public List<DataSetFundVO> selectFundList(DataSetManageVO paramVO) {
        return (List<DataSetFundVO>) list("apt.DataSetManageDAO.balSelectFundList", paramVO);
    }

    /**
     * @Method Name        : selectEtcList
     * @Method description : DataSet Etc Table 조회
     * @param              : DataSetManageVO
     * @return             : List<DataSetEtcVO>
     * @throws             : Exception
     */
    public List<DataSetEtcVO> selectEtcList(DataSetManageVO paramVO) {
        return (List<DataSetEtcVO>) list("apt.DataSetManageDAO.balSelectEtcList", paramVO);
    }

    /**
     * @Method Name        : selectTransactionList
     * @Method description : DataSet Transaction Table 조회
     * @param              : DataSetManageVO
     * @return             : List<DataSetTransactionVO>
     * @throws             : Exception
     */
    public List<DataSetTransactionVO> selectTransactionList(DataSetManageVO paramVO) {
        return (List<DataSetTransactionVO>) list("apt.DataSetManageDAO.selectTransactionList", paramVO);
    }

    /**
     * @Method Name        : selectGroupList
     * @Method description : DataSet Group Table 조회
     * @param              : DataSetManageVO
     * @return             : List<DataSetGroupVO>
     * @throws             : Exception
     */
    public List<DataSetGroupVO> selectGroupList(DataSetManageVO paramVO) {
        return (List<DataSetGroupVO>) list("apt.DataSetManageDAO.selectGroupList", paramVO);
    }

    /**
     * @Method Name        : dataSetManagementDtl
     * @Method description : 개인의 상세 정보를 조회한다.
     * @param              : DataSetManageVO
     * @return             : DataSetManageVO
     * @throws             : Exception
     */
    public DataSetManageVO dataSetManagementDtl(DataSetManageVO paramVO) {
        return (DataSetManageVO) select("apt.DataSetManageDAO.dataSetManagementDtl",paramVO);
    }

    /**
     * Summary Table 수정
     */
    public int updateSummary(DataSetSummaryVO paramVO) {
        return (Integer) update("apt.DataSetManageDAO.updateSummary",paramVO);
    }

    /**
     * Equity Table 수정
     */
    public int updateEquity(DataSetEquityVO paramVO) {
        return (Integer) update("apt.DataSetManageDAO.updateEquity",paramVO);
    }

    /**
     * Equity Table 삭제
     */
    public int deleteEquity(DataSetEquityVO paramVO) {
        return (Integer) update("apt.DataSetManageDAO.deleteEquity",paramVO);
    }

    /**
     * Fund Table 수정
     */
    public int updateFund(DataSetFundVO paramVO) {
        return (Integer) update("apt.DataSetManageDAO.updateFund",paramVO);
    }

    /**
     * Fund Table 삭제
     */
    public int deleteFund(DataSetFundVO paramVO) {
        return (Integer) update("apt.DataSetManageDAO.deleteFund",paramVO);
    }

    /**
     * Etc Table 수정
     */
    public int updateEtc(DataSetEtcVO paramVO) {
        return (Integer) update("apt.DataSetManageDAO.updateEtc",paramVO);
    }

    /**
     * Etc Table 삭제
     */
    public int deleteEtc(DataSetEtcVO paramVO) {
        return (Integer) update("apt.DataSetManageDAO.deleteEtc",paramVO);
    }

    /**
     * Transaction Table 수정
     */
    public int updateTransaction(DataSetTransactionVO paramVO) {
        return (Integer) update("apt.DataSetManageDAO.updateTransaction",paramVO);
    }

    /**
     * Transaction Table 삭제
     */
    public int deleteTransaction(DataSetTransactionVO paramVO) {
        return (Integer) update("apt.DataSetManageDAO.deleteTransaction",paramVO);
    }

    /**
     * Group Table 수정
     */
    public int updateGroup(DataSetGroupVO paramVO) {
        return (Integer) update("apt.DataSetManageDAO.updateGroup",paramVO);
    }

    /**
     * Group Table 삭제
     */
    public int deleteGroup(DataSetGroupVO paramVO) {
        return (Integer) update("apt.DataSetManageDAO.deleteGroup", paramVO);
    }

    /**
     * Summary Table 삭제
     */
    public int deleteSummary(DataSetManageVO paramVO) {
        return (Integer) update("apt.DataSetManageDAO.deleteSummary", paramVO);
    }

    /**
     * @Method Name        : selectDataSetManagementExcel
     * @Method description : 테스트 데이터 Excel 목록
     * @param              : DataSetManageVO
     * @return             : DataSetManageVO
     * @throws             : Exception
     */
    public List<DataSetManageVO> selectDataSetManagementExcel(DataSetManageVO paramVO) {
        return (List<DataSetManageVO>) list("apt.DataSetManageDAO.selectDataSetManagementExcel", paramVO);
    }

    /**
     * Summary Table 복사
     */
    public int cloneSummaryInfo(DataSetSummaryVO summaryVO) {
        return (Integer) update("apt.DataSetManageDAO.cloneSummaryInfo",summaryVO);
    }

    /**
     * Equity Table 복사
     */
    public int cloneEquityInfo(DataSetEquityVO equityVO) {
        return (Integer) update("apt.DataSetManageDAO.cloneEquityInfo", equityVO);
    }

    /**
     * Fund Table 복사
     */
    public int cloneFundInfo(DataSetFundVO fundVO) {
        return (Integer) update("apt.DataSetManageDAO.cloneFundInfo",fundVO);
    }

    /**
     * Etc Table 복사
     */
    public int cloneEtcInfo(DataSetEtcVO etcVO) {
        return (Integer) update("apt.DataSetManageDAO.cloneEtcInfo",etcVO);
    }

    /**
     * Transaction Table 복사
     */
    public int cloneTransactionInfo(DataSetTransactionVO transactionVO) {
        return (Integer) update("apt.DataSetManageDAO.cloneTransactionVOInfo",transactionVO);
    }

    /**
     * Group Table 복사
     */
    public int cloneGroupInfo(DataSetGroupVO groupVO) {
        return (Integer) update("apt.DataSetManageDAO.cloneGroupInfo",groupVO);
    }


}
