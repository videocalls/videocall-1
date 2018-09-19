package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class MsgTransactionHistoryResponseBodyVO {
	private MsgCommonQueryResultVO queryResult = new MsgCommonQueryResultVO();
	private MsgTransactionQueryParameterVO queryParams = new MsgTransactionQueryParameterVO();
	
	public MsgCommonQueryResultVO getQueryResult() {
		return queryResult;
	}
	public void setQueryResult(MsgCommonQueryResultVO queryResult) {
		this.queryResult = queryResult;
	}
	public MsgTransactionQueryParameterVO getQueryParams() {
		return queryParams;
	}
	public void setQueryParams(MsgTransactionQueryParameterVO queryParams) {
		this.queryParams = queryParams;
	}
}
