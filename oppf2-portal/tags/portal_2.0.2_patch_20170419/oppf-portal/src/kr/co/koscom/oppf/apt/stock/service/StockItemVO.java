package kr.co.koscom.oppf.apt.stock.service;

/**
 * StockItemVO
 * <p>
 * Created by chungyeol.kim on 2017-03-08.
 */
public class StockItemVO {
    private String isuSrtCd;
    private String isuCd;
    private String isuKorNm;
    private String isuKorAbbr;
    private String map_to;
    private String marketCd;

    public String getIsuSrtCd() {
        return isuSrtCd;
    }

    public void setIsuSrtCd(String isuSrtCd) {
        this.isuSrtCd = isuSrtCd;
    }

    public String getIsuCd() {
        return isuCd;
    }

    public void setIsuCd(String isuCd) {
        this.isuCd = isuCd;
    }

    public String getIsuKorNm() {
        return isuKorNm;
    }

    public void setIsuKorNm(String isuKorNm) {
        this.isuKorNm = isuKorNm;
    }

    public String getIsuKorAbbr() {
        return isuKorAbbr;
    }

    public void setIsuKorAbbr(String isuKorAbbr) {
        this.isuKorAbbr = isuKorAbbr;
    }

    public String getMap_to() {
        return map_to;
    }

    public void setMap_to(String map_to) {
        this.map_to = map_to;
    }

    public String getMarketCd() {
        return marketCd;
    }

    public void setMarketCd(String marketCd) {
        this.marketCd = marketCd;
    }
}
