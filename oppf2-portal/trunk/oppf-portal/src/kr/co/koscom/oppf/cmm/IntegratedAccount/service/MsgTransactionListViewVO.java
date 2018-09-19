package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

import java.util.ArrayList;
import java.util.List;

public class MsgTransactionListViewVO {
	private List<MsgTransactionViewVO> transaction = new ArrayList<MsgTransactionViewVO>();

	public List<MsgTransactionViewVO> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<MsgTransactionViewVO> transaction) {
		this.transaction = transaction;
	}
	
}
