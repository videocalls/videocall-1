package kr.co.koscom.oppf.apt.fix.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;


@SuppressWarnings("serial")
public class FixManageVO extends ComDefaultListVO{
    
    private String senderCompName;
    
    private String companyId;
    
    private String senderCompId;
    
    private String fixQueueId;
    
    private String useYnStatus;

	private List<String> useStatus;
	
	private String useStatusAllYn;
    
	private String createNm;
	
	private String updateNm;
    
    private String createTime;
    
    private String updateTime;

    private String searchDateFrom;
    
    private String searchDateTo;
    
    private String searchDateType;
    
    private String companyCodeId;
    
    private String companyNameKor;
    
    // fix Server 정보
    private String fixServerId;
    
    private String fixServerCode;
    
    // initiator 정보
    private String initServerId;
    
    private String initServerIp;
    
    private String initSenderCompId;
    

    private String fixStateCd;
    
    private String fixStateCdNm;
    
    private String initTargetCompId;

    // fix code table 조회용
    private String searchCode;
    private String searchId;
    private String codeId;
    private String codeValue;
    private String codeNm;

    //fix engine 
    private String  resultDt;
    private String  totCnt; 
    private String  sucCnt;
    private String  failCnt; 
    
    private String msgType;

	private String trafficTable;
	private String trafficTableCondition;

	private String connectionHost;
	private String connectionPort;

	private String socketHost;
	private String socketPort;
	
	private String sessionTargetCompId;
	
	private String sessionSenderCompId;

	private List<FixQueueVO> queuelist;
	
	public String getSenderCompName() {
		return senderCompName;
	}

	public void setSenderCompName(String senderCompName) {
		this.senderCompName = senderCompName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getSenderCompId() {
		return senderCompId;
	}

	public void setSenderCompId(String senderCompId) {
		this.senderCompId = senderCompId;
	}

	public String getSearchDateFrom() {
		return searchDateFrom;
	}

	public void setSearchDateFrom(String searchDateFrom) {
		this.searchDateFrom = searchDateFrom;
	}

	public String getSearchDateTo() {
		return searchDateTo;
	}

	public void setSearchDateTo(String searchDateTo) {
		this.searchDateTo = searchDateTo;
	}

	public String getUseYnStatus() {
		return useYnStatus;
	}

	public void setUseYnStatus(String useYnStatus) {
		this.useYnStatus = useYnStatus;
	}

	public String getSearchDateType() {
		return searchDateType;
	}

	public void setSearchDateType(String searchDateType) {
		this.searchDateType = searchDateType;
	}

	public String getFixQueueId() {
		return fixQueueId;
	}

	public void setFixQueueId(String fixQueueId) {
		this.fixQueueId = fixQueueId;
	}

	public String getCreateNm() {
		return createNm;
	}

	public void setCreateNm(String createNm) {
		this.createNm = createNm;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCompanyCodeId() {
		return companyCodeId;
	}

	public void setCompanyCodeId(String companyCodeId) {
		this.companyCodeId = companyCodeId;
	}

	public String getCompanyNameKor() {
		return companyNameKor;
	}

	public void setCompanyNameKor(String companyNameKor) {
		this.companyNameKor = companyNameKor;
	}

	public List<String> getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(List<String> useStatus) {
		this.useStatus = useStatus;
	}

	public String getUseStatusAllYn() {
		return useStatusAllYn;
	}

	public void setUseStatusAllYn(String useStatusAllYn) {
		this.useStatusAllYn = useStatusAllYn;
	}

	public String getUpdateNm() {
		return updateNm;
	}

	public void setUpdateNm(String updateNm) {
		this.updateNm = updateNm;
	}

	public String getInitServerId() {
		return initServerId;
	}

	public void setInitServerId(String initServerId) {
		this.initServerId = initServerId;
	}

	public String getInitServerIp() {
		return initServerIp;
	}

	public void setInitServerIp(String initServerIp) {
		this.initServerIp = initServerIp;
	}

	public String getInitSenderCompId() {
		return initSenderCompId;
	}

	public void setInitSenderCompId(String initSenderCompId) {
		this.initSenderCompId = initSenderCompId;
	}

	public String getFixServerId() {
		return fixServerId;
	}

	public void setFixServerId(String fixServerId) {
		this.fixServerId = fixServerId;
	}

	public String getFixServerCode() {
		return fixServerCode;
	}

	public void setFixServerCode(String fixServerCode) {
		this.fixServerCode = fixServerCode;
	}

	public String getInitTargetCompId() {
		return initTargetCompId;
	}

	public void setInitTargetCompId(String initTargetCompId) {
		this.initTargetCompId = initTargetCompId;
	}

	public String getFixStateCd() {
		return fixStateCd;
	}

	public void setFixStateCd(String fixStateCd) {
		this.fixStateCd = fixStateCd;
	}

	public String getFixStateCdNm() {
		return fixStateCdNm;
	}

	public void setFixStateCdNm(String fixStateCdNm) {
		this.fixStateCdNm = fixStateCdNm;
	}

	public String getSearchCode() {
		return searchCode;
	}

	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}

	public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getCodeNm() {
		return codeNm;
	}

	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	public String getResultDt() {
		return resultDt;
	}

	public void setResultDt(String resultDt) {
		this.resultDt = resultDt;
	}

	public String getTotCnt() {
		return totCnt;
	}

	public void setTotCnt(String totCnt) {
		this.totCnt = totCnt;
	}

	public String getSucCnt() {
		return sucCnt;
	}

	public void setSucCnt(String sucCnt) {
		this.sucCnt = sucCnt;
	}

	public String getFailCnt() {
		return failCnt;
	}

	public void setFailCnt(String failCnt) {
		this.failCnt = failCnt;
	}

	public String getTrafficTable() {
		return trafficTable;
	}

	public void setTrafficTable(String trafficTable) {
		this.trafficTable = trafficTable;
	}

	public String getTrafficTableCondition() {
		return trafficTableCondition;
	}

	public void setTrafficTableCondition(String trafficTableCondition) {
		this.trafficTableCondition = trafficTableCondition;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getConnectionHost() {
		return connectionHost;
	}

	public void setConnectionHost(String connectionHost) {
		this.connectionHost = connectionHost;
	}

	public String getConnectionPort() {
		return connectionPort;
	}

	public void setConnectionPort(String connectionPort) {
		this.connectionPort = connectionPort;
	}

	public String getSocketHost() {
		return socketHost;
	}

	public void setSocketHost(String socketHost) {
		this.socketHost = socketHost;
	}

	public String getSocketPort() {
		return socketPort;
	}

	public void setSocketPort(String socketPort) {
		this.socketPort = socketPort;
	}

	public List<FixQueueVO> getQueuelist() {
		return queuelist;
	}

	public void setQueuelist(List<FixQueueVO> queuelist) {
		this.queuelist = queuelist;
	}

	public String getSessionTargetCompId() {
		return sessionTargetCompId;
	}

	public void setSessionTargetCompId(String sessionTargetCompId) {
		this.sessionTargetCompId = sessionTargetCompId;
	}

	public String getSessionSenderCompId() {
		return sessionSenderCompId;
	}

	public void setSessionSenderCompId(String sessionSenderCompId) {
		this.sessionSenderCompId = sessionSenderCompId;
	}
}