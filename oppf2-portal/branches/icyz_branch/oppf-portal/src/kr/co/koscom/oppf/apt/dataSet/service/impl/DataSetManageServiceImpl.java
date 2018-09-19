package kr.co.koscom.oppf.apt.dataSet.service.impl;

import kr.co.koscom.oppf.apt.dataSet.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LSH on 2017. 2. 21..
 */
@Service("DataSetManageService")
public class DataSetManageServiceImpl implements DataSetManageService{
    @Resource(name="DataSetManageDAO")
    private DataSetManageDAO dataSetManageDAO;

    private String portforlioDataSetType = "G034001";
    private String balanceDataSetType = "G034002";

    /**
     * @Method Name        : selectDataSetManagementList
     * @Method description : 테스트 데이터 목록을 조회한다.
     * @param              : DataSetManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    @Override
    public Map<String, Object> selectDataSetManagementList(DataSetManageVO paramVO) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        List<DataSetManageVO> result = dataSetManageDAO.selectDataSetManagementList(paramVO);
        int resultCount = dataSetManageDAO.selectDataSetManagementListCount(paramVO);

        map.put("resultList", result);
        map.put("totCnt", resultCount);

        return map;
    }

    /**
     * @Method Name        : insertDataSetManagement
     * @Method description : 테스트 데이터 비활성화 상태인 경우 등록
     * @param              : DataSetManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Override
    public void insertDataSetManagement(DataSetManageVO paramVO) throws Exception {
        dataSetManageDAO.insertDataSetManagement(paramVO);
    }

    /**
     * @Method Name        : dataSetManagementDtl
     * @Method description : 개인의 상세 정보를 조회한다.
     * @param              : DataSetManageVO
     * @return             : DataSetManageVO
     * @throws             : Exception
     */
    @Override
    public DataSetManageVO dataSetManagementDtl(DataSetManageVO paramVO) throws Exception {
        DataSetManageVO dataSetManagementDtl = dataSetManageDAO.dataSetManagementDtl(paramVO);
        return dataSetManagementDtl;
    }

    /**
     * @Method Name        : dataSetResultList
     * @Method description : 개인의 계좌별 DataSet Table 을 조회한다
     * @param              : DataSetManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    @Override
    public Map<String, Object> dataSetResultList(DataSetManageVO paramVO) throws Exception {
        Map<String, Object> dataSetResultMap = new HashMap<String, Object>();


        DataSetSummaryVO selectSummary = dataSetManageDAO.selectSummary(paramVO);

        /*balance*/
        paramVO.setDatasetType(balanceDataSetType);
        List<DataSetEquityVO> balSelectEquityList = dataSetManageDAO.selectEquityList(paramVO);
        List<DataSetFundVO> balSelectFundList = dataSetManageDAO.selectFundList(paramVO);
        List<DataSetEtcVO> balSelectEtcList = dataSetManageDAO.selectEtcList(paramVO);

        /*portfolio*/
        paramVO.setDatasetType(portforlioDataSetType);
        List<DataSetEquityVO> portSelectEquityList = dataSetManageDAO.selectEquityList(paramVO);
        List<DataSetFundVO> portSelectFundList = dataSetManageDAO.selectFundList(paramVO);
        List<DataSetEtcVO> portSelectEtcList = dataSetManageDAO.selectEtcList(paramVO);

        List<DataSetTransactionVO> selectTransactionList = dataSetManageDAO.selectTransactionList(paramVO);
        List<DataSetGroupVO> selectInterestList = dataSetManageDAO.selectGroupList(paramVO);

        dataSetResultMap.put("selectSummary", selectSummary);

        dataSetResultMap.put("balSelectEquityList", balSelectEquityList);
        dataSetResultMap.put("balSelectFundList", balSelectFundList);
        dataSetResultMap.put("balSelectEtcList", balSelectEtcList);

        dataSetResultMap.put("portSelectEquityList", portSelectEquityList);
        dataSetResultMap.put("portSelectFundList", portSelectFundList);
        dataSetResultMap.put("portSelectEtcList", portSelectEtcList);

        dataSetResultMap.put("selectTransactionList", selectTransactionList);
        dataSetResultMap.put("selectInterestList", selectInterestList);

        return dataSetResultMap;
    }

    /**
     * DataSet Summary table 수정
     */
    @Override
    public void updateSummary(DataSetSummaryVO paramVO) throws Exception {
        dataSetManageDAO.updateSummary(paramVO);
    }

    /**
     * DataSet Equity table 수정
     */
    @Override
    public void updateEquity(DataSetEquityVO paramVO) throws Exception {
        dataSetManageDAO.updateEquity(paramVO);
    }

    /**
     * DataSet Equity table 삭제
     */
    @Override
    public void deleteEquity(DataSetEquityVO paramVO) throws Exception {
        dataSetManageDAO.deleteEquity(paramVO);
    }

    /**
     * DataSet Fund table 수정
     */
    @Override
    public void updateFund(DataSetFundVO paramVO) throws Exception {
        dataSetManageDAO.updateFund(paramVO);
    }

    /**
     * DataSet Fund table 삭제
     */
    @Override
    public void deleteFund(DataSetFundVO paramVO) throws Exception {
        dataSetManageDAO.deleteFund(paramVO);
    }

    /**
     * DataSet Etc table 수정
     */
    @Override
    public void updateEtc(DataSetEtcVO paramVO) throws Exception {
        dataSetManageDAO.updateEtc(paramVO);
    }

    /**
     * DataSet Etc table 삭제
     */
    @Override
    public void deleteEtc(DataSetEtcVO paramVO) throws Exception {
        dataSetManageDAO.deleteEtc(paramVO);
    }

    /**
     * DataSet Transaction table 수정
     */
    @Override
    public void updateTransaction(DataSetTransactionVO paramVO) throws Exception {
        dataSetManageDAO.updateTransaction(paramVO);
    }

    /**
     * DataSet Transaction table 삭제
     */
    @Override
    public void deleteTransaction(DataSetTransactionVO paramVO) throws Exception {
        dataSetManageDAO.deleteTransaction(paramVO);
    }

    /**
     * DataSet Group table 수정
     */
    @Override
    public void updateGroup(DataSetGroupVO paramVO) throws Exception {
        dataSetManageDAO.updateGroup(paramVO);
    }

    /**
     * DataSet Group table 삭제
     */
    @Override
    public void deleteGroup(DataSetGroupVO paramVO) throws Exception {
        dataSetManageDAO.deleteGroup(paramVO);
    }

    /**
     * DataSet Summary table 삭제
     */
    @Override
    public void deleteSummary(DataSetManageVO paramVO) {
        dataSetManageDAO.deleteSummary(paramVO);
    }

    /**
     * @Method Name        : selectMockUpServiceList
     * @Method description : 테스트 데이터 관리 DataSet table 삭제
     * @param              : DataSetManageVO
     * @return             :
     * @throws             : Exception
     */
    @Override
    @Transactional
    public void deleteGroupManager(DataSetManageVO paramVO) throws Exception {

        /*equityDelete*/
        DataSetEquityVO equityVO = new DataSetEquityVO();
        equityVO.setCustomerRegNumber(paramVO.getCustomerRegNumber());
        equityVO.setCompanyCodeId(paramVO.getCompanyCodeId());
        equityVO.setCustomerRealaccountNumber(paramVO.getCustomerRealaccountNumber());
        equityVO.setCustomerVtaccountNumber(paramVO.getCustomerVtaccountNumber());
        deleteEquity(equityVO);

        /*fundDelete*/
        DataSetFundVO fundVO = new DataSetFundVO();
        fundVO.setCustomerRegNumber(paramVO.getCustomerRegNumber());
        fundVO.setCompanyCodeId(paramVO.getCompanyCodeId());
        fundVO.setCustomerRealaccountNumber(paramVO.getCustomerRealaccountNumber());
        fundVO.setCustomerVtaccountNumber(paramVO.getCustomerVtaccountNumber());
        deleteFund(fundVO);

        /*etcDelete*/
        DataSetEtcVO etcVO = new DataSetEtcVO();
        etcVO.setCustomerRegNumber(paramVO.getCustomerRegNumber());
        etcVO.setCompanyCodeId(paramVO.getCompanyCodeId());
        etcVO.setCustomerRealaccountNumber(paramVO.getCustomerRealaccountNumber());
        etcVO.setCustomerVtaccountNumber(paramVO.getCustomerVtaccountNumber());
        deleteEtc(etcVO);

        /*transactionDelete*/
        DataSetTransactionVO transactionVO = new DataSetTransactionVO();
        transactionVO.setCustomerRegNumber(paramVO.getCustomerRegNumber());
        transactionVO.setCompanyCodeId(paramVO.getCompanyCodeId());
        transactionVO.setCustomerRealaccountNumber(paramVO.getCustomerRealaccountNumber());
        transactionVO.setCustomerVtaccountNumber(paramVO.getCustomerVtaccountNumber());
        deleteTransaction(transactionVO);

        /*groupDelete*/
        DataSetGroupVO groupVO = new DataSetGroupVO();
        groupVO.setCustomerRegNumber(paramVO.getCustomerRegNumber());
        groupVO.setCompanyCodeId(paramVO.getCompanyCodeId());
        groupVO.setCustomerRealaccountNumber(paramVO.getCustomerRealaccountNumber());
        groupVO.setCustomerVtaccountNumber(paramVO.getCustomerVtaccountNumber());
        deleteGroup(groupVO);

        /*summaryDelete*/
        deleteSummary(paramVO);
    }

    /**
     * DataSet Summary table 복사
     */
    @Override
    public void cloneSummaryInfo(DataSetSummaryVO summaryVO) throws Exception {
        dataSetManageDAO.cloneSummaryInfo(summaryVO);
    }

    /**
     * DataSet Equity table 복사
     */
    @Override
    @Transactional
    public void cloneEquityInfo(List<DataSetEquityVO> equityVOs, DataSetManageVO targetInfo) throws Exception {
        for(DataSetEquityVO data : equityVOs){
            data.setCustomerRegNumber(targetInfo.getCustomerRegNumber());
            data.setCompanyCodeId(targetInfo.getCompanyCodeId());
            data.setCustomerRealaccountNumber(targetInfo.getCustomerRealaccountNumber());
            data.setCustomerVtaccountNumber(targetInfo.getCustomerVtaccountNumber());

            dataSetManageDAO.cloneEquityInfo(data);
        }
    }

    /**
     * DataSet Fund table 복사
     */
    @Override
    @Transactional
    public void cloneFundInfo(List<DataSetFundVO> fundVOs, DataSetManageVO targetInfo) throws Exception {
        for(DataSetFundVO data : fundVOs){
            data.setCustomerRegNumber(targetInfo.getCustomerRegNumber());
            data.setCompanyCodeId(targetInfo.getCompanyCodeId());
            data.setCustomerRealaccountNumber(targetInfo.getCustomerRealaccountNumber());
            data.setCustomerVtaccountNumber(targetInfo.getCustomerVtaccountNumber());

            dataSetManageDAO.cloneFundInfo(data);
        }
    }

    /**
     * DataSet Etc table 복사
     */
    @Override
    @Transactional
    public void cloneEtcInfo(List<DataSetEtcVO> etcVOs, DataSetManageVO targetInfo) throws Exception {
        for(DataSetEtcVO data : etcVOs){
            data.setCustomerRegNumber(targetInfo.getCustomerRegNumber());
            data.setCompanyCodeId(targetInfo.getCompanyCodeId());
            data.setCustomerRealaccountNumber(targetInfo.getCustomerRealaccountNumber());
            data.setCustomerVtaccountNumber(targetInfo.getCustomerVtaccountNumber());

            dataSetManageDAO.cloneEtcInfo(data);
        }
    }

    /**
     * DataSet Transaction table 복사
     */
    @Override
    @Transactional
    public void cloneTransactionInfo(List<DataSetTransactionVO> transactionVOs, DataSetManageVO targetInfo) throws Exception {
        for(DataSetTransactionVO data : transactionVOs){
            data.setCustomerRegNumber(targetInfo.getCustomerRegNumber());
            data.setCompanyCodeId(targetInfo.getCompanyCodeId());
            data.setCustomerRealaccountNumber(targetInfo.getCustomerRealaccountNumber());
            data.setCustomerVtaccountNumber(targetInfo.getCustomerVtaccountNumber());

            dataSetManageDAO.cloneTransactionInfo(data);
        }
    }

    /**
     * DataSet Group(interest Table) 복사
     */
    @Override
    @Transactional
    public void cloneGroupInfo(List<DataSetGroupVO> groupVOs, DataSetManageVO targetInfo) throws Exception {
        for(DataSetGroupVO data : groupVOs){
            data.setCustomerRegNumber(targetInfo.getCustomerRegNumber());
            data.setCompanyCodeId(targetInfo.getCompanyCodeId());
            data.setCustomerRealaccountNumber(targetInfo.getCustomerRealaccountNumber());
            data.setCustomerVtaccountNumber(targetInfo.getCustomerVtaccountNumber());

            dataSetManageDAO.cloneGroupInfo(data);
        }
    }

    /**
     * @Method Name        : dataSetCopyAccount
     * @Method description : 테스트 데이터 관리 복사
     * @param              : DataSetManageVO
     * @return             :
     * @throws             : Exception
     */
    @Override
    @Transactional
    public void dataSetCopyAccount(DataSetManageVO paramVO) throws Exception {
        DataSetManageVO cloneParamVO = new DataSetManageVO();
        cloneParamVO.setCustomerRegNumber(paramVO.getCopyCustomerRegNumber());
        cloneParamVO.setCustomerVtaccountNumber(paramVO.getCopyCustomerVtaccountNumber());
        cloneParamVO.setCustomerRealaccountNumber(paramVO.getCopyCustomerRealaccountNumber());
        cloneParamVO.setCompanyCodeId(paramVO.getCopyCompanyCodeId());

        /*B 정보 select*/
        DataSetManageVO dataSetManagementDtl = dataSetManagementDtl(cloneParamVO);
        deleteGroupManager(dataSetManagementDtl);

        /*A정보 select*/
        Map<String, Object> map = dataSetResultList(paramVO);

        /*A summary*/
        DataSetSummaryVO summaryVO = (DataSetSummaryVO) map.get("selectSummary");

        summaryVO.setCustomerRegNumber(dataSetManagementDtl.getCustomerRegNumber());
        summaryVO.setCompanyCodeId(dataSetManagementDtl.getCompanyCodeId());
        summaryVO.setCustomerRealaccountNumber(dataSetManagementDtl.getCustomerRealaccountNumber());
        summaryVO.setCustomerVtaccountNumber(dataSetManagementDtl.getCustomerVtaccountNumber());
        cloneSummaryInfo(summaryVO);

        /*A Balance Equity*/
        List<DataSetEquityVO> balanceEquityVOs = (List<DataSetEquityVO>) map.get("balSelectEquityList");
        List<DataSetEquityVO> portfolioEquityVOs = (List<DataSetEquityVO>) map.get("portSelectEquityList");

        cloneEquityInfo(balanceEquityVOs, dataSetManagementDtl);
        cloneEquityInfo(portfolioEquityVOs, dataSetManagementDtl);

        /*A fund*/
        List<DataSetFundVO> balanceFundVOs = (List<DataSetFundVO>) map.get("balSelectFundList");
        List<DataSetFundVO> portfolioFundVOs = (List<DataSetFundVO>) map.get("portSelectFundList");

        cloneFundInfo(balanceFundVOs, dataSetManagementDtl);
        cloneFundInfo(portfolioFundVOs, dataSetManagementDtl);

        /*A etc*/
        List<DataSetEtcVO> balanceEtcVOs = (List<DataSetEtcVO>) map.get("balSelectEtcList");
        List<DataSetEtcVO> portfolioEtcVOs = (List<DataSetEtcVO>) map.get("portSelectEtcList");

        cloneEtcInfo(balanceEtcVOs, dataSetManagementDtl);
        cloneEtcInfo(portfolioEtcVOs, dataSetManagementDtl);

        /*A transaction*/
        List<DataSetTransactionVO> balanceTransactionVOs = (List<DataSetTransactionVO>) map.get("selectTransactionList");

        cloneTransactionInfo(balanceTransactionVOs, dataSetManagementDtl);

        /*A group*/
        List<DataSetGroupVO> balanceGroupVOs = (List<DataSetGroupVO>) map.get("selectInterestList");

        cloneGroupInfo(balanceGroupVOs, dataSetManagementDtl);


    }

    /**
     * @Method Name        : selectDataSetManagementExcel
     * @Method description : 테스트 데이터 관리 Excel 목록
     * @param              : DataSetManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    @Override
    public Map<String, Object> selectDataSetManagementExcel(DataSetManageVO paramVO) {
        List<DataSetManageVO> result = dataSetManageDAO.selectDataSetManagementExcel(paramVO);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultList", result);

        return map;
    }


}
