package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

import kr.co.koscom.oppf.cmm.util.OppfStringUtil;

public class MsgTransactionViewVO {
	private String isinCode;
	private String transDate;
	private String transType;
	private Long changeAmt;
	private Long changeQty;
	private Long qty;
	private String isinName;
	private String comName;
	private String vtAccNo;

	public String getIsinName() {
		return isinName;
	}
	public void setIsinName(String isinName) {
		this.isinName = isinName;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getVtAccNo() {
		return vtAccNo;
	}
	public void setVtAccNo(String vtAccNo) {
		this.vtAccNo = vtAccNo;
	}
	public String getIsinCode() {
		return isinCode;
	}
	public void setIsinCode(String isinCode) {
		this.isinCode = isinCode;
	}
	public String getTransDate() {
		return OppfStringUtil.addMinusChar(transDate);
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public Long getChangeAmt() {
		return changeAmt;
	}
	public void setChangeAmt(Long changeAmt) {
		this.changeAmt = changeAmt;
	}
	public Long getChangeQty() {
		return changeQty;
	}
	public void setChangeQty(Long changeQty) {
		this.changeQty = changeQty;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
}
