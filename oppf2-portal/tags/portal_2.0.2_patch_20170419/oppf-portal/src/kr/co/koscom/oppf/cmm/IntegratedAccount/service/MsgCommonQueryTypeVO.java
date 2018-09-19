package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class MsgCommonQueryTypeVO {
	private String assetType;
	private String rspType;
	private int count = 0;
	private String page;
	private int totalCnt;
	
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getRspType() {
		if(rspType == null) {
			rspType = "";
		}
		return rspType;
	}
	public void setRspType(String rspType) {
		this.rspType = rspType;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getPage() {
		if(page == null) {
			page = "";
		}
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
}
