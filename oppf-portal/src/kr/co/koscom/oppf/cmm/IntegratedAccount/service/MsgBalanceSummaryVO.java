package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class MsgBalanceSummaryVO {
	private Long cashBalance;
	/*private Long d1;
	private Long d2;*/
	private Long substitute;
	private Long receivable;
	private Long subsMargin;
	private Long loanCredit;
	private Long valAtTrade;
	private Long valueAtCur;
	private Long proLoss;
	private Long totalAccVal;
	private Long cashAvWithdraw;
	
	public Long getCashBalance() {
		return cashBalance;
	}
	public void setCashBalance(Long cashBalance) {
		this.cashBalance = cashBalance;
	}
	/*public Long getD1() {
		return d1;
	}
	public void setD1(Long d1) {
		this.d1 = d1;
	}
	public Long getD2() {
		return d2;
	}
	public void setD2(Long d2) {
		this.d2 = d2;
	}*/
	public Long getSubstitute() {
		return substitute;
	}
	public void setSubstitute(Long substitute) {
		this.substitute = substitute;
	}
	public Long getReceivable() {
		return receivable;
	}
	public void setReceivable(Long receivable) {
		this.receivable = receivable;
	}
	public Long getSubsMargin() {
		return subsMargin;
	}
	public void setSubsMargin(Long subsMargin) {
		this.subsMargin = subsMargin;
	}
	public Long getLoanCredit() {
		return loanCredit;
	}
	public void setLoanCredit(Long loanCredit) {
		this.loanCredit = loanCredit;
	}
	public Long getValAtTrade() {
		return valAtTrade;
	}
	public void setValAtTrade(Long valAtTrade) {
		this.valAtTrade = valAtTrade;
	}
	public Long getValueAtCur() {
		return valueAtCur;
	}
	public void setValueAtCur(Long valueAtCur) {
		this.valueAtCur = valueAtCur;
	}
	public Long getProLoss() {
		return proLoss;
	}
	public void setProLoss(Long proLoss) {
		this.proLoss = proLoss;
	}
	public Long getTotalAccVal() {
		return totalAccVal;
	}
	public void setTotalAccVal(Long totalAccVal) {
		this.totalAccVal = totalAccVal;
	}
	public Long getCashAvWithdraw() {
		return cashAvWithdraw;
	}
	public void setCashAvWithdraw(Long cashAvWithdraw) {
		this.cashAvWithdraw = cashAvWithdraw;
	}
	
	
}
