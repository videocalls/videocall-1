package kr.co.koscom.oppf.apt.dataSet.service;

import java.util.List;
import java.util.Map;

/**
 * Created by LSH on 2017. 2. 21..
 */
public interface DataSetManageService {

    /**
     * @Method Name        : selectDataSetManagementList
     * @Method description : 테스트 데이터 목록을 조회한다.
     * @param              : DataSetManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    Map<String,Object> selectDataSetManagementList(DataSetManageVO paramVO) throws Exception;

    /**
     * @Method Name        : insertDataSetManagement
     * @Method description : 테스트 데이터 비활성화 상태인 경우 등록
     * @param              : DataSetManageVO
     * @return             : int
     * @throws             : Exception
     */
    void insertDataSetManagement(DataSetManageVO paramVO) throws Exception;

    /**
     * @Method Name        : dataSetManagementDtl
     * @Method description : 개인의 상세 정보를 조회한다.
     * @param              : DataSetManageVO
     * @return             : DataSetManageVO
     * @throws             : Exception
     */
    DataSetManageVO dataSetManagementDtl(DataSetManageVO paramVO) throws Exception;

    /**
     * @Method Name        : dataSetResultList
     * @Method description : 개인의 계좌별 DataSet Table 을 조회한다
     * @param              : DataSetManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    Map<String,Object> dataSetResultList(DataSetManageVO paramVO) throws Exception;

    /**
     * DataSet Summary table 수정
     */
    void updateSummary(DataSetSummaryVO paramVO) throws Exception;

    /**
     * DataSet Equity table 수정
     */
    void updateEquity(DataSetEquityVO paramVO) throws Exception;

    /**
     * DataSet Equity table 삭제
     */
    void deleteEquity(DataSetEquityVO paramVO) throws Exception;

    /**
     * DataSet Fund table 수정
     */
    void updateFund(DataSetFundVO paramVO) throws Exception;

    /**
     * DataSet Fund table 삭제
     */
    void deleteFund(DataSetFundVO paramVO) throws Exception;

    /**
     * DataSet Etc table 수정
     */
    void updateEtc(DataSetEtcVO paramVO) throws Exception;

    /**
     * DataSet Etc table 삭제
     */
    void deleteEtc(DataSetEtcVO paramVO) throws Exception;

    /**
     * DataSet Transaction table 수정
     */
    void updateTransaction(DataSetTransactionVO paramVO) throws Exception;

    /**
     * DataSet Transaction table 삭제
     */
    void deleteTransaction(DataSetTransactionVO paramVO) throws Exception;

    /**
     * DataSet Group table 수정
     */
    void updateGroup(DataSetGroupVO paramVO) throws Exception;

    /**
     * DataSet Group table 삭제
     */
    void deleteGroup(DataSetGroupVO paramVO) throws Exception;

    /**
     * DataSet Summary table 삭제
     */
    void deleteSummary(DataSetManageVO paramVO);

    /**
     * @Method Name        : selectDataSetManagementExcel
     * @Method description : 테스트 데이터 관리 Excel 목록
     * @param              : DataSetManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    Map<String,Object> selectDataSetManagementExcel(DataSetManageVO paramVO) throws Exception;

    /**
     * @Method Name        : selectMockUpServiceList
     * @Method description : 테스트 데이터 관리 DataSet table 삭제
     * @param              : DataSetManageVO
     * @return             :
     * @throws             : Exception
     */
    void deleteGroupManager(DataSetManageVO paramVO) throws Exception;

    /**
     * DataSet Summary table 복사
     */
    void cloneSummaryInfo(DataSetSummaryVO summaryVO) throws Exception;

    /**
     * DataSet Equity table 복사
     */
    void cloneEquityInfo(List<DataSetEquityVO> equityVOs, DataSetManageVO targetInfo) throws Exception;

    /**
     * DataSet Fund table 복사
     */
    void cloneFundInfo(List<DataSetFundVO> fundVOs, DataSetManageVO targetInfo) throws Exception;

    /**
     * DataSet Etc table 복사
     */
    void cloneEtcInfo(List<DataSetEtcVO> etcVOs, DataSetManageVO targetInfo) throws Exception;

    /**
     * DataSet Transaction table 복사
     */
    void cloneTransactionInfo(List<DataSetTransactionVO> transactionVOs, DataSetManageVO targetInfo) throws Exception;

    /**
     * DataSet Group table 복사
     */
    void cloneGroupInfo(List<DataSetGroupVO> groupVOs, DataSetManageVO targetInfo) throws Exception;

    /**
     * @Method Name        : dataSetCopyAccount
     * @Method description : 테스트 데이터 관리 복사
     * @param              : DataSetManageVO
     * @return             :
     * @throws             : Exception
     */
    void dataSetCopyAccount(DataSetManageVO paramVO) throws Exception;
}
