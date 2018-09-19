package kr.co.koscom.oppf.cmm.tsa.service;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmTsaVO.java
* @Comment  : [TSA연계後결과]정보관리를 위한 VO 클래스
* @author   : 포털 이환덕
* @since    : 2016.06.10
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.10  이환덕        최초생성
*
*/
public class CmmTsaResVO{
    
    private String timestamp;
    private String hashValue;
    private String query;
    private String reply;
    
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public String getHashValue() {
        return hashValue;
    }
    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }
    public String getQuery() {
        return query;
    }
    public void setQuery(String query) {
        this.query = query;
    }
    public String getReply() {
        return reply;
    }
    public void setReply(String reply) {
        this.reply = reply;
    }
    
    

}