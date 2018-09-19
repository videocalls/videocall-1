package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class MsgCommonQueryResultVO {
	private int totalCnt;
	private int count;
	private String page;
	
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
}
