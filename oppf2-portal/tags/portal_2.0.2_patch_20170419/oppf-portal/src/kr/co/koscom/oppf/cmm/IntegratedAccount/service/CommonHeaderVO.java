package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class CommonHeaderVO {
	private String reqIdPlatform;
	private String reqIdConsumer;
	private String ci;
	private String comCode;
	private String comName;

	public String getReqIdConsumer() {
		return reqIdConsumer;
	}
	public void setReqIdConsumer(String reqIdConsumer) {
		this.reqIdConsumer = reqIdConsumer;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	public String getReqIdPlatform() {
		return reqIdPlatform;
	}
	public void setReqIdPlatform(String reqIdPlatform) {
		this.reqIdPlatform = reqIdPlatform;
	}
	public String getComCode() {
		return comCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
}
