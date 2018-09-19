package kr.co.koscom.oppf.cmm.util;

import kr.co.koscom.oppf.apt.stock.service.StockItemVO;
import kr.co.koscom.oppf.apt.stock.service.impl.StockManagementDAO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.ComCompanyCodeVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.impl.SvcApplDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lee
 * @date 2017-03-13
 */
@Component
public class CommonFunction {

    private static final Map<String, List<ComCompanyCodeVO>> companyGroup = new HashMap<String, List<ComCompanyCodeVO>>();

    private static final Map<String, List<StockItemVO>> stockGroup = new HashMap<String, List<StockItemVO>>();

    private static long companyGroupsLoadTime;

    private static long isinGroupsLoadTime;

    private static final long groupsCacheDuration = 3600 * 1000L;

    private static SvcApplDAO svcApplDAO;

    public static SvcApplDAO getSvcApplDAO(){ return svcApplDAO; }

    @Autowired(required=true)
    public void setDao(SvcApplDAO svcApplDAO)
    {
        this.svcApplDAO = svcApplDAO;
    }

    private static StockManagementDAO StockManagementDAO;

    public static StockManagementDAO getStockManagementDAO(){ return StockManagementDAO; }

    @Autowired(required=true)
    public void setStockManagementDAO(StockManagementDAO StockManagementDAO){ this.StockManagementDAO = StockManagementDAO; }

    public static String companyName(String companyCode) throws Exception {

        if(null == companyCode || "".equals(companyCode))
            return companyCode;

        String companyName = "";
        long now = System.currentTimeMillis();

        // 데이터가 적재되지 않았으면 데이터 가져오기 (10분단위)
        if (companyGroup.isEmpty() || now - companyGroupsLoadTime > groupsCacheDuration) {
            synchronized (companyGroup) {
                if (companyGroup.isEmpty() || now - companyGroupsLoadTime > groupsCacheDuration) {
                    ComCompanyCodeVO comCompanyCodeVO = new ComCompanyCodeVO();
                    comCompanyCodeVO.setCompanyCodeKind("G014001");
                    List<ComCompanyCodeVO> comCompanyCodeVOList = getSvcApplDAO().selectComCompanyCodeList(comCompanyCodeVO);
                    companyGroup.clear();
                    companyGroup.putAll(comCompanyCodeVOList.stream().collect(Collectors.groupingBy(ComCompanyCodeVO::getCompanyCodeId)));
                    companyGroupsLoadTime = now;
                }
            }
        }
        companyName =  companyGroup.containsKey(companyCode) ? companyGroup.get(companyCode).get(0).getCompanyNameKorAlias() : companyCode;
        return companyName;
    }

    public static String isinName(String isinCode) throws Exception {

        if(null == isinCode || "".equals(isinCode))
            return isinCode;

        String isinName = "";
        long now = System.currentTimeMillis();

        // 데이터가 적재되지 않았으면 데이터 가져오기 (10분단위)
        if (stockGroup.isEmpty() || now - isinGroupsLoadTime > groupsCacheDuration) {
            synchronized (stockGroup) {
                if (stockGroup.isEmpty() || now - isinGroupsLoadTime > groupsCacheDuration) {
                    List<StockItemVO> stockItemVOs = getStockManagementDAO().selectStockItemList();
                    stockGroup.clear();
                    stockGroup.putAll(stockItemVOs.stream().collect(Collectors.groupingBy(StockItemVO::getIsuCd)));
                    isinGroupsLoadTime = now;
                }
            }
        }
        isinName =  stockGroup.containsKey(isinCode) ? stockGroup.get(isinCode).get(0).getIsuKorNm() : isinCode;
        return isinName;
    }

    public static String equityAssetTypeName(String assetType) throws Exception {

        if(null == assetType || "".equals(assetType))
            return assetType;

        String assetTypeName = "";

        switch (assetType){
            case "KSP": assetTypeName = "유가증권";
                break;
            case "KDQ": assetTypeName = "코스닥";
                break;
            case "ETF": assetTypeName = "ETF";
                break;
            case "FUT": assetTypeName = "선물";
                break;
            case "OPT": assetTypeName = "옵션";
                break;
            case "ELW": assetTypeName = "ELW";
                break;
            case "ETC": assetTypeName = "기타";
                break;
            default: assetTypeName = assetType;
                break;
        }
        return assetTypeName;
    }

    public static String etcAssetTypeName(String assetType) throws Exception {

        if(null == assetType || "".equals(assetType))
            return assetType;

        String assetTypeName = "";

        switch (assetType){
            case "BOND": assetTypeName = "채권";
                break;
            case "STB": assetTypeName = "사채";
                break;
            case "CRP": assetTypeName = "약정식RP";
                break;
            case "RRP": assetTypeName = "수시RP";
                break;
            case "WRT": assetTypeName = "워런트";
                break;
            default: assetTypeName = assetType;
                break;
        }
        return assetTypeName;
    }

    public static String transTypeName(String transType) throws Exception {

        if(null == transType || "".equals(transType))
            return transType;

        String transTypeName = "";

        switch (transType){
            case "BID": transTypeName = "매도";
                break;
            case "ASK": transTypeName = "매수";
                break;
            case "DEP": transTypeName = "이체입금";
                break;
            case "WID": transTypeName = "이체출금";
                break;
            default: transTypeName = transType;
                break;
        }
        return transTypeName;
    }

}
