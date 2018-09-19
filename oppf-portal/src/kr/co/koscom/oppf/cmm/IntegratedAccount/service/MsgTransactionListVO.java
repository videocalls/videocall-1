package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

import java.util.ArrayList;
import java.util.List;

public class MsgTransactionListVO {
	private List<MsgTransactionVO> transaction = new ArrayList<MsgTransactionVO>();

	public List<MsgTransactionVO> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<MsgTransactionVO> transaction) {
		this.transaction = transaction;
	}
	
}
