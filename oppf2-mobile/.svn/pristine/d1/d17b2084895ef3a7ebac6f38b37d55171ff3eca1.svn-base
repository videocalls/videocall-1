package kr.co.koscom.oppfm.cmm.util;

import kr.co.koscom.oppfm.cmm.dao.CommonMapper;
import kr.co.koscom.oppfm.cmm.model.StockItemVO;
import kr.co.koscom.oppfm.commoncode.dao.CommonCodeMapper;
import kr.co.koscom.oppfm.commoncode.model.CommonCompanyCodeRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lee
 * @date 2017-05-24
 */
@Component
public class CommonFunctionUtil {

    private static final Map<String, List<CommonCompanyCodeRes>> companyGroup = new HashMap<String, List<CommonCompanyCodeRes>>();
    private static final Map<String, List<StockItemVO>> stockGroup = new HashMap<String, List<StockItemVO>>();
    private static long companyGroupsLoadTime;
    private static long isinGroupsLoadTime;
    private static final long groupsCacheDuration = 3600 * 1000L;
    private static CommonCodeMapper commonCodeMapper;
    public static CommonCodeMapper getCommonCodeMapper(){ return commonCodeMapper; }
    @Autowired(required=true)
    public void setCommonCodeMapper(CommonCodeMapper commonCodeMapper)
    {
        this.commonCodeMapper = commonCodeMapper;
    }
    private static CommonMapper commonMapper;
    public static CommonMapper getCommonMapper(){ return commonMapper; }
    @Autowired(required=true)
    public void setCommonMapper(CommonMapper commonMapper){ this.commonMapper = commonMapper; }

    public static String companyName(String companyCode) throws Exception {

        if(null == companyCode || "".equals(companyCode))
            return companyCode;

        String companyName = "";
        long now = System.currentTimeMillis();

        // 데이터가 적재되지 않았으면 데이터 가져오기 (10분단위)
        if (companyGroup.isEmpty() || now - companyGroupsLoadTime > groupsCacheDuration) {
            synchronized (companyGroup) {
                if (companyGroup.isEmpty() || now - companyGroupsLoadTime > groupsCacheDuration) {
                    String companyCodeKind = "G014001";
                    List<CommonCompanyCodeRes> companyList = commonCodeMapper.selectCompanyList(companyCodeKind);
                    companyGroup.clear();
                    companyGroup.putAll(companyList.stream().collect(Collectors.groupingBy(CommonCompanyCodeRes::getCompanyCodeId)));
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
                    List<StockItemVO> stockItemVOs = commonMapper.selectStockItemList();
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
